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
 * This class represents the FrequencyBase business object and is mapped
 * with FREQUENCY_BASE table.
 */
public class FrequencyBase extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5406416029670950959L;
    private String frequencyBaseCode; 
    private String description; 
    
    /*private TemplateReportTerms templateReportTerms; 
    private ValidFrequencyBase validFrequencyBase; 
    private AwardReporting awardReporting; 
    private AwardReportTerms awardReportTerms;*/ 
    
    /**
     * Constructs a FrequencyBase object.
     */
    public FrequencyBase() { 

    } 
    
    /**
     * 
     * This method...
     * @return
     */
    public String getFrequencyBaseCode() {
        return frequencyBaseCode;
    }

    /**
     * 
     * @param frequencyBaseCode
     */
    public void setFrequencyBaseCode(String frequencyBaseCode) {
        this.frequencyBaseCode = frequencyBaseCode;
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

    /*public TemplateReportTerms getTemplateReportTerms() {
        return templateReportTerms;
    }

    public void setTemplateReportTerms(TemplateReportTerms templateReportTerms) {
        this.templateReportTerms = templateReportTerms;
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
        hashMap.put("frequencyBaseCode", getFrequencyBaseCode());
        hashMap.put("description", getDescription());
        return hashMap;
    }
    
}