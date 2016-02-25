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

package org.kuali.coeus.common.impl.custom;

import org.kuali.rice.krms.api.engine.TermResolver;
import org.kuali.rice.krms.api.repository.term.TermResolverDefinition;
import org.kuali.rice.krms.framework.type.TermResolverTypeService;

import java.util.Set;

public abstract class CustomDataResolverTypeServiceImpl  implements TermResolverTypeService {
    public TermResolver<?> loadTermResolver(TermResolverDefinition termResolverDefinition) {
        Set<String> paramsSet = termResolverDefinition.getParameterNames();
        return new CustomDataResolver(termResolverDefinition.getOutput().getName(), paramsSet, getModuleNamePrereq());
    }

    protected abstract String getModuleNamePrereq();
}