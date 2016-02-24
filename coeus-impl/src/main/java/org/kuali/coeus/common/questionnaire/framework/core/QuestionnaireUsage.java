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
package org.kuali.coeus.common.questionnaire.framework.core;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.questionnaire.api.core.QuestionnaireUsageContract;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionnaireUsage extends KcPersistableBusinessObjectBase implements Comparable<QuestionnaireUsage>, SequenceAssociate<Questionnaire>, QuestionnaireUsageContract {

    private static final long serialVersionUID = -5676341963373665440L;

    private Long id;

    private String moduleItemCode;

    private String moduleSubItemCode;

    private Long questionnaireId;

    private String ruleId;

    private String questionnaireLabel;

    private Integer questionnaireSequenceNumber;

    private boolean mandatory;

    private CoeusModule coeusModule;
    
    private CoeusSubModule coeusSubModule;

    private Questionnaire questionnaire;

    private Questionnaire sequenceOwner;
    
    private transient boolean delete;

    private transient BusinessObjectService businessObjectService;

    protected BusinessObjectService getBusinessObjectService (){
        if (businessObjectService == null)
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        return businessObjectService;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getModuleItemCode() {
        return moduleItemCode;
    }

    public void setModuleItemCode(String moduleItemCode) {
        this.moduleItemCode = moduleItemCode;
    }

    @Override
    public String getModuleSubItemCode() {
        return moduleSubItemCode;
    }

    public void setModuleSubItemCode(String moduleSubItemCode) {
        this.moduleSubItemCode = moduleSubItemCode;
    }

    @Override
    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    @Override
    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    @Override
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

    @Override
    public void resetPersistenceState() {
        this.setId(null);
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
        if (ObjectUtils.equals(this.getQuestionnaire().getQuestionnaireSeqId(), argQuestionnaireUsage.getQuestionnaire().getQuestionnaireSeqId())) {
            if (ObjectUtils.equals(this.getQuestionnaireId(), argQuestionnaireUsage.getQuestionnaireId())) {
                return argQuestionnaireUsage.getQuestionnaireSequenceNumber().compareTo(this.getQuestionnaireSequenceNumber());
            } else {
                // compare refid is not good : 1. it's a string now, so '753' > '1001'. 2. the proposalpersonqn has high refid 6111.
                // so the assumption of newer version of qnnaire has higher refid is not right for this case.
                // compare qnnaire sequence is the right approach
                return argQuestionnaireUsage.getQuestionnaire().getSequenceNumber().compareTo(this.getQuestionnaire().getSequenceNumber());
            }
        } else {
            return this.getQuestionnaire().getQuestionnaireSeqIdAsInteger().compareTo(argQuestionnaireUsage.getQuestionnaire().getQuestionnaireSeqIdAsInteger());
        }
    }

    @Override
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
                    (List<CoeusSubModule>) getBusinessObjectService().findMatching(CoeusSubModule.class, fieldValues);
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
