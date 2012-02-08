/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi.personfinancialentity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachment;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class is form helper class for financial entity
 */
public class FinancialEntityHelper implements Serializable {

    private static final long serialVersionUID = -5837128667442140384L;
    private FinancialEntityForm form;
    private PersonFinIntDisclosure newPersonFinancialEntity;
    private FinancialEntityReporterUnit newFinancialEntityReporterUnit;
    private FinancialEntityReporter financialEntityReporter;
    private List<PersonFinIntDisclosure> activeFinancialEntities;
    private List<PersonFinIntDisclosure> inactiveFinancialEntities;
    private List<FinancialEntityReporterUnit> deletedUnits;
    private List<FinIntEntityRelType> finEntityRelationshipTypes;
    private int editEntityIndex;
    private List<FinEntityDataMatrixBean> newRelationDetails;
    private List<FinEntityDataMatrixBean> editRelationDetails;
    private FinancialEntityAttachment newFinEntityAttachment;
    private List<FinancialEntityAttachment> finEntityAttachmentList;
    private Integer newRolodexId;
    private Integer editRolodexId;
    // both prevxxx are hidden, and it will be used by js to check whether sponsor code has been changed
    // when 'sponsorCode' field is onblur 
    private String prevSponsorCode;
    private String prevNewSponsorCode;
    private String editType;
    private List<PersonFinIntDisclosure> versions;
    private String editCoiEntityId;
    private String reporterId;
    
    public String getEditType() {
        return editType;
    }


    public void setEditType(String editType) {
        this.editType = editType;
    }


    public FinancialEntityHelper(FinancialEntityForm form) {
        if (StringUtils.isBlank(reporterId)) {
            reporterId = GlobalVariables.getUserSession().getPrincipalId();
        }
        newPersonFinancialEntity = new PersonFinIntDisclosure();
        newPersonFinancialEntity.setCurrentFlag(true);
        financialEntityReporter = new FinancialEntityReporter();
        newPersonFinancialEntity.setPersonId(reporterId);
        newPersonFinancialEntity.setFinancialEntityReporterId(financialEntityReporter.getFinancialEntityReporterId());
        setNewFinancialEntityReporterUnit(new FinancialEntityReporterUnit());
        activeFinancialEntities = new ArrayList<PersonFinIntDisclosure>();
        inactiveFinancialEntities = new ArrayList<PersonFinIntDisclosure>();
        finEntityRelationshipTypes = getFinancialEntityService().getFinancialEntityRelationshipTypes();
        deletedUnits = new ArrayList<FinancialEntityReporterUnit>(); 
        newRelationDetails = getFinancialEntityService().getFinancialEntityDataMatrix();
        editRelationDetails = new ArrayList<FinEntityDataMatrixBean>(); 
        newFinEntityAttachment = new FinancialEntityAttachment();
        finEntityAttachmentList = new ArrayList<FinancialEntityAttachment>();
        editEntityIndex = -1;
        newRolodexId = -1;
        prevSponsorCode = Constants.EMPTY_STRING;
        prevNewSponsorCode = Constants.EMPTY_STRING;
        this.form = form;
    }


    public FinancialEntityForm getForm() {
        return form;
    }

    public void setForm(FinancialEntityForm form) {
        this.form = form;
    }


    public PersonFinIntDisclosure getNewPersonFinancialEntity() {
        return newPersonFinancialEntity;
    }


    public void setNewPersonFinancialEntity(PersonFinIntDisclosure newPersonFinancialEntity) {
        this.newPersonFinancialEntity = newPersonFinancialEntity;
    }


    public int getEditEntityIndex() {
        return editEntityIndex;
    }


    public void setEditEntityIndex(int editEntityIndex) {
        this.editEntityIndex = editEntityIndex;
    }


    public List<PersonFinIntDisclosure> getActiveFinancialEntities() {
        return activeFinancialEntities;
    }


    public void setActiveFinancialEntities(List<PersonFinIntDisclosure> activeFinancialEntities) {
        this.activeFinancialEntities = activeFinancialEntities;
    }


    public List<PersonFinIntDisclosure> getInactiveFinancialEntities() {
        return inactiveFinancialEntities;
    }


