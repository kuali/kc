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

import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;

public class ScreeningQuestionnaireHelper extends DisclosureQuestionnaireHelper {

    private static final long serialVersionUID = -8685872555239368202L;
    
    public ScreeningQuestionnaireHelper(CoiDisclosure coiDisclosure) {
        super(coiDisclosure);
    }

    @Override
    public ModuleQuestionnaireBean getModuleQnBean() {
        return new DisclosureModuleQuestionnaireBean(getCoiDisclosure(), CoeusSubModule.COI_SCREENING_SUBMODULE);
    }
}
