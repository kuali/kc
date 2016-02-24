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
package org.kuali.kra.protocol.actions.print;

import java.io.Serializable;

/**
 * This class is for setting the print options for ProtocolSummary
 */
public class ProtocolSummaryPrintOptions implements Serializable {

    private static final long serialVersionUID = -7053561167215452265L;
    private boolean actions = true;
    private boolean ammendmentRenewalSummary = true;
    private boolean amendmentRenewalHistory = true;
    private boolean attachments = true;
    private boolean documents = true;
    private boolean areaOfResearch = true;
    private boolean correspondents = true;
    private boolean fundingSource = true;
    private boolean investigator = true;
    private boolean notes = true;
    private boolean organizaition = true;
    private boolean protocolDetails = true;
    private boolean references = true;
    private boolean riskLevel = true;
    private boolean exception = true;
    private boolean specialReview = true;
    private boolean roles = true;
    private boolean studyPersonnels = true;
    private boolean subjects = true;
    private boolean otherData = true;
    private boolean reviewComments = true;
    private boolean protocolHistory = true;
    private boolean speciesAndGroups = true;
    private boolean principles = true;
    private boolean procedure = true; 
    /**
     * Gets the actions attribute. 
     * @return Returns the actions.
     */
    public boolean isActions() {
        return actions;
    }
    /**
     * Sets the actions attribute value.
     * @param actions The actions to set.
     */
    public void setActions(boolean actions) {
        this.actions = actions;
    }
    /**
     * Gets the ammendmentRenewalSummary attribute. 
     * @return Returns the ammendmentRenewalSummary.
     */
    public boolean isAmmendmentRenewalSummary() {
        return ammendmentRenewalSummary;
    }
    /**
     * Sets the ammendmentRenewalSummary attribute value.
     * @param ammendmentRenewalSummary The ammendmentRenewalSummary to set.
     */
    public void setAmmendmentRenewalSummary(boolean ammendmentRenewalSummary) {
        this.ammendmentRenewalSummary = ammendmentRenewalSummary;
    }
    /**
     * Gets the amendmentRenewalHistory attribute. 
     * @return Returns the amendmentRenewalHistory.
     */
    public boolean isAmendmentRenewalHistory() {
        return amendmentRenewalHistory;
    }
    /**
     * Sets the amendmentRenewalHistory attribute value.
     * @param amendmentRenewalHistory The amendmentRenewalHistory to set.
     */
    public void setAmendmentRenewalHistory(boolean amendmentRenewalHistory) {
        this.amendmentRenewalHistory = amendmentRenewalHistory;
    }
    /**
     * Gets the attachments attribute. 
     * @return Returns the attachments.
     */
    public boolean isAttachments() {
        return attachments;
    }
    /**
     * Sets the attachments attribute value.
     * @param attachments The attachments to set.
     */
    public void setAttachments(boolean attachments) {
        this.attachments = attachments;
    }
    /**
     * Gets the documents attribute. 
     * @return Returns the documents.
     */
    public boolean isDocuments() {
        return documents;
    }
    /**
     * Sets the documents attribute value.
     * @param documents The documents to set.
     */
    public void setDocuments(boolean documents) {
        this.documents = documents;
    }
    /**
     * Gets the areaOfResearch attribute. 
     * @return Returns the areaOfResearch.
     */
    public boolean isAreaOfResearch() {
        return areaOfResearch;
    }
    /**
     * Sets the areaOfResearch attribute value.
     * @param areaOfResearch The areaOfResearch to set.
     */
    public void setAreaOfResearch(boolean areaOfResearch) {
        this.areaOfResearch = areaOfResearch;
    }
    /**
     * Gets the correspondents attribute. 
     * @return Returns the correspondents.
     */
    public boolean isCorrespondents() {
        return correspondents;
    }
    /**
     * Sets the correspondents attribute value.
     * @param correspondents The correspondents to set.
     */
    public void setCorrespondents(boolean correspondents) {
        this.correspondents = correspondents;
    }
    /**
     * Gets the fundingSource attribute. 
     * @return Returns the fundingSource.
     */
    public boolean isFundingSource() {
        return fundingSource;
    }
    /**
     * Sets the fundingSource attribute value.
     * @param fundingSource The fundingSource to set.
     */
    public void setFundingSource(boolean fundingSource) {
        this.fundingSource = fundingSource;
    }
    /**
     * Gets the investigator attribute. 
     * @return Returns the investigator.
     */
    public boolean isInvestigator() {
        return investigator;
    }
    /**
     * Sets the investigator attribute value.
     * @param investigator The investigator to set.
     */
    public void setInvestigator(boolean investigator) {
        this.investigator = investigator;
    }
    /**
     * Gets the notes attribute. 
     * @return Returns the notes.
     */
    public boolean isNotes() {
        return notes;
    }
    /**
     * Sets the notes attribute value.
     * @param notes The notes to set.
     */
    public void setNotes(boolean notes) {
        this.notes = notes;
    }
    /**
     * Gets the organizaition attribute. 
     * @return Returns the organizaition.
     */
    public boolean isOrganizaition() {
        return organizaition;
    }
    /**
     * Sets the organizaition attribute value.
     * @param organizaition The organizaition to set.
     */
    public void setOrganizaition(boolean organizaition) {
        this.organizaition = organizaition;
    }
    /**
     * Gets the protocolDetails attribute. 
     * @return Returns the protocolDetails.
     */
    public boolean isProtocolDetails() {
        return protocolDetails;
    }
    /**
     * Sets the protocolDetails attribute value.
     * @param protocolDetails The protocolDetails to set.
     */
    public void setProtocolDetails(boolean protocolDetails) {
        this.protocolDetails = protocolDetails;
    }
    /**
     * Gets the references attribute. 
     * @return Returns the references.
     */
    public boolean isReferences() {
        return references;
    }
    /**
     * Sets the references attribute value.
     * @param references The references to set.
     */
    public void setReferences(boolean references) {
        this.references = references;
    }
    /**
     * Gets the riskLevel attribute. 
     * @return Returns the riskLevel.
     */
    public boolean isRiskLevel() {
        return riskLevel;
    }
    /**
     * Sets the riskLevel attribute value.
     * @param riskLevel The riskLevel to set.
     */
    public void setRiskLevel(boolean riskLevel) {
        this.riskLevel = riskLevel;
    }
    /**
     * Gets the exception attribute. 
     * @return Returns the exception.
     */
    public boolean isException() {
        return exception;
    }
    /**
     * Sets the exception attribute value.
     * @param exception The exception to set.
     */
    public void setException(boolean exception) {
        this.exception = exception;
    }
    /**
     * Gets the specialReview attribute. 
     * @return Returns the specialReview.
     */
    public boolean isSpecialReview() {
        return specialReview;
    }
    /**
     * Sets the specialReview attribute value.
     * @param specialReview The specialReview to set.
     */
    public void setSpecialReview(boolean specialReview) {
        this.specialReview = specialReview;
    }
    /**
     * Gets the roles attribute. 
     * @return Returns the roles.
     */
    public boolean isRoles() {
        return roles;
    }
    /**
     * Sets the roles attribute value.
     * @param roles The roles to set.
     */
    public void setRoles(boolean roles) {
        this.roles = roles;
    }
    /**
     * Gets the studyPersonnels attribute. 
     * @return Returns the studyPersonnels.
     */
    public boolean isStudyPersonnels() {
        return studyPersonnels;
    }
    /**
     * Sets the studyPersonnels attribute value.
     * @param studyPersonnels The studyPersonnels to set.
     */
    public void setStudyPersonnels(boolean studyPersonnels) {
        this.studyPersonnels = studyPersonnels;
    }
    /**
     * Gets the subjects attribute. 
     * @return Returns the subjects.
     */
    public boolean isSubjects() {
        return subjects;
    }
    /**
     * Sets the subjects attribute value.
     * @param subjects The subjects to set.
     */
    public void setSubjects(boolean subjects) {
        this.subjects = subjects;
    }
    /**
     * Sets the otherData attribute value.
     * @param otherData The otherData to set.
     */
    public void setOtherData(boolean otherData) {
        this.otherData = otherData;
    }
    /**
     * Gets the otherData attribute. 
     * @return Returns the otherData.
     */
    public boolean isOtherData() {
        return otherData;
    }
    
