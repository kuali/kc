/*
 * This is the main function to create all the html for a question
 * each question is associated with 'li' tag which is jquery's node
 */
function getQuestionNew(description, qtypeid, vers, childNode) {

	var qnaireid = "qnaireid" + i
	var question = $('<li class="closed"></li>').attr("id", qnaireid);
	var divId = "listcontent" + i;

	var div62 = $('<div/>');
	var linktmp = $('<a style="margin-left:2px;"></a>').attr("id",
			"listcontrol" + i).html(description);
	linktmp.click(function() {
		var idx = $(this).attr("id").substring(11);
		$(".hierarchydetail:not(#listcontent" + idx + ")").slideUp(300);
		if ($(this).parents('div:eq(0)').children('div:eq(0)').size() == 0) {
			// var idx = $(this).attr("id").substring(11);
			var vers = "1.00";
			// alert("set up table "+idx);
			var divtmp = getMaintTable(idx, vers, childNode);
			divtmp.appendTo($(this).parents('div:eq(0)'));
			$("#listcontent" + idx).slideToggle(300);
		}
		$("#listcontent" + idx).slideToggle(300);
	});
	linktmp.appendTo(div62);

	div62.appendTo(question);

	// set up qdesc & qtypeid
	sethiddenfields();
	$("#qdesc" + i).attr("value", description);
	$("#qtypeid" + i).attr("value", qtypeid);

	// if (i < 4) {
	// alert(i+"-"+$("#qdesc" + i).attr("value")+"-"+$("#qtypeid" +
	// i).attr("value"));
	// }
	return question;

} // end addQuestion

function sethiddenfields() {
	var hidtr = $('<tr/>');
	var hidtd = $('<td colspan="2"/>');
	// question id for this node
	qntag = $('<input type="hidden" id = "qid" name = "qid" />').attr("id",
			"qid" + i).attr("name", "qid" + i);
	qntag.appendTo(hidtd);
	qntag = $('<input type="hidden" id = "qseq" name = "qseq" />').attr("id",
			"qseq" + i).attr("name", "qseq" + i);
	qntag.appendTo(hidtd);
	// qntag = $('<input type="hidden" id = "newqtypeid" name = "newqtypeid"
	// />')
	// .attr("id", "newqtypeid" + i).attr("name", "newqtypeid" + i);
	// qntag.appendTo(tdtmp);
	qntag = $('<input type="hidden" id = "qnum" name = "qnum" />').attr("id",
			"qnum" + i).attr("name", "qnum" + i);
	qntag.appendTo(hidtd);
	qntag = $('<input type="hidden" id = "qdesc" name = "qdesc" />').attr("id",
			"qdesc" + i).attr("name", "qdesc" + i);
	qntag.appendTo(hidtd);
	qntag = $('<input type="hidden" id = "qtypeid" name = "qtypeid" />').attr(
			"id", "qtypeid" + i).attr("name", "qtypeid" + i);
	qntag.appendTo(hidtd);
	qntag = $('<input type="hidden" id = "cond" name = "cond" />').attr("id",
			"cond" + i).attr("name", "cond" + i);
	qntag.appendTo(hidtd);
	qntag = $('<input type="hidden" id = "condvalue" name = "condvalue" />')
			.attr("id", "condvalue" + i).attr("name", "condvalue" + i);
	qntag.appendTo(hidtd);
	hidtd.appendTo(hidtr);
	hidtr.hide(); // FF rendering issue. If not hided, then 'line' will be
	// drawn at the bottom of the table for each Q hidden row
	hidtr.appendTo($("#question-table"));

}

function getMaintTable(idx, vers, childNode) {
	var description = $("#qdesc" + idx).attr("value");
	var qtypeid = $("#qtypeid" + idx).attr("value");
	var qnaireid = "qnaireid" + idx;
	var divId = "listcontent" + idx;
	// alert(idx+"-"+description+"-"+qtypeid);
	var div64 = $(' <div class="hierarchydetail" style="margin-top:2px;">')
			.attr("id", divId);
	var tbl70 = $('<table width="100%" cellpadding="0" cellspacing="0" class="subelement" />');
	getMaintTableHeader(description, vers).appendTo(tbl70);

	var tbodytmp = $('<tbody/>');
	var tr1 = $('<tr></tr>');
	var td1 = $('<td class="subelementcontent"></td>');

	getQuestionActionSubTable(qnaireid).appendTo(td1);
	getRequirementDisplayTable(idx).appendTo(td1);

	var div192 = $('<div></div>').attr("id", "HSReqdiv" + idx);
	if (childNode == 'true') {
		var tbl196 = $('<table cellpadding="0" cellspacing="0" class="elementtable" style="width:100%; border-top:none;"></table>');
		var response = $("#cond" + idx).attr("value");
		var value = $("#condvalue" + idx).attr("value");
		if (value != '') {
			// alert ("resp value "+response+"-"+value);
			var newResponse = getRequirementDeleteRow(responseArray[response],
					value, idx);
			newResponse.appendTo(tbl196);
		} else {
			getAddRequirementRow(idx).appendTo(tbl196);

		}
		tbl196.appendTo(div192);

		var tbl266 = $(
				'<table cellpadding="0" cellspacing="0" class="elementtable" style="width:100%; border-top:none;"></table>')
				.attr("id", "tbl266-" + idx).html('<tbody/>');
		tbl266.appendTo(div192);
	} else {
		div192
				.html("There can be no Requirements for Display on root-level questions. ");
	}
	div192.appendTo(td1);

	var tbl325 = $('<table width="100%" cellpadding="0" cellspacing="0" style="border-top:#E4E3E4 solid 1px;">');
	var trtmp = $('<tr></tr>');
	var hsdiv = "#HSdiv" + idx;
	var thtmp = $(
			'<th style="background:#939393;height:18px;color:#FFFFFF;text-align:left;padding-left:4px;" colspan="3">')
			.html("Response");
//'<th class="subelementheader" style="text-align:left;" colspan="3">')
	var image = $(
			'<img src="kr/static/images/tinybutton-hide.gif" alt="show/hide this panel" title="show/hide this panel"  style="width:45px; height:15px; border:none; cursor:pointer; padding:2px; vertical-align:middle;" />')
			.attr("id", "HScontrol" + idx).toggle(function() {
				$(hsdiv).slideDown(400);
				$(this).attr("src", "kr/static/images/tinybutton-hide.gif");
			}, function() {
				$(hsdiv).slideUp(200);
				$(this).attr("src", "kr/static/images/tinybutton-show.gif");
			});
	$(image).click();
	image.prependTo(thtmp);
	thtmp.appendTo(trtmp);
	trtmp.appendTo(tbl325);
	tbl325.appendTo(td1);

	var div360 = $('<div ></div>').attr("id", "HSdiv" + idx);
	var tbl362 = $('<table class="content_table">');
	trtmp = $('<tr></tr>');
	// TODO : can't get class from kuali-stylesheet.css. ??
	var contentwhite = "background:#FFFFFF;border-top: solid #939393 0px;border-bottom: solid #939393 1px;" +
	"border-left: solid #939393 1px;border-right: solid #939393 1px;padding-top:3px;padding-right:3px;"+
    "padding-left:3px;padding-bottom:3px;";
	var contentgray = "background:#E4E3E4;border-top: solid #939393 0px;border-bottom: solid #939393 1px;" +
	"border-left: solid #939393 1px;border-right: solid #939393 1px;padding-top:3px;padding-right:3px;"+
    "padding-left:3px;padding-bottom:3px;font-weight:bold;";
	tdtmp = $(
    //'<td class="content_grey" style="width:110px; text-align:center;">').html("Type");
	'<td>').attr("style",contentgray+"width:110px; text-align:center;").html("Type");
	tdtmp.appendTo(trtmp);
	//tdtmp = $('<td class="content_grey" style="text-align:center;">').html("Values");
	tdtmp = $('<td>').attr("style",contentgray+"width:110px; text-align:center;").html("Values");
	tdtmp.appendTo(trtmp);
	trtmp.appendTo(tbl362);
	trtmp = $('<tr></tr>');
	//tdtmp = $('<td class="content_white" style="text-align:center; vertical-align:top;">');
	tdtmp = $('<td>').attr("style",contentwhite+"vertical-align:top; text-align:center;");
	var selecttmp = questionType[qtypeid];
	tdtmp.html(selecttmp);
	tdtmp.appendTo(trtmp);

	//tdtmp = $('<td class="content_white" style="text-align:left;">');
	tdtmp = $('<td>').attr("style",contentwhite+"text-align:left;");
	getQnTypeDesc(qtypeid).appendTo(tdtmp);
	tdtmp.appendTo(trtmp);
	trtmp.appendTo(tbl362);
	tbl362.appendTo(div360);
	div360.appendTo(td1);
	td1.appendTo(tr1);
	tr1.appendTo(tbodytmp);
	tbodytmp.appendTo(tbl70);
	tbl70.appendTo(div64);
	return div64;
	// div64.appendTo(div62);

}

