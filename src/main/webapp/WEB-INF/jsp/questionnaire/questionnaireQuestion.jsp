<%--
 Copyright 2006-2009 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<script language="JavaScript" type="text/javascript" src="dwr/engine.js"></script>

<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>

<script language="JavaScript" type="text/javascript"
    src="dwr/interface/CustomAttributeService.js"></script>

<script language="JavaScript" type="text/javascript"
    src="dwr/interface/SponsorService.js"></script>


<script src="scripts/jquery/jquery.js"></script>
<link rel="stylesheet" href="css/jquery/screen.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
<%-- link rel="stylesheet" href="http://dev.jquery.com/view/trunk/plugins/treeview/jquery.treeview.css" type="text/css" /--%>
<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>



<%--  

    <div align="center" style="margin: 10px">
    <div id="headermsg" align="left"></div>
    <br />
--%>
<kul:tab defaultOpen="true" tabTitle="Questionnaire Details, Content & Use "
    tabErrorKey="document.newMaintainableObject.businessObject*">
   <%-- 
        <div align="center" style="margin: 10px">
    <div id="headermsg" align="left"></div>
    <br />--%> 
     
    <c:choose>
      <c:when test = "${KualiForm.document.newMaintainableObject.maintenanceAction eq 'Copy' and !(KualiForm.document.documentHeader.workflowDocument.routeHeader.docRouteStatus eq 'F')}">
        <kra-questionnaire:questionnaireMaintCopy />      
      </c:when>
      <c:otherwise>
    <kra-questionnaire:questionnaireMaintCore />
    <kra-questionnaire:questionnaireQuestion />
    <kra-questionnaire:questionnaireMaintUsage />
      </c:otherwise>
     </c:choose>
</kul:tab>
    <input type="hidden" id="maintAction" name="maintAction" value = "${KualiForm.document.newMaintainableObject.maintenanceAction}"/>
     <input type="hidden" id="docStatus" name="docStatus" value="${KualiForm.document.documentHeader.workflowDocument.routeHeader.docRouteStatus }"  />   
     <input type="hidden" id="readOnly" name="docStatus" value="${KualiForm.readOnly}"  />   
     <input type="hidden" id="numOfQuestions" name="numOfQuestions" value="${KualiForm.numOfQuestions}"  />   
<%-- 
    <div id="globalbuttons" class="globalbuttons"><input
        type="image" id="save"
        src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" />
    <a
        href='portal.do?methodToCall=refresh&amp;docFormKey=88888888&amp;anchor=&amp;docNum='
        title="cancel"> <img
        src="kr/static/images/buttonsmall_cancel.gif" class="tinybutton"
        alt="cancel" title="cancel" border="0" /> </a></div>
    </div>
--%>

<script type="text/javascript" src="scripts/questionnaireNew.js"></script>

<script type="text/javascript" >
var qnversion=1;
$(document).ready(function() {
	  //alert($("#globalbuttons").children('input:eq(1)').attr("name"));
      if ($("#maintAction").attr("value") != 'Copy') {
          $("#globalbuttons").find('input').each(function() {
               // alert($(this).attr("name"));
                if ($(this).attr("name") == 'methodToCall.route') {
                    $(this).attr("id","route");
                } else if ($(this).attr("name") == 'methodToCall.save') {
                    $(this).attr("id","save");
                } else if ($(this).attr("name") == 'methodToCall.blanketApprove') {
                    $(this).attr("id","blanketApprove");
                } else if ($(this).attr("name") == 'methodToCall.close') {
                    $(this).attr("id","close");
                }   
            });
//      $("#globalbuttons").children('input:eq(1)').attr("id","save");
//      $("#globalbuttons").children('input:eq(0)').attr("id","route");
      }
      if ($("#maintAction").attr("value") == 'Edit') {
          if ($("#docStatus").attr("value") == 'I') {
    	    qnversion = Number($("#document\\.newMaintainableObject\\.businessObject\\.sequenceNumber").attr("value"))+1;
          } else {
              qnversion = $("#document\\.newMaintainableObject\\.businessObject\\.sequenceNumber").attr("value");
          }      
      } else if ($("#maintAction").attr("value") == 'New') {
    	  qnversion = $("#document\\.newMaintainableObject\\.businessObject\\.sequenceNumber").attr("value");
      }
	  //alert($("#globalbuttons").children('input:eq(1)').attr("id"));
	  //$("#globalbuttons").children('input:eq(1)').hide();


	  
});  



</script>
<script type="text/javascript" src="scripts/questionnaireMaint.js"></script>


