/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.service.impl;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.paymentreports.*;
import org.kuali.kra.award.service.AwardTemplateReportTermService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

public class AwardTemplateReportTermServiceImpl implements AwardTemplateReportTermService{
    
    private BusinessObjectService businessObjectService;
    
    @Override
    public String getReportTypeForAjaxCall(String reportClassCode)  {
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
    
    public Collection<String> getReportTypesUsingReportClassCode(String reportClassCode) {
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
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
    
    @Override
    public String getFrequencyForAjaxCall(String reportCode, String reportClass) {
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
    
    public Collection<String> getFrequencyUsingReportCodeAndClass(String reportCode, String reportClass) {
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
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
    
    @Override
    public String getFrequencyBaseForAjaxCall(String frequencyCode) {
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
    
    public Collection<String> getFrequencyBaseUsingFrequencyCode(String frequencyCode) {
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
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
