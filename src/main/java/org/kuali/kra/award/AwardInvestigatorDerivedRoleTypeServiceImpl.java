/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.award;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.bo.AbstractProjectPerson;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.workflow.AbstractProjectPersonDerivedRoleTypeServiceImpl;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;

public class AwardInvestigatorDerivedRoleTypeServiceImpl extends AbstractProjectPersonDerivedRoleTypeServiceImpl {
    
    protected List<String> requiredAttributes = new ArrayList<String>();
	{
		requiredAttributes.add(KcKimAttributes.AWARD);
	}
	
	private AwardService awardService;

    @Override
    protected List<? extends AbstractProjectPerson> getProjectPersons(Map<String, String> qualification) {
        String awardIdStr = qualification.get(KcKimAttributes.AWARD);

        if (StringUtils.isNotBlank(awardIdStr) && awardIdStr.matches("\\d+")) {
            Long awardId = Long.valueOf(awardIdStr);
            Award award = getAwardService().getAward(awardId);
            return award.getProjectPersons();
        } else {
            return new ArrayList<AbstractProjectPerson>();
        }
    }

    protected AwardService getAwardService() {
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

}
