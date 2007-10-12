<%--
 Copyright 2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="proposalAbstractAttributes" value="${DataDictionary.ProposalAbstract.attributes}" />
<c:set var="action" value="proposalDevelopmentAbstractsAttachments" />
<c:set var="textAreaFieldName" value="newProposalAbstract.abstractDetails" />

<kul:tab tabTitle="Abstracts" defaultOpen="false" 
         tabItemCount="${fn:length(KualiForm.document.proposalAbstracts)}" 
         tabErrorKey="document.proposalAbstracts*">
         
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Abstracts</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        
        <table cellpadding="0" cellspacing="0" summary="">
        
        	<%-- Table headers --%>
        	
          	<tr>
          		<th><div align="left">&nbsp</div></th> 
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalAbstractAttributes.abstractTypeCode}" skipHelpUrl="true" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${proposalAbstractAttributes.abstractDetails}" skipHelpUrl="true" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	</tr>
          	
          	<%-- The input controls for adding a new abstract. --%>
          	
            <tr> 
				<th class="infoline">
					<c:out value="add:" />
				</th>

                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="newProposalAbstract.abstractTypeCode" 
                	                          attributeEntry="${proposalAbstractAttributes.abstractTypeCode}" />
				</td>
				
                <td>
                	<kul:htmlControlAttribute property="newProposalAbstract.abstractDetails" attributeEntry="${proposalAbstractAttributes.abstractDetails}" />
                    <html:image property="methodToCall.updateTextArea.((#${textAreaFieldName}:${action}:${proposalAbstractAttributes.abstractDetails.label}#)).anchor${tabKey}" 
                                src='${ConfigProperties.kra.externalizable.images.url}pencil_add.png' 
                                onclick="javascript: textAreaPop(document.getElementById('${textAreaFieldName}').value,'${textAreaFieldName}','proposalDevelopment','${proposalAbstractAttributes.abstractDetails.label}');return false" 
                                styleClass="tinybutton" /> 
                </td>
 
				<td>
					<div align="center">
						<html:image property="methodToCall.addAbstract.anchor${tabKey}"
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
					</div>
                </td>
            </tr>
            
            <%-- The list of current abstracts --%>
            
            <tr>
            	<td colspan="4">
            	<div  align="left">
            
        			<c:forEach var="abstract" items="${KualiForm.document.proposalAbstracts}" varStatus="status">
	             		<kul:innerTab parentTab="Abstracts" defaultOpen="false" tabTitle="${abstract.abstractType.description}">	
	             			<div class="tab-container" align="center">
								<table cellpadding="0" cellspacing="0" summary="">
			          				<tr>
				          				<td>
	             							<div align="center">
				          					<kul:htmlControlAttribute property="document.proposalAbstracts[${status.index}].abstractDetails" 
				          					                          attributeEntry="${proposalAbstractAttributes.bigAbstractDetails}" />
                							</div>
                						</td>
				          			</tr>
				          				<td>
				          					<div align="center">
				          					<html:image property="methodToCall.deleteAbstract.line${status.index}.anchor${tabKey}"
														src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
				          					</div>
				          				</td>
				          			<tr>
				          			</tr>
				        		</table>
				        	</div>
			        	</kul:innerTab>
        			</c:forEach>
        		</div>
        		</td>
        	</tr>
        </table>
    </div> 
</kul:tab>
