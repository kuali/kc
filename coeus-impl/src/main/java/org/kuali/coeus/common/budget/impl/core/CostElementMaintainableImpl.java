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
