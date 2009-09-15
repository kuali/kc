var node;
var i = 1;
var removedNode = null;
var cutNode;
var sqlScripts = "";
var ulTagId;
var sqls = [];
var sqlidx = 0;
var deletedNodes = "";
var newNodes = ";";
var loadedidx = 0;

$(document).ready(function() {
	$.ajaxSettings.cache = false;
	$("#researcharea").treeview( {
		toggle : function() {
			var idstr = $(this).attr("id").substring(4);
			var tagId = "listcontrol" + idstr;
			var divId = "listcontent" + idstr;

			$(".hierarchydetail:not(#" + divId + ")").slideUp(300);
			$("#" + divId).slideToggle(300);
		loadChildrenRA($("#itemText" + idstr).text(), tagId);
	},
	animated : "fast",
	collapsed : true,
	control : "#treecontrol"

	});

	/*
	 * $.ajaxStart(function() { $("div#foo").text("Loading..."); });
	 * $.ajaxComplete(function() { $("div#foo").text(""); });
	 */

		$(document).ajaxStart(function() {
			// this is weird, it will not show if the alert is not included??
				// return false;
				// var img = $('<a href="#"><img
				// src="static/images/jquery/ajax-loader.gif" /></a>')
				$("#loading").show();
				// alert ("start Ajax");
				// return false;
			});

		$(document).ajaxComplete(function() {
			// alert ("complete Ajax");
				$("#loading").hide();
				// return false;
			});

	}); // $(document).ready

/*
 * A function to add sql statement to collection of sqlscripts, which will be
 * sent to server when 'save' is clicked'. 1900 length limit for query string in
 * ajax request.
 */
function addSqlScripts(sqlcommand) {
	// alert("add "+sqlcommand+"-"+sqlScripts)
	if ((sqlScripts.length + sqlcommand.length) > 1900) {
		sqls[sqlidx++] = sqlScripts;
		sqlScripts = "";
	}
	sqlScripts = sqlScripts + "#;#" + sqlcommand;
}

/*
 * top level to add research area
 */
$("#add0").click(function() {
			// click 'add' for 000001
				var trNode = $(this).parents('tr:eq(0)');
				if (trNode.children('td:eq(1)').children('input:eq(0)').attr(
						"value") == "") {
					alert("must enter research area code");
				} else if (trNode.children('td:eq(2)').children('input:eq(0)')
						.attr("value") == "") {
					alert("must enter research area");
				} else {
					var raExist
					$.ajax( {
						url : 'researchAreaAjax.do',
						type : 'GET',
						dataType : 'html',
						data : 'researchAreaCode=' + trNode
								.children('td:eq(1)').children('input:eq(0)')
								.attr("value") + '&addRA=Y',
						cache : false,
						async : false,
						timeout : 1000,
						error : function() {
							alert('Error loading XML document');
						},
						success : function(xml) {
							$(xml).find('h3').each(function() {
								raExist = $(this).text();
								// alert(raExist);

								});
						}
					}); // end ajax

					if (raExist == 'false'
							&& newNodes != ";"
							&& newNodes.indexOf(";"
									+ trNode.children('td:eq(1)').children(
											'input:eq(0)').attr("value") + ";") > -1) {
						raExist = 'true';
					}

					if (raExist == 'false') {
						var ulTag = $("#researcharea");

						var item_text = trNode.children('td:eq(1)').children(
								'input:eq(0)').attr("value")
								+ " : "
								+ trNode.children('td:eq(2)').children(
										'input:eq(0)').attr("value");
						var listitem = setupListItem(trNode
								.children('td:eq(1)').children('input:eq(0)')
								.attr("value"), trNode.children('td:eq(2)')
								.children('input:eq(0)').attr("value"));
						// need this ultag to force to display folder.
						var childUlTag = $('<ul></ul>').attr("id", "ul" + i);
						childUlTag.appendTo(listitem);

						// this is new nodes, so it is same as already loaded
						// from DB
						var loadedId = "loaded" + i;
						var inputtag = $('<input type="hidden"></input>').attr(
								"id", loadedId);
						inputtag.appendTo(childUlTag);

						listitem.appendTo(ulTag);
						// force to display folder icon
						$("#researcharea").treeview( {
							add : listitem
						// toggle: function() {
								// var subul=this.getElementsByTagName("ul")[0]
								// if (subul.style.display=="block")
								// alert("You've opened this Folder!")
								// }
								});

						newNodes = newNodes
								+ trNode.children('td:eq(1)').children(
										'input:eq(0)').attr("value") + ";";
						// apend to sqlScripts
						addSqlScripts(getInsertClause(trNode.children(
								'td:eq(1)').children('input:eq(0)').attr(
								"value"), '000001', trNode.children('td:eq(2)')
								.children('input:eq(0)').attr("value")));
					} else {
						alert("Research Area Code already exist");
					}
				}
				;

				return false;
			}); // add0


