<%--
 Copyright 2006-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%--
 This tag shows one Proposal Site and its list of congressional districts.
--%>
<%@ attribute name="tabTitle" required="true" description="This string is printed at the top. Also used for identifying the tab." %>
<%@ attribute name="showTabTitle" required="true" type="java.lang.Boolean" description="If true, the tabTitle attribute is displayed." %>
<%@ attribute name="parentTab" required="true" %>
<%@ attribute name="addTable" required="false" type="java.lang.Boolean" description="If true, the HTML code is wrapped in a HTML table. Otherwise, the tag needs to be called from within a rowspan=2 row." %>
<%@ attribute name="proposalSiteBo" required="true" type="org.kuali.kra.proposaldevelopment.bo.ProposalSite" description="The BO of type ProposalSite that is to be displayed" %>
<%@ attribute name="proposalSiteBoName" required="true" description="The JSP name of the ProposalSite BO" %>
<%@ attribute name="proposalSiteHelper" required="true" description="The JSP name of the org.kuali.kra.proposaldevelopment.web.struts.form.ProposalSiteHelper object to store added values in" %>
<%@ attribute name="addDistrictMethodToCall" required="true" description="The method to call when the user clicks the Add button; this method should add a new congressional district" %>
<%@ attribute name="deleteDistrictMethodToCall" required="true" description="The method to call when the user clicks the Delete button on the congressional district" %>
<%@ attribute name="clearSiteMethodToCall" required="false" description="The method to call when the user clicks the Clear button; this method should clear the proposal site. If this parameter is present, a Clear button and a lookup control is shown." %>
<%@ attribute name="deleteSiteMethodToCall" required="false" description="The method to call when the user clicks the Clear button; this method should delete the proposal site. If this parameter is present, a Delete button and a lookup control is shown." %>
<%@ attribute name="rolodexLookup" required="false" type="java.lang.Boolean" description="If set to true, a Rolodex lookup is done; otherwise, an Organization lookup is done" %>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="organizationAttributes" value="${DataDictionary.Organization.attributes}" />
<c:set var="congressionalDistrictAttributes" value="${DataDictionary.CongressionalDistrict.attributes}" /> 

<c:set var="proposalSiteAttributes" value="${DataDictionary.ProposalSite.attributes}" />

<c:set var="canEdit" value="false" />
<kra:section permission="modifyProposal">
    <c:set var="canEdit" value="true" />
</kra:section>


<c:if test="${showTabTitle}">
    <h3>
        <span class="subhead-left">${tabTitle}</span>
    </h3>
</c:if>

<%-- Start a new table if addTable=true --%>
<c:if test="${addTable}">
    <table cellspacing="0" cellpadding="0" align="center" summary="">
        <tr>
            <th rowspan="2">
            </th>
