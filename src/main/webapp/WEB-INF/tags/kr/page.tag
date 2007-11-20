<%--
 Copyright 2005-2007 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="docTitle" required="true" %>
<%@ attribute name="transactionalDocument" required="true" %>
<%@ attribute name="showDocumentInfo" required="false" %>
<%@ attribute name="headerMenuBar" required="false" %>
<%@ attribute name="headerTitle" required="false" %>
<%@ attribute name="htmlFormAction" required="false" %>
<%@ attribute name="renderMultipart" required="false" %>
<%@ attribute name="showTabButtons" required="false" %>
<%@ attribute name="headerDispatch" required="false" %>
<%@ attribute name="lookup" required="false" description="indicates whether the lookup page specific page should be shown" %>

<%-- for non-lookup pages --%>
<%@ attribute name="headerTabActive" required="false" %>
<%@ attribute name="feedbackKey" required="false" description="application resources key that contains feedback contact address only used when lookup attribute is false"%>
<%@ attribute name="defaultMethodToCall" required="false" %>
<%@ attribute name="errorKey" required="false" %>
<%@ attribute name="auditCount" required="false" %>
<%@ attribute name="additionalScriptFiles" required="false" type="java.util.List" %>
<%@ attribute name="documentWebScope" required="false" %>

<%-- Is the screen an inquiry? --%>
<c:set var="_isInquiry" value="${requestScope[Constants.PARAM_MAINTENANCE_VIEW_MODE] eq Constants.PARAM_MAINTENANCE_VIEW_MODE_INQUIRY}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>

<c:if test="${empty headerTitle}">
	<c:set var="headerTitle" value="${docTitle}"/>
</c:if>

<head>
	<script>var jsContextPath = "${pageContext.request.contextPath}";</script>
	<title><bean:message key="app.title" /> :: ${headerTitle}</title>
<c:forEach items="${fn:split(ConfigProperties.css.files, ',')}" var="cssFile">
<c:if test="${fn:length(fn:trim(cssFile)) > 0}">
	<link href="${pageContext.request.contextPath}/${cssFile}" rel="stylesheet" type="text/css" />
</c:if>
</c:forEach>
<c:forEach items="${fn:split(ConfigProperties.javascript.files, ',')}" var="javascriptFile">
<c:if test="${fn:length(fn:trim(javascriptFile)) > 0}">
	<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/${javascriptFile}"></script>
</c:if>
</c:forEach>
	<c:choose>
		<c:when test="${lookup}" >
			  <c:if test="${not empty KualiForm.headerNavigationTabs}">
				<link href="kr/css/${KualiForm.navigationCss}" rel="stylesheet" type="text/css" />
			  </c:if>

			  <!-- Set the focus to first text box on form -->
			  <script type="text/javascript">
			  function placeFocus() {
				if (document.forms.length > 0) {
				  var field = document.forms[0];
				  for (i = 0; i < field.length; i++) {
					if ((field.elements[i].type == "text") || (field.elements[i].type == "textarea")) {
					  document.forms[0].elements[i].focus();
					  break;
					}
				  }
			   }
			  }

			  var formHasAlreadyBeenSubmitted = false;
			  var excludeSubmitRestriction = false;
			  function hasFormAlreadyBeenSubmitted()
			  {
				  if (formHasAlreadyBeenSubmitted && !excludeSubmitRestriction) {
					 alert("Page already being processed by the server.");
					 return false;
				  } else {
					 formHasAlreadyBeenSubmitted = true;
					 return true;
				  }
				  excludeSubmitRestriction = false;
			  }
			  </script>
		</c:when>
		<c:otherwise>
			<c:forEach items="${additionalScriptFiles}" var="scriptFile" >
				<script language="JavaScript" type="text/javascript" src="${scriptFile}"></script>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</head>
