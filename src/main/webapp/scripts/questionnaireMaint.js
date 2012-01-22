// copied from questionnairenew.js

var node;
var curi = -1;
var ucount = 1;
var removedNode;
var maxCopyNodeIdx = 0;
var cutNode;
var copyNode;
var replaceNode;
var sqlScripts = "createnew";
//var jsContextPath = "${pageContext.request.contextPath}";
var sqls = [];
var sqlidx = 0;
var groupid = 0;
var curgroup = 0;
var cutNodeParentCode = 0;
var newqn= new Array();
jq(document).ready(function() {
	jq.ajaxSettings.cache = false;
	jq("#example").treeview( {
		toggle : function() {
			// alert ("toggle "+jq(this).attr("id"));
	},
	animated : "fast",
	collapsed : true,
	control : "#treecontrol"
	});

});

// end copied from questionnairenew


var uprefix = "document.newMaintainableObject.businessObject.questionnaireUsages[";
//for jquery 
var juprefix = "document\\.newMaintainableObject\\.businessObject\\.questionnaireUsages\\[";
var isCopy = 'false'; 
var qnversion=1;
var firstidx = -1;
var loadcount = 0;
var initucount = 0;


/*
 * This is the main function to create all the html for a question
 * each question is associated with 'li' tag which is jquery's node
 */
function getQuestionNew(description, qtypeid, vers, dispans, ansmax, maxlength, childNode) {

	//alert (" set up "+description+"-"+qtypeid+"-"+vers+"-"+dispans+"-"+ansmax+"-"+maxlength);
	var qnaireid = "qnaireid" + curi;
	var question = jq('<li class="closed expandable"></li>').attr("id", qnaireid);
	var divId = "listcontent" + curi;

	var div62 = jq('<div/>');
	var linktmp = jq('<a style="margin-left:2px;"></a>').attr("id",
			"listcontrol" + curi).attr("name","listcontrol" + curi).html(description);
	linktmp.click(function() {
		var idx = jq(this).attr("id").substring(11);
		jq(".hierarchydetail:not(#listcontent" + idx + ")").slideUp(300);
		if (jq(this).parents('div:eq(0)').children('div:eq(0)').size() == 0) {
			//var vers = "1.00";
			var divtmp = getMaintTable(description, qtypeid, vers, idx, childNode);
			divtmp.appendTo(jq(this).parents('div:eq(0)'));
			toggleHSControl(idx);
			jq("#listcontent" + idx).slideToggle(300);
		}
		jq("#listcontent" + idx).slideToggle(300);
	});	
	linktmp.appendTo(div62);
	
	// new version question message & link
	if (jq("#readOnly").attr("value") != 'true' && newqn[curi] && !isNaN(newqn[curi])) {
		var outerLinkSpan = jq('<span style="margin-left: 10px; font-style: italic;">A new question </span>');
	    var linkNewQ = jq('<a style="margin-left:2px;"></a>').attr("id",
			"newqn" + curi).attr("name","newqn" + curi).html("<font color=red><u>version</u></font>");
	    linkNewQ.click(function() {
		    var idx = jq(this).attr("id").substring(5);
	        newQuestionWindow = window.open(extractUrlBase() +
	    	                               "/kr/directInquiry.do?businessObjectClassName=org.kuali.kra.questionnaire.question.Question&methodToCall=start" +
	    	                               "&questionRefId=" + newqn[idx] , 
	    	                               "_blank", "width=640, height=600, scrollbars=yes");
	    });
	    linkNewQ.appendTo(outerLinkSpan);
	    var innerLinkSpan = jq('<span style="margin-left:2px;"> exists. Open question to update.</span>');
	    innerLinkSpan.appendTo(outerLinkSpan);
	    outerLinkSpan.appendTo(div62);
	    
	    //linkNewQ.appendTo(div62);

	}

	div62.appendTo(question);

	sethiddenfields();

    jq("#question"+ curi).attr("value",description+"#f#" +qtypeid+"#f#"+vers+"#f#" +dispans+"#f#"+ansmax+"#f#" +maxlength);

	return question;

} // end addQuestion

/*
 * hidden fields : some for js function to use and others are used when post to server
 */
function sethiddenfields() {
	var hidtr = jq('<tr/>');
	var hidtd = jq('<td colspan="2"/>');
	// question id for this node
	qntag = jq('<input type="hidden" id = "qid" name = "qid" />').attr("id",
			"qid" + curi).attr("name", "qid" + curi);
	qntag.appendTo(hidtd);
	qntag = jq('<input type="hidden" id = "qseq" name = "qseq" />').attr("id",
			"qseq" + curi).attr("name", "qseq" + curi);
	qntag.appendTo(hidtd);

	qntag = jq('<input type="hidden" id = "qnum" name = "qnum" />').attr("id",
			"qnum" + curi).attr("name", "qnum" + curi);
	qntag.appendTo(hidtd);
		
	qntag = jq('<input type="hidden" id = "question" name = "question" />')
	.attr("id", "question" + curi).attr("name", "question" + curi);
    qntag.appendTo(hidtd);
    
	qntag = jq('<input type="hidden" id = "question" name = "question" />')
	.attr("id", "qnaireQuestions[" + curi+"]").attr("name", "qnaireQuestions[" + curi+"]");
    qntag.appendTo(hidtd);
	
	hidtd.appendTo(hidtr);
	// FF rendering issue. If not hided, then 'line' will be
	// drawn at the bottom of the table for each Q hidden row
	hidtr.hide(); 
	
	hidtr.appendTo(jq("#question-table"));

}

/*
 * Questionnaire question maintenance table set up.
 * This function is usually called when question is clicked the first time.
 * This is the implementation to mix ajax to retrieve the maintainable with ajax call.
 * 
 */

function getMaintTable(description, qtypeid, vers, idx, childNode) {
	var qnaireid = "qnaireid" + idx;
	var divId = "listcontent" + idx;
	var div64 = jq(' <div class="hierarchydetail" style="margin-top:2px;">')
			.attr("id", divId);
	var text = '';
	var response = '';
	var value = '';
	if (childNode == 'true') {
		var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
		response = responseArray[splitq[6]];
		value = splitq[7];

		
	}

	jq.ajax( {
		url : 'maintenanceQn.do',
		type : 'POST',
		dataType : 'html',
		data : 'methodToCall=getQuestionMaintainTable&qidx=' + idx +'&questionId='+jq('#qid'+idx).attr('value')
		       + '&moveup=' + jq("#qnaireid" + idx).prev().size()+ '&movedn=' + jq("#qnaireid" + idx).next().size()
		       + '&childNode=' + childNode + '&response=' + response+ '&value=' + value + '&readOnly=' + jq("#readOnly").attr("value"),
		cache : false,
		async : false,
		timeout : 1000,
		error : function() {
			alert('Error loading XML document');
		},
		success : function(xml) {
			jq(xml).find('h3').each(function() {
				text = jq(this).html();
				//jq('#submodulediv').html(jq(this).html());

				});
		}
	}); // end ajax
	//alert(text)
	jq(div64).html(text);	
    return div64;
}

function clickSearch(curidx) {

checkToAddQn(curidx);
return false;
}

function clickAdd(curidx) {
//	alert("add question "+curidx);
	var idx = curidx;
	var addQn = "#addQn"+curidx
	 //alert(jq("#newqdesc"+idx).attr("value")+"-"+jq("#newqdispans"+idx).attr("value"))
		if (jq("#newqdesc" + idx).attr("value") == ''
				|| jq("#newqtypeid" + idx).attr("value") == '') {
			alert("Please select a question to add");
		} else {
			curi++;

			var radioVal = jq(
					".radioQn" + jq(addQn).attr("id").substring(5)
							+ ":checked").val();
			var childNode = 'true';
			if (radioVal == 'sibling'
					&& jq(addQn).parents('li:eq(0)').parents(
							'ul:eq(0)').attr("id") == 'example') {
				childNode = 'false';
			}
			var listitem = getQuestionNew(jq(
					"#newqdesc" + jq(addQn).attr("id").substring(5))
					.attr("value"),
					jq("#newqtypeid"+ jq(addQn).attr("id").substring(5)).attr("value"),
					jq("#newqvers"+ jq(addQn).attr("id").substring(5)).attr("value"),
					jq("#newqdispans"+ jq(addQn).attr("id").substring(5)).attr("value"),
					jq("#newqmaxans"+ jq(addQn).attr("id").substring(5)).attr("value"),
					jq("#newqmaxlength"+ jq(addQn).attr("id").substring(5)).attr("value"),
					childNode);
			var ultag = jq('<ul></ul>');
			ultag.appendTo(listitem);
			var idx = listitem.attr("id").substring(8);
			if (radioVal == 'sibling') {
				// alert('sibling');
				var parentUl = jq(addQn).parents('li:eq(0)').parents(
						'ul:eq(0)');
				listitem.insertAfter(jq(addQn).parents('li:eq(0)'));
				jq("#movedn" + idx).hide();
				jq(
						"#movedn"
								+ listitem.prev().attr("id")
										.substring(8)).show();
				// TODO trying to group
				// alert("parent u"+parentUl.attr("id"));
				if (parentUl.attr("id") == 'example') {
					// insert after, so assume current group 
					// TODO : need to adjust group to 20/page ?
					jq(listitem).attr("class", "group" + curgroup);
					if (jq(".group"+curgroup).size() > 20) {
						adjustGroupDown(); 
					}	
					//addToGroup(listitem);
					if (curgroup == groupid) {
						jq("#nextGroup").hide();
					} else if (curgroup > 0) {
						jq("#prevGroup").show();
					} else if (groupid > 0) {
						jq("#nextGroup").show();
					}
				}
			} else {
				var parentUl = jq(addQn).parents('li:eq(0)')
						.children('ul:eq(0)');
				listitem.appendTo(parentUl);

				// TODO : if add 2nd item, then add 'movedn' to
				// 1st
				// item. maybe use hide/show instead of 'remove'
				// "==1" is the one just added
				if (jq(addQn).parents('li:eq(0)')
						.children('ul:eq(0)').children('li').size() == 1) {
					jq("#moveup" + idx).hide();
					jq("#movedn" + idx).hide();
				} else {
					// alert("prev
					// "+listitem.prev().attr("id"));
					jq("#movedn" + idx).hide();
					jq(
							"#movedn"
									+ listitem.prev().attr("id")
											.substring(8)).show();
				}
			}

			// also need this to show 'folder' icon
			jq('#example').treeview( {
				add : listitem
			});

			//alert(childNode)
			if (childNode == 'true') {
				if (jq(addQn).parents('li:eq(0)').children('div:eq(0)').attr("class").indexOf("expandable") > 0) {
                      /* to force it to show the child node just added.
                       * the div 'class' is changing based on whether the node is expand or collapsed
                       */
					jq(addQn).parents('li:eq(0)').children('div:eq(0)').click();
						//jq("#listcontrol"+listitem.attr("id").substring(8)).click();
					}
			}
			// TODO : set up for insert
			/*
			 * questionnairenumber from #questionnairenumber
			 * questionId from #qid sequenceNumber from
			 * jq(this).parents('li:eq(0)').siblings().size() ?
			 */
			// jq(listitem).parents('ul:eq(0)').parents('li:eq(0)').size()
			// == 0 : check whetehr it is at the top level
			var parentNum;
			if (jq(listitem).parents('ul:eq(0)').parents('li:eq(0)')
					.size() == 0) {
				parentNum = 0;
			} else {
				// alert("parents li
				// "+jq(listitem).parents('ul:eq(0)').parents('li:eq(0)').attr("id"));
				parentNum = jq(
						"#qnum"
								+ jq(listitem).parents('ul:eq(0)')
										.parents('li:eq(0)').attr(
												"id").substring(8))
						.attr("value");
			}
			jq("#qnum" + jq(listitem).attr("id").substring(8)).attr(
					"value", jq("#questionNumber").attr("value"));
			var qid = jq("#newqid" + jq(addQn).attr("id").substring(5))
					.attr("value");
			jq("#qid" + jq(listitem).attr("id").substring(8)).attr(
					"value", qid);

			var seqnum = Number(jq(listitem).siblings().size()) + 1;
			if (radioVal == 'sibling') {
				//var num = Number(jq("#qseq"+jq(this).attr("id").substring(5)).attr("value"))+1;
				
			    seqnum = Number(jq("#qseq"+jq(addQn).attr("id").substring(5)).attr("value"))+1;
			   // seqnum = num;
				//alert(seqnum+"-"+jq(this).attr("id"));
			    var nextseq = seqnum +1;
			    var nextitem = jq(listitem).next();
			    // update seq for the siblings after the new node
			    while (nextitem.size() > 0) {
					var splitq = jq("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value").split("#f#");
				    var tmpstr = splitq[0] +"#f#" +splitq[1] 
			        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
			        splitq[7] +"#f#" +nextseq +"#f#" +splitq[9] +"#f#" +splitq[10] ;
				jq("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value",tmpstr);
//				    jq("#"+jqprefix + nextitem.attr("id").substring(8) + "\\]\\.questionSeqNumber").attr("value",nextseq);
			    	jq("#qseq" + nextitem.attr("id").substring(8)).attr("value", nextseq++);	
			    	nextitem = nextitem.next();
			    }	
			}    
			
			var qnum = jq("#questionNumber").attr("value");
			jq("#qseq" + jq(listitem).attr("id").substring(8)).attr("value", seqnum);
			jq("#questionNumber").attr("value",
					Number(jq("#questionNumber").attr("value")) + 1);

			idx = jq(listitem).attr("id").substring(8);		
            var tmpstr = "" +"#f#" +jq('#document\\.newMaintainableObject\\.businessObject\\.questionnaireRefId').attr("value") 
               +"#f#" +qid +"#f#" +qnum +"#f#" +parentNum +"#f#" +"N" +"#f#" +"" +"#f#" +
               "" +"#f#" +seqnum +"#f#" +"1" +"#f#" +"N" ;
            jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);

					
		}
		return false;
	
}

function addrequirement(curidx) {
	var idx = curidx;
	var response = jq("#addrequirement"+curidx).parents('tr:eq(0)').children(
			'td:eq(0)').children('select:eq(0)').attr(
			"value");
	var value = jq("#addrequirement"+curidx).parents('tr:eq(0)').children(
			'td:eq(1)').children('input:eq(0)').attr(
			"value");
	if (okToAddRequirement(response, value, idx)) {
		/*
		 * var opDesc; if (sequence == 1) { opDesc =
		 * "Current Requirements:"; } else { opDesc =
		 * opArray[operator]; }
		 */
		var newResponse = getRequirementDeleteRow(
				responseArray[response], value, idx);
		newResponse.appendTo(jq("#addrequirement"+curidx).parents('div:eq(0)')
				.children('table:eq(0)').children('tbody'));
		var idx = jq("#addrequirement"+curidx).parents('li:eq(0)').attr("id")
				.substring(8);
		var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
	    var tmpstr = splitq[0] +"#f#" +splitq[1] 
        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +"Y" +"#f#" +response +"#f#" +
        value +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] ;
	jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
		jq("#addrequirement"+curidx).parents('tr:eq(0)').remove();
	}
	// }
	return false;
	
}

function clickDeleteResponse(idx) {
	// alert("This would delete this requirement."
	// + jq(this).parents('tr:eq(0)').next().size());
	//var idx = jq(this).parents('li:eq(0)').attr("id")
	//		.substring(8);
	var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +"N" +"#f#" +"" +"#f#" +
    "" +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] ;
jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
	
	getAddRequirementRow(idx).appendTo(
			jq("#deletereq" + idx).parents('tr:eq(0)').parents(
					'tbody:eq(0)'));
	jq("#deletereq" + idx).parents('tr:eq(0)').remove();
	return false;

}

function clickCopy(curidx) {
//	alert("Copy node" +curidx);
	var liId = "li#qnaireid" + curidx;
	copyNode = jq(liId);
	//removedNode = null; // remove & cutNode should not co-exist
	cutNode = null;
	cutNodeParentCode = null;
	maxCopyNodeIdx = 0;
	return false;  // so when clicked, the page will not jump

}

function clickCut(curidx) {
	//alert("Cut node" +curidx);
	var liId = "li#qnaireid" + curidx;
	cutNode = jq(liId);
	//removedNode = null; // remove & cutNode should not co-exist
	copyNode = null;
	cutNodeParentCode = null;
	maxCopyNodeIdx = 0;
	return false;  // so when clicked, the page will not jump

}

function clickMoveup(curidx) {
//	alert("move node" +curidx);
	var curNode = jq("#moveup"+curidx).parents('li:eq(0)');

	var nextNode = jq("#moveup"+curidx).parents('li:eq(0)').prev();
	if (jq(nextNode).children('div:eq(1)').children('div:eq(0)')
			.size() == 0) {
		var nextidx = jq(nextNode).attr("id").substring(8);
		var childNode = 'true';
		if (jq(nextNode).parents('ul:eq(0)').attr("id") == 'example') {
			childNode = 'false';
		}
		var splitq = jq("#question"+nextidx).attr("value").split("#f#");

		var divtmp = getMaintTable(splitq[0], splitq[1],splitq[2], nextidx, childNode);
		divtmp.appendTo(jq(nextNode).children('div:eq(1)'));
		toggleHSControl(nextidx);
	}

	
	// jq(this).parents('li:eq(0)').remove();
	
	/*
	 * TODO : Following clone code is just a hack
	 * the last one does not have the "+" icon displayed.  so use this
	 * to add the last one twice, then remove the clone one.  so, the last one looks like OK.
	 * Should look into 'class' property to fix this hack
	 */

	var cloneNode = null;
	if (curNode.next().size() == 0) {
		//alert("clone ")
	     var cloneNode = curNode.clone(true);
	     cloneNode.appendTo(jq(nextNode).parents('ul:eq(0)'));
	     jq('#example').treeview( {
		    add : cloneNode
	     });
	}



	curNode.insertBefore(nextNode);
	var idx = jq(curNode).attr("id").substring(8);
	var seq = Number(jq("#qseq" + idx).attr("value")) - 1;
	jq("#qseq" + idx).attr("value", seq);
	var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
    splitq[7] +"#f#" +seq +"#f#" +splitq[9] +"#f#" +splitq[10] ;
    jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
//    jq("#"+jqprefix + idx + "\\]\\.questionSeqNumber").attr("value",seq);

    jq("#movedn" + curNode.attr("id").substring(8)).show();
    jq("#moveup" + nextNode.attr("id").substring(8)).show();
//alert ("cp nn size "+curNode.next().attr("id")+"-"+nextNode.prev().size())
    if (curNode.prev().size() == 0) {
	    jq("#moveup" + curNode.attr("id").substring(8)).hide();
    }
    if (nextNode.next().size() == 0 || nextNode.next().attr("id") == curNode.attr("id")) {
	// not sure why this one is = 1 if it is moved to the last one, still has curnode as 'next'
	// so, move this manipulation before insert
	// this is probably caused by the clonenode added after the last node, then removed it.
	// FF's inspect element did not see it, not sure why jquery still thinks it exists.
	    jq("#movedn" + nextNode.attr("id").substring(8)).hide();
    }
	idx = jq(nextNode).attr("id").substring(8);
	seq = Number(jq("#qseq" + idx).attr("value")) + 1;
	jq("#qseq" + idx).attr("value", seq);
	var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
    splitq[7] +"#f#" +seq +"#f#" +splitq[9] +"#f#" +splitq[10] ;
    jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
	swapGroupId(curNode, nextNode);
	
    if (cloneNode) {
    	//alert("remove clone")
	    cloneNode.remove();
    }
    return false;	

}

function clickMovedn(curidx) {
//	alert("Movedn node" +curidx);
	var curNode = jq("#movedn"+curidx).parents('li:eq(0)');
	var nextNode = jq("#movedn"+curidx).parents('li:eq(0)').next();
	/*
	 * question html is looks like <li> <div> for class
	 * <div> for listcontrol, it has children div of
	 * listcontent <ul> for children questions </li>
	 */
	if (jq(nextNode).children('div:eq(1)').children(
			'div:eq(0)').size() == 0) {
		var nextidx = jq(nextNode).attr("id").substring(8);
		var vers = "1.00";
		// alert("set up table
		// "+nextidx+"-"+jq(nextNode).children('div:eq(1)').children('a:eq(0)').attr("id"));
		var childNode = 'true';
		if (jq(nextNode).parents('ul:eq(0)').attr("id") == 'example') {
			childNode = 'false';
		}
		var splitq = jq("#question"+nextidx).attr("value").split("#f#");
		var divtmp = getMaintTable(splitq[0], splitq[1], splitq[2],nextidx, childNode);
		divtmp.appendTo(jq(nextNode).children('div:eq(1)'));
		toggleHSControl(nextidx);
	}

	var cloneNode = null;
	if (nextNode.next().size() == 0) {
		//alert("clone ")
	    var cloneNode = nextNode.clone(true);
	    cloneNode.appendTo(jq(nextNode).parents('ul:eq(0)'));
	    jq('#example').treeview( {
		    add : cloneNode
	    });
	}

	curNode.insertAfter(nextNode);
	var idx = jq(curNode).attr("id").substring(8);
	var seq = Number(jq("#qseq" + idx).attr("value")) + 1;
	jq("#qseq" + idx).attr("value", seq);

	var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
    splitq[7] +"#f#" +seq +"#f#" +splitq[9] +"#f#" +splitq[10] ;
    jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
	
	//jq("#"+jqprefix + idx + "\\]\\.questionSeqNumber").attr("value",seq);

	//alert(idx)
	jq("#moveup" + curNode.attr("id").substring(8)).show();
	jq("#movedn" + nextNode.attr("id").substring(8)).show();
	if (nextNode.prev().size() == 0) {
		// alert("move up next node");
		jq("#moveup" + nextNode.attr("id").substring(8))
				.hide();
	}
	if (curNode.next().size() == 0 || curNode.next().attr("id") == nextNode.attr("id")) {
		// see note from getmoveuplink for this weird condition check
		// alert ("move dn no next node ");
		jq("#movedn" + curNode.attr("id").substring(8))
				.hide();
	}
	var idx = jq(nextNode).attr("id").substring(8);
	seq = Number(jq("#qseq" + idx).attr("value")) - 1;
	jq("#qseq" + idx).attr("value", seq);
	var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
    splitq[7] +"#f#" +seq +"#f#" +splitq[9] +"#f#" +splitq[10] ;
    jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
//    jq("#"+jqprefix + idx + "\\]\\.questionSeqNumber").attr("value",seq);
	// TODO : trying to group
	swapGroupId(curNode, nextNode);
    if (cloneNode) {
    	//alert("remove clone")
	    cloneNode.remove();
    }
	return false;	


}

function clickRemove(curidx) {
	//alert("remove node" +curidx);
	var liId = "li#qnaireid" + curidx;
	
	//per kcirb-1486 a node cannot be deleted if it has child nodes
	//due to FE in Coeus
	if (hasChildren(liId)) {
		alert("Child Node exists, Can't delete");
		return false;
	}
	// TODO : IE problem , clone does not clone child node
		// removedNode = jq(liId).clone(true);
		// removedNode = jq(liId);
		if (jq(liId).prev().size() == 0 && jq(liId).next().size() > 0) {
			jq("#moveup" + jq(liId).next().attr("id").substring(8))
					.hide();
		}
		if (jq(liId).next().size() == 0 && jq(liId).prev().size() > 0) {
			jq("#movedn" + jq(liId).prev().attr("id").substring(8))
					.hide();
		}
		var idx = jq(liId).attr("id").substring(8);
		var parentNum;
		if (jq(liId).parents('ul:eq(0)').parents('li:eq(0)').size() == 0) {
			parentNum = 0;
			var idx1 = jq(liId).attr("class").indexOf(" ");
			if (idx1 < 0) {
				// TODO weird problem, initial from return list, class is in 'li'
				// when it is loaded , then class is included in 'div' ?
				idx1 = jq(liId).attr("class").length;
			}	
			adjustGroup(jq(liId).attr("class").substring(5, idx1)); 
			// class is  "group0 expandable"
		} else {
			parentNum = jq(
					"#qnum"
							+ jq(liId).parents('ul:eq(0)').parents(
									'li:eq(0)').attr("id")
									.substring(8)).attr("value");
		}

		deleteChild(parentNum, jq(liId).attr("id"));
		var nextseq = Number(jq("#qseq"+jq(liId).attr("id").substring(8)).attr("value"));
	    var nextitem = jq(liId).next();
	    // update hidden seq# for the items follow the deleted item
	    while (nextitem.size() > 0) {
	    	jq("#qseq" + nextitem.attr("id").substring(8)).attr("value", nextseq++);	
	    	nextitem = nextitem.next();
	    }	

		// TODO : update seqnum of the sibling nodes following it
		jq(liId).remove();
		return false;  // so when clicked, the page will not jump

}

function clickPaste(curidx) {
	//alert("paste node" +curidx);
	var qnaireid = "qnaireid" + curidx;
	if (cutNode) {
		var idx;
		var parentNode = jq("#" + qnaireid);
		var ulTag = parentNode.children('ul');
		if (ulTag.size() > 0) {
			// alert(ulTag.attr("id"));
		} else {
			// alert("not found")
			curi++;
			ulTag = jq('<ul class="filetree"></ul>').attr("id",
					"ul" + curidx);
		}

		  if (qnaireid.substring(8) == jq(cutNode).attr("id").substring(8)) {
			  alert ("Can Not cut/paste on the same node");
		  } else {	  
			// alert(jq(cutNode).children('li').size());
			
			var found = false;
			var pastenode = jq("#"+qnaireid);
			while (!found && pastenode.parents('ul:eq(0)').parents('li:eq(0)').size() > 0 ) {
			  if (pastenode.parents('ul:eq(0)').parents('li:eq(0)').attr("id") == jq(cutNode).attr("id")) {
				  found = true;
			  } else {
				  pastenode = pastenode.parents('ul:eq(0)').parents('li:eq(0)');
			  }	  
			}
			
		  if (found) {
			  alert ("Can Not cut/paste to its decendant");
		  } else {	
			if (cutNodeParentCode != null) {
				parentNum = cutNodeParentCode;
			} else {
				// paste cutnode for the 1st time
			    if (jq(cutNode).parents('ul:eq(0)').parents(
					'li:eq(0)').size() == 0) {
				    parentNum = 0;
				    var idx1 = jq(cutNode).attr("class").indexOf(" ");
					if (idx1 < 0) {
						idx1 = jq(cutNode).attr("class").length;
					}	
				    adjustGroup(jq(cutNode).attr("class").substring(5, idx1)); 
				    // class is "group0 expandable"
			    } else {
				    parentNum = jq(
						"#qnum"
								+ jq(cutNode).parents('ul:eq(0)').parents('li:eq(0)')
										.attr("id").substring(8)).attr("value");
			    }
				deleteChild(parentNum, jq(cutNode).attr("id"));
				var nextseq = Number(jq("#qseq"+jq(cutNode).attr("id").substring(8)).attr("value"));
			    var nextitem = jq(cutNode).next();
			    // update hidden seq# for the items follow the deleted item
			    while (nextitem.size() > 0) {
			    	jq("#qseq" + nextitem.attr("id").substring(8)).attr("value", nextseq++);	
			    	nextitem = nextitem.next();
			    }	
				cutNodeParentCode = parentNum;
			}
			pasteChild(qnaireid, cutNode);

			var liId = cutNode.attr("id");
			var parentRACode;
			if (jq("li#" + liId).parents('ul:eq(0)').size() > 0) {
				// only remove the first time
			    jq("li#" + liId).remove();
			   // alert(cutNode.attr("id"));
			}    
			if (jq("#"+qnaireid).prev().size() == 0) {
				jq("#moveup" + qnaireid.substring(8)).hide();
			}
			if (jq("#"+qnaireid).next().size() == 0) {
				jq("#movedn" + qnaireid.substring(8)).hide();
			}

			//cutNode = null;
		   } // not paste to itself or its children
		  }							
		//}// cutnode
			if (jq(parentNode).children('div:eq(0)').attr("class").indexOf("expandable") > 0) {
                   /* to force it to show the child node just added.
                    * the div 'class' is changing based on whether the node is expand or collapsed
                    */
				jq(parentNode).children('div:eq(0)').click();
					//jq("#listcontrol"+listitem.attr("id").substring(8)).click();
				}

		
	}// if cutnode

	if (copyNode) {
		// paste copy node
		isCopy = "true";
		if (maxCopyNodeIdx == 0) {
		   getMaxCopyNodeIdx(copyNode);
		}
		pasteChild(qnaireid, copyNode);
		//maxCopyNodeIdx = 0;
		isCopy = "false";
		if (jq("#"+qnaireid).children('div:eq(0)').attr("class").indexOf("expandable") > 0) {
            /* to force it to show the child node just added.
             * the div 'class' is changing based on whether the node is expand or collapsed
             */
			jq("#"+qnaireid).children('div:eq(0)').click();
				//jq("#listcontrol"+listitem.attr("id").substring(8)).click();
			}

	}
	return false;  // so when clicked, the page will not jump

}


/*
 * set up questionaire question panel's tab header
 */