</c:if>

            <%-- Show Location Name edit field if document is editable, print name if not editable --%>
            <th width="40%"><div align="left">
                <c:choose>
                    <c:when test="${canEdit}">
                        <kul:htmlControlAttribute property="${proposalSiteBoName}.locationName" attributeEntry="${proposalSiteAttributes.locationName}" />
                    </c:when>
                    <c:otherwise>
                        <c:out value="${proposalSiteBo.locationName}" />
                    </c:otherwise>
                </c:choose>
            </div></th>
            
            <td align="left" valign="middle" class="infoline" width="60%">
                <%-- Code for Rolodex lookup enabled sites follows --%>
                <c:if test="${rolodexLookup}">
                    <c:out value="${proposalSiteBo.rolodex.organization}" />
                    
                    <%-- Lookup control --%>
                    <kra:section permission="modifyProposal">
                        <c:if test="${!empty clearSiteMethodToCall || !empty deleteSiteMethodToCall}">
                            <kul:lookup
                                boClassName="org.kuali.kra.bo.Rolodex" 
                                fieldConversions="rolodexId:${proposalSiteBoName}.rolodexId,organization:${proposalSiteBoName}.rolodex.organization,postalCode:${proposalSiteBoName}.rolodex.postalCode,addressLine1:${proposalSiteBoName}.rolodex.addressLine1,addressLine2:${proposalSiteBoName}.rolodex.addressLine2,addressLine3:${proposalSiteBoName}.rolodex.addressLine3,city:${proposalSiteBoName}.rolodex.city,state:${proposalSiteBoName}.rolodex.state"
                                anchor="${currentTabIndex}" /> 

                            <input
                                type="hidden" name="${proposalSiteBoName}_rolodexId"
                                value="${proposalSiteBo.rolodexId}" />
                            <kul:directInquiry
                                boClassName="org.kuali.kra.bo.Rolodex"
                                inquiryParameters="${proposalSiteBoName}_rolodexId:rolodexId"
                                anchor="${currentTabIndex}" />
                            <br/>
                        </c:if>
                    </kra:section>
                    
                    <%-- Site address --%>
                    <c:if test="${!empty proposalSiteBo.rolodex.addressLine1}">
                        <c:out value="${proposalSiteBo.rolodex.addressLine1}" />
                        <br/>
                    </c:if>
                    <c:if test="${!empty proposalSiteBo.rolodex.addressLine2}">
                        <c:out value="${proposalSiteBo.rolodex.addressLine2}" />
                        <br/>
                    </c:if>
                    <c:if test="${!empty proposalSiteBo.rolodex.addressLine3}">
                        <c:out value="${proposalSiteBo.rolodex.addressLine3}" />
                        <br/>
                    </c:if>
                    <c:if test="${!empty proposalSiteBo.rolodex.city || !empty proposalSiteBo.rolodex.state || !empty proposalSiteBo.rolodex.postalCode}">
                        <c:out value="${proposalSiteBo.rolodex.city}," />&nbsp
                        <c:out value="${proposalSiteBo.rolodex.state}" />&nbsp
                        <c:out value="${proposalSiteBo.rolodex.postalCode}" />
                    </c:if>
                </c:if>
                
                <%-- Code for non-Rolodex sites follows (uses the organization field, does a organization lookup) --%>
                <c:if test="${!rolodexLookup}">
                    <%-- Site name --%>
                    <c:out value="${proposalSiteBo.organization.organizationName}" />
                    
                    <%-- Lookup control --%>
                    <kra:section permission="modifyProposal">
                        <c:if test="${!empty clearSiteMethodToCall || !empty deleteSiteMethodToCall}">
                            <kul:lookup boClassName="org.kuali.kra.bo.Organization" 
                                fieldConversions="organizationId:${proposalSiteBoName}.organizationId,organizationName:${proposalSiteBoName}.organization.organizationName,address:${proposalSiteBoName}.organization.address"
                                anchor="${currentTabIndex}" />
                            <input
                                type="hidden" name="${proposalSiteBoName}_organizationId"
                                value="${proposalSiteBo.organizationId}" />
                            <kul:directInquiry
                                boClassName="org.kuali.kra.bo.Organization"
                                inquiryParameters="${proposalSiteBoName}_organizationId:organizationId"
                                anchor="${currentTabIndex}" />
                        </c:if>
                    </kra:section>
                    <br/>
                    
                    <%-- Site address --%>
                    <c:out value="${proposalSiteBo.organization.address}"/>
                </c:if>
            </td>
            <td>
                <kra:section permission="modifyProposal">
                    <c:if test="${!empty clearSiteMethodToCall}">
                        <div align=center>
                            <html:image property="methodToCall.${clearSiteMethodToCall}"
                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-clear1.gif' styleClass="tinybutton"/>
                        </div>
                    </c:if>
                    <c:if test="${!empty deleteSiteMethodToCall}">
                        <div align=center>
                            <html:image property="methodToCall.${deleteSiteMethodToCall}"
                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                        </div>
                    </c:if>
                </kra:section>
            </td>
        </tr>
            
        <%-- Show the list of congressional districts if a proposal site is selected --%>
        <tr>
            <td style="padding: 3px;" colspan="3">
                <c:if test="${(!empty proposalSiteBo.organizationId) || (!empty proposalSiteBo.rolodexId)}">
                    <kul:innerTab tabTitle="Congressional Districts" parentTab="${tabTitle}" defaultOpen="false">
                        <table cellspacing="0" cellpadding="0" align="center" summary="">
                            <kra:section permission="modifyProposal">
                                <tr>
                                    <th>Add:</th>
                                    <td class="infoline">
                                        State:<kul:htmlControlAttribute property="${proposalSiteHelper}.newState" attributeEntry="${congressionalDistrictAttributes.stateCode}" />
                                        District Number:<kul:htmlControlAttribute property="${proposalSiteHelper}.newDistrictNumber" attributeEntry="${congressionalDistrictAttributes.districtNumber}" />
                                    </td>
                                    <td class="infoline">
                                        <div align=center>
                                            <html:image property="methodToCall.${addDistrictMethodToCall}"
                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                                        </div>
                                    </td>
                                </tr>
                            </kra:section>
                            <c:forEach var="district" items="${proposalSiteBo.congressionalDistricts}" varStatus="status">
                                <tr>
                                    <th><c:out value="${status.index+1}"/></th>
                                    <td class="infoline">
                                        <c:out value="${district.congressionalDistrict}"/>
                                    </td>
                                    <td class="infoline">
                                        <kra:section permission="modifyProposal">
                                            <div align=center>
                                                <html:image property="methodToCall.${deleteDistrictMethodToCall}.district${status.index}"
                                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                                            </div>
                                        </kra:section>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </kul:innerTab>
                </c:if>
            </td>
        <%-- End of Cong. Districts list --%>
    
<c:if test="${!empty addTable}">
        </tr>
    </table>
</c:if>
