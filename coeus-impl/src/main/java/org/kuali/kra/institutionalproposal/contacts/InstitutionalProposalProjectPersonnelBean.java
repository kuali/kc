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
package org.kuali.kra.institutionalproposal.contacts;

import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InstitutionalProposalProjectPersonnelBean extends InstitutionalProposalContactsBean {


    private static final long serialVersionUID = 6251507517307475952L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(InstitutionalProposalProjectPersonnelBean.class);

    private InstitutionalProposalPersonUnit[] newInstitutionalProposalPersonUnits;

    private transient String selectedLeadUnit;

    public InstitutionalProposalProjectPersonnelBean(InstitutionalProposalForm institutionalProposalForm) {
        super(institutionalProposalForm);
    }
    
    public void addNewProjectPersonUnit(int projectPersonIndex) {
        InstitutionalProposalPerson person = getInstitutionalProposal().getProjectPersons().get(projectPersonIndex);
        InstitutionalProposalPersonUnitRuleAddEvent event = generateAddPersonUnitEvent(person, projectPersonIndex);
        boolean success = new InstitutionalProposalPersonUnitAddRuleImpl().processAddInstitutionalProposalPersonUnitBusinessRules(event);
        if(success) {
            person.add(newInstitutionalProposalPersonUnits[projectPersonIndex]);
            if(newInstitutionalProposalPersonUnits[projectPersonIndex].isLeadUnit()) {
                getInstitutionalProposal().setLeadUnit(newInstitutionalProposalPersonUnits[projectPersonIndex].getUnit());
                setSelectedLeadUnit(newInstitutionalProposalPersonUnits[projectPersonIndex].getUnitName());
            }
            initNewInstitutionalProposalPersonUnits();
        }
    }

    /**
     * This method is for adding a project person
     */
    public void addProjectPerson() {
        InstitutionalProposalProjectPersonRuleAddEvent event = generateAddProjectPersonEvent();
        boolean success = new InstitutionalProposalProjectPersonAddRuleImpl().processAddInstitutionalProposalProjectPersonBusinessRules(event);
        if(success){
        InstitutionalProposalPerson institutionalProposalPerson = getNewProjectPerson();
            institutionalProposalPerson.setFaculty(institutionalProposalPerson.getPerson().getFacultyFlag());
            getInstitutionalProposal().add(institutionalProposalPerson);
            init();
            if(institutionalProposalPerson.isPrincipalInvestigator()) {
                institutionalProposalPerson.getUnits().add(new InstitutionalProposalPersonUnit(institutionalProposalPerson, getInstitutionalProposal().getLeadUnit(), true));
            } else {
                if(institutionalProposalPerson.isEmployee() && !institutionalProposalPerson.isKeyPerson()) {
                    institutionalProposalPerson.getUnits().add(new InstitutionalProposalPersonUnit(institutionalProposalPerson, institutionalProposalPerson.getPerson().getUnit(), false));
                }
            }
        }
    }

    /**
     * This method deletes a Project Person from the list
     * @param lineToDelete
     */
    public void deleteProjectPerson(int lineToDelete) {
        List<InstitutionalProposalPerson> projectPersons = getProjectPersonnel(); 
        if(projectPersons.size() > lineToDelete) {
            projectPersons.remove(lineToDelete);
        }        
    }

    /**
     * This method deletes a ProjectPersonUnit from the list 
     * @param projectPersonIndex
     * @param unitIndex
     */
    public void deleteProjectPersonUnit(int projectPersonIndex, int unitIndex) {
        getInstitutionalProposal().getProjectPersons().get(projectPersonIndex).getUnits().remove(unitIndex);
    }
    
    /**
     * Gets the newInstitutionalProposalPersonUnit attribute. 
     * @return Returns the newInstitutionalProposalPersonUnit.
     */
    public InstitutionalProposalPersonUnit getNewInstitutionalProposalPersonUnit(int projectPersonIndex) {
        if(newInstitutionalProposalPersonUnits == null | newInstitutionalProposalPersonUnits.length == 0) {
            initNewInstitutionalProposalPersonUnits();
        }
        return newInstitutionalProposalPersonUnits[projectPersonIndex];
    }
    
    /**
     * Gets the newInstitutionalProposalPersonUnits attribute. 
     * @return Returns the newInstitutionalProposalPersonUnits.
     */
    public InstitutionalProposalPersonUnit[] getNewInstitutionalProposalPersonUnits() {
        for(InstitutionalProposalPersonUnit apu: newInstitutionalProposalPersonUnits) {
            lazyLoadUnit(apu);
        }
        return newInstitutionalProposalPersonUnits;
    }
    

    public InstitutionalProposalPerson getNewProjectPerson() {
        return (InstitutionalProposalPerson) newInstitutionalProposalContact;
    }

    /**
     * Gets the newUnitNumber attribute. 
     * @return Returns the newUnitNumber.
     */
    public String getNewUnitNumber(int projectPersonIndex) {
        return newInstitutionalProposalPersonUnits[projectPersonIndex].getUnit() != null ? newInstitutionalProposalPersonUnits[projectPersonIndex].getUnit().getUnitNumber() : null;
    }
    
    /**
     * This method finds the InstitutionalProposalPersons
     * @return The list; may be empty
     */
    public List<InstitutionalProposalPerson> getProjectPersonnel() {
        return getInstitutionalProposal().getProjectPersons();
    }
    
    /**
     * This method finds the count of InstitutionalProposalContacts in the "Project Personnel" category
     * @return The count; may be 0
     */
    public int getProjectPersonnelCount() {
        return getProjectPersonnel().size();
    }
    
    /**
     * Gets the selectedLeadUnit attribute. 
     * @return Returns the selectedLeadUnit.
     */
    public String getSelectedLeadUnit() {
        selectedLeadUnit = "";
        for(InstitutionalProposalPerson p: getProjectPersonnel()) {
            if(p.isPrincipalInvestigator()) {
                for(InstitutionalProposalPersonUnit apu: p.getUnits()) {
                    if(apu.isLeadUnit()) {
                        selectedLeadUnit = apu.getUnitName(); 
                    }
                }
            }
        }
        return selectedLeadUnit;
    }
    
    public String getUnitName(int projectPersonIndex) {
        return newInstitutionalProposalPersonUnits[projectPersonIndex].getUnit() != null ? newInstitutionalProposalPersonUnits[projectPersonIndex].getUnit().getUnitName() : null; 
    }
     
    public String getUnitNumber(int projectPersonIndex) {
        return getNewUnitNumber(projectPersonIndex);
    }

    /**
     * Sets the selectedLeadUnit attribute value.
     * @param selectedLeadUnit The selectedLeadUnit to set.
     */
    public void setSelectedLeadUnit(String unitName) {
        this.selectedLeadUnit = unitName;
        setLeadUnitSelectionStates(unitName);
    }


    @Override
    protected InstitutionalProposalContact createNewContact() {
        return new InstitutionalProposalPerson();
    }
    
    @Override
    protected Class<? extends ContactRole> getContactRoleType() {
        return PropAwardPersonRole.class;
    }

    @Override
    protected void init() {
        super.init();
        initNewInstitutionalProposalPersonUnits();
    }
    
    private InstitutionalProposalPerson findPrincipalInvestigator() {
        InstitutionalProposalPerson institutionalProposalPerson = null;
        for(InstitutionalProposalContact person: getInstitutionalProposal().getProjectPersons()) {
            if(ContactRole.PI_CODE.equals(person.getContactRole().getRoleCode())) {
                institutionalProposalPerson = (InstitutionalProposalPerson) person;
                break;
            }
        }
        return institutionalProposalPerson;
    }
    
    private InstitutionalProposalPersonUnitRuleAddEvent generateAddPersonUnitEvent(InstitutionalProposalPerson projectPerson, int addUnitPersonIndex) {
        return new InstitutionalProposalPersonUnitRuleAddEvent("InstitutionalProposalPersonUnitRuleAddEvent", "projectPersonnelBean.newInstitutionalProposalPersonUnit", getDocument(), 
                                                                        projectPerson, newInstitutionalProposalPersonUnits[addUnitPersonIndex]);
    }

    private InstitutionalProposalProjectPersonRuleAddEvent generateAddProjectPersonEvent() {
        return new InstitutionalProposalProjectPersonRuleAddEvent("AddInstitutionalProposalProjectPersonRuleEvent", "projectPersonnelBean.newInstitutionalProposalContact", getDocument(), 
                                                    (InstitutionalProposalPerson) newInstitutionalProposalContact);
    }

    private void initNewInstitutionalProposalPersonUnits() {
        newInstitutionalProposalPersonUnits = new InstitutionalProposalPersonUnit[getInstitutionalProposal().getProjectPersons().size()];
        int personIndex = 0;
        for(InstitutionalProposalPerson p: getInstitutionalProposal().getProjectPersons()) {
            newInstitutionalProposalPersonUnits[personIndex++] = new InstitutionalProposalPersonUnit(p);
        }
    }

    /**
     * @param awardPersonUnit
     */
    private void lazyLoadUnit(InstitutionalProposalPersonUnit institutionalProposalPersonUnit) {
        if(institutionalProposalPersonUnit.getUnitNumber() != null && institutionalProposalPersonUnit.getUnit() == null) {
            Map<String, Object> identifiers = new HashMap<String, Object>();
            identifiers.put("unitNumber", institutionalProposalPersonUnit.getUnitNumber());
            Unit newUnit = (Unit) getBusinessObjectService().findByPrimaryKey(Unit.class, identifiers);
            institutionalProposalPersonUnit.setUnit(newUnit);
        }
    }
    
    private void setLeadUnitSelectionStates(String unitName) {
        InstitutionalProposalPerson institutionalProposalPerson = findPrincipalInvestigator();
        if (institutionalProposalPerson != null) {
        for(InstitutionalProposalPersonUnit associatedUnit: institutionalProposalPerson.getUnits()) {
            associatedUnit.setLeadUnit(associatedUnit.getUnit().getUnitName().equals(unitName));
        }
        } else {
          final IllegalStateException e = new IllegalStateException("institutionalProposalPerson == null");
          LOG.warn(e.getMessage(), e);
        }
    }    
}
