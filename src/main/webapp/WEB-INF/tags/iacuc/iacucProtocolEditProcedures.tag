<%--
 Copyright 2005-2013 The Kuali Foundation
 
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

<%@ attribute name="procedureIndex" required="true" 
              description="Index to identify the procedure of a specific collection for procedure display" %>
<%@ attribute name="displayTitle" required="true" 
              description="Title for this popup" %>
<%@ attribute name="procedureCollectionReference" required="true" type="java.util.List" 
              description="The object reference to the collection that holds all procedure details" %>
<%@ attribute name="procedureCollectionProperty" required="true" 
              description="Property name of the collection that holds study group procedures" %>
<%@ attribute name="submitMethod" required="true" 
              description="Indicate the method to trigger on submit" %>
<%@ attribute name="isPersonEditProcedure" required="true" 
              description="Indicate whether this is part of person edit procedure " %>


<tbody id="content-div${procedureIndex}" style="display: none;">
	<tr>
    	<th class="content_grey" style="text-align:center; background-color:#666; color:#FFF;" colspan="2">
              	${displayTitle}
        </th>
    </tr>     
   	<c:forEach var="procedure" items="${procedureCollectionReference}" varStatus="procStatus">
       	<tr>
           	<th align="left">
	            	<c:out value="${procedure.iacucProtocolStudyGroupSpecies.iacucSpecies.speciesName}"/>
            	</th>
        </tr>
		<c:set var="collectionReference" value="${procedure.responsibleProcedures}" />
		<c:set var="collectionProperty" value="${procedureCollectionProperty}[${procedureIndex}].procedureDetails[${procStatus.index}].responsibleProcedures" />
      	<c:forEach var="responsibleProcedure" items="${collectionReference}" varStatus="detailStatus">
    		<c:set var="prop" value="${collectionProperty}[${detailStatus.index}].studyProcedureActive"/>
        	${kfunc:registerEditableProperty(KualiForm, prop)} 
        	<input type="hidden" name="checkboxToReset" value="${prop}"/>
              	<tr>
              		<td>
            		<html:checkbox styleClass="checkBox" property="${collectionProperty}[${detailStatus.index}].studyProcedureActive"/>
			    	<c:out value="${responsibleProcedure.iacucProtocolStudyGroupBean.iacucProcedure.procedureDescription}" />
           		</td>
           	</tr>	
      	</c:forEach>
    </c:forEach>
	<c:if test="${isPersonEditProcedure}">
		<c:set var="protocolPersonAttributes" value="${DataDictionary.IacucProtocolPerson.attributes}" />
		<tr>
        	<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${protocolPersonAttributes.procedureQualificationDescription}" noColon="true" /></nobr></div></th>
		</tr>   		                          
	    <tr>
	    	<td>
	        	<kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${procedureIndex}].procedureQualificationDescription" 
	            	attributeEntry="${protocolPersonAttributes.procedureQualificationDescription}" 
	               	readOnly="${readOnly}" />
	        </td>
	   	</tr>   		                          
	</c:if>
	<tr>
		<td>
    		<div id="procedureEdit" class="globalbuttons">
				<html:image styleId="onProcedureEdit" property="methodToCall.${submitMethod}" 
			     		src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="tinybutton"
			     		onclick="submitFormToMethod('kualiForm', '${submitMethod}');"/>
			</div>					
		</td>
	</tr>	
</tbody>
