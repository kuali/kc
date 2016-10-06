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


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.dd.DataDictionaryOverride;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.Collection;

public class DataDictionaryOverrideMaintainableImpl extends KualiMaintainableImpl {

    private static final String DATA_DICTIONARY_RUNTIME_OVERRIDE_PARM = "DATA_DICTIONARY_RUNTIME_OVERRIDE";
    private static final String SEQ_DD_OVERRIDE_ID = "SEQ_DD_OVERRIDE_ID";

    private transient ParameterService parameterService;
    private transient SequenceAccessorService sequenceAccessorService;

    @Override
    public void saveBusinessObject() {
        final DataDictionaryOverride override = (DataDictionaryOverride) getBusinessObject();

        if (!KRADConstants.MAINTENANCE_DELETE_ACTION.equals(getMaintenanceAction())) {
            if (StringUtils.isBlank(override.getId())) {
                override.setId(getSequenceAccessorService().getNextAvailableSequenceNumber(SEQ_DD_OVERRIDE_ID, DataDictionaryOverride.class).toString());
            }

            if (isRuntimeApplicationEnabled()) {
                resetDataDictionary(override.isActive() ? DataDictionaryOverrideUtils.createNewWithOverride(getDataDictionaryService().getDataDictionary(), override.getId(), override.getAttachmentContent()) :
                        DataDictionaryOverrideUtils.createNewRemovingOverride(getDataDictionaryService().getDataDictionary(), override.getId()));
            }
            super.saveBusinessObject();
        }
    }

    protected void resetDataDictionary(DataDictionary modifiedDataDictionary) {
        final Collection<DataDictionaryService> ddServices = KcServiceLocator.getServicesOfType(DataDictionaryService.class);

        ddServices.forEach(ddService -> {
            ddService.setDataDictionary(modifiedDataDictionary);
            if (ddService instanceof org.kuali.rice.kns.service.DataDictionaryService) {
                ((org.kuali.rice.kns.service.DataDictionaryService) ddService).getDataDictionaryMap().clear();
            }
        });
    }

    @Override
    public void deleteDataObject() {
        final DataDictionaryOverride override = (DataDictionaryOverride) getBusinessObject();

        if (isRuntimeApplicationEnabled()) {
            resetDataDictionary(DataDictionaryOverrideUtils.createNewRemovingOverride(getDataDictionaryService().getDataDictionary(), override.getId()));
        }

        super.deleteDataObject();
    }

    protected boolean isRuntimeApplicationEnabled() {
        return getParameterService().getParameterValueAsBoolean(Constants.KC_SYS, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, DATA_DICTIONARY_RUNTIME_OVERRIDE_PARM);
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

    public SequenceAccessorService getSequenceAccessorService() {
        if (sequenceAccessorService == null) {
            sequenceAccessorService = KcServiceLocator.getService(SequenceAccessorService.class);
        }
        return sequenceAccessorService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
}
