/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.bo;

import org.kuali.rice.krad.bo.BusinessObject;

import java.util.List;

/**
 * Used to describe a <code>{@link BusinessObject}</code> with credit that can be split. Usually,
 * this is a <code>{@link org.kuali.coeus.propdev.impl.person.ProposalPerson}</code> or the like.
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.4 $
 */
public interface CreditSplitable {
    /**
     * Get a <code>{@link List}</code> of credit splits
     *
     * @return List<T>
     */ 
    public List<? extends CreditSplit> getCreditSplits();
  
}