/*
 * set up questionaire question panel's tab header
 */
function getMaintTableHeader(description, vers) {
	var thead = $('<thead/>');
	var trtmp = $('<tr/>');
	// TODO not sure why 'subelementheader class is not picked up from new_kuali.css
	// it is working fine in researchareahierarchy ???
	//var thtmp = $('<th class="subelementheader" align="left" >');
	var thtmp = $('<th style="background:#939393;height:18px;color:#FFFFFF;text-align:left;padding-left:4px;" align="left" >');
	var imgtmp = $(
			'<a href="#" class="hidedetail"><img src="kr/static/images/tinybutton-hide.gif" align="absmiddle" border="0" width="45" height="15"></a>')
			.toggle(
			// TODO : really need this toggle. Mock's toggle did not show up
			);
	thtmp.html(description + "( " + vers + ")");
	imgtmp.prependTo(thtmp);
	thtmp.appendTo(trtmp);
	trtmp.appendTo(thead);
	return thead;
}

/*
 * set up the action buttons cut/remove/copy/paste
 */
function getQuestionActionSubTable(qnaireid) {
	// table for Qn actions : move/remove/cut/past/lookup

	var curidx = qnaireid.substring(8);
	var tbl80 = $(
			'<table cellpadding="0" cellspacing="0" class="elementtable" width="100%">')
			.attr("id", "tbl80" + curidx);
	var trtmp = $('<tr></tr>');
	var tmp = $('<th style="text-align:right; width:120px;">Node:</th>');
	trtmp.html(tmp);
	var thtmp = $('<td/>');
	getMoveUpLink(curidx).appendTo(thtmp);
	getMoveDownLink(curidx).appendTo(thtmp);

	var image = $(
			'<a href="#"><img src="static/images/tinybutton-removenode.gif" width="79" height="15" border="0" alt="Remove Node" title="Remove this node and its child groups/sponsors"></a>&nbsp')
			.attr("id", "remove" + curidx)
			.click(function() {
				var liId = "li#" + qnaireid;
				// TODO : IE problem , clone does not clone child node
					// removedNode = $(liId).clone(true);
					//removedNode = $(liId);
					//alert("Remove node " + removedNode.attr("id"));
					if ($(liId).prev().size() == 0 && $(liId).next().size() > 0) {
						$("#moveup" + $(liId).next().attr("id").substring(8))
								.hide();
					}
					if ($(liId).next().size() == 0 && $(liId).prev().size() > 0) {
						$("#movedn" + $(liId).prev().attr("id").substring(8))
								.hide();
					}
					var idx = $(liId).attr("id").substring(8);
					var parentNum;
					if ($(liId).parents('ul:eq(0)').parents('li:eq(0)').size() == 0) {
						parentNum = 0;
						var idx1 = $(liId).attr("class").indexOf(" ");
						adjustGroup($(liId).attr("class").substring(5, idx1)); // class
						// is
						// "group0
						// expandable"
					} else {
						parentNum = $(
								"#qnum"
										+ $(liId).parents('ul:eq(0)').parents(
												'li:eq(0)').attr("id")
												.substring(8)).attr("value");
					}

					deleteChild(parentNum, $(liId).attr("id"));
					//alert(sqlScripts);
					// TODO : update seqnum of the sibling nodes following it
					$(liId).remove();
					cutNode = null;
					copyNode = null;
				});
	image.appendTo(thtmp);
	image = $(
			'<a href="#"><img src="static/images/tinybutton-cutnode.gif" width="79" height="15" border="0" alt="Cut Node" title="Cut this node and its child roups/sponsors.  (Node will not be removed until you paste it.)"></a>&nbsp')
			.attr("id", "cut" + curidx).click(function() {
				//alert("Cut node");
				var liId = "li#" + qnaireid;
				cutNode = $(liId);
				removedNode = null; // remove & cutNode should not co-exist
					copyNode = null;
				});
	image.appendTo(thtmp);
	image = $(
			'<a href="#"><img src="static/images/jquery/tinybutton-copynode.gif" width="79" height="15" border="0" alt="Copy Node" title="Copy this node and its child.)"></a>&nbsp')
			.attr("id", "copy" + curidx).click(function() {
				//alert("Copy node");
				var liId = "li#" + qnaireid;
				copyNode = $(liId);
				removedNode = null; // remove & cutNode should not co-exist
					cutNode = null;
				});
	image.appendTo(thtmp);
	image = $(
			'<a href="#"><img src="static/images/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Paste Node" title="Paste your previously cut node structure under this node"></a>')
			.attr("id", "paste" + curidx).click(function() {

				// TODO : what if paste 'not top level node' to top level, then
					// how about the exist 'condition'

					if (removedNode || cutNode) {
						var idx;
						var parentNode = $("#" + qnaireid);
						var ulTag = parentNode.children('ul');
						if (ulTag.size() > 0) {
							//alert(ulTag.attr("id"));
						} else {
							//alert("not found")
							i++;
							ulTag = $('<ul class="filetree"></ul>').attr("id",
									"ul" + curidx);
						}
						if (removedNode) {
							pasteChild(qnaireid, removedNode);
							removedNode = null;
						} else if (cutNode) {
						  if (qnaireid.substring(8) == $(cutNode).attr("id").substring(8)) {
							  alert ("Can Not cut/paste on the same node");
						  } else {	  
							if ($(cutNode).parents('ul:eq(0)').parents(
									'li:eq(0)').size() == 0) {
								parentNum = 0;
								var idx1 = $(cutNode).attr("class").indexOf(" ");
								//alert ("idx "+qnaireid.substring(8)+"-"+$(cutNode).attr("id").substring(8)+"-"+idx1);
								adjustGroup($(cutNode).attr("class").substring(
										5, idx1)); // class is "group0 expandable"
							} else {
								parentNum = $(
										"#qnum"
												+ $(cutNode)
														.parents('ul:eq(0)')
														.parents('li:eq(0)')
														.attr("id")
														.substring(8)).attr(
										"value");
							}

							deleteChild(parentNum, $(cutNode).attr("id"));
							pasteChild(qnaireid, cutNode);

							var liId = cutNode.attr("id");
							var parentRACode;
							$("li#" + liId).remove();
							cutNode = null;
						  }	
						}

					}// if removednode

					if (copyNode) {
						// paste copy node
						isCopy = "true";
						getMaxCopyNodeIdx(copyNode);
						pasteChild(qnaireid, copyNode);
						maxCopyNodeIdx = 0;
						isCopy = "false";

					}

				});
	image.appendTo(thtmp);

	thtmp.appendTo(trtmp);
	$('<th style="text-align:right;">Add Question:</th>').appendTo(trtmp);

	getAddQuestionRow(curidx).appendTo(trtmp); // row2
	trtmp.appendTo(tbl80); // row1
	return tbl80;

}