function getMaintTableHeader(description, vers) {
	var thead = jq('<thead/>');
	var trtmp = jq('<tr/>');
	// TODO not sure why 'subelementheader class is not picked up from
	// new_kuali.css
	// it is working fine in researchareahierarchy ???
	// var thtmp = jq('<th class="subelementheader" align="left" >');
	var thtmp = jq('<th style="background:#939393;height:18px;color:#FFFFFF;text-align:left;padding-left:4px;" align="left" >');
	var imgtmp = jq(
			'<a href="#" class="hidedetail"><img src="kr/static/images/tinybutton-hide.gif" title="Hide detail" align="absmiddle" border="0" width="45" height="15"></a>')
			.toggle(
			// TODO : really need this toggle. Mock's toggle did not show up
			);
	
	thtmp.html(description + "(" + vers + ")");
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
	var tbl80 = jq(
			'<table cellpadding="0" cellspacing="0" class="elementtable" width="100%">')
			.attr("id", "tbl80" + curidx);
	var trtmp = jq('<tr></tr>');
	var tmp = jq('<th style="text-align:right; width:120px;">Node:</th>');
	trtmp.html(tmp);
	var thtmp = jq('<td/>');
	getMoveUpLink(curidx).appendTo(thtmp);
	getMoveDownLink(curidx).appendTo(thtmp);

	var image = jq(
			'<a href="#"><img src="static/images/tinybutton-removenode.gif" width="79" height="15" border="0" alt="Remove Node" title="Remove this node and its child groups/sponsors"></a>&nbsp')
			.attr("id", "remove" + curidx)
			.click(function() {
				var liId = "li#" + qnaireid;
				// TODO : IE problem , clone does not clone child node
					// removedNode = jq(liId).clone(true);
					// removedNode = jq(liId);
					if (jq(liId).prev().size() == 0 && jq(liId).next().size() > 0) {
						jq("#moveup" + jq(liId).next().attr("id").substring(8))
								.hide();
					}
					if (jq(liId).next().size() == 0 && jq(liId).prev().size() > 0) {
						jq("#movedn" + jq(liId).prev().attr("id").substring(8))
								.hide();
					}
					var idx = jq(liId).attr("id").substring(8);
					var parentNum;
					if (jq(liId).parents('ul:eq(0)').parents('li:eq(0)').size() == 0) {
						parentNum = 0;
						var idx1 = jq(liId).attr("class").indexOf(" ");
						if (idx1 < 0) {
							// TODO weird problem, initial from return list, class is in 'li'
							// when it is loaded , then class is included in 'div' ?
							idx1 = jq(liId).attr("class").length;
						}	
						adjustGroup(jq(liId).attr("class").substring(5, idx1)); 
						// class is  "group0 expandable"
					} else {
						parentNum = jq(
								"#qnum"
										+ jq(liId).parents('ul:eq(0)').parents(
												'li:eq(0)').attr("id")
												.substring(8)).attr("value");
					}

					deleteChild(parentNum, jq(liId).attr("id"));
					var nextseq = Number(jq("#qseq"+jq(liId).attr("id").substring(8)).attr("value"));
				    var nextitem = jq(liId).next();
				    // update hidden seq# for the items follow the deleted item
				    while (nextitem.size() > 0) {
				    	jq("#qseq" + nextitem.attr("id").substring(8)).attr("value", nextseq++);	
				    	nextitem = nextitem.next();
				    }	

					// TODO : update seqnum of the sibling nodes following it
					jq(liId).remove();
					return false;  // so when clicked, the page will not jump
				});
	image.appendTo(thtmp);
	image = jq(
			'<a href="#"><img src="static/images/tinybutton-cutnode.gif" width="79" height="15" border="0" alt="Cut Node" title="Cut this node and its child roups/sponsors.  (Node will not be removed until you paste it.)"></a>&nbsp')
			.attr("id", "cut" + curidx).click(function() {
				// alert("Cut node");
				var liId = "li#" + qnaireid;
				cutNode = jq(liId);
				//removedNode = null; // remove & cutNode should not co-exist
				copyNode = null;
				cutNodeParentCode = null;
				maxCopyNodeIdx = 0;
				return false;  // so when clicked, the page will not jump
			});
	image.appendTo(thtmp);
	image = jq(
			'<a href="#"><img src="static/images/jquery/tinybutton-copynode.gif" width="79" height="15" border="0" alt="Copy Node" title="Copy this node and its child.)"></a>&nbsp')
			.attr("id", "copy" + curidx).click(function() {
				// alert("Copy node");
				var liId = "li#" + qnaireid;
				copyNode = jq(liId);
				//removedNode = null; // remove & cutNode should not co-exist
				cutNode = null;
				cutNodeParentCode = null;
				maxCopyNodeIdx = 0;
				return false;  // so when clicked, the page will not jump
			});
	image.appendTo(thtmp);
	image = jq(
			'<a href="#"><img src="static/images/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Paste Node" title="Paste your previously cut node structure under this node"></a>')
			.attr("id", "paste" + curidx).click(function() {

				// TODO : what if paste 'not top level node' to top level, then
					// how about the exist 'condition'

					if (cutNode) {
						var idx;
						var parentNode = jq("#" + qnaireid);
						var ulTag = parentNode.children('ul');
						if (ulTag.size() > 0) {
							// alert(ulTag.attr("id"));
						} else {
							// alert("not found")
							curi++;
							ulTag = jq('<ul class="filetree"></ul>').attr("id",
									"ul" + curidx);
						}
	
						  if (qnaireid.substring(8) == jq(cutNode).attr("id").substring(8)) {
							  alert ("Can Not cut/paste on the same node");
						  } else {	  
							// alert(jq(cutNode).children('li').size());
							
							var found = false;
							var pastenode = jq("#"+qnaireid);
							while (!found && pastenode.parents('ul:eq(0)').parents('li:eq(0)').size() > 0 ) {
							  if (pastenode.parents('ul:eq(0)').parents('li:eq(0)').attr("id") == jq(cutNode).attr("id")) {
								  found = true;
							  } else {
								  pastenode = pastenode.parents('ul:eq(0)').parents('li:eq(0)');
							  }	  
							}
							
						  if (found) {
							  alert ("Can Not cut/paste to its decendant");
						  } else {	
							if (cutNodeParentCode != null) {
								parentNum = cutNodeParentCode;
							} else {
								// paste cutnode for the 1st time
							    if (jq(cutNode).parents('ul:eq(0)').parents(
									'li:eq(0)').size() == 0) {
								    parentNum = 0;
								    var idx1 = jq(cutNode).attr("class").indexOf(" ");
									if (idx1 < 0) {
										idx1 = jq(cutNode).attr("class").length;
									}	
								    adjustGroup(jq(cutNode).attr("class").substring(5, idx1)); 
								    // class is "group0 expandable"
							    } else {
								    parentNum = jq(
										"#qnum"
												+ jq(cutNode).parents('ul:eq(0)').parents('li:eq(0)')
														.attr("id").substring(8)).attr("value");
							    }
								deleteChild(parentNum, jq(cutNode).attr("id"));
								var nextseq = Number(jq("#qseq"+jq(cutNode).attr("id").substring(8)).attr("value"));
							    var nextitem = jq(cutNode).next();
							    // update hidden seq# for the items follow the deleted item
							    while (nextitem.size() > 0) {
							    	jq("#qseq" + nextitem.attr("id").substring(8)).attr("value", nextseq++);	
							    	nextitem = nextitem.next();
							    }	
								cutNodeParentCode = parentNum;
							}
							pasteChild(qnaireid, cutNode);

							var liId = cutNode.attr("id");
							var parentRACode;
							if (jq("li#" + liId).parents('ul:eq(0)').size() > 0) {
								// only remove the first time
							    jq("li#" + liId).remove();
							   // alert(cutNode.attr("id"));
							}    
							if (jq("#"+qnaireid).prev().size() == 0) {
								jq("#moveup" + qnaireid.substring(8)).hide();
							}
							if (jq("#"+qnaireid).next().size() == 0) {
								jq("#movedn" + qnaireid.substring(8)).hide();
							}

							//cutNode = null;
						   } // not paste to itself or its children
						  }							
						//}// cutnode
							if (jq(parentNode).children('div:eq(0)').attr("class").indexOf("expandable") > 0) {
	                               /* to force it to show the child node just added.
	                                * the div 'class' is changing based on whether the node is expand or collapsed
	                                */
								jq(parentNode).children('div:eq(0)').click();
									//jq("#listcontrol"+listitem.attr("id").substring(8)).click();
								}

						
					}// if cutnode

					if (copyNode) {
						// paste copy node
						isCopy = "true";
						if (maxCopyNodeIdx == 0) {
						   getMaxCopyNodeIdx(copyNode);
						}
						pasteChild(qnaireid, copyNode);
						//maxCopyNodeIdx = 0;
						isCopy = "false";
						if (jq("#"+qnaireid).children('div:eq(0)').attr("class").indexOf("expandable") > 0) {
                            /* to force it to show the child node just added.
                             * the div 'class' is changing based on whether the node is expand or collapsed
                             */
							jq("#"+qnaireid).children('div:eq(0)').click();
								//jq("#listcontrol"+listitem.attr("id").substring(8)).click();
							}

					}
					return false;  // so when clicked, the page will not jump

				});
	image.appendTo(thtmp);
	image = jq(
	'<a href="#"><img src="static/images/jquery/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Modify Node" title="Modify the question in this node.)"></a>&nbsp')
	.attr("id", "modify" + curidx).click(function() {
		 alert("Modify node");
		return false;  // so when clicked, the page will not jump
	});
	image.appendTo(thtmp);
	
	thtmp.appendTo(trtmp);
	jq('<th style="text-align:right;">Add Question:</th>').appendTo(trtmp);

	getAddQuestionRow(curidx).appendTo(trtmp); // row2
	trtmp.appendTo(tbl80); // row1
	return tbl80;

}



/*
 * traverse thru the node to copy this node tree need to clone copy node,
 * otherwise if paste to its own children, then this will become infinite loop,
 */
function pasteChild(parentid, startnode) {
	var parentNode = jq("#" + parentid);
	var ulTag = parentNode.children('ul');

	// startnode = jq("#"+childid);
	// alert("copy node "+jq(startnode).attr("id").substring(8));
	var stidx = jq(startnode).attr("id").substring(8);
	var splitq = jq("#question"+stidx).attr("value").split("#f#");
	var qdesc = splitq[0];
	var qtypeid = splitq[1];
    var qvers= splitq[2];
    var qdispans= splitq[3];
    var qansmax= splitq[4];
    var qmaxlength= splitq[5];

	curi++;
	// set new version question if any
	newqn[curi] = newqn[stidx];
	var listitem = getQuestionNew(qdesc, qtypeid, qvers,qdispans,qansmax,qmaxlength, "true");
	var ultag = jq('<ul></ul>');
	ultag.appendTo(listitem);
	var idx = listitem.attr("id").substring(8);
	listitem.appendTo(ulTag);
	if (jq(listitem).parents('ul:eq(0)').children('li').size() == 1) {
		jq("#moveup" + idx).hide();
		jq("#movedn" + idx).hide();
	} else {
		jq("#movedn" + idx).hide();
		if (listitem.prev().size() > 0) {
			jq("#movedn" + listitem.prev().attr("id").substring(8)).show();
		}
	}

	// also need this to show 'folder' icon
	jq('#example').treeview( {
		add : listitem
	});

	jq("#qnum" + idx).attr("value", jq("#questionNumber").attr("value"));
	var qid = jq("#qid" + jq(startnode).attr("id").substring(8)).attr("value");
	jq("#qid" + idx).attr("value", qid);
	var seqnum = Number(jq(listitem).siblings().size()) + 1;
	jq("#qseq" + idx).attr("value", seqnum);

	var liId = "li#" + parentid;
	var qid = jq("#qid" + idx).attr("value");
	var qnum = jq("#questionNumber").attr("value");
	var parentNum = jq("#qnum" + jq(liId).attr("id").substring(8)).attr("value");
	jq("#questionNumber").attr("value",
			Number(jq("#questionNumber").attr("value")) + 1)

	// questionnaireQuestionsId/questionnaireRefIdFk/questionRefIdFk/questionNumber/parentQuestionNumber		
    //conditionFlag/condition/conditionValue/questionSeqNumber/versionNumber/deleted
	var tmpstr = "" +"#f#" +jq('#document\\.newMaintainableObject\\.businessObject\\.questionnaireRefId').attr("value") 
          +"#f#" +qid +"#f#" +qnum +"#f#" +parentNum +"#f#" +"N" +"#f#" +"" +"#f#" +
          "" +"#f#" +seqnum +"#f#" +"1" +"#f#" +"N" ;
	jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
    	
			
	// alert("child copy node"
	// + jq("#cond" + jq(startnode).attr("id").substring(8)).attr("value"));
	cidx = jq(startnode).attr("id").substring(8);

	var splitq = jq("#qnaireQuestions\\["+ cidx+"\\]").attr("value").split("#f#");
	cond = splitq[6];
	value = splitq[7];
	if (cond != '') {
		var newResponse = getRequirementDeleteRow(responseArray[cond], value,
				idx);
		newResponse.appendTo(jq("#addrequirement" + idx).parents('div:eq(0)')
				.children('table:eq(0)').children('tbody'));
		jq("#addrequirement" + idx).parents('tr:eq(0)').remove();
		
		splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
	    var tmpstr = splitq[0] +"#f#" +splitq[1] 
        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +"Y" +"#f#" +cond +"#f#" +
        value +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] ;
	jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
	}

	if (jq(startnode).children('ul').children('li').size() > 0) {

		jq(startnode).children('ul').children('li').each(
				function() {
					if (isCopy == 'false'
							|| (isCopy == 'true' && jq(this).attr("id")
									.substring(8) <= maxCopyNodeIdx)) {
						pasteChild(jq(listitem).attr("id"), jq(this));
					}
				});
	}

}

/*
 * traverse thru the node to delete this node tree to create scripts for delete
 * this is called by remove node action
 */
function deleteChild(parentNum, childid) {

	var idx = jq("#" + childid).attr("id").substring(8);
	var qnum = jq("#qnum" + idx).attr("value");
	var childrenli = jq("#" + childid).children('ul').children('li');
	var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
    splitq[7] +"#f#" +splitq[8]  +"#f#" +splitq[9] +"#f#" +"Y" ;
jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);

var nextseq = splitq[8];

var nextitem = jq("#" + childid).next();
// update seq for the siblings after the new node
while (nextitem.size() > 0) {
	var splitq = jq("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
    splitq[7] +"#f#" +(nextseq++) +"#f#" +splitq[9] +"#f#" +splitq[10] ;
jq("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value",tmpstr);
	nextitem = nextitem.next();
}	


	if (childrenli.size() > 0) {

		childrenli.each(function() {
			deleteChild(qnum, jq(this).attr("id"));
		});
	}

}

/*
 * traverse thru the node to collect copy nodes IE issue with clone, so set this
 * max up, in case the copied node is pasted to its children if don't set this
 * up, it will cause infinite loop
 */
function getMaxCopyNodeIdx(startnode) {

	var idx = jq(startnode).attr("id").substring(8);
	var childrenli = jq(startnode).children('ul').children('li');
	if (idx > maxCopyNodeIdx) {
		maxCopyNodeIdx = idx;
	}

	if (jq(childrenli).size() > 0) {

		jq(childrenli).each(function() {
			getMaxCopyNodeIdx(jq(this));
		});
	}

}

/*
 * set up movedown link
 */
