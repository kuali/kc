var raChanges = new RaChanges();
var nextRaChangeProcessIdx = 0;

var cutNode;
var ulTagId;
var deletedNodes = "";
var newNodes = ";";
var icur = 1;
var activeOnly = true;

/**
 * This class keeps track of all research area changes.
 * (Note: use only public methods to interact with this class.)
 */
function RaChanges() {
	
	/**
	 * This public method marks the research area as created.
	 * @param idx - html element id index of the research area to be created
	 * @param code - the research area code of the research area to be created
	 * @param parentCode - the parent's research area code of the research area to be created
	 * @param description - the description of the research area to be created
	 * @param active - the active indicator of the research area to be created
	 */
	this.create = function(idx, code, parentCode, description, active) {
		// just in case - the nextRaChangeProcessIdx is cleared to ensure that no change is missed.
		nextRaChangeProcessIdx = 0;

		var ra = new Object();
		ra.code = code;
		ra.parentCode = parentCode;
		ra.description = description;
		if (active) {
			ra.active = "true";
		} else {
			ra.active = "false";
		}
		getRaChangesElement(idx, code).create = ra;
	}

	/**
	 * This public method marks the description of the research area as updated.
	 * @param idx - html element id index of the research area to be updated
	 * @param code - the research area code of the research area to be updated
	 * @param description - the new description of the research area to be updated
	 */
	this.updateDescription = function(idx, code, description) {
		// just in case - the nextRaChangeProcessIdx array is cleared to ensure that no change is missed.
		nextRaChangeProcessIdx = 0;

	    getRaChangesElement(idx, code).updateDescription = description;
    }
	
	/**
	 * This public method marks the active indicator of the research area as updated.
	 * @param idx - html element id index of the research area to be updated
	 * @param code - the research area code of the research area to be updated
	 * @param active - the new active indicator of the research area to be updated
	 */
	this.updateActiveIndicator = function(idx, code, active) {
		// just in case - the nextRaChangeProcessIdx array is cleared to ensure that no change is missed.
		nextRaChangeProcessIdx = 0;

    	getRaChangesElement(idx, code).updateActiveIndicator = active;
	}
	
	/**
	 * This public method marks the research area's parent changed.
	 *  
	 */
	this.updateParent = function(idx, code, oldParentCode, newParentCode) {
		// just in case - the nextRaChangeProcessIdx array is cleared to ensure that no change is missed.
		nextRaChangeProcessIdx = 0;

		if (getRaChangesElement(idx, code).updateParent.oldParentCode === undefined) {
			getRaChangesElement(idx, code).updateParent.oldParentCode = oldParentCode;
		}
    	getRaChangesElement(idx, code).updateParent.newParentCode = newParentCode;
	}
	
	/**
	 * This public method marks the research area as deleted.
	 * @param idx - html element id index of the research area to be deleted
	 * @param code - the research area code of the research area to be deleted
	 * 
	 * TODO remove this
	this.markDeleted = function(idx, code) {
		// just in case - the nextRaChangeProcessIdx array is cleared to ensure that no change is missed.
		nextRaChangeProcessIdx = 0;

		getRaChangesElement(idx, code).deleteIndicator = "DELETE";
	}
	*/
	
	/**
	 * This public method checks if more change data exists that can be 
	 * gotten via getChangeData.
	 * @return true if more data is available, false otherwise
	 */
	this.moreChangeData = function() {
		if (nextRaChangeProcessIdx < raArray.length) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This public method returns the XML like encoded research area changes in a block of 
	 * max 1900 characters.  Each block is 
	 * which then is passed to the server to apply the changes. In order to
	 * support the url length limit (max 1900 characters) the result string
	 * is split in multiple strings and returned in an array.  The strings 
	 * can be independently processed by the server (i.e. they don't need 
	 * to be joined). 
	 * The format is as follows:
     * <RaChanges>
     *   <RaChangesElement>
     *       <RaCreate>
     *           <Code></Code>
     *           <ParentCode></ParentCode>
     *           <Description></Description>
     *           <Active></Active>
     *       </RaCreate>
     *       <RaUpdateDescription>
     *           <Code></Code>
     *           <Description></Description>
     *       </RaUpdateDescription>
     *       <RaUpdateActiveIndicator>
     *           <Code></Code>
     *           <Active></Active>
     *       </RaUpdateActiveIndicator>
     *       <RaUpdateParent>
     *           <Code></Code>
     *           <OldParent></OldParent>
     *           <NewParent></NewParent>
     *       </RaUpdateParent>
     *       <RaDelete>
     *           <Code></Code>
     *       </RaDelete>
     *   </RaChangesElement>
     * </RaChanges>
	 * @return array of the xml formated changes
	 */
	this.getChangeData = function() {
		var string = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><RaChanges>";
		for (var idx = 0; idx < raArray.length; idx++) {
			if (raArray[idx] !== undefined) {
			    var addString = raArray[idx].toString();
			    if (string.length + addString.length + 12 > 1900) {
				    break;
			    } else {
			        string = string + addString;
			    }
			}
		}
		nextRaChangeProcessIdx = idx;
		string = string + "</RaChanges>";
		return string;
	}
	
	/**
	 * This public method removes the most recent retrieved changed data.
	 * The method should be called after the data from getChangeData() has been
	 * successfully saved by the server.
	 */
	this.confirmSucess = function() {
		if ((nextRaChangeProcessIdx >= 0) && (nextRaChangeProcessIdx < raArray.length)) {
			raArray = raArray.slice(nextRaChangeProcessIdx, raArray.length);
			nextRaChangeProcessIdx = 0;
		} else {
			// need to reinitialize raArray after last successful batch script save.
			// otherwise, the residue may affect subsequent change & save.
			raArray = new Array();
		}
	}
    
    /**
     *  Private member that contains all the research areas indexed by the html index number of the field ids.
     */ 	
	var raArray = new Array();

	/**
     *  Private member that contains raArray indexes for the data that has been returned for submission.
     */ 	
	var inProgress = new Array();
	
	/**
	 * Private method to get the indexed container that tracks the changes for a research area.
	 * The method will create a new container if none exists.
	 * @param idx - index of the container that corresponds to the html index number found on the field ids
	 * @param code = the research area code of the research area to which the changes belong
	 * @return RAChangesElement - the container for the research area changes
	 */
	function getRaChangesElement(idx, code) {
		if (raArray[idx] === undefined) {
			raArray[idx] = new RaChangesElement(code);
		}
		return raArray[idx];
	}

    /**
     * Private container class which holds all changes for a specific research area.
     * @param code - the research area code of the research area to which the changes belong
     */
	function RaChangesElement(code) {
		this.code = code;
		this.create = new Object;
		this.updateDescription = "";
		this.updateActiveIndicator = "";
		this.updateParent = new Object;
		this.deleteIndicator = "";
	
		/**
		 * Private method to which returns the changes for this research area in an xml like format.
		 * The method is smart and ignores changes that have become obsolete by subsequent changes.
		 * @return xml like string with changes of the research area
		 */
		this.toString = function() {
			var string = "<RaChangesElement>";
			if (this.deleteIndicator > "") {
				if (researchAreaExistsOnServer(code, "")) {
				    string = string + "<RaDelete><Code>" + escape(this.code) + "</Code></RaDelete>";
				}
			} else if (this.create.code > "") {
					string = string + "<RaCreate><Code>" + escape(this.create.code) +  "</Code><ParentCode>";
					if (this.updateParent.newParentCode > "") {
						string = string + escape(this.updateParent.newParentCode);
					} else {
						string = string + escape(this.create.parentCode);
					}
					string = string + "</ParentCode><Description>";
					if (this.updateDescription > "") {
						string = string + escape(this.updateDescription);
					} else {
						string = string + escape(this.create.description);
					}
					string = string + "</Description><Active>";
					if (this.updateActiveIndicator > "") {
						string = string + escape(this.updateActiveIndicator);
					} else {
						string = string + escape(this.create.active);
					}
					string = string + "</Active></RaCreate>";
					+ escape(this.create) + "</RaCreate>";
			} else {
				if (this.updateDescription > "") {
					string = string + "<RaUpdateDescription><Code>" + escape(this.code) + "</Code><Description>" + escape(this.updateDescription) + "</Description></RaUpdateDescription>";
				}
				if (this.updateActiveIndicator > "") {
					string = string + "<RaUpdateActiveIndicator><Code>" + escape(this.code) + "</Code><Active>" + escape(this.updateActiveIndicator) + "</Active></RaUpdateActiveIndicator>";
				}
				if (this.updateParent.newParentCode > "") {
					string = string + "<RaUpdateParent><Code>" + escape(this.code) + "</Code><OldParent>" + escape(this.updateParent.oldParentCode) + "</OldParent><NewParent>" + escape(this.updateParent.newParentCode) + "</NewParent></RaUpdateParent>";
				}
			}
			string = string + "</RaChangesElement>";
			return string;
		}
	}
} // end of RaChanges class



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
	 * TODO : may remove following ajax start/complete 
	 */

	$(document).ajaxStart(function() {
			$("#loading").show();
		});

	$(document).ajaxComplete(function() {
			$("#loading").hide();
		});
	
	loadFirstLevel();

}); // $(document).ready

