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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.paymentreports.ReportStatus;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.service.AwardScheduleGenerationService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class manages the services needed for Report Tracking.
 */
public class ReportTrackingServiceImpl implements ReportTrackingService {
    
    private static final String PENDING_STATUS_DESCRIPTION = "Pending";
    
    private AwardScheduleGenerationService awardScheduleGenerationService;
    private BusinessObjectService businessObjectService;
    
    @Override
    public void refreshReportTracking(Award award) throws ParseException {
        List<AwardReportTerm> awardReportTermItems = award.getAwardReportTermItems();      
        for (AwardReportTerm awardTerm : awardReportTermItems) {
            List<java.util.Date> dates = new ArrayList<java.util.Date>();
            List<AwardReportTerm> awardReportTerms = new ArrayList<AwardReportTerm>();
            awardReportTerms.add(awardTerm);
            dates = getAwardScheduleGenerationService().generateSchedules(award, awardReportTerms, true);
            if (awardTerm.getReportTrackings() == null) {
                awardTerm.setReportTrackings(getReportTacking(awardTerm));
            } else {
                awardTerm.setReportTrackings(purgePendingReports(awardTerm.getReportTrackings(), new ArrayList<ReportTracking>()));
            }
            
            if (dates.size() == 0) {
                ReportTracking rt = buildReportTracking(award, awardTerm);
                awardTerm.getReportTrackings().add(rt);
            }
            
            for (java.util.Date date : dates) {
                if (!isAwardTermDateAlreadySet(awardTerm.getReportTrackings(), date)) {
                    ReportTracking rt = buildReportTracking(award, awardTerm);
                    java.sql.Date sqldate = new java.sql.Date(date.getTime());
                    rt.setDueDate(sqldate);
                    awardTerm.getReportTrackings().add(rt);
                }
            }
            Collections.sort(awardTerm.getReportTrackings());
        }
    }

    @Override
    public void generateReportTrackingAndSave(Award award) throws ParseException {
        List<AwardReportTerm> awardReportTermItems = award.getAwardReportTermItems();
        List<ReportTracking> reportsToSave = new ArrayList<ReportTracking>();
        List<ReportTracking> reportsToDelete = new ArrayList<ReportTracking>();        
        for (AwardReportTerm awardTerm : awardReportTermItems) {
            List<java.util.Date> dates = new ArrayList<java.util.Date>();
            List<AwardReportTerm> awardReportTerms = new ArrayList<AwardReportTerm>();
            awardReportTerms.add(awardTerm);
            dates = getAwardScheduleGenerationService().generateSchedules(award, awardReportTerms, true);
            if (awardTerm.getReportTrackings() == null) {
                awardTerm.setReportTrackings(getReportTacking(awardTerm));
            } else {
                awardTerm.setReportTrackings(purgePendingReports(awardTerm.getReportTrackings(), reportsToDelete));
                reportsToSave.addAll(awardTerm.getReportTrackings());
            }
            
            if (dates.size() == 0) {
                ReportTracking rt = buildReportTracking(award, awardTerm);
                awardTerm.getReportTrackings().add(rt);
            }
            
            for (java.util.Date date : dates) {
                if (!isAwardTermDateAlreadySet(awardTerm.getReportTrackings(), date)) {
                    ReportTracking rt = buildReportTracking(award, awardTerm);
                    java.sql.Date sqldate = new java.sql.Date(date.getTime());
                    rt.setDueDate(sqldate);
                    awardTerm.getReportTrackings().add(rt);
                    reportsToSave.add(rt);
                }
            }
            Collections.sort(awardTerm.getReportTrackings());
        }
        this.getBusinessObjectService().delete(reportsToDelete);
        this.getBusinessObjectService().save(reportsToSave);
    }
    
