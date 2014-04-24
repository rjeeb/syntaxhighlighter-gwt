package com.alexgorbatchev.syntaxhighlighter.client.brushes;

import com.google.gwt.resources.client.TextResource;

public interface Brush {
	public TextResource getBrushJsTextResource();
	
	public String getAlias();
}