/*
 * show/hide inactive research area toggle button
 */
$("#showhidebutton").click(function() {
	if (raChanges.moreChangeData() && confirm('Do you want to save changes to Research Area Hierarchy?')) {
		save();
	}

	$("#researcharea").empty();
	raChanges = new RaChanges();
	nextRaChangeProcessIdx = 0;
	cutNode = null;
	deletedNodes = "";
	newNodes = ";";
	icur = 1;

	if (activeOnly) {
		$("#showhidebutton").attr("src", "kr/images/tinybutton-hideinact.gif");
		activeOnly = false;
	} else {
		$("#showhidebutton").attr("src", "kr/images/tinybutton-showinact.gif");
		activeOnly = true;
	}
	
	loadFirstLevel();
	$("#listcontent00").show();
	return false;
});

/*
 * top level to add research area
 */
$("#add0")
		.click(function() {
			// click 'add' for 000001
				var trNode = $(this).parents('tr:eq(0)');
				if (trNode.children('td:eq(1)').children('input:eq(0)').attr(
						"value") == "") {
					alert("must enter research area code");
				} else if (trNode.children('td:eq(2)').children('input:eq(0)')
						.attr("value") == "") {
					alert("must enter research area");
				} else {
					var raCode = trNode.children('td:eq(1)').children('input:eq(0)').attr("value");
					var raExist = researchAreaExistsOnServer(raCode, deletedNodes)
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
								.children('input:eq(0)').attr("value"),
								trNode.children('td:eq(3)').children('input:eq(0)')
								.attr("checked"));
						// need this ultag to force to display folder.
						var childUlTag = $('<ul></ul>').attr("id", "ul" + icur);
						childUlTag.appendTo(listitem);

						// this is new nodes, so it is same as already loaded
						// from DB
						var loadedId = "loaded" + icur;
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
						// record changes
						raChanges.create( icur,
								         trNode.children('td:eq(1)').children('input:eq(0)').attr("value"), 
								         '000001', 
								         trNode.children('td:eq(2)').children('input:eq(0)').attr("value"),
								         trNode.children('td:eq(3)').children('input:eq(0)').attr("checked"));
						
				        // display add message
						$("#headermsg").html("Research Area Code " + trNode.children('td:eq(1)').children('input:eq(0)').attr('value') + " added");

						// reset add line
						trNode.children('td:eq(1)').children('input:eq(0)').attr("value", "");
				        trNode.children('td:eq(2)').children('input:eq(0)').attr("value", "");
				        trNode.children('td:eq(3)').children('input:eq(0)').attr("checked", true);
				        
					} else {
						alert("Research Area Code " + trNode.children('td:eq(1)').children('input:eq(0)').attr('value') + " already exist");
					}
				}
				;

				return false;
			}); // add0

