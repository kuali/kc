/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.web.struts.form.KualiForm;

/**
 * 
 * This class manages the form attributes for UnitHierarchy.
 */
public class UnitHierarchyForm extends KualiForm {

    private static final long serialVersionUID = 998128282202385681L;
    private static final String DETAIL_TYPE_CODE_A = "A";
    
    private String units;
    private String selectedUnitNumber;
    private transient ParameterService parameterService;
    
    private boolean displayWholeTree = false;
    
    /**
     * Constructs a UnitHierarchyForm.
     */
    public UnitHierarchyForm() {        
        resetUnits();
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // FIXME : just a temporary soln.  it always get the methodtocall='refresh' after it started properly the first time.  
        // need to investigate this.
        this.setMethodToCall("");
    }
    /**
     * 
     * This method reset the Units string based on the initial depth.
     * To display the whole tree, simply set the displayWholeTree attribute to true before calling this method.
     */
    public void resetUnits() {
        units = KraServiceLocator.getService(UnitService.class).getInitialUnitsForUnitHierarchy(this.getInitialUnitDepth());
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
        if (getDisplayWholeTree()){
            return KraServiceLocator.getService(UnitService.class).getMaxUnitTreeDepth();
        } else {
            final String param = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.INITIAL_UNIT_HIERARCHY_LOAD_DEPTH);
            return Integer.parseInt(param);
        }
    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }

    public boolean getDisplayWholeTree() {
        return displayWholeTree;
    }

    public void setDisplayWholeTree(boolean displayWholeTree) {
        this.displayWholeTree = displayWholeTree;
    }    
}
