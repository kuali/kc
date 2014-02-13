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
package org.kuali.kra.irb;


import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.ProtocolDocumentBase.ProtocolWorkflowType;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.role.XPathQualifierResolver;
import org.kuali.rice.krad.service.DocumentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class is to filter out the 'merged' protocol, and get the attribute if it is not merged protocol
 */
public class ProtocolQualifierResolver extends XPathQualifierResolver {
    public List<Map<String,String>> resolve(RouteContext context) {
        List<Map<String,String>> attributeSets = new ArrayList<Map<String,String>>();
        try {
            ProtocolDocument protocolDocument = (ProtocolDocument) KcServiceLocator.getService(DocumentService.class)
                    .getByDocumentHeaderIdSessionless(context.getDocument().getDocumentId() + "");
            if (ProtocolWorkflowType.NORMAL.getName().equals(protocolDocument.getProtocolWorkflowType())) {
                attributeSets = super.resolve(context);
            }
        } catch (WorkflowException e) {
            throw new RiceRuntimeException("Encountered an issue workflow.", e);
        }
        return attributeSets;
    }

}