/*
 * Load first level area of research when page is initially loaded
 */
function loadFirstLevel() {

	$.ajax( {
				url : 'researchAreaAjax.do',
				type : 'GET',
				dataType : 'html',
				cache : false,
				data : 'researchAreaCode=000001&addRA=',
				async : false,
				timeout : 1000,
				error : function() {
					alert('Error loading XML document');
				},
				success : function(xml) {
					$(xml).find('h3').each(
									function() {
										var item_text = $(this).text();
										i++;
										var racode = item_text.substring(0,
												item_text.indexOf("%3A"))
												.trim();
										item_text = item_text.replace("%3A",
												":");
										var id = "item" + i;
										var tagId = "listcontrol" + i;
										var divId = "listcontent" + i;

										// NOTES : if use 'div', then FF will
										// display the '+' and idDiv in
										// separate lines. IE7 is fine
										// But 'IE7 has problem with 'span'
										var idDiv;
										if (jQuery.browser.msie) {
											idDiv = $('<div></div>').attr("id",
													"itemText" + i).html(
													item_text); 
										} else {
											idDiv = $('<span>').attr("id",
													"itemText" + i).html(
													item_text); 
										}
										var tag = $(
												'<a style = "margin-left:2px;" ></a>')
												.attr("id", tagId).html(idDiv);
										var div = $(
												'<div  class="hierarchydetail" style="margin-top:2px; "></div>')
												.attr("id", divId);
										var hidracode = $(
												'<input type="hidden" id = "racode" name = "racode" />')
												.attr("id", "racode" + i).attr(
														"name", "racode" + i)
												.attr("value", racode);
										hidracode.appendTo(div);
										tag.click(function() {
												$(".hierarchydetail:not(#"+ divId + ")").slideUp(300);
												var idx = $(this).attr("id")
														.substring(11);
												if ($(this).siblings(
														'div:eq(1)').children(
														'table:eq(0)').size() == 0) {
													tableTag(item_text,
															"item" + idx)
															.appendTo(
																	$("#listcontent"
																			+ idx));
													if ($("#" + divId).is(":hidden")) {
														$("#listcontent" + idx).show();
														// $("#listcontent"+idx).slideToggle(300);
													}
												} else {
													$("#listcontent" + idx).slideToggle(300);
												}

												loadChildrenRA(item_text, tagId);
											});
										var listitem = $(
												'<li class="closed"></li>')
												.attr("id", id).html(tag);
										ulTagId = "researcharea";
										div.appendTo(listitem);
										// need this ultag to force to display
										// folder.
										var childUlTag = $('<ul></ul>').attr(
												"id", "ul" + i);
										childUlTag.appendTo(listitem);
										listitem.appendTo('ul#researcharea');
										// also need this to show 'folder' icon
										$('#researcharea').treeview( {
											add : listitem

										});
									});
				}
			});
} // load first level RA

/*
 * set up the area of research detail table tag. This is loading on demand. Only
 * when area of research link is clicked the first time, then it will be loaded.
 */
function tableTag(name, id) {

	var link = $('<a href="#" class="hidedetail"><img src="kr/static/images/tinybutton-hide.gif" align="absmiddle" border="0" width="45" height="15"></a>');
	var tag = $(
			'<th  style="background:#939393;height:18px;color:#FFFFFF;text-align:left;padding-left:4px;" align="left"></th>')
			.attr("id", "raHeader" + id.substring(4)).html(name);
	link.prependTo(tag);
	tag = $('<tr></tr>').html(tag);
	tag = $('<thead></thead>').html(tag);
	tag = $(
			'<table width="100%" cellpadding="0" cellspacing="0" class="subelement"> </table>')
			.html(tag);
	tbodyTag(name, id).appendTo(tag);
	return tag;
}

