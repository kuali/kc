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
<%@ tag body-content="empty" %>

<%@ attribute name="attributeEntry" required="false" type="java.util.Map" %>
<%@ attribute name="attributeEntryName" required="false"
              description="The full name of the DataDictionary entry to use,
              e.g., 'DataDictionary.Budget.attributes.budgetProjectDirectorUniversalIdentifier'.
              Either attributeEntry or attributeEntryName is required." %>
<%@ attribute name="readOnly" required="false" %>
<%@ attribute name="useShortLabel" required="false" %>
<%@ attribute name="labelFor" required="false" %>
<%@ attribute name="skipHelpUrl" required="false" %>
<%@ attribute name="noColon" required="false" %>
<%@ attribute name="forceRequired" required="false" %>

<c:if test="${not empty attributeEntryName}">
    <dd:evalNameToMap mapName="${attributeEntryName}" returnVar="attributeEntry"/>
</c:if>

<%-- There are no spaces between the tags in the middle of this file,
    to make effective the non-breaking space after the REQUIRED_FIELD_SYMBOL,
    and to eliminate any space before the colon. --%>
<c:if test="${(attributeEntry.required == true || forceRequired) && readOnly != true}">
  <font color="">${Constants.REQUIRED_FIELD_SYMBOL}&nbsp;</font></c:if
><c:if test="${not empty labelFor}"><label for="${labelFor}"></c:if
><c:if test="${!skipHelpUrl}"><a
        href="${ConfigProperties.kr.url}/help.do?methodToCall=getAttributeHelpText&amp;businessObjectClassName=${attributeEntry.fullClassName}&amp;attributeName=${attributeEntry.name}"
        tabindex="${KualiForm.nextArbitrarilyHighIndex}" target="helpWindow" title="[Help] ${attributeEntry.label}"></c:if
><c:if test="${useShortLabel == true}"><c:out value="${attributeEntry.shortLabel}" /></c:if
><c:if test="${useShortLabel != true}"><c:out value="${attributeEntry.label}" /></c:if
><c:if test="${!noColon}">:</c:if>
<c:if test="${!skipHelpUrl}"></a></c:if>
<c:if test="${not empty labelFor}"></label></c:if>
