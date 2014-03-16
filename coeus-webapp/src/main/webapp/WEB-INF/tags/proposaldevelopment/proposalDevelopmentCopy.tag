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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="proposalCopyCriteriaAttributes" value="${DataDictionary.ProposalCopyCriteria.attributes}" />
<c:set var="action" value="proposalDevelopmentActions" />
<c:set var="canCopy" value="${KualiForm.canCreateProposal}" />
<%
		boolean openFlag = false;
        java.lang.String command = request.getParameter("command");

        if(org.apache.commons.lang3.StringUtils.isNotEmpty(command) && command.equals("displayDocSearchView")) {
            //This means that the user has come thru the Document Search Page - Copy Action
            openFlag = true;
        } 
        jspContext.setAttribute("openFlag", openFlag);
%>

 <kul:tab tabTitle="Copy to New Document" defaultOpen="${openFlag}" tabErrorKey="copyCriteria*,copyCriteria.*">
         
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Copy to New Document</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
        
        	<tr>
        		<th align="right" valign="middle">Proposal:</th>
        		<td align="left" valign="middle">yes</td>
        	</tr>
        	
        	<tr>
                <th align="right" valign="middle">Lead Unit:</th>
                <td align="left" valign="middle">${KualiForm.copyCriteria.originalLeadUnitNumber}</td>
            </tr>
        		
        	<tr>
        		<th align="right" valign="middle">
        			<kul:htmlAttributeLabel attributeEntry="${proposalCopyCriteriaAttributes.includeBudget}"/>
            	</th>
        		
        	 	<td align="left" valign="middle">
                	<kul:htmlControlAttribute property="copyCriteria.includeBudget" 
                	                          attributeEntry="${proposalCopyCriteriaAttributes.includeBudget}"
                	                          disabled="${KualiForm.isCopyBudgetDisabled}" readOnly="${!canCopy}"/>
                	<kul:htmlControlAttribute property="copyCriteria.budgetVersions" 
                	                          attributeEntry="${proposalCopyCriteriaAttributes.budgetVersions}" 
                	                          disabled="${KualiForm.isCopyBudgetDisabled}" readOnly="${!canCopy}"/>
                	                         
				</td>
			</tr>
			
			<tr>
				<th align="right" valign="middle">
        			<kul:htmlAttributeLabel attributeEntry="${proposalCopyCriteriaAttributes.includeAttachments}" />
        		</th>
        		
				<td align="left" valign="middle">
                	<kul:htmlControlAttribute property="copyCriteria.includeAttachments" 
                	                          attributeEntry="${proposalCopyCriteriaAttributes.includeAttachments}" 
                	                          disabled="${KualiForm.isCopyAttachmentsDisabled}"  readOnly="${!canCopy}"/>
                </td>
			</tr>
			
			<tr>
                <th align="right" valign="middle">
                    <kul:htmlAttributeLabel attributeEntry="${proposalCopyCriteriaAttributes.leadUnitNumber}" />
                </th>
                
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="copyCriteria.leadUnitNumber" 
                                              attributeEntry="${proposalCopyCriteriaAttributes.leadUnitNumber}"  readOnly="${!canCopy}"/>
                </td>
            </tr>
         
         	<tr>
				<th align="right" valign="middle">
        			<kul:htmlAttributeLabel attributeEntry="${proposalCopyCriteriaAttributes.includeQuestionnaire}" />
        		</th>
        		
				<td align="left" valign="middle">
                	<kul:htmlControlAttribute property="copyCriteria.includeQuestionnaire" 
                	                          attributeEntry="${proposalCopyCriteriaAttributes.includeQuestionnaire}" 
                	                          readOnly="${!canCopy}"/>
                </td>
			</tr>
         
            
			<tr>
				<td align="center" colspan="2">
					<div align="center">
						<html:image property="methodToCall.copyProposal.anchor${tabKey}" disabled="${!canCopy}"
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-copyprop.gif' styleClass="tinybutton"/>
					</div>
                </td>
			</tr>
			
			
			
        </table>
    </div> 
</kul:tab>
