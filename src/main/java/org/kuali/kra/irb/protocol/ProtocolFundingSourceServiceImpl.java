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
package org.kuali.kra.irb.protocol;

import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.service.AwardService;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.proposaldevelopment.bo.LookupableDevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.LookupableDevelopmentProposalService;
import org.kuali.kra.service.FundingSourceTypeService;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kew.util.Utilities;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * This Service implementation provides the required logic for performing a multi-type lookup for funding sources. 
 * And business rule management for a a protocol's funding source list.
 */
public class ProtocolFundingSourceServiceImpl implements ProtocolFundingSourceService {

    private UnitService unitService;
    private SponsorService sponsorService;
    private AwardService awardService;
    private FundingSourceTypeService fundingSourceTypeService;
    private LookupableDevelopmentProposalService lookupableDevelopmentProposalService;
    private LookupableHelperService protocolLookupableHelperService;
    private DocumentService documentService;
    
    private static final String BO_SPONSOR_NAME = "sponsor.sponsorName";
    private static final String TITLE = "title";
    
    private static final String SPONSOR_ID = "sponsorCode";
    private static final String SPONSOR_NAME = "sponsorName";
    private static final String UNIT_ID = "unitNumber";
    private static final String UNIT_NAME = "unitName";
    private static final String PROP_ID = "proposalNumber";
    private static final String AWARD_ID = "awardId";
    
    /**
     * This enum captures the elements for fundingSource for managing the multi type lookup,
     * it is inherently tightly coupled with the primary keyvalues of the FundingSourceTable
     */
    enum FundingSourceLookup {
        SPONSOR             ("Sponsor",              
                             Sponsor.class,
                             SPONSOR_ID,
                             SPONSOR_NAME,
                             Constants.EMPTY_STRING,1),
        UNIT                ("Unit",
                              Unit.class,
                              UNIT_ID,
                              UNIT_NAME,
                              Constants.EMPTY_STRING,2),
        OTHER               ("Other",                
                              Object.class,                      
                              Constants.EMPTY_STRING,
                              Constants.EMPTY_STRING,
                              Constants.EMPTY_STRING,3),
        PROPOSAL_DEVELOPMENT("Development Proposal", 
                              LookupableDevelopmentProposal.class,
                              PROP_ID,
                              BO_SPONSOR_NAME,
                              TITLE,4),
        //TODO when institute proposal is impl'd change below to institute proposal lookup
        INSTITUTE_PROPOSAL  ("Institute Proposal",   
                              LookupableDevelopmentProposal.class,
                              PROP_ID,
                              BO_SPONSOR_NAME,
                              TITLE,5),
        AWARD               ("Award",                
                              Award.class,
                              AWARD_ID,
                              BO_SPONSOR_NAME,
                              TITLE,6),
        ;

        private Class<?> clazz;
        private String keyCode;
        private String title;
        private String name;
        private String lookupName;
        private final int fundingTypeCode;

        FundingSourceLookup(String lookupName, Class<?> clazz, String keyCode, String name, String title, Integer fundingTypeCode) {
            this.clazz = clazz;
            this.keyCode = keyCode;
            this.title = title;
            this.name = name;
            this.lookupName = lookupName;
            this.fundingTypeCode = fundingTypeCode;
        }
        
        public Class<?> getBOClass() {
            return clazz;
        }

        public String getkeyCode() {
            return keyCode;
        }
        
        public String getTitle() {
            return title;
        }
        public String getName() {
            return name;
        }
        public String getLookupName() {
            return lookupName;
        }
        public int getFundingTypeCode() {
            return fundingTypeCode;
        }   
    }

    private LookupableDevelopmentProposalService getLookupableDevelopmentProposalService() {
        return lookupableDevelopmentProposalService;
    }
    
    private LookupableHelperService getProtocolLookupableHelperService() {
        return protocolLookupableHelperService;
    }
    
    public void setProtocolLookupableHelperService(LookupableHelperService protocolLookupableHelperService) {
        this.protocolLookupableHelperService =  protocolLookupableHelperService;
    }
    
    public SponsorService getSponsorService() {
        return sponsorService;
    }

    public UnitService getUnitService() {
        return unitService;
    }
    
    public AwardService getAwardService() {
        return awardService;
    }

    public FundingSourceTypeService getFundingSourceTypeService() {
        return fundingSourceTypeService;
    }

    public void setFundingSourceTypeService(FundingSourceTypeService fundingSourceTypeService) {
        this.fundingSourceTypeService = fundingSourceTypeService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    public void setSponsorService(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }
    
    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }
    
    public void setLookupableDevelopmentProposalService(LookupableDevelopmentProposalService lookupableDevelopmentProposalService) {
        this.lookupableDevelopmentProposalService = lookupableDevelopmentProposalService;
    }

