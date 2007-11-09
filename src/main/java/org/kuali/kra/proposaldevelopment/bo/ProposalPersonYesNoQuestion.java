/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Ynq;

/**
 * Represents the Proposal Investigator Certification <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @see org.kuali.core.bo.BusinessObject
 * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
 * @author $Author: lprzybyl $
 * @version $Revision: 1.2 $
 */
public final class ProposalPersonYesNoQuestion  extends KraPersistableBusinessObjectBase {
    private Integer proposalPersonNumber;
    private Integer proposalNumber;
    private String questionId;
    private Boolean answer;
    private Ynq question;

    /**
     * Gets the value of proposalPersonNumber
     *
     * @return the value of proposalPersonNumber
     */
    public Integer getProposalPersonNumber() {
        return this.proposalPersonNumber;
    }

    /**
     * Sets the value of proposalPersonNumber
     *
     * @param argProposalPersonNumber Value to assign to this.proposalPersonNumber
     */
    public void setProposalPersonNumber(Integer argProposalPersonNumber) {
        this.proposalPersonNumber = argProposalPersonNumber;
    }

    /**
     * Gets the value of proposalNumber
     *
     * @return the value of proposalNumber
     */
    public Integer getProposalNumber() {
        return this.proposalNumber;
    }

    /**
     * Sets the value of proposalNumber
     *
     * @param argProposalNumber Value to assign to this.proposalNumber
     */
    public void setProposalNumber(Integer argProposalNumber) {
        this.proposalNumber = argProposalNumber;
    }

    /**
     * Gets the value of questionId
     *
     * @return the value of questionId
     */
    public String getQuestionId() {
        return this.questionId;
    }

    /**
     * Sets the value of questionId
     *
     * @param argQuestionId Value to assign to this.questionId
     */
    public void setQuestionId(String argQuestionId) {
        this.questionId = argQuestionId;
    }

    /**
     * Gets the value of answer
     *
     * @return the value of answer
     */
    public Boolean getAnswer() {
        return this.answer;
    }

    /**
     * Sets the value of answer
     *
     * @param argAnswer Value to assign to this.answer
     */
    public void setAnswer(Boolean argAnswer) {
        this.answer = argAnswer;
    }


    /**
     * Gets the value of ynq
     *
     * @return the value of ynq
     */
    public Ynq getQuestion() {
        return this.question;
    }

    /**
     * Sets the value of ynq
     *
     * @param argYnq Value to assign to this.ynq
     */
    public void setQuestion(Ynq argYnq) {
        this.question = argYnq;
    }


    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("proposalPersonNumber", getProposalPersonNumber());
        propMap.put("proposalNumber", getProposalNumber());
        propMap.put("questionId", getQuestionId());
        propMap.put("answer", getAnswer());
        propMap.put("question", getQuestion());
        return propMap;
    }

}