    /**
     * Gets the reviewComments attribute. 
     * @return Returns the reviewComments.
     */
    public boolean isReviewComments() {
        return reviewComments;
    }
    /**
     * Sets the reviewComments attribute value.
     * @param reviewComments The reviewComments to set.
     */
    public void setReviewComments(boolean reviewComments) {
        this.reviewComments = reviewComments;
    }
    /**
     * Gets the protocolHistory attribute. 
     * @return Returns the protocolHistory.
     */
    public boolean isProtocolHistory() {
        return protocolHistory;
    }
    /**
     * Sets the protocolHistory attribute value.
     * @param protocolHistory The protocolHistory to set.
     */
    public void setProtocolHistory(boolean protocolHistory) {
        this.protocolHistory = protocolHistory;
    }
    /**
     * Gets the speciesAndGroups attribute. 
     * @return Returns the speciesAndGroups.
     */
    public boolean isSpeciesAndGroups() {
        return speciesAndGroups;
    }
    /**
     * Sets the speciesAndGroups attribute value.
     * @param speciesAndGroups The speciesAndGroups to set.
     */
    public void setSpeciesAndGroups(boolean speciesAndGroups) {
        this.speciesAndGroups = speciesAndGroups;
    }
    /**
     * Gets the principles attribute. 
     * @return Returns the principles.
     */
    public boolean isPrinciples() {
        return principles;
    }
    /**
     * Sets the principles attribute value.
     * @param principles The principles to set.
     */
    public void setPrinciples(boolean principles) {
        this.principles = principles;
    }
    /**
     * Gets the procedure attribute. 
     * @return Returns the procedure.
     */
    public boolean isProcedure() {
        return procedure;
    }
    /**
     * Sets the procedure attribute value.
     * @param procedure The procedure to set.
     */
    public void setProcedure(boolean procedure) {
        this.procedure = procedure;
    }       
}
