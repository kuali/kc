package org.kuali.coeus.s2sgen.api.generate;

import java.util.Map;
import java.util.Set;

/**
 * This service retrieves information about the currently configured form
 * generation.
 */
public interface FormMappingService {

    /**
     * Gets form generation info for a specific namespace. the namespace cannot be blank.
     *
     * @param namespace the namespace.  cannot be blank.
     * @return the form generation info or null if it cannot be found
     * @throws java.lang.IllegalArgumentException if the namespace is blank
     */
    FormMappingInfo getFormInfo(String namespace);

    /**
     * Gets form generation info for a specific namespace and proposal number.
     * the namespace and proposal number cannot be blank.
     *
     * @param namespace the namespace.  cannot be blank.
     * @param proposalNumber the proposal number for an existing proposal. cannot be blank.
     * @return the form generation info or null if it cannot be found
     * @throws java.lang.IllegalArgumentException if the namespace or proposalNumber is blank
     */
    FormMappingInfo getFormInfo(String namespace, String proposalNumber);

    /**
     * Gets form generation info for all namespaces, keyed by namespace.
     * @return returns a map.  always non-null
     */
    Map<String, FormMappingInfo> getBindings();

    /**
     * Gets form generation info for all namespaces, keyed by namespace.
     * @return returns a map.  always non-null
     */
    Map<Integer, Set<String>> getSortedNameSpaces();


    void registerForm(FormMappingInfo info);
}
