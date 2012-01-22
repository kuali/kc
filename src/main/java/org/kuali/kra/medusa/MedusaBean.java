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
package org.kuali.kra.medusa;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.medusa.service.MedusaService;

public class MedusaBean implements Serializable{

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8727199559530816767L;
    private String medusaViewRadio;
    private String moduleName;
    private Long moduleIdentifier;
    private List<MedusaNode> parentNodes;
    private MedusaNode currentNode;
    
    public MedusaBean() {
        setMedusaViewRadio("0");
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
     * @param medudaViewRadio The medudaViewRadio to set.
     */
    public void setMedusaViewRadio(String medusaViewRadio) {
        this.medusaViewRadio = medusaViewRadio;
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
    
    /**
     * This method...
     * @return
     */
    private MedusaService getMedusaService() {
        return KraServiceLocator.getService(MedusaService.class);
    }


    public List<MedusaNode> getParentNodes() {
        if(StringUtils.equalsIgnoreCase("0", getMedusaViewRadio())){
            setParentNodes(getMedusaService().getMedusaByProposal(getModuleName(), getModuleIdentifier()));    
        }else if(StringUtils.equalsIgnoreCase("1", getMedusaViewRadio())){
            setParentNodes(getMedusaService().getMedusaByAward(getModuleName(), getModuleIdentifier()));    
        } 
        sortNodes(parentNodes);
        return parentNodes;
    }
    
    private void sortNodes(List<MedusaNode> nodes){
        Collections.sort(nodes, new MedusaNodeComparator());
        for(MedusaNode mNode: nodes){
            if(!mNode.getChildNodes().isEmpty()){
                sortNodes(mNode.getChildNodes());
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
