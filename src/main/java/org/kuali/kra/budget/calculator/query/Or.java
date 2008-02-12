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


/** This class is a wrapper for or operator ( || ).
 * Takes two Operators as parameters in the constructor and returs true
 * if any one of the operators return true as result, else returns false.
 */
public class Or extends LogicalOperator {

    /** creates new instance of Or
     * @param lhsOperator left hand operator.
     * @param rhsOperator right hand operator.
     */    
    public  Or(Operator lhsOperator, Operator rhsOperator) {        
        super(lhsOperator, rhsOperator);
    } // end Or        

    /** returs true
     * if any one of the operators return true as result, else returns false.
     * @param baseBean BaseBean
     * @return true if any one of the operators return true as result, else returns false.
     */    
    public boolean getResult(Object baseBean) {        
        return (lhsOperator.getResult(baseBean) || rhsOperator.getResult(baseBean));
    }
    
    /** 
     * returns the logical OR condition being checked using left-hand operator(lhsOperator)
     * and right-hand operator(rhsOperator)
     * @return String - OR condition
     */ 
    public String toString() {
        return "( " + lhsOperator.toString() + " || " + rhsOperator + " )";
    }

 // end getResult        

 } // end Or