function tbodyTag(name, id) {

	var idx = id.substring(4);
	var tblTag = $('<table cellpadding="0" cellspacing="0" class="elementtable" width="100%"></table>')
	var tag = $('<th colspan="4"></th>');
	var image = $(
			'<a href="#"><img src="static/images/tinybutton-removenode.gif" width="79" height="15" border="0" alt="Remove Node" title="Remove this node and its child groups/sponsors"></a>&nbsp')
			.attr("id", "remove" + idx).click(function() {
				var liId = "li#" + id;
					var parentRACode;
					if ($(liId).parents('li:eq(0)').size() == 0) {
						parentRACode = '000001';
					} else {
						parentRACode = getResearchAreaCode($(liId).parents(
								'li:eq(0)'));
					}
					addSqlScripts("remove((" + getResearchAreaCode($(liId))
							+ ";" + parentRACode + "))");
					if (deletedNodes == '') {
						deletedNodes = getResearchAreaCode($(liId));
					} else {
						deletedNodes = deletedNodes + ";"
								+ getResearchAreaCode($(liId));
					}
					deleteChild(id);
					$(liId).remove();
					cutNode = null;
					return false; // eliminate page jumping
				});
	tag.html(image);
	image = $(
			'<a href="#"><img src="static/images/tinybutton-cutnode.gif" width="79" height="15" border="0" alt="Cut Node" title="Cut this node and its child roups/sponsors.  (Node will not be removed until you paste it.)"></a>&nbsp')
			.attr("id", "cut" + idx).click(function() {
					var liId = "li#" + id;
					cutNode = $(liId).clone(true);
					return false; // eliminate page jumping
				});
	image.appendTo(tag);
	image = $(
			'<a href="#"><img src="static/images/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Paste Node" title="Paste your previously cut node structure under this node"></a>')
			.attr("id", "paste" + idx).click(function() {

		if (removedNode || cutNode) {
				var parentNode = $("#" + id);
				var ulTag = parentNode.children('ul');
				if (ulTag.size() > 0) {
						// alert(ulTag.attr("id"));
				} else {
					    alert("not found")
					    i++;
					    ulTag = $('<ul class="filetree"></ul>')
							.attr("id", "ul" + i);
				}
				if (removedNode) {
					removedNode.appendTo(ulTag);
					addSqlScripts(getInsertClause(
							getResearchAreaCode(removedNode),
							getResearchAreaCode($("#" + id)),
							getResearchAreaDescription(
									getResearchAreaCode(removedNode),
									removedNode.children('a:eq(0)').text())));
					removedNode = null;
				} else {
					var liId = cutNode.attr("id");
					if (id == $("li#" + liId).parents('li:eq(0)').attr("id")) {
						alert("can't paste to the parent node");
					} else if (id == liId) {
						alert("can't paste to the same node");
					} else {
						var liId = cutNode.attr("id");
						var parentRACode;
						// NOTE : cutNode.parents('li:eq(0)') is not working
						// $("li#"+liId).parents('li:eq(0)')is ok
						if ($("li#" + liId).parents('li:eq(0)').size() == 0) {
							parentRACode = '000001';
						} else {
							parentRACode = getResearchAreaCode($("li#" + liId)
									.parents('li:eq(0)'));
						}

						addSqlScripts(getDeleteClause(
								getResearchAreaCode(cutNode), parentRACode));
						$("li#" + liId).remove();
						cutNode.appendTo(ulTag);
						addSqlScripts(getInsertClause(
								getResearchAreaCode(cutNode),
								getResearchAreaCode($("#" + id)),
								getResearchAreaDescription(
										getResearchAreaCode(cutNode),
										cutNode.children('a:eq(0)').text())));
						$("#pcode"+$("li#" + liId).attr("id").substring(4)).html(getResearchAreaCode($("#" + id)));
						cutNode = null;
						ulTag.appendTo(parentNode);
					} // if then else if not paste back to parent node
				}
				// right now is only doing for cutnode
				// ulTag.appendTo(parentNode);


			}// if removednode
			return false; // eliminate page jumping
		}	);

	image.appendTo(tag);
	var notetag = $('<th style="text-align:right;"></th>').html("Node:");
	tag = $('<tr></tr>').html(tag);
	notetag.prependTo(tag);
	tblTag.html(tag);

	// 2nd tr
	var trTag = $('<tr></tr>');
	var tdTag = $('<td class="infoline" style="width:60px;"></td>').html(
			'&nbsp;');
	trTag.html(tdTag);
	var tdTag = $('<td class="infoline" style="width:100px;"></td>').html(
			'<b>Parent Code</b>');
	tdTag.appendTo(trTag);
	var tdTag = $('<td class="infoline" style="width:100px;"></td>').html(
			'<b>Research Code</b>');
	tdTag.appendTo(trTag);
	var tdTag = $('<td class="infoline"></td>').html('<b>Research Area</b>');
	tdTag.appendTo(trTag);
	var tdTag = $('<td class="infoline" style="width:65px;"></td>').html(
			'<b>Action</b>');
	tdTag.appendTo(trTag);

	// 3rd tr
	var trTag1 = $('<tr></tr>');
	var tag1 = $('<th style="text-align:right;"></th>').html('Edit:');
	var tdTag1;
	ulTagId = $("li#"+id).parents('ul:eq(0)').attr("id");
	//if (i < 5) {
	//	alert(id+"-"+ulTagId+"-"+$("ul#" + ulTagId).parents('li:eq(0)').size())
	//}
	if ($("ul#" + ulTagId).parents('li:eq(0)').size() == 0) {
		// TODO : this is the second level, the children of '000001'
		tdTag1 = $('<td></td>').attr("id","pcode"+idx).html("000001");
	} else {
		tdTag1 = $('<td></td>').attr("id","pcode"+idx).html(
				getResearchAreaCode($("ul#" + ulTagId).parents('li:eq(0)')));
	}
	trTag1.html(tag1);
	tdTag1.appendTo(trTag1);
	tdTag1 = $('<td></td>').html(getResearchAreaCode($("#" + id)));
	tdTag1.appendTo(trTag1);
	tdTag1 = $('<td></td>')
			.html(
					$(
							'<input type="text" name="m3" style="width:100%;" readonly="true"/>')
							.attr("id", "cdesc" + idx).attr(
									"value",
									getResearchAreaDescription(
											getResearchAreaCode($("#" + id)),
											name)));
	tdTag1.appendTo(trTag1);
	tag1 = $('<th class="infoline" style="text-align:center;"></th>');
	var editlink = $(
			'<a href="#"><img src="static/images/tinybutton-edit1.gif" width="40" height="15" border="0" title="update"></a>')
			.attr("id", "editRA" + idx)
			.click(function() {
				var header = $("#raHeader" + $(this).attr("id").substring(6));
				// $("#raHeader"+i) will not work because "i" is evaluated when
				// this function is called; not when this function is created
					var desc = editResearchArea($(this).attr("id").substring(6));

					if (desc.length == 0) {
						alert("Research area can not be empty ");
					} else if ($("#cdesc" + $(this).attr("id").substring(6))
							.attr("value") != desc) {
						$("#cdesc" + $(this).attr("id").substring(6)).attr(
								"value", desc);
						var newdesc = getResearchAreaCode($("#item"
								+ $(this).attr("id").substring(6)))
								+ " : " + desc;
						header.html(newdesc);
						$("#itemText" + $(this).attr("id").substring(6)).html(
								newdesc);
						addSqlScripts(getUpdateClause(
								getResearchAreaCode($("#item"
										+ $(this).attr("id").substring(6))),
								desc));
						// lots of trouble to update the description on item, so
						// add
						// additional 'div' tag for this purposes.
						// tried many different ways, include 'replace', but it
						// did not
						// work. So, finally decide on this approach.
						// alert(sqlScripts);
					}
					return false; // eliminate page jumping
				}); // end editlink click

	tag1.html(editlink);
	tag1.appendTo(trTag1);

	// 4th tr
	var trTag2 = $('<tr></tr>');
	var tag2 = $('<th style="text-align:right;"></th>').html('Add:');
	var tdTag2 = $('<td></td>').html(getResearchAreaCode($("#" + id)));
	trTag2.html(tag2);
	tdTag2.appendTo(trTag2);
	tdTag2 = $('<td></td>')
			.html(
					$(
							'<input type="text" name="m2" value="" style="width:100%;" maxlength="8" size="8"/>')
							.attr("id", "researchCode" + idx));
	tdTag2.appendTo(trTag2);
	tdTag2 = $('<td></td>').html(
			$('<input type="text" name="m3" value="" style="width:100%;" />')
					.attr("id", "desc" + idx));
	tdTag2.appendTo(trTag2);
	tag2 = $('<th class="infoline" style="text-align:center;"></th>');
	var addlink = $(
			'<a href="#"><img src="static/images/tinybutton-add1.gif" width="40" height="15" border="0" title="Add this Sub-group"></a>')
			.attr("id", "addRA" + idx).click(function() {
					var trNode = $(this).parents('tr:eq(0)');
					if (trNode.children('td:eq(1)').children('input:eq(0)')
							.attr("value") == "") {
						alert("must enter research area code");
					} else if (trNode.children('td:eq(2)').children(
							'input:eq(0)').attr("value") == "") {
						alert("must enter research area");
					} else {
						var raExist
						$.ajax( {
							url : 'researchAreaAjax.do',
							type : 'GET',
							dataType : 'html',
							data : 'researchAreaCode='
									+ trNode.children('td:eq(1)').children(
											'input:eq(0)').attr("value")
									+ '&deletedRas=' + deletedNodes
									+ '&addRA=Y',
							cache : false,
							async : false,
							timeout : 1000,
							error : function() {
								alert('Error loading XML document');
							},
							success : function(xml) {
								$(xml).find('h3').each(function() {
									raExist = $(this).text();
									});
							}
						}); // end ajax

						if (raExist == 'false'
								&& newNodes != ";"
								&& newNodes.indexOf(";"
										+ trNode.children('td:eq(1)').children(
												'input:eq(0)').attr("value")
										+ ";") > -1) {
							raExist = 'true';
						}
						if (raExist == 'false') {
							var parentNode = $("#" + id);
							var ulTag = parentNode.children('ul');
							if (parentNode.children('ul').size() == 0) {
								i++;
								ulTag = $('<ul class="filetree"></ul>').attr(
										"id", "ul" + id.substring(4));
							}

							ulTag.appendTo(parentNode);

							var item_text = trNode.children('td:eq(1)')
									.children('input:eq(0)').attr("value")
									+ " : "
									+ trNode.children('td:eq(2)').children(
											'input:eq(0)').attr("value");
							var listitem = setupListItem(trNode.children(
									'td:eq(1)').children('input:eq(0)').attr(
									"value"), trNode.children('td:eq(2)')
									.children('input:eq(0)').attr("value"));
							var childUlTag = $('<ul></ul>').attr("id",
									"ul" + $(this).attr("id").substring(5));
							childUlTag.appendTo(listitem);
							// this is new nodes, so it is same as already
							// loaded from DB
							var loadedId = "loaded" + idx;
							var inputtag = $('<input type="hidden"></input>')
									.attr("id", loadedId);
							inputtag.appendTo(childUlTag);

							listitem.appendTo(ulTag);
							// force to display folder icon
							$("#researcharea").treeview( {
								add : listitem
							});
							newNodes = newNodes
									+ trNode.children('td:eq(1)').children(
											'input:eq(0)').attr("value") + ";";

							addSqlScripts(getInsertClause(trNode.children(
									'td:eq(1)').children('input:eq(0)').attr(
									"value"), getResearchAreaCode($("#" + id)),
									trNode.children('td:eq(2)').children(
											'input:eq(0)').attr("value")));
						} else {
							alert("Research Area Code already exist");
						}
					}
					return false; // eliminate page jumping
				}); // end addlink click

	tag2.html(addlink);
	tag2.appendTo(trTag2);

	trTag.appendTo(tblTag);
	trTag1.appendTo(tblTag);
	trTag2.appendTo(tblTag);
	tag = $('<td class="subelementcontent"></td>').html(tblTag);
	tag = $('<tr></tr>').html(tag);
	tag = $('<tbody></tbody>').html(tag);
	return tag;
}

