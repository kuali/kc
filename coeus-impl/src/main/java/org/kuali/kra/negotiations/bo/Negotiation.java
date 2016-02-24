/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.negotiations.bo;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.negotiations.customdata.NegotiationCustomData;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.notifications.NegotiationNotification;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.krad.bo.BusinessObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class handles the negotiation BO.
 */
public class Negotiation extends KcPersistableBusinessObjectBase implements Permissionable {

    private static final long MILLISECS_PER_DAY = 24 * 60 * 60 * 1000;

    private transient KcPersonService kcPersonService;


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

    private NegotiationStatus negotiationStatus;
    private NegotiationAgreementType negotiationAgreementType;
    private NegotiationAssociationType negotiationAssociationType;

    private List<NegotiationActivity> activities;

    private int printindex;
    private boolean printAll = true;
    private Long oldNegotiationAssociationTypeId;

    private List<NegotiationNotification> negotiationNotifications;
    
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
        negotiationNotifications = new ArrayList<NegotiationNotification>();
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
        return PermissionableKeys.NEGOTIATION_KEY;
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
        return KcServiceLocator.getService(NegotiationService.class);
    }

    @Override
    public String getDocumentRoleTypeCode() {
        return RoleConstants.NEGOTIATION_ROLE_TYPE;
    }

    @Override
    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        qualifiedRoleAttributes.put(KimConstants.AttributeConstants.DOCUMENT_TYPE_NAME, this.getDocumentKey());
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

    public List<NegotiationNotification> getNegotiationNotifications() {
        return negotiationNotifications;
    }

    public void setNegotiationNotifications(List<NegotiationNotification> negotiationNotifications) {
        this.negotiationNotifications = negotiationNotifications;
    }

    public void addNotification(NegotiationNotification negotiationNotification) {
        getNegotiationNotifications().add(negotiationNotification);        
    }

    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }
}
