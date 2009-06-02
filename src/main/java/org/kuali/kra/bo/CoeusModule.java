/*
 * Copyright 2006-2009 The Kuali Foundation
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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.kuali.kra.questionnaire.QuestionnaireUsage;

@Entity 
@Table(name="OSP$COEUS_MODULE")
public class CoeusModule extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    @Id 
    @Column(name="MODULE_CODE")
    private String moduleCode; 
    @Column(name="DESCRIPTION")
    private String description; 
    
    
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="MODULE_ITEM_CODE", insertable=false, updatable=false)
    private QuestionnaireUsage questionnaireUsage;
    
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="MODULE_CODE", insertable=false, updatable=false)
    private CoeusSubModule coeusSubModule;
    
    /* TODO : Implemented in the future 
    
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="MODULE_CODE", insertable=false, updatable=false)
    private ProtocolRelatedProjects protocolRelatedProjects;
    
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="MODULE_CODE", insertable=false, updatable=false)
    private CustomDataElementUsage customDataElementUsage;
    
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="MODULE_CODE", insertable=false, updatable=false)
    private ProtocolLinks protocolLinks;
    
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="MODULE_CODE", insertable=false, updatable=false)
    private NotifActionType notifActionType;
    
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="MODULE_CODE", insertable=false, updatable=false)
    private PersonRoleModule personRoleModule;
    
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="MODULE_CODE", insertable=false, updatable=false)
    private NotificationType notificationType;
    
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="MODULE_CODE", insertable=false, updatable=false)
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