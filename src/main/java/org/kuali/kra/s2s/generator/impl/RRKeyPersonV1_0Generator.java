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

import gov.grants.apply.forms.rrKeyPersonV10.PersonProfileDataType;
import gov.grants.apply.forms.rrKeyPersonV10.ProjectRoleDataType;
import gov.grants.apply.forms.rrKeyPersonV10.RRKeyPersonDocument;
import gov.grants.apply.forms.rrKeyPersonV10.PersonProfileDataType.Profile;
import gov.grants.apply.forms.rrKeyPersonV10.PersonProfileDataType.Profile.OtherProjectRoleCategory;
import gov.grants.apply.forms.rrKeyPersonV10.RRKeyPersonDocument.RRKeyPerson;
import gov.grants.apply.forms.rrKeyPersonV10.RRKeyPersonDocument.RRKeyPerson.AdditionalProfilesAttached;
import gov.grants.apply.forms.rrKeyPersonV10.RRKeyPersonDocument.RRKeyPerson.BioSketchsAttached;
import gov.grants.apply.forms.rrKeyPersonV10.RRKeyPersonDocument.RRKeyPerson.SupportsAttached;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonComparator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov RRKeyPersonV1.0. Form is generated using XMLBean classes and is based on
 * RRKeyPerson schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RRKeyPersonV1_0Generator extends RRKeyPersonBaseGenerator {

    /**
     * 
     * This method gives details of Principal Investigator,KeyPersons and the corresponding attachments for RRKeyPerson
     * 
     * @return rrKeyPersonDocument {@link XmlObject} of type RRKeyPersonDocument.
     */
    private RRKeyPersonDocument getRRKeyPerson() {

        RRKeyPersonDocument rrKeyPersonDocument = RRKeyPersonDocument.Factory.newInstance();
        RRKeyPerson rrKeyPerson = RRKeyPerson.Factory.newInstance();
        rrKeyPerson.setFormVersion(S2SConstants.FORMVERSION_1_0);
        rrKeyPerson.setPDPI(getPersonProfilePI());
        rrKeyPerson.setKeyPersonArray(getPersonProfileKeyPerson());

        if (extraPersons.size() > 0) {
            for (ProposalPerson extraPerson : extraPersons) {
                BioSketchsAttached personBioSketch = BioSketchsAttached.Factory.newInstance();
                AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, extraPerson.getPersonId(), extraPerson
                        .getRolodexId(), BIOSKETCH_TYPE);
                personBioSketch.setBioSketchAttached(bioSketchAttachment);
                rrKeyPerson.setBioSketchsAttached(personBioSketch);

                AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, extraPerson.getPersonId(), extraPerson
                        .getRolodexId(), CURRENT_PENDING_TYPE);
                if (supportAttachment != null) {
                    SupportsAttached supportsAttached = SupportsAttached.Factory.newInstance();
                    supportsAttached.setSupportAttached(supportAttachment);
                    rrKeyPerson.setSupportsAttached(supportsAttached);
                }
            }

            for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                if (narrative.getNarrativeTypeCode() != null) {
                    if (Integer.parseInt(narrative.getNarrativeTypeCode()) == PROFILE_TYPE) {
                        AdditionalProfilesAttached additionalProfilesAttached = AdditionalProfilesAttached.Factory.newInstance();
                        additionalProfilesAttached.setAdditionalProfileAttached(getAttachedFileType(narrative));
                        rrKeyPerson.setAdditionalProfilesAttached(additionalProfilesAttached);
                    }
                }
            }
        }
        rrKeyPersonDocument.setRRKeyPerson(rrKeyPerson);
        return rrKeyPersonDocument;
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
        PersonProfileDataType.Profile profile = PersonProfileDataType.Profile.Factory.newInstance();
        ProposalPerson PI = s2sUtilService.getPrincipalInvestigator(pdDoc);
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
            String departmentName = null;
            if (pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
                departmentName = pdDoc.getDevelopmentProposal().getOwnedByUnit().getUnitName();
                if (departmentName != null) {
                    if (departmentName.length() > DEPARTMENT_NAME_MAX_LENGTH) {
                        profile.setDepartmentName(departmentName.substring(0, DEPARTMENT_NAME_MAX_LENGTH - 1));
                    }
                    else {
                        profile.setDepartmentName(departmentName);
                    }
                }
            }
            String divisionName = s2sUtilService.getDivisionName(pdDoc);
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
     * for a particular Key person. The PersonProfileDataType array will have maximum of 7 key persons.
     * 
     * @return personProfileDataTypeArray(PersonProfileDataType[]) array of person profiles
     */
    private PersonProfileDataType[] getPersonProfileKeyPerson() {
        List<PersonProfileDataType> personProfileDataTypeList = new ArrayList<PersonProfileDataType>();
        List<ProposalPerson> keyPersons = pdDoc.getDevelopmentProposal().getProposalPersons();
        Collections.sort(keyPersons, new ProposalPersonComparator());

        List<ProposalPerson> nKeyPersons = s2sUtilService.getNKeyPersons(keyPersons, true, MAX_KEY_PERSON_COUNT);
        extraPersons = s2sUtilService.getNKeyPersons(keyPersons, false, MAX_KEY_PERSON_COUNT);
        if (nKeyPersons.size() > 0) {
            for (ProposalPerson keyPerson : nKeyPersons) {
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
                    profileKeyPerson.setTitle(keyPerson.getDirectoryTitle());
                }
                profileKeyPerson.setAddress(globLibV10Generator.getAddressRequireCountryDataType(keyPerson));
                profileKeyPerson.setPhone(keyPerson.getOfficePhone());
                if (keyPerson.getFaxNumber() != null) {
                    profileKeyPerson.setFax(keyPerson.getFaxNumber());
                }
                profileKeyPerson.setEmail(keyPerson.getEmailAddress());
                if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
                    profileKeyPerson.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());
                }
                String departmentName = null;
                if (pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
                    departmentName = pdDoc.getDevelopmentProposal().getOwnedByUnit().getUnitName();
                    if (departmentName != null) {
                        if (departmentName.length() > DEPARTMENT_NAME_MAX_LENGTH) {
                            profileKeyPerson.setDepartmentName(departmentName.substring(0, DEPARTMENT_NAME_MAX_LENGTH - 1));
                        }
                        else {
                            profileKeyPerson.setDepartmentName(departmentName);
                        }
                    }
                }
                String divisionName = s2sUtilService.getDivisionName(pdDoc);
                if (divisionName != null) {
                    profileKeyPerson.setDivisionName(divisionName);
                }
                if (keyPerson.getEraCommonsUserName() != null) {
                    profileKeyPerson.setCredential(keyPerson.getEraCommonsUserName());
                }
                if (keyPerson.getProposalPersonRoleId().equals(CO_INVESTIGATOR)) {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
                }
                else {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.OTHER_SPECIFY);
                    OtherProjectRoleCategory otherProjectRole = OtherProjectRoleCategory.Factory.newInstance();
                    String otherRole;
                    if (keyPerson.getRole().getDescription() != null) {
                        if (keyPerson.getRole().getDescription().length() > ROLE_DESCRIPTION_MAX_LENGTH) {
                            otherRole = keyPerson.getRole().getDescription().substring(0, ROLE_DESCRIPTION_MAX_LENGTH);
                        }
                        else {
                            otherRole = keyPerson.getRole().getDescription();
                        }
                    }
                    else {
                        otherRole = S2SConstants.VALUE_UNKNOWN;
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

                PersonProfileDataType personProfileDataTypeKeyperson = PersonProfileDataType.Factory.newInstance();
                personProfileDataTypeKeyperson.setProfile(profileKeyPerson);
                personProfileDataTypeList.add(personProfileDataTypeKeyperson);
            }
        }
        PersonProfileDataType[] personProfileDataTypeArray = new PersonProfileDataType[0];
        personProfileDataTypeArray = personProfileDataTypeList.toArray(personProfileDataTypeArray);
        return personProfileDataTypeArray;
    }

    /**
     * This method creates {@link XmlObject} of type {@link RRKeyPersonDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {

        this.pdDoc = proposalDevelopmentDocument;
        return getRRKeyPerson();
    }

    /**
     * This method type casts the given {@link XmlObject} to the required generator type and returns back the document of that
     * generator type.
     * 
     * @param xmlObject which needs to be converted to the document type of the required generator
     * @return {@link XmlObject} document of the required generator type
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
     */
    public XmlObject getFormObject(XmlObject xmlObject) {

        RRKeyPerson rrKeyPerson = (RRKeyPerson) xmlObject;
        RRKeyPersonDocument rrKeyPersonDocument = RRKeyPersonDocument.Factory.newInstance();
        rrKeyPersonDocument.setRRKeyPerson(rrKeyPerson);
        return rrKeyPersonDocument;
    }
}
