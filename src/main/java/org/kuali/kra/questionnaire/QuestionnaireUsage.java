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
package org.kuali.kra.questionnaire;

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity 
@Table(name="QUESTIONNAIRE_USAGE")
public class QuestionnaireUsage extends KraPersistableBusinessObjectBase implements SequenceAssociate<Questionnaire> { 
    
    private static final long serialVersionUID = 1L;

    @Id 
    @Column(name="QUESTIONNAIRE_USAGE_ID")
    private Long questionnaireUsageId; 
    @Column(name="MODULE_ITEM_CODE")
    private String moduleItemCode; 
    @Column(name="MODULE_SUB_ITEM_CODE")
    private String moduleSubItemCode; 
    @Column(name="QUESTIONNAIRE_REF_ID_FK")
    private Long questionnaireRefIdFk; 
    @Column(name="RULE_ID")
    private Integer ruleId; 
    @Column(name="QUESTIONNAIRE_LABEL")
    private String questionnaireLabel; 
    @Column(name="QUESTIONNAIRE_SEQUENCE_NUMBER")
    private Integer questionnaireSequenceNumber;
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="MODULE_ITEM_CODE", insertable=false, updatable=false)
    private CoeusModule coeusModule;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="QUESTIONNAIRE_REF_ID_FK", insertable=false, updatable=false)
    private Questionnaire questionnaire;
    
    private Questionnaire sequenceOwner;
    
    
    public QuestionnaireUsage() { 

    } 
    
    public Long getQuestionnaireUsageId() {
        return questionnaireUsageId;
    }

    public void setQuestionnaireUsageId(Long questionnaireUsageId) {
        this.questionnaireUsageId = questionnaireUsageId;
    }

    public String getModuleItemCode() {
        return moduleItemCode;
    }

    public void setModuleItemCode(String moduleItemCode) {
        this.moduleItemCode = moduleItemCode;
    }

    public String getModuleSubItemCode() {
        return moduleSubItemCode;
    }

    public void setModuleSubItemCode(String moduleSubItemCode) {
        this.moduleSubItemCode = moduleSubItemCode;
    }

    public Long getQuestionnaireRefIdFk() {
        return questionnaireRefIdFk;
    }

    public void setQuestionnaireRefIdFk(Long questionnaireRefIdFk) {
        this.questionnaireRefIdFk = questionnaireRefIdFk;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getQuestionnaireLabel() {
        return questionnaireLabel;
    }

    public void setQuestionnaireLabel(String questionnaireLabel) {
        this.questionnaireLabel = questionnaireLabel;
    }

    public CoeusModule getCoeusModule() {
        return coeusModule;
    }

    public void setCoeusModule(CoeusModule coeusModule) {
        this.coeusModule = coeusModule;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("questionnaireUsageId", this.getQuestionnaireUsageId());
        hashMap.put("moduleItemCode", this.getModuleItemCode());
        hashMap.put("moduleSubItemCode", this.getModuleSubItemCode());
        hashMap.put("questionnaireRefIdFk", this.getQuestionnaireRefIdFk());
        hashMap.put("ruleId", this.getRuleId());
        hashMap.put("questionnaireLabel", this.getQuestionnaireLabel());
        return hashMap;
    }

    public Questionnaire getSequenceOwner() {
        return this.getQuestionnaire();
    }

    public void setSequenceOwner(Questionnaire newlyVersionedOwner) {
        setQuestionnaire(newlyVersionedOwner);
        
    }
    /** {@inheritDoc} */
    public void resetPersistenceState() {
        this.setQuestionnaireUsageId(null);
    }

    public Integer getSequenceNumber() {
        return this.sequenceOwner.getSequenceNumber();
    }

    public Integer getQuestionnaireSequenceNumber() {
        return questionnaireSequenceNumber;
    }

    public void setQuestionnaireSequenceNumber(Integer questionnaireSequenceNumber) {
        this.questionnaireSequenceNumber = questionnaireSequenceNumber;
    }

    
}