function getMoveDownLink(curidx) {
	var image = jq('<img style="border:none;" alt="move down" title="Move Down" type="image" >');
	var atag = jq('<a href="#"></a>')
			.attr("id", "movedn" + curidx)
			.click(
					function() {
						var curNode = jq(this).parents('li:eq(0)');
						var nextNode = jq(this).parents('li:eq(0)').next();
						/*
						 * question html is looks like <li> <div> for class
						 * <div> for listcontrol, it has children div of
						 * listcontent <ul> for children questions </li>
						 */
						if (jq(nextNode).children('div:eq(1)').children(
								'div:eq(0)').size() == 0) {
							var nextidx = jq(nextNode).attr("id").substring(8);
							var vers = "1.00";
							// alert("set up table
							// "+nextidx+"-"+jq(nextNode).children('div:eq(1)').children('a:eq(0)').attr("id"));
							var childNode = 'true';
							if (jq(nextNode).parents('ul:eq(0)').attr("id") == 'example') {
								childNode = 'false';
							}
							var splitq = jq("#question"+nextidx).attr("value").split("#f#");
							var divtmp = getMaintTable(splitq[0], splitq[1], splitq[2],nextidx, childNode);
							divtmp.appendTo(jq(nextNode).children('div:eq(1)'));
							toggleHSControl(nextidx);
						}

						var cloneNode = null;
						if (nextNode.next().size() == 0) {
							//alert("clone ")
						var cloneNode = nextNode.clone(true);
						cloneNode.appendTo(jq(nextNode).parents('ul:eq(0)'));
						jq('#example').treeview( {
							add : cloneNode
						});
						}

						curNode.insertAfter(nextNode);
						var idx = jq(curNode).attr("id").substring(8);
						var seq = Number(jq("#qseq" + idx).attr("value")) + 1;
						jq("#qseq" + idx).attr("value", seq);

						var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
					    var tmpstr = splitq[0] +"#f#" +splitq[1] 
				        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
				        splitq[7] +"#f#" +seq +"#f#" +splitq[9] +"#f#" +splitq[10] ;
					jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
						
						//jq("#"+jqprefix + idx + "\\]\\.questionSeqNumber").attr("value",seq);

						//alert(idx)
						jq("#moveup" + curNode.attr("id").substring(8)).show();
						jq("#movedn" + nextNode.attr("id").substring(8)).show();
						if (nextNode.prev().size() == 0) {
							// alert("move up next node");
							jq("#moveup" + nextNode.attr("id").substring(8))
									.hide();
						}
						if (curNode.next().size() == 0 || curNode.next().attr("id") == nextNode.attr("id")) {
							// see note from getmoveuplink for this weird condition check
							// alert ("move dn no next node ");
							jq("#movedn" + curNode.attr("id").substring(8))
									.hide();
						}
						var idx = jq(nextNode).attr("id").substring(8);
						seq = Number(jq("#qseq" + idx).attr("value")) - 1;
						jq("#qseq" + idx).attr("value", seq);
						var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
					    var tmpstr = splitq[0] +"#f#" +splitq[1] 
				        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
				        splitq[7] +"#f#" +seq +"#f#" +splitq[9] +"#f#" +splitq[10] ;
					jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
//					    jq("#"+jqprefix + idx + "\\]\\.questionSeqNumber").attr("value",seq);
						// TODO : trying to group
						swapGroupId(curNode, nextNode);
	                    if (cloneNode) {
	                    	//alert("remove clone")
						cloneNode.remove();
	                    }
	    				return false;	

					});
	image.attr("src", "static/images/jquery/arrow-down.gif");
	atag.html(image);
	if (jq("#qnaireid" + curidx).next().size() == 0) {
		// alert("movedn "+curidx+"-"+jq("#qnaireid" + curidx).attr("id"));
		jq(atag).hide();
	}
	return atag;
}

/*
 * set up move up link
 */
function getMoveUpLink(curidx) {
	var image = jq('<img style="border:none;" alt="move up" title="Move up" type="image" >');
	var atag = jq('<a href="#"></a>')
			.attr("id", "moveup" + curidx)
			.click(function() {
				var curNode = jq(this).parents('li:eq(0)');

					var nextNode = jq(this).parents('li:eq(0)').prev();
					if (jq(nextNode).children('div:eq(1)').children('div:eq(0)')
							.size() == 0) {
						var nextidx = jq(nextNode).attr("id").substring(8);
						var childNode = 'true';
						if (jq(nextNode).parents('ul:eq(0)').attr("id") == 'example') {
							childNode = 'false';
						}
						var splitq = jq("#question"+nextidx).attr("value").split("#f#");

						var divtmp = getMaintTable(splitq[0], splitq[1],splitq[2], nextidx, childNode);
						divtmp.appendTo(jq(nextNode).children('div:eq(1)'));
						toggleHSControl(nextidx);
					}

					
					// jq(this).parents('li:eq(0)').remove();
					
					/*
					 * TODO : Following clone code is just a hack
					 * the last one does not have the "+" icon displayed.  so use this
					 * to add the last one twice, then remove the clone one.  so, the last one looks like OK.
					 * Should look into 'class' property to fix this hack
					 */

					var cloneNode = null;
					if (curNode.next().size() == 0) {
						//alert("clone ")
					var cloneNode = curNode.clone(true);
					cloneNode.appendTo(jq(nextNode).parents('ul:eq(0)'));
					jq('#example').treeview( {
						add : cloneNode
					});
					}



					curNode.insertBefore(nextNode);
					var idx = jq(curNode).attr("id").substring(8);
					var seq = Number(jq("#qseq" + idx).attr("value")) - 1;
					jq("#qseq" + idx).attr("value", seq);
					var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
				    var tmpstr = splitq[0] +"#f#" +splitq[1] 
			        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
			        splitq[7] +"#f#" +seq +"#f#" +splitq[9] +"#f#" +splitq[10] ;
				jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
//				    jq("#"+jqprefix + idx + "\\]\\.questionSeqNumber").attr("value",seq);

				jq("#movedn" + curNode.attr("id").substring(8)).show();
				jq("#moveup" + nextNode.attr("id").substring(8)).show();
				//alert ("cp nn size "+curNode.next().attr("id")+"-"+nextNode.prev().size())
				if (curNode.prev().size() == 0) {
					jq("#moveup" + curNode.attr("id").substring(8)).hide();
				}
				if (nextNode.next().size() == 0 || nextNode.next().attr("id") == curNode.attr("id")) {
					// not sure why this one is = 1 if it is moved to the last one, still has curnode as 'next'
					// so, move this manipulation before insert
					// this is probably caused by the clonenode added after the last node, then removed it.
					// FF's inspect element did not see it, not sure why jquery still thinks it exists.
					jq("#movedn" + nextNode.attr("id").substring(8)).hide();
				}
					idx = jq(nextNode).attr("id").substring(8);
					seq = Number(jq("#qseq" + idx).attr("value")) + 1;
					jq("#qseq" + idx).attr("value", seq);
					var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
				    var tmpstr = splitq[0] +"#f#" +splitq[1] 
			        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
			        splitq[7] +"#f#" +seq +"#f#" +splitq[9] +"#f#" +splitq[10] ;
				jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
					swapGroupId(curNode, nextNode);
					
                    if (cloneNode) {
                    	//alert("remove clone")
					cloneNode.remove();
                    }
				return false;	
				});
	image.attr("src", "static/images/jquery/arrow-up.gif");
	atag.html(image);
	if (jq("#qnaireid" + curidx).prev().size() == 0) {
		jq(atag).hide();
	}
	return atag;
}

/*
 * set up the 'add' question row
 */
function getAddQuestionRow(curidx) {
	var tbl95 = jq(
			'<table style="border:none; width:100%;" cellpadding="0" cellspacing="0"></table>')
			.attr("id", "tbl95" + curidx);
	var trtmp = jq('<tr></tr>');
	var tdtmp = jq('<td style="border:none; width:170px;"></td>');
	
	var newqtbl = jq(
	'<table style="border:none; width:100%;" cellpadding="0" cellspacing="0"></table>');
	var newqtr = jq('<tr></tr>');
	var newqtd = jq('<td style="border:none;"></td>');

	jq('<input type="radio" name = "radio" checked="checked" value="sibling" />')
			.attr("class", "radioQn" + curidx).attr("name", "radioQn" + curidx)
			.appendTo(newqtd);
	jq('<span>as sibling&nbsp;&nbsp;&nbsp</span>').appendTo(newqtd);
	newqtd.appendTo(newqtr);
	
	newqtd = jq('<td style="border:none;"></td>');
	jq('<input type="radio" name = "radio" value="child" />').attr("class",
			"radioQn" + curidx).attr("name", "radioQn" + curidx)
			.appendTo(newqtd);
	jq('<span>as child</span>').appendTo(newqtd);
	newqtd.appendTo(newqtr);
	newqtr.appendTo(newqtbl);
	newqtbl.appendTo(tdtmp);
	
	tdtmp.appendTo(trtmp);

	tdtmp = jq('<td style="border:none;"></td>')
			.html(
					jq(
							'<input type="text" id = "newqdesc" name = "newqdesc" size="50" value="" readonly = "true"/>')
							.attr("id", "newqdesc" + curidx));
	tdtmp.appendTo(trtmp);
	tdtmp = jq('<td style="border:none; width:30px; text-align:center;"></td>');

	var atag = jq('<a href="#"></a>');

	// question id from lookup - to be added as sibling or child of this node
	var qntag = jq('<input type="hidden" id = "newqid" name = "newqid" />')
			.attr("id", "newqid" + curidx).attr("name", "newqid" + curidx);
	qntag.appendTo(tdtmp);
	qntag = jq('<input type="hidden" id = "newqtypeid" name = "newqtypeid" />')
			.attr("id", "newqtypeid" + curidx).attr("name","newqtypeid" + curidx);
	qntag.appendTo(tdtmp);
	qntag = jq('<input type="hidden" id = "newqvers" name = "newqvers" />')
	    .attr("id", "newqvers" + curidx).attr("name","newqvers" + curidx);
    qntag.appendTo(tdtmp);
    qntag = jq('<input type="hidden" id = "newqdispans" name = "newqdispans" />')
        .attr("id", "newqdispans" + curidx).attr("name","newqdispans" + curidx);
    qntag.appendTo(tdtmp);
    qntag = jq('<input type="hidden" id = "newqmaxans" name = "newqmaxans" />')
       .attr("id", "newqmaxans" + curidx).attr("name","newqmaxans" + curidx);
    qntag.appendTo(tdtmp);
    qntag = jq('<input type="hidden" id = "newqmaxlength" name = "newqmaxlength" />')
       .attr("id", "newqmaxlength" + curidx).attr("name","newqmaxlength" + curidx);
    qntag.appendTo(tdtmp);

	var image = jq(
			'<img src="static/images/searchicon.gif" id="searchQ" name="searchQ" border="0" class="tinybutton"  alt="Search Question" title="Search Question">')
			.attr("id", "search" + curidx).attr("name", "search" + curidx);
	image.click(function() {
		// TODO : IE problem. after the node is moved up or down, then the
			// "id" of the image got lost.
			// so, before figuring a solution, use this alternative to fin
			// parents id.
			var idx;
			if (jq(this).attr("id") != '') {
				idx = jq(this).attr("id").substring(6);
			} else {
				idx = jq(this).parents('li:eq(0)').attr("id").substring(8);
			}
			// alert (idx)
			checkToAddQn(idx);
			return false;
		});
	atag.html(image);
	atag.appendTo(tdtmp);
	tdtmp.appendTo(trtmp);

	image = jq(
			'<input name="addquestionnaire" src="kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" title="Add questionnaire" type="image" />')
			.attr("id", "addQn" + curidx)
			.click(function() {
				var idx = jq(this).attr("id").substring(5);
				 //alert(jq("#newqdesc"+idx).attr("value")+"-"+jq("#newqdispans"+idx).attr("value"))
					if (jq("#newqdesc" + idx).attr("value") == ''
							|| jq("#newqtypeid" + idx).attr("value") == '') {
						alert("Please select a question to add");
					} else {
						curi++;

						var radioVal = jq(
								".radioQn" + jq(this).attr("id").substring(5)
										+ ":checked").val();
						var childNode = 'true';
						if (radioVal == 'sibling'
								&& jq(this).parents('li:eq(0)').parents(
										'ul:eq(0)').attr("id") == 'example') {
							childNode = 'false';
						}
						var listitem = getQuestionNew(jq(
								"#newqdesc" + jq(this).attr("id").substring(5))
								.attr("value"),
								jq("#newqtypeid"+ jq(this).attr("id").substring(5)).attr("value"),
								jq("#newqvers"+ jq(this).attr("id").substring(5)).attr("value"),
								jq("#newqdispans"+ jq(this).attr("id").substring(5)).attr("value"),
								jq("#newqmaxans"+ jq(this).attr("id").substring(5)).attr("value"),
								jq("#newqmaxlength"+ jq(this).attr("id").substring(5)).attr("value"),
								childNode);
						var ultag = jq('<ul></ul>');
						ultag.appendTo(listitem);
						var idx = listitem.attr("id").substring(8);
						if (radioVal == 'sibling') {
							// alert('sibling');
							var parentUl = jq(this).parents('li:eq(0)').parents(
									'ul:eq(0)');
							listitem.insertAfter(jq(this).parents('li:eq(0)'));
							jq("#movedn" + idx).hide();
							jq(
									"#movedn"
											+ listitem.prev().attr("id")
													.substring(8)).show();
							// TODO trying to group
							// alert("parent u"+parentUl.attr("id"));
							if (parentUl.attr("id") == 'example') {
								// insert after, so assume current group 
								// TODO : need to adjust group to 20/page ?
								jq(listitem).attr("class", "group" + curgroup);
								if (jq(".group"+curgroup).size() > 20) {
									adjustGroupDown(); 
								}	
								//addToGroup(listitem);
								if (curgroup == groupid) {
									jq("#nextGroup").hide();
								} else if (curgroup > 0) {
									jq("#prevGroup").show();
								} else if (groupid > 0) {
									jq("#nextGroup").show();
								}
							}
						} else {
							var parentUl = jq(this).parents('li:eq(0)')
									.children('ul:eq(0)');
							listitem.appendTo(parentUl);

							// TODO : if add 2nd item, then add 'movedn' to
							// 1st
							// item. maybe use hide/show instead of 'remove'
							// "==1" is the one just added
							if (jq(this).parents('li:eq(0)')
									.children('ul:eq(0)').children('li').size() == 1) {
								jq("#moveup" + idx).hide();
								jq("#movedn" + idx).hide();
							} else {
								// alert("prev
								// "+listitem.prev().attr("id"));
								jq("#movedn" + idx).hide();
								jq(
										"#movedn"
												+ listitem.prev().attr("id")
														.substring(8)).show();
							}
						}

						// also need this to show 'folder' icon
						jq('#example').treeview( {
							add : listitem
						});

						//alert(childNode)
						if (childNode == 'true') {
							if (jq(this).parents('li:eq(0)').children('div:eq(0)').attr("class").indexOf("expandable") > 0) {
	                               /* to force it to show the child node just added.
	                                * the div 'class' is changing based on whether the node is expand or collapsed
	                                */
								jq(this).parents('li:eq(0)').children('div:eq(0)').click();
									//jq("#listcontrol"+listitem.attr("id").substring(8)).click();
								}
						}
						// TODO : set up for insert
						/*
						 * questionnairenumber from #questionnairenumber
						 * questionId from #qid sequenceNumber from
						 * jq(this).parents('li:eq(0)').siblings().size() ?
						 */
						// jq(listitem).parents('ul:eq(0)').parents('li:eq(0)').size()
						// == 0 : check whetehr it is at the top level
						var parentNum;
						if (jq(listitem).parents('ul:eq(0)').parents('li:eq(0)')
								.size() == 0) {
							parentNum = 0;
						} else {
							// alert("parents li
							// "+jq(listitem).parents('ul:eq(0)').parents('li:eq(0)').attr("id"));
							parentNum = jq(
									"#qnum"
											+ jq(listitem).parents('ul:eq(0)')
													.parents('li:eq(0)').attr(
															"id").substring(8))
									.attr("value");
						}
						jq("#qnum" + jq(listitem).attr("id").substring(8)).attr(
								"value", jq("#questionNumber").attr("value"));
						var qid = jq("#newqid" + jq(this).attr("id").substring(5))
								.attr("value");
						jq("#qid" + jq(listitem).attr("id").substring(8)).attr(
								"value", qid);

						var seqnum = Number(jq(listitem).siblings().size()) + 1;
						if (radioVal == 'sibling') {
							//var num = Number(jq("#qseq"+jq(this).attr("id").substring(5)).attr("value"))+1;
							
						    seqnum = Number(jq("#qseq"+jq(this).attr("id").substring(5)).attr("value"))+1;
						   // seqnum = num;
							//alert(seqnum+"-"+jq(this).attr("id"));
						    var nextseq = seqnum +1;
						    var nextitem = jq(listitem).next();
						    // update seq for the siblings after the new node
						    while (nextitem.size() > 0) {
								var splitq = jq("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value").split("#f#");
							    var tmpstr = splitq[0] +"#f#" +splitq[1] 
						        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
						        splitq[7] +"#f#" +nextseq +"#f#" +splitq[9] +"#f#" +splitq[10] ;
							jq("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value",tmpstr);
//							    jq("#"+jqprefix + nextitem.attr("id").substring(8) + "\\]\\.questionSeqNumber").attr("value",nextseq);
						    	jq("#qseq" + nextitem.attr("id").substring(8)).attr("value", nextseq++);	
						    	nextitem = nextitem.next();
						    }	
						}    
						
						var qnum = jq("#questionNumber").attr("value");
						jq("#qseq" + jq(listitem).attr("id").substring(8)).attr("value", seqnum);
						jq("#questionNumber").attr("value",
								Number(jq("#questionNumber").attr("value")) + 1)

								idx = jq(listitem).attr("id").substring(8);		
    var tmpstr = "" +"#f#" +jq('#document\\.newMaintainableObject\\.businessObject\\.questionnaireRefId').attr("value") 
          +"#f#" +qid +"#f#" +qnum +"#f#" +parentNum +"#f#" +"N" +"#f#" +"" +"#f#" +
          "" +"#f#" +seqnum +"#f#" +"1" +"#f#" +"N" ;
	jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);

								
					}
					return false;
				});

	tdtmp = jq('<td style="border:none; width:30px; text-align:center;"></td>');
	image.appendTo(tdtmp);

	tdtmp.appendTo(trtmp);
	trtmp.appendTo(tbl95);
	tdtmp = jq('<td/>');
	tbl95.appendTo(tdtmp);
	return tdtmp;

}

