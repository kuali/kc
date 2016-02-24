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

import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.web.struts.form.KualiForm;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * This class manages the form attributes for UnitHierarchy.
 */
public class UnitHierarchyForm extends KualiForm {

    private static final long serialVersionUID = 998128282202385681L;

    private String units;
    private String selectedUnitNumber;
    private transient ParameterService parameterService;
    
    private boolean displayWholeTree = false;
    

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
        units = KcServiceLocator.getService(UnitService.class).getInitialUnitsForUnitHierarchy(this.getInitialUnitDepth());
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
            return KcServiceLocator.getService(UnitService.class).getMaxUnitTreeDepth();
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
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
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