function pasteCopyNode(nodeid) {
	var parentNode = $("#" + nodeid);
	var ulTag = parentNode.children('ul');

	startnode = $("#" + copyNode);
	//alert("copy node " + $(startnode).attr("id").substring(8));
	qdesc = $('#qdesc' + $(startnode).attr("id").substring(8)).attr("value");
	qtypeid = $('#qtypeid' + $(startnode).attr("id").substring(8))
			.attr("value");
	i++;
	var listitem = getQuestionNew(qdesc, qtypeid, "V1.01", "true");
	var ultag = $('<ul></ul>');
	ultag.appendTo(listitem);
	var idx = listitem.attr("id").substring(8);
	$("#movedn" + idx).hide();
	if (listitem.prev().size() > 0) {
		$("#movedn" + listitem.prev().attr("id").substring(8)).show();
	}

	//alert("node " + nodeid + "-" + parentNode.children('ul').size());
	listitem.appendTo(ulTag);
	// also need this to show 'folder' icon
	$('#example').treeview( {
		add : listitem
	});

	$("#qnum" + idx).attr("value", $("#questionNumber").attr("value"));
	var qid = $("#qid" + $(startnode).attr("id").substring(8)).attr("value");
	$("#qid" + idx).attr("value", qid);
	var seqnum = Number($(listitem).siblings().size()) + 1;
	$("#qseq" + idx).attr("value", seqnum);

	var liId = "li#" + nodeid;
	// var seqnum = Number($(liId).children('li').size())+1;
	var qid = $("#qid" + idx).attr("value");
	var qnum = $("#questionNumber").attr("value");
	var parentNum = $("#qnum" + $(liId).attr("id").substring(8)).attr("value");
	var insertValues = "insert into Q" + qid + "," + qnum + "," + parentNum
			+ ",'N','',''," + seqnum + ",user,sysdate)"
	addSqlScripts(insertValues);
	//alert("paste copy node " + sqlScripts);
	$("#questionNumber").attr("value",
			Number($("#questionNumber").attr("value")) + 1)

	//alert("copy li c size " + $("#" + copyNode).attr("id") + "-"
	//		+ $("#" + copyNode).children('ul.eq(0)').children('li').size());
	if ($("#" + copyNode).children('ul.eq(0)').children('li').size() > 0) {

		$("#" + copyNode).children('ul.eq(0)').children('li').each(
				function() {
					//alert("child copy node"
					//		+ $("deletereq" + $(this).attr("id").substring(8))
					//				.size());
					pasteChild($(listitem).attr("id"), $(this).attr("id"));
				});
	}

}

/*
 * traverse thru the node to copy this node tree need to clone copy node,
 * otherwise if paste to its own children, then this will become infinite loop,
 */
function pasteChild(parentid, startnode) {
	var parentNode = $("#" + parentid);
	var ulTag = parentNode.children('ul');

	// startnode = $("#"+childid);
	// alert("copy node "+$(startnode).attr("id").substring(8));
	qdesc = $('#qdesc' + $(startnode).attr("id").substring(8)).attr("value");
	qtypeid = $('#qtypeid' + $(startnode).attr("id").substring(8))
			.attr("value");
	i++;
	var listitem = getQuestionNew(qdesc, qtypeid, "V1.01", "true");
	var ultag = $('<ul></ul>');
	ultag.appendTo(listitem);
	var idx = listitem.attr("id").substring(8);
	listitem.appendTo(ulTag);
	if ($(listitem).parents('ul:eq(0)').children('li').size() == 1) {
		$("#moveup" + idx).hide();
		$("#movedn" + idx).hide();
	} else {
		// alert("prev "+listitem.prev().attr("id"));
		$("#movedn" + idx).hide();
		if (listitem.prev().size() > 0) {
			$("#movedn" + listitem.prev().attr("id").substring(8)).show();
		}
	}

	// also need this to show 'folder' icon
	$('#example').treeview( {
		add : listitem
	});

	$("#qnum" + idx).attr("value", $("#questionNumber").attr("value"));
	var qid = $("#qid" + $(startnode).attr("id").substring(8)).attr("value");
	$("#qid" + idx).attr("value", qid);
	var seqnum = Number($(listitem).siblings().size()) + 1;
	$("#qseq" + idx).attr("value", seqnum);

	var liId = "li#" + parentid;
	// var seqnum = Number($(liId).children('li').size())+1;
	var qid = $("#qid" + idx).attr("value");
	var qnum = $("#questionNumber").attr("value");
	var parentNum = $("#qnum" + $(liId).attr("id").substring(8)).attr("value");
	var insertValues = "insert into Q" + qid + "," + qnum + "," + parentNum
			+ ",'N','',''," + seqnum + ",user,sysdate)"
	addSqlScripts(insertValues);
	$("#questionNumber").attr("value",
			Number($("#questionNumber").attr("value")) + 1)

	//alert("child copy node"
	//		+ $("#cond" + $(startnode).attr("id").substring(8)).attr("value"));
	cidx = $(startnode).attr("id").substring(8);

	cond = $("#cond" + cidx).attr("value");
	value = $("#condvalue" + cidx).attr("value");
	if (cond != '') {
		var newResponse = getRequirementDeleteRow(responseArray[cond], value,
				idx);
		newResponse.appendTo($("#addrequirement" + idx).parents('div:eq(0)')
				.children('table:eq(0)').children('tbody'));
		$("#cond" + idx).attr("value", cond);
		$("#condvalue" + idx).attr("value", value);
		$("#addrequirement" + idx).parents('tr:eq(0)').remove();
		addSqlScripts("update QCond;'Y';'" + cond + "';'" + value + "';"
				+ $("#qid" + idx).attr("value") + ";"
				+ $("#qnum" + idx).attr("value"));
		// alert(sqlScripts);
	}

	if ($(startnode).children('ul.eq(0)').children('li').size() > 0) {

		$(startnode).children('ul.eq(0)').children('li').each(
				function() {
					if (isCopy == 'false'
							|| (isCopy == 'true' && $(this).attr("id")
									.substring(8) <= maxCopyNodeIdx)) {
						pasteChild($(listitem).attr("id"), $(this));
					}
				});
	}

}

/*
 * traverse thru the node to delete this node tree to create scripts for delete
 * this is called by remove node action
 */
function deleteChild(parentNum, childid) {

	var idx = $("#" + childid).attr("id").substring(8);
	var qnum = $("#qnum" + idx).attr("value");
	var childrenli = $("#" + childid).children('ul.eq(0)').children('li');
	addSqlScripts("delete Q;" + $("#qid" + idx).attr("value") + ";" + qnum
			+ ";" + parentNum + ";" + $("#qseq" + idx).attr("value"));

	if (childrenli.size() > 0) {

		childrenli.each(function() {
			deleteChild(qnum, $(this).attr("id"));
		});
	}

}

/*
 * traverse thru the node to collect copy nodes IE issue with clone, so set this
 * max up, in case the copied node is pasted to its children if don't set this
 * up, it will cause infinite loop
 */
function getMaxCopyNodeIdx(startnode) {

	var idx = $(startnode).attr("id").substring(8);
	var childrenli = $(startnode).children('ul.eq(0)').children('li');
	if (idx > maxCopyNodeIdx) {
		maxCopyNodeIdx = idx;
	}

	if ($(childrenli).size() > 0) {

		$(childrenli).each(function() {
			getMaxCopyNodeIdx($(this));
		});
	}

}

/*
 * set up movedown link
 */
function getMoveDownLink(curidx) {
	var image = $('<img style="border:none;" alt="move down" title="Move Down" type="image" >');
	var atag = $('<a href="#"></a>')
			.attr("id", "movedn" + curidx)
			.click(
					function() {
						var curNode = $(this).parents('li:eq(0)');
						var nextNode = $(this).parents('li:eq(0)').next();
						/*
						 * question html is looks like <li> <div> for class
						 * <div> for listcontrol, it has children div of
						 * listcontent <ul> for children questions </li>
						 */
						if ($(nextNode).children('div:eq(1)').children(
								'div:eq(0)').size() == 0) {
							var nextidx = $(nextNode).attr("id").substring(8);
							var vers = "1.00";
							// alert("set up table
							// "+nextidx+"-"+$(nextNode).children('div:eq(1)').children('a:eq(0)').attr("id"));
							var childNode = 'true';
							if ($(nextNode).parents('ul:eq(0)').attr("id") == 'example') {
								childNode = 'false';
							}
							var divtmp = getMaintTable(nextidx, vers, childNode);
							divtmp.appendTo($(nextNode).children('div:eq(1)'));
						}

						curNode.insertAfter(nextNode);
						var idx = $(curNode).attr("id").substring(8);
						var seq = Number($("#qseq" + idx).attr("value")) + 1;
						$("#qseq" + idx).attr("value", seq);
						addSqlScripts("update QMove;" + seq + ";"
								+ $("#qid" + idx).attr("value") + ";"
								+ $("#qnum" + idx).attr("value"));

						$("#moveup" + curNode.attr("id").substring(8)).show();
						$("#movedn" + nextNode.attr("id").substring(8)).show();
						if (nextNode.prev().size() == 0) {
							// alert("move up next node");
							$("#moveup" + nextNode.attr("id").substring(8))
									.hide();
						}
						if (curNode.next().size() == 0) {
							// alert ("move dn no next node ");
							$("#movedn" + curNode.attr("id").substring(8))
									.hide();
						}
						var idx = $(nextNode).attr("id").substring(8);
						seq = Number($("#qseq" + idx).attr("value")) - 1;
						$("#qseq" + idx).attr("value", seq);
						addSqlScripts("update QMove;" + seq + ";"
								+ $("#qid" + idx).attr("value") + ";"
								+ $("#qnum" + idx).attr("value"));
						// TODO : trying to group
						swapGroupId(curNode, nextNode);

					});
	image.attr("src", "static/images/jquery/arrow-down.gif");
	atag.html(image);
	if ($("#qnaireid" + curidx).next().size() == 0) {
		// alert("movedn "+curidx+"-"+$("#qnaireid" + curidx).attr("id"));
		$(atag).hide();
	}
	return atag;
}

