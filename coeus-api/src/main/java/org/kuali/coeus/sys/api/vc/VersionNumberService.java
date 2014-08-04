package org.kuali.coeus.sys.api.vc;

public interface VersionNumberService {

    /**
     * Retrieves the version number of the Kuali Coeus Application.  This method should return a
     * String that represents a semantically versioned number and in accordance to the maven artifact
     * versioning rules.
     *
     * @return a version number.  Will not return null or a blank string.
     * @throws java.lang.IllegalStateException if the version number is blank.  This represents a system
     * misconfiguration
     */
    String getVersion();

    /**
     * Checks if the version is less than the version returned from {@link #getVersion()}.
     * @param version the version to check.  cannot be blank.
     * @throws java.lang.IllegalArgumentException if the version is blank
     */
    boolean lessThan(String version);

    /**
     * Checks if the version is greater than the version returned from {@link #getVersion()}.
     * @param version the version to check.  cannot be blank.
     * @throws java.lang.IllegalArgumentException if the version is blank
     */
    boolean greaterThan(String version);

    /**
     * Checks if the version is equal to the version returned from {@link #getVersion()}.
     * @param version the version to check.  cannot be blank.
     * @throws java.lang.IllegalArgumentException if the version is blank
     */
    boolean equalTo(String version);
}
