package org.kuali.kra.s2s.depend;

import org.kuali.kra.bo.ArgValueLookup;

import java.util.List;

public interface ArgValueLookupService {

    /**
     * Retrieves all the {@link ArgValueLookup}s.  Will return an empty
     * List if no items exist.
     * @return a list of {@link ArgValueLookup}s or an empty list.
     */
    List<ArgValueLookup> findAllArgValueLookups();
}
