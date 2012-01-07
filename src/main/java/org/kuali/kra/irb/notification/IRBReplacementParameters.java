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
package org.kuali.kra.irb.notification;

/**
 * 
 * This class defines the replacement parameters available for the IRB coeus module
 */
public class IRBReplacementParameters {

    public static final String DOCUMENT_NUMBER = "{DOCUMENT_NUMBER}";
    public static final String PROTOCOL_NUMBER = "{PROTOCOL_NUMBER}";
    public static final String SEQUENCE_NUMBER = "{SEQUENCE_NUMBER}";
    public static final String PROTOCOL_TITLE = "{PROTOCOL_TITLE}";
    public static final String PI_NAME = "{PI_NAME}";
    public static final String LEAD_UNIT = "{LEAD_UNIT}";
    public static final String LEAD_UNIT_NAME = "{LEAD_UNIT_NAME}";
    public static final String LAST_SUBMISSION_TYPE_CODE = "{LAST_SUBMISSION_TYPE_CODE}";
    public static final String LAST_SUBMISSION_NAME = "{LAST_SUBMISSION_NAME}";
    public static final String LAST_SUBMISSION_TYPE_QUAL_CODE = "{LAST_SUBMISSION_TYPE_QUAL_CODE}";
    public static final String LAST_SUBMISSION_TYPE_QUAL_NAME = "{LAST_SUBMISSION_TYPE_QUAL_NAME}";
    public static final String LAST_ACTION_TYPE_CODE = "{LAST_ACTION_TYPE_CODE}";
    public static final String LAST_ACTION_NAME = "{LAST_ACTION_NAME}";
    public static final String PROTOCOL_TYPE_CODE = "{PROTOCOL_TYPE_CODE}";
    public static final String PROTOCOL_TYPE_DESCRIPTION = "{PROTOCOL_TYPE_DESCRIPTION}";
    public static final String PROTOCOL_STATUS_CODE = "{PROTOCOL_STATUS_CODE}";
    public static final String PROTOCOL_STATUS_DESCRIPTION = "{PROTOCOL_STATUS_DESCRIPTION}";
    public static final String SUBMISSION_STATUS_CODE = "{SUBMISSION_STATUS_CODE}";
    public static final String SUBMISSION_STATUS_NAME = "{SUBMISSION_STATUS_NAME}";
    public static final String PROTOCOL_REVIEW_TYPE_DESC = "{PROTOCOL_REVIEW_TYPE_DESC}";
    public static final String COMMITTEE_NAME = "{COMMITTEE_NAME}";
    // parameters below were added for watermark subtext replacement
    public static final String PROTOCOL_INITIAL_APPROVAL_DATE = "{PROTOCOL_INITIAL_APPROVAL_DATE}";
    public static final String PROTOCOL_LAST_APPROVAL_DATE = "{PROTOCOL_LAST_APPROVAL_DATE}";
    public static final String PROTOCOL_EXPIRATION_DATE = "{PROTOCOL_EXPIRATION_DATE}";
    
    public static final String[] REPLACEMENT_PARAMETERS = new String[] { DOCUMENT_NUMBER,
                                                                         PROTOCOL_NUMBER, 
                                                                         SEQUENCE_NUMBER, 
                                                                         PROTOCOL_TITLE,
                                                                         PI_NAME, 
                                                                         LEAD_UNIT, 
                                                                         LEAD_UNIT_NAME, 
                                                                         LAST_SUBMISSION_TYPE_CODE,
                                                                         LAST_SUBMISSION_NAME, 
                                                                         LAST_SUBMISSION_TYPE_QUAL_CODE,
                                                                         LAST_SUBMISSION_TYPE_QUAL_NAME,
                                                                         LAST_ACTION_TYPE_CODE,
                                                                         LAST_ACTION_NAME,
                                                                         PROTOCOL_TYPE_CODE,
                                                                         PROTOCOL_TYPE_DESCRIPTION,
                                                                         PROTOCOL_STATUS_CODE,
                                                                         PROTOCOL_STATUS_DESCRIPTION,
                                                                         SUBMISSION_STATUS_CODE,
                                                                         SUBMISSION_STATUS_NAME,
                                                                         PROTOCOL_REVIEW_TYPE_DESC,
                                                                         COMMITTEE_NAME, 
                                                                         // parameters below were added for watermark text
                                                                         PROTOCOL_INITIAL_APPROVAL_DATE,
                                                                         PROTOCOL_LAST_APPROVAL_DATE,
                                                                         PROTOCOL_EXPIRATION_DATE
                                                                       };
    
}
