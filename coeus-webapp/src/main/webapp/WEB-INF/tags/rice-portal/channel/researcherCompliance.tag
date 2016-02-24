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
<%@ tag import="org.kuali.coeus.sys.framework.service.KcServiceLocator" %>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<channel:portalChannelTop channelTitle="Conflict of Interest" />
<div class="body">
<c:set var="cmotd" value="<%= (KcServiceLocator.getService(org.kuali.kra.coi.service.CoiMessagesService.class)).getMessages() %>" scope="page"/>
<c:if test="${!empty pageScope.cmotd}">
	<c:forEach items = "${pageScope.cmotd}" var = "msg">
        <div class="alert alert-error">
          <c:out value="${msg}" />
        </div>
   </c:forEach>
</c:if>
  <strong>My Financial Entities </strong>
  <ul class="chan">
    <li><portal:portalLink displayTitle="false" title="Create New Financial Entity" url="${ConfigProperties.application.url}/financialEntityManagement.do?methodToCall=management&financialEntityHelper.reporterId=&coiDocId=">Financial Entity</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="View/Edit Financial Entities" url="${ConfigProperties.application.url}/financialEntityManagement.do?methodToCall=editList&financialEntityHelper.reporterId=&coiDocId=">View/Edit Financial Entities</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="All My Financial Entities" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true">All My Financial Entities</portal:portalLink></li>
  </ul>
  <strong>My Disclosures </strong>
  <ul class="chan">
    <li><portal:portalLink displayTitle="false" title="Master Disclosure" url="${ConfigProperties.application.url}/coiDisclosure.do?methodToCall=viewMasterDisclosure">Master Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="Create Annual Disclosure" url="${ConfigProperties.application.url}/coiDisclosure.do?methodToCall=docHandler&command=initiate&docTypeName=CoiDisclosureDocument">Create Annual Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="Create Manual Disclosure" url="${ConfigProperties.application.url}/coiDisclosure.do?methodToCall=docHandler&command=initiate_12&docTypeName=CoiDisclosureDocument">Create Manual Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="New Project Disclosures" url="${ConfigProperties.application.url}/projectEventDisclosure.do?methodToCall=getDisclosuresToComplete">New Project Disclosures To Complete</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="Update Master Disclosure" url="${ConfigProperties.application.url}/coiDisclosure.do?methodToCall=docHandler&command=initiate_6&docTypeName=CoiDisclosureDocument">Update Master Disclosure</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="All My Disclosures" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.coi.CoiDisclosure&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&personId=${UserSession.principalId}">All My Disclosures</portal:portalLink></li>
   	<li><portal:portalLink displayTitle="false" title="My Disclosure Reviews" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.coi.CoiDisclosureReviews&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >All My Disclosure Reviews</portal:portalLink></li>
    <li><portal:portalLink displayTitle="false" title="Search Disclosures" url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.coi.CoiDisclosure&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true">Search Disclosures</portal:portalLink></li>
  </ul>
</div>
<channel:portalChannelBottom />
