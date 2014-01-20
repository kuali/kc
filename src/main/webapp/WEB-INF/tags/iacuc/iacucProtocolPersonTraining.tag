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

<%@ attribute name="personIndex" required="true" 
              description="Index to identify the procedure of a specific collection for procedure display" %>
<%@ attribute name="displayTitle" required="true" 
              description="Title for this popup" %>
<%@ attribute name="trainingCollectionReference" required="true" type="java.util.List" 
              description="The object reference to the collection that holds all person training details" %>

<c:set var="iacucPersonTrainingAttributes" value="${DataDictionary.IacucPersonTraining.attributes}" />
<c:set var="personTrainingAttributes" value="${DataDictionary.PersonTraining.attributes}" />
<c:set var="trainingAttributes" value="${DataDictionary.Training.attributes}" />
<c:set var="iacucSpeciesAttributes" value="${DataDictionary.IacucSpecies.attributes}" />
<c:set var="iacucProcedureAttributes" value="${DataDictionary.IacucProcedure.attributes}" />

<tbody id="training-div${personIndex}" style="display: none;">
	<tr>
    	<th class="content_grey" style="text-align:center; background-color:#666; color:#FFF;" colspan="9">
              	${displayTitle}
        </th>
    </tr>     
    <tr>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${iacucProcedureAttributes.procedureDescription}" noColon="true" /></div></th>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${iacucSpeciesAttributes.speciesName}" noColon="true" /></div></th>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${personTrainingAttributes.trainingNumber}" noColon="true" /></div></th>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${personTrainingAttributes.trainingCode}" noColon="true" /></div></th>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${trainingAttributes.description}" noColon="true" /></div></th>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${personTrainingAttributes.dateRequested}" noColon="true" /></div></th>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${personTrainingAttributes.dateSubmitted}" noColon="true" /></div></th>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${personTrainingAttributes.dateAcknowledged}" noColon="true" /></div></th>
        	
    </tr>
   	<c:forEach var="iacucTraining" items="${trainingCollectionReference}" varStatus="trainingStatus">
        <tr>
        	<td align="left" valign="middle" class="infoline">
        		<c:out value="${iacucTraining.iacucProcedure.procedureDescription}"/>
        	</td>
        	<td align="left" valign="middle" class="infoline">
        		<c:out value="${iacucTraining.iacucSpecies.speciesName}"/>
        	</td>
        	<td align="left" valign="middle" class="infoline">
        		<c:out value="${iacucTraining.personTraining.trainingNumber}"/>
        	</td>
        	<td align="left" valign="middle" class="infoline">
        		<c:out value="${iacucTraining.personTraining.trainingCode}"/>
        	</td>
        	<td align="left" valign="middle" class="infoline">
        		<c:out value="${iacucTraining.personTraining.training.description}"/>
        	</td>
        	<td align="left" valign="middle" class="infoline">
        		<c:out value="${iacucTraining.personTraining.dateRequested}"/>
        	</td>
        	<td align="left" valign="middle" class="infoline">
        		<c:out value="${iacucTraining.personTraining.dateSubmitted}"/>
        	</td>
        	<td align="left" valign="middle" class="infoline">
        		<c:out value="${iacucTraining.personTraining.dateAcknowledged}"/>
        	</td>
        	
        </tr>
    </c:forEach>
</tbody>
