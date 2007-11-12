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
<%@ include file="/WEB-INF/jsp/proposalPerson.jsp"%>
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
                    <th align="left" nowrap="nowrap"> <div align="right">Full Name: </div></th>

                    <td>
                      <kul:htmlControlAttribute property="${proposalPerson}.fullName" attributeEntry="${proposalPersonAttributes.fullName}" /> 
                    </td>
                    <th align="left" width="15%"><div align="right">User Name: </div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.userName" 
                                                                     attributeEntry="${proposalPersonAttributes.userName}" 
                                                                           readOnly="${!personEditableFields['userName'] }"/>
                    </td>
                  </tr>
                  <tr>
                    <th align="left" width="15%"><div align="right">Email Address:</div></th>

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
                    <th align="left" width="15%"> <div align="right">Primary Title: </div></th>

                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.primaryTitle" 
                                                         attributeEntry="${proposalPersonAttributes.primaryTitle}" 
                                                               readOnly="${!personEditableFields['primaryTitle'] }" />
                    </td>
                    <th align="left" nowrap="nowrap"> <div align="right">Directory Title: </div></th>
                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.directoryTitle"
                                                         attributeEntry="${proposalPersonAttributes.directoryTitle}" 
                                                               readOnly="${!personEditableFields['directoryTitle'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" width="15%"> <div align="right">Home Unit: </div></th>

                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.homeUnit" 
                                                         attributeEntry="${proposalPersonAttributes.homeUnit}" 
                                                               readOnly="${!personEditableFields['fullName'] }" />
                    </td>
                    <th align="left" nowrap="nowrap"> <div align="right">School </div></th>
                    <td><kul:htmlControlAttribute property="${proposalPerson}.school" 
                                            attributeEntry="${proposalPersonAttributes.school}" 
                                                  readOnly="${!personEditableFields['school'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" width="15%"> <div align="right">eRA Commons User Name: </div></th>

                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.eraCommonsUserName" 
                                                                     attributeEntry="${proposalPersonAttributes.eraCommonsUserName}" 
                                                                           readOnly="${!personEditableFields['eraCommonsUserName'] }"/>
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right">Fax: </div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.faxNumber" 
                                                                     attributeEntry="${proposalPersonAttributes.faxNumber}" 
                                                                           readOnly="${!personEditableFields['faxNumber'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right">Pager: </div></th>

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
                    <th align="left"> <div align="right">Office Location: </div></th>

                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.officeLocation" 
                                                         attributeEntry="${proposalPersonAttributes.officeLocation}" 
                                                               readOnly="${!personEditableFields['officeLocation'] }" />
                    </td>
                    <th align="left"> <div align="right">Sec.Office Location: </div></th>
                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.secondaryOfficeLocation" 
                                                         attributeEntry="${proposalPersonAttributes.secondaryOfficeLocation}" 
                                                               readOnly="${!personEditableFields['secondaryOfficeLocation'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right">Address Line 1: </div></th>

                    <td><kul:htmlControlAttribute property="${proposalPerson}.addressLine1" 
                                            attributeEntry="${proposalPersonAttributes.addressLine1}" 
                                                  readOnly="${!personEditableFields['addressLine1'] }" />
                    </td>
                    <th align="left" nowrap="nowrap"> <div align="right">Address Line 2: </div></th>
                    <td><kul:htmlControlAttribute property="${proposalPerson}.addressLine2" 
                                            attributeEntry="${proposalPersonAttributes.addressLine2}" 
                                                  readOnly="${!personEditableFields['addressLine2'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right">Address Line 3: </div></th>

                    <td><kul:htmlControlAttribute property="${proposalPerson}.addressLine3" 
                                            attributeEntry="${proposalPersonAttributes.addressLine3}" 
                                                  readOnly="${!personEditableFields['fullName'] || addressLine3}" />
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right">City: </div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.city" 
                                                                     attributeEntry="${proposalPersonAttributes.city}" 
                                                                           readOnly="${!personEditableFields['city'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right">County: </div></th>

                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.county" 
                                                                     attributeEntry="${proposalPersonAttributes.county}" 
                                                                           readOnly="${!personEditableFields['county'] }" />
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right">State: </div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${proposalPerson}.state" 
                                                                     attributeEntry="${proposalPersonAttributes.state}" 
                                                                           readOnly="${!personEditableFields['state'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right">Postal Code: </div></th>
                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.postalCode" 
                                                                     attributeEntry="${proposalPersonAttributes.postalCode}" 
                                                                           readOnly="${!personEditableFields['postalCode'] }" />
                    </td>
                    <th align="left" nowrap="nowrap"> <div align="right">Country: </div></th>
                    <td align="left"><kul:htmlControlAttribute property="${proposalPerson}.countryCode" 
                                                                     attributeEntry="${proposalPersonAttributes.countryCode}" 
                                                                           readOnly="${!personEditableFields['countryCode'] }" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"><div align="right">% Effort: </div></th>
                    <td align="left"><span>
                      <kul:htmlControlAttribute property="${proposalPerson}.percentageEffort" 
                                          attributeEntry="${proposalPersonAttributes.percentEffort}" 
                                                readOnly="${!personEditableFields['percentEffort'] }" />
                    </span></td>
                    <th align="left" nowrap="nowrap"><div align="right">Faculty:</div></th>

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
</table>
           </div>
          </div>
</div>

