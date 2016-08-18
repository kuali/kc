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
package org.kuali.coeus.sys.impl.dd;


import org.kuali.coeus.sys.framework.dd.DataDictionaryOverride;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.datadictionary.exporter.DataDictionaryMap;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.krad.service.DataDictionaryService;

import java.util.Collection;

public class DataDictionaryOverrideMaintainableImpl extends KualiMaintainableImpl {

    private static final String DATA_DICTIONARY_RUNTIME_OVERRIDE_PARM = "DATA_DICTIONARY_RUNTIME_OVERRIDE";
    private transient ParameterService parameterService;

    @Override
    public void saveBusinessObject() {
        final DataDictionaryOverride override = (DataDictionaryOverride) getBusinessObject();
        if (override.isActive() && getParameterService().getParameterValueAsBoolean(Constants.KC_SYS, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, DATA_DICTIONARY_RUNTIME_OVERRIDE_PARM)) {
            final DataDictionary modifiedDataDictionary = DataDictionaryOverrideUtils.createNewWithOverride(getDataDictionaryService().getDataDictionary(), override.getAttachmentContent());
            final DataDictionaryMap previousMap = (DataDictionaryMap) getDataDictionaryService().getDataDictionaryMap();
            //in case there are multiple instances of the DataDictionaryService, get them all and set the new DataDictionary instance
            final Collection<DataDictionaryService> ddServices = KcServiceLocator.getServicesOfType(DataDictionaryService.class);
            previousMap.clear();

            ddServices.forEach(ddService -> {
                ddService.setDataDictionary(modifiedDataDictionary);
                if (ddService instanceof org.kuali.rice.kns.service.DataDictionaryService) {
                    ((org.kuali.rice.kns.service.DataDictionaryService) ddService).setDataDictionaryMap(previousMap);
                }
            });
        }
        super.saveBusinessObject();
    }

    @Override
    protected org.kuali.rice.kns.service.DataDictionaryService getDataDictionaryService() {
        return (org.kuali.rice.kns.service.DataDictionaryService) super.getDataDictionaryService();
    }

    public ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }

        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
