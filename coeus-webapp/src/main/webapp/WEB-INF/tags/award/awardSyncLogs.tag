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

<%@ attribute name="rows" required="false"
              description="The total number of rows to display" %>

<c:if test="${fn:length(KualiForm.awardSyncBean.hierarchyList) > 0}">
  <c:forEach items="${KualiForm.awardSyncBean.hierarchyList}" var="node" varStatus="ctr">
    <c:if test="${empty rows || ctr.index < rows}">
    <c:set var="awardStatus" value="${KualiForm.awardSyncBean.awardStatuses[node.awardNumber]}"/>
    <c:set var="success" value="${awardStatus.success}"/>
  <tr id="syncLogRow-${node.awardNumber}" class="${success ? 'syncValidationSuccess' : 'syncValidationError'} syncLogRow">
    <th class="syncStatus"></th>
    <c:choose><c:when test="${awardStatus != null}">
    <th>
      <c:out value="${awardStatus.awardNumber}"/> : <c:out value="${awardStatus.award.accountNumber}"/>
    </th>
    <th>
      <c:out value="${awardStatus.award.sequenceNumber}"/> : <c:out value="${awardStatus.award.awardDocument.documentNumber}"/>
    </th>
    <td><c:out value="${awardStatus.status}"/>
    </td>
    <td>
      <a href="${ConfigProperties.application.url}/awardHome.do?methodToCall=docHandler&command=displayDocSearchView&docId=${awardStatus.award.awardDocument.documentNumber}"
	     target="_blank">
	    <img title="Open Award" 
	          alt="Open Award" style="border: medium none ;" 
	          src="static/images/tinybutton-openaward.gif"/>
	  </a>
	  <a href="#" onClick="showDetails(this, '${node.awardNumber}');"><img src="static/images/tinybutton-showdetails.gif" alt="show details" style="border: medium none;" title="show details" /></a>
	</td>
    </c:when><c:otherwise>
    <th>
      <c:out value="${node.awardNumber}"/> : <c:out value="${node.award.accountNumber}"/>
    </th>
    <th>
      <c:out value="${node.award.sequenceNumber}"/> : <c:out value="${node.award.awardDocument.documentNumber}"/>
    </th>
    <td><c:out value="Not applicable"/>
    </td>
    <td>
      <a href="${ConfigProperties.application.url}/awardHome.do?methodToCall=docHandler&command=displayDocSearchView&docId=${node.award.awardDocument.documentNumber}"
	     target="_blank">
	    <img title="Open Award" 
	          alt="Open Award" style="border: medium none ;" 
	          src="static/images/tinybutton-openaward.gif"/>
	  </a>
	  <a href="#" onClick="showDetails(this, '${node.awardNumber}');"><img src="static/images/tinybutton-showdetails.gif" alt="show details" style="border: medium none;" title="show details" /></a>
	</td>
	</c:otherwise></c:choose>    	
    </tr>
    <tr class="syncResultsRow"><td colspan="5" style="margin:0; padding:0; border:0;"><div style="display:none;"></div></td></tr>
    </c:if>
    <c:if test="${!empty rows && ctr.index == rows}">
    <tr class="syncLogRow-loading"><th colspan="5">Loading....</th></tr>
    </c:if>    
  </c:forEach>
</c:if>
