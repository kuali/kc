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

import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ApplicationType;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ApplicationType.TypeOfApplication;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments.*;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments.HumanSubjectSection.InclusionOfChildren;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments.HumanSubjectSection.InclusionOfWomenAndMinorities;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments.HumanSubjectSection.ProtectionOfHumanSubjects;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments.HumanSubjectSection.TargetedPlannedEnrollmentTable;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document.PHS398ResearchPlan12.ResearchPlanAttachments.OtherResearchPlanSections.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating the XML object for grants.gov PHS398ResearchPlanV1_2.
 * Form is generated using XMLBean classes and is based on
 * PHS398ResearchPlanV1_2 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("PHS398ResearchPlanV1_2Generator")
public class PHS398ResearchPlanV1_2Generator extends
		PHS398ResearchPlanBaseGenerator {

    @Value("http://apply.grants.gov/forms/PHS398_ResearchPlan_1_2-V1.2")
    private String namespace;

    @Value("PHS398_ResearchPlan_1_2-V1.2")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/PHS398_ResearchPlan-V1.2.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398ResearchPlan12V12")
    private String packageName;

    @Value("195")
    private int sortIndex;

	/**
	 * 
	 * This method gives the list of attachments for PHS398ResearchPlan form
	 * 
	 * @return phsResearchPlanDocument {@link XmlObject} of type
	 *         PHS398ResearchPlan12Document.
	 */
	private PHS398ResearchPlan12Document getPHS398ResearchPlan() {
		PHS398ResearchPlan12Document phsResearchPlanDocument = PHS398ResearchPlan12Document.Factory
				.newInstance();
		PHS398ResearchPlan12 phsResearchPlan = PHS398ResearchPlan12.Factory
				.newInstance();
		phsResearchPlan.setFormVersion(FormVersion.v1_2.getVersion());
		phsResearchPlan.setApplicationType(getApplicationType());
		ResearchPlanAttachments researchPlanAttachments = ResearchPlanAttachments.Factory
				.newInstance();
		getNarrativeAttachments(researchPlanAttachments);
		AttachmentGroupMin0Max100DataType attachmentGroupMin0Max100DataType = AttachmentGroupMin0Max100DataType.Factory
				.newInstance();
		attachmentGroupMin0Max100DataType
				.setAttachedFileArray(getAttachedFileDataTypes());
		researchPlanAttachments.setAppendix(attachmentGroupMin0Max100DataType);
		phsResearchPlan.setResearchPlanAttachments(researchPlanAttachments);
		phsResearchPlanDocument.setPHS398ResearchPlan12(phsResearchPlan);
		return phsResearchPlanDocument;
	}

	private void getNarrativeAttachments(
			ResearchPlanAttachments researchPlanAttachments) {
		HumanSubjectSection humanSubjectSection = HumanSubjectSection.Factory
				.newInstance();
		OtherResearchPlanSections otherResearchPlanSections = OtherResearchPlanSections.Factory
				.newInstance();
		AttachedFileDataType attachedFileDataType = null;
		
		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			switch (Integer.parseInt(narrative.getNarrativeType().getCode())) {
			case INTRODUCTION_TO_APPLICATION:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				IntroductionToApplication introductionToApplication = IntroductionToApplication.Factory
						.newInstance();
				introductionToApplication
						.setAttFile(attachedFileDataType);
				researchPlanAttachments
						.setIntroductionToApplication(introductionToApplication);
				break;
			case SPECIFIC_AIMS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				SpecificAims specificAims = SpecificAims.Factory.newInstance();
				specificAims.setAttFile(attachedFileDataType);
				researchPlanAttachments.setSpecificAims(specificAims);
				break;
			case BACKGROUND_SIGNIFICANCE:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				BackgroundSignificance backgroundSignificance = BackgroundSignificance.Factory
						.newInstance();
				backgroundSignificance
						.setAttFile(attachedFileDataType);
				researchPlanAttachments
						.setBackgroundSignificance(backgroundSignificance);
				break;
			case PROGRESS_REPORT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ProgressReport progressReport = ProgressReport.Factory
						.newInstance();
				progressReport.setAttFile(attachedFileDataType);
				researchPlanAttachments.setProgressReport(progressReport);
				break;
			case RESEARCH_DESIGN_METHODS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResearchDesignMethods researchDesignMethods = ResearchDesignMethods.Factory
						.newInstance();
				researchDesignMethods
						.setAttFile(attachedFileDataType);
				researchPlanAttachments
						.setResearchDesignMethods(researchDesignMethods);
				break;
			case INCLUSION_ENROLLMENT_REPORT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionEnrollmentReport inclusionEnrollmentReport = InclusionEnrollmentReport.Factory
						.newInstance();
				inclusionEnrollmentReport
						.setAttFile(attachedFileDataType);
				researchPlanAttachments
						.setInclusionEnrollmentReport(inclusionEnrollmentReport);
				break;
			case PROGRESS_REPORT_PUBLICATION_LIST:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ProgressReportPublicationList progressReportPublicationList = ProgressReportPublicationList.Factory
						.newInstance();
				progressReportPublicationList
						.setAttFile(attachedFileDataType);
				researchPlanAttachments
						.setProgressReportPublicationList(progressReportPublicationList);
				break;
			case PROTECTION_OF_HUMAN_SUBJECTS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ProtectionOfHumanSubjects protectionOfHumanSubjects = ProtectionOfHumanSubjects.Factory
						.newInstance();
				protectionOfHumanSubjects
						.setAttFile(attachedFileDataType);
				humanSubjectSection
						.setProtectionOfHumanSubjects(protectionOfHumanSubjects);
				break;
			case INCLUSION_OF_WOMEN_AND_MINORITIES:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionOfWomenAndMinorities inclusionOfWomenAndMinorities = InclusionOfWomenAndMinorities.Factory
						.newInstance();
				inclusionOfWomenAndMinorities
						.setAttFile(attachedFileDataType);
				humanSubjectSection
						.setInclusionOfWomenAndMinorities(inclusionOfWomenAndMinorities);
				break;
			case TARGETED_PLANNED_ENROLLMENT_TABLE:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				TargetedPlannedEnrollmentTable tarPlannedEnrollmentTable = TargetedPlannedEnrollmentTable.Factory
						.newInstance();
				tarPlannedEnrollmentTable
						.setAttFile(attachedFileDataType);
				humanSubjectSection
						.setTargetedPlannedEnrollmentTable(tarPlannedEnrollmentTable);
				break;
			case INCLUSION_OF_CHILDREN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionOfChildren inclusionOfChildren = InclusionOfChildren.Factory
						.newInstance();
				inclusionOfChildren.setAttFile(attachedFileDataType);
				humanSubjectSection.setInclusionOfChildren(inclusionOfChildren);
				break;
			case VERTEBRATE_ANIMALS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory
						.newInstance();
				vertebrateAnimals.setAttFile(attachedFileDataType);
				otherResearchPlanSections
						.setVertebrateAnimals(vertebrateAnimals);
				break;
			case SELECT_AGENT_RESEARCH:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				SelectAgentResearch selectAgentResearch = SelectAgentResearch.Factory
						.newInstance();
				selectAgentResearch.setAttFile(attachedFileDataType);
				otherResearchPlanSections
						.setSelectAgentResearch(selectAgentResearch);
				break;
			case MULTIPLE_PI_LEADERSHIP_PLAN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				MultiplePDPILeadershipPlan multiplePILeadershipPlan = MultiplePDPILeadershipPlan.Factory
						.newInstance();
				multiplePILeadershipPlan
						.setAttFile(attachedFileDataType);
				otherResearchPlanSections
						.setMultiplePDPILeadershipPlan(multiplePILeadershipPlan);
				break;
			case CONSORTIUM_CONTRACTUAL_ARRANGEMENTS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ConsortiumContractualArrangements contractualArrangements = ConsortiumContractualArrangements.Factory
						.newInstance();
				contractualArrangements
						.setAttFile(attachedFileDataType);
				otherResearchPlanSections
						.setConsortiumContractualArrangements(contractualArrangements);
				break;
			case LETTERS_OF_SUPPORT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				LettersOfSupport lettersOfSupport = LettersOfSupport.Factory
						.newInstance();
				lettersOfSupport.setAttFile(attachedFileDataType);
				otherResearchPlanSections.setLettersOfSupport(lettersOfSupport);
				break;
			case RESOURCE_SHARING_PLANS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResourceSharingPlans resourceSharingPlans = ResourceSharingPlans.Factory
						.newInstance();
				resourceSharingPlans.setAttFile(attachedFileDataType);
				otherResearchPlanSections
						.setResourceSharingPlans(resourceSharingPlans);
				break;
			}
		}
		researchPlanAttachments.setHumanSubjectSection(humanSubjectSection);
		researchPlanAttachments
				.setOtherResearchPlanSections(otherResearchPlanSections);
	}

	/**
	 * 
	 * This method is used to get ApplicationType from
	 * ProposalDevelopmentDocumentContract
	 * 
	 * @return ApplicationType corresponding to the proposal type code.
	 */
	private ApplicationType getApplicationType() {
		ApplicationType applicationType = ApplicationType.Factory.newInstance();
		DevelopmentProposalContract developmentProposal = pdDoc
				.getDevelopmentProposal();
		if (developmentProposal.getProposalType() != null
				&& Integer.parseInt(developmentProposal.getProposalType().getCode()) < PROPOSAL_TYPE_CODE_6) {
			// Check <6 to ensure that if proposalType='TASk ORDER", it must
			// not be
			// set. THis is because enum ApplicationType has no
			// entry for TASK ORDER
			TypeOfApplication.Enum typeOfApplication = TypeOfApplication.Enum
					.forInt(Integer.parseInt(developmentProposal
							.getProposalType().getCode()));
			applicationType.setTypeOfApplication(typeOfApplication);
		}
		return applicationType;
	}

	/**
	 * 
	 * This method is used to get List of appendix attachments from
	 * NarrativeAttachment
	 * 
	 * @return AttachedFileDataType[] array of attachments for the corresponding
	 *         narrative type code APPENDIX.
	 */
	private AttachedFileDataType[] getAttachedFileDataTypes() {
		DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
		List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
		AttachedFileDataType attachedFileDataType = null;
		
		for (NarrativeContract narrative : developmentProposal.getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null
					&& Integer.parseInt(narrative.getNarrativeType().getCode()) == APPENDIX) {
				attachedFileDataType = getAttachedFileType(narrative);
				if(attachedFileDataType != null){
					attachedFileDataTypeList.add(attachedFileDataType);
				}
			}
		}
		return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
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
		return getPHS398ResearchPlan();
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
