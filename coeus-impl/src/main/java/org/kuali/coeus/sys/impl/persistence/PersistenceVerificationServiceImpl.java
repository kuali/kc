package org.kuali.coeus.sys.impl.persistence;


import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.persistence.PersistenceVerificationService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.criteria.CountFlag;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.bo.DataObjectRelationship;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.metadata.DataObjectMetadata;
import org.kuali.rice.krad.data.provider.MetadataProvider;
import org.kuali.rice.krad.data.provider.ProviderRegistry;
import org.kuali.rice.krad.datadictionary.BusinessObjectEntry;
import org.kuali.rice.krad.datadictionary.PrimitiveAttributeDefinition;
import org.kuali.rice.krad.datadictionary.RelationshipDefinition;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import static org.kuali.coeus.sys.framework.util.CollectionUtils.*;

@Component("persistenceVerificationService")
public class PersistenceVerificationServiceImpl implements PersistenceVerificationService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("kcPersistenceStructureService")
    private KcPersistenceStructureService kcPersistenceStructureService;

    @Autowired
    @Qualifier("dataDictionaryService")
    private DataDictionaryService dataDictionaryService;

    @Autowired
    @Qualifier("providerRegistry")
    private ProviderRegistry providerRegistry;

    @Override
    public boolean verifyRelationshipsForDelete(Object bo, Collection<Class<?>> ignoredRelationships) {
        return verifyOjbRelationships(bo, ignoredRelationships) && verifyDDRelationships(bo, ignoredRelationships) && verifyKradDataRelationships(bo, ignoredRelationships);
    }

    @Override
    public boolean verifyRelationshipsForUpsert(Object bo, Collection<Class<?>> ignoredRelationships) {
        return true;
    }

    protected boolean verifyOjbRelationships(Object bo, Collection<Class<?>> ignoredRelationships) {
        final List<DataObjectRelationship> ojbRelationships = getKcPersistenceStructureService().getRelationshipsTo(bo.getClass());
        boolean success = true;
        for (DataObjectRelationship relationship : ojbRelationships) {
            if (!ignoredRelationships.contains(relationship.getParentClass())) {
                final Map<String, Object> criteria = Maps.transformEntries(relationship.getParentToChildReferences(), (key, value) -> {
                    try {
                        return PropertyUtils.getProperty(bo, value);
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                });
                if (getBusinessObjectService().countMatching(relationship.getParentClass(), criteria) > 0) {
                    getGlobalVariableService().getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.ERROR_DELETION_BLOCKED,
                            dataDictionaryService.getDataDictionary().getBusinessObjectEntry(relationship.getParentClass().getName()).getObjectLabel());
                    success = false;
                }
            }
        }
        return success;
    }

    protected boolean verifyDDRelationships(Object bo, Collection<Class<?>> ignoredRelationships) {
        final Collection<RelationshipDefinition> ddRelationships = new ArrayList<>();
        for (BusinessObjectEntry entry : getDataDictionaryService().getDataDictionary().getBusinessObjectEntries().values()) {
            ddRelationships.addAll(Collections2.filter(entry.getRelationships(), relationship -> relationship.getTargetClass().equals(bo.getClass())));
        }

        boolean success = true;
        for (RelationshipDefinition relationship : ddRelationships) {
            if (!ignoredRelationships.contains(relationship.getSourceClass())) {
                final Map<String, Object> criteria = new HashMap<>();
                for (PrimitiveAttributeDefinition attr : relationship.getPrimitiveAttributes()) {
                    try {
                        criteria.put(attr.getSourceName(), PropertyUtils.getProperty(bo, attr.getTargetName()));
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (getBusinessObjectService().countMatching(relationship.getSourceClass(), criteria) > 0) {
                    getGlobalVariableService().getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.ERROR_DELETION_BLOCKED,
                            dataDictionaryService.getDataDictionary().getBusinessObjectEntry(relationship.getSourceClass().getName()).getObjectLabel());
                    success = false;
                }
            }
        }
        return success;
    }

    protected boolean verifyKradDataRelationships(Object bo, Collection<Class<?>> ignoredRelationships) {
        final Map<Class<?>, org.kuali.rice.krad.data.metadata.DataObjectRelationship> kradDataRelationships = new HashMap<>();

        for (MetadataProvider provider : getProviderRegistry().getMetadataProviders()) {
            for (DataObjectMetadata entry : provider.provideMetadata().values()) {
                for (org.kuali.rice.krad.data.metadata.DataObjectRelationship relationship : entry.getRelationships()) {
                    if (relationship.getRelatedType().equals(bo.getClass())) {
                        kradDataRelationships.put(entry.getType(), relationship);
                    }
                }
            }
        }

        for (Map.Entry<Class<?>,org.kuali.rice.krad.data.metadata.DataObjectRelationship> relationship : kradDataRelationships.entrySet()) {
            if (!ignoredRelationships.contains(relationship.getKey())) {

                final Map<String, Object> criteria = relationship.getValue().getAttributeRelationships().stream().map(attr -> {
                    try {
                        return entry(attr.getParentAttributeName(), PropertyUtils.getProperty(bo, attr.getChildAttributeName()));
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(entriesToMap());

                if (getDataObjectService().findMatching(relationship.getKey(),
                        QueryByCriteria.Builder.andAttributes(criteria)
                                .setCountFlag(CountFlag.ONLY)
                                .build())
                        .getTotalRowCount() > 0) {
                    getGlobalVariableService().getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.ERROR_DELETION_BLOCKED,
                            dataDictionaryService.getDataDictionary().getDataObjectEntry(relationship.getKey().getName()).getObjectLabel());
                    return false;
                }
            }
        }
        return true;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public KcPersistenceStructureService getKcPersistenceStructureService() {
        return kcPersistenceStructureService;
    }

    public void setKcPersistenceStructureService(KcPersistenceStructureService kcPersistenceStructureService) {
        this.kcPersistenceStructureService = kcPersistenceStructureService;
    }

    public DataDictionaryService getDataDictionaryService() {
        return dataDictionaryService;
    }

    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    protected ProviderRegistry getProviderRegistry() {
        return providerRegistry;
    }

    public void setProviderRegistry(ProviderRegistry providerRegistry) {
        this.providerRegistry = providerRegistry;
    }
}
