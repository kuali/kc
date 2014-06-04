package org.kuali.coeus.propdev.api.budget.editable;

public interface BudgetChangedDataContract {

    Integer getChangeNumber();

    String getColumnName();

    String getProposalNumber();

    String getChangedValue();
    
    String getComments();
    
    String getDisplayValue();
    
    String getOldDisplayValue();

    BudgetColumnsToAlterContract getEditableColumn();
}