/*
 * set up child to parent requirement table
 */
function getRequirementDisplayTable(curidx) {
	var tbl154 = jq('<table width="100%" cellpadding="0" cellspacing="0" style="border-top:#E4E3E4 solid 1px;">');
	var trtmp = jq('<tr></tr>');
	var thtmp = jq('<th style="background:#939393;height:18px;color:#FFFFFF;text-align:left;padding-left:4px;" colspan="3">');
	
	thtmp.html("Requirements for Display");
	var hsdiv = "#HSReqdiv" + curidx;
	var image = jq(
			'<img src="kr/images/tinybutton-hide.gif" alt="show/hide this panel" title="show/hide this panel"  style="width:45px; height:15px; border:none; cursor:pointer; padding:2px; vertical-align:middle;" />')
			.attr("id", "HSReqcontrol" + curidx).toggle(function() {
				jq(hsdiv).slideDown(400);
				jq(this).attr("src", "kr/static/images/tinybutton-hide.gif");
			}, function() {
				jq(hsdiv).slideUp(200);
				jq(this).attr("src", "kr/static/images/tinybutton-show.gif");
			});
	jq(image).click(); // if don't do this, then it needs to be clicked twice
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

	var trtmp = jq('<tr></tr>');
	var thtmp = jq('<th style="text-align:center; width:150px;"></th>').html(
			"Add");
	thtmp.appendTo(trtmp);
	tdtmp = jq('<td class="content_info" style="text-align:center;"></td>')
			.html("Parent Response ");
	// alert("response options "+responseOptions.html()+"-"+i);
	var respclone = responseOptions.clone(true);
	respclone.attr("id", "parentResponse" + curidx).appendTo(tdtmp);
	tdtmp.appendTo(trtmp);
	tdtmp = jq('<td class="content_info" style="text-align:center;"></td>')
			.html("Value:");
	jq('<input type="text" size="25" />').appendTo(tdtmp);
	tdtmp.appendTo(trtmp);
	tdtmp = jq('<td class="content_info" class="content_white" style="width:65px; text-align:center;"></td>');
	var image = jq(
			'<input name="addrequirement" src="kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" type="image" />')
			.attr("id", "addrequirement" + curidx)
			.click(
					function() {
						var idx = jq(this).attr("id").substring(14);
						var response = jq(this).parents('tr:eq(0)').children(
								'td:eq(0)').children('select:eq(0)').attr(
								"value");
						var value = jq(this).parents('tr:eq(0)').children(
								'td:eq(1)').children('input:eq(0)').attr(
								"value");
						if (okToAddRequirement(response, value, idx)) {
							/*
							 * var opDesc; if (sequence == 1) { opDesc =
							 * "Current Requirements:"; } else { opDesc =
							 * opArray[operator]; }
							 */
							var newResponse = getRequirementDeleteRow(
									responseArray[response], value, idx);
							newResponse.appendTo(jq(this).parents('div:eq(0)')
									.children('table:eq(0)').children('tbody'));
							var idx = jq(this).parents('li:eq(0)').attr("id")
									.substring(8);
							var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
						    var tmpstr = splitq[0] +"#f#" +splitq[1] 
					        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +"Y" +"#f#" +response +"#f#" +
					        value +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] ;
						jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
							jq(this).parents('tr:eq(0)').remove();
						}
						// }
						return false;
					});

	image.appendTo(tdtmp);
	tdtmp.appendTo(trtmp);
	return trtmp;
}

/*
 * check if required fields entered for requirement to add Also, some basic
 * validation of number and date
 */
function okToAddRequirement(response, value, idx) {
	jq("#qnaireid"+curi)
	var stidx = jq("#qnaireid"+idx).parents('ul:eq(0)').parents('li:eq(0)').attr("id").substring(8);
	var splitq = jq("#question"+stidx).attr("value").split("#f#");
	var qtypeid = splitq[1];
	//lookup 
	if (qtypeid == 6 && splitq[5] > 0) {
		qtypeid = splitq[5];
	}
//	alert (stidx+" -"+qtypeid);

	var valid = false;
	if (value == '') {
		alert("Please enter a value");
	} else if (response == 0) {
		alert("Please select a response");
	} else if (!isValidBranchingCondition(qtypeid, response)) {
		alert("Invalid Branching condition");
	} else if (response >= 5 && response <= 10 && isNaN(value)) {
		alert("Value must be a number");
	} else if (response > 10 && !isDate(value, 'MM/dd/yyyy')) {
		alert("Not a Valid Date (mm/dd/yyyy)");
	} else {
		valid = true;
	}
	return valid;
}

function isValidBranchingCondition(parentQntypeId, response) {
	var valid = true;
	if (response < 5 && (parentQntypeId == 3 || parentQntypeId == 4)) {
		// yes/no question
		valid = false;
	} else if (response >= 5 && response <= 10 && parentQntypeId != 3) {
		// number
		valid = false;
	} else if (response > 10 && parentQntypeId != 4) {
		// date 
		valid = false;
	}
	// no validation for lookup because there is not lookup return type
	return valid;
}
/*
 * set the newly added requirement row with 'delete' button
 */
function getRequirementDeleteRow(response, value, curidx) {
	var trtmp = jq('<tr></tr>');
	var thtmp = jq('<th style="text-align:left; border-top:none; width:150px;">')
			.html("Current Requirements:");
	thtmp.appendTo(trtmp);
	var tdtmp = jq('<td style="text-align:left; border-top:none;">').html(
			response + " : " + value);
	tdtmp.appendTo(trtmp);
	tdtmp = jq('<td class="content_white" style="text-align:center; border-top:none; width:65px;">');
	var image = jq(
			'<input src="kr/static/images/tinybutton-delete1.gif"  style="border:none;" alt="delete" type="image" />')
			.attr("id", "deletereq" + curidx).click(
					function() {
						// alert("This would delete this requirement."
						// + jq(this).parents('tr:eq(0)').next().size());
						var idx = jq(this).parents('li:eq(0)').attr("id")
								.substring(8);
						var splitq = jq("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
					    var tmpstr = splitq[0] +"#f#" +splitq[1] 
				        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +"N" +"#f#" +"" +"#f#" +
				        "" +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] ;
					jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
						
						getAddRequirementRow(idx).appendTo(
								jq(this).parents('tr:eq(0)').parents(
										'tbody:eq(0)'));
						jq(this).parents('tr:eq(0)').remove();
						return false;
					});
	if (jq("#readOnly").attr("value") != 'true') {
	image.appendTo(tdtmp);
	}
	tdtmp.appendTo(trtmp);
	return trtmp;

}

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
	if (nodeIndex == -1) {
		lookupType = "multivalue";
	} else if (nodeIndex == -2) {
		lookupType = "replace";
	} else {
		lookupType = "single";
	}
	//alert("nodeidx "+nodeIndex)
	var winPop = window.open(extractUrl + "/questionLookup.do?nodeIndex="
			+ nodeIndex + "&lookupType=" + lookupType + "&anchor=topOfForm",
			"_blank", "width=1000, height=800, scrollbars=yes");

}

/*
 * This is called by pop window, it returns the selected question.
 */
function returnQuestion(newQuestionId, newQuestion, newQuestionTypeId,newQuestionSequence,displayedAnswers,maxAnswers,answerMaxLength
		,nodeIndex) {
	// TODO : these need to be defined in 'input' tag, otherwise, the value set
	// will not stuck.
	// questionid, description, and typeid returned from question lookup.
	// alert("qid "+ nodeIndex+"-"+newQuestionId);
	jq("#newqid" + nodeIndex).attr("value", newQuestionId);
	jq("#newqdesc" + nodeIndex).attr("value", newQuestion);
	jq("#newqtypeid" + nodeIndex).attr("value", newQuestionTypeId);
	jq("#newqvers" + nodeIndex).attr("value", newQuestionSequence);
	jq("#newqdispans" + nodeIndex).attr("value", displayedAnswers);
	jq("#newqmaxans" + nodeIndex).attr("value", maxAnswers);
	jq("#newqmaxlength" + nodeIndex).attr("value", answerMaxLength);
	 //alert("qid "+ nodeIndex+"-"+jq("#newqdesc" + nodeIndex).attr("value")+"-"+newQuestion);
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
	// qid/desc/qtypeid/qseqnum
	var parentnum = 0;
	var parentidx = 0;
	var firstidx = -1;
	var initgroup = groupid;
	if (jq(".group" + groupid).size() >= 20) {
		initgroup++;
	}
	for ( var k = 0; k < questions.length; k++) {
		var field = questions[k].split("#f#");
		curi++;
		var parenturl = jq('#example');
		// alert(questions[k]);
		var listitem = getQuestionNew(field[1], field[2], field[3], field[4], field[5], field[6],'false');
		var ultag = jq('<ul></ul>');
		ultag.appendTo(listitem);
		var idx = listitem.attr("id").substring(8);
		// listitem.appendTo('ul#example');
		// last one no 'move dn'
		if (firstidx == -1) {
			firstidx = idx;
		}

		listitem.appendTo(jq(parenturl));
		// also need this to show 'folder' icon
		jq('#example').treeview( {
			add : listitem
		});
		// TODO : try grouping
		addToGroup(listitem);

		// alert(jq(listitem).parents('ul:eq(0)').size());
		if (jq(listitem).parents('ul:eq(0)').children('li').size() == 1) {
			jq("#moveup" + idx).hide();
			jq("#movedn" + idx).hide();
		} else {
			// alert("prev "+listitem.prev().attr("id"));
			jq("#movedn" + idx).hide();
			if (listitem.prev().size() > 0) {
				jq("#movedn" + listitem.prev().attr("id").substring(8)).show();
			}
		}

		// TODO : set up for insert
		/*
		 * questionnairenumber from #questionnairenumber questionId from #qid
		 * sequenceNumber from jq(this).parents('li:eq(0)').siblings().size() ?
		 */

		jq("#qid" + jq(listitem).attr("id").substring(8)).attr("value", field[0]);

		var seqnum = Number(jq(listitem).siblings().size()) + 1;
		jq("#qseq" + jq(listitem).attr("id").substring(8)).attr("value", seqnum);
		var qnum = jq("#questionNumber").attr("value");
		jq("#qnum" + jq(listitem).attr("id").substring(8)).attr("value", qnum);
		jq("#questionNumber").attr("value",
				Number(jq("#questionNumber").attr("value")) + 1)

    var tmpstr = "" +"#f#" +jq('#document\\.newMaintainableObject\\.businessObject\\.questionnaireRefId').attr("value") 
          +"#f#" +field[0]+"#f#" +qnum +"#f#" +parentnum +"#f#" +"N" +"#f#" +"" +"#f#" +
          "" +"#f#" +seqnum +"#f#" +"1" +"#f#" +"N" ;
	jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
				
				

	} // end for to set up questions
	/*
	 * TODO : Following clone code is just a hack
	 * the last one does not have the "+" icon displayed.  so use this
	 * to add the last one twice, then remove the clone one.  so, the last one looks like OK.
	 * Should look into 'class' property to fix this hack
	 */
	var cloneNode = jq("#qnaireid"+curi).clone(true);
	cloneNode.appendTo(jq('#example'));
	jq('#example').treeview( {
		add : cloneNode
	});
	cloneNode.remove();
	
	// alert(curgroup + "-" + initgroup + "-" + firstidx + "-" + groupid)
	jq(".group" + curgroup).hide();
	curgroup = initgroup;
	jq(".group" + curgroup).show();
	while (initgroup++ < groupid) {
		jq(".group" + initgroup).hide();
	}

	jq("#listcontrol" + firstidx).click();
	
	if (groupid > 0) {
		if (curgroup == groupid) {
		    jq("#nextGroup").hide();
		} 
		if (curgroup < groupid) {
		    jq("#nextGroup").show();
		} 
		if (curgroup == 0) {
		    jq("#nextGroup").show();
		    jq("#prevGroup").hide();
		}
		if (curgroup > 0) {
		    jq("#prevGroup").show();
		}
	}	

}// end returnquestionlist

/*
 * get question type description for display
 */
