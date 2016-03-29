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

/**
 * 
 * This class needs to be extended by specific action correspondence objects.
 */
public abstract class AbstractProtocolActionsCorrespondence extends ProtocolActionsCorrespondenceBase {


    private static final long serialVersionUID = 4993731064232051911L;

    @Override
    protected org.kuali.kra.protocol.actions.correspondence.ProtocolActionTypeToCorrespondenceTemplateService getProtocolActionTypeToCorrespondenceTemplateService() {
        return KcServiceLocator.getService(ProtocolActionTypeToCorrespondenceTemplateService.class);
    }


    @Override
    public CorrespondenceXmlStreamBase getCorrespondenceXmlStream() {
        return KcServiceLocator.getService(CorrespondenceXmlStream.class);
    }

    @Override
    protected ProtocolPrintWatermarkBase getNewProtocolPrintWatermarkInstanceHook() {
        return new ProtocolPrintWatermark();
    }
    
    @Override
    protected String getAdministratorType() {
        return RoleConstants.IRB_ADMINISTRATOR;
    }

    @Override
    protected String getModuleNameSpace() {
        return Constants.MODULE_NAMESPACE_IRB;
    }
}
