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
package org.kuali.kra.personmasschange.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.personmasschange.document.PersonMassChangeDocument;

public class PersonMassChange extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 564388913686346670L;

    private long personMassChangeId;
    
    private String replaceePersonId;
    private String replaceeRolodexId;
    private String replaceeFullName;
    
    private String replacerPersonId;
    private String replacerRolodexId;
    private String replacerFullName;
    
    private PersonMassChangeDocument personMassChangeDocument;
    
    private List<AwardPersonMassChange> awardPersonMassChangeList;
    private List<InstitutionalProposalPersonMassChange> institutionalProposalPersonMassChangeList;
    private List<ProposalDevelopmentPersonMassChange> proposalDevelopmentPersonMassChangeList;
    private List<ProposalLogPersonMassChange> proposalLogPersonMassChangeList;
    private List<SubawardPersonMassChange> subawardPersonMassChangeList;
    private List<NegotiationPersonMassChange> negotiationPersonMassChangeList;
    private List<CommitteePersonMassChange> committeePersonMassChangeList;
    private List<ProtocolPersonMassChange> protocolPersonMassChangeList;
    private List<SchedulePersonMassChange> schedulePersonMassChangeList;
    private List<UnitPersonMassChange> unitPersonMassChangeList;
    
    public PersonMassChange() {
        awardPersonMassChangeList = new ArrayList<AwardPersonMassChange>();
        AwardPersonMassChange newAwardPersonMassChange = new AwardPersonMassChange();
        newAwardPersonMassChange.setPersonMassChange(this);
        awardPersonMassChangeList.add(newAwardPersonMassChange);
        
        institutionalProposalPersonMassChangeList = new ArrayList<InstitutionalProposalPersonMassChange>();
        InstitutionalProposalPersonMassChange newInstitutionalProposalPersonMassChange = new InstitutionalProposalPersonMassChange();
        newInstitutionalProposalPersonMassChange.setPersonMassChange(this);
        institutionalProposalPersonMassChangeList.add(newInstitutionalProposalPersonMassChange);
        
        proposalDevelopmentPersonMassChangeList = new ArrayList<ProposalDevelopmentPersonMassChange>();
        ProposalDevelopmentPersonMassChange newProposalDevelopmentPersonMassChange = new ProposalDevelopmentPersonMassChange();
        newProposalDevelopmentPersonMassChange.setPersonMassChange(this);
        proposalDevelopmentPersonMassChangeList.add(newProposalDevelopmentPersonMassChange);
        
        proposalLogPersonMassChangeList = new ArrayList<ProposalLogPersonMassChange>();
        ProposalLogPersonMassChange newProposalLogPersonMassChange = new ProposalLogPersonMassChange();
        newProposalLogPersonMassChange.setPersonMassChange(this);
        proposalLogPersonMassChangeList.add(newProposalLogPersonMassChange);
        
        subawardPersonMassChangeList = new ArrayList<SubawardPersonMassChange>();
        SubawardPersonMassChange newSubawardPersonMassChange = new SubawardPersonMassChange();
        newSubawardPersonMassChange.setPersonMassChange(this);
        subawardPersonMassChangeList.add(newSubawardPersonMassChange);
        
        negotiationPersonMassChangeList = new ArrayList<NegotiationPersonMassChange>();
        NegotiationPersonMassChange newNegotiationPersonMassChange = new NegotiationPersonMassChange();
        newNegotiationPersonMassChange.setPersonMassChange(this);
        negotiationPersonMassChangeList.add(newNegotiationPersonMassChange);
        
        committeePersonMassChangeList = new ArrayList<CommitteePersonMassChange>();
        CommitteePersonMassChange newCommitteePersonMassChange = new CommitteePersonMassChange();
        newCommitteePersonMassChange.setPersonMassChange(this);
        committeePersonMassChangeList.add(newCommitteePersonMassChange);
        
        protocolPersonMassChangeList = new ArrayList<ProtocolPersonMassChange>();
        ProtocolPersonMassChange newProtocolPersonMassChange = new ProtocolPersonMassChange();
        newProtocolPersonMassChange.setPersonMassChange(this);
        protocolPersonMassChangeList.add(newProtocolPersonMassChange);
        
        schedulePersonMassChangeList = new ArrayList<SchedulePersonMassChange>();
        SchedulePersonMassChange newSchedulePersonMassChange = new SchedulePersonMassChange();
        newSchedulePersonMassChange.setPersonMassChange(this);
        schedulePersonMassChangeList.add(newSchedulePersonMassChange);
        
        unitPersonMassChangeList = new ArrayList<UnitPersonMassChange>();
        UnitPersonMassChange newUnitPersonMassChange = new UnitPersonMassChange();
        newUnitPersonMassChange.setPersonMassChange(this);
        unitPersonMassChangeList.add(newUnitPersonMassChange);
    }
    
    public long getPersonMassChangeId() {
        return personMassChangeId;
    }

    public void setPersonMassChangeId(long personMassChangeId) {
        this.personMassChangeId = personMassChangeId;
    }
    
    public String getReplaceePersonId() {
        return replaceePersonId;
    }

    public void setReplaceePersonId(String replaceePersonId) {
        this.replaceePersonId = replaceePersonId;
    }

    public String getReplaceeRolodexId() {
        return replaceeRolodexId;
    }

    public void setReplaceeRolodexId(String replaceeRolodexId) {
        this.replaceeRolodexId = replaceeRolodexId;
    }

    public String getReplaceeFullName() {
        return replaceeFullName;
    }

    public void setReplaceeFullName(String replaceeFullName) {
        this.replaceeFullName = replaceeFullName;
    }

    public String getReplacerPersonId() {
        return replacerPersonId;
    }

    public void setReplacerPersonId(String replacerPersonId) {
        this.replacerPersonId = replacerPersonId;
    }

    public String getReplacerRolodexId() {
        return replacerRolodexId;
    }

    public void setReplacerRolodexId(String replacerRolodexId) {
        this.replacerRolodexId = replacerRolodexId;
    }

    public String getReplacerFullName() {
        return replacerFullName;
    }

    public void setReplacerFullName(String replacerFullName) {
        this.replacerFullName = replacerFullName;
    }
    
    public PersonMassChangeDocument getPersonMassChangeDocument() {
        return personMassChangeDocument;
    }

    public void setPersonMassChangeDocument(PersonMassChangeDocument personMassChangeDocument) {
        this.personMassChangeDocument = personMassChangeDocument;
    }

    public List<AwardPersonMassChange> getAwardPersonMassChangeList() {
        return awardPersonMassChangeList;
    }

    public void setAwardPersonMassChangeList(List<AwardPersonMassChange> awardPersonMassChangeList) {
        this.awardPersonMassChangeList = awardPersonMassChangeList;
    }

    public List<InstitutionalProposalPersonMassChange> getInstitutionalProposalPersonMassChangeList() {
        return institutionalProposalPersonMassChangeList;
    }

    public void setInstitutionalProposalPersonMassChangeList(List<InstitutionalProposalPersonMassChange> institutionalProposalPersonMassChangeList) {
        this.institutionalProposalPersonMassChangeList = institutionalProposalPersonMassChangeList;
    }

    public List<ProposalDevelopmentPersonMassChange> getProposalDevelopmentPersonMassChangeList() {
        return proposalDevelopmentPersonMassChangeList;
    }

    public void setProposalDevelopmentPersonMassChangeList(List<ProposalDevelopmentPersonMassChange> proposalDevelopmentPersonMassChangeList) {
        this.proposalDevelopmentPersonMassChangeList = proposalDevelopmentPersonMassChangeList;
    }

    public List<ProposalLogPersonMassChange> getProposalLogPersonMassChangeList() {
        return proposalLogPersonMassChangeList;
    }

    public void setProposalLogPersonMassChangeList(List<ProposalLogPersonMassChange> proposalLogPersonMassChangeList) {
        this.proposalLogPersonMassChangeList = proposalLogPersonMassChangeList;
    }

    public List<SubawardPersonMassChange> getSubawardPersonMassChangeList() {
        return subawardPersonMassChangeList;
    }

    public void setSubawardPersonMassChangeList(List<SubawardPersonMassChange> subawardPersonMassChangeList) {
        this.subawardPersonMassChangeList = subawardPersonMassChangeList;
    }

    public List<NegotiationPersonMassChange> getNegotiationPersonMassChangeList() {
        return negotiationPersonMassChangeList;
    }

    public void setNegotiationPersonMassChangeList(List<NegotiationPersonMassChange> negotiationPersonMassChangeList) {
        this.negotiationPersonMassChangeList = negotiationPersonMassChangeList;
    }

    public List<CommitteePersonMassChange> getCommitteePersonMassChangeList() {
        return committeePersonMassChangeList;
    }

    public void setCommitteePersonMassChangeList(List<CommitteePersonMassChange> committeePersonMassChangeList) {
        this.committeePersonMassChangeList = committeePersonMassChangeList;
    }

    public List<ProtocolPersonMassChange> getProtocolPersonMassChangeList() {
        return protocolPersonMassChangeList;
    }

    public void setProtocolPersonMassChangeList(List<ProtocolPersonMassChange> protocolPersonMassChangeList) {
        this.protocolPersonMassChangeList = protocolPersonMassChangeList;
    }

    public List<SchedulePersonMassChange> getSchedulePersonMassChangeList() {
        return schedulePersonMassChangeList;
    }

    public void setSchedulePersonMassChangeList(List<SchedulePersonMassChange> schedulePersonMassChangeList) {
        this.schedulePersonMassChangeList = schedulePersonMassChangeList;
    }

    public List<UnitPersonMassChange> getUnitPersonMassChangeList() {
        return unitPersonMassChangeList;
    }

    public void setUnitPersonMassChangeList(List<UnitPersonMassChange> unitPersonMassChangeList) {
        this.unitPersonMassChangeList = unitPersonMassChangeList;
    }

    public AwardPersonMassChange getAwardPersonMassChange() {
        return awardPersonMassChangeList.isEmpty() ? null : awardPersonMassChangeList.get(0);
    }

    public void setAwardPersonMassChange(AwardPersonMassChange awardPersonMassChange) {
        awardPersonMassChangeList.set(0, awardPersonMassChange);
    }

    public InstitutionalProposalPersonMassChange getInstitutionalProposalPersonMassChange() {
        return institutionalProposalPersonMassChangeList.isEmpty() ? null : institutionalProposalPersonMassChangeList.get(0);
    }

    public void setInstitutionalProposalPersonMassChange(InstitutionalProposalPersonMassChange institutionalProposalPersonMassChange) {
        institutionalProposalPersonMassChangeList.set(0, institutionalProposalPersonMassChange);
    }

    public ProposalDevelopmentPersonMassChange getProposalDevelopmentPersonMassChange() {
        return proposalDevelopmentPersonMassChangeList.isEmpty() ? null : proposalDevelopmentPersonMassChangeList.get(0);
    }

    public void setProposalDevelopmentPersonMassChange(ProposalDevelopmentPersonMassChange proposalDevelopmentPersonMassChange) {
        proposalDevelopmentPersonMassChangeList.set(0, proposalDevelopmentPersonMassChange);
    }

    public ProposalLogPersonMassChange getProposalLogPersonMassChange() {
        return proposalLogPersonMassChangeList.isEmpty() ? null : proposalLogPersonMassChangeList.get(0);
    }

    public void setProposalLogPersonMassChange(ProposalLogPersonMassChange proposalLogPersonMassChange) {
        proposalLogPersonMassChangeList.set(0, proposalLogPersonMassChange);
    }

    public SubawardPersonMassChange getSubawardPersonMassChange() {
        return subawardPersonMassChangeList.isEmpty() ? null : subawardPersonMassChangeList.get(0);
    }

    public void setSubawardPersonMassChange(SubawardPersonMassChange subawardPersonMassChange) {
        subawardPersonMassChangeList.set(0, subawardPersonMassChange);
    }

    public NegotiationPersonMassChange getNegotiationPersonMassChange() {
        return negotiationPersonMassChangeList.isEmpty() ? null : negotiationPersonMassChangeList.get(0);
    }

    public void setNegotiationPersonMassChange(NegotiationPersonMassChange negotiationPersonMassChange) {
        negotiationPersonMassChangeList.set(0, negotiationPersonMassChange);
    }

    public CommitteePersonMassChange getCommitteePersonMassChange() {
        return committeePersonMassChangeList.isEmpty() ? null : committeePersonMassChangeList.get(0);
    }

    public void setCommitteePersonMassChange(CommitteePersonMassChange committeePersonMassChange) {
        committeePersonMassChangeList.set(0, committeePersonMassChange);
    }

    public ProtocolPersonMassChange getProtocolPersonMassChange() {
        return protocolPersonMassChangeList.isEmpty() ? null : protocolPersonMassChangeList.get(0);
    }

    public void setProtocolPersonMassChange(ProtocolPersonMassChange protocolPersonMassChange) {
        protocolPersonMassChangeList.set(0, protocolPersonMassChange);
    }

    public SchedulePersonMassChange getSchedulePersonMassChange() {
        return schedulePersonMassChangeList.isEmpty() ? null : schedulePersonMassChangeList.get(0);
    }

    public void setSchedulePersonMassChange(SchedulePersonMassChange schedulePersonMassChange) {
        schedulePersonMassChangeList.set(0, schedulePersonMassChange);
    }

    public UnitPersonMassChange getUnitPersonMassChange() {
        return unitPersonMassChangeList.isEmpty() ? null : unitPersonMassChangeList.get(0);
    }

    public void setUnitPersonMassChange(UnitPersonMassChange unitPersonMassChange) {
        unitPersonMassChangeList.set(0, unitPersonMassChange);
    }

    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> propMap = new LinkedHashMap<String, Object>();
        propMap.put("personMassChangeId", getPersonMassChangeId());
        propMap.put("replaceePersonId", getReplaceePersonId());
        propMap.put("replaceeRolodexId", getReplaceeRolodexId());
        propMap.put("replacerPersonId", getReplacerPersonId());
        propMap.put("replacerRolodexId", getReplacerRolodexId());
        return propMap;
    }

}