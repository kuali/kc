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


public final class DataType {
    
    public static final String INT = "primitiveInt";
    public static final String CHAR = "primitiveChar";
    public static final String FLOAT = "primitiveFloat";
    public static final String DOUBLE = "primitiveDouble";
    public static final String BOOLEAN = "primitiveBoolean";

    /** Creates a new instance of DataType */
    private DataType() {
    }
    
    public static Class getClass(String type) {
        try{
            return DataType.class.getDeclaredField(type).getType();
        }catch (NoSuchFieldException noSuchFieldException) {
            noSuchFieldException.printStackTrace();
            return null;
        }
    }
    
}
