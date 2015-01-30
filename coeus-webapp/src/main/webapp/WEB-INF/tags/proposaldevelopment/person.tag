<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

 <%@ attribute name="proposalPerson" description="The ProposalPerson which this is for." required="true" %>
 <%@ attribute name="index" description="Index of the property for a ProposalPerson" required="false" %>
 <%@ attribute name="personIndex" description="Index of a ProposalPerson" required="true" %>

 <c:set var="proposalPersonAttributes" value="${DataDictionary.ProposalPerson.attributes}" />
 <c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}" />
 <c:set var="isParent" value="${KualiForm.document.developmentProposalList[0].parent}" />

 <c:set var="proposalPersonExtendedAttributes" value="${DataDictionary.ProposalPersonExtendedAttributes.attributes}" />
<%--
<c:set var="proposalPersonExtendedAttributes" value="${DataDictionary.ProposalPersonExtendedAttributes.attributes}" />
 --%>
 
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyProposal']}" scope="request" /> 
<c:set var="keypersonrole" value="<%=org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE%>" />
<c:set var="coirole" value="<%=org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE%>" />
<c:choose>
<c:when test="${empty KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].fullName}">
<c:set var="parentTabName" value="" />
</c:when>
<c:otherwise>
<bean:define id="parentTabName" name="KualiForm" property="${proposalPerson}.fullName"/>
</c:otherwise>
</c:choose>
<bean:define id="personEditableFields" name="KualiForm" property="personEditableFields" />  
		
		  <c:if test="${readOnly or isParent}">
		  	<c:set var="personEditableFields" value="${newMap}" />
		  </c:if>

          <!-- TAB -->
            <div>
            <div class="tab-container" align="center" id="G100">
              <h3>
                  <span class="subhead-left"><bean:write name="KualiForm" property="${proposalPerson}.fullName"/></span>
                  <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.coeus.common.framework.person.KcPerson" altText="help"/></span>
              </h3>
	<table cellpadding=0 cellspacing=0 summary="">
          	<tr>
				<td>

	<c:set var="personDetailsTabErrorKey" value="${proposalPerson}.projectRole*,${proposalPerson}.percentageEffort,${proposalPerson}.pagerNumber*,${proposalPerson}.userName,${proposalPerson}.emailAddress,${proposalPerson}.officePhone,${proposalPerson}.officePhone,${proposalPerson}.eraCommonsUserName,${proposalPerson}.primaryTitle,${proposalPerson}.directoryTitle,${proposalPerson}.faxNumber,${proposalPerson}.mobilePhoneNumber,${proposalPerson}.officeLocation,${proposalPerson}.addressLine1,${proposalPerson}.addressLine2,${proposalPerson}.addressLine3,${proposalPerson}.city,${proposalPerson}.county,${proposalPerson}.state,${proposalPerson}.postalCode,${proposalPerson}.countryCode,${proposalPerson}.facultyFlag" />				
              
	<kul:innerTab tabTitle="Person Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="${personDetailsTabErrorKey}" auditCluster="keyPersonnelAuditErrors" tabAuditKey="document.developmentProposalList[0].proposalPersons[${personIndex}]*">
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
                      <c:choose>
                        <c:when test="${!empty KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].personId}">
                            <input type="hidden" name="${proposalPerson}.personId" value="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].personId}"/>
                            <kul:directInquiry boClassName="org.kuali.coeus.common.framework.person.KcPerson" inquiryParameters="${proposalPerson}.personId:personId" anchor="${tabKey}" />
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="${proposalPerson}.rolodexId" value="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].rolodexId}"/>
                            <kul:directInquiry boClassName="org.kuali.coeus.common.framework.rolodex.Rolodex" inquiryParameters="${proposalPerson}.rolodexId:rolodexId" anchor="${tabKey}" />
                        </c:otherwise>
                      </c:choose>
                    </td>
                    <th align="left" width="15%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.userName}"  /></div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.userName" 
                                                                     attributeEntry="${proposalPersonAttributes.userName}" 
                                                                           readOnly="${!personEditableFields['userName'] }"/>
                    </td>
                  </tr>
                  <tr>
                  <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.firstName}"  /></div></th>
                  <td>                 
                      <kul:htmlControlAttribute property="${proposalPerson}.firstName" attributeEntry="${proposalPersonAttributes.firstName}" 
                                                readOnly="${personEditableFields['firstName'] }"/>
                  </td>
                  <th align="left" width="15%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.lastName}"  /></div></th>
                   <td>
                      <kul:htmlControlAttribute property="${proposalPerson}.lastName" attributeEntry="${proposalPersonAttributes.lastName}" 
                                                readOnly="${personEditableFields['lastName'] }"/>
                  </td>
                  </tr>
                  <tr>
                   <th align="left" width="15%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.middleName}"  /></div></th>
                   <td>                   
                      <kul:htmlControlAttribute property="${proposalPerson}.middleName" attributeEntry="${proposalPersonAttributes.middleName}" 
                                                readOnly="${personEditableFields['middleName'] }"/>
                   
                  </td>
                  <tr>
                  <tr>
                    <th colspan="4">Organization</th>
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
                                                               readOnly="${!personEditableFields['homeUnit'] }" />
                    </td>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.division}"  /></div></th>
                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.division"
                                                         attributeEntry="${proposalPersonAttributes.division}" 
                                                               readOnly="${!personEditableFields['division'] }" />
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
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.countryCode}"  /></div></th>
                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.countryCode" attributeEntry="${proposalPersonAttributes.countryCode}"  
                    	readOnly="${!personEditableFields['countryCode'] }"
                    	onchange="javascript: loadStates(this.options[this.selectedIndex].value, '${proposalPerson}.state');return false" />
                    	
                    	<c:set var="currentCountryCode" value="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].countryCode}"/>
                    	<jsp:setProperty name="KualiForm" property="currentPersonCountryCode" value="${currentCountryCode }"  />  
                    </td>
                    
                  </tr>
                  
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.postalCode}"  /></div></th>
                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.postalCode" attributeEntry="${proposalPersonAttributes.postalCode}" 
                    	readOnly="${!personEditableFields['postalCode'] }" />
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.state}"  /></div></th>
                    <td align="left" width="30%">
                    	${KualiForm.valueFinderResultDoNotCache}
                    	<kul:htmlControlAttribute property="${proposalPerson}.state"  attributeEntry="${proposalPersonAttributes.state}" 
                    		readOnly="${!personEditableFields['state'] }" />
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
     <c:choose>
       <c:when test="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].proposalPersonRoleId == keypersonrole}">
    
                  <tr>
                  <th align="left" nowrap="nowrap" width="15%"> <div align="right">*<kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.projectRole}"  /></div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.projectRole" 
                                                                     attributeEntry="${proposalPersonAttributes.projectRole}" 
                                                                       readOnly="${!personEditableFields['projectRole'] }" />
                    </td>
                    

 
       <c:if test="${KualiForm.document.developmentProposalList[0].sponsorNihOsc && KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].proposalPersonRoleId == keypersonrole}">
                 <th align="left" nowrap="nowrap"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.otherSignificantContributorFlag}" /></div></th>
                    <td align="left"><span>
                      <kul:htmlControlAttribute property="${proposalPerson}.otherSignificantContributorFlag" 
                                          attributeEntry="${proposalPersonAttributes.otherSignificantContributorFlag}" 
                                                readOnly="${!personEditableFields['otherSignificantContributorFlag'] }" />
                    </span></td>
         </c:if>
          
           </tr>
       </c:when>
       <c:when test="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].proposalPersonRoleId == coirole && KualiForm.document.developmentProposal.sponsorNihMultiplePi}">
                  <tr>
                  <th align="left" nowrap="nowrap" width="15%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.multiplePi}"  /></div></th>
                    <td align="left" colspan="3"><kul:htmlControlAttribute property="${proposalPerson}.multiplePi" 
                                                                     attributeEntry="${proposalPersonAttributes.multiplePi}" 
                                                                       readOnly="${!personEditableFields['multiplePi'] }" />
                    </td>
                  </tr>
                         
	   </c:when>
	 </c:choose>
                  <tr>
                    <th colspan="4">Education</th>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.educationLevel}"  /></div></th>
                    <td align="left"><span>
                      <kul:htmlControlAttribute property="${proposalPerson}.educationLevel" 
                                          attributeEntry="${proposalPersonAttributes.educationLevel}" 
                                                readOnly="${!personEditableFields['educationLevel'] }" />
                    </span></td>
                    <th align="left" nowrap="nowrap"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.yearGraduated}"  /></div></th>

                    <td align="left"><label>
                       <kul:htmlControlAttribute property="${proposalPerson}.yearGraduated" 
                                          attributeEntry="${proposalPersonAttributes.yearGraduated}" 
                                                readOnly="${!personEditableFields['yearGraduated']}" />
                    </label></td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.major}"  /></div></th>
                    <td align="left"><span>
                      <kul:htmlControlAttribute property="${proposalPerson}.major" 
                                          attributeEntry="${proposalPersonAttributes.major}" 
                                                readOnly="${!personEditableFields['major'] }" />
                    </span></td>
                    <th align="left" nowrap="nowrap"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.degree}"  /></div></th>

                    <td align="left"><label>
                       <kul:htmlControlAttribute property="${proposalPerson}.degree" 
                                          attributeEntry="${proposalPersonAttributes.degree}" 
                                                readOnly="${!personEditableFields['degree']}" />
                    </label></td>
                  </tr> 
                  <tr>
                    <th align="left" nowrap="nowrap"><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.school}"  /></div></th>
                    <td align="left" colspan="3"><span>
                      <kul:htmlControlAttribute property="${proposalPerson}.school" 
                                          attributeEntry="${proposalPersonAttributes.school}" 
                                                readOnly="${!personEditableFields['school'] }" />
                    </span></td>

                  </tr>                                    
   </tr>           
    
      </tr>
       </tbody>
