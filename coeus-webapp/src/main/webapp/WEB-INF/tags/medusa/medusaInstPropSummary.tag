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
      <th colspan="4" style="border-style: solid; text-align: left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;">Institutional Proposal ${node.bo.proposalNumber}</th>
    </tr>
    <tr>
      <td style="text-align: center;" colspan="4">
	  
	  <a href="${ConfigProperties.application.url}/institutionalProposalHome.do?methodToCall=docHandler&command=displayDocSearchView&docId=${node.bo.institutionalProposalDocument.documentNumber}&medusaOpenedDoc=true"
	     target="_blank" class="medusaOpenLink">
	    <img title="Open Proposal" 
	         alt="Open Proposal" style="border: medium none ;" 
	         src="static/images/tinybutton-openproposal.gif"/>
	  </a>
	  <a href="${ConfigProperties.application.url}/institutionalProposalHome.do?methodToCall=docHandler&command=displayDocSearchView&docId=${node.bo.institutionalProposalDocument.documentNumber}&medusaOpenedDoc=true&tabStates(Notes)=OPEN#Notes"
	     target="_blank" class="medusaOpenLink">
	    <img title="Open Proposal Notes" 
	          alt="Open Proposal Notes" style="border: medium none ;" 
	          src="static/images/tinybutton-notes.gif"/>
	  </a> 
    </tr>
    <tr>
      <th>Proposal No.</th>
      <th colspan="2">Title</th>
      <th>Status</th>
    </tr>
    <tr>
      <td style="text-align:center;"><c:out value="${node.bo.proposalNumber}"/></td>
      <td style="text-align:center;" colspan="2"><c:out value="${node.bo.title}"/></td>
      <td style="text-align:center;"><c:out value="${node.bo.proposalStatus.description}"/></td>
    </tr>
    <tr>
      <th style="text-align:right;">Proposal Type:</th>
      <td style="text-align:left;"><c:out value="${node.bo.proposalType.description}"/></td>
      <th style="text-align:right;">Sponsor Prpsl No:</th>
      <td><c:out value="${node.bo.sponsorProposalNumber}"/></td>
    </tr>
    <tr>
      <th style="text-align:right;">Account:</th>
      <td><c:out value="${node.bo.currentAccountNumber}"/></td>
      <th style="text-align:right;">Activity Type:</th>
      <td><c:out value="${node.bo.activityType.description}"/></td>
    </tr>
    <tr>
      <th style="text-align:right;">NSF Code:</th>
      <td><c:out value="${node.bo.nsfCodeBo.description}"/></td>
      <th style="text-align:right;">Notice of Opp:</th>
      <td><c:out value="${node.bo.noticeOfOpportunity.description}"/></td>
    </tr>
    <tr>
      <th style="text-align:right;">Sponsor:</th>
      <td colspan="3"><c:out value="${node.bo.sponsorCode} ${node.bo.sponsorName}"/></td>
    </tr>    
    <tr>
      <th style="text-align:right;">Prime Sponsor:</th>
      <td colspan="3"><c:out value="${node.bo.primeSponsor.sponsorCode} ${node.bo.primeSponsor.sponsorName}"/></td>
    </tr>
    <tr>
      <th>&nbsp;</th>
      <th>Initial Period</th>
      <th>Total Period</th>
      <th style="text-align: center;" rowspan="6">
        <div class="medusaStatusBox">
          <div><span class="heading">Cost Sharing</span><span class="value"><input type="checkbox" disabled="true" ${node.bo.costSharingIndicator eq "1 " ? "checked='checked'" : ""}/></span></div>
          <div><span class="heading">Unrecovered F&A</span><span class="value"><input type="checkbox" disabled="true" ${node.bo.idcRateIndicator eq "1 " ? "checked='checked'" : ""}/></span></div>
          <div><span class="heading">Special Review</span><span class="value"><input type="checkbox" disabled="true" ${node.bo.specialReviewIndicator eq "1 " ? "checked='checked'" : ""}/></span></div>
        </div>
      </th>
    </tr>
    <tr>
      <th style="text-align:right;">Requested Start Date:</th>
      <td style="text-align:right;"><fmt:formatDate pattern="MM/dd/yyyy" value="${node.bo.requestedStartDateInitial}"/></td>
      <td style="text-align:right;"><fmt:formatDate pattern="MM/dd/yyyy" value="${node.bo.requestedStartDateTotal}"/></td>
    </tr>
    <tr>
      <th style="text-align:right;">Requested End Date:</th>
      <td style="text-align:right;"><fmt:formatDate pattern="MM/dd/yyyy" value="${node.bo.requestedEndDateInitial}"/></td>
      <td style="text-align:right;"><fmt:formatDate pattern="MM/dd/yyyy" value="${node.bo.requestedEndDateTotal}"/></td>
    </tr>
    <tr>
      <th style="text-align:right;">Total Direct Cost:</th>
      <td style="text-align:right;"><fmt:formatNumber type="currency" value="${node.bo.totalDirectCostInitial}"/></td>
      <td style="text-align:right;"><fmt:formatNumber type="currency" value="${node.bo.totalDirectCostTotal}"/></td>
    </tr>
    <tr>
      <th style="text-align:right;">Total F&A Cost:</th>
      <td style="text-align:right;"><fmt:formatNumber type="currency" value="${node.bo.totalIndirectCostInitial}"/></td>
      <td style="text-align:right;"><fmt:formatNumber type="currency" value="${node.bo.totalIndirectCostTotal}"/></td>
    </tr>
    <tr>
      <th style="text-align:right;">Total All Cost:</th>
      <td style="text-align:right;"><fmt:formatNumber type="currency" value="${node.bo.totalInitialCost}"/></td>
      <td style="text-align:right;"><fmt:formatNumber type="currency" value="${node.bo.totalCost}"/></td>
    </tr>
    <tr>
      <th colspan="2">Investigators</th>
      <th colspan="2">Units</th>
    </tr>
    <c:forEach items="${node.bo.projectPersons}" var="person">
      <tr>
        <td style="text-align: center;" colspan="2">
           <c:out value="${person.fullName}"/>
           <c:if test="${person.principalInvestigator}">(Principal Investigator)</c:if>
        </td>
        <td style="text-align: center;" colspan="2">
          <c:forEach items="${person.units}" var="unit">
            <c:out value="${unit.unitNumber} : ${unit.unitName}"/>
            <c:if test="${unit.leadUnit}">(Lead Unit)</c:if>
            <br/>
          </c:forEach>
        </td>
      </tr>
    </c:forEach>
  </table>
