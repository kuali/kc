<%--
 Copyright 2005-2010 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="hierarchy" required="true"
              description="The root of the current hierarchy" 
              type="org.kuali.kra.award.awardhierarchy.AwardHierarchy" %>

<c:if test="${fn:length(hierarchy.children) > 0}">
  <c:forEach items="${hierarchy.children}" var="node">
    <c:set var="awardStatus" value="${KualiForm.awardSyncBean.awardStatuses[node.awardNumber]}"/>
    <c:set var="success" value="${awardStatus.success}"/>
  <tr class="${success ? 'syncValidationSuccess' : 'syncValidationError'}">
    <th class="syncStatus"></th>
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
    </tr>
    <tr class="syncResultsRow"><td colspan="5" style="margin:0; padding:0; border:0;"><div style="display:none;"></div></td></tr>
    <c:set var="_node" value="${node}" scope="request"/>
    <c:import url="/WEB-INF/jsp/award/awardSyncTreeRecurse.jsp" />
  </c:forEach>
</c:if>
