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
package org.kuali.coeus.common.questionnaire.framework.print;

import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.protocol.actions.print.CorrespondencePrintOption;

import java.util.List;

public interface CorrespondencePrintingService {
    /**
     * 
     * Provides a hook to fetch correspondences' {@code Printable}s.
     * @param printableBusinessObject
     * @param correspondenceToPrint List of selected Correspondences to print, in the form of {@code CorrespondencePrintOption}s
     * @return List of printable correspondences.
     */
    public List<Printable> getCorrespondencePrintable(KcPersistableBusinessObjectBase printableBusinessObject,
            List<CorrespondencePrintOption> correspondenceToPrint);
}
