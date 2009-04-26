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
<%@ attribute name="extraTopButtons" required="false" type="java.util.List" %>
<%@ attribute name="headerDispatch" required="false" %>
<%@ attribute name="lookup" required="false"
	description="indicates whether the lookup page specific page should be shown"%>

<%-- for non-lookup pages --%>
<%@ attribute name="headerTabActive" required="false" %>
<%@ attribute name="feedbackKey" required="false"
	description="application resources key that contains feedback contact address only used when lookup attribute is false"%>
<%@ attribute name="defaultMethodToCall" required="false" %>
<%@ attribute name="errorKey" required="false" %>
<%@ attribute name="auditCount" required="false" %>
<%@ attribute name="additionalScriptFiles" required="false"
	type="java.util.List"%>
<%@ attribute name="documentWebScope" required="false" %>
<%@ attribute name="maintenanceDocument" required="false"%>
<%@ attribute name="sessionDocument" required="false"%>
<%@ attribute name="renderRequiredFieldsLabel" required = "false" %>


<%-- Is the screen an inquiry? --%>
<c:set var="_isInquiry"
	value="${requestScope[Constants.PARAM_MAINTENANCE_VIEW_MODE] eq Constants.PARAM_MAINTENANCE_VIEW_MODE_INQUIRY}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>

<c:if test="${empty headerTitle}">
	<c:set var="headerTitle" value="${docTitle}"/>
</c:if>

<head>
	<script>var jsContextPath = "${pageContext.request.contextPath}";</script>
	<title><bean:message key="app.title" /> :: ${headerTitle}</title>
	<c:forEach items="${fn:split(ConfigProperties.css.files, ',')}"
		var="cssFile">
<c:if test="${fn:length(fn:trim(cssFile)) > 0}">
			<link href="${pageContext.request.contextPath}/${cssFile}"
				rel="stylesheet" type="text/css" />
</c:if>
</c:forEach>
	<c:forEach items="${fn:split(ConfigProperties.javascript.files, ',')}"
		var="javascriptFile">
<c:if test="${fn:length(fn:trim(javascriptFile)) > 0}">
			<script language="JavaScript" type="text/javascript"
				src="${pageContext.request.contextPath}/${javascriptFile}"></script>
</c:if>
</c:forEach>
	<c:choose>
		<c:when test="${lookup}" >
			  <c:if test="${not empty KualiForm.headerNavigationTabs}">
				<link href="kr/css/${KualiForm.navigationCss}" rel="stylesheet"
					type="text/css" />
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
			  </script>
		</c:when>
		<c:otherwise>
			<c:forEach items="${additionalScriptFiles}" var="scriptFile" >
				<script language="JavaScript" type="text/javascript"
					src="${scriptFile}"></script>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</head>
<c:choose>
	<c:when test="${lookup}" >
		<body onload="placeFocus();">
		<kul:backdoor />

			<c:if
				test="${! empty headerMenuBar and !_isInquiry and KualiForm.showMaintenanceLinks}">
				<div class="lookupcreatenew" title="Create a new record">
					${headerMenuBar}
				</div>
		</c:if>

		<c:if test="${showDocumentInfo}">
				<h1>
					${docTitle}
					<kul:help documentTypeName="${KualiForm.docTypeName}"
						altText="document help" />
				</h1>
		</c:if>
    </c:when>
	<c:otherwise>
		<c:if test="${not empty KualiForm.anchor}">
			<c:if test="${ConfigProperties.test.mode ne 'true'}">
				<c:set var="anchorScript"
					value="jumpToAnchor('${KualiForm.anchor}');" />
			</c:if>
		</c:if>
		<body onload="if ( !restoreScrollPosition() ) { ${anchorScript} }"
			onKeyPress="return isReturnKeyAllowed('${Constants.DISPATCH_REQUEST_PARAMETER}.' , event);">
			<kul:backdoor />
			${headerMenuBar}
	</c:otherwise>
</c:choose>

