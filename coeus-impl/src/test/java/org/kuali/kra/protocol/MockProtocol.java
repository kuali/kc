package org.kuali.kra.protocol;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.protocol.actions.ProtocolStatusBase;

class MockProtocol extends Protocol {

    @Override
    public void refreshReferenceObject(String referenceObjectName) {
    }

    @Override
    public ProtocolStatusBase getProtocolStatus() {
        return new ProtocolStatus();
    }

}