<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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

<%@ attribute name="committeeMembership" description="The current committee membership which is being processed" required="true" %>
<%@ attribute name="memberIndex" description="The index of the current committee membership which is being processed" required="true" %>
<%@ attribute name="parentTabValue" description="The tabTitle of the parent tab" required="true" %>
<%@ attribute name="readOnly" description="All fields are displayed as read-only elements." required="true" %>

<c:set var="committeeMembershipAttributes" value="${DataDictionary.CommitteeMembership.attributes}" />
<c:set var="personAttributes" value="${DataDictionary.KcPerson.attributes}" />

<c:choose>
    <c:when test="${empty KualiForm.document.committee.committeeMemberships[memberIndex].personName}">
        <c:set var="parentTabName" value="" />
    </c:when>
    <c:otherwise>
        <bean:define id="parentTabName" name="KualiForm" property="${committeeMembership}.personName"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${!empty KualiForm.document.committee.committeeMemberships[memberIndex].personId}">
        <c:set var="person" value="${committeeMembership}.person" />
    </c:when>
    <c:otherwise>
        <c:set var="person" value="${committeeMembership}.rolodex" />
    </c:otherwise>
</c:choose>

<table border="0" cellpadding="0" cellspacing="0" summary="">
    <tr>
        <td>
            <kul:innerTab tabTitle="Contact Information" 
                          parentTab="${parentTabName}" 
                          defaultOpen="false"
                          useCurrentTabIndexAsKey="true" 
                          tabErrorKey="">
                <div class="innerTab-container" align="left">
                    <table border="0" class=tab cellpadding="0" cellspacing="0" summary="">
                        <tr>
                            <th> 
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes.emailAddress}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.emailAddress" 
                                                          attributeEntry="${personAttributes.emailAddress}" 
                                                          readOnly="true" /> 
                            </td>
                            <th>
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes.officePhone}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.officePhone" 
                                                          attributeEntry="${personAttributes.officePhone}" 
                                                          readOnly="true" />
                            </td>
                        </tr>
                        <tr>
                            <th> 
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.primaryTitle']}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.primaryTitle" 
                                                          attributeEntry="${personAttributes['extendedAttributes.primaryTitle']}" 
                                                          readOnly="true" /> 
                            </td>
                            <th>
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.directoryTitle']}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.directoryTitle" 
                                                          attributeEntry="${personAttributes['extendedAttributes.directoryTitle']}" 
                                                          readOnly="true" />
                            </td>
                        </tr>
                        <tr>
                            <th> 
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes.organizationIdentifier}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.organizationIdentifier" 
                                                          attributeEntry="${personAttributes.organizationIdentifier}" 
                                                          readOnly="true" /> 
                            </td>
                            <th align="left">
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.school']}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.school" 
                                                          attributeEntry="${personAttributes['extendedAttributes.school']}" 
                                                          readOnly="true" />
                            </td>
                        </tr>
                        <tr>
                            <th> 
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes.pagerNumber}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.pagerNumber" 
                                                          attributeEntry="${personAttributes.pagerNumber}" 
                                                          readOnly="true" /> 
                            </td>
                            <th>
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes.faxNumber}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.faxNumber" 
                                                          attributeEntry="${personAttributes.faxNumber}" 
                                                          readOnly="true" />
                            </td>
                        </tr>
                        <tr>
                            <th> 
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.officeLocation']}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.officeLocation" 
                                                          attributeEntry="${personAttributes['extendedAttributes.officeLocation']}" 
                                                          readOnly="true" /> 
                            </td>
                            <th>
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes.mobilePhoneNumber}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.mobilePhoneNumber" 
                                                          attributeEntry="${personAttributes.mobilePhoneNumber}" 
                                                          readOnly="true" />
                            </td>
                        </tr>
                        <tr>
                            <th> 
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes.addressLine1}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.addressLine1" 
                                                          attributeEntry="${personAttributes.addressLine1}" 
                                                          readOnly="true" /> 
                            </td>
                            <th>
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.secondaryOfficeLocation']}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.secondaryOfficeLocation" 
                                attributeEntry="${personAttributes['extendedAttributes.secondaryOfficeLocation']}" readOnly="true" />
                            </td>
                        </tr>
                        <tr>
                            <th> 
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes.addressLine2}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.addressLine2" 
                                                          attributeEntry="${personAttributes.addressLine2}" 
                                                          readOnly="true" /> 
                            </td>
                            <th>
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes['extendedAttributes.county']}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.county" 
                                                          attributeEntry="${personAttributes['extendedAttributes.county']}" 
                                                          readOnly="true" />
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes.city}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.city" 
                                                          attributeEntry="${personAttributes.city}" 
                                                          readOnly="true" />
                            </td>
                            <th>
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes.state}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.state" 
                                                          attributeEntry="${personAttributes.state}" 
                                                          readOnly="true" />
                            </td>
                        </tr>
                        <tr>
                            <th> 
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes.postalCode}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.postalCode" 
                                                          attributeEntry="${personAttributes.postalCode}" 
                                                          readOnly="true" /> 
                            </td>
                            <th>
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${personAttributes.countryCode}" />
                                </div>
                            </th>
                            <td align="left">
                                <kul:htmlControlAttribute property="${person}.countryCode" 
                                                          attributeEntry="${personAttributes.countryCode}" 
                                                          readOnly="true" />
                            </td>
                        </tr>
                    </table>
                </div>
            </kul:innerTab>
        </td>
    </tr>
</table>
