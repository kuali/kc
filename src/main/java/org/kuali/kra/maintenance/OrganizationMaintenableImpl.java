/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.maintenance;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.maintenance.Maintainable;
import org.kuali.core.util.AssertionUtils;
import org.kuali.core.web.format.Formatter;
import org.kuali.core.web.ui.Section;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationYnq;
import org.kuali.kra.bo.Ynq;
import org.kuali.kra.budget.RateDecimal;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RateDecimalFormatter;
import org.kuali.kra.service.YnqService;

public class OrganizationMaintenableImpl extends KraMaintainableImpl {

    public OrganizationMaintenableImpl() {
        super();
        Formatter.registerFormatter(RateDecimal.class, RateDecimalFormatter.class);

    }

    /**
     * This is a hook for initializing the BO from the maintenance framework.
     * It initializes the {@link Explanation}s collection.
     *
     * @param generateDefaultValues true for initialization
     */
    @Override
    public void setGenerateDefaultValues(boolean generateDefaultValues) {
        if (generateDefaultValues) {
            initOrganizationYnq();
        }
        super.setGenerateDefaultValues(generateDefaultValues);
    }

    
    /**
     * This is just trying to populate existing organization that has no ynq
     * @see org.kuali.core.maintenance.KualiMaintainableImpl#getCoreSections(org.kuali.core.maintenance.Maintainable)
     */
    @Override
    public List<Section> getCoreSections(Maintainable oldMaintainable) {
        Organization organization = ((Organization)getBusinessObject());
        if (organization.getOrganizationYnqs() == null || organization.getOrganizationYnqs().isEmpty()) {
            initOrganizationYnq();
        }
        return super.getCoreSections(oldMaintainable);
    }


    /**
     * 
     * This method generate organizationynqs list based on ynq type or 'organization'
     */
    private void initOrganizationYnq() {
        Organization organization = ((Organization)getBusinessObject());
        List<OrganizationYnq> organizationYnqs = organization.getOrganizationYnqs();
        AssertionUtils.assertThat(organizationYnqs.isEmpty());
        
        List<Ynq> ynqs = getOrganizationTypeYnqs();
        for (Ynq ynq : ynqs) {
            OrganizationYnq organizationYnq = new OrganizationYnq();
            organizationYnq.setYnq(ynq);
            organizationYnq.setQuestionId(ynq.getQuestionId());
            if (StringUtils.isNotBlank(organization.getOrganizationId())) {
                organizationYnq.setOrganizationId(organization.getOrganizationId()); 
            }
            organizationYnqs.add(organizationYnq);
        }
    }

    /**
     * 
     * This method calls ynqservice to get ynq list of organization type.
     * @return
     */
    private List<Ynq> getOrganizationTypeYnqs() {
         List<Ynq> ynqs = (KraServiceLocator.getService(YnqService.class).getYnq("O"));
         return ynqs;
     }


}
