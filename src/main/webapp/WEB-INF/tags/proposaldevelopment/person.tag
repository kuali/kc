 <%--
 Copyright 2006-2009 The Kuali Foundation

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
<%@ include file="/WEB-INF/jsp/proposaldevelopment/proposalPerson.jsp"%>
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyProposal']}" scope="request" /> 
<c:set var="keypersonrole" value="<%=org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE%>" />
<c:choose>
<c:when test="${empty KualiForm.document.proposalPersons[personIndex].fullName}">
<c:set var="parentTabName" value="" />
</c:when>
<c:otherwise>
<bean:define id="parentTabName" name="KualiForm" property="${proposalPerson}.fullName"/>
</c:otherwise>
</c:choose>
<div class="tab-container" align="center">
<bean:define id="personEditableFields" name="KualiForm" property="personEditableFields" />  
		
		  <c:if test="${readOnly}">
		  	<c:set var="personEditableFields" value="${newMap}" />
		  </c:if>

          <!-- TAB -->
            <div id="workarea">
            <div class="tab-container" align="center" id="G100">
              <h3>
                  <span class="subhead-left"><bean:write name="KualiForm" property="${proposalPerson}.fullName"/></span>
                  <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.Person" altText="help"/></span>
              </h3>
	<table cellpadding=0 cellspacing=0 summary="">
          	<tr>
				<td>

	<c:set var="personDetailsTabErrorKey" value="${proposalPerson}.projectRole*,${proposalPerson}.percentageEffort,${proposalPerson}.pagerNumber*,${proposalPerson}.userName,${proposalPerson}.emailAddress,${proposalPerson}.officePhone,${proposalPerson}.officePhone,${proposalPerson}.eraCommonsUserName,${proposalPerson}.primaryTitle,${proposalPerson}.directoryTitle,${proposalPerson}.faxNumber,${proposalPerson}.mobilePhoneNumber,${proposalPerson}.officeLocation,${proposalPerson}.addressLine1,${proposalPerson}.addressLine2,${proposalPerson}.addressLine3,${proposalPerson}.city,${proposalPerson}.county,${proposalPerson}.state,${proposalPerson}.postalCode,${proposalPerson}.countryCode,${proposalPerson}.facultyFlag" />				
              
	<kul:innerTab tabTitle="Person Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="${personDetailsTabErrorKey}">
			<div class="innerTab-container" align="left">
              <table class=tab cellpadding=0 cellspacing="0" summary=""> 
                <tbody id="G1">
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.proposalPersonRoleId}" /></div></th>
                    <td colspan="3">
                      <kra-pd:proposalPersonRole proposalPerson="${proposalPerson}" personIndex="${personIndex}"/>
                     </td>
                  </tr>              
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.fullName}"  /></div></th>

                    <td>
                      <kul:htmlControlAttribute property="${proposalPerson}.fullName" attributeEntry="${proposalPersonAttributes.fullName}" 
                                                readOnly="${!personEditableFields['fullName'] }"/> 
                    </td>
                    <th align="left" width="15%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.userName}"  /></div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.userName" 
                                                                     attributeEntry="${proposalPersonAttributes.userName}" 
                                                                           readOnly="${!personEditableFields['userName'] }"/>
                    </td>
                  </tr>
                  <tr>
                    <th align="left" width="15%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.emailAddress}"  /></div></th>

                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.emailAddress" 
                                                                     attributeEntry="${proposalPersonAttributes.emailAddress}" 
                                                                           readOnly="${!personEditableFields['emailAddress'] }"/>
                    </td>
                    <th align="left" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.officePhone}"  /> </div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.officePhone" 
                                                                     attributeEntry="${proposalPersonAttributes.officePhone}" 
                                                                           readOnly="${!personEditableFields['officePhone'] }" />
                    </td>
                  </tr>
               
                  <tr>
                    <th align="left" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.primaryTitle}"  /> </div></th>

                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.primaryTitle" 
                                                         attributeEntry="${proposalPersonAttributes.primaryTitle}" 
                                                               readOnly="${!personEditableFields['primaryTitle'] }" />
                    </td>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.directoryTitle}"  /></div></th>
                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.directoryTitle"
                                                         attributeEntry="${proposalPersonAttributes.directoryTitle}" 
                                                               readOnly="${!personEditableFields['directoryTitle'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.homeUnit}"  /></div></th>

                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.homeUnit" 
                                                         attributeEntry="${proposalPersonAttributes.homeUnit}" 
                                                               readOnly="${!personEditableFields['fullName'] }" />
                    </td>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.school}"  /></div></th>
                    <td><kul:htmlControlAttribute property="${proposalPerson}.school" 
                                            attributeEntry="${proposalPersonAttributes.school}" 
                                                  readOnly="${!personEditableFields['school'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.eraCommonsUserName}"  /> </div></th>

                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.eraCommonsUserName" 
                                                                     attributeEntry="${proposalPersonAttributes.eraCommonsUserName}" 
                                                                           readOnly="${!personEditableFields['eraCommonsUserName'] }"/>
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.faxNumber}"  /></div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.faxNumber" 
                                                                     attributeEntry="${proposalPersonAttributes.faxNumber}" 
                                                                           readOnly="${!personEditableFields['faxNumber'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.pagerNumber}"  /></div></th>

                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.pagerNumber" 
                                                                     attributeEntry="${proposalPersonAttributes.pagerNumber}" 
                                                                           readOnly="${!personEditableFields['pagerNumber'] }" />
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.mobilePhoneNumber}"  /> </div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.mobilePhoneNumber" 
                                                                     attributeEntry="${proposalPersonAttributes.mobilePhoneNumber}" 
                                                                           readOnly="${!personEditableFields['mobilePhoneNumber'] }"/>
                    </td>
                  </tr>
                  <tr>
                    <th align="left"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.officeLocation}"  /></div></th>

                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.officeLocation" 
                                                         attributeEntry="${proposalPersonAttributes.officeLocation}" 
                                                               readOnly="${!personEditableFields['officeLocation'] }" />
                    </td>
                    <th align="left"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.secondaryOfficeLocation}"  /></div></th>
                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.secondaryOfficeLocation" 
                                                         attributeEntry="${proposalPersonAttributes.secondaryOfficeLocation}" 
                                                               readOnly="${!personEditableFields['secondaryOfficeLocation'] }" />
                    </td>
                  </tr>
                  
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.addressLine1}"  /></div></th>

                    <td><kul:htmlControlAttribute property="${proposalPerson}.addressLine1" 
                                            attributeEntry="${proposalPersonAttributes.addressLine1}" 
                                                  readOnly="${!personEditableFields['addressLine1'] }" />
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.city}"  /></div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.city" 
                                                                     attributeEntry="${proposalPersonAttributes.city}" 
                                                                           readOnly="${!personEditableFields['city'] }" />
                    </td>
                   </tr>
                  
                  
                  <tr>
                  	<th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.addressLine2}"  /></div></th>
                     	<td><kul:htmlControlAttribute property="${proposalPerson}.addressLine2" 
                                            attributeEntry="${proposalPersonAttributes.addressLine2}" 
                                                  readOnly="${!personEditableFields['addressLine2'] }" />
                      	</td>
                    
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.county}"  /></div></th>
                     	<td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.county" 
                                                                     attributeEntry="${proposalPersonAttributes.county}" 
                                                                           readOnly="${!personEditableFields['county'] }" />
                          </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.addressLine3}"  /></div></th>

                    <td><kul:htmlControlAttribute property="${proposalPerson}.addressLine3" 
                                            attributeEntry="${proposalPersonAttributes.addressLine3}" 
                                                  readOnly="${!personEditableFields['addressLine3'] || addressLine3}" />
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.state}"  /></div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.state" 
                                                                     attributeEntry="${proposalPersonAttributes.state}" 
                                                                           readOnly="${!personEditableFields['state'] }" />
                    </td>
                  </tr>
                  
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.postalCode}"  /></div></th>
                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.postalCode" 
                                                                     attributeEntry="${proposalPersonAttributes.postalCode}" 
                                                                           readOnly="${!personEditableFields['postalCode'] }" />
                    </td>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.countryCode}"  /></div></th>
                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.countryCode" 
                                                                     attributeEntry="${proposalPersonAttributes.countryCode}" 
                                                                           readOnly="${!personEditableFields['countryCode'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.percentageEffort}"  /></div></th>
                    <td align="left"><span>
                      <kul:htmlControlAttribute property="${proposalPerson}.percentageEffort" 
                                          attributeEntry="${proposalPersonAttributes.percentageEffort}" 
                                                readOnly="${!personEditableFields['percentageEffort'] }" />
                    </span></td>
                    <th align="left" nowrap="nowrap"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.facultyFlag}"  /></div></th>

                    <td align="left"><label>
                       <kul:htmlControlAttribute property="${proposalPerson}.facultyFlag" 
                                          attributeEntry="${proposalPersonAttributes.facultyFlag}" 
                                                readOnly="${!personEditableFields['facultyFlag']}" />
                    </label></td>
                  </tr>
   </tr>
    
    
     <c:if test="${KualiForm.document.proposalPersons[personIndex].proposalPersonRoleId == keypersonrole}">
    
                  <tr>
                  <th align="left" nowrap="nowrap" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.projectRole}"  /></div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.projectRole" 
                                                                     attributeEntry="${proposalPersonAttributes.projectRole}" 
                                                                       readOnly="${!personEditableFields['projectRole'] }" />
                    </td>
                    

 
       <c:if test="${KualiForm.document.nih && KualiForm.document.proposalPersons[personIndex].proposalPersonRoleId == keypersonrole}">
                 <th align="left" nowrap="nowrap"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.otherSignificantContributorFlag}" /></div></th>
                    <td align="left"><span>
                      <kul:htmlControlAttribute property="${proposalPerson}.otherSignificantContributorFlag" 
                                          attributeEntry="${proposalPersonAttributes.otherSignificantContributorFlag}" 
                                                readOnly="${!personEditableFields['otherSignificantContributorFlag'] }" />
                    </span></td>
         </c:if>
          
           </tr>
       </c:if>           
    
      </tr>
       </tbody>
