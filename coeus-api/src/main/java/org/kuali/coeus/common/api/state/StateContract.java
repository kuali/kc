package org.kuali.coeus.common.api.state;

import org.kuali.coeus.sys.api.model.Coded;

public interface StateContract extends Coded {

    String getName();
    String getCountryCode();
}
