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

import gov.grants.apply.forms.nasaOtherProjectInformationV10.FYDataType;
import gov.grants.apply.forms.nasaOtherProjectInformationV10.NASAOtherProjectInformationDocument;
import gov.grants.apply.forms.nasaOtherProjectInformationV10.NASAOtherProjectInformationDocument.NASAOtherProjectInformation;
import gov.grants.apply.forms.nasaOtherProjectInformationV10.NASAOtherProjectInformationDocument.NASAOtherProjectInformation.HistoricImpact;
import gov.grants.apply.forms.nasaOtherProjectInformationV10.NASAOtherProjectInformationDocument.NASAOtherProjectInformation.InternationalParticipation;
import gov.grants.apply.forms.nasaOtherProjectInformationV10.NASAOtherProjectInformationDocument.NASAOtherProjectInformation.NASACivilServicePersonnel;
import gov.grants.apply.forms.nasaOtherProjectInformationV10.NASAOtherProjectInformationDocument.NASAOtherProjectInformation.NASACivilServicePersonnel.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerContract;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating the XML object for grants.gov
 * NasaOtherProjectInformationV1_0. Form is generated using XMLBean classes and
 * is based on NasaOtherProjectInformation schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("NASAOtherProjectInformationV1_0Generator")
public class NASAOtherProjectInformationV1_0Generator extends
        S2SBaseFormGenerator {

    private static final String PRINCIPAL_INVESTIGATOR = "PI";
    private static final String COLLABORATOR_ROLE = "Collaborator";
    private static final String C0_INVESTIGATOR = "Co-I";
    private static final String EQUIPMENT = "Equipment";
    private static final String FACILITY = "Facility";
    private static final Integer HISTORICAL_IMPACT = 106;
    private static final Integer CIVIL_SERVICE_PERSONNEL = 101;
    private static final Integer EXPLATATION = 107;
    private static final Integer INTERNATIONAL_PARTICIPATION = 108;
    private static final Integer INTERNATIONAL_PARTICIPATION_SUPPORT = 109;
    private static final Integer FTE = 104;
    private static final Integer FISCAL_YEAR = 103;
    private static final int PROGRAM_SPECIFIC_DATA = 47;
    private static final int APPENDICES = 48;
    private static final int NON_US_ORGANIZATION_LETTERS_OF_ENDORSEMENT = 49;
    private static final int NARRATIVE_IRB_ACUC_LETTERS = 50;
    private static final int MAX_EXPLANATION_LENGTH = 2000;
    private static final String NOT_ANSWERED = "No";
    
    private static final int FISCAL_YEAR_2006 = 2006;
    private static final int FISCAL_YEAR_2007 = 2007;
    private static final int FISCAL_YEAR_2008 = 2008;
    private static final int FISCAL_YEAR_2009 = 2009;
    private static final int FISCAL_YEAR_2010 = 2010;
    private static final int FISCAL_YEAR_2011 = 2011;
    private static final int FISCAL_YEAR_2012 = 2012;
    private static final int FISCAL_YEAR_2013 = 2013;
    private static final int FISCAL_YEAR_2014 = 2014;
    private static final int FISCAL_YEAR_2015 = 2015;
    private static final int FISCAL_YEAR_2016 = 2016;
    private static final int FISCAL_YEAR_2017 = 2017;
    private static final int FISCAL_YEAR_2018 = 2018;
    private static final int FISCAL_YEAR_2019 = 2019;
    private static final int FISCAL_YEAR_2020 = 2020;
    private static final int FISCAL_YEAR_2021 = 2021;
    private static final int FISCAL_YEAR_1 = 0;
    private static final int FISCAL_YEAR_2 = 1;
    private static final int FISCAL_YEAR_3 = 2;
    private static final int FISCAL_YEAR_4 = 3;
    private static final int FISCAL_YEAR_5 = 4;
    private static final int FISCAL_YEAR_6 = 5;

    @Value("http://apply.grants.gov/forms/NASA_OtherProjectInformation-V1.0")
    private String namespace;

    @Value("NASA_OtherProjectInformation-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/NASA_OtherProjectInformation-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.nasaOtherProjectInformationV10")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    List<? extends AnswerHeaderContract> answerHeaders;

    /**
     * 
     * This method gives information of NasaCivilServicePersonnel,
     * HistoricalImpact, InternationalParticipation from
     * NasaOtherProjectInformation
     * 
     * @return nasaOtherInformationDocument {@link XmlObject} of type
     *         NASAOtherProjectInformationDocument.
     */

    private NASAOtherProjectInformationDocument getNasaOtherProjectInformation() {

        NASAOtherProjectInformationDocument nasaOtherInformationDocument = NASAOtherProjectInformationDocument.Factory
                .newInstance();
        NASAOtherProjectInformation nasaOtherProjectInformation = NASAOtherProjectInformation.Factory
                .newInstance();
        nasaOtherProjectInformation
                .setFormVersion(FormVersion.v1_0.getVersion());
        nasaOtherProjectInformation
                .setNASACivilServicePersonnel(getNasaCivilServicePersonnel());
        nasaOtherProjectInformation.setHistoricImpact(getHistoricImpact());
        nasaOtherProjectInformation
                .setInternationalParticipation(getInternationalParticipation());
        nasaOtherInformationDocument
                .setNASAOtherProjectInformation(nasaOtherProjectInformation);
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
                .getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null
                    && Integer.parseInt(narrative.getNarrativeType().getCode()) == PROGRAM_SPECIFIC_DATA) {
                AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                if(attachedFileDataType != null){
                    nasaOtherProjectInformation.setPSDataAttach(attachedFileDataType);
                    break;
                }
            }
        }

        AttachmentGroupMin0Max100DataType attachmentAppendix = AttachmentGroupMin0Max100DataType.Factory
                .newInstance();
        attachmentAppendix.setAttachedFileArray(getAppendixFileDataTypes());
        nasaOtherProjectInformation.setAppendAttach(attachmentAppendix);

        AttachmentGroupMin0Max100DataType attachmentEndorsement = AttachmentGroupMin0Max100DataType.Factory
                .newInstance();
        attachmentEndorsement
                .setAttachedFileArray(getEndorsementFileDataTypes());
        nasaOtherProjectInformation
                .setLetterEndorsAttach(attachmentEndorsement);

        AttachmentGroupMin0Max100DataType attachmentIRBACUC = AttachmentGroupMin0Max100DataType.Factory
                .newInstance();
        attachmentIRBACUC
                .setAttachedFileArray(getIRBACUCLettersFileDataTypes());
        nasaOtherProjectInformation.setIRBACUCLettersAttach(attachmentIRBACUC);
        nasaOtherInformationDocument
                .setNASAOtherProjectInformation(nasaOtherProjectInformation);
        return nasaOtherInformationDocument;
    }

    /**
     * 
     * This method gives information of NasaCivilServicePersonnel for
     * NasaOtherProjectInformation
     * 
     * @return NASACivilServicePersonnel object containing Nasa civil service
     *         personnel details.
     */
    private NASACivilServicePersonnel getNasaCivilServicePersonnel() {

        NASACivilServicePersonnel nasaCivilServicePersonnel = NASACivilServicePersonnel.Factory
                .newInstance();

        String answerDetails = getAnswer(CIVIL_SERVICE_PERSONNEL, answerHeaders);
        if(answerDetails!=null && !answerDetails.equals(NOT_ANSWERED)){
            YesNoDataType.Enum answer = (answerDetails.equals(
                    YnqConstant.YES.code()) ? YesNoDataType.Y_YES
                            : YesNoDataType.N_NO);
            nasaCivilServicePersonnel.setCivilServicePersonnel(answer);
        }
        
        List<String> fteAnswerDetails = getAnswerList(FTE);
        List<String> fiscalYearDetails = getAnswerList(FISCAL_YEAR);
        if(fteAnswerDetails.size()>FISCAL_YEAR_1){
            FYFTE1 fyfte1 = FYFTE1.Factory.newInstance();
            String fte = fteAnswerDetails.get(FISCAL_YEAR_1);
            BigDecimal fte1= BigDecimal.valueOf(Double.parseDouble(fte));
            fyfte1.setFTE1(fte1);
            if(fiscalYearDetails.size()>FISCAL_YEAR_1){
                String fiscalYear =fiscalYearDetails.get(FISCAL_YEAR_1);
                FYDataType.Enum fyscalYear = getFisaclYear(fiscalYear);
                fyfte1.setFY1(fyscalYear);
                nasaCivilServicePersonnel.setFYFTE1(fyfte1);
            }           
        }else if(answerDetails!=null && answerDetails.equals(YnqConstant.YES.code())){
            nasaCivilServicePersonnel.setFYFTE1(null);
        }
        if(fteAnswerDetails.size()>FISCAL_YEAR_2){
            FYFTE2 fyfte2 = FYFTE2.Factory.newInstance();
            String fte = fteAnswerDetails.get(FISCAL_YEAR_2);
            BigDecimal fte2= BigDecimal.valueOf(Double.parseDouble(fte));
            fyfte2.setFTE2(fte2);
            if(fiscalYearDetails.size()>FISCAL_YEAR_2){
                String fiscalYear =fiscalYearDetails.get(FISCAL_YEAR_2);
                FYDataType.Enum fyscalYear = getFisaclYear(fiscalYear);
                fyfte2.setFY2(fyscalYear);
                nasaCivilServicePersonnel.setFYFTE2(fyfte2);
            }
        }
        if(fteAnswerDetails.size()>FISCAL_YEAR_3){
            FYFTE3 fyfte3 = FYFTE3.Factory.newInstance();
            String fte = fteAnswerDetails.get(FISCAL_YEAR_3);
            BigDecimal fte3= BigDecimal.valueOf(Double.parseDouble(fte));
            fyfte3.setFTE3(fte3);
            if(fiscalYearDetails.size()>FISCAL_YEAR_3){
            String fiscalYear =fiscalYearDetails.get(FISCAL_YEAR_3);
            FYDataType.Enum fyscalYear = getFisaclYear(fiscalYear);
            fyfte3.setFY3(fyscalYear);
            nasaCivilServicePersonnel.setFYFTE3(fyfte3);
            }
        }
        if(fteAnswerDetails.size()>FISCAL_YEAR_4){
            FYFTE4 fyfte4 = FYFTE4.Factory.newInstance();
            String fte = fteAnswerDetails.get(FISCAL_YEAR_4);
            BigDecimal fte4= BigDecimal.valueOf(Double.parseDouble(fte));
            fyfte4.setFTE4(fte4);
            if(fiscalYearDetails.size()>FISCAL_YEAR_4){
                String fiscalYear =fiscalYearDetails.get(FISCAL_YEAR_4);
                FYDataType.Enum fyscalYear = getFisaclYear(fiscalYear);
                fyfte4.setFY4(fyscalYear);
                nasaCivilServicePersonnel.setFYFTE4(fyfte4);
            }
        }
        if(fteAnswerDetails.size()>FISCAL_YEAR_5){
            FYFTE5 fyfte5 = FYFTE5.Factory.newInstance();
            String fte = fteAnswerDetails.get(FISCAL_YEAR_5);
            BigDecimal fte5= BigDecimal.valueOf(Double.parseDouble(fte));
            fyfte5.setFTE5(fte5);
            if(fiscalYearDetails.size()>FISCAL_YEAR_5){
                String fiscalYear =fiscalYearDetails.get(FISCAL_YEAR_5);
                FYDataType.Enum fyscalYear = getFisaclYear(fiscalYear);
                fyfte5.setFY5(fyscalYear);
                nasaCivilServicePersonnel.setFYFTE5(fyfte5);
            }
        }
        if(fteAnswerDetails.size()>FISCAL_YEAR_6){
            FYFTE6 fyfte6 = FYFTE6.Factory.newInstance();
            String fte = fteAnswerDetails.get(FISCAL_YEAR_6);
            BigDecimal fte6= BigDecimal.valueOf(Double.parseDouble(fte));
            fyfte6.setFTE6(fte6);
            if(fiscalYearDetails.size()>FISCAL_YEAR_6){
                String fiscalYear =fiscalYearDetails.get(FISCAL_YEAR_6);
                FYDataType.Enum fyscalYear = getFisaclYear(fiscalYear);
                fyfte6.setFY6(fyscalYear);
                nasaCivilServicePersonnel.setFYFTE6(fyfte6);
            }
        }
        if(fteAnswerDetails.size()!=fiscalYearDetails.size()){
            nasaCivilServicePersonnel.setFYFTE1(null);
        }
        return nasaCivilServicePersonnel;
    }
    
    
    private FYDataType.Enum getFisaclYear(String fiscalYear){
        FYDataType.Enum fyscalYear = null;
        switch(Integer.parseInt(fiscalYear)){
            case FISCAL_YEAR_2006:
                fyscalYear = FYDataType.X_2006;
                break;
            case FISCAL_YEAR_2007:
                fyscalYear = FYDataType.X_2007;
                break;
            case FISCAL_YEAR_2008:
                fyscalYear = FYDataType.X_2008;
                break;
            case FISCAL_YEAR_2009:
                fyscalYear = FYDataType.X_2009;
                break;
            case FISCAL_YEAR_2010:
                fyscalYear = FYDataType.X_2010;
                break;
            case FISCAL_YEAR_2011:
                fyscalYear = FYDataType.X_2011;
                break;
            case FISCAL_YEAR_2012:
                fyscalYear = FYDataType.X_2012;
                break;
            case FISCAL_YEAR_2013:
                fyscalYear = FYDataType.X_2013;
                break;
            case FISCAL_YEAR_2014:
                fyscalYear = FYDataType.X_2014;
                break;
            case FISCAL_YEAR_2015:
                fyscalYear = FYDataType.X_2015;
                break;
            case FISCAL_YEAR_2016:
                fyscalYear = FYDataType.X_2016;
                break;
            case FISCAL_YEAR_2017:
                fyscalYear = FYDataType.X_2017;
                break;
            case FISCAL_YEAR_2018:
                fyscalYear = FYDataType.X_2018;
                break;
            case FISCAL_YEAR_2019:
                fyscalYear = FYDataType.X_2019;
                break;
            case FISCAL_YEAR_2020:
                fyscalYear = FYDataType.X_2020;
                break;
            case FISCAL_YEAR_2021:
                fyscalYear = FYDataType.X_2021;
                break;
        }
        
        return fyscalYear;
    }
    /**
     * 
     * This method gives HistoricalImpact information based on the proposal Ynq
     * question id.
     * 
     * @return HistoricImpact object containing HistoricalImpact details.
     */
    private HistoricImpact getHistoricImpact() {
        HistoricImpact historicImpact = HistoricImpact.Factory.newInstance();
        
       String answerDetails = getAnswer(HISTORICAL_IMPACT, answerHeaders);
        if(answerDetails!= null && !answerDetails.equals(NOT_ANSWERED)){
            YesNoDataType.Enum answer = (answerDetails.equals(
                    YnqConstant.YES.code()) ? YesNoDataType.Y_YES
                    : YesNoDataType.N_NO);
            historicImpact.setHistoricImpactQ(answer);
        }
      
        
        String childAnswerrDetails = getChildQuestionAnswer(HISTORICAL_IMPACT,EXPLATATION, answerHeaders);
        
        if(childAnswerrDetails!= null && !childAnswerrDetails.equals(NOT_ANSWERED)){
            if (childAnswerrDetails.length() > MAX_EXPLANATION_LENGTH) {
                historicImpact.setHistoricImpactEx(childAnswerrDetails.substring(0,
                                MAX_EXPLANATION_LENGTH));            
            }else{
                historicImpact.setHistoricImpactEx(childAnswerrDetails);
            }
        }else if(answerDetails!=null && answerDetails.equals(YnqConstant.YES.code())){
            historicImpact.setHistoricImpactEx(null);
        }
        return historicImpact;
    }

    /**
     * 
     * This method gives the information about InternationalParticipation such
     * as international participation question,explanation Role and Facility.
     * 
     * 
     * @return InternationalParticipation object containing information
     *         regarding the international participation.
     */
    private InternationalParticipation getInternationalParticipation() {
        InternationalParticipation inParticipation = InternationalParticipation.Factory
                .newInstance();
        
        String answerDetails = getAnswer(INTERNATIONAL_PARTICIPATION, answerHeaders);
        if(answerDetails != null && !answerDetails.equals(NOT_ANSWERED)){
            YesNoDataType.Enum  answer = (answerDetails.equals(
                    YnqConstant.YES.code()) ? YesNoDataType.Y_YES
                    : YesNoDataType.N_NO);
            inParticipation.setInternationalParticipationQ(answer);
        }
       
        String childAnswerDetails = getChildQuestionAnswer(INTERNATIONAL_PARTICIPATION,EXPLATATION, answerHeaders);
        if(childAnswerDetails != null){
            if (inParticipation.getInternationalParticipationQ() != null
                    && inParticipation.getInternationalParticipationQ().equals(
                            YesNoDataType.Y_YES)) {
                if (childAnswerDetails.length() > MAX_EXPLANATION_LENGTH) {
                    inParticipation
                    .setInternationalParticipatioEx(childAnswerDetails
                            .substring(0,
                                    MAX_EXPLANATION_LENGTH));
                } else {
                    inParticipation
                    .setInternationalParticipatioEx(childAnswerDetails);
                }
            }
        }else if(answerDetails!=null && answerDetails.equals(YnqConstant.YES.code())){
            inParticipation
            .setInternationalParticipatioEx(null);
        }
          List<String> answerList = getAnswerList(INTERNATIONAL_PARTICIPATION_SUPPORT);
          
          if(answerList.size()>0){
              if(answerList.contains(PRINCIPAL_INVESTIGATOR)){
                  inParticipation.setInternationalParticipationPI(YesNoDataType.Y_YES);
              }
              if(answerList.contains(C0_INVESTIGATOR)){
                  inParticipation.setInternationalParticipationCoI(YesNoDataType.Y_YES);
              }
              if(answerList.contains(COLLABORATOR_ROLE)){
                  inParticipation.setInternationalParticipationCollaborator(YesNoDataType.Y_YES);
              }
              if(answerList.contains(EQUIPMENT)){
                  inParticipation.setInternationalParticipationEquipment(YesNoDataType.Y_YES);
              }
              if(answerList.contains(FACILITY)){
                  inParticipation.setInternationalParticipationFacility(YesNoDataType.Y_YES);
              }
          }else if(answerDetails != null && answerDetails.equals(YnqConstant.YES.code())){
              inParticipation.setInternationalParticipationPI(null);
          }
        
        return inParticipation;
    }
    /**
     * 
     * This method is used to get the answerList for a particular Questionnaire question
     * question based on the question id.
     * 
     * @param questionSeqId
     *            the question seq id to be passed.
     * @return returns the answerList for a particular
     *         question based on the question id passed.
     */
    private List<String> getAnswerList(Integer questionSeqId){
        List <String> answerList= new ArrayList<>();
        for(AnswerHeaderContract answerHeader:answerHeaders){
            List<? extends AnswerContract> answerDetails = answerHeader.getAnswers();
            for(AnswerContract answers:answerDetails){
                if(questionSeqId.equals(getQuestionAnswerService().findQuestionById(answers.getQuestionId()).getQuestionSeqId())){
                    if(answers.getAnswer()!=null){
                        answerList.add(answers.getAnswer());
                    }
                }
            }
        }
        return answerList;
    }

    /**
     * 
     * This method is used to get List of attachments for Appendices from
     * NarrativeAttachment
     * 
     * @return AttachedFileDataType[] array of attached files based on the
     *         Narrative Type Code.
     */
    private AttachedFileDataType[] getAppendixFileDataTypes() {
        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
        AttachedFileDataType attachedFileDataType = null;
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
                .getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null
                    && Integer.parseInt(narrative.getNarrativeType().getCode()) == APPENDICES) {
                attachedFileDataType = getAttachedFileType(narrative);
                if(attachedFileDataType != null){
                    attachedFileDataTypeList.add(attachedFileDataType);
                }
            }
        }
        return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
    }

    /**
     * 
     * This method is used to get List of attachments for non-US organization
     * letters of endorsement type from NarrativeAttachment
     * 
     * @return AttachedFileDataType[] array of attached files based on the
     *         Narrative Type Code.
     */
    private AttachedFileDataType[] getEndorsementFileDataTypes() {
        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
        AttachedFileDataType attachedFileDataType = null;
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
                .getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null
                    && Integer.parseInt(narrative.getNarrativeType().getCode()) == NON_US_ORGANIZATION_LETTERS_OF_ENDORSEMENT) {
                attachedFileDataType = getAttachedFileType(narrative);
                if(attachedFileDataType != null){
                    attachedFileDataTypeList.add(attachedFileDataType);
                }
            }
        }
        return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
    }

    /**
     * 
     * This method is used to get List of attachments for IRB-ACUC-LETTERS type
     * from NarrativeAttachment
     * 
     * @return AttachedFileDataType[] array of attached files based on the
     *         Narrative Type Code.
     */
    private AttachedFileDataType[] getIRBACUCLettersFileDataTypes() {
        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
        AttachedFileDataType attachedFileDataType = null;
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
                .getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null
                    && Integer.parseInt(narrative.getNarrativeType().getCode()) == NARRATIVE_IRB_ACUC_LETTERS) {
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
     * {@link NASAOtherProjectInformationDocument} by populating data from the
     * given {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument
     *            for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given
     *         {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(
            ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        answerHeaders = getPropDevQuestionAnswerService().getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber());
        return getNasaOtherProjectInformation();
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
