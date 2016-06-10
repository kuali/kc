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

import org.apache.commons.collections4.MapUtils;
import org.kuali.coeus.common.framework.person.citi.PersonTrainingCitiCourse;
import org.kuali.coeus.common.framework.person.citi.PersonTrainingCitiRecord;
import org.kuali.rice.kns.inquiry.KualiInquirableImpl;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;


public class PersonTrainingCitiCourseInquirableImpl extends KualiInquirableImpl {

    @Override
    public BusinessObject getBusinessObject(@SuppressWarnings("unchecked") Map fieldValues) {
        if (MapUtils.isEmpty(fieldValues)) {
            return null;
        }

        final Collection<PersonTrainingCitiRecord> records = getLookupService().findCollectionBySearchHelper(PersonTrainingCitiRecord.class, fieldValues, true);
        final Optional<PersonTrainingCitiRecord> record = records.stream().findFirst();
        if (record.isPresent()) {
            final PersonTrainingCitiRecord firstRecord = record.get();
            return new PersonTrainingCitiCourse(firstRecord.getGroupId(), firstRecord.getGroup(), firstRecord.getCurriculumNumber(),
                    firstRecord.getCurriculum(), firstRecord.getStageNumber(), firstRecord.getStage());
        }
        return null;
    }

}
