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
<c:set var="developmentProposal" value="${KualiForm.document.budget.budgetParent}" />
<c:set var="proposalNumber" value="${developmentProposal.proposalNumber}" />
<c:set var="documentNumber" value="${developmentProposal.proposalDocument.documentNumber}" />
<c:set var="hierarchyStatus" value="${developmentProposal.hierarchyStatus}" />
<c:set var="hierarchyParentStatus" value="${KualiForm.hierarchyParentStatus}"/>
<c:set var="hierarchyChildStatus" value="${KualiForm.hierarchyChildStatus}"/>
<c:set var="hierarchyNoneStatus" value="${KualiForm.hierarchyNoneStatus}"/>
<c:set var="maintainProposalHierarchy" value="maintainProposalHierarchy" />


<kul:tab tabTitle="Proposal Hierarchy" defaultOpen="false"  
            tabErrorKey="newHierarchyChildProposal*,newHierarchyProposal*">
         
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Proposal Hierarchy</span>
      		<span class="subhead-right"><kul:help parameterNamespace="KC-PD" parameterDetailType="Document" parameterName="proposalHierarchyHelpUrl" altText="help"/></span>
        </h3>	
		<table cellpadding="0" cellspacing="0" summary="">
			<tr>
				<td colspan="3">
					<div class="floaters">
						You are currently viewing the budget for <b>Proposal # ${proposalNumber} (Document # ${documentNumber})</b>, which is 
						<c:choose>
							<c:when test="${hierarchyStatus == hierarchyParentStatus}" >
								a Parent Document.
							</c:when>
							<c:when test="${hierarchyStatus == hierarchyChildStatus}" >
								a Child Document.
							</c:when>
							<c:otherwise>
								currently unlinked to a proposal hierarchy. 
							</c:otherwise>
						</c:choose>
					</div>
				</td>
			</tr>
			<c:if test="${not KualiForm.editingMode[maintainProposalHierarchy]}"><tr>
				<td class="infoline" align="center">
					<div align="center">You do not have permission to perform Proposal Hierarchy actions.</div>
				</td>
			</tr>
			</c:if>
		</table>
    </div> 
</kul:tab>
