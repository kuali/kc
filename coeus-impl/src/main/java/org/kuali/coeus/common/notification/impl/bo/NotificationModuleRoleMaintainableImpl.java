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
package org.kuali.coeus.common.notification.impl.bo;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.krad.service.SequenceAccessorService;

import java.util.List;

/**
 * Maintainable implementation for handling the sequence for Notification Number.
 */
public class NotificationModuleRoleMaintainableImpl extends KraMaintainableImpl {

    private static final long serialVersionUID = 5816092888763757328L;
    
    private static final String NOTIFICATION_MODULE_ROLE_ID_SEQUENCE_NAME = "SEQ_NTFCTN_MODULE_ROLE_ID";
    private static final String NOTIFICATION_MODULE_ROLE_SECTION_ID = "Edit Notification Module Role";
    private static final String NOTIFICATION_MODULE_ROLE_ID_NAME = "notificationModuleRoleId";
    
    private transient SequenceAccessorService sequenceAccessorService;
    
    
    @Override
    public void setGenerateDefaultValues(String docTypeName) {
        super.setGenerateDefaultValues(docTypeName);
        
        NotificationModuleRole notificationModuleRole = (NotificationModuleRole) getBusinessObject();
        Long nextNotificationModuleRoleId = getSequenceAccessorService().getNextAvailableSequenceNumber(NOTIFICATION_MODULE_ROLE_ID_SEQUENCE_NAME, NotificationModuleRole.class);
        notificationModuleRole.setNotificationModuleRoleId(nextNotificationModuleRoleId);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Section> getSections(MaintenanceDocument document, Maintainable oldMaintainable) {
        List<Section> sections = super.getSections(document, oldMaintainable);
        
        for (Section section : sections) {
            if (StringUtils.equals(section.getSectionId(), NOTIFICATION_MODULE_ROLE_SECTION_ID)) {
                for (Row row : section.getRows()) {
                    for (Field field : row.getFields()) {
                        if (StringUtils.equals(field.getPropertyName(), NOTIFICATION_MODULE_ROLE_ID_NAME)) {
                            field.setReadOnly(true);
                        }
                    }
                }
            }
        }

        return sections;
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