function getQnTypeDesc(qtypeid, idx) {
	 //alert("gettypedesc "+qtypeid);
	var splitq = jq("#question"+ idx).attr("value").split("#f#");
    var qdispans= splitq[3];
    var qansmax= splitq[4];
    var qmaxlength= splitq[5];

	var divtmp = null;
	switch (Number(qtypeid)) {
	// has to use Number(qtypeid)
	case 1:
		divtmp = jq('<div id="responsetypeYesNo1b" class="responsetypediv1b">')
				.html(
						jq('<p>The user will be presented with the following radio buttons: Yes, No.<br />Only one selection is possible.<br />A selection is required.</p>'));
		break;
	case 2:
		divtmp = jq('<div id="responsetypeYesNoNA1b" class="responsetypediv1b">')
				.html(
						jq('<p>The user will be presented with the following pulldown: Yes, No, Not Applicable.<br />Only one selection is possible.<br />A selection is required.</p>'));
		break;
	case 3:
		divtmp = jq('<div id="responsetypeNumber1b" class="responsetypediv1b">')
				.html(
						jq('<p>The user will be presented with '+qdispans+' text box.<br />The entered value will be validated requiring a number only.<br />The maximum length of the number in characters is '+qmaxlength+'.<br />The number of possible answers is '+qansmax+'. </p>'));
		break;
	case 4:
		divtmp = jq('<div id="responsetypeDate1b" class="responsetypediv1b">')
				.html(
						jq('<p>The user will be presented with '+qdispans+' text boxes.<br />The entered value will be validated for a date in MM/DD/YYYY format.<br />A response is required for each text box.</p>'));
		break;
	case 5:
		divtmp = jq('<div id="responsetypeText1b" class="responsetypediv1b">')
				.html(
						jq('<p>The user will be presented with '+qdispans+' text areas.<br />The number of possible answers is '+qansmax+'.<br />Maximum length of each response in characters: '+qmaxlength+'.</p>'));
		break;
//	case 6:
//		divtmp = jq(
//				'<div id="responsetypeDropdown1b" class="responsetypediv1b">')
//				.html(
//						jq('<p>The user will be presented with a dropdown of options.<br />Only one selection is possible.<br />A selection is required.</p> Possible answers are:<br />1. One Fish<br />2. Two Fish<br />3. Red Fish<br />4. Blue Fish'));
//		break;
//	case 7:
//		divtmp = jq(
//				'<div id="responsetypeCheckbox1b" class="responsetypediv1b">')
//				.html(
//						jq('<p>The user will be presented with 4 checkboxes.<br />At least one selection is required.<br />Up to 4 selections are allowed.</p>Possible answers are:<br />1. One Byte<br />2. Two Bites<br />3. Red Light<br />4. Green lights'));
//		break;
	case 6:
		divtmp = jq('<div id="responsetypeSearch1b" class="responsetypediv1b">')
				.html(
						jq('<p>The user will be presented with the ability to search for: '+qdispans+'.<br />The field to return is: '+qmaxlength+'.<br />The number of possible returns is '+qansmax+'.</p>'));
		break;
	default:
		divtmp = jq('<div id="responsetypeSearch1b" class="responsetypediv1b">')
				.html(jq('<p>Unknown</p>'));
	}
	return divtmp;
}



/*
 * add list item to group for paging
 */
function addToGroup(listitem) {
	// TODO : try grouping
	if (jq(".group" + groupid).size() >= 20) {
		groupid++;
	}
	var currentClass = jq(listitem).attr("class");
	jq(listitem).attr("class", "group" + groupid +" " + currentClass);
	// jq(listitem).addClass("group" + groupid);
	if (curgroup != groupid) {
		jq(listitem).hide();
	}

}

/*
 * in the case of move up/down; node may be moved to different group (page)
 */
function swapGroupId(curNode, nextNode) {
	// class may like "group0 expandable ..", the last item has one more item
	var curclass = jq(curNode).attr("class");
	var nextclass = jq(nextNode).attr("class");
	jq(curNode).attr("class", nextclass);
	jq(nextNode).attr("class", curclass);
	// alert(curclass+"-"+nextclass);
	if (curclass.substring(0, 7).trim() != nextclass.substring(0, 7).trim()) {
		jq(curNode).hide();
		jq(nextNode).show();
	}
//	if (curclass != nextclass) {
//		jq(curNode).attr("class", nextclass);
//		jq(nextNode).attr("class", curclass);
//	}
}

/*
 * adjust group if list item is removed
 */
function adjustGroup(idx) {
	//alert(idx + "-" + groupid + "-" + curgroup )
	while (idx < groupid) {
		idx1 = Number(idx) + 1;
		node = jq(".group" + idx1 + ":eq(0)");
		nodeclass = jq(node).attr("class"); // may have multiple classes
		// specified
		nodeclass = nodeclass.replace("group" + idx1, "group" + idx);
		// alert(idx + "-" + groupid + "-" + curgroup + "-"
		// + jq(".group" + idx1).size() + nodeclass);
		jq(node).attr("class", nodeclass);
		if (curgroup == idx) {
			// alert("show");
			jq(node).show();
		}
		idx++;
		if (idx == groupid) {
			// Note : size =1 because node is not removed yet
			if (jq(".group" + idx).size() == 1) {
				groupid--;
			}
		}
		// alert("loop back " + idx + "<" + groupid);
	}
	//alert(idx +"-"+groupid+"-"+curgroup)
	if (idx == groupid) {
		if (jq(".group" + idx).size() == 1) {
			groupid--;
		}
	}
	
	if (curgroup > groupid) {
		jq(".group" + groupid).show();
		curgroup = groupid;
		jq("#nextGroup").hide();
	}	

	if (groupid > 0) {
		if (curgroup == groupid) {
		    jq("#nextGroup").hide();
		} 
		if (curgroup < groupid) {
		    jq("#nextGroup").show();
		} 
		if (curgroup == 0) {
		    jq("#nextGroup").show();
		    jq("#prevGroup").hide();
		}
		if (curgroup > 0) {
		    jq("#prevGroup").show();
		}
	} else {
	    jq("#nextGroup").hide();
	    jq("#prevGroup").hide();
	}
}

/*
 * adjust group if list item is inserted
 */
function adjustGroupDown() {
	
	var idx = curgroup;
	
	while (idx < groupid  && jq(".group"+idx).size() > 20) {
		var idx1 = Number(idx) + 1;
		node = jq(".group" + idx + ":eq(20)");
		nodeclass = jq(node).attr("class"); // may have multiple classes
		// specified
		nodeclass = nodeclass.replace("group" + idx, "group" + idx1);
		jq(node).attr("class", nodeclass);
		if (curgroup == idx) {
			// alert("show");
			jq(node).hide();
		}
		idx++;
		// alert("loop back " + idx + "<" + groupid);
	}

	if (idx == groupid) {
		if (jq(".group" + idx).size()> 20) {
			groupid++;
			var node = jq(".group" + idx + ":eq(20)");
			var nodeclass = jq(node).attr("class"); // may have multiple classes
			// specified
			nodeclass = nodeclass.replace("group" + idx, "group" + groupid);
			jq(node).attr("class", nodeclass);
			if (curgroup == idx) {
				// alert("show");
				jq(node).hide();
			}
			if (curgroup == (groupid-1)) {
				jq("#nextGroup").show();
			}	

		}
	}
	
}


/* integrate with edit, new */
// -- should be shared
var moduleCodes = [ 'select', 'Award', 'Institute Proposal',
		'Development Proposal', 'Subcontracts', 'Negotiations', 'Person',
		'IRB', 'Annual Coi Disclosure' ];
// TODO : initial submodule codes.  should come from DB
var subModuleCodes = new Array(8);
  for (i = 0; i < subModuleCodes.length; ++ i) {
	  subModuleCodes [i] = new Array(5);
  }
  subModuleCodes[0][0] = '';
  subModuleCodes[3][0] = '';
  subModuleCodes[7][0] = '';
  subModuleCodes[3][1] = 'Proposal Budget';
  subModuleCodes[7][1] = 'Amendment / Renewal';
  subModuleCodes[7][2] = 'Protocol Submission';
  subModuleCodes[7][3] = 'Renewal';
  subModuleCodes[7][4] = 'Amendment';
  subModuleCodes[3][2] = 'S2S Questionnaires';
  subModuleCodes[3][3] = 'Proposal Person Certification';
	  
var opArray = [ 'select', 'and', 'or' ];
var responseArray = [ 'select', 'Contains text value', 'Begins with text', 'Ends with text', 'Matches text',
		'Less than number', 'Less than or equals number', 'Equals number', 'Not Equal to number',
		'Greater than or equals number', 'Greater than number', 'Before date',
		'After date' ];
var questionType = [ 'select', 'Yes/No', 'Yes/No/NA', 'Number', 'Date', 'Text',
		'Lookup' ];

var responseOptions = jq('<select name="CustomData"></select>');
jq('<option value="0" selected="selected">select</option>').appendTo(
		responseOptions);
jq('<option value="1">Contains text value</option>').appendTo(responseOptions);
jq('<option value="2">Begins with text</option>').appendTo(responseOptions);
jq('<option value="3">Ends with text</option>').appendTo(responseOptions);
jq('<option value="4">Matches text</option>').appendTo(responseOptions);
jq('<option value="5">Less than number</option>').appendTo(responseOptions);
jq('<option value="6">Less than or equals number</option>').appendTo(
		responseOptions);
jq('<option value="7">Equals number</option>').appendTo(responseOptions);
jq('<option value="8">Not Equal to number</option>').appendTo(responseOptions);
jq('<option value="9">Greater than or equals number</option>').appendTo(
		responseOptions);
jq('<option value="10">Greater than number</option>').appendTo(responseOptions);
jq('<option value="11">Before date</option>').appendTo(responseOptions);
jq('<option value="12">After date</option>').appendTo(responseOptions);

// TODO : currently this one is not working copied to questionnairequestion.jsp
jq("#addUsage")
		.click(function() {
			// TODO : 1 header and one 'add' row, so has 2 more
			if (jq("#newQuestionnaireUsage\\.moduleItemCode").attr("value") == '') {
				alert("Please select a module");
			} else if (jq("#newQuestionnaireUsage\\.questionnaireLabel").attr("value") == '') {
				alert("Please enter Label");
			} else  if (isDuplicateUsage(jq("#newQuestionnaireUsage\\.moduleItemCode").attr("value"),jq("#newQuestionnaireUsage\\.moduleSubItemCode").attr("value"), qnversion)) {
				alert("Module is already added");
			} else {	
				trtmp = jq('<tr/>').attr("id", "usage" + ucount);
				thtmp = jq('<th class="infoline"/>').html(ucount);
				thtmp.appendTo(trtmp);
				tdtmp = jq('<td align="left" valign="middle">')
						.html(
								moduleCodes[jq(
										"#newQuestionnaireUsage\\.moduleItemCode")
										.attr("value")]);
				modulecode = jq('<input type="hidden"/>').attr(
						"value",
						jq("#newQuestionnaireUsage\\.moduleItemCode").attr(
								"value"));
				modulecode.prependTo(tdtmp);
				tdtmp.appendTo(trtmp);
				tdtmp = jq('<td align="left" valign="middle">')
				.html(
						subModuleCodes[jq(
						"#newQuestionnaireUsage\\.moduleItemCode")
						.attr("value")][jq(
								"#newQuestionnaireUsage\\.moduleSubItemCode")
								.attr("value")]);
				subModulecode = jq('<input type="hidden"/>').attr(
						"value",
						jq("#newQuestionnaireUsage\\.moduleSubItemCode").attr(
								"value"));
				subModulecode.prependTo(tdtmp);

				tdtmp.appendTo(trtmp);
				var radioChecked = jq("#newQuestionnaireUsage\\.mandatory").attr('checked');
                var mandatoryValue = "No";
				if (radioChecked) {
	                mandatoryValue = "Yes";
				}
				tdtmp = jq('<td align="left" valign="middle">').html(mandatoryValue);
				tdtmp.appendTo(trtmp);

				tdtmp = jq('<td align="left" valign="middle">').html(
						jq("#newQuestionnaireUsage\\.questionnaireLabel").attr(
								"value"));
				tdtmp.appendTo(trtmp);
				tdtmp = jq('<td align="left" valign="middle">').html(qnversion);
				tdtmp.appendTo(trtmp);
				inputtmp = jq(
						'<input type="image" id="deleteUsage" name="deleteUsage" title="Delete Usage" src="static/images/tinybutton-delete1.gif" class="tinybutton">')
						.attr("id", "deleteUsage" + ucount).click(function() {
								shiftUsage(jq(this).attr("id").substring(11));
								ucount--;
								jq("#utr"+ucount).remove();
								// TODO : delete usage also need to update 'item
								// number' in the first column
								curnode = jq(this).parents('tr:eq(0)');
								while (curnode.next().size() > 0) {
									curnode = curnode.next();
									// alert(Number(curnode.children('th:eq(0)').html())-1);
									curnode.children('th:eq(0)').html(
											Number(curnode.children('th:eq(0)')
													.html()) - 1)
								}
								jq(this).parents('tr:eq(0)').remove();
								return false;
							});
				tdtmp = jq('<td align="left" valign="middle">');
				jq('<div align="center">').html(inputtmp).appendTo(tdtmp);
				tdtmp.appendTo(trtmp);
				trtmp.appendTo(jq("#usage-table"));
		        // usage hidden fields
		        var hidtr = jq('<tr id = "utr" name = "utr"/>').attr("id","utr"+ucount).attr("name", "utr"+ucount);
		        var hidtd = jq('<td colspan="2"/>');
		        // question id for this node
		        
		        getUsageHidden("questionnaireUsageId", "").appendTo(hidtd);
		        getUsageHidden("moduleItemCode", jq("#newQuestionnaireUsage\\.moduleItemCode").attr("value")).appendTo(hidtd);
		        getUsageHidden("moduleSubItemCode", jq("#newQuestionnaireUsage\\.moduleSubItemCode").attr("value")).appendTo(hidtd);
                
		        getUsageMandatoryHiddenTag(jq("#newQuestionnaireUsage\\.mandatory").attr("checked")).appendTo(hidtd);
		        //getUsageHidden("mandatory", jq("#newQuestionnaireUsage\\.mandatory").attr("value")).appendTo(hidtd);
		        getUsageHidden("questionnaireLabel", jq("#newQuestionnaireUsage\\.questionnaireLabel").attr("value")).appendTo(hidtd);
		        getUsageHidden("questionnaireSequenceNumber", qnversion).appendTo(hidtd);
		        getUsageHidden("ruleId", "0").appendTo(hidtd);
		        getUsageHidden("versionNumber", "1").appendTo(hidtd);
		        getUsageHidden("questionnaireRefIdFk", jq('#document\\.newMaintainableObject\\.businessObject\\.questionnaireRefId').attr("value")).appendTo(hidtd);
		        
		        hidtd.appendTo(hidtr);
		        hidtr.hide(); // FF rendering issue. If not hided, then 'line' will be
		        // drawn at the bottom of the table for each Q hidden row
		        hidtr.appendTo(jq("#usage-table"));
				ucount++;

			   }// end if-then-else
				return false;
			});

/*
 * search icon click function at the root level.  This will result in multi-value lookup.
 */
jq("#rootSearch").click(function() {
	// alert(jq(this).parents('li:eq(0)').attr("id"));
		// TODO : IE problem. after the node is moved up or down, then the "id"
		// of the image got lost.
		// so, before figuring a solution, use this alternative to fin parents
		// id.
		checkToAddQn(-1);
		return false;
	});

