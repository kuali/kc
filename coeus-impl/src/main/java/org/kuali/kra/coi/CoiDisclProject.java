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
package org.kuali.kra.coi;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.protocol.IacucProtocolType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.protocol.ProtocolType;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CoiDisclProject extends KcPersistableBusinessObjectBase implements Disclosurable {
    


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
    private ScaleTwoDecimal numberField1;
    private ScaleTwoDecimal numberField2;
    private String shortTextField3;
    private String longTextField3;
    private String selectBox1;
    private Integer disclosureDispositionCode; 
    private String disclosureStatusCode;  
    private ProposalType proposalType;
    private ProtocolType protocolType;
    private IacucProtocolType iacucProtocolType;
    private Protocol protocol;
    private IacucProtocol iacucProtocol;
    private DevelopmentProposal proposal;
    private InstitutionalProposal institutionalProposal;
    private Award award;
    private CoiDisclosureEventType coiDisclosureEventType;
    private Long originalCoiDisclosureId; 
    private CoiDisclosure originalCoiDisclosure; 

    @SkipVersioning
    private CoiDisclosure coiDisclosure; 
    @SkipVersioning
    private List<CoiDiscDetail> coiDiscDetails; 
    
    @SkipVersioning
    private transient List<LabelValuePair> headerItems; 
    
    @SkipVersioning
    private CoiDispositionStatus coiDispositionStatus;

    private transient DataObjectService dataObjectService;
    
    public CoiDisclProject(String coiDisclosureNumber, Integer sequenceNumber) { 
        this.coiDisclosureNumber = coiDisclosureNumber;
        this.sequenceNumber = sequenceNumber;
        headerItems = new ArrayList<LabelValuePair>();

    } 
    public CoiDisclProject() { 
        headerItems = new ArrayList<LabelValuePair>();
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
    public ScaleTwoDecimal getNumberField1() {
        return numberField1;
    }
    public void setNumberField1(ScaleTwoDecimal numberField1) {
        this.numberField1 = numberField1;
    }
    public ScaleTwoDecimal getNumberField2() {
        return numberField2;
    }
    public void setNumberField2(ScaleTwoDecimal numberField2) {
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
        } else if (isIacucProtocolEvent()) {
            label = "IACUC Protocol Number";
        } else if (this.isProposalEvent()) {
            label = "Proposal Number";
        }
        return label;
    }
    
    public String getProjectTitleLabel() {
        String label = "Project Title";
        if (isAwardEvent()) {
            label = "Award Title";
        } else if (isProtocolEvent() || isIacucProtocolEvent()) {
            label = "Protocol Name";
        }
        return label;
    }
    
    public String getProjectTypeLabel() {
        String label = "Project Type";
        if (isProtocolEvent() || isIacucProtocolEvent()) {
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
        if (isAwardEvent() || isManualAwardEvent()) {
            description = "Award";
        } else if (isProtocolEvent() || isManualProtocolEvent()) {
            description = "Protocol";
        } else if (isIacucProtocolEvent() || isManualIacucProtocolEvent()) {
            description = "IACUC Protocol";
        } else if (isManualTravelEvent()) {
            description = "Travel";
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
    
    public IacucProtocolType getIacucProtocolType() {
        if (StringUtils.isNotBlank(selectBox1) && protocolType == null) {
            this.refreshReferenceObject("iacucProtocolType");
        }
        return iacucProtocolType;
    }
    
    public void setIacucProtocolType(IacucProtocolType iacucProtocolType) {
        this.iacucProtocolType = iacucProtocolType;
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

    public boolean isIacucProtocolEvent() {
        return StringUtils.equals(CoiDisclosureEventType.IACUC_PROTOCOL, this.disclosureEventType);
    }

    public boolean isManualProposalEvent() {
        return StringUtils.equals(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL, this.disclosureEventType);
    }

    public boolean isManualAwardEvent() {
        return StringUtils.equals(CoiDisclosureEventType.MANUAL_AWARD, this.disclosureEventType);
    }
    
    public boolean isManualProtocolEvent() {
        return StringUtils.equals(CoiDisclosureEventType.MANUAL_IRB_PROTOCOL, this.disclosureEventType);
    }

    public boolean isManualIacucProtocolEvent() {
        return StringUtils.equals(CoiDisclosureEventType.MANUAL_IACUC_PROTOCOL, this.disclosureEventType);
    }

    public boolean isManualTravelEvent() {
        return StringUtils.equals(CoiDisclosureEventType.MANUAL_TRAVEL, this.disclosureEventType);
    }

    public boolean isManualEvent() {
        return isManualAwardEvent() || isManualProposalEvent() || isManualProtocolEvent() || isManualIacucProtocolEvent() || isManualTravelEvent();
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
    
    public IacucProtocol getIacucProtocol() {
        if (iacucProtocol == null) {
            this.refreshReferenceObject("iacucProtocol");
        }
        return iacucProtocol;
    }
    
    public void setIacucProtocol(IacucProtocol iacucProtocol) {
        this.iacucProtocol = iacucProtocol;
    }
    
    public DevelopmentProposal getProposal() {
        if (proposal == null) {
            proposal = getDataObjectService().find(DevelopmentProposal.class, getCoiProjectId());
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

    public class LabelValuePair {
        private String label;
        private String value;
        public LabelValuePair(String label, String value) {
            this.label = label;
            this.value = value;
        }
        public String getLabel() {
            return label;
        }
        public void setLabel(String label) {
            this.label = label;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }

    public List<LabelValuePair> getHeaderItems() {
        if (headerItems == null && coiProjectId != null) {
            initHeaderItems();
        }
        return headerItems;
    }
    public void setHeaderItems(List<LabelValuePair> headerItems) {
        this.headerItems = headerItems;
    }
    
    public void initHeaderItems() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyyy");
        headerItems = new ArrayList<LabelValuePair>();
        if (coiDisclosureEventType == null) {
           this.refreshReferenceObject("coiDisclosureEventType");
        }
        headerItems.add(new LabelValuePair(coiDisclosureEventType.getProjectTitleLabel(), coiProjectTitle));
        if (coiDisclosureEventType.isUseSelectBox1()) {            
            headerItems.add(new LabelValuePair(coiDisclosureEventType.getSelectBox1Label(), getSelectDesc()));
        }
        if (coiDisclosureEventType.isUseLongTextField1()) {            
            headerItems.add(new LabelValuePair(coiDisclosureEventType.getLongTextField1Label(), longTextField1));
        }
        if (coiDisclosureEventType.isUseShortTextField1()) {            
            headerItems.add(new LabelValuePair(coiDisclosureEventType.getShortTextField1Label(), shortTextField1));
        }
        if (coiDisclosureEventType.isUseLongTextField2()) {            
            headerItems.add(new LabelValuePair(coiDisclosureEventType.getLongTextField2Label(), longTextField2));
        }
        if (coiDisclosureEventType.isUseShortTextField2()) {            
            headerItems.add(new LabelValuePair(coiDisclosureEventType.getShortTextField2Label(), shortTextField2));
        }
        if (coiDisclosureEventType.isUseLongTextField3()) {            
            headerItems.add(new LabelValuePair(coiDisclosureEventType.getLongTextField3Label(), longTextField3));
        }
        if (coiDisclosureEventType.isUseShortTextField3()) {            
            headerItems.add(new LabelValuePair(coiDisclosureEventType.getShortTextField3Label(), shortTextField3));
        }
        if (coiDisclosureEventType.isUseNumberField1()) {            
            headerItems.add(new LabelValuePair(coiDisclosureEventType.getNumberField1Label(), numberField1 == null ? "" : numberField1.toString()));
        }
        if (coiDisclosureEventType.isUseNumberField2()) {            
            headerItems.add(new LabelValuePair(coiDisclosureEventType.getNumberField2Label(), numberField2 == null ? "" : numberField2.toString()));
        }
        if (coiDisclosureEventType.isUseDateField1()) {            
            headerItems.add(new LabelValuePair(coiDisclosureEventType.getDateField1Label(), dateField1 == null ? "" : df.format(dateField1)));
        }
        if (coiDisclosureEventType.isUseDateField2()) {            
            headerItems.add(new LabelValuePair(coiDisclosureEventType.getDateField2Label(), dateField2 == null ? "" : df.format(dateField2)));
        }
    }
    
    private String getSelectDesc() {
        String description = Constants.EMPTY_STRING;
        
        try {
            String valuesFinder = coiDisclosureEventType.getSelectBox1ValuesFinder();
            if (StringUtils.isNotBlank(valuesFinder)) {
                Class valuesFinderClass = Class.forName(valuesFinder);
                KeyValuesFinder keyValuesFinder = (KeyValuesFinder)valuesFinderClass.newInstance();
                List<KeyValue> keyValues = keyValuesFinder.getKeyValues();
                if (!CollectionUtils.isEmpty(keyValues)) {
                    for (KeyValue keyValue : keyValues) {
                        if (keyValue.getKey().equals(selectBox1)) {
                            description = keyValue.getValue();
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            //Failed to load select box 
        }
        return description;
      
    }
    public Long getOriginalCoiDisclosureId() {
        return originalCoiDisclosureId;
    }
    public void setOriginalCoiDisclosureId(Long originalCoiDisclosureId) {
        this.originalCoiDisclosureId = originalCoiDisclosureId;
    }
    public Integer getDisclosureDispositionCode() {
        return disclosureDispositionCode;
    }
    public void setDisclosureDispositionCode(Integer disclosureDispositionCode) {
        this.disclosureDispositionCode = disclosureDispositionCode;
    }
    public void setDisclosureDispositionCode(String disclosureDispositionCode) {
        this.disclosureDispositionCode = Integer.valueOf(disclosureDispositionCode);
    }
    public String getDisclosureStatusCode() {
        return disclosureStatusCode;
    }
    public void setDisclosureStatusCode(String disclosureStatusCode) {
        this.disclosureStatusCode = disclosureStatusCode;
    }
    public void setCoiDispositionStatus(CoiDispositionStatus coiDispositionStatus) {
        this.coiDispositionStatus = coiDispositionStatus;
    }
    public CoiDispositionStatus getCoiDispositionStatus() {
        if (disclosureDispositionCode != null &&
                (coiDispositionStatus == null || !StringUtils.equals(coiDispositionStatus.getCoiDispositionCode(), disclosureDispositionCode.toString()))) {
            this.refreshReferenceObject("coiDispositionStatus");
        }
        return coiDispositionStatus;
    }

    public CoiDisclosure getOriginalCoiDisclosure() {
        if (originalCoiDisclosureId != null && originalCoiDisclosure ==null) {
            this.refreshReferenceObject("originalCoiDisclosure");
        }
        return originalCoiDisclosure;
    }
    public void setOriginalCoiDisclosure(CoiDisclosure originalCoiDisclosure) {
        this.originalCoiDisclosure = originalCoiDisclosure;
    }
	public DataObjectService getDataObjectService() {
        if (dataObjectService == null) {
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }
		return dataObjectService;
	}
	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
    
}
