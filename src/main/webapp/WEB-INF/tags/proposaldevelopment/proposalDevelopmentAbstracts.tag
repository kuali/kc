<%--
 Copyright 2005-2013 The Kuali Foundation
 
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
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyProposal']}" scope="request" />

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="proposalAbstractAttributes" value="${DataDictionary.ProposalAbstract.attributes}" />
<c:set var="action" value="proposalDevelopmentAbstractsAttachments" />
<c:set var="textAreaFieldName" value="newProposalAbstract.abstractDetails" />

<kul:tab tabTitle="Abstracts" defaultOpen="false" 
         tabItemCount="${fn:length(KualiForm.document.developmentProposalList[0].proposalAbstracts)}" 
         tabErrorKey="document.developmentProposalList[0].proposalAbstract*,newProposalAbstract*">
         
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Abstracts</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.ProposalAbstract" altText="help"/></span>
        </h3>
    
        <table id="abstracts-table" cellpadding="0" cellspacing="0" summary="">
        <tbody>
        
        	<%-- Table headers --%>
        	<c:if test="${fn:length(KualiForm.document.developmentProposalList[0].proposalAbstracts) > 0 || KualiForm.editingMode['modifyProposal']}" >
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalAbstractAttributes.updateTimestamp}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalAbstractAttributes.updateUser}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalAbstractAttributes.abstractTypeCode}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalAbstractAttributes.abstractDetails}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	</tr>
          	</c:if>
          	
          	<%-- The input controls for adding a new abstract. --%>
          	<kra:section permission="modifyProposal">   
            <tr class="addline"> 
				<th class="infoline">
					<c:out value="Add:" />
				</th>
				
 				<td>&nbsp;</td>
 				
 				<td>&nbsp;</td>
				
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="newProposalAbstract.abstractTypeCode" 
                	                          attributeEntry="${proposalAbstractAttributes.abstractTypeCode}" />
				</td>
				
                <td align="left">
                	<kul:htmlControlAttribute property="newProposalAbstract.abstractDetails" attributeEntry="${proposalAbstractAttributes.abstractDetails}" />
                </td>
 
				<td>
					<div align="center">
						<html:image property="methodToCall.addAbstract.anchor${tabKey}"
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
					</div>
                </td>
            </tr>
            </kra:section>   
            <%-- The list of current abstracts --%>
            
            <c:if test="${fn:length(KualiForm.document.developmentProposalList[0].proposalAbstracts) > 0}" >
        	<c:forEach var="abstract" items="${KualiForm.document.developmentProposalList[0].proposalAbstracts}" varStatus="status">
	             <tr>
	             	<th>${status.index + 1}</th>
	             	
	             	<td align="left" valign="middle" id="document.developmentProposalList[0].proposalAbstract[${status.index}].timestampDisplay">
	             	  <kul:htmlControlAttribute property="document.developmentProposalList[0].proposalAbstract[${status.index}].timestampDisplay" attributeEntry="${proposalAbstractAttributes.updateTimestamp}" readOnly="true" />
					</td>
					
	             	<td align="left" valign="middle">
                	    <kul:htmlControlAttribute property="document.developmentProposalList[0].proposalAbstract[${status.index}].uploadUserFullName" readOnly="true" attributeEntry="${proposalAbstractAttributes.updateUser}" />
					</td>
					
	             	<td>${abstract.abstractType.description}</td>
	             	
				    <td>
	             		<div align="left">
					        <kul:htmlControlAttribute property="document.developmentProposalList[0].proposalAbstract[${status.index}].abstractDetails" 
					          					      attributeEntry="${proposalAbstractAttributes.abstractDetails}" />
                		</div>
                	</td>
                	
				    <td>
				        <div align="center">&nbsp;
				        	<kra:section permission="modifyProposal">  
					          <html:image property="methodToCall.deleteAbstract.line${status.index}.anchor${tabKey}"
									      src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							</kra:section>
				    	</div>
				    </td>
				</tr>	
        	</c:forEach>
        	</c:if>
        </tbody>
        </table>
    </div> 
</kul:tab>
