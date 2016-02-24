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

<%@ attribute name="node" required="true" type="org.kuali.coeus.common.framework.medusa.MedusaNode"%>
  <table style="border: 1px solid rgb(147, 147, 147); padding: 0px; width: 97%; border-collapse: collapse;">
    <tr>
      <th colspan="4" style="border-style: solid; text-align: left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;">Development Proposal ${node.bo.proposalNumber}</th>
    </tr>
    <tr>
      <td style="text-align: center;" colspan="4">
	  <a href="${ConfigProperties.application.url}/kc-pd-krad/proposalDevelopment?methodToCall=docHandler&command=displayDocSearchView&docId=${node.bo.proposalDocument.documentNumber}"
	     target="_blank" class="medusaOpenLink">
	    <img title="Open Proposal" 
	          alt="Open Proposal" style="border: medium none ;" 
	          src="static/images/tinybutton-openproposal.gif"/>
	  </a>    
	  <a href="${ConfigProperties.application.url}/kc-pd-krad/proposalDevelopment?methodToCall=docHandler&command=displayDocSearchView&docId=${node.bo.proposalDocument.documentNumber}&navigateToPageId=PropDev-AttachmentsPage&defaultOpenTab=PropDev-AttachmentsPage-NotesSection"
	     target="_blank" class="medusaOpenLink">
	    <img title="Open Proposal Notes" 
	          alt="Open Proposal Notes" style="border: medium none ;" 
	          src="static/images/tinybutton-notes.gif"/>
	  </a> 
      </td>
    </tr>
    <tr>
      <th style="text-align: right;">Proposal No:</th>
      <td><c:out value="${node.bo.proposalNumber}"/></td>
      <th style="text-align: right;">Status:</th>
      <td><c:out value="${node.bo.proposalState.description}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Lead Unit:</th>
      <td colspan="3"><c:out value="${node.bo.ownedByUnitNumber}:${node.bo.ownedByUnit.unitName}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Start Date:</th>
      <td><fmt:formatDate pattern="MM/dd/yyyy" value="${node.bo.requestedStartDateInitial}"/></td>
      <th style="text-align: right;">End Date:</th>
      <td><fmt:formatDate pattern="MM/dd/yyyy" value="${node.bo.requestedEndDateInitial}"/></td>      
    </tr>
    <tr>
      <th style="text-align: right;">Title:</th>
      <td colspan="3"><c:out value="${node.bo.title}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Proposal Type:</th>
      <td colspan="3"><c:out value="${node.bo.proposalType.description}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">NSF Code:</th>
      <td colspan="3"><c:out value="${node.bo.nsfCodeBo.description}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Sponsor:</th>
      <td colspan="3"><c:out value="${node.bo.sponsorCode} ${node.bo.sponsorName}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Prime Sponsor:</th>
      <td colspan="3"><c:out value="${node.bo.primeSponsorCode} ${node.bo.primeSponsor.sponsorName}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Sponsor Proposal No:</th>
      <td><c:out value="${node.bo.sponsorProposalNumber}"/></td>
      <th style="text-align: right;">Activity Type:</th>
      <td><c:out value="${node.bo.activityType.description}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Program Title:</th>
      <td colspan="3"><c:out value="${programAnnouncementTitle}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Notice of Opportunity:</th>
      <td><c:out value="${node.bo.noticeOfOpportunity.description}"/></td>
      <th style="text-align: right;">Program No:</th>
      <td><c:out value="${node.bo.programAnnouncementNumber}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Attachments:</th>
      <td><c:out value="${node.bo.attachmentsStatus}"/></td>
      <th style="text-align: right;">Budget:</th>
      <td><c:out value="${node.bo.budgetStatusDescription}"/></td>      
    </tr>
    <tr>
      <th colspan="2">Investigators</th>
      <th colspan="2">Units</th>
    </tr>
    <c:forEach items="${node.bo.proposalPersons}" var="person">
      <tr>
        <td style="text-align: center;" colspan="2">
           <c:out value="${person.fullName}"/> 
           <c:if test="${person.proposalPersonRoleId == 'PI'}">(Principal Investigator)</c:if>
        </td>
        <td style="text-align: center;" colspan="2">
          <c:forEach items="${person.units}" var="unit">
            <c:out value="${unit.unitNumber} : ${unit.unit.unitName}"/>
            <c:if test="${unit.leadUnit}">(Lead Unit)</c:if>
            <br/>
          </c:forEach>
        </td>
      </tr>
    </c:forEach>
  </table>
