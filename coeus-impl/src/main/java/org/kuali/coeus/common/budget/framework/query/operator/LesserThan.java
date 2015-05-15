/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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



/** This class is a wrapper for lesser than operator ( &lt; ).
 * Takes field and Comparable object as parameters in the constructor and returs true
 * if the field of the Object object is lesser than the Comparable object, else returns false.
 */
public class LesserThan extends RelationalOperator {


    /** creates new instance of LesserThan.
     * @param fieldName field which has to be compared.
     * @param fixedData compare value.
     */    
    public  LesserThan(String fieldName, Comparable fixedData) {        
        super(fieldName, fixedData);
    } // end LesserThan        

    /** rreturs true if the field of the CoeusBean object is lesser than the Comparable object, else returns false.
     * @param baseBean Object
     * @return true if the field of the CoeusBean object is lesser than the Comparable object, else returns false.
     */    
    public boolean getResult(Object baseBean) {  
        if(fixedData == null) return false; //cannot query property < null. will always return false
        try{
            return compare(baseBean) < 0;
        }catch (Exception exception) {
            return false;
        }
    }
    
    /** 
     * returns the lesser than condition being checked using fieldName and fixedData
     * @return String - Lesser than condition
     */
    public String toString() {
        return "( " + fieldName + " < " + fixedData + " )";
    }

 } // end LesserThan



