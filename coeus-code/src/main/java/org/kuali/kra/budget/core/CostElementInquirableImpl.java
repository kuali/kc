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
package org.kuali.kra.budget.core;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.inquiry.KualiInquirableImpl;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.ArrayList;
import java.util.List;

public class CostElementInquirableImpl extends KualiInquirableImpl {
    
    private static final String KFS_ON_PARM_NMSPC_CD = "KC-AWARD";
    private static final String KFS_ON_PARM_DTL_TYP_CD = "Document";
    private static final String KFS_ON_PARM_NM = "FIN_SYSTEM_INTEGRATION_ON";
    private static final String KFS_ON_OFF_VALUE = "OFF";
    private static final String KFS_SECTION_NAME = "Object Code KFS Options";
    
    @Override
    public List<Section> getSections(BusinessObject bo) {
        List<Section> oldSections = super.getSections(bo);
        
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