package org.kuali.coeus.common.framework.sponsor;

import org.kuali.rice.krad.bo.PersistableBusinessObject;


public interface Sponsorable extends PersistableBusinessObject {

    String getSponsorCode();

    void setSponsorCode(String sponsorCode);
}
