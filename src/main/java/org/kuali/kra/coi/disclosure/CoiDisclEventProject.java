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
package org.kuali.kra.coi.disclosure;

import java.io.Serializable;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDiscDetail;


public class CoiDisclEventProject implements Serializable {

    // TODO : this is a class to help UI rendering with the mock design hierarchy structure of : discl -> projects -> fe details
    // this is not final.  
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3373941718110328593L;
    private String eventType; 
    private KraPersistableBusinessObjectBase eventProjectBo; 
    private List<CoiDiscDetail> coiDiscDetails;
    private boolean disclosureFlag;
   
    
    public CoiDisclEventProject() {
        
    }
    
    public CoiDisclEventProject(String eventType, KraPersistableBusinessObjectBase eventProjectBo, List<CoiDiscDetail> coiDiscDetails) {
        this.eventType = eventType;
        this.eventProjectBo = eventProjectBo;
        this.coiDiscDetails = coiDiscDetails;
    }
    
    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public KraPersistableBusinessObjectBase getEventProjectBo() {
        return eventProjectBo;
    }
    public void setEventProjectBo(KraPersistableBusinessObjectBase eventProjectBo) {
        this.eventProjectBo = eventProjectBo;
    }
    public List<CoiDiscDetail> getCoiDiscDetails() {
        return coiDiscDetails;
    }
    public void setCoiDiscDetails(List<CoiDiscDetail> coiDiscDetails) {
        this.coiDiscDetails = coiDiscDetails;
    }

    public boolean isDisclosureFlag() {
        return disclosureFlag;
    }

    public void setDisclosureFlag(boolean disclosureFlag) {
        this.disclosureFlag = disclosureFlag;
    } 


}
