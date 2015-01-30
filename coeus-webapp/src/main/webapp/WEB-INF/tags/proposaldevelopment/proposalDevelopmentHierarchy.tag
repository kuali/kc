<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<c:set var="proposalNumber" value="${KualiForm.document.developmentProposalList[0].proposalNumber}" />
<c:set var="documentNumber" value="${KualiForm.document.documentNumber}" />
<c:set var="hierarchyStatus" value="${KualiForm.document.developmentProposalList[0].hierarchyStatus}" />
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
						You are currently viewing <b>Proposal # ${proposalNumber} (Document # ${documentNumber})</b>, which is 
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
						The following actions are available:
					</div>
				</td>
			</tr>
			<kra:section permission="${maintainProposalHierarchy}">
			<c:choose>
				<c:when test="${hierarchyStatus == hierarchyParentStatus}" >
					<tr><th>Link Child Proposal</th><th>Link Budget Type</th><th>Actions</th></tr>
					<tr><td><div align="center">
							<kul:htmlControlAttribute property="newHierarchyChildProposalNumber" attributeEntry="${DataDictionary.DevelopmentProposal.attributes.proposalNumber}" />
			                <kul:lookup boClassName="org.kuali.coeus.propdev.impl.core.DevelopmentProposal"
			                            fieldConversions="proposalNumber:newHierarchyChildProposalNumber" 
			                            lookupParameters="hierarchyNoneStatus:hierarchyStatus"
			                            anchor="${tabKey}" />
			            </div></td>
			            <td><div align="center">
			            	<kul:htmlControlAttribute property="newHierarchyBudgetTypeCode" attributeEntry="${DataDictionary.DevelopmentProposal.attributes.hierarchyBudgetType}" />
						</div></td>
			            <td><div align="center">
			            	<html:image property="methodToCall.linkChildToHierarchy.anchor${tabKey}"
						       	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-linkchildprop.gif' styleClass="tinybutton"/>
						</div></td>
					</tr>        				
					<tr>
						<td class="infoline" colspan="2">&nbsp;</td>
						<td class="infoline">
							<div align="center">
							    <html:image property="methodToCall.syncAllHierarchy.anchor${tabKey}"
						        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-syncall.gif' styleClass="tinybutton"/>
							</div>
		                </td>
					</tr>
				</c:when>
				<c:when test="${hierarchyStatus == hierarchyChildStatus}" >
					<tr>
						<td class="infoline" colspan="3">
							<div align="center">
								<html:image property="methodToCall.removeFromHierarchy.anchor${tabKey}"
						        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-remhierarchy.gif' styleClass="tinybutton"/>
								<html:image property="methodToCall.syncToHierarchyParent.anchor${tabKey}"
						        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-synctoparent.gif' styleClass="tinybutton"/>
							</div>
		                </td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr><th>Link Child Proposal</th><th>Link Budget Type</th><th>Actions</th></tr>
					<tr><td><div align="center">
							    <kul:htmlControlAttribute property="newHierarchyProposalNumber" 
							    		attributeEntry="${DataDictionary.DevelopmentProposal.attributes.proposalNumber}" 
							    		onblur="" />
			                	<kul:lookup boClassName="org.kuali.coeus.propdev.impl.core.DevelopmentProposal"
			                	            fieldConversions="proposalNumber:newHierarchyProposalNumber" 
			                	            lookupParameters="hierarchyParentStatus:hierarchyStatus"
			                	            anchor="${tabKey}" />
			            </div></td>
			            <td><div align="center">
			            	<kul:htmlControlAttribute property="newHierarchyBudgetTypeCode" attributeEntry="${DataDictionary.DevelopmentProposal.attributes.hierarchyBudgetType}" />
						</div></td>
			            <td><div align="center">
								<html:image property="methodToCall.linkToHierarchy.anchor${tabKey}"
						        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-linktohierarchy.gif' styleClass="tinybutton"/>
						</div></td>
					</tr>        				
					<tr>
						<td class="infoline" colspan="2">&nbsp;</td>
						<td class="infoline">
							<div align="center">
								<html:image property="methodToCall.createHierarchy.anchor${tabKey}"
						        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-createhierarchy.gif' styleClass="tinybutton"/>
							</div>
		                </td>
					</tr>
				</c:otherwise>
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
