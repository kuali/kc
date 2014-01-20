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
              description="The object reference to the collection that holds all the current Protocol Species" %>
<%@ attribute name="collectionProperty" required="true" 
              description="The property name of the collection that holds all the current Protocol Species" %>
<%@ attribute name="procedureBeanProperty" required="true" 
              description="The procedure bean property" %>
<%@ attribute name="procedureBeanIndex" required="true" 
              description="The procedure bean index" %>

<c:set var="studyGroupBeanAttributes" value="${DataDictionary.IacucProtocolStudyGroupBean.attributes}" />
<c:set var="protocolStudyGroupAttributes" value="${DataDictionary.IacucProtocolStudyGroup.attributes}" />

<c:set var="modifyPermission" value="${KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}" />
<c:set var="readOnly" value="${!modifyPermission}" />

<table id="protocolProcedures" cellpadding="0" cellspacing="0" summary="">
	<tr>
  		<th><div align="left">&nbsp;</div></th> 
  		<th width="60%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${studyGroupBeanAttributes.protocolSpeciesAndGroups}" noColon="true" /></div></th>
  		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolStudyGroupAttributes.count}" noColon="true" /></div></th>
		<c:if test="${!readOnly}">
			<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
		</c:if>		
  	</tr>
	<kra:permission value="${KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}"> 
		<tbody class="addline">  
    	<tr>
			<th class="infoline">
				Add:
			</th>
         	<td width="60%" align="left" valign="middle" class="infoline">
            	<div align="center">
            		<kul:htmlControlAttribute property="${procedureBeanProperty}.protocolSpeciesAndGroups" 
         		                          attributeEntry="${studyGroupBeanAttributes.protocolSpeciesAndGroups}" 
         		                          readOnly="${readOnly}" styleClass="fixed-size-850-select"/>
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
          				styleClass="tinybutton addButton"/>
         		</div>
         	</td>
     	</tr>
     	</tbody>
	</kra:permission>         
    <c:forEach var="protocolStudyGroupBeanDetail" items="${collectionReference}" varStatus="detailStatus">
	    <tr>
			<th class="infoline">
	   			<c:out value="${detailStatus.index+1}" />
			</th>
	      	<td align="left" valign="middle" class="infoline">
	         	<div align="left">
					<c:out value="${protocolStudyGroupBeanDetail.groupAndSpecies}" />
	      		</div>
	      	</td>
	      	<td align="left" valign="middle" class="infoline">
	         	<div align="center">
            		<kul:htmlControlAttribute property="${collectionProperty}[${detailStatus.index}].iacucProtocolStudyGroup.count" 
         		                          attributeEntry="${protocolStudyGroupAttributes.count}" 
         		                          readOnly="${readOnly}" />
	         	</div>
	        </td>
			<td rowspan="1">
				<div align=center>
	        		<c:if test="${!readOnly}">
					    <c:set var="studyGroup" value="document.protocolList[0].iacucProtocolStudyGroupBeans[${procedureBeanIndex}].iacucProtocolSpeciesStudyGroups[${detailStatus.index}]" />
	                	<html:image property="methodToCall.deleteProtocolSpeciesStudyGroup.line${studyGroup}.line${detailStatus.index}"
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
				<c:set var="procedureCustomDataList" value="${KualiForm.document.protocol.iacucProtocolStudyGroupBeans[procedureBeanIndex].iacucProtocolSpeciesStudyGroups[detailStatus.index].iacucProtocolStudyGroup.iacucProtocolStudyCustomDataList}" />
				<c:set var="procedureCategoryName" value="${KualiForm.document.protocol.iacucProtocolStudyGroupBeans[procedureBeanIndex].procedureCategory}" />
	     		<kra-iacuc:procedureCustomData
	                      collectionReference="${procedureCustomDataList}"
    	                  collectionProperty="document.protocolList[0].iacucProtocolStudyGroupBeans[${procedureBeanIndex}].iacucProtocolSpeciesStudyGroups[${detailStatus.index}].iacucProtocolStudyGroup.iacucProtocolStudyCustomDataList"
    	                  procedureBeanIndex="${procedureBeanIndex}"
	                      procedureDetailBeanIndex="${detailStatus.index}"
                     	  procedureCategoryName="${procedureCategoryName}"/>
			</td>
		</tr>
    </c:forEach>
</table>     
