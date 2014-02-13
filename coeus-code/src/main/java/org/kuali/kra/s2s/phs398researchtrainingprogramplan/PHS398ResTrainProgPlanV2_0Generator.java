/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.s2s.phs398researchtrainingprogramplan;

import gov.grants.apply.forms.phs398ResearchTrainingProgramPlan20V20.PHS398ResearchTrainingProgramPlan20Document;
import gov.grants.apply.forms.phs398ResearchTrainingProgramPlan20V20.PHS398ResearchTrainingProgramPlan20Document.PHS398ResearchTrainingProgramPlan20;
import gov.grants.apply.forms.phs398ResearchTrainingProgramPlan20V20.PHS398ResearchTrainingProgramPlan20Document.PHS398ResearchTrainingProgramPlan20.ResearchTrainingProgramPlanAttachments;
import gov.grants.apply.forms.phs398ResearchTrainingProgramPlan20V20.PHS398ResearchTrainingProgramPlan20Document.PHS398ResearchTrainingProgramPlan20.ResearchTrainingProgramPlanAttachments.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * 
 * This class is the xml generator for PHS398ResTrainProgPlanV2_0 form
 *  ref namespace://apply.grants.gov/forms/PHS398_ResearchTrainingProgramPlan_2_0-V2.0
 */
public class PHS398ResTrainProgPlanV2_0Generator extends S2SBaseFormGenerator{
    private static final int PHS_RES_TRAINING_PLAN_INTRODUCTION_112 = 112;
    private static final int PHS_RES_TRAINING_PLAN_BACKGROUND_113 = 113;
    private static final int PHS_RES_TRAINING_PLAN_PROGRAM_PLAN_114 = 114;
    private static final int PHS_RES_TRAINING_PLAN_RECRUITMENT_PLAN_115 = 115;
    private static final int PHS_RES_TRAINING_PLAN_RESP_CONDUCT_SEARCH_116 = 116;
    private static final int PHS_RES_TRAINING_PLAN_PROGRESS_REPORT_117 = 117;
    private static final int PHS_RES_TRAINING_PLAN_HUMAN_SUBJECTS_118 = 118;
    private static final int PHS_RES_TRAINING_PLAN_SELECT_AGENT_SEARCH_120 = 120;
    private static final int PHS_RES_TRAINING_PLAN_VERT_ANIMALS_119 = 119;
    private static final int PHS_RES_TRAINING_PLAN_PI_LEADERSHIP_PLAN_121 = 121;
    private static final int PHS_RES_TRAINING_PLAN_CONS_CONTRACTUAL_122 = 122;
    private static final int PHS_RES_TRAINING_PLAN_FAC_BIOSKETCHES_123 = 123;
    private static final int PHS_RES_TRAINING_PLAN_DATA_TABLES_124 = 124;
    private static final int PHS_RES_TRAINING_PLAN_SUPPORT_LETTERS_125 = 125;
    private static final int PHS_RES_TRAINING_PLAN_APPENDIX_126 = 126;

