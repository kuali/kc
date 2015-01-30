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
 This tag shows a list of Proposal Sites. If the user has permission to edit a proposal, sites can be added and deleted.
--%>
<%@ attribute name="tabTitle" required="true" description="The title string displayed at the top." %>
<%@ attribute name="proposalSitesList" required="true" type="java.util.List" description="A List of org.kuali.coeus.propdev.impl.location.ProposalSite objects to be displayed." %>
<%@ attribute name="proposalSitesListName" required="true" description="The JSP name of proposalSitesList" %>
<%@ attribute name="newProposalSite" required="true" type="org.kuali.coeus.propdev.impl.location.ProposalSite" description="A ProposalSite object for storing a new Proposal Site selected by the user." %>
<%@ attribute name="newProposalSiteField" required="true" description="The JSP name of the newProposalSite object" %>
<%@ attribute name="locationNameEditable" required="false" description="If this is set to false, the location names cannot be edited." %>
<%@ attribute name="congressionalDistrictHelperList" required="true" description="" %>
<%@ attribute name="addSiteMethodToCall" required="true" description="The action method to call when the Add Proposal Site button is clicked" %>
<%@ attribute name="deleteSiteMethodToCall" required="true" description="The action method to call when the Delete Proposal Site button is clicked" %>
<%@ attribute name="clearAddressMethodToCall" required="false" description="The method to call when the user clicks the Clear Address button; this method should delete the proposal site. If this parameter is not present, no Clear Address button is shown." %>
<%@ attribute name="addDistrictMethodToCall" required="true" description="The action method to call when the Add District button is clicked" %>
<%@ attribute name="deleteDistrictMethodToCall" required="true" description="The action method to call when the Delete District button is clicked" %>
<%@ attribute name="locationType" required="true" description="Can be 'rolo' for a Rolodex item, or 'org' for an Organization" %>

<c:if test="${empty locationNameEditable}">
    <c:set var="locationNameEditable" value="true"/>
</c:if>

<h3>
    <span class="subhead-left">${tabTitle}</span>
        <c:choose>
    	   <c:when test="${fn:contains(tabTitle,'Other Organizations')}"> 
     	  	 <span class="subhead-right"><kul:help parameterNamespace="KC-PD" parameterDetailType="Document" parameterName="otherOrganizationDocumentHelpUrl" altText="help"/></span>
			</c:when>
    	   <c:when test="${fn:contains(tabTitle,'Performance Site Locations')}"> 
     	  	 <span class="subhead-right"><kul:help parameterNamespace="KC-PD" parameterDetailType="Document" parameterName="proposalDevelopmentperformancesitelocationsHelpUrl" altText="help"/></span>
			</c:when>
			<c:otherwise>
        		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.coeus.propdev.impl.location.ProposalSite" altText="help"/></span>
 	    	</c:otherwise>
 	    </c:choose>
</h3>
<table cellpadding=0 cellspacing="0" summary="">
    <tr>
        <th><div align="left">&nbsp;</div></th>  
        <th><div align="left"><kul:htmlAttributeLabel attributeEntry="${propLocationAttributes.location}" noColon="true" /></div></th>
        <kul:htmlAttributeHeaderCell literalLabel="Address" scope="col" align="left"/> 
        <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
    </tr>

    <kra:section permission="modifyProposal">
        <kra-pd:addProposalSite
            newProposalSite="${newProposalSite}"
            newProposalSiteField="${newProposalSiteField}"
            locationNameEditable="${locationNameEditable}"
            addMethodToCall="${addSiteMethodToCall}"
            locationType="${locationType}" />
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
                locationNameEditable="${locationNameEditable}"
                congressionalDistrictHelper="${congressionalDistrictHelperList}[${status.index}]"
                deleteSiteMethodToCall="${deleteSiteMethodToCall}.site${status.index}"
                addDistrictMethodToCall="${addDistrictMethodToCall}.site${status.index}"
                clearAddressMethodToCall="${clearAddressMethodToCall}.site${status.index}"
                deleteDistrictMethodToCall="${deleteDistrictMethodToCall}.site${status.index}"
                locationType="${locationType}" />
        </tr>
    </c:forEach>
</table>
