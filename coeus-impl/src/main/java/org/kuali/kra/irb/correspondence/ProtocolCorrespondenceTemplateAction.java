/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.correspondence;

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateActionBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateAuthorizationService;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateFormBase;

/**
 * 
 * Action class for ProtocolCorrespondenceTemplate.
 */
public class ProtocolCorrespondenceTemplateAction extends ProtocolCorrespondenceTemplateActionBase {
    
    
    protected String getModifyCorrespondenceTemplatePermissionNameHook() {
        return PermissionConstants.MODIFY_CORRESPONDENCE_TEMPLATE;
    }
    
    protected String getViewCorrespondenceTemplatePermissionNameHook() {
        return PermissionConstants.VIEW_IACUC_CORRESPONDENCE_TEMPLATE;
    }

    protected ProtocolCorrespondenceTemplateFormBase getNewProtocolCorrespondenceTemplateFormInstanceHook() {
        return new ProtocolCorrespondenceTemplateForm();        
    }
    
    protected Class<ProtocolCorrespondenceTemplateService> getProtocolCorrespondenceTemplateServiceClassHook() {
        return ProtocolCorrespondenceTemplateService.class;
    }
    
    protected Class<? extends ProtocolCorrespondenceTemplateAuthorizationService> getProtocolCorrespondenceTemplateAuthorizationServiceClassHook() {
        return ProtocolCorrespondenceTemplateAuthorizationService.class;
    }
}
