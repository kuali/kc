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
package org.kuali.kra.award.lookup;


import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kns.lookup.Lookupable;
import org.kuali.rice.krad.lookup.LookupForm;
import org.kuali.rice.krad.lookup.LookupableImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component("awardLookupableKrad")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AwardLookupableKrad extends LookupableImpl implements InitializingBean {

    @Autowired
    @Qualifier("awardLookupable")
    private Lookupable awardLookupable;

    @Override
    public Collection<?> performSearch(LookupForm form, Map<String, String> searchCriteria, boolean bounded) {
        return getAwardLookupable().getSearchResults(searchCriteria);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setDataObjectClass(getAwardLookupable().getBusinessObjectClass());
    }

    @Override
    public Class<?> getDataObjectClass() {
        if (super.getDataObjectClass() == null) {
            setDataObjectClass(getAwardLookupable().getBusinessObjectClass());
        }

        return super.getDataObjectClass();
    }

    public Lookupable getAwardLookupable() {
        if (awardLookupable == null) {
            awardLookupable = KcServiceLocator.getService("awardLookupable");
        }

        return awardLookupable;
    }

    public void setAwardLookupable(Lookupable awardLookupable) {
        this.awardLookupable = awardLookupable;
    }
}
