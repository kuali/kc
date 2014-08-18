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

import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.core.api.util.tree.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MedusaNode extends Node<Object, String> implements Serializable {
    private static final long serialVersionUID = 6899695450845010658L;
    
    private Object extraInfo;
    
    public Object getBo() {
        return getData();
    }
    public void setBo(Object bo) {
    	setData(bo);
    }
    public String getType() {
        return getNodeType();
    }
    public void setType(String type) {
    	setNodeType(type);
    }
    public List<? super MedusaNode> getChildNodes() {
    	return getChildren();
    }
    public void setChildNodes(List<? extends Node<Object, String>> childNodes) {
    	setChildren((List<Node<Object, String>>) childNodes);
    }
    public Object getExtraInfo() {
        return extraInfo;
    }
    public void setExtraInfo(Object extraInfo) {
        this.extraInfo = extraInfo;
    }   
    public void addChildNode(MedusaNode node) {
    	getChildren().add(node);
    }
    public String getNodeLabel() {
    	if (getData() instanceof DevelopmentProposal) {
    		return "Development Proposal " + ((DevelopmentProposal) getData()).getProposalNumber();
    	} else if (getData() instanceof InstitutionalProposal) {
    		return "Institutional Proposal " + ((InstitutionalProposal) getData()).getProposalNumber();
    	} else {
    		return "---";
    	}
    }
    
    
}     