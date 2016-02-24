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
package org.kuali.kra.personmasschange.bo;

import org.kuali.coeus.propdev.impl.person.masschange.ProposalDevelopmentPersonMassChange;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.personmasschange.document.PersonMassChangeDocument;

import java.util.ArrayList;
import java.util.List;

public class PersonMassChange extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 2236054992674521658L;

    private long personMassChangeId;
    
    private String replaceePersonId;
    private Integer replaceeRolodexId;
    private String replaceeFullName;
    
    private String replacerPersonId;
    private Integer replacerRolodexId;
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

    public Integer getReplaceeRolodexId() {
        return replaceeRolodexId;
    }

    public void setReplaceeRolodexId(Integer replaceeRolodexId) {
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

    public Integer getReplacerRolodexId() {
        return replacerRolodexId;
    }

    public void setReplacerRolodexId(Integer replacerRolodexId) {
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
