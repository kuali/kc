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
$(document).ready(function() {
	 // alert($("#globalbuttons").children('input:eq(1)').attr("name"));
    if ($("#maintAction").attr("value") != 'Copy') {
        $("#globalbuttons").find('input').each(function() {
              //alert($(this).attr("name"));
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
//    $("#globalbuttons").children('input:eq(1)').attr("id","save");
//    $("#globalbuttons").children('input:eq(0)').attr("id","route");
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

<%-- if questionnaireMaint.js is moved above the previous scipr, then the 'click' is not working.
    it did not go thru "#save" and go straight to post --%>
<script type="text/javascript" src="scripts/questionnaireMaint.js"></script>

<script type="text/javascript" >
//load question if there are
// alert(editdata);
var editdata = document.getElementById("editData").value;

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
        if (field[0] == 'null') {
        	field[0]="";
        }    
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



</script>