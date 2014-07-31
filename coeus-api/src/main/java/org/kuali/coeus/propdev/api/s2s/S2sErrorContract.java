package org.kuali.coeus.propdev.api.s2s;

import org.kuali.coeus.sys.api.model.Identifiable;

public interface S2sErrorContract extends Identifiable {

    String getKey();

    String getMessage();

    String getLink();
}
