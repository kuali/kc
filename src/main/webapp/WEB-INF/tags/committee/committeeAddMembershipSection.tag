<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="readOnly" description="All fields are displayed as read-only elements." required="true" %>

<c:set var="committeeMembershipAttributes" value="${DataDictionary.CommitteeMembership.attributes}" />

<c:if test="${!readOnly}">
    <kra:permission value="${KualiForm.membershipHelper.modifyCommittee}">
        <kra:uncollapsable tabTitle="Add ${KualiForm.document.committee.committeeType.description} Member"
            tabErrorKey="membershipHelper.newCommitteeMembership.*" 
            auditCluster="committeeMembershipAuditErrors" 
            tabAuditKey="newCommitteeMembership*">
          <div align="center">
            <table  cellpadding="0" cellspacing="0" class="grid" summary="">
              <tr>
                <th class="grid"><div align="right">*Person:</div></th>
                <td nowrap class="grid">
                    <c:choose>                  
                        <c:when test="${empty KualiForm.membershipHelper.newCommitteeMembership.personId && empty KualiForm.membershipHelper.newCommitteeMembership.rolodexId}">
                            <label>Employee Search</label>
                            <label>
                                <kul:lookup boClassName="org.kuali.kra.bo.Person" 
                                    fieldConversions="personId:membershipHelper.newCommitteeMembership.personId,fullName:membershipHelper.newCommitteeMembership.personName" />
                            </label>
                            <br>
                            <label>Non-employee Search</label> 
                            <label>
                                <kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" 
                                    fieldConversions="rolodexId:membershipHelper.newCommitteeMembership.rolodexId,fullName:membershipHelper.newCommitteeMembership.personName" />
                            </label>
                        </c:when>
                        <c:otherwise>
                            <label>
                                <kul:htmlControlAttribute property="membershipHelper.newCommitteeMembership.personName" attributeEntry="${committeeMembershipAttributes.personName}" readOnly="true"/> 
                            </label>
                            <br/>
                        </c:otherwise>
                    </c:choose>
                </td>
              </tr>
            </table>
            <br>
            <html:image property="methodToCall.clearCommitteeMembership" 
                 src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" 
                 title="Clear Fields" alt="Clear Fields" 
                 styleClass="tinybutton"/>
            <html:image property="methodToCall.addCommitteeMembership" 
                 src="${ConfigProperties.kr.externalizable.images.url}tinybutton-addmember.gif" 
                 title="Add Committee Member" 
                 alt="Add Committee Member" 
                 styleClass="tinybutton"/>
          </div>
        </kra:uncollapsable>
    </kra:permission>
    <br/>
</c:if>
