<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp" %>
<%--
  ~ Copyright (c) 2014. Boston University
  ~
  ~ Licensed under the Educational Community License, Version 2.0 (the "License"); you may not use this
  ~ file except in compliance with the License. You may obtain a copy of the License at
  ~
  ~ http://www.opensource.org/licenses/ecl1.php
  ~
  ~ Unless required by applicable law or agreed to in writing, software distributed under the License is
  ~ distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND either express or
  ~ implied.
  ~
  ~ See the License for the specific language governing permissions and limitations under the License.
  --%>

<c:set var="awardExtensionAttributes" value="${DataDictionary.AwardExtension.attributes}"/>

<kul:tab defaultOpen="false" spanForLongTabTitle="true"
         tabTitle="Clinical Trial Information"
         tabErrorKey="document.awardList[0]*">
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Clinical Trial Information</span>
        </h3>

        <table cellpadding=0 cellspacing=0 summary="">
            <tr>
                <th width="55%">
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${awardExtensionAttributes.clinicalTrialInitiatedBy}"/>
                    </div>
                </th>
                <td width="45%">
                    <kul:htmlControlAttribute property="document.awardList[0].extension.clinicalTrialInitiatedBy"
                                              attributeEntry="${awardExtensionAttributes.clinicalTrialInitiatedBy}"/>
                </td>
            </tr>
            <tr>
                <th width="55%">
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${awardExtensionAttributes.INDIDEResponsibility}"/>
                    </div>
                </th>
                <td width="45%">
                    <kul:htmlControlAttribute property="document.awardList[0].extension.INDIDEResponsibility"
                                              attributeEntry="${awardExtensionAttributes.INDIDEResponsibility}"/>
                </td>
            </tr>
            <tr>
                <th width="55%">
                    <div align="right">
                        <kul:htmlAttributeLabel
                                attributeEntry="${awardExtensionAttributes.clinicalTrialRegistrationDate}"/>
                    </div>
                </th>
                <td width="45%">
                    <kul:htmlControlAttribute property="document.awardList[0].extension.clinicalTrialRegistrationDate"
                                              attributeEntry="${awardExtensionAttributes.clinicalTrialRegistrationDate}"/>
                </td>
            </tr>
        </table>
    </div>
</kul:tab>

<%-- <c:if test="${KualiForm.document.awardList[0].extension.awardTransactionTypeCode == 12}">  --%>

<kul:tab defaultOpen="false" spanForLongTabTitle="true"
         tabTitle="Legacy Identifiers"
         tabErrorKey="document.awardList[0]*">
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Legacy Identifiers</span>
        </h3>

        <table cellpadding=0 cellspacing=0 summary="">
            <tr>
                <th width="55%">
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${awardExtensionAttributes.spudsRecordNumber}"/>
                    </div>
                </th>
                <td width="45%">
                    <kul:htmlControlAttribute property="document.awardList[0].extension.spudsRecordNumber"
                                              attributeEntry="${awardExtensionAttributes.spudsRecordNumber}"
                                              readOnly="true"/>
                </td>
            </tr>
            <tr>
                <th width="55%">
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${awardExtensionAttributes.walkerSourceNumber}"/>
                    </div>
                </th>
                <td width="45%">
                    <kul:htmlControlAttribute property="document.awardList[0].extension.walkerSourceNumber"
                                              attributeEntry="${awardExtensionAttributes.walkerSourceNumber}"/>
                </td>
            </tr>
        </table>
    </div>
</kul:tab>
<%-- </c:if> --%>
