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
package org.kuali.kra.iacuc.correspondence;

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateActionBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateAuthorizationService;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateFormBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateService;

public class IacucProtocolCorrespondenceTemplateAction extends ProtocolCorrespondenceTemplateActionBase{

    @Override
    protected Class<? extends ProtocolCorrespondenceTemplateAuthorizationService> getProtocolCorrespondenceTemplateAuthorizationServiceClassHook() {
        return IacucProtocolCorrespondenceTemplateAuthorizationService.class;
    }

    @Override
    protected ProtocolCorrespondenceTemplateFormBase getNewProtocolCorrespondenceTemplateFormInstanceHook() {
        return new IacucProtocolCorrespondenceTemplateForm();
    }

    @Override
    protected String getViewCorrespondenceTemplatePermissionNameHook() {
        return PermissionConstants.VIEW_IACUC_CORRESPONDENCE_TEMPLATE;
    }

    @Override
    protected Class<? extends ProtocolCorrespondenceTemplateService> getProtocolCorrespondenceTemplateServiceClassHook() {
        return IacucProtocolCorrespondenceTemplateService.class;
    }

    @Override
    protected String getModifyCorrespondenceTemplatePermissionNameHook() {
        return PermissionConstants.MODIFY_IACUC_CORRESPONDENCE_TEMPLATE;
    }

}
