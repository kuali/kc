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
package org.kuali.coeus.sys.framework.rule;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.criteria.CountFlag;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.metadata.DataObjectMetadata;
import org.kuali.rice.krad.data.provider.MetadataProvider;
import org.kuali.rice.krad.data.provider.ProviderRegistry;
import org.kuali.rice.krad.datadictionary.BusinessObjectEntry;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.bo.DataObjectRelationship;
import org.kuali.rice.krad.datadictionary.PrimitiveAttributeDefinition;
import org.kuali.rice.krad.datadictionary.RelationshipDefinition;
import org.kuali.rice.krad.util.KRADConstants;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class KcMaintenanceDocumentRuleBase extends MaintenanceDocumentRuleBase {

    private transient KcPersistenceStructureService kcPersistenceStructureService;
    private transient ProviderRegistry providerRegistry;
    private transient DataObjectService dataObjectService;
    private transient GlobalVariableService globalVariableService;

    @Override
    protected boolean processGlobalRouteDocumentBusinessRules(MaintenanceDocument document) {
        boolean retVal = super.processGlobalRouteDocumentBusinessRules(document);

        if (document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_DELETE_ACTION)) {
            retVal &= verifyOjbRelationships() && verifyDDRelationships() && verifyKradDataRelationships();
        }
        return retVal;
    }

    /**
     * Override this method to manually ignore certain relationships from delete verification.
     */
    protected Collection<Class<?>> relationshipDeleteVerificationIgnores() {
        return Collections.emptyList();
    }

    protected boolean verifyOjbRelationships() {
        final List<DataObjectRelationship> ojbRelationships = getKcPersistenceStructureService().getRelationshipsTo(getNewBo().getClass());
        for (DataObjectRelationship relationship : ojbRelationships) {
            if (!relationshipDeleteVerificationIgnores().contains(relationship.getParentClass())) {
                final Map<String, Object> criteria = Maps.transformEntries(relationship.getParentToChildReferences(), new Maps.EntryTransformer<String, String, Object>() {
                    @Override
                    public Object transformEntry(String key, String value) {
                        try {
                            return PropertyUtils.getProperty(getNewBo(), value);
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                if (getBoService().countMatching(relationship.getParentClass(), criteria) > 0) {
                    getGlobalVariableService().getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.ERROR_DELETION_BLOCKED);
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean verifyDDRelationships() {
        final Collection<RelationshipDefinition> ddRelationships = new ArrayList<>();
        for (BusinessObjectEntry entry : getDataDictionaryService().getDataDictionary().getBusinessObjectEntries().values()) {
            ddRelationships.addAll(Collections2.filter(entry.getRelationships(), new Predicate<RelationshipDefinition>() {
                @Override
                public boolean apply(RelationshipDefinition relationship) {
                    return relationship.getTargetClass().equals(getNewBo().getClass());
                }
            }));
        }

        for (RelationshipDefinition relationship : ddRelationships) {
            if (!relationshipDeleteVerificationIgnores().contains(relationship.getSourceClass())) {
                final Map<String, Object> criteria = new HashMap<>();
                for (PrimitiveAttributeDefinition attr : relationship.getPrimitiveAttributes()) {
                    try {
                        criteria.put(attr.getSourceName(), PropertyUtils.getProperty(getNewBo(), attr.getTargetName()));
                    } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (getBoService().countMatching(relationship.getSourceClass(), criteria) > 0) {
                    getGlobalVariableService().getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.ERROR_DELETION_BLOCKED);
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean verifyKradDataRelationships() {
        final Map<Class<?>, org.kuali.rice.krad.data.metadata.DataObjectRelationship> kradDataRelationships = new HashMap<>();
        for (MetadataProvider provider : getProviderRegistry().getMetadataProviders()) {
            for (DataObjectMetadata entry : provider.provideMetadata().values()) {
                for (org.kuali.rice.krad.data.metadata.DataObjectRelationship relationship : entry.getRelationships()) {
                    if (relationship.getRelatedType().equals(getNewBo().getClass())) {
                        kradDataRelationships.put(entry.getType(), relationship);
                    }
                }
            }
        }

        for (Map.Entry<Class<?>,org.kuali.rice.krad.data.metadata.DataObjectRelationship> relationship : kradDataRelationships.entrySet()) {
            if (!relationshipDeleteVerificationIgnores().contains(relationship.getKey())) {
                final Map<String, Object> criteria = new HashMap<>();
                for (org.kuali.rice.krad.data.metadata.DataObjectAttributeRelationship attr : relationship.getValue().getAttributeRelationships()) {
                    try {
                        criteria.put(attr.getParentAttributeName(), PropertyUtils.getProperty(getNewBo(), attr.getChildAttributeName()));
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (getDataObjectService().findMatching(relationship.getKey(),
                        QueryByCriteria.Builder.andAttributes(criteria)
                                .setCountFlag(CountFlag.ONLY)
                                .build())
                        .getTotalRowCount() > 0) {
                    getGlobalVariableService().getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.ERROR_DELETION_BLOCKED);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean processRules(KcDocumentEventBaseExtension event) {
        return event.getRule().processRules(event);
    }
    
    /**
     * 
     * This method to check pk does exist in table.  Maybe this should be in service instead in this rule base
     */
    protected boolean checkExistenceFromTable(Class<? extends BusinessObject> clazz, Map<String, ?> fieldValues, String errorField, String errorParam) {
        boolean success = true;
        success = getBoService().countMatching(clazz, fieldValues) != 0;
        if (!success) {
            getGlobalVariableService().getMessageMap().putErrorWithoutFullErrorPath(KRADConstants.MAINTENANCE_NEW_MAINTAINABLE + errorField, RiceKeyConstants.ERROR_EXISTENCE, errorParam);
        }
        return success;
    }

    protected KcPersistenceStructureService getKcPersistenceStructureService() {
        if (kcPersistenceStructureService == null) {
            this.kcPersistenceStructureService = KcServiceLocator.getService(KcPersistenceStructureService.class);
        }
        return kcPersistenceStructureService;
    }

    public void setKcPersistenceStructureService(KcPersistenceStructureService kcPersistenceStructureService) {
        this.kcPersistenceStructureService = kcPersistenceStructureService;
    }

    protected ProviderRegistry getProviderRegistry() {
        if (providerRegistry == null) {
            this.providerRegistry = KcServiceLocator.getService("providerRegistry");
        }
        return providerRegistry;
    }

    public void setProviderRegistry(ProviderRegistry providerRegistry) {
        this.providerRegistry = providerRegistry;
    }

    protected DataObjectService getDataObjectService() {
        if (dataObjectService == null) {
            this.dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    protected GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            this.globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
