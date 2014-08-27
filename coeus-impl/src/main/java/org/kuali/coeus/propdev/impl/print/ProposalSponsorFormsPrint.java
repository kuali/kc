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
package org.kuali.coeus.propdev.impl.print;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.common.framework.sponsor.form.SponsorFormTemplate;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides the implementation for printing Print Certification
 * Report. It generates XML that conforms with Certification Report XSD, fetches
 * XSL style-sheets applicable to this XML, returns XML and XSL for any consumer
 * that would use this XML and XSls for any purpose like report generation, PDF
 * streaming etc.
 * 
 */

@Component("proposalSponsorFormsPrint")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProposalSponsorFormsPrint extends AbstractPrint {
	private static final String LOCAL_PRINT_FORM_SPONSOR_CODE ="LOCAL_PRINT_FORM_SPONSOR_CODE";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("nihResearchAndRelatedXmlStream")
    private NIHResearchAndRelatedXmlStream nihResearchAndRelatedXmlStream;

    @Autowired
    @Qualifier("proposalDevelopmentXmlStream")
	private ProposalDevelopmentXmlStream proposalDevelopmentXmlStream;
	/**
	 * This method fetches the XSL style-sheets required for transforming the
	 * generated XML into PDF.
	 * 
	 * @return {@link ArrayList}} of {@link Source} XSLs
	 */
	public Map<String,Source> getXSLTemplateWithBookmarks() {
		Map<String,Source> sourceMap = new LinkedHashMap<String,Source>(); 
		List<SponsorFormTemplate> printFormTemplates = (List<SponsorFormTemplate>)this. getReportParameters().get(ProposalDevelopmentPrintingServiceImpl.SELECTED_TEMPLATES);
		
		for (SponsorFormTemplate sponsorFormTemplate : printFormTemplates) {
		    SponsorFormTemplate sponsorTemplate = (SponsorFormTemplate) getBusinessObjectService().findBySinglePrimaryKey(SponsorFormTemplate.class, 
		            sponsorFormTemplate.getSponsorFormTemplateId());
		    sourceMap.put(sponsorFormTemplate.getPageDescription(),new StreamSource(new ByteArrayInputStream(sponsorTemplate.getAttachmentContent())));
        }
		return sourceMap;
	}

    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

	public ProposalDevelopmentXmlStream getProposalDevelopmentXmlStream() {
		return proposalDevelopmentXmlStream;
	}

	public void setProposalDevelopmentXmlStream(
			ProposalDevelopmentXmlStream proposalDevelopmentXmlStream) {
		this.proposalDevelopmentXmlStream = proposalDevelopmentXmlStream;
	}
	
	@Override
	public Map<String, byte[]> renderXML() throws PrintingException {
		DevelopmentProposal developmentProposal=(DevelopmentProposal) getPrintableBusinessObject();
		if (developmentProposal.getSponsorCode().equals(getProposalParameterValue(LOCAL_PRINT_FORM_SPONSOR_CODE))){
			setXmlStream(proposalDevelopmentXmlStream);
		} else {
			setXmlStream(nihResearchAndRelatedXmlStream);
		}
			
		return super.renderXML();
	}
	
	private String getProposalParameterValue(String param) {
		String value = null;
		try {
			value = PrintingUtils.getParameterValue(param);
		} catch (Exception e) {
			//TODO Log Exception
		}
		return value;
	}

	public NIHResearchAndRelatedXmlStream getNihResearchAndRelatedXmlStream() {
		return nihResearchAndRelatedXmlStream;
	}

	public void setNihResearchAndRelatedXmlStream(
			NIHResearchAndRelatedXmlStream nihResearchAndRelatedXmlStream) {
		this.nihResearchAndRelatedXmlStream = nihResearchAndRelatedXmlStream;
	}
}
