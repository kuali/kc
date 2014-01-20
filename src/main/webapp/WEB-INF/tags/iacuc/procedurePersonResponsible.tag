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

<%@ attribute name="collectionReference" required="true" type="java.util.List" 
              description="The object reference to the collection that holds all the current Procedure person responsible" %>
<%@ attribute name="collectionProperty" required="true" 
              description="The property name of the collection that holds all the current Procedure person responsible" %>
<%@ attribute name="procedureBeanIndex" required="true" 
              description="The procedure bean index" %>
<%@ attribute name="procedureDetailBeanIndex" required="true" 
              description="The procedure bean index" %>
<%@ attribute name="procedurePersonProperty" required="true" 
              description="The procedure person property" %>

<c:set var="modifyPermission" value="${KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}" />
<c:set var="readOnly" value="${!modifyPermission}" />

<c:set var="personResponsibleAttributes" value="${DataDictionary.IacucProcedurePersonResponsible.attributes}" />
<c:set var="protocolPersonAttributes" value="${DataDictionary.IacucProtocolPerson.attributes}" />

<kul:innerTab tabTitle="Persons Responsible" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="iacucProtocolStudyGroupBeans[${procedureBeanIndex}].iacucProtocolStudyGroupDetailBeans[${procedureDetailBeanIndex}].newIacucProcedurePersonResponsible*" useCurrentTabIndexAsKey="true">
	<div class="innerTab-container" align="left">
    	<h3>
    		<span class="subhead-left">Persons Responsible</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="${businessObjectClassName}" altText="help"/></span>
        </h3>
        
        <table id="procedurePersonsTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${personResponsibleAttributes.protocolPersonsResponsible}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${personResponsibleAttributes.trainingDetails}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolPersonAttributes.procedureQualificationDescription}" noColon="true" /></nobr></div></th>
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
          	</tr>     

            
        	<c:forEach var="protocolPersonResponsible" items="${collectionReference}" varStatus="status">
                <tr>
	                <c:set var="textAreaFieldName" value="${collectionProperty}[${status.index}].personResponsibleDescription" />

		        	<c:choose>
		        		<c:when test="${not empty protocolPersonResponsible.trainingDetails}">
	                	<c:set var="training" value="${protocolPersonResponsible.trainingDetails}" />
		        	</c:when><c:otherwise>
	                	<c:set var="training" value="" />
		        	</c:otherwise></c:choose>


					<th class="infoline">
					   <c:out value="${status.index+1}" />
					</th>

		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
							<c:out value="${protocolPersonResponsible.personName}" />
		            	</div>
		            </td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
					   		<c:out value="${training}" />
		            	</div>
					</td>
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
							<c:out value="${protocolPersonResponsible.protocolPerson.procedureQualificationDescription}" />
		            	</div>
					</td>
					<td><div align=center>
                        <c:if test="${!readOnly}">
						    <c:set var="personBean" value="${collectionProperty}[${status.index}]" />
                            <html:image property="methodToCall.deleteProcedureGroupPersonResponsible.${personBean}.line${status.index}"
									    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                        </c:if>
	                </div></td>
	            </tr>
        	</c:forEach>
        </table>
    </div>
</kul:innerTab>
