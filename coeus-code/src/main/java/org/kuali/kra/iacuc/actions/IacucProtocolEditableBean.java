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
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.actions;

import org.kuali.kra.protocol.actions.ProtocolEditableBean;

public class IacucProtocolEditableBean extends IacucProtocolActionBean implements ProtocolEditableBean {


    private static final long serialVersionUID = 5518447794781881082L;

    private boolean generalInfoEnabled = false;
    private boolean fundingSourceEnabled = false;
    private boolean protocolReferencesEnabled = false;
    private boolean protocolOrganizationsEnabled = false;
    private boolean subjectsEnabled = false;
    private boolean addModifyAttachmentsEnabled = false;
    private boolean areasOfResearchEnabled = false;
    private boolean specialReviewEnabled = false;
    private boolean protocolPersonnelEnabled = false;
    private boolean othersEnabled = false;
    private boolean protocolPermissionsEnabled = false;
    private boolean questionnaireEnabled = false;
    
    private boolean threersEnabled = false;
    private boolean speciesAndGroupsEnabled = false;
    private boolean proceduresEnabled = false;
    private boolean protocolExceptionsEnabled = false;

    /**
     * Constructs a ProtocolEditableBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolEditableBean(IacucActionHelper actionHelper) {
        super(actionHelper);
    }

    public boolean getGeneralInfoEnabled() {
        return generalInfoEnabled;
    }

    public void setGeneralInfoEnabled(boolean generalInfoEnabled) {
        this.generalInfoEnabled = generalInfoEnabled;
    }

    public boolean getFundingSourceEnabled() {
        return fundingSourceEnabled;
    }

    public void setFundingSourceEnabled(boolean fundingSourceEnabled) {
        this.fundingSourceEnabled = fundingSourceEnabled;
    }

    public boolean getProtocolReferencesEnabled() {
        return protocolReferencesEnabled;
    }

    public void setProtocolReferencesEnabled(boolean protocolReferencesEnabled) {
        this.protocolReferencesEnabled = protocolReferencesEnabled;
    }

    public boolean getProtocolOrganizationsEnabled() {
        return protocolOrganizationsEnabled;
    }

    public void setProtocolOrganizationsEnabled(boolean protocolOrganizationsEnabled) {
        this.protocolOrganizationsEnabled = protocolOrganizationsEnabled;
    }

    public boolean getSubjectsEnabled() {
        return subjectsEnabled;
    }

    public void setSubjectsEnabled(boolean subjectsEnabled) {
        this.subjectsEnabled = subjectsEnabled;
    }

    public boolean getAddModifyAttachmentsEnabled() {
        return addModifyAttachmentsEnabled;
    }

    public void setAddModifyAttachmentsEnabled(boolean addModifyAttachmentsEnabled) {
        this.addModifyAttachmentsEnabled = addModifyAttachmentsEnabled;
    }

    public boolean getAreasOfResearchEnabled() {
        return areasOfResearchEnabled;
    }

    public void setAreasOfResearchEnabled(boolean areasOfResearchEnabled) {
        this.areasOfResearchEnabled = areasOfResearchEnabled;
    }

    public boolean getSpecialReviewEnabled() {
        return specialReviewEnabled;
    }

    public void setSpecialReviewEnabled(boolean specialReviewEnabled) {
        this.specialReviewEnabled = specialReviewEnabled;
    }

    public boolean getProtocolPersonnelEnabled() {
        return protocolPersonnelEnabled;
    }

    public void setProtocolPersonnelEnabled(boolean protocolPersonnelEnabled) {
        this.protocolPersonnelEnabled = protocolPersonnelEnabled;
    }

    public boolean getOthersEnabled() {
        return othersEnabled;
    }

    public void setOthersEnabled(boolean othersEnabled) {
        this.othersEnabled = othersEnabled;
    }

    public boolean getProtocolPermissionsEnabled() {
        return protocolPermissionsEnabled;
    }

    public void setProtocolPermissionsEnabled(boolean protocolPermissionsEnabled) {
        this.protocolPermissionsEnabled = protocolPermissionsEnabled;
    }

    public boolean getQuestionnaireEnabled() {
        return questionnaireEnabled;
    }

    public void setQuestionnaireEnabled(boolean questionnaireEnabled) {
        this.questionnaireEnabled = questionnaireEnabled;
    }

    public boolean isThreersEnabled() {
        return threersEnabled;
    }

    public void setThreersEnabled(boolean threersEnabled) {
        this.threersEnabled = threersEnabled;
    }

    public boolean isSpeciesAndGroupsEnabled() {
        return speciesAndGroupsEnabled;
    }

    public void setSpeciesAndGroupsEnabled(boolean speciesAndGroupsEnabled) {
        this.speciesAndGroupsEnabled = speciesAndGroupsEnabled;
    }

    public boolean isProceduresEnabled() {
        return proceduresEnabled;
    }

    public void setProceduresEnabled(boolean proceduresEnabled) {
        this.proceduresEnabled = proceduresEnabled;
    }

    public boolean isProtocolExceptionsEnabled() {
        return protocolExceptionsEnabled;
    }

    public void setProtocolExceptionsEnabled(boolean protocolExceptionsEnabled) {
        this.protocolExceptionsEnabled = protocolExceptionsEnabled;
    }

}
