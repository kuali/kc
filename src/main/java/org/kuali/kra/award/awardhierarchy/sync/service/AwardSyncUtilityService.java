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
package org.kuali.kra.award.awardhierarchy.sync.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.awardhierarchy.sync.AwardSyncLog;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncStatus;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

public interface AwardSyncUtilityService {

    /**
     * 
     * Parse the message map and grab error messages out expanding the error key for the status
     * message.
     * @param awardStatus
     * @return
     */
    List<AwardSyncLog> getLogsFromSaveErrors(AwardSyncStatus awardStatus);
    
    /**
     * 
     * Parse the audit error map out and generate hierarchy sync logs expanding out
     * the error key for the real error.
     * @param awardStatus
     * @return
     */
    List<AwardSyncLog> getLogsFromAuditErrors(AwardSyncStatus awardStatus);
    
    /**
     * 
     * Based on org.kuali.rice.kew.routelog.web.RouteLogAction.populateRouteLogFutureRequests
     * that is used to generate the list of requests for a document enroute.
     * @param awardDocument
     * @return
     * @throws WorkflowException
     */
    List<String> buildListForFYI(AwardDocument awardDocument) throws WorkflowException;
    
    /**
     * Returns true if the object passed in has the same sync keys as keyValues.
     * @param object
     * @param keyValues
     * @return
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     */
    boolean doKeyValuesMatch(PersistableBusinessObject object, Map<String, Object> keyValues)
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, 
        InvocationTargetException, ClassNotFoundException;  
    
    /**
     * Finds a BO in the collection whose keys match those in the keyValue map.
     * @param items
     * @param keyValues
     * @return
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     */
    PersistableBusinessObject findMatchingBo(Collection<? extends PersistableBusinessObject> items, Map<String, Object> keyValues) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, InvocationTargetException, ClassNotFoundException;    
}
