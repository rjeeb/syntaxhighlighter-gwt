syntaxhighlighter-gwt
=====================

SyntaxHighlighter-GWT

GWT Wrapper around [SyntaxHighlighter system](http://alexgorbatchev.com/SyntaxHighlighter/), provided by Alex Gorbatchev

Based loosely around https://code.google.com/p/gwt-syntaxhighlighter/.   It has been largely rewritten for simplicity.

Usage:

In Gradle
```Groovy
compile 'com.alexgorbatchev:syntaxhighlighter-gwt:0.2.1'
compile group:'com.alexgorbatchev', name:'syntaxhighlighter-gwt', version:'0.2.1', classifier:'sources'
```

In YourModule.gwt.xml
```XML
<inherits name='com.alexgorbatchev.syntaxhighlighter.Highlighter'/>
```

In Java
```Java

SyntaxHighlighter highlighter = new SyntaxHighlighter(Brushes.XML, "<test>\n  <item></item>\n</test>");
		
somePanel.add(highlighter);
```
