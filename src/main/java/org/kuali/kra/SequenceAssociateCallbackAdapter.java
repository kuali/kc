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
package org.kuali.kra;

import java.util.List;


/**
 * This class is an adapter for SeparatelySequenceableAssociateAssignmentCallback
 */
public class SequenceAssociateCallbackAdapter 
                implements SeparatelySequenceableAssociateAssignmentCallback {

    /**
     * Override to implement
     * @see org.kuali.kra.SeparatelySequenceableAssociateAssignmentCallback#assign(org.kuali.kra.SeparatelySequenceableAssociate)
     */
    public void assign(SequenceOwner newOwner, SeparatelySequenceableAssociate newAssociate) {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.kuali.kra.SeparatelySequenceableAssociateAssignmentCallback#assign(java.util.List)
     */
    public void assign(SequenceOwner newOwner, List<SeparatelySequenceableAssociate> newAssociate) {
        throw new UnsupportedOperationException();
    }

}
