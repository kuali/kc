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
package org.kuali.kra.coi.disclosure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.personfinancialentity.FinEntityDataMatrixBean;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityService;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class DisclosureHelper implements Serializable {

    private CoiDisclosureForm form;
    private DisclosurePersonUnit newDisclosurePersonUnit;
    private List<DisclosurePersonUnit> deletedUnits;
    private List<FinEntityDataMatrixBean> editRelationDetails;
    private List<FinEntityDataMatrixBean> newRelationDetails;
    private boolean canViewDisclosureFeHistory;
    private boolean canEditDisclosureFinancialEntity;

    public DisclosureHelper(CoiDisclosureForm form) {
        this.form = form;
        setNewDisclosurePersonUnit(new DisclosurePersonUnit());
        deletedUnits = new ArrayList<DisclosurePersonUnit>(); 
        newRelationDetails = getFinancialEntityService().getFinancialEntityDataMatrix();
        editRelationDetails = new ArrayList<FinEntityDataMatrixBean>(); 
        canViewDisclosureFeHistory = hasCanViewDisclosureFeHistoryPermission();
        canEditDisclosureFinancialEntity = hasCanEditDisclosureFinancialEntityPermission();
   }

    public CoiDisclosureForm getForm() {
        return form;
    }

    public void setForm(CoiDisclosureForm form) {
        this.form = form;
    }

    public DisclosurePersonUnit getNewDisclosurePersonUnit() {
        return newDisclosurePersonUnit;
    }

    public void setNewDisclosurePersonUnit(DisclosurePersonUnit newDisclosurePersonUnit) {
        this.newDisclosurePersonUnit = newDisclosurePersonUnit;
        DisclosurePerson disclosurePerson = null;
        if (CollectionUtils.isEmpty(((CoiDisclosureDocument)this.form.getDocument()).getCoiDisclosure().getDisclosurePersons())) {
            disclosurePerson = ((CoiDisclosureDocument)this.form.getDocument()).getCoiDisclosure().getDisclosureReporter();
        } else {
            disclosurePerson = ((CoiDisclosureDocument)this.form.getDocument()).getCoiDisclosure().getDisclosurePersons().get(0);
        }
        this.newDisclosurePersonUnit.setDisclosurePersonId(disclosurePerson.getDisclosurePersonId());
        this.newDisclosurePersonUnit.setDisclosurePerson(disclosurePerson);
        this.newDisclosurePersonUnit.setPersonId(disclosurePerson.getPersonId());
    }

    public List<DisclosurePersonUnit> getDeletedUnits() {
        return deletedUnits;
    }

    public void setDeletedUnits(List<DisclosurePersonUnit> deletedUnits) {
        this.deletedUnits = deletedUnits;
    }

    public List<FinEntityDataMatrixBean> getEditRelationDetails() {
        return editRelationDetails;
    }

    public void setEditRelationDetails(List<FinEntityDataMatrixBean> editRelationDetails) {
        this.editRelationDetails = editRelationDetails;
    }

    public List<FinEntityDataMatrixBean> getNewRelationDetails() {
        return newRelationDetails;
    }

    public void setNewRelationDetails(List<FinEntityDataMatrixBean> newRelationDetails) {
        this.newRelationDetails = newRelationDetails;
    }

    private FinancialEntityService getFinancialEntityService() {
        return KraServiceLocator.getService(FinancialEntityService.class);
    }

    public boolean isCanViewDisclosureFeHistory() {
        return canViewDisclosureFeHistory;
    }

    public void setCanViewDisclosureFeHistory(boolean canViewDisclosureFeHistory) {
        this.canViewDisclosureFeHistory = canViewDisclosureFeHistory;
    }

    private boolean hasCanViewDisclosureFeHistoryPermission() {
        // TODO : to br implemented after coi task authorizer are set
        // for now, just return true
        return true;
    }

    public boolean isCanEditDisclosureFinancialEntity() {
        return canEditDisclosureFinancialEntity;
    }

    public void setCanEditDisclosureFinancialEntity(boolean canEditDisclosureFinancialEntity) {
        this.canEditDisclosureFinancialEntity = canEditDisclosureFinancialEntity;
    }
    private boolean hasCanEditDisclosureFinancialEntityPermission() {
        // TODO : to br implemented after coi task authorizer are set
        // for now, just return true
        return true;
    }
    
}
