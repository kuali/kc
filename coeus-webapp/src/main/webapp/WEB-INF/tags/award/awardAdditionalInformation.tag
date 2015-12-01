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
<%-- member of awardPaymentAndInvoices.tag --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp" %>
<c:set var="awardExtensionAttributes" value="${DataDictionary.AwardExtension.attributes}"/>
<div align="center">
    <c:set var="awardAttributes" value="${DataDictionary.Award.attributes}"/>
    <kul:innerTab parentTab="Payment & Invoices" defaultOpen="true"
                  tabTitle="Additional Financial Information"
                  tabErrorKey="document.awardList[0]*" noShowHideButton="true">
        <table cellpadding="0" cellspacing="0" summary="">
            <tr>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel
                                attributeEntry="${awardExtensionAttributes.avcIndicator}"/>
                    </div>
                </th>
                <td>
                    <kul:htmlControlAttribute
                            property="document.awardList[0].extension.avcIndicator"
                            attributeEntry="${awardExtensionAttributes.avcIndicator}"/>
                </td>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel
                                attributeEntry="${awardExtensionAttributes.interestEarned}"/>
                    </div>
                </th>
                <td>
                    <kul:htmlControlAttribute
                            property="document.awardList[0].extension.interestEarned"
                            attributeEntry="${awardExtensionAttributes.interestEarned}"/>
                </td>
            </tr>
            <tr>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel
                                attributeEntry="${awardExtensionAttributes.a133Cluster}"/>
                    </div>
                </th>
                <td>
                    <kul:htmlControlAttribute
                            property="document.awardList[0].extension.a133Cluster"
                            attributeEntry="${awardExtensionAttributes.a133Cluster}"/>
                </td>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel
                                attributeEntry="${awardExtensionAttributes.interestEarnedAccountNumber}"/>
                    </div>
                </th>
                <td>
                    <kul:htmlControlAttribute
                            property="document.awardList[0].extension.interestEarnedAccountNumber"
                            attributeEntry="${awardExtensionAttributes.interestEarnedAccountNumber}"/>
                </td>
            </tr>
            <tr>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel
                                attributeEntry="${awardExtensionAttributes.fringeNotAllowedIndicator}"/>
                    </div>
                </th>
                <td>
                    <kul:htmlControlAttribute
                            property="document.awardList[0].extension.fringeNotAllowedIndicator"
                            attributeEntry="${awardExtensionAttributes.fringeNotAllowedIndicator}"/>
                </td>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel
                                attributeEntry="${awardExtensionAttributes.federalRateDate}"/>
                    </div>
                </th>
                <td>
                    <kul:htmlControlAttribute property="document.awardList[0].extension.federalRateDate"
                                              attributeEntry="${awardExtensionAttributes.federalRateDate}"/>
                </td>
            </tr>
            <tr>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel
                                attributeEntry="${awardExtensionAttributes.programIncome}"/>
                    </div>
                </th>
                <td>
                    <kul:htmlControlAttribute property="document.awardList[0].extension.programIncome"
                                              attributeEntry="${awardExtensionAttributes.programIncome}"/>
                </td>

                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel
                                attributeEntry="${awardExtensionAttributes.buBmcFaSplit}"/>
                    </div>
                </th>
                <td>
                    <kul:htmlControlAttribute property="document.awardList[0].extension.buBmcFaSplit"
                                              attributeEntry="${awardExtensionAttributes.buBmcFaSplit}"/>
                </td>
            </tr>
            <tr>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel
                                attributeEntry="${awardExtensionAttributes.stockAward}"/>
                    </div>
                </th>
                <td>
                    <kul:htmlControlAttribute property="document.awardList[0].extension.stockAward"
                                              attributeEntry="${awardExtensionAttributes.stockAward}"/>
                </td>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel
                                attributeEntry="${awardExtensionAttributes.foreignCurrencyAward}"/>
                    </div>
                </th>
                <td>
                    <kul:htmlControlAttribute property="document.awardList[0].extension.foreignCurrencyAward"
                                              attributeEntry="${awardExtensionAttributes.foreignCurrencyAward}"/>
                </td>
            </tr>
            <tr>
                <th>
                    <div align="right">
                        <kul:htmlAttributeLabel attributeEntry="${awardExtensionAttributes.NCENotificationDate}"/>
                    </div>
                </th>
                <td>
                    <kul:htmlControlAttribute property="document.awardList[0].extension.NCENotificationDate"
                                              attributeEntry="${awardExtensionAttributes.NCENotificationDate}"/>
                </td>
                <th>
                    <div align="right">
                    </div>
                </th>
                <td valign="middle">
                    <div align="left">
                    </div>
                </td>
            </tr>

        </table>
    </kul:innerTab>
</div>
