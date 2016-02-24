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
package org.kuali.kra.coi.personfinancialentity;

import org.kuali.kra.coi.DisclosureReporter;
import org.kuali.kra.coi.DisclosureReporterUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is for FE reporter.  KC only
 */
public class FinancialEntityReporter extends DisclosureReporter {


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
