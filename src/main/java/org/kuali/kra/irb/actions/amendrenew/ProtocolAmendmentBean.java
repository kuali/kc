/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProtocolAmendmentBean implements Serializable {

    private String summary = "";
    
    private boolean generalInfo = false;
    private boolean fundingSource = false;
    private boolean protocolReferences = false;
    private boolean protocolOrganizations = false;
    private boolean subjects = false;
    private boolean addModifyAttachments = false;
    private boolean areasOfResearch = false;
    private boolean specialReview = false;
    private boolean protocolPersonnel = false;
    private boolean others = false;
    
    public ProtocolAmendmentBean() {
        
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

    public boolean getProtocolReferences() {
        return protocolReferences;
    }

    public void setProtocolReferences(boolean protocolReferences) {
        this.protocolReferences = protocolReferences;
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
}
