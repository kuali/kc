/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.irb.protocol;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.protocol.ProtocolNumberServiceImplBase;
import org.kuali.rice.krad.service.SequenceAccessorService;

import java.util.Calendar;

/**
 * ProtocolNumberService Implementation.
 */
public class ProtocolNumberServiceImpl extends ProtocolNumberServiceImplBase implements ProtocolNumberService {

    private static final String ZERO = "0";
    private static final String SEQUENCE_NAME = "SEQ_PROTOCOL_ID";
    private static final int MAX_NUMBER = 1000000;

    @Override
    protected String getSequenceNameHook() {
        return SEQUENCE_NAME;
    }
    
    protected Class getSequenceOwnerClass() {
        return Protocol.class;
    }
    
    
}