</table></div>
</kul:innerTab>
</td></tr>

<c:choose>
	<c:when test="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].proposalPersonExtendedAttributes != null}">
		<c:set var="personEA" value="document.developmentProposalList[0].proposalPersons[${personIndex}].proposalPersonExtendedAttributes" />
		<tr>
			<td colspan=4>
				<kul:innerTab tabTitle="Extended Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="document.developmentProposalList[0].proposalPersons*">
					<div class="innerTab-container" align="left">
						<table class=tab cellpadding=0 cellspacing="0" summary="">
							<tbody id="G2">
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.ageByFiscalYear}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.ageByFiscalYear" 
                                          attributeEntry="${proposalPersonExtendedAttributes.ageByFiscalYear}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.race}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.race" 
                                          attributeEntry="${proposalPersonExtendedAttributes.race}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.educationLevel}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.educationLevel" 
                                          attributeEntry="${proposalPersonExtendedAttributes.educationLevel}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.degree}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.degree" 
                                          attributeEntry="${proposalPersonExtendedAttributes.degree}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.major}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.major" 
                                          attributeEntry="${proposalPersonExtendedAttributes.major}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.personId}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.personId" 
                                          attributeEntry="${proposalPersonExtendedAttributes.personId}" 
                                                readOnly="${true }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.handicappedFlag}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.handicappedFlag" 
                                          attributeEntry="${proposalPersonExtendedAttributes.handicappedFlag}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.handicapType}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.handicapType" 
                                          attributeEntry="${proposalPersonExtendedAttributes.handicapType}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.veteranFlag}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.veteranFlag" 
                                          attributeEntry="${proposalPersonExtendedAttributes.veteranFlag}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.veteranType}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.veteranType" 
                                          attributeEntry="${proposalPersonExtendedAttributes.veteranType}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.hasVisa}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.hasVisa" 
                                          attributeEntry="${proposalPersonExtendedAttributes.hasVisa}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.visaCode}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.visaCode" 
                                          attributeEntry="${proposalPersonExtendedAttributes.visaCode}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.visaType}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.visaType" 
                                          attributeEntry="${proposalPersonExtendedAttributes.visaType}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.visaRenewalDate}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.visaRenewalDate" 
                                          attributeEntry="${proposalPersonExtendedAttributes.visaRenewalDate}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.officeLocation}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.officeLocation" 
                                          attributeEntry="${proposalPersonExtendedAttributes.officeLocation}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.secondaryOfficeLocation}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.secondaryOfficeLocation" 
                                          attributeEntry="${proposalPersonExtendedAttributes.secondaryOfficeLocation}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.school}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.school" 
                                          attributeEntry="${proposalPersonExtendedAttributes.school}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.yearGraduated}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.yearGraduated" 
                                          attributeEntry="${proposalPersonExtendedAttributes.yearGraduated}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.directoryDepartment}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.directoryDepartment" 
                                          attributeEntry="${proposalPersonExtendedAttributes.directoryDepartment}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.primaryTitle}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.primaryTitle" 
                                          attributeEntry="${proposalPersonExtendedAttributes.primaryTitle}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.directoryTitle}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.directoryTitle" 
                                          attributeEntry="${proposalPersonExtendedAttributes.directoryTitle}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.vacationAccrualFlag}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.vacationAccrualFlag" 
                                          attributeEntry="${proposalPersonExtendedAttributes.vacationAccrualFlag}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.onSabbaticalFlag}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.onSabbaticalFlag" 
                                          attributeEntry="${proposalPersonExtendedAttributes.onSabbaticalFlag}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.county}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.county" 
                                          attributeEntry="${proposalPersonExtendedAttributes.county}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.idProvided}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.idProvided" 
                                          attributeEntry="${proposalPersonExtendedAttributes.idProvided}" 
                                                readOnly="${readOnly }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.idVerified}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.idVerified" 
                                          attributeEntry="${proposalPersonExtendedAttributes.idVerified}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								<tr>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.versionNumber}"  />
									</div></th>
									<td align-"left">
										<kul:htmlControlAttribute property="${personEA}.versionNumber" 
                                          attributeEntry="${proposalPersonExtendedAttributes.versionNumber}" 
                                                readOnly="${true }" />
									</td>
									<th nowrap="nowrap"><div align="right">
										<kul:htmlAttributeLabel attributeEntry="${proposalPersonExtendedAttributes.citizenshipTypeCode}"  />
									</div></th>
									<td align="left">
										<kul:htmlControlAttribute property="${personEA}.citizenshipTypeCode" 
                                          attributeEntry="${proposalPersonExtendedAttributes.citizenshipTypeCode}" 
                                                readOnly="${readOnly }" />
									</td>
								</tr>
								
								
								 
							</tbody>
						</table>
					</div>
				</kul:innerTab>
			</td>
		</tr>
	</c:when>
