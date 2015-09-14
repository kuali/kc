package org.kuali.coeus.sys.framework.mq;

import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.io.Serializable;

/**
 * This is a convenience class to make it easier to construct jms message objects without having to deal with
 * jms checked exceptions.  It also, abstracts away JMS implementation specific code.
 */
public final class MessageFactory {

    private MessageFactory() {
        throw new UnsupportedOperationException("do not call");
    }

    /**
     * Takes a Serializable object and wraps it in a Object Message.
     *
     * @param obj the serializable message
     * @return the jms compliant message
     * @throws IllegalArgumentException if the object is null
     */
    public static ObjectMessage createObjectMessage(Serializable obj) {
        if (obj == null) {
            throw new IllegalArgumentException("cannot be null");
        }

        ObjectMessage message = new ActiveMQObjectMessage();
        try {
            message.setObject(obj);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        return message;
    }
}
