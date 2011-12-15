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
package org.kuali.kra.negotiations.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.kra.negotiations.customdata.NegotiationCustomData;

/**
 * 
 * This class handles the negotiation BO.
 */
public class Negotiation extends KraPersistableBusinessObjectBase implements Permissionable {

    private static final long MILLISECS_PER_DAY = 24 * 60 * 60 * 1000;

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2529772854773433195L;

    private Long negotiationId;
    private String documentNumber;
    private Long negotiationStatusId;
    private Long negotiationAgreementTypeId;
    private Long negotiationAssociationTypeId;
    private String negotiatorPersonId;
    private String negotiatorName;
    private Date negotiationStartDate;
    private Date negotiationEndDate;
    private Date anticipatedAwardDate;
    private String documentFolder;
    private String allAttachments;

    // transient
    private String negotiatorUserName;

    private NegotiationUnassociatedDetail unAssociatedDetail;
    private List<NegotiationCustomData> negotiationCustomDataList;
    private Negotiable associatedDocument;

    private NegotiationDocument negotiationDocument;

    /**
     * Long awardId - award String proposalNumber -developmentProposal Long proposalId - institutionalProposal
     */
    private String associatedDocumentId;
    //transient - workaround for inability to actually display warning on final document unless an error exists.
    private String associatedDocumentWarning;

    private NegotiationStatus negotiationStatus;
    private NegotiationAgreementType negotiationAgreementType;
    private NegotiationAssociationType negotiationAssociationType;

    private List<NegotiationActivity> activities;
    
    private int printindex;
    private boolean printAll = true;
    private Long oldNegotiationAssociationTypeId;

    public int getPrintindex() {
        return printindex;
    }

    public void setPrintindex(int printindex) {
        this.printindex = printindex;
    }

    public Negotiation() {
        super();
        activities = new ArrayList<NegotiationActivity>();
        negotiationCustomDataList = new ArrayList<NegotiationCustomData>();
    }

    public Integer getNegotiationAge() {
        if (getNegotiationStartDate() == null) {
            return null;
        }
        else {
            long start = getNegotiationStartDate().getTime();
            long end = 0L;
            if (getNegotiationEndDate() == null) {
                end = Calendar.getInstance().getTimeInMillis();
            }
            else {
                end = getNegotiationEndDate().getTime();
            }

            return new Long((end - start) / MILLISECS_PER_DAY).intValue();
        }
    }

    public String getAllAttachments() {
        return allAttachments;
    }

    public void setAllAttachments(String allAttachments) {
        this.allAttachments = allAttachments;
    }

    public Long getNegotiationId() {
        return negotiationId;
    }


    public void setNegotiationId(Long negotiationId) {
        this.negotiationId = negotiationId;
    }


