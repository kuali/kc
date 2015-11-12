/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
import org.kuali.rice.core.api.util.RiceKeyConstants;
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
        if (bo == null) {
            throw new IllegalArgumentException("bo cannot be null");
        }

        if (ignoredRelationships == null) {
            throw new IllegalArgumentException("ignoredRelationships cannot be null");
        }

        return verifyOjbRelationshipsForDelete(bo, ignoredRelationships) && verifyDDRelationshipsForDelete(bo, ignoredRelationships) && verifyKradDataRelationshipsForDelete(bo, ignoredRelationships);
    }

    @Override
    public boolean verifyRelationshipsForUpdate(Object bo, Collection<Class<?>> ignoredRelationships) {
        return verifyRelationshipsForUpsert(bo, ignoredRelationships);
    }

    @Override
    public boolean verifyRelationshipsForInsert(Object bo, Collection<Class<?>> ignoredRelationships) {
        return verifyRelationshipsForUpsert(bo, ignoredRelationships);
    }

    protected boolean verifyRelationshipsForUpsert(Object bo, Collection<Class<?>> ignoredRelationships) {
        if (bo == null) {
            throw new IllegalArgumentException("bo cannot be null");
        }

        if (ignoredRelationships == null) {
            throw new IllegalArgumentException("ignoredRelationships cannot be null");
        }

        return verifyOjbRelationshipsForUpsert(bo, ignoredRelationships) && verifyDDRelationshipsForUpsert(bo, ignoredRelationships) && verifyKradDataRelationshipsForUpsert(bo, ignoredRelationships);

    }

    protected boolean verifyOjbRelationshipsForUpsert(Object bo, Collection<Class<?>> ignoredRelationships) {

        boolean success = true;
        for (String field : ((List<String>) getKcPersistenceStructureService().listFieldNames(bo.getClass()))) {
             final Map<String, org.kuali.rice.krad.bo.DataObjectRelationship> relationships = getKcPersistenceStructureService().getRelationshipMetadata(bo.getClass(), field);
             if (relationships != null) {
                 for (Map.Entry<String, org.kuali.rice.krad.bo.DataObjectRelationship> entry : relationships.entrySet()) {
                     if (!ignoredRelationships.contains(entry.getValue().getRelatedClass())) {
                         final Map<String, Object> criteria = new HashMap<>();
                         criteria.put(entry.getValue().getParentToChildReferences().get(field), getProperty(bo, field));
                         if (getBusinessObjectService().countMatching(entry.getValue().getRelatedClass(), criteria) == 0) {
                             getGlobalVariableService().getMessageMap().putError(entry.getValue().getParentAttributeName(), RiceKeyConstants.ERROR_EXISTENCE,
                                     dataDictionaryService.getDataDictionary().getBusinessObjectEntry(entry.getValue().getRelatedClass().getName()).getObjectLabel());
                             success = false;
                         }
                     }
                 }
             }
        }
        return success;
    }

    protected boolean verifyDDRelationshipsForUpsert(Object bo, Collection<Class<?>> ignoredRelationships) {
        final Collection<RelationshipDefinition> ddRelationships = getDataDictionaryService().getDataDictionary().getBusinessObjectEntry(bo.getClass().getName()).getRelationships();

        boolean success = true;
        for (RelationshipDefinition relationship : ddRelationships) {
            if (!ignoredRelationships.contains(relationship.getTargetClass())) {
                final Map<String, Object> criteria = new HashMap<>();
                for (PrimitiveAttributeDefinition attr : relationship.getPrimitiveAttributes()) {
                    criteria.put(attr.getTargetName(), getProperty(bo, attr.getSourceName()));
                }

                if (!criteria.values().stream().anyMatch(Objects::isNull) && getBusinessObjectService().countMatching(relationship.getTargetClass(), criteria) == 0) {

                    for (PrimitiveAttributeDefinition attr : relationship.getPrimitiveAttributes()) {
                        getGlobalVariableService().getMessageMap().putError(attr.getSourceName(), RiceKeyConstants.ERROR_EXISTENCE,
                                dataDictionaryService.getDataDictionary().getBusinessObjectEntry(relationship.getTargetClass().getName()).getObjectLabel());
                    }
                    success = false;
                }
            }
        }
        return success;
    }

    protected boolean verifyKradDataRelationshipsForUpsert(Object bo, Collection<Class<?>> ignoredRelationships) {

        final Collection<org.kuali.rice.krad.data.metadata.DataObjectRelationship> kradDataRelationships = new ArrayList<>();

        for (MetadataProvider provider : getProviderRegistry().getMetadataProviders()) {
            kradDataRelationships.addAll(provider.getMetadataForType(bo.getClass()).getRelationships());
        }
        boolean success = true;
        for (org.kuali.rice.krad.data.metadata.DataObjectRelationship relationship : kradDataRelationships) {
            if (!ignoredRelationships.contains(relationship.getRelatedType())) {

                final Map<String, Object> criteria = relationship.getAttributeRelationships().stream()
                        .map(attr -> entry(attr.getChildAttributeName(), getProperty(bo, attr.getParentAttributeName())))
                        .collect(entriesToMap());

                if (getDataObjectService().findMatching(relationship.getRelatedType(),
                        QueryByCriteria.Builder.andAttributes(criteria)
                                .setCountFlag(CountFlag.ONLY)
                                .build())
                        .getTotalRowCount() == 0) {

                    for (org.kuali.rice.krad.data.metadata.DataObjectAttributeRelationship rel : relationship.getAttributeRelationships()) {
                        getGlobalVariableService().getMessageMap().putError(rel.getParentAttributeName(), KeyConstants.ERROR_DELETION_BLOCKED,
                                dataDictionaryService.getDataDictionary().getDataObjectEntry(relationship.getRelatedType().getName()).getObjectLabel());
                    }

                    success = false;
                }
            }
        }
        return success;
    }

    protected boolean verifyOjbRelationshipsForDelete(Object bo, Collection<Class<?>> ignoredRelationships) {
        final List<DataObjectRelationship> ojbRelationships = getKcPersistenceStructureService().getRelationshipsTo(bo.getClass());
        boolean success = true;
        for (DataObjectRelationship relationship : ojbRelationships) {
            if (!ignoredRelationships.contains(relationship.getParentClass())) {
                final Map<String, Object> criteria = Maps.transformEntries(relationship.getParentToChildReferences(), (key, value) -> getProperty(bo, value));
                if (getBusinessObjectService().countMatching(relationship.getParentClass(), criteria) > 0) {
                    getGlobalVariableService().getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.ERROR_DELETION_BLOCKED,
                            dataDictionaryService.getDataDictionary().getBusinessObjectEntry(relationship.getParentClass().getName()).getObjectLabel());
                    success = false;
                }
            }
        }
        return success;
    }

    protected boolean verifyDDRelationshipsForDelete(Object bo, Collection<Class<?>> ignoredRelationships) {
        final Collection<RelationshipDefinition> ddRelationships = new ArrayList<>();
        for (BusinessObjectEntry entry : getDataDictionaryService().getDataDictionary().getBusinessObjectEntries().values()) {
            ddRelationships.addAll(Collections2.filter(entry.getRelationships(), relationship -> relationship.getTargetClass().equals(bo.getClass())));
        }

        boolean success = true;
        for (RelationshipDefinition relationship : ddRelationships) {
            if (!ignoredRelationships.contains(relationship.getSourceClass())) {
                final Map<String, Object> criteria = new HashMap<>();
                for (PrimitiveAttributeDefinition attr : relationship.getPrimitiveAttributes()) {
                        criteria.put(attr.getSourceName(), getProperty(bo, attr.getTargetName()));
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

    protected boolean verifyKradDataRelationshipsForDelete(Object bo, Collection<Class<?>> ignoredRelationships) {
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

        boolean success = true;
        for (Map.Entry<Class<?>,org.kuali.rice.krad.data.metadata.DataObjectRelationship> relationship : kradDataRelationships.entrySet()) {
            if (!ignoredRelationships.contains(relationship.getKey())) {

                final Map<String, Object> criteria = relationship.getValue().getAttributeRelationships().stream()
                        .map(attr -> entry(attr.getParentAttributeName(), getProperty(bo, attr.getChildAttributeName())))
                        .collect(entriesToMap());

                if (getDataObjectService().findMatching(relationship.getKey(),
                        QueryByCriteria.Builder.andAttributes(criteria)
                                .setCountFlag(CountFlag.ONLY)
                                .build())
                        .getTotalRowCount() > 0) {
                    getGlobalVariableService().getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.ERROR_DELETION_BLOCKED,
                            dataDictionaryService.getDataDictionary().getDataObjectEntry(relationship.getKey().getName()).getObjectLabel());
                    success = false;
                }
            }
        }
        return success;
    }

    private Object getProperty(Object o, String prop) {
        try {
            return PropertyUtils.getProperty(o, prop);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
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
