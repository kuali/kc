/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.protocol.funding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.protocol.ProtocolProtocolAction;
import org.kuali.kra.proposaldevelopment.bo.LookupableDevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.LookupableDevelopmentProposalService;
import org.kuali.kra.service.FundingSourceTypeService;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kew.util.Utilities;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * This Service implementation provides the required logic for performing a multi-type lookup for funding sources. 
 * And business rule management for a a protocol's funding source list.
 */
public class ProtocolFundingSourceServiceImpl implements ProtocolFundingSourceService {

    private static final String BO_SPONSOR_NAME = "sponsor.sponsorName";
    private static final String TITLE = "title";
    
    private static final String SPONSOR_CODE = "sponsorCode";
    private static final String SPONSOR_NAME = "sponsorName";
    private static final String UNIT_NUMBER = "unitNumber";
    private static final String UNIT_NAME = "unitName";
    private static final String PROPOSAL_ID = "proposalId";
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    private static final String AWARD_ID = "awardId";
    private static final String AWARD_NUMBER = "awardNumber";
    
    private static String MAINT_DOC_LOOKUP_URL_PREFIX= "${kuali.docHandler.url.prefix}/kr/";

    private FundingSourceTypeService fundingSourceTypeService;
    private SponsorService sponsorService;
    private UnitService unitService;
    private InstitutionalProposalService institutionalProposalService;
    private LookupableDevelopmentProposalService lookupableDevelopmentProposalService;
    private AwardService awardService;
    
    private LookupableHelperService protocolLookupableHelperService;
    private DocumentService documentService;
    private ParameterService parameterService;
    private BusinessObjectService businessObjectService;
    
    private Map<Integer, FundingSourceLookup> fundingEnumMap;
    
    /**
     * This enum captures the elements for fundingSource for managing the multi type lookup.
     * It is inherently tightly coupled with the primary keyvalues of the FundingSourceTable.
     */
    enum FundingSourceLookup {
        
        /**
         * Lookup parameters for Sponsor.
         */
        SPONSOR               ("Sponsor",              
                               Sponsor.class,
                               Constants.EMPTY_STRING,
                               SPONSOR_CODE,
                               SPONSOR_NAME,
                               Constants.EMPTY_STRING, 1),
        
        /**
         * Lookup parameters for Unit.
         */
        UNIT                  ("Unit",
                               Unit.class,
                               Constants.EMPTY_STRING,
                               UNIT_NUMBER,
                               UNIT_NAME,
                               Constants.EMPTY_STRING, 2),
        
        /**
         * Lookup parameters for Other.  This is a free text category.
         */
        OTHER                 ("Other",                
                               Object.class,                      
                               Constants.EMPTY_STRING,
                               Constants.EMPTY_STRING,
                               Constants.EMPTY_STRING,
                               Constants.EMPTY_STRING, 3),
        
        /**
         * Lookup parameters for Development Proposal.
         */
        PROPOSAL_DEVELOPMENT  ("Development Proposal", 
                               LookupableDevelopmentProposal.class,
                               Constants.EMPTY_STRING,
                               PROPOSAL_NUMBER,
                               BO_SPONSOR_NAME,
                               TITLE, 4),
        
        /**
         * Lookup parameters for Institutional Proposal.
         */
        INSTITUTIONAL_PROPOSAL("Institutional Proposal",   
                               InstitutionalProposal.class,
                               PROPOSAL_ID,
                               PROPOSAL_NUMBER,
                               BO_SPONSOR_NAME,
                               TITLE, 5),
        
        /**
         * Lookup parameters for Award.
         */
        AWARD                 ("Award",                
                               Award.class,
                               AWARD_ID,
                               AWARD_NUMBER,
                               BO_SPONSOR_NAME,
                               TITLE, 6);

        private String lookupName;
        private Class<?> clazz;
        private String keyCode;
        private String number;
        private String name;
        private String title;
        private final int fundingTypeCode;