/*
 * set up move up link
 */
function getMoveUpLink(curidx) {
	var image = $('<img style="border:none;" alt="move up" title="Move up" type="image" >');
	var atag = $('<a href="#"></a>')
			.attr("id", "moveup" + curidx)
			.click(function() {
				var curNode = $(this).parents('li:eq(0)');
				// TODO : trying to resolve IE issue
					// if( !!document.all ) {
					// curNode.html($(this).parents('li:eq(0)').html());
					// alert(curNode.html());
					// }

					var nextNode = $(this).parents('li:eq(0)').prev();
					if ($(nextNode).children('div:eq(1)').children('div:eq(0)')
							.size() == 0) {
						var nextidx = $(nextNode).attr("id").substring(8);
						var vers = "1.00";
						// alert("set up table
						// "+nextidx+"-"+$(nextNode).children('div:eq(1)').children('a:eq(0)').attr("id"));
						var childNode = 'true';
						if ($(nextNode).parents('ul:eq(0)').attr("id") == 'example') {
							childNode = 'false';
						}
						var divtmp = getMaintTable(nextidx, vers, childNode);
						divtmp.appendTo($(nextNode).children('div:eq(1)'));
					}

					// $(this).parents('li:eq(0)').remove();
					curNode.insertBefore(nextNode);
					var idx = $(curNode).attr("id").substring(8);
					var seq = Number($("#qseq" + idx).attr("value")) - 1;
					$("#qseq" + idx).attr("value", seq);
					addSqlScripts("update QMove;" + seq + ";"
							+ $("#qid" + idx).attr("value") + ";"
							+ $("#qnum" + idx).attr("value"));

					$("#movedn" + curNode.attr("id").substring(8)).show();
					$("#moveup" + nextNode.attr("id").substring(8)).show();
					if (curNode.prev().size() == 0) {
						$("#moveup" + curNode.attr("id").substring(8)).hide();
					}
					if (nextNode.next().size() == 0) {
						$("#movedn" + nextNode.attr("id").substring(8)).hide();
					}
					idx = $(nextNode).attr("id").substring(8);
					seq = Number($("#qseq" + idx).attr("value")) + 1;
					$("#qseq" + idx).attr("value", seq);
					addSqlScripts("update QMove;" + seq + ";"
							+ $("#qid" + idx).attr("value") + ";"
							+ $("#qnum" + idx).attr("value"));
					// TODO : trying to group
					swapGroupId(curNode, nextNode);
				});
	image.attr("src", "static/images/jquery/arrow-up.gif");
	atag.html(image);
	if ($("#qnaireid" + curidx).prev().size() == 0) {
		// alert("moveup "+curidx+"-"+$("#qnaireid" + curidx).prev().size())
		$(atag).hide();
	}
	return atag;
}

/*
 * set up the 'add' question row
 */
function getAddQuestionRow(curidx) {
	var tbl95 = $(
			'<table style="border:none; width:100%;" cellpadding="0" cellspacing="0"></table>')
			.attr("id", "tbl95" + curidx);
	var trtmp = $('<tr></tr>');
	var tdtmp = $('<td style="border:none; width:170px;"></td>');
	$('<input type="radio" name = "radio" checked="checked" value="sibling" />')
			.attr("class", "radioQn" + curidx).attr("name", "radioQn" + curidx)
			.appendTo(tdtmp);
	$('<span>as sibling&nbsp;&nbsp;&nbsp</span>').appendTo(tdtmp);
	$('<input type="radio" name = "radio" value="child" />').attr("class",
			"radioQn" + curidx).attr("name", "radioQn" + curidx)
			.appendTo(tdtmp);
	$('<span>as child</span>').appendTo(tdtmp);
	tdtmp.appendTo(trtmp);

	tdtmp = $('<td style="border:none;"></td>')
			.html(
					$(
							'<input type="text" id = "newqdesc" name = "newqdesc" size="50" value="" readonly = "true"/>')
							.attr("id", "newqdesc" + curidx));
	tdtmp.appendTo(trtmp);
	tdtmp = $('<td style="border:none; width:30px; text-align:center;"></td>');

	var atag = $('<a href="#"></a>');

	// question id from lookup - to be added as sibling or child of this node
	var qntag = $('<input type="hidden" id = "newqid" name = "newqid" />')
			.attr("id", "newqid" + curidx).attr("name", "newqid" + curidx);
	qntag.appendTo(tdtmp);
	qntag = $('<input type="hidden" id = "newqtypeid" name = "newqtypeid" />')
			.attr("id", "newqtypeid" + curidx).attr("name",
					"newqtypeid" + curidx);
	qntag.appendTo(tdtmp);

	var image = $(
			'<img src="static/images/searchicon.gif" id="searchQ" name="searchQ" border="0" class="tinybutton"  alt="Search Question" title="Search Question">')
			.attr("id", "search" + curidx).attr("name", "search" + curidx);
	image.click(function() {
		// TODO : IE problem. after the node is moved up or down, then the
			// "id" of the image got lost.
			// so, before figuring a solution, use this alternative to fin
			// parents id.
			var idx;
			if ($(this).attr("id") != '') {
				idx = $(this).attr("id").substring(6);
			} else {
				idx = $(this).parents('li:eq(0)').attr("id").substring(8);
			}
			// alert (idx)
			checkToAddQn(idx);
			return false;
		});
	atag.html(image);
	atag.appendTo(tdtmp);
	tdtmp.appendTo(trtmp);

	image = $(
			'<input name="addquestionnaire" src="kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" type="image" />')
			.attr("id", "addQn" + curidx)
			.click(function() {
				var idx = $(this).attr("id").substring(5);
				// alert($("#newqdesc"+idx).attr("value")+"-"+$("#newqtypeid"+idx).attr("value"))
					if ($("#newqdesc" + idx).attr("value") == ''
							|| $("#newqtypeid" + idx).attr("value") == '') {
						alert("Please select a question to add");
					} else {
						i++;

						var radioVal = $(
								".radioQn" + $(this).attr("id").substring(5)
										+ ":checked").val();
						var childNode = 'true';
						if (radioVal == 'sibling'
								&& $(this).parents('li:eq(0)').parents(
										'ul:eq(0)').attr("id") == 'example') {
							childNode = 'false';
						}
						var listitem = getQuestionNew($(
								"#newqdesc" + $(this).attr("id").substring(5))
								.attr("value"),
								$(
										"#newqtypeid"
												+ $(this).attr("id").substring(
														5)).attr("value"),
								"V1.01", childNode);
						var ultag = $('<ul></ul>');
						ultag.appendTo(listitem);
						var idx = listitem.attr("id").substring(8);
						if (radioVal == 'sibling') {
							// alert('sibling');
							var parentUl = $(this).parents('li:eq(0)').parents(
									'ul:eq(0)');
							listitem.appendTo(parentUl);
							$("#movedn" + idx).hide();
							$(
									"#movedn"
											+ listitem.prev().attr("id")
													.substring(8)).show();
							// TODO trying to group
							// alert("parent u"+parentUl.attr("id"));
							if (parentUl.attr("id") == 'example') {
								addToGroup(listitem);
								if (curgroup == groupid) {
									$("#nextGroup").hide();
								} else if (curgroup > 0) {
									$("#prevGroup").show();
								} else if (groupid > 0) {
									$("#nextGroup").show();
								}
							}
						} else {
							var parentUl = $(this).parents('li:eq(0)')
									.children('ul:eq(0)');
							listitem.appendTo(parentUl);

							// alert('children'+$(this).parents('li:eq(0)').children('ul:eq(0)').children('li').size());
							// TODO : if add 2nd item, then add 'movedn' to
							// 1st
							// item. maybe use hide/show instead of 'remove'
							// "==1" is the one just added
							if ($(this).parents('li:eq(0)')
									.children('ul:eq(0)').children('li').size() == 1) {
								$("#moveup" + idx).hide();
								$("#movedn" + idx).hide();
							} else {
								// alert("prev
								// "+listitem.prev().attr("id"));
								$("#movedn" + idx).hide();
								$(
										"#movedn"
												+ listitem.prev().attr("id")
														.substring(8)).show();
							}
						}

						// also need this to show 'folder' icon
						$('#example').treeview( {
							add : listitem
						});

						if (childNode == 'true') {
							// alert("parent li
							// "+$(this).parents('li:eq(0)').attr("id"));
							// $(this).parents('li:eq(0)').click();
							// $(this).parents('li:eq(0)').toggle('fast');
						}
						// TODO : set up for insert
						/*
						 * questionnairenumber from #questionnairenumber
						 * questionId from #qid sequenceNumber from
						 * $(this).parents('li:eq(0)').siblings().size() ?
						 */
						// $(listitem).parents('ul:eq(0)').parents('li:eq(0)').size()
						// == 0 : check whetehr it is at the top level
						var parentNum;
						if ($(listitem).parents('ul:eq(0)').parents('li:eq(0)')
								.size() == 0) {
							parentNum = 0;
						} else {
							// alert("parents li
							// "+$(listitem).parents('ul:eq(0)').parents('li:eq(0)').attr("id"));
							parentNum = $(
									"#qnum"
											+ $(listitem).parents('ul:eq(0)')
													.parents('li:eq(0)').attr(
															"id").substring(8))
									.attr("value");
						}
						$("#qnum" + $(listitem).attr("id").substring(8)).attr(
								"value", $("#questionNumber").attr("value"));
						// alert("parents li
						// "+$(this).attr("id").substring(5)+"
						// "+$("#qnum"+$(this).attr("id").substring(5)).attr("value"));
						var qid = $("#newqid" + $(this).attr("id").substring(5))
								.attr("value");
						$("#qid" + $(listitem).attr("id").substring(8)).attr(
								"value", qid);

						var seqnum = Number($(listitem).siblings().size()) + 1;
						$("#qseq" + $(listitem).attr("id").substring(8)).attr(
								"value", seqnum);
						var qnum = $("#questionNumber").attr("value");
						var insertValues = "insert into Q" + qid + "," + qnum
								+ "," + parentNum + ",'N','',''," + seqnum
								+ ",user,sysdate)"
						addSqlScripts(insertValues);
						$("#questionNumber").attr("value",
								Number($("#questionNumber").attr("value")) + 1)
					}
					return false;
				});

	tdtmp = $('<td style="border:none; width:30px; text-align:center;"></td>');
	image.appendTo(tdtmp);

	tdtmp.appendTo(trtmp);
	trtmp.appendTo(tbl95);
//	trtmp1 = $('<tr></tr>');
//	$('<th style="text-align:right;">Add Question(s):</th>').appendTo(trtag);
	tdtmp = $('<td/>');
	tbl95.appendTo(tdtmp);
	return tdtmp;

}

