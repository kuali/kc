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
package org.kuali.kra.award;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;

/**
 * This class is to validate all rules pertaining to Award Template sync functionality
 */
public class AwardTemplateSyncRuleImpl implements AwardTemplateSyncRule {

    private ErrorReporter errorReporter;
    @Override
    public boolean processAwardTemplateSyncRules(AwardTemplateSyncEvent awardTemplateSyncEvent) {
        Award award = awardTemplateSyncEvent.getAward();
        errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        if(award.getTemplateCode()==null){
            errorReporter.reportError(awardTemplateSyncEvent.getErrorPathPrefix(), KeyConstants.ERROR_NO_TEMPLATE_CODE);
            return false;
        }
                
        return true;
    }

}