        /**
         * Constructs a FundingSourceLookup.
         * @param lookupName The displayed name of the lookup
         * @param clazz The class reference of what this lookup is querying
         * @param keyCode The primary key to search
         * @param number The displayed user-readable number
         * @param name The name of the funding source
         * @param title The title of the funding source
         * @param fundingTypeCode The number type code of the lookup
         */
        FundingSourceLookup(String lookupName, Class<?> clazz, String keyCode, String number, String name, String title, Integer fundingTypeCode) {
            this.clazz = clazz;
            this.keyCode = keyCode;
            this.number = number;
            this.title = title;
            this.name = name;
            this.lookupName = lookupName;
            this.fundingTypeCode = fundingTypeCode;
        }
        
        public String getLookupName() {
            return lookupName;
        }
        
        public Class<?> getBOClass() {
            return clazz;
        }

        public String getKeyCode() {
            return keyCode;
        }
        
        public String getNumber() {
            return number;
        }
        
        public String getTitle() {
            return title;
        }
        
        public String getName() {
            return name;
        }
        
        public int getFundingTypeCode() {
            return fundingTypeCode;
        }   
    }
    
    public ProtocolFundingSourceServiceImpl() {
        initFundingTypeMap();
        
    }
    
    private void initFundingTypeMap() {
        fundingEnumMap = new HashMap<Integer, FundingSourceLookup>();
        fundingEnumMap.put(FundingSourceLookup.AWARD.getFundingTypeCode(), FundingSourceLookup.AWARD);
        fundingEnumMap.put(FundingSourceLookup.INSTITUTIONAL_PROPOSAL.getFundingTypeCode(), FundingSourceLookup.INSTITUTIONAL_PROPOSAL);
        fundingEnumMap.put(FundingSourceLookup.OTHER.getFundingTypeCode(), FundingSourceLookup.OTHER);
        fundingEnumMap.put(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getFundingTypeCode(), FundingSourceLookup.PROPOSAL_DEVELOPMENT);
        fundingEnumMap.put(FundingSourceLookup.SPONSOR.getFundingTypeCode(), FundingSourceLookup.SPONSOR);
        fundingEnumMap.put(FundingSourceLookup.UNIT.getFundingTypeCode(), FundingSourceLookup.UNIT);
    }

    /** {@inheritDoc} */
    public void deleteProtocolFundingSource(Protocol protocol, int lineNumber) {
        protocol.getProtocolFundingSources().remove(lineNumber);
    }


    /** {@inheritDoc} */
    public ProtocolFundingSource updateProtocolFundingSource(String typeCode, String source, String number, String name) {
        ProtocolFundingSource protocolFundingSource = null;
        FundingSourceType fundingSourceType = getFundingSourceTypeService().getFundingSourceType(typeCode);
        
        switch (fundingEnumMap.get(Integer.valueOf(typeCode))) {
            case SPONSOR:
                protocolFundingSource = buildSponsorFundingSource(fundingSourceType, source, number);
                break;
            case UNIT:
                protocolFundingSource = buildUnitFundingSource(fundingSourceType, source, number);
                break;
            case OTHER:
                protocolFundingSource = buildOtherFundingSource(fundingSourceType, source, number, name);
                break;
            case PROPOSAL_DEVELOPMENT:
                protocolFundingSource = buildProposalDevelopmentFundingSource(fundingSourceType, source, number, name);
                break;
            case INSTITUTIONAL_PROPOSAL:
                protocolFundingSource = buildInstitutionalProposalFundingSource(fundingSourceType, source, number, name);
                break;
            case AWARD:
                protocolFundingSource = buildAwardFundingSource(fundingSourceType, source, number, name);
                break;
            default:
                break;
        }
        return protocolFundingSource;
    }
    
