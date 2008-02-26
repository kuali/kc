/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.bo;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonService;

/**
 * 
 * This class is BO for narrarive
 */
public class Narrative extends KraPersistableBusinessObjectBase {
    private String proposalNumber;
    private Integer moduleNumber;
    private String comments;
    private String contactName;
    private String emailAddress;
    private Integer moduleSequenceNumber;
    private String moduleStatusCode;
    private String moduleTitle;
    private String narrativeTypeCode;
    private String phoneNumber;
    private NarrativeType narrativeType;
    private NarrativeStatus narrativeStatus;
    private String fileName;
    private List<NarrativeUserRights> narrativeUserRights;
    private List<NarrativeAttachment> narrativeAttachmentList;
    transient private FormFile narrativeFile;
    private String institutionalAttachmentTypeCode;
    private boolean viewAttachment;
    private boolean modifyAttachment;
    private String loggedInUserPersonId;

    public Narrative() {
        narrativeAttachmentList = new ArrayList<NarrativeAttachment>(1);
        narrativeUserRights = new ArrayList<NarrativeUserRights>();
        loggedInUserPersonId = findLoggedInUserPersonId();
    }
    
    protected String findLoggedInUserPersonId() {
        String loggedInUser = GlobalVariables.getUserSession().getLoggedInUserNetworkId();
        return getService(ProposalPersonService.class).getPerson(loggedInUser).getPersonId();//get person id for looged in user
    }

