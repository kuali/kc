/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.kim.bo;

import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Unit;

/**
 * KRA implementation of the <code>{@link KimQualifiedRolePerson}</code>. It associates a <code>{@link Person}</code> with a 
 * <code>{@link KimRole}</code>. KRA has specific needs on the RolePerson, so this is a specialized version that takes
 * <code>{@link Unit}</code> and Sub<code>{@link Unit}<code>s into consideration.<br/>
 * <br/>
 * <code>{@link Unit}</code> and sub<code>{@linkUnit}</code> information is stored as a <code>{@link KimPersonQualifiedRoleAttribute}</code>
 * as part of the <code>qualifiedRoleAttributes</code> implementation of <code>{@link KimQualifiedRole}</code>.
 * 
 */
public class KraQualifiedRolePerson extends KimQualifiedRolePerson {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3954928435359069216L;
    private static final String KRA_UNIT_NUMBER_ATTR_NAME = "kra.unitNumber";
    private static final String KRA_SUB_UNITS_ATTR_NAME = "kra.subunits";
    
    private Unit unit;
    private String unitName;
    private Person person;
    private String kraPersonId;
    
    
    /**
     * Get the unit number
     * 
     * @return String <code>null</code> if no unitNumber is set.
     */
    public String getUnitNumber() {
        for (KimPersonQualifiedRoleAttribute attribute : getQualifiedRoleAttributes()) {
            if (KRA_UNIT_NUMBER_ATTR_NAME.equals(attribute.getAttributeName())) {
                return attribute.getAttributeValue();
            }
        }
        
        return null;
    }
    
    /**
     * Assign a <code>unitNumber</code>. 
     * 
     * @param unitNumber as a String
     */
    public void setUnitNumber(String unitNumber) {
        KimPersonQualifiedRoleAttribute attribute = new KimPersonQualifiedRoleAttribute();
        attribute.setAttributeName(KRA_UNIT_NUMBER_ATTR_NAME);
        attribute.setAttributeValue(unitNumber);
        getQualifiedRoleAttributes().add(attribute);
    }


    /**
     * Retrieve flag for checking subunits
     * 
     * @return 'Y' if we want to check subunits
     */
    public String getSubUnitsAllowed() {
        for (KimPersonQualifiedRoleAttribute attribute : getQualifiedRoleAttributes()) {
            if (KRA_SUB_UNITS_ATTR_NAME.equals(attribute.getAttributeName())) {
                return attribute.getAttributeValue();
            }
        }
        
        return null;
    }
    
    /**
     * Set flag for checking subunits 
     * 
     * @param subUnitsAllowed 'Y' for yes and 'N' for no.
     */
    public void setSubUnitsAllowed(String subUnitsAllowed) {
        KimPersonQualifiedRoleAttribute attribute = new KimPersonQualifiedRoleAttribute();
        attribute.setAttributeName(KRA_SUB_UNITS_ATTR_NAME);
        attribute.setAttributeValue(subUnitsAllowed);
        getQualifiedRoleAttributes().add(attribute);
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Gets the unitName attribute. 
     * @return Returns the unitName.
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * Sets the unitName attribute value.
     * @param unitName The unitName to set.
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * Gets the kraPersonId attribute. 
     * @return Returns the kraPersonId.
     */
    public String getKraPersonId() {
        return kraPersonId;
    }

    /**
     * Sets the kraPersonId attribute value.
     * @param kraPersonId The kraPersonId to set.
     */
    public void setKraPersonId(String kraPersonId) {
        this.kraPersonId = kraPersonId;
    }

    /**
     * Gets the person attribute. 
     * @return Returns the person.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the person attribute value.
     * @param person The person to set.
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
