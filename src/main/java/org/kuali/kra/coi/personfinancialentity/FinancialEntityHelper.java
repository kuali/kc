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
package org.kuali.kra.coi.personfinancialentity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FinancialEntityHelper implements Serializable {

    private static final long serialVersionUID = -5837128667442140384L;
    private FinancialEntityForm form;
    private PersonFinIntDisclosure newPersonFinancialEntity;
    private List<PersonFinIntDisclosure> activeFinancialEntities;
    private List<PersonFinIntDisclosure> inactiveFinancialEntities;
    private int editEntityIndex;
    
    public FinancialEntityHelper(FinancialEntityForm form) {
        newPersonFinancialEntity = new PersonFinIntDisclosure();
        newPersonFinancialEntity.setCurrentFlag(true);
        activeFinancialEntities = new ArrayList<PersonFinIntDisclosure>();
        inactiveFinancialEntities = new ArrayList<PersonFinIntDisclosure>();
        editEntityIndex = -1;
        this.form = form;
    }


    public FinancialEntityForm getForm() {
        return form;
    }

    public void setForm(FinancialEntityForm form) {
        this.form = form;
    }


    public PersonFinIntDisclosure getNewPersonFinancialEntity() {
        return newPersonFinancialEntity;
    }


    public void setNewPersonFinancialEntity(PersonFinIntDisclosure newPersonFinancialEntity) {
        this.newPersonFinancialEntity = newPersonFinancialEntity;
    }


    public int getEditEntityIndex() {
        return editEntityIndex;
    }


    public void setEditEntityIndex(int editEntityIndex) {
        this.editEntityIndex = editEntityIndex;
    }


    public List<PersonFinIntDisclosure> getActiveFinancialEntities() {
        return activeFinancialEntities;
    }


    public void setActiveFinancialEntities(List<PersonFinIntDisclosure> activeFinancialEntities) {
        this.activeFinancialEntities = activeFinancialEntities;
    }


    public List<PersonFinIntDisclosure> getInactiveFinancialEntities() {
        return inactiveFinancialEntities;
    }


    public void setInactiveFinancialEntities(List<PersonFinIntDisclosure> inactiveFinancialEntities) {
        this.inactiveFinancialEntities = inactiveFinancialEntities;
    }

}
