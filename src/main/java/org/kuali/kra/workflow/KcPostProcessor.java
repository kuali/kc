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
package org.kuali.kra.workflow;

import java.rmi.RemoteException;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO;
import org.kuali.rice.kns.service.PostProcessorService;
import org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor;

/**
 * Extends the {@code KualiPostProcessor} to record the actual user performing an action on a workflow status change.
 */
public class KcPostProcessor extends KualiPostProcessor {
    
    @Override
    public boolean doRouteStatusChange(DocumentRouteStatusChangeDTO statusChangeEvent) throws RemoteException {
        return ((PostProcessorService) KraServiceLocator.getService("kcPostProcessorService")).doRouteStatusChange(statusChangeEvent);
    }

}
