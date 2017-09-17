package me.rjb.client.brushes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface BrushResources extends ClientBundle {
	
	public final static BrushResources INSTANCE = GWT.create(BrushResources.class);
	
	@Source("shBrushABL.js")
    TextResource abl();
	
	@Source("shBrushAppleScript.js")
    TextResource appleScript();
	
	@Source("shBrushAS3.js")
    TextResource as3();
	
	@Source("shBrushBash.js")
    TextResource bash();
	
	@Source("shBrushColdFusion.js")
    TextResource coldFusion();
	
	@Source("shBrushCpp.js")
    TextResource cpp();
	
	@Source("shBrushCSharp.js")
    TextResource cSharp();
	
	@Source("shBrushCss.js")
    TextResource css();
	
	@Source("shBrushDelphi.js")
    TextResource delphi();
	
	@Source("shBrushDiff.js")
    TextResource diff();
	
	@Source("shBrushErlang.js")
    TextResource erlang();
	
	@Source("shBrushGroovy.js")
    TextResource groovy();
	
	@Source("shBrushHaxe.js")
    TextResource haxe();
	
	@Source("shBrushJava.js")
    TextResource java();
	
	@Source("shBrushJavaFX.js")
    TextResource javaFX();
	
	@Source("shBrushJScript.js")
    TextResource jScript();
	
	@Source("shBrushPerl.js")
    TextResource perl();
	
	@Source("shBrushPhp.js")
    TextResource php();
	
	@Source("shBrushPlain.js")
    TextResource plain();
	
	@Source("shBrushPowerShell.js")
    TextResource powerShell();
	
	@Source("shBrushPython.js")
    TextResource python();
	
	@Source("shBrushRuby.js")
    TextResource ruby();
	
	@Source("shBrushSass.js")
    TextResource sass();
	
	@Source("shBrushScala.js")
    TextResource scala();
	
	@Source("shBrushSql.js")
    TextResource sql();
	
	@Source("shBrushTAP.js")
    TextResource tap();
	
	@Source("shBrushTypeScript.js")
    TextResource typeScript();
	
	@Source("shBrushVb.js")
    TextResource vb();
	
	@Source("shBrushXml.js")
    TextResource xml();
	
}