    private PHS398ResearchTrainingProgramPlan20 getPHS398ResearchTrainingProgramPlan(ProposalDevelopmentDocument proposalDevelopmentDocument) throws S2SException {
        DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        PHS398ResearchTrainingProgramPlan20 phs398ResTrainProgPlan = PHS398ResearchTrainingProgramPlan20.Factory.newInstance();
        
        ResearchTrainingProgramPlanAttachments researchTrainingProgramPlanAttachments = ResearchTrainingProgramPlanAttachments.Factory.newInstance();
  
        phs398ResTrainProgPlan.setFormVersion(S2SConstants.FORMVERSION_2_0);        
        ArrayList<AttachedFileDataType> attachedFileDataTypes = new ArrayList<AttachedFileDataType>();

        AttachedFileDataType attachedFileDataType;
        for (Narrative narrative : developmentProposal.getNarratives()) {
             if (narrative.getNarrativeTypeCode() != null) {
                 switch(Integer.parseInt(narrative.getNarrativeTypeCode())){
                     case(PHS_RES_TRAINING_PLAN_INTRODUCTION_112):
                         attachedFileDataType = getAttachedFileType(narrative);
                         IntroductionToApplication introductionToApplication = researchTrainingProgramPlanAttachments.addNewIntroductionToApplication();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         introductionToApplication.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_BACKGROUND_113):
                         attachedFileDataType = getAttachedFileType(narrative);
                         Background backGround = researchTrainingProgramPlanAttachments.addNewBackground();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         backGround.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_PROGRAM_PLAN_114):
                         attachedFileDataType = getAttachedFileType(narrative);
                         ProgramPlan programPlan = researchTrainingProgramPlanAttachments.addNewProgramPlan();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         programPlan.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_RECRUITMENT_PLAN_115):
                         attachedFileDataType = getAttachedFileType(narrative);
                         RecruitmentAndRetentionPlanToEnhanceDiversity recruitmentAndRetentionPlanToEnhanceDiversity = researchTrainingProgramPlanAttachments.addNewRecruitmentAndRetentionPlanToEnhanceDiversity();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         recruitmentAndRetentionPlanToEnhanceDiversity.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_RESP_CONDUCT_SEARCH_116):
                         attachedFileDataType = getAttachedFileType(narrative);
                         ResponsibleConductOfResearch responsibleConductOfResearch = researchTrainingProgramPlanAttachments.addNewResponsibleConductOfResearch();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         responsibleConductOfResearch.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_PROGRESS_REPORT_117):
                         attachedFileDataType = getAttachedFileType(narrative);
                         ProgressReport progressReport = researchTrainingProgramPlanAttachments.addNewProgressReport();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         progressReport.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_HUMAN_SUBJECTS_118):
                         attachedFileDataType = getAttachedFileType(narrative);
                         HumanSubjects humanSubjects = researchTrainingProgramPlanAttachments.addNewHumanSubjects();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         humanSubjects.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_SELECT_AGENT_SEARCH_120):
                         attachedFileDataType = getAttachedFileType(narrative);
                         SelectAgentResearch selectAgentResearch = researchTrainingProgramPlanAttachments.addNewSelectAgentResearch();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         selectAgentResearch.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_VERT_ANIMALS_119):
                         attachedFileDataType = getAttachedFileType(narrative);
                         VertebrateAnimals vertebrateAnimals = researchTrainingProgramPlanAttachments.addNewVertebrateAnimals();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         vertebrateAnimals.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_PI_LEADERSHIP_PLAN_121):
                         attachedFileDataType = getAttachedFileType(narrative);
                         MultiplePDPILeadershipPlan multiplePDPILeadershipPlan = researchTrainingProgramPlanAttachments.addNewMultiplePDPILeadershipPlan();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         multiplePDPILeadershipPlan.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_CONS_CONTRACTUAL_122):
                         attachedFileDataType = getAttachedFileType(narrative);
                         ConsortiumContractualArrangements consortiumContractualArrangements = researchTrainingProgramPlanAttachments.addNewConsortiumContractualArrangements();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         consortiumContractualArrangements.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_FAC_BIOSKETCHES_123):
                         attachedFileDataType = getAttachedFileType(narrative);
                         ParticipatingFacultyBiosketches participatingFacultyBiosketches = researchTrainingProgramPlanAttachments.addNewParticipatingFacultyBiosketches();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         participatingFacultyBiosketches.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_DATA_TABLES_124):
                         attachedFileDataType = getAttachedFileType(narrative);
                         DataTables dataTables = researchTrainingProgramPlanAttachments.addNewDataTables();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         dataTables.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_SUPPORT_LETTERS_125):
                         attachedFileDataType = getAttachedFileType(narrative);
                         LettersOfSupport lettersOfSupport = researchTrainingProgramPlanAttachments.addNewLettersOfSupport();
                         if(attachedFileDataType == null){
                             continue;
                         }
                         lettersOfSupport.setAttFile(attachedFileDataType);
                         break;
                     case(PHS_RES_TRAINING_PLAN_APPENDIX_126):
                         attachedFileDataType = getAttachedFileType(narrative);
                         if(attachedFileDataType == null){
                             continue;
                         }
                         attachedFileDataTypes.add(attachedFileDataType);
                         break;
                 }
             }
         }
         if (!attachedFileDataTypes.isEmpty()){
             AttachmentGroupMin0Max100DataType attachmentGroup = researchTrainingProgramPlanAttachments.addNewAppendix();
             attachmentGroup.setAttachedFileArray(attachedFileDataTypes.toArray(new AttachedFileDataType[0]));
         }
        
         if (researchTrainingProgramPlanAttachments != null)   phs398ResTrainProgPlan.setResearchTrainingProgramPlanAttachments(researchTrainingProgramPlanAttachments);
        
         ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(phs398ResTrainProgPlan.toString().getBytes());            
         sortAttachments(byteArrayInputStream);
        return phs398ResTrainProgPlan;        
    }

    /**
     * Gets the parameterService attribute. 
     * @return Returns the parameterService.
     */
    public ParameterService getParameterService() {
        return KcServiceLocator.getService(ParameterService.class);
    }

    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) throws S2SException {
        PHS398ResearchTrainingProgramPlan20 phs398ResearchTrainingProgramPlan = getPHS398ResearchTrainingProgramPlan(proposalDevelopmentDocument);
        PHS398ResearchTrainingProgramPlan20Document pHS398ResearchTrainingProgramPlan20Document = PHS398ResearchTrainingProgramPlan20Document.Factory.newInstance();
        pHS398ResearchTrainingProgramPlan20Document.setPHS398ResearchTrainingProgramPlan20(phs398ResearchTrainingProgramPlan);
        return pHS398ResearchTrainingProgramPlan20Document;
    }

}
