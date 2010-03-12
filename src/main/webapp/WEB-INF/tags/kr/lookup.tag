<%--
 Copyright 2005-2007 The Kuali Foundation
 
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="boClassName" required="true" description="The name of the class to look up instances of in the linked lookup." %>
<%@ attribute name="fieldConversions" required="false" description="Pre-set values to populate within the lookup form." %>
<%@ attribute name="lookupParameters" required="false" description="On return from lookup, these parameters describe which attributes of the business object to populate in the lookup parent." %>
<%@ attribute name="hideReturnLink" required="false" description="Whether the return link should be rendered or not in the linked lookup." %>
<%@ attribute name="suppressActions" required="false" description="Whether action buttons should be rendered or not in the linked lookup." %>
<%@ attribute name="tabindexOverride" required="false" description="Overrides the tab index of this lookup icon." %>
<%@ attribute name="extraButtonSource" required="false" description="The image source of an extra button to have displayed in the linked lookup." %>
<%@ attribute name="extraButtonParams" required="false" description="The parameters, such as the method to call, which the extra button will apply when clicked." %>
<%@ attribute name="anchor" required="false" description="If present, on return from lookup, the page will be scrolled to the named anchor specified by this attribute." %>
<%@ attribute name="fieldLabel" required="false" description="The label of the field which this lookup icon is associated with." %>
<%@ attribute name="readOnlyFields" required="false" description="Which fields in the linked lookup should be displayed at read only." %>
<%@ attribute name="referencesToRefresh" required="false" description="On return from the lookup, the references on the parent business object which will be refreshed." %>
<%@ attribute name="autoSearch" required="false" description="Determines whether auto-search will be used in the lookup." %>
<%@ attribute name="searchIconOverride" required="false" description="If present, changes the url of the icon image from the default Kuali magnifying glass to the image given here." %>
<%@ attribute name="baseLookupUrl" required="false" description="The url the lookup will direct itself to." %>

<c:choose>
  <c:when test="${!empty tabindexOverride}">
    <c:set var="tabindex" value="${tabindexOverride}"/>
  </c:when>
  <c:otherwise>
    <c:set var="tabindex" value="${KualiForm.nextArbitrarilyHighIndex}"/>
  </c:otherwise>
</c:choose>
<c:choose>
  <c:when test="${!empty searchIconOverride}">
    <c:set var="lookupicon" value="${searchIconOverride}"/>
  </c:when>
  <c:otherwise>
    <c:set var="lookupicon" value="${ConfigProperties.kr.externalizable.images.url}searchicon.gif"/>
  </c:otherwise>
</c:choose>

<c:set var="epMethodToCallAttribute" value="methodToCall.performLookup.(!!${boClassName}!!).(((${fieldConversions}))).((#${lookupParameters}#)).((<${hideReturnLink}>)).(([${extraButtonSource}])).((*${extraButtonParams}*)).((^${suppressActions}^)).((&${readOnlyFields}&)).((/${referencesToRefresh}/)).((~${autoSearch}~)).(::::;${baseLookupUrl};::::).anchor${anchor}"/>
${kfunc:registerEditableProperty(KualiForm, epMethodToCallAttribute)} 
<input type="image" tabindex="${tabindex}" name="${epMethodToCallAttribute}"
   src="${lookupicon}" border="0" class="tinybutton" valign="middle" alt="Search ${fieldLabel}" title="Search ${fieldLabel}" />