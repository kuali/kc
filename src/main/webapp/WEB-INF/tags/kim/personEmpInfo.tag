<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="afflnIdx" required="true" %>
<c:set var="docEmploymentInfoAttributes" value="${DataDictionary.PersonDocumentEmploymentInfo.attributes}" />

<kul:subtab lookedUpCollectionName="empInfo" width="${tableWidth}" subTabTitle="Employment Information">      
	<table cellpadding="0" cellspacing="0" summary="">
       	<tr>
            <th width="5%" rowspan="20" style="border-style:none">&nbsp;</th>
       		<th><div align="left">&nbsp</div></th> 
       		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmploymentInfoAttributes.entityEmploymentId}" noColon="true" /></div></th>
       		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmploymentInfoAttributes.primary}" noColon="true" /></div></th>
       		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmploymentInfoAttributes.employeeStatusCode}" noColon="true" /></div></th>
       		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmploymentInfoAttributes.employeeTypeCode}" noColon="true" /></div></th>
       		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmploymentInfoAttributes.baseSalaryAmount}" noColon="true" /></div></th>
       		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmploymentInfoAttributes.primaryDepartmentCode}" noColon="true" /></div></th>
	        <c:if test="${not inquiry}">	
	              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
	        </c:if>
        </tr>     
      	<c:if test="${not inquiry and not readOnly}">	          	
             <tr>
				<th class="infoline">Add:</th>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="document.affiliations[${afflnIdx}].newEmpInfo.employeeId" attributeEntry="${docEmploymentInfoAttributes.employeeId}" readOnly="${readOnly}" />
	            	</div>
				</td>
                <td class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="document.affiliations[${afflnIdx}].newEmpInfo.primary" attributeEntry="${docEmploymentInfoAttributes.primary}" readOnly="${readOnly}" />
                </div>
                </td>                
                <td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="document.affiliations[${afflnIdx}].newEmpInfo.employeeStatusCode" attributeEntry="${docEmploymentInfoAttributes.employeeStatusCode}" readOnly="${readOnly}" />
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="document.affiliations[${afflnIdx}].newEmpInfo.employeeTypeCode" attributeEntry="${docEmploymentInfoAttributes.employeeTypeCode}" readOnly="${readOnly}" />
                	</div>
                </td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="document.affiliations[${afflnIdx}].newEmpInfo.baseSalaryAmount" attributeEntry="${docEmploymentInfoAttributes.baseSalaryAmount}" readOnly="${readOnly}" />
                	</div>
                </td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="document.affiliations[${afflnIdx}].newEmpInfo.primaryDepartmentCode" attributeEntry="${docEmploymentInfoAttributes.primaryDepartmentCode}" readOnly="${readOnly}" />
                	</div>
                </td>                
                <td class="infoline">
					<div align="center">
						<html:image property="methodToCall.addEmpInfo.line${afflnIdx}.anchor${tabKey}"
						src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
       		</tr>         
  		</c:if>          
    	<c:forEach var="empInfo" items="${KualiForm.document.affiliations[afflnIdx].empInfos}" varStatus="status">
	       	<tr>
				<th class="infoline">
					<c:out value="${status.index+1}" />
				</th>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="document.affiliations[${afflnIdx}].empInfos[${status.index}].employeeId"  attributeEntry="${docEmploymentInfoAttributes.employeeId}" readOnly="${readOnly}" />
				</div>
				</td>
                <td>
                <div align="center"> <kul:htmlControlAttribute property="document.affiliations[${afflnIdx}].empInfos[${status.index}].primary" attributeEntry="${docEmploymentInfoAttributes.primary}" readOnly="${readOnly}" />
                </div>
                </td>	                 
				<td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="document.affiliations[${afflnIdx}].empInfos[${status.index}].employeeStatusCode" attributeEntry="${docEmploymentInfoAttributes.employeeStatusCode}"  readOnlyAlternateDisplay="${empInfo.employmentStatus.employmentStatusName}" readOnly="${readOnly}" />
                	</div>
                </td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="document.affiliations[${afflnIdx}].empInfos[${status.index}].employeeTypeCode" attributeEntry="${docEmploymentInfoAttributes.employeeTypeCode}" readOnlyAlternateDisplay="${empInfo.employmentType.employmentTypeName}" readOnly="${readOnly}" />
                	</div>
                </td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="document.affiliations[${afflnIdx}].empInfos[${status.index}].baseSalaryAmount" attributeEntry="${docEmploymentInfoAttributes.baseSalaryAmount}" readOnly="${readOnly}" />
                	</div>
                </td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="document.affiliations[${afflnIdx}].empInfos[${status.index}].primaryDepartmentCode" attributeEntry="${docEmploymentInfoAttributes.primaryDepartmentCode}" readOnly="${readOnly}" />
                	</div>
                </td>
           			<c:if test="${not inquiry}">						
						<td>
							<div align=center>&nbsp;
				        	     <c:choose>
				        	       	<c:when test="${empInfo.edit  or readOnly}">
				        	          <img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
				        	       	</c:when>
				        	       	<c:otherwise>
				        	          <html:image property='methodToCall.deleteEmpInfo.line${afflnIdx}:${status.index}.anchor${currentTabIndex}'
											src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif' styleClass='tinybutton'/>
				        	       	</c:otherwise>
				        	     </c:choose>  
							</div>
		                </td>
	           		</c:if>     
	    		</tr>	            
        	</c:forEach>        
		<tr>
            <td colspan=10 style="padding:0px; border-style:none; height:22px; background-color:#F6F6F6">&nbsp;</td>
        </tr>            
	</table>
</kul:subtab>