<c:choose>
	<c:when test="${lookup}" >
		<body onload="placeFocus();">
		<kul:backdoor />

		<c:if test="${! empty headerMenuBar and !_isInquiry and KualiForm.showMaintenanceLinks}">
			<div class="lookupcreatenew" title="Create a new record">${headerMenuBar}</div>
		</c:if>

		<c:if test="${showDocumentInfo}">
			<h1>${docTitle}<kul:help documentTypeName="${KualiForm.docTypeName}" altText="document help"/></h1>
		</c:if>
    </c:when>
	<c:otherwise>
		<c:if test="${not empty KualiForm.anchor}">
			<c:if test="${ConfigProperties.test.mode ne 'true'}">
				<c:set var="anchorScript" value="jumpToAnchor('${KualiForm.anchor}');" />
			</c:if>
		</c:if>
		<body onload="${anchorScript}" onKeyPress="return isReturnKeyAllowed('${Constants.DISPATCH_REQUEST_PARAMETER}.' , event);">
		<kul:backdoor />${headerMenuBar}
	</c:otherwise>
</c:choose>

<c:set var="encoding" value=""/>
<c:if test="${not empty renderMultipart and renderMultipart eq true}">
	<c:set var="encoding" value="multipart/form-data"/>
</c:if>

<html:form styleId="kualiForm" action="/${htmlFormAction}.do" method="post" enctype="${encoding}" onsubmit="return hasFormAlreadyBeenSubmitted();">
<c:choose>
	<c:when test="${lookup}" >
	</c:when>
	<c:otherwise>
		<a name="topOfForm"></a>
		<div class="headerarea-kra" id="headerarea">
			<h1>${docTitle}&nbsp;<c:if test="${showDocumentInfo}"><kul:help documentTypeName="${KualiForm.docTypeName}" altText="document help"/></c:if></h1>
			<c:if test="${!empty defaultMethodToCall}">
				<kul:enterKey methodToCall="${defaultMethodToCall}" />
			</c:if>
	</c:otherwise>
</c:choose>

    <c:set var="headerClass" value="header"/>
    <c:if test="${not empty KualiForm.additionalDocInfo1 or not empty KualiForm.additionalDocInfo2}">
		 <c:set var="headerClass" value="header-3row"/>
    </c:if>

