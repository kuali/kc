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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="docTitle" required="true" description="The title to display for the page." %>
<%@ attribute name="transactionalDocument" required="true" description="The name of the document type this document page is rendering." %>
<%@ attribute name="showDocumentInfo" required="false" description="Boolean value of whether to display the Document Type name and document type help on the page." %>
<%@ attribute name="headerMenuBar" required="false" description="HTML text for menu bar to display at the top of the page." %>
<%@ attribute name="headerTitle" required="false" description="The title of this page which will be displayed in the browser's header bar.  If left blank, docTitle will be used instead." %>
<%@ attribute name="htmlFormAction" required="false" description="The URL that the HTML form rendered on this page will be posted to." %>
<%@ attribute name="renderMultipart" required="false" description="Boolean value of whether the HTML form rendred on this page will be encoded to accept multipart - ie, uploaded attachment - input." %>
<%@ attribute name="showTabButtons" required="false" description="Whether to show the show/hide all tabs buttons." %>
<%@ attribute name="extraTopButtons" required="false" type="java.util.List" description="A List of org.kuali.rice.kns.web.ui.ExtraButton objects to display at the top of the page." %>
<%@ attribute name="headerDispatch" required="false" description="Overrides the header navigation tab buttons to go directly to the action given here." %>
<%@ attribute name="lookup" required="false"
	description="indicates whether the lookup page specific page should be shown"%>

<%-- for non-lookup pages --%>
<%@ attribute name="headerTabActive" required="false" description="The name of the active header tab, if header navigation is used." %>
<%@ attribute name="feedbackKey" required="false"
	description="application resources key that contains feedback contact address only used when lookup attribute is false"%>
<%@ attribute name="defaultMethodToCall" required="false" description="The name of default methodToCall on the action for this page." %>
<%@ attribute name="errorKey" required="false" description="If present, this is the key which will be used to match errors that need to be rendered at the top of the page." %>
<%@ attribute name="auditCount" required="false" description="The number of audit errors displayed on this page." %>
<%@ attribute name="additionalScriptFiles" required="false"
	type="java.util.List" description="A List of JavaScript file names to have included on the page." %>
<%@ attribute name="documentWebScope" required="false" description="The scope this page - which is hard coded to session, making this attribute somewhat useless." %>
<%@ attribute name="maintenanceDocument" required="false" description="Boolean value of whether this page is rendering a maintenance document." %>
<%@ attribute name="sessionDocument" required="false" description="Unused." %>
<%@ attribute name="renderRequiredFieldsLabel" required = "false" description="Boolean value of whether to include a helpful note that the asterisk represents a required field - good for accessibility." %>
<%@ attribute name="alternativeHelp" required="false"%>

<%-- Is the screen an inquiry? --%>
<c:set var="_isInquiry"
	value="${requestScope[Constants.PARAM_MAINTENANCE_VIEW_MODE] eq Constants.PARAM_MAINTENANCE_VIEW_MODE_INQUIRY}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>

<c:if test="${empty headerTitle}">
	<c:set var="headerTitle" value="${docTitle}"/>
</c:if>

<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<c:if test="${not empty SESSION_TIMEOUT_WARNING_MILLISECONDS}">
	<script type="text/javascript">
	<!--
	setTimeout("alert('Your session will expire in ${SESSION_TIMEOUT_WARNING_MINUTES} minutes.')",'${SESSION_TIMEOUT_WARNING_MILLISECONDS}');
	// -->
	</script>
</c:if>

	<script type="text/javascript">var jsContextPath = "${pageContext.request.contextPath}";</script>
	<title><bean:message key="app.title" /> :: ${headerTitle}</title>
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

<!-- new iframe resize logic -->
<script type="text/javascript">

