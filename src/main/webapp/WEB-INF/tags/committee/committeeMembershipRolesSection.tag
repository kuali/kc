<%@ include file="/WEB-INF/jsp/committee/committeeMember.jsp"%>

<c:set var="membershipRoleAttributes" value="${DataDictionary.CommitteeMembershipRole.attributes}" />

<table cellpadding=0 cellspacing=0 summary="">
    <tr>
        <td>
            <kra:innerTab tabTitle="Roles" 
                          parentTab="${parentTabValue}" 
                          defaultOpen="false"
                          useCurrentTabIndexAsKey="true" 
                          tabErrorKey="membershipRolesHelper.newCommitteeMembershipRoles[${memberIndex}].*,document.committeeList[0].committeeMemberships[${memberIndex}].membershipRoles*">
                <div class="innerTab-container" align="left">
                    <table id="membership-role-table-${memberIndex}" cellpadding=0 cellspacing=0 class="datatable" summary="View/edit committee membership roles">
                    
                        <%-- Header --%>
                        <tr>
                            <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" align="center" />
                            <kul:htmlAttributeHeaderCell attributeEntry="${membershipRoleAttributes.membershipRoleCode}" scope="col" align="center" />
                            <kul:htmlAttributeHeaderCell attributeEntry="${membershipRoleAttributes.startDate}" scope="col" align="center" />
                            <kul:htmlAttributeHeaderCell attributeEntry="${membershipRoleAttributes.endDate}" scope="col" align="center" />
                            <c:if test="${!readOnly}">
                                <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center" />
                            </c:if>
                        </tr>
                        <%-- Header --%>
                        
                        <%-- New data --%>
                        <c:if test="${!readOnly}">
                            <tr>
                                <th class="infoline" align="center">
                                    <c:out value="Add:" />
                                </th>
                                
                                <td align="left" valign="middle" class="infoline">
                                    <div align="left">
                                        <kul:htmlControlAttribute property="membershipRolesHelper.newCommitteeMembershipRoles[${memberIndex}].membershipRoleCode" 
                                                                  attributeEntry="${membershipRoleAttributes.membershipRoleCode}" />
                                    </div>
                                </td>
                                
                                <td align="left" valign="middle" class="infoline">
                                    <div align="center">
                                        <kul:htmlControlAttribute property="membershipRolesHelper.newCommitteeMembershipRoles[${memberIndex}].startDate" 
                                                                  attributeEntry="${membershipRoleAttributes.startDate}" 
                                                                  datePicker="true" />
                                    </div>
                                </td>
                                
                                <td align="left" valign="middle" class="infoline">
                                    <div align="center">
                                        <kul:htmlControlAttribute property="membershipRolesHelper.newCommitteeMembershipRoles[${memberIndex}].endDate" 
                                                                  attributeEntry="${membershipRoleAttributes.endDate}" 
                                                                  datePicker="true" />
                                    </div>
                                </td>

                               <td align="left" valign="middle" class="infoline">
                                   <div align=center>
                                       <html:image property="methodToCall.addCommitteeMembershipRole.${committeeMembership}.line${memberIndex}"
                                                   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
                                                   styleClass="tinybutton"/>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                        <%-- New data --%>

                        <%-- Existing data --%>
                        <c:forEach var="membershipRole" items="${KualiForm.document.committeeList[0].committeeMemberships[memberIndex].membershipRoles}" varStatus="status">
                            <c:set var="roleStyle" value="" scope="request"/>
                            <c:set var="tmpErrProp" value="document.committeeList[0].committeeMemberships[${memberIndex}].membershipRoles[${status.index}].membershipRoleCode"/>
                            <c:forEach items="${ErrorPropertyList}" var="key">
                                <c:if test="${key eq tmpErrProp}">
                                    <c:set var="roleStyle" value="background-color:#FFD5D5" scope="request"/>
                                </c:if>
                            </c:forEach>
                            
                            <tr>
                                <th class="infoline" align="center">
                                    <c:out value="${status.index+1}" />
                                </th>
                                <td align="left" valign="middle">
                                    <div align="left" style="${roleStyle}">
                                        <kul:htmlControlAttribute property="${committeeMembership}.membershipRoles[${status.index}].membershipRoleCode" 
                                                                  attributeEntry="${membershipRoleAttributes.membershipRoleCode}" 
                                                                  readOnlyAlternateDisplay="${membershipRole.membershipRole.description}" 
                                                                  readOnly="true" />
                                    </div>
                                </td>
                                <td align="left" valign="middle">
                                    <div align="center">
                                        <kul:htmlControlAttribute property="${committeeMembership}.membershipRoles[${status.index}].startDate" 
                                                                  attributeEntry="${membershipRoleAttributes.startDate}" 
                                                                  readOnly="${readOnly}"
                                                                  datePicker="true" />
                                    </div>
                                </td>
                                <td align="left" valign="middle">
                                    <div align="center">
                                        <kul:htmlControlAttribute property="${committeeMembership}.membershipRoles[${status.index}].endDate" 
                                                                  attributeEntry="${membershipRoleAttributes.endDate}" 
                                                                  readOnly="${readOnly}"
                                                                  datePicker="true" />
                                    </div>
                                </td>
 
                                <c:if test="${!readOnly}">
                                    <td align="left" valign="middle">
                                        <div align="center">                    
                                            <html:image property="methodToCall.deleteCommitteeMembershipRole.${committeeMembership}.line${status.index}"
                                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' 
                                                        styleClass="tinybutton"/>
                                        </div>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        <%-- Existing data --%>
                        
                    </table>
                </div>
            </kra:innerTab>
        </td>
    </tr>
</table>