<script type="text/javascript" >
//load question if there are
var firstidx = -1;
var loadcount = 0;
var initucount = 0;

var editdata = document.getElementById("editData").value;
// alert(editdata);
if (editdata.indexOf("#;#") > -1 ) {
    

var dataarray = editdata.split("#;#");
var idxArray = new Array(dataarray.length);
// alert (retdata+"-"+dataarray[0]);
var questions = dataarray[0].split("#q#");
// qqid/qid/seq/desc/qtypeid/qnum/cond/value
// var parentnum = 0;
var parentidx = 0;

loadQuestion();
// if (questions.length > loadcount) {
// loadIntervalId = setInterval ( "loadQuestion()", 5000 );
// }

// TODO : only the first question is expanded
$("#listcontrol" + firstidx).click();
// $("#listcontrol"+firstidx).click();

// TODO : test grouping questions
// alert("groupidx = "+groupid+$(".group1:eq(0)").attr("id"));
$(".group1").hide();
$(".group2").hide();
jumpToAnchor('topOfForm');

// TODO : remove it later
//  $("#"+jqprefix + "2\\]\\.questionSeqNumber").attr("value",2);
//  $("#"+jqprefix + "3\\]\\.questionSeqNumber").attr("value",1);
//alert($("#"+jqprefix + "2\\]\\.questionSeqNumber").attr("value"))

// load usage
// TODO : need to load 'questionnaire version too because it will be part of the uniqueness check
// quid/modulecode/label/qver/subitem/rule/ver

if (dataarray.length > 1) {
    var usages = dataarray[1].split("#u#");
    for ( var k = 0; k < usages.length; k++) {
        field = usages[k].split("#f#");
        trtmp = $('<tr/>').attr("id", "usage" + ucount);
        thtmp = $('<th class="infoline"/>').html(ucount);
        thtmp.appendTo(trtmp);
        // tdtmp = $('<td align="left" valign="middle">').html(field[1]);
        var tdtmp = $('<td align="left" valign="middle">').html(
                moduleCodes[field[1]]);
        var modulecode = $('<input type="hidden"/>').attr("value", field[1]);
        modulecode.appendTo(tdtmp);
        tdtmp.appendTo(trtmp);
        tdtmp = $('<td align="left" valign="middle">').html(field[2]);
        tdtmp.appendTo(trtmp);
        // TODO : questionnaire version# will be loaded later
        //tdtmp = $('<td align="left" valign="middle">').html(field[2]);  
        tdtmp = $('<td align="left" valign="middle">').html(field[3]);  
        tdtmp.appendTo(trtmp);
        inputtmp = $(
                '<input type="image" id="deleteUsage" name="deleteUsage" src="static/images/tinybutton-delete1.gif" class="tinybutton">')
                .attr("id", "deleteUsage" + ucount).click(
                        function() {
                            addSqlScripts("delete U;"
                                    + $(this).parents('tr:eq(0)').children(
                                            'td:eq(0)').children('input:eq(0)')
                                            .attr("value")
                                            +";"+ $(this).parents('tr:eq(0)').children(
											'td:eq(2)').html());
                                                        // alert(sqlScripts);
								$("#utr"+$(this).attr("id").substring(11)).remove();
								ucount--;
								alert(ucount)
                            curnode = $(this).parents('tr:eq(0)');
                            // alert("size "+curnode.next().size());
                            while (curnode.next().size() > 0) {
                                curnode = curnode.next();
                                // alert(Number(curnode.children('th:eq(0)').html())-1);
                                curnode.children('th:eq(0)').html(
                                        Number(curnode.children('th:eq(0)')
                                                .html()) - 1)
                                // curnode=curnode.next();
                            }

                            $(this).parents('tr:eq(0)').remove();
                            return false;
                        });
        tdtmp = $('<td align="left" valign="middle">');
        if ($("#readOnly").attr("value") != 'true') {
        $('<div align="center">').html(inputtmp).appendTo(tdtmp);
        } else {
            $('<div align="center">').appendTo(tdtmp);
        }    
        tdtmp.appendTo(trtmp);
        trtmp.appendTo($("#usage-table"));

        // usage hidden fields
		var hidtr = $('<tr id = "utr" name = "utr"/>').attr("id","utr"+ucount).attr("name", "utr"+ucount);
        var hidtd = $('<td colspan="2"/>');
        // question id for this node
        var qntag = $('<input type="hidden" id = "usage" name = "usage" />').attr("id",
        		uprefix + ucount+"].questionnaireUsageId").attr("name", uprefix + ucount+"].questionnaireUsageId")
                .attr("value",field[0]);
        qntag.appendTo(hidtd);
        qntag = $('<input type="hidden" id = "usage" name = "usage" />').attr("id",
                uprefix + ucount+"].moduleItemCode").attr("name", uprefix + ucount+"].moduleItemCode")
                .attr("value",field[1]);
        qntag.appendTo(hidtd);
        
        qntag = $('<input type="hidden" id = "usage" name = "usage" />').attr("id",
                uprefix + ucount+"].moduleSubItemCode").attr("name", uprefix + ucount+"].moduleSubItemCode")
                .attr("value",field[4]);
        qntag.appendTo(hidtd);
        qntag = $('<input type="hidden" id = "usage" name = "usage" />').attr("id",
                uprefix + ucount+"].questionnaireLabel").attr("name", uprefix + ucount+"].questionnaireLabel")
                .attr("value",field[2]);
        qntag.appendTo(hidtd);
        qntag = $('<input type="hidden" id = "usage" name = "usage" />').attr("id",
                uprefix + ucount+"].questionnaireSequenceNumber").attr("name", uprefix + ucount+"].questionnaireSequenceNumber")
                .attr("value",field[3]);
        qntag.appendTo(hidtd);
        qntag = $('<input type="hidden" id = "usage" name = "usage" />').attr("id",
                uprefix + ucount+"].ruleId").attr("name", uprefix + ucount+"].ruleId")
                .attr("value",field[5]);
        qntag.appendTo(hidtd);
        qntag = $('<input type="hidden" id = "usage" name = "usage" />').attr("id",
                uprefix + ucount+"].versionNumber").attr("name", uprefix + ucount+"].versionNumber")
                .attr("value",field[6]);
        qntag.appendTo(hidtd);
        qntag = $('<input type="hidden" id = "usage" name = "usage" />').attr("id",
                uprefix + ucount+"].questionnaireRefIdFk").attr("name", uprefix + ucount+"].questionnaireRefIdFk")
                .attr("value",$('#document\\.newMaintainableObject\\.businessObject\\.questionnaireRefId').attr("value"));
        qntag.appendTo(hidtd);
        
        hidtd.appendTo(hidtr);
        hidtr.hide(); // FF rendering issue. If not hided, then 'line' will be
        // drawn at the bottom of the table for each Q hidden row
        //hidtr.appendTo($("#qhiddiv"));
        hidtr.appendTo($("#usage-table"));
        ucount++;

        
    }
    initucount = ucount-1 ;
    // end for load usages
} // check dataarray.length

} // end if editdata

