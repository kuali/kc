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
package org.kuali.kra.irb.actions.correspondence;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.actions.print.CorrespondenceXmlStream;
import org.kuali.kra.irb.actions.print.ProtocolPrintWatermark;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.print.CorrespondenceXmlStreamBase;
import org.kuali.kra.protocol.actions.print.ProtocolPrintWatermarkBase;

public class ProtocolActionsCorrespondence extends ProtocolActionsCorrespondenceBase {

    private static final long serialVersionUID = 241798237383300450L;
    private String protocolActionType;
    
    public ProtocolActionsCorrespondence(String protocolActionType) {
        this.protocolActionType = protocolActionType;
    }
    
    @Override
    public String getProtocolActionType() {
        return protocolActionType;
    }
    
    public void setProtocolActionType(String protocolActionType) {
        this.protocolActionType = protocolActionType;
    }

    @Override
    protected ProtocolActionTypeToCorrespondenceTemplateService getProtocolActionTypeToCorrespondenceTemplateService() {
        return KcServiceLocator.getService(ProtocolActionTypeToCorrespondenceTemplateService.class);
    }
    
    @Override
    protected ProtocolPrintWatermarkBase getNewProtocolPrintWatermarkInstanceHook() {
        return new ProtocolPrintWatermark();
    }

    @Override
    public CorrespondenceXmlStreamBase getCorrespondenceXmlStream() {
        return KcServiceLocator.getService(CorrespondenceXmlStream.class);
    }

    @Override
    protected String getAdministratorType() {
        return RoleConstants.IRB_ADMINISTRATOR;
    }

    @Override
    protected String getModuleNameSpace() {
        return Constants.MODULE_NAMESPACE_PROTOCOL;
    }    

}
