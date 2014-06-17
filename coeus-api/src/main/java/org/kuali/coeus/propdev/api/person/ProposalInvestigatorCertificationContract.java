package org.kuali.coeus.propdev.api.person;

import java.util.Date;

public interface ProposalInvestigatorCertificationContract extends ProposalPersonLink {

    Boolean isCertified();

    Date getDateCertified();

    Date getDateReceivedByOsp();
}
