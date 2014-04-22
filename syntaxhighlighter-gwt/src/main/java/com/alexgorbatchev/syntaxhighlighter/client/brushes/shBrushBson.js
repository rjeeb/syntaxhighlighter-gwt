;(function()
{
	// CommonJS
	typeof(require) != 'undefined' ? SyntaxHighlighter = require('shCore').SyntaxHighlighter : null;

	function Brush()
	{
		this.regexList = [
		    { regex: /{|}|\[|\]/gm, css: 'bold' }, 
			{ regex: SyntaxHighlighter.regexLib.doubleQuotedString, css: 'comments' }, 
			{ regex: /^.*?:/gm, css: 'color1'} 
			];
	};

	Brush.prototype	= new SyntaxHighlighter.Highlighter();
	Brush.aliases	= ['bson'];

	SyntaxHighlighter.brushes.BSON = Brush;

	// CommonJS
	typeof(exports) != 'undefined' ? exports.Brush = Brush : null;
})();