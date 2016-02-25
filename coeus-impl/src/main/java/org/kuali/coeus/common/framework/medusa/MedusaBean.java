/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.framework.medusa;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;

public class MedusaBean implements Serializable{


    private static final long serialVersionUID = -8727199559530816767L;
    private String medusaViewRadio;
    private String moduleName;
    private String complianceModulesCheckbox;
    private Long moduleIdentifier;
    private List<MedusaNode> parentNodes;
    private MedusaNode currentNode;

    ParameterService parameterService = CoreFrameworkServiceLocator.getParameterService();
    String complianceModuleCheckboxDefault = parameterService.getParameterValueAsString("KC-GEN","Document","KC_MEDUSA_COMPLIANCE_MODULE_CHECKBOX_DEFAULT");

    public MedusaBean() {
        setMedusaViewRadio("0");
        if (StringUtils.equalsIgnoreCase(complianceModuleCheckboxDefault, "N")) {
            setComplianceModulesCheckbox("");
        }
        else {
            setComplianceModulesCheckbox("includeComplianceModules");
        }
    }

    public void init(String moduleName, Long moduleIdentifier) {
        setMedusaViewRadio("0");
        setModuleName(moduleName);
        setModuleIdentifier(moduleIdentifier);
        generateParentNodes();
    }

    /**
     * Gets the medudaViewRadio attribute. 
     * @return Returns the medudaViewRadio.
     */
    public String getMedusaViewRadio() {
        return medusaViewRadio;
    }

    /**
     * Sets the medudaViewRadio attribute value.
     * @param medusaViewRadio The medudaViewRadio to set.
     */
    public void setMedusaViewRadio(String medusaViewRadio) {
        if (!StringUtils.equals(this.medusaViewRadio, medusaViewRadio)) {
            this.medusaViewRadio = medusaViewRadio;
            generateParentNodes();
        }
    }

    public String getComplianceModulesCheckbox() {
        return this.complianceModulesCheckbox;
    }

    public void setComplianceModulesCheckbox(String complianceModulesCheckbox) {
        if (!StringUtils.equals(this.complianceModulesCheckbox, complianceModulesCheckbox)) {
            this.complianceModulesCheckbox = complianceModulesCheckbox;
            generateParentNodes();
        }
    }

    /**
     * Gets the moduleName attribute. 
     * @return Returns the moduleName.
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Sets the moduleName attribute value.
     * @param moduleName The moduleName to set.
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Gets the moduleIdentifier attribute. 
     * @return Returns the moduleIdentifier.
     */
    public Long getModuleIdentifier() {
        return moduleIdentifier;
    }

    /**
     * Sets the moduleIdentifier attribute value.
     * @param moduleIdentifier The moduleIdentifier to set.
     */
    public void setModuleIdentifier(Long moduleIdentifier) {
        this.moduleIdentifier = moduleIdentifier;
    }


    private MedusaService getMedusaService() {
        return KcServiceLocator.getService(MedusaService.class);
    }

    public void generateParentNodes() {
        boolean includeComplianceModules = StringUtils.equals("includeComplianceModules", getComplianceModulesCheckbox());
        if(StringUtils.equalsIgnoreCase("0", getMedusaViewRadio())){
            setParentNodes(getMedusaService().getMedusaByProposal(getModuleName(), getModuleIdentifier(), includeComplianceModules));
        }else if(StringUtils.equalsIgnoreCase("1", getMedusaViewRadio())){
            setParentNodes(getMedusaService().getMedusaByAward(getModuleName(), getModuleIdentifier(), includeComplianceModules));
        }
        sortNodes(parentNodes);
    }


    public List<MedusaNode> getParentNodes() {
        if (parentNodes == null) {
            generateParentNodes();
        }
        return parentNodes;
    }

    private void sortNodes(List<MedusaNode> nodes){
        Collections.sort(nodes, new MedusaNodeComparator());
        for(MedusaNode mNode: nodes){
            if(!mNode.getChildNodes().isEmpty()){
                sortNodes((List<MedusaNode>) mNode.getChildNodes());
            }
        }
    }

    public void setParentNodes(List<MedusaNode> parentNodes) {
        this.parentNodes = parentNodes;
    }

    public MedusaNode getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(MedusaNode currentNode) {
        this.currentNode = currentNode;
    }

}
