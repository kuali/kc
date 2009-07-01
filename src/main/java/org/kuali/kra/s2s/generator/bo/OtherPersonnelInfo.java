/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.bo;

import java.util.LinkedHashMap;



public class OtherPersonnelInfo {


    private String personnelType;
    private int numberPersonnel;
    private String role;
    private CompensationInfo compensation;


    public OtherPersonnelInfo() {
    }


    /**
     * Getter for property numberPersonnel.
     * 
     * @return Value of property numberPersonnel.
     */
    public int getNumberPersonnel() {
        return numberPersonnel;
    }

    /**
     * Setter for property numberPersonnel.
     * 
     * @param numberPersonnel New value of property numberPersonnel.
     */
    public void setNumberPersonnel(int numberPersonnel) {
        this.numberPersonnel = numberPersonnel;
    }

    /**
     * Getter for property personnelType.
     * 
     * @return Value of property personnelType.
     */
    public String getPersonnelType() {
        return personnelType;
    }

    /**
     * Setter for property personnelType.
     * 
     * @param personnelType New value of property personnelType.
     */
    public void setPersonnelType(String personnelType) {
        this.personnelType = personnelType;
    }


    /**
     * Getter for property role.
     * 
     * @return Value of property role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter for property role.
     * 
     * @param role New value of property role.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Getter for property compensation.
     * 
     * @return Value of property compensation.
     */
    public CompensationInfo getCompensation() {
        return compensation;
    }

    /**
     * Setter for property compensation.
     * 
     * @param compensation New value of property compensation.
     */
    public void setCompensation(CompensationInfo compensation) {
        this.compensation = compensation;
    }


    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("personnelType", getNumberPersonnel());
        hashMap.put("numberPersonnel", getPersonnelType());
        hashMap.put("role", getRole());

        return hashMap;
    }
}
