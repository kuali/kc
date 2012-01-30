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
package org.kuali.kra.external.agency.impl;


import java.net.URL;

import javax.xml.ws.WebServiceClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.integration.cg.service.AgencyCreationService;
import org.kuali.kfs.integration.cg.service.AgencyCreationServiceSOAP;
import org.kuali.kra.external.agency.AgencyCreationClient;



/**
 * This class is the implementation of the client that
 * connects to the financial web service that creates 
 * an award.
 */

public final class AgencyCreationClientImpl extends AgencyCreationClientBase {
    private static final Log LOG = LogFactory.getLog(AgencyCreationClientImpl.class);
    public final static URL WSDL_LOCATION;
    
    private AgencyCreationClientImpl() {
        
    }

    public static AgencyCreationClient getInstance() {
        if (client == null)
            client = new AgencyCreationClientImpl();
        return client;
      }

    private static AgencyCreationClientImpl client;
      
    static
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if ( null == cl ) cl = AgencyCreationClientImpl.class.getClassLoader();
        String wsdlPath =  ((WebServiceClient) (AgencyCreationServiceSOAP.class.getAnnotation(WebServiceClient.class))).wsdlLocation();
        WSDL_LOCATION = cl.getResource(wsdlPath); 
    }
    
    @Override
    protected AgencyCreationService getServiceHandle() {
        AgencyCreationServiceSOAP ss = new AgencyCreationServiceSOAP(WSDL_LOCATION, SERVICE_NAME);
        return ss.getAgencyCreationServicePort();   
    }

}