function shiftUsage(uidx) {
	var k = uidx;
	while (k < (ucount -1)) {
		//alert(ucount+"-"+(k-1))
		jq("#"+juprefix+ (k-1)+"\\]\\.questionnaireUsageId").attr("value",jq("#"+juprefix+k+"\\]\\.questionnaireUsageId").attr("value"));
		jq("#"+juprefix+ (k-1)+"\\]\\.moduleItemCode").attr("value",jq("#"+juprefix+k+"\\]\\.moduleItemCode").attr("value"));
		jq("#"+juprefix+ (k-1)+"\\]\\.moduleSubItemCode").attr("value",jq("#"+juprefix+k+"\\]\\.moduleSubItemCode").attr("value"));
		jq("#"+juprefix+ (k-1)+"\\]\\.mandatory").attr("checked",jq("#"+juprefix+k+"\\]\\.mandatory").attr("checked"));
		jq("#"+juprefix+ (k-1)+"\\]\\.questionnaireLabel").attr("value",jq("#"+juprefix+k+"\\]\\.questionnaireLabel").attr("value"));
		jq("#"+juprefix+ (k-1)+"\\]\\.questionnaireSequenceNumber").attr("value",jq("#"+juprefix+k+"\\]\\.questionnaireSequenceNumber").attr("value"));
		jq("#"+juprefix+ (k-1)+"\\]\\.ruleId").attr("value",jq("#"+juprefix+k+"\\]\\.ruleId").attr("value"));
		jq("#"+juprefix+ (k-1)+"\\]\\.versionNumber").attr("value",jq("#"+juprefix+k+"\\]\\.versionNumber").attr("value"));
		jq("#"+juprefix+ (k-1)+"\\]\\.questionnaireRefIdFk").attr("value",jq("#"+juprefix+k+"\\]\\.questionnaireRefIdFk").attr("value"));
		k++;
	}	
}


/*
 * function when 'next' button is clicked. Move to next page of 20 questions
 */
jq("#nextGroup").click(function() {
	if (groupid != curgroup) {
		jq(".group" + curgroup).hide();
		if (++curgroup == groupid) {
			jq("#nextGroup").hide();
		}
		if (curgroup  == 1) {
			jq("#prevGroup").show();
		} 
		jq(".group" + curgroup).show();
		// alert(jq(".group"+curgroup+":eq(0)").attr("id"));
		jq(
				"#listcontrol"
						+ jq(".group" + curgroup + ":eq(0)").attr("id")
								.substring(8)).click();
//		jumpToAnchor('topOfForm');
	}
	return false;

});

/*
 * function when 'back' button is clicked. Move to previous page of 20 questions
 */
jq("#prevGroup").click(function() {
	// alert ("prevgroup");
	if (groupid > 0) {
		jq(".group" + curgroup).hide();
		if (--curgroup == 0) {
			jq("#prevGroup").hide();
		} 
		if (curgroup == (groupid-1)) {
			jq("#nextGroup").show();
		}	
		jq(".group" + curgroup).show();
		// alert(jq(".group"+curgroup+":eq(0)").attr("id"));
		jq(
				"#listcontrol"
						+ jq(".group" + curgroup + ":eq(0)").attr("id")
								.substring(8)).click();
	}
	return false;

});

jq("#backToTop").click(function() {
	jq(".group" + curgroup).hide();
	curgroup = 0;
	jq(".group" + curgroup).show();
	// alert(jq(".group"+curgroup+":eq(0)").attr("id"));
		jq(
				"#listcontrol"
						+ jq(".group" + curgroup + ":eq(0)").attr("id")
								.substring(8)).click();
		jumpToAnchor('topOfForm');
		return false;

	});

// This is save function when 'save' button is clicked for edit or create new
// questionnaire.
// First save questionnaire bo because 'description'

jq(document).ready(function() {

    if (jq("#maintAction").attr("value") != 'Copy') {
        jq("#globalbuttons").find('input').each(function() {
              //alert(jq(this).attr("name"));
              if (jq(this).attr("name") == 'methodToCall.route') {
                  jq(this).attr("id","route");
              } else if (jq(this).attr("name") == 'methodToCall.save') {
                  jq(this).attr("id","save");
              } else if (jq(this).attr("name") == 'methodToCall.blanketApprove') {
                  jq(this).attr("id","blanketApprove");
              } else if (jq(this).attr("name") == 'methodToCall.close') {
                  jq(this).attr("id","close");
              }
          });
        jq("#addTemplate").find('input').each(function() {
            //alert(jq(this).attr("name"));
            if (jq(this).attr("name") == 'methodToCall.addTemplate') {
                jq(this).attr("id","add");
            }  
        });

    }
    if (jq("#maintAction").attr("value") == 'Edit') {
        qnversion = jq("#document\\.newMaintainableObject\\.businessObject\\.sequenceNumber").attr("value");
    } else if (jq("#maintAction").attr("value") == 'New') {
  	    qnversion = jq("#document\\.newMaintainableObject\\.businessObject\\.sequenceNumber").attr("value");
    }
	
	/*
	 * The above code and the following has to be in the document.ready block.
	 */
    
	jq("#save").click(function() {
	  //alert("save");
		return checkBeforeSubmit();
	}); // #save
  
  jq("#route").click(function() {
		return checkBeforeSubmit();
	}); // #route

  jq("#blanketApprove").click(function() {
		return checkBeforeSubmit();
  }); // #route
  jq("#close").click(function() {
		return checkBeforeSubmit();
  }); // #close
  
  jq("#add").click(function() {
		var retval = false;
	  if (jq('input[type=file]').val() == '') {
			alert("Please select a file name");
	  } else {
		    retval = true;
	  }	
		return retval;  
    }); // #add template
    // hide routelog for view because it is using edit to create new doc for view
   // alert(jq("#readOnly").attr("value") +  jq("#docStatus").attr("value") )
   if (jq("#readOnly").attr("value") == 'true' && jq("#docStatus").attr("value") == 'I') {    
       // option 1 : simply hide it
        jq("#tab-RouteLog-div").hide();
        jq("#tab-RouteLog-div").prev().hide();
       
       // option 2 : display message on route log tab
       /*
        var routemsg = jq('<div class="tab-container">')
            .attr("id", "noroutelog");
        
        var tbltmp = jq('<table width="100%" width="100%" cellpadding="0" cellspacing="0" class="datatable" />');
 
        var tbodytmp = jq('<tbody/>');
        var tr1 = jq('<tr></tr>');
        var td1 = jq('<td class="subelementcontent"></td>');
        td1.html("Route log is not available for Bootstrap data");
        tr1.html(td1);
        tbodytmp.html(tr1);
        tbltmp.html(tbodytmp);
        routemsg.html(tbltmp);
        routemsg.insertAfter(jq("#tab-RouteLog-div").children('div:eq(0)'));
        
        jq("#tab-RouteLog-div").children('div:eq(0)').hide();
       */ 
     //   jq("#tab-RouteLog-div").find('[class^=tab-container]').each(
     //   function() {       
     //        jq(this).hide(); 
     //           });
   }
   
   
  // the show/hide for question and usage panels are not from kuali framework
  // so to override the onclick here to also expand question and usage panels
  // first remove the 'onclick' attribute, then add 'click' function
  jq('input[name=methodToCall\\.showAllTabs]').attr('onclick', '').click(function() {
		expandAllTab();
		if (jq("a.questionpanelcontrol").html().indexOf("show.gif") > -1) {
            jq("a.questionpanelcontrol").click();
        }
		if (jq("a.usagepanelcontrol").html().indexOf("show.gif") > -1) {
            jq("a.usagepanelcontrol").click();
        }
        return false;
	});	
  
}); // document.ready


/*
 * some basic rule check when action buttons is clicked
 */
function checkBeforeSubmit() {
	//alert("in checkbeforesubmit")
	//var numOfQuestions =  jq('#numOfQuestions').attr("value",i);
	var qname = jq('#document\\.newMaintainableObject\\.businessObject\\.name').attr("value");
	var qnaireid = jq('#document\\.newMaintainableObject\\.businessObject\\.questionnaireRefId').attr("value");
	var qid = jq('#document\\.newMaintainableObject\\.businessObject\\.questionnaireId').attr("value");
	var docstatus = jq('#docStatus').attr("value");
	var qdescription = jq('#document\\.newMaintainableObject\\.businessObject\\.description').attr("value");
	var qisfinal = jq('#document\\.newMaintainableObject\\.businessObject\\.isFinal').attr("checked");
	var qtemplate = jq('#templateFileNameHidden').val();

	var retval = false;
	//if (jq('#newQuestionnaire\\.questionnaireId').attr("value") == '0') {
	//	// TODO : temp hack for 'edit', the first time to save,it will based on this to version
	//}	qnaireid=":"+qnaireid;
	// alert
		// ("save"+qname+qdescription+jq('#newQuestionnaire\\.isFinal').attr("checked"));
		if (qname == '') {
			alert("Questionnaire Name is required");
		} else if (qdescription == '') {
			alert("Questionnaire description is required");
		} else if (jq("#example").children('li').size() == 0) {
			alert("No question is added");	
		} else if ((qtemplate != '') && (!qtemplate.match(/\.xsl$/))) {
			alert("Template files must be of type: text/xsl");
		} else {
			
			    retval = true;
		}	// if-then-else
		return retval;  // comment for rice maint
	
}

/*
 * This method is to check whether the new coeus module is already exist. For
 * each version of questionnaire, each module can only be added once.
 */
function isDuplicateUsage(moduleitemcode, modulesubitemcode, vers) {
	var isduplicate = false;
	var k =0;
	jq("#usage-table").children('tbody:eq(0)').children('tr').each(
	  function() {
        if (k++ > 0 && jq(this).children('td:eq(0)').children('input:eq(0)').attr("value") == moduleitemcode && jq(this).children('td:eq(1)').children('input:eq(0)').attr("value") == modulesubitemcode && jq(this).children('td:eq(4)').html() == vers) {
        	isduplicate = true;
        }   
	});
	return isduplicate;
	
}
// -- end should be shared

/*
 * load question when page is initially loaded
 */
function loadQuestion() {
    var qlen = questions.length;
    var rootidx;
    var pnum0 = 0;
    var nodecount = 0;
    var refid = jq('#document\\.newMaintainableObject\\.businessObject\\.questionnaireRefId').attr("value");
    for ( var k = 0; k < qlen; k++) {
        var curq = questions[k];
        // alert("parent 0 "+k+"-"+curq+pnum0+"-"+nodecount+"-"+qlen);
        if (curq.indexOf("parent-") == 0) {
        } else {

            var field = curq.split("#f#");
            var parentnum = field[8];
            curi++;
            var parenturl;
            var ischild = 'false';
            if (parentnum == 0) {
                parenturl = jq('#example');
                pnum0++;
                rootidx = curi;

            } else {
                if (field[2] == 1) { // the first children
                    parentidx = curi - 1;
                } else {
                    for ( var l = rootidx; l <= curi - 1; l++) {
                        if (jq("#qnum" + l).attr("value")) {
                            if (jq("#qnum" + l).attr("value") == parentnum) {
                                parentidx = l;
                            }
                        }
                    }
                }
                parenturl = jq("#qnaireid" + parentidx).children('ul:eq(0)');
                ischild = 'true';
            }
            nodecount++;
            newqn[curi] = field[15];
            var listitem = getQuestionNew(field[3], field[4], field[9], field[10], field[11], field[12], ischild);
            var ultag = jq('<ul></ul>');
            ultag.appendTo(listitem);
            var idx = listitem.attr("id").substring(8);
            // listitem.appendTo('ul#example');
            // last one no 'move dn'
            if (firstidx == -1) {
                firstidx = idx;
            }

            listitem.appendTo(jq(parenturl));
            // also need this to show 'folder' icon
            jq('#example').treeview( {
                add : listitem
            });

            // TODO : test idea of display page by page with hide().
            if (parentnum == 0) {
                addToGroup(listitem);
                if (groupid > 0) {
                    jq(listitem).hide();
                }
            }

            if (jq(listitem).parents('ul:eq(0)').children('li').size() == 1) {
                jq("#moveup" + idx).hide();
                jq("#movedn" + idx).hide();
            } else {
                jq("#movedn" + idx).hide();
                if (listitem.prev().size() > 0) {
                    jq("#movedn" + listitem.prev().attr("id").substring(8))
                            .show();
                }
            }

           // alert ("parent "+parentnum+"-"+field[1]+"-"+field[6]);
            if (parentnum > 0 && field[6] != 'null') {
                // alert ("add req for parent
                // "+jq("#addrequirement"+i).parents('tr:eq(0)').size());
                var newResponse = getRequirementDeleteRow(
                        responseArray[field[6]], field[7]);
                newResponse.appendTo(jq("#addrequirement" + curi).parents(
                        'div:eq(0)').children('table:eq(0)').children('tbody'));
                jq("#addrequirement" + curi).parents('tr:eq(0)').remove();
            }

            jq("#qnum" + idx).attr("value", field[5]);
            jq("#qid" + idx).attr("value", field[1]);
            jq("#qseq" + idx).attr("value", field[2]);
            // set up qdesc & qtypeid

            if (field[6] == 'null') {
                field[6] = ''
            }        
            if (field[7] == 'null') {
                field[7] = ''
            }        
            var tmpstr = field[0] +"#f#" +refid
            +"#f#" +field[1] +"#f#" +field[5] +"#f#" +field[8] +"#f#" +field[14] +"#f#" +field[6] +"#f#" +
            field[7] +"#f#" +field[2] +"#f#" +field[13] +"#f#" +"N" ;
    jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
            
        } // end if-then-else
    } // end for to set up questions

    //alert(groupid+"-grp "+curgroup);
    if (groupid == 0) {
        jq("#nextGroup").hide();
        jq("#prevGroup").hide();
    }   
    if (curgroup == 0) {
        jq("#prevGroup").hide();
    }   
    if (groupid > curgroup ) {
        jq("#nextGroup").show();
    }   

    loadcount= curi-2;  // will be used to add qq to questionnairequestions list
    //alert ("load count " +loadcount);
} // loadquestion


// ok now the load questions & usages if this is editing
if (jq("#maintAction").attr("value") != 'Copy') {
jq("#nextGroup").hide();
jq("#prevGroup").hide();
}


var editdata = document.getElementById("editData").value;
// new/edit or when 'copy' is approved, then questions/usage should be displayed
if ((jq("#maintAction").attr("value") != 'Copy' || jq("#docStatus").attr("value") == 'F') && editdata.indexOf("#;#") > -1 ) {    
    var dataarray = editdata.split("#;#");
    var idxArray = new Array(dataarray.length);
    var questions = dataarray[0].split("#q#");
// qqid/qid/seq/desc/qtypeid/qnum/cond/value
// var parentnum = 0;
    var parentidx = 0;

    loadQuestion();

// TODO : only the first question is expanded
    jq("#listcontrol" + firstidx).click();
// jq("#listcontrol"+firstidx).click();

    jq(".group1").hide();
    jq(".group2").hide();
    jumpToAnchor('topOfForm');

    if (dataarray.length > 1) {
        loadUsages(dataarray[1].split("#u#"));    
    } // check dataarray.length

} // end if editdata

