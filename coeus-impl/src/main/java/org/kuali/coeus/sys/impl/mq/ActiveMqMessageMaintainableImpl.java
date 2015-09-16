package org.kuali.coeus.sys.impl.mq;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.springframework.jms.core.JmsOperations;

import javax.jms.*;


public class ActiveMqMessageMaintainableImpl extends KualiMaintainableImpl{

    private static final String RESEND = "Resend";
    private transient JmsOperations jmsOperations;

    @Override
    public void doRouteStatusChange(DocumentHeader documentHeader) {
        super.doRouteStatusChange(documentHeader);
        final WorkflowDocument workflowDocument = documentHeader.getWorkflowDocument();

        if (workflowDocument.isProcessed() && RESEND.equals(getMaintenanceAction())) {
            final Message message  = ((ActiveMqMessage) getBusinessObject()).getJmsMessage();
            getJmsOperations().execute((session, producer) -> {
                    producer.send(session.createQueue(((ActiveMqMessage) getBusinessObject()).getOriginalQueueName()),
                            session.createObjectMessage(((ObjectMessage) message).getObject()));
                return null;
            });
            //consuming the message off the queue using jms is not removing it from the queue.  deleting directly.
            this.deleteBusinessObject();
        }
    }

    public JmsOperations getJmsOperations() {
        if (jmsOperations == null) {
            jmsOperations = KcServiceLocator.getService("genericJmsOperations");
        }

        return jmsOperations;
    }

    public void setJmsOperations(JmsOperations jmsOperations) {
        this.jmsOperations = jmsOperations;
    }


    //overriding saves because by default the business object is saved when workflow is processed
    @Override
    public void saveBusinessObject() {
        if (!RESEND.equals(getMaintenanceAction())) {
            super.saveBusinessObject();
        }
    }

    @Override
    public void saveDataObject() {
        if (!RESEND.equals(getMaintenanceAction())) {
            super.saveDataObject();
        }
    }
}
