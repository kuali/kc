/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardConstants;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.paymentreports.ReportRegenerationType;
import org.kuali.kra.award.paymentreports.ReportStatus;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTrackingBean;
import org.kuali.kra.award.service.AwardScheduleGenerationService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

/**
 * 
 * This class manages the services needed for Report Tracking.
 */
public class ReportTrackingServiceImpl implements ReportTrackingService {

    private static final String PENDING_STATUS_DESCRIPTION = "Pending";
    public static final String REPORT_CLASS = "reportClass";
    private static final String AWARD_REPORT_TRACKING_ID = "awardReportTrackingId";
    public static final String REPORT_STATUS_CODE = "REPORT_STATUS_CODE";
    public static final String AWARD_NUMBER = "AWARD_NUMBER";
    public static final String OSP_DISTRIBUTION_CODE = "ospDistributionCode";
    public static final String FREQUENCY_BASE_CODE = "frequencyBaseCode";
    public static final String FREQUENCY_CODE = "frequencyCode";
    public static final String REPORT_CODE = "reportCode";
    public static final String REPORT_CLASS_CODE = "reportClassCode";
    public static final String AWARD_NUMBER_CAMEL = "awardNumber";
    public static final String AWARD_REPORT_TERM_ID = "awardReportTermId";
    public static final String DESCRIPTION = "DESCRIPTION";

    private AwardScheduleGenerationService awardScheduleGenerationService;
    private BusinessObjectService businessObjectService;
    private AwardService awardService;
    
    @Override
    public void refreshReportTracking(Award award) throws ParseException {
        List<AwardReportTerm> awardReportTermItems = new ArrayList<>(award.getAwardReportTermItems());      
        for (AwardReportTerm awardTerm : awardReportTermItems) {
            List<AwardReportTerm> awardReportTerms = new ArrayList<>();
            awardReportTerms.add(awardTerm);
            List<java.util.Date> dates = getAwardScheduleGenerationService().generateSchedules(award, awardReportTerms, true);
            if (awardTerm.getReportTrackings() == null) {
                awardTerm.setReportTrackings(getReportTacking(awardTerm));
            } else {
                awardTerm.setReportTrackings(purgePendingReports(awardTerm, awardTerm.getReportTrackings(), new ArrayList<ReportTracking>()));
            }
            
            if (autoRegenerateReports(award) &&  award.getPrincipalInvestigator() != null) {
                runDateCalcuations(dates, award, awardTerm, new ArrayList<ReportTracking>());
            }
            Collections.sort(awardTerm.getReportTrackings());
        }
    }

    @Override
    public void generateReportTrackingAndSave(Award award, boolean forceReportRegeneration) throws ParseException {
        if ((forceReportRegeneration || autoRegenerateReports(award)) && award.getPrincipalInvestigator() != null) {
            
            List<AwardReportTerm> awardReportTermItems = award.getAwardReportTermItems();
            List<ReportTracking> reportsToSave = new ArrayList<>();
            List<ReportTracking> reportsToDelete = new ArrayList<>();
            
            for (AwardReportTerm awardTerm : awardReportTermItems) {
                awardTerm.refreshReferenceObject(REPORT_CLASS);
                if (!awardTerm.getReportClass().getGenerateReportRequirements()) {
                    continue;
                }
                /**
                * creating this secondary AwardReportTerm List as we need to pass a List of AwardReportTerms to the dates generation
                * service below, and we only want to be concerned with the current item, the whole list that we are looping through.
                */
                List<AwardReportTerm> awardReportTerms = new ArrayList<>();
                awardReportTerms.add(awardTerm);
                List<java.util.Date> dates = getAwardScheduleGenerationService().generateSchedules(award, awardReportTerms, true);
                
                if (awardTerm.getReportTrackings() == null) {
                    //pull the report tracking items from the database.
                    awardTerm.setReportTrackings(getReportTacking(awardTerm));
                } else {
                    /**
                     * Purge pending reports from the already existing ReportTracking list, and mark those to be persisted.
                     * Note, passing in reportsToDelete as any pending reports will be put in there so they are removed from the DB,
                     * if needed.
                     */
                    awardTerm.setReportTrackings(purgePendingReports(awardTerm, awardTerm.getReportTrackings(), reportsToDelete));
                    reportsToSave.addAll(awardTerm.getReportTrackings());
                }
                
                runDateCalcuations(dates, award, awardTerm, reportsToSave);

                deleteExtraReports(dates, award, awardTerm, reportsToDelete);

                Collections.sort(awardTerm.getReportTrackings());
            }
            this.getBusinessObjectService().delete(reportsToDelete);
            /**
             * if any reports have been update, update the last updated user and date.
             */
            for (ReportTracking rt : reportsToSave) {
                /**
                 * if the report tracking has been saved, and it's not in pending status, we need to check for updates.
                 */
                if (rt.getAwardReportTrackingId() != null && !StringUtils.equals(rt.getStatusCode(), getPendingReportStatus().getReportStatusCode())) {
                    ReportTracking dbRt = this.getBusinessObjectService().findByPrimaryKey(ReportTracking.class, Collections.singletonMap(AWARD_REPORT_TRACKING_ID, rt.getAwardReportTrackingId()));
                    if (rt.hasBeenUpdated(dbRt)) {
                        rt.setLastUpdateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
                        rt.setLastUpdateUser(GlobalVariables.getUserSession().getPerson().getName());
                    }
                }
            }
            this.getBusinessObjectService().save(reportsToSave);
        }
    }
    
