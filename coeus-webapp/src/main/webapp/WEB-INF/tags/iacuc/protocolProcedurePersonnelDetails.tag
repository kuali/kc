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

<c:set var="parentTabName" value="" />
<c:set var="personResponsibleAttributes" value="${DataDictionary.IacucProcedurePersonResponsible.attributes}" />
<c:set var="personAttributes" value="${DataDictionary.IacucProtocolPerson.attributes}" />
<c:set var="protocolPersonAttributes" value="${DataDictionary.IacucProtocolPerson.attributes}" />
<c:set var="procedureViewedBySpecies" value="${KualiForm.iacucProtocolProceduresHelper.procedureViewedBySpecies}" />

<kul:innerTab tabTitle="Personnel" parentTab="${parentTabName}" defaultOpen="true" tabErrorKey="" useCurrentTabIndexAsKey="true">
		<h3>
  			<span class="subhead-left">Personnel</span>
  			<span class="subhead-right"><kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="iacucProtocolProcedurePersonnelHelp" altText="Help"/></span>
     	</h3>
   		<table id="procedure-personnel-table" cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${personAttributes.personName}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolPersonAttributes.procedureQualificationDescription}" noColon="true" /></nobr></div></th>
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
          	</tr>     
			<c:forEach items="${KualiForm.document.protocolList[0].protocolPersons}" var="person" varStatus="status">
				<c:set var="procedurePersonIndex" value="${status.index}"/>
	          	<tr>
					<th class="infoline">
					   <c:out value="${status.index+1}" />
					</th>
			      	<td width="20%" align="left" valign="middle" class="infoline">
			         	<div align="left">
							<c:out value="${person.personName}" />
							</br>
							<a href="#training-div${procedurePersonIndex}" id="viewTrainingLink" >
							    <img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-viewtrainingdetails.gif" alt="View Training Details" class="tinybutton addButton" />
							</a>		               	
			      		</div>
			      	</td>
		            <td width="60%" align="left" valign="middle" class="infoline">
		               	<div align="left">
							<c:out value="${person.procedureQualificationDescription}" />
		            	</div>
					</td>
		            <td width="20%" align="left" valign="middle" class="infoline">
		               	<div align="center">
								<a href="#content-div${procedurePersonIndex}" id="editProcedureLink" >
								    <img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-assigneditproceedures.gif" alt="Edit Procedure" class="tinybutton addButton" />
								</a>		               	
		            	</div>
					</td>
				</tr>
				<c:set var="displayTrainingTitle" value="Training Details for : ${person.personName}" />
				<c:set var="trainingCollectionReference" value="${KualiForm.document.protocolList[0].protocolPersons[status.index].iacucPersonTrainings}" />
   				<kra-iacuc:iacucProtocolPersonTraining
                    personIndex="${procedurePersonIndex}"
                    displayTitle="${displayTrainingTitle}"
                    trainingCollectionReference="${trainingCollectionReference}"/>
				
				<c:set var="displayTitle" value="Procedures Conducted by: ${person.personName}" />
				<c:set var="procedureCollectionReference" value="${KualiForm.document.protocolList[0].protocolPersons[status.index].procedureDetails}" />
				<c:set var="procedureCollectionProperty" value="document.protocolList[0].protocolPersons" />
		 		<c:set var="submitMethod" value="setEditPersonProcedures.line${procedurePersonIndex}" />
   				<kra-iacuc:iacucProtocolEditProcedures
                    procedureIndex="${procedurePersonIndex}"
                    displayTitle="${displayTitle}"
                    procedureCollectionReference="${procedureCollectionReference}"
                    procedureCollectionProperty="${procedureCollectionProperty}"
                    submitMethod="${submitMethod}" 
                    isPersonEditProcedure="true"
                    procedureViewedBySpecies="${procedureViewedBySpecies}"/>
			</c:forEach>
   		</table>
</kul:innerTab>
