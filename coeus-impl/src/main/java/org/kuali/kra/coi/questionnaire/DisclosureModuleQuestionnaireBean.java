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
package org.kuali.kra.coi.questionnaire;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.rice.krad.service.BusinessObjectService;

public class DisclosureModuleQuestionnaireBean extends ModuleQuestionnaireBean {
    
    private CoiDisclosure coiDisclosure;
    
    public DisclosureModuleQuestionnaireBean(CoiDisclosure coiDisclosure) {
        super(CoeusModule.COI_DISCLOSURE_MODULE_CODE, coiDisclosure.getCoiDisclosureId() == null ? "" : coiDisclosure.getCoiDisclosureId().toString(), coiDisclosure.getEventTypeCode(), "-1", 
                isFinalDoc(coiDisclosure));
        this.coiDisclosure = coiDisclosure;
    }
    
    public DisclosureModuleQuestionnaireBean(CoiDisclosure coiDisclosure, String subModuleCode) {
        super(CoeusModule.COI_DISCLOSURE_MODULE_CODE, coiDisclosure.getCoiDisclosureId() == null ? "" : coiDisclosure.getCoiDisclosureId().toString(), subModuleCode, "-1", 
                isFinalDoc(coiDisclosure));
        this.coiDisclosure = coiDisclosure;
    }
    
    public DisclosureModuleQuestionnaireBean(CoiDisclosure coiDisclosure, CoiDisclProject coiDisclProject, boolean finalDoc) {
        super(CoeusModule.COI_DISCLOSURE_MODULE_CODE, coiDisclosure.getCoiDisclosureId() == null ? "" : coiDisclosure.getCoiDisclosureId().toString(), coiDisclProject.getDisclosureEventType(), coiDisclProject.getCoiProjectId(), finalDoc);
        this.coiDisclosure = coiDisclosure;
    }
    
    public DisclosureModuleQuestionnaireBean(String moduleItemCode, String moduleItemKey, String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc) {
        super(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
    }
    
    protected static boolean isFinalDoc(CoiDisclosure coiDisclosure) {
        if (coiDisclosure.getCoiDisclosureDocument().getDocumentHeader().hasWorkflowDocument()) {
            return coiDisclosure.getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isEnroute()
                    || coiDisclosure.getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isFinal();
        } else {
            return false;        
        }
    }
    
    @Override
    public KrmsRulesContext getKrmsRulesContextFromBean() {
        if (coiDisclosure != null) {
            return coiDisclosure.getCoiDisclosureDocument();
        } else {
            if (StringUtils.isNotBlank(getModuleItemKey())) {
                return KcServiceLocator.getService(BusinessObjectService.class).findBySinglePrimaryKey(CoiDisclosure.class, Long.valueOf(getModuleItemKey())).getCoiDisclosureDocument();
            } else {
                return null;
            }
        }
    }

    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

}
