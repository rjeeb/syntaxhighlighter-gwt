package com.alexgorbatchev.syntaxhighlighter.client.brushes;

import com.google.gwt.resources.client.TextResource;

public enum Brushes implements Brush {
	ABL(BrushResources.INSTANCE.abl()),
	AppleScript(BrushResources.INSTANCE.appleScript()),
	AS3(BrushResources.INSTANCE.as3()),
	Bash(BrushResources.INSTANCE.bash()),
	Cpp(BrushResources.INSTANCE.cpp()),
	Csharp(BrushResources.INSTANCE.cSharp()),
	Css(BrushResources.INSTANCE.css()),
	Delphi(BrushResources.INSTANCE.delphi()),
	Diff(BrushResources.INSTANCE.diff()),
	Erlang(BrushResources.INSTANCE.erlang()),
	Groovy(BrushResources.INSTANCE.groovy()),
	Haxe(BrushResources.INSTANCE.haxe()),
	Java(BrushResources.INSTANCE.java()),
	JavaFX(BrushResources.INSTANCE.javaFX()),
	JScript(BrushResources.INSTANCE.jScript()),
	Perl(BrushResources.INSTANCE.perl()),
	Plain(BrushResources.INSTANCE.plain()),
	PowerShell(BrushResources.INSTANCE.powerShell()),
	Python(BrushResources.INSTANCE.python()),
	Ruby(BrushResources.INSTANCE.ruby()),
	Sass(BrushResources.INSTANCE.sass()),
	Scala(BrushResources.INSTANCE.scala()),
	Sql(BrushResources.INSTANCE.sql()),
	TAP(BrushResources.INSTANCE.tap()),
	TypeScript(BrushResources.INSTANCE.typeScript()),
	VB(BrushResources.INSTANCE.vb()),
	XML(BrushResources.INSTANCE.xml());
	
	private TextResource brushJsTextResource;
	
	Brushes(TextResource brushJsTextResource) {
		this.brushJsTextResource = brushJsTextResource;
	}
	
	@Override
	public TextResource getBrushJsTextResource() {
		return brushJsTextResource;
	}
	
	@Override
	public String getAlias() {
		return brushJsTextResource.getName().toLowerCase();
	}
}
