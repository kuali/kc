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
package org.kuali.coeus.common.impl.version.history;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.*;

@Component("versionHistoryService")
public class VersionHistoryServiceImpl implements VersionHistoryService {
	
    public static final String VERSION_STATUS_FIELD = "statusForOjb";
    public static final String SEQUENCE_OWNER_CLASS_NAME_FIELD = "sequenceOwnerClassName";
    public static final String SEQUENCE_OWNER_REFERENCE_VERSION_NAME = "sequenceOwnerVersionNameValue";
    public static final String SEQUENCE_OWNER_REFERENCE_SEQ_NUMBER = "sequenceOwnerSequenceNumber";
    public static final String SEQUENCE_OWNER_SEQUENCE_NUMBER_FIELD = "sequenceOwnerSequenceNumber";
    
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService bos;
    
    protected VersionHistory createVersionHistory(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner, VersionStatus versionStatus, String userId) {
        VersionHistory versionHistory = new VersionHistory(sequenceOwner, versionStatus, userId, new Date(new java.util.Date().getTime()));
        
        List<VersionHistory> list = new ArrayList<VersionHistory>();
        list.add(versionHistory);
        bos.save(list);
        return versionHistory;
    }
    
    public VersionHistory updateVersionHistory(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner, VersionStatus versionStatus, String userId) {
        VersionHistory currentVersion = getVersionHistory(sequenceOwner.getClass(), getVersionName(sequenceOwner), sequenceOwner.getSequenceNumber());
        if (currentVersion == null) {
            currentVersion = createVersionHistory(sequenceOwner,versionStatus, userId);
        }
        currentVersion.setStatus(versionStatus);
        
        //if newly active, clear any other active version histories
        if (versionStatus == VersionStatus.ACTIVE) {
            archiveActiveVersions(sequenceOwner.getClass(), getVersionName(sequenceOwner), currentVersion);
        }
        bos.save(currentVersion);

        return currentVersion;
    }
    protected void archiveActiveVersions(Class klass, String versionName, VersionHistory currentVersion) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(SEQUENCE_OWNER_CLASS_NAME_FIELD, klass.getName());
        fieldValues.put(SEQUENCE_OWNER_REFERENCE_VERSION_NAME, versionName);  
        fieldValues.put(VERSION_STATUS_FIELD, VersionStatus.ACTIVE.toString());
        for (VersionHistory version : bos.findMatching(VersionHistory.class, fieldValues)) {
            if (!version.equals(currentVersion)) {
                version.setStatus(VersionStatus.ARCHIVED);
                bos.save(version);
            }
        }
    }
    
    protected VersionHistory getVersionHistory(Class klass, String versionName, Integer sequenceNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(SEQUENCE_OWNER_CLASS_NAME_FIELD, klass.getName());
        fieldValues.put(SEQUENCE_OWNER_REFERENCE_VERSION_NAME, versionName);     
        fieldValues.put(SEQUENCE_OWNER_SEQUENCE_NUMBER_FIELD, sequenceNumber);
        List<VersionHistory> history = (List<VersionHistory>) getBusinessObjectService().findMatching(VersionHistory.class, fieldValues);
        if (history != null && !history.isEmpty()) {
            return history.get(0);
        } else {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public VersionHistory findActiveVersion(Class<? extends SequenceOwner> klass, String versionName) {
        List<VersionHistory> histories = new ArrayList<VersionHistory>(bos.findMatching(VersionHistory.class, buildFieldValueMapForActiveVersionHistory(klass, versionName)));       
        VersionHistory activeVersionHistory = findActiveVersionHistory(histories);        
        return activeVersionHistory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<VersionHistory> loadVersionHistory(Class<? extends SequenceOwner> klass, String versionName) {
        List<VersionHistory> histories = findVersionHistory(klass, versionName);
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
    
    protected BusinessObjectService getBusinessObjectService() {
        return bos;
    }

    @SuppressWarnings("unchecked")
    public VersionHistory findPendingVersion(Class<? extends SequenceOwner> klass, String versionName, String sequenceNumber) {
        VersionHistory pendingVersionHistory = null;
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(SEQUENCE_OWNER_CLASS_NAME_FIELD, klass.getName());
        fieldValues.put(SEQUENCE_OWNER_REFERENCE_VERSION_NAME, versionName);
        fieldValues.put(SEQUENCE_OWNER_REFERENCE_SEQ_NUMBER, sequenceNumber);
        fieldValues.put(VERSION_STATUS_FIELD, VersionStatus.PENDING.name());
        
        List<VersionHistory> histories = new ArrayList<VersionHistory>(bos.findMatching(VersionHistory.class, fieldValues));
        if(CollectionUtils.isNotEmpty(histories)) {
            pendingVersionHistory = histories.get(0);
        }
        
        return pendingVersionHistory;
    }
    
    @SuppressWarnings("unchecked")
    public VersionHistory findPendingVersion(Class<? extends SequenceOwner> klass, String versionName) {
        VersionHistory pendingVersionHistory = null;
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(SEQUENCE_OWNER_CLASS_NAME_FIELD, klass.getName());
        fieldValues.put(SEQUENCE_OWNER_REFERENCE_VERSION_NAME, versionName);
        fieldValues.put(VERSION_STATUS_FIELD, VersionStatus.PENDING.name());
        
        List<VersionHistory> histories = new ArrayList<VersionHistory>(bos.findMatching(VersionHistory.class, fieldValues));
        if(CollectionUtils.isNotEmpty(histories)) {
            pendingVersionHistory = histories.get(0);
        }
        
        return pendingVersionHistory;
    }
    
    

    @SuppressWarnings("unchecked")
    protected Map<String, Object> buildFieldValueMapForActiveVersionHistory(Class<? extends SequenceOwner> klass, String versionName) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(SEQUENCE_OWNER_CLASS_NAME_FIELD, klass.getName());
        fieldValues.put(SEQUENCE_OWNER_REFERENCE_VERSION_NAME, versionName);
        fieldValues.put(VERSION_STATUS_FIELD, VersionStatus.ACTIVE.name());
        return fieldValues;
    }


    protected VersionHistory findActiveVersionHistory(List<VersionHistory> histories) {
        VersionHistory activeVersionHistory = null;
        for(VersionHistory vh: histories) {
            if(vh.getStatus() == VersionStatus.ACTIVE) {
                activeVersionHistory = vh;
                break;
            }
        }
        return activeVersionHistory;
    }
    
    @Deprecated
    @SuppressWarnings("unchecked")
    protected Map<Integer, SequenceOwner<? extends SequenceOwner<?>>> findSequenceOwners(Class klass, String versionField, String versionName) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(versionField, versionName);
        Collection<SequenceOwner<? extends SequenceOwner<?>>> c = bos.findMatching(klass, fieldValues);
        Map<Integer, SequenceOwner<? extends SequenceOwner<?>>> map = new TreeMap<Integer, SequenceOwner<? extends SequenceOwner<?>>>();
        for(SequenceOwner<?> owner: c) {
            map.put(owner.getSequenceNumber(), owner);
        }
        return map;
    }
    
    protected String getVersionName(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner) {
        return ObjectUtils.getPropertyValue(sequenceOwner, sequenceOwner.getVersionNameField()).toString();
    }

    @Override
    public List<VersionHistory> findVersionHistory(Class<? extends SequenceOwner> klass, String versionName) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(SEQUENCE_OWNER_CLASS_NAME_FIELD, klass.getName());
        fieldValues.put(SEQUENCE_OWNER_REFERENCE_VERSION_NAME, versionName);        
        return new ArrayList<VersionHistory>(bos.findMatching(VersionHistory.class, fieldValues));
        
    }

    @Override
    public void loadSequenceOwner(Class klass,VersionHistory versionHistory) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(versionHistory.getSequenceOwnerVersionNameField(), versionHistory.getSequenceOwnerVersionNameValue());
        fieldValues.put("sequenceNumber", versionHistory.getSequenceOwnerSequenceNumber());
        Collection<SequenceOwner<? extends SequenceOwner<?>>> c = bos.findMatching(klass, fieldValues);
        
        for (SequenceOwner<? extends SequenceOwner<?>> sequenceOwner : c) {
            versionHistory.setSequenceOwner(sequenceOwner);
        }
    }
    
    public VersionHistory getActiveOrNewestVersion(Class<? extends SequenceOwner> klass, String versionName) {
        List<VersionHistory> versions = findVersionHistory(klass, versionName);
        VersionHistory history = null;
        for (VersionHistory version : versions) {
            if (history == null) {
                history = version;
            } else if (version.isActiveVersion()) {
                history = version;
            } else if (!history.isActiveVersion() 
                    && version.getSequenceOwnerSequenceNumber() > history.getSequenceOwnerSequenceNumber()
                    && version.getStatus() != VersionStatus.CANCELED) {
                history = version;
            }
        }
        return history;
    }
}
