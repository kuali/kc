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
package org.kuali.coeus.common.framework.print;

import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

import java.util.List;

/**
 * 
 * This class provides the API for KC Printing. It will take any KC
 * <code>Printable</code> and return the printable PDF form of that Printable
 * in an OutputStream which can be decorated how the implementing print consumer
 * requires.
 * 
 */
public interface PrintingService {
	public int WHITESPACE_LENGTH_76 = 76;
	public int WHITESPACE_LENGTH_60 = 60;

	/**
	 * 
	 * This method invokes the KC printable architecture for reports,
	 * notifications, docs and bos. It will take raw KC XML from bo/docs and
	 * perform the XSLT to generate XML-FO, and will render the Printable XML-FO
	 * as a PDF OutputStream.
	 * 
	 * @throws PrintingException
	 */
	public AttachmentDataSource print(Printable printableArtifact)
			throws PrintingException;

	/**
	 * 
	 * This method invokes the KC printable architecture for reports,
	 * notifications, docs and bos. It will take raw KC XML from bo/docs and
	 * perform the XSLT to generate XML-FO, and will render the {@link List} of
	 * Printable XML-FO as a PDF OutputStream.
	 * 
	 * @throws PrintingException
	 */
	public AttachmentDataSource print(List<Printable> printableArtifactList)
			throws PrintingException;
}
