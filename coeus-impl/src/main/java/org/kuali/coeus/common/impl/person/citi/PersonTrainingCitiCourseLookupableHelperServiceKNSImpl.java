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
package org.kuali.coeus.common.impl.person.citi;

import org.kuali.coeus.common.framework.person.citi.PersonTrainingCitiCourse;
import org.kuali.coeus.common.framework.person.citi.PersonTrainingCitiRecord;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("personTrainingCitiCourseLookupableHelperServiceKNS")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Lazy
public class PersonTrainingCitiCourseLookupableHelperServiceKNSImpl extends KualiLookupableHelperServiceImpl {

    @Override
    public List<PersonTrainingCitiCourse> getSearchResults(Map<String, String> fieldValues) {
        this.businessObjectClass = PersonTrainingCitiRecord.class;
        @SuppressWarnings("unchecked")
        final List<PersonTrainingCitiRecord> results = (List<PersonTrainingCitiRecord>) super.getSearchResults(fieldValues);
        this.businessObjectClass = PersonTrainingCitiCourse.class;
        return results.stream()
                .map(rec ->  new PersonTrainingCitiCourse(rec.getGroupId(), rec.getGroup(), rec.getCurriculumNumber(),
                rec.getCurriculum(), rec.getStageNumber(), rec.getStage()))
                .filter(CollectionUtils.distinctByKey(PersonTrainingCitiCourse::getTitle))
                .collect(Collectors.toList());
    }
}
