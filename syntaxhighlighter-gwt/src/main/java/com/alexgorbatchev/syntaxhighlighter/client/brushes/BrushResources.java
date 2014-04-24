package com.alexgorbatchev.syntaxhighlighter.client.brushes;

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
	
	@Source("shBrushAutoIt.js")
	TextResource autoIt();
	
	@Source("shBrushBash.js")
	TextResource bash();

	
	@Source("shBrushColdFusion.js")
	TextResource coldfusion();
	
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
	
	@Source("shBrushJava.js")
	TextResource java();
	
	@Source("shBrushJavaFX.js")
	TextResource javaFX();
	
	@Source("shBrushJScript.js")
	TextResource jScript();
	
	@Source("shBrushLisp.js")
	TextResource lisp();
	
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
	
	@Source("shBrushRS.js")
	TextResource rs();
	
	@Source("shBrushRuby.js")
	TextResource ruby();
	
	@Source("shBrushSass.js")
	TextResource sass();
	
	@Source("shBrushScala.js")
	TextResource scala();
	
	@Source("shBrushScheme.js")
	TextResource scheme();
	
	@Source("shBrushSql.js")
	TextResource sql();
	
	@Source("shBrushVb.js")
	TextResource vb();
	
	@Source("shBrushXml.js")
	TextResource xml();
	
}
