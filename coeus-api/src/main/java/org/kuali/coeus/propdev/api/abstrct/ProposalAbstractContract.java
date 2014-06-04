package org.kuali.coeus.propdev.api.abstrct;

import java.util.Date;

public interface ProposalAbstractContract {

    String getProposalNumber();

    String getAbstractDetails();

    AbstractTypeContract getAbstractType();

    Date getTimestampDisplay();

    String getUploadUserDisplay();
}
