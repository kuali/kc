/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.comparator.AwardReportTermsComparator1;
import org.kuali.kra.award.comparator.AwardReportTermsComparator2;
import org.kuali.kra.award.comparator.AwardReportTermsComparator3;
import org.kuali.kra.award.comparator.AwardReportTermsComparator4;
import org.kuali.kra.award.comparator.AwardReportTermsComparator5;
import org.kuali.kra.award.lookup.keyvalue.ReportClassValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.service.AwardReportsService;

/**
 * 
 * This is the implementation of <code>AwardReportsService</code> interface.
 */
public class AwardReportsServiceImpl implements AwardReportsService {
    
    /**
     * 
     * @see org.kuali.kra.service.AwardReportsService#doPreparations(
     * org.kuali.kra.award.web.struts.form.AwardForm)
     */
    public void doPreparations(AwardForm awardForm){
        
        assignReportClassToAwardForm(awardForm);        
        assignReportCodeToAwardForm(awardForm);        
        sortAwardReportTerms(awardForm.getAwardDocument().getAward().getAwardReportTerms());
    }
    
    /**
     * 
     * This method sorts the list of AwardReportTerm objects using different comparator classes.
     * @param awardReportTerms
     */
    protected void sortAwardReportTerms(List<AwardReportTerm> awardReportTerms){
        Collections.sort(awardReportTerms, new AwardReportTermsComparator5());
        Collections.sort(awardReportTerms, new AwardReportTermsComparator4());
        Collections.sort(awardReportTerms, new AwardReportTermsComparator3());
        Collections.sort(awardReportTerms, new AwardReportTermsComparator2());
        Collections.sort(awardReportTerms, new AwardReportTermsComparator1());
    }
    
    protected void assignReportClassToAwardForm(AwardForm awardForm){
        ReportClassValuesFinder reportClassValuesFinder = new ReportClassValuesFinder();
        List<KeyLabelPair> reportClasses = new ArrayList<KeyLabelPair>();
        
        reportClasses = reportClassValuesFinder.getKeyValues();
        awardForm.setReportClasses(reportClasses);
        
        for(int i=0;i<reportClasses.size();i++){
            awardForm.getNewAwardReportTerm().add(new AwardReportTerm());
        }        
    }
    
    protected void assignReportCodeToAwardForm(AwardForm awardForm){
        ReportCodeValuesFinder reportCodeValuesFinder = new ReportCodeValuesFinder();
        
        awardForm.setReportCodes(reportCodeValuesFinder.getKeyValues());        
        
        for(int i=0;i<awardForm.getAwardDocument().getAward().getAwardReportTerms().size();i++){
            awardForm.getNewAwardReportTermRecipient().add(new AwardReportTerm());
        }
    }

}
