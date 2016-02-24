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

<%@ attribute name="researchAreaReference" required="true" %>

<c:set var="membershipExpertiseAttributes" value="${DataDictionary.CommitteeMembershipExpertise.attributes}" />

<table border="0" cellpadding="0" cellspacing="0" summary="">
    <tr>
        <td>
            <kul:innerTab tabTitle="Expertise" 
                          parentTab="${parentTabValue}" 
                          defaultOpen="false"
                          useCurrentTabIndexAsKey="true" 
                          tabErrorKey="committeeHelper.newCommitteeMembershipExpertise[${memberIndex}].*,document.committeeList[0].committeeMemberships[${memberIndex}].areasOfExpertise.inactive.*,document.committeeList[0].committeeMemberships[${memberIndex}].membershipExpertise*">
                <div class="innerTab-container" align="left">
                    <table border="0" id="membership-expertise-table-${memberIndex}" cellpadding="0" cellspacing="0" class="datatable" summary="View/edit committee membership expertise">
                    
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
                                        (select)
                                        <kul:multipleValueLookup boClassName="${researchAreaReference}" 
                                                                 lookedUpCollectionName="committeeResearchAreas"
                                                                 anchor="${currentTabIndex}.memberIndex${memberIndex}"/>
                                    </div>
                                </td>
                                
                                <td align="left" valign="middle" class="infoline">
                                    <div align=center>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                        <%-- New data --%>

                        <%-- Existing data --%>
                        <%-- Set the initial value of the error key prefix that is built up in the following loop based on testing for indices --%>
						<c:set var="inactiveAreasErrorKeyPrefix" value="document.committeeList[0].committeeMemberships[${memberIndex}].areasOfExpertise.inactive." scope="request"/>
                        <c:forEach var="membershipExpertise" items="${KualiForm.document.committeeList[0].committeeMemberships[memberIndex].membershipExpertise}" varStatus="status">
                            <tr>
                                <th class="infoline">
                                    <c:out value="${status.index+1}" />
                                </th>
                                <td align="left" valign="middle">
                                    <div align="left">
                                        ${membershipExpertise.researchAreaCode} ${membershipExpertise.researchArea.description}
                                         <!--- error handling --> <%-- Check if the research area indexed by the current iteration is an error key, and if so show the error icon --%>
										<kul:checkErrors keyMatch="${inactiveAreasErrorKeyPrefix}${status.index}.*" />
										<c:if test="${hasErrors}">
										<%-- display the error icon --%>
										<kul:fieldShowErrorIcon />
										<%-- build up the error key prefix by appending the current index --%>
										<c:set var="inactiveAreasErrorKeyPrefix" value="${inactiveAreasErrorKeyPrefix}${status.index}." scope="request" />
									</c:if> 
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
