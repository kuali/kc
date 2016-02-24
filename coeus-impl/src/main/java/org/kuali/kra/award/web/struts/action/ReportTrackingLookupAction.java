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
package org.kuali.kra.award.web.struts.action;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.sys.framework.controller.DocHandlerService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.AwardReportTracking;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingDao;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingPrintingService;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingType;
import org.kuali.kra.award.infrastructure.AwardPermissionConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.lookup.Lookupable;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.action.KualiLookupAction;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportTrackingLookupAction extends KualiLookupAction {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ReportTrackingLookupForm.class);
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
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
            LookupUtils.preProcessRangeFields(lookupForm.getFields());
            List<ReportTracking> groupedResults = 
                getReportTrackingDao().getResultsGroupedBy(lookupForm.getFields(), lookupForm.getGroupedByFields(), lookupForm.getGroupedByDisplayFields());
            lookupForm.setGroupedByResults(groupedResults);
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }
    
    public ActionForward getDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        LookupUtils.preProcessRangeFields(lookupForm.getFields());
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
    
    public ActionForward moveGroupByColumns(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        String fieldName = lookupForm.getMoveField();
        Integer newIndex = lookupForm.getNewColumnIndex() - 1;
        Integer oldIndex = lookupForm.getGroupedByDisplayFields().indexOf(fieldName);
        if (newIndex >= 0 && oldIndex >= 0) {
            lookupForm.getGroupedByDisplayFields().remove(fieldName);
            if (newIndex > oldIndex) {
                newIndex--;
            }
            lookupForm.getGroupedByDisplayFields().add(newIndex, fieldName);
            
            String origItem = lookupForm.getGroupedByFields().get(oldIndex);
            lookupForm.getGroupedByFields().remove(origItem);
            lookupForm.getGroupedByFields().add(newIndex, origItem);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward moveDetailColumns(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        String fieldName = lookupForm.getMoveField();
        Integer newIndex = lookupForm.getNewColumnIndex();
        Integer oldIndex = lookupForm.getDetailFields().indexOf(fieldName);
        if (newIndex >= 0 && oldIndex >= 0) {
            lookupForm.getDetailFields().remove(fieldName);
            if (newIndex > oldIndex) {
                newIndex--;
            }
            lookupForm.getDetailFields().add(newIndex, fieldName);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward printAllReportTracking(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        List<Printable> printableArtifactLists = new ArrayList<Printable>();
        Map<String, String> allFields = new HashMap<String,String>(lookupForm.getFields());
		List<ReportTracking> detailResults =
                getReportTrackingDao().getResultsGroupedBy(allFields, lookupForm.getGroupedByFields(), lookupForm.getGroupedByDisplayFields());
        for (ReportTracking detailResult : detailResults) {
            AwardReportTracking  printables = new AwardReportTracking();  
            printables = getReportTrackingPrintingService().getReportPrintable(ReportTrackingType.AWARD_REPORT_TRACKING,detailResult,printables);
            printableArtifactLists.add((AwardReportTracking) printables.clone());
        }
        AttachmentDataSource attachmentDataSource =
            getReportTrackingPrintingService()
            .printAwardReportTracking(printableArtifactLists);
        streamToResponse(attachmentDataSource, response);
        actionForward = RESPONSE_ALREADY_HANDLED;
        return actionForward;
    }
    public ActionForward printReportTracking(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        ReportTrackingLookupForm lookupForm = (ReportTrackingLookupForm) form;
        List<ReportTracking> detailResults = lookupForm.getGroupedByResults();
        String line = request.getParameter("line");
        ReportTracking reportTracking = detailResults.get(Integer.parseInt(line));
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        AwardReportTracking printable = new AwardReportTracking();  
        printable = getReportTrackingPrintingService().getReportPrintable(
                ReportTrackingType.AWARD_REPORT_TRACKING,reportTracking,printable);
        printableArtifactList.add(printable);
        AttachmentDataSource attachmentDataSource =
            getReportTrackingPrintingService()
            .printAwardReportTracking(printableArtifactList);
        streamToResponse(attachmentDataSource, response);
        actionForward = RESPONSE_ALREADY_HANDLED;
        return actionForward;
    }
    public ActionForward openAwardReports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String awardNumber = getSelectedAwardNumber(request);
        List<VersionHistory> versions = KcServiceLocator.getService(VersionHistoryService.class).loadVersionHistory(Award.class, awardNumber);
        Award newest = null;
        for (VersionHistory version : versions) {
            if (newest == null || version.getSequenceOwnerSequenceNumber() > newest.getSequenceNumber() &&
                    version.getStatus() != VersionStatus.CANCELED) {
                newest = ((Award) version.getSequenceOwner());
            }
        }
        String docNumber = newest.getAwardDocument().getDocumentNumber();
        final AwardDocument awardDocument = (AwardDocument) getDocumentService().getByDocumentHeaderId(docNumber);
        String forwardUrl = buildForwardUrl(awardDocument.getDocumentHeader().getWorkflowDocument().getDocumentId());
        return new ActionForward(forwardUrl, true);
    }    
    
    protected String getSelectedAwardNumber(HttpServletRequest request) {
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
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
    protected String buildForwardUrl(String routeHeaderId) {
        DocHandlerService researchDocumentService = KcServiceLocator.getService(DocHandlerService.class);
        String forward = researchDocumentService.getDocHandlerUrl(routeHeaderId);
        //forward = forward.replaceFirst(DEFAULT_TAB, ALTERNATE_OPEN_TAB);
        if (forward.indexOf("?") == -1) {
            forward += "?";
        }
        else {
            forward += "&";
        }
        forward += KewApiConstants.DOCUMENT_ID_PARAMETER + "=" + routeHeaderId;
        forward += "&" + KewApiConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
            forward += "&" + KewApiConstants.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getPrincipalName();
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
            reportTrackingDao = KcServiceLocator.getService(ReportTrackingDao.class);
        }
        return reportTrackingDao;
    }

    public void setReportTrackingDao(ReportTrackingDao reportTrackingDao) {
        this.reportTrackingDao = reportTrackingDao;
    }

    protected DateTimeService getDateTimeService() {
        if (dateTimeService == null) {
            dateTimeService = KcServiceLocator.getService(DateTimeService.class);
        }
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public DocumentService getDocumentService() {
        if (documentService == null) {
            documentService = KcServiceLocator.getService(DocumentService.class);
        }
        return documentService;
    }

    public VersionHistoryService getVersionHistoryService() {
        if (versionHistoryService == null) {
            versionHistoryService = KcServiceLocator.getService(VersionHistoryService.class);
        }
        return versionHistoryService;
    }
    
    private ReportTrackingPrintingService getReportTrackingPrintingService() {
        return KcServiceLocator.getService(ReportTrackingPrintingService.class);
    }
    /**
     * method to stream the byte array to response object
     *
     * @param fileContents
     * @param fileName
     * @param fileContentType
     * @param response
     * @throws Exception
     */
    protected void streamToResponse(AttachmentDataSource attachmentDataSource,
            HttpServletResponse response) throws Exception {
        byte[] xbts = attachmentDataSource.getData();
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);

            WebUtils
                    .saveMimeOutputStreamAsFile(response, attachmentDataSource
                            .getType(), baos, attachmentDataSource
                            .getName());

        } finally {
            try {
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (IOException ioEx) {
                LOG.error("Error while downloading attachment");
                throw new RuntimeException("IOException occurred while downloading attachment", ioEx);
            }
        }
    }
    public UnitAuthorizationService getUnitAuthorizationService() {
        if (unitAuthorizationService == null) {
            unitAuthorizationService = KcServiceLocator.getService(UnitAuthorizationService.class);
        }
        return unitAuthorizationService;
    }
}
