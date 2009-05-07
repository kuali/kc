/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.contacts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.bo.ContactRole;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;

/**
 * This class provides support for the Award Contacts Project Personnel panel
 */
public class AwardProjectPersonnelBean extends AwardContactsBean {
    private static final long serialVersionUID = -8213637358006756203L;

    private AwardPersonUnit newAwardPersonUnit;

    private transient String selectedLeadUnit;
    
    public AwardProjectPersonnelBean(AwardForm awardForm) {
        super(awardForm);        
    }

    public void addNewProjectPersonUnit(int projectPersonIndex) {
        AwardPerson person = (AwardPerson) getAward().getProjectPersons().get(projectPersonIndex);
        AwardPersonUnitRuleAddEvent event = generateAddPersonUnitEvent(person);
        boolean success = new AwardPersonUnitAddRuleImpl().processAddAwardPersonUnitBusinessRules(event);
        if(success) {
            person.add(newAwardPersonUnit);
            if(newAwardPersonUnit.isLeadUnit()) {
                setSelectedLeadUnit(newAwardPersonUnit.getUnitName());
            }
            initNewAwardPersonUnit();
        }
    }
    /**
     * This method is for adding a project person
     */
    public void addProjectPerson() {
        AwardProjectPersonRuleAddEvent event = generateAddProjectPersonEvent();
        boolean success = new AwardProjectPersonAddRuleImpl().processAddAwardProjectPersonBusinessRules(event);
        if(success){
            getAward().add(getNewProjectPerson());
            init();
        }
    }

    /**
     * This method clears the NewProjectPersonUnit
     */
    public void clearNewProjectPersonUnit() {
        initNewAwardPersonUnit();
    }

    /**
     * This method deletes a Project Person from the list
     * @param lineToDelete
     */
    public void deleteProjectPerson(int lineToDelete) {
        List<AwardPerson> projectPersons = getProjectPersonnel(); 
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
        getAward().getProjectPersons().get(projectPersonIndex).getUnits().remove(unitIndex);
    }
    
    /**
     * Gets the newAwardPersonUnit attribute. 
     * @return Returns the newAwardPersonUnit.
     */
    public AwardPersonUnit getNewAwardPersonUnit() {
        return newAwardPersonUnit;
    }
    
    /**
     * @return
     */
    public AwardPerson getNewProjectPerson() {
        return (AwardPerson) newAwardContact;
    }

    /**
     * Gets the newUnitNumber attribute. 
     * @return Returns the newUnitNumber.
     */
    public String getNewUnitNumber() {
        return newAwardPersonUnit.getUnit() != null ? newAwardPersonUnit.getUnit().getUnitNumber() : null;
    }
    
    /**
     * This method finds the AwardPersons
     * @return The list; may be empty
     */
    public List<AwardPerson> getProjectPersonnel() {
        return getAward().getProjectPersons();
    }
    
    /**
     * This method finds the count of AwardContacts in the "Project Personnel" category
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
        for(AwardPerson p: getProjectPersonnel()) {
            if(p.isPrincipalInvestigator()) {
                for(AwardPersonUnit apu: p.getUnits()) {
                    if(apu.isLeadUnit()) {
                        selectedLeadUnit = apu.getUnitName(); 
                    }
                }
            }
        }
        return selectedLeadUnit;
    }
    
    public String getUnitName() {
        return newAwardPersonUnit.getUnit() != null ? newAwardPersonUnit.getUnit().getUnitName() : null; 
    }
     
    public String getUnitNumber() {
        return getNewUnitNumber();
    }
    
    /**
     * Sets the newAwardPersonUnit attribute value.
     * @param newAwardPersonUnit The newAwardPersonUnit to set.
     */
    public void setNewAwardPersonUnit(AwardPersonUnit newAwardPersonUnit) {
        this.newAwardPersonUnit = newAwardPersonUnit;
    }
    
    /**
     * Sets the newUnitNumber attribute value.
     * @param newUnitNumber The newUnitNumber to set.
     */
    public void setNewUnitNumber(String newUnitNumber) {
        Unit newUnit = null;
        if(newUnitNumber != null) {
            Map<String, Object> identifiers = new HashMap<String, Object>();
            identifiers.put("unitNumber", newUnitNumber);
            newUnit = (Unit) getBusinessObjectService().findByPrimaryKey(Unit.class, identifiers);
        }
        newAwardPersonUnit.setUnit(newUnit);        
    }

    /**
     * Sets the selectedLeadUnit attribute value.
     * @param selectedLeadUnit The selectedLeadUnit to set.
     */
    public void setSelectedLeadUnit(String unitName) {
        this.selectedLeadUnit = unitName;
        setLeadUnitSelectionStates(unitName);
    }


    /**
     * @see org.kuali.kra.award.contacts.AwardContactsBean#getContactRoleType()
     */
    @Override
    protected Class<? extends ContactRole> getContactRoleType() {
        return ProposalPersonRole.class;
    }
    
    /**
     * @see org.kuali.kra.award.contacts.AwardContactsBean#init()
     */
    @Override
    protected void init() {
        super.init();
        initNewAwardPersonUnit();
    }

    private AwardPerson findPrincipalInvestigator() {
        AwardPerson awardPerson = null;
        for(AwardContact person: getAward().getProjectPersons()) {
            if(ContactRole.PI_CODE.equals(person.getContactRole().getRoleCode())) {
                awardPerson = (AwardPerson) person;
                break;
            }
        }
        return awardPerson;
    }
    
    private AwardPersonUnitRuleAddEvent generateAddPersonUnitEvent(AwardPerson projectPerson) {
        return new AwardPersonUnitRuleAddEvent("AwardPersonUnitRuleAddEvent", "projectPersonnelBean.newAwardPersonUnit", getDocument(), 
                                                                        projectPerson, newAwardPersonUnit);
    }
    
    private AwardProjectPersonRuleAddEvent generateAddProjectPersonEvent() {
        return new AwardProjectPersonRuleAddEvent("AddAwardProjectPersonRuleEvent", "projectPersonnelBean.newAwardContact", getDocument(), 
                                                    (AwardPerson) newAwardContact);
    }

    @Override
    protected AwardContact createNewContact() {
        return new AwardPerson();
    }

    private void initNewAwardPersonUnit() {
        newAwardPersonUnit = new AwardPersonUnit(getNewProjectPerson());
    }

    private void setLeadUnitSelectionStates(String unitName) {
        AwardPerson awardPerson = findPrincipalInvestigator();
        for(AwardPersonUnit associatedUnit: awardPerson.getUnits()) {
            associatedUnit.setLeadUnit(associatedUnit.getUnit().getUnitName().equals(unitName));
        }
    }    
}