    public void setInactiveFinancialEntities(List<PersonFinIntDisclosure> inactiveFinancialEntities) {
        this.inactiveFinancialEntities = inactiveFinancialEntities;
    }


    public FinancialEntityReporterUnit getNewFinancialEntityReporterUnit() {
        return newFinancialEntityReporterUnit;
    }


    public void setNewFinancialEntityReporterUnit(FinancialEntityReporterUnit newFinancialEntityReporterUnit) {
        this.newFinancialEntityReporterUnit = newFinancialEntityReporterUnit;
        this.newFinancialEntityReporterUnit.setFinancialEntityReporterId(financialEntityReporter.getFinancialEntityReporterId());
        this.newFinancialEntityReporterUnit.setFinancialEntityReporter(financialEntityReporter);
        this.newFinancialEntityReporterUnit.setPersonId(financialEntityReporter.getPersonId());

    }
    
    private FinancialEntityService getFinancialEntityService() {
        return KraServiceLocator.getService(FinancialEntityService.class);
    }

    public void setVersions(PersonFinIntDisclosure personFinIntDisclosure) {
        versions = personFinIntDisclosure.getVersions();
    }
    
    public List<PersonFinIntDisclosure> getVersions() {
        return versions;
    }
    
    public FinancialEntityReporter getFinancialEntityReporter() {
        return financialEntityReporter;
    }    
    private void refreshFinancialEntityReporter() {
        if (StringUtils.isBlank(reporterId)) {
            reporterId = GlobalVariables.getUserSession().getPrincipalId();
        }
        financialEntityReporter = getFinancialEntityService().getFinancialEntityReporter(reporterId);
        newPersonFinancialEntity.setFinancialEntityReporterId(financialEntityReporter.getFinancialEntityReporterId());
    }


    public void setFinancialEntityReporter(FinancialEntityReporter financialEntityReporter) {
        this.financialEntityReporter = financialEntityReporter;
    }


    public List<FinancialEntityReporterUnit> getDeletedUnits() {
        return deletedUnits;
    }


    public void setDeletedUnits(List<FinancialEntityReporterUnit> deletedUnits) {
        this.deletedUnits = deletedUnits;
    }


    public List<FinIntEntityRelType> getFinEntityRelationshipTypes() {
        return finEntityRelationshipTypes;
    }


    public void setFinEntityRelationshipTypes(List<FinIntEntityRelType> finEntityRelationshipTypes) {
        this.finEntityRelationshipTypes = finEntityRelationshipTypes;
    }


    public List<FinEntityDataMatrixBean> getNewRelationDetails() {
        return newRelationDetails;
    }


    public void setNewRelationDetails(List<FinEntityDataMatrixBean> newRelationDetails) {
        this.newRelationDetails = newRelationDetails;
    }


    public List<FinEntityDataMatrixBean> getEditRelationDetails() {
        return editRelationDetails;
    }


    public void setEditRelationDetails(List<FinEntityDataMatrixBean> editRelationDetails) {
        this.editRelationDetails = editRelationDetails;
    }

    
    public FinancialEntityAttachment getNewFinEntityAttachment() {
        return newFinEntityAttachment;
    }


    public void setNewFinEntityAttachment(FinancialEntityAttachment newFinEntityAttachment) {
        this.newFinEntityAttachment = newFinEntityAttachment;
    }

    /**
     * 
     * This method is to initiate the financial helper
     */
    public void initiate() {
        /* TODO : this is if user to re-enter to financial entity page after leaving it for something else
         * trying to clean up whatever left in the session.
         * Try to combine with the 'init' process when this helper is instantiated ?
         * 
         */
        if (StringUtils.isBlank(reporterId)) {
            reporterId = GlobalVariables.getUserSession().getPrincipalId();
        }
        newPersonFinancialEntity = new PersonFinIntDisclosure();
        newPersonFinancialEntity.setCurrentFlag(true);
        newPersonFinancialEntity.setPersonId(reporterId);
        newPersonFinancialEntity.setFinancialEntityReporterId(financialEntityReporter.getFinancialEntityReporterId());
        this.setActiveFinancialEntities(getFinancialEntities(true));
        this.setInactiveFinancialEntities(getFinancialEntities(false));
        this.refreshFinancialEntityReporter();
        this.setNewFinancialEntityReporterUnit(new FinancialEntityReporterUnit());
        newRelationDetails = getFinancialEntityService().getFinancialEntityDataMatrix();
        editRelationDetails = new ArrayList<FinEntityDataMatrixBean>(); 
        editEntityIndex = -1;
        prevSponsorCode = Constants.EMPTY_STRING;
        prevNewSponsorCode = Constants.EMPTY_STRING;
        newRolodexId = -1;
        editRolodexId = -1;
    }
    
