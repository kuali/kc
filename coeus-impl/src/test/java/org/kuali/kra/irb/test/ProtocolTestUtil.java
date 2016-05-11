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
package org.kuali.kra.irb.test;

import org.jmock.Mockery;
import org.kuali.kra.irb.Protocol;

/**
 * Utility for working with Protocol objects for testing.
 */
public final class ProtocolTestUtil {
    
    /** private ctor for utility class. */
    private ProtocolTestUtil() {
        throw new UnsupportedOperationException("do not call");
    }
    
    /**
     * Gets a Protocol that does try to access Spring services.
     * @param context a JMock context used for mocking used services.
     * @return a protocol
     */
    public static Protocol getProtocol(final Mockery context) {
        
        final Protocol protocol = new Protocol() {
            
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing
            }
        };
        
        return protocol;
    }
}
