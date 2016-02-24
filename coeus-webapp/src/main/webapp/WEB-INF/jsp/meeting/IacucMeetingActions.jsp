<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<!DOCTYPE html>
<html>

    <head>
        <script>var jsContextPath = "${pageContext.request.contextPath}";</script>
        <title><bean:message key="app.title" /> :: $(headerTitle})</title>
        <style type="text/css">
            #workarea td.tab-subhead1
            {
		        font-weight: bold;
		        background-color: #939393;
		        height: 18px;
		        text-align: left;
		        border-left: 1px solid #999999;
		        color: #FFFFFF;
		        padding: 2px 6px;
		        border-bottom-width: 1px;
		        border-bottom-style: solid;
		        border-bottom-color: #B2B2B2;
            }
        </style>
    <c:forEach items="${fn:split(ConfigProperties.kns.css.files, ',')}"
        var="cssFile">
<c:if test="${fn:length(fn:trim(cssFile)) > 0}">
            <link href="${pageContext.request.contextPath}/${cssFile}"
                rel="stylesheet" type="text/css" />
</c:if>
</c:forEach>
    <c:forEach items="${fn:split(ConfigProperties.kns.javascript.files, ',')}"
        var="javascriptFile">
<c:if test="${fn:length(fn:trim(javascriptFile)) > 0}">
            <script language="JavaScript" type="text/javascript"
                src="${pageContext.request.contextPath}/${javascriptFile}"></script>
</c:if>
</c:forEach>
        <script type="text/javascript" src="scripts/jquery/jquery.tablesorter.js"></script> 
        <script language="JavaScript" type="text/javascript"
				src="dwr/interface/MeetingService.js"></script>
    </head>
	<body onload="if ( !restoreScrollPosition() ) {  }"
			onKeyPress="return isReturnKeyAllowed('methodToCall.' , event);">
			
<html:form styleId="kualiForm" action="/iacucMeetingActions.do"
	method="post" 
	onsubmit="return hasFormAlreadyBeenSubmitted();">
	
   <c:set var="KualiForm" value="${KualiForm}" /> 
	<jsp:useBean id="KualiForm" type="org.kuali.rice.kns.web.struts.form.KualiForm" /> 

    <a name="topOfForm"></a>
	<%-- <div align="center" style="margin: 10px">
	<div id="headermsg" align="left"></div>
	<br /> --%>
		
<div class="headerarea" id="headerarea">
    <h1>Iacuc Meeting 
        <a href="${pageContext.request.contextPath}/kr/help.do?methodToCall=getDocumentHelpText&amp;documentTypeName=CommonCommitteeDocument" tabindex="1000000" target="helpWindow"  title="[Help]document help"><img src="kr/static/images/my_cp_inf.gif" alt="[Help]document help" hspace=5 border=0  align="middle">
        </a>
    </h1>
</div>

<!--TABBED TOP NAVIGATION-->
<div class="horz-links-bkgrnd" id="horz-links">
  <div id="tabs">
    <dl class="tabul">
     	<dt><span class="tabright">
        <c:choose>
            <c:when test="${!readOnly}">
                <input type="submit" name="methodToCall.headerTab.headerDispatch.save.navigateTo.management" value="${KualiForm.meetingHelper.tabLabel}" alt="Meeting">
            </c:when>
            <c:otherwise>
                <input type="submit" name="methodToCall.headerTab.headerDispatch.reload.navigateTo.management" value="${KualiForm.meetingHelper.tabLabel}" alt="Meeting">
            </c:otherwise>
        </c:choose>
        </span></dt> 
        <dt class="licurrent" ><span class="tabright tabcurrent">
        <input type="submit" name="methodToCall.actions" value="Meeting Actions" alt="Meeting  Actions">
        </span></dt>
    </dl>
  </div>
