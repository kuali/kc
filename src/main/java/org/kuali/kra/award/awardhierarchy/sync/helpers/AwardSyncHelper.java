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
package org.kuali.kra.award.awardhierarchy.sync.helpers;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

/**
 * Award Sync Helper interface. Describes the interface for classes
 * that will be used to create and apply award sync changes.
 */
public interface AwardSyncHelper {

    /**
     * Applies the associated change to award.
     * @param award
     * @param change
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws AwardSyncException
     */
    void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, InvocationTargetException, 
        ClassNotFoundException, NoSuchMethodException, InstantiationException, AwardSyncException;
    
    /**
     * Builds the xmlExport maps for syncable and an optional attrName including
     * parent references.
     * @param syncable
     * @param attrName
     * @return
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    AwardSyncXmlExport buildXmlExport(PersistableBusinessObject syncable, String attrName) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, 
        InvocationTargetException;
    
    /**
     * Create an award sync change for the syncableObject.
     * @param syncType
     * @param syncableObject
     * @param awardAttrName attribute on the award under which syncableObject can be found
     * @param boAttrName attribute on the syncableObject that should be synced by this change
     * @return
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    AwardSyncChange createAwardSyncChange(AwardSyncType syncType, PersistableBusinessObject syncableObject, 
            String awardAttrName, String boAttrName)
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, 
        InvocationTargetException;
    
}
