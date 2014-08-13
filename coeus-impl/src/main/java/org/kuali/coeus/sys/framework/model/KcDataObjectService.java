package org.kuali.coeus.sys.framework.model;

public interface KcDataObjectService {

    void initVersionNumberForPersist(KcDataObject kcDataObject);
    void initUpdateFieldsForPersist(KcDataObject kcDataObject);
    void initObjectIdForPersist(KcDataObject kcDataObject);

    void initVersionNumberForUpdate(KcDataObject kcDataObject);
    void initUpdateFieldsForUpdate(KcDataObject kcDataObject);
    void initObjectIdForUpdate(KcDataObject kcDataObject);
}
