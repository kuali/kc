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
          	<tr class="addline">
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
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
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
	                
	                	<div id="replacePersonnelDiv${status.index}" style="display:block;">
	                		<%--
		                    <kra:fileicon attachment="${narrative}"/>
			                <kul:htmlControlAttribute property="document.developmentProposalList[0].narrative[${status.index}].fileName" 
			                	 readOnly="true" attributeEntry="${narrativeAttributes.fileName}" />
			                	  --%>
			               	<kra:fileicon attachment="${propPersonBio}"/> ${propPersonBio.fileName}
		                </div>
		                <div id="filePersonnelDiv${status.index}" valign="middle" style="display:none;">
		                	<%--
		                	<html:file property="document.developmentProposalList[0].narrative[${status.index}].narrativeFile" />
							<html:image property="methodToCall.replaceProposalAttachment.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
							 --%>
							<html:file property="document.developmentProposalList[0].propPersonBios[${status.index}].personnelAttachmentFile" />
							<html:image property="methodToCall.replacePersonnelAttachment.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
	                
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
						<c:set var="replaceKey" value="biographyAttachments.${propPersonBio.positionNumber}.replace" />
						<c:set var="replaceAttachment" value="${KualiForm.editingMode[replaceKey]}" />
						
						<c:if test="${(replaceAttachment) }">							
							<html:image styleId="replacePersonnelAttachment.line${status.index}" 
											onclick="javascript: showHide('filePersonnelDiv${status.index}','replacePersonnelDiv${status.index}') ; return false"  
											src='${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif' styleClass="tinybutton"
											property="methodToCall.replacePersonnelAttachment.line${status.index}.anchor${currentTabIndex};return false" />

					    </c:if>	
					</div>
	                </td>
	            </tr>
        	</c:forEach> 
        	</c:if>       

          	
        </table>
        <c:set var="sectionLabel" value="" />
    </div>
</kul:tab>
