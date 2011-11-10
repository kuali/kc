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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;


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

    public boolean isProposalEvent() {
        return StringUtils.equals(CoiDisclProject.PROPOSAL_EVENT, this.eventType);
    }
    
    public boolean isInstitutionalProposalEvent() {
        return StringUtils.equals(CoiDisclProject.INSTITUTIONAL_PROPOSAL_EVENT, this.eventType);
    }
    
    public boolean isAwardEvent() {
        return StringUtils.equals(CoiDisclProject.AWARD_EVENT, this.eventType);
    }
    
    public boolean isProtocolEvent() {
        return StringUtils.equals(CoiDisclProject.PROTOCOL_EVENT, this.eventType);
    }

    public String getProjectId() {
        // TODO : may be should add an 'disclosurable' interface, and add projectid method
        // so these bo can implement this method
        String projectId = "";
        if (isAwardEvent()) {
            projectId = ((Award)this.eventProjectBo).getAwardNumber();
        } else if (isProtocolEvent()) {
            projectId = ((Protocol)this.eventProjectBo).getProtocolNumber();
        } else {
            if (eventProjectBo instanceof DevelopmentProposal) {
                projectId = ((DevelopmentProposal)this.eventProjectBo).getProposalNumber();
            } else {
                projectId = ((InstitutionalProposal)this.eventProjectBo).getProposalNumber();
            }
        }
        return projectId;
    }
    
    public String getCompleteMessage() {
        int completeCount = 0;
        if (CollectionUtils.isNotEmpty(this.getCoiDiscDetails())) {
            for (CoiDiscDetail coiDiscDetail : this.getCoiDiscDetails()) {
                if (StringUtils.isNotBlank(coiDiscDetail.getEntityStatusCode())) {
                    completeCount ++;
                }
                
            }
        }
        return completeCount + "/" +this.getCoiDiscDetails().size() + " Reviews Complete";
    }

    public boolean isComplete() {
        // TODO : this is kind of duplicate with getCompleteMessage.
        // may want to merge for better solution
        boolean isComplete = true;
        if (CollectionUtils.isNotEmpty(this.getCoiDiscDetails())) {
            for (CoiDiscDetail coiDiscDetail : this.getCoiDiscDetails()) {
                if (StringUtils.isBlank(coiDiscDetail.getEntityStatusCode())) {
                    isComplete = false;
                    break;
                }
                
            }
        }
        return isComplete;
    }

}
