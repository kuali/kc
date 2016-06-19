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
package org.kuali.coeus.common.framework.person.citi;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.bo.Training;

public class PersonTrainingCitiMap extends KcPersistableBusinessObjectBase {
    private Long id;
    private String groupId;
    private String stageNumber;
    private Integer trainingCode;
    private Training training;
    private PersonTrainingCitiCourse personTrainingCitiCourse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(String stageNumber) {
        this.stageNumber = stageNumber;
    }

    public Integer getTrainingCode() {
        return trainingCode;
    }

    public void setTrainingCode(Integer trainingCode) {
        this.trainingCode = trainingCode;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonTrainingCitiMap)) return false;

        PersonTrainingCitiMap that = (PersonTrainingCitiMap) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (stageNumber != null ? !stageNumber.equals(that.stageNumber) : that.stageNumber != null) return false;
        return trainingCode != null ? trainingCode.equals(that.trainingCode) : that.trainingCode == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (stageNumber != null ? stageNumber.hashCode() : 0);
        result = 31 * result + (trainingCode != null ? trainingCode.hashCode() : 0);
        return result;
    }

    public PersonTrainingCitiCourse getPersonTrainingCitiCourse() {
        return personTrainingCitiCourse;
    }

    public void setPersonTrainingCitiCourse(PersonTrainingCitiCourse personTrainingCitiCourse) {
        this.personTrainingCitiCourse = personTrainingCitiCourse;
    }
}
