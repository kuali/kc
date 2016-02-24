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

import java.io.Serializable;

/**
 * This interface describes a message producer for a specific message type.
 * @param <T> the message type.
 */
public interface Producer<T extends Serializable> {

    /**
     * Sends a message of type type.  The message cannot be null.
     * @param message The message object.  This object is not a JMS type but rather the pieve of the message that is
     *                 wrapped by a JMS type
     *
     * @throws IllegalArgumentException if the message is null or not valid
     */
    void sendMessage(final T message);
}