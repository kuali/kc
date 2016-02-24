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
package org.kuali.kra.protocol.actions.print;

import java.io.Serializable;

/**
 * 
 * This class is a bean for questionnaire print options.  It is used 
 * Questionnaire print option in protocol print.
 */
public class QuestionnairePrintOption implements Serializable {

    private static final long serialVersionUID = -2388949517675336400L;
    private Long questionnaireId;
    private Integer questionnaireSeqId;
    private String label;
    private String questionnaireName;
    private String itemKey;
    private String subItemKey;
    private String subItemCode;
    private boolean selected;
    private boolean questionnaireActive;

    
    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getSubItemKey() {
        return subItemKey;
    }

    public void setSubItemKey(String subItemKey) {
        this.subItemKey = subItemKey;
    }

    public String getSubItemCode() {
        return subItemCode;
    }

    public void setSubItemCode(String subItemCode) {
        this.subItemCode = subItemCode;
    }

    public Integer getQuestionnaireSeqId() {
        return questionnaireSeqId;
    }

    public void setQuestionnaireSeqId(Integer questionnaireSeqId) {
        this.questionnaireSeqId = questionnaireSeqId;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public boolean isQuestionnaireActive() {
        return questionnaireActive;
    }

    public void setQuestionnaireActive(boolean questionnaireActive) {
        this.questionnaireActive = questionnaireActive;
    }


}
