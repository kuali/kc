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
package org.kuali.kra.coi.lookup;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiReviewStatus;
import org.kuali.kra.coi.lookup.dao.CoiDisclosureDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.web.struts.action.KualiAction;


public class CoiCustomAdminSearchAction extends KualiAction {

    private CoiDisclosureDao coiDisclosureDao;
    private ParameterService parameterService;
    
    public ActionForward openCustomSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CoiCustomAdminSearchForm searchForm = (CoiCustomAdminSearchForm) form;
        CustomAdminSearchHelper helper = searchForm.getCustomAdminSearchHelper();
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
        List<String> inProgressStatusCodes = (List<String>) getParameterService().getParameterValuesAsString(CoiDisclosureDocument.class, Constants.COI_WORK_IN_PROGRESS_REVIEW_STATUS_PARM);
        return getCoiDisclosureDao().getReviewsForReviewStatuses(inProgressStatusCodes);
    }


    public CoiDisclosureDao getCoiDisclosureDao() {
        if (coiDisclosureDao == null) {
            coiDisclosureDao = KraServiceLocator.getService(CoiDisclosureDao.class);
        }
        return coiDisclosureDao;
    }

    public void setCoiDisclosureDao(CoiDisclosureDao coiDisclosureDao) {
        this.coiDisclosureDao = coiDisclosureDao;
    }

    public ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KraServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
