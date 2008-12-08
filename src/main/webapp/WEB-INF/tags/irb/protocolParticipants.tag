<%--
 Copyright 2006-2008 The Kuali Foundation

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

<c:set var="protocolParticipantAttributes" value="${DataDictionary.ProtocolParticipant.attributes}" />
<c:set var="participantTypeAttributes" value="${DataDictionary.ParticipantType.attributes}" />
<c:set var="action" value="protocolParticipant" />

<kul:tab tabTitle="Participant Types" defaultOpen="false" tabErrorKey="newProtocolParticipant.*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Participant Types </span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.bo.ProtocolType" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 class="datatable" summary="View/edit protocol participants">
        
        	<%-- Header --%>
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${protocolParticipantAttributes.participantTypeCode}" scope="col" />
				<kul:htmlAttributeHeaderCell attributeEntry="${protocolParticipantAttributes.participantCount}" scope="col" />
				<c:if test="${not readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
			</tr>
			<%-- Header --%>
			
            <%-- New data --%>
        	<kra:section permission="modifyProtocol">
	            <tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>
	
	            <td align="left" valign="middle" class="infoline">
	               	<div align="center">
	               		<kul:htmlControlAttribute property="newProtocolParticipant.participantTypeCode" attributeEntry="${protocolParticipantAttributes.participantTypeCode}" />
	            	</div>
				</td>
				
	
	            <td align="left" valign="middle" class="infoline">
	               	<div align="center">
	               	    <kul:htmlControlAttribute property="newProtocolParticipant.participantCount" attributeEntry="${protocolParticipantAttributes.participantCount}" />
	               	</div>
	            </td>
	
				<td align="left" valign="middle" class="infoline">
					<div align=center>
						<html:image property="methodToCall.addProtocolParticipant.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
	               </td>
	            </tr>
            </kra:section>
			<%-- New data --%>
			
			<%-- Existing data --%>
        	<c:forEach var="protocolParticipant" items="${KualiForm.document.protocol.protocolParticipants}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                	<div align="center"> <kul:htmlControlAttribute property="document.protocol.protocolParticipants[${status.index}].participantTypeCode" attributeEntry="${protocolParticipantAttributes.participantTypeCode}"  /> </div>
					</td>
	                <td align="left" valign="middle">
	                	<div align="center"> <kul:htmlControlAttribute property="document.protocol.protocolParticipants[${status.index}].participantCount" attributeEntry="${protocolParticipantAttributes.participantCount}" /> </div>
	                </td>

					<td>
						<div align=center>&nbsp;
							<kra:section permission="modifyProtocol">  
								<html:image property="methodToCall.deleteProtocolParticipant.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							</kra:section>  
						</div>
	                </td>
	            </tr>
        	</c:forEach>
			<%-- Existing data --%>
			        				
        </table>
    </div>
</kul:tab>
