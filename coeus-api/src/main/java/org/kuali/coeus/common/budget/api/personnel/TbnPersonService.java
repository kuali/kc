package org.kuali.coeus.common.budget.api.personnel;

public interface TbnPersonService {

    /**
     * This method will retrieves a {@link TbnPersonContract} by id.  The id cannot be null.
     * @param id the id.  Cannot be null.
     * @return the {@link TbnPersonContract} or null if not found.
     * @throws java.lang.IllegalArgumentException if the id is blank
     */
    TbnPersonContract getTbnPerson(String id);
}
