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

/** implements Operator and holds left hand and right hand values
 * which are instances of Operators (Since Logical Operators operate on two Relational, Logical Operators).
 */
public abstract class LogicalOperator implements Operator {
    
    /** holds left hand Operator.
     */    
    protected Operator lhsOperator;
    /** holds right hand operator.
     */    
    protected Operator rhsOperator;
    
    /**
     * @param lhsOperator left hand Operator.
     * @param rhsOperator right hand operator.
     */    
    public  LogicalOperator(Operator lhsOperator, Operator rhsOperator) {
        this.lhsOperator = lhsOperator;
        this.rhsOperator = rhsOperator;
    } // end LogicalOperator
    
} // end LogicalOperator



