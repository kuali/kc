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
package org.kuali.coeus.common.budget.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.web.ui.Section;

import java.util.ArrayList;
import java.util.List;

public class CostElementMaintainableImpl extends KualiMaintainableImpl {

    private static final long serialVersionUID = -2403270541923494151L;
    
    private static final String KFS_ON_PARM_NMSPC_CD = "KC-AWARD";
    private static final String KFS_ON_PARM_DTL_TYP_CD = "Document";
    private static final String KFS_ON_PARM_NM = "FIN_SYSTEM_INTEGRATION_ON";
    private static final String KFS_ON_OFF_VALUE = "OFF";
    private static final String KFS_SECTION_NAME = "Edit Cost Element KFS Option List";

    @Override
    @SuppressWarnings("unchecked")
    public List<Section> getSections(MaintenanceDocument document, Maintainable oldMaintainable) {
        List<Section> oldSections = super.getSections(document, oldMaintainable);
        
        String kfsOnParameterValue = getParameterService().getParameterValueAsString(KFS_ON_PARM_NMSPC_CD, KFS_ON_PARM_DTL_TYP_CD, KFS_ON_PARM_NM);
        
        List<Section> sections = new ArrayList<Section>();
        if (!StringUtils.equals(kfsOnParameterValue, KFS_ON_OFF_VALUE)) {
            sections.addAll(oldSections);
        } else {
            for (Section oldSection : oldSections) {
                if (!StringUtils.equals(oldSection.getSectionTitle(), KFS_SECTION_NAME)) {
                    sections.add(oldSection);
                }
            }
        }
        
        return sections;
    }

    private ParameterService getParameterService() {
        return KcServiceLocator.getService(ParameterService.class);
    }
    
}