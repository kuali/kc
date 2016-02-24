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
package org.kuali.kra.coi.disclosure;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CoiDisclEventProject implements Serializable {

    // TODO : this is a class to help UI rendering with the mock design hierarchy structure of : discl -> projects -> fe details
    // this is not final.  

    private static final long serialVersionUID = 3373941718110328593L;
    private String eventType; 
    private KcPersistableBusinessObjectBase eventProjectBo;
    private List<CoiDiscDetail> coiDiscDetails;
    private boolean disclosureFlag;
   
    
    public CoiDisclEventProject() {
        
    }
    
    public CoiDisclEventProject(String eventType, KcPersistableBusinessObjectBase eventProjectBo, List<CoiDiscDetail> coiDiscDetails) {
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
    public KcPersistableBusinessObjectBase getEventProjectBo() {
        return eventProjectBo;
    }
    public void setEventProjectBo(KcPersistableBusinessObjectBase eventProjectBo) {
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
        return StringUtils.equals(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, this.eventType);
    }
    
    public boolean isInstitutionalProposalEvent() {
        return StringUtils.equals(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, this.eventType);
    }
    
    public boolean isAwardEvent() {
        return StringUtils.equals(CoiDisclosureEventType.AWARD, this.eventType);
    }
    
    public boolean isAnnual() {
        return StringUtils.equals(CoiDisclosureEventType.ANNUAL, this.eventType);
    }
    
    public boolean isProtocolEvent() {
        return StringUtils.equals(CoiDisclosureEventType.IRB_PROTOCOL, this.eventType);
    }

    public boolean isEventExcludFE() {
        boolean eventExcludeFE = false;
        if (isAwardEvent()) {
            eventExcludeFE = isEventExcludFE(CoiDisclosureEventType.AWARD);
        } else if (isProtocolEvent()) {
            eventExcludeFE = isEventExcludFE(CoiDisclosureEventType.IRB_PROTOCOL);
        } else if (isProposalEvent()) {
            eventExcludeFE = isEventExcludFE(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);
        } else if (isInstitutionalProposalEvent()) {
            eventExcludeFE = isEventExcludFE(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL);
        } return eventExcludeFE;

    }

    /*
     * excluded FE from event.  this is specifically for annual project check
     */
    private boolean isEventExcludFE(String eventTypeCode) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("eventTypeCode", eventTypeCode);
        CoiDisclosureEventType CoiDisclosureEventType =  KcServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(CoiDisclosureEventType.class, fieldValues);
        return CoiDisclosureEventType.isExcludeFinancialEntities();
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
                if (coiDiscDetail.getEntityDispositionCode() != null && coiDiscDetail.getEntityDispositionCode() > 0) {
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
                if (coiDiscDetail.getEntityDispositionCode() == null || coiDiscDetail.getEntityDispositionCode() == 0) {
                    isComplete = false;
                    break;
                }
                
            }
        }
        return isComplete;
    }

}