    public void deleteProtocolFundingSource(Protocol protocol, int lineNumber) {
         protocol.getProtocolFundingSources().remove(lineNumber);
    }


    /** {@inheritDoc} */
    public ProtocolFundingSource updateProtocolFundingSource(String sourceType, String sourceId, String sourceName) {
        ProtocolFundingSource source = null;
        
        if (StringUtils.isNotBlank(sourceType) && StringUtils.isNotBlank(sourceId)) {   
            source = new ProtocolFundingSource(sourceId, getFundingSourceTypeService().getFundingSourceType(sourceType),null,Constants.EMPTY_STRING); 
            if ( FundingSourceLookup.OTHER.getFundingTypeCode()==(Integer.valueOf(sourceType))) {
                source.setFundingSourceName(sourceName);
            } else if (FundingSourceLookup.SPONSOR.getFundingTypeCode()==(Integer.valueOf(sourceType))) {
                source.setFundingSourceName(getSponsorService().getSponsorName(sourceId));
            } else if (FundingSourceLookup.UNIT.getFundingTypeCode()==(Integer.valueOf(sourceType))) {
                source.setFundingSourceName(getUnitService().getUnitName(sourceId));
            } else if (FundingSourceLookup.AWARD.getFundingTypeCode()==(Integer.valueOf(sourceType))) {
                if (isLinkedWithAward()) {
                    Award award  = getAwardService().getAward(sourceId);
                    if (award != null) {
                        source.setFundingSourceName(award.getSponsorName()!=null?award.getSponsorName():Constants.EMPTY_STRING);
                        source.setFundingSourceTitle(award.getTitle()!=null?award.getTitle():Constants.EMPTY_STRING);
                    }
                } else {
                    source.setFundingSourceName(sourceName);
                }
            }
            else if (FundingSourceLookup.PROPOSAL_DEVELOPMENT.getFundingTypeCode()==(Integer.valueOf(sourceType))) {
                if (isLinkedWithDevProposal()) {
                    LookupableDevelopmentProposal devProposal = getLookupableDevelopmentProposalService().getLookupableDevelopmentProposal(sourceId);
                    if (devProposal != null) {
                        source.setFundingSourceName(devProposal.getSponsorName()!=null?devProposal.getSponsorName():Constants.EMPTY_STRING);
                        source.setFundingSourceTitle(devProposal.getTitle()!=null?devProposal.getTitle():Constants.EMPTY_STRING);
                    }
                } else {
                    source.setFundingSourceName(sourceName);
                }
            }
            else if (FundingSourceLookup.INSTITUTE_PROPOSAL.getFundingTypeCode()==(Integer.valueOf(sourceType))) {
                if (isLinkedWithProposal()) {
                    //TODO Add guts here when InstituteProposal is built...
                } else {
                    source.setFundingSourceName(sourceName);
                }
            }
        } 
        return source;
    }


