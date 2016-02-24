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
package org.kuali.kra.irb.actions.submit;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

public class ProtocolActionServiceTestBase extends KcIntegrationTestBase {
   
    public static Protocol getProtocol(final Mockery context) {
        
        final Protocol protocol = new Protocol() {

            private static final long serialVersionUID = -1273061983131550371L;

            @Override
            protected ProtocolLocationService getProtocolLocationService() {
                final Protocol aThis = this;
                final ProtocolLocationService mock = context.mock(ProtocolLocationService.class);
                context.checking(new Expectations() {{
                    allowing(mock).addDefaultProtocolLocation(aThis);
                }});
                return mock;
            }
            
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing
            }
        };
        
        return protocol;
    }
}
