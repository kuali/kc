/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.bo;


import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class TrainingModule extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Integer trainingCode;
    private String moduleCode;
    private CoeusModule coeusModule;
    private Training training;
    private Integer id;


    public Integer getTrainingCode() {
        return trainingCode;
    }

    public void setTrainingCode(Integer trainingCode) {
        this.trainingCode = trainingCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public CoeusModule getCoeusModule() {
        return coeusModule;
    }

    public void setCoeusModule(CoeusModule coeusModule) {
        this.coeusModule = coeusModule;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
