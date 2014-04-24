syntaxhighlighter-gwt
=====================

SyntaxHighlighter-GWT

GWT Wrapper around [SyntaxHighlighter system](http://alexgorbatchev.com/SyntaxHighlighter/), provided by Alex Gorbatchev

Based loosely around https://code.google.com/p/gwt-syntaxhighlighter/.   It has been largely rewritten for simplicity.

Usage:

In YourModule.gwt.xml
```XML
<inherits name='com.alexgorbatchev.syntaxhighlighter.Highlighter'/>
```

In Java
```Java

SyntaxHighlighter highlighter = new SyntaxHighlighter(Brushes.XML, "<test>\n  <item></item>\n</test>");
		
somePanel.add(highlighter);
```
