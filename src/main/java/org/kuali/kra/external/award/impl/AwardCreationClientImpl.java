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
package org.kuali.kra.external.award.impl;


import java.net.URL;

import javax.xml.ws.WebServiceClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.integration.cg.service.AwardCreationService;
import org.kuali.kfs.integration.cg.service.AwardCreationServiceSOAP;
import org.kuali.kra.external.award.AwardCreationClient;



/**
 * This class is the implementation of the client that
 * connects to the financial web service that creates 
 * an award.
 */

public final class AwardCreationClientImpl extends AwardCreationClientBase {
    private static final Log LOG = LogFactory.getLog(AwardCreationClientImpl.class);
    public final static URL WSDL_LOCATION;
    
    private AwardCreationClientImpl() {
        
    }

    public static AwardCreationClient getInstance() {
        if (client == null)
            client = new AwardCreationClientImpl();
        return client;
      }

    private static AwardCreationClientImpl client;
      
    static
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if ( null == cl ) cl = AwardCreationClientImpl.class.getClassLoader();
        String wsdlPath =  ((WebServiceClient) (AwardCreationServiceSOAP.class.getAnnotation(WebServiceClient.class))).wsdlLocation();
        WSDL_LOCATION = cl.getResource(wsdlPath); 
    }
    
    @Override
    protected AwardCreationService getServiceHandle() {
        AwardCreationServiceSOAP ss = new AwardCreationServiceSOAP(WSDL_LOCATION, SERVICE_NAME);
        return ss.getAwardCreationServicePort();   
    }

}