    private List<PersonFinIntDisclosure> getFinancialEntities(boolean active) {
        if (StringUtils.isBlank(reporterId)) {
            reporterId = GlobalVariables.getUserSession().getPrincipalId();
        }
        return getFinancialEntityService().getFinancialEntities(reporterId, active);
    }


    public Integer getNewRolodexId() {
        return newRolodexId;
    }


    public void setNewRolodexId(Integer newRolodexId) {
        this.newRolodexId = newRolodexId;
    }


    public String getPrevSponsorCode() {
        return prevSponsorCode;
    }


    public void setPrevSponsorCode(String prevSponsorCode) {
        this.prevSponsorCode = prevSponsorCode;
    }


    public Integer getEditRolodexId() {
        return editRolodexId;
    }


    public void setEditRolodexId(Integer editRolodexId) {
        this.editRolodexId = editRolodexId;
    }


    public String getPrevNewSponsorCode() {
        return prevNewSponsorCode;
    }


    public void setPrevNewSponsorCode(String prevNewSponsorCode) {
        this.prevNewSponsorCode = prevNewSponsorCode;
    }
    
    public void resetPrevSponsorCode() {
        if (StringUtils.equals(FinancialEntityAction.ACTIVATE_ENTITY, this.getEditType())) {
            setPrevSponsorCode(getActiveFinancialEntities().get(editEntityIndex).getSponsorCode());
        } else {
            setPrevSponsorCode(getInactiveFinancialEntities().get(editEntityIndex).getSponsorCode());
        }
    }


    public String getEditCoiEntityId() {
        return editCoiEntityId;
    }


    public void setEditCoiEntityId(String editCoiEntityId) {
        this.editCoiEntityId = editCoiEntityId;
    }


    public String getReporterId() {
        return reporterId;
    }


    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    
    public void addNewFinancialEntityAttachment() {
        /**
         * Adds the "new" financialEntityAttachment to the FinancialEntity Document.  Before
         * adding this method executes validation.  If the validation fails the attachment is not added.    
         */
        syncNewFile(getNewFinEntityAttachment());

        newFinEntityAttachment.setFinancialEntityId(getNewPersonFinancialEntity().getPersonFinIntDisclosureId()); 
        newFinEntityAttachment.setSequenceNumber(getNewPersonFinancialEntity().getSequenceNumber());
        newFinEntityAttachment.updateParms();
        getFinEntityAttachmentList().add(newFinEntityAttachment);
        newFinEntityAttachment = new FinancialEntityAttachment();
    }

    protected void syncNewFile(FinancialEntityAttachment attachment) {
        assert attachment != null : "the attachment is null";

        if (doesNewFileExist(attachment)) {
            AttachmentFile newFile = AttachmentFile.createFromFormFile(attachment.getNewFile());
            //setting the sequence number to the old file sequence number
            if (attachment.getFile() != null) {
                newFile.setSequenceNumber(attachment.getFile().getSequenceNumber());
            }
            attachment.setFile(newFile);
            // set to null, so the subsequent post will not create new file again
            attachment.setNewFile(null);
        }
    }

    private static boolean doesNewFileExist(FinancialEntityAttachment attachment) {
        assert attachment != null : "the attachment was null";
        return attachment.getNewFile() != null && StringUtils.isNotBlank(attachment.getNewFile().getFileName());
    }

    public List<FinancialEntityAttachment> getFinEntityAttachmentList() {
        return finEntityAttachmentList;
    }


    public void setFinEntityAttachmentList(List<FinancialEntityAttachment> finEntityAttachmentList) {
        this.finEntityAttachmentList = finEntityAttachmentList;
    }

 }
