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
      <c:when test = "${KualiForm.document.newMaintainableObject.maintenanceAction eq 'Copy'}">
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
      $("#globalbuttons").children('input:eq(1)').attr("id","save");
      $("#globalbuttons").children('input:eq(0)').attr("id","route");
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


// load usage
// TODO : need to load 'questionnaire version too because it will be part of the uniqueness check
// quid/modulecode/label/vers

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
        $('<div align="center">').html(inputtmp).appendTo(tdtmp);
        tdtmp.appendTo(trtmp);
        ucount++;
        trtmp.appendTo($("#usage-table"));

    }
    // end for load usages
} // check dataarray.length

} // end if editdata

function loadQuestion() {
    var qlen = questions.length;
    var rootidx;
    var pnum0 = 0;
    var nodecount = 0;
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
            var listitem = getQuestionNew(field[3], field[4], "V1.01", ischild);
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

            if (parentnum > 0 && field[6] != 'null') {
                // alert ("add req for parent
                // "+$("#addrequirement"+i).parents('tr:eq(0)').size());
                var newResponse = getRequirementDeleteRow(
                        responseArray[field[6]], field[7]);
                newResponse.appendTo($("#addrequirement" + i).parents(
                        'div:eq(0)').children('table:eq(0)').children('tbody'));
                $("#cond" + i).attr("value", field[6]);
                $("#condvalue" + i).attr("value", field[7]);
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

} // loadquestion



</script>