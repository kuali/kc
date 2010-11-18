/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.actions.submit;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class ProtocolActionServiceTestBase extends KcUnitTestBase {
   
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