<c:set var="encoding" value=""/>
<c:if test="${not empty renderMultipart and renderMultipart eq true}">
	<c:set var="encoding" value="multipart/form-data"/>
</c:if>

<html:form styleId="kualiForm" action="/${htmlFormAction}.do"
	method="post" enctype="${encoding}"
	onsubmit="return hasFormAlreadyBeenSubmitted();">
<c:choose>
	<c:when test="${lookup}" >
	</c:when>
	<c:otherwise>
		<a name="topOfForm"></a>
		<div class="headerarea" id="headerarea">
				<h1>
					${docTitle}&nbsp;
					<c:if test="${showDocumentInfo}">
						<kul:help documentTypeName="${KualiForm.docTypeName}"
							altText="document help" />
					</c:if>
				</h1>
			<c:if test="${!empty defaultMethodToCall}">
				<kul:enterKey methodToCall="${defaultMethodToCall}" />
			</c:if>
	</c:otherwise>
</c:choose>

<!-- DOCUMENT INFO HEADER BOX -->
	<c:set var="docHeaderAttributes"
		value="${DataDictionary.DocumentHeader.attributes}" />
	<c:set var="dummyAttributes"
		value="${DataDictionary.AttributeReferenceDummy.attributes}" />
<c:if test="${showDocumentInfo}">
<%--
    <c:if test="${!empty KualiForm.document.documentHeader.additionalDocId1.label}">
        <c:set var="secondDocAttributeName" value="${KualiForm.document.documentHeader.additionalDocId1.key}" />
        <c:set var="secondDocId" value="${KualiForm.document.documentHeader.additionalDocId1.label}" />
        <c:set var="addColumn" value="true" />
    </c:if>
		<c:if
			test="${!empty KualiForm.document.documentHeader.additionalDocId2.label}">
			<c:set var="thirdDocAttributeName"
				value="${KualiForm.document.documentHeader.additionalDocId2.key}" />
			<c:set var="thirdDocId"
				value="${KualiForm.document.documentHeader.additionalDocId2.label}" />
        <c:set var="addColumn" value="true" />
    </c:if>
    <c:set var="headerClass" value="headerinfo"/>
		<c:if
			test="${not empty KualiForm.additionalDocInfo1 or not empty KualiForm.additionalDocInfo2}">
		<c:choose>
			<c:when test="${lookup}" >
				<c:set var="headerClass" value="headerinfo-3row"/>
			</c:when>
			<c:otherwise>
				<c:set var="headerClass" value=""/>
			</c:otherwise>
		</c:choose>
    </c:if>
--%>

	<c:set var="KualiForm" value="${KualiForm}" /> 
	<jsp:useBean id="KualiForm" type="org.kuali.rice.kns.web.struts.form.KualiForm" /> 
	
    <c:set var="numberOfHeaderRows" value="<%=new Integer((int) java.lang.Math.ceil((double) KualiForm.getDocInfo().size()/KualiForm.getNumColumns()))%>" />
    <c:set var="headerFieldCount" value="<%=new Integer(KualiForm.getDocInfo().size())%>" />
  	<c:set var="headerFields" value="${KualiForm.docInfo}" />
  	<c:set var="fieldCounter" value="0" />
  	
  <div class="headerbox">
	<c:choose>
		<c:when test="${lookup}" >
					<table summary="document header: general information"
						cellpadding="0" cellspacing="0">
		</c:when>
		<c:otherwise>
			<table class="headerinfo" summary="document header: general information" cellpadding="0" cellspacing="0">
		</c:otherwise>
	</c:choose>
		
	<c:forEach var="i" begin="1" end="${numberOfHeaderRows}" varStatus="status">
	 <tr>
			<c:forEach var="j" begin="1" end="<%=KualiForm.getNumColumns()%>" varStatus="innerStatus">
				<c:choose>
					<c:when test="${headerFieldCount > fieldCounter}">
			 		<c:set var="headerField" value="${headerFields[fieldCounter]}" />
			 			<c:choose>
			 				<c:when test="${(empty headerField) or (empty headerField.ddAttributeEntryName)}">
								<kul:htmlAttributeHeaderCell />
								<td>&nbsp;</td>
			 				</c:when>
			 				<c:otherwise>
					        	<kul:htmlAttributeHeaderCell attributeEntryName="${headerField.ddAttributeEntryName}" horizontal="true" scope="row" />
					        	<td>
					        	<c:if test="${empty headerField.nonLookupValue and empty headerField.displayValue}">
					        		&nbsp;
					        	</c:if>
								<c:choose>
									<c:when test="${headerField.lookupAware and (not lookup)}" >
										${headerField.nonLookupValue}
									</c:when> 
									<c:otherwise>
										${headerField.displayValue}
									</c:otherwise>
								</c:choose>
						 		</td>
			 				</c:otherwise>
			 			</c:choose>
					</c:when>
					<c:otherwise>
						<kul:htmlAttributeHeaderCell />
						<td>&nbsp;</td>
					</c:otherwise>
				</c:choose>
		 		<c:if test="${headerFieldCount > fieldCounter}">
			 	</c:if>
				<c:set var="fieldCounter" value="${fieldCounter+1}" /> 
		 </c:forEach>
