package me.rjb.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.TextResource;
import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLPreElement;
import me.rjb.client.brushes.Brush;
import me.rjb.client.core.CoreResources;
import me.rjb.client.themes.Theme;
import me.rjb.client.themes.Themes;

import java.util.HashSet;
import java.util.Map;

import static elemental2.dom.DomGlobal.document;
import static elemental2.dom.DomGlobal.window;

/**
 * @author Matt Davis
 * based on original code from https://code.google.com/p/gwt-syntaxhighlighter/
 * by
 * @author Xlorep DarkHelm
 */
public class SyntaxHighlighter {

    private static boolean init=false;

    private HTMLDivElement element;

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

    public static SyntaxHighlighter create(Brush brush, String text){
        return new SyntaxHighlighter(brush, text);
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

        element= (HTMLDivElement) document.createElement("div");


    }

    public Brush getBrush() {
        return brush;
    }

    public String getText() {
        return text;
    }

    public SyntaxHighlighter setText(String text) {
        setText(text, true);
        return this;
    }

    public SyntaxHighlighter setText(String text, boolean highlight) {
        this.text = text;
        if (highlight) {
            highlight();
        }

        return this;
    }

    public boolean isAutoLinks() {
        return Boolean.valueOf(params.get(Param.AUTO_LINKS));
    }

    public SyntaxHighlighter setAutoLinks(boolean autoLinks) {
        setParam(Param.AUTO_LINKS, autoLinks);
        return this;
    }

    public String getClassName() {
        return params.get(Param.CLASS_NAME);
    }

    public SyntaxHighlighter setClassName(String className) {
        setParam(Param.CLASS_NAME, className);
        return this;
    }

    public boolean isCollapse() {
        return Boolean.valueOf(params.get(Param.COLLAPSE));
    }

    public SyntaxHighlighter setCollapse(boolean collapse) {
        setParam(Param.COLLAPSE, collapse);
        return this;
    }

    public int getFirstLine() {
        return Integer.valueOf(params.get(Param.FIRST_LINE));
    }

    public SyntaxHighlighter setFirstLine(int firstLine) {
        setParam(Param.FIRST_LINE, firstLine);
        return this;
    }

    public boolean isGutter() {
        return Boolean.valueOf(params.get(Param.GUTTER));
    }

    public SyntaxHighlighter setGutter(boolean gutter) {
        setParam(Param.GUTTER, gutter);
        return this;
    }

    public boolean isHtmlScript() {
        return Boolean.valueOf(params.get(Param.HTML_SCRIPT));
    }

    public boolean isSmartTabs() {
        return Boolean.valueOf(params.get(Param.SMART_TABS));
    }

    public SyntaxHighlighter setSmartTabs(boolean smartTabs) {
        setParam(Param.SMART_TABS, smartTabs);
        return this;
    }

    public int getTabSize() {
        return Integer.valueOf(params.get(Param.TAB_SIZE));
    }

    public SyntaxHighlighter setTabSize(int tabSize) {
        setParam(Param.TAB_SIZE, tabSize);
        return this;
    }

    public boolean isToolbar() {
        return Boolean.valueOf(params.get(Param.TOOLBAR));
    }

    public SyntaxHighlighter setToolbar(boolean toolbar) {
        setParam(Param.TOOLBAR, toolbar);
        return this;
    }

    public SyntaxHighlighter highlight() {

        if (text != null && !text.trim().isEmpty()) {
            makePre(createParams(), text);

            elemental2.dom.Element newElement = element.firstElementChild;
            doHighlight(newElement);
            newElement = element.firstElementChild;

            if (newElement.hasChildNodes() && newElement.firstElementChild.getAttribute("id").startsWith("highlighter_")) {
                this.highlighterElement = newElement.firstElementChild;
                replElement(highlighterElement);
            } else {
                GWT.log("Failed to highlight element");
            }
        }

        return this;
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
        HTMLPreElement pre = (HTMLPreElement) document.createElement("pre");
        if (window.navigator.userAgent.contains("msie") || window.navigator.userAgent.contains("MSIE")) {
            code = code.replace("\r\n", "\n").replace("\n", "\r\n");

        }
        pre.innerHTML=code.replace("<", "&lt;");
        pre.classList.add(params);
        replElement(pre);
    }

    private void replElement(Element element) {
        if (this.element.hasChildNodes()) {
            this.element.replaceChild(element, this.element.firstChild);
        } else {
            this.element.appendChild(element);
        }
    }

    /**
     * Creates the String for the list of parameters to be used in the {@code <div>} element's class.
     *
     * @return the {@code <div>} element's parameters.
     */
    private String createParams() {
        String ret = "brush:" + brush.getAlias() + ";" + Param.makeString(params);
        return ret;
    }

    public HTMLDivElement getElement() {
        return element;
    }

    /**
     * JSNI method to perform a highlight of the Instance(s) that need to be highlighted.
     */
    private static native void doHighlight(Element newElement)
    /*-{
        SyntaxHighlighter.highlight({}, newElement);
	}-*/;
}