<!-- DOCUMENT INFO HEADER BOX -->
<c:set var="docHeaderAttributes" value="${DataDictionary.DocumentHeader.attributes}" />
<c:set var="dummyAttributes" value="${DataDictionary.AttributeReferenceDummy.attributes}" />
<c:if test="${showDocumentInfo}">
    <c:choose>
        <c:when test="${!empty KualiForm.document.documentHeader.financialDocumentInErrorNumber}">
            <c:set var="secondDocAttribute" value="${docHeaderAttributes.financialDocumentInErrorNumber}" />
            <c:set var="secondDocId" value="${KualiForm.document.documentHeader.financialDocumentInErrorNumber}" />
            <c:set var="addColumn" value="true" />
        </c:when>
        <c:when test="${!empty KualiForm.document.documentHeader.financialDocumentTemplateNumber}">
            <c:set var="secondDocAttribute" value="${docHeaderAttributes.financialDocumentTemplateNumber}" />
            <c:set var="secondDocId" value="${KualiForm.document.documentHeader.financialDocumentTemplateNumber}" />
            <c:set var="addColumn" value="true" />
        </c:when>
    </c:choose>
    <c:if test="${!empty KualiForm.document.documentHeader.correctedByDocumentId}">
        <c:set var="thirdDocAttribute" value="${docHeaderAttributes.correctedByDocumentId}" />
        <c:set var="thirdDocId" value="${KualiForm.document.documentHeader.correctedByDocumentId}" />
        <c:set var="addColumn" value="true" />
    </c:if>
    <c:set var="headerClass" value="headerinfo"/>
    <c:if test="${not empty KualiForm.additionalDocInfo1 or not empty KualiForm.additionalDocInfo2}">
		<c:choose>
			<c:when test="${lookup}" >
				<c:set var="headerClass" value="headerinfo-3row"/>
			</c:when>
			<c:otherwise>
				<c:set var="headerClass" value=""/>
			</c:otherwise>
		</c:choose>
    </c:if>

  <div class="headerbox">
	<c:choose>
		<c:when test="${lookup}" >
			<table summary="document header: general information" cellpadding="0" cellspacing="0">
		</c:when>
		<c:otherwise>
			<table class="${headerClass}" summary="document header: general information" cellpadding="0" cellspacing="0">
		</c:otherwise>
	</c:choose>
        <tr>
            <kul:htmlAttributeHeaderCell attributeEntry="${docHeaderAttributes.documentNumber}" horizontal="true" scope="row" />
            <td>${KualiForm.document.documentHeader.documentNumber}</td>
            <kul:htmlAttributeHeaderCell attributeEntry="${docHeaderAttributes.financialDocumentStatusCode}" horizontal="true" scope="row" />
            <td>${KualiForm.document.documentHeader.workflowDocument.statusDisplayValue}</td>

            <c:if test="${addColumn}">
                <kul:htmlAttributeHeaderCell attributeEntry="${secondDocAttribute}" horizontal="true" scope="row"/>
                <td>
					<c:choose>
						<c:when test="${lookup}" >
							${secondDocId}
						</c:when>
						<c:otherwise>
							<a href="${ConfigProperties.workflow.url}/DocHandler.do?docId=${secondDocId}&command=displayDocSearchView">${secondDocId}</a>
						</c:otherwise>
					</c:choose>
				</td>
            </c:if>
        </tr>
        <tr>
            <kul:htmlAttributeHeaderCell attributeEntry="${dummyAttributes.initiatorNetworkId}" horizontal="true" scope="row" />
            <td>
				<c:choose>
					<c:when test="${lookup}" >
						${KualiForm.document.documentHeader.workflowDocument.initiatorNetworkId}
					</c:when>
					<c:otherwise>
						<kul:inquiry boClassName="org.kuali.core.bo.user.UniversalUser" keyValues="${PropertyConstants.KUALI_USER_PERSON_UNIVERSAL_IDENTIFIER}=${KualiForm.document.documentHeader.workflowDocument.routeHeader.initiator.uuId}" render="true">${KualiForm.document.documentHeader.workflowDocument.initiatorNetworkId}</kul:inquiry>
					</c:otherwise>
				</c:choose>
			</td>
            <kul:htmlAttributeHeaderCell attributeEntry="${dummyAttributes.createDate}" horizontal="true" scope="row" />
            <td><fmt:formatDate value="${KualiForm.document.documentHeader.workflowDocument.createDate}" pattern="hh:mm a MM/dd/yyyy" /></td>
            <c:if test="${addColumn}">
                <kul:htmlAttributeHeaderCell attributeEntry="${thirdDocAttribute}" horizontal="true" scope="row"/>
                <td>

					<c:choose>
						<c:when test="${lookup}" >
							${thirdDocId}
						</c:when>
						<c:otherwise>
							<a href="${ConfigProperties.workflow.url}/DocHandler.do?docId=${thirdDocId}&command=displayDocSearchView">${thirdDocId}</a>
						</c:otherwise>
					</c:choose>
				</td>
            </c:if>
        </tr>
        <c:if test="${not empty KualiForm.additionalDocInfo1 or not empty KualiForm.additionalDocInfo2}">
            <tr>
                <kul:htmlAttributeHeaderCell attributeEntryName="${KualiForm.additionalDocInfo1.key}" horizontal="true" scope="row" />
                <td>${KualiForm.additionalDocInfo1.label}&nbsp;</td>
                <kul:htmlAttributeHeaderCell attributeEntryName="${KualiForm.additionalDocInfo2.key}" horizontal="true" scope="row"/>
                <td>${KualiForm.additionalDocInfo2.label}&nbsp;</td>
                <c:if test="${addColumn}">
                    <kul:htmlAttributeHeaderCell/>
                    <td><br/></td>
                </c:if>
            </tr>
        </c:if>
    </table>
     </div>
</c:if>

