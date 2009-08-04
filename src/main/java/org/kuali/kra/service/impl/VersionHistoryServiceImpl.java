/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;

public class VersionHistoryServiceImpl implements VersionHistoryService {
    public static final String VERSION_STATUS_FIELD = "statusForOjb";
    public static final String SEQUENCE_OWNER_CLASS_NAME_FIELD = "sequenceOwnerClassName";
    public static final String SEQUENCE_OWNER_REFERENCE_VERSION_NAME = "sequenceOwnerVersionNameValue";
    public static final String SEQUENCE_OWNER_SEQUENCE_NUMBER_FIELD = "sequenceNumber";
    
    private BusinessObjectService bos;
    
    /**
     * @see org.kuali.kra.service.VersionHistoryService#createVersionHistory(org.kuali.kra.SequenceOwner, org.kuali.kra.bo.versioning.VersionStatus, java.lang.String)
     */
    public VersionHistory createVersionHistory(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner, VersionStatus versionStatus, String userId) {
        VersionHistory versionHistory = new VersionHistory(sequenceOwner, versionStatus, userId, new Date(new java.util.Date().getTime()));
        
        List<VersionHistory> list = new ArrayList<VersionHistory>();
        if(versionStatus == VersionStatus.ACTIVE || versionStatus == VersionStatus.CANCELED) {
            resetExistingVersionsToArchived(sequenceOwner, list);
        }
        list.add(versionHistory);
        bos.save(list);
        return versionHistory;
    }

    /**
     * @see org.kuali.kra.service.VersionHistoryService#findActiveVersion(java.lang.Class, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public VersionHistory findActiveVersion(Class<? extends SequenceOwner> klass, String versionName) {
        List<VersionHistory> histories = new ArrayList<VersionHistory>(bos.findMatching(VersionHistory.class, buildFieldValueMapForActiveVersionHistory(klass, versionName)));
        
        /*
         * For some reason, in the testcase the BOS doesn't bring back just the ACTIVE record, despite the VERSION_STATUS_FIELD being included.
         * However, in the live run, this does bring back just one record. Just to be safe, I provide an alternative approach
         * 
         */
//        VersionHistory activeVersionHistory = histories.size() == 1 ? histories.get(0) : null;        
        VersionHistory activeVersionHistory = findActiveVersionHistory(histories);
        
        if(activeVersionHistory != null) {
            String versionFieldName = activeVersionHistory.getSequenceOwnerVersionNameField();
            SequenceOwner<?> owner = findSequenceOwners(klass, versionFieldName, versionName).get(activeVersionHistory.getSequenceOwnerSequenceNumber());
            activeVersionHistory.setSequenceOwner(owner);
        }
        
        return activeVersionHistory;
    }

    /**
     * @see org.kuali.kra.service.VersionHistoryService#loadVersionHistory(java.lang.Class, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<VersionHistory> loadVersionHistory(Class<? extends SequenceOwner> klass, String versionName) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(SEQUENCE_OWNER_CLASS_NAME_FIELD, klass.getName());
        fieldValues.put(SEQUENCE_OWNER_REFERENCE_VERSION_NAME, versionName);        
        List<VersionHistory> histories = new ArrayList<VersionHistory>(bos.findMatching(VersionHistory.class, fieldValues));
        if(histories.size() > 0) {
            String versionFieldName = histories.get(0).getSequenceOwnerVersionNameField();
            Map<Integer, SequenceOwner<? extends SequenceOwner<?>>> map = findSequenceOwners(klass, versionFieldName, versionName);
            for(VersionHistory vh: histories) {
                SequenceOwner owner = map.get(vh.getSequenceOwnerSequenceNumber());
                if(owner != null) {
                    vh.setSequenceOwner(owner);
                }
            }
        }
        return histories;
    }

    /**
     * @param bos
     */
    public void setBusinessObjectService(BusinessObjectService bos) {
        this.bos = bos;
    }

    /**
     * This method...
     * @param klass
     * @param versionName
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> buildFieldValueMapForActiveVersionHistory(Class<? extends SequenceOwner> klass, String versionName) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(SEQUENCE_OWNER_CLASS_NAME_FIELD, klass.getName());
        fieldValues.put(SEQUENCE_OWNER_REFERENCE_VERSION_NAME, versionName);
        fieldValues.put(VERSION_STATUS_FIELD, VersionStatus.ACTIVE.name());
        return fieldValues;
    }

    /**
     * This method...
     * @param histories
     * @return
     */
    private VersionHistory findActiveVersionHistory(List<VersionHistory> histories) {
        VersionHistory activeVersionHistory = null;
        if(histories.size() > 0) {
            if(histories.size() == 1 && histories.get(0).getStatus() == VersionStatus.ACTIVE) {
                activeVersionHistory = histories.get(0);
            } else {
                for(VersionHistory vh: histories) {
                    if(vh.getStatus() == VersionStatus.ACTIVE) {
                        activeVersionHistory = vh;
                        break;
                    }
                }
            }
        }
        return activeVersionHistory;
    }
    
    @SuppressWarnings("unchecked")
    private Map<Integer, SequenceOwner<? extends SequenceOwner<?>>> findSequenceOwners(Class klass, String versionField, String versionName) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(versionField, versionName);
        Collection<SequenceOwner<? extends SequenceOwner<?>>> c = bos.findMatching(klass, fieldValues);
        Map<Integer, SequenceOwner<? extends SequenceOwner<?>>> map = new TreeMap<Integer, SequenceOwner<? extends SequenceOwner<?>>>();
        for(SequenceOwner<?> owner: c) {
            map.put(owner.getSequenceNumber(), owner);
        }
        return map;
    }

    private void resetExistingVersionsToArchived(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner, List<VersionHistory> versionHistories) {
        List<VersionHistory> existingEntries = loadVersionHistory(sequenceOwner.getClass(), getVersionName(sequenceOwner));
        for(VersionHistory versionHistory: existingEntries) {
            versionHistory.setStatus(VersionStatus.ARCHIVED);
        }
        versionHistories.addAll(existingEntries);
    }
    
    private String getVersionName(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner) {
        return ObjectUtils.getPropertyValue(sequenceOwner, sequenceOwner.getVersionNameField()).toString();
    }
}