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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.s2sgen.impl.generate.S2SFormGenerator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.api.resourceloader.ResourceLoader;
import org.kuali.rice.core.framework.resourceloader.SpringResourceLoader;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanIsAbstractException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import javax.xml.namespace.QName;

import java.lang.reflect.Proxy;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SpringBeanConfigurationTest extends KcIntegrationTestBase {

    private static final Log LOG = LogFactory.getLog(SpringBeanConfigurationTest.class);

    private static final Collection<String> IGNORE_PATTERN = Stream.of(
            "^org.springframework.aop.*", "viewResolver", "enWorkflowDocumentService"
    ).collect(Collectors.toList());

    private Collection<SpringResourceLoader> springResourceLoaders;

    @Before
    public void findSpringResourceLoaders() {
        springResourceLoaders = new ArrayList<>();
        processResourceLoader(GlobalResourceLoader.getResourceLoader());
    }

    private void processResourceLoader(ResourceLoader parent) {
        if (parent == null) {
            return;
        }
        if (parent.getResourceLoaders() != null) {
            parent.getResourceLoaders().forEach(this::processResourceLoader);
        }
        if (parent instanceof SpringResourceLoader) {
            springResourceLoaders.add((SpringResourceLoader) parent);
        }
    }

    /**
     * Some Spring Beans are initialized lazily, are prototype scope, are not directly used during spring wiring,
     * or are using the GlobalResourceLoaderFactoryBean but are no longer valid bean names and as a result,
     * startup errors don't occur. In these cases, an error occurs at bean retrieval time.  This test method makes
     * sure all spring beans can be retrieved.
     */
    @Test
    public void test_all_spring_bean_retrieval() {
        toEachSpringBean(BeanFactory::getBean, false);
    }

    /**
     * Apply a void function to each spring bean available in each spring context available from each spring resource loader.
     *
     * @param function the function to apply
     * @param ignoreCreationException whether to ignore exception that occurs when creating a bean
     */
    private void toEachSpringBean(VoidFunction function, boolean ignoreCreationException) {
        Map<QName, List<KeyValue<String, Exception>>> failedBeans = new HashMap<>();

        for (SpringResourceLoader r : springResourceLoaders) {
            ApplicationContext context = r.getContext();
            for (String name : context.getBeanDefinitionNames()) {
                if (process(name)) {
                    try {
                        function.r(context, name);
                    } catch (BeanIsAbstractException e) {
                        //ignore since the bean can't be created
                    } catch (BeanCreationException e) {
                        //if there is no way to ignore creation errors all tests will fail even if one bean is bad regardless of the type
                        //we do want this type of failure to be tested by at least one test method but not all tests
                        if (!ignoreCreationException) {
                            LOG.error("unable to create bean " + name + (context instanceof ConfigurableWebApplicationContext ? " for locations " + Arrays.asList(((ConfigurableWebApplicationContext) context).getConfigLocations()) : ""), e);
                            throw e;
                        }
                    } catch (RuntimeException e) {
                        LOG.error("failed to execute function for bean " + name + (context instanceof ConfigurableWebApplicationContext ? " for locations " + Arrays.asList(((ConfigurableWebApplicationContext) context).getConfigLocations()) : ""), e);
                        List<KeyValue<String, Exception>> rlFailures = failedBeans.get(r.getName());
                        if (rlFailures == null) {
                            rlFailures = new ArrayList<>();
                        }
                        rlFailures.add(new DefaultKeyValue<>(name, e));
                        failedBeans.put(r.getName(), rlFailures);
                    }
                }
            }
        }

        Assert.assertTrue("the following beans failed to retrieve " + failedBeans, failedBeans.isEmpty());
    }

    private boolean process(String name) {
        return IGNORE_PATTERN.stream().noneMatch(name::matches);
    }

    private static class PrototypeVerification implements VoidFunction {

        private final Class<?> clazz;

        private PrototypeVerification(final Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public void r(ApplicationContext context, String name) {
            Object o = context.getBean(name);
            //not for type based checks I could use: ApplicationContext.getBeanNamesForType() but this method has too
            //many limitations where it wont consider certain beans like nested
            if (clazz.isInstance(o)) {
                if (!context.isPrototype(name)) {
                    Assert.fail("A prototype bean should always be configured as prototype, bean: " + name + " of type: " + clazz.getName());
                }

                Object o2 = context.getBean(name);
                Assert.assertNotSame("A prototype bean should always return a unique instance, bean: " + name + " of type: " + clazz.getName(), o, o2);
            }
        }
    }

    @Test
    public void test_krad_maintainable_are_prototype_scope() {
        toEachSpringBean(new PrototypeVerification(org.kuali.rice.krad.maintenance.Maintainable.class), true);
    }

    //the kns maintainable extends the krad maintainable for now.  Placing this here for completeness
    //and in case this relationship ever changes
    @Test
    @SuppressWarnings("deprecation")
    public void test_kns_maintainable_are_prototype_scope() {
        toEachSpringBean(new PrototypeVerification(org.kuali.rice.kns.maintenance.Maintainable.class), true);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void test_kns_lookupables_are_prototype_scope() {
        toEachSpringBean(new PrototypeVerification(org.kuali.rice.kns.lookup.Lookupable.class), true);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void test_kns_lookup_helper_service_are_prototype_scope() {
        toEachSpringBean(new PrototypeVerification(org.kuali.rice.kns.lookup.LookupableHelperService.class), true);
    }

    @Test
    public void test_generators_are_prototype_scope() {
        toEachSpringBean(new PrototypeVerification(S2SFormGenerator.class), true);
    }

    @Test
    public void test_printables_are_prototype_scope() {
        toEachSpringBean(new PrototypeVerification(Printable.class), true);
    }
    
    @Test
    public void test_xmlstreams_are_prototype_scope() {
        toEachSpringBean(new PrototypeVerification(XmlStream.class), true);
    }

    /**
     * Method searches out all prototype beans in all contexts.  It then
     * makes sure that the bean exists in all contexts as a prototype.
     * This helps find issues with importing a prototype bean using the
     * GlobalResourceLoaderFactoryBean as a singleton.
     */
    @Test
    public void test_beans_across_other_contexts_correct_scope() {
        final Set<String> prototypes = new HashSet<>();
        final Set<String> nonPrototypes = new HashSet<>();
        toEachSpringBean((context, name) -> {
            final Object o = context.getBean(name);
            final Object o2 = context.getBean(name);

            if (o != o2) {
                //skip checking if a proxy
                if (!Proxy.isProxyClass(o.getClass())) {
                    prototypes.add(name);
                }
            } else {
                nonPrototypes.add(name);
            }
        }, true);
        final Collection<String> misConfigured = CollectionUtils.retainAll(nonPrototypes, prototypes);
        Assert.assertTrue("The following beans are prototypes in one spring context and non-prototypes in another spring context: " + misConfigured, misConfigured.isEmpty());
    }

    /**
     * This method looks for the same bean name in different context but of different class types.
     * This is either a "partial" bean override or a bean name conflict
     */
    @Test
    public void test_beans_across_other_contexts_name_conflict() {
        final HashMap<String, Set<Class<?>>> beans = new HashMap<>();
        toEachSpringBean((context, name) -> {
                Object o = context.getBean(name);
                if (o != null) {
                    Set<Class<?>> beanClasses = beans.get(name);
                    if (beanClasses == null) {
                        beanClasses = new HashSet<>();
                    }

                    final Class<?> clazz;
                    if (Proxy.isProxyClass(o.getClass())) {
                        try {
                            clazz = (Class<?>) Proxy.getInvocationHandler(o).invoke(o, Object.class.getMethod("getClass", new Class[]{}), new Object[]{});
                        } catch (Throwable e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        clazz = o.getClass();
                    }

                    beanClasses.add(clazz);
                    beans.put(name, beanClasses);
                } else {
                    LOG.warn("bean " + name + " is null");
                }

            }, true);

        final Set<Map.Entry<String, Set<Class<?>>>> entrySet = new HashSet<>(beans.entrySet());
        CollectionUtils.filter(entrySet, object -> object.getValue().size() > 1);

        Assert.assertTrue("The following bean names are duplicated in different contexts with different class names " + entrySet,
                entrySet.isEmpty());
    }

    @FunctionalInterface
    private interface VoidFunction {
        void r(ApplicationContext context, String name);
    }
}
