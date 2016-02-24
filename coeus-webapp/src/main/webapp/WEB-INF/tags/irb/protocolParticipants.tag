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

<c:set var="attributes" value="${DataDictionary.ProtocolParticipant.attributes}" />
<c:set var="action" value="protocolParticipant" />
<c:set var="readOnly" value="${!KualiForm.protocolHelper.modifySubjects}" />

<kul:tab tabTitle="Participant Types" defaultOpen="false" tabErrorKey="protocolHelper.newProtocolParticipant*,document.protocolList[0].protocolParticipants*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Participant Types </span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.protocol.participant.ProtocolParticipant" altText="help"/></span>
        </h3>
        
        <table id="participants-table" cellpadding=0 cellspacing=0 class="datatable" summary="View/edit protocol participants">
        
        	<%-- Header --%>
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${attributes.participantTypeCode}" scope="col" />
				<kul:htmlAttributeHeaderCell attributeEntry="${attributes.participantCount}" scope="col" />
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
			</tr>
			<%-- Header --%>
			
            <%-- New data --%>
        	<kra:permission value="${KualiForm.protocolHelper.modifySubjects}">
        		<tbody class="addline">            
	            <tr>
					<th class="infoline">
						<c:out value="Add:" />
					</th>
		
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="protocolHelper.newProtocolParticipant.participantTypeCode" 
		               		                          attributeEntry="${attributes.participantTypeCode}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
				
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               	    <kul:htmlControlAttribute property="protocolHelper.newProtocolParticipant.participantCount" 
		               	                              attributeEntry="${attributes.participantCount}"
		               	                              readOnly="${readOnly}" />
		               	</div>
		            </td>
		
					<td align="left" valign="middle" class="infoline">
						<div align=center>
						    <html:image property="methodToCall.addProtocolParticipant.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
							title= "Add protocol participants" styleClass="tinybutton addButton"/>
						</div>
					</td>
	            </tr>
	            </tbody>
            </kra:permission>
			<%-- New data --%>
			
			<%-- Existing data --%>        	
        	
        	<c:forEach items="${KualiForm.document.protocolList[0].protocolParticipants}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                    <div align="center"> 
	                    	<kul:htmlControlAttribute property="document.protocolList[0].protocolParticipants[${status.index}].participantType.description" 
	                    	                          attributeEntry="${attributes.participantTypeCode}" 
	                    	                          readOnly="true" /> 
	                    </div>
					</td>
	                <td align="left" valign="middle">
	                	<div align="center"> 
	                		<kul:htmlControlAttribute property="document.protocolList[0].protocolParticipants[${status.index}].participantCount" 
	                		                          attributeEntry="${attributes.participantCount}" 
	                		                          readOnly="${readOnly}" /> 
	                	</div>
	                </td>

                   <c:if test="${!readOnly}">
						<td>
							<div align=center>&nbsp;					
								<html:image property="methodToCall.deleteProtocolParticipant.line${status.index}.anchor${currentTabIndex}.validate0"
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
