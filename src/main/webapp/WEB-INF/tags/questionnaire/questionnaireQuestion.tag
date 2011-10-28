<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="readOnly" value="false" scope="request" />
<a name="questionnaireQuestion"></a>
<div class="tab-container" align="center">
<h3><span class="subhead-left"><a href="#"
    class="questionpanelcontrol"><img src='kr/images/tinybutton-show.gif'
    alt='show/hide panel' title='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a>
Content </span> <span class="subhead-right"> 
<html:image property="methodToCall.printQuestionnaire"
	src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printdark.gif' styleClass="tinybutton"
   alt="Print Questionnaire Answer" title="Print Questionnaire Answer" onclick="excludeSubmitRestriction = true;"/> 
   <a title="[Help]help" target="helpWindow" href="${ConfigProperties.application.url}/kr/help.do?methodToCall=getBusinessObjectHelpText&amp;businessObjectClassName=org.kuali.kra.questionnaire.Questionnaire">
   <img styleClass="tinybutton"
  alt="[Help]help" src="${ConfigProperties.kr.externalizable.images.url}my_cp_inf.gif"></a>
<%-- 
<kul:help
    businessObjectClassName="org.kuali.kra.questionnaire.Questionnaire"
    altText="help" /> --%>
    </span></h3>
<div id="questionpanelcontent">

<table id="question-table" width="100%" cellpadding="0" cellspacing="0"
    class="datatable">

    <tr>
        <td colspan="2"><!-- Section Controls -->
        <div style="padding-bottom: 5px;">
        <table width="100%" cellpadding="0" cellspacing="0" class="subelement"
            style="border-top: solid 1px #939393;">
            <tr>
                <td class="content_grey"
                    style="text-align: right; width: 190px;">Hierarchy
                Display Options:</td>
                <td class="content_white" style="text-align: center;">
                <div id="treecontrol" style="display: inline;"><a
                    title="Collapse the entire tree below" href="#"><img
                    src="/${fn:trim(ConfigProperties.app.context.name)}/static/images/jquery/minus.gif" />
                Collapse All</a> &nbsp;&nbsp;&nbsp;&nbsp;<a
                    title="Expand the entire tree below" href="#"><img
                    src="/${fn:trim(ConfigProperties.app.context.name)}/static/images/jquery/plus.gif" />
                Expand All</a></div>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <%-- 
                <input
                    src="/${fn:trim(ConfigProperties.app.context.name)}/static/images/tinybutton-refresh.gif"
                    onclick="javascript: alert('This would refresh the hierarchies in this questionnaire for non-script browsers.'); return false;"
                    style="border: none;" alt="refresh" title="Refresh"
                    type="image" /> 
                  --%>  
                    <input name="nextGroup" id="nextGroup"
                    src="static/images/tinybutton-next.gif"
                    style="border: none;" alt="Continue" type="image" /> <input
                    name="prevGroup" id="prevGroup"
                    src="static/images/tinybutton-back.gif"
                    style="border: none;" alt="Back" type="image" /></td>
                <td class="content_grey" style="text-align: right;">Add
                Question(s):</td>
                <c:if test="${!KualiForm.readOnly}">
                <td class="content_white" style="text-align: center;">
               <%--    <input
                    type="hidden" type="text" id="newqdesc0" name="newqdesc0"
                    size="50" value="" /> 
                    <input type="hidden" id="newqid0"
                    name="newqid0" value="" /> <input type="hidden"
                    id="newqtypeid0" name="newqtypeid0" value="" /> --%> 
                    <input
                    name="rootSearch" id="rootSearch"
                    src="static/images/searchicon.gif" align="top" alt="search "
                    title="Search " style="border: none;" type="image" /> <%--<input name="addRootQn" id="addRootQn" src="kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" type="image" /> --%>
                </td>
                </c:if>
            </tr>
        </table>

        </div>

        </td>
    </tr>

    <tr>
        <td colspan="2">
        <ul id="example" class="filetree">
        </ul>
        </td>
    </tr>

   <div id="qhiddiv">
   </div>

   <div id="hiddiv">
   </div>
    <input type="hidden" id="qncount" value="${index}" />
    
    <!--  required by modify question -->
    <input type="hidden" id="repqid" value=""/>
    <input type="hidden" id="repqdesc" value=""/>
    <input type="hidden" id="repqtypeid" value=""/>
    <input type="hidden" id="repqvers" value=""/>
    <input type="hidden" id="repqdispans" value=""/>
    <input type="hidden" id="repqmaxans" value=""/>
    <input type="hidden" id="repqmaxlength" value=""/>
</table>
</div>
</div>


<script>

				$("#questionpanelcontent").hide();
				$("a.questionpanelcontrol").toggle(
					function()
					{
						$("#questionpanelcontent").slideDown(500);
						$("a.questionpanelcontrol").html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' title='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
					},function(){
						$("#questionpanelcontent").slideUp(500);
						$("a.questionpanelcontrol").html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' title='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
					}
				);
   </script>