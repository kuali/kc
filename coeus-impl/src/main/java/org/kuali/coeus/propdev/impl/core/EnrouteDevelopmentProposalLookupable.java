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
package org.kuali.coeus.propdev.impl.core;
import org.kuali.rice.krad.lookup.LookupForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("enrouteDevelopmentProposalLookupable")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EnrouteDevelopmentProposalLookupable extends PropDevLookupableHelperServiceImpl {

	private static final long serialVersionUID = 1L;
	
     @SuppressWarnings("unchecked")
	@Override
    public List<?> performSearch(LookupForm form, Map<String, String> searchCriteria, boolean unbounded) {
    	
    	searchCriteria.put("proposalStateTypeCode", "2|5");
    	List<DevelopmentProposal> list = (List<DevelopmentProposal>) super.performSearch(form, searchCriteria, unbounded);
    	return list;
    }
    
    @Override
    public boolean validateSearchParameters(LookupForm form, Map<String,String> fieldValues) {
    	form.getViewPostMetadata().getLookupCriteria().put("proposalStateTypeCode", new HashMap<String, Object>());
        return super.validateSearchParameters(form, fieldValues);
       
    }
}
