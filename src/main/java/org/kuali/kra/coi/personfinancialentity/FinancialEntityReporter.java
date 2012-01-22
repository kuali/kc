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
package org.kuali.kra.coi.personfinancialentity;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.coi.DisclosureReporter;
import org.kuali.kra.coi.DisclosureReporterUnit;

/**
 * 
 * This class is for FE reporter.  KC only
 */
public class FinancialEntityReporter extends DisclosureReporter {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2436497197072156523L;

    /** TODO : not sure about this table.  
     * 1. should we combine this with coi reporter/correspondent, 
     * 2. personRoleId do we need it 
    **/
    private Long financialEntityReporterId;

    private String personId;

    //    private String personName;  
    private String reporterRoleId;

    private List<FinancialEntityReporterUnit> financialEntityReporterUnits;

    private List<PersonFinIntDisclosure> personFinIntDisclosures;

    public FinancialEntityReporter() {
        this.financialEntityReporterUnits = new ArrayList<FinancialEntityReporterUnit>();
        this.personFinIntDisclosures = new ArrayList<PersonFinIntDisclosure>();
        this.reporterRoleId = "FER";
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    //    public String getPersonName() {  
    //        return personName;  
    //    }  
    //  
    //  
    //    public void setPersonName(String personName) {  
    //        this.personName = personName;  
    //    }  
    //  
    public String getReporterRoleId() {
        return reporterRoleId;
    }

    public void setReporterRoleId(String reporterRoleId) {
        this.reporterRoleId = reporterRoleId;
    }

    public Long getFinancialEntityReporterId() {
        return financialEntityReporterId;
    }

    public void setFinancialEntityReporterId(Long financialEntityReporterId) {
        this.financialEntityReporterId = financialEntityReporterId;
    }

    public List<FinancialEntityReporterUnit> getFinancialEntityReporterUnits() {
        return financialEntityReporterUnits;
    }

    public void setFinancialEntityReporterUnits(List<FinancialEntityReporterUnit> financialEntityReporterUnits) {
        this.financialEntityReporterUnits = financialEntityReporterUnits;
    }

    public List<PersonFinIntDisclosure> getPersonFinIntDisclosures() {
        return personFinIntDisclosures;
    }

    public void setPersonFinIntDisclosures(List<PersonFinIntDisclosure> personFinIntDisclosures) {
        this.personFinIntDisclosures = personFinIntDisclosures;
    }

    @Override
    public List<? extends DisclosureReporterUnit> getDisclosureReporterUnits() {
        return getFinancialEntityReporterUnits();
    }
}
