/*
* Html Insert Plugin
*
* @author Kim Hyang min <chi1004kr@naver.com>
* @version 1.0.1
*/
( function() {
	CKEDITOR.plugins.add( 'tokens',
	{
		lang: [ 'en'],
		init: function( editor )
		{
			editor.addCommand( 'tokens', new CKEDITOR.dialogCommand( 'tokens', {
				allowedContent: 'div{*}; iframe{*}[!width,!height,!src,!frameborder,!allowfullscreen]; object param[*]'
			}));

			editor.ui.addButton( 'Tokens',
			{
				label : 'Html Insert Plugin',
				toolbar : 'insert',
				command : 'tokens',
				icon : this.path + 'images/icon.png'
			});

			CKEDITOR.dialog.add( 'tokens', function ( instance )
			{
				var video;

				return {
					title : 'Html Insert Plugin',
					minWidth : 500,
					minHeight : 200,
					contents :
						[{
							id : 'customPlugin',
							expand : true,
							elements :
								[{
									id : 'txtHtml',
									type : 'textarea',
									label : 'Html Source Code 입력',
									autofocus : 'autofocus',
									onChange : function ( api )
									{
										handleEmbedChange( this, api );
									},
									onKeyUp : function ( api )
									{
										handleEmbedChange( this, api );
									},
									validate : function ()
									{
										if ( this.isEnabled() )
										{
											if ( !this.getValue() )
											{
												alert( editor.lang.youtube.noCode );
												return false;
											}
											else
											//if ( this.getValue().length === 0 || this.getValue().indexOf( '//' ) === -1 )
											if ( this.getValue().length === 0 )
											{
												alert( editor.lang.youtube.invalidEmbed );
												return false;
											}
										}
									}
								},
								{
									type : 'html',
									html : editor.lang.youtube.or + '<hr>'
								},
								{
									type : 'hbox',
									widths : [ '70%', '15%', '15%' ],
									children :
									[
										{
											id : 'txtUrl',
											type : 'text',
											label : 'Iframe Url 입력',
											onChange : function ( api )
											{
												handleLinkChange( this, api );
											},
											onKeyUp : function ( api )
											{
												handleLinkChange( this, api );
											},
											validate : function ()
											{
												if ( this.isEnabled() )
												{
													if ( !this.getValue() )
													{
														alert( editor.lang.youtube.noCode );
														return false;
													}
													else{
														//video = ytVidId(this.getValue());

														//if ( this.getValue().length === 0 ||  video === false)
														if ( this.getValue().length === 0)
														{
															alert( editor.lang.youtube.invalidUrl );
															return false;
														}
													}
												}
											}
										},
										{
											type : 'text',
											id : 'txtWidth',
											width : '60px',
											label : editor.lang.youtube.txtWidth,
											'default' : editor.config.youtube_width != null ? editor.config.youtube_width : '640',
											validate : function ()
											{
												if ( this.getValue() )
												{
													var width = parseInt ( this.getValue() ) || 0;

													if ( width === 0 )
													{
														alert( editor.lang.youtube.invalidWidth );
														return false;
													}
												}
												else {
													alert( editor.lang.youtube.noWidth );
													return false;
												}
											}
										},
										{
											type : 'text',
											id : 'txtHeight',
											width : '60px',
											label : editor.lang.youtube.txtHeight,
											'default' : editor.config.youtube_height != null ? editor.config.youtube_height : '360',
											validate : function ()
											{
												if ( this.getValue() )
												{
													var height = parseInt ( this.getValue() ) || 0;

													if ( height === 0 )
													{
														alert( editor.lang.youtube.invalidHeight );
														return false;
													}
												}
												else {
													alert( editor.lang.youtube.noHeight );
													return false;
												}
											}
										}
									]
								},
								{
									type : 'hbox',
									widths : [ '100%' ],
									children :
										[
											{
												id : 'chkResponsive',
												type : 'checkbox',
												label : editor.lang.youtube.txtResponsive,
												'default' : editor.config.youtube_responsive != null ? editor.config.youtube_responsive : true
											}
										]
								},
								{
									type : 'hbox',
									widths : [ '55%', '45%' ],
									children :
									[
										{
											id : 'chkMgoon',
											type : 'checkbox',
											'default' : editor.config.chk_mgoon != null ? editor.config.chk_mgoon : true,
											label : 'mgoon url을 edges로 자동변환'
										}
									]
								}
							]
						}
					],
					onOk: function()
					{
						
						var content = '';
						var responsiveStyle='';

						if ( this.getContentElement( 'customPlugin', 'txtHtml' ).isEnabled() )
						{
							content = this.getValueOf( 'customPlugin', 'txtHtml' );
						}
						else {
							var url = this.getValueOf( 'customPlugin', 'txtUrl' ); 
							var params = [];
							var width = this.getValueOf( 'customPlugin', 'txtWidth' );
							var height = this.getValueOf( 'customPlugin', 'txtHeight' );


							if ( this.getContentElement( 'customPlugin', 'chkResponsive').getValue() === true ) {
								content += '<div style="position:relative;padding-bottom:56.25%;padding-top:30px;height:0;overflow:hidden;">';
								responsiveStyle = 'style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;"';
							}

							if ( this.getContentElement( 'customPlugin', 'chkMgoon' ).getValue() === true )
							{
								url = url.replace(/http:\/\//g,'');
								url = url.replace(/https:\/\//g,'');
								var urlArray = url.split("/");
								var mgoonNumber = urlArray[urlArray.length-1];
								
								//url = 'http://www.mgoon.com/play/partner/edge/6638509';
								url = 'http://www.mgoon.com/play/partner/edge/'+mgoonNumber;
							}else{
								url = url.replace(/http:\/\//g,'');
								url = url.replace(/https:\/\//g,'');
								url = 'http://'+url;
							}
							
							content += '<iframe width="' + width + '" height="' + height + '" src="' + url + '" ' + responsiveStyle;
							content += 'frameborder="0" allowfullscreen></iframe>';

							if ( this.getContentElement( 'customPlugin', 'chkResponsive').getValue() === true ) {
								content += '</div>';
							}
						}

						var element = CKEDITOR.dom.element.createFromHtml( content );
						var instance = this.getParentEditor();
						instance.insertElement(element);
					}
				};
			});
		}
	});
})();

function handleLinkChange( el, api )
{
	if ( el.getValue().length > 0 )
	{
		el.getDialog().getContentElement( 'customPlugin', 'txtHtml' ).disable();
	}
	else {
		el.getDialog().getContentElement( 'customPlugin', 'txtHtml' ).enable();
	}
}

function handleEmbedChange( el, api )
{
	if ( el.getValue().length > 0 )
	{
		el.getDialog().getContentElement( 'customPlugin', 'txtUrl' ).disable();
	}
	else {
		el.getDialog().getContentElement( 'customPlugin', 'txtUrl' ).enable();
	}
}
