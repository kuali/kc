/*
 * Copyright 2006-2008 The Kuali Foundation
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

/**
 * ProtocolNumberService.
 */
public interface ProtocolNumberService {

    /**
     * Generate a unique Protocol Number.  The Protocol Number
     * has the following format:
     * 
     *     YYDDxxxxxx
     *     
     * where YY is the year, e.g. 09.
     *       MM is the month, e.g. 03 for March
     *       xxxxxx is a sequence number
     *       
     * @return a protocol number
     */
    public String generateProtocolNumber();
}
