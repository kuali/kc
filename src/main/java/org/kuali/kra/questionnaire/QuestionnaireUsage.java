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
package org.kuali.kra.questionnaire;

import org.apache.commons.lang.ObjectUtils;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class QuestionnaireUsage extends KraPersistableBusinessObjectBase implements Comparable<QuestionnaireUsage>, SequenceAssociate<Questionnaire> {

    private static final long serialVersionUID = -5676341963373665440L;

    private Long questionnaireUsageId;

    private String moduleItemCode;

    private String moduleSubItemCode;

    private String questionnaireRefIdFk;

    private Integer ruleId;

    private String questionnaireLabel;

    private Integer questionnaireSequenceNumber;

    private boolean mandatory;

    private CoeusModule coeusModule;

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

    public String getQuestionnaireRefIdFk() {
        return questionnaireRefIdFk;
    }

    public void setQuestionnaireRefIdFk(String questionnaireRefIdFk) {
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
                return argQuestionnaireUsage.getQuestionnaireRefIdFk().compareTo(this.getQuestionnaireRefIdFk());
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
}
