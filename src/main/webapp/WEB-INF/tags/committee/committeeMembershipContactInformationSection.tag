<%@ include file="/WEB-INF/jsp/committee/committeeMember.jsp"%>

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
