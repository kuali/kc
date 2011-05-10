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
package org.kuali.kra.jqueryajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase;

/**
 * 
 * This class is to handle jquery ajax calls.
 */
public class JqueryAjaxAction extends KualiDocumentActionBase {


    /**
     * 
     * This method uses the case-insensitive lookup method of the unit service to get the unit name using the unit number
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getUnitName (ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    throws Exception {
        
        JqueryAjaxForm ajaxForm = (JqueryAjaxForm)form;
        Unit unit = getUnitService().getUnitCaseInsensitive(ajaxForm.getCode());
        String unitName = null;
        if(null != unit){
            unitName = unit.getUnitName();
            if(null != unit.getUnitNumber()){
                ajaxForm.setCode(unit.getUnitNumber());
            }
        }
        if (unitName == null && StringUtils.isNotBlank(ajaxForm.getCode())) {
            unitName = "<span style='color: red;'>not found</span>";
        }
        ajaxForm.setReturnVal(unitName);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    private UnitService getUnitService() {
        return KraServiceLocator.getService(UnitService.class);
    }
}
