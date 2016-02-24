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
<%@ include file="tldHeader.jsp"%>

<%@ page buffer = "16kb" %>

<c:if test="${!empty KualiForm.backLocation}">
    <c:choose>
     <c:when test="${fn:contains(KualiForm.backLocation,'?')}">
      <c:set var="backLocation" value="${KualiForm.backLocation}&" />
     </c:when>
     <c:otherwise>
      <c:set var="backLocation" value="${KualiForm.backLocation}?" />
     </c:otherwise>
    </c:choose>
    <c:if test="${!fn:contains(backLocation,'methodToCall')}">
      <c:set var="backLocation" value="${backLocation}methodToCall=refresh&" />
    </c:if>
</c:if>

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
		lookupBusinessObjectClassName="${KualiForm.lookupable.businessObjectClass.name}" altText="lookup help" /></h1>
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
	<html-el:hidden name="KualiForm" property="hasReturnableRow" />
	<html-el:hidden name="KualiForm" property="docNum" />
	<html-el:hidden name="KualiForm" property="fieldNameToFocusOnAfterSubmit"/>

	<kul:errors errorTitle="Errors found in Search Criteria:" />
	<kul:messages/>
	<div class="right">
		<div class="excol">
		* required field
		</div>
	</div>
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
							href='<c:out value="${backLocation}docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}" />'  title="cancel"><img
							src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" class="tinybutton" alt="cancel" title="cancel" 
							border="0" /></a>
					</c:if> <!-- Optional extra button --> <c:if
						test="${! empty KualiForm.extraButtonSource && extraButtonSource != ''}">
						<a
							href='<c:out value="${backLocation}refreshCaller=kualiLookupable&docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}" /><c:out value="${KualiForm.extraButtonParams}" />'><img
							src='<c:out value="${KualiForm.extraButtonSource}" />'
							class="tinybutton" border="0" /></a>
					</c:if>
					
					<%-- removed for 3219 --%>
					<%--
					<c:if test="${ KualiForm.hasReturnableRow }" >
						<input type="image" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_retselected.gif" class="tinybutton" name="methodToCall.prepareToReturnSelectedResults" alt="Return selected results" title="Return selected results"/>
					</c:if>
					--%>
					
					</td>
				</tr>
			</table>
			</div>
			<br>
			<br>	
            <kul:displayMultipleValueLookupResults resultsList="${requestScope.reqSearchResults}"/>
			</td>
			<td width="1%"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20"
				height="20"></td>
		</tr>
	</table>
</kul:page>