<c:choose>
	<c:when test="${lookup}" >
		<%-- nothing to display--%>
	</c:when>
	<c:otherwise>

		</div>
		<c:if test="${not empty KualiForm.headerNavigationTabs}">
		  <div class="horz-links-bkgrnd" id="horz-links">
		  
		   <div id="tabs">
             <dl class="tabul">
		  
			<c:choose>
			  <c:when test="${empty headerDispatch}">
					<c:forEach var="headerTab" items="${KualiForm.headerNavigationTabs}" varStatus="status">
            <c:set var="currentTab" value="${headerTabActive eq headerTab.headerTabNavigateTo}" /> <!-- ${headerTab.headerTabNavigateTo}; ${headerTabActive}; ${currentTab} -->
            <c:choose><c:when test="${currentTab}"><dt class="licurrent"></c:when><c:otherwise><dt></c:otherwise></c:choose>
             <span class="tabright ${currentTab ? 'tabcurrent' : ''}">
              <html:submit value="${headerTab.headerTabDisplayName}" property="methodToCall.headerTab.headerDispatch.${headerDispatch}.navigateTo.${headerTab.headerTabNavigateTo}.x"  alt="${headerTab.headerTabDisplayName}" disabled="true" />
            </span></dt>
          </c:forEach>
			  </c:when>

			  <c:otherwise>
          <c:forEach var="headerTab" items="${KualiForm.headerNavigationTabs}" varStatus="status">
            <c:set var="currentTab" value="${headerTabActive eq headerTab.headerTabNavigateTo}" /> <!-- ${headerTab.headerTabNavigateTo}; ${headerTabActive}; ${currentTab} -->
            <c:choose><c:when test="${currentTab}"><dt class="licurrent"></c:when><c:otherwise><dt></c:otherwise></c:choose>
             <span class="tabright ${currentTab ? 'tabcurrent' : ''}">
               <html:submit value="${headerTab.headerTabDisplayName}" property="methodToCall.headerTab.headerDispatch.${headerDispatch}.navigateTo.${headerTab.headerTabNavigateTo}.x"  alt="${headerTab.headerTabDisplayName}" disabled="${headerTab.disabled}"  />
          </span></dt></c:forEach>
			  </c:otherwise>
			</c:choose>	 
		     </dl>
		 </div>
		 </div>
		</c:if>
		<div class="msg-excol">
		  <div class="left-errmsg">
			 <kul:errorCount auditCount="${auditCount}"/>
			 <c:if test="${!empty errorKey}">
				  <kul:errors keyMatch="${errorKey}" errorTitle=" "/>
			 </c:if>
			 <c:if test="${empty errorKey}">
				 <kul:errors keyMatch="${Constants.GLOBAL_ERRORS}" errorTitle=" "/>
			 </c:if>
			 <kul:messages/>
		  </div>
		  <div class="right">
			<c:if test="${showTabButtons != '' && showTabButtons == true}">
			 <div class="excol">
				<html:image property="methodToCall.showAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-expandall.gif" title="show all panel content" alt="show all panel content" styleClass="tinybutton" onclick="javascript: return expandAllTab(document, tabStatesSize); " />
				<html:image property="methodToCall.hideAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-collapseall.gif" title="hide all panel content" alt="hide all panel content" styleClass="tinybutton" onclick="javascript: return collapseAllTab(document, tabStatesSize); " />
			  </div>
		   </c:if>
		  </div>
		</div>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="1%"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20" height="20"/></td>
				<td>

	</c:otherwise>
</c:choose>
					<jsp:doBody/>

<c:choose>
	<c:when test="${lookup}" >
					<kul:footer lookup="true"/>
	</c:when>
	<c:otherwise>
					<div class="left-errmsg">
						<kul:errors displayRemaining="true" errorTitle="Other errors:"/>
					</div>
					<kul:footer feedbackKey="${feedbackKey}" />

					<!-- So that JS expandAllTab / collapseAllTab know the tabStates size. Subtract 1 because currentTabIndex = size + 1. -->
					<html:hidden property="tabStatesSize" value="${KualiForm.currentTabIndex - 1}" />


					<!-- state maintenance for returning the user to the action list if they started there -->
					<logic:present name="KualiForm" property="returnToActionList">
						<html:hidden name="KualiForm" property="returnToActionList" />
					</logic:present>
	</c:otherwise>
</c:choose>
<c:if test="${transactionalDocument}" >
	<c:choose>
		<c:when test="${KualiForm.document.sessionDocument}" >
			<html:hidden property="documentWebScope" value="session"/>	
			<html:hidden property="formKey" value="${KualiForm.formKey}"/>	
			<html:hidden property="docFormKey" value="${KualiForm.formKey}"/>	
		</c:when>
		<c:otherwise>
			<html:hidden property="documentWebScope" value="request"/>	
		</c:otherwise>
	</c:choose>
</c:if>

</html:form>
</body>
</html:html>