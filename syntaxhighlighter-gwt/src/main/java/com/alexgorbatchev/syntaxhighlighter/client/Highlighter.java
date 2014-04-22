package com.alexgorbatchev.syntaxhighlighter.client;

import java.util.HashMap;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.PreElement;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class Highlighter extends Widget implements HasText {
	
	private static HashMap<Brush, BrushImpl> loadedBrushes;
	private static HashMap<BrushImpl, Highlighter> loadedHighlighters;
	
	static {
		loadedBrushes = new HashMap<Brush, BrushImpl>();
		loadedHighlighters = new HashMap<BrushImpl, Highlighter>();
	}
	
	public static void loadBrushes(Brush... brushes) {
		for (Brush b : brushes) {
			if (!loadedBrushes.containsKey(b)) {
				BrushImpl bi = BrushImpl.load(b);
				loadedBrushes.put(b, bi);
				Highlighter h = new Highlighter(b);
				loadedHighlighters.put(bi, h);
			}
		}
	}
	
	private BrushImpl brush;
	private Instance myself;
	
	private Highlighter(Brush brush) {
		if (loadedBrushes.containsKey(brush)) {
			this.brush = loadedBrushes.get(brush);
		}
		setElement(Document.get().createDivElement());
		myself = new Instance(this, Param.getDefaults(), this.brush, "");
		
	}
	
	public Highlighter(Brush brush, String text) {
		if (loadedBrushes.containsKey(brush)) {
			this.brush = loadedBrushes.get(brush);
		}
		setElement(Document.get().createDivElement());
		myself = new Instance(this, loadedHighlighters.get(this.brush).myself.getParams(), this.brush, text);
		addAttachHandler(new AttachEvent.Handler() {
			@Override
			public void onAttachOrDetach(final AttachEvent event) {
				if (event.isAttached()) {
					if (isBrushLoaded()) {
						highlight();
					}
					else {
						Scheduler.get().scheduleDeferred(new ScheduledCommand() {
							
							@Override
							public void execute() {
								onAttachOrDetach(event);
							}
						});
					}
				}
			}
		});
	}
	
	public boolean isBrushLoaded() {
		return Highlighter.this.brush.isLoaded();
	}
	
	@Override
	public String getText() {
		return myself.getCode();
	}
	
	@Override
	public void setText(String text) {
		myself.setCode(text);
		highlight();
	}
	
	/**
	 * Creates the {@code <pre>} tag that will be used to make the SyntaxHighlighter highlight the code.
	 * 
	 * @param params
	 *            the parameters to set as the {@code <pre>} tag's class attribute.
	 * @param code
	 *            the code to place in the {@code <pre>} tag to be highlighter.
	 */
	void makePre(String params, String code) {
		PreElement pre = Document.get().createPreElement();
		if (Window.Navigator.getUserAgent().contains("msie") || Window.Navigator.getUserAgent().contains("MSIE")) {
			code = code.replace("\r\n", "\n").replace("\n", "\r\n");
			
		}
		pre.setInnerHTML(code.replace("<", "&lt;"));
		pre.setClassName(params);
		replElement(pre);
	}
	
	/**
	 * Highlights the current instance, if possible.
	 */
	public void highlight() {
		myself.highlight();
	}
	
	public void replElement(Element element) {
		if (getElement().hasChildNodes()) {
			getElement().replaceChild(element, getElement().getFirstChild());
		}
		else {
			getElement().appendChild(element);
		}
	}
	
	/**
	 * Tests to see if this Highlighter is equal to some other Object. The other Object must be a Highlighter, and have
	 * the same Brush & code set for it to be true.
	 * 
	 * @param obj
	 *            the object to test equality with.
	 * @return true if equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Highlighter other = (Highlighter) obj;
		if (this.myself != other.myself && (this.myself == null || !this.myself.equals(other.myself))) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the hash code for this Highlighter, based on the Brush and code for this Highlighter.
	 * 
	 * @return the hashcode.
	 */
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 53 * hash + (this.myself != null ? this.myself.hashCode() : 0);
		return hash;
	}
	
}
