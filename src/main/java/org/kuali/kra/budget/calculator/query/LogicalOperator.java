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

/** implements Operator and holds left hand and right hand values
 * which are instances of Opertors(Since Logical Operators operate on two Relational, Logical Operators).
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



