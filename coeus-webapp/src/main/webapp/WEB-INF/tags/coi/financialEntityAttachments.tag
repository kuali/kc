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

<c:set var="finEntAttachAttributes" value="${DataDictionary.FinancialEntityAttachment.attributes}" />
<c:set var="attachmentFileAttributes" value="${DataDictionary.AttachmentFile.attributes}" />

<kul:tab tabTitle="Attachments" defaultOpen="false" tabErrorKey="financialEntityHelper.newFinEntityAttachment.*">
	<div class="tab-container" align="center">
    	<h3>
        	<span class="subhead-left">Attachments</span>
            <span class="subhead-right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="coiNotesAndAttachmentsHelp" altText="help"/></span>
	    </h3>                    
			<table width="100%" cellpadding="0" cellspacing="0" class="datatable">
       			<tbody id="G3">
		          	<tr>
        		  	    <th><div align="left">&nbsp;</div></th> 
          				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${finEntAttachAttributes.updateTimestamp}" noColon="true" /></div></th>
		          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${finEntAttachAttributes.updateUser}" noColon="true" /></div></th>
        		  		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${finEntAttachAttributes.contactName}" noColon="true" /></div></th>
          				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${finEntAttachAttributes.contactEmailAddress}" noColon="true" /></div></th>
		          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${finEntAttachAttributes.description}" noColon="true" /></div></th>
        		  		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${attachmentFileAttributes['name']}" noColon="true" /></div></th>
              			<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
		          	</tr>   

			        <%-- Start New data --%>
		            <c:if test="${!readOnly}">
		            	<c:set var="newAttachment" value="financialEntityHelper.newFinEntityAttachment" />
		            	<tbody class="addline">
			          	<tr>
							<th class="infoline">
								Add:
							</th>

            			    <td class="infoline">                
	            		    	<div align="left">
				                	<kul:htmlControlAttribute property="${newAttachment}.updateTimestamp" attributeEntry="${finEntAttachAttributes.updateTimestamp}" readOnly="true"/>
				            	</div>
							</td>
            			    <td class="infoline">                
	            		    	<div align="left">
				                	<kul:htmlControlAttribute property="${newAttachment}.updateUser" attributeEntry="${finEntAttachAttributes.updateUser}" readOnly="true"/>
				            	</div>
							</td>
			                <td class="infoline">
	            		    	<div align="left">
		                			<kul:htmlControlAttribute property="${newAttachment}.contactName" attributeEntry="${finEntAttachAttributes.contactName}"/>
				            	</div>
			                </td>
			                <td class="infoline">
	            		    	<div align="left">
	    	            			<kul:htmlControlAttribute property="${newAttachment}.contactEmailAddress" attributeEntry="${finEntAttachAttributes.contactEmailAddress}"/>
				            	</div>
			                </td>
			                <td class="infoline">
	            		    	<div align="left">
        			                <kul:htmlControlAttribute property="${newAttachment}.description" attributeEntry="${finEntAttachAttributes.description}"/>
				            	</div>
			                </td>
			                <td class="infoline">
	            		    	<div align="left">
	    			          		<html:file property="${newAttachment}.newFile" size="50"/>
									<c:set var="fileErrorProperty" value="${newAttachment}.newFile" />
		               				<kul:checkErrors keyMatch="${fileErrorProperty}" auditMatch="${fileErrorProperty}"/>
	            			   		<c:if test="${hasErrors}">
                    			 		<kul:fieldShowErrorIcon />
		                    	    </c:if>
				            	</div>
			                </td>
	         				<td colspan="4" class="infoline">
								<div align="center">
									<html:image property="methodToCall.addNewFinancialEntityAttachment.anchor${tabKey}"
									src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton addButton"/>
								</div>
							</td>
	    		     	</tr>
	    		     	</tbody>
            		</c:if>
			        <%-- End New data --%>
			        
			        <%-- Start Existing data --%>
					<c:forEach var="oldAttachment" items="${KualiForm.financialEntityHelper.finEntityAttachmentList}" varStatus="attStatus">
		            	<tr>
							<th class="infoline" align="right">
								${attStatus.index+1}:
							</th>
	    		            <td>
    	            			<kul:htmlControlAttribute property="financialEntityHelper.finEntityAttachmentList[${attStatus.index}].updateTimestamp"
	    	    	                                      attributeEntry="${finEntAttachAttributes.updateTimestamp}" 
	    		                                          readOnly="true" />
							</td>
    		        	    <td>
                				<kul:htmlControlAttribute property="financialEntityHelper.finEntityAttachmentList[${attStatus.index}].updateUser"
	                	    	                          attributeEntry="${finEntAttachAttributes.updateUser}" 
	            	            	                      readOnly="true" />
							</td>
	    		            <td>
    	            			<kul:htmlControlAttribute property="financialEntityHelper.finEntityAttachmentList[${attStatus.index}].contactName"
	    	    	                                      attributeEntry="${finEntAttachAttributes.contactName}" 
	    		                                          readOnly="true" />
							</td>
    		        	    <td>
                				<kul:htmlControlAttribute property="financialEntityHelper.finEntityAttachmentList[${attStatus.index}].contactEmailAddress"
	        	            	                          attributeEntry="${finEntAttachAttributes.contactEmailAddress}" 
	    	                    	                      readOnly="true" />
							</td>
	    		            <td>
    	            			<kul:htmlControlAttribute property="financialEntityHelper.finEntityAttachmentList[${attStatus.index}].description"
	    	        	                                  attributeEntry="${finEntAttachAttributes.description}" 
	        		                                      readOnly="true" /> 
							</td>
    		        	    <td>
		                	    <kra:fileicon attachment="${oldAttachment.attachmentFile}"/> ${oldAttachment.fileName}
							</td>
			                <td>
								<div align=center>
									<html:image property="methodToCall.viewFinancialEntityAttachment.line${attStatus.index}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
										alt="View Financial Entity Attachment" onclick="excludeSubmitRestriction = true;"/>
									<html:image property="methodToCall.deleteFinancialEntityAttachment.line${attStatus.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
								</div>
    			            </td>
        			    </tr>
		        	</c:forEach> 
			        <%-- End Existing data --%>
    			</tbody>
	        </table>
	</div>	        
</kul:tab>
