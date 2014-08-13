/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.framework.medusa;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class MedusaBean implements Serializable{


    private static final long serialVersionUID = -8727199559530816767L;
    private String medusaViewRadio;
    private String moduleName;
    private Long moduleIdentifier;
    private List<MedusaNode> parentNodes;
    private MedusaNode currentNode;
    
    public MedusaBean() {
        setMedusaViewRadio("0");
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
        if(StringUtils.equalsIgnoreCase("0", getMedusaViewRadio())){
            setParentNodes(getMedusaService().getMedusaByProposal(getModuleName(), getModuleIdentifier()));    
        }else if(StringUtils.equalsIgnoreCase("1", getMedusaViewRadio())){
            setParentNodes(getMedusaService().getMedusaByAward(getModuleName(), getModuleIdentifier()));    
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
