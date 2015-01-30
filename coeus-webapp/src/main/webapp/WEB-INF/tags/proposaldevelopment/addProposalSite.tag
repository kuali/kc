<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<%--
 This tag generates a table row that lets the user add a Proposal Site to a list of Proposal Sites (see the multipleProposalSites tag).
--%>
<%@ attribute name="newProposalSite" required="true" type="org.kuali.coeus.propdev.impl.location.ProposalSite" description="A ProposalSite object for storing a new Proposal Site selected by the user." %>
<%@ attribute name="newProposalSiteField" required="true" description="The JSP name of the newProposalSite object" %>
<%@ attribute name="locationNameEditable" required="false" description="If this is set to false, the location names cannot be edited." %>
<%@ attribute name="addMethodToCall" required="true" description="The action method to call when the add button is clicked" %>
<%@ attribute name="locationType" required="true" description="Can be 'rolo' for a Rolodex item, or 'org' for an Organization" %>

<c:set var="propLocationAttributes" value="${DataDictionary.ProposalSite.attributes}" />

<c:if test="${empty locationNameEditable}">
    <c:set var="locationNameEditable" value="true"/>
</c:if>

<tr class="addline">
    <th class="infoline" width="10%">
        <c:set var="class" value="infoline" />
        Add:
    </th>
    
    <%-- Location Name input field --%>
    <td class="${class}" width="20%">
        <c:if test="${locationNameEditable}">
            <kul:htmlControlAttribute
                property="${newProposalSiteField}.locationName"
                attributeEntry="${propLocationAttributes.locationName}" />
        </c:if>
    </td>
    
    <%-- Address selection --%>
    <td class="${class}" width="45%">
        <kul:htmlControlAttribute
            property="${newProposalSiteField}.organization"
            attributeEntry="${propLocationAttributes.rolodexId}" />
            
        <%-- Code for Rolodex lookup enabled sites follows --%>
        <c:if test="${locationType == 'rolo'}">
            <%-- Site name --%>
            <c:set var="fieldName" value="${newProposalSiteField}.rolodexId"/>
            ${kfunc:registerEditableProperty(KualiForm, fieldName)}
      		<input type = "hidden" value = "${newProposalSite.rolodexId}" name = "${fieldName}"/>
            <c:choose>
                <c:when test="${empty newProposalSite.rolodexId}">
                    <c:out value="(Select)" />
                </c:when>
                <c:otherwise>
                    <span class="changedClearOnReset"><c:out value="${newProposalSite.rolodex.organization}" /></span>
                </c:otherwise>
            </c:choose>
            
            <%-- The lookup control --%>
            <kul:checkErrors keyMatch="${newProposalSiteField}.address" />
            <c:if test="${hasErrors}">
                <kul:fieldShowErrorIcon />
            </c:if>
            <kra:section permission="modifyProposal">
                <kul:lookup
                    boClassName="org.kuali.coeus.common.framework.rolodex.Rolodex" 
                    fieldConversions="rolodexId:${newProposalSiteField}.rolodexId,organization:${newProposalSiteField}.locationName,postalCode:${newProposalSiteField}.rolodex.postalCode,addressLine1:${newProposalSiteField}.rolodex.addressLine1,addressLine2:${newProposalSiteField}.rolodex.addressLine2,addressLine3:${newProposalSiteField}.rolodex.addressLine3,city:${newProposalSiteField}.rolodex.city,state:${newProposalSiteField}.rolodex.state"
                    anchor="${currentTabIndex}"/> 
                <kul:directInquiry
                    boClassName="org.kuali.coeus.common.framework.rolodex.Rolodex"
                    inquiryParameters="${newProposalSiteField}.rolodexId:rolodexId"
                    anchor="${currentTabIndex}"/>
                <br/>
            </kra:section>
            
            <%-- Site address --%>
            <span class="changedClearOnReset">
            <c:if test="${!empty newProposalSite.rolodex.addressLine1}">
                <c:out value="${newProposalSite.rolodex.addressLine1}" />
                <br/>
            </c:if>
            <c:if test="${!empty newProposalSite.rolodex.addressLine2}">
                <c:out value="${newProposalSite.rolodex.addressLine2}" />
                <br/>
            </c:if>
            <c:if test="${!empty newProposalSite.rolodex.addressLine3}">
                <c:out value="${newProposalSite.rolodex.addressLine3}" />
                <br/>
            </c:if>
            <c:if test="${!empty newProposalSite.rolodex.city || !empty newProposalSite.rolodex.state || !empty newProposalSite.rolodex.postalCode}">
                <c:out value="${newProposalSite.rolodex.city}," />&nbsp;
                <c:out value="${newProposalSite.rolodex.state}" />&nbsp;
                <c:out value="${newProposalSite.rolodex.postalCode}" />
            </c:if>
        </c:if>
        </span>
        
        <%-- Code for non-Rolodex sites follows (uses the organization field, does a organization lookup) --%>
        <c:if test="${locationType == 'org'}">
            <%-- Site name --%>
            <input type = "hidden" value = "${newProposalSite.organizationId}" name = "${newProposalSiteField}.organizationId"/>
            <c:choose>
                <c:when test="${empty newProposalSite.organizationId}">
                    <c:out value="(Select)" />
                </c:when>
                <c:otherwise>
                    <span class="changedClearOnReset"><c:out value="${newProposalSite.organization.organizationName}" /></span>
                </c:otherwise>
            </c:choose>
            
            <%-- The lookup control --%>
            <kul:checkErrors keyMatch="${newProposalSiteField}.address" />
            <c:if test="${hasErrors}">
                <kul:fieldShowErrorIcon />
            </c:if>
            <kra:section permission="modifyProposal">
                <kul:lookup boClassName="org.kuali.coeus.common.framework.org.Organization" 
                    fieldConversions="organizationId:${newProposalSiteField}.organizationId,organizationName:${newProposalSiteField}.locationName,address:${newProposalSiteField}.organization.address"
                    anchor="${currentTabIndex}"/> 
                <kul:directInquiry
                    boClassName="org.kuali.coeus.common.framework.org.Organization"
                    inquiryParameters="${newProposalSiteField}.organizationId:organizationId"
                    anchor="${currentTabIndex}"/>
            </kra:section>
            <br/>
            
            <%-- Site address --%>
            <span class="changedClearOnReset">
            <c:out value="${newProposalSite.organization.address}"/>
            </span>
        </c:if>
    </td>

    <%-- Add button --%>
    <td class="${class}" width="25%">
        <div align=center>
            <html:image property="methodToCall.${addMethodToCall}.anchor${currentTabIndex}"
            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
        </div>
    </td>
</tr>
