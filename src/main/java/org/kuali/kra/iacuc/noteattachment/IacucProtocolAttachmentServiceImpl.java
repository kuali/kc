/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.iacuc.noteattachment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolDao;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentServiceImpl;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentStatus;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentType;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentTypeGroup;
import org.kuali.kra.protocol.personnel.ProtocolPerson;
import org.kuali.rice.krad.service.BusinessObjectService;


public class IacucProtocolAttachmentServiceImpl extends ProtocolAttachmentServiceImpl implements IacucProtocolAttachmentService {

    public IacucProtocolAttachmentServiceImpl(BusinessObjectService boService, ProtocolDao protocolDao) {
        super(boService, protocolDao);
    }

    @Override
    public Class<? extends Protocol> getProtocolClassHook() {
        return IacucProtocol.class;
    }

    @Override
    public Class<? extends ProtocolAttachmentStatus> getProtocolAttachmentStatusClassHook() {
        return IacucProtocolAttachmentStatus.class;
    }

    @Override
    public Class<? extends ProtocolPerson> getProtocolPersonClassHook() {
        return IacucProtocolPerson.class;
    }

    @Override
    public Class<? extends ProtocolAttachmentType> getProtocolAttachmentTypeClassHook() {
        return IacucProtocolAttachmentType.class;
    }

    @Override
    public Class<? extends ProtocolAttachmentTypeGroup> getProtocolAttachmentTypeGroupClassHook() {
        return IacucProtocolAttachmentTypeGroup.class;
    }

    @Override
    public Class<? extends ProtocolAttachmentProtocol> getProtocolAttachmentProtocolClassHook() {
        return IacucProtocolAttachmentProtocol.class;
    }

    @Override
    public Class<? extends ProtocolAttachmentPersonnel> getProtocolAttachmentPersonnelClassHook() {
        return IacucProtocolAttachmentPersonnel.class;
    }

    @Override
    /** {@inheritDoc} */
    public Collection<ProtocolAttachmentType> getTypesForGroup(String code) {
        if (code == null) {
            throw new IllegalArgumentException("the code is null");
        }
  
        @SuppressWarnings("unchecked")
        final Collection<IacucProtocolAttachmentTypeGroup> typeGroups
            = this.boService.findMatching(IacucProtocolAttachmentTypeGroup.class, Collections.singletonMap("groupCode", code));
        if (typeGroups == null) {
          return new ArrayList<ProtocolAttachmentType>();
        }
      
        final Collection<ProtocolAttachmentType> types = new ArrayList<ProtocolAttachmentType>();
        for (final ProtocolAttachmentTypeGroup typeGroup : typeGroups) {
            types.add(typeGroup.getType());
        }
      
        return types;
    }

}
