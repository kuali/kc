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
package org.kuali.coeus.sys.impl.persistence;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.metadata.ClassNotPersistenceCapableException;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.persistence.PersistenceVerificationService;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.criteria.CountFlag;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.bo.DataObjectRelationship;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.metadata.MetadataCommon;
import org.kuali.rice.krad.data.provider.ProviderRegistry;
import org.kuali.rice.krad.datadictionary.BusinessObjectEntry;
import org.kuali.rice.krad.datadictionary.PrimitiveAttributeDefinition;
import org.kuali.rice.krad.datadictionary.RelationshipDefinition;
import org.kuali.rice.krad.exception.ClassNotPersistableException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import static org.kuali.coeus.sys.framework.util.CollectionUtils.entry;
import static org.kuali.coeus.sys.framework.util.CollectionUtils.nullSafeEntriesToMap;

@Component("persistenceVerificationService")
public class PersistenceVerificationServiceImpl implements PersistenceVerificationService {

    private static final Log LOG = LogFactory.getLog(PersistenceVerificationServiceImpl.class);

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

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
    public MessageMap verifyRelationshipsForDelete(Object bo, Collection<Class<?>> ignoredRelationships) {
        if (bo == null) {
            throw new IllegalArgumentException("bo cannot be null");
        }

        if (ignoredRelationships == null) {
            throw new IllegalArgumentException("ignoredRelationships cannot be null");
        }
        final MessageMap ojb = verifyOjbRelationshipsForDelete(bo, ignoredRelationships);
        final MessageMap dd = verifyDDRelationshipsForDelete(bo, ignoredRelationships);
        final MessageMap krad = verifyKradDataRelationshipsForDelete(bo, ignoredRelationships);

        ojb.merge(dd);
        ojb.merge(krad);
        return ojb;
    }

    @Override
    public MessageMap verifyRelationshipsForUpdate(Object bo, Collection<Class<?>> ignoredRelationships) {
        return verifyRelationshipsForUpsert(bo, ignoredRelationships);
    }

    @Override
    public MessageMap verifyRelationshipsForInsert(Object bo, Collection<Class<?>> ignoredRelationships) {
        return verifyRelationshipsForUpsert(bo, ignoredRelationships);
    }

    @Override
    public List<String> persistableFields(Class<?> boClazz) {
        if (boClazz == null) {
            throw new IllegalArgumentException("bo cannot be null");
        }

        final List<String> jpaFields = getProviderRegistry().getMetadataProviders().stream()
                .filter(provider -> provider.getMetadataForType(boClazz) != null)
                .flatMap(provider -> provider.getMetadataForType(boClazz).getAttributes().stream())
                .map(MetadataCommon::getName)
                .distinct()
                .collect(Collectors.toList());

        if (!jpaFields.isEmpty()) {
            return jpaFields;
        }

        @SuppressWarnings("unchecked")
        final List<String> ojbFields = getKcPersistenceStructureService().listFieldNames(boClazz);

        return ojbFields;
    }

    @Override
    public List<String> pkFields(Class<?> boClazz) {
        if (boClazz == null) {
            throw new IllegalArgumentException("bo cannot be null");
        }

        final List<String> jpaFields = getProviderRegistry().getMetadataProviders().stream()
                .filter(provider -> provider.getMetadataForType(boClazz) != null)
                .flatMap(provider -> provider.getMetadataForType(boClazz).getPrimaryKeyAttributeNames().stream())
                .distinct()
                .collect(Collectors.toList());

        if (!jpaFields.isEmpty()) {
            return jpaFields;
        }

        @SuppressWarnings("unchecked")
        final List<String> ojbFields = getKcPersistenceStructureService().getPrimaryKeys(boClazz);

        return ojbFields;
    }

    protected MessageMap verifyRelationshipsForUpsert(Object bo, Collection<Class<?>> ignoredRelationships) {
        if (bo == null) {
            throw new IllegalArgumentException("bo cannot be null");
        }

        if (ignoredRelationships == null) {
            throw new IllegalArgumentException("ignoredRelationships cannot be null");
        }

        final MessageMap ojb = verifyOjbRelationshipsForUpsert(bo, ignoredRelationships);
        final MessageMap dd = verifyDDRelationshipsForUpsert(bo, ignoredRelationships);
        final MessageMap krad = verifyKradDataRelationshipsForUpsert(bo, ignoredRelationships);

        ojb.merge(dd);
        ojb.merge(krad);
        return ojb;
    }

