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
package org.kuali.coeus.award.api;

import com.codiform.moo.Moo;
import com.codiform.moo.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.award.dto.AwardBudgetExtDto;
import org.kuali.coeus.award.dto.AwardBudgetGeneralInfoDto;
import org.kuali.coeus.award.dto.AwardDto;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.rest.NotImplementedException;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value="/api/v2")
@Controller("awardController")
public class AwardController extends RestController {

    @Autowired
    @Qualifier("awardDao")
    private AwardDao awardDao;

    @Autowired
    @Qualifier("commonApiService")
    private CommonApiService commonApiService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @RequestMapping(method= RequestMethod.GET, value="/awards/{awardId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    AwardDto getAward(@PathVariable String awardId, @RequestParam(value = "includeBudgets", required = false) boolean includeBudgets) {
        Award award = getAwardDao().getAward(awardId);
        if(award == null) {
            throw new ResourceNotFoundException("Award with award id " + awardId + " not found.");
        }

        AwardDto awardDto = commonApiService.convertObject(award, AwardDto.class);
        if (!includeBudgets) {
            awardDto.setBudgets(new ArrayList<>());
        }
        return awardDto;
    }

    @RequestMapping(method= RequestMethod.GET, value="/awards", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    List<AwardDto> getAwardByCriteria(@RequestParam(value = "awardNumber", required = false) String awardNumber,
                                         @RequestParam(value = "awardHierarchy", required = false) String awardHierarchy,
                                         @RequestParam(value = "includeBudgets", required = false) boolean includeBudgets
                                         ) {
        List<AwardDto> awardDtos = new ArrayList<>();
        if(StringUtils.isBlank(awardNumber) && StringUtils.isBlank(awardHierarchy)) {
            throw new NotImplementedException("GET all awards not yet implemented in version 2.");
        }
        if(StringUtils.isNotBlank(awardNumber)) {
            List<Award> awards = getAwardDao().getAwardByAwardNumber(awardNumber);
            awardDtos = translateAwards(includeBudgets, awards);
        }
        else if(StringUtils.isNotBlank(awardHierarchy)) {
            List<Award> awards = getAwardDao().getAwardByAwardHierarchy(awardHierarchy);
            awardDtos = translateAwards(includeBudgets, awards);
        }
        return awardDtos;
    }

    protected List<AwardDto> translateAwards(boolean includeBudgets, List<Award> awards) {
        List<AwardDto> awardDtos;
        awardDtos = awards.stream().map(award -> {
            AwardDto awardDto = commonApiService.convertObject(award, AwardDto.class);
            if (!includeBudgets) {
                awardDto.setBudgets(new ArrayList<>());
            }
            return awardDto;
        }).collect(Collectors.toList());
        return awardDtos;
    }

    @RequestMapping(method= RequestMethod.GET, value="/awards/{awardId}/budgets/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    List<AwardBudgetExtDto> getBudgets( @PathVariable String awardId) {
        Award award = getAwardDao().getAward(awardId);
        if(award == null) {
            throw new ResourceNotFoundException("Award with award id " + awardId + " not found.");
        }
        List<AwardBudgetExtDto> awardBudgetExtDtos = new ArrayList<>();
        award.getBudgets().stream().forEach(budget -> {
                    AwardBudgetExtDto awardBudgetExtDto = commonApiService.convertObject(budget, AwardBudgetExtDto.class);
                    awardBudgetExtDtos.add(awardBudgetExtDto);
                }
        );
        return awardBudgetExtDtos;
    }

    @RequestMapping(method= RequestMethod.GET, value="/awards/budgets/{budgetId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    AwardBudgetExtDto getAwardBudget(@PathVariable String budgetId) {
        AwardBudgetExt budget = getAwardDao().getAwardBudget(budgetId);
        if(budget == null) {
            throw new ResourceNotFoundException("Budget with budget id " + budgetId + " not found.");
        }
        return commonApiService.convertObject(budget, AwardBudgetExtDto.class);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/awards/budgets/{budgetId}/general-info/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    void modifyAwardBudget(@RequestBody AwardBudgetGeneralInfoDto generalInfoDto, @PathVariable String budgetId) {
        AwardBudgetExt budget = getAwardDao().getAwardBudget(budgetId);
        if(budget == null) {
            throw new ResourceNotFoundException("Budget with budget id " + budgetId + " not found.");
        }
        updateDataObjectFromDto(budget, generalInfoDto);
        businessObjectService.save(budget);
    }

    protected void updateDataObjectFromDto(AwardBudgetExt existingDataObject, AwardBudgetGeneralInfoDto input) {
        Configuration mooConfig = new Configuration();
        mooConfig.setSourcePropertiesRequired(false);
        Moo moo = new Moo(mooConfig);
        moo.update(input, existingDataObject);
    }

    public AwardDao getAwardDao() {
        return awardDao;
    }

}
