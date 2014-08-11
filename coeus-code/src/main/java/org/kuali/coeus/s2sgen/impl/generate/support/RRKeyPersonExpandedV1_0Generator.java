/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.rrKeyPersonExpandedV10.PersonProfileDataType;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.PersonProfileDataType.Profile;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.PersonProfileDataType.Profile.OtherProjectRoleCategory;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.ProjectRoleDataType;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.RRKeyPersonExpandedDocument;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.RRKeyPersonExpandedDocument.RRKeyPersonExpanded;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.RRKeyPersonExpandedDocument.RRKeyPersonExpanded.AdditionalProfilesAttached;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.RRKeyPersonExpandedDocument.RRKeyPersonExpanded.BioSketchsAttached;
import gov.grants.apply.forms.rrKeyPersonExpandedV10.RRKeyPersonExpandedDocument.RRKeyPersonExpanded.SupportsAttached;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.util.FieldValueConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Class for generating the XML object for grants.gov RRKeyPersonExpandedV1.0. Form is generated using XMLBean classes and is based
 * on RRKeyPersonExpanded schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("RRKeyPersonExpandedV1_0Generator")
public class RRKeyPersonExpandedV1_0Generator extends RRKeyPersonExpandedBaseGenerator {

    private static final Log LOG = LogFactory.getLog(RRKeyPersonExpandedV1_0Generator.class);

    @Value("http://apply.grants.gov/forms/RR_KeyPersonExpanded-V1.0")
    private String namespace;

    @Value("RR_KeyPersonExpanded-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/RR_KeyPersonExpanded-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrKeyPersonExpandedV10")
    private String packageName;

    @Value("155")
    private int sortIndex;

    @Autowired
    @Qualifier("sponsorHierarchyService")
    private SponsorHierarchyService sponsorHierarchyService;