    protected MessageMap verifyOjbRelationshipsForUpsert(Object bo, Collection<Class<?>> ignoredRelationships) {

        final MessageMap errors = new MessageMap();
        @SuppressWarnings("unchecked")
        final List<String> fields = (List<String>) getKcPersistenceStructureService().listFieldNames(bo.getClass());
        fields.forEach(field -> {
            final Map<String, org.kuali.rice.krad.bo.DataObjectRelationship> relationships = getKcPersistenceStructureService().getRelationshipMetadata(bo.getClass(), field);
            if (relationships != null) {
                relationships.entrySet().stream()
                        .filter(relationship -> !ignoredRelationships.contains(relationship.getValue().getRelatedClass()))
                        .forEach(relationship -> {
                            /*Ignore the null values because they may not be required and the DD validation
                            in the next step will catch it if needed*/
                            if (getProperty(bo, field) != null) {
                                Map<String, Object> criteria = Collections.singletonMap(relationship.getValue().getParentToChildReferences().get(field), getProperty(bo, field));
                                if (validCriteria(criteria) && getBusinessObjectService().countMatching(relationship.getValue().getRelatedClass(),
                                        criteria) == 0) {
                                    errors.putError(relationship.getValue().getParentAttributeName(), RiceKeyConstants.ERROR_EXISTENCE, getRelationshipDescriptor(relationship.getValue().getRelatedClass()));
                                }
                            }
                        });
            }
        });
        return errors;
    }

    protected MessageMap verifyDDRelationshipsForUpsert(Object bo, Collection<Class<?>> ignoredRelationships) {
        final Collection<RelationshipDefinition> ddRelationships = getDataDictionaryService().getDataDictionary().getBusinessObjectEntry(bo.getClass().getName()).getRelationships();

        final MessageMap errors = new MessageMap();
        ddRelationships.stream()
                .filter(relationship -> !ignoredRelationships.contains(relationship.getTargetClass()))
                .forEach(relationship -> {
                    final Map<String, Object> criteria = new HashMap<>();
                    for (PrimitiveAttributeDefinition attr : relationship.getPrimitiveAttributes()) {
                        criteria.put(attr.getTargetName(), getProperty(bo, attr.getSourceName()));
                    }
                    try {
                        if (validCriteria(criteria) && getBusinessObjectService().countMatching(relationship.getTargetClass(), criteria) == 0) {

                            relationship.getPrimitiveAttributes().forEach(attr -> errors.putError(attr.getSourceName(), RiceKeyConstants.ERROR_EXISTENCE,
                                    getRelationshipDescriptor(relationship.getTargetClass())));
                        }
                    } catch (ClassNotPersistenceCapableException | ClassNotPersistableException e) {
                        LOG.debug(bo.getClass().getName() + " has a relationship to a non-persistable class " + relationship.getSourceClass(), e);
                    }
                });
        return errors;
    }

    protected MessageMap verifyKradDataRelationshipsForUpsert(Object bo, Collection<Class<?>> ignoredRelationships) {

        final Collection<org.kuali.rice.krad.data.metadata.DataObjectRelationship> kradDataRelationships = getProviderRegistry().getMetadataProviders().stream()
                .filter(provider -> provider.getMetadataForType(bo.getClass()) != null)
                .flatMap(provider -> provider.getMetadataForType(bo.getClass()).getRelationships().stream())
                .collect(Collectors.toList());

        final MessageMap errors = new MessageMap();
        kradDataRelationships.stream()
                .filter(relationship -> !ignoredRelationships.contains(relationship.getRelatedType()))
                .forEach(relationship -> {

                    final Map<String, Object> criteria = relationship.getAttributeRelationships().stream()
                            .map(attr -> entry(attr.getChildAttributeName(), getProperty(bo, attr.getParentAttributeName())))
                            .collect(nullSafeEntriesToMap());

                    if (validCriteria(criteria) && getDataObjectService().findMatching(relationship.getRelatedType(),
                            QueryByCriteria.Builder.andAttributes(criteria).setCountFlag(CountFlag.ONLY).build()).getTotalRowCount() == 0) {

                        relationship.getAttributeRelationships().forEach(rel -> errors.putError(rel.getParentAttributeName(), RiceKeyConstants.ERROR_EXISTENCE,
                                getRelationshipDescriptor(relationship.getRelatedType())));
                    }
                });
        return errors;
    }

