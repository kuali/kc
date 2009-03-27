<%@ include file="/WEB-INF/jsp/committee/committeeMember.jsp"%>

<c:set var="membershipExpertiseAttributes" value="${DataDictionary.CommitteeMembershipExpertise.attributes}" />
<%-- TODO: set readOnly to something like "${!KualiForm.protocolHelper.modifyProtocol}" --%>
<c:set var="readOnly" value="false" />

<table cellpadding=0 cellspacing=0 summary="">
    <tr>
        <td>
            <kul:innerTab tabTitle="Expertise" 
                          parentTab="${parentTabValue}" 
                          defaultOpen="false" 
                          tabErrorKey="membershipExpertiseHelper.newCommitteeMembershipExpertise[${memberIndex}].*,document.committeeList[0].committeeMemberships[${memberIndex}].membershipExpertise*">
                <div class="innerTab-container" align="left">
                    <table id="membership-expertise-table" cellpadding=0 cellspacing=0 class="datatable" summary="View/edit committee membership expertise">
                    
                        <%-- Header --%>
                        <tr>
                            <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" align="center" />
                            <kul:htmlAttributeHeaderCell attributeEntry="${membershipExpertiseAttributes.researchAreaCode}" scope="col" align="center" />
                            <c:if test="${!readOnly}">
                                <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center" />
                            </c:if>
                        </tr>
                        <%-- Header --%>
                        
                        <%-- New data --%>
                        <c:if test="${!readOnly}">
                            <tr>
                                <th class="infoline">
                                    <c:out value="Add:" />
                                </th>
                                
                                <td align="left" valign="middle" class="infoline">
                                    <div align="left">
                                        <kul:htmlControlAttribute property="membershipExpertiseHelper.newCommitteeMembershipExpertise[${memberIndex}].researchAreaCode" 
                                                                  attributeEntry="${membershipExpertiseAttributes.researchAreaCode}" />
                                        <kul:lookup boClassName="org.kuali.kra.bo.ResearchArea" 
                                                    fieldConversions="researchAreaCode:membershipExpertiseHelper.newCommitteeMembershipExpertise[${memberIndex}].researchAreaCode,description:membershipExpertiseHelper.newCommitteeMembershipExpertise[${memberIndex}].researchArea.description" 
                                                    anchor="${currentTabIndex}"/>
                                        <br> 
                                        ${KualiForm.membershipExpertiseHelper.newCommitteeMembershipExpertise[memberIndex].researchArea.description}
                                    </div>
                                </td>
                                
                               <td align="left" valign="middle" class="infoline">
                                   <div align=center>
                                       <html:image property="methodToCall.addCommitteeMembershipExpertise.${committeeMembership}.line${memberIndex}"
                                                   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
                                                   styleClass="tinybutton"/>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                        <%-- New data --%>

                        <%-- Existing data --%>
                        <c:forEach var="membershipExpertise" items="${KualiForm.document.committeeList[0].committeeMemberships[memberIndex].membershipExpertise}" varStatus="status">
                            <tr>
                                <th class="infoline">
                                    <c:out value="${status.index+1}" />
                                </th>
                                <td align="left" valign="middle">
                                    <div align="left">
                                        <kul:htmlControlAttribute property="${committeeMembership}.membershipExpertise[${status.index}].researchAreaCode" 
                                                                  attributeEntry="${membershipExpertiseAttributes.researchAreaCode}" 
                                                                  readOnlyAlternateDisplay="${membershipExpertise.researchArea.description}" 
                                                                  readOnly="true" />
                                    </div>
                                </td>
 
                                <c:if test="${!readOnly}">
                                    <td align="left" valign="middle">
                                        <div align="center">                    
                                            <html:image property="methodToCall.deleteCommitteeMembershipExpertise.${committeeMembership}.line${status.index}"
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
            </kul:innerTab>
        </td>
    </tr>
</table>
