/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.irb.actions.correspondence;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
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

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4993731064232051911L;

    @Override
    protected org.kuali.kra.protocol.actions.correspondence.ProtocolActionTypeToCorrespondenceTemplateService getProtocolActionTypeToCorrespondenceTemplateService() {
        return KraServiceLocator.getService(ProtocolActionTypeToCorrespondenceTemplateService.class);
    }


    @Override
    public CorrespondenceXmlStreamBase getCorrespondenceXmlStream() {
        return KraServiceLocator.getService(CorrespondenceXmlStream.class);
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
        return Constants.MODULE_NAMESPACE_PROTOCOL;
    }
}