    /*
     * Builds a funding source for a Sponsor
     */
    private ProtocolFundingSource buildSponsorFundingSource(FundingSourceType fundingSourceType, String source, String sourceNumber) {
        ProtocolFundingSource protocolFundingSource = null;
        
        if (fundingSourceType != null && (StringUtils.isNotBlank(source) || StringUtils.isNotBlank(sourceNumber))) {
            String fundingSource = StringUtils.isNotEmpty(source) ? source : sourceNumber;
            String fundingSourceName = getSponsorService().getSponsorName(fundingSource);
            String fundingSourceTitle = Constants.EMPTY_STRING;
            protocolFundingSource = new ProtocolFundingSource(fundingSource, fundingSource, fundingSourceType, fundingSourceName, fundingSourceTitle); 
        }
        
        return protocolFundingSource;
    }
        
    
    /*
     * Builds a funding source for a Unit
     */
    private ProtocolFundingSource buildUnitFundingSource(FundingSourceType fundingSourceType, String source, String sourceNumber) {
        ProtocolFundingSource protocolFundingSource = null;
        
        if (fundingSourceType != null && (StringUtils.isNotBlank(source) || StringUtils.isNotBlank(sourceNumber))) {
            String fundingSource = StringUtils.isNotBlank(source) ? source : sourceNumber;
            String fundingSourceName = getUnitService().getUnitName(fundingSource);
            String fundingSourceTitle = Constants.EMPTY_STRING;
            protocolFundingSource = new ProtocolFundingSource(fundingSource, fundingSource, fundingSourceType, fundingSourceName, fundingSourceTitle); 
        }
        
        return protocolFundingSource;
    }
    
    /*
     * Builds a funding source for any other entry
     */
    private ProtocolFundingSource buildOtherFundingSource(FundingSourceType fundingSourceType, String source, String sourceNumber, String sourceName) {
        ProtocolFundingSource protocolFundingSource = null;
        
        if (fundingSourceType != null && (StringUtils.isNotBlank(source) || StringUtils.isNotBlank(sourceNumber))) {
            String fundingSource = StringUtils.isNotEmpty(source) ? source : sourceNumber;
            String fundingSourceName = sourceName;
            String fundingSourceTitle = Constants.EMPTY_STRING;
            protocolFundingSource = new ProtocolFundingSource(fundingSource, fundingSource, fundingSourceType, fundingSourceName, fundingSourceTitle);
        }
        
        return protocolFundingSource;
    }
    
    /*
     * Builds a funding source for a Proposal Development
     */
    private ProtocolFundingSource buildProposalDevelopmentFundingSource(FundingSourceType fundingSourceType, String source, String sourceNumber, 
            String sourceName) {
        ProtocolFundingSource protocolFundingSource = null;

        if (StringUtils.isNotBlank(source) || StringUtils.isNotBlank(sourceNumber)) {
            String fundingSource = StringUtils.isNotBlank(source) ? source : sourceNumber;
            String fundingSourceName = sourceName;
            String fundingSourceTitle = Constants.EMPTY_STRING;
            if (isLinkedWithDevProposal()) {
                LookupableDevelopmentProposal devProposal = getLookupableDevelopmentProposalService().getLookupableDevelopmentProposal(fundingSource);
                if (devProposal != null) {
                    fundingSourceName = devProposal.getSponsorName();
                    fundingSourceTitle = devProposal.getTitle();
                }
            }
            protocolFundingSource = new ProtocolFundingSource(fundingSource, fundingSource, fundingSourceType, fundingSourceName, fundingSourceTitle); 
        }
        
        return protocolFundingSource;
    }
    
    /*
     * Builds a funding source for an Institutional Proposal
     */
    private ProtocolFundingSource buildInstitutionalProposalFundingSource(FundingSourceType fundingSourceType, String source, String sourceNumber, 
            String sourceName) {
        ProtocolFundingSource protocolFundingSource = null;
        
        if (StringUtils.isNotBlank(source) || StringUtils.isNotBlank(sourceNumber)) {
            String fundingSource = source;
            String fundingSourceNumber = sourceNumber;
            String fundingSourceName = sourceName;
            String fundingSourceTitle = Constants.EMPTY_STRING;
            if (isLinkedWithProposal()) {
                InstitutionalProposal instProposal = getInstitutionalProposal(source, sourceNumber);
                if (instProposal != null) {
                    fundingSource = String.valueOf(instProposal.getProposalId());
                    fundingSourceNumber = instProposal.getProposalNumber();
                    fundingSourceName = instProposal.getSponsorName();
                    fundingSourceTitle = instProposal.getTitle();
                    
                }
            }
            protocolFundingSource = new ProtocolFundingSource(fundingSource, fundingSourceNumber, fundingSourceType, fundingSourceName, fundingSourceTitle); 
        }
        
        return protocolFundingSource;
    }
    
