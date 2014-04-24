package com.alexgorbatchev.syntaxhighlighter.client.brushes;

import com.google.gwt.resources.client.TextResource;

public enum Brushes implements Brush {
	ABL(BrushResources.INSTANCE.abl()),
	AppleScript(BrushResources.INSTANCE.appleScript()),
	AS3(BrushResources.INSTANCE.as3()),
	Autolt(BrushResources.INSTANCE.autoIt()),
	Bash(BrushResources.INSTANCE.bash()),
	CodeFusion(BrushResources.INSTANCE.coldfusion()),
	Cpp(BrushResources.INSTANCE.cpp()),
	Csharp(BrushResources.INSTANCE.cSharp()),
	Css(BrushResources.INSTANCE.css()),
	Delphi(BrushResources.INSTANCE.delphi()),
	Diff(BrushResources.INSTANCE.diff()),
	Erlang(BrushResources.INSTANCE.erlang()),
	Groovy(BrushResources.INSTANCE.groovy()),
	Java(BrushResources.INSTANCE.java()),
	JavaFX(BrushResources.INSTANCE.javaFX()),
	JScript(BrushResources.INSTANCE.jScript()),
	Lisp(BrushResources.INSTANCE.lisp()),
	Perl(BrushResources.INSTANCE.perl()),
	Plain(BrushResources.INSTANCE.plain()),
	PowerShell(BrushResources.INSTANCE.powerShell()),
	Python(BrushResources.INSTANCE.python()),
	RS(BrushResources.INSTANCE.rs()),
	Ruby(BrushResources.INSTANCE.ruby()),
	Sass(BrushResources.INSTANCE.sass()),
	Scala(BrushResources.INSTANCE.scala()),
	Scheme(BrushResources.INSTANCE.scheme()),
	Sql(BrushResources.INSTANCE.sql()),
	Vb(BrushResources.INSTANCE.vb()),
	XML(BrushResources.INSTANCE.xml());
	
	private TextResource brushJsTextResource;
	
	Brushes(TextResource brushJsTextResource) {
		this.brushJsTextResource = brushJsTextResource;
	}
	
	@Override
	public TextResource getBrushJsTextResource() {
		return brushJsTextResource;
	}
	
	public String getAlias() {
		return brushJsTextResource.getName().toLowerCase();
	}
}