<%--
		 <c:if test="${addColumn}">
		 	<c:if test="${i==1}">
			 	<c:set var="attributeEntry" value="${secondDocAttributeName}" />
			 	<c:set var="docId" value="${secondDocId}" />
		 	</c:if>
		 	<c:if test="${i==2}">
			 	<c:set var="attributeEntry" value="${thirdDocAttributeName}" />
			 	<c:set var="docId" value="${thirdDocId}" />
		 	</c:if>
		 	<c:if test="${i<=2}">
	            <kul:htmlAttributeHeaderCell attributeEntry="${attributeEntry}" horizontal="true" scope="row"/>
	            <td>
					<c:choose>
						<c:when test="${lookup}" >
							${docId}
						</c:when> 
						<c:otherwise>
							<a href="${ConfigProperties.workflow.url}/DocHandler.do?docId=${docId}&command=displayDocSearchView">${docId}</a>
						</c:otherwise>
					</c:choose>
				</td>
       		 </c:if>
       		 <c:if test="${i>2}">
	             <kul:htmlAttributeHeaderCell/>
                 <td><br/></td>
       		 </c:if>
        </c:if>
--%>
      </tr>
    </c:forEach>
   </table>
  </div>
</c:if>

<c:choose>
	<c:when test="${lookup}" >
		<%-- Display the expand/collapse buttons for the lookup/inquiry, if specified. --%>
		<c:if test="${showTabButtons != '' && showTabButtons == true}">
			<div class="right">
				<div class="excol">
					<div class="lookupcreatenew">
						<html:image property="methodToCall.showAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-expandall.gif" title="show all panel content" alt="show all panel content" styleClass="tinybutton" onclick="javascript: return expandAllTab(document, tabStatesSize); " />
						<html:image property="methodToCall.hideAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-collapseall.gif" title="hide all panel content" alt="hide all panel content" styleClass="tinybutton" onclick="javascript: return collapseAllTab(document, tabStatesSize); " />
					</div>
				</div>		 
			</div>
		</c:if>
	</c:when>
	<c:otherwise>

		</div>
		<c:if test="${not empty KualiForm.headerNavigationTabs}">
		  <div class="horz-links-bkgrnd" id="horz-links">
			<c:choose>
			  <c:when test="${empty headerDispatch}">
													<c:forEach var="headerTab"
														items="${KualiForm.headerNavigationTabs}"
														varStatus="status">
														<html:submit value="${headerTab.headerTabDisplayName}"
															property="methodToCall.headerTab.headerDispatch.${headerDispatch}.navigateTo.${headerTab.headerTabNavigateTo}.x"
															alt="${headerTab.headerTabDisplayName}" disabled="true"
                              title="${headerTab.headerTabDisplayName}"
															styleClass="${(headerTabActive eq headerTab.headerTabNavigateTo) ? 'selected' : ''}" />
													</c:forEach>
			  </c:when>
			  <c:otherwise>
													<c:forEach var="headerTab"
														items="${KualiForm.headerNavigationTabs}"
														varStatus="status">
														<html:submit value="${headerTab.headerTabDisplayName}"
															property="methodToCall.headerTab.headerDispatch.${headerDispatch}.navigateTo.${headerTab.headerTabNavigateTo}.x"
															alt="${headerTab.headerTabDisplayName}"
															disabled="${headerTab.disabled}"
                              title="${headerTab.headerTabDisplayName}"
															styleClass="${(headerTabActive eq headerTab.headerTabNavigateTo) ? 'selected' : ''}" />
													</c:forEach>
			  </c:otherwise>
			</c:choose>
		  </div>
		</c:if>
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
		  <div class="right">
		    <div class="excol">
		  	   <c:if test="${!empty extraTopButtons}">
		         <c:forEach items="${extraTopButtons}" var="extraButton">
		           <html:image src="${extraButton.extraButtonSource}" styleClass="tinybutton" property="${extraButton.extraButtonProperty}" alt="${extraButton.extraButtonAltText}" onclick="${extraButton.extraButtonOnclick}"/> &nbsp;&nbsp;
		         </c:forEach>
	           </c:if>
			   <c:if test="${showTabButtons != '' && showTabButtons == true}">			 
				  <html:image property="methodToCall.showAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-expandall.gif" title="show all panel content" alt="show all panel content" styleClass="tinybutton" onclick="javascript: return expandAllTab(document, tabStatesSize); " />
				  <html:image property="methodToCall.hideAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-collapseall.gif" title="hide all panel content" alt="hide all panel content" styleClass="tinybutton" onclick="javascript: return collapseAllTab(document, tabStatesSize); " />			  
		       </c:if>
			   <c:if test="${renderRequiredFieldsLabel}" >
				<br>* required field
			   </c:if>	
		  	 </div>		 
		  </div>
		</div>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
											<td width="1%">
												<img
													src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif"
													alt="" width="20" height="20" />
											</td>
				<td>

	</c:otherwise>
