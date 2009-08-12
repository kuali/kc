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
 This tag shows a list of Proposal Sites. If the user has permission to edit a proposal, sites can be added and deleted.
--%>
<%@ attribute name="tabTitle" required="true" description="The title string displayed at the top." %>
<%@ attribute name="proposalSitesList" required="true" type="java.util.List" description="A List of org.kuali.kra.proposaldevelopment.bo.ProposalSite objects to be displayed." %>
<%@ attribute name="proposalSitesListName" required="true" description="The JSP name of proposalSitesList" %>
<%@ attribute name="newProposalSite" required="true" type="org.kuali.kra.proposaldevelopment.bo.ProposalSite" description="A ProposalSite object for storing a new Proposal Site selected by the user." %>
<%@ attribute name="newProposalSiteField" required="true" description="The JSP name of the newProposalSite object" %>
<%@ attribute name="proposalSiteHelperList" required="true" description="" %>
<%@ attribute name="addSiteMethodToCall" required="true" description="The action method to call when the Add Proposal Site button is clicked" %>
<%@ attribute name="deleteSiteMethodToCall" required="true" description="The action method to call when the Delete Proposal Site button is clicked" %>
<%@ attribute name="addDistrictMethodToCall" required="true" description="The action method to call when the Add District button is clicked" %>
<%@ attribute name="deleteDistrictMethodToCall" required="true" description="The action method to call when the Delete District button is clicked" %>
<%@ attribute name="rolodexLookup" required="false" type="java.lang.Boolean" description="If set to true, a Rolodex lookup is done; otherwise, an Organization lookup is done" %>

<h3>
    <span class="subhead-left">${tabTitle}</span>
    <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.ProposalLocation" altText="help"/></span>
</h3>
<table cellpadding=0 cellspacing="0" summary="">
    <tr>
        <th><div align="left">&nbsp</div></th>  
        <th><div align="left"><kul:htmlAttributeLabel attributeEntry="${propLocationAttributes.location}" noColon="true" /></div></th>
        <kul:htmlAttributeHeaderCell literalLabel="Address" scope="col" align="left"/> 
        <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
    </tr>

    <kra:section permission="modifyProposal">
        <tr>
            <td colspan="4">
                <kra-pd:addProposalSite newProposalSite="${newProposalSite}" newProposalSiteField="${newProposalSiteField}" addMethodToCall="${addSiteMethodToCall}" rolodexLookup="${rolodexLookup}" />
            </td>
        </tr>
    </kra:section>
    <c:forEach var="proposalSite" items="${proposalSitesList}" varStatus="status">
        <tr>
            <th rowspan="2">
                <c:out value="${status.index+1}" />
            </th>
            <kra-pd:proposalSite
                tabTitle="${tabTitle} ${status.index}"
                showTabTitle="false"
                parentTab="Organization/Location"
                proposalSiteBo="${proposalSitesList[status.index]}"
                proposalSiteBoName="${proposalSitesListName}[${status.index}]"
                proposalSiteHelper="${proposalSiteHelperList}[${status.index}]"
                deleteSiteMethodToCall="${deleteSiteMethodToCall}.site${status.index}"
                addDistrictMethodToCall="${addDistrictMethodToCall}.site${status.index}"
                deleteDistrictMethodToCall="${deleteDistrictMethodToCall}.site${status.index}"
                rolodexLookup="${rolodexLookup}" />
        </tr>
    </c:forEach>
</table>
