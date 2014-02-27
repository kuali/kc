<%--
 Copyright 2005-2014 The Kuali Foundation
 
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
			<kra:section permission="${maintainProposalHierarchy}">
			<c:choose>
				<c:when test="${hierarchyStatus == hierarchyChildStatus && KualiForm.syncableBudget}" >
					<tr>
						<td class="infoline" colspan="3">
							<div align="center">
								<html:image property="methodToCall.syncBudgetToParent.anchor${tabKey}"
						        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-syncbudgettoparent.gif' styleClass="tinybutton"/>
							</div>
		                </td>
					</tr>
				</c:when><c:when test="${hierarchyStatus == hierarchyChildStatus}">
					<tr>
						<td class="infoline" colspan="3">
							<div align="center">
								This budget cannot be synced as it is not the final or most recent budget for the proposal.
							</div>
						</td>
					</tr>
				</c:when>
			</c:choose>
			</kra:section>
			<c:if test="${not KualiForm.editingMode[maintainProposalHierarchy]}"><tr>
				<td class="infoline" align="center">
					<div align="center">You do not have permission to perform Proposal Hierarchy actions.</div>
				</td>
			</tr>
			</c:if>
		</table>
    </div> 
</kul:tab>