var jq = jQuery.noConflict();
var bodyHeight;
function publishHeight(){
    var parentUrl = "";
    if(navigator.cookieEnabled){
        parentUrl = jQuery.cookie('parentUrl');
        var passedUrl = decodeURIComponent( document.location.hash.replace( /^#/, '' ) );
        if(passedUrl && passedUrl.substring(0, 4) === "http"){
            jQuery.cookie('parentUrl', passedUrl, {path: '/'});
            parentUrl = passedUrl;
        }
    }

    if(parentUrl === ""){
        //make the assumption for not cross-domain, will have no effect if cross domain (message wont be
        //received)
        parentUrl = window.location;
        parentUrl = decodeURIComponent(parentUrl);
    }

    var height = jQuery("body").outerHeight();
    jQuery("body").attr("style", "overflow-x: auto; padding-right: 20px;");
    if (parentUrl && !isNaN(height) && height > 0) {
        jQuery.postMessage({ if_height: height}, parentUrl, parent);
        bodyHeight = height;
    }
}

jQuery(function(){
  publishHeight();
  window.onresize = publishHeight;
  window.setInterval(publishHeight, 249);
});
</script>

    <c:choose>
    <c:when test="${lookup}" >
      <c:if test="${not empty KualiForm.headerNavigationTabs}">
        <link href="kr/css/${KualiForm.navigationCss}" rel="stylesheet" type="text/css" />
      </c:if>

      <!-- allow for custom lookup calls -->
      <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/kr/scripts/lookup.js"></script>

    </c:when>
    <c:otherwise>
      <c:forEach items="${additionalScriptFiles}" var="scriptFile" >
        <script language="JavaScript" type="text/javascript" src="${scriptFile}"></script>
      </c:forEach>
    </c:otherwise>
  </c:choose>
</head>
<%--KULRICE-12287:Added a new tag for displaying a banner in the testing environments which
  * reminds users that it is a non-production environment
  --%>
<kul:testBanner />
<c:choose>
	<c:when test="${lookup}" >
		<body onload="placeFocus();
		<c:if test='<%= jspContext.findAttribute("KualiForm") != null %>'>
			<c:if test='<%= jspContext.findAttribute("KualiForm").getClass() == org.kuali.rice.kns.web.struts.form.LookupForm.class %>'>
				<c:out value ="${KualiForm.lookupable.extraOnLoad}" />
			</c:if>
		</c:if>
		">
    <div id="Uif-Application">
    <%--KULRICE-12287:* Modified where the backdoor information is displayed on each page
		  * so it will be included in the header bar instead of being
		  * absolutely positioned
		  --%>
		<c:if
				test="${! empty headerMenuBar and !_isInquiry and KualiForm.showMaintenanceLinks}">
				<div class="lookupcreatenew">
					${headerMenuBar}
				</div>
		</c:if>
		<c:choose>
			<c:when test="${!empty alternativeHelp}">
				<h1>${docTitle}<kul:help documentTypeName="${KualiForm.docTypeName}" alternativeHelp="${alternativeHelp}" altText="document help"/></h1>
			</c:when>
			<c:otherwise>
				<c:if test="${showDocumentInfo}">
					<h1>${docTitle}<kul:help documentTypeName="${KualiForm.docTypeName}" altText="document help"/></h1>
				</c:if>
			</c:otherwise>
		</c:choose>

    </c:when>
	<c:otherwise>
		<c:if test="${not empty KualiForm.anchor}">
			<c:if test="${ConfigProperties.test.mode ne 'true'}">
				<c:set var="anchorScript"
					value="jumpToAnchor('${KualiForm.anchor}');" />
			</c:if>
		</c:if>
		<c:if test="${empty anchorScript}">
		  <c:set var="anchorScript" value="placeFocus();" />
		</c:if>
		<body onload="if ( !restoreScrollPosition() ) { ${anchorScript} }"
			onKeyPress="return isReturnKeyAllowed('${Constants.DISPATCH_REQUEST_PARAMETER}.' , event);">
    <div id="Uif-Application">
			<%--KULRICE-12287:Modified where the backdoor information is displayed on each page
		  * so it will be included in the header bar instead of being
		  * absolutely positioned
		  --%>
			${headerMenuBar}
	</c:otherwise>
</c:choose>

<c:set var="encoding" value=""/>
<c:if test="${not empty renderMultipart and renderMultipart eq true}">
	<c:set var="encoding" value="multipart/form-data"/>
</c:if>

<html:form styleId="kualiForm" action="/${htmlFormAction}.do"
	method="post" enctype="${encoding}" acceptCharset="utf-8"
	onsubmit="return hasFormAlreadyBeenSubmitted();">
<c:choose>
	<c:when test="${lookup}" >
	</c:when>
	<c:otherwise>
		<a name="topOfForm"></a>
		<div class="headerarea" id="headerarea">
				<h1>
					${docTitle}&nbsp;
					<c:choose>
						<c:when test="${!empty alternativeHelp}">
							<kul:help documentTypeName="${KualiForm.docTypeName}" alternativeHelp="${alternativeHelp}" altText="document help" />
						</c:when>
						<c:otherwise>
							<c:if test="${showDocumentInfo}">
								<kul:help documentTypeName="${KualiForm.docTypeName}" altText="document help"/>
							</c:if>
						</c:otherwise>
					</c:choose>
				</h1>
			<c:if test="${!empty defaultMethodToCall}">
				<kul:enterKey methodToCall="${defaultMethodToCall}" />
			</c:if>
    <%--KULRICE-12287: Modified where the backdoor information is displayed on each page
		      * so it will be included in the header bar instead of being
		      * absolutely positioned
		      --%>
    <kul:backdoor />
	</c:otherwise>
</c:choose>

<!-- DOCUMENT INFO HEADER BOX -->
	<c:set var="docHeaderAttributes"
		value="${DataDictionary.DocumentHeader.attributes}" />
<c:if test="${showDocumentInfo}">
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
						<html:image property="methodToCall.showAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-expandall.gif" title="show all panel content" alt="show all panel content" styleClass="tinybutton" onclick="return expandAllTab();" tabindex="-1" />
						<html:image property="methodToCall.hideAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-collapseall.gif" title="hide all panel content" alt="hide all panel content" styleClass="tinybutton" onclick="return collapseAllTab();" tabindex="-1" />
					</div>
				</div>
			</div>
		</c:if>
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
                                    <c:set var="currentTab" value="${headerTabActive eq headerTab.headerTabNavigateTo}" />
                                    <c:choose>
                                        <c:when test="${currentTab}"><dt class="licurrent"></c:when>
                                        <c:otherwise><dt></c:otherwise>
                                    </c:choose>
                                    <span class="tabright ${currentTab ? 'tabcurrent' : ''}">
                                        <html:submit value="${headerTab.headerTabDisplayName}" property="methodToCall.headerTab.headerDispatch.${headerDispatch}.navigateTo.${headerTab.headerTabNavigateTo}"  alt="${headerTab.headerTabDisplayName}" disabled="true" />
                                    </span></dt>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="headerTab" items="${KualiForm.headerNavigationTabs}" varStatus="status">
                                    <c:set var="currentTab" value="${headerTabActive eq headerTab.headerTabNavigateTo}" />
                                    <c:choose><c:when test="${currentTab}"><dt class="licurrent"></c:when><c:otherwise><dt></c:otherwise></c:choose>
                                    <span class="tabright ${currentTab ? 'tabcurrent' : ''}">
                                        <html:submit value="${headerTab.headerTabDisplayName}" property="methodToCall.headerTab.headerDispatch.${headerDispatch}.navigateTo.${headerTab.headerTabNavigateTo}"  alt="${headerTab.headerTabDisplayName}" disabled="${headerTab.disabled}"  />
                                    </span></dt>
                                </c:forEach>
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
                 <%-- KC customization, added Heading for bootstrap skinning --%>
				  <kul:errors keyMatch="${errorKey}" errorTitle="Page Errors:"/>
			 </c:if>
			 <c:if test="${empty errorKey}">
                 <%-- KC customization, added Heading for bootstrap skinning --%>
                 <kul:errors keyMatch="${Constants.GLOBAL_ERRORS}"
													errorTitle="Page Errors:" />
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
				  <html:image property="methodToCall.showAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-expandall.gif" title="show all panel content" alt="show all panel content" styleClass="tinybutton" onclick="javascript: return expandAllTab(document, tabStatesSize); " tabindex="-1" />
				  <html:image property="methodToCall.hideAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-collapseall.gif" title="hide all panel content" alt="hide all panel content" styleClass="tinybutton" onclick="javascript: return collapseAllTab(document, tabStatesSize); " tabindex="-1" />
		       </c:if>
			   <c:if test="${renderRequiredFieldsLabel}" >
				<br>* required field
			   </c:if>
		  	 </div>
		  </div>
		</div>

        <%-- KC customization, switching the error to a message for bootstrap skinning --%>
        <script type="text/javascript">
            //if no error exists then render the error as a message
            jQuery( document ).ready(function() {
                if(jQuery(".error").length === 0) {
                    jQuery(".kul-error").addClass("kul-message");
                }
            });
        </script>

		<table class="page-main" width="100%" cellpadding="0" cellspacing="0">
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
<kul:editablePropertiesGuid />

</html:form>
<div id="formComplete"></div>
</div>
</body>

</html:html>