    public String getDocumentNumber() {
        return documentNumber;
    }


    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }


    public Long getNegotiationStatusId() {
        return negotiationStatusId;
    }


    public void setNegotiationStatusId(Long negotiationStatusId) {
        this.negotiationStatusId = negotiationStatusId;
    }


    public Long getNegotiationAgreementTypeId() {
        return negotiationAgreementTypeId;
    }


    public void setNegotiationAgreementTypeId(Long negotiationAgreementTypeId) {
        this.negotiationAgreementTypeId = negotiationAgreementTypeId;
    }


    public Long getNegotiationAssociationTypeId() {
        return negotiationAssociationTypeId;
    }


    public void setNegotiationAssociationTypeId(Long negotiationAssociationTypeId) {
        this.negotiationAssociationTypeId = negotiationAssociationTypeId;
    }


    public String getNegotiatorPersonId() {
        return negotiatorPersonId;
    }


    public void setNegotiatorPersonId(String negotiatorPersonId) {
        this.negotiatorPersonId = negotiatorPersonId;
        if (getNegotiator() != null) {
            setNegotiatorName(getNegotiator().getFullName());
        }
    }

    public KcPerson getNegotiator() {
        if (this.getNegotiatorPersonId() == null) {
            return null;
        }
        else {
            try {
                return getKcPersonService().getKcPersonByPersonId(this.getNegotiatorPersonId());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    public String getNegotiatorUserName() {
        KcPerson negotiator = getNegotiator();
        if (negotiator == null) {
            return negotiatorUserName;
        }
        else {
            return negotiator.getUserName();
        }
    }

    public void setNegotiatorUserName(String negotiatorUserName) {
        this.negotiatorUserName = negotiatorUserName;
        KcPerson negotiator = null;
        try {
            negotiator = getKcPersonService().getKcPersonByUserName(negotiatorUserName);
        }
        catch (IllegalArgumentException e) {
            // invalid username, will be caught by validation routines
        }
        if (negotiator != null) {
            setNegotiatorPersonId(negotiator.getPersonId());
        }
        else {
            setNegotiatorPersonId(null);
        }
    }

    public Date getNegotiationStartDate() {
        if (negotiationStartDate == null || negotiationStartDate.equals(""))
        {
            Calendar now = Calendar.getInstance();
            setNegotiationStartDate (new java.sql.Date (now.get(Calendar.YEAR)-1900, now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)));
        }
        return negotiationStartDate;
    }


    public void setNegotiationStartDate(Date negotiationStartDate) {
        this.negotiationStartDate = negotiationStartDate;
    }


    public Date getNegotiationEndDate() {
        return negotiationEndDate;
    }


    public void setNegotiationEndDate(Date negotiationEndDate) {
        this.negotiationEndDate = negotiationEndDate;
    }


    public Date getAnticipatedAwardDate() {
        return anticipatedAwardDate;
    }


    public void setAnticipatedAwardDate(Date anticipatedAwardDate) {
        this.anticipatedAwardDate = anticipatedAwardDate;
    }


    public String getDocumentFolder() {
        return documentFolder;
    }


    public void setDocumentFolder(String documentFolder) {
        this.documentFolder = documentFolder;
    }


    public String getAssociatedDocumentId() {
        return associatedDocumentId;
    }


    public void setAssociatedDocumentId(String associatedDocumentId) {
        this.associatedDocumentId = associatedDocumentId;
    }


    public NegotiationStatus getNegotiationStatus() {
        return negotiationStatus;
    }


    public void setNegotiationStatus(NegotiationStatus negotiationStatus) {
        this.negotiationStatus = negotiationStatus;
    }


    public NegotiationAgreementType getNegotiationAgreementType() {
        return negotiationAgreementType;
    }


    public void setNegotiationAgreementType(NegotiationAgreementType negotiationAgreementType) {
        this.negotiationAgreementType = negotiationAgreementType;
    }


    public NegotiationAssociationType getNegotiationAssociationType() {
        return negotiationAssociationType;
    }


    public void setNegotiationAssociationType(NegotiationAssociationType negotiationAssociationType) {
        this.negotiationAssociationType = negotiationAssociationType;
    }

    public NegotiationUnassociatedDetail getUnAssociatedDetail() {
        if (unAssociatedDetail == null) {
            unAssociatedDetail = getNegotiationService().findAndLoadNegotiationUnassociatedDetail(this);
        }
        return unAssociatedDetail;
    }

    public void setUnAssociatedDetail(NegotiationUnassociatedDetail unAssociatedDetail) {
        this.unAssociatedDetail = unAssociatedDetail;
    }


    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("negotiationId", this.getNegotiationId());
        return map;
    }

    public List<NegotiationActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<NegotiationActivity> activities) {
        this.activities = activities;
    }

    @Override
    public String getDocumentNumberForPermission() {
        return this.negotiationId != null ? this.negotiationId.toString() : null;
    }

    @Override
    public String getDocumentKey() {
        return Permissionable.NEGOTIATION_KEY;
    }

    @Override
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();
        roleNames.add(RoleConstants.NEGOTIATION_COI);
        roleNames.add(RoleConstants.NEGOTIATION_KP);
        roleNames.add(RoleConstants.NEGOTIATION_NEGOTIATION_ADMINISTRATOR);
        roleNames.add(RoleConstants.NEGOTIATION_NEGOTIATOR);
        roleNames.add(RoleConstants.NEGOTIATION_PI);
        return roleNames;
    }

    @Override
    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_NEGOTIATION;
    }

    @Override
    public String getLeadUnitNumber() {
        Negotiable bo = getAssociatedDocument();
        if (bo != null) {
            ((BusinessObject) bo).refresh();
            return bo.getLeadUnitNumber();
        }
        else {
            return "";
        }
    }

    public Negotiable getAssociatedNegotiable() {
        return getNegotiationService().getAssociatedObject(this);
    }

    private NegotiationService getNegotiationService() {
        return KraServiceLocator.getService(NegotiationService.class);
    }

    @Override
    public String getDocumentRoleTypeCode() {
        return RoleConstants.NEGOTIATION_ROLE_TYPE;
    }

    @Override
    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        qualifiedRoleAttributes.put(KcKimAttributes.DOCUMENT_TYPE_NAME, this.getDocumentKey());
    }

    /**
     * Gets the negotiationCustomDataList attribute.
     * 
     * @return Returns the negotiationCustomDataList.
     */
    public List<NegotiationCustomData> getNegotiationCustomDataList() {
        return negotiationCustomDataList;
    }

    /**
     * Sets the negotiationCustomDataList attribute value.
     * 
     * @param negotiationCustomDataList The negotiationCustomDataList to set.
     */
    public void setNegotiationCustomDataList(List<NegotiationCustomData> negotiationCustomDataList) {
        this.negotiationCustomDataList = negotiationCustomDataList;
    }


    public NegotiationDocument getDocument() {
        return negotiationDocument;
    }

    public NegotiationDocument getNegotiationDocument() {
        return negotiationDocument;
    }

    public void setNegotiationDocument(NegotiationDocument negotiationDocument) {
        this.negotiationDocument = negotiationDocument;
    }

    public Negotiable getAssociatedDocument() {
        if (associatedDocument == null
                || !StringUtils.equals(associatedDocument.getAssociatedDocumentId(), getAssociatedDocumentId())) {
            associatedDocument = getNegotiationService().getAssociatedObject(this);
        }
        return associatedDocument;
    }

    public void setAssociatedDocument(Negotiable associatedDocument) {
        this.associatedDocument = associatedDocument;
    }

    public String getNegotiatorName() {
        return negotiatorName;
    }

    public void setNegotiatorName(String negotiatorName) {
        this.negotiatorName = negotiatorName;
    }

    public boolean isPrintAll() {
        return printAll;
    }

    public void setPrintAll(boolean printAll) {
        this.printAll = printAll;
    }

    public Long getOldNegotiationAssociationTypeId() {
        return oldNegotiationAssociationTypeId;
    }

    public void setOldNegotiationAssociationTypeId(Long oldNegotiationAssociationTypeId) {
        this.oldNegotiationAssociationTypeId = oldNegotiationAssociationTypeId;
    }

    public String getAssociatedDocumentWarning() {
        return associatedDocumentWarning;
    }

    public void setAssociatedDocumentWarning(String associatedDocumentWarning) {
        this.associatedDocumentWarning = associatedDocumentWarning;
    }
}
