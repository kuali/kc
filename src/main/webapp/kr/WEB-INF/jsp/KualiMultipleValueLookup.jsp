<%--
 Copyright 2007 The Kuali Foundation.
 
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

<kul:page lookup="true" showDocumentInfo="false"
	headerMenuBar="${KualiForm.lookupable.createNewUrl}   ${KualiForm.lookupable.htmlMenuBar}"
	headerTitle="Lookup" docTitle="" transactionalDocument="false"
	htmlFormAction="multipleValueLookup">

	<SCRIPT type="text/javascript">
    var kualiForm = document.forms['KualiForm'];
    var kualiElements = kualiForm.elements;
  </SCRIPT>

	<div class="headerarea-small" id="headerarea-small">
	<h1><c:out value="${KualiForm.lookupable.title}" /><kul:help
		resourceKey="lookupHelpText" altText="lookup help" /></h1>
	</div>
	<kul:enterKey methodToCall="search" />

	<html-el:hidden name="KualiForm" property="backLocation" />
	<html-el:hidden name="KualiForm" property="formKey" />
	<html-el:hidden name="KualiForm" property="lookupableImplServiceName" />
	<html-el:hidden name="KualiForm" property="businessObjectClassName" />
	<html-el:hidden name="KualiForm" property="conversionFields" />
	<html-el:hidden name="KualiForm" property="hideReturnLink" />
	<html-el:hidden name="KualiForm" property="suppressActions" />
	<html-el:hidden name="KualiForm" property="extraButtonSource" />
	<html-el:hidden name="KualiForm" property="extraButtonParams" />
	<html-el:hidden name="KualiForm" property="multipleValues" />
	<html-el:hidden name="KualiForm" property="lookupAnchor" />
	<html-el:hidden name="KualiForm" property="readOnlyFields" />
	<html-el:hidden name="KualiForm" property="lookupResultsSequenceNumber" />
	<html-el:hidden name="KualiForm" property="lookedUpCollectionName" />
	<html-el:hidden name="KualiForm" property="viewedPageNumber" />
	<html-el:hidden name="KualiForm" property="resultsActualSize" />
	<html-el:hidden name="KualiForm" property="resultsLimitedSize" />
	
	<kul:errors errorTitle="Errors found in Search Criteria:" />
	<kul:messages/>

	<table width="100%">
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
							href='<c:out value="${KualiForm.backLocation}?methodToCall=refresh&docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}" />'  title="cancel"><img
							src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" class="tinybutton" alt="cancel" title="cancel" 
							border="0" /></a>
					</c:if> <!-- Optional extra button --> <c:if
						test="${! empty KualiForm.extraButtonSource && extraButtonSource != ''}">
						<a
							href='<c:out value="${KualiForm.backLocation}?methodToCall=refresh&refreshCaller=kualiLookupable&docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}" /><c:out value="${KualiForm.extraButtonParams}" />'><img
							src='<c:out value="${KualiForm.extraButtonSource}" />'
							class="tinybutton" border="0" /></a>
					</c:if>
						<input type="image" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_retnovalue.gif" class="tinybutton" name="methodToCall.prepareToReturnNone.x" alt="Return no results" title="Return no results"/>
						<input type="image" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_retselected.gif" class="tinybutton" name="methodToCall.prepareToReturnSelectedResults.x" alt="Return selected results" title="Return selected results"/>
					</td>
				</tr>
			</table>
			</div>

			<br>
			<br>
			<div class="right"><logic-el:present name="KualiForm"
				property="formKey">
				<c:if
					test="${KualiForm.formKey!='' && KualiForm.hideReturnLink != true && !KualiForm.multipleValues}">
					<a
						href='<c:out value="${KualiForm.backLocation}?methodToCall=refresh&docFormKey=${KualiForm.formKey}" />' title="return with no value">
					return with no value </a>
				</c:if>
			</logic-el:present></div>
            <kul:displayMultipleValueLookupResults resultsList="${requestScope.reqSearchResults}"/>
			</td>
			<td width="1%"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20"
				height="20"></td>
		</tr>
	</table>
</kul:page>