    /**
     *  Validates fundingSource exists of this type for code provided.
     * @see org.kuali.kra.irb.protocol.ProtocolFundingSourceService#isValidIdForType(org.kuali.kra.irb.protocol.ProtocolFundingSource)
     */
    public boolean isValidIdForType(ProtocolFundingSource source) {
        boolean ret= false;

        if (source != null && source.getFundingSourceType() != null )  {
            if (source.getFundingSourceType().getDescription().equalsIgnoreCase(FundingSourceLookup.OTHER.getLookupName())) {
                ret=true;
            } else if (source.getFundingSourceType().getDescription().equalsIgnoreCase(FundingSourceLookup.AWARD.getLookupName())
                    && !isLinkedWithAward() ) {
                ret=true;
            } else if (source.getFundingSourceType().getDescription().equalsIgnoreCase(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getLookupName())
                    && !isLinkedWithDevProposal() ) {
                ret=true;
            } else if (source.getFundingSourceType().getDescription().equalsIgnoreCase(FundingSourceLookup.INSTITUTE_PROPOSAL.getLookupName())
                    && !isLinkedWithProposal() ) {
                ret=true;
            } else {
                String typeCode = source.getFundingSourceTypeCode().toString();
                String src = source.getFundingSource();
                String name = source.getFundingSourceName();
                String title = source.getFundingSourceTitle();
                
                ProtocolFundingSource testSrc = updateProtocolFundingSource(typeCode, src, name);       
                
                if (testSrc != null && (StringUtils.isNotBlank(testSrc.getFundingSourceName())) ) {
                    ret=true;
                }
            }
        }
        return ret;
    }

    
    /** {@inheritDoc} */
    public Entry<String, String>  getLookupParameters(String boClassName) {        
        HashMap<String, String> boAndFields = new HashMap<String, String>();
        FundingSourceLookup sourceLookup = FundingSourceLookup.OTHER;
        
        if (boClassName.equalsIgnoreCase(FundingSourceLookup.UNIT.getLookupName())) {
           sourceLookup = FundingSourceLookup.UNIT;
        } else if (boClassName.equalsIgnoreCase(FundingSourceLookup.SPONSOR.getLookupName())) {
           sourceLookup = FundingSourceLookup.SPONSOR;
        } else if (boClassName.equalsIgnoreCase(FundingSourceLookup.AWARD.getLookupName())) {
           sourceLookup = FundingSourceLookup.AWARD;
        } else if (boClassName.equalsIgnoreCase(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getLookupName())) {
           sourceLookup = FundingSourceLookup.PROPOSAL_DEVELOPMENT;
        }  else if (boClassName.equalsIgnoreCase(FundingSourceLookup.INSTITUTE_PROPOSAL.getLookupName())) {
          //TODO readd when instituteProposal is impl'd
          //   boClassName = "org.kuali.kra.bo.proposaldevelopment.document.ProposalDevelopmentDocument";
          //   fieldConversions="proposalNumber:document.protocolList[0].newFundingSource.fundingSource,sponsor.sponsorName:document.protocolList[0].newFundingSource.fundingSourceName,title:document.protocolList[0].newFundingSource.fundingSourceTitle";
          throw new IllegalArgumentException("Funding source parameter lookup error. The processIsValidLookup rule was not invoked or missed error condition.");
        } else {
            throw new IllegalArgumentException("Funding source parameter lookup error. The processIsValidLookup rule was not invoked or missed error condition.");
        }
           
        if (sourceLookup != FundingSourceLookup.OTHER) {
            String fieldConversions = createCustomFieldConversions(sourceLookup);        
            boAndFields.put(sourceLookup.getBOClass().getName(), fieldConversions);
        }
        
        return boAndFields.entrySet().iterator().next();
    }
    
    private String createCustomFieldConversions(FundingSourceLookup sourceLookup) {
        StringBuffer fieldConversions = new StringBuffer();
        if (sourceLookup != FundingSourceLookup.OTHER) {
            
            fieldConversions.append(sourceLookup.getkeyCode()+Constants.COLON);
            fieldConversions.append(Constants.PROTO_FUNDING_SRC_ID_FIELD+Constants.COMMA);
    
            fieldConversions.append(sourceLookup.getName()+Constants.COLON);
            fieldConversions.append(Constants.PROTO_FUNDING_SRC_NAME_FIELD+Constants.COMMA);

            fieldConversions.append(sourceLookup.getName()+Constants.COLON);
            fieldConversions.append(Constants.PROTO_FUNDING_SRC_NAME_FIELD_DIV+Constants.COMMA);
            
            
            //Note: not all funding sources have a title
            if (StringUtils.isNotBlank(sourceLookup.getTitle())) {
                fieldConversions.append(sourceLookup.getTitle()+Constants.COLON);
                fieldConversions.append(Constants.PROTO_FUNDING_SRC_TITLE_FIELD);
            }
        }
        return fieldConversions.toString();
    }
    
    /** {@inheritDoc} */
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

    public String getViewProtocolFundingSourceUrl(ProtocolFundingSource protocolFundingSource, ProtocolProtocolAction action) throws Exception {

        Integer fundingCode = protocolFundingSource.getFundingSourceType().getFundingSourceTypeCode();
        String retUrl=null;
        String maintDocLookupPrefix= "${kuali.docHandler.url.prefix}/kr/";
        
      //TODO add Institute proposal when ready
        if (fundingCode.equals(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getFundingTypeCode())) {        
            String docNum = protocolFundingSource.getFundingProposal().getDocumentNumber();            
            ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(docNum);
            Long routeHeaderId = doc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            retUrl = action.buildForwardUrl(routeHeaderId);
        } else if (fundingCode.equals(FundingSourceLookup.AWARD.getFundingTypeCode())) {        
            Award award = protocolFundingSource.getFundingAward();
            AwardDocument awardDocument = award.getAwardDocument();
            AwardDocument doc = (AwardDocument) getDocumentService().getByDocumentHeaderId(awardDocument.getDocumentNumber());
            Long routeHeaderId = doc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            retUrl = action.buildForwardUrl(routeHeaderId);
        } else if (fundingCode.equals(FundingSourceLookup.SPONSOR.getFundingTypeCode())) {
            Sponsor sponsor = new Sponsor();
            sponsor.setSponsorCode(protocolFundingSource.getFundingSource());
            HtmlData forward = 
                getProtocolLookupableHelperService().getInquiryUrl(sponsor, FundingSourceLookup.SPONSOR.getkeyCode());
            retUrl = Utilities.substituteConfigParameters(maintDocLookupPrefix+((HtmlData.AnchorHtmlData)forward).getHref());
        } else  if (fundingCode.equals(FundingSourceLookup.UNIT.getFundingTypeCode())) {
            Unit unit = new Unit();
            unit.setUnitNumber(protocolFundingSource.getFundingSource());
            HtmlData forward = 
                getProtocolLookupableHelperService().getInquiryUrl(unit, FundingSourceLookup.UNIT.getkeyCode());
            retUrl = Utilities.substituteConfigParameters(maintDocLookupPrefix+((HtmlData.AnchorHtmlData)forward).getHref());
        }
        
        return retUrl;
    }
    
