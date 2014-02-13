/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.coeus.sys.impl.controller;

import org.kuali.coeus.sys.framework.controller.DocHandlerService;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("docHandlerService")
public class DocHandlerServiceImpl implements DocHandlerService {

    @Autowired
    @Qualifier("routeHeaderService")
    private RouteHeaderService routeHeaderService;
    
    public String getDocHandlerUrl(String routeHeaderId) {
        DocumentRouteHeaderValue routeHeader = routeHeaderService.getRouteHeader(routeHeaderId);
        return routeHeader.getDocumentType().getResolvedDocumentHandlerUrl();
    }

    public void setRouteHeaderService(RouteHeaderService routeHeaderService) {
        this.routeHeaderService = routeHeaderService;
    }
    
}
