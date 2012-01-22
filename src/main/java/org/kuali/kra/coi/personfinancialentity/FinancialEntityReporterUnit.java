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

import org.kuali.kra.coi.DisclosureReporterUnit;

/**
 * 
 * This class is FE reporter units.  KC only
 */
public class FinancialEntityReporterUnit extends DisclosureReporterUnit {

    private static final long serialVersionUID = -1254443328656115963L;

    private Long financialEntityReporterUnitsId;

    private Long financialEntityReporterId;

    private String unitNumber;

    private boolean leadUnitFlag;

    private String personId;

    //    @SkipVersioning  
    private FinancialEntityReporter financialEntityReporter;

    public FinancialEntityReporterUnit() {
        setLeadUnitFlag(false);
    }

    public Long getFinancialEntityReporterUnitsId() {
        return financialEntityReporterUnitsId;
    }

    public void setFinancialEntityReporterUnitsId(Long financialEntityReporterUnitsId) {
        this.financialEntityReporterUnitsId = financialEntityReporterUnitsId;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public boolean isLeadUnitFlag() {
        return leadUnitFlag;
    }

    public void setLeadUnitFlag(boolean leadUnitFlag) {
        this.leadUnitFlag = leadUnitFlag;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Long getFinancialEntityReporterId() {
        return financialEntityReporterId;
    }

    public void setFinancialEntityReporterId(Long financialEntityReporterId) {
        this.financialEntityReporterId = financialEntityReporterId;
    }

    public FinancialEntityReporter getFinancialEntityReporter() {
        return financialEntityReporter;
    }

    public void setFinancialEntityReporter(FinancialEntityReporter financialEntityReporter) {
        this.financialEntityReporter = financialEntityReporter;
    }

    @Override
    public Long getReporterUnitId() {
        return getFinancialEntityReporterUnitsId();
    }
}