/*
 * set up child to parent requirement table
 */
function getRequirementDisplayTable(curidx) {
	var tbl154 = $('<table width="100%" cellpadding="0" cellspacing="0" style="border-top:#E4E3E4 solid 1px;">');
	var trtmp = $('<tr></tr>');
	//var thtmp = $('<th class="subelementheader" style="text-align:left;" colspan="3">');
	var thtmp = $('<th style="background:#939393;height:18px;color:#FFFFFF;text-align:left;padding-left:4px;" colspan="3">');
	
	thtmp.html("Requirements for Display");
	var hsdiv = "#HSReqdiv" + curidx;
	var image = $(
			'<img src="kr/images/tinybutton-hide.gif" alt="show/hide this panel" title="show/hide this panel"  style="width:45px; height:15px; border:none; cursor:pointer; padding:2px; vertical-align:middle;" />')
			.attr("id", "HSReqcontrol" + curidx).toggle(function() {
				$(hsdiv).slideDown(400);
				$(this).attr("src", "kr/static/images/tinybutton-hide.gif");
			}, function() {
				$(hsdiv).slideUp(200);
				$(this).attr("src", "kr/static/images/tinybutton-show.gif");
			});
	$(image).click(); // if don't do this, then it needs to be clicked twice
	// for first time.
	image.prependTo(thtmp);
	thtmp.appendTo(trtmp);
	trtmp.appendTo(tbl154);
	return tbl154;

}

/*
 * set up add requirement row
 */
function getAddRequirementRow(curidx) {

	var trtmp = $('<tr></tr>');
	var thtmp = $('<th style="text-align:center; width:150px;"></th>').html(
			"Add");
	thtmp.appendTo(trtmp);
	/*
	 * var tdtmp = $('<td class="content_info" style="text-align:center;"></td>');
	 * var selecttmp = $('<select name="CustomData"></select>'); $('<option
	 * value="0" selected="selected">select</option>').appendTo(selecttmp); $('<option
	 * value="1">and</option>').appendTo(selecttmp); $('<option value="2">or</option>').appendTo(selecttmp);
	 * selecttmp.appendTo(tdtmp); tdtmp.appendTo(trtmp);
	 */
	tdtmp = $('<td class="content_info" style="text-align:center;"></td>')
			.html("Parent Response ");
	// alert("response options "+responseOptions.html()+"-"+i);
	var respclone = responseOptions.clone(true);
	respclone.attr("id", "parentResponse" + curidx).appendTo(tdtmp);
	tdtmp.appendTo(trtmp);
	tdtmp = $('<td class="content_info" style="text-align:center;"></td>')
			.html("Value:");
	$('<input type="text" size="25" />').appendTo(tdtmp);
	tdtmp.appendTo(trtmp);
	tdtmp = $('<td class="content_info" class="content_white" style="width:65px; text-align:center;"></td>');
	var image = $(
			'<input name="addrequirement" src="kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" type="image" />')
			.attr("id", "addrequirement" + curidx)
			.click(
					function() {
						var idx = $(this).attr("id").substring(14);
						var response = $(this).parents('tr:eq(0)').children(
								'td:eq(0)').children('select:eq(0)').attr(
								"value");
						var value = $(this).parents('tr:eq(0)').children(
								'td:eq(1)').children('input:eq(0)').attr(
								"value");
						if (okToAddRequirement(response, value)) {
							/*
							 * var opDesc; if (sequence == 1) { opDesc =
							 * "Current Requirements:"; } else { opDesc =
							 * opArray[operator]; }
							 */
							var newResponse = getRequirementDeleteRow(
									responseArray[response], value, idx);
							newResponse.appendTo($(this).parents('div:eq(0)')
									.children('table:eq(0)').children('tbody'));
							var idx = $(this).parents('li:eq(0)').attr("id")
									.substring(8);
							$("#cond" + idx).attr("value", response);
							$("#condvalue" + idx).attr("value", value);
							addSqlScripts("update QCond;'Y';'" + response
									+ "';'" + value + "';"
									+ $("#qid" + idx).attr("value") + ";"
									+ $("#qnum" + idx).attr("value"));
							// alert(sqlScripts);
							$(this).parents('tr:eq(0)').remove();
						}
						// }
						return false;
					});

	image.appendTo(tdtmp);
	tdtmp.appendTo(trtmp);
	return trtmp;
}

/*
 * check if required fields entered for requirement to add
 */
function okToAddRequirement(response, value) {
	var valid = false;
	if (value == '') {
		alert("Please enter a value");
	} else if (response == 0) {
		alert("Please select a response");
	} else if (response >= 3 && response <= 7 && isNaN(value)) {
		alert("Value must be a number");
	} else if (response > 7 && !isDate(value)) {
		alert("Not a Valid Date");
	} else {
		valid = true;
	}
	return valid;
}

/*
 * set the newly added requirement row with 'delete' button
 */
