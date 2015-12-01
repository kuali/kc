/*
 * Copyright (c) 2014. Boston University
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND either express or
 * implied.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 */

package edu.bu.kuali.kra.award.home;

import edu.bu.kuali.kra.bo.AwardTransmission;

import org.kuali.rice.krad.bo.PersistableBusinessObjectExtension;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent BU additional fields (BUKC-0002) for Award module.
 */
public class AwardExtension extends KcPersistableBusinessObjectBase implements
        PersistableBusinessObjectExtension {

    private static final long serialVersionUID = 3138263301644052314L;

    private Long awardId;
    private String a133Cluster;
    private String proposedForTransmissionIndicator;
    private Date lastTransmissionDate;
    private String childType;
    private String childDescription;
    private String majorProject;
    private String arraCode;
    private String avcIndicator;
    private Boolean fringeNotAllowedIndicator;
    private String interestEarned;
    private String interestEarnedAccountNumber;
    private String federalRateDate;
    private String buBmcFaSplit;
    private String conferenceGrant;
    private String programIncome;
    private String stockAward;
    private String foreignCurrencyAward;
    private Date NCENotificationDate;
    private String clinicalTrialInitiatedBy;
    private String INDIDEResponsibility;
    private Date clinicalTrialRegistrationDate;
    private String spudsRecordNumber;
	private String walkerSourceNumber;
    private String primeSponsorAwardId;
 	private String grantNumber;

    // BUKC-0167: Award: request that a Federal Clinical Trial field be added to the KC awards module (ENHC0013482)
    private Boolean  federalClinicalTrial;
    // BUKC-0090: Adding new custom data element needed in KC: FAIN (ENHC0012305)
    private String fain;

    // BUKC-0014: KC/SAP Interface - add SAP Transmission collection
    private boolean validatedForTransmission; // not persisted
    private List<AwardTransmission> awardTransmissions;

    public AwardExtension() {

        awardTransmissions = new ArrayList<AwardTransmission>();
    }
    public String getProposedForTransmissionIndicator() {
        return proposedForTransmissionIndicator;
    }

    public void setProposedForTransmissionIndicator(
            String proposedForTransmissionIndicator) {
        this.proposedForTransmissionIndicator = proposedForTransmissionIndicator;
    }

    public Date getLastTransmissionDate() {
        return lastTransmissionDate;
    }

    public void setLastTransmissionDate(Date lastTransmissionDate) {
        this.lastTransmissionDate = lastTransmissionDate;
    }

    public String getChildType() {
        return childType;
    }

    public void setChildType(String childType) {
        this.childType = childType;
    }

    public String getChildDescription() {
        return childDescription;
    }

    public void setChildDescription(String childDescription) {
        this.childDescription = childDescription;
    }

    public String getMajorProject() {
        return majorProject;
    }

    public void setMajorProject(String majorProject) {
        this.majorProject = majorProject;
    }

    public String getArraCode() {
        return arraCode;
    }

    public void setArraCode(String arraCode) {
        this.arraCode = arraCode;
    }

    public String getAvcIndicator() {
        return avcIndicator;
    }

    public void setAvcIndicator(String avcIndicator) {
        this.avcIndicator = avcIndicator;
    }

    public Boolean getFringeNotAllowedIndicator() {
        return fringeNotAllowedIndicator;
    }

    public void setFringeNotAllowedIndicator(Boolean fringeNotAllowedIndicator) {
        this.fringeNotAllowedIndicator = fringeNotAllowedIndicator;
    }

    public String getInterestEarned() {
        return interestEarned;
    }

    public void setInterestEarned(String interestEarned) {
        this.interestEarned = interestEarned;
    }

    public String getInterestEarnedAccountNumber() {
        return interestEarnedAccountNumber;
    }

    public void setInterestEarnedAccountNumber(
            String interestEarnedAccountNumber) {
        this.interestEarnedAccountNumber = interestEarnedAccountNumber;
    }

    public String getFederalRateDate() {
        return federalRateDate;
    }

    public void setFederalRateDate(String federalRateDate) {
        this.federalRateDate = federalRateDate;
    }

    public String getBuBmcFaSplit() {
        return buBmcFaSplit;
    }

    public void setBuBmcFaSplit(String buBmcFaSplit) {
        this.buBmcFaSplit = buBmcFaSplit;
    }

    public String getConferenceGrant() {
        return conferenceGrant;
    }

    public void setConferenceGrant(String conferenceGrant) {
        this.conferenceGrant = conferenceGrant;
    }

    public String getProgramIncome() {
        return programIncome;
    }

    public void setProgramIncome(String programIncome) {
        this.programIncome = programIncome;
    }

    public String getStockAward() {
        return stockAward;
    }

    public void setStockAward(String stockAward) {
        this.stockAward = stockAward;
    }

    public String getForeignCurrencyAward() {
        return foreignCurrencyAward;
    }

    public void setForeignCurrencyAward(String foreignCurrencyAward) {
        this.foreignCurrencyAward = foreignCurrencyAward;
    }

    public Date getNCENotificationDate() {
        return NCENotificationDate;
    }

    public void setNCENotificationDate(Date nCENotificationDate) {
        NCENotificationDate = nCENotificationDate;
    }

    public String getClinicalTrialInitiatedBy() {
        return clinicalTrialInitiatedBy;
    }

    public void setClinicalTrialInitiatedBy(String clinicalTrialInitiatedBy) {
        this.clinicalTrialInitiatedBy = clinicalTrialInitiatedBy;
    }

    public String getINDIDEResponsibility() {
        return INDIDEResponsibility;
    }

    public void setINDIDEResponsibility(String iNDIDEResponsibility) {
        INDIDEResponsibility = iNDIDEResponsibility;
    }

    public Date getClinicalTrialRegistrationDate() {
        return clinicalTrialRegistrationDate;
    }

    public void setClinicalTrialRegistrationDate(
            Date clinicalTrialRegistrationDate) {
        this.clinicalTrialRegistrationDate = clinicalTrialRegistrationDate;
    }

    public String getSpudsRecordNumber() {
        return spudsRecordNumber;
    }

    public void setSpudsRecordNumber(String spudsRecordNumber) {
        this.spudsRecordNumber = spudsRecordNumber;
    }

    public String getWalkerSourceNumber() {
        return walkerSourceNumber;
    }

    public void setWalkerSourceNumber(String walkerSourceNumber) {
        this.walkerSourceNumber = walkerSourceNumber;
    }

    public String getPrimeSponsorAwardId() {
        return primeSponsorAwardId;
    }

    public void setPrimeSponsorAwardId(String primeSponsorAwardId) {
        this.primeSponsorAwardId = primeSponsorAwardId;
    }

    public String getGrantNumber() {
        return grantNumber;
    }

    public void setGrantNumber(String grantNumber) {
        this.grantNumber = grantNumber;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public String getA133Cluster() {
        return a133Cluster;
    }

    public void setA133Cluster(String a133Cluster) {
        this.a133Cluster = a133Cluster;
    }

    public List<AwardTransmission> getAwardTransmissions() {
        return awardTransmissions;
    }

    public void setAwardTransmissions(List<AwardTransmission> awardTransmissions) {
        this.awardTransmissions = awardTransmissions;
    }

    public void setValidatedForTransmission(boolean validatedForTransmission) {
        this.validatedForTransmission = validatedForTransmission;
    }

    public boolean isValidatedForTransmission() {
        return validatedForTransmission;
    }

    public String getFain() {
		return fain;
	}

	public void setFain(String fain) {
		this.fain = fain;
	}

	public Boolean getFederalClinicalTrial() {
			return federalClinicalTrial;
		}

	public void setFederalClinicalTrial(Boolean federalClinicalTrial) {
		this.federalClinicalTrial = federalClinicalTrial;
		}
	
}