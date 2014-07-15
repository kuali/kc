/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.impl.unit;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.web.struts.action.KualiAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * This class for UnitHierarchy
 */
public class UnitHierarchyAction extends KualiAction {
    //nothing needed here yet
    
    public ActionForward expandAllUnitHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UnitHierarchyForm unitForm = (UnitHierarchyForm) form;
        unitForm.setDisplayWholeTree(true);
        unitForm.resetUnits();
        return mapping.findForward(Constants.MAPPING_BASIC);
        
    }
    
    public ActionForward collapseAllUnitHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UnitHierarchyForm unitForm = (UnitHierarchyForm) form;
        unitForm.setDisplayWholeTree(false);
        unitForm.resetUnits();
        return mapping.findForward(Constants.MAPPING_BASIC);
        
    }
}
