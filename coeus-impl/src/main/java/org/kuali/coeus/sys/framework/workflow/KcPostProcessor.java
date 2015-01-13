/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.sys.framework.workflow;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kew.framework.postprocessor.*;
import org.kuali.rice.krad.service.PostProcessorService;
import org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor;

import java.util.List;

/**
 * Extends the {@code KualiPostProcessor} to record the actual user performing an action on a workflow status change.
 */
public class KcPostProcessor implements PostProcessor {
    
    @Override
    public ProcessDocReport doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) throws Exception {
        return ((PostProcessorService) KcServiceLocator.getService("kcPostProcessorService")).doRouteStatusChange(statusChangeEvent);
    }

    @Override
    public ProcessDocReport doRouteLevelChange(final DocumentRouteLevelChange levelChangeEvent) throws Exception {
        return ((PostProcessorService) KcServiceLocator.getService("kcPostProcessorService")).doRouteLevelChange(levelChangeEvent);
    }

    @Override
    public ProcessDocReport doDeleteRouteHeader(DeleteEvent event) throws Exception {
        return ((PostProcessorService) KcServiceLocator.getService("kcPostProcessorService")).doDeleteRouteHeader(event);
    }

    @Override
    public ProcessDocReport doActionTaken(final ActionTakenEvent event) throws Exception {
        return ((PostProcessorService) KcServiceLocator.getService("kcPostProcessorService")).doActionTaken(event);
    }

    @Override
    public ProcessDocReport afterActionTaken(ActionType actionPerformed, ActionTakenEvent event) throws Exception{
        return ((PostProcessorService) KcServiceLocator.getService("kcPostProcessorService")).afterActionTaken(actionPerformed,event);
    }

    @Override
    public ProcessDocReport beforeProcess(BeforeProcessEvent processEvent) throws Exception {
        return ((PostProcessorService) KcServiceLocator.getService("kcPostProcessorService")).beforeProcess(processEvent);
    }

    @Override
    public ProcessDocReport afterProcess(AfterProcessEvent processEvent) throws Exception {
        return ((PostProcessorService) KcServiceLocator.getService("kcPostProcessorService")).afterProcess(processEvent);
    }

    @Override
    public List<String> getDocumentIdsToLock(DocumentLockingEvent lockingEvent) throws Exception {
        return ((PostProcessorService) KcServiceLocator.getService("kcPostProcessorService")).getDocumentIdsToLock(lockingEvent);
    }
}
