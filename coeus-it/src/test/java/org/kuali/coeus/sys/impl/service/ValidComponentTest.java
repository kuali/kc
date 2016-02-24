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
package org.kuali.coeus.sys.impl.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.api.component.ComponentService;
import org.kuali.rice.coreservice.api.parameter.ParameterQueryResults;
import org.kuali.rice.coreservice.api.parameter.ParameterRepositoryService;

import java.util.Collection;
import java.util.stream.Collectors;

public class ValidComponentTest extends KcIntegrationTestBase {

    private ParameterRepositoryService parameterRepositoryService;
    private ComponentService componentService;

    @Before
    public void initServices() {
        parameterRepositoryService = KcServiceLocator.getService(ParameterRepositoryService.class);
        componentService = KcServiceLocator.getService(ComponentService.class);
    }

    @Test
    public void test_all_parameters_have_valid_components() {
        final QueryByCriteria emptyCriteria = QueryByCriteria.Builder.create().build();
        final ParameterQueryResults allParameters = parameterRepositoryService.findParameters(emptyCriteria);
        final Collection<Parameter> withInvalidComponents = allParameters.getResults().stream().filter(p -> componentService.getComponentByCode(p.getNamespaceCode(), p.getComponentCode()) == null).collect(Collectors.toList());
        Assert.assertTrue("The following parameters do not have valid components " + withInvalidComponents, withInvalidComponents.isEmpty());
    }
}
