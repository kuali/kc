package org.kuali.coeus.common.api.person.attr;

import org.kuali.coeus.sys.api.model.Describable;
import org.kuali.coeus.sys.api.model.Inactivatable;

public interface CitizenshipTypeContract extends Inactivatable, Describable {
    Integer getCode();
}
