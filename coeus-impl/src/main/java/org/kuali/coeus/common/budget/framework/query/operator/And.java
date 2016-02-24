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



/** This class is a wrapper for and operator ( and ).
 * Takes two Operators as parameters and returs true
 * if both the operators return true as result, else returns false.
 */
public class And extends LogicalOperator {
    
    /** creates new instance of And.
     * @param lhsOperator left hand operator.
     * @param rhsOperator right hand operator.
     */    
    public  And(Operator lhsOperator, Operator rhsOperator) {
        super(lhsOperator, rhsOperator);
    } // end And
    
    /** returs true if both the operators return true as result,
     * else returns false.
     * @param baseBean BaseBean
     * @return returs true if both the operators return true as result,
     * else returns false.
     */    
    public boolean getResult(Object baseBean) {
        return (lhsOperator.getResult(baseBean) && rhsOperator.getResult(baseBean));
    }
    
    /** 
     * returns the logical AND condition being checked using left-hand operator(lhsOperator)
     * and right-hand operator(rhsOperator)
     * @return String - AND condition
     */ 
    public String toString() {
        return "( " + lhsOperator.toString() + " && " + rhsOperator + " )";
    }
    
    public And and(Operator relatesTo) {
        return new And(this, relatesTo);
    }

    public Or or(Operator relatesTo) {
        return new Or(this, relatesTo);
    }
    
} // end And



