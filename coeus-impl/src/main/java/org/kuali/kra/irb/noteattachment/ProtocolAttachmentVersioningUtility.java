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

import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentVersioningUtilityBase;

/**
 * Class used for versioning protocol attachments.
 * 
 * <p>
 * The basic algorithm for versioning is the following:
 * 
 * <ol>
 * <li> check if versioning is required </li>
 * <li> version the File BO </li>
 * <li> associate the new File version with an "existing" Attachment BO </li>
 * <li> create a new Protocol Document </li>
 * <li> version the Protocol and all its BOs which includes all attachment BOs (that are referencing the new file BO) </li>
 * <li> set the new Protocol Document w/ the new Protocol version </li>
 * <li> set the struts form to reference the new Protocol Document </li>
 * </ol>
 * </p>
 * 
 * It was easier to create the version of the file before the version of the Protocol to allow for easy updating of file references.
 * Also, versioning of the Protocol naturally only needs to happen once, at the end of this process.
 */
public class ProtocolAttachmentVersioningUtility extends ProtocolAttachmentVersioningUtilityBase {

    
    public ProtocolAttachmentVersioningUtility(final ProtocolForm form) {
        super(form, KcServiceLocator.getService(ProtocolAttachmentService.class),
                    KcServiceLocator.getService(VersioningService.class));
    }

    @Override
    protected Class<? extends ProtocolAttachmentProtocolBase> getProtocolAttachmentProtocolClassHook() {
        return ProtocolAttachmentProtocol.class;
    }
}
