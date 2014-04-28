package com.alexgorbatchev.syntaxhighlighter.client;

import java.util.HashSet;
import java.util.Map;

import com.alexgorbatchev.syntaxhighlighter.client.brushes.Brush;
import com.alexgorbatchev.syntaxhighlighter.client.core.CoreResources;
import com.alexgorbatchev.syntaxhighlighter.client.themes.Theme;
import com.alexgorbatchev.syntaxhighlighter.client.themes.Themes;
import com.google.gwt.core.client.GWT;
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

/**
 * 
 * @author Matt Davis
 * based on original code from https://code.google.com/p/gwt-syntaxhighlighter/
 * by
 * @author Xlorep DarkHelm 
 *
 */
public class SyntaxHighlighter extends Widget implements HasText {
	
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
			CoreResources.INSTANCE.gwtFixCss().ensureInjected();
			
			CssResource themeCssResource = theme.getThemeCssResource();
			if (themeCssResource != null) {
				themeCssResource.ensureInjected();
			}
			
			String coreScript = CoreResources.INSTANCE.coreJavascript().getText();
			ScriptInjector.fromString(coreScript).inject();
			
			init = true;
		}
	}
	
	private static HashSet<String> brushesLoaded = new HashSet<String>();
	
	private static void loadBrushIfNeeded(Brush brush) {
		if (!brushesLoaded.contains(brush.getAlias())) {
			GWT.log("Loading brush <" + brush.getAlias() + ">");
			TextResource brushJsTextResource = brush.getBrushJsTextResource();
			if (brushJsTextResource != null) {
				String brushJs = brushJsTextResource.getText();
				ScriptInjector.fromString(brushJs).inject();
			}
			brushesLoaded.add(brush.getAlias());
		}
	}
	
	private String text;
	private Map<Param, String> params;
	private Brush brush;
	
	private Element highlighterElement;
	
	public SyntaxHighlighter(Brush brush, String text) {
		if (!init) {
			init();
		}
		
		params = Param.getDefaults();
		this.text = text;
		this.brush = brush;
		loadBrushIfNeeded(brush);
		
		setElement(Document.get().createDivElement());
		
		addAttachHandler(new AttachEvent.Handler() {
			@Override
			public void onAttachOrDetach(final AttachEvent event) {
				
				if (event.isAttached()) {
					highlight();
				}
			}
		});
	}
	
	public Brush getBrush() {
		return brush;
	}
	
	@Override
	public String getText() {
		return text;
	}
	
	@Override
	public void setText(String text) {
		setText(text, true);
	}
	
	public void setText(String text, boolean highlight) {
		this.text = text;
		if (highlight) {
			highlight();
		}
	}
	
	public boolean isAutoLinks() {
		return Boolean.valueOf(params.get(Param.AUTO_LINKS));
	}
	
	public void setAutoLinks(boolean autoLinks) {
		setParam(Param.AUTO_LINKS, autoLinks);
	}
	
	public String getClassName() {
		return params.get(Param.CLASS_NAME);
	}
	
	public void setClassName(String className) {
		setParam(Param.CLASS_NAME, className);
	}
	
	public boolean isCollapse() {
		return Boolean.valueOf(params.get(Param.COLLAPSE));
	}
	
	public void setCollapse(boolean collapse) {
		setParam(Param.COLLAPSE, collapse);
	}
	
	public int getFirstLine() {
		return Integer.valueOf(params.get(Param.FIRST_LINE));
	}
	
	public void setFirstLine(int firstLine) {
		setParam(Param.FIRST_LINE, firstLine);
	}
	
	public boolean isGutter() {
		return Boolean.valueOf(params.get(Param.GUTTER));
	}
	
	public void setGutter(boolean gutter) {
		setParam(Param.GUTTER, gutter);
	}
	
	public boolean isHtmlScript() {
		return Boolean.valueOf(params.get(Param.HTML_SCRIPT));
	}
	
	public boolean isSmartTabs() {
		return Boolean.valueOf(params.get(Param.SMART_TABS));
	}
	
	public void setSmartTabs(boolean smartTabs) {
		setParam(Param.SMART_TABS, smartTabs);
	}
	
	public int getTabSize() {
		return Integer.valueOf(params.get(Param.TAB_SIZE));
	}
	
	public void setTabSize(int tabSize) {
		setParam(Param.TAB_SIZE, tabSize);
	}
	
	public boolean isToolbar() {
		return Boolean.valueOf(params.get(Param.TOOLBAR));
	}
	
	public void setToolbar(boolean toolbar) {
		setParam(Param.TOOLBAR, toolbar);
	}
	
	@Override
	public String getTitle() {
		return params.get(Param.TITLE);
	}
	
	@Override
	public void setTitle(String title) {
		setParam(Param.TITLE, title);
	}
	
	public void highlight() {
		
		if (text != null && !text.trim().isEmpty()) {
			makePre(createParams(), text);
			
			Element newElement = getElement().getFirstChildElement();
			doHighlight(newElement);
			newElement = getElement().getFirstChildElement();
			
			if (newElement.hasChildNodes() && newElement.getFirstChildElement().getId().startsWith("highlighter_")) {
				this.highlighterElement = newElement.getFirstChildElement();
				replElement(highlighterElement);
			}
			else {
				GWT.log("Failed to highlight element");
			}
		}
	}
	
	private void setParam(Param param, Object value) {
		// String strValue = Param.stringValue(value);
		String strValue = String.valueOf(value);
		if (value.getClass().isArray()) {
			strValue = "[" + strValue + "]";
		}
		params.put(param, strValue);
	}
	
	private void makePre(String params, String code) {
		PreElement pre = Document.get().createPreElement();
		if (Window.Navigator.getUserAgent().contains("msie") || Window.Navigator.getUserAgent().contains("MSIE")) {
			code = code.replace("\r\n", "\n").replace("\n", "\r\n");
			
		}
		pre.setInnerHTML(code.replace("<", "&lt;"));
		pre.setClassName(params);
		replElement(pre);
	}
	
	private void replElement(Element element) {
		if (getElement().hasChildNodes()) {
			getElement().replaceChild(element, getElement().getFirstChild());
		}
		else {
			getElement().appendChild(element);
		}
	}
	
	/**
	 * Creates the String for the list of parameters to be used in the {@code <div>} element's class.
	 * 
	 * @return the {@code <div>} element's parameters.
	 */
	private String createParams() {
		String ret = "brush: " + brush.getAlias() + "; " + Param.makeString(params);
		return ret;
	}
	
	/**
	 * JSNI method to perform a highlight of the Instance(s) that need to be highlighted.
	 */
	private static native void doHighlight(Element newElement)
	/*-{
		SyntaxHighlighter.highlight({}, newElement);
	}-*/;
}
