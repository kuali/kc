/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.budget.calculator.query;



/** This class is a wrapper for greater than operator ( > ).
 * Takes field and Comparable object as parameters in the constructor and returs true
 * if the field of the Object object is greater than the Comparable object, else returns false.
 */
public class GreaterThan extends RelationalOperator {

    /** creates a new GreaterThan object.
     * @param fieldName field which has to be compared.
     * @param fixedData compare value.
     */    
    public  GreaterThan(String fieldName, Comparable fixedData) {        
        super(fieldName, fixedData);
    } // end GreaterThan        

    /** returns true if the field of the Object object is greater than the Comparable object, else returns false.
     * @param baseBean Object
     * @return true if the field of the Object object is greater than the Comparable object, else returns false.
     */    
    public boolean getResult(Object baseBean) {
        if(fixedData == null) return false; //cannot query property > null. will always return false
        try{
            return compare(baseBean) > 0;
        }catch (Exception exception) {
            return false;
        }
    }
    
    /** 
     * returns the greater than condition being checked using fieldName and fixedData
     * @return String - Greater than condition
     */ 
    public String toString() {
        return "( " + fieldName + " > " + fixedData + " )";
    }

 } // end GreaterThan



