package me.rjb.client.core;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.TextResource;

public interface CoreResources extends ClientBundle {
	
	public final static CoreResources INSTANCE = GWT.create(CoreResources.class);
	
	@Source("shCore.min.js")
    TextResource coreJavascript();
	
	@CssResource.NotStrict
	@Source("shGWTFix.gss")
    CssResource gwtFixCss();
}
