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

/** The Operator interface should be implemented by any class whose instances are intended to be executed as an Operator. The class must define a method of boolean argument called getResult.
 *
 * This interface is designed to provide a common protocol for objects that wish be recognised as Operators. For example, Operator is implemented by class GreaterThan which is a wrapper for greater than ( > ) operator.
 *
 */
public interface Operator {

    /** true if operation succeeds, else return false.
     * @param baseBean BaseBean
     * @return true if operation succeeds, else return false.
     */    
    public boolean getResult(Object baseBean);

}





