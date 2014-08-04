/*
 * Copyright 2005-2013 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.rrKeyPersonExpanded20V20.PersonProfileDataType;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.PersonProfileDataType.Profile;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.PersonProfileDataType.Profile.OtherProjectRoleCategory;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.ProjectRoleDataType;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.RRKeyPersonExpanded20Document;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.RRKeyPersonExpanded20Document.RRKeyPersonExpanded20;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.RRKeyPersonExpanded20Document.RRKeyPersonExpanded20.AdditionalProfilesAttached;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.RRKeyPersonExpanded20Document.RRKeyPersonExpanded20.BioSketchsAttached;
import gov.grants.apply.forms.rrKeyPersonExpanded20V20.RRKeyPersonExpanded20Document.RRKeyPersonExpanded20.SupportsAttached;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonDegreeContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.api.core.AuditError;
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
 * This class generates RRKeyPersonExpanded xml object. It uses xmlbeans for
 * generation of the form. Form is generated based on RRKeyPersonExpanded
 * version 2.0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("RRKeyPersonExpandedV2_0Generator")
public class RRKeyPersonExpandedV2_0Generator extends
		RRKeyPersonExpandedBaseGenerator {

    private static final int MAX_KEY_PERSON_COUNT = 100;

	RolodexContract rolodex;

    @Value("http://apply.grants.gov/forms/RR_KeyPersonExpanded_2_0-V2.0")
    private String namespace;

    @Value("RR_KeyPersonExpanded_2_0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/RR_KeyPersonExpanded-V2.0.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrKeyPersonExpanded20V20")
    private String packageName;

    @Value("155")
    private int sortIndex;

    @Autowired
    @Qualifier("rolodexService")
	private RolodexService rolodexService;

	/*
	 * This method gives details of Principal Investigator,KeyPersons and the
	 * corresponding attachments for RRKeyPersons
	 * 
	 */
	private RRKeyPersonExpanded20Document getRRKeyPersonExpanded() {
		RRKeyPersonExpanded20Document rrKeyPersonExpandedDocument = RRKeyPersonExpanded20Document.Factory
				.newInstance();
		RRKeyPersonExpanded20 rrKeyPersonExpanded = RRKeyPersonExpanded20.Factory
				.newInstance();
		setRRKeyPersonExpandedAttributes(rrKeyPersonExpanded);
		rrKeyPersonExpandedDocument
				.setRRKeyPersonExpanded20(rrKeyPersonExpanded);
		return rrKeyPersonExpandedDocument;
	}

	/*
	 * This method is used to set all attributes of
	 * RRkeyPersonExpandedAttributes object
	 */
	private void setRRKeyPersonExpandedAttributes(
			RRKeyPersonExpanded20 rrKeyPersonExpanded) {
		rrKeyPersonExpanded.setFormVersion(FormVersion.v2_0.getVersion());
		rrKeyPersonExpanded.setPDPI(getPersonProfilePI());
		PersonProfileDataType[] keyPersonArray = getpersonProfileKeyPerson();
		if (keyPersonArray.length > 0) {
			rrKeyPersonExpanded.setKeyPersonArray(keyPersonArray);
		}
		saveKeyPersonAttachmentsToProposal();
		if (extraPersons.size() > 0) {
			for (ProposalPersonContract extraPerson : extraPersons) {
				setBioSketchAttchment(rrKeyPersonExpanded, extraPerson);
				setCurrentPendingTypeAttachment(rrKeyPersonExpanded,
						extraPerson);
			}
			for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
					.getNarratives()) {
				if (narrative.getNarrativeType().getCode() != null) {
					if (Integer.parseInt(narrative.getNarrativeType().getCode()) == PROFILE_TYPE) {
						setProfileTypeAttachment(rrKeyPersonExpanded, narrative);
					}
				}
			}
		}
	}

	/*
	 * This method is used to add profile type attachment to rrKeyPersonExpanded
	 */
	private void setProfileTypeAttachment(
			RRKeyPersonExpanded20 rrKeyPersonExpanded, NarrativeContract narrative) {
		AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
		if(attachedFileDataType != null){
			AdditionalProfilesAttached additionalProfilesAttached = AdditionalProfilesAttached.Factory
					.newInstance();
			additionalProfilesAttached
					.setAdditionalProfileAttached(attachedFileDataType);
			rrKeyPersonExpanded
				.setAdditionalProfilesAttached(additionalProfilesAttached);
		}
	}

	/*
	 * This method is used to add current type pending attachment to
	 * rrKeyPersonExpanded
	 */
	private void setCurrentPendingTypeAttachment(
			RRKeyPersonExpanded20 rrKeyPersonExpanded,
			ProposalPersonContract extraPerson) {
		AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc,
				extraPerson.getPersonId(), extraPerson.getRolodexId(),
				CURRENT_PENDING_TYPE);
		if (supportAttachment != null) {
			SupportsAttached supportsAttached = SupportsAttached.Factory
					.newInstance();
			supportsAttached.setSupportAttached(supportAttachment);
			rrKeyPersonExpanded.setSupportsAttached(supportsAttached);
		}
	}

	/*
	 * This method is used to add biosketch type attachment to
	 * rrKeyPersonExpanded
	 */
	private void setBioSketchAttchment(
			RRKeyPersonExpanded20 rrKeyPersonExpanded,
			ProposalPersonContract extraPerson) {
		BioSketchsAttached 
		    personBioSketch =  BioSketchsAttached.Factory.newInstance();
		AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(
				pdDoc, extraPerson.getPersonId(), extraPerson.getRolodexId(),
				BIOSKETCH_TYPE);
		personBioSketch.setBioSketchAttached(bioSketchAttachment);
		rrKeyPersonExpanded.setBioSketchsAttached(personBioSketch);
	}

	/*
	 * This method is used to get PersonProfile details of Principal
	 * Investigator.It also gives the information about the attachments related
	 * to the principal investigator.
	 * 
	 */
	private PersonProfileDataType getPersonProfilePI() {
		PersonProfileDataType profileDataType = PersonProfileDataType.Factory
				.newInstance();
		Profile profile = Profile.Factory.newInstance();
		ProposalPersonContract PI = s2SProposalPersonService.getPrincipalInvestigator(pdDoc);
		if (PI != null) {
			setPersonalProfileDetailsToProfile(profileDataType, profile, PI);
		}
		return profileDataType;
	}

	/*
	 * This method is used to add Person Profile details of Principal
	 * Investigator and attachments to profile
	 */
	private void setPersonalProfileDetailsToProfile(
			PersonProfileDataType profileDataType, Profile profile,
			ProposalPersonContract PI) {
		assignRolodexId(PI);
		profile.setName(globLibV20Generator.getHumanNameDataType(PI));
		setDirectoryTitleToProfile(profile, PI);
		profile.setAddress(globLibV20Generator.getAddressDataType(PI));
		profile.setPhone(PI.getOfficePhone());
		if (PI.getFaxNumber() != null) {
			profile.setFax(PI.getFaxNumber());
		}
		if (PI.getDegree() != null) {
			profile.setDegreeType(PI.getDegree());
		}
		if (PI.getYearGraduated() != null) {
			profile.setDegreeYear(PI.getYearGraduated());
		}		
		if(PI.getDegree() == null && PI.getYearGraduated() == null ){		    
		   if(PI.getProposalPersonDegrees() != null && PI.getProposalPersonDegrees().size() > 0){
		       ProposalPersonDegreeContract proposalPersonDegree = PI.getProposalPersonDegrees().get(0);
		       if(proposalPersonDegree != null){  
		           if(proposalPersonDegree.getDegreeType() != null && proposalPersonDegree.getDegreeType().getDescription()!= null)
		               profile.setDegreeType(proposalPersonDegree.getDegreeType().getDescription());
		           if(proposalPersonDegree.getGraduationYear() != null)
		               profile.setDegreeYear(proposalPersonDegree.getGraduationYear());
		       }		   
		   }		
		}
		profile.setEmail(PI.getEmailAddress());
		DevelopmentProposalContract developmentProposal = pdDoc
				.getDevelopmentProposal();
		setOrganizationName(profile, developmentProposal);
		setDepartmentNameToProfile(profile,PI);
		String divisionName = PI.getDivision();
		if (divisionName != null) {
			profile.setDivisionName(divisionName);
		}
		if (PI.getEraCommonsUserName() != null) {
			profile.setCredential(PI.getEraCommonsUserName());
		} else {
            if (getSponsorHierarchyService().isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())) {
                getAuditErrors().add(new AuditError(AuditError.NO_FIELD_ERROR_KEY, ERROR_ERA_COMMON_USER_NAME + PI.getFullName(),
                        AuditError.GG_LINK));
            }
        }
		profile.setProjectRole(ProjectRoleDataType.PD_PI);
		setAttachments(profile, PI);
		profileDataType.setProfile(profile);
	}

	/*
	 * This method is used to add department name to profile
	 */
	private void setDepartmentNameToProfile(Profile profile, ProposalPersonContract PI) {
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
	}

	/*
	 * This method is used to add directory title to profile
	 */
	private void setDirectoryTitleToProfile(Profile profile, ProposalPersonContract PI) {
		if (PI.getDirectoryTitle() != null) {
			if (PI.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
				profile.setTitle(PI.getDirectoryTitle().substring(0,
						DIRECTORY_TITLE_MAX_LENGTH));
			} else {
				profile.setTitle(PI.getDirectoryTitle());
			}
		}
	}

	/*
	 * This method is used to assign rolodex id
	 */
	private void assignRolodexId(ProposalPersonContract PI) {
	    if (PI.getPersonId() != null) {
            pIPersonOrRolodexId = PI.getPersonId();
             rolodex = null;
        } else if (PI.getRolodexId() != null) {
            pIPersonOrRolodexId = PI.getRolodexId().toString();
            rolodex = rolodexService.getRolodex(Integer.valueOf(pIPersonOrRolodexId));
        }
	}

	/*
	 * This method is used to add attachments related to principle indicator
	 */
	private void setAttachments(Profile profile, ProposalPersonContract PI) {
		setBioSketchAttachment(profile, PI);
		setCurrentPendingAttachment(profile, PI);
	}

	/*
	 * This method is used to add the current pending type attachment to profile
	 */
	private void setCurrentPendingAttachment(Profile profile, ProposalPersonContract PI) {
		AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc,
				PI.getPersonId(), PI.getRolodexId(), CURRENT_PENDING_TYPE);
		if (supportAttachment != null) {
			PersonProfileDataType.Profile.SupportsAttached supportsAttached = PersonProfileDataType.Profile.SupportsAttached.Factory
					.newInstance();
			supportsAttached.setSupportAttached(supportAttachment);
			profile.setSupportsAttached(supportsAttached);
		}
	}

	/*
	 * This method is used to add the bioskectch attachment to profile
	 */
	private void setBioSketchAttachment(Profile profile, ProposalPersonContract PI) {
		PersonProfileDataType.Profile.BioSketchsAttached personBioSketch = PersonProfileDataType.Profile.BioSketchsAttached.Factory
				.newInstance();
		AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(
				pdDoc, PI.getPersonId(), PI.getRolodexId(), BIOSKETCH_TYPE);
		if (bioSketchAttachment != null) {
		    personBioSketch.setBioSketchAttached(bioSketchAttachment);
	        profile.setBioSketchsAttached(personBioSketch);
		}
	}

	/*
	 * This method returns an array of PersonProfileDataType which contains the
	 * PersonProfile details and corresponding attachments for a particular Key
	 * person. The PersonProfileDataType array will have maximum of 39 key
	 * persons.
	 * 
	 */
	private PersonProfileDataType[] getpersonProfileKeyPerson() {
		List<PersonProfileDataType> personProfileDataTypeList = new ArrayList<PersonProfileDataType>();
		DevelopmentProposalContract developmentProposal = pdDoc
				.getDevelopmentProposal();
		List<? extends ProposalPersonContract> keyPersons = developmentProposal
				.getProposalPersons();
		if (keyPersons != null) {
			Collections.sort(keyPersons, new ProposalPersonComparator());
		}
		List<ProposalPersonContract> nKeyPersons = s2SProposalPersonService.getNKeyPersons(
				keyPersons, true, MAX_KEY_PERSON_COUNT);
		extraPersons = s2SProposalPersonService.getNKeyPersons(keyPersons, false,
				MAX_KEY_PERSON_COUNT);
		if (nKeyPersons.size() > 0) {
			setKeyPersonToPersonProfileDataType(personProfileDataTypeList,
					nKeyPersons);
		}
		PersonProfileDataType[] personProfileDataArray = new PersonProfileDataType[0];
		personProfileDataArray = personProfileDataTypeList
				.toArray(personProfileDataArray);
		return personProfileDataArray;
	}

	/*
	 * This method is used to add key person to person profile data type
	 */
	private void setKeyPersonToPersonProfileDataType(
			List<PersonProfileDataType> personProfileDataTypeList,
			List<ProposalPersonContract> nKeyPersons) {
		for (ProposalPersonContract keyPerson : nKeyPersons) {
			if (pIPersonOrRolodexId != null) {
				// Don't add PI to keyperson list
				if (keyPerson.getPersonId() != null
						&& keyPerson.getPersonId().equals(pIPersonOrRolodexId)) {
					continue;
				} else if ((keyPerson.getRolodexId() != null)
						&& pIPersonOrRolodexId.equals(keyPerson.getRolodexId()
								.toString())) {
					continue;
				}
			}
			Profile profileKeyPerson = Profile.Factory.newInstance();
			setAllkeyPersonDetailsToKeyPerson(keyPerson, profileKeyPerson);
			setAttachments(profileKeyPerson, keyPerson);
			PersonProfileDataType personProfileDataTypeKeyPerson = PersonProfileDataType.Factory
					.newInstance();
			personProfileDataTypeKeyPerson.setProfile(profileKeyPerson);
			personProfileDataTypeList.add(personProfileDataTypeKeyPerson);
		}
	}

	/*
	 * This method is used to add all key person details to key person
	 */
	private void setAllkeyPersonDetailsToKeyPerson(ProposalPersonContract keyPerson,
            Profile profileKeyPerson) {
        assignRolodexId(keyPerson);
		profileKeyPerson.setName(globLibV20Generator
				.getHumanNameDataType(keyPerson));
		setDirectoryTitleToProfile(profileKeyPerson, keyPerson);
		profileKeyPerson.setAddress(globLibV20Generator
				.getAddressDataType(keyPerson));
		profileKeyPerson.setPhone(keyPerson.getOfficePhone());
		if (keyPerson.getFaxNumber() != null) {
			profileKeyPerson.setFax(keyPerson.getFaxNumber());
		}
		if (keyPerson.getDegree() != null) {
			profileKeyPerson.setDegreeType(keyPerson.getDegree());
		}
		if (keyPerson.getYearGraduated() != null) {
			profileKeyPerson.setDegreeYear(keyPerson.getYearGraduated());
		}
		if(keyPerson.getDegree() == null && keyPerson.getYearGraduated() == null ){
	          if(keyPerson.getProposalPersonDegrees() != null && keyPerson.getProposalPersonDegrees().size() > 0){
	              ProposalPersonDegreeContract proposalPersonDegree = keyPerson.getProposalPersonDegrees().get(0);
	              if(proposalPersonDegree != null){  
	                  if(proposalPersonDegree.getDegreeType() != null &&proposalPersonDegree.getDegreeType().getDescription() != null)
	                      profileKeyPerson.setDegreeType(proposalPersonDegree.getDegreeType().getDescription());
	                  if(proposalPersonDegree.getGraduationYear() != null)
	                      profileKeyPerson.setDegreeYear(proposalPersonDegree.getGraduationYear());
	              }	            
	          }  
	     }
		profileKeyPerson.setEmail(keyPerson.getEmailAddress());
		DevelopmentProposalContract developmentProposal = pdDoc
				.getDevelopmentProposal();
		setOrganizationName(profileKeyPerson, developmentProposal);
		setDepartmentNameToProfile(profileKeyPerson,keyPerson);
		String divisionName = keyPerson.getDivision();
		if (divisionName != null) {
			profileKeyPerson.setDivisionName(divisionName);
		}
		if (keyPerson.getEraCommonsUserName() != null) {
			profileKeyPerson.setCredential(keyPerson.getEraCommonsUserName());
		} else {
            if (getSponsorHierarchyService().isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())) {
                if (keyPerson.isMultiplePi()) {
                    getAuditErrors().add(new AuditError(AuditError.NO_FIELD_ERROR_KEY, ERROR_ERA_COMMON_USER_NAME + keyPerson.getFullName(),
                            AuditError.GG_LINK));
                }
            }
        }
        if (keyPerson.isMultiplePi() || keyPerson.isCoInvestigator()) {
            if(getSponsorHierarchyService().isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())){
                if (keyPerson.isMultiplePi()) {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.PD_PI);
                } else {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_INVESTIGATOR);
                }
            }else{
                profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
            }
        } else {
			setProjectRoleCategoryToProfile(keyPerson, profileKeyPerson);
		}
	}

	private void setOrganizationName(Profile profileKeyPerson,
			DevelopmentProposalContract developmentProposal) {
		if (developmentProposal.getApplicantOrganization() != null
				&& developmentProposal.getApplicantOrganization()
						.getOrganization() != null) {
		    if (rolodex != null){
                profileKeyPerson.setOrganizationName(rolodex.getOrganization());
            }
            else
                profileKeyPerson.setOrganizationName(developmentProposal
                    .getApplicantOrganization().getOrganization()
                    .getOrganizationName());
		}
	}

	/*
	 * This method is used to add project role category to profile
	 */
	private void setProjectRoleCategoryToProfile(ProposalPersonContract keyPerson,
			Profile profileKeyPerson) {
	    if (keyPerson.getRolodexId() == null) {
	        profileKeyPerson.setProjectRole(ProjectRoleDataType.OTHER_SPECIFY);
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
	            otherRole = FieldValueConstants.VALUE_UNKNOWN;
	        }
	        otherProjectRole.setStringValue(otherRole);
	        profileKeyPerson.setOtherProjectRoleCategory(otherProjectRole);
	    } else {
            profileKeyPerson.setProjectRole(ProjectRoleDataType.PD_PI);
        }
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link RRKeyPersonExpanded20Document} by populating data from the given
	 * {@link ProposalDevelopmentDocumentContract}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocumentContract}
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getRRKeyPersonExpanded();
	}

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
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
