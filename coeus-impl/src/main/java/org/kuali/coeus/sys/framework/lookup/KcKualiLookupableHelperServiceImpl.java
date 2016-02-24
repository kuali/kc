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
package org.kuali.coeus.sys.framework.lookup;

import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.encryption.EncryptionService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.service.BusinessObjectDictionaryService;
import org.kuali.rice.kns.service.BusinessObjectMetaDataService;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.krad.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This is a convenient abstract class that autowires all the dependencies in the parent classes.
 */
public abstract class KcKualiLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    @Autowired
    @Qualifier("businessObjectDictionaryService")
    @Override
    public void setBusinessObjectDictionaryService(BusinessObjectDictionaryService businessObjectDictionaryService) {
        super.setBusinessObjectDictionaryService(businessObjectDictionaryService);
    }

    @Autowired
    @Qualifier("businessObjectService")
    @Override
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        super.setBusinessObjectService(businessObjectService);
    }

    @Autowired
    @Qualifier("businessObjectMetaDataService")
    @Override
    public void setBusinessObjectMetaDataService(BusinessObjectMetaDataService businessObjectMetaDataService) {
        super.setBusinessObjectMetaDataService(businessObjectMetaDataService);
    }

    @Autowired
    @Qualifier("dataDictionaryService")
    @Override
    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        super.setDataDictionaryService(dataDictionaryService);
    }

    @Autowired
    @Qualifier("encryptionService")
    @Override
    public void setEncryptionService(EncryptionService encryptionService) {
        super.setEncryptionService(encryptionService);
    }

    @Autowired
    @Qualifier("lookupResultsService")
    @Override
    public void setLookupResultsService(LookupResultsService lookupResultsService) {
        super.setLookupResultsService(lookupResultsService);
    }

    @Autowired
    @Qualifier("lookupService")
    @Override
    public void setLookupService(LookupService lookupService) {
        super.setLookupService(lookupService);
    }

    @Autowired
    @Qualifier("persistenceStructureService")
    @Override
    public void setPersistenceStructureService(PersistenceStructureService persistenceStructureService) {
        super.setPersistenceStructureService(persistenceStructureService);
    }

    @Autowired
    @Qualifier("sequenceAccessorService")
    @Override
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        super.setSequenceAccessorService(sequenceAccessorService);
    }

    @Autowired
    @Qualifier("maintenanceDocumentDictionaryService")
    @Override
    public void setMaintenanceDocumentDictionaryService(MaintenanceDocumentDictionaryService maintenanceDocumentDictionaryService) {
        super.setMaintenanceDocumentDictionaryService(maintenanceDocumentDictionaryService);
    }

    @Autowired
    @Qualifier("kualiConfigurationService")
    @Override
    public void setParameterService(ConfigurationService configurationService) {
        super.setParameterService(configurationService);
    }

    @Autowired
    @Qualifier("parameterService")
    @Override
    public void setParameterService(ParameterService parameterService) {
        super.setParameterService(parameterService);
    }
}
