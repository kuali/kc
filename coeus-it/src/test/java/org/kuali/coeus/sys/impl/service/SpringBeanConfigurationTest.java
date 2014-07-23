package org.kuali.coeus.sys.impl.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.s2sgen.impl.generate.S2SFormGenerator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.api.resourceloader.ResourceLoader;
import org.kuali.rice.core.framework.resourceloader.SpringResourceLoader;
import org.kuali.rice.kns.lookup.Lookupable;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.krad.uif.service.ViewHelperService;
import org.springframework.beans.factory.BeanIsAbstractException;
import org.springframework.context.ApplicationContext;

import javax.xml.namespace.QName;
import java.lang.reflect.Proxy;
import java.util.*;


public class SpringBeanConfigurationTest extends KcIntegrationTestBase {

    private static final Log LOG = LogFactory.getLog(SpringBeanConfigurationTest.class);

    private static final Collection<String> IGNORE_PATTERN = new ArrayList<String>() {{
        add("org.springframework.aop");
    }};

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
            for (ResourceLoader child : parent.getResourceLoaders()) {
                processResourceLoader(child);
            }
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
        toEachSpringBean(new VoidFunction() {
            @Override
            public void r(ApplicationContext context, String name) {
                context.getBean(name);
            }
        });
    }

    private void toEachSpringBean(VoidFunction function) {
        Map<QName, List<KeyValue<String, Exception>>> failedBeans = new HashMap<>();

        for (SpringResourceLoader r : springResourceLoaders) {
            ApplicationContext context = r.getContext();
            for (String name: context.getBeanDefinitionNames()) {
                if (process(name)) {
                    try {
                        function.r(context, name);
                    } catch (BeanIsAbstractException e) {
                        //ignore since the bean can't be created
                    } catch (RuntimeException e) {
                        LOG.error("bean failed to execute function", e);
                        List<KeyValue<String, Exception>> rlFailures = failedBeans.get(r.getName());
                        if (rlFailures == null) {
                            rlFailures = new ArrayList<>();
                        }
                        rlFailures.add(new DefaultKeyValue<String, Exception>(name, e));
                        failedBeans.put(r.getName(), rlFailures);
                    }
                }
            }
        }

        Assert.assertTrue("the following beans failed to retrieve " + failedBeans, failedBeans.isEmpty());
    }

    private boolean process(String name) {
        for (String pattern : IGNORE_PATTERN) {
            if (name.contains(pattern)) {
                return false;
            }
        }
        return true;
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
                Object o2 = context.getBean(name);
                Assert.assertNotSame("A prototype bean should always return a unique instance, bean: " + name + " of type: " + clazz.getName(), o, o2);
            }
        }
    }

    @Test
    public void test_view_helper_service_are_prototype_scope() {
        toEachSpringBean(new PrototypeVerification(ViewHelperService.class));
    }

    @Test
    public void test_lookupables_are_prototype_scope() {
        toEachSpringBean(new PrototypeVerification(Lookupable.class));
    }

    @Test
    public void test_lookup_helper_service_are_prototype_scope() {
        toEachSpringBean(new PrototypeVerification(LookupableHelperService.class));
    }

    @Test
    public void test_generators_are_prototype_scope() {
        toEachSpringBean(new PrototypeVerification(S2SFormGenerator.class));
    }

    @Test
    public void test_printables_are_prototype_scope() {
        toEachSpringBean(new PrototypeVerification(Printable.class));
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
        toEachSpringBean(new VoidFunction() {
            @Override
            public void r(ApplicationContext context, String name) {
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
            }
        });
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
        toEachSpringBean(new VoidFunction() {
            @Override
            public void r(ApplicationContext context, String name) {
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

            }
        });

        final Set<Map.Entry<String, Set<Class<?>>>> entrySet = new HashSet<>(beans.entrySet());
        CollectionUtils.filter(entrySet, new Predicate<Map.Entry<String, Set<Class<?>>>>() {
            @Override
            public boolean evaluate(Map.Entry<String, Set<Class<?>>> object) {
                return object.getValue().size() > 1;
            }
        });

        Assert.assertTrue("The following bean names are duplicated in different contexts with different class names " + entrySet,
                entrySet.isEmpty());
    }

    private static interface VoidFunction {
        void r(ApplicationContext context, String name);
    }
}
