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

<c:set var="syncChangeAttrs" value="${DataDictionary.AwardSyncChange.attributes}" />
<c:set var="syncStatusAttrs" value="${DataDictionary.AwardSyncStatus.attributes}" />
<c:set var="syncLogAttrs" value="${DataDictionary.AwardSyncLog.attributes}" />

<c:set var="syncStatusAwardNumber" value="${KualiForm.awardSyncBean.statusAwardNumber}"/>
    <h3>Sync Details for ${syncStatusAwardNumber}</h3>
    <table cellpadding="0" cellspacing="0" style="width:100%" class="resultsTable">
        <c:set var="awardStatus" value="${KualiForm.awardSyncBean.awardStatuses[syncStatusAwardNumber]}"/>
	    <c:if test="${not empty awardStatus.changeLogs}">
	      <tr>
	        <th rowspan="${fn:length(awardStatus.changeLogs)+1}">Actions</th>
	        <th><kul:htmlAttributeLabel attributeEntry="${syncChangeAttrs.syncType}" noColon="true"/></th>
	        <th><kul:htmlAttributeLabel attributeEntry="${syncChangeAttrs.objectDesc}" noColon="true"/></th>
	        <th><kul:htmlAttributeLabel attributeEntry="${syncChangeAttrs.dataDesc}" noColon="true"/></th>
	        <th><kul:htmlAttributeLabel attributeEntry="${syncLogAttrs.status}" noColon="true"/></th>
	      </tr>
	      <c:forEach items="${awardStatus.changeLogs}" var="log">
	        <tr>
	          <td><c:out value="${log.change.type.syncDesc}"/></td>
	          <td><c:out value="${log.change.objectDesc}"/></td>
	          <td><c:out value="${log.change.dataDesc}"/></td>
	          <td><c:out value="${log.status}"/></td>
	        </tr>
	      </c:forEach>
	    </c:if>
	    <c:if test="${not empty awardStatus.validationLogs}">
			<tr>
			  <th rowspan="${fn:length(awardStatus.validationLogs)+1}">Validation Messages</th>
			  <th colspan="4"><kul:htmlAttributeLabel attributeEntry="${syncLogAttrs.status}" noColon="true"/></th></tr>
			<c:forEach items="${awardStatus.validationLogs}" var="log">
			  <tr><td colspan="4"><c:out value="${log.status}"/></td></tr>
			</c:forEach>   
	    </c:if>
	</table>
<kul:csrf />