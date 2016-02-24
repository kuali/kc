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
