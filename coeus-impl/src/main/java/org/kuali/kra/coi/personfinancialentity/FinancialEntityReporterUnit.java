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
