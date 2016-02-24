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
package org.kuali.coeus.common.framework.print;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.proposal.framework.report.CurrentAndPendingReportService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.io.Serializable;
import java.util.*;


public class ReportHelper implements Serializable {

    @Autowired
    @Qualifier("currentAndPendingReportService")
    private CurrentAndPendingReportService currentAndPendingReportService;

    private String reportType;
    private String personId;
    private KcPerson targetPerson;
    private List<CurrentReportBean> currentReportBeans;
    private List<PendingReportBean> pendingReportBeans;

    public ReportHelper() {
        currentReportBeans = new ArrayList<CurrentReportBean>();
        pendingReportBeans = new ArrayList<PendingReportBean>();
        setTargetPerson(new KcPerson());

    }

    public List<CurrentReportBean> getCurrentReportBeans() {
        return currentReportBeans;
    }

    public void setCurrentReportBeans(List<CurrentReportBean> currentReportBeans) {
        this.currentReportBeans = currentReportBeans;
    }

    public List<PendingReportBean> getPendingReportBeans() {
        return pendingReportBeans;
    }

    public void setPendingReportBeans(List<PendingReportBean> pendingReportBeans) {
        this.pendingReportBeans = pendingReportBeans;
    }

    public void prepareCurrentReport() {
       setCurrentReportBeans(getCurrentAndPendingReportService().loadCurrentReportData(personId));
    }

    public void preparePendingReport() {
        setPendingReportBeans(getCurrentAndPendingReportService().loadPendingReportData(personId));
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public KcPerson getTargetPerson() {
        return targetPerson;
    }

    public void setTargetPerson(KcPerson targetPerson) {
        this.targetPerson = targetPerson;
    }

    public CurrentAndPendingReportService getCurrentAndPendingReportService() {
        if (currentAndPendingReportService == null) {
            currentAndPendingReportService = KcServiceLocator.getService(CurrentAndPendingReportService.class);
        }
        return currentAndPendingReportService;
    }

    public void setCurrentAndPendingReportService(CurrentAndPendingReportService currentAndPendingReportService) {
        this.currentAndPendingReportService = currentAndPendingReportService;
    }


}
