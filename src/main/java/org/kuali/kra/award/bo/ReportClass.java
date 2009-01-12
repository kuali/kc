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
 * This class represents the ReportClass business object and is mapped to
 * REPORT_CLASS table.
 */
public class ReportClass extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2641812275218339806L;
    private Integer reportClassCode; 
    private String description; 
    private boolean generateReportRequirements; 
    
    /*private TemplateReportTerms templateReportTerms; 
    private ValidClassReportFreq validClassReportFreq; 
    private AwardReporting awardReporting; 
    private AwardReportTerms awardReportTerms;*/ 
    
    /**
     * Constructs a ReportClass.java
     */
    public ReportClass() { 

    } 
    
    /**
     * 
     * @return
     */
    public Integer getReportClassCode() {
        return reportClassCode;
    }

    /**
     * 
     * @param reportClassCode
     */
    public void setReportClassCode(Integer reportClassCode) {
        this.reportClassCode = reportClassCode;
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
    public boolean getGenerateReportRequirements() {
        return generateReportRequirements;
    }

    /**
     * 
     * @param generateReportRequirements
     */
    public void setGenerateReportRequirements(boolean generateReportRequirements) {
        this.generateReportRequirements = generateReportRequirements;
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
    protected LinkedHashMap<String,Object> toStringMapper() {
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("reportClassCode", getReportClassCode());
        hashMap.put("description", getDescription());
        hashMap.put("generateReportRequirements", getGenerateReportRequirements());
        return hashMap;
    }
    
}