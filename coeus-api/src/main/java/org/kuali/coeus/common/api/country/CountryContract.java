package org.kuali.coeus.common.api.country;

import org.kuali.coeus.sys.api.model.Coded;

public interface CountryContract extends Coded {

    String getAlternateCode();
    String getName();
}