</table></div>
</kul:innerTab>
</td></tr>
<tr>
<td colspan=4>
<kul:innerTab tabTitle="Degrees" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="newProposalPersonDegree.*,newProposalPersonDegree[${personIndex}].graduationYear*,newProposalPersonDegree[${personIndex}].degree*,newProposalPersonDegree[${personIndex}].degreeCode*">
 <table class=tab cellpadding=0 cellspacing="0" summary="" >
     <kra-pd:personDegreeSection proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>
  </table>
</kul:innerTab>
</td></tr>

<bean:define id="unitDetailsRequired" name="KualiForm" property="${proposalPerson}.role.unitDetailsRequired" />
<c:set var="unitsErrorKey" value="document.proposalPersons[${personIndex}].units*,newProposalPersonUnit[${personIndex}]*" />
<c:choose>
 <c:when test="${unitDetailsRequired == 'Y'  || !KualiForm.editingMode['modifyProposal']}">
   	<tr>
		<td colspan=4>
  <kul:innerTab tabTitle="Unit Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="${unitsErrorKey}">
              <table class=tab cellpadding=0 cellspacing="0" summary="" >
              <kra-pd:personUnitSection proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>
  </table>
  </kul:innerTab>
 
  </td>
  </tr>
  </c:when>
  <c:otherwise>
     <c:choose>
      <c:when test="${KualiForm.document.proposalPersons[personIndex].optInUnitStatus == 'Y'}"> 
   	  <tr><td colspan=4>
      <kul:innerTab tabTitle="Unit Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="${unitsErrorKey}">
        <div class="innerTab-container" align="left">
         <table class=tab cellpadding=0 cellspacing="0" summary=""> 
         <tr>
            <td colspan=3><div class="floaters">
               <p> You have the option to remove unit details for a key person.</p>
               <p><html:image property="methodToCall.removeUnitDetails.${proposalPerson}.line${personIndex}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-remunitdet.gif" title="Add Unit Details" alt="Add Unit Details" styleClass="tinybutton"/></p>
             </div>
           </td>
        </tr>
       <tr>
        <td>
           <table class=tab cellpadding=0 cellspacing="0" summary="" >
           <kra-pd:personUnitSection proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>
           </table>
       </td>
     </tr>
    </table>
    </div>
   </kul:innerTab>
  </td>
  </tr>
  </c:when>
   <c:otherwise>
   <tr><td colspan=4>
   <kul:innerTab tabTitle="Unit Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="document.proposalPersons[${personIndex}].newProposalPersonUnit*,newProposalPersonUnit[${status.index}]*">
   <div class="innerTab-container" align="left">
   <table class=tab cellpadding=0 cellspacing="0" summary=""> 
    <tr>
       <td colspan=3><div class="floaters">
         <p> You have the option to add unit details for a key person</p>
          <p><html:image property="methodToCall.addUnitDetails.${proposalPerson}.line${status.index}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-addunitdet.gif" title="Add Unit Details" alt="Add Unit Details" styleClass="tinybutton"/></p>
          </div>
       </td>
     </tr>
     </div>
     </table>
     </kul:innerTab>
    </td>
  </tr>
   </c:otherwise>
  </c:choose>
