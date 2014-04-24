package com.alexgorbatchev.syntaxhighlighter.client;

import com.alexgorbatchev.syntaxhighlighter.client.brushes.Brushes;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class SyntaxHighlighterGWTTest implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		
		SyntaxHighlighter highlighter = new SyntaxHighlighter(Brushes.XML, "<test>\n  <item></item>\n</test>");
		
		RootLayoutPanel.get().add(highlighter);
		
	}
}