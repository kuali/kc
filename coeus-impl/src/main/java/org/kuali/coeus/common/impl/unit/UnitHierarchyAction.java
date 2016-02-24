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
