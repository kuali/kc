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
package org.kuali.kra.iacuc.actions.correspondence;

import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondenceType;
import org.kuali.kra.iacuc.correspondence.ValidIacucProtoActionCoresp;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionTypeToCorrespondenceTemplateServiceImplBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTypeBase;
import org.kuali.kra.protocol.correspondence.ValidProtoActionCoresp;

public class IacucProtocolActionTypeToCorrespondenceTemplateServiceImpl 
    extends ProtocolActionTypeToCorrespondenceTemplateServiceImplBase implements IacucProtocolActionTypeToCorrespondenceTemplateService {
    
    public IacucProtocolActionTypeToCorrespondenceTemplateServiceImpl() {
    }

    @Override
    protected Class<? extends ValidProtoActionCoresp> getProtocolActionCorrespondenceMappingClassHook() {
        return ValidIacucProtoActionCoresp.class;
    }

    @Override
    protected Class<? extends ProtocolCorrespondenceTypeBase> getProtocolCorrespondenceTypeClassHook() {
        return IacucProtocolCorrespondenceType.class;
    }
}
