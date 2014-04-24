package com.alexgorbatchev.syntaxhighlighter.client;

import com.alexgorbatchev.syntaxhighlighter.client.brushes.Brushes;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.event.shared.UmbrellaException;

public class SyntaxHighlighterGWTTest implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		
		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable e) {
				GWT.log("Uncaught exception escaped", getRootException(e));
			}
		});
		
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(20);
		
		SyntaxHighlighter highlighter = new SyntaxHighlighter(Brushes.XML, "<test>\n  <item></item>\n</test>");
		
		vp.add(highlighter);
		
		final SyntaxHighlighter highlighter2 = new SyntaxHighlighter(Brushes.JScript, "function myFunction() {\n alert(\"Hello World\")\n}");
		
		vp.add(highlighter2);
		
		Button button = new Button("Click Me");
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				highlighter2.setFirstLine(4);
				highlighter2.highlight();
			}
		});
		
		vp.add(button);
		
		RootLayoutPanel.get().add(vp);
		
	}
	
	private static Throwable getRootException(Throwable e) {
		for (Throwable th : ((UmbrellaException) e).getCauses()) {
			if (th instanceof UmbrellaException) {
				return getRootException(th);
			}
			else {
				return th;
			}
		}
		return e;
	}
}