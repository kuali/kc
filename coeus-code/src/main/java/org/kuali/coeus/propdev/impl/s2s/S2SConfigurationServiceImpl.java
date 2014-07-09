package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.Truth;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Service("s2SConfigurationService")
public class S2SConfigurationServiceImpl implements S2SConfigurationService {

    private static final Log LOG = LogFactory.getLog(S2SConfigurationServiceImpl.class);

    private static final String S2S_NMSPC_CD = "KC-S2S";
    private static final String S2S_CMPNT_CD = "All";
    private static final Pattern PLACEHOLDER = Pattern.compile("@\\{#param\\((.*?)\\)\\}");

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;

    @Override
    public String getValueAsString(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name is blank");
        }

        String config = parameterService.getParameterValueAsString(S2S_NMSPC_CD, S2S_CMPNT_CD, name);
        if (config != null) {
            config = replaceParameterPlaceholders(config);
        } else {
            config = configurationService.getPropertyValueAsString(name);
        }

        return config;
    }

    @Override
    public Boolean getValueAsBoolean(String name) {
        final String val = getValueAsString(name);
        final Boolean b = Truth.strToBooleanIgnoreCase(val);

        if (val != null && b == null) {
            LOG.warn("config: " + name + " with value: " + val + " is not a valid Boolean");
        }

        return b;
    }

    protected String replaceParameterPlaceholders(String parameterValue) {
        return replaceParameterPlaceholders(parameterValue, new HashSet<Parm>());
    }

    protected Parm extractParm(String params) {
        final String[] ps = params.split(",");

        final String namespace = ps[0].replace("'", "").replace("\"", "").trim();
        final String component = ps[1].replace("'", "").replace("\"", "").trim();
        final String name = ps[2].replace("'", "").replace("\"", "").trim();

        return new Parm(namespace,component,name);
    }

    protected void detectCycles(Set<Parm> current, Set<Parm> processed, String parameterValue, String replacedParameterValue) {
        //detect cycle
        for (Parm p : current) {
            if (processed.contains(p)) {
                throw new IllegalStateException("Parameter value contains cycle " + parameterValue);
            } else {
                processed.add(p);
            }
        }

        //detect direct cycle... parameter was valid and the value was replaced but the value is the same
        for (Parm p : current) {
            if (StringUtils.isNotBlank(p.namespace) && StringUtils.isNotBlank(p.component) && StringUtils.isNotBlank(p.name)) {
                if (parameterValue.equals(replacedParameterValue)) {
                    throw new IllegalStateException("Parameter value contains a direct cycle " + parameterValue);
                }
            }
        }
    }

    protected String replaceParameterValues(Set<Parm> current, String parameterValue){
        String replacedParameterValue = parameterValue;

        final Matcher placeholderMatcher =  PLACEHOLDER.matcher(replacedParameterValue);
        while (placeholderMatcher.find()) {

            final String placeholder = placeholderMatcher.group(0);
            final String params = placeholderMatcher.group(1);
            final Parm p = extractParm(params);
            current.add(p);

            if (StringUtils.isNotBlank(p.namespace) && StringUtils.isNotBlank(p.component) & StringUtils.isNotBlank(p.name)) {
                final String value = parameterService.getParameterValueAsString(p.namespace, p.component, p.name);
                replacedParameterValue = replacedParameterValue.replace(placeholder, value != null ? value : "");
            } else {
                LOG.warn("Parameter value contains an invalid reference to another parameter " + parameterValue);
            }
        }
        return replacedParameterValue;
    }

    protected String replaceParameterPlaceholders(String parameterValue, Set<Parm> processed) {
        //these are the current matches for this iteration...  this is used to detect indirect cycles along
        //with the processed Set for previous iterations
        final Set<Parm> current = new HashSet<Parm>();
        final String replacedParameterValue = replaceParameterValues(current, parameterValue);

        detectCycles(current, processed, parameterValue, replacedParameterValue);

        //If the parameter value has been changed then recurse to see if there are more parameters to replace
        if (!parameterValue.equals(replacedParameterValue)) {
            return replaceParameterPlaceholders(replacedParameterValue, processed);
        }
        return replacedParameterValue;
    }

    protected final static class Parm {
        private final String namespace;
        private final String component;
        private final String name;

        public Parm(String namespace, String component, String name) {
            this.namespace = namespace;
            this.component = component;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Parm parm = (Parm) o;

            if (component != null ? !component.equals(parm.component) : parm.component != null) return false;
            if (name != null ? !name.equals(parm.name) : parm.name != null) return false;
            if (namespace != null ? !namespace.equals(parm.namespace) : parm.namespace != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = namespace != null ? namespace.hashCode() : 0;
            result = 31 * result + (component != null ? component.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Parm{" +
                    "namespace='" + namespace + '\'' +
                    ", component='" + component + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
