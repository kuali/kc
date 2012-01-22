/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.FrequencyBase;
import org.kuali.kra.award.paymentreports.Report;
import org.kuali.kra.award.paymentreports.ValidClassReportFrequency;
import org.kuali.kra.award.paymentreports.ValidFrequencyBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardTemplateReportTermService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AwardTemplateReportTermServiceImpl implements AwardTemplateReportTermService{
    
    private BusinessObjectService businessObjectService;
    
    /**
     * 
     * @see org.kuali.kra.service.AwardTemplateReportTermService#getReportTypeForAjaxCall(java.lang.String)
     */
    public String getReportTypeForAjaxCall(String reportClassCode) throws Exception {
        Collection reportTypes = getReportTypesUsingReportClassCode(reportClassCode);
        String attributeNames="";
        for (Object aReportCode : reportTypes) {
            Report aReport = businessObjectService.findBySinglePrimaryKey(Report.class, aReportCode);
            if(aReport != null) {
                attributeNames += "," + aReportCode +";"+ aReport.getDescription();
            }
        }
        return attributeNames;
    }
    
    public Collection getReportTypesUsingReportClassCode(String reportClassCode) throws Exception {
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> classReportFreqParams = new HashMap<String, String>();
        classReportFreqParams.put("reportClassCode", reportClassCode);
        Collection<ValidClassReportFrequency> coll = businessObjectService.findMatching(ValidClassReportFrequency.class, classReportFreqParams);
        Set<String> reportTypes = new HashSet<String>();
        if ( !coll.isEmpty()) {
            for (ValidClassReportFrequency aVcrf: coll) {
                reportTypes.add(aVcrf.getReportCode());
            }
        }
        return reportTypes;
    }
    
    /**
     * 
     * @see org.kuali.kra.service.AwardTemplateReportTermService#getFrequencyForAjaxCall(java.lang.String, java.lang.String)
     */
    public String getFrequencyForAjaxCall(String reportCode, String reportClass) throws Exception {
        Collection frequencyCodes = getFrequencyUsingReportCodeAndClass(reportCode, reportClass);
        String attributeNames="";
        for (Object aFrequencyCode : frequencyCodes) {
            Frequency aFrequency = businessObjectService.findBySinglePrimaryKey(Frequency.class, aFrequencyCode);
            if(aFrequency != null) {
                attributeNames += "," + aFrequencyCode +";"+ aFrequency.getDescription();
            }
        }
        return attributeNames;
    }
    
    public Collection getFrequencyUsingReportCodeAndClass(String reportCode, String reportClass) throws Exception {
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> classReportFreqParams = new HashMap<String, String>();
        classReportFreqParams.put("reportCode", reportCode);
        classReportFreqParams.put("reportClassCode", reportClass);
        Collection<ValidClassReportFrequency> coll = businessObjectService.findMatching(ValidClassReportFrequency.class, classReportFreqParams);
        Set<String> frequencyCodes = new HashSet<String>();
        if ( !coll.isEmpty()) {
            for (ValidClassReportFrequency aVcrf: coll) {
                frequencyCodes.add(aVcrf.getFrequencyCode());
            }
        }
        return frequencyCodes;
    }
    
    /**
     * 
     * @see org.kuali.kra.service.AwardTemplateReportTermService#getFrequencyBaseForAjaxCall(java.lang.String)
     */
    public String getFrequencyBaseForAjaxCall(String frequencyCode) throws Exception {
        Collection frequencyBaseCodes = getFrequencyBaseUsingFrequencyCode(frequencyCode);
        String attributeNames="";
        for (Object aFrequencyBaseCode : frequencyBaseCodes) {
            FrequencyBase aFrequencyBase = businessObjectService.findBySinglePrimaryKey(FrequencyBase.class, aFrequencyBaseCode);
            if(aFrequencyBase != null) {
                attributeNames += "," + aFrequencyBaseCode +";"+ aFrequencyBase.getDescription();
            }
        }
        return attributeNames;
    }
    
    public Collection getFrequencyBaseUsingFrequencyCode(String frequencyCode) throws Exception {
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("frequencyCode", frequencyCode);
        Collection<ValidFrequencyBase> coll = businessObjectService.findMatching(ValidFrequencyBase.class, params);
        Set<String> frequencyBaseCodes = new HashSet<String>();
        if ( !coll.isEmpty()) {
            for (ValidFrequencyBase aVcrf: coll) {
                frequencyBaseCodes.add(aVcrf.getFrequencyBaseCode());
            }
        }
        return frequencyBaseCodes;
    }
}
