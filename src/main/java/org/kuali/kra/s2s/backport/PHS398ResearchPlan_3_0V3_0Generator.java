package org.kuali.kra.s2s.backport;

import gov.grants.apply.forms.phs398ResearchPlan30V30.PHS398ResearchPlan30Document;
import gov.grants.apply.forms.phs398ResearchPlan30V30.PHS398ResearchPlan30Document.PHS398ResearchPlan30;
import gov.grants.apply.forms.phs398ResearchPlan30V30.PHS398ResearchPlan30Document.PHS398ResearchPlan30.ResearchPlanAttachments;
import gov.grants.apply.forms.phs398ResearchPlan30V30.PHS398ResearchPlan30Document.PHS398ResearchPlan30.ResearchPlanAttachments.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.impl.PHS398ResearchPlanBaseGenerator;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class PHS398ResearchPlan_3_0V3_0Generator extends PHS398ResearchPlanBaseGenerator {

    private static final int DATA_SAFETY_MONITORING_PLANS = 149;
    private static final int KEY_BIO_CHEMICAL_RESOURCES = 150;

    /**
     * This method is used to get List of appendix attachments from
     * NarrativeAttachment
     *
     * @return AttachedFileDataType[] array of attachments for the corresponding
     * narrative type code APPENDIX.
     */
    private AttachedFileDataType[] getAppendixAttachedFileDataTypes() {
        List<AttachedFileDataType> afdts = new ArrayList<AttachedFileDataType>();
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getNarrativeTypeCode() != null && Integer.parseInt(narrative.getNarrativeType().getNarrativeTypeCode()) == APPENDIX) {
                AttachedFileDataType afdt = getAttachedFileType(narrative);
                if (afdt != null) {
                    afdts.add(afdt);
                }
            }
        }

        return afdts.toArray(new AttachedFileDataType[] {});
    }

    private PHS398ResearchPlan30Document getPHS398ResearchPlan() {
        PHS398ResearchPlan30Document phsResearchPlanDocument = PHS398ResearchPlan30Document.Factory.newInstance();
        PHS398ResearchPlan30 phsResearchPlan = PHS398ResearchPlan30.Factory.newInstance();
        phsResearchPlan.setFormVersion(FormVersion.v3_0.getVersion());
        ResearchPlanAttachments researchPlanAttachments = ResearchPlanAttachments.Factory.newInstance();
        getNarrativeAttachments(researchPlanAttachments);
        AttachmentGroupMin0Max100DataType attachmentGroupMin0Max100DataType = AttachmentGroupMin0Max100DataType.Factory.newInstance();
        attachmentGroupMin0Max100DataType.setAttachedFileArray(getAppendixAttachedFileDataTypes());
        researchPlanAttachments.setAppendix(attachmentGroupMin0Max100DataType);
        phsResearchPlan.setResearchPlanAttachments(researchPlanAttachments);
        phsResearchPlanDocument.setPHS398ResearchPlan30(phsResearchPlan);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(phsResearchPlanDocument.toString().getBytes());
        sortAttachments(byteArrayInputStream);
        return phsResearchPlanDocument;
    }

    private void getNarrativeAttachments(ResearchPlanAttachments researchPlanAttachments) {

        for(Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            final AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
            if (attachedFileDataType != null) {
                switch (Integer.parseInt(narrative.getNarrativeType().getNarrativeTypeCode())) {
                    case INTRODUCTION_TO_APPLICATION:
                        IntroductionToApplication introductionToApplication = IntroductionToApplication.Factory.newInstance();
                        introductionToApplication.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setIntroductionToApplication(introductionToApplication);
                        break;
                    case SPECIFIC_AIMS:
                        SpecificAims specificAims = SpecificAims.Factory.newInstance();
                        specificAims.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setSpecificAims(specificAims);
                        break;
                    case RESEARCH_STRATEGY:
                        ResearchStrategy researchStrategy = ResearchStrategy.Factory.newInstance();
                        researchStrategy.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setResearchStrategy(researchStrategy);
                        break;
                    case PROGRESS_REPORT_PUBLICATION_LIST:
                        ProgressReportPublicationList progressReportPublicationList = ProgressReportPublicationList.Factory.newInstance();
                        progressReportPublicationList.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setProgressReportPublicationList(progressReportPublicationList);
                        break;
                    case PROTECTION_OF_HUMAN_SUBJECTS:
                        ProtectionOfHumanSubjects protectionOfHumanSubjects = ProtectionOfHumanSubjects.Factory.newInstance();
                        protectionOfHumanSubjects.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setProtectionOfHumanSubjects(protectionOfHumanSubjects);
                        break;
                    case INCLUSION_OF_WOMEN_AND_MINORITIES:
                        InclusionOfWomenAndMinorities inclusionOfWomenAndMinorities = InclusionOfWomenAndMinorities.Factory.newInstance();
                        inclusionOfWomenAndMinorities.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setInclusionOfWomenAndMinorities(inclusionOfWomenAndMinorities);
                        break;
                    case INCLUSION_OF_CHILDREN:
                        InclusionOfChildren inclusionOfChildren = InclusionOfChildren.Factory.newInstance();
                        inclusionOfChildren.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setInclusionOfChildren(inclusionOfChildren);
                        break;
                    case VERTEBRATE_ANIMALS:
                        VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory.newInstance();
                        vertebrateAnimals.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setVertebrateAnimals(vertebrateAnimals);
                        break;
                    case SELECT_AGENT_RESEARCH:
                        SelectAgentResearch selectAgentResearch = SelectAgentResearch.Factory.newInstance();
                        selectAgentResearch.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setSelectAgentResearch(selectAgentResearch);
                        break;
                    case MULTIPLE_PI_LEADERSHIP_PLAN:
                        MultiplePDPILeadershipPlan multiplePILeadershipPlan = MultiplePDPILeadershipPlan.Factory.newInstance();
                        multiplePILeadershipPlan.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setMultiplePDPILeadershipPlan(multiplePILeadershipPlan);
                        break;
                    case CONSORTIUM_CONTRACTUAL_ARRANGEMENTS:
                        ConsortiumContractualArrangements contractualArrangements = ConsortiumContractualArrangements.Factory.newInstance();
                        contractualArrangements.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setConsortiumContractualArrangements(contractualArrangements);
                        break;
                    case LETTERS_OF_SUPPORT:
                        LettersOfSupport lettersOfSupport = LettersOfSupport.Factory.newInstance();
                        lettersOfSupport.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setLettersOfSupport(lettersOfSupport);
                        break;
                    case RESOURCE_SHARING_PLANS:
                        ResourceSharingPlans resourceSharingPlans = ResourceSharingPlans.Factory.newInstance();
                        resourceSharingPlans.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setResourceSharingPlans(resourceSharingPlans);
                        break;
                    case DATA_SAFETY_MONITORING_PLANS:
                        DataSafetyMonitoringPlan dataSafetyMonitoringPlans = DataSafetyMonitoringPlan.Factory.newInstance();
                        dataSafetyMonitoringPlans.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setDataSafetyMonitoringPlan(dataSafetyMonitoringPlans);
                        break;
                    case KEY_BIO_CHEMICAL_RESOURCES:
                        KeyBiologicalAndOrChemicalResources keyBiologicalAndOrChemicalResources = KeyBiologicalAndOrChemicalResources.Factory.newInstance();
                        keyBiologicalAndOrChemicalResources.setAttFile(attachedFileDataType);
                        researchPlanAttachments.setKeyBiologicalAndOrChemicalResources(keyBiologicalAndOrChemicalResources);
                        break;
                }
            }
        }

    }

    /**
     * This method creates {@link XmlObject} of type
     * {@link PHS398ResearchPlan30Document} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     *
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given
     * {@link ProposalDevelopmentDocument}
     */
    @Override
    public XmlObject getFormObject(
            ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getPHS398ResearchPlan();
    }

}
