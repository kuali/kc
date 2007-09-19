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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="index" description="The name of the key field to discern this person from other persons." required="false" %>
<%@ attribute name="excludeSections" description="A comma-separated list of section(s) that will not be shown or editable" required="false" %>

<c:set var="proposalPersonAttributes" value="${DataDictionary.ProposalPerson.attributes}" />
<c:set var="personUnitAttributes" value="${DataDictionary.ProposalPersonUnit.attributes}" />
<c:set var="attributePrefix" value="newProposalPerson" />

<c:if test="${!empty index}">
    <c:set var="attributePrefix" value="document.proposalPersons[${index}]" />
</c:if>

<div class="tab-container" align="center">
          <!-- TAB -->
            <div id="workarea">
            <div class="tab-container" align="center" id="G100">
<!-- style="display: none;" -->
              <div class="h2-container">

                <h2><span class="subhead-left"><bean:write name="KualiForm" property="${attributePrefix}.fullName"/></span></h2>
              </div>
              <table cellpadding=0 cellspacing="0" summary="">
                <tr>
                  <td colspan="13" nowrap class="tab-subhead1"><a href="#" id="A1" onclick="rend(this, false)"><img src="kr/static/images/tinybutton-hide.gif" alt="show/hide this panel" width=45 height=15  border=0 align="absmiddle" id="F1"></a> Person Details</td>
                </tr>
                <tbody id="G1">
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right">Full Name: </div></th>

                    <td>
                      <kul:htmlControlAttribute property="${attributePrefix}.fullName" attributeEntry="${proposalPersonAttributes.fullName}" /> 
                    </td>
                    <th align="left" width="15%"><div align="right">User Name: </div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${attributePrefix}.userName" 
                                                                     attributeEntry="${proposalPersonAttributes.userName}" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" width="15%"><div align="right">Email Address:</div></th>

                    <td align="left" width="30%"><kul:htmlControlAttribute property="${attributePrefix}.emailAddress" 
                                                                     attributeEntry="${proposalPersonAttributes.emailAddress}" />
                    </td>
                    <th align="left" width="15%"> <div align="right">Office Phone: </div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${attributePrefix}.officePhone" 
                                                                     attributeEntry="${proposalPersonAttributes.officePhone}" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" width="15%"> <div align="right">Primary Title: </div></th>

                    <td align="left"><kul:htmlControlAttribute property="${attributePrefix}.primaryTitle" 
                                                                     attributeEntry="${proposalPersonAttributes.primaryTitle}" />
                    </td>
                    <th align="left" nowrap="nowrap"> <div align="right">Directory Title: </div></th>
                    <td align="left"><kul:htmlControlAttribute property="${attributePrefix}.directoryTitle"
                                                                     attributeEntry="${proposalPersonAttributes.directoryTitle}" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" width="15%"> <div align="right">Home Unit: </div></th>

                    <td align="left"><kul:htmlControlAttribute property="${attributePrefix}.homeUnit" 
                                                                     attributeEntry="${proposalPersonAttributes.homeUnit}" />
                    </td>
                    <th align="left" nowrap="nowrap"> <div align="right">School </div></th>
                    <td><kul:htmlControlAttribute property="${attributePrefix}.school" 
                                                                     attributeEntry="${proposalPersonAttributes.school}" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" width="15%"> <div align="right">eRA Commons User Name: </div></th>

                    <td align="left" width="30%"><kul:htmlControlAttribute property="${attributePrefix}.eraCommonsUserName" 
                                                                     attributeEntry="${proposalPersonAttributes.eraCommonsUserName}" />
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right">Fax: </div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${attributePrefix}.faxNumber" 
                                                                     attributeEntry="${proposalPersonAttributes.faxNumber}" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right">Pager: </div></th>

                    <td align="left" width="30%"><kul:htmlControlAttribute property="${attributePrefix}.pagerNumber" 
                                                                     attributeEntry="${proposalPersonAttributes.pagerNumber}" />
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right">Mobile: </div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${attributePrefix}.mobilePhoneNumber" 
                                                                     attributeEntry="${proposalPersonAttributes.mobilePhoneNumber}" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left"> <div align="right">Office Location: </div></th>

                    <td align="left"><kul:htmlControlAttribute property="${attributePrefix}.officeLocation" 
                                                                     attributeEntry="${proposalPersonAttributes.officeLocation}" />
                    </td>
                    <th align="left"> <div align="right">Sec.Office Location: </div></th>
                    <td align="left"><kul:htmlControlAttribute property="${attributePrefix}.secondaryOfficeLocation" 
                                                                     attributeEntry="${proposalPersonAttributes.secondaryOfficeLocation}" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right">Address Line 1: </div></th>

                    <td><kul:htmlControlAttribute property="${attributePrefix}.addressLine1" 
                                                                     attributeEntry="${proposalPersonAttributes.addressLine1}" />
                    </td>
                    <th align="left" nowrap="nowrap"> <div align="right">Address Line 2: </div></th>
                    <td><kul:htmlControlAttribute property="${attributePrefix}.addressLine2" 
                                                                     attributeEntry="${proposalPersonAttributes.addressLine2}" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right">Address Line 3: </div></th>

                    <td><kul:htmlControlAttribute property="${attributePrefix}.addressLine3" 
                                                                     attributeEntry="${proposalPersonAttributes.addressLine3}" />
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right">City: </div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${attributePrefix}.city" 
                                                                     attributeEntry="${proposalPersonAttributes.city}" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right">County: </div></th>

                    <td align="left" width="30%"><kul:htmlControlAttribute property="${attributePrefix}.county" 
                                                                     attributeEntry="${proposalPersonAttributes.county}" />
                    </td>
                    <th align="left" nowrap="nowrap" width="15%"> <div align="right">State: </div></th>
                    <td align="left" width="30%"><kul:htmlControlAttribute property="${attributePrefix}.state" 
                                                                     attributeEntry="${proposalPersonAttributes.state}" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"> <div align="right">Postal Code: </div></th>
                    <td align="left"><kul:htmlControlAttribute property="${attributePrefix}.postalCode" 
                                                                     attributeEntry="${proposalPersonAttributes.postalCode}" />
                    </td>
                    <th align="left" nowrap="nowrap"> <div align="right">Country: </div></th>
                    <td align="left"><kul:htmlControlAttribute property="${attributePrefix}.countryCode" 
                                                                     attributeEntry="${proposalPersonAttributes.countryCode}" />
                    </td>
                  </tr>
                  <tr>
                    <th align="left" nowrap="nowrap"><div align="right">% Effort: </div></th>
                    <td align="left"><span>
                      <kul:htmlControlAttribute property="${attributePrefix}.percentageEffort" 
                                                                     attributeEntry="${proposalPersonAttributes.percentEffort}" />
                    </span></td>
                    <th align="left" nowrap="nowrap"><div align="right">Faculty:</div></th>

                    <td align="left"><label>
                      <kul:htmlControlAttribute property="${attributePrefix}.isFaculty" 
                                                                     attributeEntry="${proposalPersonAttributes.isFaculty}" />
                    </label></td>
                  </tr>
                </tbody>
              </table>

              <c:if test="${!fn:contains(excludeSections, 'unit')}">
                <kra-pd:personUnitSection proposalPerson="${attributePrefix}" />
              </c:if>

              <c:if test="${!fn:contains(excludeSections, 'degrees')}">
                  <kra-pd:personDegreeSection proposalPerson="${attributePrefix}"/>
              </c:if>

              <c:if test="${!fn:contains(excludeSections, 'certify')}">
              <table cellpadding=0 cellspacing="0" summary="">
                <tr>
                  <td colspan="12" nowrap class="tab-subhead1"><a href="#" id="A5" onclick="rend(this, false)"><img src="kr/static/images/tinybutton-hide.gif" alt="show/hide this panel" width=45 height=15  border=0 align="absmiddle" id="F5"></a> Certify</td>
                </tr>
                <tbody id="G5">
                  <tr>
                    <th width="10%">Code</th>

                    <th> Question</th>
                    <th>Answer</th>
                  </tr>
                  <tr>
                    <th scope="row"><span class="copybold">H4</span></th>
                    <td><span class="copybold">Lobbying activities have been conducted regarding the proposal </span></td>

                    <td nowrap><label>
                      <input name="RadioGroup1" type="radio" class="nobord" id="RadioGroup1_0" value="radio">
                      yes</label>
                        <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="RadioGroup1" type="radio" class="nobord" id="RadioGroup1_1" value="radio">
                          no&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    </td>

                  </tr>
                  <tr>
                    <th scope="row"><span class="copybold">P1</span></th>
                    <td><span class="copybold">Can you certify that the information submitted within this application
                      is true, complete and accurate to the best of your knowledge? That any
                      false, fictitious, or fraudulent statements or claims may subject you,
                      as the PI/Co-PI/Co-I to criminal, civil or administrative penalties?
                      That you agree to accept responsibility for the scientific conduct of
                      the project and to provide the required </span></td>
                    <td nowrap><label>
                      <input name="RadioGroup1" type="radio" class="nobord" id="RadioGroup1_0" value="radio">
                      yes</label>

                        <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="RadioGroup1" type="radio" class="nobord" id="RadioGroup1_1" value="radio">
                          no&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    </td>
                  </tr>
                  <tr>
                    <th  scope="row"><span class="copybold">P2</span></th>

                    <td><span class="copybold">Is there any potential for a perceived or real conflict of interest as
                      defined in MIT's Policies and Procedures with regard to this proposal? </span></td>
                    <td nowrap><label>
                      <input name="RadioGroup1" type="radio" class="nobord" id="RadioGroup1_0" value="radio">
                      yes</label>
                        <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="RadioGroup1" type="radio" class="nobord" id="RadioGroup1_1" value="radio">
                          no&nbsp;&nbsp;&nbsp;&nbsp;</label>

                    </td>
                  </tr>
                  <tr>
                    <th  scope="row"><span class="copybold">P3</span></th>
                    <td><span class="copybold">If this is a NIH/NSF proposal have you submitted the required financial
                      disclosures in the web based Coeus Conflict of Interest module? </span></td>
                    <td nowrap><label>
                      <input name="RadioGroup1" type="radio" class="nobord" id="RadioGroup1_0" value="radio">
                      yes</label>

                        <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="RadioGroup1" type="radio" class="nobord" id="RadioGroup1_1" value="radio">
                          no&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    </td>
                  </tr>
                  <tr>
                    <th  scope="row"><span class="copybold">P5</span></th>

                    <td><span class="copybold">Are you currently debarred, suspended, proposed for debarment, declared
                      ineligible or voluntarily excluded from current transactions by a
                      federal department or agency?</span></td>
                    <td nowrap><label>
                      <input name="RadioGroup1" type="radio" class="nobord" id="RadioGroup1_0" value="radio">
                      yes</label>
                        <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="RadioGroup1" type="radio" class="nobord" id="RadioGroup1_1" value="radio">
                          no&nbsp;&nbsp;&nbsp;&nbsp;</label>

                    </td>
                  </tr>
                  <tr>
                    <th  scope="row"><span class="copybold">P6</span></th>
                    <td><span class="copybold">Are you familiar with the requirements of the Procurement Integrity Act
                      [(OFPP, Section 27 (1-3)] (http://web.mit.edu/osp/www/Procuint.htm) and
                      will you report any violations to the Office of Sponsored Programs? </span></td>
                    <td nowrap><label>
                      <input name="RadioGroup1" type="radio" class="nobord" id="RadioGroup1_0" value="radio">
                      yes</label>

                        <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="RadioGroup1" type="radio" class="nobord" id="RadioGroup1_1" value="radio">
                          no&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    </td>
                  </tr>
                </tbody>
              </table>
              </c:if>
            </div>
          </div>
</div>
