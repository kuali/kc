/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.procedures;

/**
 * This class represents different possible enumerations of iacuc procedure details navigation
 */
public enum IacucProcedureNavigation {
    PROCEDURES("procedures", "updateProcedures"),
    PERSONNEL("personnel", "updatePersonnel"),
    LOCATION("location", "updateLocation"),
    SUMMARY("summary", "updateSummary");

    private final String displayName;
    private final String methodName;
    
    IacucProcedureNavigation(String displayName, String methodName){
        this.displayName = displayName;
        this.methodName = methodName;
    }
    
    public String getDisplayName(){
        return displayName;
    }
    
    public String getMethodName(){
        return methodName;
    }

}
