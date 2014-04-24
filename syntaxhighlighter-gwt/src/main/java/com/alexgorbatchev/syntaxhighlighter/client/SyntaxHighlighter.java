package com.alexgorbatchev.syntaxhighlighter.client;

import java.util.HashSet;

import com.alexgorbatchev.syntaxhighlighter.client.brushes.Brush;
import com.alexgorbatchev.syntaxhighlighter.client.core.CoreResources;
import com.alexgorbatchev.syntaxhighlighter.client.themes.Theme;
import com.alexgorbatchev.syntaxhighlighter.client.themes.Themes;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.PreElement;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class SyntaxHighlighter extends Widget implements HasText {
	
	private static HashSet<String> brushesLoaded = new HashSet<String>();
	
	private static boolean init;
	
	/**
	 * Initialize with {@link Themes#Default}
	 * Initialize methods may only be called once and are called automatically when first SyntaxHighlighter
	 * is created with {@link Themes#Default} if they have not already been called
	 */
	public static void init() {
		init(Themes.Default);
	}
	
	/**
	 * Initialize with {@link Themes} given
	 * Initialize methods may only be called once and are called automatically when first SyntaxHighlighter
	 * is created with {@link Themes#Default} if they have not already been called
	 */
	public static void init(Themes theme) {
		init((Theme) theme);
	}
	
	/**
	 * Use this method for custom themes by implement the Theme interface 
	 * Initialize methods may only be called once and are called automatically when first SyntaxHighlighter
	 * is created with {@link Themes#Default} if they have not already been called
	 */
	public static void init(Theme theme) {
		if (!init) {
			CoreResources.INSTANCE.coreCss().ensureInjected();
			CoreResources.INSTANCE.gwtFixCss().ensureInjected();
			
			CssResource themeCssResource = theme.getThemeCssResource();
			if (themeCssResource != null) {
				themeCssResource.ensureInjected();
			}
			
			String coreScript = CoreResources.INSTANCE.coreJavascript().getText();
			ScriptInjector.fromString(coreScript).setRemoveTag(false).inject();
			
			init = true;
		}
	}
	
	private Instance myself;
	
	public SyntaxHighlighter(Brush brush, String text) {
		if (!init) {
			init();
		}
		
		if (!brushesLoaded.contains(brush.getAlias())) {
			TextResource brushJsTextResource = brush.getBrushJsTextResource();
			if (brushJsTextResource != null) {
				String brushJs = brushJsTextResource.getText();
				ScriptInjector.fromString(brushJs).inject();
			}
		}
		
		setElement(Document.get().createDivElement());
		myself = new Instance(this, Param.getDefaults(), brush, "");
		
		addAttachHandler(new AttachEvent.Handler() {
			@Override
			public void onAttachOrDetach(final AttachEvent event) {
				if (event.isAttached()) {
					highlight();
				}
			}
		});
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
}
