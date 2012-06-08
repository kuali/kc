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
package org.kuali.kra.iacuc.summary;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.protocol.summary.ProtocolSummary;

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
        compareProtocols(other);
        compareExceptions(other);
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

    private void compareProtocols(IacucProtocolSummary other) {
//TODO        for (IacucProtocolSpeciesSummary mySummary : speciesSummaries) {
    }

    private void compareExceptions(IacucProtocolSummary other) {
        for (IacucProtocolExceptionSummary mySummary : exceptionSummaries) {
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


}
