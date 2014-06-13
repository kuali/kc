package org.kuali.coeus.propdev.api.person;

import java.util.Date;

public interface ProposalInvestigatorCertificationContract extends ProposalPersoned {

    Boolean isCertified();

    Date getDateCertified();

    Date getDateReceivedByOsp();
}