/*
 * This is for editing 'research area' description. it will pop up a window to
 * ask for modification.
 */
function editResearchArea(idx) {
	var desc = $("#cdesc" + idx).attr("value");
	var newDesc = window.prompt("Modify Research Area ", desc);
	newDesc = newDesc.trim();
	if (newDesc.length > 200) {
		newDesc = newDesc.substring(0, 200);
	}
	return newDesc;
}

/*
 * set up area of resear list tag. the main table detail is not set up
 * initially.
 */
function setupListItem(code, name) {
	i++;
	var id1 = "item" + i;
	var tagId = "listcontrol" + i;
	var divId = "listcontent" + i;
	var idDiv;
	// for later change RA description
	if (jQuery.browser.msie) {
		idDiv = $('<div></div>').attr("id", "itemText" + i).html(
				code + " : " + name);
	} else {
		idDiv = $('<span>').attr("id", "itemText" + i)
				.html(code + " : " + name);
	}
	var tag = $('<a style = "margin-left:2px;" ></a>').attr("id", tagId).html(
			idDiv);
	var detDiv = $(
			'<div  class="hierarchydetail" style="margin-top:2px; "></div>')
			.attr("id", divId);
	var hidracode = $('<input type="hidden" id = "racode" name = "racode" />')
			.attr("id", "racode" + i).attr("name", "racode" + i).attr("value",
					code);
	hidracode.appendTo(detDiv);
	$(tag).click(
			function() {
				$(".hierarchydetail:not(#" + divId + ")").slideUp(300);
				if ($(this).siblings('div:eq(1)').children('table:eq(0)')
						.size() == 0) {
					var idx = $(this).attr("id").substring(11);
					tableTag(code + " : " + name, "item" + idx).appendTo(
							$("#listcontent" + idx));
					if ($("#" + divId).is(":hidden")) {
						$("#listcontent" + idx).show();
					}
				} else {

					// $(".hierarchydetail:not(#"+divId+")").slideUp(300);
					$("#" + divId).slideToggle(300);
					// $("#"+divId).show();;
				}
				// this is a new item, so should not need to loadchildren
			});
	var listitem = $('<li class="closed"></li>').attr("id", id1).html(tag);
	detDiv.appendTo(listitem);
	return listitem;
}

