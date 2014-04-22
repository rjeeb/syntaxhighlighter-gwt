package com.alexgorbatchev.syntaxhighlighter.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SyntaxHighlighterGWTTest implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		Highlighter.loadBrushes(Brush.Brushes.XML());
		final Highlighter highlighter = new Highlighter(Brush.Brushes.XML(), "<test>\n  <item></item>\n</test>");
		
		highlighter.setWidth("200px");
		highlighter.setHeight("200px");
		
		VerticalPanel vp = new VerticalPanel();
		vp.add(highlighter);
		
		RootLayoutPanel.get().add(vp);
		
	}
}