function loadQuestion() {
    var qlen = questions.length;
    var rootidx;
    var pnum0 = 0;
    var nodecount = 0;
    var refid = $('#document\\.newMaintainableObject\\.businessObject\\.questionnaireRefId').attr("value");
    for ( var k = 0; k < qlen; k++) {
        var curq = questions[k];
        // alert("parent 0 "+k+"-"+curq+pnum0+"-"+nodecount+"-"+qlen);
        if (curq.indexOf("parent-") == 0) {
            // parentnum = curq.substring(7);
            // for (var l = 1 ; l <= k+1; l++) {
            // if ($("#qnum"+l).attr("value")) {
            // if ($("#qnum"+l).attr("value") == parentnum) {
            // parentidx = l;
            // }
            // }
            // }
        } else {

            var field = curq.split("#f#");
            var parentnum = field[8];
            i++;
            var parenturl;
            var ischild = 'false';
            if (parentnum == 0) {
                // if ((pnum0 == 20 && loadcount != 0) || (loadcount == 0 &&
                // pnum0 == 40)) {
                // //alert("break");
                // break;
                // }
                parenturl = $('#example');
                pnum0++;
                rootidx = i;
                // alert("parent 0 "+pnum0+"-"+nodecount);

            } else {
                if (field[2] == 1) { // the first children
                    parentidx = i - 1;
                } else {
                    for ( var l = rootidx; l <= i - 1; l++) {
                        if ($("#qnum" + l).attr("value")) {
                            if ($("#qnum" + l).attr("value") == parentnum) {
                                parentidx = l;
                            }
                        }
                    }
                }
                parenturl = $("#qnaireid" + parentidx).children('ul:eq(0)');
                ischild = 'true';
            }
            nodecount++;
            var listitem = getQuestionNew(field[3], field[4], field[9], field[10], field[11], field[12], ischild);
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

            // TODO : test idea of display page by page with hide().
            if (parentnum == 0) {
                addToGroup(listitem);
                if (groupid > 0) {
                    $(listitem).hide();
                }
            }

            if ($(listitem).parents('ul:eq(0)').children('li').size() == 1) {
                $("#moveup" + idx).hide();
                $("#movedn" + idx).hide();
            } else {
                $("#movedn" + idx).hide();
                if (listitem.prev().size() > 0) {
                    $("#movedn" + listitem.prev().attr("id").substring(8))
                            .show();
                }
            }

           // alert ("parent "+parentnum+"-"+field[1]+"-"+field[6]);
            if (parentnum > 0 && field[6] != 'null') {
                // alert ("add req for parent
                // "+$("#addrequirement"+i).parents('tr:eq(0)').size());
                var newResponse = getRequirementDeleteRow(
                        responseArray[field[6]], field[7]);
                newResponse.appendTo($("#addrequirement" + i).parents(
                        'div:eq(0)').children('table:eq(0)').children('tbody'));
//                $("#cond" + i).attr("value", field[6]);
//                $("#condvalue" + i).attr("value", field[7]);
                $("#addrequirement" + i).parents('tr:eq(0)').remove();
            }
            // TODO : set up for insert
            /*
             * questionnairenumber from #questionnairenumber questionId from
             * #qid sequenceNumber from
             * $(this).parents('li:eq(0)').siblings().size() ?
             */

            $("#qnum" + idx).attr("value", field[5]);
            $("#qid" + idx).attr("value", field[1]);
            $("#qseq" + idx).attr("value", field[2]);
            // set up qdesc & qtypeid
            $("#qdesc" + idx).attr("value", field[3]);
            $("#qtypeid" + idx).attr("value", field[4]);
            $("#qvers" + idx).attr("value", field[9]);
            $("#qdispans" + idx).attr("value", field[10]);
            $("#qansmax" + idx).attr("value", field[11]);
            $("#qmaxlength" + idx).attr("value", field[12]);
            //alert("set to 123 :"+idx)
            // qqid/qid/seq/desc/qtypeid/qnum/cond/condvalue/parentqnum/questionseqnum
            if (field[0] == '' || field[0] == null) {
                alert("qqid is null "+questions[k]);
            }    

            var tmpstr = field[0] +"#f#" +refid
            +"#f#" +field[1] +"#f#" +field[5] +"#f#" +field[8] +"#f#" +field[14] +"#f#" +field[6] +"#f#" +
            field[7] +"#f#" +field[2] +"#f#" +field[13] +"#f#" +"N" ;
    $("#qnaireQuestions\\["+ idx+"\\]").attr("value",tmpstr);

//            $("#"+jqprefix + idx+"\\]\\.questionnaireQuestionsId").attr("value",field[0]);
//            $("#"+jqprefix + idx+"\\]\\.questionnaireRefIdFk").attr("value",refid);
//            $("#"+jqprefix + idx+"\\]\\.questionRefIdFk").attr("value",field[1]);
//            $("#"+jqprefix + idx+"\\]\\.questionNumber").attr("value",field[5]);
//            $("#"+jqprefix + idx+"\\]\\.parentQuestionNumber").attr("value",field[8]);
//            $("#"+jqprefix + idx+"\\]\\.conditionFlag").attr("value",field[14]);
//            $("#"+jqprefix + idx+"\\]\\.condition").attr("value",field[6]);
//            $("#"+jqprefix + idx+"\\]\\.conditionValue").attr("value",field[7]);
//            $("#"+jqprefix + idx+"\\]\\.questionSeqNumber").attr("value",field[2]);
//            $("#"+jqprefix + idx+"\\]\\.versionNumber").attr("value",field[13]);
//            $("#"+jqprefix + idx+"\\]\\.deleted").attr("value","N");
            //alert($("#document\\.newMaintainableObject\\.businessObject\\.questionnaireQuestions\\[" + idx+"\\]\\.questionnaireQuestionsId").attr("value"));
            
        } // end if-then-else
    } // end for to set up questions

    //alert(groupid+"-grp "+curgroup);
    if (groupid == 0) {
        $("#nextGroup").hide();
        $("#prevGroup").hide();
    }   
    if (curgroup == 0) {
        $("#prevGroup").hide();
    }   
    if (groupid > curgroup ) {
        $("#nextGroup").show();
    }   

    loadcount=i-2;  // will be used to add qq to questionnairequestions list
    //alert ("load count " +loadcount);
} // loadquestion



</script>