    public Integer getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(Integer moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getModuleSequenceNumber() {
        return moduleSequenceNumber;
    }

    public void setModuleSequenceNumber(Integer moduleSequenceNumber) {
        this.moduleSequenceNumber = moduleSequenceNumber;
    }

    public String getModuleStatusCode() {
        return moduleStatusCode;
    }

    public void setModuleStatusCode(String moduleStatusCode) {
        this.moduleStatusCode = moduleStatusCode;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getNarrativeTypeCode() {
        return narrativeTypeCode;
    }

    public void setNarrativeTypeCode(String narrativeTypeCode) {
        this.narrativeTypeCode = narrativeTypeCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("moduleNumber", getModuleNumber());
        hashMap.put("proposalNumber", getProposalNumber());
        hashMap.put("comments", getComments());
        hashMap.put("contactName", getContactName());
        hashMap.put("emailAddress", getEmailAddress());
        hashMap.put("moduleSequenceNumber", getModuleSequenceNumber());
        hashMap.put("moduleStatusCode", getModuleStatusCode());
        hashMap.put("moduleTitle", getModuleTitle());
        hashMap.put("narrativeTypeCode", getNarrativeTypeCode());
        hashMap.put("phoneNumber", getPhoneNumber());
        return hashMap;
    }

    public NarrativeType getNarrativeType() {
        return narrativeType;
    }

    public void setNarrativeType(NarrativeType narrativeType) {
        this.narrativeType = narrativeType;
    }

    public List<NarrativeUserRights> getNarrativeUserRights() {
        return narrativeUserRights;
    }

    public void setNarrativeUserRights(List<NarrativeUserRights> narrativeUserRights) {
        this.narrativeUserRights = narrativeUserRights;
    }

    /**
     * Gets the narrativeStatus attribute.
     * 
     * @return Returns the narrativeStatus.
     */
    public NarrativeStatus getNarrativeStatus() {
        return narrativeStatus;
    }

    /**
     * Sets the narrativeStatus attribute value.
     * 
     * @param narrativeStatus The narrativeStatus to set.
     */
    public void setNarrativeStatus(NarrativeStatus narrativeStatus) {
        this.narrativeStatus = narrativeStatus;
    }

    public List<NarrativeAttachment> getNarrativeAttachmentList() {
        return narrativeAttachmentList;
    }

    public void setNarrativeAttachmentList(List<NarrativeAttachment> narrativePdfList) {
        this.narrativeAttachmentList = narrativePdfList;
    }

    public FormFile getNarrativeFile() {
        return narrativeFile;
    }

    public void setNarrativeFile(FormFile narrativeFile) {
        this.narrativeFile = narrativeFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 
     * This method is to return institutional attachment type.  This column does not exist in table.
     * Basically, it is for narrativetypecode in table. 
     * @return
     */
    public String getInstitutionalAttachmentTypeCode() {
        return narrativeTypeCode;
    }

    /**
     * 
     * This method set the institutional attachment type.  Since instituteattachmenttypecode is set as 'required' in DD.
     * So, it is set to narrativetypecode, and hence to return narrative type code.
     * @param institutionalAttachmentTypeCode
     */
    public void setInstitutionalAttachmentTypeCode(String institutionalAttachmentTypeCode) {
        //this.institutionalAttachmentTypeCode = institutionalAttachmentTypeCode;
        this.narrativeTypeCode = institutionalAttachmentTypeCode;
    }

    /**
     * Gets the view attribute.
     * 
     * @return Returns the view.
     */
    public boolean getViewAttachment() {
        List<NarrativeUserRights> narrativeUserRights = getNarrativeUserRights();
        if(narrativeUserRights.isEmpty())
            refreshReferenceObject("narrativeUserRights");
        for (NarrativeUserRights narrativeRight : narrativeUserRights) {
            if (StringUtils.equals(narrativeRight.getUserId(),loggedInUserPersonId)) {
                return (narrativeRight.getAccessType().equals(
                        NarrativeRight.MODIFY_NARRATIVE_RIGHT.getAccessType()) || 
                        narrativeRight.getAccessType().equals(
                        NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType()));
            }
        }
        return false;
    }

    /**
     * Sets the view attribute value.
     * 
     * @param view The view to set.
     */
    public void setViewAttachment(boolean view) {
        this.viewAttachment = view;
    }

    /**
     * Gets the modify attribute.
     * 
     * @return Returns the modify.
     */
    public boolean getModifyAttachment() {
      List<NarrativeUserRights> narrativeUserRights = getNarrativeUserRights();
      if(narrativeUserRights.isEmpty())
          refreshReferenceObject("narrativeUserRights");
      for (NarrativeUserRights narrativeRight : narrativeUserRights) {
          if (StringUtils.equals(narrativeRight.getUserId(),loggedInUserPersonId)) {
              
              return narrativeRight.getAccessType().equals(
                      NarrativeRight.MODIFY_NARRATIVE_RIGHT.getAccessType());
          }
      }
      return false;
    }

    /**
     * Sets the modify attribute value.
     * 
     * @param modify The modify to set.
     */
    public void setModifyAttachment(boolean modify) {
        this.modifyAttachment = modify;
        // If user has modify right, gets view access as well
        if (modify)
            setViewAttachment(true);
    }

    /**
     * 
     * This method used to populate the attachment to narrative object by reading FormFile 
     */
    public void populateAttachment() {
        FormFile narrativeFile = getNarrativeFile();
        if(narrativeFile==null) return;
        byte[] narrativeFileData;
        try {
            narrativeFileData = narrativeFile.getFileData();
            if (narrativeFileData.length > 0) {
                NarrativeAttachment narrativeAttachment;
                if (getNarrativeAttachmentList().isEmpty()) {
                    narrativeAttachment = new NarrativeAttachment();
                    getNarrativeAttachmentList().add(narrativeAttachment);
                }else {
                    narrativeAttachment = getNarrativeAttachmentList().get(0);
                    if (narrativeAttachment == null) {
                        narrativeAttachment = new NarrativeAttachment();
                        getNarrativeAttachmentList().set(0, narrativeAttachment);
                    }
                }
                String fileName = narrativeFile.getFileName();
                narrativeAttachment.setFileName(fileName);
                narrativeAttachment.setContentType(narrativeFile.getContentType());
                narrativeAttachment.setNarrativeData(narrativeFile.getFileData());
                narrativeAttachment.setProposalNumber(getProposalNumber());
                narrativeAttachment.setModuleNumber(getModuleNumber());
                setFileName(narrativeAttachment.getFileName());
            }else {
                getNarrativeAttachmentList().clear();
            }
        }catch (FileNotFoundException e) {
            getNarrativeAttachmentList().clear();
        }catch (IOException e) {
            getNarrativeAttachmentList().clear();
        }
    }
    
    /**
     * Gets index i from the narrativeUserRights list.
     * 
     * @param index
     * @return Question at index i
     */
    public NarrativeUserRights getNarrativeUserRight(int index) {
        while (getNarrativeUserRights().size() <= index) {
            getNarrativeUserRights().add(new NarrativeUserRights());
        }
        return (NarrativeUserRights) getNarrativeUserRights().get(index);
    }

    public void clearAttachment() {
        getNarrativeAttachmentList().clear();
    }
    
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getNarrativeUserRights());
        return managedLists;
    }
}
