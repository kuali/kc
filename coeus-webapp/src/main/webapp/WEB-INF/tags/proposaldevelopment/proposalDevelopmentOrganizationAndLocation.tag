<%--
 Copyright 2005-2013 The Kuali Foundation
 
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