    /*
     * Retrieves an Institutional Proposal, favoring the ID (source) but using the number (sourceNumber) as backup
     */
    private InstitutionalProposal getInstitutionalProposal(String source, String sourceNumber) {
        InstitutionalProposal institutionalProposal = null;
        if (StringUtils.isNotBlank(source) && StringUtils.isNumeric(source)) {
            institutionalProposal = getInstitutionalProposalService().getInstitutionalProposal(source);
        } else if (sourceNumber != null) {
            institutionalProposal = getInstitutionalProposalService().getActiveInstitutionalProposalVersion(sourceNumber);
        }
        return institutionalProposal;
    }
    
    /*
     * Builds a funding source for an Award
     */
    private ProtocolFundingSource buildAwardFundingSource(FundingSourceType fundingSourceType, String source, String sourceNumber, String sourceName) {
        ProtocolFundingSource protocolFundingSource = null;
        
        if (StringUtils.isNotBlank(source) || StringUtils.isNotBlank(sourceNumber)) {
            String fundingSource = source;
            String fundingSourceNumber = sourceNumber;
            String fundingSourceName = sourceName;
            String fundingSourceTitle = Constants.EMPTY_STRING;
            if (isLinkedWithAward()) {
                Award award  = getAward(source, sourceNumber);
                if (award != null) {
                    fundingSource = String.valueOf(award.getAwardId());
                    fundingSourceNumber = award.getAwardNumber();
                    fundingSourceName = award.getSponsorName();
                    fundingSourceTitle = award.getTitle();
                    protocolFundingSource 
                        = new ProtocolFundingSource(fundingSource, fundingSourceNumber, fundingSourceType, fundingSourceName, fundingSourceTitle); 
                }
            }
            protocolFundingSource = new ProtocolFundingSource(fundingSource, fundingSourceNumber, fundingSourceType, fundingSourceName, fundingSourceTitle); 
        }
        
        return protocolFundingSource;
    }
    
    /*
     * Retrieves a matching Award, first preferring the ID (source) and then searching the number (sourceNumber).
     */
    private Award getAward(String source, String sourceNumber) {
        Award award = null;
        if (source != null && StringUtils.isNotBlank(source) && StringUtils.isNumeric(source)) {
            award = getAwardService().getAward(Long.parseLong(source));
        } else if (sourceNumber != null) {
            List<Award> results = getAwardService().findAwardsForAwardNumber(sourceNumber);
            if (!results.isEmpty()) {
                award = results.get(0);
            }
        }
        return award;
    }

