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

<c:set var="finEntAttachAttributes" value="${DataDictionary.FinancialEntityAttachment.attributes}" />
<c:set var="attachmentFileAttributes" value="${DataDictionary.AttachmentFile.attributes}" />

	<div class="tab-container" align="center">
    	<h3>
        	<span class="subhead-left"> 
	        <a href="#" id ="finEntityAttachmentsControl" class="finEntityAttachmentsSubpanel"><img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> Attachments </span>
    	    <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachment" altText="help"/> </span>
	    </h3>
    	<div id="finEntityAttachmentsContent" class="finEntityAttachmentsSubpanelContent">                    
			<table id="attachments-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
       			<tbody id="G3">
		          	<tr>
        		  	    <th><div align="left">&nbsp;</div></th> 
          				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${finEntAttachAttributes.updateTimestamp}" noColon="true" /></div></th>
		          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${finEntAttachAttributes.updateUser}" noColon="true" /></div></th>
        		  		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${finEntAttachAttributes.contactName}" noColon="true" /></div></th>
          				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${finEntAttachAttributes.contactEmailAddress}" noColon="true" /></div></th>
		          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${finEntAttachAttributes.description}" noColon="true" /></div></th>
        		  		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${finEntAttachAttributes.fileName}" noColon="true" /></div></th>
              			<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
		          	</tr>   

			        <%-- Start New data --%>
		            <c:if test="${!readOnly}">
		            	<c:set var="newAttachment" value="financialEntityHelper.newFinEntityAttachment" />
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
	            			   		<c:if test="${hasErrors}">
                    			 		<kul:fieldShowErrorIcon />
		                    	    </c:if>
				            	</div>
			                </td>
	         				<td colspan="4" class="infoline">
								<div align="center">
									<html:image property="methodToCall.addNewFinancialEntityAttachment.anchor${tabKey}"
									src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton"/>
								</div>
							</td>
	    		     	</tr>
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
									<html:image property="methodToCall.viewFinancialEntityAttachment.line${attStatus.index}.anchor${currentTabIndex}"
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
