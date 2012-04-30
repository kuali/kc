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
package org.kuali.kra.iacuc.actions;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.protocol.actions.ProtocolActionType;

public class IacucProtocolActionType extends ProtocolActionType { 
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 162958759286804034L;
    
    public static final String PROTOCOL_CREATED = "100";
    public static final String APPROVED = "204";
    public static final String DISAPPROVED = "301";
    public static final String MAJOR_REVISIONS_REQUIRED = "211";
    public static final String REVISIONS_REQUIRED = "213";

    public static List<String>getActionTypeSubmissionDocs() {
        return ACTION_TYPE_SUBMISSION_DOC;
    }
    protected static final List<String> ACTION_TYPE_SUBMISSION_DOC;
    static {
      final List<String> codes = new ArrayList<String>();     
      codes.add(PROTOCOL_CREATED);
      codes.add(APPROVED);
      codes.add(DISAPPROVED);
      codes.add(MAJOR_REVISIONS_REQUIRED);
      codes.add(REVISIONS_REQUIRED);
      ACTION_TYPE_SUBMISSION_DOC = codes;
  }

}