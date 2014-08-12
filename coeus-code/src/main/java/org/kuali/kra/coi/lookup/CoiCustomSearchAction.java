/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.coi.lookup;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDispositionStatus;
import org.kuali.kra.coi.CoiReviewStatus;
import org.kuali.kra.coi.actions.CoiDisclosureActionService;
import org.kuali.kra.coi.lookup.dao.CoiDisclosureDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@SuppressWarnings("deprecation")
public class CoiCustomSearchAction extends KualiAction {

    private CoiDisclosureDao coiDisclosureDao;
    private ParameterService parameterService;
    private CoiDisclosureActionService coiDisclosureActionService;
    private DocumentService documentService;
    private PermissionService permissionService;
    private KcAuthorizationService kraAuthorizationService;
    
    public ActionForward openCustomSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(isAuthorizedForCoiLookups()) {
            CoiCustomSearchForm searchForm = (CoiCustomSearchForm) form;
            searchForm.setCanQuickApprove(getPermissionService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                    "KC-COIDISCLOSURE", PermissionConstants.APPROVE_COI_DISCLOSURE));
            CustomAdminSearchHelper helper = searchForm.getCustomAdminSearchHelper();
            helper.setAllOpenReviews(getOpenReviews());
            helper.setPendingReviews(getPendingReviews());
            helper.setInProgressReviews(getInProgressReviews());
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        else {
            return mapping.findForward(KRADConstants.MAPPING_PORTAL);
        }
    }
    
    public ActionForward quickApproveDisclosure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CoiCustomSearchForm searchForm = (CoiCustomSearchForm) form;
        CustomAdminSearchHelper helper = searchForm.getCustomAdminSearchHelper();
        String coiDisclosureDocumentNumber = getCoiDisclosureDocumentNumber(request);
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument) getDocumentService().getByDocumentHeaderId(coiDisclosureDocumentNumber);
        if (!helper.hasFinEnt(coiDisclosureDocument.getCoiDisclosure())
                && getKraAuthorizationService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), coiDisclosureDocument.getCoiDisclosure(), PermissionConstants.APPROVE_COI_DISCLOSURE)) {
            getCoiDisclosureActionService().approveDisclosure(coiDisclosureDocument.getCoiDisclosure(), CoiDispositionStatus.NO_CONFLICT_EXISTS);
            GlobalVariables.getMessageMap().putInfo(Constants.NO_FIELD, KeyConstants.MESSAGE_COI_DISCLOSURE_QUICK_APPROVED);
        } else {
            GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_COI_NO_PERMISSION_APPROVE);
        }
        helper.setAllOpenReviews(getOpenReviews());
        helper.setPendingReviews(getPendingReviews());
        helper.setInProgressReviews(getInProgressReviews());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    protected List<CoiDisclosure> getOpenReviews() {
        return getCoiDisclosureDao().getReviewsForReviewStatuses(Arrays.asList(new String[]{CoiReviewStatus.SUBMITTED_FOR_REVIEW, CoiReviewStatus.ASSIGNED_TO_REVIEWER}));
    }
    
    protected List<CoiDisclosure> getPendingReviews() {
        return getCoiDisclosureDao().getReviewsForReviewStatuses(Arrays.asList(new String[]{CoiReviewStatus.SUBMITTED_FOR_REVIEW}));
    }

    protected List<CoiDisclosure> getInProgressReviews() {
        Collection<String> inProgressStatusCodes = getParameterService().getParameterValuesAsString(CoiDisclosureDocument.class, Constants.COI_WORK_IN_PROGRESS_REVIEW_STATUS_PARM);
        return getCoiDisclosureDao().getReviewsForReviewStatuses(inProgressStatusCodes);
    }
    
    /**
     * Parses the method to call attribute to pick off the line number which should have an action performed on it.
     *
     * @param request
     * @return
     */
    protected String getCoiDisclosureDocumentNumber(HttpServletRequest request) {
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String documentNumber = StringUtils.substringBetween(parameterName, ".disclosureDocNbr", ".");
            if (StringUtils.isEmpty(documentNumber)) {
                return null;
            } else {
                return documentNumber;
            }
        }
        return null;
    }    

    protected CoiDisclosureDao getCoiDisclosureDao() {
        if (coiDisclosureDao == null) {
            coiDisclosureDao = KcServiceLocator.getService(CoiDisclosureDao.class);
        }
        return coiDisclosureDao;
    }

    public void setCoiDisclosureDao(CoiDisclosureDao coiDisclosureDao) {
        this.coiDisclosureDao = coiDisclosureDao;
    }

    protected ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    protected CoiDisclosureActionService getCoiDisclosureActionService() {
        if (coiDisclosureActionService == null) {
            coiDisclosureActionService = KcServiceLocator.getService(CoiDisclosureActionService.class);
        }
        return coiDisclosureActionService;
    }

    public void setCoiDisclosureActionService(CoiDisclosureActionService coiDisclosureActionService) {
        this.coiDisclosureActionService = coiDisclosureActionService;
    }

    protected DocumentService getDocumentService() {
        if (documentService == null) {
            documentService = KcServiceLocator.getService(DocumentService.class);
        }
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    protected KcAuthorizationService getKraAuthorizationService() {
        if (kraAuthorizationService == null) {
            kraAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        }
        return kraAuthorizationService;
    }

    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    protected PermissionService getPermissionService() {
        if (permissionService == null) {
            permissionService = KcServiceLocator.getService(PermissionService.class);
        }
        return permissionService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
    
    protected boolean isAuthorizedForCoiLookups() {
        ApplicationTask task = new ApplicationTask(TaskName.LOOKUP_COI_DISCLOSURES);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
}
