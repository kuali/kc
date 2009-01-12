/*
 * Copyright 2006-2008 The Kuali Foundation
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

package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represents the Frequency business object and is mapped
 * with FREQUENCY table.
 */
public class Frequency extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8019195552412635447L;
    private Integer frequencyCode; 
    private String description; 
    private Integer numberOfDays; 
    private Integer numberOfMonths; 
    private boolean repeatFlag; 
    private boolean proposalDueFlag; 
    private boolean invoiceFlag; 
    private Integer advanceNumberOfDays; 
    private Integer advanceNumberOfMonths; 
    
    /*private TemplateReportTerms templateReportTerms; 
    private ValidClassReportFreq validClassReportFreq; 
    private ValidFrequencyBase validFrequencyBase; 
    private AwardReporting awardReporting; 
    private AwardReportTerms awardReportTerms;*/ 
    
    /**
     * Constructs a Frequency object.
     */
    public Frequency() { 

    } 
    
    /**
     *
     * @return
     */
    public Integer getFrequencyCode() {
        return frequencyCode;
    }

    /**
     *
     * @param frequencyCode
     */
    public void setFrequencyCode(Integer frequencyCode) {
        this.frequencyCode = frequencyCode;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    /**
     *
     * @param numberOfDays
     */
    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    /**
     *
     * @return
     */
    public Integer getNumberOfMonths() {
        return numberOfMonths;
    }

    /**
     *
     * @param numberOfMonths
     */
    public void setNumberOfMonths(Integer numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    /**
     *
     * @return
     */
    public boolean getRepeatFlag() {
        return repeatFlag;
    }

    /**
     *
     * @param repeatFlag
     */
    public void setRepeatFlag(boolean repeatFlag) {
        this.repeatFlag = repeatFlag;
    }

    /**
     *
     * @return
     */
    public boolean getProposalDueFlag() {
        return proposalDueFlag;
    }

    /**
     *
     * @param proposalDueFlag
     */
    public void setProposalDueFlag(boolean proposalDueFlag) {
        this.proposalDueFlag = proposalDueFlag;
    }

    /**
     *
     * @return
     */
    public boolean getInvoiceFlag() {
        return invoiceFlag;
    }

    /**
     *
     * @param invoiceFlag
     */
    public void setInvoiceFlag(boolean invoiceFlag) {
        this.invoiceFlag = invoiceFlag;
    }

    /**
     *
     * @return
     */
    public Integer getAdvanceNumberOfDays() {
        return advanceNumberOfDays;
    }

    /**
     *
     * @param advanceNumberOfDays
     */
    public void setAdvanceNumberOfDays(Integer advanceNumberOfDays) {
        this.advanceNumberOfDays = advanceNumberOfDays;
    }

    /**
     *
     * @return
     */
    public Integer getAdvanceNumberOfMonths() {
        return advanceNumberOfMonths;
    }

    /**
     *
     * @param advanceNumberOfMonths
     */
    public void setAdvanceNumberOfMonths(Integer advanceNumberOfMonths) {
        this.advanceNumberOfMonths = advanceNumberOfMonths;
    }

    /*public TemplateReportTerms getTemplateReportTerms() {
        return templateReportTerms;
    }

    public void setTemplateReportTerms(TemplateReportTerms templateReportTerms) {
        this.templateReportTerms = templateReportTerms;
    }

    public ValidClassReportFreq getValidClassReportFreq() {
        return validClassReportFreq;
    }

    public void setValidClassReportFreq(ValidClassReportFreq validClassReportFreq) {
        this.validClassReportFreq = validClassReportFreq;
    }

    public ValidFrequencyBase getValidFrequencyBase() {
        return validFrequencyBase;
    }

    public void setValidFrequencyBase(ValidFrequencyBase validFrequencyBase) {
        this.validFrequencyBase = validFrequencyBase;
    }

    public AwardReporting getAwardReporting() {
        return awardReporting;
    }

    public void setAwardReporting(AwardReporting awardReporting) {
        this.awardReporting = awardReporting;
    }

    public AwardReportTerms getAwardReportTerms() {
        return awardReportTerms;
    }

    public void setAwardReportTerms(AwardReportTerms awardReportTerms) {
        this.awardReportTerms = awardReportTerms;
    }*/

    /**
     * 
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("frequencyCode", getFrequencyCode());
        hashMap.put("description", getDescription());
        hashMap.put("numberOfDays", getNumberOfDays());
        hashMap.put("numberOfMonths", getNumberOfMonths());
        hashMap.put("repeatFlag", getRepeatFlag());
        hashMap.put("proposalDueFlag", getProposalDueFlag());
        hashMap.put("invoiceFlag", getInvoiceFlag());
        hashMap.put("advanceNumberOfDays", getAdvanceNumberOfDays());
        hashMap.put("advanceNumberOfMonths", getAdvanceNumberOfMonths());
        return hashMap;
    }
    
}