/*
 * Load first level area of research when page is initially loaded
 */
function loadFirstLevel() {
	var addRA =""
	if (activeOnly) {
		addRA = "A";
	}
	$.ajax( {
				url : 'researchAreaAjax.do',
				type : 'GET',
				dataType : 'html',
				cache : false,
				data : 'researchAreaCode=000001&addRA=' + addRA,
				async : false,
				timeout : 1000,
				error : function() {
					alert('Error loading XML document');
				},
				success : function(xml) {
					$(xml)
							.find('h3')
							.each(
									function() {
										var item_text = $(this).text();
										icur++;
										var racode = item_text.substring(0,
												item_text.indexOf("%3A"))
												.trim();
										var activeflag = item_text.substring(
												item_text.indexOf("%4A") + 3)
												.trim();
										item_text = item_text.replace("%3A",
												":");
										item_text = item_text.substring(0,
												item_text.indexOf("%4A"))
												.trim();
										var id = "item" + icur;
										var tagId = "listcontrol" + icur;
										var divId = "listcontent" + icur;

										// NOTES : if use 'div', then FF will
										// display the '+' and idDiv in
										// separate lines. IE7 is fine
										// But 'IE7 has problem with 'span'
										var idDiv;
										if (jQuery.browser.msie) {
											idDiv = $('<div></div>').attr("id",
													"itemText" + icur).html(
													item_text);
										} else {
											idDiv = $('<span>').attr("id",
													"itemText" + icur).html(
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
												.attr("id", "racode" + icur).attr(
														"name", "racode" + icur)
												.attr("value", racode);
										hidracode.appendTo(div);
										var hidactiveflag = $(
											    '<input type="hidden" id = "activeflag" name = "activeflag" />')
											    .attr("id", "activeflag" + icur).attr(
											    		"name", "activeflag" + icur)
											    .attr("value", activeflag);
										hidactiveflag.appendTo(div);	    			
										tag
												.click(function() {
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
														if ($("#" + divId).is(
																":hidden")) {
															$(
																	"#listcontent"
																			+ idx)
																	.show();
															// $("#listcontent"+idx).slideToggle(300);
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
										ulTagId = "researcharea";
										div.appendTo(listitem);
										// need this ultag to force to display
										// folder.
										var childUlTag = $('<ul></ul>').attr(
												"id", "ul" + icur);
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

//	var link = $('<a href="#" class="hidedetail"><img src="kr/static/images/tinybutton-hide.gif" align="absmiddle" border="0" width="45" height="15"></a>');
	var tag = $(
			'<th  style="background:#939393;height:18px;color:#FFFFFF;text-align:left;padding-left:4px;" align="left"></th>')
			.attr("id", "raHeader" + id.substring(4)).html(name);
//	link.prependTo(tag);
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
			'<a href="#"><img src="static/images/tinybutton-removenode.gif" width="79" height="15" border="0" alt="Remove Node" title="Remove this node and its child research areas"></a>&nbsp')
			.attr("id", "remove" + idx).click(
					function() {
						var liId = "li#" + id;
						deleteResearchArea(liId);
						/*
						 * TODO remove this
						var parentRACode;
						if ($(liId).parents('li:eq(0)').size() == 0) {
							parentRACode = '000001';
						} else {
							parentRACode = getResearchAreaCode($(liId).parents(
									'li:eq(0)'));
						}
						
						raChanges.markDeleted(idx, getResearchAreaCode($(liId)));
						if (deletedNodes == '') {
							deletedNodes = getResearchAreaCode($(liId));
						} else {
							deletedNodes = deletedNodes + ";"
									+ getResearchAreaCode($(liId));
						}
						deleteChild(id);
						
						$(liId).remove();
						cutNode = null;
						*/
						return false; // eliminate page jumping
					});
	tag.html(image);
	image = $(
			'<a href="#"><img src="static/images/tinybutton-cutnode.gif" width="79" height="15" border="0" alt="Cut Node" title="Cut this node and its child research areas.  (Node will not be removed until you paste it.)"></a>&nbsp')
			.attr("id", "cut" + idx).click(function() {
				var liId = "li#" + id;
				cutNode = $(liId).clone(true);
				return false; // eliminate page jumping
				});
	image.appendTo(tag);
	image = $(
			'<a href="#"><img src="static/images/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Paste Node" title="Paste your previously cut node structure under this node"></a>')
			.attr("id", "paste" + idx).click(function() {

				if (cutNode) {
					var parentNode = $("#" + id);
					var ulTag = parentNode.children('ul');
					if (ulTag.size() > 0) {
						// alert(ulTag.attr("id"));
				} else {
					alert("not found")
					icur++;
					ulTag = $('<ul class="filetree"></ul>')
							.attr("id", "ul" + icur);
				}
					var liId = cutNode.attr("id");
					if (id == $("li#" + liId).parents('li:eq(0)').attr("id")) {
						alert("Paste failed:  Already at this node.");
					} else if (id == liId) {
						alert("Paste failed:  Can not paste to itself.");
			// We also need to check if we are pasting the node to one of its children.
		    // Because when this happens then these notes become inaccessible. A bad thing!
					} else {
						var parentRACode;
						// NOTE : cutNode.parents('li:eq(0)') is not working
						// $("li#"+liId).parents('li:eq(0)')is ok
						if ($("li#" + liId).parents('li:eq(0)').size() == 0) {
							parentRACode = '000001';
						} else {
							parentRACode = getResearchAreaCode($("li#" + liId)
									.parents('li:eq(0)'));
						}

						raChanges.updateParent(liId.substring(4), getResearchAreaCode(cutNode), parentRACode, getResearchAreaCode($("#" + id)));

						$("li#" + liId).remove();
						cutNode.appendTo(ulTag);
						$("#pcode" + $("li#" + liId).attr("id").substring(4))
								.html(getResearchAreaCode($("#" + id)));
						cutNode = null;
						ulTag.appendTo(parentNode);
						if ($("#" + id).children('div:eq(0)').attr("class")
								.indexOf("expandable") > 0) {
							/*
							 * to force it to show the child node just added.
							 * the div 'class' is changing based on whether the
							 * node is expand or collapsed
							 */
							$("#" + id).children('div:eq(0)').click();
							$("#listcontrol" + liId.substring(4)).click();
						}

					} // if then else if not paste back to parent node

			}
			return false; // eliminate page jumping
		}	);

	image.appendTo(tag);
	var notetag = $('<th style="text-align:right;"></th>').html("Node:");
	tag = $('<tr></tr>').html(tag);
	notetag.prependTo(tag);
	tblTag.html(tag);



	getTableHeader().appendTo(tblTag);
	getEditRow(name, id).appendTo(tblTag);
	getAddRow(id).appendTo(tblTag);
	tag = $('<td class="subelementcontent"></td>').html(tblTag);
	tag = $('<tr></tr>').html(tag);
	tag = $('<tbody></tbody>').html(tag);
	return tag;
}

/*
 * set up the research area table header.
 */
function getTableHeader() {
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
	var tdTag = $('<td class="infoline"></td>').html('<b>Active</b>');
	tdTag.appendTo(trTag);
	var tdTag = $('<td class="infoline" style="width:65px;"></td>').html(
			'<b>Action</b>');
	tdTag.appendTo(trTag);
	return trTag;
}

/*
 * set up the 'Edit' row for research area
 */
function getEditRow(name, id) {
	// 3rd tr
	var idx = id.substring(4);
	var raCode = getResearchAreaCode($("#" + id));
	var trTag1 = $('<tr></tr>');
	var tag1 = $('<th style="text-align:right;"></th>').html('Edit:');
	var tdTag1;
	var ulTagId = $("li#" + id).parents('ul:eq(0)').attr("id");
	// if (i < 5) {
	// alert(id+"-"+ulTagId+"-"+$("ul#" + ulTagId).parents('li:eq(0)').size())
	// }
	if ($("ul#" + ulTagId).parents('li:eq(0)').size() == 0) {
		// TODO : this is the second level, the children of '000001'
		tdTag1 = $('<td></td>').attr("id", "pcode" + idx).html("000001");
	} else {
		tdTag1 = $('<td></td>').attr("id", "pcode" + idx).html(
				getResearchAreaCode($("ul#" + ulTagId).parents('li:eq(0)')));
	}
	trTag1.html(tag1);
	tdTag1.appendTo(trTag1);
	tdTag1 = $('<td></td>').html(raCode);
	tdTag1.appendTo(trTag1);
	tdTag1 = $('<td></td>')
			.html(
					$(
							'<input type="text" name="m3" style="width:100%;" readonly="true"/>')
							.attr("id", "cdesc" + idx).attr(
									"value",
									getResearchAreaDescription(raCode, name)));
	tdTag1.appendTo(trTag1);
	if (isActive($("#" + id))) {
	    tdTag1 = $('<td></td>').html( $('<input type="checkbox" name="m4" checked/>').attr("id", "checkActive" + idx));
	} else {
	    tdTag1 = $('<td></td>').html( $('<input type="checkbox" name="m4"/>').attr("id", "checkActive" + idx));
	}
	tdTag1.click(function() {
		if ($('#checkActive' + idx).attr('checked')) {
			if ((ulTagId == 'researcharea') || ($('#checkActive' + ulTagId.substring(2)).attr('checked'))) {
			    raChanges.updateActiveIndicator(idx, raCode, 'true');
			} else {
				$('#checkActive' + idx).attr('checked', false);
				alert('Parent node must be active');
			}
		} else {
			return deactivateResearchArea(id);
		}
    });
	tdTag1.appendTo(trTag1);
	tag1 = $('<th class="infoline" style="text-align:center;"></th>');
	var editlink = $(
			'<a href="#"><img src="static/images/tinybutton-edit1.gif" width="40" height="15" border="0" title="update"></a>')
			.attr("id", "editRA" + idx)
			.click(function() {
				var header = $("#raHeader" + $(this).attr("id").substring(6));
				// $("#raHeader"+i) will not work because "i" is evaluated when
					// this function is called; not when this function is
					// created
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
						raChanges.updateDescription($(this).attr("id").substring(6), raCode, desc);
						// lots of trouble to update the description on item, so
						// add
						// additional 'div' tag for this purposes.
						// tried many different ways, include 'replace', but it
						// did not
						// work. So, finally decide on this approach.
					}
					return false; // eliminate page jumping
				}); // end editlink click

	tag1.html(editlink);
	tag1.appendTo(trTag1);

	return trTag1;
}

/*
 * set up 'add' row for research area
 */
function getAddRow(id) {
	// 4th tr
	var idx = id.substring(4);
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
			$('<input type="text" name="m3" value="" style="width:100%;" maxlength="200" size="80"/>')
					.attr("id", "desc" + idx));
	tdTag2.appendTo(trTag2);
	tdTag2 = $('<td></td>').html(
			$('<input type="checkbox" name="m4" value="true" checked/>')
					.attr("id", "active" + idx));
	tdTag2.appendTo(trTag2);
	tag2 = $('<th class="infoline" style="text-align:center;"></th>');
	var addlink = $(
			'<a href="#"><img src="static/images/tinybutton-add1.gif" width="40" height="15" border="0" title="Add this Sub-group"></a>')
			.attr("id", "addRA" + idx)
			.click(
					function() {
						var trNode = $(this).parents('tr:eq(0)');
						if (trNode.children('td:eq(1)').children('input:eq(0)')
								.attr("value") == "") {
							alert("must enter research area code");
						} else if (trNode.children('td:eq(2)').children(
								'input:eq(0)').attr("value") == "") {
							alert("must enter research area");
						} else { 
							var raCode = trNode.children('td:eq(1)').children('input:eq(0)').attr("value");
							var raExist = researchAreaExistsOnServer(raCode, deletedNodes);
							if (raExist == 'false'
									&& newNodes != ";"
									&& newNodes.indexOf(";"
											+ trNode.children('td:eq(1)')
													.children('input:eq(0)')
													.attr("value") + ";") > -1) {
								raExist = 'true';
							}
							if (raExist == 'false') {
								var parentNode = $("#" + id);
								var ulTag = parentNode.children('ul');
								if (parentNode.children('ul').size() == 0) {
									icur++;
									ulTag = $('<ul class="filetree"></ul>')
											.attr("id", "ul" + id.substring(4));
								}

								ulTag.appendTo(parentNode);

								var item_text = trNode.children('td:eq(1)').children('input:eq(0)').attr("value")
										+ " : " + trNode.children('td:eq(2)').children('input:eq(0)').attr("value");
								var listitem = setupListItem(trNode.children('td:eq(1)').children('input:eq(0)').attr("value"), 
										                     trNode.children('td:eq(2)').children('input:eq(0)').attr("value"), 
										                     trNode.children('td:eq(3)').children('input:eq(0)').attr("checked"));
								var childUlTag = $('<ul></ul>').attr("id","ul" + $(this).attr("id").substring(5));
								childUlTag.appendTo(listitem);
								// this is new nodes, so it is same as already
								// loaded from DB
								var loadedId = "loaded" + idx;
								var inputtag = $(
										'<input type="hidden"></input>').attr(
										"id", loadedId);
								inputtag.appendTo(childUlTag);

								listitem.appendTo(ulTag);
								// force to display folder icon
								$("#researcharea").treeview( {
									add : listitem
								});
								newNodes = newNodes + trNode.children('td:eq(1)').children('input:eq(0)').attr("value") + ";";

								raChanges.create(icur,
										         trNode.children('td:eq(1)').children('input:eq(0)').attr("value"),
										         getResearchAreaCode($("#" + id)),
										         trNode.children('td:eq(2)').children('input:eq(0)').attr("value"),
										         trNode.children('td:eq(3)').children('input:eq(0)').attr("checked"));
								
						        // display add message
								$("#headermsg").html("Research Area Code " + trNode.children('td:eq(1)').children('input:eq(0)').attr('value') + " added");

								// reset add line
								trNode.children('td:eq(1)').children('input:eq(0)').attr("value", "");
								trNode.children('td:eq(2)').children('input:eq(0)').attr("value", "");
								trNode.children('td:eq(3)').children('input:eq(0)').attr("checked", true);

								if (parentNode.children('div:eq(0)').attr(
										"class").indexOf("expandable") > 0) {
									/*
									 * to force it to show the child node just
									 * added. the div 'class' is changing based
									 * on whether the node is expand or
									 * collapsed
									 */
									parentNode.children('div:eq(0)').click();
									$(
											"#listcontrol"
													+ listitem.attr("id")
															.substring(4))
											.click();
								}
								// $("#listcontrol"+listitem.attr("id").substring(4)).show();
								// $("#listcontent"+listitem.attr("id").substring(4)).slideToggle(300);
							} else {
								alert("Research Area Code " + trNode.children('td:eq(1)').children('input:eq(0)').attr("value") + " already exist");
							}
						}
						return false; // eliminate page jumping
					}); // end addlink click

	tag2.html(addlink);
	tag2.appendTo(trTag2);
    return trTag2;
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
 * set up area of research list tag. the main table detail is not set up
 * initially.
 */
function setupListItem(code, name, activeflag) {
	icur++;
	var id1 = "item" + icur;
	var tagId = "listcontrol" + icur;
	var divId = "listcontent" + icur;
	var idDiv;
	// for later change RA description
	if (jQuery.browser.msie) {
		idDiv = $('<div></div>').attr("id", "itemText" + icur).html(
				code + " : " + name);
	} else {
		idDiv = $('<span>').attr("id", "itemText" + icur)
				.html(code + " : " + name);
	}
	var tag = $('<a style = "margin-left:2px;" ></a>').attr("id", tagId).html(
			idDiv);
	var detDiv = $(
			'<div  class="hierarchydetail" style="margin-top:2px; "></div>')
			.attr("id", divId);
	var hidracode = $('<input type="hidden" id = "racode" name = "racode" />')
			.attr("id", "racode" + icur).attr("name", "racode" + icur).attr("value",
					code);
	hidracode.appendTo(detDiv)
	var hidactiveflag = $('<input type="hidden" id = "activeflag" name = "activeflag" />')
            .attr("id", "activeflag" + icur).attr("name", "activeflag" + icur).attr("value", activeflag);
	hidactiveflag.appendTo(detDiv);
	
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
	var parentActiveFlag = $('#activeflag' + tagId.substring(11)).val();
	var liNode = parentNode.parents('li:eq(0)');
	var ulNode = liNode.children('ul:eq(0)');
	var inputNodev;
	var addRA = "";
	if (activeOnly) {
		addRA = "A";
	}
	if (liNode.children('ul').size() == 0
			|| ulNode.children('input').size() == 0) {
		$
				.ajax( {
					url : 'researchAreaAjax.do',
					type : 'GET',
					dataType : 'html',
					data : 'researchAreaCode=' + getResearchAreaCode(liNode) + '&addRA=' + addRA,
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
									"ul" + icur);
						} else {
							ulTag = ulNode;
						}

						ulTag.appendTo(liNode);
						var loadedId = "loaded" + icur;
						var inputtag = $('<input type="hidden"></input>').attr(
								"id", loadedId);
						inputtag.appendTo(ulTag);
						$(xml)
								.find('h3')
								.each(
										function() {
											var item_text = $(this).text();
											icur++;
											var racode = item_text.substring(0, item_text.indexOf("%3A")).trim();
											var activeflag = item_text.substring(item_text.indexOf("%4A") + 3).trim();
											item_text = item_text.replace("%3A", ":");
									        item_text = item_text.substring(0, item_text.indexOf("%4A")).trim();
											var id = "item" + icur;
											var tagId = "listcontrol" + icur;
											var divId = "listcontent" + icur;

											var idDiv;
											if (jQuery.browser.msie) {
												idDiv = $('<div></div>').attr("id", "itemText" + icur).html(item_text);
											} else {
												idDiv = $('<span>').attr("id", "itemText" + icur).html(item_text);
											}
											var tag = $('<a style = "margin-left:2px;" ></a>').attr("id", tagId).html(idDiv);
											var detDiv = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id", divId);
											var hidracode = $(
													'<input type="hidden" id = "racode" name = "racode" />')
													.attr("id", "racode" + icur)
													.attr("name", "racode" + icur)
													.attr("value", racode);
											hidracode.appendTo(detDiv);
											// check if parent active flag was unselected by user
											if ((activeflag == 'true') && (parentActiveFlag == 'false')) {
												var hidactiveflag = $(
									            '<input type="hidden" id = "activeflag" name = "activeflag" />')
									            .attr("id", "activeflag" + icur).attr(
									                    "name", "activeflag" + icur)
									            .attr("value", "false");
											} else {
												var hidactiveflag = $(
									            '<input type="hidden" id = "activeflag" name = "activeflag" />')
									            .attr("id", "activeflag" + icur).attr(
									                    "name", "activeflag" + icur)
									            .attr("value", activeflag);
											}
									        hidactiveflag.appendTo(detDiv);	    			
											tag
													.click(function() {
														$(".hierarchydetail:not(#" + divId + ")").slideUp(300);
														var idx = $(this).attr("id").substring(11);
														if ($(this)
																.siblings('div:eq(1)')
																.children('table:eq(0)')
																.size() == 0) {
															tableTag(
																	item_text,
																	"item"
																			+ idx)
																	.appendTo(
																			$("#listcontent"
																					+ idx));
															if ($(
																	"#listcontent"
																			+ idx)
																	.is(
																			":hidden")) {
																$(
																		"#listcontent"
																				+ idx)
																		.show();
															}
														} else {
															$(
																	"#listcontent"
																			+ idx)
																	.slideToggle(
																			300);
														}

														loadChildrenRA(
																item_text,
																tagId);
													});
											var listitem = $(
													'<li class="closed"></li>')
													.attr("id", id).html(tag);
											ulTagId = ulTag.attr("id");
											detDiv.appendTo(listitem);
											var childUlTag = $('<ul></ul>')
													.attr("id", "ul" + icur);
											childUlTag.appendTo(listitem);
											listitem.appendTo(ulTag);
											// force to display folder icon
											$("#researcharea").treeview( {
												add : listitem
											});

											if (icur == 1) {
												// alert (listitem.html());
											}

										});
					}
				});
	}
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
	// alert(code+"-"+code.length+"-"+endIdx+"-"+nodeName);
	return nodeName.substring(endIdx + 2);
	// return nodeName;
}

/*
 * similar to getResearchAreaCode, except this is for 'active'
 */
function isActive(node) {
	if ($("#activeflag" + node.attr("id").substring(4)).attr("value").trim() == 'true') {
		return true;
	} else {
		return false;
	}
}


//// <!-- initial state -->
//$(".hierarchydetail").hide();
//// <!-- hidedetail -->
//$(".hidedetail").toggle(function() {
//	//$(".hierarchydetail").slideUp(300);
//		$(this).parents('thead:eq(1)').next().slideDown(400);
//        $(this).html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
//	}, function() {
//		$(this).parents('thead:eq(1)').next().slideUp(400);
//        $(this).html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
//	});

// <!-- listcontent00 -->
$("#listcontrol00").click(function() {
	$(".hierarchydetail:not(#listcontent00)").slideUp(300);
	$("#listcontent00").slideToggle(300);
});

/**
 * This method inquieres with the server if the research area exists.
 */
function researchAreaExistsOnServer(researchAreaCode, ignoreNodes){
	var exists = "false";
	$.ajax( {
		url : 'researchAreaAjax.do',
		type : 'GET',
		dataType : 'html',
		data : 'researchAreaCode='
				+ researchAreaCode
				+ '&deletedRas=' + ignoreNodes
				+ '&addRA=Y',
		cache : false,
		async : false,
		timeout : 1000,
		error : function() {
			alert('Error loading XML document');
		},
		success : function(xml) {
			$(xml).find('h3').each(function() {
				exists = $(this).text();
			});
		}
	}); // end ajax
	
	return exists;
}

/**
 * This method submits the request to deactivate a research area to server. 
 * It first checks to see that there are no pending changes that need to be persisted, and submits only if so. 
 * If there are pending changes then it will simply ask the user to either save or abandon the changes. 
 */
function deactivateResearchArea(id) {
	if(raChanges.moreChangeData()) {
		if(confirm('You must save (or abandon) all outstanding changes before attempting a deactivation. Do you want to save changes to Research Area Hierarchy?')) {
			save();
		}
		
		$("#researcharea").empty();
		raChanges = new RaChanges();
		nextRaChangeProcessIdx = 0;
		cutNode = null;
		deletedNodes = "";
		newNodes = ";";
		icur = 1;
		
		loadFirstLevel();
		$("#listcontent00").show();
		return false;
	}
	else {
		var researchAreaCode = getResearchAreaCode($("#" + id));
		// alert("RAcode is " + researchAreaCode);
		var retValue = false;
		// alert("retvalue before ajax" + retValue);
		$("#headermsg").html(""); 
		$.ajax( {
			url : 'researchAreaAjax.do',
			type : 'POST',
			dataType : 'html',
			data : 'researchAreaCode='
					+ researchAreaCode
					+ '&addRA=I',
			cache : false,
			async : false,
			timeout : 1000,
			error : function() {
				alert('This research area cannot be deactivated because it (or one of its descendants) is being currently referenced.');
				retValue = false;
			},
			success : function(xml) {
				$(xml).find('h3').each(function() {
					retmsg = $(this).text();
					});
				if (retmsg == 'Success') {										
					$('<span id="msg"/>').css("color", "black").html("Research area deactivated successfully").appendTo($("#headermsg"));
					$('<br/>').appendTo($("#headermsg"));
					
					$('input[id^=checkActive]', 'li#' + id).each(function() {
						$(this).attr('checked', false);
					});					
				    $('input[id^=activeflag]', 'li#' + id).val('false');
				    
					retValue = true;
				} else {
					alert('This research area cannot be deactivated because it (or one of its descendants) is being currently referenced.');
					$('<span id="msg"/>').css("color", "red").html("Research area could not be deactivated.<br/>" + retmsg).appendTo($("#headermsg"))
					$('<br/>').appendTo($("#headermsg"));					
					retValue = false;
				}
			}
		}); // end ajax
		// alert("retvalue before returning" + retValue);
		return retValue;
	}
}

/**
 * This method submits the request to delete a research area to server. 
 * It first checks to see that there are no pending changes that need to be persisted, and submits only if so. 
 * If there are pending changes then it will simply ask the user to either save or abandon the changes. 
 */
function deleteResearchArea(liId) {
	if(raChanges.moreChangeData()) {
		if(confirm('You must save (or abandon) all outstanding changes before attempting a delete. Do you want to save changes to Research Area Hierarchy?')) {
			save();
		}
		
		$("#researcharea").empty();
		raChanges = new RaChanges();
		nextRaChangeProcessIdx = 0;
		cutNode = null;
		deletedNodes = "";
		newNodes = ";";
		icur = 1;
		
		loadFirstLevel();
		$("#listcontent00").show();
		return false;
	}
	else {
		var researchAreaCode = getResearchAreaCode($(liId));
		// alert("RAcode is " + researchAreaCode);
		$("#headermsg").html(""); 
		$.ajax( {
			url : 'researchAreaAjax.do',
			type : 'POST',
			dataType : 'html',
			data : 'researchAreaCode='
					+ researchAreaCode
					+ '&addRA=D',
			cache : false,
			async : false,
			timeout : 1000,
			error : function() {
				alert('This research area cannot be deleted (perhaps due to archival reasons), but you could try deactivating it.');
			},
			success : function(xml) {
				$(xml).find('h3').each(function() {
					retmsg = $(this).text();
					});
				if (retmsg == 'Success') {
					// alert("removing node now");
					$(liId).remove();
					cutNode = null;
					$('<span id="msg"/>').css("color", "black").html("Research area deleted successfully").appendTo($("#headermsg"));
					$('<br/>').appendTo($("#headermsg"));
				} else {
					$('<span id="msg"/>').css("color", "red").html("Research area could not be deleted.<br/>" + retmsg).appendTo($("#headermsg"))
					$('<br/>').appendTo($("#headermsg"));
					alert('This research area cannot be deleted (perhaps due to archival reasons), but you could try deactivating it.');
				}
			}
		}); // end ajax
		return false;
	}
}

/**
 * This Method submits the research area changes to the server in order to save them.
 */
function save() {
	var error = false;
	while(raChanges.moreChangeData() && !error) {
		$("#headermsg").html(""); // clear error message
		var retmsg;
		$.ajax( {
			url : 'researchAreaAjax.do',
			type : 'GET',
			dataType : 'html',
			cache : false,
			data : 'sqlScripts=' + raChanges.getChangeData() + '&addRA=S',
			async : false,
			timeout : 1000,
			error : function() {
			    error = true;
				$('<span id="msg"/>').css("color", "red").html("Error when save Areas of Research").appendTo($("#headermsg"))
				$('<br/>').appendTo($("#headermsg"));
			},
			success : function(xml) {
				$(xml).find('h3').each(function() {
					retmsg = $(this).text();
					});
				if (retmsg == 'Success') {
					raChanges.confirmSucess();
					$('<span id="msg"/>').css("color", "black").html("Areas of Research saved successfully").appendTo($("#headermsg"));
					$('<br/>').appendTo($("#headermsg"));
				} else {
					error = true;
					$('<span id="msg"/>').css("color", "red").html("Error when save Areas of Research <br/>" + retmsg).appendTo($("#headermsg"))
					$('<br/>').appendTo($("#headermsg"));
				}
			}
		});
	} // end while
}

/*
 * This Method processes the click on the save button.
 */
$("#save").click(function() {
	save();
	return false;
});
	
/*
 * This Method processes the click on the close button.
 */
$("#close").click(function() {
	if (raChanges.moreChangeData() && confirm('Do you want to save changes to Research Area Hierarchy?')) {
		save();
	}
});

/*
 * paste to root node
 */
$("#paste0").click(
		function() {
			if (cutNode) {
			var ulTag = $("#researcharea");
				var liId = cutNode.attr("id");
				if ("researcharea" == $("li#" + liId).parents('ul:eq(0)')
						.attr("id")) {
					alert("Paste failed:  Already at the root node.");
				} else {
					var parentRACode = getResearchAreaCode($("li#" + liId)
							.parents('li:eq(0)'));
					
					raChanges.updateParent(liId.substring(4), getResearchAreaCode(cutNode), parentRACode, "000001");

					$("li#" + liId).remove();
					cutNode.appendTo(ulTag);
					cutNode = null;
					$("#pcode" + $("li#" + liId).attr("id").substring(4))
							.html("000001");
					// ulTag.appendTo(parentNode);
				} // if then else if not paste back to parent node
			}
			return false; // eliminate page jumping
		});
