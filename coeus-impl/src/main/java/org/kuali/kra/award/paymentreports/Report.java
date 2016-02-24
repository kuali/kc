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
 * This class represents the Report business object and is mapped
 * with REPORT table.
 */
public class Report extends KcPersistableBusinessObjectBase implements MutableInactivatable {


    private static final long serialVersionUID = 4555054434451627778L;

    private String reportCode;

    private String description;

    private boolean finalReportFlag;

    private boolean active;


    public Report() {
    }


    public String getReportCode() {
        return reportCode;
    }

    /**
     * 
     * @param reportCode
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
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


    public boolean getFinalReportFlag() {
        return finalReportFlag;
    }

    /**
     * 
     * @param finalReportFlag
     */
    public void setFinalReportFlag(boolean finalReportFlag) {
        this.finalReportFlag = finalReportFlag;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((reportCode == null) ? 0 : reportCode.hashCode());
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
        if (!(obj instanceof Report)) {
            return false;
        }
        return equals((Report) obj);
    }

    /**
     * 
     * Convenience method with Report
     * @param report
     * @return
     */
    public boolean equals(Report report) {
        if (reportCode == null) {
            if (report.reportCode != null) {
                return false;
            }
        } else if (!reportCode.equals(report.reportCode)) {
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