function getRequirementDeleteRow(response, value, curidx) {
	var trtmp = $('<tr></tr>');
	var thtmp = $('<th style="text-align:left; border-top:none; width:150px;">')
			.html("Current Requirements:");
	thtmp.appendTo(trtmp);
	var tdtmp = $('<td style="text-align:left; border-top:none;">').html(
			response + " : " + value);
	tdtmp.appendTo(trtmp);
	tdtmp = $('<td class="content_white" style="text-align:center; border-top:none; width:65px;">');
	var image = $(
			'<input src="kr/static/images/tinybutton-delete1.gif"  style="border:none;" alt="delete" type="image" />')
			.attr("id", "deletereq" + curidx).click(
					function() {
						//alert("This would delete this requirement."
						//		+ $(this).parents('tr:eq(0)').next().size());
						var idx = $(this).parents('li:eq(0)').attr("id")
								.substring(8);
						addSqlScripts("update QCond;'N';'';'';"
								+ $("#qid" + idx).attr("value") + ";"
								+ $("#qnum" + idx).attr("value"));
						getAddRequirementRow(idx).appendTo(
								$(this).parents('tr:eq(0)').parents(
										'tbody:eq(0)'));
						$(this).parents('tr:eq(0)').remove();
						return false;
					});
	image.appendTo(tdtmp);
	tdtmp.appendTo(trtmp);
	return trtmp;

}

// test lookup pop

/*
 * This is to pop up window for question lookup. If lookup is from root level,
 * then popup window for multivalue look up If lookup from any node, then it's
 * single value lookup.
 */
function checkToAddQn(nodeIndex) {

	var url = window.location.href
	var pathname = window.location.pathname
	var idx1 = url.indexOf(pathname);
	var idx2 = url.indexOf("/", idx1 + 1);
	var extractUrl = url.substr(0, idx2);
	var lookupType;
	if (nodeIndex == 0) {
		lookupType = "multivalue";
	} else {
		lookupType = "single";
	}
	var winPop = window.open(extractUrl + "/questionLookup.do?nodeIndex="
			+ nodeIndex + "&lookupType=" + lookupType + "&anchor=topOfForm",
			"_blank", "width=1000, height=800, scrollbars=yes");

}

/*
 * This is called by pop window, it returns the selected question.
 */
function returnQuestion(newQuestionId, newQuestion, newQuestionTypeId,
		nodeIndex) {
	// alert("return QNID "+ newQuestionId+newQuestionTypeId);
	// TODO : these need to be defined in 'input' tag, otherwise, the value set
	// will not stuck.
	// questionid, description, and typeid returned from question lookup.
	$("#newqid" + nodeIndex).attr("value", newQuestionId);
	$("#newqdesc" + nodeIndex).attr("value", newQuestion);
	$("#newqtypeid" + nodeIndex).attr("value", newQuestionTypeId);
	// alert("qid "+ nodeIndex+$("#qid"+nodeIndex).attr("value"));
}

/*
 * This is called by multivalue lookup pop up window. return list of questions
 * selected. each question is separated by "#q#" each field is separated by
 * "#f#"
 */
