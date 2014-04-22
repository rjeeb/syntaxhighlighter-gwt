syntaxhighlighter-gwt
=====================

SyntaxHighlighter-GWT

GWT Wrapper around [SyntaxHighlighter system](http://alexgorbatchev.com/SyntaxHighlighter/), provided by Alex Gorbatchev

Based on the GWT Wrapper at https://code.google.com/p/gwt-syntaxhighlighter/ that is out of date.

Updated with minor fixes and now uses gradle.




Usage:

In YourModule.gwt.xml
```XML
<inherits name='com.alexgorbatchev.syntaxhighlighter.Highlighter'/>
```

In Java
```Java
Highlighter.loadBrushes(Brush.Brushes.XML());
		
Highlighter highlighter = new Highlighter(Brush.Brushes.XML(), "<test>\n  <item></item>\n</test>");
		
RootLayoutPanel.get().add(highlighter);
```
