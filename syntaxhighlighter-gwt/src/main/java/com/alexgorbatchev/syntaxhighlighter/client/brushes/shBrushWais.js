;(function()
{
	// CommonJS
	typeof(require) != 'undefined' ? SyntaxHighlighter = require('shCore').SyntaxHighlighter : null;

	function Brush()
	{
		this.regexList = [
		    { regex: /^\w\w/gm, css: 'keyword' },				// Field Tag
			{ regex: /^#.*/gm, css: 'script' },	 		// Wais Header
			{ regex: /^\+\+/gm, css: 'script'},			// Record Ender
			{ regex: /: .+/gm, css: 'comments'},				// Field Name
			{ regex: /^\s{4}.*/gm, css: 'italic'}
			];
	};

	Brush.prototype	= new SyntaxHighlighter.Highlighter();
	Brush.aliases	= ['wais'];

	SyntaxHighlighter.brushes.Wais = Brush;

	// CommonJS
	typeof(exports) != 'undefined' ? exports.Brush = Brush : null;
})();
