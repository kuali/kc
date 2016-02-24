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
package org.kuali.kra.award.awardhierarchy.sync.helpers;

import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

import java.lang.reflect.InvocationTargetException;

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
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws AwardSyncException
     */
    void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, 
        ClassNotFoundException, NoSuchMethodException, InstantiationException, AwardSyncException;
    
    /**
     * Builds the xmlExport maps for syncable and an optional attrName including
     * parent references.
     * @param syncable
     * @param attrName
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    AwardSyncXmlExport buildXmlExport(PersistableBusinessObject syncable, String attrName) 
        throws NoSuchFieldException, IllegalAccessException, 
        InvocationTargetException;
    
    /**
     * Create an award sync change for the syncableObject.
     * @param syncType
     * @param syncableObject
     * @param awardAttrName attribute on the award under which syncableObject can be found
     * @param boAttrName attribute on the syncableObject that should be synced by this change
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    AwardSyncChange createAwardSyncChange(AwardSyncType syncType, PersistableBusinessObject syncableObject, 
            String awardAttrName, String boAttrName)
        throws NoSuchFieldException, IllegalAccessException, 
        InvocationTargetException;
    
}
