package org.kuali.kra.service.impl.adapters;

import java.util.List;

import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Adapter for VersionHistoryService
 */
public class VersionHistoryServiceAdapter implements VersionHistoryService {
    BusinessObjectService bos;

    public VersionHistory findActiveVersion(Class<? extends SequenceOwner> klass, String versionName) {
        return null;
    }

    public List<VersionHistory> loadVersionHistory(Class<? extends SequenceOwner> klass, String versionName) {
        return null;
    }

    public void setBusinessObjectService(BusinessObjectService bos) {
        this.bos = bos;
    }

    public BusinessObjectService getBusinessObjectService() { 
        return bos;
    }

    public VersionHistory findPendingVersion(Class<? extends SequenceOwner> klass, String versionName, String sequenceNumber) {
        return null;
    }

    public VersionHistory findPendingVersion(Class<? extends SequenceOwner> klass, String versionName) {
        return null;
    }

    @Override
    public List<VersionHistory> findVersionHistory(Class<? extends SequenceOwner> klass, String versionName) {
        return null;
    }

    @Override
    public void loadSequenceOwner(Class<? extends SequenceOwner> klass, VersionHistory versionHistory) {
    }   
    
    @Override
    public VersionHistory updateVersionHistory(SequenceOwner<? extends SequenceOwner<?>> sequenceOwner,
            VersionStatus versionStatus, String userId) {
        return null;
    }

    @Override
    public VersionHistory getActiveOrNewestVersion(Class<? extends SequenceOwner> klass, String versionName) {
        // TODO Auto-generated method stub
        return null;
    }   
    
    
}