    /**
     * This method deletes any reports that are outside the dates currently in the award. For example, when the projectEndDate for 
     * an award is moved to an earlier date, this method removes the extra report tracking entries for those dates that
     * are no longer part of the project start - end dates.
     * @param dates
     * @param award
     * @param awardTerm
     * @param reportsToDelete
     */
    protected void deleteExtraReports(List<java.util.Date> dates, Award award, AwardReportTerm awardTerm,
            List<ReportTracking> reportsToDelete) {
        HashMap<java.util.Date, String> dateMap= new HashMap<java.util.Date, String>();
        for (java.util.Date date : dates) {
            dateMap.put(date, null);
        }
        
        List<ReportTracking> reportTrackings = awardTerm.getReportTrackings();
        List<ReportTracking> reportTrackingsClean = new ArrayList<ReportTracking>();

        for (ReportTracking reportTracking : reportTrackings) {
            if (reportTracking.getDueDate() != null && !dateMap.containsKey(reportTracking.getDueDate()) ) {
                reportsToDelete.add(reportTracking);
            } else {
                reportTrackingsClean.add(reportTracking);
            }
        }
        awardTerm.setReportTrackings(reportTrackingsClean);
    }

    protected void runDateCalcuations(List<java.util.Date> dates, Award award, AwardReportTerm awardTerm, List<ReportTracking> reportsToSave) {
        if (dates.size() == 0 && awardTerm.getReportTrackings().size() == 0) {
            ReportTracking rt = buildReportTracking(award, awardTerm);
            awardTerm.getReportTrackings().add(rt);
        }
        /**
         * Add a new report tracking item for each date, if that date doesn't already have a report tracking item.
         */
         
        for (java.util.Date date : dates) {
            if (!isAwardTermDateAlreadySet(awardTerm.getReportTrackings(), date)) {
                ReportTracking rt = buildReportTracking(award, awardTerm);
                java.sql.Date sqldate = new java.sql.Date(date.getTime());
                rt.setDueDate(sqldate);
                awardTerm.getReportTrackings().add(rt);
                reportsToSave.add(rt);
            }
        }
    }

    
    /**
     * 
     * This method builds a basic report tracking item pre-populated with Award and AwardTerm data.
     * @param award
     * @param awardTerm
     * @return
     */
    protected ReportTracking buildReportTracking(Award award, AwardReportTerm awardTerm) {
        awardTerm.refresh();
        ReportTracking reportTracking = new ReportTracking();
        reportTracking.setAwardNumber(award.getAwardNumber());
        reportTracking.setAwardReportTermId(awardTerm.getAwardReportTermId());
        reportTracking.setDueDate(awardTerm.getDueDate());
        reportTracking.setFrequency(awardTerm.getFrequency());
        reportTracking.setFrequencyBase(awardTerm.getFrequencyBase());
        reportTracking.setFrequencyBaseCode(awardTerm.getFrequencyBaseCode());
        reportTracking.setFrequencyCode(awardTerm.getFrequencyCode());
        reportTracking.setOspDistributionCode(awardTerm.getOspDistributionCode());
        reportTracking.setLastUpdateDate(new Timestamp(new java.util.Date().getTime()));
        reportTracking.setLastUpdateUser(null);
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
        reportTracking.setTitle(award.getTitle());
        reportTracking.setBaseDate(calculateBaseDate(awardTerm));
        return reportTracking;
    }
    
