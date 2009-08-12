/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s.generator.impl;

import gov.grants.apply.forms.sf424ShortV10.SF424ShortDocument;
import gov.grants.apply.forms.sf424ShortV10.SF424ShortDocument.SF424Short;
import gov.grants.apply.system.globalLibraryV20.ApplicantTypeCodeDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationType;
import org.kuali.kra.bo.OrganizationTypeList;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This Class is used to generate XML object for grants.gov SF424ShortV1_0. This form is generated using XMLBean classes and is
 * based on SF424Short-V1.0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SF424ShortV1_0Generator extends SF424BaseGenerator {

    private String applicantTypeOtherSpecify = null;
    private static final String SPONSOR_CODE = "sponsorCode";
    private static final String DESCRIPTION = "description";
    private static final String ABSTRACT_TYPE_PROJECT_DESCRIPTION = "1";
    private static final int SPONSOR_NAME_MAX_LENGTH = 60;
    private static final int CFDA_NUMBER_MAX_LENGTH = 15;
    private static final int PROGRAM_ANNOUNCEMENT_TITLE_MAX_LENGTH = 120;
    private static final int OPPORTUNITY_ID_MAX_LENGTH = 40;
    private static final int ABSTRACT_TYPE_CODE_MAX_LENGTH = 1000;
    private static final int OFFICE_PHONE_MAX_LENGTH = 25;
    private static final int EMAIL_ADDRESS_MAX_LENGTH = 60;
    private static final int FAX_NUMBER_MAX_LENGTH = 25;

    private BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);

    /**
     * 
     * This method gets SF424Short information for the form which includes informations regarding
     * ApplicationType,AgencyName,CFDANumber,DUNSNumber,AuthorizedRepresentative.
     * 
     * @return sf424ShortDocument {@link XmlObject} of type SF424ShortDocument.
     */
    private SF424ShortDocument getsf424ShortDocument() {

        SF424ShortDocument sf424ShortDocument = SF424ShortDocument.Factory.newInstance();
        SF424Short sf424Short = SF424Short.Factory.newInstance();
        sf424Short.setFormVersion(S2SConstants.FORMVERSION_1_0);

        // Set default values for mandatory fields
        sf424Short.setAgencyName("");
        sf424Short.setFundingOpportunityNumber("");
        sf424Short.setFundingOpportunityTitle("");
        sf424Short.setOrganizationName("");
        sf424Short.setEmployerTaxpayerIdentificationNumber("");
        sf424Short.setProjectDescription("");
        sf424Short.setAuthorizedRepresentativeTitle("");
        sf424Short.setAuthorizedRepresentativePhoneNumber("");
        sf424Short.setAuthorizedRepresentativeEmail("");

        Map<String, String> sponsorMap = new HashMap<String, String>();
        sponsorMap.put(SPONSOR_CODE, pdDoc.getDevelopmentProposal().getPrimeSponsorCode());
        Sponsor sponsor = (Sponsor) businessObjectService.findByPrimaryKey(Sponsor.class, sponsorMap);
        if (pdDoc.getDevelopmentProposal().getSponsor() != null && pdDoc.getDevelopmentProposal().getSponsor().getSponsorName() != null) {
            if (pdDoc.getDevelopmentProposal().getSponsor().getSponsorName().length() > SPONSOR_NAME_MAX_LENGTH) {
                sf424Short.setAgencyName(pdDoc.getDevelopmentProposal().getSponsor().getSponsorName().substring(0, SPONSOR_NAME_MAX_LENGTH));
            }
            else {
                sf424Short.setAgencyName(pdDoc.getDevelopmentProposal().getSponsor().getSponsorName());
            }
        }
        else if (sponsor.getSponsorName() != null) {
            if (sponsor.getSponsorName().length() > SPONSOR_NAME_MAX_LENGTH) {
                sf424Short.setAgencyName(sponsor.getSponsorName().substring(0, SPONSOR_NAME_MAX_LENGTH));
            }
            else {
                sf424Short.setAgencyName(sponsor.getSponsorName());
            }
        }

        if (pdDoc.getDevelopmentProposal().getCfdaNumber() != null) {
            if (pdDoc.getDevelopmentProposal().getCfdaNumber().length() > CFDA_NUMBER_MAX_LENGTH) {
                sf424Short.setCFDANumber(pdDoc.getDevelopmentProposal().getCfdaNumber().substring(0, CFDA_NUMBER_MAX_LENGTH));
            }
            else {
                sf424Short.setCFDANumber(pdDoc.getDevelopmentProposal().getCfdaNumber());
            }
        }
        if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle() != null) {
            if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle().length() > PROGRAM_ANNOUNCEMENT_TITLE_MAX_LENGTH) {
                sf424Short.setCFDAProgramTitle(pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle().substring(0,
                        PROGRAM_ANNOUNCEMENT_TITLE_MAX_LENGTH));
            }
            else {
                sf424Short.setCFDAProgramTitle(pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle());
            }
        }
        sf424Short.setDateReceived(s2sUtilService.getCurrentCalendar());
        S2sOpportunity s2sOpportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
        if (s2sOpportunity != null) {
            s2sOpportunity.refreshNonUpdateableReferences();
            if (s2sOpportunity.getOpportunityId().length() > OPPORTUNITY_ID_MAX_LENGTH) {
                sf424Short.setFundingOpportunityNumber(s2sOpportunity.getOpportunityId().substring(0, OPPORTUNITY_ID_MAX_LENGTH));
            }
            else {
                sf424Short.setFundingOpportunityNumber(s2sOpportunity.getOpportunityId());
            }
            if (s2sOpportunity.getOpportunityTitle() != null) {
                sf424Short.setFundingOpportunityTitle(s2sOpportunity.getOpportunityTitle());
            }
        }

        Organization organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
        if (organization.getOrganizationName() != null) {
            sf424Short.setOrganizationName(organization.getOrganizationName());
        }

        Rolodex rolodex = pdDoc.getDevelopmentProposal().getApplicantOrganization().getRolodex();
        if (rolodex != null) {
            sf424Short.setAddress(globLibV20Generator.getAddressDataType(rolodex));
        }
        ApplicantTypeCodeDataType.Enum applicantTypeCode = getApplicantType(APPLICANT_TYPE_1_INDEX);
        if (applicantTypeCode != null) {
            sf424Short.setApplicantTypeCode1(applicantTypeCode);
        }
        ApplicantTypeCodeDataType.Enum applicantTypeCode2 = getApplicantType(APPLICANT_TYPE_2_INDEX);
        if (applicantTypeCode2 != null) {
            sf424Short.setApplicantTypeCode2(applicantTypeCode2);
        }
        ApplicantTypeCodeDataType.Enum applicantTypeCode3 = getApplicantType(APPLICANT_TYPE_3_INDEX);
        if (applicantTypeCode3 != null) {
            sf424Short.setApplicantTypeCode3(applicantTypeCode3);
        }
        if (applicantTypeOtherSpecify != null && !applicantTypeOtherSpecify.equals("")) {
            sf424Short.setApplicantTypeOtherSpecify(applicantTypeOtherSpecify);
        }
        sf424Short.setEmployerTaxpayerIdentificationNumber(organization.getFedralEmployerId());
        sf424Short.setDUNSNumber(organization.getDunsNumber());
        String congressionalDistrict = organization.getCongressionalDistrict() == null ? S2SConstants.VALUE_UNKNOWN : organization
                .getCongressionalDistrict();
        if (congressionalDistrict.length() > CONGRESSIONAL_DISTRICT_MAX_LENGTH) {
            sf424Short.setCongressionalDistrictApplicant(congressionalDistrict.substring(0, CONGRESSIONAL_DISTRICT_MAX_LENGTH));
        }
        else {
            sf424Short.setCongressionalDistrictApplicant(congressionalDistrict);
        }

        sf424Short.setProjectTitle(pdDoc.getDevelopmentProposal().getTitle());

        for (ProposalAbstract proposalAbstract : pdDoc.getDevelopmentProposal().getProposalAbstracts()) {
            if (proposalAbstract.getAbstractTypeCode() != null
                    && proposalAbstract.getAbstractTypeCode().equals(ABSTRACT_TYPE_PROJECT_DESCRIPTION)) {
                if (proposalAbstract.getAbstractTypeCode().length() > ABSTRACT_TYPE_CODE_MAX_LENGTH) {
                    sf424Short.setProjectDescription(proposalAbstract.getAbstractTypeCode().substring(0,
                            ABSTRACT_TYPE_CODE_MAX_LENGTH));
                }
                else {
                    sf424Short.setProjectDescription(proposalAbstract.getAbstractTypeCode());
                }
                break;
            }
        }

        sf424Short.setProjectStartDate(s2sUtilService.convertDateToCalendar(pdDoc.getDevelopmentProposal().getRequestedStartDateInitial()));
        sf424Short.setProjectEndDate(s2sUtilService.convertDateToCalendar(pdDoc.getDevelopmentProposal().getRequestedEndDateInitial()));

        ProposalPerson pi = s2sUtilService.getPrincipalInvestigator(pdDoc);
        sf424Short.setProjectDirectorGroup(globLibV20Generator.getContactPersonDataType(pi));

        // Rolodex is set to getOrganization.getRolodex above
        sf424Short.setContactPersonGroup(globLibV20Generator.getContactPersonDataType(rolodex));

        // value is hard coded
        sf424Short.setApplicationCertification(YesNoDataType.Y_YES);

        DepartmentalPerson aorInfo = s2sUtilService.getDepartmentalPerson(pdDoc);
        sf424Short.setAuthorizedRepresentative(globLibV20Generator.getHumanNameDataType(aorInfo));

        if (aorInfo.getPrimaryTitle() != null) {
            if (aorInfo.getPrimaryTitle().length() > PRIMARY_TITLE_MAX_LENGTH) {
                sf424Short.setAuthorizedRepresentativeTitle(aorInfo.getPrimaryTitle().substring(0, PRIMARY_TITLE_MAX_LENGTH));
            }
            else {
                sf424Short.setAuthorizedRepresentativeTitle(aorInfo.getPrimaryTitle());
            }
        }

        if (aorInfo.getOfficePhone() != null) {
            if (aorInfo.getOfficePhone().length() > OFFICE_PHONE_MAX_LENGTH) {
                sf424Short.setAuthorizedRepresentativePhoneNumber(aorInfo.getOfficePhone().substring(0, OFFICE_PHONE_MAX_LENGTH));
            }
            else {
                sf424Short.setAuthorizedRepresentativePhoneNumber(aorInfo.getOfficePhone());
            }
        }

        if (aorInfo.getEmailAddress() != null) {
            if (aorInfo.getEmailAddress().length() > EMAIL_ADDRESS_MAX_LENGTH) {
                sf424Short.setAuthorizedRepresentativeEmail(aorInfo.getEmailAddress().substring(0, EMAIL_ADDRESS_MAX_LENGTH));
            }
            else {
                sf424Short.setAuthorizedRepresentativeEmail(aorInfo.getEmailAddress());
            }
        }

        if (aorInfo.getFaxNumber() != null) {
            if (aorInfo.getFaxNumber().length() > FAX_NUMBER_MAX_LENGTH) {
                sf424Short.setAuthorizedRepresentativeFaxNumber(aorInfo.getFaxNumber().substring(0, FAX_NUMBER_MAX_LENGTH));
            }
            else {
                sf424Short.setAuthorizedRepresentativeFaxNumber(aorInfo.getFaxNumber());
            }
        }
        sf424Short.setAuthorizedRepresentativeSignature(aorInfo.getFullName());
        sf424Short.setAuthorizedRepresentativeDateSigned(s2sUtilService.getCurrentCalendar());
        sf424ShortDocument.setSF424Short(sf424Short);
        return sf424ShortDocument;
    }


    /**
     * 
     * Gets the Applicant type code information for the particular applicant.It returns enumeration value for the code such as State
     * Government,Non-profit Organization,Native American Tribal Government etc.
     * 
     * @param index (int)
     * @return appTypeCodeDataType(ApplicantTypeCodeDataType.Enum) applicant type corresponding to the applicant type code.
     */
    private ApplicantTypeCodeDataType.Enum getApplicantType(int index) {
        ApplicantTypeCodeDataType.Enum appTypeCodeDataType = ApplicantTypeCodeDataType.X_OTHER_SPECIFY;
        Collection<OrganizationTypeList> orgTypeList = businessObjectService.findMatchingOrderBy(OrganizationTypeList.class,
                new HashMap<String, String>(), DESCRIPTION, true);
        Iterator<OrganizationTypeList> orgTypeIterator = orgTypeList.iterator();
        int count = -1;
        OrganizationTypeList orgList;
        while (orgTypeIterator.hasNext()) {
            for (OrganizationType orgType : pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationTypes()) {
                orgList = orgTypeIterator.next();
                count++;
                if (count == index) {
                    appTypeCodeDataType = ApplicantTypeCodeDataType.Enum.forString(orgList.getDescription());
                    switch (orgType.getOrganizationTypeCode()) {
                        case 3: {
                            applicantTypeOtherSpecify = APPLICANT_OTHERSPECIFY_FEDERAL;
                            break;
                        }
                        case 14: {
                            applicantTypeOtherSpecify = APPLICANT_OTHERSPECIFY_DISADVANTAGED;
                            break;
                        }
                        case 15: {
                            applicantTypeOtherSpecify = APPLICANT_OTHERSPECIFY_WOMEN;
                            break;
                        }
                    }
                    return appTypeCodeDataType;
                }
            }
        }
        return appTypeCodeDataType;
    }

    /**
     * This method creates {@link XmlObject} of type {@link SF424ShortDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getsf424ShortDocument();
    }

    /**
     * This method typecasts the given {@link XmlObject} to the required generator type and returns back the document of that
     * generator type.
     * 
     * @param xmlObject which needs to be converted to the document type of the required generator
     * @return {@link XmlObject} document of the required generator type
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
     */
    public XmlObject getFormObject(XmlObject xmlObject) {
        SF424ShortDocument sf424ShortDocument = SF424ShortDocument.Factory.newInstance();
        SF424Short sf424Short = (SF424Short) xmlObject;
        sf424ShortDocument.setSF424Short(sf424Short);
        return sf424ShortDocument;
    }
}
