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
package org.kuali.kra.protocol.specialreview;

import org.kuali.coeus.common.framework.compliance.core.SpecialReviewHelperBase;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

/**
 * Defines the Special Review Helper for ProtocolBase.
 */
public abstract class ProtocolSpecialReviewHelperBase extends SpecialReviewHelperBase<ProtocolSpecialReviewBase> {

    private static final long serialVersionUID = -6004130465079070854L;
    
    private transient TaskAuthorizationService taskAuthorizationService;
    
    
    public TaskAuthorizationService getTaskAuthorizationService() {
        if (taskAuthorizationService == null) {
            taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        }
        return taskAuthorizationService;
    }
    
    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }

}
