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
