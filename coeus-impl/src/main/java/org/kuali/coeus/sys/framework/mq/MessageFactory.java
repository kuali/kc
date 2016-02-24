/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2016 Kuali, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
