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

import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.common.api.training.dto.PersonTrainingDto;
import org.kuali.coeus.common.framework.person.attr.PersonTraining;
import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.kra.bo.TrainingModule;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RequestMapping(value="/api/v1")
@Controller("trainingController")
public class TrainingController extends RestController {

    public static final String PERSON_ID = "personId";
    public static final String MODULE_CODE = "moduleCode";
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("commonApiService")
    private CommonApiService commonApiService;

    @RequestMapping(method= RequestMethod.GET, value="/person-training-modules/{personId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    List<PersonTrainingDto> getTrainingForPerson(@PathVariable String personId,
                                                 @RequestParam(value = "moduleCode", required = false) String moduleCode) {
        List<PersonTraining> personTrainings = getPersonTrainings(personId);
        if(personTrainings.size() == 0) {
            throw new ResourceNotFoundException("No trainings found for person with id " + personId);
        }
        if(moduleCode != null) {
            personTrainings = getPersonTrainingsForModule(moduleCode, personTrainings);
        }
        return convertToDto(personTrainings);
    }

    public List<PersonTraining> getPersonTrainingsForModule(String moduleCode, List<PersonTraining> personTrainings) {
        Set<Integer> trainingModuleCodes = getTrainingModules(moduleCode).stream().map(TrainingModule::getTrainingCode).collect(Collectors.toSet());
        return personTrainings.stream().filter(personTraining -> trainingModuleCodes.contains(personTraining.getTrainingCode())).collect(Collectors.toList());
    }

    protected List<PersonTrainingDto> convertToDto(List<PersonTraining> relevant) {
        return relevant.stream()
                .map(training -> commonApiService.convertObject(training, PersonTrainingDto.class))
                .collect(Collectors.toList());
    }

    public void setCommonApiService(CommonApiService commonApiService) {
        this.commonApiService = commonApiService;
    }

    protected List<PersonTraining> getPersonTrainings(String personId) {
        Map<String, Object> keys = new HashMap<>();
        keys.put(PERSON_ID, personId);
        return (List<PersonTraining>)businessObjectService.findMatching(PersonTraining.class, keys);
    }

    protected List<TrainingModule> getTrainingModules(String moduleCode) {
        Map<String, Object> keys;
        keys = new HashMap<>();
        keys.put(MODULE_CODE, moduleCode);
        return (List<TrainingModule>)businessObjectService.findMatching(TrainingModule.class, keys);
    }

}