    protected boolean isLinkedWithDevProposal() {
        boolean ret = true;
        if (getKualiConfigurationService().getParameterWithoutExceptions(
                Constants.PARAMETER_MODULE_PROTOCOL,
                Constants.PARAMETER_COMPONENT_DOCUMENT,
                Constants.ENABLE_PROTOCOL_TO_DEV_PROPOSAL_LINK)!= null) {
            ret = getKualiConfigurationService().getIndicatorParameter(Constants.PARAMETER_MODULE_PROTOCOL,
                    Constants.PARAMETER_COMPONENT_DOCUMENT,
                Constants.ENABLE_PROTOCOL_TO_DEV_PROPOSAL_LINK);
        }
        return ret;        
    }
    
    protected boolean isLinkedWithProposal() {
        boolean ret = true;
        if (getKualiConfigurationService().getParameterWithoutExceptions(
                Constants.PARAMETER_MODULE_PROTOCOL,
                Constants.PARAMETER_COMPONENT_DOCUMENT,
                Constants.ENABLE_PROTOCOL_TO_PROPOSAL_LINK)!= null) {
            ret = getKualiConfigurationService().getIndicatorParameter(Constants.PARAMETER_MODULE_PROTOCOL,
                    Constants.PARAMETER_COMPONENT_DOCUMENT,
                Constants.ENABLE_PROTOCOL_TO_PROPOSAL_LINK);
        }
        return ret;        
    }
    
    protected boolean isLinkedWithAward() {
        boolean ret = true;
        if (getKualiConfigurationService().getParameterWithoutExceptions(
                Constants.PARAMETER_MODULE_PROTOCOL,
                Constants.PARAMETER_COMPONENT_DOCUMENT,
                Constants.ENABLE_PROTOCOL_TO_AWARD_LINK)!= null) {
            ret = getKualiConfigurationService().getIndicatorParameter(Constants.PARAMETER_MODULE_PROTOCOL,
                    Constants.PARAMETER_COMPONENT_DOCUMENT,
                Constants.ENABLE_PROTOCOL_TO_AWARD_LINK);
        }
        return ret;        
    }
    
    public boolean updateSourceNameEditable(String fundingTypeCode) {
        boolean isEditable = false;
        if (StringUtils.isNotBlank(fundingTypeCode) && !fundingTypeCode.equalsIgnoreCase("select") ) {
            Integer val =  Integer.valueOf(fundingTypeCode);
            if (val.equals(FundingSourceLookup.OTHER.getFundingTypeCode())) {        
                isEditable = true;
            } else if (val.equals(FundingSourceLookup.AWARD.getFundingTypeCode())
                       && !isLinkedWithAward()) {
                isEditable = true;
            } else if (val.equals(FundingSourceLookup.INSTITUTE_PROPOSAL.getFundingTypeCode())
                       && !isLinkedWithProposal()) {
                isEditable = true;
            }else if (val.equals(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getFundingTypeCode()) 
                      && !isLinkedWithDevProposal()) {
                isEditable = true;
            }
        }
        return isEditable;
    }
    
    public boolean isViewable(int fundingTypeCode) {
        boolean ret = true;
        if ((fundingTypeCode == FundingSourceLookup.OTHER.getFundingTypeCode())) {
            ret = false;
        } else if ((fundingTypeCode == FundingSourceLookup.INSTITUTE_PROPOSAL.getFundingTypeCode()) && !isLinkedWithProposal()) {
            ret = false;
        } else if ((fundingTypeCode == FundingSourceLookup.AWARD.getFundingTypeCode()) && !isLinkedWithAward()) {
            ret = false;
        } else if ((fundingTypeCode == FundingSourceLookup.PROPOSAL_DEVELOPMENT.getFundingTypeCode()) && !isLinkedWithDevProposal()) {
            ret = false;
        } 
        return ret;
    }


    
    private DocumentService getDocumentService() {
        return documentService;
    }
    public void setDocumentService(DocumentService documentService) {
        this.documentService =  documentService;
    }
    
    private KualiConfigurationService getKualiConfigurationService() {
        return KraServiceLocator.getService(KualiConfigurationService.class);        
    }
    
}
