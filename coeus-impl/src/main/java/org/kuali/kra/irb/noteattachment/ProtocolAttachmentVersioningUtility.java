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