    /**
     * 
     * This method calculates the the frequency base date based on the award term's selected base code.
     * It is a duplication of the logic on awardReportClasses.tag.
     * If this function requires updating, you'll need to update the javascript in the tag file.
     * @param awardTerm
     * @return
     */
    protected Date calculateBaseDate(AwardReportTerm awardTerm) { 
        Date returnDate = null;
        if (awardTerm != null && awardTerm.getFrequencyBaseCode() != null) {
            if (StringUtils.equalsIgnoreCase(awardTerm.getFrequencyBaseCode(), "1")) {
                returnDate = awardTerm.getAward().getAwardExecutionDate();
            } else if (StringUtils.equalsIgnoreCase(awardTerm.getFrequencyBaseCode(), "2")) {
                returnDate = awardTerm.getAward().getAwardEffectiveDate();
            } else if (StringUtils.equalsIgnoreCase(awardTerm.getFrequencyBaseCode(), "3")) {
                returnDate = awardTerm.getAward().getLastAwardAmountInfo().getObligationExpirationDate();
            } else if (StringUtils.equalsIgnoreCase(awardTerm.getFrequencyBaseCode(), "4")) {
                returnDate = awardTerm.getAward().getLastAwardAmountInfo().getFinalExpirationDate();
            } else if (StringUtils.equalsIgnoreCase(awardTerm.getFrequencyBaseCode(), "5")) {
                returnDate = awardTerm.getAward().getLastAwardAmountInfo().getCurrentFundEffectiveDate();
            }
        }
        return returnDate;
    }

    protected ReportStatus getPendingReportStatus() {
        Map params = new HashMap();
        params.put(DESCRIPTION, PENDING_STATUS_DESCRIPTION);
        ReportStatus rs = this.getBusinessObjectService().findByPrimaryKey(ReportStatus.class, params);
        return rs;
    }
    
    /**
     * 
     * This method purges (puts them in the deleteReportsList) and report tracking items that are pending, and come from award term
     * that has a frequency base that allows for regeneration.  Any ReportTracking items not purges are in the NEW returning list.
     * @param awardTerm
     * @param reportListToClean
     * @param deleteReports
     * @return
     */
    private List<ReportTracking> purgePendingReports(AwardReportTerm awardTerm, List<ReportTracking> reportListToClean, List<ReportTracking> deleteReports) {
        List<ReportTracking> reportTrackingReturn = new ArrayList<ReportTracking>();
        for (ReportTracking rt : reportListToClean) {
            if (StringUtils.equals(getPendingReportStatus().getReportStatusCode(), rt.getStatusCode())
                    && (awardTerm.getFrequencyBase() != null  
                            && StringUtils.equals(awardTerm.getFrequencyBase().getReportRegenerationType().getDescription(),
                                    ReportRegenerationType.REGEN.getDescription()))) {
                deleteReports.add(rt);
            } else {
                reportTrackingReturn.add(rt);
            }
        }
        return reportTrackingReturn;
    }
    
    private boolean isAwardTermDateAlreadySet(List<ReportTracking> reportTrackings, java.util.Date date) {
        boolean retVal = false;
        if (date == null && reportTrackings.size() > 0) {
            retVal =  true;
        }
        if (!retVal) {
            for (ReportTracking rt : reportTrackings) {
                if (rt.getDueDate() != null && rt.getDueDate().getTime() == date.getTime()) {
                    retVal =  true;
                }
            }
        }
        return retVal;
    }
    
    @Override
    public List<ReportTracking> getReportTacking(AwardReportTerm awardTerm) {
        List<ReportTracking> reportTrackings = new ArrayList<ReportTracking>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(AWARD_REPORT_TERM_ID, awardTerm.getAwardReportTermId());
        Collection<ReportTracking> reportTrackingCollection = this.getBusinessObjectService().findMatching(ReportTracking.class, params);
        //if there are none, check to make sure this isn't due to award versioning and the id changing
        if (reportTrackingCollection != null && !reportTrackingCollection.isEmpty()) {
            reportTrackings.addAll(reportTrackingCollection);    
        } else {
            params.clear();
            params.put(AWARD_NUMBER_CAMEL, awardTerm.getAwardNumber());
            params.put(REPORT_CLASS_CODE, awardTerm.getReportClassCode());
            params.put(REPORT_CODE, awardTerm.getReportCode());
            params.put(FREQUENCY_CODE, awardTerm.getFrequencyCode());
            params.put(FREQUENCY_BASE_CODE, awardTerm.getFrequencyBaseCode());
            params.put(OSP_DISTRIBUTION_CODE, awardTerm.getOspDistributionCode());
            reportTrackingCollection = this.getBusinessObjectService().findMatching(ReportTracking.class, params);
            for (ReportTracking reportTrack : reportTrackingCollection) {
                reportTrack.setAwardReportTermId(awardTerm.getAwardReportTermId());
            }
            reportTrackings.addAll(reportTrackingCollection);
        }
        Collections.sort(reportTrackings);
        return reportTrackings;
    }
    
