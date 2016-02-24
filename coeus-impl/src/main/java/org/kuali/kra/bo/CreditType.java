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
package org.kuali.kra.bo;


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * Class representation of the Credit Type Business Object
 *
 * $Id: CreditType.java,v 1.2 2008-07-23 19:16:44 gmcgrego Exp $
 */
public class CreditType extends KcPersistableBusinessObjectBase {

    private String creditTypeCode;

    private Boolean addsToHundred;

    private String description;

    /**
     * Retrieves the description attribute
     * 
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Assigns the description attribute
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the credit type code attribute from the credit type bo
     *
     * @return String
     */
    public String getCreditTypeCode() {
        return creditTypeCode;
    }

    /**
     * Assigns the credit type code attribute to the credit type bo
     *
     * @param creditTypeCode
     */
    public void setCreditTypeCode(String creditTypeCode) {
        this.creditTypeCode = creditTypeCode;
    }

    /**
     * Gets the value of addsToHundred
     *
     * @return the value of addsToHundred
     */
    public final Boolean isAddsToHundred() {
        return this.addsToHundred;
    }

    /**
     * Sets the value of addsToHundred
     *
     * @param argAddsToHundred Value to assign to this.addsToHundred
     */
    public final void setAddsToHundred(final Boolean argAddsToHundred) {
        this.addsToHundred = argAddsToHundred;
    }
}
