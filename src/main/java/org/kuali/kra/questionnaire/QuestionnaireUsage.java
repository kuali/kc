/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang.ObjectUtils;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionnaireUsage extends KraPersistableBusinessObjectBase implements Comparable<QuestionnaireUsage>, SequenceAssociate<Questionnaire> {

    private static final long serialVersionUID = -5676341963373665440L;

    private Long questionnaireUsageId;

    private String moduleItemCode;

    private String moduleSubItemCode;

    private String questionnaireRefIdFk;

    private String ruleId;

    private String questionnaireLabel;

    private Integer questionnaireSequenceNumber;

    private boolean mandatory;

    private CoeusModule coeusModule;
    
    private CoeusSubModule coeusSubModule;

    private Questionnaire questionnaire;

    private Questionnaire sequenceOwner;
    
    private transient boolean delete;

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

    public String getQuestionnaireRefIdFk() {
        return questionnaireRefIdFk;
    }

    public void setQuestionnaireRefIdFk(String questionnaireRefIdFk) {
        this.questionnaireRefIdFk = questionnaireRefIdFk;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getQuestionnaireLabel() {
        return questionnaireLabel;
    }

    public void setQuestionnaireLabel(String questionnaireLabel) {
        this.questionnaireLabel = questionnaireLabel;
    }

    public CoeusModule getCoeusModule() {
        if (coeusModule == null && getModuleItemCode() != null) {
            this.refreshReferenceObject("coeusModule");
        }
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

    public int compareTo(QuestionnaireUsage argQuestionnaireUsage) {
        if (ObjectUtils.equals(this.getQuestionnaire().getQuestionnaireId(), argQuestionnaireUsage.getQuestionnaire().getQuestionnaireId())) {
            if (ObjectUtils.equals(this.getQuestionnaireRefIdFk(), argQuestionnaireUsage.getQuestionnaireRefIdFk())) {
                return argQuestionnaireUsage.getQuestionnaireSequenceNumber().compareTo(this.getQuestionnaireSequenceNumber());
            } else {
                // compare refid is not good : 1. it's a string now, so '753' > '1001'. 2. the proposalpersonqn has high refid 6111.
                // so the assumption of newer version of qnnaire has higher refid is not right for this case.
                // compare qnnaire sequence is the right approach
                return argQuestionnaireUsage.getQuestionnaire().getSequenceNumber().compareTo(this.getQuestionnaire().getSequenceNumber());
            }
        } else {
            return this.getQuestionnaire().getQuestionnaireIdAsInteger().compareTo(argQuestionnaireUsage.getQuestionnaire().getQuestionnaireIdAsInteger());
        }
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public CoeusSubModule getCoeusSubModule() {
        if (coeusSubModule == null) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("moduleCode", Integer.valueOf(moduleItemCode));
            fieldValues.put("subModuleCode", Integer.valueOf(moduleSubItemCode));
            List<CoeusSubModule> subModules = 
                    (List<CoeusSubModule>) KraServiceLocator.getService(BusinessObjectService.class).findMatching(CoeusSubModule.class, fieldValues);
            if (subModules != null && !subModules.isEmpty()) {
                coeusSubModule = subModules.get(0);
            }
        }
        return coeusSubModule;
    }

    public void setCoeusSubModule(CoeusSubModule coeusSubModule) {
        this.coeusSubModule = coeusSubModule;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
