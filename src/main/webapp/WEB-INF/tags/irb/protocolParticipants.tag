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

<c:set var="protocolParticipantBeanAttributes" value="${DataDictionary.ProtocolParticipantBean.attributes}" />
<c:set var="action" value="protocolParticipant" />
<c:set var="readOnly" value="${!KualiForm.protocolHelper.modifySubjects}" />

<kul:tab tabTitle="Participant Types" defaultOpen="false" tabErrorKey="participantsHelper.newParticipant.*,participantsHelper.existingParticipants[*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Participant Types </span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.protocol.ProtocolType" altText="help"/></span>
        </h3>
        
        <table id="participants-table" cellpadding=0 cellspacing=0 class="datatable" summary="View/edit protocol participants">
        
        	<%-- Header --%>
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${protocolParticipantBeanAttributes.participantTypeCode}" scope="col" />
				<kul:htmlAttributeHeaderCell attributeEntry="${protocolParticipantBeanAttributes.participantCount}" scope="col" />
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
			</tr>
			<%-- Header --%>
			
            <%-- New data --%>
        	<kra:permission value="${KualiForm.protocolHelper.modifySubjects}">            
	            <tr>
					<th class="infoline">
						<c:out value="Add:" />
					</th>
		
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="participantsHelper.newParticipant.participantTypeCode" attributeEntry="${protocolParticipantBeanAttributes.participantTypeCode}" readOnly="false" />
		            	</div>
					</td>
				
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               	    <kul:htmlControlAttribute property="participantsHelper.newParticipant.participantCount" attributeEntry="${protocolParticipantBeanAttributes.participantCount}" readOnly="false" />
		               	</div>
		            </td>
		
					<td align="left" valign="middle" class="infoline">
						<div align=center>
						    <html:image property="methodToCall.addProtocolParticipant.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
					</td>
	            </tr>
	            
            </kra:permission>
			<%-- New data --%>
			
			<%-- Existing data --%>        	
        	
        	<c:forEach var="protocolParticipant" items="${KualiForm.participantsHelper.existingParticipants}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                    <div align="center"> 
	                    	${protocolParticipant.participantTypeDescription} 
	                    </div>
					</td>
	                <td align="left" valign="middle">
	                	<div align="center"> 
	                		<kul:htmlControlAttribute property="participantsHelper.existingParticipants[${status.index}].participantCount" attributeEntry="${protocolParticipantBeanAttributes.participantCount}" readOnly="${readOnly}" /> 
	                	</div>
	                </td>

                   <c:if test="${!readOnly}">
						<td>
							<div align=center>&nbsp;					
								<html:image property="methodToCall.deleteProtocolParticipant.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							</div>
		                </td>
		            </c:if>
	            </tr>
        	</c:forEach>
        	
        	
			<%-- Existing data --%>
			        				
        </table>
    </div>
</kul:tab>