</c:choose>
					<jsp:doBody/>

<c:choose>
	<c:when test="${lookup}" >
					<kul:footer lookup="true"/>
					<!-- So that JS expandAllTab / collapseAllTab know the tabStates size. Subtract 1 because currentTabIndex = size + 1. -->
					<html:hidden property="tabStatesSize" value="${KualiForm.currentTabIndex - 1}" />
	</c:when>
	<c:otherwise>
					<div class="left-errmsg">
															<kul:errors displayRemaining="true"
																errorTitle="Other errors:"
																warningTitle="Other warnings:"
																infoTitle="Other informational messages:"/>
					</div>
					<kul:footer feedbackKey="${feedbackKey}" />

					<!-- So that JS expandAllTab / collapseAllTab know the tabStates size. Subtract 1 because currentTabIndex = size + 1. -->
														<html:hidden property="tabStatesSize"
															value="${KualiForm.currentTabIndex - 1}" />


					<!-- state maintenance for returning the user to the action list if they started there -->
														<logic:present name="KualiForm"
															property="returnToActionList">
															<html:hidden name="KualiForm"
																property="returnToActionList" />
					</logic:present>
	</c:otherwise>
</c:choose>
<c:if test="${transactionalDocument || maintenanceDocument}">
    <html:hidden property="documentWebScope" value="session"/>	
	<html:hidden property="formKey" value="${KualiForm.formKey}" />
	<html:hidden property="docFormKey" value="${KualiForm.formKey}" />
    <html:hidden property="docNum" value="${KualiForm.document.documentNumber}" />
</c:if>

</html:form>
<div id="formComplete"></div> 
</body>
</html:html>