    /**
     *  Validates fundingorg.kuali.kra.irb.protocol.funding.for code provided.
     * @see org.kuali.kra.iorg.kuali.kra.irb.protocol.funding.lFundingSourceService#isValidIdForType(org.kuali.kra.irb.protocol.funding.ProtocolFundingSource)
     */
    public boolean isValidIdForType(ProtocolFundingSource source) {
        boolean ret = false;

        if (source != null && source.getFundingSourceType() != null)  {
            if (source.getFundingSourceType().getDescription().equalsIgnoreCase(FundingSourceLookup.OTHER.getLookupName())) {
                ret = true;
            } else if (source.getFundingSourceType().getDescription().equalsIgnoreCase(FundingSourceLookup.AWARD.getLookupName())
                    && !isLinkedWithAward()) {
                ret = true;
            } else if (source.getFundingSourceType().getDescription().equalsIgnoreCase(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getLookupName())
                    && !isLinkedWithDevProposal()) {
                ret = true;
            } else if (source.getFundingSourceType().getDescription().equalsIgnoreCase(FundingSourceLookup.INSTITUTIONAL_PROPOSAL.getLookupName())
                    && !isLinkedWithProposal()) {
                ret = true;
            } else {
                String typeCode = source.getFundingSourceTypeCode().toString();
                String src = source.getFundingSource();
                String number = source.getFundingSourceNumber();
                String name = source.getFundingSourceName();
                
                ProtocolFundingSource testSrc = updateProtocolFundingSource(typeCode, src, number, name);       
                
                if (testSrc != null && (StringUtils.isNotBlank(testSrc.getFundingSourceName()))) {
                    ret = true;
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
        }  else if (boClassName.equalsIgnoreCase(FundingSourceLookup.INSTITUTIONAL_PROPOSAL.getLookupName())) {
            sourceLookup = FundingSourceLookup.INSTITUTIONAL_PROPOSAL;
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
            // Not all funding sources have a key code and instead use a number
            if (StringUtils.isNotBlank(sourceLookup.getKeyCode())) {
                fieldConversions.append(sourceLookup.getKeyCode() + Constants.COLON);
                fieldConversions.append(Constants.PROTO_FUNDING_SRC_ID_FIELD + Constants.COMMA);
            }
            
            fieldConversions.append(sourceLookup.getNumber() + Constants.COLON);
            fieldConversions.append(Constants.PROTO_FUNDING_SRC_NUMBER_FIELD + Constants.COMMA);
            
            fieldConversions.append(sourceLookup.getName() + Constants.COLON);
            fieldConversions.append(Constants.PROTO_FUNDING_SRC_NAME_FIELD);
            
            // Not all funding sources have a title
            if (StringUtils.isNotBlank(sourceLookup.getTitle())) {
                fieldConversions.append(Constants.COMMA);
                fieldConversions.append(sourceLookup.getTitle() + Constants.COLON);
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

    /**
     * ${@inheritDoc}
     * @see org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService#getViewProtocolFundingSourceUrl(
     *      org.kuali.kra.irb.protocol.funding.ProtocolFundingSource, org.kuali.kra.irb.protocol.ProtocolProtocolAction)
     */
    public String getViewProtocolFundingSourceUrl(ProtocolFundingSource protocolFundingSource, ProtocolProtocolAction action) throws Exception {
        Integer fundingCode = protocolFundingSource.getFundingSourceType().getFundingSourceTypeCode();
        String retUrl = null;
        
        if (fundingCode.equals(FundingSourceLookup.SPONSOR.getFundingTypeCode())) {
            Sponsor sponsor = new Sponsor();
            sponsor.setSponsorCode(protocolFundingSource.getFundingSourceNumber());
            HtmlData forward = getProtocolLookupableHelperService().getInquiryUrl(sponsor, FundingSourceLookup.SPONSOR.getNumber());
            retUrl = Utilities.substituteConfigParameters(MAINT_DOC_LOOKUP_URL_PREFIX + ((HtmlData.AnchorHtmlData) forward).getHref());
        } else  if (fundingCode.equals(FundingSourceLookup.UNIT.getFundingTypeCode())) {
            Unit unit = new Unit();
            unit.setUnitNumber(protocolFundingSource.getFundingSourceNumber());
            HtmlData forward = getProtocolLookupableHelperService().getInquiryUrl(unit, FundingSourceLookup.UNIT.getNumber());
            retUrl = Utilities.substituteConfigParameters(MAINT_DOC_LOOKUP_URL_PREFIX + ((HtmlData.AnchorHtmlData) forward).getHref());
        } else if (fundingCode.equals(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getFundingTypeCode())) {        
            String docNum = protocolFundingSource.getFundingDevelopmentProposal().getProposalDocument().getDocumentNumber();            
            ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(docNum);
            Long routeHeaderId = doc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            retUrl = action.buildForwardUrl(routeHeaderId);
        } else if (fundingCode.equals(FundingSourceLookup.INSTITUTIONAL_PROPOSAL.getFundingTypeCode())) {
            String docNum = protocolFundingSource.getFundingInstitutionalProposal().getInstitutionalProposalDocument().getDocumentNumber();            
            InstitutionalProposalDocument doc = (InstitutionalProposalDocument) getDocumentService().getByDocumentHeaderId(docNum);
            Long routeHeaderId = doc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            retUrl = action.buildForwardUrl(routeHeaderId);
        } else if (fundingCode.equals(FundingSourceLookup.AWARD.getFundingTypeCode())) {        
            String docNum = protocolFundingSource.getFundingAward().getAwardDocument().getDocumentNumber();            
            AwardDocument doc = (AwardDocument) getDocumentService().getByDocumentHeaderId(docNum);
            Long routeHeaderId = doc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            retUrl = action.buildForwardUrl(routeHeaderId);
        }
        
        return retUrl;
    }

    private boolean isLinkedWithDevProposal() {
        return this.isLinkedWith(Constants.ENABLE_PROTOCOL_TO_DEV_PROPOSAL_LINK);     
    }
    
    private boolean isLinkedWithProposal() {
        return this.isLinkedWith(Constants.ENABLE_PROTOCOL_TO_PROPOSAL_LINK);       
    }
    
    private boolean isLinkedWithAward() {
        return this.isLinkedWith(Constants.ENABLE_PROTOCOL_TO_AWARD_LINK);          
    }
    
    /**
     * Gets if a protocol doc is linked to something based on a passed in parameter name
     * @param link the parameter name
     * @return true if linked false if not
     */
    private boolean isLinkedWith(String link) {
        assert link != null : "link is null";
        
        if (!this.parameterService.parameterExists(ProtocolDocument.class, link)) {
            return true;
        }
        
        return this.parameterService.getIndicatorParameter(ProtocolDocument.class, link);  
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
            } else if (val.equals(FundingSourceLookup.INSTITUTIONAL_PROPOSAL.getFundingTypeCode())
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
        boolean ret = false;
        if ((fundingTypeCode == FundingSourceLookup.UNIT.getFundingTypeCode())) {
            ret = true;
        } else if ((fundingTypeCode == FundingSourceLookup.SPONSOR.getFundingTypeCode())) {
            ret = true;
        } else if ((fundingTypeCode == FundingSourceLookup.INSTITUTIONAL_PROPOSAL.getFundingTypeCode()) && isLinkedWithProposal()) {
            ret = true;
        } else if ((fundingTypeCode == FundingSourceLookup.AWARD.getFundingTypeCode()) && isLinkedWithAward()) {
            ret = true;
        } else if ((fundingTypeCode == FundingSourceLookup.PROPOSAL_DEVELOPMENT.getFundingTypeCode()) && isLinkedWithDevProposal()) {
            ret = true;
        } 
        return ret;
    }

    public FundingSourceTypeService getFundingSourceTypeService() {
        return fundingSourceTypeService;
    }

    public void setFundingSourceTypeService(FundingSourceTypeService fundingSourceTypeService) {
        this.fundingSourceTypeService = fundingSourceTypeService;
    }
    
    public SponsorService getSponsorService() {
        return sponsorService;
    }
    
    public void setSponsorService(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    public UnitService getUnitService() {
        return unitService;
    }
    
    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
    
    public InstitutionalProposalService getInstitutionalProposalService() {
        return institutionalProposalService;
    }
    
    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
    }

    public LookupableDevelopmentProposalService getLookupableDevelopmentProposalService() {
        return lookupableDevelopmentProposalService;
    }
    
    public void setLookupableDevelopmentProposalService(LookupableDevelopmentProposalService lookupableDevelopmentProposalService) {
        this.lookupableDevelopmentProposalService = lookupableDevelopmentProposalService;
    }
    
    public AwardService getAwardService() {
        return awardService;
    }
    
    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }
    
    public LookupableHelperService getProtocolLookupableHelperService() {
        return protocolLookupableHelperService;
    }
    
    public void setProtocolLookupableHelperService(LookupableHelperService protocolLookupableHelperService) {
        this.protocolLookupableHelperService =  protocolLookupableHelperService;
    }
    
    public DocumentService getDocumentService() {
        return documentService;
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService =  documentService;
    }
    
    public ParameterService getParameterService() {
        return parameterService;
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}