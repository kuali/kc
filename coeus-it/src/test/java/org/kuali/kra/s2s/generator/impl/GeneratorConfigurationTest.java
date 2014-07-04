package org.kuali.kra.s2s.generator.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.s2s.formmapping.FormMappingInfo;
import org.kuali.kra.s2s.formmapping.FormMappingService;
import org.kuali.kra.s2s.generator.FormGenerator;
import org.kuali.kra.s2s.generator.S2SFormGenerator;
import org.kuali.kra.s2s.service.S2SFormGeneratorService;
import org.kuali.kra.s2s.service.impl.S2SFormGeneratorServiceImpl;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class GeneratorConfigurationTest extends KcIntegrationTestBase {

    private FormMappingService mappingService;
    private S2SFormGeneratorServiceImpl s2SFormGeneratorService;

    @Before
    public void retrieveMappingService() {
        mappingService = KcServiceLocator.getService(FormMappingService.class);
        s2SFormGeneratorService = (S2SFormGeneratorServiceImpl) KcServiceLocator.getService(S2SFormGeneratorService.class);
    }

    /**
     * This tests that all configured generators can be found within the running spring context.  This will also force
     * spring to wire each generator ensuring that all wired dependencies can be properly injected.
     */
    @Test
    public void test_find_all_generators() {
        Map<String, FormMappingInfo> mappings =  mappingService.getBindings();

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

        Collection<String> notFound = new ArrayList<>();

        for (FormMappingInfo info : mappings.values()) {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
            Resource resource = resourceLoader.getResource("classpath:" + info.getStyleSheet());
            if (!resource.exists()) {
                notFound.add(info.getStyleSheet());
            }
        }

        Assert.assertTrue("Stylesheets not found: " + notFound, notFound.isEmpty());
    }
}
