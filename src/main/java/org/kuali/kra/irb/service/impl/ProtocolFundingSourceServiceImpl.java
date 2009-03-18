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

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

import java.util.HashMap;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolFundingSource;
import org.kuali.kra.irb.service.ProtocolFundingSourceService;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.FundingSourceTypeService;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kns.util.KNSConstants;
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
                    if (source.getFundingAward().getSponsor()!= null) {
                        source.setFundingSourceName(source.getFundingAward().getSponsor().getSponsorName());
                    } else {
                        source.setFundingSourceName("");
                    }
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
                    source.setFundingSourceName(source.getFundingProposal().getSponsor().getSponsorName());
                    source.setFundingSourceTitle(source.getFundingProposal().getTitle());
                }
            }
            else if (fundingType.getDescription().equalsIgnoreCase("Other")) {
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
            if (StringUtils.hasText(testSrc.getFundingSourceName()) ||
                    StringUtils.hasText(testSrc.getFundingSourceTitle())) {
                ret=true;
            }
        }

        return ret;
    }
    
    public boolean isValidLookup(String boClassName) {
        boolean isValid = true;
        
        if (StringUtils.hasText(boClassName)) {            
            if (boClassName.equalsIgnoreCase("Institute Proposal")) {
               GlobalVariables.getErrorMap().putError("document.protocolList[0].newFundingSource.fundingSourceTypeCode", "error.custom", "Lookup is Temporarily unavailable for Funding Type Institute Proposal");            
               isValid = false;
           }  else if (boClassName.equalsIgnoreCase("Other")) {
               GlobalVariables.getErrorMap().putError("document.protocolList[0].newFundingSource.fundingSourceTypeCode", "error.custom", "Lookup is unavailable for Funding Type Other");            
               isValid = false;
           }
       } else {
           GlobalVariables.getErrorMap().putError("document.protocolList[0].newFundingSource.fundingSourceTypeCode", "error.custom", "Funding Type must be select to perform Lookup");            
           isValid = false;
        }   
        return isValid;
    }
    
    public HashMap<String, String>  getLookupParameters(String boClassName) {
        
        HashMap<String, String> boAndFields = new HashMap<String, String>();
        String fieldConversions=null;
        
           if (boClassName.equalsIgnoreCase(Unit.class.getSimpleName())) {
               boClassName = Unit.class.getName();
               fieldConversions="unitNumber:document.protocolList[0].newFundingSource.fundingSource,unitName:document.protocolList[0].newFundingSource.fundingSourceName";
           } else if (boClassName.equalsIgnoreCase("Sponsor")) {
               boClassName = "org.kuali.kra.bo.Sponsor";
               fieldConversions="sponsorCode:document.protocolList[0].newFundingSource.fundingSource,sponsorName:document.protocolList[0].newFundingSource.fundingSourceName";
           } else if (boClassName.equalsIgnoreCase("Award")) {
               boClassName = "org.kuali.kra.award.bo.Award";
               fieldConversions="awardNumber:document.protocolList[0].newFundingSource.fundingSource,sponsor.sponsorName:document.protocolList[0].newFundingSource.fundingSourceName,title:document.protocolList[0].newFundingSource.fundingSourceTitle";
           } else if (boClassName.equalsIgnoreCase("Development Proposal")) {
               boClassName = "org.kuali.kra.irb.bo.LookupableDevelopmentProposal";
               fieldConversions="proposalNumber:document.protocolList[0].newFundingSource.fundingSource,sponsor.sponsorName:document.protocolList[0].newFundingSource.fundingSourceName,title:document.protocolList[0].newFundingSource.fundingSourceTitle";
           } else if (boClassName.equalsIgnoreCase("Institute Proposal")) {
               //TODO readd when instituteProposal is impl'd
                //   boClassName = "org.kuali.kra.bo.proposaldevelopment.document.ProposalDevelopmentDocument";
                //   fieldConversions="proposalNumber:document.protocolList[0].newFundingSource.fundingSource,sponsor.sponsorName:document.protocolList[0].newFundingSource.fundingSourceName,title:document.protocolList[0].newFundingSource.fundingSourceTitle";
           } 
           boAndFields.put(boClassName, fieldConversions);

        return boAndFields;
    }

    public String updateLookupParameter(String parameter, String boClassName, String fieldConversions) {
        StringBuffer fullParameterBuffer = new StringBuffer(parameter);
        int start = fullParameterBuffer.indexOf(KNSConstants.METHOD_TO_CALL_BOPARM_LEFT_DEL)+KNSConstants.METHOD_TO_CALL_BOPARM_LEFT_DEL.length();
        int end = fullParameterBuffer.indexOf(KNSConstants.METHOD_TO_CALL_BOPARM_RIGHT_DEL);        
        fullParameterBuffer.replace(start, end, boClassName);

        start = fullParameterBuffer.indexOf(KNSConstants.METHOD_TO_CALL_PARM1_LEFT_DEL)+KNSConstants.METHOD_TO_CALL_PARM1_LEFT_DEL.length();
        end = fullParameterBuffer.indexOf(KNSConstants.METHOD_TO_CALL_PARM1_RIGHT_DEL);        
        fullParameterBuffer.replace(start, end, fieldConversions);
        
        return fullParameterBuffer.toString();
    }

}
