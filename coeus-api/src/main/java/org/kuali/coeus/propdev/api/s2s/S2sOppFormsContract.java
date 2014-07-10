package org.kuali.coeus.propdev.api.s2s;


import org.kuali.coeus.propdev.api.core.NumberedProposal;

public interface S2sOppFormsContract extends NumberedProposal {

    String getOppNameSpace();

    Boolean getAvailable();

    String getFormName();

    Boolean getInclude();

    Boolean getMandatory();

    String getSchemaUrl();

    Boolean getSelectToPrint();

    Boolean getUserAttachedForm();
}
