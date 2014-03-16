/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.summary;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.protocol.summary.ProtocolSummary;

import java.util.ArrayList;
import java.util.List;

public class IacucProtocolSummary extends ProtocolSummary {

    private static final long serialVersionUID = -8169452881498847762L;

    private String projectType;
    private String layStmt1;
    private String layStmt2;
    
    private boolean projectTypeChanged;
    private boolean layStmt1Changed = false;
    private boolean layStmt2Changed = false;

    private IacucThreeRsSummary threeRsInfo;
    private List<IacucProtocolSpeciesSummary> speciesSummaries = new ArrayList<IacucProtocolSpeciesSummary>();
    private List<IacucProtocolExceptionSummary> exceptionSummaries = new ArrayList<IacucProtocolExceptionSummary>();
    private String procedureOverviewSummary;
    private boolean procedureOverviewSummaryChanged = false;
    private List<IacucProcedureSummary> procedureSummaries = new ArrayList<IacucProcedureSummary>();
    
    public IacucProtocolSummary() {
        super();
    }
    
    public void compare(IacucProtocolSummary other) {
        super.compare(other);
        projectTypeChanged = !StringUtils.equals(projectType, other.projectType);
        layStmt1Changed = !StringUtils.equals(layStmt1, other.layStmt1);
        layStmt2Changed = !StringUtils.equals(layStmt2, other.layStmt2);
        compareThreeRs(other);
        compareSpecies(other);
        compareExceptions(other);
        compareProcedures(other);
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getLayStmt1() {
        return layStmt1;
    }

    public void setLayStmt1(String layStmt1) {
        this.layStmt1 = layStmt1;
    }

    public String getLayStmt2() {
        return layStmt2;
    }

    public void setLayStmt2(String layStmt2) {
        this.layStmt2 = layStmt2;
    }

    public boolean isProjectTypeChanged() {
        return projectTypeChanged;
    }

    public boolean isLayStmt1Changed() {
        return layStmt1Changed;
    }
    
    public boolean isLayStmt2Changed() {
        return layStmt2Changed;
    }

    public IacucThreeRsSummary getThreeRsInfo() {
        return threeRsInfo;
    }
    
    public void setThreeRsInfo(IacucThreeRsSummary threeRsInfo) {
        this.threeRsInfo = threeRsInfo;
    }    
    
    public List<IacucProcedureSummary> getProcedureSummaries() {
        return procedureSummaries;
    }

    public void setProcedureSummaries(List<IacucProcedureSummary> procedureSummaries) {
        this.procedureSummaries = procedureSummaries;
    }

    public void setProjectTypeChanged(boolean projectTypeChanged) {
        this.projectTypeChanged = projectTypeChanged;
    }

    public void setLayStmt1Changed(boolean layStmt1Changed) {
        this.layStmt1Changed = layStmt1Changed;
    }

    public void setLayStmt2Changed(boolean layStmt2Changed) {
        this.layStmt2Changed = layStmt2Changed;
    }

    public void setSpeciesSummaries(List<IacucProtocolSpeciesSummary> speciesSummaries) {
        this.speciesSummaries = speciesSummaries;
    }

    public void setExceptionSummaries(List<IacucProtocolExceptionSummary> exceptionSummaries) {
        this.exceptionSummaries = exceptionSummaries;
    }

    private void compareThreeRs(IacucProtocolSummary other) {
        threeRsInfo.compare(other.getThreeRsInfo());
    }
    
    private void compareSpecies(IacucProtocolSummary other) {
        for (IacucProtocolSpeciesSummary mySummary : speciesSummaries) {
            mySummary.compare(other);
        }
    }

    public IacucProtocolSpeciesSummary findSpeciesSummary(Integer speciesId) {
        for (IacucProtocolSpeciesSummary species : speciesSummaries) {
            if (species.getSpeciesId().equals(speciesId)) {
                return species;
            }
        }
        return null;
    }

    public List<IacucProtocolSpeciesSummary> getSpeciesSummaries() {
        return speciesSummaries;
    }

    public List<IacucProtocolExceptionSummary> getExceptionSummaries() {
        return exceptionSummaries;
    }

    private void compareExceptions(IacucProtocolSummary other) {
        for (IacucProtocolExceptionSummary mySummary : exceptionSummaries) {
            mySummary.compare(other);
        }
    }

    private void compareProcedures(IacucProtocolSummary other) {
        procedureOverviewSummaryChanged = !StringUtils.equals(procedureOverviewSummary, other.procedureOverviewSummary);
        for (IacucProcedureSummary mySummary : procedureSummaries) {
            mySummary.compare(other);
        }
    }
    
    public IacucProtocolExceptionSummary findExceptionSummary(Integer exceptionId) {
        for (IacucProtocolExceptionSummary exception : exceptionSummaries) {
            if (exception.getIacucProtocolExceptionId().equals(exceptionId)) {
                return exception;
            }
        }
        return null;
    }

    public String getProcedureOverviewSummary() {
        return procedureOverviewSummary;
    }

    public void setProcedureOverviewSummary(String procedureOverviewSummary) {
        this.procedureOverviewSummary = procedureOverviewSummary;
    }

    public boolean isProcedureOverviewSummaryChanged() {
        return procedureOverviewSummaryChanged;
    }

    public void setProcedureOverviewSummaryChanged(boolean procedureOverviewSummaryChanged) {
        this.procedureOverviewSummaryChanged = procedureOverviewSummaryChanged;
    }

    public IacucProcedureSummary findProcedureSummary(Integer procedureCode) {
        for (IacucProcedureSummary procedure : procedureSummaries) {
            if (procedure.getProcedureCode().equals(procedureCode)) {
                return procedure;
            }
        }
        return null;
    }

}
