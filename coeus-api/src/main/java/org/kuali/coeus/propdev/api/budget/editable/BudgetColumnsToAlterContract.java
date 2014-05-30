package org.kuali.coeus.propdev.api.budget.editable;


public interface BudgetColumnsToAlterContract {

    String getColumnName();

    String getColumnLabel();

    Integer getDataLength();

    String getDataType();

    boolean getHasLookup();

    String getLookupClass();

    String getLookupReturn();
}