function returnQuestionList(questionList) {
	// alert("multivalue "+questionList.substring(1200));
	questionList = questionList.replace(/"/g, '\"');
	// load questions
	var questions = questionList.split("#q#");
	// qid/desc/qtypeid
	var parentnum = 0;
	var parentidx = 0;
	var firstidx = -1;
	var initgroup = groupid;
	if ($(".group" + groupid).size() >= 20) {
		initgroup++;
	}
	for ( var k = 0; k < questions.length; k++) {
		var field = questions[k].split("#f#");
		i++;
		var parenturl = $('#example');
		var listitem = getQuestionNew(field[1], field[2], "V1.01", 'false');
		var ultag = $('<ul></ul>');
		ultag.appendTo(listitem);
		var idx = listitem.attr("id").substring(8);
		// listitem.appendTo('ul#example');
		// last one no 'move dn'
		if (firstidx == -1) {
			firstidx = idx;
		}

		listitem.appendTo($(parenturl));
		// also need this to show 'folder' icon
		$('#example').treeview( {
			add : listitem
		});

		// alert($(listitem).parents('ul:eq(0)').size());
		if ($(listitem).parents('ul:eq(0)').children('li').size() == 1) {
			$("#moveup" + idx).hide();
			$("#movedn" + idx).hide();
		} else {
			// alert("prev "+listitem.prev().attr("id"));
			$("#movedn" + idx).hide();
			if (listitem.prev().size() > 0) {
				$("#movedn" + listitem.prev().attr("id").substring(8)).show();
			}
		}

		// TODO : set up for insert
		/*
		 * questionnairenumber from #questionnairenumber questionId from #qid
		 * sequenceNumber from $(this).parents('li:eq(0)').siblings().size() ?
		 */

		// alert("questionnairenumber "+$("#questionNumber").attr("value")+" qid
		// "+$("#qid"+$(this).attr("id").substring(5)).attr("value"));
		$("#qid" + $(listitem).attr("id").substring(8)).attr("value", field[0]);
		$("#qdesc" + $(listitem).attr("id").substring(8)).attr("value",
				field[1]);
		$("#qtypeid" + $(listitem).attr("id").substring(8)).attr("value",
				field[2]);
		var seqnum = Number($(listitem).siblings().size()) + 1;
		$("#qseq" + $(listitem).attr("id").substring(8)).attr("value", seqnum);
		var qnum = $("#questionNumber").attr("value");
		$("#qnum" + $(listitem).attr("id").substring(8)).attr("value", qnum);
		var insertValues = "insert into Q" + field[0] + "," + qnum + ","
				+ parentnum + ",'N','',''," + seqnum + ",user,sysdate)"
		addSqlScripts(insertValues);
		$("#questionNumber").attr("value",
				Number($("#questionNumber").attr("value")) + 1)

		// TODO : try grouping
		addToGroup(listitem);

	} // end for to set up questions
	//alert(curgroup + "-" + initgroup + "-" + firstidx + "-" + groupid)
	$(".group" + curgroup).hide();
	curgroup = initgroup;
	$(".group" + curgroup).show();
	while (initgroup++ < groupid) {
		$(".group" + initgroup).hide();
	}

	$("#listcontrol" + firstidx).click();
	
	if (groupid > 0) {
		if (curgroup == groupid) {
		    $("#nextGroup").hide();
		} 
		if (curgroup < groupid) {
		    $("#nextGroup").show();
		} 
		if (curgroup == 0) {
		    $("#nextGroup").show();
		    $("#prevGroup").hide();
		}
		if (curgroup > 0) {
		    $("#prevGroup").show();
		}
	}	

	// $("#listcontrol" + firstidx).click();

	// alert(sqlScripts);
}// end returnquestionlist

function getQnTypeDesc(qtypeid) {
	// alert("gettypedesc "+qtypeid);
	var divtmp = null;
	switch (Number(qtypeid)) {
	// has to use Number(qtypeid)
	case 1:
		divtmp = $('<div id="responsetypeYesNo1b" class="responsetypediv1b">')
				.html(
						$('<p>The user will be presented with the following radio buttons: Yes, No.<br />Only one selection is possible.<br />A selection is required.</p>'));
		break;
	case 2:
		divtmp = $('<div id="responsetypeYesNoNA1b" class="responsetypediv1b">')
				.html(
						$('<p>The user will be presented with the following pulldown: Yes, No, Not Applicable.<br />Only one selection is possible.<br />A selection is required.</p>'));
		break;
	case 3:
		divtmp = $('<div id="responsetypeNumber1b" class="responsetypediv1b">')
				.html(
						$('<p>The user will be presented with 1 text box.<br />The entered value will be validated requiring a number only.<br />The maximum length of the number in characters is 5.<br />The number of possible answers is 1. </p>'));
		break;
	case 4:
		divtmp = $('<div id="responsetypeDate1b" class="responsetypediv1b">')
				.html(
						$('<p>The user will be presented with 2 text boxes.<br />The entered value will be validated for a date in MM/DD/YYYY format.<br />A response is required for each text box.</p>'));
		break;
	case 5:
		divtmp = $('<div id="responsetypeText1b" class="responsetypediv1b">')
				.html(
						$('<p>The user will be presented with 2 text areas.<br />The number of possible answers is 2.<br />Maximum length of each response in characters: 256.</p>'));
		break;
	case 6:
		divtmp = $(
				'<div id="responsetypeDropdown1b" class="responsetypediv1b">')
				.html(
						$('<p>The user will be presented with a dropdown of options.<br />Only one selection is possible.<br />A selection is required.</p> Possible answers are:<br />1. One Fish<br />2. Two Fish<br />3. Red Fish<br />4. Blue Fish'));
		break;
	case 7:
		divtmp = $(
				'<div id="responsetypeCheckbox1b" class="responsetypediv1b">')
				.html(
						$('<p>The user will be presented with 4 checkboxes.<br />At least one selection is required.<br />Up to 4 selections are allowed.</p>Possible answers are:<br />1. One Byte<br />2. Two Bites<br />3. Red Light<br />4. Green lights'));
		break;
	case 8:
		divtmp = $('<div id="responsetypeSearch1b" class="responsetypediv1b">')
				.html(
						$('<p>The user will be presented with the ability to search for: Person.<br />The field to return is: Name.<br />The number of possible returns is 2.</p>'));
		break;
	default:
		divtmp = $('<div id="responsetypeSearch1b" class="responsetypediv1b">')
				.html($('<p>Unknown</p>'));
	}
	return divtmp;
}

function getInsertClause(code, parentCode, description) {

	// need to rework on real update_user
	var values = "'" + code + "','" + parentCode + "', 'N', '" + description
			+ "', sysdate, user";
	return "insert into Q values(" + values + ")";
}

function addSqlScripts(sqlcommand) {
	sqlScripts = sqlScripts + "#;#" + sqlcommand;
	if (sqlScripts.length > 1900) {
		// right now the query string also contains newquestionnaire data, so
		// limit to 1700 for test now.
		sqls[sqlidx++] = sqlScripts;
		sqlScripts = "sqls";
		// alert(sqlidx);
	}
}

function addToGroup(listitem) {
	// TODO : try grouping
	if ($(".group" + groupid).size() >= 20) {
		groupid++;
	}
	$(listitem).attr("class", "group" + groupid);
	//$(listitem).addClass("group" + groupid);
	if (curgroup != groupid) {
		// alert("show");
		$(listitem).hide();
	}

}

function swapGroupId(curNode, nextNode) {
	// class mya like "group0 expandable ..", the last item has one more item
	var curclass = $(curNode).attr("class");
	var nextclass = $(nextNode).attr("class");
	$(curNode).attr("class", nextclass);
	$(nextNode).attr("class", curclass);
	if (curclass.substring(0, 7) != nextclass.substring(0, 7)) {
		$(curNode).hide();
		$(nextNode).show();
	}
	if (curclass != nextclass) {
		$(curNode).attr("class", nextclass);
		$(nextNode).attr("class", curclass);
	}
	// $("#listcontrol"+$(nextNode).attr("id").substring(8)).click(); // only
	// the node that is moved; close all
	// $("#listcontrol"+$(nextNode).attr("id").substring(8)).click(); // open
	// next node

}

function adjustGroup(idx) {
	while (idx < groupid) {
		idx1 = Number(idx) + 1;
		node = $(".group" + idx1 + ":eq(0)");
		nodeclass = $(node).attr("class"); // may have multiple classes
		// specified
		nodeclass = nodeclass.replace("group" + idx1, "group" + idx);
		// alert(idx + "-" + groupid + "-" + curgroup + "-"
		// + $(".group" + idx1).size() + nodeclass);
		$(node).attr("class", nodeclass);
		if (curgroup == idx) {
			// alert("show");
			$(node).show();
		}
		idx++;
		if (idx == groupid) {
			if ($(".group" + idx).size() == 0) {
				groupid--;
			}
		}
		// alert("loop back " + idx + "<" + groupid);
	}
	if (groupid > 0) {
		if (curgroup == groupid) {
		    $("#nextGroup").hide();
		} 
		if (curgroup < groupid) {
		    $("#nextGroup").show();
		} 
		if (curgroup == 0) {
		    $("#nextGroup").show();
		    $("#prevGroup").hide();
		}
		if (curgroup > 0) {
		    $("#prevGroup").show();
		}
	} else {
	    $("#nextGroup").hide();
	    $("#prevGroup").hide();
	}
}

// http://www.redips.net/javascript/date-validation/
// code found in web. no license info. can we use it ?
function isDate(txtDate) {
	var objDate; // date object initialized from the txtDate string
	var mSeconds; // milliseconds from txtDate
	// date length should be 10 characters - no more, no less
	if (txtDate.length != 10)
		return false;

	// extract day, month and year from the txtDate string
	// expected format is mm/dd/yyyy
	// subtraction will cast variables to integer implicitly
	if (isNaN(txtDate.substring(3, 5)) || isNaN(txtDate.substring(0, 2))
			|| isNaN(txtDate.substring(6, 10))) {
		return false;
	}
	if (txtDate.substring(2, 3) != '/' || txtDate.substring(5, 6) != '/') {
		return false;
	}	

	var day = txtDate.substring(3, 5) - 0;
	var month = txtDate.substring(0, 2) - 1; // because months in JS start
	// with 0
	var year = txtDate.substring(6, 10) - 0;

	// test year range
	if (year < 999 || year > 3000) {
		return false;
	}	

	// convert txtDate to the milliseconds
	mSeconds = (new Date(year, month, day)).getTime();

	// set the date object from milliseconds
	objDate = new Date();
	objDate.setTime(mSeconds);

	// if there exists difference then date isn't valid
	if (objDate.getFullYear() != year || objDate.getMonth() != month || objDate.getDate() != day) {
		return false;
	}	
	return true;
}

/* integrate with edit, new */
// -- should be shared
var moduleCodes = [ 'select', 'Award', 'Institute Proposal',
		'Development Proposal', 'Subcontracts', 'Negotiations', 'Person',
		'IRB', 'Annual Coi Disclosure' ];
var opArray = [ 'select', 'and', 'or' ];
var responseArray = [ 'select', 'Contains text value', 'Matches text',
		'Less than number', 'Less than or equals number', 'Equals number',
		'Greater than or equals number', 'Greater than number', 'Before date',
		'After date' ];
var questionType = [ 'select', 'Yes/No', 'Yes/No/NA', 'Number', 'Date', 'Text',
		'Lookup' ];

var responseOptions = $('<select name="CustomData"></select>');
$('<option value="0" selected="selected">select</option>').appendTo(
		responseOptions);
$('<option value="1">Contains text value</option>').appendTo(responseOptions);
$('<option value="2">Matches text</option>').appendTo(responseOptions);
$('<option value="3">Less than number</option>').appendTo(responseOptions);
$('<option value="4">Less than or equals number</option>').appendTo(
		responseOptions);
$('<option value="5">Equals number</option>').appendTo(responseOptions);
$('<option value="6">Greater than or equals number</option>').appendTo(
		responseOptions);
$('<option value="7">Greater than number</option>').appendTo(responseOptions);
$('<option value="8">Before date</option>').appendTo(responseOptions);
$('<option value="9">After date</option>').appendTo(responseOptions);

$("#addUsage")
		.click(function() {
			// TODO : 1 header and one 'add' row, so has 2 more
				ucount = $("#usage-table").children('tbody:eq(0)').children(
						'tr').size() - 1;
				trtmp = $('<tr/>').attr("id", "usage" + ucount);
				thtmp = $('<th class="infoline"/>').html(ucount);
				thtmp.appendTo(trtmp);
				tdtmp = $('<td align="left" valign="middle">')
						.html(
								moduleCodes[$(
										"#newQuestionnaireUsage\\.moduleItemCode")
										.attr("value")]);
				modulecode = $('<input type="hidden"/>').attr(
						"value",
						$("#newQuestionnaireUsage\\.moduleItemCode").attr(
								"value"));
				modulecode.prependTo(tdtmp);
				tdtmp.appendTo(trtmp);
				tdtmp = $('<td align="left" valign="middle">').html(
						$("#newQuestionnaireUsage\\.questionnaireLabel").attr(
								"value"));
				tdtmp.appendTo(trtmp);
				tdtmp = $('<td align="left" valign="middle">').html("1.00");
				tdtmp.appendTo(trtmp);
				inputtmp = $(
						'<input type="image" id="deleteUsage" name="deleteUsage" src="static/images/tinybutton-delete1.gif" class="tinybutton">')
						.attr("id", "deleteUsage" + ucount).click(function() {
							// alert("delete
								// "+$(this).parents('tr:eq(0)').attr("id"))

								addSqlScripts("delete U;"
										+ $(this).parents('tr:eq(0)').children(
												'td:eq(0)').children(
												'input:eq(0)').attr("value"));
								// TODO : delete usage also need to update 'item
								// number' in the first column
								curnode = $(this).parents('tr:eq(0)');
								while (curnode.next().size() > 0) {
									curnode = curnode.next();
									// alert(Number(curnode.children('th:eq(0)').html())-1);
									curnode.children('th:eq(0)').html(
											Number(curnode.children('th:eq(0)')
													.html()) - 1)
								}
								$(this).parents('tr:eq(0)').remove();
								return false;
							});
				tdtmp = $('<td align="left" valign="middle">');
				$('<div align="center">').html(inputtmp).appendTo(tdtmp);
				tdtmp.appendTo(trtmp);
				ucount++;
				trtmp.appendTo($("#usage-table"));
				addSqlScripts("insert U;"
						+ $("#newQuestionnaireUsage\\.moduleItemCode").attr(
								"value")
						+ ";"
						+ $("#newQuestionnaireUsage\\.questionnaireLabel")
								.attr("value"));
				// alert(sqlScripts);
				return false;
			});
// $("#deleteUsage").click(function(){
// alert("delete usage");
// return false;
// });

$("#rootSearch").click(function() {
	// alert($(this).parents('li:eq(0)').attr("id"));
		// TODO : IE problem. after the node is moved up or down, then the "id"
		// of the image got lost.
		// so, before figuring a solution, use this alternative to fin parents
		// id.
		checkToAddQn(0);
		return false;
	});

$("#nextGroup").click(function() {
	if (groupid != curgroup) {
		$(".group" + curgroup).hide();
		if (++curgroup == groupid) {
			$("#nextGroup").hide();
		}
		if (curgroup  == 1) {
			$("#prevGroup").show();
		} 
		$(".group" + curgroup).show();
		// alert($(".group"+curgroup+":eq(0)").attr("id"));
		$(
				"#listcontrol"
						+ $(".group" + curgroup + ":eq(0)").attr("id")
								.substring(8)).click();
		jumpToAnchor('topOfForm');
	}
	return false;

});

$("#prevGroup").click(function() {
	//alert ("prevgroup");
	if (groupid > 0) {
		$(".group" + curgroup).hide();
		if (--curgroup == 0) {
			$("#prevGroup").hide();
		} 
		if (curgroup == (groupid-1)) {
			$("#nextGroup").show();
		}	
		$(".group" + curgroup).show();
		// alert($(".group"+curgroup+":eq(0)").attr("id"));
		$(
				"#listcontrol"
						+ $(".group" + curgroup + ":eq(0)").attr("id")
								.substring(8)).click();
		jumpToAnchor('topOfForm');
	}
	return false;

});

$("#backToTop").click(function() {
	$(".group" + curgroup).hide();
	curgroup = 0;
	$(".group" + curgroup).show();
	// alert($(".group"+curgroup+":eq(0)").attr("id"));
		$(
				"#listcontrol"
						+ $(".group" + curgroup + ":eq(0)").attr("id")
								.substring(8)).click();
		jumpToAnchor('topOfForm');
		return false;

	});

// This is save function when 'save' button is clicked for edit or create new
// questionnaire.
// First save questionnaire bo because 'description'
$("#save").click(function() {
	var qname = $('#newQuestionnaire\\.name').attr("value");
	var qnaireid = $('#newQuestionnaire\\.questionnaireId').attr("value");
	var qdescription = $('#newQuestionnaire\\.description').attr("value");
	var qisfinal = $('#newQuestionnaire\\.isFinal').attr("checked");
	// alert
		// ("save"+qname+qdescription+$('#newQuestionnaire\\.isFinal').attr("checked"));
		if (qname == '') {
			alert("Questionnaire Name is required");
		} else if (qdescription == '') {
			alert("Questionnaire description is required");
		} else {
			// TODO : FF seems to have trouble with "#;#"

			if (sqlScripts.indexOf("#;#") > 1) {
				// if current sqlScripts is not in array yet
				// 10 should be fine to use as check
				sqls[sqlidx++] = sqlScripts;
			}
			// TODO : problem with '&' in string will in name or description
			qname = qname.replace(/&/g, ";amp");
			qdescription = qdescription.replace(/&/g, ";amp");

			var desc1 = "";
			var saveok = 'true';
			if (qdescription.length > 1800) {
				// TODO : 1800 should be ok
				desc1 = qdescription.substring(1800);
				qdescription = qdescription.substring(0, 1800);
			}

			// Save new questionnaire bo
			$("#headermsg").html(""); // clear error message
			$.ajax( {
				url : 'questionnaireAjax.do',
				type : 'GET',
				dataType : 'html',
				cache : false,
				data : 'action=savebo&newQuestionnaire.name=' + qname
						+ '&newQuestionnaire.questionnaireId=' + qnaireid
						+ '&newQuestionnaire.description=' + qdescription
						+ '&newQuestionnaire.isFinal=' + qisfinal,
				async : false,
				timeout : 1000,
				error : function() {
					// alert('Error loading XML document');
				jumpToAnchor('topOfForm');
				$('<span id="msg"/>').css("color", "red").html(
						"Error when save Questionnaire").appendTo(
						$("#headermsg"))
				$('<br/>').appendTo($("#headermsg"));
				saveok = 'false';
			},
			success : function(xml) {
				// sqlScripts="createnew";
				$(xml).find('h3').each(function() {
					// var item_text = $(this).text();
						$('#newQuestionnaire\\.questionnaireId').attr("value",
								$(this).text().substring(9));
						$('<span id="msg"/>').css("color", "black").html(
								"Questionnaire saved successfully").appendTo(
								$("#headermsg"));
						$('<br/>').appendTo($("#headermsg"));
						jumpToAnchor('topOfForm');
					});
			}
			});// .ajax

			if (desc1 != '' && saveok == 'true') {
				// if description is really long cause the query string more
				// than 2000 characters
				$.ajax( {
					url : 'questionnaireAjax.do',
					type : 'GET',
					dataType : 'html',
					cache : false,
					data : 'action=savebo1&newQuestionnaire.name=' + qname
							+ '&newQuestionnaire.questionnaireId='
							+ questionnaireId
							+ '&newQuestionnaire.description=' + desc1
							+ '&newQuestionnaire.isFinal=' + qisfinal,
					async : false,
					timeout : 1000,
					error : function() {
						// alert('Error loading XML document');
					jumpToAnchor('topOfForm');
					$('<span id="msg"/>').css("color", "red").html(
							"Error when save Questionnaire").appendTo(
							$("#headermsg"))
					$('<br/>').appendTo($("#headermsg"));
				},
				success : function(xml) {
					// sqlScripts="createnew";
					$(xml).find('h3').each(function() {
						// var item_text = $(this).text();
							$('#newQuestionnaire\\.questionnaireId').attr(
									"value", $(this).text().substring(9));
						});
				}
				});// .ajax

			}
            var retmsg="";
			for ( var k = 0; k < sqls.length; k++) {
				sqlScripts = sqls[k];
				sqlScripts = sqlScripts.replace(/#;#/g, ";;;");
				qnaireid = $('#newQuestionnaire\\.questionnaireId').attr(
						"value");
				// alert(sqls.length+"-"+k)
				// TODO : should consider to send newquestionnaire itself to
				// save.
				// need to resend newquestionnaire because it might be changed.
				// also need to deal with 'description' which is varchar2(2000)
				// we can not assume that is is not over 2000, so need to think
				// about it.
				$.ajax( {
					url : 'questionnaireAjax.do',
					type : 'GET',
					dataType : 'html',
					cache : false,
					data : 'action=new&sqlScripts=' + sqlScripts
							+ '&newQuestionnaire.questionnaireId=' + qnaireid,
					async : false,
					timeout : 1000,
					error : function() {
						alert('error when saving');
					},
					success : function(xml) {
			            $(xml).find('h3').each(function(){
			                retmsg = $(this).text();
			             // alert(raExist);
			 
			              });
			            if (retmsg.indexOf("error") > -1) {
			            	$("#headermsg").html("");
			                $('<span id="msg"/>').css("color", "red").html(
			                        "Error when save Questionnaire <br/>"+retmsg).appendTo(
			                        $("#headermsg"))
			                       $('<br/>').appendTo($("#headermsg"));
			            } else {	
						   sqlScripts = "edit";
						   retmsg="";
			            }   
					}
				});// .ajax
				if (retmsg != '') {
					break;
				}	
			} // end for
			sqlidx = 0;
		} // if-then-else
		return false;
	});

// -- end should be shared
