<%--
 Copyright 2005-2008 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ page
	import="org.kuali.rice.kns.exception.KualiExceptionIncident"%>
<%@ include file="tldHeader.jsp"%>

<c:set var="textAreaAttributes"
	value="${DataDictionary.AttributeReferenceElements.attributes}" />

<%
Object incident=request.getAttribute("org.kuali.rice.kns.web.struts.pojo.KualiExceptionIncident");
request.setAttribute("test", incident);
%>

<c:set var="parameters"
       value="<%=request.getAttribute(\"org.kuali.rice.kns.web.struts.action.KualiExceptionHandlerAction\")%>" />

<c:if test="${not empty parameters}">
	<c:set var="documentId"       value="${parameters.documentId}" />
	<c:set var="userEmail"        value="${parameters.userEmail}" />
	<c:set var="userName"         value="${parameters.userName}" />
	<c:set var="principalName"         value="${parameters.principalName}" />
	<c:set var="componentName"    value="${parameters.componentName}" />
	<c:set var="exceptionReportSubject" value="${parameters.exceptionReportSubject}" />
	<c:set var="exceptionMessage" value="${parameters.exceptionMessage}" />
	<c:set var="displayMessage"   value="${parameters.displayMessage}" />
	<c:set var="stackTrace"       value="${parameters.stackTrace}" />
	<c:set var="exceptionHideIncidentReport" value="${parameters.exceptionHideIncidentReport}" />

</c:if>
<c:if test="${empty documentId}">
	<c:set var="documentId"
       value="<%=request.getParameter(KualiExceptionIncident.DOCUMENT_ID)%>" />
</c:if>
<c:if test="${empty userEmail}">
	<c:set var="userEmail"
       value="<%=request.getParameter(KualiExceptionIncident.USER_EMAIL)%>" />
</c:if>
<c:if test="${empty userName}">
	<c:set var="userName"
       value="<%=request.getParameter(KualiExceptionIncident.USER_NAME)%>" />
</c:if>
<c:if test="${empty principalName}">
	<c:set var="principalName"
       value="<%=request.getParameter(KualiExceptionIncident.UUID)%>" />
</c:if>
<c:if test="${empty componentName}">
	<c:set var="componentName"
       value="<%=request.getParameter(KualiExceptionIncident.COMPONENT_NAME)%>" />
</c:if>
<c:if test="${empty exceptionReportSubject}">
	<c:set var="exceptionReportSubject"
       value="<%=request.getParameter(KualiExceptionIncident.EXCEPTION_REPORT_SUBJECT)%>" />
</c:if>
<c:if test="${empty exceptionMessage}">
	<c:set var="exceptionMessage"
       value="<%=request.getParameter(KualiExceptionIncident.EXCEPTION_MESSAGE)%>" />
</c:if>
<c:if test="${empty displayMessage}">
	<c:set var="displayMessage"
       value="<%=request.getParameter(KualiExceptionIncident.DISPLAY_MESSAGE)%>" />
</c:if>
<c:if test="${empty stackTrace}">
	<c:set var="stackTrace"
       value="<%=request.getParameter(KualiExceptionIncident.STACK_TRACE)%>" />
</c:if>

<c:set var="docTitle" value="Incident Report" />

<c:if test="${exceptionHideIncidentReport eq true}">
	<c:set var="docTitle" value="Error" />
</c:if>

<kul:page showDocumentInfo="false"
	headerTitle="Incident Report"
	docTitle="${docTitle}"
	transactionalDocument="false"
	htmlFormAction="kualiExceptionIncidentReport"
	defaultMethodToCall="notify"
	errorKey="*">
  <html:hidden property="documentId"       write="false" value="${documentId}" />
  <html:hidden property="userEmail"        write="false" value="${userEmail}" />
  <html:hidden property="userName"         write="false" value="${userName}" />
  <html:hidden property="principalName"         write="false" value="${principalName}" />
  <html:hidden property="componentName"    write="false" value="${componentName}" />
  <html:hidden property="exceptionReportSubject" write="false" value="${exceptionReportSubject}" />
  <html:hidden property="exceptionMessage" write="false" value="${exceptionMessage}" />
  <html:hidden property="displayMessage"   write="false" value="${displayMessage}" />
  <html:hidden property="stackTrace"       write="false" value="${stackTrace}" />
  <html:hidden property="exceptionHideIncidentReport" write="false" value="${exceptionHideIncidentReport}" />

<c:if test="${exceptionHideIncidentReport eq false}">
  <div align="center">
    <font color="blue" size="3">Please use the Incident Report form below to report the problems</font>
  </div>
  <br/>
</c:if>
	<div class="topblurb">
		<div align="center">
			<table cellpadding="10" cellspacing="0" border="0" class="container2">
			  <c:if test="${exceptionHideIncidentReport eq false}">	
				 <tr>
					<td colspan="2" class="infoline">
						<div align="left">
							<font color="blue">This information will be forwarded to
								our support team. Please describe what action you were taking
								when the problem occurred</font>
						</div>
					</td>
				 </tr>
				<tr>
					<td>
						<div align="left" valign="top">
							<strong>Document Id</strong>
						</div>
					</td>
					<td align="left" valign="top">
						<div align="left">
							<font color="green">${documentId}</font>
						</div>
					</td>
				</tr>
               </c:if>
				<tr>
					<td>
						<div align="left" valign="top">
							<strong>Error Message</strong>
						</div>
					</td>
					<td align="left">
						<div align="left">
							<font color="red">${displayMessage}</font>
						</div>
					</td>
				</tr>
			  <c:if test="${exceptionHideIncidentReport eq false}">		
				<tr>
					<td valign="top" align="left">
						<div align="left">
							<strong>User Input</strong>
						</div>
					</td>
					<td align="left" valign="top">
						<textarea name='description' rows='5' cols='100' maxlength='1000'></textarea>
					</td>
				</tr>
			  </c:if>
				<tr>
					<td>
						&nbsp;
					</td>
					<td align="left">
						<div>
							<c:if test="${exceptionHideIncidentReport eq false}">	
								<input type="image" name="submit" class="tinybutton"
								src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_submit.gif"
								class="globalbuttons" title="submit" alt="Submit Incident">
							</c:if>
							<input type="image" name="cancel" value="true" class="tinybutton"
								src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif"
								class="globalbuttons" title="close" alt="Close Without Submitting Incident">
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<br />
	<c:if test="${exceptionHideIncidentReport eq false && !KualiConfigurationService.isProductionEnvironment}">
		<table>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td valign="top" colspan="2">
					<div align="left" valign="top">
						<strong>******************Stack Trace-Only shown when not
							in production*****************</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td align="left" valign="top" colspan="2">
					<div align="left">
						<pre>${stackTrace}</pre>
					</div>
				</td>
			</tr>
		</table>
	</c:if>
</kul:page>

