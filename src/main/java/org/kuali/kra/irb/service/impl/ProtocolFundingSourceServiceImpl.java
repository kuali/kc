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
package org.kuali.kra.irb.service.impl;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolFundingSource;
import org.kuali.kra.irb.service.ProtocolFundingSourceService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.FundingSourceTypeService;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.service.UnitService;
import org.springframework.util.StringUtils;

import edu.iu.uis.eden.exception.WorkflowException;

public class ProtocolFundingSourceServiceImpl implements ProtocolFundingSourceService {


    public FundingSourceTypeService getFundingSourceTypeService() {
        return fundingSourceTypeService;
    }

    public void setFundingSourceTypeService(FundingSourceTypeService fundingSourceTypeService) {
        this.fundingSourceTypeService = fundingSourceTypeService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    public void setSponsorService(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    private UnitService unitService;
    private SponsorService sponsorService;
    private DocumentService documentService;
    private FundingSourceTypeService fundingSourceTypeService;
    private BusinessObjectService businessObjectService;

    // ProposalService proposalService;


    public void addDefaultProtocolFundingSource(Protocol protocol) {
        // TODO Auto-generated method stub

    }

    public void addProtocolFundingSource(Protocol protocol) {
         ProtocolFundingSource newProtocolFundingSource = protocol.getNewFundingSource();
         protocol.getProtocolFundingSources().add(newProtocolFundingSource);
         protocol.setNewFundingSource(new ProtocolFundingSource());
    }

    public void deleteProtocolFundingSource(Protocol protocol, int lineNumber) {
         protocol.getProtocolFundingSources().remove(lineNumber);
    }


    public ProtocolFundingSource getNameAndTitle(String sourceType, String sourceId, String sourceName, String sourceTitle) {

        FundingSourceType fundingType = getfundingSourceTypeService().getFundingSourceType(sourceType);
        ProtocolFundingSource source = new ProtocolFundingSource();
        


        if (fundingType != null && StringUtils.hasText(sourceId)) {
            source.setFundingSourceName(sourceName);
            source.setFundingSourceTitle(sourceTitle);
            
            
            if (fundingType.getDescription().equalsIgnoreCase("Sponsor")) {
                String name = getSponsorService().getSponsorName(sourceId);
                source.setFundingSourceTitle("");
                source.setFundingSourceName(name);
            }
            else if (fundingType.getDescription().equalsIgnoreCase("Unit")) {
                Unit unitObj = getUnitService().getUnit(sourceId);
                String unitName = null;
                if (unitObj != null) {
                    unitName = unitObj.getUnitName();
                }
                source.setFundingSourceTitle("");
                source.setFundingSourceName(unitName);
            }
            else if (fundingType.getDescription().equalsIgnoreCase("Award")) {
                source.setFundingSource(sourceId);
                source.setFundingSourceType(fundingType);
                if (source.getFundingAward()!= null) {
                    //TODO source.setFundingSourceName(source.getFundingAward().getSponsor().getSponsorName());
                    source.setFundingSourceName(source.getFundingAward().getTitle());
                    source.setFundingSourceTitle(source.getFundingAward().getTitle());
                }
            }
            else if (fundingType.getDescription().equalsIgnoreCase("Development Proposal")) {
                source.setFundingSource(sourceId);
                source.setFundingSourceType(fundingType);
                if (source.getFundingProposal()!= null) {
                    source.setFundingSourceName(source.getFundingProposal().getSponsor().getSponsorName());
                    source.setFundingSourceTitle(source.getFundingProposal().getTitle());
                }
            }
            else if (fundingType.getDescription().equalsIgnoreCase("Institute Proposal")) {
                source.setFundingSource(sourceId);
                source.setFundingSourceType(fundingType);
                if (source.getFundingProposal()!= null) {
                  //  source.setFundingSourceName(source.getFundingProposal().getSponsor().getSponsorName());
                    source.setFundingSourceName(source.getFundingProposal().getTitle());
                    source.setFundingSourceTitle(source.getFundingProposal().getTitle());
                }
            }
            else if (fundingType.getDescription().equalsIgnoreCase("Other")) {
                // Setting to empty string prevents the error message
                if (sourceTitle == null) {
                    source.setFundingSourceTitle("");
                }
                if (sourceName == null) {
                    source.setFundingSourceName("");
                }
            }
        }
        return source;
    }

    public SponsorService getSponsorService() {
        return sponsorService;
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public FundingSourceTypeService getfundingSourceTypeService() {
        return fundingSourceTypeService;
    }

    public boolean isValidIdForType(ProtocolFundingSource source) {
        boolean ret= false;

        if (source.getFundingSourceType().getDescription().equalsIgnoreCase("Other")) {
            ret=true;
        } else  {
            ProtocolFundingSource testSrc = 
                getNameAndTitle(source.getFundingSourceTypeCode().toString(), source.getFundingSource(), source.getFundingSourceName(), source.getFundingSourceTitle());
            if (StringUtils.hasText(testSrc.getFundingSourceName())) {
                ret=true;
            }
        }

        return ret;
    }


}
