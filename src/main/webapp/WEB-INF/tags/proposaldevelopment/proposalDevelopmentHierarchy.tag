<%--
 Copyright 2006-2009 The Kuali Foundation
 
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
<c:set var="proposalNumber" value="${KualiForm.document.developmentProposalList[0].proposalNumber}" />
<c:set var="documentNumber" value="${KualiForm.document.documentNumber}" />
<c:set var="hierarchyStatus" value="${KualiForm.document.developmentProposalList[0].hierarchyStatus}" />
<c:set var="hierarchyParentStatus" value="${KualiForm.hierarchyParentStatus}"/>
<c:set var="hierarchyChildStatus" value="${KualiForm.hierarchyChildStatus}"/>
<c:set var="hierarchyNoneStatus" value="${KualiForm.hierarchyNoneStatus}"/>
<c:set var="maintainProposalHierarchy" value="maintainProposalHierarchy" />


<kul:tab tabTitle="Proposal Hierarchy" defaultOpen="false"  
            tabErrorKey="newHierarchyChildProposal.*, newHierarchyProposal.*">
         
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Proposal Hierarchy</span>
        </h3>

		
		<table cellpadding="0" cellspacing="0" summary="">
			<tr>
				<td>
					<div class="floaters">
						You are currently viewing <b>Proposal #${proposalNumber} (Document #${documentNumber})</b>, which is 
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
			<kra:section permission="${maintainProposalHierarchy}"><tr>
				<td class="infoline" align="center">
					<div align="center">
						<c:choose>
							<c:when test="${hierarchyStatus == hierarchyParentStatus}" >
							    <kul:htmlAttributeLabel attributeEntry="${DataDictionary.DevelopmentProposal.attributes.proposalNumber}" />
							    <kul:htmlControlAttribute property="newHierarchyChildProposal.proposalNumber" attributeEntry="${DataDictionary.DevelopmentProposal.attributes.proposalNumber}" />
			                	<kul:lookup boClassName="org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal" 
			                	            fieldConversions="proposalNumber:newHierarchyChildProposal.proposalNumber" 
			                	            lookupParameters="hierarchyNoneStatus:hierarchyStatus"
			                	            anchor="${tabKey}" />
								<html:image property="methodToCall.linkChildToHierarchy.anchor${tabKey}"
						        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-linkchildprop.gif' styleClass="tinybutton"/>
						        - or -
								<html:image property="methodToCall.syncAllHierarchy.anchor${tabKey}"
						        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-syncall.gif' styleClass="tinybutton"/>
							</c:when>
							<c:when test="${hierarchyStatus == hierarchyChildStatus}" >
								<html:image property="methodToCall.removeFromHierarchy.anchor${tabKey}"
						        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-remhierarchy.gif' styleClass="tinybutton"/>
								<html:image property="methodToCall.syncToHierarchyParent.anchor${tabKey}"
						        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-synctoparent.gif' styleClass="tinybutton"/>
							</c:when>
							<c:otherwise>
								<html:image property="methodToCall.createHierarchy.anchor${tabKey}"
						        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-createhierarchy.gif' styleClass="tinybutton"/>
							    - or -
							    <kul:htmlAttributeLabel attributeEntry="${DataDictionary.DevelopmentProposal.attributes.proposalNumber}" />
							    <kul:htmlControlAttribute property="newHierarchyProposal.proposalNumber" 
							    		attributeEntry="${DataDictionary.DevelopmentProposal.attributes.proposalNumber}" 
							    		onblur="" />
			                	<kul:lookup boClassName="org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal" 
			                	            fieldConversions="proposalNumber:newHierarchyProposal.proposalNumber" 
			                	            lookupParameters="hierarchyParentStatus:hierarchyStatus"
			                	            anchor="${tabKey}" />
								<html:image property="methodToCall.linkToHierarchy.anchor${tabKey}"
						        	    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-linktohierarchy.gif' styleClass="tinybutton"/>
							</c:otherwise>
						</c:choose>
					</div>
                </td>
			</tr></kra:section>
			<c:if test="${not KualiForm.editingMode[maintainProposalHierarchy]}"><tr>
				<td class="infoline" align="center">
					<div align="center">You do not have permission to perform Proposal Hierarchy actions.</div>
				</td>
			</tr>
			</c:if>
		</table>
    </div> 
</kul:tab>
