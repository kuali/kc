/*
 * Copyright 2005-2010 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator;


import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType.FileLocation;
import gov.grants.apply.system.globalV10.HashValueDocument.HashValue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xml.security.exceptions.Base64DecodingException;
import org.apache.xml.security.utils.Base64;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.s2s.generator.bo.AttachmentData;
import org.kuali.kra.s2s.generator.impl.GlobalLibraryV1_0Generator;
import org.kuali.kra.s2s.generator.impl.GlobalLibraryV2_0Generator;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.kra.service.SponsorService;
import org.kuali.rice.kns.util.AuditError;

/**
 * 
 * This class defines the Base methods for the Form Generator classes
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class S2SBaseFormGenerator implements S2SFormGenerator {

    private static final Log LOG = LogFactory.getLog(S2SBaseFormGenerator.class);

    private List<AttachmentData> attachments;
    public static final String TYPE_SEPARATOR = "_";
    public static final String KEY_VALUE_SEPARATOR = "-";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String TITLE = "TITLE";
    public static final String MODULE_NUMBER = "M";
    public static final String AREAS_AFFECTED_ABSTRACT_TYPE_CODE="16";
    private static final String NARRATIVE_ATTACHMENT_LIST = "narrativeAttachmentList";
       
    protected static final int ORGANIZATON_NAME_MAX_LENGTH = 60;
    protected static final int DUNS_NUMBER_MAX_LENGTH = 13;
    protected static final int PRIMARY_TITLE_MAX_LENGTH = 45;
    protected static final int CONGRESSIONAL_DISTRICT_MAX_LENGTH = 6;


    protected ProposalDevelopmentDocument pdDoc = null;
    private List<AuditError> auditErrors;

    /*
     * Reference to global library generators are defined here. The actual form generator will decide which object to be used for
     * respective implementations.
     */
    protected GlobalLibraryV1_0Generator globLibV10Generator = new GlobalLibraryV1_0Generator();
    protected GlobalLibraryV2_0Generator globLibV20Generator = new GlobalLibraryV2_0Generator();

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
     * @param type
     * @param moduleNumber
     * @param desc
     * @return
     */
    public String createContentId(Narrative narrative) {
        String retVal = "N-" + narrative.getModuleNumber();
        if(narrative.getNarrativeType()!=null){
            if (narrative.getNarrativeType().getAllowMultiple() != null
                    && narrative.getNarrativeType().getAllowMultiple().equals("Y") &&
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

    public String createContentId(ProposalPersonBiography biography) {
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
    private synchronized static HashValue createHashValue(String hashValueStr) {
        HashValue hashValue = null;
        hashValue = HashValue.Factory.newInstance();
        hashValue.setHashAlgorithm(S2SConstants.HASH_ALGORITHM);
        try {
            hashValue.setByteArrayValue(org.apache.xml.security.utils.Base64.decode(hashValueStr));
        }
        catch (Base64DecodingException e) {
        }
        return hashValue;
    }

    /**
     * 
     * This method is used to encode the hash value based on Message Digest
     * 
     * @param attachment
     * @return Base64.encode(rawDigest) (String)
     */
    protected final static String computeAttachmentHash(byte[] attachment) {
        org.apache.xml.security.Init.init();
        MessageDigest messageDigester;
        try {
            messageDigester = MessageDigest.getInstance(S2SConstants.HASH_ALGORITHM);
            byte[] rawDigest = messageDigester.digest(attachment);
            return Base64.encode(rawDigest);
        }
        catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage(), e);
            return "";
        }
    }

    protected AttachedFileDataType getAttachedFileType(Narrative narrative) {
        AttachedFileDataType attachedFileDataType = null;
        byte[] attachementContent = null;
        narrative.refreshReferenceObject("narrativeAttachmentList");
        if(narrative.getNarrativeAttachmentList()!= null && narrative.getNarrativeAttachmentList().size() > 0 ){
        	attachementContent = narrative.getNarrativeAttachmentList().get(0).getContent();
        }
	    if(attachementContent != null && attachementContent.length > 0 ){
	    	
	        FileLocation fileLocation = FileLocation.Factory.newInstance();
	        String contentId = createContentId(narrative);
	        fileLocation.setHref(contentId);
	    	
	        attachedFileDataType = AttachedFileDataType.Factory.newInstance();
	        attachedFileDataType.setFileLocation(fileLocation);
	        attachedFileDataType.setFileName(narrative.getFileName());
	        attachedFileDataType.setMimeType(S2SConstants.CONTENT_TYPE_OCTET_STREAM);
	        narrative.refreshReferenceObject(NARRATIVE_ATTACHMENT_LIST);
			attachedFileDataType.setHashValue(getHashValue(attachementContent));
	        AttachmentData attachmentData = new AttachmentData();
	        attachmentData.setContent(attachementContent);
	        attachmentData.setContentId(contentId);
	        attachmentData.setContentType(S2SConstants.CONTENT_TYPE_OCTET_STREAM);
	        attachmentData.setFileName(narrative.getFileName());
	        addAttachment(attachmentData);
	    }
        return attachedFileDataType;
    }
    /**
     * 
     * This method is used to get List of Other attachments from NarrativeAttachmentList
     * 
     * @return AttachedFileDataType[] based on the narrative type code.
     */
    protected AttachedFileDataType[] getAttachedFileDataTypes(String narrativeTypeCode) {

        int size = 0;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && narrative.getNarrativeTypeCode().equals(narrativeTypeCode)) {
                size++;
            }
        }
        AttachedFileDataType[] attachedFileDataTypes = new AttachedFileDataType[size];
        int attachments = 0;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && narrative.getNarrativeTypeCode().equals(narrativeTypeCode)) {
                attachedFileDataTypes[attachments] = getAttachedFileType(narrative);
                attachments++;
            }
        }
        return attachedFileDataTypes;
    }
    /**
     * 
     * This method is used to get List of Other attachments from NarrativeAttachmentList
     * 
     * @return AttachedFileDataType[] based on the narrative type code.
     */
    protected AttachedFileDataType getAttachedFileDataType(String narrativeTypeCode) {

        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && narrative.getNarrativeTypeCode().equals(narrativeTypeCode)) {
                return getAttachedFileType(narrative);
            }
        }
        return null;
    }

    /**
     * 
     * This method fetches the attachments for {@link ProposalPerson}. For a given person or rolodex ID, it will fetch the document
     * of required type, also passed alongside as documentType
     * 
     * @param pdDoc {@link ProposalDevelopmentDocument} from which the attachments are to be fetched
     * @param personId ID of the proposal person
     * @param rolodexId Rolodex ID of the person
     * @param documentType type of document thats to be fetched
     * @return {@link AttachedFileDataType} containing the required document
     */
    protected AttachedFileDataType getPernonnelAttachments(ProposalDevelopmentDocument pdDoc, String personId, Integer rolodexId,
            String documentType) {
        boolean personBiographyFound = false;
        for (ProposalPersonBiography proposalPersonBiography : pdDoc.getDevelopmentProposal().getPropPersonBios()) {
            if (personId != null && proposalPersonBiography.getPersonId() != null
                    && proposalPersonBiography.getPersonId().equals(personId)
                    && documentType.equals(proposalPersonBiography.getDocumentTypeCode())) {
                personBiographyFound = true;
            }
            else if (rolodexId != null && proposalPersonBiography.getRolodexId() != null
                    && proposalPersonBiography.getRolodexId().toString().equals(rolodexId.toString())
                    && documentType.equals(proposalPersonBiography.getDocumentTypeCode())) {
                personBiographyFound = true;
            }
            if (personBiographyFound) {
                FileLocation fileLocation = FileLocation.Factory.newInstance();
                String contentId = createContentId(proposalPersonBiography);
                fileLocation.setHref(contentId);
                AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                attachedFileDataType.setFileLocation(fileLocation);
                attachedFileDataType.setFileName(proposalPersonBiography.getFileName());
                attachedFileDataType.setMimeType(S2SConstants.CONTENT_TYPE_OCTET_STREAM);
                proposalPersonBiography.refreshReferenceObject("personnelAttachmentList");
                attachedFileDataType.setHashValue(getHashValue(proposalPersonBiography.getPersonnelAttachmentList().get(0)
                        .getContent()));
                AttachmentData attachmentData = new AttachmentData();
                attachmentData.setContent(proposalPersonBiography.getPersonnelAttachmentList().get(0).getContent());
                attachmentData.setContentId(contentId);
                attachmentData.setContentType(S2SConstants.CONTENT_TYPE_OCTET_STREAM);
                attachmentData.setFileName(proposalPersonBiography.getFileName());
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
    
    protected boolean isSponsorNIH(ProposalDevelopmentDocument document) {
		SponsorService sponsorService = KraServiceLocator
				.getService(SponsorService.class);
		return sponsorService.isSponsorNihMultiplePi(document.getDevelopmentProposal());
	}
    
	protected Narrative saveNarrative(byte[] attachment, String narrativeTypeCode,String fileName,String comment) {
		Narrative narrative = null;
		narrative = new Narrative();
		narrative.setModuleStatusCode("C");
		narrative.setNarrativeTypeCode(narrativeTypeCode);
		narrative.setComments(comment);
		narrative.setModuleTitle(comment);
		NarrativeType narrativeType = new NarrativeType();
		narrativeType.setDescription(comment);
		narrativeType.setSystemGenerated("Y");
		narrativeType.setNarrativeTypeCode(narrativeTypeCode);
		narrative.setNarrativeType(narrativeType);
		narrative.setModuleSequenceNumber(getNextModuleSequenceNumber(pdDoc));
		NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
		narrativeAttachment
				.setContentType(S2SConstants.CONTENT_TYPE_OCTET_STREAM);
		narrativeAttachment.setNarrativeData(attachment);
		narrativeAttachment.setFileName(fileName);
		narrative.setFileName(fileName);
		narrative.getNarrativeAttachmentList().add(narrativeAttachment);
		KraServiceLocator.getService(NarrativeService.class).addNarrative(
					pdDoc, narrative);
		return narrative;
	}
    private Integer getNextModuleSequenceNumber(ProposalDevelopmentDocument proposaldevelopmentDocument) {
        List<Narrative> narrativeList = proposaldevelopmentDocument.getDevelopmentProposal().getNarratives();
        List<Narrative> instituteAttachmentsList = proposaldevelopmentDocument.getDevelopmentProposal().getInstituteAttachments();
        List<Narrative> mergedNarrativeList = new ArrayList<Narrative>();
        mergedNarrativeList.addAll(narrativeList);
        mergedNarrativeList.addAll(instituteAttachmentsList);
        if(mergedNarrativeList.isEmpty()) return 1;
        Collections.sort(mergedNarrativeList, new Comparator<Narrative>(){
            public int compare(Narrative n1, Narrative n2) { 
                return (n1.getModuleSequenceNumber()).compareTo(n2.getModuleSequenceNumber()); 
              } 
        });
        return mergedNarrativeList.get(mergedNarrativeList.size()-1).getModuleSequenceNumber().intValue()+1;
    }
    /**
     * Sets the attachments attribute value.
     * @param attachments The attachments to set.
     */
    public void setAttachments(List<AttachmentData> attachments) {
        this.attachments = attachments;
    }
    
}
