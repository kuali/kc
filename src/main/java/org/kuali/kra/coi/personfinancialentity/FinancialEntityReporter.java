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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Transient;

import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;

public class FinancialEntityReporter extends KraPersistableBusinessObjectBase {
    /** TODO : not sure about this table.  
     * 1. should we combine this with coi reporter/correspondent, 
     * 2. personRoleId do we need it 
    **/

    private Long financialEntityReporterId;
    private String personId;
//    private String personName;
    private String reporterRoleId;
    private List<FinancialEntityReporterUnit> financialEntityReporterUnits; 
    private List<PersonFinIntDisclosure> personFinIntDisclosures; 
    private int selectedUnit;
    @Transient
    private transient KcPersonService kcPersonService;
    @Transient
    private transient KcPerson reporter;
    

    
    public FinancialEntityReporter() {
        this.financialEntityReporterUnits = new ArrayList<FinancialEntityReporterUnit>();
        this.personFinIntDisclosures = new ArrayList<PersonFinIntDisclosure>();
        this.reporterRoleId = "FER";
    }

    
    @Override
    protected LinkedHashMap toStringMapper() {
        // TODO Auto-generated method stub
        return null;
    }


    public String getPersonId() {
        return personId;
    }


    public void setPersonId(String personId) {
        this.personId = personId;
    }


//    public String getPersonName() {
//        return personName;
//    }
//
//
//    public void setPersonName(String personName) {
//        this.personName = personName;
//    }
//

    public String getReporterRoleId() {
        return reporterRoleId;
    }


    public void setReporterRoleId(String reporterRoleId) {
        this.reporterRoleId = reporterRoleId;
    }


    public Long getFinancialEntityReporterId() {
        return financialEntityReporterId;
    }


    public void setFinancialEntityReporterId(Long financialEntityReporterId) {
        this.financialEntityReporterId = financialEntityReporterId;
    }


    public List<FinancialEntityReporterUnit> getFinancialEntityReporterUnits() {
        return financialEntityReporterUnits;
    }


    public void setFinancialEntityReporterUnits(List<FinancialEntityReporterUnit> financialEntityReporterUnits) {
        this.financialEntityReporterUnits = financialEntityReporterUnits;
    }


    public List<PersonFinIntDisclosure> getPersonFinIntDisclosures() {
        return personFinIntDisclosures;
    }


    public void setPersonFinIntDisclosures(List<PersonFinIntDisclosure> personFinIntDisclosures) {
        this.personFinIntDisclosures = personFinIntDisclosures;
    }
    
    public KcPerson getReporter() {
        if (reporter == null && this.personId != null) {
            reporter = getKcPersonService().getKcPersonByPersonId(this.personId);
        }
        return reporter;
    }

    /**
     * Gets the KC Person Service.
     * 
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }

        return this.kcPersonService;
    }


    public int getSelectedUnit() {
        return selectedUnit;
    }


    public void setSelectedUnit(int selectedUnit) {
        this.selectedUnit = selectedUnit;
    }


}
