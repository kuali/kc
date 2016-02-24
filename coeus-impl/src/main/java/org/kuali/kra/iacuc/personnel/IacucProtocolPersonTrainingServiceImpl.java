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
package org.kuali.kra.iacuc.personnel;

import org.kuali.kra.iacuc.IacucPersonTraining;
import org.kuali.kra.protocol.personnel.ProtocolPersonTrainingServiceImplBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucProtocolPersonTrainingServiceImpl extends ProtocolPersonTrainingServiceImplBase implements IacucProtocolPersonTrainingService{
    private static final String PERSON_ID_FIELD = "personId";

    public List<IacucPersonTraining> getIacucPersonTrainingDetails(String personId) {
        Map<String, Object> matchingKeys = new HashMap<String, Object>();
        matchingKeys.put(PERSON_ID_FIELD, personId);
        return (List<IacucPersonTraining>) getBusinessObjectService().findMatching(IacucPersonTraining.class, matchingKeys);
    }

}
