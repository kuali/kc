/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.web.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kns.bo.Parameter;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.web.struts.form.KualiForm;

public class UnitHierarchyForm extends KualiForm {

    private static final long serialVersionUID = 998128282202385681L;
    private static final String DETAIL_TYPE_CODE_A = "A";
    
    private String units;
    private String selectedUnitNumber;
    
    /**
     * Constructs a UnitHierarchyForm.
     */
    public UnitHierarchyForm() {
        units = KraServiceLocator.getService(UnitService.class).getInitialUnitsForUnitHierarchy(this.getInitialUnitDepth());        
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // FIXME : just a temporary soln.  it always get the methodtocall='refresh' after it started properly the first time.  
        // need to investigate this.
        this.setMethodToCall("");
    }
    
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getSelectedUnitNumber() {
        return selectedUnitNumber;
    }

    public void setSelectedUnitNumber(String selectedUnitNumber) {
        this.selectedUnitNumber = selectedUnitNumber;
    }

    /**
     * Gets the Initial Unit Depth from a system parameter.
     * @return Initial Unit Depth
     */
    public int getInitialUnitDepth() {
        
        Parameter sysParam = KraServiceLocator.getService(KualiConfigurationService.class).getParameter(
                Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, DETAIL_TYPE_CODE_A,
                Constants.INITIAL_UNIT_HIERARCHY_LOAD_DEPTH);
        
        return Integer.parseInt(sysParam.getParameterValue());
    }
}
