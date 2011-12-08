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
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingDao;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.AwardPermissionConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.ResearchDocumentService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.exception.AuthorizationException;
import org.kuali.rice.kns.lookup.Lookupable;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.struts.action.KualiLookupAction;

public class ReportTrackingLookupAction extends KualiLookupAction {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ReportTrackingLookupForm.class);
    
    private ReportTrackingDao reportTrackingDao;
    private DateTimeService dateTimeService;
    private DocumentService documentService;
    private VersionHistoryService versionHistoryService;
    private UnitAuthorizationService unitAuthorizationService;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        isAuthorized();
        return super.execute(mapping, form, request, response);
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        
        Lookupable kualiLookupable = lookupForm.getLookupable();
        if (kualiLookupable == null) {
            LOG.error("Lookupable is null.");
            throw new RuntimeException("Lookupable is null.");
        }

        // validate search parameters
        kualiLookupable.validateSearchParameters(lookupForm.getFields());
        
        if (lookupForm.isViewRawResults()) {
            return super.search(mapping, lookupForm, request, response);
        } else {
            List<ReportTracking> groupedResults = 
                getReportTrackingDao().getResultsGroupedBy(lookupForm.getFields(), lookupForm.getGroupedByFields(), lookupForm.getGroupedByDisplayFields());
            lookupForm.setGroupedByResults(groupedResults);
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
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
        lookupForm.setViewRawResults(false);
        return this.search(mapping, lookupForm, request, response);
    }
    
    public ActionForward resetCustomView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        lookupForm.resetCustomFields();
        lookupForm.setCurrentView();
        return this.search(mapping, lookupForm, request, response);        
    }
    
    public ActionForward viewRawResults(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        lookupForm.setViewRawResults(true);
        lookupForm.setHideReturnLink(true);
        lookupForm.setSuppressActions(true);
        return this.search(mapping, lookupForm, request, response);        
    }
    
    public ActionForward viewAggregateResults(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        lookupForm.setViewRawResults(false);
        return this.search(mapping, lookupForm, request, response);        
    }    
    
    public ActionForward openAwardReports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String awardNumber = getSelectedAwardNumber(request);
        List<VersionHistory> versions = KraServiceLocator.getService(VersionHistoryService.class).loadVersionHistory(Award.class, awardNumber);
        Award newest = null;
        for (VersionHistory version : versions) {
            if (newest == null || version.getSequenceOwnerSequenceNumber() > newest.getSequenceNumber() &&
                    version.getStatus() != VersionStatus.CANCELED) {
                newest = ((Award) version.getSequenceOwner());
            }
        }
        String docNumber = newest.getAwardDocument().getDocumentNumber();
        final AwardDocument awardDocument = (AwardDocument) getDocumentService().getByDocumentHeaderId(docNumber);
        String forwardUrl = buildForwardUrl(awardDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId());
        return new ActionForward(forwardUrl, true);
    }    
    
    protected String getSelectedAwardNumber(HttpServletRequest request) {
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            return StringUtils.substringBetween(parameterName, ".awardNumber", ".");
        } else {
            return null;
        }

    }
    
    /**
     * Takes a routeHeaderId for a particular document and constructs the URL to forward to that document
     * Copied from KraTransactionalDocument as this does not extend from that.
     * 
     * @param routeHeaderId
     * @return String
     */
    protected String buildForwardUrl(Long routeHeaderId) {
        ResearchDocumentService researchDocumentService = KraServiceLocator.getService(ResearchDocumentService.class);
        String forward = researchDocumentService.getDocHandlerUrl(routeHeaderId);
        //forward = forward.replaceFirst(DEFAULT_TAB, ALTERNATE_OPEN_TAB);
        if (forward.indexOf("?") == -1) {
            forward += "?";
        }
        else {
            forward += "&";
        }
        forward += KEWConstants.ROUTEHEADER_ID_PARAMETER + "=" + routeHeaderId;
        forward += "&" + KEWConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
            forward += "&" + KEWConstants.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getPrincipalName();
        }
        return forward;
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
    
    protected void isAuthorized() {
        //check permissions
        boolean userHasPermission = false;
        String permissionName = AwardPermissionConstants.VIEW_AWARD.getAwardPermission();
        userHasPermission = getUnitAuthorizationService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), "KC-AWARD", permissionName);
        if (!userHasPermission) {
            permissionName = AwardPermissionConstants.MODIFY_AWARD.getAwardPermission();
            userHasPermission = getUnitAuthorizationService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), "KC-AWARD", permissionName);
        }
        if (!userHasPermission) {
            permissionName = AwardPermissionConstants.MODIFY_AWARD_REPORT_TRACKING.getAwardPermission();
            userHasPermission = getUnitAuthorizationService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), "KC-AWARD", permissionName);            
        }
        if (!userHasPermission) {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPrincipalName(), "Search", "Report Tracking");
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

    public DocumentService getDocumentService() {
        if (documentService == null) {
            documentService = KraServiceLocator.getService(DocumentService.class);
        }
        return documentService;
    }

    public VersionHistoryService getVersionHistoryService() {
        if (versionHistoryService == null) {
            versionHistoryService = KraServiceLocator.getService(VersionHistoryService.class);
        }
        return versionHistoryService;
    }

    public UnitAuthorizationService getUnitAuthorizationService() {
        if (unitAuthorizationService == null) {
            unitAuthorizationService = KraServiceLocator.getService(UnitAuthorizationService.class);
        }
        return unitAuthorizationService;
    }
}
