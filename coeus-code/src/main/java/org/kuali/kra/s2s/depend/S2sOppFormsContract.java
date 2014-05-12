package org.kuali.kra.s2s.depend;


public interface S2sOppFormsContract {

    String getOppNameSpace();

    String getProposalNumber();

    Boolean getAvailable();

    String getFormName();

    Boolean getInclude();

    Boolean getMandatory();

    String getSchemaUrl();

    Boolean getSelectToPrint();

    Boolean getUserAttachedForm();
}