/*
 * load children area of research when parents RA is expanding.
 */
function loadChildrenRA(nodeName, tagId) {
	var parentNode = $("#" + tagId);
	var liNode = parentNode.parents('li:eq(0)');
	var ulNode = liNode.children('ul:eq(0)');
	var inputNodev;

	if (liNode.children('ul').size() == 0
			|| ulNode.children('input').size() == 0) {
		$
				.ajax( {
					url : 'researchAreaAjax.do',
					type : 'GET',
					dataType : 'html',
					data : 'researchAreaCode=' + getResearchAreaCode(liNode),
					cache : false,
					async : false,
					timeout : 1000,
					error : function() {
						alert('Error loading XML document');
					},
					success : function(xml) {
						var ulTag;
						if (liNode.children('ul').size() == 0) {
							ulTag = $('<ul class="filetree"></ul>').attr("id",
									"ul" + i);
						} else {
							ulTag = ulNode;
						}

						ulTag.appendTo(liNode);
						var loadedId = "loaded" + i;
						var inputtag = $('<input type="hidden"></input>').attr(
								"id", loadedId);
						inputtag.appendTo(ulTag);
						$(xml)
								.find('h3')
								.each(
										function() {
											var item_text = $(this).text();
											i++;
											var racode = item_text.substring(0,
													item_text.indexOf("%3A"))
													.trim();
											item_text = item_text.replace(
													"%3A", ":");
											var id = "item" + i;
											var tagId = "listcontrol" + i;
											var divId = "listcontent" + i;

											var idDiv;
											if (jQuery.browser.msie) {
												idDiv = $('<div></div>').attr(
														"id", "itemText" + i)
														.html(item_text); 
											} else {
												idDiv = $('<span>').attr("id",
														"itemText" + i).html(
														item_text); 
											}
											var tag = $(
													'<a style = "margin-left:2px;" ></a>')
													.attr("id", tagId).html(
															idDiv);
											var detDiv = $(
													'<div  class="hierarchydetail" style="margin-top:2px; "></div>')
													.attr("id", divId);
											var hidracode = $(
													'<input type="hidden" id = "racode" name = "racode" />')
													.attr("id", "racode" + i)
													.attr("name", "racode" + i)
													.attr("value", racode);
											hidracode.appendTo(detDiv);
											tag.click(function() {
													$(
															".hierarchydetail:not(#"
																	+ divId
																	+ ")")
															.slideUp(300);
													var idx = $(this)
															.attr("id")
															.substring(11);
													if ($(this)
															.siblings(
																	'div:eq(1)')
															.children(
																	'table:eq(0)')
															.size() == 0) {
														tableTag(item_text,
																"item" + idx)
																.appendTo(
																		$("#listcontent"
																				+ idx));
														if ($(
																"#listcontent"
																		+ idx)
																.is(":hidden")) {
															$(
																	"#listcontent"
																			+ idx)
																	.show();
														}
													} else {
														$("#listcontent" + idx)
																.slideToggle(
																		300);
													}

													loadChildrenRA(item_text,
															tagId);
												});
											var listitem = $(
													'<li class="closed"></li>')
													.attr("id", id).html(tag);
											ulTagId = ulTag.attr("id");
											detDiv.appendTo(listitem);
											var childUlTag = $('<ul></ul>')
													.attr("id", "ul" + i);
											childUlTag.appendTo(listitem);
											listitem.appendTo(ulTag);
											// force to display folder icon
											$("#researcharea").treeview( {
												add : listitem
													});

											if (i == 1) {
												// alert (listitem.html());
											}

										});
					}
				});
	}
	loadedidx = i;
} // end loadChildrenRA

