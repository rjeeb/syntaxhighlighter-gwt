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

Simple Java
```Java
//optionally set theme before creating any SyntaxHighlighter
//otherwise default is used
SyntaxHighlighter.init(Themes.RDark);

SyntaxHighlighter highlighter = new SyntaxHighlighter(Brushes.XML, "<test>\n  <item></item>\n</test>");
somePanel.add(highlighter);
```


Change settings/test
```Java

//update text
hightlight.setText("<somexml></somexml>");

//update settings
highlighter.setFirstLine(4); //starts numbering of lines from 4
highlighter.setToolbar(false); //remove top bar
highlighter.setGutter(false); //remove line numbers


//call highlight to update settings
//highlight is called automatically on attach to a panel and when changing text but must be called manually after settings changes
highlighter.highlight(); 

```


