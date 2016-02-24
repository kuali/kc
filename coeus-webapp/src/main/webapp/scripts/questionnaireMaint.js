/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
jQuery(document).ready(function() {
	jQuery.ajaxSettings.cache = false;
	jQuery("#example").treeview( {
		toggle : function() {
			// alert ("toggle "+jQuery(this).attr("id"));
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
	var question = jQuery('<li class="closed expandable"></li>').attr("id", qnaireid);
	var divId = "listcontent" + curi;

	var div62 = jQuery('<div/>');
	var linktmp = jQuery('<a style="margin-left:2px;"></a>').attr("id",
			"listcontrol" + curi).attr("name","listcontrol" + curi).html(description);
	linktmp.click(function() {
		var idx = jQuery(this).attr("id").substring(11);
		jQuery(".hierarchydetail:not(#listcontent" + idx + ")").slideUp(300);
		if (jQuery(this).parents('div:eq(0)').children('div:eq(0)').size() == 0) {
			//var vers = "1.00";
			var divtmp = getMaintTable(description, qtypeid, vers, idx, childNode);
			divtmp.appendTo(jQuery(this).parents('div:eq(0)'));
			toggleHSControl(idx);
			jQuery("#listcontent" + idx).slideToggle(300);
		}
		jQuery("#listcontent" + idx).slideToggle(300);
	});	
	linktmp.appendTo(div62);
	
	// new version question message & link
	if (jQuery("#readOnly").attr("value") != 'true' && newqn[curi] && !isNaN(newqn[curi])) {
		var outerLinkSpan = jQuery('<span style="margin-left: 10px; font-style: italic;">A new question </span>');
	    var linkNewQ = jQuery('<a style="margin-left:2px;"></a>').attr("id",
			"newqn" + curi).attr("name","newqn" + curi).html("<font color=red><u>version</u></font>");
	    linkNewQ.click(function() {
		    var idx = jQuery(this).attr("id").substring(5);
	        newQuestionWindow = window.open(extractUrlBase() +
	    	                               "/kr/directInquiry.do?businessObjectClassName=org.kuali.coeus.common.questionnaire.framework.question.Question&methodToCall=start" +
	    	                               "&id=" + newqn[idx] ,
	    	                               "_blank", "width=640, height=600, scrollbars=yes");
	    });
	    linkNewQ.appendTo(outerLinkSpan);
	    var innerLinkSpan = jQuery('<span style="margin-left:2px;"> exists. Open question to update.</span>');
	    innerLinkSpan.appendTo(outerLinkSpan);
	    outerLinkSpan.appendTo(div62);
	    
	    //linkNewQ.appendTo(div62);

	}

	div62.appendTo(question);

	sethiddenfields();

    jQuery("#question"+ curi).attr("value",description+"#f#" +qtypeid+"#f#"+vers+"#f#" +dispans+"#f#"+ansmax+"#f#" +maxlength);

	return question;

} // end addQuestion

/*
 * hidden fields : some for js function to use and others are used when post to server
 */
function sethiddenfields() {
	var hidtr = jQuery('<tr/>');
	var hidtd = jQuery('<td colspan="2"/>');
	// question id for this node
	qntag = jQuery('<input type="hidden" id = "qid" name = "qid" />').attr("id",
			"qid" + curi).attr("name", "qid" + curi);
	qntag.appendTo(hidtd);
	qntag = jQuery('<input type="hidden" id = "qseq" name = "qseq" />').attr("id",
			"qseq" + curi).attr("name", "qseq" + curi);
	qntag.appendTo(hidtd);

	qntag = jQuery('<input type="hidden" id = "qnum" name = "qnum" />').attr("id",
			"qnum" + curi).attr("name", "qnum" + curi);
	qntag.appendTo(hidtd);
		
	qntag = jQuery('<input type="hidden" id = "question" name = "question" />')
	.attr("id", "question" + curi).attr("name", "question" + curi);
    qntag.appendTo(hidtd);
    
	qntag = jQuery('<input type="hidden" id = "question" name = "question" />')
	.attr("id", "qnaireQuestions[" + curi+"]").attr("name", "qnaireQuestions[" + curi+"]");
    qntag.appendTo(hidtd);
	
	hidtd.appendTo(hidtr);
	// FF rendering issue. If not hided, then 'line' will be
	// drawn at the bottom of the table for each Q hidden row
	hidtr.hide(); 
	
	hidtr.appendTo(jQuery("#question-table"));

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
	var div64 = jQuery(' <div class="hierarchydetail" style="margin-top:2px;">')
			.attr("id", divId);
	var text = '';
	var response = '';
	var value = '';
	var ruleId = '';
	// the requirement response is also including root node, so comment out this childnode check.
//	if (childNode == 'true') {
		var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
		response = responseArray[splitq[6]];
		value = splitq[7];
		ruleId = splitq[11];
		
//	}

	jQuery.ajax( {
		url : 'maintenanceQn.do',
		type : 'POST',
		dataType : 'html',
		data : 'methodToCall=getQuestionMaintainTable&qidx=' + idx +'&questionId='+jQuery('#qid'+idx).attr('value')
		       + '&moveup=' + jQuery("#qnaireid" + idx).prev().size()+ '&movedn=' + jQuery("#qnaireid" + idx).next().size()
		       + '&childNode=' + childNode + '&response=' + response+ '&value=' + value + '&ruleId=' + ruleId + '&readOnly=' + jQuery("#readOnly").attr("value"),
		cache : false,
		async : false,
		timeout : 1000,
		error : function() {
			alert('Error loading XML document');
		},
		success : function(xml) {
			jQuery(xml).find('h3').each(function() {
				text = jQuery(this).html();
				//jQuery('#submodulediv').html(jQuery(this).html());

				});
		}
	}); // end ajax
	//alert(text)
	jQuery(div64).html(text);	
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
	 //alert(jQuery("#newqdesc"+idx).attr("value")+"-"+jQuery("#newqdispans"+idx).attr("value"))
		if (jQuery("#newqdesc" + idx).attr("value") == ''
				|| jQuery("#newqtypeid" + idx).attr("value") == '') {
			alert("Please select a question to add");
		} else {
			curi++;

			var radioVal = jQuery(
					".radioQn" + jQuery(addQn).attr("id").substring(5)
							+ ":checked").val();
			var childNode = 'true';
			if (radioVal == 'sibling'
					&& jQuery(addQn).parents('li:eq(0)').parents(
							'ul:eq(0)').attr("id") == 'example') {
				childNode = 'false';
			}
			var listitem = getQuestionNew(jQuery(
					"#newqdesc" + jQuery(addQn).attr("id").substring(5))
					.attr("value"),
					jQuery("#newqtypeid"+ jQuery(addQn).attr("id").substring(5)).attr("value"),
					jQuery("#newqvers"+ jQuery(addQn).attr("id").substring(5)).attr("value"),
					jQuery("#newqdispans"+ jQuery(addQn).attr("id").substring(5)).attr("value"),
					jQuery("#newqmaxans"+ jQuery(addQn).attr("id").substring(5)).attr("value"),
					jQuery("#newqmaxlength"+ jQuery(addQn).attr("id").substring(5)).attr("value"),
					childNode);
			var ultag = jQuery('<ul></ul>');
			ultag.appendTo(listitem);
			var idx = listitem.attr("id").substring(8);
			if (radioVal == 'sibling') {
				// alert('sibling');
				var parentUl = jQuery(addQn).parents('li:eq(0)').parents(
						'ul:eq(0)');
				listitem.insertAfter(jQuery(addQn).parents('li:eq(0)'));
				jQuery("#movedn" + idx).hide();
				jQuery(
						"#movedn"
								+ listitem.prev().attr("id")
										.substring(8)).show();
				// TODO trying to group
				// alert("parent u"+parentUl.attr("id"));
				if (parentUl.attr("id") == 'example') {
					// insert after, so assume current group 
					// TODO : need to adjust group to 20/page ?
					jQuery(listitem).attr("class", "group" + curgroup);
					if (jQuery(".group"+curgroup).size() > 20) {
						adjustGroupDown(); 
					}	
					//addToGroup(listitem);
					if (curgroup == groupid) {
						jQuery("#nextGroup").hide();
					} else if (curgroup > 0) {
						jQuery("#prevGroup").show();
					} else if (groupid > 0) {
						jQuery("#nextGroup").show();
					}
				}
			} else {
				var parentUl = jQuery(addQn).parents('li:eq(0)')
						.children('ul:eq(0)');
				listitem.appendTo(parentUl);

				// TODO : if add 2nd item, then add 'movedn' to
				// 1st
				// item. maybe use hide/show instead of 'remove'
				// "==1" is the one just added
				if (jQuery(addQn).parents('li:eq(0)')
						.children('ul:eq(0)').children('li').size() == 1) {
					jQuery("#moveup" + idx).hide();
					jQuery("#movedn" + idx).hide();
				} else {
					// alert("prev
					// "+listitem.prev().attr("id"));
					jQuery("#movedn" + idx).hide();
					jQuery(
							"#movedn"
									+ listitem.prev().attr("id")
											.substring(8)).show();
				}
			}

			// also need this to show 'folder' icon
			jQuery('#example').treeview( {
				add : listitem
			});

			//alert(childNode)
			if (childNode == 'true') {
				if (jQuery(addQn).parents('li:eq(0)').children('div:eq(0)').attr("class").indexOf("expandable") > 0) {
                      /* to force it to show the child node just added.
                       * the div 'class' is changing based on whether the node is expand or collapsed
                       */
					jQuery(addQn).parents('li:eq(0)').children('div:eq(0)').click();
						//jQuery("#listcontrol"+listitem.attr("id").substring(8)).click();
					}
			}
			// TODO : set up for insert
			/*
			 * questionnairenumber from #questionnairenumber
			 * questionId from #qid sequenceNumber from
			 * jQuery(this).parents('li:eq(0)').siblings().size() ?
			 */
			// jQuery(listitem).parents('ul:eq(0)').parents('li:eq(0)').size()
			// == 0 : check whetehr it is at the top level
			var parentNum;
			if (jQuery(listitem).parents('ul:eq(0)').parents('li:eq(0)')
					.size() == 0) {
				parentNum = 0;
			} else {
				// alert("parents li
				// "+jQuery(listitem).parents('ul:eq(0)').parents('li:eq(0)').attr("id"));
				parentNum = jQuery(
						"#qnum"
								+ jQuery(listitem).parents('ul:eq(0)')
										.parents('li:eq(0)').attr(
												"id").substring(8))
						.attr("value");
			}
			jQuery("#qnum" + jQuery(listitem).attr("id").substring(8)).attr(
					"value", jQuery("#questionNumber").attr("value"));
			var qid = jQuery("#newqid" + jQuery(addQn).attr("id").substring(5))
					.attr("value");
			jQuery("#qid" + jQuery(listitem).attr("id").substring(8)).attr(
					"value", qid);

			var seqnum = Number(jQuery(listitem).siblings().size()) + 1;
			if (radioVal == 'sibling') {
				//var num = Number(jQuery("#qseq"+jQuery(this).attr("id").substring(5)).attr("value"))+1;
				
			    seqnum = Number(jQuery("#qseq"+jQuery(addQn).attr("id").substring(5)).attr("value"))+1;
			   // seqnum = num;
				//alert(seqnum+"-"+jQuery(this).attr("id"));
			    var nextseq = seqnum +1;
			    var nextitem = jQuery(listitem).next();
			    // update seq for the siblings after the new node
			    while (nextitem.size() > 0) {
					var splitq = jQuery("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value").split("#f#");
				    var tmpstr = splitq[0] +"#f#" +splitq[1] 
			        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
			        splitq[7] +"#f#" +nextseq +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +splitq[11] ;
				jQuery("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value",tmpstr);
//				    jQuery("#"+jqprefix + nextitem.attr("id").substring(8) + "\\]\\.questionSeqNumber").attr("value",nextseq);
			    	jQuery("#qseq" + nextitem.attr("id").substring(8)).attr("value", nextseq++);	
			    	nextitem = nextitem.next();
			    }	
			}    
			
			var qnum = jQuery("#questionNumber").attr("value");
			jQuery("#qseq" + jQuery(listitem).attr("id").substring(8)).attr("value", seqnum);
			jQuery("#questionNumber").attr("value",
					Number(jQuery("#questionNumber").attr("value")) + 1);

			idx = jQuery(listitem).attr("id").substring(8);		
            var tmpstr = "" +"#f#" +jQuery('#document\\.newMaintainableObject\\.businessObject\\.id').attr("value")
               +"#f#" +qid +"#f#" +qnum +"#f#" +parentNum +"#f#" +"N" +"#f#" +"" +"#f#" +
               "" +"#f#" +seqnum +"#f#" +"1" +"#f#" +"N"+"#f#" +null ;
            jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);

					
		}
		return false;
	
}

function addrequirement(curidx) {
	var idx = curidx;
	var response = jQuery("#addrequirement"+curidx).parents('tr:eq(0)').children(
			'td:eq(0)').children('select:eq(0)').attr(
			"value");
	var value = jQuery("#addrequirement"+curidx).parents('tr:eq(0)').children(
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
		newResponse.prependTo(jQuery("#addrequirement"+curidx).parents('div:eq(0)')
				.children('table:eq(0)').children('tbody'));
		var idx = jQuery("#addrequirement"+curidx).parents('li:eq(0)').attr("id")
				.substring(8);
		var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
	    var tmpstr = splitq[0] +"#f#" +splitq[1] 
        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +"Y" +"#f#" +response +"#f#" +
        value +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +splitq[11] ;
	jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
		jQuery("#addrequirement"+curidx).parents('tr:eq(0)').remove();
	}
	// }
	return false;
	
}
function addRuleRequirement(curidx) {
	var idx = curidx;
	var response = jQuery("#addRuleRequirement"+curidx).parents('tr:eq(1)').children(
			'td:eq(0)').children('select:eq(0)').attr(
			"value");
	var value = jQuery("#addRuleRequirement"+curidx).parents('tr:eq(0)').children(
			'td:eq(0)').children('input:eq(0)').attr(
			"value");
	if (value == '') {
        alert("Please enter a value");
        return false;
	}else{
		/*
		 * var opDesc; if (sequence == 1) { opDesc =
		 * "Current Requirements:"; } else { opDesc =
		 * opArray[operator]; }
		 */
		var newResponse = getRuleEvaluationDeleteRow(value, idx);
		newResponse.appendTo(jQuery("#addRuleRequirement"+curidx).parents('div:eq(0)')
				.children('table:eq(0)').children('tbody'));
		var idx = jQuery("#addRuleRequirement"+curidx).parents('li:eq(0)').attr("id")
				.substring(8);
		var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
	    var tmpstr = splitq[0] +"#f#" +splitq[1] 
        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
        splitq[7] +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +value ;
	    jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
		jQuery("#addRuleRequirement"+curidx).parents('tr:eq(0)').remove();
	}
	// }
	return false;
	
}

function clickDeleteResponse(idx) {
	// alert("This would delete this requirement."
	// + jQuery(this).parents('tr:eq(0)').next().size());
	//var idx = jQuery(this).parents('li:eq(0)').attr("id")
	//		.substring(8);
	var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +"N" +"#f#" +"" +"#f#" +
    "" +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +splitq[11] ;
jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
	
	getAddRequirementRow(idx).prependTo(
			jQuery("#deletereq" + idx).parents('tr:eq(0)').parents(
					'tbody:eq(0)'));
	jQuery("#deletereq" + idx).parents('tr:eq(0)').remove();
	return false;

}
function clickDeleteRule(idx) {
	
	// alert("This would delete this rule requirement."
	// + jQuery(this).parents('tr:eq(0)').next().size());
	//var idx = jQuery(this).parents('li:eq(0)').attr("id")
	//		.substring(8);
	var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +"N" +"#f#" +"" +"#f#" +
    "" +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +splitq[11] ;
    jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
	
    getAddRuleRequirementRow(idx).appendTo(
			jQuery("#deleteRule" + idx).parents('tr:eq(0)').parents(
					'tbody:eq(0)'));
	jQuery("#deleteRule" + idx).parents('tr:eq(0)').remove();
	return false;

}

function clickCopy(curidx) {
//	alert("Copy node" +curidx);
	var liId = "li#qnaireid" + curidx;
	copyNode = jQuery(liId);
	//removedNode = null; // remove & cutNode should not co-exist
	cutNode = null;
	cutNodeParentCode = null;
	maxCopyNodeIdx = 0;
	return false;  // so when clicked, the page will not jump

}

function clickCut(curidx) {
	//alert("Cut node" +curidx);
	var liId = "li#qnaireid" + curidx;
	cutNode = jQuery(liId);
	//removedNode = null; // remove & cutNode should not co-exist
	copyNode = null;
	cutNodeParentCode = null;
	maxCopyNodeIdx = 0;
	return false;  // so when clicked, the page will not jump

}

function clickMoveup(curidx) {
//	alert("move node" +curidx);
	var curNode = jQuery("#moveup"+curidx).parents('li:eq(0)');

	var nextNode = jQuery("#moveup"+curidx).parents('li:eq(0)').prev();
	if (jQuery(nextNode).children('div:eq(1)').children('div:eq(0)')
			.size() == 0) {
		var nextidx = jQuery(nextNode).attr("id").substring(8);
		var childNode = 'true';
		if (jQuery(nextNode).parents('ul:eq(0)').attr("id") == 'example') {
			childNode = 'false';
		}
		var splitq = jQuery("#question"+nextidx).attr("value").split("#f#");

		var divtmp = getMaintTable(splitq[0], splitq[1],splitq[2], nextidx, childNode);
		divtmp.appendTo(jQuery(nextNode).children('div:eq(1)'));
		toggleHSControl(nextidx);
	}

	
	// jQuery(this).parents('li:eq(0)').remove();
	
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
	     cloneNode.appendTo(jQuery(nextNode).parents('ul:eq(0)'));
	     jQuery('#example').treeview( {
		    add : cloneNode
	     });
	}



	curNode.insertBefore(nextNode);
	var idx = jQuery(curNode).attr("id").substring(8);
	var seq = Number(jQuery("#qseq" + idx).attr("value")) - 1;
	jQuery("#qseq" + idx).attr("value", seq);
	var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
    splitq[7] +"#f#" +seq +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +splitq[11] ;
    jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
//    jQuery("#"+jqprefix + idx + "\\]\\.questionSeqNumber").attr("value",seq);

    jQuery("#movedn" + curNode.attr("id").substring(8)).show();
    jQuery("#moveup" + nextNode.attr("id").substring(8)).show();
//alert ("cp nn size "+curNode.next().attr("id")+"-"+nextNode.prev().size())
    if (curNode.prev().size() == 0) {
	    jQuery("#moveup" + curNode.attr("id").substring(8)).hide();
    }
    if (nextNode.next().size() == 0 || nextNode.next().attr("id") == curNode.attr("id")) {
	// not sure why this one is = 1 if it is moved to the last one, still has curnode as 'next'
	// so, move this manipulation before insert
	// this is probably caused by the clonenode added after the last node, then removed it.
	// FF's inspect element did not see it, not sure why jquery still thinks it exists.
	    jQuery("#movedn" + nextNode.attr("id").substring(8)).hide();
    }
	idx = jQuery(nextNode).attr("id").substring(8);
	seq = Number(jQuery("#qseq" + idx).attr("value")) + 1;
	jQuery("#qseq" + idx).attr("value", seq);
	var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
    splitq[7] +"#f#" +seq +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +splitq[11] ;
    jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
	swapGroupId(curNode, nextNode);
	
    if (cloneNode) {
    	//alert("remove clone")
	    cloneNode.remove();
    }
    return false;	

}

function clickMovedn(curidx) {
//	alert("Movedn node" +curidx);
	var curNode = jQuery("#movedn"+curidx).parents('li:eq(0)');
	var nextNode = jQuery("#movedn"+curidx).parents('li:eq(0)').next();
	/*
	 * question html is looks like <li> <div> for class
	 * <div> for listcontrol, it has children div of
	 * listcontent <ul> for children questions </li>
	 */
	if (jQuery(nextNode).children('div:eq(1)').children(
			'div:eq(0)').size() == 0) {
		var nextidx = jQuery(nextNode).attr("id").substring(8);
		var vers = "1.00";
		// alert("set up table
		// "+nextidx+"-"+jQuery(nextNode).children('div:eq(1)').children('a:eq(0)').attr("id"));
		var childNode = 'true';
		if (jQuery(nextNode).parents('ul:eq(0)').attr("id") == 'example') {
			childNode = 'false';
		}
		var splitq = jQuery("#question"+nextidx).attr("value").split("#f#");
		var divtmp = getMaintTable(splitq[0], splitq[1], splitq[2],nextidx, childNode);
		divtmp.appendTo(jQuery(nextNode).children('div:eq(1)'));
		toggleHSControl(nextidx);
	}

	var cloneNode = null;
	if (nextNode.next().size() == 0) {
		//alert("clone ")
	    var cloneNode = nextNode.clone(true);
	    cloneNode.appendTo(jQuery(nextNode).parents('ul:eq(0)'));
	    jQuery('#example').treeview( {
		    add : cloneNode
	    });
	}

	curNode.insertAfter(nextNode);
	var idx = jQuery(curNode).attr("id").substring(8);
	var seq = Number(jQuery("#qseq" + idx).attr("value")) + 1;
	jQuery("#qseq" + idx).attr("value", seq);

	var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
    splitq[7] +"#f#" +seq +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +splitq[11] ;
    jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
	
	//jQuery("#"+jqprefix + idx + "\\]\\.questionSeqNumber").attr("value",seq);

	//alert(idx)
	jQuery("#moveup" + curNode.attr("id").substring(8)).show();
	jQuery("#movedn" + nextNode.attr("id").substring(8)).show();
	if (nextNode.prev().size() == 0) {
		// alert("move up next node");
		jQuery("#moveup" + nextNode.attr("id").substring(8))
				.hide();
	}
	if (curNode.next().size() == 0 || curNode.next().attr("id") == nextNode.attr("id")) {
		// see note from getmoveuplink for this weird condition check
		// alert ("move dn no next node ");
		jQuery("#movedn" + curNode.attr("id").substring(8))
				.hide();
	}
	var idx = jQuery(nextNode).attr("id").substring(8);
	seq = Number(jQuery("#qseq" + idx).attr("value")) - 1;
	jQuery("#qseq" + idx).attr("value", seq);
	var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
    splitq[7] +"#f#" +seq +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +splitq[11] ;
    jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
//    jQuery("#"+jqprefix + idx + "\\]\\.questionSeqNumber").attr("value",seq);
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
		// removedNode = jQuery(liId).clone(true);
		// removedNode = jQuery(liId);
		if (jQuery(liId).prev().size() == 0 && jQuery(liId).next().size() > 0) {
			jQuery("#moveup" + jQuery(liId).next().attr("id").substring(8))
					.hide();
		}
		if (jQuery(liId).next().size() == 0 && jQuery(liId).prev().size() > 0) {
			jQuery("#movedn" + jQuery(liId).prev().attr("id").substring(8))
					.hide();
		}
		var idx = jQuery(liId).attr("id").substring(8);
		var parentNum;
		if (jQuery(liId).parents('ul:eq(0)').parents('li:eq(0)').size() == 0) {
			parentNum = 0;
			var idx1 = jQuery(liId).attr("class").indexOf(" ");
			if (idx1 < 0) {
				// TODO weird problem, initial from return list, class is in 'li'
				// when it is loaded , then class is included in 'div' ?
				idx1 = jQuery(liId).attr("class").length;
			}	
			adjustGroup(jQuery(liId).attr("class").substring(5, idx1)); 
			// class is  "group0 expandable"
		} else {
			parentNum = jQuery(
					"#qnum"
							+ jQuery(liId).parents('ul:eq(0)').parents(
									'li:eq(0)').attr("id")
									.substring(8)).attr("value");
		}

		deleteChild(parentNum, jQuery(liId).attr("id"));
		var nextseq = Number(jQuery("#qseq"+jQuery(liId).attr("id").substring(8)).attr("value"));
	    var nextitem = jQuery(liId).next();
	    // update hidden seq# for the items follow the deleted item
	    while (nextitem.size() > 0) {
	    	jQuery("#qseq" + nextitem.attr("id").substring(8)).attr("value", nextseq++);	
	    	nextitem = nextitem.next();
	    }	

		// TODO : update seqnum of the sibling nodes following it
		jQuery(liId).remove();
		return false;  // so when clicked, the page will not jump

}

function clickPaste(curidx) {
	//alert("paste node" +curidx);
	var qnaireid = "qnaireid" + curidx;
	if (cutNode) {
		var idx;
		var parentNode = jQuery("#" + qnaireid);
		var ulTag = parentNode.children('ul');
		if (ulTag.size() > 0) {
			// alert(ulTag.attr("id"));
		} else {
			// alert("not found")
			curi++;
			ulTag = jQuery('<ul class="filetree"></ul>').attr("id",
					"ul" + curidx);
		}

		  if (qnaireid.substring(8) == jQuery(cutNode).attr("id").substring(8)) {
			  alert ("Can Not cut/paste on the same node");
		  } else {	  
			// alert(jQuery(cutNode).children('li').size());
			
			var found = false;
			var pastenode = jQuery("#"+qnaireid);
			while (!found && pastenode.parents('ul:eq(0)').parents('li:eq(0)').size() > 0 ) {
			  if (pastenode.parents('ul:eq(0)').parents('li:eq(0)').attr("id") == jQuery(cutNode).attr("id")) {
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
			    if (jQuery(cutNode).parents('ul:eq(0)').parents(
					'li:eq(0)').size() == 0) {
				    parentNum = 0;
				    var idx1 = jQuery(cutNode).attr("class").indexOf(" ");
					if (idx1 < 0) {
						idx1 = jQuery(cutNode).attr("class").length;
					}	
				    adjustGroup(jQuery(cutNode).attr("class").substring(5, idx1)); 
				    // class is "group0 expandable"
			    } else {
				    parentNum = jQuery(
						"#qnum"
								+ jQuery(cutNode).parents('ul:eq(0)').parents('li:eq(0)')
										.attr("id").substring(8)).attr("value");
			    }
				deleteChild(parentNum, jQuery(cutNode).attr("id"));
				var nextseq = Number(jQuery("#qseq"+jQuery(cutNode).attr("id").substring(8)).attr("value"));
			    var nextitem = jQuery(cutNode).next();
			    // update hidden seq# for the items follow the deleted item
			    while (nextitem.size() > 0) {
			    	jQuery("#qseq" + nextitem.attr("id").substring(8)).attr("value", nextseq++);	
			    	nextitem = nextitem.next();
			    }	
				cutNodeParentCode = parentNum;
			}
			pasteChild(qnaireid, cutNode);

			var liId = cutNode.attr("id");
			var parentRACode;
			if (jQuery("li#" + liId).parents('ul:eq(0)').size() > 0) {
				// only remove the first time
			    jQuery("li#" + liId).remove();
			   // alert(cutNode.attr("id"));
			}    
			if (jQuery("#"+qnaireid).prev().size() == 0) {
				jQuery("#moveup" + qnaireid.substring(8)).hide();
			}
			if (jQuery("#"+qnaireid).next().size() == 0) {
				jQuery("#movedn" + qnaireid.substring(8)).hide();
			}

			//cutNode = null;
		   } // not paste to itself or its children
		  }							
		//}// cutnode
			if (jQuery(parentNode).children('div:eq(0)').attr("class").indexOf("expandable") > 0) {
                   /* to force it to show the child node just added.
                    * the div 'class' is changing based on whether the node is expand or collapsed
                    */
				jQuery(parentNode).children('div:eq(0)').click();
					//jQuery("#listcontrol"+listitem.attr("id").substring(8)).click();
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
		if (jQuery("#"+qnaireid).children('div:eq(0)').attr("class").indexOf("expandable") > 0) {
            /* to force it to show the child node just added.
             * the div 'class' is changing based on whether the node is expand or collapsed
             */
			jQuery("#"+qnaireid).children('div:eq(0)').click();
				//jQuery("#listcontrol"+listitem.attr("id").substring(8)).click();
			}

	}
	return false;  // so when clicked, the page will not jump

}




/*
 * traverse thru the node to copy this node tree need to clone copy node,
 * otherwise if paste to its own children, then this will become infinite loop,
 */
function pasteChild(parentid, startnode) {
	var parentNode = jQuery("#" + parentid);
	var ulTag = parentNode.children('ul');

	// startnode = jQuery("#"+childid);
	// alert("copy node "+jQuery(startnode).attr("id").substring(8));
	var stidx = jQuery(startnode).attr("id").substring(8);
	var splitq = jQuery("#question"+stidx).attr("value").split("#f#");
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
	var ultag = jQuery('<ul></ul>');
	ultag.appendTo(listitem);
	var idx = listitem.attr("id").substring(8);
	listitem.appendTo(ulTag);
	if (jQuery(listitem).parents('ul:eq(0)').children('li').size() == 1) {
		jQuery("#moveup" + idx).hide();
		jQuery("#movedn" + idx).hide();
	} else {
		jQuery("#movedn" + idx).hide();
		if (listitem.prev().size() > 0) {
			jQuery("#movedn" + listitem.prev().attr("id").substring(8)).show();
		}
	}

	// also need this to show 'folder' icon
	jQuery('#example').treeview( {
		add : listitem
	});

	jQuery("#qnum" + idx).attr("value", jQuery("#questionNumber").attr("value"));
	var qid = jQuery("#qid" + jQuery(startnode).attr("id").substring(8)).attr("value");
	jQuery("#qid" + idx).attr("value", qid);
	var seqnum = Number(jQuery(listitem).siblings().size()) + 1;
	jQuery("#qseq" + idx).attr("value", seqnum);

	var liId = "li#" + parentid;
	var qid = jQuery("#qid" + idx).attr("value");
	var qnum = jQuery("#questionNumber").attr("value");
	var parentNum = jQuery("#qnum" + jQuery(liId).attr("id").substring(8)).attr("value");
	jQuery("#questionNumber").attr("value",
			Number(jQuery("#questionNumber").attr("value")) + 1)

	// questionnaireQuestionsId/questionnaireId/questionId/questionNumber/parentQuestionNumber
    //conditionFlag/condition/conditionValue/questionSeqNumber/versionNumber/deleted
	var tmpstr = "" +"#f#" +jQuery('#document\\.newMaintainableObject\\.businessObject\\.id').attr("value")
          +"#f#" +qid +"#f#" +qnum +"#f#" +parentNum +"#f#" +"N" +"#f#" +"" +"#f#" +
          "" +"#f#" +seqnum +"#f#" +"1" +"#f#" +"N" +"#f#" +null ;
	jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
    	
			
	// alert("child copy node"
	// + jQuery("#cond" + jQuery(startnode).attr("id").substring(8)).attr("value"));
	cidx = jQuery(startnode).attr("id").substring(8);

	var splitq = jQuery("#qnaireQuestions\\["+ cidx+"\\]").attr("value").split("#f#");
	cond = splitq[6];
	value = splitq[7];
	ruleId = splitq[11];
	if (cond != '') {
		var newResponse = getRequirementDeleteRow(responseArray[cond], value,
				idx);
		newResponse.prependTo(jQuery("#addrequirement" + idx).parents('div:eq(0)')
				.children('table:eq(0)').children('tbody'));
		jQuery("#addrequirement" + idx).parents('tr:eq(0)').remove();
		
		splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
	    var tmpstr = splitq[0] +"#f#" +splitq[1] 
        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +"Y" +"#f#" +cond +"#f#" +
        value +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +splitq[11] ;
	jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
	}
	if (ruleId != '' && ruleId != 'null') {
		var newResponse = getRuleEvaluationDeleteRow(ruleId, idx);
		newResponse.appendTo(jQuery("#addRuleRequirement"+idx).parents('div:eq(0)')
				.children('table:eq(0)').children('tbody'));
		jQuery("#addRuleRequirement" + idx).parents('tr:eq(0)').remove();
		var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
	    var tmpstr = splitq[0] +"#f#" +splitq[1] 
        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
        splitq[7] +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +ruleId ;
	    jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
	}

	if (jQuery(startnode).children('ul').children('li').size() > 0) {

		jQuery(startnode).children('ul').children('li').each(
				function() {
					if (isCopy == 'false'
							|| (isCopy == 'true' && jQuery(this).attr("id")
									.substring(8) <= maxCopyNodeIdx)) {
						pasteChild(jQuery(listitem).attr("id"), jQuery(this));
					}
				});
	}

}

/*
 * traverse thru the node to delete this node tree to create scripts for delete
 * this is called by remove node action
 */
function deleteChild(parentNum, childid) {

	var idx = jQuery("#" + childid).attr("id").substring(8);
	var qnum = jQuery("#qnum" + idx).attr("value");
	var childrenli = jQuery("#" + childid).children('ul').children('li');
	var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
    splitq[7] +"#f#" +splitq[8]  +"#f#" +splitq[9] +"#f#" +"Y" +"#f#" +splitq[11] ;
jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);

var nextseq = splitq[8];

var nextitem = jQuery("#" + childid).next();
// update seq for the siblings after the new node
while (nextitem.size() > 0) {
	var splitq = jQuery("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value").split("#f#");
    var tmpstr = splitq[0] +"#f#" +splitq[1] 
    +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
    splitq[7] +"#f#" +(nextseq++) +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +splitq[11] ;
jQuery("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value",tmpstr);
	nextitem = nextitem.next();
}	


	if (childrenli.size() > 0) {

		childrenli.each(function() {
			deleteChild(qnum, jQuery(this).attr("id"));
		});
	}

}

/*
 * traverse thru the node to collect copy nodes IE issue with clone, so set this
 * max up, in case the copied node is pasted to its children if don't set this
 * up, it will cause infinite loop
 */
function getMaxCopyNodeIdx(startnode) {

	var idx = jQuery(startnode).attr("id").substring(8);
	var childrenli = jQuery(startnode).children('ul').children('li');
	if (idx > maxCopyNodeIdx) {
		maxCopyNodeIdx = idx;
	}

	if (jQuery(childrenli).size() > 0) {

		jQuery(childrenli).each(function() {
			getMaxCopyNodeIdx(jQuery(this));
		});
	}

}


/*
 * set up add requirement row
 */
function getAddRequirementRow(curidx) {

	var trtmp = jQuery('<tr></tr>');
	var thtmp = jQuery('<th style="text-align:center; width:150px;"></th>').html(
			"Add");
	thtmp.appendTo(trtmp);
	tdtmp = jQuery('<td class="content_info" style="text-align:center;"></td>')
			.html("Parent Response ");
	// alert("response options "+responseOptions.html()+"-"+i);
	var respclone = responseOptions.clone(true);
	// if it is rootnode
	if (jQuery("#qnaireid"+curidx).parents('ul:eq(0)').attr("id") == 'example') {
	    respclone = rootNodeResponseOptions.clone(true);
	}
	
	respclone.attr("id", "parentResponse" + curidx).appendTo(tdtmp);
	tdtmp.appendTo(trtmp);
	tdtmp = jQuery('<td class="content_info" style="text-align:center;"></td>')
			.html("Value:");
	jQuery('<input type="text" size="25" />').attr("id", "reqVal" + curidx).attr("name", "reqVal" + curidx).appendTo(tdtmp);

	tdtmp.appendTo(trtmp);
	tdtmp = jQuery('<td class="content_info" class="content_white" style="width:65px; text-align:center;"></td>');
	var image = jQuery(
			'<input name="addrequirement" src="kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" type="image" />')
			.attr("id", "addrequirement" + curidx)
			.click(
					function() {
						var idx = jQuery(this).attr("id").substring(14);
						var response = jQuery(this).parents('tr:eq(0)').children(
								'td:eq(0)').children('select:eq(0)').attr(
								"value");
						var value = jQuery(this).parents('tr:eq(0)').children(
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
							newResponse.prependTo(jQuery(this).parents('div:eq(0)')
									.children('table:eq(0)').children('tbody'));
							var idx = jQuery(this).parents('li:eq(0)').attr("id")
									.substring(8);
							var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
						    var tmpstr = splitq[0] +"#f#" +splitq[1] 
					        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +"Y" +"#f#" +response +"#f#" +
					        value +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +splitq[11] ;
						    jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
							jQuery(this).parents('tr:eq(0)').remove();
						}
						// }
						return false;
					});

	image.appendTo(tdtmp);
	tdtmp.appendTo(trtmp);
	return trtmp;
}

/*
 * set up add requirement row
 */
function getAddRuleRequirementRow(curidx) {
	var trtmp = jQuery('<tr></tr>');
	var thtmp = jQuery('<th style="text-align:left; width:150px;"></th>').html(
			"Add Rule");
	thtmp.appendTo(trtmp);
	tdtmp = jQuery('<td colspan="2" align="left" class="content_info" style="text-align:left;"/>');
	jQuery('<input type="text" size="25" />').attr("id", "ruleId" + curidx).attr("name", "ruleId" + curidx).appendTo(tdtmp);
    var atag = jQuery('<a href="#"></a>');
    var image = jQuery('<img border="0" title="Search Rule" alt="Search Rule" class="tinybutton" src="static/images/searchicon.gif" />')
            .attr("id", "searchRule" + curidx).attr("name", "searchRule" + curidx).click(
       function() { clickSearchRule(jQuery(this).parent().prev().attr("id").replace(/(:|\.|\[|\])/g,'\\$1'));});
    image.appendTo(atag);
    atag.appendTo(tdtmp);        
//    jQuery('<a href="#"><img border="0" title="Search Rule"
//                                            alt="Search Rule" class="tinybutton" 
//                                             src="static/images/searchicon.gif"  onClick="clickSearchRule(${qidx})"></a>').appendTo(tdtmp);
	tdtmp.appendTo(trtmp);
	tdtmp = jQuery('<td class="content_info" class="content_white" style="width:65px; text-align:center;"></td>');
	var image = jQuery(
			'<input name="addRuleRequirement" src="kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" type="image" />')
			.attr("id", "addRuleRequirement" + curidx)
			.click(
					function() {
						var idx = jQuery(this).attr("id").substring(18);
						var value = jQuery(this).parents('tr:eq(0)').children(
								'td:eq(0)').children('input:eq(0)').attr(
								"value");
						if (value == '') {
					        alert("Please enter a value");
					        return false;
						}else{
							/*
							 * var opDesc; if (sequence == 1) { opDesc =
							 * "Current Requirements:"; } else { opDesc =
							 * opArray[operator]; }
							 */
							var newResponse = getRuleEvaluationDeleteRow(value, idx);
							newResponse.appendTo(jQuery(this).parents('div:eq(0)')
									.children('table:eq(0)').children('tbody'));
							var idx = jQuery(this).parents('li:eq(0)').attr("id")
									.substring(8);
							jQuery('<input type="hidden" size="25" />').attr("id", "ruleId" + curidx).attr("value", value);
							var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
						    var tmpstr = splitq[0] +"#f#" +splitq[1] 
					        				+"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
					        				splitq[7] +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +value ;
						    jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
							jQuery(this).parents('tr:eq(0)').remove();
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
	jQuery("#qnaireid"+curi)
	if (value == '') {
        alert("Please enter a value");
        return false;
	}
// root node may have rule evaluation as response
	if (jQuery("#qnaireid"+idx).parents('ul:eq(0)').parents('li:eq(0)').length > 0) {
	// this is a child node, then check
    	var stidx = jQuery("#qnaireid"+idx).parents('ul:eq(0)').parents('li:eq(0)').attr("id").substring(8);
    	var splitq = jQuery("#question"+stidx).attr("value").split("#f#");
	var qtypeid = splitq[1];
	//lookup 
	if (qtypeid == 6 && splitq[5] > 0) {
		qtypeid = splitq[5];
	}
//	alert (stidx+" -"+qtypeid);

	var valid = false;
//	if (value == '') {
//		alert("Please enter a value");
//	} else  
    if (response == 0) {
		alert("Please select a response");
	} else if (!isValidBranchingCondition(qtypeid, response)) {
		alert("Invalid Branching condition");
	} else if (response >= 5 && response <= 10 && isNaN(value)) {
		alert("Value must be a number");
	} else if (response > 10 && response <= 12 && !isDate(value, 'MM/dd/yyyy')) {
		alert("Not a Valid Date (mm/dd/yyyy)");
	} else {
		valid = true;
	}
	return valid;
	} else {
	   // this is root node, only rule evaluation
	   if (response == 0) {
        alert("Please select a response");
        return false;
       } else {
	    return true;
	    }
	}
}

function isValidBranchingCondition(parentQntypeId, response) {
	var valid = true;
	if (response < 5 && (parentQntypeId == 3 || parentQntypeId == 4)) {
		// yes/no question
		valid = false;
	} else if (response >= 5 && response <= 10 && parentQntypeId != 3) {
		// number
		valid = false;
	} else if (response > 10 && response <= 12 && parentQntypeId != 4) {
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
	var trtmp = jQuery('<tr></tr>');
	var thtmp = jQuery('<th style="text-align:left; border-top:none; width:150px;">')
			.html("Current Requirements:");
	thtmp.appendTo(trtmp);
	var tdtmp = jQuery('<td colspan="2" style="text-align:left; border-top:none;">').html(
			response + " : " + value);
	tdtmp.appendTo(trtmp);
	tdtmp = jQuery('<td class="content_white" style="text-align:center; border-top:none; width:65px;">');
	var image = jQuery(
			'<input src="kr/static/images/tinybutton-delete1.gif"  style="border:none;" alt="delete" type="image" />')
			.attr("id", "deletereq" + curidx).click(
					function() {
						// alert("This would delete this requirement."
						// + jQuery(this).parents('tr:eq(0)').next().size());
						var idx = jQuery(this).parents('li:eq(0)').attr("id")
								.substring(8);
						var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
					    var tmpstr = splitq[0] +"#f#" +splitq[1] 
				        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +"N" +"#f#" +"" +"#f#" +
				        "" +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +splitq[11] ;
					jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
						
						getAddRequirementRow(idx).prependTo(
								jQuery(this).parents('tr:eq(0)').parents(
										'tbody:eq(0)'));
						jQuery(this).parents('tr:eq(0)').remove();
						return false;
					});
	if (jQuery("#readOnly").attr("value") != 'true') {
	image.appendTo(tdtmp);
	}
	tdtmp.appendTo(trtmp);
	return trtmp;

}
function getRuleEvaluationDeleteRow(value, curidx) {
	var trtmp = jQuery('<tr></tr>');
	var thtmp = jQuery('<th style="text-align:left; border-top:none; width:150px;">')
			.html("Rule Id:");
	thtmp.appendTo(trtmp);
	var tdtmp = jQuery('<td colspan="2" align="left" style="text-align:left; border-top:none;">').html(value);
	tdtmp.appendTo(trtmp);
	tdtmp = jQuery('<input type="hidden"/>').attr("id","ruleId"+curidx).attr("name","ruleId"+curidx).attr("value",value);
	tdtmp.appendTo(trtmp);
	tdtmp = jQuery('<td class="content_white" style="text-align:center; border-top:none; width:65px;">');
	var image = jQuery(
			'<input src="kr/static/images/tinybutton-delete1.gif"  style="border:none;" alt="delete" type="image" />')
			.attr("id", "deleterulereq" + curidx).click(
					function() {
						// alert("This would delete this requirement."
						// + jQuery(this).parents('tr:eq(0)').next().size());
						var idx = jQuery(this).parents('li:eq(0)').attr("id").substring(8);
						var splitq = jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value").split("#f#");
					    var tmpstr = splitq[0] +"#f#" +splitq[1] 
				        				+"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
				        				splitq[7] +"#f#" +splitq[8] +"#f#" +splitq[9] +"#f#" +splitq[10] +"#f#" +null ;
					    jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
						getAddRuleRequirementRow(idx).appendTo(
								jQuery(this).parents('tr:eq(0)').parents(
										'tbody:eq(0)'));
						jQuery(this).parents('tr:eq(0)').remove();
						return false;
					});
	if (jQuery("#readOnly").attr("value") != 'true') {
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
	jQuery("#newqid" + nodeIndex).attr("value", newQuestionId);
	jQuery("#newqdesc" + nodeIndex).attr("value", newQuestion);
	jQuery("#newqtypeid" + nodeIndex).attr("value", newQuestionTypeId);
	jQuery("#newqvers" + nodeIndex).attr("value", newQuestionSequence);
	jQuery("#newqdispans" + nodeIndex).attr("value", displayedAnswers);
	jQuery("#newqmaxans" + nodeIndex).attr("value", maxAnswers);
	jQuery("#newqmaxlength" + nodeIndex).attr("value", answerMaxLength);
	 //alert("qid "+ nodeIndex+"-"+jQuery("#newqdesc" + nodeIndex).attr("value")+"-"+newQuestion);
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
	if (jQuery(".group" + groupid).size() >= 20) {
		initgroup++;
	}
	for ( var k = 0; k < questions.length; k++) {
		var field = questions[k].split("#f#");
		curi++;
		var parenturl = jQuery('#example');
		// alert(questions[k]);
		var listitem = getQuestionNew(field[1], field[2], field[3], field[4], field[5], field[6],'false');
		var ultag = jQuery('<ul></ul>');
		ultag.appendTo(listitem);
		var idx = listitem.attr("id").substring(8);
		// listitem.appendTo('ul#example');
		// last one no 'move dn'
		if (firstidx == -1) {
			firstidx = idx;
		}

		listitem.appendTo(jQuery(parenturl));
		// also need this to show 'folder' icon
		jQuery('#example').treeview( {
			add : listitem
		});
		// TODO : try grouping
		addToGroup(listitem);

		// alert(jQuery(listitem).parents('ul:eq(0)').size());
		if (jQuery(listitem).parents('ul:eq(0)').children('li').size() == 1) {
			jQuery("#moveup" + idx).hide();
			jQuery("#movedn" + idx).hide();
		} else {
			// alert("prev "+listitem.prev().attr("id"));
			jQuery("#movedn" + idx).hide();
			if (listitem.prev().size() > 0) {
				jQuery("#movedn" + listitem.prev().attr("id").substring(8)).show();
			}
		}

		// TODO : set up for insert
		/*
		 * questionnairenumber from #questionnairenumber questionId from #qid
		 * sequenceNumber from jQuery(this).parents('li:eq(0)').siblings().size() ?
		 */

		jQuery("#qid" + jQuery(listitem).attr("id").substring(8)).attr("value", field[0]);

		var seqnum = Number(jQuery(listitem).siblings().size()) + 1;
		jQuery("#qseq" + jQuery(listitem).attr("id").substring(8)).attr("value", seqnum);
		var qnum = jQuery("#questionNumber").attr("value");
		jQuery("#qnum" + jQuery(listitem).attr("id").substring(8)).attr("value", qnum);
		jQuery("#questionNumber").attr("value",
				Number(jQuery("#questionNumber").attr("value")) + 1)

    var tmpstr = "" +"#f#" +jQuery('#document\\.newMaintainableObject\\.businessObject\\.id').attr("value")
          +"#f#" +field[0]+"#f#" +qnum +"#f#" +parentnum +"#f#" +"N" +"#f#" +"" +"#f#" +
          "" +"#f#" +seqnum +"#f#" +"1" +"#f#" +"N" +"#f#" +null ;
	jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
				
				

	} // end for to set up questions
	/*
	 * TODO : Following clone code is just a hack
	 * the last one does not have the "+" icon displayed.  so use this
	 * to add the last one twice, then remove the clone one.  so, the last one looks like OK.
	 * Should look into 'class' property to fix this hack
	 */
	var cloneNode = jQuery("#qnaireid"+curi).clone(true);
	cloneNode.appendTo(jQuery('#example'));
	jQuery('#example').treeview( {
		add : cloneNode
	});
	cloneNode.remove();
	
	// alert(curgroup + "-" + initgroup + "-" + firstidx + "-" + groupid)
	jQuery(".group" + curgroup).hide();
	curgroup = initgroup;
	jQuery(".group" + curgroup).show();
	while (initgroup++ < groupid) {
		jQuery(".group" + initgroup).hide();
	}

	jQuery("#listcontrol" + firstidx).click();
	
	if (groupid > 0) {
		if (curgroup == groupid) {
		    jQuery("#nextGroup").hide();
		} 
		if (curgroup < groupid) {
		    jQuery("#nextGroup").show();
		} 
		if (curgroup == 0) {
		    jQuery("#nextGroup").show();
		    jQuery("#prevGroup").hide();
		}
		if (curgroup > 0) {
		    jQuery("#prevGroup").show();
		}
	}	

}// end returnquestionlist


/*
 * add list item to group for paging
 */
function addToGroup(listitem) {
	// TODO : try grouping
	if (jQuery(".group" + groupid).size() >= 20) {
		groupid++;
	}
	var currentClass = jQuery(listitem).attr("class");
	jQuery(listitem).attr("class", "group" + groupid +" " + currentClass);
	// jQuery(listitem).addClass("group" + groupid);
	if (curgroup != groupid) {
		jQuery(listitem).hide();
	}

}

/*
 * in the case of move up/down; node may be moved to different group (page)
 */
function swapGroupId(curNode, nextNode) {
	// class may like "group0 expandable ..", the last item has one more item
	var curclass = jQuery(curNode).attr("class");
	var nextclass = jQuery(nextNode).attr("class");
	jQuery(curNode).attr("class", nextclass);
	jQuery(nextNode).attr("class", curclass);
	// alert(curclass+"-"+nextclass);
	if (curclass.substring(0, 7).trim() != nextclass.substring(0, 7).trim()) {
		jQuery(curNode).hide();
		jQuery(nextNode).show();
	}
//	if (curclass != nextclass) {
//		jQuery(curNode).attr("class", nextclass);
//		jQuery(nextNode).attr("class", curclass);
//	}
}

/*
 * adjust group if list item is removed
 */
function adjustGroup(idx) {
	//alert(idx + "-" + groupid + "-" + curgroup )
	while (idx < groupid) {
		idx1 = Number(idx) + 1;
		node = jQuery(".group" + idx1 + ":eq(0)");
		nodeclass = jQuery(node).attr("class"); // may have multiple classes
		// specified
		nodeclass = nodeclass.replace("group" + idx1, "group" + idx);
		// alert(idx + "-" + groupid + "-" + curgroup + "-"
		// + jQuery(".group" + idx1).size() + nodeclass);
		jQuery(node).attr("class", nodeclass);
		if (curgroup == idx) {
			// alert("show");
			jQuery(node).show();
		}
		idx++;
		if (idx == groupid) {
			// Note : size =1 because node is not removed yet
			if (jQuery(".group" + idx).size() == 1) {
				groupid--;
			}
		}
		// alert("loop back " + idx + "<" + groupid);
	}
	//alert(idx +"-"+groupid+"-"+curgroup)
	if (idx == groupid) {
		if (jQuery(".group" + idx).size() == 1) {
			groupid--;
		}
	}
	
	if (curgroup > groupid) {
		jQuery(".group" + groupid).show();
		curgroup = groupid;
		jQuery("#nextGroup").hide();
	}	

	if (groupid > 0) {
		if (curgroup == groupid) {
		    jQuery("#nextGroup").hide();
		} 
		if (curgroup < groupid) {
		    jQuery("#nextGroup").show();
		} 
		if (curgroup == 0) {
		    jQuery("#nextGroup").show();
		    jQuery("#prevGroup").hide();
		}
		if (curgroup > 0) {
		    jQuery("#prevGroup").show();
		}
	} else {
	    jQuery("#nextGroup").hide();
	    jQuery("#prevGroup").hide();
	}
}

/*
 * adjust group if list item is inserted
 */
function adjustGroupDown() {
	
	var idx = curgroup;
	
	while (idx < groupid  && jQuery(".group"+idx).size() > 20) {
		var idx1 = Number(idx) + 1;
		node = jQuery(".group" + idx + ":eq(20)");
		nodeclass = jQuery(node).attr("class"); // may have multiple classes
		// specified
		nodeclass = nodeclass.replace("group" + idx, "group" + idx1);
		jQuery(node).attr("class", nodeclass);
		if (curgroup == idx) {
			// alert("show");
			jQuery(node).hide();
		}
		idx++;
		// alert("loop back " + idx + "<" + groupid);
	}

	if (idx == groupid) {
		if (jQuery(".group" + idx).size()> 20) {
			groupid++;
			var node = jQuery(".group" + idx + ":eq(20)");
			var nodeclass = jQuery(node).attr("class"); // may have multiple classes
			// specified
			nodeclass = nodeclass.replace("group" + idx, "group" + groupid);
			jQuery(node).attr("class", nodeclass);
			if (curgroup == idx) {
				// alert("show");
				jQuery(node).hide();
			}
			if (curgroup == (groupid-1)) {
				jQuery("#nextGroup").show();
			}	

		}
	}
	
}


/* integrate with edit, new */
// -- should be shared

	  

var responseArray = [ 'select', 'Contains text value', 'Begins with text', 'Ends with text', 'Matches text',
		'Less than number', 'Less than or equals number', 'Equals number', 'Not Equal to number',
		'Greater than or equals number', 'Greater than number', 'Before date',
		'After date', 'Rule Evaluation' ];
var questionType = [ 'select', 'Yes/No', 'Yes/No/NA', 'Number', 'Date', 'Text',
		'Lookup' ];

var responseOptions = jQuery('<select name="CustomData"></select>');
jQuery('<option value="0" selected="selected">select</option>').appendTo(
		responseOptions);
jQuery('<option value="1">Contains text value</option>').appendTo(responseOptions);
jQuery('<option value="2">Begins with text</option>').appendTo(responseOptions);
jQuery('<option value="3">Ends with text</option>').appendTo(responseOptions);
jQuery('<option value="4">Matches text</option>').appendTo(responseOptions);
jQuery('<option value="5">Less than number</option>').appendTo(responseOptions);
jQuery('<option value="6">Less than or equals number</option>').appendTo(
		responseOptions);
jQuery('<option value="7">Equals number</option>').appendTo(responseOptions);
jQuery('<option value="8">Not Equal to number</option>').appendTo(responseOptions);
jQuery('<option value="9">Greater than or equals number</option>').appendTo(
		responseOptions);
jQuery('<option value="10">Greater than number</option>').appendTo(responseOptions);
jQuery('<option value="11">Before date</option>').appendTo(responseOptions);
jQuery('<option value="12">After date</option>').appendTo(responseOptions);
jQuery('<option value="13">Rule Evaluation</option>').appendTo(responseOptions);

// root node only has rule evaluation
var rootNodeResponseOptions = jQuery('<select name="CustomData"></select>');
jQuery('<option value="0" selected="selected">select</option>').appendTo(
        rootNodeResponseOptions);
jQuery('<option value="13">Rule Evaluation</option>').appendTo(rootNodeResponseOptions);

// TODO : currently this one is not working copied to questionnairequestion.jsp
jQuery("#addUsage").click(function() {
			var newItemCode = jQuery("#newQuestionnaireUsage\\.moduleItemCode").attr("value");
			var newSubItemCode = jQuery("#newQuestionnaireUsage\\.moduleSubItemCode").attr("value");
			var newItemCodeDesc = jQuery("#newQuestionnaireUsage\\.moduleItemCode option:selected").text();
			var newSubItemCodeDesc = newSubItemCode != '0' ? jQuery("#newQuestionnaireUsage\\.moduleSubItemCode option:selected").text()
					: "";
			var ruleId = jQuery("#newQuestionnaireUsage\\.ruleId").attr("value");
			var label = jQuery("#newQuestionnaireUsage\\.questionnaireLabel").attr("value");
			var mandatory = jQuery("#newQuestionnaireUsage\\.mandatory").attr("checked") ? "Yes" : "No";
			if (newItemCode == '') {
				alert("Please select a module");
			} else if (label == '') {
				alert("Please enter Label");
			} else  if (isDuplicateUsage(newItemCode,newSubItemCode, ruleId, qnversion)) {
				alert("Module is already added");
			} else {	
				var template = jQuery('tr.usageTemplate').html();
				//count should count the indexes of the visible usages.
				var countHtml = jQuery('#usage-table tr:visible th').last().html();
				var count = parseInt(countHtml);
				if (isNaN(count)) {
					count = 0;
				}
				//index counts any existing(even deleted) usages so the index is one greater.
				var index = parseInt(jQuery('#usage-table tr:last th').html());
				if (isNaN(index)) {
					index = 0;
				}
				template = template.replace(/%COUNT%/g, count+1);
				template = template.replace(/%COEUS_MODULE_DESC%/g, newItemCodeDesc);
				template = template.replace(/%COEUS_MODULE_CODE%/g, newItemCode);
				template = template.replace(/%COEUS_SUBMODULE_DESC%/g, newSubItemCodeDesc);
				template = template.replace(/%COEUS_SUB_MODULE_CODE%/g, newSubItemCode);
				template = template.replace(/%RULE_ID%/g, ruleId);
				template = template.replace(/%MANDATORY%/g, mandatory);
				template = template.replace(/%LABEL%/g, label);
				template = template.replace(/%SEQUENCE%/g, qnversion);
				template = template.replace(/%INDEX%/g, index);
				var newRow = jQuery('<tr/>').append(template);
				jQuery(newRow).appendTo(jQuery("#usage-table tbody"));
				//reapply the delete on-click handler.
				loadUsages();
			}
			return false;
});

/*
 * search icon click function at the root level.  This will result in multi-value lookup.
 */
jQuery("#rootSearch").click(function() {
	// alert(jQuery(this).parents('li:eq(0)').attr("id"));
		// TODO : IE problem. after the node is moved up or down, then the "id"
		// of the image got lost.
		// so, before figuring a solution, use this alternative to fin parents
		// id.
		checkToAddQn(-1);
		return false;
	});


/*
 * function when 'next' button is clicked. Move to next page of 20 questions
 */
jQuery("#nextGroup").click(function() {
	if (groupid != curgroup) {
		jQuery(".group" + curgroup).hide();
		if (++curgroup == groupid) {
			jQuery("#nextGroup").hide();
		}
		if (curgroup  == 1) {
			jQuery("#prevGroup").show();
		} 
		jQuery(".group" + curgroup).show();
		// alert(jQuery(".group"+curgroup+":eq(0)").attr("id"));
		jQuery(
				"#listcontrol"
						+ jQuery(".group" + curgroup + ":eq(0)").attr("id")
								.substring(8)).click();
//		jumpToAnchor('topOfForm');
	}
	return false;

});

/*
 * function when 'back' button is clicked. Move to previous page of 20 questions
 */
jQuery("#prevGroup").click(function() {
	// alert ("prevgroup");
	if (groupid > 0) {
		jQuery(".group" + curgroup).hide();
		if (--curgroup == 0) {
			jQuery("#prevGroup").hide();
		} 
		if (curgroup == (groupid-1)) {
			jQuery("#nextGroup").show();
		}	
		jQuery(".group" + curgroup).show();
		// alert(jQuery(".group"+curgroup+":eq(0)").attr("id"));
		jQuery(
				"#listcontrol"
						+ jQuery(".group" + curgroup + ":eq(0)").attr("id")
								.substring(8)).click();
	}
	return false;

});

jQuery("#backToTop").click(function() {
	jQuery(".group" + curgroup).hide();
	curgroup = 0;
	jQuery(".group" + curgroup).show();
	// alert(jQuery(".group"+curgroup+":eq(0)").attr("id"));
		jQuery(
				"#listcontrol"
						+ jQuery(".group" + curgroup + ":eq(0)").attr("id")
								.substring(8)).click();
		jumpToAnchor('topOfForm');
		return false;

	});

// This is save function when 'save' button is clicked for edit or create new
// questionnaire.
// First save questionnaire bo because 'description'

jQuery(document).ready(function() {

    if (jQuery("#maintAction").attr("value") != 'Copy') {
        jQuery("#globalbuttons").find('input').each(function() {
              //alert(jQuery(this).attr("name"));
              if (jQuery(this).attr("name") == 'methodToCall.route') {
                  jQuery(this).attr("id","route");
              } else if (jQuery(this).attr("name") == 'methodToCall.save') {
                  jQuery(this).attr("id","save");
              } else if (jQuery(this).attr("name") == 'methodToCall.blanketApprove') {
                  jQuery(this).attr("id","blanketApprove");
              } else if (jQuery(this).attr("name") == 'methodToCall.close') {
                  jQuery(this).attr("id","close");
              }
          });
        jQuery("#addTemplate").find('input').each(function() {
            //alert(jQuery(this).attr("name"));
            if (jQuery(this).attr("name") == 'methodToCall.addTemplate') {
                jQuery(this).attr("id","add");
            }  
        });

    }
    if (jQuery("#maintAction").attr("value") == 'Edit') {
        qnversion = jQuery("#document\\.newMaintainableObject\\.businessObject\\.sequenceNumber").attr("value");
    } else if (jQuery("#maintAction").attr("value") == 'New') {
  	    qnversion = jQuery("#document\\.newMaintainableObject\\.businessObject\\.sequenceNumber").attr("value");
    }
	
	/*
	 * The above code and the following has to be in the document.ready block.
	 */
    
	jQuery("#save").click(function() {
	  //alert("save");
		return checkBeforeSubmit();
	}); // #save
  
  jQuery("#route").click(function() {
		return checkBeforeSubmit();
	}); // #route

  jQuery("#blanketApprove").click(function() {
		return checkBeforeSubmit();
  }); // #route
  jQuery("#close").click(function() {
		return checkBeforeSubmit();
  }); // #close
  
  jQuery("#add").click(function() {
		var retval = false;
	  if (jQuery('input[type=file]').val() == '') {
			alert("Please select a file name");
	  } else {
		    retval = true;
	  }	
		return retval;  
    }); // #add template
    // hide routelog for view because it is using edit to create new doc for view
   // alert(jQuery("#readOnly").attr("value") +  jQuery("#docStatus").attr("value") )
   if (jQuery("#readOnly").attr("value") == 'true' && jQuery("#docStatus").attr("value") == 'I') {    
       // option 1 : simply hide it
        jQuery("#tab-RouteLog-div").hide();
        jQuery("#tab-RouteLog-div").prev().hide();
       
       // option 2 : display message on route log tab
       /*
        var routemsg = jQuery('<div class="tab-container">')
            .attr("id", "noroutelog");
        
        var tbltmp = jQuery('<table width="100%" width="100%" cellpadding="0" cellspacing="0" class="datatable" />');
 
        var tbodytmp = jQuery('<tbody/>');
        var tr1 = jQuery('<tr></tr>');
        var td1 = jQuery('<td class="subelementcontent"></td>');
        td1.html("Route log is not available for Bootstrap data");
        tr1.html(td1);
        tbodytmp.html(tr1);
        tbltmp.html(tbodytmp);
        routemsg.html(tbltmp);
        routemsg.insertAfter(jQuery("#tab-RouteLog-div").children('div:eq(0)'));
        
        jQuery("#tab-RouteLog-div").children('div:eq(0)').hide();
       */ 
     //   jQuery("#tab-RouteLog-div").find('[class^=tab-container]').each(
     //   function() {       
     //        jQuery(this).hide(); 
     //           });
   }
   
   
  // the show/hide for question and usage panels are not from kuali framework
  // so to override the onclick here to also expand question and usage panels
  // first remove the 'onclick' attribute, then add 'click' function
  jQuery('input[name=methodToCall\\.showAllTabs]').attr('onclick', '').click(function() {
		expandAllTab();
		if (jQuery("a.questionpanelcontrol").html().indexOf("show.gif") > -1) {
            jQuery("a.questionpanelcontrol").click();
        }
		if (jQuery("a.usagepanelcontrol").html().indexOf("show.gif") > -1) {
            jQuery("a.usagepanelcontrol").click();
        }
        return false;
	});	
  
}); // document.ready


/*
 * some basic rule check when action buttons is clicked
 */
function checkBeforeSubmit() {
	//alert("in checkbeforesubmit")
	//var numOfQuestions =  jQuery('#numOfQuestions').attr("value",i);
	var qname = jQuery('#document\\.newMaintainableObject\\.businessObject\\.name').attr("value");
	var qnaireid = jQuery('#document\\.newMaintainableObject\\.businessObject\\.id').attr("value");
	var qid = jQuery('#document\\.newMaintainableObject\\.businessObject\\.questionnaireId').attr("value");
	var docstatus = jQuery('#docStatus').attr("value");
	var qdescription = jQuery('#document\\.newMaintainableObject\\.businessObject\\.description').attr("value");
	var qisfinal = jQuery('#document\\.newMaintainableObject\\.businessObject\\.isFinal').attr("checked");
	var qtemplate = jQuery('#templateFileNameHidden').val();

	var retval = false;
	//if (jQuery('#newQuestionnaire\\.questionnaireId').attr("value") == '0') {
	//	// TODO : temp hack for 'edit', the first time to save,it will based on this to version
	//}	qnaireid=":"+qnaireid;
	// alert
		// ("save"+qname+qdescription+jQuery('#newQuestionnaire\\.isFinal').attr("checked"));
		if (qname == '') {
			alert("Questionnaire Name is required");
		} else if (qdescription == '') {
			alert("Questionnaire description is required");
		} else if (jQuery("#example").children('li').size() == 0) {
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
	jQuery("#usage-table").children('tbody:eq(0)').children('tr').each(
	  function() {
        if (k++ > 0 && jQuery(this).children('td:eq(0)').children('input:eq(0)').attr("value") == moduleitemcode && jQuery(this).children('td:eq(1)').children('input:eq(0)').attr("value") == modulesubitemcode && jQuery(this).children('td:eq(4)').html() == vers) {
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
    var refid = jQuery('#document\\.newMaintainableObject\\.businessObject\\.id').attr("value");
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
                parenturl = jQuery('#example');
                pnum0++;
                rootidx = curi;

            } else {
                if (field[2] == 1) { // the first children
                    parentidx = curi - 1;
                } else {
                    for ( var l = rootidx; l <= curi - 1; l++) {
                        if (jQuery("#qnum" + l).attr("value")) {
                            if (jQuery("#qnum" + l).attr("value") == parentnum) {
                                parentidx = l;
                            }
                        }
                    }
                }
                parenturl = jQuery("#qnaireid" + parentidx).children('ul:eq(0)');
                ischild = 'true';
            }
            nodecount++;
            newqn[curi] = field[15];
            var listitem = getQuestionNew(field[3], field[4], field[9], field[10], field[11], field[12], ischild);
            var ultag = jQuery('<ul></ul>');
            ultag.appendTo(listitem);
            var idx = listitem.attr("id").substring(8);
            // listitem.appendTo('ul#example');
            // last one no 'move dn'
            if (firstidx == -1) {
                firstidx = idx;
            }

            listitem.appendTo(jQuery(parenturl));
            // also need this to show 'folder' icon
            jQuery('#example').treeview( {
                add : listitem
            });

            // TODO : test idea of display page by page with hide().
            if (parentnum == 0) {
                addToGroup(listitem);
                if (groupid > 0) {
                    jQuery(listitem).hide();
                }
            }

            if (jQuery(listitem).parents('ul:eq(0)').children('li').size() == 1) {
                jQuery("#moveup" + idx).hide();
                jQuery("#movedn" + idx).hide();
            } else {
                jQuery("#movedn" + idx).hide();
                if (listitem.prev().size() > 0) {
                    jQuery("#movedn" + listitem.prev().attr("id").substring(8))
                            .show();
                }
            }

           // alert ("parent "+parentnum+"-"+field[1]+"-"+field[6]);
            if (parentnum > 0 && field[6] != 'null') {
                // alert ("add req for parent
                // "+jQuery("#addrequirement"+i).parents('tr:eq(0)').size());
                var newResponse = getRequirementDeleteRow(
                        responseArray[field[6]], field[7]);
                newResponse.prependTo(jQuery("#addrequirement" + curi).parents(
                        'div:eq(0)').children('table:eq(0)').children('tbody'));
                jQuery("#addrequirement" + curi).parents('tr:eq(0)').remove();
            }

            jQuery("#qnum" + idx).attr("value", field[5]);
            jQuery("#qid" + idx).attr("value", field[1]);
            jQuery("#qseq" + idx).attr("value", field[2]);
            // set up qdesc & qtypeid

            if (field[6] == 'null') {
                field[6] = ''
            }        
            if (field[7] == 'null') {
                field[7] = ''
            }        
//            if (field[16] == 'null') {
//                field[16] = ''
//            }        
            var tmpstr = field[0] +"#f#" +refid
            +"#f#" +field[1] +"#f#" +field[5] +"#f#" +field[8] +"#f#" +field[14] +"#f#" +field[6] +"#f#" +
            field[7] +"#f#" +field[2] +"#f#" +field[13] +"#f#" +"N" +"#f#" +field[16];
    jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);
            
        } // end if-then-else
    } // end for to set up questions

    //alert(groupid+"-grp "+curgroup);
    if (groupid == 0) {
        jQuery("#nextGroup").hide();
        jQuery("#prevGroup").hide();
    }   
    if (curgroup == 0) {
        jQuery("#prevGroup").hide();
    }   
    if (groupid > curgroup ) {
        jQuery("#nextGroup").show();
    }   

    loadcount= curi-2;  // will be used to add qq to questionnairequestions list
    //alert ("load count " +loadcount);
} // loadquestion


// ok now the load questions & usages if this is editing
if (jQuery("#maintAction").attr("value") != 'Copy') {
jQuery("#nextGroup").hide();
jQuery("#prevGroup").hide();
}


var editdata = document.getElementById("editData").value;
// new/edit or when 'copy' is approved, then questions/usage should be displayed
if ((jQuery("#maintAction").attr("value") != 'Copy' || jQuery("#docStatus").attr("value") == 'F') && editdata.indexOf("#;#") > -1 ) {    
    var dataarray = editdata.split("#;#");
    var idxArray = new Array(dataarray.length);
    var questions = dataarray[0].split("#q#");
// qqid/qid/seq/desc/qtypeid/qnum/cond/value
// var parentnum = 0;
    var parentidx = 0;

    loadQuestion();

// TODO : only the first question is expanded
    jQuery("#listcontrol" + firstidx).click();
// jQuery("#listcontrol"+firstidx).click();

    jQuery(".group1").hide();
    jQuery(".group2").hide();
    jumpToAnchor('topOfForm');

    if (dataarray.length > 1) {
        loadUsages(dataarray[1].split("#u#"));    
    } // check dataarray.length

} // end if editdata

function loadUsages(usages) {
	jQuery('.deleteUsage').click(
	    function() {
	    	jQuery(this).next().attr("value", "Yes");
	        curnode = jQuery(this).parents('tr:eq(0)');
	        while (curnode.next().size() > 0) {
	            curnode = curnode.next();
	            curnode.children('th:eq(0)').html(
	                    Number(curnode.children('th:eq(0)')
	                            .html()) - 1)
	        }
	
	        jQuery(this).parents('tr:eq(0)').hide();
	        return false;
	    });
} // end loadusages



function showViewFile(template) {
	if (jQuery("#viewTemplate[style*='none']")) {
       jQuery('#viewTemplate').show();
       jQuery('#fileNameDiv').show();
	}
	jQuery('#templateFileNameHidden').val(jQuery(template).val());
	jQuery('#fileNameDiv').html(jQuery(template).val());
    jQuery('#fileNameDiv').hide();
}
function replaceTemplate(image) {
   jQuery(image).hide();
   jQuery('#templateFileDiv').show();
   jQuery('#fileNameDiv').hide();
   jQuery('#viewTemplate').hide();
   
}

function moduleCodeChange(moduleCode) {
	jQuery.ajax( {
		url : 'maintenanceQn.do',
		type : 'POST',
		dataType : 'html',
		data : 'methodToCall=getSubModuleCodeList&moduleCode=' + jQuery(moduleCode).val(),
		cache : false,
		async : false,
		timeout : 1000,
		error : function() {
			alert('Error loading XML document');
		},
		success : function(xml) {
			jQuery(xml).find('h3').each(function() {
				var text = jQuery(this).html();
				jQuery('#submodulediv').html(jQuery(this).html());

				});
		}
	}); // end ajax

}

/*
 * set up the toggle function for 'requirements' and 'response' tabs' show/hide
 * This is done when the question is clicked for the first time and the maint table is retrieved by ajax call
 */
	function toggleHSControl(idx) {
		jQuery("#HScontrol" + idx).toggle(function() {
			       var curidx = jQuery(this).attr("id").substring(9);
					jQuery("#HSdiv"+curidx).slideDown(400);
					jQuery(this).attr("src", "kr/static/images/tinybutton-hide.gif");
				}, function() {
					var curidx = jQuery(this).attr("id").substring(9);
					jQuery("#HSdiv"+curidx).slideUp(200);
					jQuery(this).attr("src", "kr/static/images/tinybutton-show.gif");
				});
		jQuery("#HScontrol" + idx).click();
		
		jQuery("#HSReqcontrol" + idx).toggle(function() {
		       var curidx = jQuery(this).attr("id").substring(12);
				jQuery("#HSReqdiv"+curidx).slideDown(400);
				jQuery(this).attr("src", "kr/static/images/tinybutton-hide.gif");
			}, function() {
				var curidx = jQuery(this).attr("id").substring(12);
				jQuery("#HSReqdiv"+curidx).slideUp(200);
				jQuery(this).attr("src", "kr/static/images/tinybutton-show.gif");
			});
	    jQuery("#HSReqcontrol" + idx).click();

}
	
function hasChildren(element) {
	var $childrenCount = 0;
	jQuery(element).children("ul").each(function() {
		$childrenCount += jQuery(this).children().size();		
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

	jQuery("#repqid").attr("value", newQuestionId);
	jQuery("#repqdesc").attr("value", newQuestion);
	jQuery("#repqtypeid").attr("value", newQuestionTypeId);
	jQuery("#repqvers").attr("value", newQuestionSequence);
	jQuery("#repqdispans").attr("value", displayedAnswers);
	jQuery("#repqmaxans").attr("value", maxAnswers);
	jQuery("#repqmaxlength").attr("value", answerMaxLength);
	
	addForReplace(replaceNode);
}


function addForReplace(curidx) {
	//alert("replace question "+curidx);
	var idx = curidx;
	var addQn = "#addQn"+curidx
	var originalNodeConfig = jQuery("#qnaireQuestions\\["+ curidx+"\\]").attr("value");
	//alert(jQuery("#repqdesc").attr("value")+"-"+jQuery("#repqdispans").attr("value"))
		if (jQuery("#repqdesc").attr("value") == ''
				|| jQuery("#repqtypeid").attr("value") == '') {
			alert("Please select a question to add");
		} else {
			curi++;

			var radioVal = 'sibling';
			var childNode = 'false';

			//alert(jQuery("#repqdesc").attr("value"));
			var listitem = getQuestionNew(
					jQuery("#repqdesc").attr("value"),
					jQuery("#repqtypeid").attr("value"),
					jQuery("#repqvers").attr("value"),
					jQuery("#repqdispans").attr("value"),
					jQuery("#repqmaxans").attr("value"),
					jQuery("#repqmaxlength").attr("value"),
					childNode);
			var ultag = jQuery('<ul></ul>');
			ultag.appendTo(listitem);
			var idx = listitem.attr("id").substring(8);
			//alert(idx);
			if (radioVal == 'sibling') {
				// alert('sibling');
				var parentUl = jQuery(addQn).parents('li:eq(0)').parents(
						'ul:eq(0)');
				listitem.insertAfter(jQuery(addQn).parents('li:eq(0)'));
				jQuery("#movedn" + idx).hide();
				jQuery("#movedn" + listitem.prev().attr("id")
									  .substring(8)).show();
				// TODO trying to group
				//alert("parent u"+parentUl.attr("id"));
				if (parentUl.attr("id") == 'example') {
					// insert after, so assume current group 
					// TODO : need to adjust group to 20/page ?
					jQuery(listitem).attr("class", "group" + curgroup);
					if (jQuery(".group"+curgroup).size() > 20) {
						adjustGroupDown(); 
					}	
					//addToGroup(listitem);
					if (curgroup == groupid) {
						jQuery("#nextGroup").hide();
					} else if (curgroup > 0) {
						jQuery("#prevGroup").show();
					} else if (groupid > 0) {
						jQuery("#nextGroup").show();
					}
				}
			}

			// also need this to show 'folder' icon
			jQuery('#example').treeview( {
				add : listitem
			});


			// TODO : set up for insert
			/*
			 * questionnairenumber from #questionnairenumber
			 * questionId from #qid sequenceNumber from
			 * jQuery(this).parents('li:eq(0)').siblings().size() ?
			 */
			// jQuery(listitem).parents('ul:eq(0)').parents('li:eq(0)').size()
			// == 0 : check whetehr it is at the top level
			var parentNum;
			if (jQuery(listitem).parents('ul:eq(0)').parents('li:eq(0)')
					.size() == 0) {
				parentNum = 0;
			} else {
				// alert("parents li
				// "+jQuery(listitem).parents('ul:eq(0)').parents('li:eq(0)').attr("id"));
				parentNum = jQuery(
						"#qnum"
								+ jQuery(listitem).parents('ul:eq(0)')
										.parents('li:eq(0)').attr(
												"id").substring(8))
						.attr("value");
			}
			//alert(parentNum);
			//alert(jQuery("#questionNumber").attr("value"));
			jQuery("#qnum" + jQuery(listitem).attr("id").substring(8)).attr(
					"value", jQuery("#questionNumber").attr("value"));
			var qid = jQuery("#repqid").attr("value");
			jQuery("#qid" + jQuery(listitem).attr("id").substring(8)).attr(
					"value", qid);

			var seqnum = Number(jQuery(listitem).siblings().size()) + 1;
			if (radioVal == 'sibling') {
				//var num = Number(jQuery("#qseq"+jQuery(this).attr("id").substring(5)).attr("value"))+1;
				
			    seqnum = Number(jQuery("#qseq"+jQuery(addQn).attr("id").substring(5)).attr("value"))+1;
			   // seqnum = num;
				//alert(seqnum+"-"+jQuery(this).attr("id"));
			    var nextseq = seqnum +1;
			    var nextitem = jQuery(listitem).next();
			    // update seq for the siblings after the new node
			    while (nextitem.size() > 0) {
					var splitq = jQuery("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value").split("#f#");
				    var tmpstr = splitq[0] +"#f#" +splitq[1] 
			        +"#f#" +splitq[2] +"#f#" +splitq[3] +"#f#" +splitq[4] +"#f#" +splitq[5] +"#f#" +splitq[6] +"#f#" +
			        splitq[7] +"#f#" +nextseq +"#f#" +splitq[9] +"#f#" +splitq[10] + "#f#" +splitq[11];
				jQuery("#qnaireQuestions\\["+ nextitem.attr("id").substring(8)+"\\]").attr("value",tmpstr);
//				    jQuery("#"+jqprefix + nextitem.attr("id").substring(8) + "\\]\\.questionSeqNumber").attr("value",nextseq);
			    	jQuery("#qseq" + nextitem.attr("id").substring(8)).attr("value", nextseq++);	
			    	nextitem = nextitem.next();
			    }	
			}    
			
			var qnum = jQuery("#questionNumber").attr("value");
			jQuery("#qseq" + jQuery(listitem).attr("id").substring(8)).attr("value", seqnum);
			jQuery("#questionNumber").attr("value",
					Number(jQuery("#questionNumber").attr("value")) + 1);

			var splitq = originalNodeConfig.split("#f#");
			var cond = splitq[6];
			var value = splitq[7];
			var ruleId =splitq[11];
			idx = jQuery(listitem).attr("id").substring(8);		
            var tmpstr = "" +"#f#" +jQuery('#document\\.newMaintainableObject\\.businessObject\\.id').attr("value")
               +"#f#" +qid +"#f#" +qnum +"#f#" +parentNum +"#f#" + "N" +"#f#" + cond +"#f#" +
               value +"#f#" +seqnum +"#f#" +"1" +"#f#" +"N" +"#f#" + ruleId;
            jQuery("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);

					
		}
	
	    //Let's move any children to this node now
	    var liId = "li#qnaireid" + curidx;
		jQuery(liId).children("ul").children("li").each(function() {
			var tmpIdx = jQuery(this).attr("id").substring(8);
			//alert(tmpIdx);
			clickCut(tmpIdx);
			clickPaste(idx);
		});
		
		//Lets remove the original node
	    clickRemove(curidx);
	    
		return false;
	
}


function clickUpdateQuestionVersion(curidx) {
   //jQuery("#listcontrol"+curidx).click()
	//alert(curidx);
	jQuery.ajax( {
		url : 'maintenanceQn.do',
		type : 'POST',
		dataType : 'html',
		data : 'methodToCall=getQuestionCurrentVersion&qidx=' + curidx +'&questionId='+jQuery('#qid'+curidx).attr('value'),
		cache : false,
		async : false,
		timeout : 1000,
		error : function() {
			alert('Error loading XML document');
		},
		success : function(xml) {
			jQuery(xml).find('input').each(function() {
			    //alert(jQuery(this).attr('id') +":" + jQuery(this).attr('value'));
			    if (jQuery(this).attr('id') == 'questionId') {
					jQuery("#repqid").attr("value", jQuery(this).attr('value'));
			    } else if (jQuery(this).attr('id') == 'question') {
					jQuery("#repqdesc").attr("value", jQuery(this).attr('value'));
			    } else if (jQuery(this).attr('id') == 'questionTypeId') {
					jQuery("#repqtypeid").attr("value", jQuery(this).attr('value'));
			    } else if (jQuery(this).attr('id') == 'questionSequence') {
					jQuery("#repqvers").attr("value", jQuery(this).attr('value'));
			    } else if (jQuery(this).attr('id') == 'displayedAnswers') {
					jQuery("#repqdispans").attr("value", jQuery(this).attr('value'));
			    } else if (jQuery(this).attr('id') == 'maxAnswers') {
					jQuery("#repqmaxans").attr("value", jQuery(this).attr('value'));
			    } else if (jQuery(this).attr('id') == 'answerMaxLength') {
					jQuery("#repqmaxlength").attr("value", jQuery(this).attr('value'));
			    }
			});
			replaceNode = curidx;
			addForReplace(curidx);
		}
	}); // end ajax
	
	return false;
}

function clickSearchRule(fieldId) {
     var winPop = window.open("krmsRuleLookup.do?fieldId="
            + fieldId  + "&anchor=topOfForm",
            "_blank", "width=1000, height=800, scrollbars=yes");
}

function returnRule(ruleId, fieldId) {
	var id = '#' + fieldId;
	id = id.replace(/(:|\.|\[|\])/g,'\\$1');
   jQuery(id).attr("value",ruleId);
}

