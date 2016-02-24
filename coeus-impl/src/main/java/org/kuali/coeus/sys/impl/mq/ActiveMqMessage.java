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
package org.kuali.coeus.sys.impl.mq;

import org.apache.activemq.openwire.OpenWireFormat;
import org.apache.activemq.util.ByteSequence;
import org.apache.activemq.wireformat.WireFormat;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import java.io.IOException;
import java.io.Serializable;

public class ActiveMqMessage extends PersistableBusinessObjectBase {

    private static final WireFormat WIRE_FORMAT = new OpenWireFormat();

    private Long id;

    private String container;

    private String messageIdProducer;

    private Long messageIdSequence;

    private Long expiration;

    private byte[] message;

    private Long priority;

    private String xid;

    private transient Class<? extends Serializable> type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContainer() {
        return container;
    }

    public String getCurrentQueueName() {
        return getContainer().replaceFirst("queue://", "");
    }

    /**
     * This removes the dead letter queue prefix from the queue name
     */
    public String getOriginalQueueName() {
        return getCurrentQueueName().replaceFirst("DLQ.", "");
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getMessageIdProducer() {
        return messageIdProducer;
    }

    public void setMessageIdProducer(String messageIdProducer) {
        this.messageIdProducer = messageIdProducer;
    }

    public Long getMessageIdSequence() {
        return messageIdSequence;
    }

    public void setMessageIdSequence(Long messageIdSequence) {
        this.messageIdSequence = messageIdSequence;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public Message getJmsMessage() {
        final byte[] msg = getMessage();

        if (msg != null) {
            try {
                return (Message) WIRE_FORMAT.unmarshal(new ByteSequence(msg));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public String getMessageJson() {
        final byte[] msg = getMessage();

        if (msg != null) {
            try {
                final Object oMessage = WIRE_FORMAT.unmarshal(new ByteSequence(msg));
                if (oMessage instanceof ObjectMessage) {
                    final Serializable o = ((ObjectMessage) oMessage).getObject();
                    final ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
                    return writer.writeValueAsString(o);
                }
            } catch (IOException|JMSException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void setMessageJson(String messageJson) {
        if (messageJson != null) {
            try {
                final Serializable o = new ObjectMapper().readValue(messageJson, getType());

                final byte[] msg = getMessage();
                final Object oMessage = WIRE_FORMAT.unmarshal(new ByteSequence(msg));
                if (oMessage instanceof ObjectMessage) {
                    ((ObjectMessage) oMessage).setObject(o);
                }
                setMessage(WIRE_FORMAT.marshal(oMessage).getData());
            } catch (IOException|JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Class<? extends Serializable> getType() {
        final byte[] msg = getMessage();

        if (msg != null && type == null) {
            try {
                final Object oMessage =  WIRE_FORMAT.unmarshal(new ByteSequence(msg));
                if (oMessage instanceof ObjectMessage) {
                    type = ((ObjectMessage) oMessage).getObject().getClass();
                }
            } catch (IOException|JMSException e) {
                throw new RuntimeException(e);
            }
        }

        return type;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }
}
