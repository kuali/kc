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
 * This class represents the Report business object and is mapped
 * with REPORT table.
 */
public class Report extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4555054434451627778L;
    private Integer reportCode; 
    private String description; 
    private boolean finalReportFlag; 
    
    /*private TemplateReportTerms templateReportTerms; 
    private ValidClassReportFreq validClassReportFreq; 
    private AwardReporting awardReporting; 
    private AwardReportTerms awardReportTerms;*/ 
    
    /**
     * Constructs a Report.java
     */
    public Report() { 

    } 
    
    /**
     * 
     * @return
     */
    public Integer getReportCode() {
        return reportCode;
    }

    /**
     * 
     * @param reportCode
     */
    public void setReportCode(Integer reportCode) {
        this.reportCode = reportCode;
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
    public boolean getFinalReportFlag() {
        return finalReportFlag;
    }

    /**
     * 
     * @param finalReportFlag
     */
    public void setFinalReportFlag(boolean finalReportFlag) {
        this.finalReportFlag = finalReportFlag;
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
        hashMap.put("reportCode", getReportCode());
        hashMap.put("description", getDescription());
        hashMap.put("finalReportFlag", getFinalReportFlag());
        return hashMap;
    }
    
}