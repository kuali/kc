package org.kuali.coeus.common.api.custom.attr;

import org.kuali.coeus.sys.api.model.DocumentTyped;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;
import org.kuali.coeus.sys.api.model.Inactivatable;

public interface CustomAttributeDocumentContract extends IdentifiableNumeric, DocumentTyped, Inactivatable {

    boolean isRequired();
    String getTypeName();
    CustomAttributeContract getCustomAttribute();
}
