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
package org.kuali.kra.iacuc.actions.amendrenew;

import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.IacucProtocolEditableBean;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean;

public class IacucProtocolAmendmentBean extends IacucProtocolEditableBean implements ProtocolAmendmentBean {

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
    private boolean protocolPermissions = false;
    private boolean questionnaire = false;
    
    private boolean threers = false;
    private boolean speciesAndGroups = false;
    private boolean procedures = false;
    private boolean protocolExceptions = false;
    
    /**
     * Constructs a ProtocolAmendmentBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolAmendmentBean(IacucActionHelper actionHelper) {
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
               getThreers() ||
               getSpeciesAndGroups() ||
               getProcedures() ||
               getProtocolExceptions() ||
               getQuestionnaire();
    }

    public boolean getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(boolean questionnaire) {
        this.questionnaire = questionnaire;
    }

    public boolean getThreers() {
        return threers;
    }

    public void setThreers(boolean threers) {
        this.threers = threers;
    }

    public boolean getSpeciesAndGroups() {
        return speciesAndGroups;
    }

    public void setSpeciesAndGroups(boolean speciesAndGroups) {
        this.speciesAndGroups = speciesAndGroups;
    }

    public boolean getProcedures() {
        return procedures;
    }

    public void setProcedures(boolean procedures) {
        this.procedures = procedures;
    }

    public boolean getProtocolExceptions() {
        return protocolExceptions;
    }

    public void setProtocolExceptions(boolean protocolExceptions) {
        this.protocolExceptions = protocolExceptions;
    }
}
