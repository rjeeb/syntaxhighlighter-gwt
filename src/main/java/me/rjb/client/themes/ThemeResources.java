package me.rjb.client.themes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface ThemeResources extends ClientBundle {
	
	public final static ThemeResources INSTANCE = GWT.create(ThemeResources.class);
	
	@CssResource.NotStrict
	@Source("shThemeDefault.gss")
    CssResource defaultCss();
	
	@CssResource.NotStrict
	@Source("shThemeDjango.gss")
    CssResource djangoCss();
	
	@CssResource.NotStrict
	@Source("shThemeEclipse.gss")
    CssResource eclipseCss();
	
	@CssResource.NotStrict
	@Source("shThemeEmacs.gss")
    CssResource emacsCss();
	
	@CssResource.NotStrict
	@Source("shThemeFadeToGrey.gss")
    CssResource fadeToGreyCss();
	
	@CssResource.NotStrict
	@Source("shThemeMDUltra.gss")
    CssResource mdUltraCss();
	
	@CssResource.NotStrict
	@Source("shThemeMidnight.gss")
    CssResource midnightCss();
	
	@CssResource.NotStrict
	@Source("shThemeRDark.gss")
    CssResource rDarkCss();
}
