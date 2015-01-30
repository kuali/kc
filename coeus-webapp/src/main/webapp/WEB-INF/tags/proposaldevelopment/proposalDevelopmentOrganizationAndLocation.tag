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
 This is the tag for the Organization/Location panel.
--%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="organizationAttributes" value="${DataDictionary.Organization.attributes}" />
<c:set var="propLocationAttributes" value="${DataDictionary.ProposalLocation.attributes}" />
<c:set var="rolodexAttributes" value="${DataDictionary.Rolodex.attributes}" />

<kul:tab tabTitle="Organization/Location" defaultOpen="false" tabErrorKey="document.developmentProposal.*,newPerformanceSite.*,newOtherOrganization.*,applicantOrganizationHelper*,performingOrganizationHelper*,performanceSiteHelpers[*,otherOrganizationHelpers[*">
	<div class="tab-container" align="center">
        <kra-pd:proposalSite
            tabTitle="Applicant Organization"
            showTabTitle="true"
            parentTab="Organization/Location"
            addTable="true"
            proposalSiteBo="${KualiForm.document.developmentProposal.applicantOrganization}"
            proposalSiteBoName="document.developmentProposal.applicantOrganization"
            locationNameEditable="false"
            addressSelectable="false"
            congressionalDistrictHelper="applicantOrganizationHelper"
            addDistrictMethodToCall="addApplicantOrgCongDistrict"
            deleteDistrictMethodToCall="deleteApplicantOrgCongDistrict"
            clearSiteMethodToCall="clearApplicantOrganization"
            locationType="org" />
        <kra-pd:proposalSite
            tabTitle="Performing Organization"
            showTabTitle="true"
            parentTab="Organization/Location"
            addTable="true"
            proposalSiteBo="${KualiForm.document.developmentProposal.performingOrganization}"
            proposalSiteBoName="document.developmentProposal.performingOrganization"
            locationNameEditable="false"
            congressionalDistrictHelper="performingOrganizationHelper"
            addDistrictMethodToCall="addPerformingOrgCongDistrict"
            deleteDistrictMethodToCall="deletePerformingOrgCongDistrict"
            locationType="org" />
        <kra-pd:multipleProposalSites
            tabTitle="Performance Site Locations"
            proposalSitesList="${KualiForm.document.developmentProposal.performanceSites}"
            proposalSitesListName="document.developmentProposal.performanceSites"
            congressionalDistrictHelperList="performanceSiteHelpers"
            newProposalSite="${KualiForm.newPerformanceSite}"
            newProposalSiteField="newPerformanceSite"
            addSiteMethodToCall="addPerformanceSite"
            deleteSiteMethodToCall="deletePerformanceSite"
            clearAddressMethodToCall="clearPerformanceSiteAddress"
            addDistrictMethodToCall="addPerformanceSiteCongDistrict"
            deleteDistrictMethodToCall="deletePerformanceSiteCongDistrict"
            locationType="rolo" />
        <kra-pd:multipleProposalSites
            tabTitle="Other Organizations"
            proposalSitesList="${KualiForm.document.developmentProposal.otherOrganizations}"
            proposalSitesListName="document.developmentProposal.otherOrganizations"
            locationNameEditable="false"
            congressionalDistrictHelperList="otherOrganizationHelpers"
            newProposalSite="${KualiForm.newOtherOrganization}"
            newProposalSiteField="newOtherOrganization"
            addSiteMethodToCall="addOtherOrganization"
            deleteSiteMethodToCall="deleteOtherOrganization"
            addDistrictMethodToCall="addOtherOrgCongDistrict"
            deleteDistrictMethodToCall="deleteOtherOrgCongDistrict"
            locationType="org" />
    </div>
</kul:tab>
