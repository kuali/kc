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
package org.kuali.kra.iacuc;

import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolModule;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolJavaFunctionKrmsTermServiceBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucJavaFunctionKrmsTermServiceImpl extends ProtocolJavaFunctionKrmsTermServiceBase implements IacucJavaFunctionKrmsTermService {
    private BusinessObjectService businessObjectService;

    @Override
    protected ProtocolBase getActiveProtocol(String protocolNumber) {
        Map<String,String> keyMap = new HashMap<String,String>();
        keyMap.put("protocolNumber", protocolNumber);
        List<IacucProtocol> protocols = (List <IacucProtocol>)getBusinessObjectService().findMatchingOrderBy(
                            IacucProtocol.class, keyMap, "sequenceNumber", false);
        return protocols.get(0);
    }

    @Override
    public String getRenewalActionTypeCode() {
        return IacucProtocolActionType.RENEWAL_CREATED;
    }

    @Override
    public String getProtocolPersonnelModuleTypeCode() {
        return IacucProtocolModule.PROTOCOL_PERSONNEL;
    }

    @Override
    public String getProtocolOrganizationModuleTypeCode() {
        return IacucProtocolModule.PROTOCOL_ORGANIZATIONS;
    }

    @Override
    public String getNotifySubmissionTypeCode() {
        return IacucProtocolSubmissionType.FYI;
    }


    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
