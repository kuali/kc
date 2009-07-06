/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.AwardNumberService;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;

/**
 * 
 * This class represents the Struts Action for Award Actions page(AwardActions.jsp)
 */
public class AwardActionsAction extends AwardAction implements AuditModeAction {    
    
    /** {@inheritDoc} */
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (AwardForm) form, true);
    }

    /** {@inheritDoc} */
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (AwardForm) form, false);
    }
    
    public ActionForward maintainAwardHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String awardNumber = getAwardNumber(request);
        AwardForm awardForm = (AwardForm)form;
        String[] splitAwardNumber = awardNumber.split("-");
        
        //String nxtAwardNumber = getAwardNumberService().getNextAwardNumberInHierarchy(getAwardNumber(request));
        String nxtAwardNumber = splitAwardNumber[0] + "-000" + (Integer.parseInt(splitAwardNumber[1]) + 1);
        
        awardForm.setCommand(KEWConstants.INITIATE_COMMAND);
        createDocument(awardForm);
        Award award = awardForm.getAwardDocument().getAward();
        award.setAwardNumber(nxtAwardNumber);

        Map<String, String> primaryKeys = new HashMap<String, String>();
        primaryKeys.put("awardNumber", awardNumber);
        AwardHierarchy awardHierarchy = (AwardHierarchy)(KraServiceLocator.getService(BusinessObjectService.class)).findByPrimaryKey(AwardHierarchy.class, primaryKeys);
        awardForm.setPrevAwardNumber(awardHierarchy.getAwardNumber());
        awardForm.setPrevRootAwardNumber(awardHierarchy.getRootAwardNumber());
        return mapping.findForward(Constants.MAPPING_AWARD_HOME_PAGE);
    }
    
    public ActionForward copyAwardInHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public AwardNumberService getAwardNumberService(){
        return KraServiceLocator.getService(AwardNumberService.class);
    }
    
    protected String getAwardNumber(HttpServletRequest request) {
        String awardNumber = "";
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            awardNumber = StringUtils.substringBetween(parameterName, ".awardNumber", ".");
        }

        return awardNumber;
    }
}
