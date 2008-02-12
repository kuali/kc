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



/** This class is a wrapper for equals operator ( != ).
 * Takes field and Comparable objects as parameters in the constructor and returns true
 * if the field of the BaseBean object is not equal to the Comparable object, else returns false.
 */

public class NotEquals extends RelationalOperator {
    
    /** creates a new NotEquals object.
     * @param fieldName field which has to be compared.
     * @param fixedData compare value.
     */  
    public  NotEquals(String fieldName, Comparable fixedData) {        
        super(fieldName, fixedData);
    } // end NotEquals   
    
    /** creates a new NotEquals object.
     * Since Boolean object is not Comparable, use this constructor to check boolean values.
     * @param fieldName field which has to be compared.
     * @param booleanFixedData compare value.
     */  
    public  NotEquals(String fieldName, boolean booleanFixedData) {        
        super(fieldName, booleanFixedData);
    }     

     /** returns true if the field of the BaseBean object is not equal to the Comparable object/boolean data, else returns false.
     * @param baseBean BaseBean
     * @return true if the field of the BaseBean object is not equal to the Comparable object/boolean data, else returns false.
     */ 
    public boolean getResult(Object baseBean) {
        try{
            return compare(baseBean) != 0;
        }catch (Exception exception) {
            return false;
        }
    } // end getResult 
    
    /** 
     * returns the inequality condition being checked using fieldName and fixedData
     * @return String - Inequality condition
     */ 
    public String toString() {
        if (! isBoolean) {
            return "( " + fieldName + " != " + fixedData + " )";
        } else {
            return "( " + fieldName + " != " + booleanFixedData + " )";
        }
    }

 } // end NotEquals



