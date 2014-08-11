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

import java.io.ByteArrayInputStream;


import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.HumanSubjectSection;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.IntroductionToApplication;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.ProgressReportPublicationList;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.ResearchStrategy;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.SpecificAims;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.HumanSubjectSection.InclusionOfChildren;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.HumanSubjectSection.InclusionOfWomenAndMinorities;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.HumanSubjectSection.ProtectionOfHumanSubjects;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections.ConsortiumContractualArrangements;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections.LettersOfSupport;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections.MultiplePDPILeadershipPlan;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections.ResourceSharingPlans;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections.SelectAgentResearch;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20.ResearchPlanAttachments.OtherResearchPlanSections.VertebrateAnimals;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document;
import gov.grants.apply.forms.phs398ResearchPlan20V20.PHS398ResearchPlan20Document.PHS398ResearchPlan20;
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


/**
 * Class for generating the XML object for grants.gov PHS398ResearchPlanV1_2.
 * Form is generated using XMLBean classes and is based on
 * PHS398ResearchPlanV1_2 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("PHS398ResearchPlan_2_0_V2Generator")
public class PHS398ResearchPlan_2_0_V2Generator extends
		PHS398ResearchPlanBaseGenerator {

    @Value("http://apply.grants.gov/forms/PHS398_ResearchPlan_2_0-V2.0")
    private String namespace;

    @Value("PHS398_ResearchPlan_2_0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/PHS398_ResearchPlan_2_0-V2.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398ResearchPlan20V20")
    private String packageName;

    @Value("195")
    private int sortIndex;

    /**
	 * 
	 * This method gives the list of attachments for PHS398ResearchPlan form
	 * 
	 * @return phsResearchPlanDocument {@link XmlObject} of type
	 *         PHS398ResearchPlan20Document.
	 */
	private PHS398ResearchPlan20Document getPHS398ResearchPlan() {
		PHS398ResearchPlan20Document phsResearchPlanDocument = PHS398ResearchPlan20Document.Factory
				.newInstance();
		PHS398ResearchPlan20 phsResearchPlan = PHS398ResearchPlan20.Factory
				.newInstance();
		phsResearchPlan.setFormVersion(FormVersion.v2_0.getVersion());
		ResearchPlanAttachments researchPlanAttachments = ResearchPlanAttachments.Factory
				.newInstance();
		getNarrativeAttachments(researchPlanAttachments);
		AttachmentGroupMin0Max100DataType attachmentGroupMin0Max100DataType = AttachmentGroupMin0Max100DataType.Factory
				.newInstance();
		attachmentGroupMin0Max100DataType
				.setAttachedFileArray(getAttachedFileDataTypes());
		researchPlanAttachments.setAppendix(attachmentGroupMin0Max100DataType);
		phsResearchPlan.setResearchPlanAttachments(researchPlanAttachments);
		phsResearchPlanDocument.setPHS398ResearchPlan20(phsResearchPlan);
		
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(phsResearchPlanDocument.toString().getBytes());            
		sortAttachments(byteArrayInputStream);		
		return phsResearchPlanDocument;
	}

	private void getNarrativeAttachments(
			ResearchPlanAttachments researchPlanAttachments) {
		HumanSubjectSection humanSubjectSection = HumanSubjectSection.Factory
				.newInstance();
		OtherResearchPlanSections otherResearchPlanSections = OtherResearchPlanSections.Factory
				.newInstance();

        ResearchStrategy researchStrategy = ResearchStrategy.Factory.newInstance();
		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
		    AttachedFileDataType attachedFileDataType=null;
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
			case RESEARCH_STRATEGY:
                attachedFileDataType = getAttachedFileType(narrative);
                if(attachedFileDataType == null){
                    continue;
                }
			    researchStrategy.setAttFile(attachedFileDataType);
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
        researchPlanAttachments.setResearchStrategy(researchStrategy);
		researchPlanAttachments.setHumanSubjectSection(humanSubjectSection);
		researchPlanAttachments
				.setOtherResearchPlanSections(otherResearchPlanSections);
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
		int size = 0;
		AttachedFileDataType[] attachedFileDataTypes = null;
		DevelopmentProposalContract developmentProposal = pdDoc
				.getDevelopmentProposal();
		for (NarrativeContract narrative : developmentProposal.getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null
					&& Integer.parseInt(narrative.getNarrativeType().getCode()) == APPENDIX) {
				size++;
			}
		}
		attachedFileDataTypes = new AttachedFileDataType[size];
		int attachments = 0;
		for (NarrativeContract narrative : developmentProposal.getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null
					&& Integer.parseInt(narrative.getNarrativeType().getCode()) == APPENDIX) {
				attachedFileDataTypes[attachments] = getAttachedFileType(narrative);
				attachments++;
			}
		}
		return attachedFileDataTypes;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHS398ResearchPlan20Document} by populating data from the given
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
