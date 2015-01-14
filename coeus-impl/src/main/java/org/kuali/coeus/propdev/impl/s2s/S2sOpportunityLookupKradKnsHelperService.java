package org.kuali.coeus.propdev.impl.s2s;


import java.util.List;

public interface S2sOpportunityLookupKradKnsHelperService {

    public List<S2sOpportunity> performSearch(String providerCode, String cfdaNumber, String opportunityId);
}
