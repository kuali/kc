package org.kuali.kra.s2s.depend;

import org.kuali.coeus.sys.api.model.Coded;
import org.kuali.coeus.sys.api.model.Describable;

public interface NarrativeTypeContract extends Coded, Describable {

    boolean isAllowMultiple();
    boolean isSystemGenerated();
    String getNarrativeTypeGroup();
}
