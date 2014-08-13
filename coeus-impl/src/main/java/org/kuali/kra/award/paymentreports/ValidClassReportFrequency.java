/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.paymentreports;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * 
 * This class represents a ValidClassReportFrequency business objects 
 */
public class ValidClassReportFrequency extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 369663255546045771L;

    private Integer validClassReportFreqId;

    private String reportClassCode;

    private String reportCode;

    private String frequencyCode;

    private Frequency frequency;

    private Report report;

    private ReportClass reportClass;


    public ValidClassReportFrequency() {
    }

    public ValidClassReportFrequency(String reportClassCode, String reportCode, String frquencyCode) {
        this.reportClassCode = reportClassCode;
        this.reportCode = reportCode;
        this.frequencyCode = frquencyCode;
    }


    public Integer getValidClassReportFreqId() {
        return validClassReportFreqId;
    }

    /**
     *
     * @param validClassReportFreqId
     */
    public void setValidClassReportFreqId(Integer validClassReportFreqId) {
        this.validClassReportFreqId = validClassReportFreqId;
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


    public String getFrequencyCode() {
        return frequencyCode;
    }

    /**
     *
     * @param frequencyCode
     */
    public void setFrequencyCode(String frequencyCode) {
        this.frequencyCode = frequencyCode;
    }


    public Frequency getFrequency() {
        return frequency;
    }

    /**
     *
     * @param frequency
     */
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }


    public Report getReport() {
        return report;
    }

    /**
     *
     * @param report
     */
    public void setReport(Report report) {
        this.report = report;
    }


    public ReportClass getReportClass() {
        return reportClass;
    }

    /**
     *
     * @param reportClass
     */
    public void setReportClass(ReportClass reportClass) {
        this.reportClass = reportClass;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((frequencyCode == null) ? 0 : frequencyCode.hashCode());
        result = PRIME * result + ((reportClassCode == null) ? 0 : reportClassCode.hashCode());
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
        if (!(obj instanceof ValidClassReportFrequency)) {
            return false;
        }
        return equals((ValidClassReportFrequency) obj);
    }

    /**
     * 
     * Convenience method for equality of ValidClassReportFrequency
     * @param validClassReportFrequency
     * @return
     */
    public boolean equals(ValidClassReportFrequency validClassReportFrequency) {
        if (frequencyCode == null) {
            if (validClassReportFrequency.frequencyCode != null) {
                return false;
            }
        } else if (!frequencyCode.equals(validClassReportFrequency.frequencyCode)) {
            return false;
        }
        if (reportClassCode == null) {
            if (validClassReportFrequency.reportClassCode != null) {
                return false;
            }
        } else if (!reportClassCode.equals(validClassReportFrequency.reportClassCode)) {
            return false;
        }
        if (reportCode == null) {
            if (validClassReportFrequency.reportCode != null) {
                return false;
            }
        } else if (!reportCode.equals(validClassReportFrequency.reportCode)) {
            return false;
        }
        return true;
    }
}
