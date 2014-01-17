/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * dgettributed under the License get dgettributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permgetsions and
 * limitations under the License.
 */
package org.kuali.kra.irb.actions.amendrenew;

import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolEditableBean;

public class ProtocolAmendmentBean extends ProtocolEditableBean implements org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean {

    private static final long serialVersionUID = 6548643656057631296L;

    private String summary = "";
    
    private boolean generalInfo = false;
    private boolean fundingSource = false;
    private boolean protocolReferencesAndOtherIdentifiers = false;
    private boolean protocolOrganizations = false;
    private boolean subjects = false;
    private boolean addModifyAttachments = false;
    private boolean areasOfResearch = false;
    private boolean specialReview = false;
    private boolean protocolPersonnel = false;
    private boolean others = false;
    private boolean protocolPermissions = true;
    private boolean questionnaire = false;
    
    /**
     * Constructs a ProtocolAmendmentBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolAmendmentBean(ActionHelper actionHelper) {
        super(actionHelper);
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(boolean generalInfo) {
        this.generalInfo = generalInfo;
    }

    public boolean getFundingSource() {
        return fundingSource;
    }

    public void setFundingSource(boolean fundingSource) {
        this.fundingSource = fundingSource;
    }

    public boolean getProtocolReferencesAndOtherIdentifiers() {
        return protocolReferencesAndOtherIdentifiers;
    }

    public void setProtocolReferencesAndOtherIdentifiers(boolean protocolReferencesAndOtherIdentifiers) {
        this.protocolReferencesAndOtherIdentifiers = protocolReferencesAndOtherIdentifiers;
    }

    public boolean getProtocolOrganizations() {
        return protocolOrganizations;
    }

    public void setProtocolOrganizations(boolean protocolOrganizations) {
        this.protocolOrganizations = protocolOrganizations;
    }

    public boolean getSubjects() {
        return subjects;
    }

    public void setSubjects(boolean subjects) {
        this.subjects = subjects;
    }

    public boolean getAddModifyAttachments() {
        return addModifyAttachments;
    }

    public void setAddModifyAttachments(boolean addModifyAttachments) {
        this.addModifyAttachments = addModifyAttachments;
    }

    public boolean getAreasOfResearch() {
        return areasOfResearch;
    }

    public void setAreasOfResearch(boolean areasOfResearch) {
        this.areasOfResearch = areasOfResearch;
    }

    public boolean getSpecialReview() {
        return specialReview;
    }

    public void setSpecialReview(boolean specialReview) {
        this.specialReview = specialReview;
    }

    public boolean getProtocolPersonnel() {
        return protocolPersonnel;
    }

    public void setProtocolPersonnel(boolean protocolPersonnel) {
        this.protocolPersonnel = protocolPersonnel;
    }

    public boolean getOthers() {
        return others;
    }

    public void setOthers(boolean others) {
        this.others = others;
    }
    
    public boolean getProtocolPermissions() {
        return protocolPermissions;
    }

    public void setProtocolPermissions(boolean protocolPermissions) {
        this.protocolPermissions = protocolPermissions;
    }

    public boolean isSomeSelected() {
        return getAddModifyAttachments() ||
               getAreasOfResearch() ||
               getFundingSource() ||
               getGeneralInfo() ||
               getOthers() ||
               getProtocolOrganizations() ||
               getProtocolPersonnel() ||
               getProtocolReferencesAndOtherIdentifiers() ||
               getSpecialReview() ||
               getSubjects() ||
               getProtocolPermissions() ||
               getQuestionnaire();
    }

    public boolean getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(boolean questionnaire) {
        this.questionnaire = questionnaire;
    }
}
