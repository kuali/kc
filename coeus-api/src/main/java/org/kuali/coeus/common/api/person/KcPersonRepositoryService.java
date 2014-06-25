package org.kuali.coeus.common.api.person;


public interface KcPersonRepositoryService {

    /**
     * Finds a KcPerson by user name.  The user name cannot be blank.  Will return null if not found.
     * @param userName the user name.  cannot be blank.
     * @return the KcPerson or null
     * @throws java.lang.IllegalArgumentException if the user name is blank.
     */
    KcPersonContract findKcPersonByUserName(String userName);
}