    protected MessageMap verifyOjbRelationshipsForDelete(Object bo, Collection<Class<?>> ignoredRelationships) {
        final List<DataObjectRelationship> ojbRelationships = getKcPersistenceStructureService().getRelationshipsTo(bo.getClass());

        final MessageMap errors = new MessageMap();
        ojbRelationships.stream()
                .filter(relationship -> !ignoredRelationships.contains(relationship.getParentClass()))
                .forEach(relationship -> {
            final Map<String, Object> criteria = relationship.getParentToChildReferences().entrySet().stream()
                    .map(entry -> entry(entry.getKey(), getProperty(bo, entry.getValue())))
                    .collect(nullSafeEntriesToMap());

            if (validCriteria(criteria) && getBusinessObjectService().countMatching(relationship.getParentClass(), criteria) > 0) {
                errors.putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.ERROR_DELETION_BLOCKED, getRelationshipDescriptor(relationship.getParentClass()));
            }
        });
        return errors;
    }

    protected boolean validCriteria(Map<String, Object> criteria) {
        return !criteria.isEmpty() && !criteria.values().stream()
                .anyMatch(val -> val == null || (val instanceof String && StringUtils.isBlank((String) val)));
    }

    protected MessageMap verifyDDRelationshipsForDelete(Object bo, Collection<Class<?>> ignoredRelationships) {

        final Collection<RelationshipDefinition> ddRelationships = getDataDictionaryService().getDataDictionary().getBusinessObjectEntries().values().stream()
                .flatMap(entry -> entry.getRelationships().stream())
                .filter(relationship -> relationship.getTargetClass().equals(bo.getClass()))
                .collect(Collectors.toList());

        final MessageMap errors = new MessageMap();
        ddRelationships.stream()
                .filter(relationship -> !ignoredRelationships.contains(relationship.getSourceClass()))
                .forEach(relationship -> {
                    final Map<String, Object> criteria = relationship.getPrimitiveAttributes().stream()
                            .map(attr -> entry(attr.getSourceName(), getProperty(bo, attr.getTargetName())))
                            .collect(nullSafeEntriesToMap());
                    try {
                        if (validCriteria(criteria) && areAllColumnsInCriteriaMapped(criteria.keySet(), relationship.getSourceClass()) && getBusinessObjectService().countMatching(relationship.getSourceClass(), criteria) > 0) {
                            errors.putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.ERROR_DELETION_BLOCKED,
                                    getRelationshipDescriptor(relationship.getSourceClass()));
                        }
                    } catch (ClassNotPersistenceCapableException | ClassNotPersistableException e) {
                        LOG.debug(bo.getClass().getName() + " has a relationship to a non-persistable class " + relationship.getSourceClass(), e);
                    }
                });
        return errors;
    }

    protected boolean areAllColumnsInCriteriaMapped(Set<String> keySet, Class<?> clazz) {
        return persistableFields(clazz).containsAll(keySet);
    }

    protected MessageMap verifyKradDataRelationshipsForDelete(Object bo, Collection<Class<?>> ignoredRelationships) {

        final List<Map.Entry<Class<?>, org.kuali.rice.krad.data.metadata.DataObjectRelationship>> kradDataRelationships = getProviderRegistry().getMetadataProviders().stream()
                .flatMap(provider -> provider.provideMetadata().values().stream())
                .flatMap(entry -> entry.getRelationships().stream()
                        .filter(relationship -> relationship.getRelatedType().equals(bo.getClass()))
                        .map(relationship -> CollectionUtils.<Class<?>, org.kuali.rice.krad.data.metadata.DataObjectRelationship>entry(entry.getType(), relationship)))
                .collect(Collectors.toList());

        final MessageMap errors = new MessageMap();
        kradDataRelationships.stream()
                .filter(relationship -> !ignoredRelationships.contains(relationship.getKey()))
                .forEach(relationship -> {
            final Map<String, Object> criteria = relationship.getValue().getAttributeRelationships().stream()
                    .map(attr -> entry(attr.getParentAttributeName(), getProperty(bo, attr.getChildAttributeName())))
                    .collect(nullSafeEntriesToMap());

            if (validCriteria(criteria) && getDataObjectService().findMatching(relationship.getKey(),
                    QueryByCriteria.Builder.andAttributes(criteria).setCountFlag(CountFlag.ONLY).build()).getTotalRowCount() > 0) {
                errors.putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.ERROR_DELETION_BLOCKED,
                        getRelationshipDescriptor(relationship.getKey()));
            }
        });
        return errors;
    }

    protected String getRelationshipDescriptor(Class clazz) {
        BusinessObjectEntry entry = dataDictionaryService.getDataDictionary().getBusinessObjectEntry(clazz.getName());
        if (entry != null && StringUtils.isNotEmpty(entry.getObjectLabel())) {
            return entry.getObjectLabel();
        }
        return clazz.getSimpleName();
    }

    private Object getProperty(Object o, String prop) {
        try {
            return PropertyUtils.getProperty(o, prop);
        }
        catch( NoSuchMethodException e) {
            // anonymous access
            return null;
        }
        catch (IllegalAccessException | InvocationTargetException e) {
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