</div>

            <c:set var="errorKey" value="meetingHelper"/>
            <div class="msg-excol">
				<div class="left-errmsg">
					<kul:errorCount auditCount="${auditCount}"/>
					<c:if test="${!empty errorKey}">
						<kul:errors keyMatch="${errorKey}" errorTitle=" "/>
					</c:if>
					<c:if test="${empty errorKey}">
					    <kul:errors keyMatch="${Constants.GLOBAL_ERRORS}"
															errorTitle=" " />
					</c:if>
					<kul:messages/>
					<kul:lockMessages/>
				</div>
            </div>
            
            <div class="right">
                <div class="excol">
                    <html:image property="methodToCall.showAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-expandall.gif" title="show all panel content" alt="show all panel content" styleClass="tinybutton" onclick="return expandAllTab();" />
                    <html:image property="methodToCall.hideAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-collapseall.gif" title="hide all panel content" alt="hide all panel content" styleClass="tinybutton" onclick="return collapseAllTab();" />
                </div>
            </div>

<table width="100%" cellpadding="0" cellspacing="0">
  <tr>
    <td class="column-left"><img src="static/images/pixel_clear.gif" alt="" width="20" height="20"></td>
    <td><div  align="center">
        <div id="workarea">
        
    <!-- Tabbed Panel Head: Meeting details -->

            
      <kra-meeting:meetingAgenda />
    
      <kra-meeting:meetingActionMinutes />
      <kra-meeting:meetingActionPrint />
      <kra-meeting:meetingCorrespondence />
          
      <input type="hidden" name="meetingHelper.viewId" id="meetingHelper.viewId" value="${KualiForm.meetingHelper.viewId}"/>
          
	<!-- Tabbed Panel Footer -->    
    <div class="tab-container" align="center" id="G125" style="display: none;"></div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
            <tr>
                <td align="left" class="footer"><img src="static/images/pixel_clear.gif" alt="" width="12" height="14" class="bl3"></td>
                <td align="right" class="footer-right"><img src="static/images/pixel_clear.gif" alt="" width="12" height="14" class="br3"></td>
            </tr>
        </table>
    </div>
        
        
        <div align="right"><br>
          * required </div>
        <div id="globalbuttons" class="globalbuttons"> 
          <c:if test = "${KualiForm.meetingHelper.canModifySchedule}">
 	      	<input type="image" name="methodToCall.save" src="kr/static/images/buttonsmall_save.gif"  class="globalbuttons" title="save" alt="save">
 	      </c:if>
	      <input type="image" name="methodToCall.close" src="kr/static/images/buttonsmall_close.gif" class="globalbuttons" title="close" alt="close">
	      <c:if test = "${KualiForm.meetingHelper.canModifySchedule}">
	      	<input type="image" name="methodToCall.cancel" src="kr/static/images/buttonsmall_cancel.gif" class="globalbuttons" title="cancel" alt="cancel">
	      </c:if>
       </div></td>
    <td class="column-right"><img src="static/images/pixel_clear.gif" alt="" width="20" height="20"></td>
  </tr>
</table>
</html:form>
<div id="formComplete"></div> 
</body>
    <SCRIPT type="text/javascript">
    var kualiForm = document.forms['KualiForm'];
    var kualiElements = kualiForm.elements;

    $(document).ready(function()     {
       var viewId = $("#meetingHelper\\.viewId").attr("value");
       if (viewId) {
         //  $("#"+viewId).click();
           //window.open(extractUrlBase()+"/"+action+".do?methodToCall="+viewId.substr(0,viewId.length - 1)+"&line="+(viewId.substr(viewId.length - 1))+"&docFormKey=0&documentWebScope=false");
           openNewWindow('iacucMeetingActions',viewId.substr(0,viewId.length - 1),viewId.substr(viewId.length - 1) - 1,0,'false');            
           $("#meetingHelper\\.viewId").attr("value","");
       }
    } ); 

    </SCRIPT> 

</html>
