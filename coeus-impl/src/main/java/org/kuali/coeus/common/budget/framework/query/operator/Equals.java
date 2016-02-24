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

package org.kuali.coeus.common.budget.framework.query.operator;


/** This class is a wrapper for equals operator ( == ).
 * Takes field and Comparable objects as parameters in the constructor and returs true
 * if the field of the Object object is equal to the Comparable object, else returns false.
 */

public class Equals extends RelationalOperator {
    
    /** creates a new Equals object.
     * @param fieldName field which has to be compared.
     * @param fixedData compare value.
     */  
    public  Equals(String fieldName, Comparable fixedData) {
        super(fieldName, fixedData);
    }
    
    /** creates a new Equals object.
     * Since Boolean object is not Comparable, use this constructor to check boolean values.
     * @param fieldName field which has to be compared.
     * @param booleanFixedData compare value.
     */  
    public  Equals(String fieldName, boolean booleanFixedData) {
        super(fieldName, booleanFixedData);
    }
    
    /** returns true if the field of the Object object is equal to the Comparable object/boolean data, else returns false.
     * @param baseBean Object
     * @return true if the field of the Object object is equal to the Comparable object/boolean data, else returns false.
     */ 
    public boolean getResult(Object baseBean) {
        try{
            return compare(baseBean) == 0;
        }catch (Exception exception) {
            return false;
        }
    } // end getResult
    
    /**
     * returns the equality condition being checked using fieldName and fixedData
     * @return String - Equality condition
     */
    public String toString() {
        if (! isBoolean) {
            return "( " + fieldName + " == " + fixedData + " )";
        } else {
            return "( " + fieldName + " == " + booleanFixedData + " )";
        }
    }
    
} // end Equals



