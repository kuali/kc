/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.person.creditsplit;

import org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplit;
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
     * @return List&lt;T&gt;
     */ 
    public List<? extends CreditSplit> getCreditSplits();
  
}
