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
<c:set var="readOnly" value="${not KualiForm.editingMode['addNarratives']}" scope="request" />

<c:set var="propPersonBioAttributes" value="${DataDictionary.ProposalPersonBiography.attributes}" />
<c:set var="propPerDocTypeAttributes" value="${DataDictionary.PropPerDocType.attributes}" />
<c:set var="textAreaFieldName" value="newPropPersonBio.description" />
<c:set var="action" value="proposalDevelopmentAbstractsAttachments" />
<kul:tab tabTitle="Personnel Attachments (${fn:length(KualiForm.document.developmentProposalList[0].propPersonBios)})" defaultOpen="false" tabErrorKey="document.developmentProposalList[0].propPersonBio*,newPropPersonBio*">
	<div class="tab-container" align="center">
	<c:set var="sectionLabel" value="Personnel Attachments" />
	
		<kra:section permission="addNarratives">
			<c:set var="sectionLabel" value="Add ${sectionLabel}" />
        </kra:section>
        
        <h3>
    		<span class="subhead-left"><c:out value="${sectionLabel}" /></span>
            <span class="subhead-right"><kul:help parameterNamespace="KC-PD" parameterDetailType="Document" parameterName="proposalDevelopmentpersonnelattachmentsHelpUrl" altText="help"/></span>
 	    </h3>
        <table cellpadding=0 cellspacing=0 summary="">
        	<c:if test="${fn:length(KualiForm.document.developmentProposalList[0].propPersonBios) > 0  || KualiForm.editingMode['addNarratives']}" >
          	<tr>
          	    <th><div align="left">&nbsp;</div></th> 
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${propPersonBioAttributes.updateTimestamp}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${propPersonBioAttributes.updateUser}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${propPersonBioAttributes.proposalPersonNumber}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${propPersonBioAttributes.documentTypeCode}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${propPersonBioAttributes.description}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${propPersonBioAttributes.fileName}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	</tr>   
          	</c:if>  
          	
          	<kra:section permission="addNarratives">   
          	<tr>
          	  <c:set var="personSelectStyle" value="" scope="request"/>
          	  
			     <c:forEach items="${ErrorPropertyList}" var="key">
				    <c:if test="${key eq 'newPropPersonBio.proposalPersonNumber'}">
					  <c:set var="personSelectStyle" value="background-color:#FFD5D5" scope="request"/>
				    </c:if>
			     </c:forEach>
          	    
				<th class="infoline">
					Add:
				</th>

                <td class="infoline">                
                	<kul:htmlControlAttribute property="newPropPersonBio.updateTimestamp" attributeEntry="${propPersonBioAttributes.updateTimestamp}" readOnly="true" />	            
				</td>
                <td class="infoline">
                	<kul:htmlControlAttribute property="newPropPersonBio.updateUser" attributeEntry="${propPersonBioAttributes.updateUser}" readOnly="true" />
                </td>
                <td class="infoline">          	
          		       <html:select property="newPropPersonBio.proposalPersonNumber" style="${personSelectStyle}">
  		                    <c:set var="proposalPersons" value="${KualiForm.document.developmentProposalList[0].proposalPersons}"/>
  		                    <option value="">select</option>
	    		            <html:options collection="proposalPersons" property="proposalPersonNumber" labelProperty="fullName"/>
	  			        </html:select>
                </td>
                <td class="infoline">                	
                	<kul:htmlControlAttribute property="newPropPersonBio.documentTypeCode" attributeEntry="${propPersonBioAttributes.documentTypeCode}" styleClass="fixed-size-select"/>
				</td>
                <td class="infoline">
                	<kul:htmlControlAttribute property="newPropPersonBio.description" attributeEntry="${propPersonBioAttributes.description}" />
				</td>
                
                <td class="infoline">
                	<html:file property="newPropPersonBio.personnelAttachmentFile" />
                </td>
				<td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addPersonnelAttachment.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
            </tr>
 			</kra:section>
 
 			<c:if test="${fn:length(KualiForm.document.developmentProposalList[0].propPersonBios) > 0}" >
        	<c:forEach var="propPersonBio" items="${KualiForm.document.developmentProposalList[0].propPersonBios}" varStatus="status">
	             <tr>
					<th class="infoline" align="right">
						${status.index+1}:
					</th>
	                <td>
                	   <kul:htmlControlAttribute property="document.developmentProposalList[0].propPersonBio[${status.index}].timestampDisplay" readOnly="true" attributeEntry="${propPersonBioAttributes.updateTimestamp}" /> 
					</td>
	                <td>
                	    ${propPersonBio.uploadUserFullName}
	                </td>
	                <td>
        			    <%-- <input type="hidden" name="document.developmentProposalList[0].propPersonBio[${status.index}].proposalPersonNumber" value="${propPersonBio.proposalPersonNumber}" /> --%> 
	                	<%-- <kul:htmlControlAttribute property="document.developmentProposalList[0].propPersonBio[${status.index}].personId" attributeEntry="${propPersonBioAttributes.personId}" /> --%> 
        				<c:forEach var="keyPerson" items="${KualiForm.document.developmentProposalList[0].proposalPersons}" varStatus="idx">
        				   <c:if test="${keyPerson.proposalPersonNumber == propPersonBio.proposalPersonNumber}" >
        				       ${keyPerson.fullName}
		        			    <%-- <input type="hidden" name="document.developmentProposalList[0].propPersonBio[${status.index}].personId" value="${keyPerson.personId}" /> 
			    			    <input type="hidden" name="document.developmentProposalList[0].propPersonBio[${status.index}].rolodexId" value="${keyPerson.rolodexId}" /> --%> 
        				   </c:if>
						</c:forEach>
	                </td>
	                <td>   
	                    ${propPersonBio.propPerDocType.description}          	
					</td>
	                <td>
	                	${propPersonBio.description} 
					</td>
	                <td>
	                    <kra:fileicon attachment="${propPersonBio}"/> ${propPersonBio.fileName}
	                </td>
	                <td>
					<div align=center>
						<html:image property="methodToCall.viewPersonnelAttachment.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
									onclick="javascript: openNewWindow('proposalDevelopmentAbstractsAttachments','viewPersonnelAttachment',${status.index},${KualiForm.formKey},'${KualiForm.document.sessionDocument}'); return false" /> 
						
						<kra:section permission="addNarratives">  									
							<html:image property="methodToCall.deletePersonnelAttachment.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						</kra:section>
					</div>
	                </td>
	            </tr>
        	</c:forEach> 
        	</c:if>       

          	
        </table>
        <c:set var="sectionLabel" value="" />
    </div>
</kul:tab>
