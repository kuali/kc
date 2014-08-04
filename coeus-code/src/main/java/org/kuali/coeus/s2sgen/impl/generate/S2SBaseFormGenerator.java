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
package org.kuali.coeus.s2sgen.impl.generate;


import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType.FileLocation;
import gov.grants.apply.system.globalV10.HashValueDocument.HashValue;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.impl.util.Base64;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerContract;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.common.questionnaire.api.core.QuestionAnswerService;
import org.kuali.coeus.propdev.api.questionnaire.PropDevQuestionAnswerService;
import org.kuali.coeus.propdev.api.person.attachment.ProposalPersonBiographyContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeService;
import org.kuali.coeus.s2sgen.api.core.InfastructureConstants;
import org.kuali.coeus.s2sgen.api.generate.AttachmentData;
import org.kuali.coeus.s2sgen.api.generate.FormMappingInfo;
import org.kuali.coeus.s2sgen.api.generate.FormMappingService;
import org.kuali.coeus.s2sgen.impl.generate.support.GlobalLibraryV1_0Generator;
import org.kuali.coeus.s2sgen.impl.generate.support.GlobalLibraryV2_0Generator;
import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class defines the Base methods for the Form Generator classes
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class S2SBaseFormGenerator implements S2SFormGenerator, InitializingBean, BeanNameAware {

    private static final Log LOG = LogFactory.getLog(S2SBaseFormGenerator.class);

    protected static final String NOT_ANSWERED = "No";
    protected static final String KEY_VALUE_SEPARATOR = "-";
    protected static final String AREAS_AFFECTED_ABSTRACT_TYPE_CODE="16";
    protected static final int ORGANIZATON_NAME_MAX_LENGTH = 60;
    protected static final int DUNS_NUMBER_MAX_LENGTH = 13;
    protected static final int PRIMARY_TITLE_MAX_LENGTH = 45;
    protected static final int CONGRESSIONAL_DISTRICT_MAX_LENGTH = 6;
    private static final String NARRATIVE_ATTACHMENT_FILE_LOCATION = "att:FileLocation";
    protected static final String DEFAULT_SORT_INDEX = "1000";

    protected ProposalDevelopmentDocumentContract pdDoc = null;
    private List<AuditError> auditErrors = new ArrayList<>();
    private List<AttachmentData> attachments = new ArrayList<>();

    private String beanName;

    @Autowired
    @Qualifier("narrativeService")
    private NarrativeService narrativeService;

    @Autowired
    @Qualifier("propDevQuestionAnswerService")
    private PropDevQuestionAnswerService propDevQuestionAnswerService;

    @Autowired
    @Qualifier("questionAnswerService")
    private QuestionAnswerService questionAnswerService;

    @Autowired
    @Qualifier("sponsorHierarchyService")
    private SponsorHierarchyService sponsorHierarchyService;

    @Autowired
    @Qualifier("formMappingService")
    private FormMappingService formMappingService;

    /*
     * Reference to global library generators are defined here. The actual form generator will decide which object to be used for
     * respective implementations.
     */
    @Autowired
    @Qualifier("GlobalLibraryV1_0Generator")
    protected GlobalLibraryV1_0Generator globLibV10Generator;

    @Autowired
    @Qualifier("GlobalLibraryV2_0Generator")
    protected GlobalLibraryV2_0Generator globLibV20Generator;

    /**
     * Gets the list of attachments associated with a form. As the form generator fills the form data, the attachment information is
     * stored into the instance variable
     * 
     * @return List<AttachementData> List of attachments associated with the the form. Returns an empty list if no attachment is
     *         available.
     */

    public List<AttachmentData> getAttachments() {
        return attachments;
    }

    /**
     * 
     * This is for adding attachment for the forms during form generation.
     * 
     * @param attachment - The attachment data to add.
     */
    protected void addAttachment(AttachmentData attachment) {
        attachments.add(attachment);
    }

    /**
     * 
     * This method is used to generate the HashValue for a particular file stream. The hashing algorithm is defined in constant
     * S2SConstants.HASH_ALGORITHM
     * 
     * @param fileData - They byte[] containing the file data.
     * @return HashValue - The hash value calculated for the fileData input.
     */
    protected HashValue getHashValue(byte[] fileData) {
        return createHashValue(computeAttachmentHash(fileData));
    }
    /**
     * 
     * Generates the contentId or href for narrative attachments in S2S
     */
    public String createContentId(NarrativeContract narrative) {
        String retVal = "N-" + narrative.getModuleNumber();
        if(narrative.getNarrativeType()!=null){
            if (narrative.getNarrativeType().isAllowMultiple() &&
                    StringUtils.isNotBlank(narrative.getModuleTitle())) {
                retVal += "_" + narrative.getModuleTitle().trim();
            }else{
                retVal += "_" + narrative.getNarrativeType().getDescription().trim();
            }
        }
        int index = getIndexOfAttachmentAlreadyAdded(retVal);
        if(index > 0){
            retVal+=("-"+index);
        }
        return retVal;
    }
    
    private int getIndexOfAttachmentAlreadyAdded(String contentId) {
        int index = 0;
        List<AttachmentData> attachments = getAttachments();
        for (AttachmentData attachmentData : attachments) {
            String attContentId = attachmentData.getContentId();
            int lastIndex = attContentId.indexOf('-',6);
            if(lastIndex!=-1){
                attContentId = attContentId.substring(0, attContentId.lastIndexOf('-'));
            }
            if(attContentId.equals(contentId)){
                index++;
            }
        }
        return index;
    }

    public String createContentId(ProposalPersonBiographyContract biography) {
        String retVal = "B-" + biography.getProposalPersonNumber() + "_" + biography.getBiographyNumber();
        if(biography.getPropPerDocType() != null) 
            retVal += "_" + biography.getPropPerDocType().getDescription().trim();
        if (StringUtils.isNotBlank(biography.getDescription())) {
            retVal += "_" + biography.getDescription().trim();
        }
        int index = getIndexOfAttachmentAlreadyAdded(retVal);
        if(index > 0){
            retVal+=index;
        }
        return retVal;
    }



    /**
     * 
     * This method creates and returns Hash Value for particular form
     * 
     * @param hashValueStr
     * @return hashValue (HashValue)
     * 
     */
    private synchronized static HashValue createHashValue(byte[] hashValueStr) {
        HashValue hashValue = null;
        hashValue = HashValue.Factory.newInstance();
        hashValue.setHashAlgorithm(InfastructureConstants.HASH_ALGORITHM);
        hashValue.setByteArrayValue(Base64.decode(hashValueStr));
        return hashValue;
    }

    /**
     * 
     * This method is used to encode the hash value based on Message Digest
     * 
     * @param attachment
     * @return Base64.encode(rawDigest) (String)
     */
    protected final static byte[] computeAttachmentHash(byte[] attachment) {
        MessageDigest messageDigester;
        try {
            messageDigester = MessageDigest.getInstance(InfastructureConstants.HASH_ALGORITHM);
            byte[] rawDigest = messageDigester.digest(attachment);
            return Base64.encode(rawDigest);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    protected AttachedFileDataType getAttachedFileType(NarrativeContract narrative) {
        AttachedFileDataType attachedFileDataType = null;
        byte[] attachementContent = null;
        if(narrative.getNarrativeAttachment()!= null){
        	attachementContent = narrative.getNarrativeAttachment().getData();
        }
	    if(attachementContent != null && attachementContent.length > 0 ){
	    	
	        FileLocation fileLocation = FileLocation.Factory.newInstance();
	        String contentId = createContentId(narrative);
	        fileLocation.setHref(contentId);
	    	
	        attachedFileDataType = AttachedFileDataType.Factory.newInstance();
	        attachedFileDataType.setFileLocation(fileLocation);
	        attachedFileDataType.setFileName(narrative.getNarrativeAttachment().getName());
	        attachedFileDataType.setMimeType(InfastructureConstants.CONTENT_TYPE_OCTET_STREAM);
			attachedFileDataType.setHashValue(getHashValue(attachementContent));
	        AttachmentData attachmentData = new AttachmentData();
	        attachmentData.setContent(attachementContent);
	        attachmentData.setContentId(contentId);
	        attachmentData.setContentType(InfastructureConstants.CONTENT_TYPE_OCTET_STREAM);
	        attachmentData.setFileName(narrative.getNarrativeAttachment().getName());
	        addAttachment(attachmentData);
	    }
        return attachedFileDataType;
    }
    /**
     * 
     * This method is used to get List of Other attachments from NarrativeAttachment
     * 
     * @return AttachedFileDataType[] based on the narrative type code.
     */
    protected AttachedFileDataType[] getAttachedFileDataTypes(String narrativeTypeCode) {

        int size = 0;
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType() != null
                    && narrative.getNarrativeType().getCode().equals(narrativeTypeCode)) {
                size++;
            }
        }
        AttachedFileDataType[] attachedFileDataTypes = new AttachedFileDataType[size];
        int attachments = 0;
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType() != null
                    && narrative.getNarrativeType().getCode().equals(narrativeTypeCode)) {
                attachedFileDataTypes[attachments] = getAttachedFileType(narrative);
                attachments++;
            }
        }
        return attachedFileDataTypes;
    }
    /**
     * 
     * This method is used to get List of Other attachments from NarrativeAttachment
     * 
     * @return AttachedFileDataType[] based on the narrative type code.
     */
    protected AttachedFileDataType getAttachedFileDataType(String narrativeTypeCode) {

        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType() != null
                    && narrative.getNarrativeType().getCode().equals(narrativeTypeCode)) {
                return getAttachedFileType(narrative);
            }
        }
        return null;
    }

    /**
     * 
     * This method fetches the attachments for {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}. For a given person or rolodex ID, it will fetch the document
     * of required type, also passed alongside as documentType
     * 
     * @param pdDoc {@link ProposalDevelopmentDocumentContract} from which the attachments are to be fetched
     * @param personId ID of the proposal person
     * @param rolodexId Rolodex ID of the person
     * @param documentType type of document thats to be fetched
     * @return {@link AttachedFileDataType} containing the required document
     */
    protected AttachedFileDataType getPernonnelAttachments(ProposalDevelopmentDocumentContract pdDoc, String personId, Integer rolodexId,
            String documentType) {
        boolean personBiographyFound = false;
        for (ProposalPersonBiographyContract proposalPersonBiography : pdDoc.getDevelopmentProposal().getPropPersonBios()) {
            if (personId != null && proposalPersonBiography.getPersonId() != null
                    && proposalPersonBiography.getPersonId().equals(personId)
                    && documentType.equals(proposalPersonBiography.getPropPerDocType().getCode())) {
                personBiographyFound = true;
            }
            else if (rolodexId != null && proposalPersonBiography.getRolodexId() != null
                    && proposalPersonBiography.getRolodexId().toString().equals(rolodexId.toString())
                    && proposalPersonBiography.getPropPerDocType() != null
                    && documentType.equals(proposalPersonBiography.getPropPerDocType().getCode())) {
                personBiographyFound = true;
            }
            if (personBiographyFound) {
                FileLocation fileLocation = FileLocation.Factory.newInstance();
                String contentId = createContentId(proposalPersonBiography);
                fileLocation.setHref(contentId);
                AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                attachedFileDataType.setFileLocation(fileLocation);
                attachedFileDataType.setFileName(proposalPersonBiography.getName());
                attachedFileDataType.setMimeType(InfastructureConstants.CONTENT_TYPE_OCTET_STREAM);
                attachedFileDataType.setHashValue(getHashValue(proposalPersonBiography.getPersonnelAttachment()
                        .getData()));
                AttachmentData attachmentData = new AttachmentData();
                attachmentData.setContent(proposalPersonBiography.getPersonnelAttachment().getData());
                attachmentData.setContentId(contentId);
                attachmentData.setContentType(InfastructureConstants.CONTENT_TYPE_OCTET_STREAM);
                attachmentData.setFileName(proposalPersonBiography.getName());
                addAttachment(attachmentData);
                return attachedFileDataType;
            }
        }
        return null;
    }

    /**
     * Gets the auditErrors attribute. 
     * @return Returns the auditErrors.
     */
    public List<AuditError> getAuditErrors() {
        return auditErrors;
    }

    /**
     * Sets the auditErrors attribute value.
     * @param auditErrors The auditErrors to set.
     */
    public void setAuditErrors(List<AuditError> auditErrors) {
        this.auditErrors = auditErrors;
    }
    
    protected boolean isSponsorNIH(ProposalDevelopmentDocumentContract document) {
		return sponsorHierarchyService.isSponsorNihMultiplePi(document.getDevelopmentProposal().getSponsor().getSponsorCode());
	}
    
	protected NarrativeContract saveNarrative(byte[] attachment, String narrativeTypeCode,String fileName,String comment) {
        return narrativeService.createSystemGeneratedNarrative(pdDoc.getDevelopmentProposal().getProposalNumber(), narrativeTypeCode, attachment, fileName, comment);
	}

    /**
     * Sets the attachments attribute value.
     * @param attachments The attachments to set.
     */
    public void setAttachments(List<AttachmentData> attachments) {
        this.attachments = attachments;
    }

    /**
     * Sort the attachments.
     * @param byteArrayInputStream
     */
    public void sortAttachments(ByteArrayInputStream byteArrayInputStream)  {
        List<String> attachmentNameList = new ArrayList<String> ();
        List<AttachmentData> attacmentList = getAttachments();
        List<AttachmentData> tempAttacmentList = new ArrayList<AttachmentData>();
        
        try{
            DocumentBuilderFactory domParserFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder domParser = domParserFactory.newDocumentBuilder();
            Document document = domParser.parse(byteArrayInputStream);
            byteArrayInputStream.close();
            NodeList fileLocationList = document.getElementsByTagName(NARRATIVE_ATTACHMENT_FILE_LOCATION);                       
            
           for(int itemLocation=0;itemLocation<fileLocationList.getLength();itemLocation++){
               String attachmentName =fileLocationList.item(itemLocation).getAttributes().item(0).getNodeValue();
               String[] name = attachmentName.split(KEY_VALUE_SEPARATOR);               
               String fileName =name[name.length-1];
               attachmentNameList.add(fileName);
           }
        }catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        
        for(String attachmentName :attachmentNameList){
            for(AttachmentData attachment : attacmentList){                
                String[] names = attachment.getContentId().split(KEY_VALUE_SEPARATOR);               
                String fileName =names[names.length-1];
                if(fileName.equalsIgnoreCase(attachmentName)){
                    tempAttacmentList.add(attachment);
                }
            }
        }
        if(tempAttacmentList.size() > 0){
            attachments.clear();
            for(AttachmentData tempAttachment :tempAttacmentList){
                attachments.add(tempAttachment);
            } 
        }
    }

    /**
     *
     * This method is used to get the answer for a particular Questionnaire question
     * question based on the question id.
     *
     * @param questionSeqId
     *            the question seq id to be passed.
     * @return returns the answer for a particular
     *         question based on the question id passed.
     */
    protected String getAnswer(Integer questionSeqId, List<? extends AnswerHeaderContract> answerHeaders) {
        for(AnswerHeaderContract answerHeader:answerHeaders){
            if(answerHeader!=null){
                List<? extends AnswerContract> answerDetails = answerHeader.getAnswers();
                for(AnswerContract answers:answerDetails){
                    if(questionSeqId.equals(getQuestionAnswerService().findQuestionById(answers.getQuestionId()).getQuestionSeqId())){
                        return answers.getAnswer();
                    }
                }
            }
        }

        return null;
    }

    /**
     *
     * This method is used to get the answerId for a particular Questionnaire question
     * question based on the question id.
     *
     * @param questionSeqId
     *            the question seq id to be passed.
     * @return returns the answer for a particular
     *         question based on the question id passed.
     */
    protected Long getAnswerId(Integer questionSeqId, List<? extends AnswerHeaderContract> answerHeaders) {
        if (answerHeaders != null && !answerHeaders.isEmpty()) {
            for (AnswerHeaderContract answerHeader : answerHeaders) {
                List<? extends AnswerContract> answerDetails = answerHeader.getAnswers();
                for (AnswerContract answers : answerDetails) {
                    if (answers.getAnswer() != null && questionSeqId.equals(getQuestionAnswerService().findQuestionById(answers.getQuestionId()).getQuestionSeqId())) {
                        return answers.getId();
                    }
                }
            }
        }
        return null;
    }
    /**
     *
     * This method is used to get the child question answer for a particular Questionnaire question
     * question based on the question id.
     * @param parentQuestionSeqId
     *            the parentQuestion id to be passed.
     * @param questionSeqId
     *            the question id to be passed.
     * @return returns the answer for a particular
     *         question based on the question id passed.
     */
    protected String getChildQuestionAnswer(Integer parentQuestionSeqId,Integer questionSeqId, List<? extends AnswerHeaderContract> answerHeaders) {
        for(AnswerHeaderContract answerHeader:answerHeaders){
            if(answerHeader!=null){
                List<? extends AnswerContract> answerDetails = answerHeader.getAnswers();
                for(AnswerContract answers:answerDetails){
                    if(answers.getParentAnswers()!= null){
                        AnswerContract parentAnswer =  answers.getParentAnswers().get(0);
                        if(questionSeqId.equals(getQuestionAnswerService().findQuestionById(answers.getQuestionId()).getQuestionSeqId()) &&
                                parentQuestionSeqId.equals(getQuestionAnswerService().findQuestionById(parentAnswer.getQuestionId()).getQuestionSeqId()) ){
                            return answers.getAnswer();
                        }
                    }
                }
            }
        }

        return null;

    }

    /*
  * This method will get the childAnswer for sub question
  */
    protected String getAnswers(Integer questionSeqId, List<? extends AnswerHeaderContract> answerHeaders) {

        String answer = null;
        String childAnswer = null;
        StringBuilder stringBuilder = new StringBuilder();
        if (answerHeaders != null && !answerHeaders.isEmpty()) {
            for (AnswerHeaderContract answerHeader : answerHeaders) {
                List<? extends AnswerContract> answerDetails = answerHeader.getAnswers();
                for (AnswerContract answers : answerDetails) {
                    if (questionSeqId.equals(getQuestionAnswerService().findQuestionById(answers.getQuestionId()).getQuestionSeqId())) {
                        answer = answers.getAnswer();
                        if (answer != null) {
                            if (!answer.equals(NOT_ANSWERED)) {
                                stringBuilder.append(answer);
                                stringBuilder.append(",");
                            }
                        }
                        childAnswer = stringBuilder.toString();
                    }
                }
            }
        }
        return childAnswer;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public abstract String getNamespace();

    public String getGeneratorName() {
        return beanName;
    }

    public abstract String getFormName();

    public abstract Resource getStylesheet();

    public abstract String getPackageName();

    public abstract int getSortIndex();

    public NarrativeService getNarrativeService() {
        return narrativeService;
    }

    public PropDevQuestionAnswerService getPropDevQuestionAnswerService() {
        return propDevQuestionAnswerService;
    }

    public QuestionAnswerService getQuestionAnswerService() {
        return questionAnswerService;
    }

    public void setNarrativeService(NarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    public void setPropDevQuestionAnswerService(PropDevQuestionAnswerService propDevQuestionAnswerService) {
        this.propDevQuestionAnswerService = propDevQuestionAnswerService;
    }

    public void setQuestionAnswerService(QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = questionAnswerService;
    }

    public SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }

    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
    }

    public FormMappingService getFormMappingService() {
        return formMappingService;
    }

    public void setFormMappingService(FormMappingService formMappingService) {
        this.formMappingService = formMappingService;
    }

    public GlobalLibraryV1_0Generator getGlobLibV10Generator() {
        return globLibV10Generator;
    }

    public void setGlobLibV10Generator(GlobalLibraryV1_0Generator globLibV10Generator) {
        this.globLibV10Generator = globLibV10Generator;
    }

    public GlobalLibraryV2_0Generator getGlobLibV20Generator() {
        return globLibV20Generator;
    }

    public void setGlobLibV20Generator(GlobalLibraryV2_0Generator globLibV20Generator) {
        this.globLibV20Generator = globLibV20Generator;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        formMappingService.registerForm(new FormMappingInfo(getNamespace(), getGeneratorName(), getFormName(), getStylesheet().getURL().getPath(), getSortIndex(), false));
    }
}
