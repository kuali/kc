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
package org.kuali.kra.award.web.struts.action;


import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.lookup.Lookupable;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.struts.action.KualiLookupAction;

public class ReportTrackingLookupAction extends KualiLookupAction {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ReportTrackingLookupForm.class);
    
    private ReportTrackingDao reportTrackingDao;
    private DateTimeService dateTimeService;
    
    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        
        Lookupable kualiLookupable = lookupForm.getLookupable();
        if (kualiLookupable == null) {
            LOG.error("Lookupable is null.");
            throw new RuntimeException("Lookupable is null.");
        }

        // validate search parameters
        kualiLookupable.validateSearchParameters(lookupForm.getFields());
        
        List<ReportTracking> groupedResults = getReportTrackingDao().getResultsGroupedBy(lookupForm.getFields(), lookupForm.getGroupedByFields(), lookupForm.getGroupedByDisplayFields());
        lookupForm.setGroupedByResults(groupedResults);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward getDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        Map<String, String> allFields = new HashMap<String, String>(lookupForm.getFields());
        populateAggregateValues(lookupForm.getGroupedByResults().get(lookupForm.getGroupByResultIndex()), allFields, lookupForm.getGroupedByFields());
        List<ReportTracking> detailResults = getReportTrackingDao().getDetailResults(allFields, lookupForm.getDetailFields());
        lookupForm.setDetailResults(detailResults);
        return mapping.findForward("ajaxDetails");
    }
    
    public ActionForward updateView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        lookupForm.setCurrentView();
        return this.search(mapping, lookupForm, request, response);
    }
    
    public ActionForward resetCustomView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        lookupForm.resetCustomFields();
        lookupForm.setCurrentView();
        return this.search(mapping, lookupForm, request, response);        
    }
    
    /**
     * Add the aggregate(grouped by) values passed in via javascript and ajax,
     * to the fields map for the DAO search. 
     * @param aggregateValues
     * @param fields
     * @param columns
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    protected void populateAggregateValues(ReportTracking aggregateValues, Map<String, String> fields, List<String> columns) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (String column : columns) {
            if (Date.class.isAssignableFrom(ObjectUtils.easyGetPropertyType(aggregateValues, column))
                    && ObjectUtils.getPropertyValue(aggregateValues, column) != null) {
                fields.put(column, getDateTimeService().toDateString((java.util.Date) ObjectUtils.getPropertyValue(aggregateValues, column)));
            } else {
                fields.put(column, BeanUtils.getProperty(aggregateValues, column));
            }
        }
    }
    
    protected ReportTrackingDao getReportTrackingDao() {
        if (reportTrackingDao == null) {
            reportTrackingDao = KraServiceLocator.getService(ReportTrackingDao.class);
        }
        return reportTrackingDao;
    }

    public void setReportTrackingDao(ReportTrackingDao reportTrackingDao) {
        this.reportTrackingDao = reportTrackingDao;
    }

    protected DateTimeService getDateTimeService() {
        if (dateTimeService == null) {
            dateTimeService = KraServiceLocator.getService(DateTimeService.class);
        }
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

}
