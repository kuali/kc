 <%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/proposaldevelopment/proposalPerson.jsp"%>
<bean:define id="parentTabName" name="KualiForm" property="${proposalPerson}.fullName"/>
<div class="tab-container" align="center">
<bean:define id="personEditableFields" name="KualiForm" property="personEditableFields" />
          <!-- TAB -->
            <div id="workarea">
            <div class="tab-container" align="center" id="G100">
              <div class="h2-container">

                <h2><span class="subhead-left"><bean:write name="KualiForm" property="${proposalPerson}.fullName"/></span></h2>
              </div>
<table cellpadding=0 cellspacing=0 summary="">
          	<tr>
				<td>
              
<kul:innerTab tabTitle="Person Details" parentTab="${parentTabName}" defaultOpen="true">
			<div class="innerTab-container" align="left">
              <table class=tab cellpadding=0 cellspacing="0" summary=""> 
                <tbody id="G1">
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.proposalPersonRoleId}" /></div></th>
                    <td colspan="3"><kul:htmlControlAttribute property="${proposalPerson}.proposalPersonRoleId" attributeEntry="${proposalPersonAttributes.proposalPersonRoleId}" /></td>
                  </tr>              
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.fullName}"  /></div></th>

                    <td>
                      <kul:htmlControlAttribute property="${proposalPerson}.fullName" attributeEntry="${proposalPersonAttributes.fullName}" /> 
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
                    <th align="left" width="15%"> <div align="right">Office Phone: </div></th>
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
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right">Mobile: </div></th>
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
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.addressLine2}"  /></div></th>
                    <td><kul:htmlControlAttribute property="${proposalPerson}.addressLine2" 
                                            attributeEntry="${proposalPersonAttributes.addressLine2}" 
                                                  readOnly="${!personEditableFields['addressLine2'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.addressLine3}"  /></div></th>

                    <td><kul:htmlControlAttribute property="${proposalPerson}.addressLine3" 
                                            attributeEntry="${proposalPersonAttributes.addressLine3}" 
                                                  readOnly="${!personEditableFields['fullName'] || addressLine3}" />
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.city}"  /></div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.city" 
                                                                     attributeEntry="${proposalPersonAttributes.city}" 
                                                                           readOnly="${!personEditableFields['city'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalPersonAttributes.county}"  /></div></th>

                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.county" 
                                                                     attributeEntry="${proposalPersonAttributes.county}" 
                                                                           readOnly="${!personEditableFields['county'] }" />
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
                                          attributeEntry="${proposalPersonAttributes.percentEffort}" 
                                                readOnly="${!personEditableFields['percentEffort'] }" />
                    </span></td>
                    <th align="left" nowrap="nowrap"><div align="right">isFaculty</div></th>

                    <td align="left"><label>
                      <kul:htmlControlAttribute property="${proposalPerson}.isFaculty" 
                                          attributeEntry="${proposalPersonAttributes.isFaculty}" 
                                                readOnly="${!personEditableFields['isFaculty']}" />
                    </label></td>
                  </tr>
                </tbody>
</table></div>
</kul:innerTab>
</td></tr>
<bean:define id="isInvestigator" name="KualiForm" property="${proposalPerson}.isInvestigator" />
<c:if test="${isInvestigator == 'Yes'}">
          	<tr>
				<td colspan=4>

  <kul:innerTab tabTitle="Unit Details" parentTab="${parentTabName}" defaultOpen="true">
              <table class=tab cellpadding=0 cellspacing="0" summary="" >
              <kra-pd:personUnitSection proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>
  </table>
  </kul:innerTab>
  </td></tr>
</c:if>
          	<tr>
				<td colspan=4>

<kul:innerTab tabTitle="Degrees" parentTab="${parentTabName}" defaultOpen="true">
              <table class=tab cellpadding=0 cellspacing="0" summary="" >
              <kra-pd:personDegreeSection proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>
  </table>
</kul:innerTab>
</td></tr>
          	<tr>
				<td colspan=4>

<bean:define id="certificationRequired" name="KualiForm" property="${proposalPerson}.role.certificationRequired" />
<c:if test="${certificationRequired == 'Yes'}">
<kul:innerTab tabTitle="Certify" parentTab="${parentTabName}" defaultOpen="true">
              <table class=tab cellpadding=0 cellspacing="0" summary="" >
              <kra-pd:personYnqSection proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>
  </table>
</kul:innerTab>
</c:if>

</td></tr>
</table>
           </div>
          </div>
</div>

