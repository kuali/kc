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
package org.kuali.kra.common.printing;

import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.protocol.actions.print.CorrespondencePrintOption;

public interface CorrespondencePrintingService {
    /**
     * 
     * Provides a hook to fetch correspondences' {@code Printable}s.
     * @param printableBusinessObject
     * @param correspondenceToPrint List of selected Correspondences to print, in the form of {@code CorrespondencePrintOption}s
     * @return List of printable correspondences.
     */
    public List<Printable> getCorrespondencePrintable(KraPersistableBusinessObjectBase printableBusinessObject, 
            List<CorrespondencePrintOption> correspondenceToPrint);
}
