package org.kuali.coeus.propdev.impl.s2s;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import static org.junit.Assert.*;


public class S2SConfigurationServiceImplTest {

    private static final String S2S_NMSPC_CD = "KC-S2S";
    private static final String S2S_CMPNT_CD = "All";

    private Mockery context;

    @Before()
    public void setUpMockery() {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getConfigurationValue_null_name() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        configService.getValueAsString(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getConfigurationValue_blank_name() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        configService.getValueAsString(" ");
    }

    @Test
    public void test_getConfigurationValue_no_parameter_no_xml_config() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";

        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(null));

            oneOf(configurationService).getPropertyValueAsString(name);
            will(returnValue(null));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertNull(value);
    }

    @Test
    public void test_getConfigurationValue_no_parameter_xml_config_present() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";
        final String xmlVal = "A_PARAM_NAME";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(null));

            oneOf(configurationService).getPropertyValueAsString(name);
            will(returnValue(xmlVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(value, xmlVal);
    }

    //this tests that the db defined parameters take precedence as long as the parameter is not null
    @Test
    public void test_getConfigurationValue_parameter_present() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";
        final String paramVal = "A_PARAM_NAME";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(value, paramVal);
    }

    //this tests that the db defined parameters take precedence as long as the parameter is not null even when blank
    @Test
    public void test_getConfigurationValue_parameter_present_but_blank() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";
        final String paramVal = " ";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(value, paramVal);
    }

    @Test
    public void test_getConfigurationValue_parameter_present_with_placeholder() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";
        final String paramVal = "A PARAM @{#param('KC-AB', 'Document', 'AWARD_BUDGET_POST_ENABLED')} VALUE";
        final String nestedParamVal = "NESTED";
        final String resolvedParamVal = "A PARAM NESTED VALUE";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
            oneOf(parameterService).getParameterValueAsString("KC-AB", "Document", "AWARD_BUDGET_POST_ENABLED");
            will(returnValue(nestedParamVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(resolvedParamVal, value);
    }

    @Test
    public void test_getConfigurationValue_parameter_present_with_placeholder_double_quote() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";
        final String paramVal = "A PARAM @{#param(\"KC-AB\", \"Document\", \"AWARD_BUDGET_POST_ENABLED\")} VALUE";
        final String nestedParamVal = "NESTED";
        final String resolvedParamVal = "A PARAM NESTED VALUE";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
            oneOf(parameterService).getParameterValueAsString("KC-AB", "Document", "AWARD_BUDGET_POST_ENABLED");
            will(returnValue(nestedParamVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(resolvedParamVal, value);
    }

    @Test
    public void test_getConfigurationValue_parameter_present_with_placeholder_mixed_quote() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";
        final String paramVal = "A PARAM @{#param('KC-AB', \"Document', \"AWARD_BUDGET_POST_ENABLED\")} VALUE";
        final String nestedParamVal = "NESTED";
        final String resolvedParamVal = "A PARAM NESTED VALUE";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
            oneOf(parameterService).getParameterValueAsString("KC-AB", "Document", "AWARD_BUDGET_POST_ENABLED");
            will(returnValue(nestedParamVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(resolvedParamVal, value);
    }

    @Test
    public void test_getConfigurationValue_parameter_present_with_placeholder_multiple() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";

        final String paramVal = "A PARAM @{#param('KC-AB', 'Document', 'FOO_PARM')} @{#param('KC-AB', 'Document', 'BAR_PARM')} VALUE";
        final String nestedParamVal1 = "FOO";
        final String nestedParamVal2 = "BAR";
        final String resolvedParamVal = "A PARAM FOO BAR VALUE";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
            oneOf(parameterService).getParameterValueAsString("KC-AB", "Document", "FOO_PARM");
            will(returnValue(nestedParamVal1));
            oneOf(parameterService).getParameterValueAsString("KC-AB", "Document", "BAR_PARM");
            will(returnValue(nestedParamVal2));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(resolvedParamVal, value);
    }

    @Test
    public void test_getConfigurationValue_parameter_present_with_placeholder_multiple_same() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";

        final String paramVal = "A PARAM @{#param('KC-AB', 'Document', 'AWARD_BUDGET_POST_ENABLED')} @{#param('KC-AB', 'Document', 'AWARD_BUDGET_POST_ENABLED')} VALUE";
        final String nestedParamVal = "NESTED";
        final String resolvedParamVal = "A PARAM NESTED NESTED VALUE";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
            oneOf(parameterService).getParameterValueAsString("KC-AB", "Document", "AWARD_BUDGET_POST_ENABLED");
            will(returnValue(nestedParamVal));
            oneOf(parameterService).getParameterValueAsString("KC-AB", "Document", "AWARD_BUDGET_POST_ENABLED");
            will(returnValue(nestedParamVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(resolvedParamVal, value);
    }

    @Test(expected = IllegalStateException.class)
    public void test_getConfigurationValue_parameter_present_with_placeholder_cycle() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";

        final String paramVal = "A PARAM @{#param('KC-S2S', 'All', 'A_PARAM_NAME')} VALUE";
        context.checking(new Expectations() {{
            allowing(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        configService.getValueAsString(name);
    }

    @Test(expected = IllegalStateException.class)
    public void test_getConfigurationValue_parameter_present_with_placeholder_direct_cycle() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";
        final String paramVal = "@{#param('KC-S2S', 'All', 'A_PARAM_NAME')}";
        context.checking(new Expectations() {{
            allowing(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        configService.getValueAsString(name);
    }

    @Test(expected = IllegalStateException.class)
    public void test_getConfigurationValue_parameter_present_with_placeholder_indirect_cycle() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";

        final String paramVal = "@{#param('KC-S2S', 'All', 'OTHER_PARAM')}";

        final String name2 = "OTHER_PARAM";
        final String paramVal2 = "@{#param('KC-S2S', 'All', 'A_PARAM_NAME')}";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name2);
            will(returnValue(paramVal2));
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
            ignoring(parameterService);
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        configService.getValueAsString(name);
    }

    @Test
    public void test_getConfigurationValue_parameter_present_with_placeholder_null_placeholder_value() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";

        final String paramVal = "A PARAM @{#param('KC-AB', 'Document', 'AWARD_BUDGET_POST_ENABLED')} VALUE";
        final String resolvedParamVal = "A PARAM  VALUE";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
            oneOf(parameterService).getParameterValueAsString("KC-AB", "Document", "AWARD_BUDGET_POST_ENABLED");
            will(returnValue(null));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(resolvedParamVal, value);
    }

    @Test
    public void test_getConfigurationValue_parameter_present_with_placeholder_malformed() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";
        final String paramVal = "A PARAM @{param('KC-AB', 'Document', 'AWARD_BUDGET_POST_ENABLED')} VALUE";
        final String resolvedParamVal = paramVal;
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(resolvedParamVal, value);
    }

    @Test
    public void test_getConfigurationValue_parameter_present_with_placeholder_comma_trailing_nospace() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";
        final String paramVal = "A PARAM @{#param('KC-AB', 'Document', 'AWARD_BUDGET_POST_ENABLED',)} VALUE";
        final String nestedParamVal = "NESTED";
        final String resolvedParamVal = "A PARAM NESTED VALUE";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
            oneOf(parameterService).getParameterValueAsString("KC-AB", "Document", "AWARD_BUDGET_POST_ENABLED");
            will(returnValue(nestedParamVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(resolvedParamVal, value);
    }

    @Test
    public void test_getConfigurationValue_parameter_present_with_placeholder_comma_notrailing_nospace() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";
        final String paramVal = "A PARAM @{#param('KC-AB', 'Document', 'AWARD_BUDGET_POST_ENABLED')} VALUE";
        final String nestedParamVal = "NESTED";
        final String resolvedParamVal = "A PARAM NESTED VALUE";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
            oneOf(parameterService).getParameterValueAsString("KC-AB", "Document", "AWARD_BUDGET_POST_ENABLED");
            will(returnValue(nestedParamVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(resolvedParamVal, value);
    }

    @Test
    public void test_getConfigurationValue_parameter_present_with_placeholder_comma_notrailing_space() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";
        final String paramVal = "A PARAM @{#param('KC-AB', 'Document', 'AWARD_BUDGET_POST_ENABLED' )} VALUE";
        final String nestedParamVal = "NESTED";
        final String resolvedParamVal = "A PARAM NESTED VALUE";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
            oneOf(parameterService).getParameterValueAsString("KC-AB", "Document", "AWARD_BUDGET_POST_ENABLED");
            will(returnValue(nestedParamVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(resolvedParamVal, value);
    }

    @Test
    public void test_getConfigurationValue_parameter_present_with_placeholder_space() {
        final S2SConfigurationServiceImpl configService = new S2SConfigurationServiceImpl();
        final ParameterService parameterService = context.mock(ParameterService.class);
        final ConfigurationService configurationService = context.mock(ConfigurationService.class);

        final String name = "A_PARAM_NAME";
        final String paramVal = "A PARAM @{#param( 'KC-AB', 'Document', 'AWARD_BUDGET_POST_ENABLED' )} VALUE";
        final String nestedParamVal = "NESTED";
        final String resolvedParamVal = "A PARAM NESTED VALUE";
        context.checking(new Expectations() {{
            oneOf(parameterService).getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
            will(returnValue(paramVal));
            oneOf(parameterService).getParameterValueAsString("KC-AB", "Document", "AWARD_BUDGET_POST_ENABLED");
            will(returnValue(nestedParamVal));
        }});
        configService.setParameterService(parameterService);
        configService.setConfigurationService(configurationService);

        final String value = configService.getValueAsString(name);

        assertEquals(resolvedParamVal, value);
    }
}
