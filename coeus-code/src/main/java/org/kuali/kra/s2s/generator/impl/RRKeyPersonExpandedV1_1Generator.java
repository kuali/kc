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
package org.kuali.kra.s2s.generator.impl;

import gov.grants.apply.forms.rrKeyPersonExpandedV11.PersonProfileDataType;
import gov.grants.apply.forms.rrKeyPersonExpandedV11.PersonProfileDataType.Profile;
import gov.grants.apply.forms.rrKeyPersonExpandedV11.PersonProfileDataType.Profile.OtherProjectRoleCategory;
import gov.grants.apply.forms.rrKeyPersonExpandedV11.ProjectRoleDataType;
import gov.grants.apply.forms.rrKeyPersonExpandedV11.RRKeyPersonExpandedDocument;
import gov.grants.apply.forms.rrKeyPersonExpandedV11.RRKeyPersonExpandedDocument.RRKeyPersonExpanded;
import gov.grants.apply.forms.rrKeyPersonExpandedV11.RRKeyPersonExpandedDocument.RRKeyPersonExpanded.AdditionalProfilesAttached;
import gov.grants.apply.forms.rrKeyPersonExpandedV11.RRKeyPersonExpandedDocument.RRKeyPersonExpanded.BioSketchsAttached;
import gov.grants.apply.forms.rrKeyPersonExpandedV11.RRKeyPersonExpandedDocument.RRKeyPersonExpanded.SupportsAttached;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.sponsor.SponsorService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonComparator;
import org.kuali.kra.s2s.util.AuditError;
import org.kuali.kra.s2s.util.S2SConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class generates RRKeyPersonExpanded xml object. It uses xmlbeans for
 * generation of the form. Form is generated based on RRKeyPersonExpanded
 * schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RRKeyPersonExpandedV1_1Generator extends
		RRKeyPersonExpandedBaseGenerator {

	/**
	 * 
	 * This method gives details of Principal Investigator,KeyPersons and the
	 * corresponding attachments for RRKeyPersons
	 * 
	 * @return rrKeyPersonExpandedDocument {@link XmlObject} of type
	 *         RRKeyPersonExpandedDocument.
	 */
	private RRKeyPersonExpandedDocument getRRKeyPersonExpanded() {

		RRKeyPersonExpandedDocument rrKeyPersonExpandedDocument = RRKeyPersonExpandedDocument.Factory
				.newInstance();
		RRKeyPersonExpanded rrKeyPersonExpanded = RRKeyPersonExpanded.Factory
				.newInstance();
		rrKeyPersonExpanded.setFormVersion(S2SConstants.FORMVERSION_1_1);
		rrKeyPersonExpanded.setPDPI(getPersonProfilePI());
		rrKeyPersonExpanded.setKeyPersonArray(getpersonProfileKeyPerson());
		saveKeyPersonAttachmentsToProposal();

		AttachedFileDataType attachedFileDataType = null;
        BioSketchsAttached bioSketchAttached = BioSketchsAttached.Factory.newInstance();
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null) {
				if (Integer.parseInt(narrative.getNarrativeTypeCode()) == BIOSKETCH_DOC_TYPE) {
					attachedFileDataType = getAttachedFileType(narrative);
					if (attachedFileDataType != null) {
						bioSketchAttached.setBioSketchAttached(attachedFileDataType);
						rrKeyPersonExpanded.setBioSketchsAttached(bioSketchAttached);
						break;
					}
				}
			}
		}
        
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null) {
				if (Integer.parseInt(narrative.getNarrativeTypeCode()) == CURRENTPENDING_DOC_TYPE) {
					attachedFileDataType = getAttachedFileType(narrative);
					if (attachedFileDataType != null) {
						SupportsAttached supportsAttached = SupportsAttached.Factory
								.newInstance();
						supportsAttached
								.setSupportAttached(attachedFileDataType);
						rrKeyPersonExpanded
								.setSupportsAttached(supportsAttached);
						break;
					}
				}
			}
		}
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null) {
				if (Integer.parseInt(narrative.getNarrativeTypeCode()) == PROFILE_TYPE) {
					attachedFileDataType = getAttachedFileType(narrative);
					if (attachedFileDataType != null) {
						AdditionalProfilesAttached additionalProfilesAttached = AdditionalProfilesAttached.Factory
								.newInstance();
						additionalProfilesAttached
								.setAdditionalProfileAttached(attachedFileDataType);
						rrKeyPersonExpanded
								.setAdditionalProfilesAttached(additionalProfilesAttached);
						break;
					}
				}
			}
		}
		rrKeyPersonExpandedDocument.setRRKeyPersonExpanded(rrKeyPersonExpanded);
		return rrKeyPersonExpandedDocument;
	}

	/**
	 * 
	 * This method is used to get PersonProfile details of Principal
	 * Investigator.It also gives the information about the attachments related
	 * to the principal investigator.
	 * 
	 * @return profileDataType(PersonProfileDataType) profile of PI
	 */
	private PersonProfileDataType getPersonProfilePI() {

		PersonProfileDataType profileDataType = PersonProfileDataType.Factory
				.newInstance();
		Profile profile = Profile.Factory.newInstance();
		ProposalPerson PI = s2sUtilService.getPrincipalInvestigator(pdDoc);
		if (PI != null) {
			if (PI.getPersonId() != null) {
				pIPersonOrRolodexId = PI.getPersonId();
			} else if (PI.getRolodexId() != null) {
				pIPersonOrRolodexId = PI.getRolodexId().toString();
			}
			profile.setName(globLibV20Generator.getHumanNameDataType(PI));
			if (PI.getDirectoryTitle() != null) {
				if (PI.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
					profile.setTitle(PI.getDirectoryTitle().substring(0,
							DIRECTORY_TITLE_MAX_LENGTH));
				} else {
					profile.setTitle(PI.getDirectoryTitle());
				}
			}
			profile.setAddress(globLibV20Generator.getAddressDataType(PI));
			profile.setPhone(PI.getOfficePhone());
			if (PI.getFaxNumber() != null) {
				profile.setFax(PI.getFaxNumber());
			}
			profile.setEmail(PI.getEmailAddress());
			if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
				profile.setOrganizationName(pdDoc.getDevelopmentProposal()
						.getApplicantOrganization().getOrganization()
						.getOrganizationName());
			}
			if(PI.getHomeUnit() != null) {
                KcPersonService kcPersonService = KcServiceLocator.getService(KcPersonService.class);
                KcPerson kcPersons = kcPersonService.getKcPersonByPersonId(PI.getPersonId());
                String departmentName =  kcPersons.getOrganizationIdentifier();
                profile.setDepartmentName(departmentName);
            }
            else
            {
                DevelopmentProposal developmentProposal = pdDoc.getDevelopmentProposal();
                profile.setDepartmentName(developmentProposal.getOwnedByUnit().getUnitName());
            }
			String divisionName = PI.getDivision();
			if (divisionName != null) {
				profile.setDivisionName(divisionName);
			}
			if (PI.getEraCommonsUserName() != null) {
				profile.setCredential(PI.getEraCommonsUserName());
			} else {
                if (KcServiceLocator.getService(SponsorService.class).isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal())) {
                    getAuditErrors().add(new AuditError(Constants.NO_FIELD, S2SConstants.ERROR_ERA_COMMON_USER_NAME + PI.getFullName(), 
                            Constants.GRANTS_GOV_PAGE + "." + Constants.GRANTS_GOV_PANEL_ANCHOR));             
                }
            }
			profile.setProjectRole(ProjectRoleDataType.PD_PI);

			PersonProfileDataType.Profile.BioSketchsAttached personBioSketch = PersonProfileDataType.Profile.BioSketchsAttached.Factory
					.newInstance();
			AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(
					pdDoc, PI.getPersonId(), PI.getRolodexId(), BIOSKETCH_TYPE);
			personBioSketch.setBioSketchAttached(bioSketchAttachment);
			profile.setBioSketchsAttached(personBioSketch);

			AttachedFileDataType supportAttachment = getPernonnelAttachments(
					pdDoc, PI.getPersonId(), PI.getRolodexId(),
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
	 * This method returns an array of PersonProfileDataType which contains the
	 * PersonProfile details and corresponding attachments for a particular Key
	 * person. The PersonProfileDataType array will have maximum of 39 key
	 * persons.
	 * 
	 * @return personProfileDataTypeArray(PersonProfileDataType[]) array of
	 *         person profiles
	 */
	private PersonProfileDataType[] getpersonProfileKeyPerson() {

		List<PersonProfileDataType> personProfileDataTypeList = new ArrayList<PersonProfileDataType>();
		List<ProposalPerson> keyPersons = pdDoc.getDevelopmentProposal()
				.getProposalPersons();
		Collections.sort(keyPersons, new ProposalPersonComparator());
		List<ProposalPerson> nKeyPersons = s2sUtilService.getNKeyPersons(
				keyPersons, true, MAX_KEY_PERSON_COUNT);
		extraPersons = s2sUtilService.getNKeyPersons(keyPersons, false,
				MAX_KEY_PERSON_COUNT);
		if (nKeyPersons.size() > 0) {
			for (ProposalPerson keyPerson : nKeyPersons) {
				if (pIPersonOrRolodexId != null) {
					// Don't add PI to keyperson list
					if (keyPerson.getPersonId() != null
							&& keyPerson.getPersonId().equals(
									pIPersonOrRolodexId)) {
						continue;
					} else if ((keyPerson.getRolodexId() != null)
							&& pIPersonOrRolodexId.equals(keyPerson
									.getRolodexId().toString())) {
						continue;
					}
				}
				Profile profileKeyPerson = Profile.Factory.newInstance();
				profileKeyPerson.setName(globLibV20Generator
						.getHumanNameDataType(keyPerson));
				if (keyPerson.getDirectoryTitle() != null) {
					if (keyPerson.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
						profileKeyPerson.setTitle(keyPerson.getDirectoryTitle()
								.substring(0, DIRECTORY_TITLE_MAX_LENGTH));
					} else {
						profileKeyPerson
								.setTitle(keyPerson.getDirectoryTitle());
					}
				}
				profileKeyPerson.setAddress(globLibV20Generator
						.getAddressDataType(keyPerson));
				profileKeyPerson.setPhone(keyPerson.getOfficePhone());
				if (keyPerson.getFaxNumber() != null) {
					profileKeyPerson.setFax(keyPerson.getFaxNumber());
				}
				profileKeyPerson.setEmail(keyPerson.getEmailAddress());
				if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
					profileKeyPerson.setOrganizationName(pdDoc
							.getDevelopmentProposal()
							.getApplicantOrganization().getOrganization()
							.getOrganizationName());
				}
				if(keyPerson.getHomeUnit() != null) {
                    KcPersonService kcPersonService = KcServiceLocator.getService(KcPersonService.class);
                    KcPerson kcPersons = kcPersonService.getKcPersonByPersonId(keyPerson.getPersonId());
                    String departmentName =  kcPersons.getOrganizationIdentifier();
                    profileKeyPerson.setDepartmentName(departmentName);
                }
                else
                {
                    DevelopmentProposal developmentProposal = pdDoc.getDevelopmentProposal();
                    profileKeyPerson.setDepartmentName(developmentProposal.getOwnedByUnit().getUnitName());
                }
				String divisionName = keyPerson.getDivision();
				if (divisionName != null) {
					profileKeyPerson.setDivisionName(divisionName);
				}
				if (keyPerson.getEraCommonsUserName() != null) {
					profileKeyPerson.setCredential(keyPerson
							.getEraCommonsUserName());
				} else {
	                if (KcServiceLocator.getService(SponsorService.class).isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal())) {
	                    if (keyPerson.isMultiplePi()) {
	                        getAuditErrors().add(new AuditError(Constants.NO_FIELD, S2SConstants.ERROR_ERA_COMMON_USER_NAME + keyPerson.getFullName(),
	                                Constants.GRANTS_GOV_PAGE + "." + Constants.GRANTS_GOV_PANEL_ANCHOR));             
	                    }
	                }
	            }
                if (keyPerson.getProposalPersonRoleId().equals(CO_INVESTIGATOR)) {
                    if(KcServiceLocator.getService(SponsorService.class).isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal())){
                        if (keyPerson.isMultiplePi()) {
                            profileKeyPerson.setProjectRole(ProjectRoleDataType.PD_PI);
                        } else {
                            profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
                        }
                    }else{
                        profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
                    }
                } else {
					profileKeyPerson
							.setProjectRole(ProjectRoleDataType.OTHER_SPECIFY);
					OtherProjectRoleCategory otherProjectRole = OtherProjectRoleCategory.Factory
							.newInstance();
					String otherRole;
					if (keyPerson.getProjectRole() != null) {
						if (keyPerson.getProjectRole().length() > ROLE_DESCRIPTION_MAX_LENGTH) {
							otherRole = keyPerson.getProjectRole().substring(0,
									ROLE_DESCRIPTION_MAX_LENGTH);
						} else {
							otherRole = keyPerson.getProjectRole();
						}
					} else {
						otherRole = S2SConstants.VALUE_UNKNOWN;
					}
					otherProjectRole.setStringValue(otherRole);
					profileKeyPerson
							.setOtherProjectRoleCategory(otherProjectRole);
				}

				PersonProfileDataType.Profile.BioSketchsAttached personBioSketch = PersonProfileDataType.Profile.BioSketchsAttached.Factory
						.newInstance();
				AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(
						pdDoc, keyPerson.getPersonId(), keyPerson
								.getRolodexId(), BIOSKETCH_TYPE);
				personBioSketch.setBioSketchAttached(bioSketchAttachment);
				profileKeyPerson.setBioSketchsAttached(personBioSketch);

				AttachedFileDataType supportAttachment = getPernonnelAttachments(
						pdDoc, keyPerson.getPersonId(), keyPerson
								.getRolodexId(), CURRENT_PENDING_TYPE);
				if (supportAttachment != null) {
					PersonProfileDataType.Profile.SupportsAttached supportsAttached = PersonProfileDataType.Profile.SupportsAttached.Factory
							.newInstance();
					supportsAttached.setSupportAttached(supportAttachment);
					profileKeyPerson.setSupportsAttached(supportsAttached);
				}

				PersonProfileDataType personProfileDataTypeKeyPerson = PersonProfileDataType.Factory
						.newInstance();
				personProfileDataTypeKeyPerson.setProfile(profileKeyPerson);
				personProfileDataTypeList.add(personProfileDataTypeKeyPerson);
			}
		}
		PersonProfileDataType[] personProfileDataArray = new PersonProfileDataType[0];
		personProfileDataArray = personProfileDataTypeList
				.toArray(personProfileDataArray);
		return personProfileDataArray;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link RRKeyPersonExpandedDocument} by populating data from the given
	 * {@link ProposalDevelopmentDocument}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocument}
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocument proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getRRKeyPersonExpanded();
	}

	/**
	 * This method typecasts the given {@link XmlObject} to the required
	 * generator type and returns back the document of that generator type.
	 * 
	 * @param xmlObject
	 *            which needs to be converted to the document type of the
	 *            required generator
	 * @return {@link XmlObject} document of the required generator type
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
	 */
	public XmlObject getFormObject(XmlObject xmlObject) {
		RRKeyPersonExpanded rrKeyPersonExpanded = (RRKeyPersonExpanded) xmlObject;
		RRKeyPersonExpandedDocument rrKeyPersonExpandedDocument = RRKeyPersonExpandedDocument.Factory
				.newInstance();
		rrKeyPersonExpandedDocument.setRRKeyPersonExpanded(rrKeyPersonExpanded);
		return rrKeyPersonExpandedDocument;
	}

}
