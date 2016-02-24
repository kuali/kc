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
