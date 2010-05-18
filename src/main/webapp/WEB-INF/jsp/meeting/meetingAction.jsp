<%--
 Copyright 2005-2010 The Kuali Foundation

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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>

<head>
	<script>var jsContextPath = "${pageContext.request.contextPath}";</script>
	<title>Kuali :: Meeting</title>
	
	<c:forEach items="${fn:split(ConfigProperties.css.files, ',')}" var="cssFile">
        <c:if test="${fn:length(fn:trim(cssFile)) > 0}">
			<link href="${pageContext.request.contextPath}/${cssFile}"
				rel="stylesheet" type="text/css" />
        </c:if>
    </c:forEach>
	<c:forEach items="${fn:split(ConfigProperties.javascript.files, ',')}" var="javascriptFile">
        <c:if test="${fn:length(fn:trim(javascriptFile)) > 0}">
			<script language="JavaScript" type="text/javascript"
				src="${pageContext.request.contextPath}/${javascriptFile}"></script>
        </c:if>
    </c:forEach>
<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>

</head>
	<body onload="if ( !restoreScrollPosition() ) {  }"
			onKeyPress="return isReturnKeyAllowed('methodToCall.' , event);">
			
<html:form styleId="kualiForm" action="/meetingManagement.do"
	method="post" 
	onsubmit="return hasFormAlreadyBeenSubmitted();">
	
   <c:set var="KualiForm" value="${KualiForm}" /> 
	<jsp:useBean id="KualiForm" type="org.kuali.rice.kns.web.struts.form.KualiForm" /> 

		<a name="topOfForm"></a>
<div class="headerarea" id="headerarea">
  <h1>Meeting <a href="#"> <img src="kr/static/images/my_cp_inf.gif" alt="help" width=15 height=14 border=0 align=absmiddle onClick="MM_openBrWindow('../kra-coeus-irb/help-pop.html','','scrollbars=yes,resizable=yes,width=500,height=500')"></a></h1>
</div>

<!--TABBED TOP NAVIGATION-->
<div class="horz-links-bkgrnd" id="horz-links">
  <div id="tabs">
    <dl class="tabul">
     	<dt><span class="tabright">
        <input type="submit" name="methodToCall.meeting" value="${KualiForm.meetingHelper.tabLabel}" alt="Meeting">
        </span></dt> 
        <dt class="licurrent" ><span class="tabright tabcurrent">
        <c:choose>
            <c:when test="${!readOnly}">
                <input type="submit" name="methodToCall.headerTab.headerDispatch.save.navigateTo.meetingAction" value="Meeting Actions" alt="Meeting Actions">
            </c:when>
            <c:otherwise>
                <input type="submit" name="methodToCall.headerTab.headerDispatch.reload.navigateTo.meetingAction" value="Meeting Actions" alt="Meeting Actions">
            </c:otherwise>
        </c:choose>
        </span></dt>
    </dl>
  </div>
</div>

<div class="msg-excol">
  <div class="left-errmsg">
    <div class=""><span class="excol"> </span></div>
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
      <kra-meeting:meetingCorrespondence />
          
          
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
 	      <input type="image" name="methodToCall.save" src="kr/static/images/buttonsmall_save.gif"  class="globalbuttons" title="save" alt="save">
	      <input type="image" name="methodToCall.close" src="kr/static/images/buttonsmall_close.gif" class="globalbuttons" title="close" alt="close">
	      <input type="image" name="methodToCall.cancel" src="kr/static/images/buttonsmall_cancel.gif" class="globalbuttons" title="cancel" alt="cancel">
       </div></td>
    <td class="column-right"><img src="static/images/pixel_clear.gif" alt="" width="20" height="20"></td>
  </tr>
</table>
</html:form>
<div id="formComplete"></div> 
</body>
</html:html>
