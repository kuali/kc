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
 This is the tag for the Organization/Location panel.
--%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="organizationAttributes" value="${DataDictionary.Organization.attributes}" />
<c:set var="propLocationAttributes" value="${DataDictionary.ProposalLocation.attributes}" />
<c:set var="rolodexAttributes" value="${DataDictionary.Rolodex.attributes}" />

<kul:tab tabTitle="Organization/Location" defaultOpen="false" tabErrorKey="document.developmentProposalList[0].organizationId*,document.developmentProposalList[0].performingOrganizationId*,document.developmentProposalList[0].proposalLocation*,newPropLocation*">
	<div class="tab-container" align="center">
        <kra-pd:proposalSite
            tabTitle="Applicant Organization"
            showTabTitle="true"
            parentTab="Organization/Location"
            addTable="true"
            proposalSiteBo="${KualiForm.document.developmentProposal.applicantOrganization}"
            proposalSiteBoName="document.developmentProposal.applicantOrganization"
            proposalSiteHelper="applicantOrganizationHelper"
            addDistrictMethodToCall="addApplicantOrgCongDistrict"
            deleteDistrictMethodToCall="deleteApplicantOrgCongDistrict" />
        <kra-pd:proposalSite
            tabTitle="Performing Organization"
            showTabTitle="true"
            parentTab="Organization/Location"
            addTable="true"
            proposalSiteBo="${KualiForm.document.developmentProposal.performingOrganization}"
            proposalSiteBoName="document.developmentProposal.performingOrganization"
            proposalSiteHelper="performingOrganizationHelper"
            addDistrictMethodToCall="addPerformingOrgCongDistrict"
            deleteDistrictMethodToCall="deletePerformingOrgCongDistrict"
            clearSiteMethodToCall="clearPerformingOrganization" />
        <kra-pd:multipleProposalSites
            tabTitle="Performance Site Locations"
            proposalSitesList="${KualiForm.document.developmentProposal.performanceSites}"
            proposalSitesListName="document.developmentProposal.performanceSites"
            proposalSiteHelperList="performanceSiteHelpers"
            newProposalSite="${KualiForm.newPerformanceSite}"
            newProposalSiteField="newPerformanceSite"
            addSiteMethodToCall="addPerformanceSite"
            deleteSiteMethodToCall="deletePerformanceSite"
            addDistrictMethodToCall="addPerformanceSiteCongDistrict"
            deleteDistrictMethodToCall="deletePerformanceSiteCongDistrict"
            rolodexLookup="true" />
        <kra-pd:multipleProposalSites
            tabTitle="Other Organizations"
            proposalSitesList="${KualiForm.document.developmentProposal.otherOrganizations}"
            proposalSitesListName="document.developmentProposal.otherOrganizations"
            proposalSiteHelperList="otherOrganizationHelpers"
            newProposalSite="${KualiForm.newOtherOrganization}"
            newProposalSiteField="newOtherOrganization"
            addSiteMethodToCall="addOtherOrganization"
            deleteSiteMethodToCall="deleteOtherOrganization"
            addDistrictMethodToCall="addOtherOrgCongDistrict"
            deleteDistrictMethodToCall="deleteOtherOrgCongDistrict"
            rolodexLookup="false" />
    </div>
</kul:tab>
