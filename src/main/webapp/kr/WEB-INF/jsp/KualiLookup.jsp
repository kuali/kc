<%--
 Copyright 2006-2009 The Kuali Foundation.

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
<%@ include file="tldHeader.jsp"%>

<%--NOTE: DO NOT FORMAT THIS FILE, DISPLAY:COLUMN WILL NOT WORK CORRECTLY IF IT CONTAINS LINE BREAKS --%>
<c:set var="headerMenu" value="" />
<c:if test="${KualiForm.suppressActions!=true}">
    <c:set var="headerMenu" value="${KualiForm.lookupable.createNewUrl}   ${KualiForm.lookupable.htmlMenuBar}" />
</c:if>
<c:if test="${KualiForm.supplementalActionsEnabled==true}">
    <c:set var="headerMenu" value="${headerMenu} ${KualiForm.lookupable.supplementalMenuBar}" />
</c:if>
<kul:page lookup="true" showDocumentInfo="false"
	headerMenuBar="${headerMenu}"
	headerTitle="Lookup" docTitle="" transactionalDocument="false"
	htmlFormAction="lookup">

	<SCRIPT type="text/javascript">
    var kualiForm = document.forms['KualiForm'];
    var kualiElements = kualiForm.elements;
  </SCRIPT>

	<c:if test="${KualiForm.supplementalActionsEnabled==true}">
		<div class="headerarea-small" id="headerarea-small">
			<h1><c:out value="${KualiForm.lookupable.title}" /><kul:help
				resourceKey="lookupHelpText" altText="lookup help" /></h1>
		</div>
	</c:if>
	<kul:enterKey methodToCall="search" />

	<html-el:hidden name="KualiForm" property="backLocation" />
	<html-el:hidden name="KualiForm" property="formKey" />
	<html-el:hidden name="KualiForm" property="lookupableImplServiceName" />
	<html-el:hidden name="KualiForm" property="businessObjectClassName" />
	<html-el:hidden name="KualiForm" property="conversionFields" />
	<html-el:hidden name="KualiForm" property="hideReturnLink" />
	<html-el:hidden name="KualiForm" property="suppressActions" />
	<html-el:hidden name="KualiForm" property="multipleValues" />
	<html-el:hidden name="KualiForm" property="lookupAnchor" />
	<html-el:hidden name="KualiForm" property="readOnlyFields" />
	<html-el:hidden name="KualiForm" property="referencesToRefresh" />
	<html-el:hidden name="KualiForm" property="hasReturnableRow" />
	<html-el:hidden name="KualiForm" property="docNum" />
	<html-el:hidden name="KualiForm" property="showMaintenanceLinks" />

	<c:forEach items="${KualiForm.extraButtons}" varStatus="status">
		<html-el:hidden name="KualiForm" property="extraButtons[${status.index}].extraButtonSource" />
		<html-el:hidden name="KualiForm" property="extraButtons[${status.index}].extraButtonParams" />
	</c:forEach>
	<div class="right">
		<div class="excol">
		* required field
		</div>
	</div>
    <div class="msg-excol">
      <div class="left-errmsg">
        <kul:errors errorTitle="Errors found in Search Criteria:" />
        <kul:messages/>
	  </div>
    </div>
    <br/>

	<table width="100%">
	  <c:if test="${KualiForm.lookupCriteriaEnabled}">
		<tr>
			<td width="1%"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20"
				height="20"></td>
			<td>

			<div id="lookup" align="center"><br />
			<br />
			<table align="center" cellpadding=0 cellspacing=0 class="datatable-100">
				<c:set var="FormName" value="KualiForm" scope="request" />
				<c:set var="FieldRows" value="${KualiForm.lookupable.rows}" scope="request" />
				<c:set var="ActionName" value="Lookup.do" scope="request" />
				<c:set var="IsLookupDisplay" value="true" scope="request" />
				<c:set var="cellWidth" value="50%" scope="request" />

                <kul:rowDisplay rows="${FieldRows}" skipTheOldNewBar="true" />

				<tr align=center>
					<td height="30" colspan=2 class="infoline"><html:image
						property="methodToCall.search" value="search"
						src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_search.gif" styleClass="tinybutton"
						alt="search" title="search" border="0" /> <html:image
						property="methodToCall.clearValues" value="clearValues"
						src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_clear.gif" styleClass="tinybutton"
						alt="clear" title="clear" border="0" /> <c:if test="${KualiForm.formKey!=''}">
						<a
							href='<c:out value="${KualiForm.backLocation}?methodToCall=refresh&docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}" />'  title="cancel"><img
							src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" class="tinybutton" alt="cancel" title="cancel"
							border="0" /></a>
					</c:if>
					<!-- Optional extra buttons -->
					<c:forEach items="${KualiForm.extraButtons}" var="extraButton" varStatus="status">
						<c:if test="${!empty extraButton.extraButtonSource && !empty extraButton.extraButtonParams}">
							<a href='<c:out value="${KualiForm.backLocation}?methodToCall=refresh&refreshCaller=kualiLookupable&docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}" /><c:out value="${extraButton.extraButtonParams}" />'><img
							    src='<c:out value="${extraButton.extraButtonSource}" />'
								class="tinybutton" border="0" /></a>
						</c:if>
					</c:forEach>
					<c:if test="${KualiForm.multipleValues }">
						<a
							href='<c:out value="${KualiForm.backLocation}?methodToCall=refresh&docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}" />'>
						<img src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_retnovalue.gif" class="tinybutton"
							border="0" /></a>
						<a
							href='<c:out value="${KualiForm.backLocation}?methodToCall=refresh&docFormKey=${KualiForm.formKey}&refreshCaller=multipleValues&searchResultKey=${searchResultKey}&searchResultDataKey=${searchResultDataKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}"/>'>
						<img src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_returnthese.gif" class="tinybutton"
							border="0" /></a>
					</c:if>
					</td>
				</tr>
			  </c:if>
			</table>
			</div>

			<br>
			<br>
			<div class="right"><logic-el:present name="KualiForm"
				property="formKey">
				<c:if
					test="${KualiForm.formKey!='' && KualiForm.hideReturnLink != true && !KualiForm.multipleValues}">
					<a
						href='<c:out value="${KualiForm.backLocation}?methodToCall=refresh&docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}" />' title="return with no value">
					return with no value</a>
				</c:if>
			</logic-el:present></div>
			<c:if test="${reqSearchResultsActualSize>0}">
				<c:out value="${reqSearchResultsActualSize}" /> items found.  Please refine your search criteria to narrow down your search.
          </c:if>
			<c:if test="${!empty reqSearchResultsActualSize }">
			    <c:if test="${KualiForm.searchUsingOnlyPrimaryKeyValues}">
			    	<bean-el:message key="lookup.using.primary.keys" arg0="${KualiForm.primaryKeyFieldLabels}"/>
			    	<br/><br/>
			    </c:if>
			    <c:if test="${!empty reqSearchResults && !KualiForm.hasReturnableRow && KualiForm.formKey!='' && KualiForm.hideReturnLink!=true && !KualiForm.multipleValues}">
    				<bean-el:message key="lookup.no.returnable.rows" />
    				<br/><br/>
    			</c:if>

				<display:table class="datatable-100" cellspacing="0"
				requestURIcontext="false" cellpadding="0" name="${reqSearchResults}"
				id="row" export="true" pagesize="100"
				requestURI="lookup.do?methodToCall=viewResults&reqSearchResultsActualSize=${reqSearchResultsActualSize}&searchResultKey=${searchResultKey}&searchUsingOnlyPrimaryKeyValues=${KualiForm.searchUsingOnlyPrimaryKeyValues}&actionUrlsExist=${KualiForm.actionUrlsExist}">

				<%-- the param['d-16544-e'] parameter below is NOT null when we are in exporting mode, so this check disables rendering of return/action URLs when we are exporting to CSV, excel, xml, etc. --%>
				<c:if test="${param['d-16544-e'] == null}">
					<logic:present name="KualiForm" property="formKey">
						<c:if
							test="${KualiForm.formKey!='' && KualiForm.hideReturnLink!=true && !KualiForm.multipleValues}">
							<c:if test="${row.rowReturnable}">
								<display:column class="infocell" property="returnUrl" media="html" title="Return Value"/>
							</c:if>
						</c:if>
						<c:if test="${KualiForm.actionUrlsExist==true && KualiForm.suppressActions!=true && !KualiForm.multipleValues && KualiForm.showMaintenanceLinks}">
							<c:choose>
								<c:when test="${row.actionUrls!=''}">
									<display:column class="infocell" property="actionUrls"
										title="Actions" media="html" />
								</c:when>
								<c:otherwise>
									<display:column class="infocell"
										title="Actions" media="html">&nbsp;</display:column>
								</c:otherwise>
							</c:choose>
						</c:if>
					</logic:present>
				</c:if>

				<c:forEach items="${row.columns}" var="column" varStatus="loopStatus">
          <c:set var="colClass" value="${ fn:startsWith(column.formatter, 'org.kuali.rice.kns.web.format.CurrencyFormatter') ? 'numbercell' : 'infocell' }" />
					<c:choose>
						<%--NOTE: Check if exporting first, as this should be outputted without extra HTML formatting --%>
						<c:when	test="${param['d-16544-e'] != null}">
								<display:column class="${colClass}" sortable="${column.sortable}"
									title="${column.columnTitle}" comparator="${column.comparator}"
									maxLength="${column.maxLength}"><c:out value="${column.propertyValue}" escapeXml="false" default="" /></display:column>
						</c:when>
						<c:when	test="${!empty column.columnAnchor.href || column.multipleAnchors}">
							<display:column class="${colClass}" sortable="${column.sortable}"
								title="${column.columnTitle}" comparator="${column.comparator}">
								<c:choose>
									<c:when	test="${column.multipleAnchors}">
										<c:set var="numberOfColumnAnchors" value="${column.numberOfColumnAnchors}" />
										<logic:iterate id="columnAnchor" name="column" property="columnAnchors" indexId="ctr">
										<a href="<c:out value="${columnAnchor.href}"/>" target="blank" title="${columnAnchor.title}"><c:out
											value="${fn:substring(columnAnchor.displayText, 0, column.maxLength)}" escapeXml="${column.escapeXMLValue}"
											/><c:if test="${column.maxLength gt 0 && fn:length(columnAnchor.displayText) gt column.maxLength}">...</c:if></a>
											<c:if test="${ctr lt numberOfColumnAnchors-1}">,</c:if>
										</logic:iterate>
									</c:when>
									<c:otherwise>
										<a href="<c:out value="${column.columnAnchor.href}"/>" target="blank" title="${column.columnAnchor.title}"><c:out
											value="${fn:substring(column.propertyValue, 0, column.maxLength)}" escapeXml="${column.escapeXMLValue}"
											/><c:if test="${column.maxLength gt 0 && fn:length(column.propertyValue) gt column.maxLength}">...</c:if></a>
			                        </c:otherwise>
			                     </c:choose>
			                </display:column>
						</c:when>
<%--NOTE: DO NOT FORMAT THIS FILE, DISPLAY:COLUMN WILL NOT WORK CORRECTLY IF IT CONTAINS LINE BREAKS --%>
						<c:otherwise>
							<display:column class="${colClass}" sortable="${column.sortable}"
								title="${column.columnTitle}" comparator="${column.comparator}"
								maxLength="${column.maxLength}" decorator="org.kuali.rice.kns.web.ui.FormatAwareDecorator"><c:out value="${column.propertyValue}"/>&nbsp;</display:column>
                        </c:otherwise>
					</c:choose>
				</c:forEach>

												</display:table>
			</c:if></td>
			<td width="1%"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20"
				height="20"></td>
		</tr>
	</table>
</kul:page>