</c:choose>

<tr>
<td colspan=4>
<kul:innerTab tabTitle="Degrees" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="newProposalPersonDegree.*,newProposalPersonDegree[${personIndex}].graduationYear*,newProposalPersonDegree[${personIndex}].degree*,newProposalPersonDegree[${personIndex}].degreeCode*">
 <table class=tab cellpadding=0 cellspacing="0" summary="" >
     <kra-pd:personDegreeSection proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>
  </table>
</kul:innerTab>
</td></tr>

<bean:define id="unitDetailsRequired" name="KualiForm" property="${proposalPerson}.role.unitDetailsRequired" />
<c:set var="unitsErrorKey" value="document.developmentProposalList[0].proposalPersons[${personIndex}].unit*,newProposalPersonUnit[${personIndex}]*" />
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
      <c:when test="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].optInUnitStatus == 'Y'}"> 
   	  <tr><td colspan=4>
      <kul:innerTab tabTitle="Unit Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="${unitsErrorKey}">
        <div class="innerTab-container" align="left">
         <table class=tab cellpadding=0 cellspacing="0" summary=""> 
         <tr>
            <td colspan=3><div class="floaters">
               <p> You have the option to remove unit details for a key person.</p>
               <p><html:image property="methodToCall.removeUnitDetails.${proposalPerson}.line${personIndex}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-remunitdet.gif" title="Add Unit Details" alt="Remove Unit Details" styleClass="tinybutton"/></p>
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
   <kul:innerTab tabTitle="Unit Details" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="document.developmentProposalList[0].proposalPersons[${personIndex}].newProposalPersonUnit*,newProposalPersonUnit[${status.index}]*">
   <div class="innerTab-container" align="left">
   <table class=tab cellpadding=0 cellspacing="0" summary=""> 
    <tr>
       <td colspan=3><div class="floaters">
         <p> You have the option to add unit details for a key person</p>
          <p><html:image property="methodToCall.addUnitDetails.${proposalPerson}.line${status.index}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-addunitdet.gif" title="Add Unit Details" alt="Add Unit Details" styleClass="tinybutton"/></p>
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
 	<c:choose>
 		<c:when test="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].anyYNQsAnswered}">
		   	<tr>
				<td colspan=4>
		  			<kul:innerTab tabTitle="Certify" parentTab="${parentTabName}" defaultOpen="false"  auditCluster="keyPersonnelAuditErrors" tabAuditKey="document.developmentProposalList[0].proposalPersons[${personIndex}]*">
		     			<table class=tab cellpadding=0 cellspacing="0" summary="" >
		     				<kra-pd:personYnqSection proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>
		    			</table>
		 			</kul:innerTab>
		   		</td>
		  	</tr>
		</c:when>
  	</c:choose>
  	<tr>
		<td colspan=4>
			<c:set var="answerHeaderIndex" value="0" />
			<c:set var="property" value="proposalPersonQuestionnaireHelpers[${personIndex}]" />
			<c:set var="bean" value="${KualiForm.proposalPersonQuestionnaireHelpers[personIndex]}" />
			<c:set var ="completed" value="${KualiForm.proposalPersonQuestionnaireHelpers[personIndex].answerHeaders[0].allQuestionsAnswered}"/>
			<c:set var="questionnaireAnswerableUpToApproval" value="${KualiForm.proposalPersonQuestionnaireHelpers[personIndex].canAnswerAfterRouting}"/>
        <%-- hidden rule results --%>
            <input type="hidden" name="ruleReferenced" id ="ruleReferenced" 
       					value = "${bean.ruleReferenced}" />
			
			<kra-questionnaire:questionnaireAnswersInnerTab bean = "${bean}" property = "${property}" 
				answerHeaderIndex = "${answerHeaderIndex}" parentTab="${parentTabName}" completed="${completed}"
				printLineIndex="${personIndex }" answerableUpToApproval="${questionnaireAnswerableUpToApproval}"/>
		</td>
	</tr>
  </c:when>
  <c:otherwise>
     <c:choose>
      <c:when test="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].optInCertificationStatus == 'Y'}"> 
   	  <tr><td colspan=4>
      <kul:innerTab tabTitle="Certify" parentTab="${parentTabName}" defaultOpen="false" auditCluster="keyPersonnelAuditErrors" tabAuditKey="proposalPersonQuestionnaireHelpers[[${personIndex}].answerHeaders[0].answers[0].answer*" >
      <div class="innerTab-container" align="left">
       <table class=tab cellpadding=0 cellspacing="0" summary=""> 
      <tr>
       <td colspan=3><div class="floaters">
         <p> You have the option to remove Certification Questions for a key person.</p>
          <p><html:image property="methodToCall.removeCertificationQuestion.${proposalPerson}.line${status.index}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-remcertquest.gif" title="Remove Certification Question" alt="Remove Certification Question" styleClass="tinybutton"/></p>
          </div>
       </td>
     </tr>
     
   <c:choose>
 		<c:when test="${KualiForm.document.developmentProposalList[0].proposalPersons[personIndex].anyYNQsAnswered}">  
		   <tr>
			<td>
		    	<table class=tab cellpadding=0 cellspacing="0" summary="" >
		        	<kra-pd:personYnqSection proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>
		      	</table>
		   	</td>
		   </tr>
	   </c:when>
   </c:choose>
   
   <tr>
		<td>
			<c:set var="answerHeaderIndex" value="0" />
			<c:set var="property" value="proposalPersonQuestionnaireHelpers[${personIndex}]" />
			<c:set var="bean" value="${KualiForm.proposalPersonQuestionnaireHelpers[personIndex]}" />
			<c:set var ="completed" value="${KualiForm.proposalPersonQuestionnaireHelpers[personIndex].answerHeaders[0].allQuestionsAnswered}"/>
			<c:set var="questionnaireAnswerableUpToApproval" value="${KualiForm.proposalPersonQuestionnaireHelpers[personIndex].canAnswerAfterRouting}"/>			
			
			<kra-questionnaire:questionnaireAnswersInnerTab bean = "${bean}" property = "${property}" 
				answerHeaderIndex = "${answerHeaderIndex}" parentTab="${parentTabName}" 
				completed="${completed}"
				printLineIndex="${personIndex }" answerableUpToApproval="${questionnaireAnswerableUpToApproval}"/>
				
			<%--<kra-questionnaire:questionnaireAnswers bean = "${bean}" property = "${property}" answerHeaderIndex = "${answerHeaderIndex}"/>--%>
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
          <p><html:image property="methodToCall.addCertificationQuestion.${proposalPerson}.line${personIndex}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-inclcertquest.gif" title="Add Certification Question" alt="Add Certification Question" styleClass="tinybutton"/></p>
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

