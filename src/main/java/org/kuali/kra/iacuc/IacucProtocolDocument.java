/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra.iacuc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;


/**
 * 
 * This class represents the Protocol Document Object.
 * ProtocolDocument has a 1:1 relationship with Protocol Business Object.
 * We have declared a list of Protocol BOs in the ProtocolDocument at the same time to
 * get around the OJB anonymous keys issue of primary keys of different data types.
 * Also we have provided convenient getter and setter methods so that to the outside world;
 * Protocol and ProtocolDocument can have a 1:1 relationship.
 */
@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_PROTOCOL)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class IacucProtocolDocument extends ProtocolDocument<IacucProtocol> { 
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1014286912251147390L;
    private static final Log LOG = LogFactory.getLog(IacucProtocolDocument.class);
    public static final String DOCUMENT_TYPE_CODE = "ICPR";
    
	
    /**
     * Constructs a ProtocolDocument object.
     */
	public IacucProtocolDocument() { 
        super();
	}
	
	@Override
    protected IacucProtocol createNewProtocolInstanceHook() {
        return new IacucProtocol();
    }

    @Override
    protected void setThisDocumentOnProtocolInstanceHook(IacucProtocol protocol) {
        protocol.setIacucProtocolDocument(this);
    }    

    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }

}
