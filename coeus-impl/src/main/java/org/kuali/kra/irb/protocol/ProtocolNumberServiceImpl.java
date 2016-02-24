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
package org.kuali.kra.irb.protocol;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.protocol.ProtocolNumberServiceImplBase;

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
