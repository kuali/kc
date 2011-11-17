/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.awardhierarchy;

import java.io.Serializable;

public class AwardHierarchyTempObject implements Serializable{
    private String awardNumber;
    private String awardNumber1;
    private String awardNumber2;
    private String selectBox1;
    private String selectBox2;
    private String newChildPanelTargetAward;
    private String copyAwardPanelTargetAward;
    private Boolean copyDescendants;
    private String copyAwardRadio;
    private String createNewChildRadio;
    
    public AwardHierarchyTempObject(){
        awardNumber1 = "";
        awardNumber2 = "";
        selectBox1 = "";
        selectBox2 = "";
        newChildPanelTargetAward = "";
        copyAwardPanelTargetAward = "";
    }


    /**
     * Gets the selectBox1 attribute. 
     * @return Returns the selectBox1.
     */
    public String getSelectBox1() {
        return selectBox1;
    }
    /**
     * Sets the selectBox1 attribute value.
     * @param selectBox1 The selectBox1 to set.
     */
    public void setSelectBox1(String selectBox1) {
        this.selectBox1 = selectBox1;
    }


    /**
     * Gets the awardNumber1 attribute. 
     * @return Returns the awardNumber1.
     */
    public String getAwardNumber1() {
        return awardNumber1;
    }


    /**
     * Sets the awardNumber1 attribute value.
     * @param awardNumber1 The awardNumber1 to set.
     */
    public void setAwardNumber1(String awardNumber1) {
        this.awardNumber1 = awardNumber1;
    }


    /**
     * Gets the awardNumber2 attribute. 
     * @return Returns the awardNumber2.
     */
    public String getAwardNumber2() {
        return awardNumber2;
    }


    /**
     * Sets the awardNumber2 attribute value.
     * @param awardNumber2 The awardNumber2 to set.
     */
    public void setAwardNumber2(String awardNumber2) {
        this.awardNumber2 = awardNumber2;
    }


    /**
     * Gets the newChildPanelTargetAward attribute. 
     * @return Returns the newChildPanelTargetAward.
     */
    public String getNewChildPanelTargetAward() {
        return newChildPanelTargetAward;
    }


    /**
     * Sets the newChildPanelTargetAward attribute value.
     * @param newChildPanelTargetAward The newChildPanelTargetAward to set.
     */
    public void setNewChildPanelTargetAward(String newChildPanelTargetAward) {
        this.newChildPanelTargetAward = newChildPanelTargetAward;
    }


    /**
     * Gets the selectBox2 attribute. 
     * @return Returns the selectBox2.
     */
    public String getSelectBox2() {
        return selectBox2;
    }


    /**
     * Sets the selectBox2 attribute value.
     * @param selectBox2 The selectBox2 to set.
     */
    public void setSelectBox2(String selectBox2) {
        this.selectBox2 = selectBox2;
    }


    /**
     * Gets the copyAwardPanelTargetAward attribute. 
     * @return Returns the copyAwardPanelTargetAward.
     */
    public String getCopyAwardPanelTargetAward() {
        return copyAwardPanelTargetAward;
    }


    /**
     * Sets the copyAwardPanelTargetAward attribute value.
     * @param copyAwardPanelTargetAward The copyAwardPanelTargetAward to set.
     */
    public void setCopyAwardPanelTargetAward(String copyAwardPanelTargetAward) {
        this.copyAwardPanelTargetAward = copyAwardPanelTargetAward;
    }


    /**
     * Gets the copyDescendants attribute. 
     * @return Returns the copyDescendants.
     */
    public Boolean getCopyDescendants() {
        return copyDescendants;
    }


    /**
     * Sets the copyDescendants attribute value.
     * @param copyDescendants The copyDescendants to set.
     */
    public void setCopyDescendants(Boolean copyDescendants) {
        this.copyDescendants = copyDescendants;
    }


    /**
     * Gets the copyAwardRadio attribute. 
     * @return Returns the copyAwardRadio.
     */
    public String getCopyAwardRadio() {
        return copyAwardRadio;
    }


    /**
     * Sets the copyAwardRadio attribute value.
     * @param copyAwardRadio The copyAwardRadio to set.
     */
    public void setCopyAwardRadio(String copyAwardRadio) {
        this.copyAwardRadio = copyAwardRadio;
    }


    /**
     * Gets the createNewChildRadio attribute. 
     * @return Returns the createNewChildRadio.
     */
    public String getCreateNewChildRadio() {
        return createNewChildRadio;
    }


    /**
     * Sets the createNewChildRadio attribute value.
     * @param createNewChildRadio The createNewChildRadio to set.
     */
    public void setCreateNewChildRadio(String createNewChildRadio) {
        this.createNewChildRadio = createNewChildRadio;
    }    

    public String getAwardNumber() {
        return awardNumber;
    }
    
    
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }
}