    @Override
    public List<ReportTracking> getReportTacking(Award award) {
        Map params = new HashMap();
        params.put(AWARD_NUMBER, award.getAwardNumber());
        Collection<ReportTracking> reportTrackingCollection = this.getBusinessObjectService().findMatching(ReportTracking.class, params);
        List<ReportTracking> reportTrackings = new ArrayList<ReportTracking>();
        reportTrackings.addAll(reportTrackingCollection);
        Collections.sort(reportTrackings);
        return reportTrackings;
    }
    
    @Override
    public boolean autoRegenerateReports(Award award) {
        boolean retVal = StringUtils.endsWith(award.getAwardNumber(), AwardConstants.ROOT_AWARD_SUFFIX);
        if (!retVal) {
            for (AwardReportTerm term : award.getAwardReportTermItems()) {
                List<ReportTracking> tracking = getReportTacking(term);
                if (!tracking.isEmpty()) {
                    return true;
                }
            }
        }
        return retVal;
    }

    @Override
    public void setReportTrackingListSelected(List<ReportTracking> reportTrackingListing, boolean selectedValue) {
        for (ReportTracking rt : reportTrackingListing) {
            rt.setMultiEditSelected(selectedValue);
        }
    }

    @Override
    public void updateMultipleReportTrackingRecords(List<ReportTracking> reportTrackingListing,
            ReportTrackingBean reportTrackingBean) {
        for (ReportTracking rt : reportTrackingListing) {
            if (rt.getMultiEditSelected()) {
                if (StringUtils.isNotBlank(reportTrackingBean.getComments())) {
                    rt.setComments(reportTrackingBean.getComments());
                }
                if (StringUtils.isNotBlank(reportTrackingBean.getPreparerId())) {
                    rt.setPreparerId(reportTrackingBean.getPreparerId());
                    rt.setPreparerName(reportTrackingBean.getPreparerName());
                }
                if (reportTrackingBean.getActivityDate() != null) {
                    rt.setActivityDate(reportTrackingBean.getActivityDate());
                }
                if (StringUtils.isNotBlank(reportTrackingBean.getStatusCode())) {
                    rt.setStatusCode(reportTrackingBean.getStatusCode());
                    rt.setReportStatus(getReportStatus(reportTrackingBean.getStatusCode()));
                }
            }
        }
    }
    
    protected ReportStatus getReportStatus(String statusCode) {
        Map params = new HashMap();
        params.put(REPORT_STATUS_CODE, statusCode);
        ReportStatus rs = this.getBusinessObjectService().findByPrimaryKey(ReportStatus.class, params);
        return rs;
    }

    @Override
    public boolean shouldAlertReportTrackingDetailChange(Award award) {
        boolean retVal = false;
        
        if (award.getAwardId() != null) {
            Award dbAward = this.getAwardService().getAward(award.getAwardId());
            if (dbAward != null) {
                List<ReportTracking> dbReportTrackings = this.getReportTacking(dbAward);
                if (dbReportTrackings != null && !dbReportTrackings.isEmpty()) {
                    retVal = !dateCompare(award.getAwardExecutionDate(), dbAward.getAwardExecutionDate()) 
                        || !dateCompare(award.getAwardEffectiveDate(), dbAward.getAwardEffectiveDate())
                        || !dateCompare(award.getLastAwardAmountInfo().getObligationExpirationDate(), 
                                dbAward.getLastAwardAmountInfo().getObligationExpirationDate())
                        || !dateCompare(award.getLastAwardAmountInfo().getFinalExpirationDate(), dbAward.getLastAwardAmountInfo().getFinalExpirationDate())
                        || !dateCompare(award.getLastAwardAmountInfo().getCurrentFundEffectiveDate(), 
                                dbAward.getLastAwardAmountInfo().getCurrentFundEffectiveDate());
                }
            }
}
        return retVal;
    }

    private boolean dateCompare(Date formDate, Date dbDate) {
        boolean retVal = false;
        if (formDate == null && dbDate == null) {
            retVal = true;
        } else {
            if (formDate != null && dbDate != null && formDate.equals(dbDate)) {
                retVal = true;
            }
        }
        return retVal;
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

    public AwardService getAwardService() {
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }
}
