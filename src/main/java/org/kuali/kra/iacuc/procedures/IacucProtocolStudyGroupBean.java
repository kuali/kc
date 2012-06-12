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
package org.kuali.kra.iacuc.procedures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IacucProtocolStudyGroupBean implements Serializable {


    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 112662320812476906L;

    private List<String> protocolSpeciesAndGroups;
    private List<String> protocolPersonsResponsible;

    // these fields are for header display in tag
    private Integer procedureCategoryCode; 
    private Integer procedureCode; 
    private String procedureDescription; 
    private String procedureCategory; 
    
    private List<IacucProtocolStudyGroupDetailBean> iacucProtocolStudyGroupDetailBeans;
    
    public IacucProtocolStudyGroupBean() {
        resetStudyGroupItems();
    }
    
    public void resetStudyGroupItems() {
        initializeStudyGroupItems();
        setIacucProtocolStudyGroupDetailBeans(new ArrayList<IacucProtocolStudyGroupDetailBean>());
    }
    
    public void initializeStudyGroupItems() {
        setProtocolSpeciesAndGroups(new ArrayList<String>());
        setProtocolPersonsResponsible(new ArrayList<String>());
    }
    
    public List<String> getProtocolSpeciesAndGroups() {
        return protocolSpeciesAndGroups;
    }
    
    public void setProtocolSpeciesAndGroups(List<String> protocolSpeciesAndGroups) {
        this.protocolSpeciesAndGroups = protocolSpeciesAndGroups;
    }
    
    public List<String> getProtocolPersonsResponsible() {
        return protocolPersonsResponsible;
    }
    
    public void setProtocolPersonsResponsible(List<String> protocolPersonsResponsible) {
        this.protocolPersonsResponsible = protocolPersonsResponsible;
    }

    public Integer getProcedureCategoryCode() {
        return procedureCategoryCode;
    }

    public void setProcedureCategoryCode(Integer procedureCategoryCode) {
        this.procedureCategoryCode = procedureCategoryCode;
    }

    public Integer getProcedureCode() {
        return procedureCode;
    }


    public void setProcedureCode(Integer procedureCode) {
        this.procedureCode = procedureCode;
    }


    public String getProcedureDescription() {
        return procedureDescription;
    }


    public void setProcedureDescription(String procedureDescription) {
        this.procedureDescription = procedureDescription;
    }


    public String getProcedureCategory() {
        return procedureCategory;
    }


    public void setProcedureCategory(String procedureCategory) {
        this.procedureCategory = procedureCategory;
    }


    public List<IacucProtocolStudyGroupDetailBean> getIacucProtocolStudyGroupDetailBeans() {
        return iacucProtocolStudyGroupDetailBeans;
    }


    public void setIacucProtocolStudyGroupDetailBeans(List<IacucProtocolStudyGroupDetailBean> iacucProtocolStudyGroupDetailBeans) {
        this.iacucProtocolStudyGroupDetailBeans = iacucProtocolStudyGroupDetailBeans;
    }


}