    /**
     * 
     * This method gives details of Principal Investigator,KeyPersons and the corresponding attachments for RRKeyPersons
     * 
     * @return rrKeyPersonExpandedDocument {@link XmlObject} of type RRKeyPersonExpandedDocument.
     */
    private RRKeyPersonExpandedDocument getRRKeyPersonExpanded() {

        LOG.info("Inside RRKeyPersonExpanded ");
        RRKeyPersonExpandedDocument rrKeyPersonExpandedDocument = RRKeyPersonExpandedDocument.Factory.newInstance();
        RRKeyPersonExpanded rrKeyPersonExpanded = RRKeyPersonExpanded.Factory.newInstance();
        rrKeyPersonExpanded.setFormVersion(FormVersion.v1_0.getVersion());
        rrKeyPersonExpanded.setPDPI(getPersonProfilePI());
        rrKeyPersonExpanded.setKeyPersonArray(getpersonProfileKeyPerson());

        if (extraPersons.size() > 0) {
            for (ProposalPersonContract extraPerson : extraPersons) {
                BioSketchsAttached personBioSketch = BioSketchsAttached.Factory.newInstance();
                AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, extraPerson.getPersonId(), extraPerson
                        .getRolodexId(), BIOSKETCH_TYPE);
                personBioSketch.setBioSketchAttached(bioSketchAttachment);
                rrKeyPersonExpanded.setBioSketchsAttached(personBioSketch);

                AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, extraPerson.getPersonId(), extraPerson
                        .getRolodexId(), CURRENT_PENDING_TYPE);
                if (supportAttachment != null) {
                    SupportsAttached supportsAttached = SupportsAttached.Factory.newInstance();
                    supportsAttached.setSupportAttached(supportAttachment);
                    rrKeyPersonExpanded.setSupportsAttached(supportsAttached);
                }
            }
            AttachedFileDataType attachedFileDataType = null;
            for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                if (narrative.getNarrativeType().getCode() != null) {
                    if (Integer.parseInt(narrative.getNarrativeType().getCode()) == PROFILE_TYPE) {
                    	attachedFileDataType = getAttachedFileType(narrative);
                    	if(attachedFileDataType != null){
	                        AdditionalProfilesAttached additionalProfilesAttached = AdditionalProfilesAttached.Factory.newInstance();
	                        additionalProfilesAttached.setAdditionalProfileAttached(attachedFileDataType);
	                        rrKeyPersonExpanded.setAdditionalProfilesAttached(additionalProfilesAttached);
	                        break;
                    	}
                    }
                }
            }
        }
        rrKeyPersonExpandedDocument.setRRKeyPersonExpanded(rrKeyPersonExpanded);
        return rrKeyPersonExpandedDocument;
    }

    /**
     * 
     * This method is used to get PersonProfile details of Principal Investigator.It also gives the information about the
     * attachments related to the principal investigator.
     * 
     * @return profileDataType(PersonProfileDataType) profile of PI
     */
    private PersonProfileDataType getPersonProfilePI() {

        PersonProfileDataType profileDataType = PersonProfileDataType.Factory.newInstance();
        Profile profile = Profile.Factory.newInstance();
        ProposalPersonContract PI = s2SProposalPersonService.getPrincipalInvestigator(pdDoc);
        if (PI != null) {
            if (PI.getPersonId() != null) {
                pIPersonOrRolodexId = PI.getPersonId();
            }
            else if (PI.getRolodexId() != null) {
                pIPersonOrRolodexId = PI.getRolodexId().toString();
            }
            profile.setName(globLibV10Generator.getHumanNameDataType(PI));
            if (PI.getDirectoryTitle() != null) {
                if (PI.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
                    profile.setTitle(PI.getDirectoryTitle().substring(0, DIRECTORY_TITLE_MAX_LENGTH));
                }
                else {
                    profile.setTitle(PI.getDirectoryTitle());
                }
            }
            profile.setAddress(globLibV10Generator.getAddressRequireCountryDataType(PI));
            profile.setPhone(PI.getOfficePhone());
            if (PI.getFaxNumber() != null) {
                profile.setFax(PI.getFaxNumber());
            }
            profile.setEmail(PI.getEmailAddress());
            if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
                profile.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());
            }
            if(PI.getHomeUnit() != null) {
                KcPersonContract kcPerson = PI.getPerson();
                String departmentName =  kcPerson.getOrganizationIdentifier();
                profile.setDepartmentName(departmentName);
            }
            else
            {
                DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
                profile.setDepartmentName(developmentProposal.getOwnedByUnit().getUnitName());
            }
            String divisionName = PI.getDivision();
            if (divisionName != null) {
                profile.setDivisionName(divisionName);
            }
            if (PI.getEraCommonsUserName() != null) {
                profile.setCredential(PI.getEraCommonsUserName());
            }
            profile.setProjectRole(ProjectRoleDataType.PD_PI);

            PersonProfileDataType.Profile.BioSketchsAttached personBioSketch = PersonProfileDataType.Profile.BioSketchsAttached.Factory
                    .newInstance();
            AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, PI.getPersonId(), PI.getRolodexId(),
                    BIOSKETCH_TYPE);
            personBioSketch.setBioSketchAttached(bioSketchAttachment);
            profile.setBioSketchsAttached(personBioSketch);

            AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, PI.getPersonId(), PI.getRolodexId(),
                    CURRENT_PENDING_TYPE);
            if (supportAttachment != null) {
                PersonProfileDataType.Profile.SupportsAttached supportsAttached = PersonProfileDataType.Profile.SupportsAttached.Factory
                        .newInstance();
                supportsAttached.setSupportAttached(supportAttachment);
                profile.setSupportsAttached(supportsAttached);
            }
            profileDataType.setProfile(profile);
        }
        return profileDataType;
    }

    /**
     * 
     * This method returns an array of PersonProfileDataType which contains the PersonProfile details and corresponding attachments
     * for a particular Key person. The PersonProfileDataType array will have maximum of 39 key persons.
     * 
     * @return personProfileDataTypeArray(PersonProfileDataType[]) array of person profiles
     */
    private PersonProfileDataType[] getpersonProfileKeyPerson() {

        List<PersonProfileDataType> personProfileDataTypeList = new ArrayList<PersonProfileDataType>();
        List<? extends ProposalPersonContract> keyPersons = pdDoc.getDevelopmentProposal().getProposalPersons();
        Collections.sort(keyPersons, new ProposalPersonComparator());
        List<ProposalPersonContract> nKeyPersons = s2SProposalPersonService.getNKeyPersons(keyPersons, true, MAX_KEY_PERSON_COUNT);
        extraPersons = s2SProposalPersonService.getNKeyPersons(keyPersons, false, MAX_KEY_PERSON_COUNT);
        if (nKeyPersons.size() > 0) {
            for (ProposalPersonContract keyPerson : nKeyPersons) {
                if (pIPersonOrRolodexId != null) {
                    // Don't add PI to keyperson list
                    if (keyPerson.getPersonId() != null && keyPerson.getPersonId().equals(pIPersonOrRolodexId)) {
                        continue;
                    }
                    else if ((keyPerson.getRolodexId() != null) && pIPersonOrRolodexId.equals(keyPerson.getRolodexId().toString())) {
                        continue;
                    }
                }
                Profile profileKeyPerson = Profile.Factory.newInstance();
                profileKeyPerson.setName(globLibV10Generator.getHumanNameDataType(keyPerson));
                if (keyPerson.getDirectoryTitle() != null) {
                    if (keyPerson.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
                        profileKeyPerson.setTitle(keyPerson.getDirectoryTitle().substring(0, DIRECTORY_TITLE_MAX_LENGTH));
                    }
                    else {
                        profileKeyPerson.setTitle(keyPerson.getDirectoryTitle());
                    }
                }
                profileKeyPerson.setAddress(globLibV10Generator.getAddressRequireCountryDataType(keyPerson));
                profileKeyPerson.setPhone(keyPerson.getOfficePhone());
                if (keyPerson.getFaxNumber() != null) {
                    profileKeyPerson.setFax(keyPerson.getFaxNumber());
                }
                profileKeyPerson.setEmail(keyPerson.getEmailAddress());
                profileKeyPerson.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());               
                if(keyPerson.getHomeUnit() != null) {
                    KcPersonContract kcPerson = keyPerson.getPerson();
                    String departmentName =  kcPerson.getOrganizationIdentifier();
                    profileKeyPerson.setDepartmentName(departmentName);
                }
                else
                {
                    DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
                    profileKeyPerson.setDepartmentName(developmentProposal.getOwnedByUnit().getUnitName());
                }
                String divisionName = keyPerson.getDivision();
                if (divisionName != null) {
                    profileKeyPerson.setDivisionName(divisionName);
                }
                if (keyPerson.getEraCommonsUserName() != null) {
                    profileKeyPerson.setCredential(keyPerson.getEraCommonsUserName());
                }
                if (keyPerson.isMultiplePi() || keyPerson.isCoInvestigator()) {
                    if(sponsorHierarchyService.isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())){
                        if (keyPerson.isMultiplePi()) {
                            profileKeyPerson.setProjectRole(ProjectRoleDataType.PD_PI);
                        } else {
                            profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
                        }
                    }else{
                        profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
                    }
                } else {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.OTHER_SPECIFY);
                    OtherProjectRoleCategory otherProjectRole = OtherProjectRoleCategory.Factory.newInstance();
                    String otherRole;
                    if (keyPerson.getProjectRole() != null) {
                        if (keyPerson.getProjectRole().length() > ROLE_DESCRIPTION_MAX_LENGTH) {
                            otherRole = keyPerson.getProjectRole().substring(0, ROLE_DESCRIPTION_MAX_LENGTH);
                        }
                        else {
                            otherRole = keyPerson.getProjectRole();
                        }
                    }
                    else {
                        otherRole = FieldValueConstants.VALUE_UNKNOWN;
                    }
                    otherProjectRole.setStringValue(otherRole);
                    profileKeyPerson.setOtherProjectRoleCategory(otherProjectRole);
                }

                PersonProfileDataType.Profile.BioSketchsAttached personBioSketch = PersonProfileDataType.Profile.BioSketchsAttached.Factory
                        .newInstance();
                AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, keyPerson.getPersonId(), keyPerson
                        .getRolodexId(), BIOSKETCH_TYPE);
                personBioSketch.setBioSketchAttached(bioSketchAttachment);
                profileKeyPerson.setBioSketchsAttached(personBioSketch);

                AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, keyPerson.getPersonId(), keyPerson
                        .getRolodexId(), CURRENT_PENDING_TYPE);
                if (supportAttachment != null) {
                    PersonProfileDataType.Profile.SupportsAttached supportsAttached = PersonProfileDataType.Profile.SupportsAttached.Factory
                            .newInstance();
                    supportsAttached.setSupportAttached(supportAttachment);
                    profileKeyPerson.setSupportsAttached(supportsAttached);
                }

                PersonProfileDataType personProfileDataTypeKeyPerson = PersonProfileDataType.Factory.newInstance();
                personProfileDataTypeKeyPerson.setProfile(profileKeyPerson);
                personProfileDataTypeList.add(personProfileDataTypeKeyPerson);
            }
        }
        PersonProfileDataType[] personProfileDataArray = new PersonProfileDataType[0];
        personProfileDataArray = personProfileDataTypeList.toArray(personProfileDataArray);
        return personProfileDataArray;
    }

    /**
     * This method creates {@link XmlObject} of type {@link RRKeyPersonExpandedDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRRKeyPersonExpanded();
    }

    public SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }

    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
