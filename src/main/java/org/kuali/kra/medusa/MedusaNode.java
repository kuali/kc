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
import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.krad.bo.BusinessObject;


public class MedusaNode implements Serializable {
    private static final long serialVersionUID = 6899695450845010658L;
    
    private BusinessObject bo;
    private String type;
    private Object extraInfo;
    private List<MedusaNode> childNodes = new ArrayList<MedusaNode>();
    
    public BusinessObject getBo() {
        return bo;
    }
    public void setBo(BusinessObject bo) {
        this.bo = bo;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<MedusaNode> getChildNodes() {
        return childNodes;
    }
    public void setChildNodes(List<MedusaNode> childNodes) {
        this.childNodes = childNodes;
    }
    public Object getExtraInfo() {
        return extraInfo;
    }
    public void setExtraInfo(Object extraInfo) {
        this.extraInfo = extraInfo;
    }   
}     
