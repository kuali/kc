/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.coi;

import java.sql.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.protocol.ProtocolType;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class CoiDisclProject extends KraPersistableBusinessObjectBase implements Disclosurable { 
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -870946478393121916L;
//    public static final String PROPOSAL_EVENT = "12";
//    public static final String INSTITUTIONAL_PROPOSAL_EVENT = "14";
//    public static final String AWARD_EVENT = "11";
//    public static final String PROTOCOL_EVENT = "13";
    private Long coiDisclProjectsId; 
    private Long coiDisclosureId; 
    private String coiDisclosureNumber; 
    private Integer sequenceNumber;
    private String coiProjectId;
    private String coiProjectTitle;
    private String moduleItemKey;
    private String shortTextField1; 
    private String longTextField1; 
    private String shortTextField2; 
    // TODO : event type is still not certain because we still have not new schema from coeus
    // this should come from a table eventually I think ?
    private String disclosureEventType; 
    private String longTextField2; 
    private Date dateField1; 
    private Date dateField2; 
    private KualiDecimal numberField1; 
    private KualiDecimal numberField2;
    private String shortTextField3;
    private String longTextField3;
    private String selectBox1;
    private ProposalType proposalType;
    private ProtocolType protocolType;
    private Protocol protocol;
    private DevelopmentProposal proposal;
    private InstitutionalProposal institutionalProposal;
    private Award award;
    private CoiDisclosureEventType coiDisclosureEventType;
         
    private CoiDisclosure coiDisclosure; 
    @SkipVersioning
    private List<CoiDiscDetail> coiDiscDetails; 

    public CoiDisclProject(String coiDisclosureNumber, Integer sequenceNumber) { 
        this.coiDisclosureNumber = coiDisclosureNumber;
        this.sequenceNumber = sequenceNumber;

    } 
    public CoiDisclProject() { 

    } 

    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getCoiDiscDetails());
        return managedLists;
    }
    
    public Long getCoiDisclProjectsId() {
        return coiDisclProjectsId;
    }

    public void setCoiDisclProjectsId(Long coiDisclProjectsId) {
        this.coiDisclProjectsId = coiDisclProjectsId;
    }

    public Long getCoiDisclosureId() {
        return coiDisclosureId;
    }

    public void setCoiDisclosureId(Long coiDisclosureId) {
        this.coiDisclosureId = coiDisclosureId;
    }

    public String getCoiDisclosureNumber() {
        return coiDisclosureNumber;
    }

    public void setCoiDisclosureNumber(String coiDisclosureNumber) {
        this.coiDisclosureNumber = coiDisclosureNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getCoiProjectId() {
        return coiProjectId;
    }
    public void setCoiProjectId(String coiProjectId) {
        this.coiProjectId = coiProjectId;
    }
    public String getCoiProjectTitle() {
        return coiProjectTitle;
    }
    public void setCoiProjectTitle(String coiProjectTitle) {
        this.coiProjectTitle = coiProjectTitle;
    }
    public String getModuleItemKey() {
        return moduleItemKey;
    }
    public void setModuleItemKey(String moduleItemKey) {
        this.moduleItemKey = moduleItemKey;
    }
    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

    public ProposalType getProposalType() {
        return proposalType;
    }

    public void setProposalType(ProposalType proposalType) {
        this.proposalType = proposalType;
    }

    public List<CoiDiscDetail> getCoiDiscDetails() {
        return coiDiscDetails;
    }
    public void setCoiDiscDetails(List<CoiDiscDetail> coiDiscDetails) {
        this.coiDiscDetails = coiDiscDetails;
    }
    public String getDisclosureEventType() {
        return disclosureEventType;
    }
    public void setDisclosureEventType(String disclosureEventType) {
        this.disclosureEventType = disclosureEventType;
    }
    
    public String getShortTextField1() {
        return shortTextField1;
    }
    public void setShortTextField1(String shortTextField1) {
        this.shortTextField1 = shortTextField1;
    }
    public String getLongTextField1() {
        return longTextField1;
    }
    public void setLongTextField1(String longTextField1) {
        this.longTextField1 = longTextField1;
    }
    public String getShortTextField2() {
        return shortTextField2;
    }
    public void setShortTextField2(String shortTextField2) {
        this.shortTextField2 = shortTextField2;
    }
    public String getLongTextField2() {
        return longTextField2;
    }
    public void setLongTextField2(String longTextField2) {
        this.longTextField2 = longTextField2;
    }
    public Date getDateField1() {
        return dateField1;
    }
    public void setDateField1(Date dateField1) {
        this.dateField1 = dateField1;
    }
    public Date getDateField2() {
        return dateField2;
    }
    public void setDateField2(Date dateField2) {
        this.dateField2 = dateField2;
    }
    public KualiDecimal getNumberField1() {
        return numberField1;
    }
    public void setNumberField1(KualiDecimal numberField1) {
        this.numberField1 = numberField1;
    }
    public KualiDecimal getNumberField2() {
        return numberField2;
    }
    public void setNumberField2(KualiDecimal numberField2) {
        this.numberField2 = numberField2;
    }
    public String getShortTextField3() {
        return shortTextField3;
    }
    public void setShortTextField3(String shortTextField3) {
        this.shortTextField3 = shortTextField3;
    }
    public String getLongTextField3() {
        return longTextField3;
    }
    public void setLongTextField3(String longTextField3) {
        this.longTextField3 = longTextField3;
    }
    public String getSelectBox1() {
        return selectBox1;
    }
    public void setSelectBox1(String selectBox1) {
        this.selectBox1 = selectBox1;
    }
    
    public String getProjectIdLabel() {
        String label = "Project Id";
        if (isAwardEvent()) {
            label = "Award Number";
        } else if (isProtocolEvent()) {
            label = "Protocol Number";
        }
        return label;
    }
    
    public String getProjectTitleLabel() {
        String label = "Project Title";
        if (isAwardEvent()) {
            label = "Award Title";
        } else if (isProtocolEvent()) {
            label = "Protocol Name";
        }
        return label;
    }
    
    public String getProjectTypeLabel() {
        String label = "Project Type";
        if (isProtocolEvent()) {
            label = "Protocol type";
        }
        return label;
    }
    
    public String getProjectStartDateLabel() {
        String label = "Project Start Date";
        if (isAwardEvent()) {
            label = "Award Date";
        }
        return label;
    }
    
    public String getEventDescription() {
        String description = "Proposal";
        if (isAwardEvent()) {
            description = "Award";
        } else if (isProtocolEvent()) {
            description = "Protocol";
        }
        return description;

    }
    public ProtocolType getProtocolType() {
        if (StringUtils.isNotBlank(selectBox1) && protocolType == null) {
            this.refreshReferenceObject("protocolType");
        }
        return protocolType;
    }
    
    public void setProtocolType(ProtocolType protocolType) {
        this.protocolType = protocolType;
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
    

    @Override
    public String getProjectName() {
        return getCoiProjectTitle();
    }
    
    @Override
    public String getProjectId() {
        return getCoiProjectId();
    }
    
    public boolean isProposalEvent() {
        return StringUtils.equals(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, this.disclosureEventType);
    }
    
    public boolean isInstitutionalProposalEvent() {
        return StringUtils.equals(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, this.disclosureEventType);
    }
    
    public boolean isAwardEvent() {
        return StringUtils.equals(CoiDisclosureEventType.AWARD, this.disclosureEventType);
    }
    
    public boolean isProtocolEvent() {
        return StringUtils.equals(CoiDisclosureEventType.IRB_PROTOCOL, this.disclosureEventType);
    }
    public Protocol getProtocol() {
        if (protocol == null) {
            this.refreshReferenceObject("protocol");
        }
        return protocol;
    }
    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
    public DevelopmentProposal getProposal() {
        if (proposal == null) {
            this.refreshReferenceObject("proposal");
        }
        return proposal;
    }
    public void setProposal(DevelopmentProposal proposal) {
        this.proposal = proposal;
    }
    public Award getAward() {
        if (award == null) {
            this.refreshReferenceObject("award");
        }
        return award;
    }
    public void setAward(Award award) {
        this.award = award;
    }
    public CoiDisclosureEventType getCoiDisclosureEventType() {
        return coiDisclosureEventType;
    }
    public void setCoiDisclosureEventType(CoiDisclosureEventType coiDisclosureEventType) {
        this.coiDisclosureEventType = coiDisclosureEventType;
    }
    public InstitutionalProposal getInstitutionalProposal() {
        if (institutionalProposal == null) {
            this.refreshReferenceObject("institutionalProposal");
        }
        return institutionalProposal;
    }
    public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }    

}