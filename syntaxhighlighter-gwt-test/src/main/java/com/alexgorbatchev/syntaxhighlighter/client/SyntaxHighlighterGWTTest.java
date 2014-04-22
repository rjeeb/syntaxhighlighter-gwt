package com.alexgorbatchev.syntaxhighlighter.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class SyntaxHighlighterGWTTest implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		
		Highlighter.loadBrushes(Brush.Brushes.XML());
		
		Highlighter highlighter = new Highlighter(Brush.Brushes.XML(), "<test>\n  <item></item>\n</test>");
		
		RootLayoutPanel.get().add(highlighter);
		
	}
}