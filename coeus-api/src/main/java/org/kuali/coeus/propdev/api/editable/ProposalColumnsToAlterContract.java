package org.kuali.coeus.propdev.api.editable;


public interface ProposalColumnsToAlterContract {

    String getColumnName();

    String getColumnLabel();

    Integer getDataLength();

    String getDataType();

    boolean getHasLookup();

    String getLookupClass();

    String getLookupReturn();
}
