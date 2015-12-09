/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.s2sgen.api.generate.FormMappingInfo;
import org.kuali.coeus.s2sgen.api.generate.FormMappingService;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.S2SFormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.S2SFormGeneratorRetrievalService;
import org.kuali.coeus.s2sgen.impl.generate.S2SFormGeneratorRetrievalServiceImpl;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class GeneratorConfigurationTest extends KcIntegrationTestBase {

    private FormMappingService mappingService;
    private S2SFormGeneratorRetrievalServiceImpl s2SFormGeneratorService;

    @Before
    public void retrieveMappingService() {
        mappingService = KcServiceLocator.getService(FormMappingService.class);
        s2SFormGeneratorService = (S2SFormGeneratorRetrievalServiceImpl) KcServiceLocator.getService(S2SFormGeneratorRetrievalService.class);
    }

    /**
     * This tests that all configured generators can be found within the running spring context.  This will also force
     * spring to wire each generator ensuring that all wired dependencies can be properly injected.
     */
    @Test
    public void test_find_all_generators() {
        Map<String, FormMappingInfo> mappings =  mappingService.getBindings();

        Assert.assertFalse("No Mappings found", mappings.isEmpty());

        for (FormMappingInfo info : mappings.values()) {
            final S2SFormGenerator generator;
            try {
                generator = KcServiceLocator.getService(info.getGeneratorName());
            } catch (RuntimeException e) {
                throw new RuntimeException("failed to find or create generator: " + info.getGeneratorName(), e);
            }
            Assert.assertNotNull("generator: " + info.getGeneratorName() + " was null", generator);
        }

    }

    /**
     * This tests that all classes annotated with FormGenerator are configured in the FormMappingService.
     */
    @Test
    public void test_find_all_config_entries() {

        Collection<String> notFound = new ArrayList<>();

        //this makes sure that the application context available to the s2SFormGeneratorService finds the FormGenerators
        Map<String, Object> beans = s2SFormGeneratorService.getApplicationContext().getBeansWithAnnotation(FormGenerator.class);
        Assert.assertFalse("No FormGenerators found", beans.isEmpty());

        Map<String, FormMappingInfo> mappings =  mappingService.getBindings();
        Assert.assertFalse("No Mappings found", mappings.isEmpty());

        for (String beanName: beans.keySet()) {
            boolean found = false;
            for (FormMappingInfo info : mappings.values()) {
                if (beanName.equals(info.getGeneratorName())) {
                    found = true;
                    break;
                }
            }

            //GlobalLibrary & UserAttachedFormGenerator generators are special and aren't directly configured
            if (!found && !GlobalLibraryV1_0Generator.class.getSimpleName().equals(beanName)
                    && !GlobalLibraryV2_0Generator.class.getSimpleName().equals(beanName)
                    && !UserAttachedFormGenerator.class.getSimpleName().equals(beanName)) {
                notFound.add(beanName);
            }
        }

        Assert.assertTrue("FormGenerators not configured: " + notFound, notFound.isEmpty());
    }

    /**
     * This test will make sure that all generators that are configured, have a valid stylesheet configured.
     */
    @Test
    public void test_find_all_stylesheets() {
        Map<String, FormMappingInfo> mappings =  mappingService.getBindings();

        Assert.assertFalse("No Mappings found", mappings.isEmpty());

        Collection<String> notFound = new ArrayList<>();

        for (FormMappingInfo info : mappings.values()) {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
            Resource resource = resourceLoader.getResource(info.getStyleSheet());
            if (!resource.exists()) {
                notFound.add(info.getStyleSheet());
            }
        }

        Assert.assertTrue("Stylesheets not found: " + notFound, notFound.isEmpty());
    }
}
