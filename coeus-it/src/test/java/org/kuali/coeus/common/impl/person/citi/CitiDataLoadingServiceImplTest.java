package org.kuali.coeus.common.impl.person.citi;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.citi.CitiDataLoadingService;
import org.kuali.coeus.common.framework.person.citi.PersonTrainingCitiRecord;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.kra.test.infrastructure.lifecycle.KcIntegrationTestBaseLifecycle;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.BusinessObjectService;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;

import static org.kuali.kra.test.infrastructure.lifecycle.KcIntegrationTestBaseLifecycle.BROWSER_ADDRESS;
import static org.kuali.kra.test.infrastructure.lifecycle.KcIntegrationTestBaseLifecycle.BROWSER_PROTOCOL;
import static org.kuali.kra.test.infrastructure.lifecycle.KcIntegrationTestBaseLifecycle.PORTAL_ADDRESS;

public class CitiDataLoadingServiceImplTest extends KcIntegrationTestBase {

    private static final int DEMO_RECORD_COUNT = 17;

    private BusinessObjectService businessObjectService;
    private CitiDataLoadingService citiDataLoadingService;
    private ConfigurationService configurationService;

    @Before
    public void init() {
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        citiDataLoadingService = KcServiceLocator.getService(CitiDataLoadingService.class);
        configurationService = KcServiceLocator.getService("kualiConfigurationService");

        setConfigProperty("citi.endpoints", "");
        setConfigProperty("citi.delimiter", "\t");

        businessObjectService.deleteMatching(PersonTrainingCitiRecord.class, Collections.emptyMap());
    }

    @Test
    public void test_basic_load_csv() {
        test_basic_load_file("CITI-Demo.csv", ",");
    }

    @Test
    public void test_basic_load_tsv() {
        test_basic_load_file("CITI-Demo.tsv", "\t");
    }

    public void test_basic_load_file(String fileName, String delimiter) {
        final String baseUrl = BROWSER_PROTOCOL + "://" + BROWSER_ADDRESS + ":" + KcIntegrationTestBaseLifecycle.getPort() + "/" + PORTAL_ADDRESS;
        setConfigProperty("citi.endpoints", baseUrl + "/static/citi/demo/" + fileName);
        setConfigProperty("citi.delimiter", delimiter);

        int count = businessObjectService.countMatching(PersonTrainingCitiRecord.class, Collections.emptyMap());

        Assert.assertEquals(0, count);

        citiDataLoadingService.loadRecords();

        count = businessObjectService.countMatching(PersonTrainingCitiRecord.class, Collections.emptyMap());

        Assert.assertEquals(DEMO_RECORD_COUNT, count);
    }

    private void setConfigProperty(String key, String value)  {
        try {
            final Field propertyHolderField = configurationService.getClass().getDeclaredField("propertyHolder");
            propertyHolderField.setAccessible(true);

            final Object propertyHolder = propertyHolderField.get(configurationService);

            final Method clearProperty = propertyHolder.getClass().getMethod("clearProperty", String.class);
            clearProperty.setAccessible(true);
            clearProperty.invoke(propertyHolder, key);

            final Method setProperty = propertyHolder.getClass().getMethod("setProperty", String.class, String.class);
            setProperty.setAccessible(true);
            setProperty.invoke(propertyHolder, key, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
