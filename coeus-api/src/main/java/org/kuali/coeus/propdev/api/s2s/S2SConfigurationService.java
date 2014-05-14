package org.kuali.coeus.propdev.api.s2s;

public interface S2SConfigurationService {

    /**
     * This method gets a config value for the s2s application from a config name as a String.
     * The name cannot be blank.  This method will return the config value or null
     * if the value cannot be found.
     *
     * @param name the name to retrieve the value for
     * @return the configuration value or null
     * @throws java.lang.IllegalArgumentException if the name is blank
     */
    String getValueAsString(String name);

    /**
     * This method gets a config value for the s2s application from a config name as a Boolean.
     * The name cannot be blank.  This method will return the config value or null
     * if the value cannot be found.
     *
     * <p>
     *   This method will return null if the parameter does not exist or is not a valid truth value.
     * </p>
     *
     * valid true values (case insensitive):
     * <ul>
     *     <li>Y</li>
     *     <li>true</li>
     *     <li>on</li>
     *     <li>1</li>
     *     <li>t</li>
     *     <li>enabled</li>
     * </ul>
     *
     * valid false values (case insensitive):
     * <ul>
     *     <li>N</li>
     *     <li>false</li>
     *     <li>off</li>
     *     <li>0</li>
     *     <li>f</li>
     *     <li>disabled</li>
     * </ul>
     *
     * @param name the name to retrieve the value for
     * @return the configuration value or null
     * @throws java.lang.IllegalArgumentException if the name is blank
     */
    Boolean getValueAsBoolean(String name);
}
