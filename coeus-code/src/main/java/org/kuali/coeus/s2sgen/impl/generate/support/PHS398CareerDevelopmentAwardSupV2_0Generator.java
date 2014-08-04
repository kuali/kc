/*
 * Copyright 2005-2013 The Kuali Foundation.
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

import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.CitizenshipDataType;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.CitizenshipDataType.Enum;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.CandidateBackground;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.CareerDevelopmentAndTrainingActivities;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.CareerGoalsAndObjectives;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.ConsortiumContractualArrangements;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.InclusionOfChildren;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.InclusionOfWomenAndMinorities;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.InsitutionalEnvironment;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.InstitutionalCommitment;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.IntroductionToApplication;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.LettersOfSupport;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.MentoringPlan;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.ProtectionOfHumanSubjects;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.ResearchStrategy;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.ResourceSharingPlans;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.ResponsibleConductOfResearch;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.SelectAgentResearch;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.SpecificAims;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.ProgressReportPublicationList;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.StatementsOfSupport;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20.PHS398CareerDevelopmentAwardSup20Document.PHS398CareerDevelopmentAwardSup20.CareerDevelopmentAwardAttachments.VertebrateAnimals;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.s2sgen.impl.citizenship.CitizenshipType;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.generate.support.PHS398CareerDevelopmentAwardSupBaseGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


/**
 * Class for generating the XML object for grants.gov
 * PHS398CareerDevelopmentAwardSup V2.0 Form is generated using XMLBean classes
 * and is based on PHS398ResearchPlanV2_0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("PHS398CareerDevelopmentAwardSupV2_0Generator")
public class PHS398CareerDevelopmentAwardSupV2_0Generator extends
		PHS398CareerDevelopmentAwardSupBaseGenerator {
    public static final int NARRATIVE_TYPE_PHS_CAREER_SUPPPORT_LTRS = 144;

    @Value("http://apply.grants.gov/forms/PHS398_CareerDevelopmentAwardSup_2_0-V2.0")
    private String namespace;

    @Value("PHS398_CareerDevelopmentAwardSup_2_0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/PHS398_CareerDevelopmentAwardSup-V2.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398CareerDevelopmentAwardSup20V20")
    private String packageName;

    @Value("200")
    private int sortIndex;

    private XmlObject getPHS398CareerDevelopmentAwardSup() {
	    PHS398CareerDevelopmentAwardSup20Document phs398CareerDevelopmentAwardSupDocument = PHS398CareerDevelopmentAwardSup20Document.Factory
	            .newInstance();
		PHS398CareerDevelopmentAwardSup20 phs398CareerDevelopmentAwardSup20 = PHS398CareerDevelopmentAwardSup20.Factory
				.newInstance();
		phs398CareerDevelopmentAwardSup20
				.setFormVersion(FormVersion.v2_0.getVersion());
		phs398CareerDevelopmentAwardSup20
				.setCitizenship(getCitizenshipDataType());
		phs398CareerDevelopmentAwardSup20
				.setCareerDevelopmentAwardAttachments(getCareerDevelopmentAwardAttachments());
		phs398CareerDevelopmentAwardSupDocument
				.setPHS398CareerDevelopmentAwardSup20(phs398CareerDevelopmentAwardSup20);
		return phs398CareerDevelopmentAwardSupDocument;
	}

	private Enum getCitizenshipDataType() {
	    for (ProposalPersonContract proposalPerson : pdDoc.getDevelopmentProposal().getProposalPersons()) {
			if (proposalPerson.isInvestigator()) {
				CitizenshipType citizenShip=getS2SProposalPersonService().getCitizenship(proposalPerson);
				if(citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA.toString())){
					return CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA;
				}
				else if(citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S.toString())){
					return CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S;
				}
				else if(citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL.toString())){
					return CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL;
				}
				else if(citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S_PENDING.toString())){
				    return CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S_PENDING;
                }
			}
	    }
		return CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S;
	}	

	/*
	 * This method fetches all attachments related to Career development award.
	 */
	private CareerDevelopmentAwardAttachments getCareerDevelopmentAwardAttachments() {
		CareerDevelopmentAwardAttachments careerDevelopmentAwardAttachments = CareerDevelopmentAwardAttachments.Factory
				.newInstance();
		AttachmentGroupMin0Max100DataType attachmentGroupMin0Max100DataType = AttachmentGroupMin0Max100DataType.Factory
				.newInstance();
		List<AttachedFileDataType> attachedFileList = new ArrayList<AttachedFileDataType>();
		AttachedFileDataType attachedFileDataType = null;
		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			int narrativeTypeCode = Integer.parseInt(narrative.getNarrativeType().getCode());
			switch (narrativeTypeCode) {
			case NARRATIVE_TYPE_INTRODUCTION_TO_APPLICATION:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				IntroductionToApplication introductionToApplication = IntroductionToApplication.Factory
						.newInstance();
				introductionToApplication
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setIntroductionToApplication(introductionToApplication);
				break;
			case NARRATIVE_TYPE_SPECIFIC_AIMS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				SpecificAims specificAims = SpecificAims.Factory.newInstance();
				specificAims.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setSpecificAims(specificAims);
				break;			
			case NARRATIVE_TYPE_PROGRESS_REPORT_PUBLICATION_LIST:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ProgressReportPublicationList progressReportPublicationList = ProgressReportPublicationList.Factory
						.newInstance();
				progressReportPublicationList
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setProgressReportPublicationList(progressReportPublicationList);
				break;
			case NARRATIVE_TYPE_PROTECTION_OF_HUMAN_SUBJECTS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null) {
	                continue;
	            }
				ProtectionOfHumanSubjects protectionOfHumanSubjects = ProtectionOfHumanSubjects.Factory
						.newInstance();
				protectionOfHumanSubjects
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setProtectionOfHumanSubjects(protectionOfHumanSubjects);
				break;
			case NARRATIVE_TYPE_INCLUSION_OF_WOMEN_AND_MINORITIES:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionOfWomenAndMinorities inclusionOfWomenAndMinorities = InclusionOfWomenAndMinorities.Factory
						.newInstance();
				inclusionOfWomenAndMinorities
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setInclusionOfWomenAndMinorities(inclusionOfWomenAndMinorities);
				break;			
			case NARRATIVE_TYPE_INCLUSION_OF_CHILDREN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionOfChildren inclusionOfChildren = InclusionOfChildren.Factory
						.newInstance();
				inclusionOfChildren.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setInclusionOfChildren(inclusionOfChildren);
				break;
			case NARRATIVE_TYPE_VERTEBRATE_ANIMALS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory
						.newInstance();
				vertebrateAnimals.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setVertebrateAnimals(vertebrateAnimals);
				break;
			case NARRATIVE_TYPE_SELECT_AGENT_RESEARCH:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				SelectAgentResearch selectAgentResearch = SelectAgentResearch.Factory
						.newInstance();
				selectAgentResearch.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setSelectAgentResearch(selectAgentResearch);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_CONSORTIUM_CONTRACT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ConsortiumContractualArrangements consortiumContractualArrangements = ConsortiumContractualArrangements.Factory
						.newInstance();
				consortiumContractualArrangements
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setConsortiumContractualArrangements(consortiumContractualArrangements);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_RESOURCE_SHARING_PLAN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResourceSharingPlans resourceSharingPlans = ResourceSharingPlans.Factory
						.newInstance();
				resourceSharingPlans.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setResourceSharingPlans(resourceSharingPlans);
				break;
			case NARRATIVE_TYPE_CANDIDATE_BACKGROUND:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				CandidateBackground candidateBackground = CandidateBackground.Factory
						.newInstance();
				candidateBackground.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setCandidateBackground(candidateBackground);
				break;
			case NARRATIVE_TYPE_CAREER_GOALS_AND_OBJECTIVES:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				CareerGoalsAndObjectives careerGoalsAndObjectives = CareerGoalsAndObjectives.Factory
						.newInstance();
				careerGoalsAndObjectives
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setCareerGoalsAndObjectives(careerGoalsAndObjectives);
				break;
			case NARRATIVE_TYPE_CAREER_DEVELOPMENT_AND_TRAINING:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				CareerDevelopmentAndTrainingActivities careerDevelopmentAndTrainingActivities = CareerDevelopmentAndTrainingActivities.Factory
						.newInstance();
				careerDevelopmentAndTrainingActivities
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setCareerDevelopmentAndTrainingActivities(careerDevelopmentAndTrainingActivities);
				break;
			case NARRATIVE_TYPE_RESPONSIBLE_CONDUCT_OF_RESEARCH:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResponsibleConductOfResearch responsibleConductOfResearch = ResponsibleConductOfResearch.Factory
						.newInstance();
				responsibleConductOfResearch
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setResponsibleConductOfResearch(responsibleConductOfResearch);
				break;
			case NARRATIVE_TYPE_PHS398_MENTORING_PLAN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				MentoringPlan mentoringPlan = MentoringPlan.Factory
						.newInstance();
				mentoringPlan.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setMentoringPlan(mentoringPlan);
				break;
			case NARRATIVE_TYPE_PHS398_MENTOR_STATEMENTS_LETTERS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				StatementsOfSupport statementsOfSupport = StatementsOfSupport.Factory
						.newInstance();
				statementsOfSupport.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setStatementsOfSupport(statementsOfSupport);
				break;
			case NARRATIVE_TYPE_PSH398_INSTITUTIONAL_ENVIRONMENT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InsitutionalEnvironment insitutionalEnvironment = InsitutionalEnvironment.Factory
						.newInstance();
				insitutionalEnvironment
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setInsitutionalEnvironment(insitutionalEnvironment);
				break;
			case NARRATIVE_TYPE_PHS398_INSTITUTIONAL_COMMITMENT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InstitutionalCommitment institutionalCommitment = InstitutionalCommitment.Factory
						.newInstance();
				institutionalCommitment
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setInstitutionalCommitment(institutionalCommitment);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_APPENDIX:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				attachedFileList.add(attachedFileDataType);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_REASEARCH_STRATEGY:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResearchStrategy researchStrategy = ResearchStrategy.Factory
						.newInstance();
				researchStrategy.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setResearchStrategy(researchStrategy);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_SUPPPORT_LTRS:
                attachedFileDataType = getAttachedFileType(narrative);
                if(attachedFileDataType == null){
                    continue;
                }
                LettersOfSupport lettersOfSupport = (LettersOfSupport) LettersOfSupport.Factory
                        .newInstance();
                lettersOfSupport.setAttFile(attachedFileDataType);
                careerDevelopmentAwardAttachments
                    .setLettersOfSupport(lettersOfSupport);
                break;             
			}
		}
		attachmentGroupMin0Max100DataType.setAttachedFileArray(attachedFileList
				.toArray(new AttachedFileDataType[0]));
		careerDevelopmentAwardAttachments
				.setAppendix(attachmentGroupMin0Max100DataType);
		return careerDevelopmentAwardAttachments;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHS398ResearchPlan12Document} by populating data from the given
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
		return getPHS398CareerDevelopmentAwardSup();
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