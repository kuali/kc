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
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.datadictionary.DataDictionaryException;
import org.kuali.rice.krad.util.KRADConstants;

import java.io.IOException;
import java.util.UUID;


public class DataDictionaryOverrideMaintenanceDocumentRules extends KcMaintenanceDocumentRuleBase {

    private static final String ERROR_KEY_SPRING_OVERIDE = "error.spring.overide.not.valid";
    private static final String ERROR_KEY_INVALID_FILE = "error.invalid.data.dictionary.override.invalid.fileName";
    private static final String ERROR_PROPERTY = "document.newMaintainableObject.overrideBeansFile";
    private static final String UNKNOWN_ERROR = "Unknown Error";


    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        boolean valid = isFileValid(document);
        final DataDictionaryOverride override = (DataDictionaryOverride) document.getDocumentBusinessObject();

        try {
            if (!KRADConstants.MAINTENANCE_DELETE_ACTION.equals(document.getNewMaintainableObject().getMaintenanceAction()) && override.isActive()) {
                byte[] overrideFile = override.getOverrideBeansFile() != null ? override.getOverrideBeansFile().getFileData() : override.getAttachmentContent();
                if (overrideFile != null) {
                    DataDictionaryOverrideUtils.createNewWithOverride(getDataDictionaryService().getDataDictionary(),
                            override.getId() != null ? override.getId() : UUID.randomUUID().toString(),
                            overrideFile);
                }
            } else if (KRADConstants.MAINTENANCE_DELETE_ACTION.equals(document.getNewMaintainableObject().getMaintenanceAction())) {
                DataDictionaryOverrideUtils.createNewRemovingOverride(getDataDictionaryService().getDataDictionary(), override.getId() != null ? override.getId(): UUID.randomUUID().toString());
            }
        } catch (DataDictionaryException|IOException e) {
            final String msg = e.getMessage();
            getGlobalVariableService().getMessageMap().putError(ERROR_PROPERTY, ERROR_KEY_SPRING_OVERIDE, StringUtils.isNotBlank(msg) ? msg : UNKNOWN_ERROR);
            valid = false;
        }

        return valid;
    }

    protected boolean isFileValid(MaintenanceDocument document) {
        final DataDictionaryOverride override = (DataDictionaryOverride)document.getNewMaintainableObject().getDataObject();
        if (override.getOverrideBeansFile() == null && override.getFileName() == null) {
            getGlobalVariableService().getMessageMap().putError(ERROR_PROPERTY, ERROR_KEY_INVALID_FILE);
            return false;
        }
        return true;
    }
}
