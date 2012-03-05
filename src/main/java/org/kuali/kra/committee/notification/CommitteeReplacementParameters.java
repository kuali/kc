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
package org.kuali.kra.committee.notification;

/**
 * 
 * This class defines the replacement parameters available for the IRB coeus module
 */
public class CommitteeReplacementParameters {

    public static final String DOCUMENT_NUMBER = "{DOCUMENT_NUMBER}";
    public static final String SEQUENCE_NUMBER = "{SEQUENCE_NUMBER}";
    public static final String COMMITTEE_NAME = "{COMMITTEE_NAME}";
    public static final String ACTION_TAKEN = "{ACTION_TAKEN}";
    public static final String LAST_ACTION_DATE = "{LAST_ACTION_DATE}";
    public static final String OBJECT_INDEX = "{OBJECT_INDEX}";

    
    public static final String[] REPLACEMENT_PARAMETERS = new String[] { DOCUMENT_NUMBER,
                                                                         SEQUENCE_NUMBER, 
                                                                         COMMITTEE_NAME,
                                                                         ACTION_TAKEN, 
                                                                         LAST_ACTION_DATE,
                                                                         OBJECT_INDEX
                                                                       };
}
