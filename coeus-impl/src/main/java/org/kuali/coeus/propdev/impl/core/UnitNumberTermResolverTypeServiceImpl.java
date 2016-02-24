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

import org.kuali.rice.krms.api.engine.TermResolutionException;
import org.kuali.rice.krms.api.engine.TermResolver;
import org.kuali.rice.krms.api.repository.term.TermResolverDefinition;
import org.kuali.rice.krms.api.repository.term.TermSpecificationDefinition;
import org.kuali.rice.krms.framework.type.TermResolverTypeService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component("unitNumberTermResolverTypeService")
public class UnitNumberTermResolverTypeServiceImpl implements TermResolverTypeService {

    @Override
    public TermResolver<?> loadTermResolver(
            final TermResolverDefinition termResolverDefinition) {
        
            return new TermResolver<String>() {

                @Override
                public Set<String> getPrerequisites() {
                    HashSet<String> results = new HashSet<String>();

                    results.add("pdoc");
                    return results;
                }

                @Override
                public String getOutput() {
                    TermSpecificationDefinition def = termResolverDefinition.getOutput();
                    return def.getName();
                }

                @Override
                public Set<String> getParameterNames() {
                    return Collections.unmodifiableSet(termResolverDefinition.getParameterNames());
                }

                @Override
                public int getCost() {
                    return 1;
                }

                @Override
                public String resolve(
                        Map<String, Object> resolvedPrereqs,
                        Map<String, String> parameters)
                        throws TermResolutionException {
                    ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument)resolvedPrereqs.get("pdoc");
                    return pd.getDevelopmentProposal().getUnitNumber();
                }
                
            };
   }

}
