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

<%@ attribute name="collectionReference" required="true" type="java.util.List" 
              description="The object reference to the collection that holds all the current Protocol Species" %>
<%@ attribute name="collectionProperty" required="true" 
              description="The property name of the collection that holds all the current Protocol Species" %>
<%@ attribute name="procedureBeanProperty" required="true" 
              description="The procedure bean property" %>
<%@ attribute name="procedureBeanIndex" required="true" 
              description="The procedure bean index" %>

<c:set var="studyGroupBeanAttributes" value="${DataDictionary.IacucProtocolStudyGroupBean.attributes}" />
<c:set var="protocolStudyGroupAttributes" value="${DataDictionary.IacucProtocolStudyGroup.attributes}" />
<c:set var="studyGroupDetailBeanAttributes" value="${DataDictionary.IacucProtocolStudyGroupDetailBean.attributes}" />

<c:set var="modifyPermission" value="${KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}" />
<c:set var="readOnly" value="${!modifyPermission}" />

<table id="protocolProcedures" cellpadding="0" cellspacing="0" summary="">
	<tr>
  		<th><div align="left">&nbsp;</div></th> 
  		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${studyGroupBeanAttributes.protocolSpeciesAndGroups}" noColon="true" /></div></th>
  		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${studyGroupBeanAttributes.protocolPersonsResponsible}" noColon="true" /></div></th>
  		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolStudyGroupAttributes.painCategoryCode}" noColon="true" /></nobr></div></th>
  		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolStudyGroupAttributes.count}" noColon="true" /></div></th>
		<c:if test="${!readOnly}">
			<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
		</c:if>		
  	</tr>
	<kra:permission value="${KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}">   
    	<tr>
			<th class="infoline">
				Add:
			</th>
         	<td width="40%" align="left" valign="middle" class="infoline">
            	<div align="center">
            		<kul:htmlControlAttribute property="${procedureBeanProperty}.protocolSpeciesAndGroups" 
         		                          attributeEntry="${studyGroupBeanAttributes.protocolSpeciesAndGroups}" 
         		                          readOnly="${readOnly}" />
         		</div>
			</td>
         	<td width="40%" align="left" valign="middle" class="infoline">
            	<div align="center">
            		<kul:htmlControlAttribute property="${procedureBeanProperty}.protocolPersonsResponsible" 
         		                          attributeEntry="${studyGroupBeanAttributes.protocolPersonsResponsible}" 
         		                          readOnly="${readOnly}" />
         		</div>
			</td>
         	<td width="10%" align="left" valign="middle" class="infoline">
            	<div align="center">
            		&nbsp;
         		</div>
			</td>
         	<td width="10%" align="left" valign="middle" class="infoline">
            	<div align="center">
            		&nbsp;
         		</div>
			</td>
			<td class="infoline" rowspan="1">
				<div align="center">
					<html:image property="methodToCall.addProtocolStudyGroup.line${procedureBeanIndex}.anchor${currentTabIndex}" 
          				src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
          				styleClass="tinybutton"/>
         		</div>
         	</td>
     	</tr>
	</kra:permission>         
    <c:forEach var="protocolStudyGroupBeanDetail" items="${collectionReference}" varStatus="detailStatus">
	    <tr>
			<th class="infoline">
	   			<c:out value="${detailStatus.index+1}" />
			</th>
	      	<td align="left" valign="middle" class="infoline">
	         	<div align="center">
					<c:out value="${protocolStudyGroupBeanDetail.speciesAndGroupsText}" />
	      		</div>
	      	</td>
	      	<td align="left" valign="middle" class="infoline">
	         	<div align="center">
					<c:out value="${protocolStudyGroupBeanDetail.listOfPersonsResponsible}" />
	      		</div>
	      	</td>
	      	<td align="left" valign="middle" class="infoline">
	         	<div align="center">
            		<kul:htmlControlAttribute property="${collectionProperty}[${detailStatus.index}].maxPainCategoryCode" 
         		                          attributeEntry="${studyGroupDetailBeanAttributes.maxPainCategoryCode}" 
         		                          readOnly="${readOnly}" />
	      		</div>
	      	</td>
	      	<td align="left" valign="middle" class="infoline">
	         	<div align="center">
            		<kul:htmlControlAttribute property="${collectionProperty}[${detailStatus.index}].totalSpeciesCount" 
         		                          attributeEntry="${studyGroupDetailBeanAttributes.totalSpeciesCount}" 
         		                          readOnly="${readOnly}" />
	         	</div>
	        </td>
			<td rowspan="2">
				<div align=center>
	        		<c:if test="${!readOnly}">
					    <c:set var="procedureBean" value="document.protocolList[0].iacucProtocolStudyGroupBeans[${procedureBeanIndex}].iacucProtocolStudyGroupDetailBeans[${detailStatus.index}]" />
	                	<html:image property="methodToCall.deleteProtocolStudyGroup.line${procedureBean}.line${detailStatus.index}"
		    				src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
	            	</c:if>
	         	</div>
	        </td>
	  	</tr>
	  	<tr>
	    	<td align="left" valign="middle" class="infoline">
	      		&nbsp;
	      	</td>
	     	<td colspan="4">
				<c:set var="procedurePersonsResponsible" value="${KualiForm.document.protocol.iacucProtocolStudyGroupBeans[procedureBeanIndex].iacucProtocolStudyGroupDetailBeans[detailStatus.index].iacucProcedurePersonsResponsible}" />
				<c:set var="procedureLocations" value="${KualiForm.document.protocol.iacucProtocolStudyGroupBeans[procedureBeanIndex].iacucProtocolStudyGroupDetailBeans[detailStatus.index].iacucProtocolStudyGroupLocations}" />
				<c:set var="procedureCustomDataList" value="${KualiForm.document.protocol.iacucProtocolStudyGroupBeans[procedureBeanIndex].iacucProtocolStudyGroupDetailBeans[detailStatus.index].iacucProtocolStudyCustomDataList}" />
				<c:set var="procedureCategoryName" value="${KualiForm.document.protocol.iacucProtocolStudyGroupBeans[procedureBeanIndex].procedureCategory}" />
	     		<kra-iacuc:procedureLocations
	                      collectionReference="${procedureLocations}"
    	                  collectionProperty="document.protocolList[0].iacucProtocolStudyGroupBeans[${procedureBeanIndex}].iacucProtocolStudyGroupDetailBeans[${detailStatus.index}].iacucProtocolStudyGroupLocations"
    	                  procedureBeanIndex="${procedureBeanIndex}"
	                      procedureDetailBeanIndex="${detailStatus.index}"
                     	  procedureLocationProperty="document.protocolList[0].iacucProtocolStudyGroupBeans[${procedureBeanIndex}].iacucProtocolStudyGroupDetailBeans[${detailStatus.index}]"/>
	     		<kra-iacuc:procedurePersonResponsible
	                      collectionReference="${procedurePersonsResponsible}"
    	                  collectionProperty="document.protocolList[0].iacucProtocolStudyGroupBeans[${procedureBeanIndex}].iacucProtocolStudyGroupDetailBeans[${detailStatus.index}].iacucProcedurePersonsResponsible"
    	                  procedureBeanIndex="${procedureBeanIndex}"
	                      procedureDetailBeanIndex="${detailStatus.index}"
                     	  procedurePersonProperty="document.protocolList[0].iacucProtocolStudyGroupBeans[${procedureBeanIndex}].iacucProtocolStudyGroupDetailBeans[${detailStatus.index}]"/>
	     		<kra-iacuc:procedureCustomData
	                      collectionReference="${procedureCustomDataList}"
    	                  collectionProperty="document.protocolList[0].iacucProtocolStudyGroupBeans[${procedureBeanIndex}].iacucProtocolStudyGroupDetailBeans[${detailStatus.index}].iacucProtocolStudyCustomDataList"
    	                  procedureBeanIndex="${procedureBeanIndex}"
	                      procedureDetailBeanIndex="${detailStatus.index}"
                     	  procedureCategoryName="${procedureCategoryName}"/>
			</td>
		</tr>
    </c:forEach>
</table>     
