package org.kuali.coeus.propdev.api.editable;

public interface ProposalChangedDataContract {

    Integer getChangeNumber();

    String getColumnName();

    String getProposalNumber();

    String getChangedValue();
    
    String getComments();
    
    String getDisplayValue();
    
    String getOldDisplayValue();

    ProposalColumnsToAlterContract getEditableColumn();
}
