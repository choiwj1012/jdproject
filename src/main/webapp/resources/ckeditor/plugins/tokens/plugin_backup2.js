CKEDITOR.plugins.add( 'tokens', {
	requires: 'dialog',
	icons: 'about', // %REMOVE_LINE_CORE%
	hidpi: true, // %REMOVE_LINE_CORE%
	
	init: function( editor ) {
		var config = editor.config,
         lang = editor.lang.format;

		var command = editor.addCommand( 'tokens', new CKEDITOR.dialogCommand( 'tokens' ) );
		command.modes = { wysiwyg: 1, source: 1 };
		command.canUndo = false;
		command.readOnly = 1;

		editor.ui.addButton && editor.ui.addButton( 'Tokens', {
			label: 'tokens',
			command: 'tokens',
			toolbar: 'about'
		} );

		CKEDITOR.dialog.add( 'tokens', this.path + 'dialogs/tokens.js' );
	}
} );
