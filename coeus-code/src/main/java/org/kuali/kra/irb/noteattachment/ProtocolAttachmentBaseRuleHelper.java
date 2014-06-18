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
package org.kuali.kra.irb.noteattachment;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.protocol.framework.attachment.ProtocolAttachmentService;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;

/**
 * This class contains methods to "help" in validating ProtocolAttachmentBase.
 */
@SuppressWarnings("deprecation")
class ProtocolAttachmentBaseRuleHelper extends org.kuali.coeus.common.protocol.framework.attachment.ProtocolAttachmentBaseRuleHelper {

    protected ProtocolAttachmentBaseRuleHelper(String aPropertyPrefix) {
        super(aPropertyPrefix, KcServiceLocator.getService(ProtocolAttachmentService.class), KNSServiceLocator.getKNSDictionaryValidationService());
    }

    protected ProtocolAttachmentBaseRuleHelper(final ProtocolAttachmentService attachmentService,
            final DictionaryValidationService validationService) {
                super(attachmentService, validationService);
    }
    
}
