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
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * ]
 * This class handles the negotiation BO.
 */
public class Negotiation extends KraPersistableBusinessObjectBase {

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
    private Date negotiationStartDate;
    private Date negotiationEndDate;
    private Date anticipatedAwardDate;
    private String documentFolder;
    
    /**
     * Long awardId - award
     * String proposalNumber -developmentProposal
     * Long proposalId - institutionalProposal
     */
    private String associatedDocumentId;
    
    
    private NegotiationStatus negotiationStatus;
    private NegotiationAgreementType negotiationAgreementType;
    private NegotiationAssociationType negotiationAssociationType;
    
    public Negotiation() {
        super();
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
    }



    public Date getNegotiationStartDate() {
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



    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("negotiationId", this.getNegotiationId());
        return map;
    }

}
