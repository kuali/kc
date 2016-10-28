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
package org.kuali.coeus.common.api.training;

import junit.framework.Assert;
import org.junit.Test;
import org.kuali.coeus.common.api.document.impl.CommonApiServiceImpl;
import org.kuali.coeus.common.api.training.dto.PersonTrainingDto;
import org.kuali.coeus.common.framework.person.attr.PersonTraining;
import org.kuali.coeus.common.framework.shortUrl.ResourceNotFoundException;
import org.kuali.kra.bo.TrainingModule;
import org.springframework.test.annotation.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class TrainingControllerTest {

    @ExpectedException(ResourceNotFoundException.class)
    @Test
    public void testTrainingModuleFilter() {

        TrainingController trainingControllerMock = new TrainingController() {
            @Override
            protected List<TrainingModule> getTrainingModules(String moduleCode) {
                List<TrainingModule> trainingModules = new ArrayList<>();
                trainingModules.add(getTrainingModule(1, "2"));
                trainingModules.add(getTrainingModule(2, "2"));
                return trainingModules;
            }

        };


        List<PersonTraining> personTrainings = new ArrayList<>();
        personTrainings.add(getPersonTraining(1, "101"));
        personTrainings.add(getPersonTraining(2, "101"));
        personTrainings.add(getPersonTraining(3, "101"));
        personTrainings.add(getPersonTraining(4, "101"));

        List<PersonTraining> trainings = trainingControllerMock.getPersonTrainingsForModule("101", personTrainings);
        Assert.assertTrue(trainings.size() == 2);
        Assert.assertTrue(trainings.get(0).getPersonId().equalsIgnoreCase("101"));
        Assert.assertTrue(trainings.get(1).getPersonId().equalsIgnoreCase("101"));
        Assert.assertTrue(trainings.get(0).getTrainingCode().equals(1));
        Assert.assertTrue(trainings.get(1).getTrainingCode().equals(2));

        trainings = trainingControllerMock.getPersonTrainingsForModule("102", personTrainings);

    }

    @ExpectedException(ResourceNotFoundException.class)
    @Test
    public void testTrainingModuleFilterNoTrainings() {

        TrainingController trainingControllerMock = new TrainingController() {
            @Override
            protected List<TrainingModule> getTrainingModules(String moduleCode) {
                List<TrainingModule> trainingModules = new ArrayList<>();
                trainingModules.add(getTrainingModule(1, "2"));
                trainingModules.add(getTrainingModule(2, "2"));
                return trainingModules;
            }
        };

        List<PersonTraining> trainings = trainingControllerMock.getPersonTrainingsForModule("101", new ArrayList<>());
        Assert.assertTrue(trainings.size() == 0);

    }

    @ExpectedException(ResourceNotFoundException.class)
    @Test
    public void testTrainingModuleFilterNoModuleCode() {

        TrainingController trainingControllerMock = new TrainingController() {
            @Override
            protected List<TrainingModule> getTrainingModules(String moduleCode) {
                List<TrainingModule> trainingModules = new ArrayList<>();
                trainingModules.add(getTrainingModule(1, "2"));
                trainingModules.add(getTrainingModule(2, "2"));
                return trainingModules;
            }

            @Override
            protected List<PersonTraining> getPersonTrainings(String personId) {
                List<PersonTraining> personTrainings = new ArrayList<>();
                personTrainings.add(getPersonTraining(1, "101"));
                personTrainings.add(getPersonTraining(2, "101"));
                personTrainings.add(getPersonTraining(3, "101"));
                personTrainings.add(getPersonTraining(4, "101"));
                return personTrainings;
            }
        };

        trainingControllerMock.setCommonApiService(new CommonApiServiceImpl());
        List<PersonTrainingDto> trainings = trainingControllerMock.getTrainingForPerson("101", null);
        Assert.assertTrue(trainings.size() == 4);

    }

    private TrainingModule getTrainingModule(Integer trainingCode, String moduleCode) {
        TrainingModule tm1 = new TrainingModule();
        tm1.setTrainingCode(trainingCode);
        tm1.setModuleCode(moduleCode);
        return tm1;
    }

    private PersonTraining getPersonTraining(Integer trainingCode, String personId) {
        PersonTraining pt1 = new PersonTraining();
        pt1.setTrainingCode(trainingCode);
        pt1.setPersonId(personId);
        return pt1;
    }


}
