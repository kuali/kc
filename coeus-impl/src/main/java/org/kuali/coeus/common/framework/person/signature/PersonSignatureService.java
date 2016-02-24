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
package org.kuali.coeus.common.framework.person.signature;


public interface PersonSignatureService {

    /**
     * 
     * This method is to apply signature to the pdf document.
     * In this method the input pdfBytes are merged with appropriate signature and 
     * the result is returned.
     * @param pdfBytes
     * @return pdfFileData
     * @throws Exception
     */
    byte[] applySignature(byte[] pdfBytes) throws Exception;

}
