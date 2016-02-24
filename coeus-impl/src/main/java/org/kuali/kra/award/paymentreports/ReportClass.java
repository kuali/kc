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
package org.kuali.kra.award.paymentreports;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

/**
 * 
 * This class represents the ReportClass business object and is mapped to
 * REPORT_CLASS table.
 */
public class ReportClass extends KcPersistableBusinessObjectBase implements MutableInactivatable {


    private static final long serialVersionUID = 2641812275218339806L;

    private String reportClassCode;

    private String description;

    private boolean generateReportRequirements;

    private boolean active;


    public ReportClass() {
    }


    public String getReportClassCode() {
        return reportClassCode;
    }

    /**
     * 
     * @param reportClassCode
     */
    public void setReportClassCode(String reportClassCode) {
        this.reportClassCode = reportClassCode;
    }


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

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((reportClassCode == null) ? 0 : reportClassCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ReportClass)) {
            return false;
        }
        return equals((ReportClass) obj);
    }

    /**
     * 
     * Convenience method for equality of ReportClass
     * @param reportClass
     * @return
     */
    public boolean equals(ReportClass reportClass) {
        if (reportClassCode == null) {
            if (reportClass.reportClassCode != null) {
                return false;
            }
        } else if (!reportClassCode.equals(reportClass.reportClassCode)) {
            return false;
        }
        return true;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
