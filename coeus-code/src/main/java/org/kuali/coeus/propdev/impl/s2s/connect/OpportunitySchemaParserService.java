package org.kuali.coeus.propdev.impl.s2s.connect;

import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;

import java.util.List;

public interface OpportunitySchemaParserService {
    List<S2sOppForms> getForms(String proposalNumber,String schema) throws S2sCommunicationException;
}
