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
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.personmasschange.document.PersonMassChangeDocument;

public class PersonMassChange extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 2236054992674521658L;

    private long personMassChangeId;
    
    private String replaceePersonId;
    private String replaceeRolodexId;
    private String replaceeFullName;
    
    private String replacerPersonId;
    private String replacerRolodexId;
    private String replacerFullName;
    
    private boolean changeAllSequences;
    
    private PersonMassChangeDocument personMassChangeDocument;
    
    private List<AwardPersonMassChange> awardPersonMassChangeList;
    private List<IacucProtocolPersonMassChange> iacucProtocolPersonMassChangeList;
    private List<InstitutionalProposalPersonMassChange> institutionalProposalPersonMassChangeList;
    private List<ProposalDevelopmentPersonMassChange> proposalDevelopmentPersonMassChangeList;
    private List<ProposalLogPersonMassChange> proposalLogPersonMassChangeList;
    private List<SubawardPersonMassChange> subawardPersonMassChangeList;
    private List<NegotiationPersonMassChange> negotiationPersonMassChangeList;
    private List<ProtocolPersonMassChange> protocolPersonMassChangeList;
    private List<UnitAdministratorPersonMassChange> unitAdministratorPersonMassChangeList;
    
    public PersonMassChange() {
        awardPersonMassChangeList = new ArrayList<AwardPersonMassChange>();
        AwardPersonMassChange newAwardPersonMassChange = new AwardPersonMassChange();
        newAwardPersonMassChange.setPersonMassChange(this);
        awardPersonMassChangeList.add(newAwardPersonMassChange);
        
        iacucProtocolPersonMassChangeList = new ArrayList<IacucProtocolPersonMassChange>();
        IacucProtocolPersonMassChange newIacucProtocolPersonMassChange = new IacucProtocolPersonMassChange();
        newIacucProtocolPersonMassChange.setPersonMassChange(this);
        iacucProtocolPersonMassChangeList.add(newIacucProtocolPersonMassChange);
        
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

        protocolPersonMassChangeList = new ArrayList<ProtocolPersonMassChange>();
        ProtocolPersonMassChange newProtocolPersonMassChange = new ProtocolPersonMassChange();
        newProtocolPersonMassChange.setPersonMassChange(this);
        protocolPersonMassChangeList.add(newProtocolPersonMassChange);

        unitAdministratorPersonMassChangeList = new ArrayList<UnitAdministratorPersonMassChange>();
        UnitAdministratorPersonMassChange newUnitAdministratorPersonMassChange = new UnitAdministratorPersonMassChange();
        newUnitAdministratorPersonMassChange.setPersonMassChange(this);
        unitAdministratorPersonMassChangeList.add(newUnitAdministratorPersonMassChange);
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
    
    public boolean isChangeAllSequences() {
        return changeAllSequences;
    }
    
    public void setChangeAllSequences(boolean changeAllSequences) {
        this.changeAllSequences = changeAllSequences;
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

    public List<IacucProtocolPersonMassChange> getIacucProtocolPersonMassChangeList() {
        return iacucProtocolPersonMassChangeList;
    }

    public void setIacucProtocolPersonMassChangeList(List<IacucProtocolPersonMassChange> iacucProtocolPersonMassChangeList) {
        this.iacucProtocolPersonMassChangeList = iacucProtocolPersonMassChangeList;
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

    public List<ProtocolPersonMassChange> getProtocolPersonMassChangeList() {
        return protocolPersonMassChangeList;
    }

    public void setProtocolPersonMassChangeList(List<ProtocolPersonMassChange> protocolPersonMassChangeList) {
        this.protocolPersonMassChangeList = protocolPersonMassChangeList;
    }
    
    public List<UnitAdministratorPersonMassChange> getUnitAdministratorPersonMassChangeList() {
        return unitAdministratorPersonMassChangeList;
    }

    public void setUnitAdministratorPersonMassChangeList(List<UnitAdministratorPersonMassChange> unitAdministratorPersonMassChangeList) {
        this.unitAdministratorPersonMassChangeList = unitAdministratorPersonMassChangeList;
    }

    public AwardPersonMassChange getAwardPersonMassChange() {
        return awardPersonMassChangeList.isEmpty() ? null : awardPersonMassChangeList.get(0);
    }

    public void setAwardPersonMassChange(AwardPersonMassChange awardPersonMassChange) {
        awardPersonMassChangeList.set(0, awardPersonMassChange);
    }
    
    public IacucProtocolPersonMassChange getIacucProtocolPersonMassChange() {
        return iacucProtocolPersonMassChangeList.isEmpty() ? null : iacucProtocolPersonMassChangeList.get(0);
    }

    public void setIacucProtocolPersonMassChange(IacucProtocolPersonMassChange iacucProtocolPersonMassChange) {
        iacucProtocolPersonMassChangeList.set(0, iacucProtocolPersonMassChange);
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

    public ProtocolPersonMassChange getProtocolPersonMassChange() {
        return protocolPersonMassChangeList.isEmpty() ? null : protocolPersonMassChangeList.get(0);
    }

    public void setProtocolPersonMassChange(ProtocolPersonMassChange protocolPersonMassChange) {
        protocolPersonMassChangeList.set(0, protocolPersonMassChange);
    }

    public UnitAdministratorPersonMassChange getUnitAdministratorPersonMassChange() {
        return unitAdministratorPersonMassChangeList.isEmpty() ? null : unitAdministratorPersonMassChangeList.get(0);
    }

    public void setUnitPersonMassChange(UnitAdministratorPersonMassChange unitAdministratorPersonMassChange) {
        unitAdministratorPersonMassChangeList.set(0, unitAdministratorPersonMassChange);
    }

}