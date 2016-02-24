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

import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;

public class DisclProjectQuestionnaireHelper extends DisclosureQuestionnaireHelper {

    private CoiDisclosure originalDisclosure;
    private CoiDisclProject coiDisclProject;
    
    public DisclProjectQuestionnaireHelper(CoiDisclProject coiDisclProject, CoiDisclosure coiDisclosure, CoiDisclosure originalDisclosure) {
        super(coiDisclosure);
        this.coiDisclProject = coiDisclProject;
        this.originalDisclosure = originalDisclosure;
    }

    @Override
    public ModuleQuestionnaireBean getModuleQnBean() {
        return new DisclosureModuleQuestionnaireBean(originalDisclosure, getCoiDisclProject(), 
                getCoiDisclosure().getCoiDisclosureDocument().getDocumentHeader().hasWorkflowDocument() ? getCoiDisclosure().getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isApproved() : false);
    }

    public CoiDisclProject getCoiDisclProject() {
        return coiDisclProject;
    }

    public void setCoiDisclProject(CoiDisclProject coiDisclProject) {
        this.coiDisclProject = coiDisclProject;
        setCoiDisclosure(coiDisclProject.getCoiDisclosure());
    }

    public void preSave(CoiDisclosure coiDisclosure) {
        setCoiDisclosure(coiDisclosure);
        super.preSave();
    }

    public CoiDisclosure getOriginalDisclosure() {
        return originalDisclosure;
    }

    public void setOriginalDisclosure(CoiDisclosure originalDisclosure) {
        this.originalDisclosure = originalDisclosure;
    }    

}
