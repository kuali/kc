package org.kuali.coeus.common.api.custom.attr;

import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

public interface CustomAttributeDocValueContract extends IdentifiableNumeric {

    String getDocumentNumber();
    String getValue();
    CustomAttributeContract getCustomAttribute();
}
