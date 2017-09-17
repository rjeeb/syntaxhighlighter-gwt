package me.rjb.client.themes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface ThemeResources extends ClientBundle {
	
	public final static ThemeResources INSTANCE = GWT.create(ThemeResources.class);
	
	@CssResource.NotStrict
	@Source("shThemeDefault.css")
    CssResource defaultCss();
	
	@CssResource.NotStrict
	@Source("shThemeDjango.css")
    CssResource djangoCss();
	
	@CssResource.NotStrict
	@Source("shThemeEclipse.css")
    CssResource eclipseCss();
	
	@CssResource.NotStrict
	@Source("shThemeEmacs.css")
    CssResource emacsCss();
	
	@CssResource.NotStrict
	@Source("shThemeFadeToGrey.css")
    CssResource fadeToGreyCss();
	
	@CssResource.NotStrict
	@Source("shThemeMDUltra.css")
    CssResource mdUltraCss();
	
	@CssResource.NotStrict
	@Source("shThemeMidnight.css")
    CssResource midnightCss();
	
	@CssResource.NotStrict
	@Source("shThemeRDark.css")
    CssResource rDarkCss();
}
