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
package org.kuali.kra.committee.bo;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is Template implementation of BoAttributeTestBase<T> class, to test ScheduleStatus BO for toStringMapper.
 */
public class ScheduleStatusTest extends BoAttributeTestBase<ScheduleStatus> {

    private static final int ATTRIBUTE_COUNT = 2;
    
    private static final String FIELD_SCHEDULESTATUSCODE = "scheduleStatusCode";
    private static final int FIELD_SCHEDULESTATUSCODE_VALUE = 1;

    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_DESCRIPTION_VALUE = "something for test";

    /**
     * Constructs a ScheduleStatusTest.java.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public ScheduleStatusTest() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        super(ATTRIBUTE_COUNT, new ScheduleStatus());
    }

    /**
     * @see org.kuali.kra.committee.bo.BoAttributeTestBase#getFieldMap()
     */
    @Override
    protected Map<String, Object> getFieldMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(FIELD_SCHEDULESTATUSCODE, FIELD_SCHEDULESTATUSCODE_VALUE);
        map.put(FIELD_DESCRIPTION, FIELD_DESCRIPTION_VALUE);
        return map;
    }

}