function loadUsages(usages) {
    for ( var k = 0; k < usages.length; k++) {
        field = usages[k].split("#f#");
        var trtmp = jq('<tr/>').attr("id", "usage" + ucount);
        var thtmp = jq('<th class="infoline"/>').html(ucount);
        thtmp.appendTo(trtmp);
        // tdtmp = jq('<td align="left" valign="middle">').html(field[1]);
        var tdtmp = jq('<td align="left" valign="middle">').html(
                moduleCodes[field[1]]);
        var modulecode = jq('<input type="hidden"/>').attr("value", field[1]);
        modulecode.appendTo(tdtmp);
        tdtmp.appendTo(trtmp);
        var subModulecode = jq('<input type="hidden"/>').attr("value", field[4]);
        //TODO : fix here
        tdtmp = jq('<td align="left" valign="middle">').html(
        		subModuleCodes[field[1]][field[4]]);
        subModulecode.appendTo(tdtmp);
        tdtmp.appendTo(trtmp);
        var mandatoryValue = "No";
        if (field[7] == 'Y') {
            mandatoryValue = "Yes";
        }
        tdtmp = jq('<td align="left" valign="middle">').html(mandatoryValue);
        tdtmp.appendTo(trtmp);
        tdtmp = jq('<td align="left" valign="middle">').html(field[2]);
        tdtmp.appendTo(trtmp);
        // TODO : questionnaire version# will be loaded later
        //tdtmp = jq('<td align="left" valign="middle">').html(field[2]);  
        tdtmp = jq('<td align="left" valign="middle">').html(field[3]);  
        tdtmp.appendTo(trtmp);
        var inputtmp = jq(
                '<input type="image" id="deleteUsage" name="deleteUsage" title="Delete Usage" src="static/images/tinybutton-delete1.gif" class="tinybutton">')
                .attr("id", "deleteUsage" + ucount).click(
                        function() {
                                                        // alert(sqlScripts);
							    shiftUsage(jq(this).attr("id").substring(11));
								ucount--;
								jq("#utr"+ucount).remove();
                            curnode = jq(this).parents('tr:eq(0)');
                            while (curnode.next().size() > 0) {
                                curnode = curnode.next();
                                curnode.children('th:eq(0)').html(
                                        Number(curnode.children('th:eq(0)')
                                                .html()) - 1)
                            }

                            jq(this).parents('tr:eq(0)').remove();
                            return false;
                        });
        tdtmp = jq('<td align="left" valign="middle">');
        if (jq("#readOnly").attr("value") != 'true') {
        jq('<div align="center">').html(inputtmp).appendTo(tdtmp);
        } else {
            jq('<div align="center">').appendTo(tdtmp);
        }    
        tdtmp.appendTo(trtmp);
        trtmp.appendTo(jq("#usage-table"));

        // usage hidden fields
		var hidtr = jq('<tr id = "utr" name = "utr"/>').attr("id","utr"+ucount).attr("name", "utr"+ucount);
        var hidtd = jq('<td colspan="2"/>');
        // question id for this node
        if (field[0] == 'null') {
        	field[0]="";
        }    
        getUsageHidden("questionnaireUsageId", field[0]).appendTo(hidtd);
        getUsageHidden("moduleItemCode", field[1]).appendTo(hidtd);
        getUsageHidden("moduleSubItemCode", field[4]).appendTo(hidtd);
        var isChecked = false;
        if (field[7] == 'Y') {
        	isChecked = true;
        }
        getUsageMandatoryHiddenTag(isChecked).appendTo(hidtd);
        //getUsageHidden("mandatory", field[7]).appendTo(hidtd);
        getUsageHidden("questionnaireLabel", field[2]).appendTo(hidtd);
        getUsageHidden("questionnaireSequenceNumber", field[3]).appendTo(hidtd);
        getUsageHidden("ruleId", field[5]).appendTo(hidtd);
        getUsageHidden("versionNumber", field[6]).appendTo(hidtd);
        getUsageHidden("questionnaireRefIdFk", jq('#document\\.newMaintainableObject\\.businessObject\\.questionnaireRefId').attr("value")).appendTo(hidtd);
        
        hidtd.appendTo(hidtr);
        hidtr.hide(); // FF rendering issue. If not hided, then 'line' will be
        // drawn at the bottom of the table for each Q hidden row
        hidtr.appendTo(jq("#usage-table"));
        ucount++;
    }
    initucount = ucount-1 ;


} // end loadusages

function getUsageMandatoryHiddenTag(isChecked) {
    var mandatorytag = jq('<input type="checkbox" title="Mandatory" class="" style="" onblur="" onchange="" onclick="" >');
    jq(mandatorytag).attr("id",uprefix + (ucount-1) +"].mandatory");
    jq(mandatorytag).attr("name",uprefix + (ucount-1) +"].mandatory");
    if (isChecked) {
    	jq(mandatorytag).attr("checked", true);
    } else {
    	jq(mandatorytag).attr("checked", false);
    }
    jq("#document\\.newMaintainableObject\\.businessObject\\.questionnaireUsages["+ (ucount-1) +"]\\.mandatory").hide();
    return mandatorytag;
}

function getUsageHidden(name, value) {
	return jq('<input type="hidden" id = "usage" name = "usage" />').attr("id",
    		uprefix + (ucount-1)+"]."+name).attr("name", uprefix + (ucount-1)+"]."+name)
            .attr("value",value);	
}

function newVersionQuestionPop(questionRefId) {

    newQuestionWindow = window.open(extractUrlBase() +
    	                               "/" + name + "directInquiry.do?businessObjectClassName=org.kuali.kra.questionnaire.question.Question&methodToCall=start" +
    	                               "&questionRefId=" + questionRefId , 
    	                               "_blank", "width=640, height=600, scrollbars=yes");
}

function showViewFile(template) {
	if (jq("#viewTemplate[style*='none']")) {
       jq('#viewTemplate').show();
       jq('#fileNameDiv').show();
	}
	jq('#templateFileNameHidden').val(jq(template).val());
	jq('#fileNameDiv').html(jq(template).val());
    jq('#fileNameDiv').hide();
}
function replaceTemplate(image) {
   jq(image).hide();
   jq('#templateFileDiv').show();
   jq('#fileNameDiv').hide();
   jq('#viewTemplate').hide();
   
}

function moduleCodeChange(moduleCode) {
	jq.ajax( {
		url : 'maintenanceQn.do',
		type : 'POST',
		dataType : 'html',
		data : 'methodToCall=getSubModuleCodeList&moduleCode=' + jq(moduleCode).val(),
		cache : false,
		async : false,
		timeout : 1000,
		error : function() {
			alert('Error loading XML document');
		},
		success : function(xml) {
			jq(xml).find('h3').each(function() {
				var text = jq(this).html();
				jq('#submodulediv').html(jq(this).html());

				});
		}
	}); // end ajax

}

/*
 * set up the toggle function for 'requirements' and 'response' tabs' show/hide
 * This is done when the question is clicked for the first time and the maint table is retrieved by ajax call
 */
	function toggleHSControl(idx) {
		jq("#HScontrol" + idx).toggle(function() {
			       var curidx = jq(this).attr("id").substring(9);
					jq("#HSdiv"+curidx).slideDown(400);
					jq(this).attr("src", "kr/static/images/tinybutton-hide.gif");
				}, function() {
					var curidx = jq(this).attr("id").substring(9);
					jq("#HSdiv"+curidx).slideUp(200);
					jq(this).attr("src", "kr/static/images/tinybutton-show.gif");
				});
		jq("#HScontrol" + idx).click();
		
		jq("#HSReqcontrol" + idx).toggle(function() {
		       var curidx = jq(this).attr("id").substring(12);
				jq("#HSReqdiv"+curidx).slideDown(400);
				jq(this).attr("src", "kr/static/images/tinybutton-hide.gif");
			}, function() {
				var curidx = jq(this).attr("id").substring(12);
				jq("#HSReqdiv"+curidx).slideUp(200);
				jq(this).attr("src", "kr/static/images/tinybutton-show.gif");
			});
	    jq("#HSReqcontrol" + idx).click();

}
	
function hasChildren(element) {
	var $childrenCount = 0;
	jq(element).children("ul").each(function() {
		$childrenCount += jq(this).children().size();		
	});
	
	if ($childrenCount > 0) {
		return true;
	} else {
		return false;
	}
}

function clickModify(curidx) {
	//alert(curidx);
	replaceNode = curidx;
	checkToAddQn(-2);	
	return false;
}

function replaceQuestion(newQuestionId, newQuestion, newQuestionTypeId,newQuestionSequence,displayedAnswers,maxAnswers,answerMaxLength
		,nodeIndex) {

	jq("#repqid").attr("value", newQuestionId);
	jq("#repqdesc").attr("value", newQuestion);
	jq("#repqtypeid").attr("value", newQuestionTypeId);
	jq("#repqvers").attr("value", newQuestionSequence);
	jq("#repqdispans").attr("value", displayedAnswers);
	jq("#repqmaxans").attr("value", maxAnswers);
	jq("#repqmaxlength").attr("value", answerMaxLength);
	
	addForReplace(replaceNode);
}


function addForReplace(curidx) {
	//alert("replace question "+curidx);
	var idx = curidx;
	var addQn = "#addQn"+curidx
	//alert(jq("#repqdesc").attr("value")+"-"+jq("#repqdispans").attr("value"))
		if (jq("#repqdesc").attr("value") == ''
				|| jq("#repqtypeid").attr("value") == '') {
			alert("Please select a question to add");
		} else {
			curi++;

			var radioVal = 'sibling';
			var childNode = 'false';

			//alert(jq("#repqdesc").attr("value"));
			var listitem = getQuestionNew(
					jq("#repqdesc").attr("value"),
					jq("#repqtypeid").attr("value"),
					jq("#repqvers").attr("value"),
					jq("#repqdispans").attr("value"),
					jq("#repqmaxans").attr("value"),
					jq("#repqmaxlength").attr("value"),
					childNode);
			var ultag = jq('<ul></ul>');
			ultag.appendTo(listitem);
			var idx = listitem.attr("id").substring(8);
			//alert(idx);
			if (radioVal == 'sibling') {
				// alert('sibling');
				var parentUl = jq(addQn).parents('li:eq(0)').parents(
						'ul:eq(0)');
				listitem.insertAfter(jq(addQn).parents('li:eq(0)'));
				jq("#movedn" + idx).hide();
				jq("#movedn" + listitem.prev().attr("id")
									  .substring(8)).show();
				// TODO trying to group
				//alert("parent u"+parentUl.attr("id"));
				if (parentUl.attr("id") == 'example') {
					// insert after, so assume current group 
					// TODO : need to adjust group to 20/page ?
					jq(listitem).attr("class", "group" + curgroup);
					if (jq(".group"+curgroup).size() > 20) {
						adjustGroupDown(); 
					}	
					//addToGroup(listitem);
					if (curgroup == groupid) {
						jq("#nextGroup").hide();
					} else if (curgroup > 0) {
						jq("#prevGroup").show();
					} else if (groupid > 0) {
						jq("#nextGroup").show();
					}
				}
			}

			// also need this to show 'folder' icon
			jq('#example').treeview( {
				add : listitem
			});


			// TODO : set up for insert
			/*
			 * questionnairenumber from #questionnairenumber
			 * questionId from #qid sequenceNumber from
			 * jq(this).parents('li:eq(0)').siblings().size() ?
			 */
			// jq(listitem).parents('ul:eq(0)').parents('li:eq(0)').size()
			// == 0 : check whetehr it is at the top level
			var parentNum;
			if (jq(listitem).parents('ul:eq(0)').parents('li:eq(0)')
					.size() == 0) {
				parentNum = 0;
			} else {
				// alert("parents li
				// "+jq(listitem).parents('ul:eq(0)').parents('li:eq(0)').attr("id"));
				parentNum = jq(
						"#qnum"
								+ jq(listitem).parents('ul:eq(0)')
										.parents('li:eq(0)').attr(
												"id").substring(8))
						.attr("value");
			}
			//alert(parentNum);
			//alert(jq("#questionNumber").attr("value"));
			jq("#qnum" + jq(listitem).attr("id").substring(8)).attr(
					"value", jq("#questionNumber").attr("value"));
			var qid = jq("#repqid").attr("value");
			jq("#qid" + jq(listitem).attr("id").substring(8)).attr(
					"value", qid);

			var seqnum = Number(jq(listitem).siblings().size()) + 1;
			if (radioVal == 'sibling') {
				//var num = Number(jq("#qseq"+jq(this).attr("id").substring(5)).attr("value"))+1;
				
			    seqnum = Number(jq("#qseq"+jq(addQn).attr("id").substring(5)).attr("value"))+1;
			   // seqnum = num;
				//alert(seqnum+"-"+jq(this).attr("id"));
			    var nextseq = seqnum +1;
			    var nextitem = jq(listitem).next();
			    // update seq for the siblings after the new node
			    while (nextitem.size() > 0) {
					var splitq = jq("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value").split("#f#");
				    var tmpstr = splitq[0] +"#f#" +splitq[1] 
			        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
			        splitq[7] +"#f#" +nextseq +"#f#" +splitq[9] +"#f#" +splitq[10] ;
				jq("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value",tmpstr);
//				    jq("#"+jqprefix + nextitem.attr("id").substring(8) + "\\]\\.questionSeqNumber").attr("value",nextseq);
			    	jq("#qseq" + nextitem.attr("id").substring(8)).attr("value", nextseq++);	
			    	nextitem = nextitem.next();
			    }	
			}    
			
			var qnum = jq("#questionNumber").attr("value");
			jq("#qseq" + jq(listitem).attr("id").substring(8)).attr("value", seqnum);
			jq("#questionNumber").attr("value",
					Number(jq("#questionNumber").attr("value")) + 1);

			idx = jq(listitem).attr("id").substring(8);		
            var tmpstr = "" +"#f#" +jq('#document\\.newMaintainableObject\\.businessObject\\.questionnaireRefId').attr("value") 
               +"#f#" +qid +"#f#" +qnum +"#f#" +parentNum +"#f#" +"N" +"#f#" +"" +"#f#" +
               "" +"#f#" +seqnum +"#f#" +"1" +"#f#" +"N" ;
            jq("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);

					
		}
	
	    //Let's move any children to this node now
	    var liId = "li#qnaireid" + curidx;
		jq(liId).children("ul").children("li").each(function() {
			var tmpIdx = jq(this).attr("id").substring(8);
			//alert(tmpIdx);
			clickCut(tmpIdx);
			clickPaste(idx);
		});
		
		//Lets remove the original node
	    clickRemove(curidx);
	    
		return false;
	
}


function clickUpdateQuestionVersion(curidx) {
   //jq("#listcontrol"+curidx).click()
	//alert(curidx);
	jq.ajax( {
		url : 'maintenanceQn.do',
		type : 'POST',
		dataType : 'html',
		data : 'methodToCall=getQuestionCurrentVersion&qidx=' + curidx +'&questionId='+jq('#qid'+curidx).attr('value'),
		cache : false,
		async : false,
		timeout : 1000,
		error : function() {
			alert('Error loading XML document');
		},
		success : function(xml) {
			jq(xml).find('input').each(function() {
			    //alert(jq(this).attr('id') +":" + jq(this).attr('value'));
			    if (jq(this).attr('id') == 'questionId') {
					jq("#repqid").attr("value", jq(this).attr('value'));
			    } else if (jq(this).attr('id') == 'question') {
					jq("#repqdesc").attr("value", jq(this).attr('value'));
			    } else if (jq(this).attr('id') == 'questionTypeId') {
					jq("#repqtypeid").attr("value", jq(this).attr('value'));
			    } else if (jq(this).attr('id') == 'questionSequence') {
					jq("#repqvers").attr("value", jq(this).attr('value'));
			    } else if (jq(this).attr('id') == 'displayedAnswers') {
					jq("#repqdispans").attr("value", jq(this).attr('value'));
			    } else if (jq(this).attr('id') == 'maxAnswers') {
					jq("#repqmaxans").attr("value", jq(this).attr('value'));
			    } else if (jq(this).attr('id') == 'answerMaxLength') {
					jq("#repqmaxlength").attr("value", jq(this).attr('value'));
			    }
			});
			replaceNode = curidx;
			addForReplace(curidx);
		}
	}); // end ajax
	
	return false;
}

