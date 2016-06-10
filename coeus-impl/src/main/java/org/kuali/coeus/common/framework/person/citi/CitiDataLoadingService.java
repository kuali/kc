package org.kuali.coeus.common.framework.person.citi;


public interface CitiDataLoadingService {

    /**
     * This service will first clear out the CITI staging and error tables.  It will then call one or more CITI apis,
     * fetch training data and load it into a staging table to be later processed.  At the end of this method,
     * all staging records should be loaded into the staging table with a Staged status and no error messages.
     */
    void loadRecords();
}
