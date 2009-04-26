/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kns.bo.Parameter;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.web.struts.form.KualiForm;

public class UnitHierarchyForm  extends KualiForm {

    private String units;
    private String selectedUnitNumber;
    private int initialUnitDepth;
    private static final Log LOG = LogFactory.getLog(UnitHierarchyForm.class);
    /**
     * Constructs a UnitHierarchyForm.
     */
    public UnitHierarchyForm() {
        super();
        units = KraServiceLocator.getService(UnitService.class).getInitialUnitsForUnitHierarchy(getInitialUnitDepth());        

    }

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

    public int getInitialUnitDepth() {
        int depth = 3;
        try {
           Parameter sysParam = KraServiceLocator.getService(KualiConfigurationService.class).getParameter(
                Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, "A", Constants.INITIAL_UNIT_HIERARCHY_LOAD_DEPTH);
            depth=Integer.parseInt(sysParam.getParameterValue());
        } catch (Exception e) {
            LOG.debug("System param for initialUnitLoadDepth is not defined");
        }
 
        return depth;
    }

    public void setInitialUnitDepth(int initialUnitDepth) {
        this.initialUnitDepth = initialUnitDepth;
    }

}
