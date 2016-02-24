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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolPersonAttributes" value="${DataDictionary.ProtocolPerson.attributes}" />
<div id="workarea">
<c:forEach items="${KualiForm.document.protocolList[0].protocolPersons}" var="person" varStatus="status">
    <c:set var="protocolPersonProperty" value="document.protocolList[0].protocolPersons[${status.index}]" />
    <c:if test="${not KualiForm.personnelHelper.protocolFinal || KualiForm.document.protocolList[0].correctionMode}">
        <c:set var="leftSideHtmlProperty" value="${protocolPersonProperty}.delete" />
    </c:if>
    <c:set var="personUnitRequired" value="${person.protocolPersonRole.unitDetailsRequired}" />
    <c:set var="transparent" value="false" />

    <c:if test="${status.first}">
      <c:set var="transparent" value="true" />
    </c:if> 
    	<c:set var="descri" value="${person.protocolPersonRole.description}" />
	<c:set var="personIndex" value="${status.index}" />
	<kul:tab tabTitle="${fn:substring(person.personName, 0, 22)}"
			 tabErrorKey="document.protocolList[0].protocolPersons[${personIndex}].*"
			 auditCluster="personnelAuditErrors" 
			 tabAuditKey="document.protocolList[0].protocolPersons[${personIndex}].*" 
			 useRiceAuditMode="true"
	         tabDescription="${descri}"
	         leftSideHtmlProperty="${leftSideHtmlProperty}" 
	         leftSideHtmlAttribute="${protocolPersonAttributes.delete}" 
	     	 leftSideHtmlDisabled="false" 
	         defaultOpen="${hasErrors}" 
			 useCurrentTabIndexAsKey="true"
	         transparentBackground="${transparent}">
        <div class="tab-container" align="center">
		    <div id="workarea">
				<h3>
					<span class="subhead-left"><bean:write name="KualiForm" property="${protocolPersonProperty}.personName"/></span>
				</h3>
				<kra-irb:personDetailsSection personIndex="${status.index}" protocolPerson="${protocolPersonProperty}"/>
				<kra-irb:personContactInformationSection personIndex="${status.index}" protocolPerson="${protocolPersonProperty}"/>
  				<kra-irb:personAttachmentSection personIndex="${status.index}" protocolPerson="${protocolPersonProperty}"/> 
  				<c:if test="${personUnitRequired}">
					<kra-irb:personUnitsSection personIndex="${status.index}" protocolPerson="${protocolPersonProperty}"/>
  				</c:if>
			</div>
		 </div>
	</kul:tab>
 </c:forEach>

<c:if test="${fn:length(KualiForm.document.protocolList[0].protocolPersons) > 0}">
    <kul:panelFooter />
</c:if>

</div>
