package me.rjb.client.themes;

import com.google.gwt.resources.client.CssResource;

public enum Themes implements Theme {
	Default(ThemeResources.INSTANCE.defaultCss()),
	Django(ThemeResources.INSTANCE.djangoCss()),
	Eclipse(ThemeResources.INSTANCE.eclipseCss()),
	Emacs(ThemeResources.INSTANCE.emacsCss()),
	FadeToGrey(ThemeResources.INSTANCE.fadeToGreyCss()),
	MDUltra(ThemeResources.INSTANCE.mdUltraCss()),
	Midnight(ThemeResources.INSTANCE.midnightCss()),
	RDark(ThemeResources.INSTANCE.rDarkCss());
	
	private CssResource themeCssResource;
	
	Themes(CssResource themeCssResource) {
		this.themeCssResource = themeCssResource;
	}
	
	@Override
	public CssResource getThemeCssResource() {
		return themeCssResource;
	}
	
}