    private ReportTracking buildReportTracking(Award award, AwardReportTerm awardTerm) {
        awardTerm.refresh();
        ReportTracking reportTracking = new ReportTracking();
        reportTracking.setAwardNumber(award.getAwardNumber());
        reportTracking.setAwardReportTermId(awardTerm.getAwardReportTermId());
        reportTracking.setDueDate(awardTerm.getDueDate());
        reportTracking.setFrequency(awardTerm.getFrequency());
        reportTracking.setFrequencyBase(awardTerm.getFrequencyBase());
        reportTracking.setFrequencyBaseCode(awardTerm.getFrequencyBaseCode());
        reportTracking.setFrequencyCode(awardTerm.getFrequencyCode());
        java.sql.Date lastUpdateDate = new java.sql.Date(new java.util.Date().getTime());
        reportTracking.setLastUpdateDate(lastUpdateDate);
        reportTracking.setLastUpdateUser(GlobalVariables.getUserSession().getPerson().getName());
        reportTracking.setLeadUnit(award.getLeadUnit());
        reportTracking.setLeadUnitNumber(award.getLeadUnitNumber());
        reportTracking.setOverdue(0);
        reportTracking.setPiName(award.getPrincipalInvestigatorName());
        if (award.getPrincipalInvestigator() != null) {
            reportTracking.setPiPersonId(award.getPrincipalInvestigator().getPersonId());
            reportTracking.setPiRolodexId(award.getPrincipalInvestigator().getRolodexId());
        }
        reportTracking.setReport(awardTerm.getReport());
        reportTracking.setReportClass(awardTerm.getReportClass());
        reportTracking.setReportClassCode(awardTerm.getReportClassCode());
        reportTracking.setReportCode(awardTerm.getReportCode());
        
        ReportStatus pending = getPendingReportStatus();
        reportTracking.setReportStatus(pending);
        reportTracking.setStatusCode(pending.getReportStatusCode());
        
        reportTracking.setSponsor(award.getSponsor());
        reportTracking.setSponsorAwardNumber(award.getSponsorAwardNumber());
        reportTracking.setSponsorCode(award.getSponsorCode());
        reportTracking.setTitle(awardTerm.getReport().getDescription());
        return reportTracking;
    }
    
    private ReportStatus getPendingReportStatus() {
        Map params = new HashMap();
        params.put("DESCRIPTION", PENDING_STATUS_DESCRIPTION);
        ReportStatus rs = (ReportStatus) this.getBusinessObjectService().findByPrimaryKey(ReportStatus.class, params);
        return rs;
    }
    
    private List<ReportTracking> purgePendingReports(List<ReportTracking> reportListToClean, List<ReportTracking> deleteReports) {
        List<ReportTracking> reportTrackingReturn = new ArrayList<ReportTracking>();
        for (ReportTracking rt : reportListToClean) {
            if (StringUtils.equals(getPendingReportStatus().getReportStatusCode(), rt.getStatusCode())) {
                deleteReports.add(rt);
            } else {
                reportTrackingReturn.add(rt);
            }
        }
        return reportTrackingReturn;
    }
    
    private boolean isAwardTermDateAlreadySet(List<ReportTracking> reportTrackings, java.util.Date date) {
        for (ReportTracking rt : reportTrackings) {
            if (rt.getDueDate().getTime() == date.getTime()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public List<ReportTracking> getReportTacking(AwardReportTerm awardTerm) {
        Map params = new HashMap();
        params.put("AWARD_REPORT_TERM_ID", awardTerm.getAwardReportTermId());
        Collection<ReportTracking> reportTrackingCollection = this.getBusinessObjectService().findMatching(ReportTracking.class, params);
        List<ReportTracking> reportTrackings = new ArrayList<ReportTracking>();
        reportTrackings.addAll(reportTrackingCollection);
        Collections.sort(reportTrackings);
        return reportTrackings;
    }

    public AwardScheduleGenerationService getAwardScheduleGenerationService() {
        return awardScheduleGenerationService;
    }

    public void setAwardScheduleGenerationService(AwardScheduleGenerationService awardScheduleGenerationService) {
        this.awardScheduleGenerationService = awardScheduleGenerationService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