</c:otherwise>
</c:choose>

<bean:define id="certificationRequired" name="KualiForm" property="${proposalPerson}.role.certificationRequired" /> 

<c:choose>
 <c:when test="${certificationRequired == 'Y'  || !KualiForm.editingMode['modifyProposal']}">
   	<tr>
	<td colspan=4>
  <kul:innerTab tabTitle="Certify" parentTab="${parentTabName}" defaultOpen="false"  auditCluster="keyPersonnelAuditErrors" tabAuditKey="document.proposalPersons[${personIndex}]*">
     <table class=tab cellpadding=0 cellspacing="0" summary="" >
     <kra-pd:personYnqSection proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>
    </table>
 </kul:innerTab>
   </td>
  </tr>
  </c:when>
  <c:otherwise>
     <c:choose>
      <c:when test="${KualiForm.document.proposalPersons[personIndex].optInCertificationStatus == 'Y'}"> 
   	  <tr><td colspan=4>
      <kul:innerTab tabTitle="Certify" parentTab="${parentTabName}" defaultOpen="false" auditCluster="keyPersonnelAuditErrors" tabAuditKey="document.proposalPersons[${personIndex}]*" >
      <div class="innerTab-container" align="left">
       <table class=tab cellpadding=0 cellspacing="0" summary=""> 
      <tr>
       <td colspan=3><div class="floaters">
         <p> You have the option to remove Certification Questions for a key person.</p>
          <p><html:image property="methodToCall.removeCertificationQuestion.${proposalPerson}.line${status.index}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-remcertquest.gif" title="Add Unit Details" alt="Add Unit Details" styleClass="tinybutton"/></p>
          </div>
       </td>
     </tr>
   <tr>
     <td>
       <table class=tab cellpadding=0 cellspacing="0" summary="" >
        <kra-pd:personYnqSection proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>
      </table>
   </td>
   </tr>
   </div>
   </table>
   </kul:innerTab>
  </td>
  </tr>
  </c:when>
   <c:otherwise>
   <tr><td colspan=4>
   <kul:innerTab tabTitle="Certify" parentTab="${parentTabName}" defaultOpen="false" >
    <div class="innerTab-container" align="left">
     <table class=tab cellpadding=0 cellspacing="0" summary=""> 
      <tr>
       <td colspan=3><div class="floaters">
         <p> You have the option to add Certification Questions for a key person</p>
          <p><html:image property="methodToCall.addCertificationQuestion.${proposalPerson}.line${personIndex}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-inclcertquest.gif" title="Add Certification Question" alt="Add Certification Question" styleClass="tinybutton"/></p>
          </div>
       </td>
   </tr>
   </div>
   </table>
   </kul:innerTab>
  </td>
  </tr>
  </c:otherwise>
 </c:choose>
</c:otherwise>

</c:choose>

</table>
  </div>
   </div>
</div>