/*
 * Utility function to get code from 'code : description' This need to be
 * refined because if code contains ':', then this is not working correctly.
 */
function getResearchAreaCode(node) {
	return $("#racode" + node.attr("id").substring(4)).attr("value");
}

/*
 * similar to getResearchAreaCode, except this is for 'description'
 */
function getResearchAreaDescription(code, nodeName) {

	var endIdx = nodeName.indexOf(":", code.length);
	//alert(code+"-"+code.length+"-"+endIdx+"-"+nodeName);
	return nodeName.substring(endIdx + 2);
	// return nodeName;
}

/*
 * create insert sql statement
 */
function getInsertClause(code, parentCode, description) {

	var columns = "RESEARCH_AREA_CODE,PARENT_RESEARCH_AREA_CODE,HAS_CHILDREN_FLAG, DESCRIPTION, update_timestamp, update_user";
	description = description.replace(/'/g, "''");
	description = description.replace(/&/g, ";amp");

	var values = "'" + code + "','" + parentCode + "', 'N', '" + description
			+ "', sysdate, user";
	return "insert R values(" + values + ")";
}

/*
 * create delete sql statement
 */
function getDeleteClause(code, parentCode) {

	return "delete R'" + code + "' and PARENT_RESEARCH_AREA_CODE = '"
			+ parentCode + "'";
}

/*
 * create update sql statement to update description
 */
function getUpdateClause(code, newDesc) {
	newDesc = newDesc.replace(/'/g, "''");
	newDesc = newDesc.replace(/&/g, ";amp");
	return "update R'" + newDesc + "' where RESEARCH_AREA_CODE = '" + code
			+ "'";
}

function okToSave() {
	alert("oktosave ");
	document.getElementById("sqlScripts").value = sqlScripts;
	return "true";

}

// <!-- initial state -->
$(".hierarchydetail").hide();
// <!-- hidedetail -->
$(".hidedetail").toggle(function() {
	$(".hierarchydetail").slideUp(300);
});
// <!-- listcontent00 -->
$("#listcontrol00").click(function() {
	$(".hierarchydetail:not(#listcontent00)").slideUp(300);
	$("#listcontent00").slideToggle(300);
});

function hasFormAlreadyBeenSubmitted() {
	// return false;
}

$(document).ready(function() {

		// performance test
		loadFirstLevel();
		$("#listcontent00").show();
		// //$("#listcontent00").slideToggle(300);
		// $("#listcontrol00").show();
		loadedidx = i;
	})
$("#loading").hide();

/*
 * function to save what has been done up to this point.
 */
$("#save").click(function() {
	if (sqlScripts.indexOf("#;#") > -1) {
		// if current sqlScripts is not in array yet
		// 10 should be fine to use as check
		sqls[sqlidx++] = sqlScripts;
	}
	// alert ("save"+sqls.length+sqlScripts);

	for ( var k = 0; k < sqls.length; k++) {
		if (sqls[k] != '') {
			sqlScripts = sqls[k];
			sqlScripts = sqlScripts.replace(/#;#/g, ";;;");
			$("#headermsg").html(""); // clear error message
			var retmsg;
			$.ajax( {
				url : 'researchAreaAjax.do',
				type : 'GET',
				dataType : 'html',
				cache : false,
				data : 'sqlScripts=' + sqlScripts + '&addRA=S',
				async : false,
				timeout : 1000,
				error : function() {
					// alert('Error loading XML document');
					// jumpToAnchor('topOfForm');
					$('<span id="msg"/>').css("color", "red").html(
							"Error when save Areas of Research").appendTo(
							$("#headermsg"))
					$('<br/>').appendTo($("#headermsg"));

				},
				success : function(xml) {
					$(xml).find('h3').each(function() {
						retmsg = $(this).text();
						// alert(raExist);

						});
					if (retmsg == 'Success') {
						sqlScripts = "";
						sqls[k] = "";
						$('<span id="msg"/>').css("color", "black").html(
								"Areas of Research saved successfully")
								.appendTo($("#headermsg"));
						$('<br/>').appendTo($("#headermsg"));
						// jumpToAnchor('topOfForm');
						// alert("success"+xml);
					} else {
						// alert (retmsg);
						$('<span id="msg"/>').css("color", "red").html(
								"Error when save Areas of Research <br/>"
										+ retmsg).appendTo($("#headermsg"))
						$('<br/>').appendTo($("#headermsg"));
					}
				}
			});
		}
	} // end for
	sqlidx = 0;
	loadedidx = i;
	return false;
});

$("#close")
		.click(function() {
			if (sqlScripts.indexOf("#;#") > -1) {
				// if current sqlScripts is not in array yet
				// 10 should be fine to use as check
				sqls[sqlidx++] = sqlScripts;
			}
			// alert ("save"+sqls.length+sqlScripts);
			if (sqls.length > 0
					&& confirm('Do you want to save changes to Research Area Hierarchy?')) {
				for ( var k = 0; k < sqls.length; k++) {
					if (sqls[k] != '') {
						sqlScripts = sqls[k];
						sqlScripts = sqlScripts.replace(/#;#/g, ";;;");
						$("#headermsg").html(""); // clear error message
						var retmsg;
						$
								.ajax( {
									url : 'researchAreaAjax.do',
									type : 'GET',
									dataType : 'html',
									cache : false,
									data : 'sqlScripts=' + sqlScripts + '&addRA=S',
									async : false,
									timeout : 1000,
									error : function() {
										// alert('Error loading XML document');
										// jumpToAnchor('topOfForm');
										$('<span id="msg"/>')
												.css("color", "red")
												.html(
														"Error when save Areas of Research")
												.appendTo($("#headermsg"))
										$('<br/>').appendTo($("#headermsg"));

									},
									success : function(xml) {
										$(xml).find('h3').each(function() {
											retmsg = $(this).text();
											// alert(raExist);

											});
										if (retmsg == 'Success') {
											sqlScripts = "";
											sqls[k] = "";
											$('<span id="msg"/>')
													.css("color", "black")
													.html(
															"Areas of Research saved successfully")
													.appendTo($("#headermsg"));
											$('<br/>')
													.appendTo($("#headermsg"));
											// jumpToAnchor('topOfForm');
											// alert("success"+xml);
										} else {
											// alert (retmsg);
											$('<span id="msg"/>').css("color",
													"red").html(
													"Error when save Areas of Research <br/>"
															+ retmsg).appendTo(
													$("#headermsg"))
											$('<br/>')
													.appendTo($("#headermsg"));
										}
									}
								});
					}
				} // end for
			} // end confirm
			sqlidx = 0;
			loadedidx = i;
			// return false;
		});


/*
 * paste to root node
 */
 $("#paste0").click(function() {
	if (removedNode || cutNode) {
		var ulTag = $("#researcharea");
	if (removedNode) {
	} else {
		var liId = cutNode.attr("id");
		if ("researcharea" == $("li#" + liId).parents('ul:eq(0)').attr("id")) {
			alert("can't paste to the root node");
		} else {
			var liId = cutNode.attr("id");
			var parentRACode  = getResearchAreaCode($("li#" + liId).parents('li:eq(0)'));
			

			addSqlScripts(getDeleteClause(
					getResearchAreaCode(cutNode), parentRACode));
			$("li#" + liId).remove();
			cutNode.appendTo(ulTag);
			addSqlScripts(getInsertClause(
					getResearchAreaCode(cutNode),
					"000001",
					getResearchAreaDescription(
							getResearchAreaCode(cutNode),
							cutNode.children('a:eq(0)').text())));
			cutNode = null;
			$("#pcode"+$("li#" + liId).attr("id").substring(4)).html("000001");
			//ulTag.appendTo(parentNode);
		} // if then else if not paste back to parent node
	}
}// if removednode
return false; // eliminate page jumping
}	);


/*
 * To keep newnodes list up-to-date. This will prevent cases like add
 * 01->01.1->01.1.1. 01.1 & 01.1.1 are new. remove 01.1 will also remove 01.1.1.
 * This function will remove both the newnodes list.
 */
function deleteChild(childid) {

	var childrenli = $("#" + childid).children('ul.eq(0)').children('li');
	if (newNodes != ";"
			&& newNodes.indexOf(";" + getResearchAreaCode($("#" + childid))
					+ ";") > -1) {
		newNodes = newNodes.replace(";" + getResearchAreaCode($("#" + childid))
				+ ";", ";");
	}

	if (childrenli.size() > 0) {

		childrenli.each(function() {
			deleteChild($(this).attr("id"));
		});
	}

}
