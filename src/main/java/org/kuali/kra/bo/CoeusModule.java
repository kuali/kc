/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.questionnaire.QuestionnaireUsage;

public class CoeusModule extends KraPersistableBusinessObjectBase { 
    
    public static final String AWARD_MODULE_CODE = "1";
    public static final String INSTITUTIONAL_PROPOSAL_MODULE_CODE = "2";
    public static final String PROPOSAL_DEVELOPMENT_MODULE_CODE = "3";
    public static final String SUBCONTRACTS_MODULE_CODE = "4";
    public static final String NEGOTIATIONS_MODULE_CODE = "5";
    public static final String PERSON_MODULE_CODE = "6";
    public static final String IRB_MODULE_CODE = "7";
    public static final String COI_DISCLOSURE_MODULE_CODE = "8";
    public static final String IACUC_PROTOCOL_MODULE_CODE = "9";
    public static final String COMMITTEE_MODULE_CODE = "11";
    
    public static final int AWARD_MODULE_CODE_INT = 1;
    public static final int INSTITUTIONAL_PROPOSAL_MODULE_CODE_INT = 2;
    public static final int PROPOSAL_DEVELOPMENT_MODULE_CODE_INT = 3;
    public static final int SUBCONTRACTS_MODULE_CODE_INT = 4;
    public static final int NEGOTIATIONS_MODULE_CODE_INT = 5;
    public static final int PERSON_MODULE_CODE_INT = 6;
    public static final int IRB_MODULE_CODE_INT = 7;
    public static final int COI_DISCLOSURE_MODULE_CODE_INT = 8;
    public static final int IACUC_PROTOCOL_MODULE_CODE_INT = 9;
    public static final int COMMITEE_MODULE_CODE_INT = 11;
    
    private static final long serialVersionUID = 1L;

    private String moduleCode; 
    private String description; 
    
    private QuestionnaireUsage questionnaireUsage;
    private CoeusSubModule coeusSubModule;
    
    /* TODO : Implemented in the future 
    
    private ProtocolRelatedProjects protocolRelatedProjects;
    
    private CustomDataElementUsage customDataElementUsage;
    private ProtocolLinks protocolLinks;
    private NotifActionType notifActionType;
    private PersonRoleModule personRoleModule;
    private NotificationType notificationType;
    private NotificationDetails notificationDetails;
    */
    
    public CoeusModule() { 

    } 
    
    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionnaireUsage getQuestionnaireUsage() {
        return questionnaireUsage;
    }

    public void setQuestionnaireUsage(QuestionnaireUsage questionnaireUsage) {
        this.questionnaireUsage = questionnaireUsage;
    }

    public CoeusSubModule getCoeusSubModule() {
        return coeusSubModule;
    }

    public void setCoeusSubModule(CoeusSubModule coeusSubModule) {
        this.coeusSubModule = coeusSubModule;
    }

    /* TODO : Implemented later
    public ProtocolRelatedProjects getProtocolRelatedProjects() {
        return protocolRelatedProjects;
    }

    public void setProtocolRelatedProjects(ProtocolRelatedProjects protocolRelatedProjects) {
        this.protocolRelatedProjects = protocolRelatedProjects;
    }

    public CustomDataElementUsage getCustomDataElementUsage() {
        return customDataElementUsage;
    }

    public void setCustomDataElementUsage(CustomDataElementUsage customDataElementUsage) {
        this.customDataElementUsage = customDataElementUsage;
    }

    public ProtocolLinks getProtocolLinks() {
        return protocolLinks;
    }

    public void setProtocolLinks(ProtocolLinks protocolLinks) {
        this.protocolLinks = protocolLinks;
    }

    public NotifActionType getNotifActionType() {
        return notifActionType;
    }

    public void setNotifActionType(NotifActionType notifActionType) {
        this.notifActionType = notifActionType;
    }

    public PersonRoleModule getPersonRoleModule() {
        return personRoleModule;
    }

    public void setPersonRoleModule(PersonRoleModule personRoleModule) {
        this.personRoleModule = personRoleModule;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationDetails getNotificationDetails() {
        return notificationDetails;
    }

    public void setNotificationDetails(NotificationDetails notificationDetails) {
        this.notificationDetails = notificationDetails;
    }
    */

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("moduleCode", this.getModuleCode());
        hashMap.put("description", this.getDescription());
        return hashMap;
    }
    
}
