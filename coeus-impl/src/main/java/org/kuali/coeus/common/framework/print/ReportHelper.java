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