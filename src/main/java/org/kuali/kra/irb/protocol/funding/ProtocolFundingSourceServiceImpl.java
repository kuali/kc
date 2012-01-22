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
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.protocol.ProtocolProtocolAction;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.LookupableDevelopmentProposal;
import org.kuali.kra.service.FundingSourceTypeService;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.util.Utilities;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * Implements ProtocolFundingSource.
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
    
    private static final String MAINT_DOC_LOOKUP_URL_PREFIX = "${kuali.docHandler.url.prefix}/kr/";
    private static final Log LOG = LogFactory.getLog(ProtocolFundingSourceServiceImpl.class);

    private FundingSourceTypeService fundingSourceTypeService;
    private SponsorService sponsorService;
    private UnitService unitService;
    private InstitutionalProposalService institutionalProposalService;
    private AwardService awardService;
    
    private LookupableHelperService protocolLookupableHelperService;
    private DocumentService documentService;
    private ParameterService parameterService;
    private BusinessObjectService businessObjectService;
    
    /**
     * This enum captures the elements for fundingSource for managing the multi type lookup.
     */
    private enum FundingSourceLookup {
        
        /**
         * Lookup parameters for Sponsor.
         */
        SPONSOR               (Sponsor.class,
                               Constants.EMPTY_STRING,
                               SPONSOR_CODE,
                               SPONSOR_NAME,
                               Constants.EMPTY_STRING),
        
        /**
         * Lookup parameters for Unit.
         */
        UNIT                  (Unit.class,
                               Constants.EMPTY_STRING,
                               UNIT_NUMBER,
                               UNIT_NAME,
                               Constants.EMPTY_STRING),
        
        /**
         * Lookup parameters for Other.  This is a free text category.
         */
        OTHER                 (Object.class,
                               Constants.EMPTY_STRING,
                               Constants.EMPTY_STRING,
                               Constants.EMPTY_STRING,
                               Constants.EMPTY_STRING),
        
        /**
         * Lookup parameters for Development Proposal.
         */
        PROPOSAL_DEVELOPMENT  (LookupableDevelopmentProposal.class,
                               Constants.EMPTY_STRING,
                               PROPOSAL_NUMBER,
                               BO_SPONSOR_NAME,
                               TITLE),
        
        /**
         * Lookup parameters for Institutional Proposal.
         */
        INSTITUTIONAL_PROPOSAL(InstitutionalProposal.class,
                               PROPOSAL_ID,
                               PROPOSAL_NUMBER,
                               BO_SPONSOR_NAME,
                               TITLE),
        
        /**
         * Lookup parameters for Award.
         */
        AWARD                 (Award.class,
                               AWARD_ID,
                               AWARD_NUMBER,
                               BO_SPONSOR_NAME,
                               TITLE);

        private final Class<?> clazz;
        private final String keyCode;
        private final String number;
        private final String name;
        private final String title;

        /**
         * Constructs a FundingSourceLookup.
         * @param clazz The class reference of what this lookup is querying
         * @param keyCode The primary key to search
         * @param number The displayed user-readable number
         * @param name The name of the funding source
         * @param title The title of the funding source
         */
        FundingSourceLookup(Class<?> clazz, String keyCode, String number, String name, String title) {
            this.clazz = clazz;
            this.keyCode = keyCode;
            this.number = number;
            this.title = title;
            this.name = name;
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
        
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService#updateProtocolFundingSource(java.lang.String, java.lang.String, java.lang.String)
     */
    public ProtocolFundingSource updateProtocolFundingSource(String fundingSourceTypeCode, String fundingSourceNumber,
            String fundingSourceName) {
        ProtocolFundingSource protocolFundingSource = null;
        if (StringUtils.isBlank(fundingSourceTypeCode) || isAuthorizedToAccess(fundingSourceTypeCode)) {
            if (StringUtils.isNotBlank(fundingSourceTypeCode) && fundingSourceTypeCode.contains(Constants.COLON)) {
                fundingSourceTypeCode = StringUtils.split(fundingSourceTypeCode, Constants.COLON)[0];
            }
            if (FundingSourceType.SPONSOR.equals(fundingSourceTypeCode)) {
                protocolFundingSource = buildSponsorFundingSource(fundingSourceNumber);
            }
            else if (FundingSourceType.UNIT.equals(fundingSourceTypeCode)) {
                protocolFundingSource = buildUnitFundingSource(fundingSourceNumber);
            }
            else if (FundingSourceType.PROPOSAL_DEVELOPMENT.equals(fundingSourceTypeCode) && isDevelopmentProposalLinkEnabled()) {
                protocolFundingSource = buildProposalDevelopmentFundingSource(fundingSourceNumber, fundingSourceName);
            }
            else if (FundingSourceType.INSTITUTIONAL_PROPOSAL.equals(fundingSourceTypeCode) && isInstitionalProposalLinkEnabled()) {
                protocolFundingSource = buildInstitutionalProposalFundingSource(fundingSourceNumber, fundingSourceName);
            }
            else if (FundingSourceType.AWARD.equals(fundingSourceTypeCode) && isAwardLinkEnabled()) {
                protocolFundingSource = buildAwardFundingSource(fundingSourceNumber, fundingSourceName);
            }
            else {
                protocolFundingSource = buildOtherFundingSource(fundingSourceTypeCode, fundingSourceNumber, fundingSourceName);
            }
        }
        return protocolFundingSource;
    }
    
    /*
     * a utility method to check if dwr/ajax call really has authorization
     * 'updateProtocolFundingSource' also accessed by non ajax call
     */
    private boolean isAuthorizedToAccess(String fundingSourceTypeCode) {
        boolean isAuthorized = true;
        // TODO : this is a quick hack for KC 3.1.1 to provide authorization check for dwr/ajax call. dwr/ajax will be replaced by
        // jquery/ajax in rice 2.0
        // if this is ajax call, then formkey will be included in fundingsourcetypecode as 'fundingsourcetypecode:forKey'
        if (fundingSourceTypeCode.contains(Constants.COLON)) {
            if (GlobalVariables.getUserSession() != null) {
                String[] invalues = StringUtils.split(fundingSourceTypeCode, Constants.COLON);
                String formKey = invalues[1];
                fundingSourceTypeCode = invalues[0];
                if (StringUtils.isBlank(formKey)) {
                    isAuthorized = false;
                }
                else {
                    Object formObj = GlobalVariables.getUserSession().retrieveObject(formKey);
                    if (formObj == null || !(formObj instanceof ProtocolForm)) {
                        isAuthorized = false;
                    }
                    else {
                        ProtocolForm protocolForm = (ProtocolForm) formObj;
                        isAuthorized = protocolForm.getProtocolHelper().getModifyProtocol()
                                && protocolForm.getProtocolHelper().getModifyFundingSource();

                    }

                }
            } else {
                // TODO : it seemed that tomcat has this issue intermittently ?
                LOG.info("dwr/ajax does not have session ");
            }
        }
        return isAuthorized;

    }
    
    /**
     * Builds a Protocol funding source for a Sponsor.
     * 
     * @param fundingSourceNumber the human-readable number for the funding source
     * @return an instance of a Protocol funding source
     */
    private ProtocolFundingSource buildSponsorFundingSource(String fundingSourceNumber) {
        ProtocolFundingSource fundingSource = null;
        
        if (StringUtils.isNotBlank(fundingSourceNumber)) {
            String fundingSourceName = getSponsorService().getSponsorName(fundingSourceNumber);
            String fundingSourceTitle = Constants.EMPTY_STRING;
            fundingSource = new ProtocolFundingSource(fundingSourceNumber, FundingSourceType.SPONSOR, fundingSourceName, fundingSourceTitle); 
        }
        
        return fundingSource;
    }

    /**
     * Builds a Protocol funding source for a Unit.
     * 
     * @param fundingSourceNumber the human-readable number for the funding source
     * @return an instance of a Protocol funding source
     */
    private ProtocolFundingSource buildUnitFundingSource(String fundingSourceNumber) {
        ProtocolFundingSource fundingSource = null;
        
        if (StringUtils.isNotBlank(fundingSourceNumber)) {
            String fundingSourceName = getUnitService().getUnitName(fundingSourceNumber);
            String fundingSourceTitle = Constants.EMPTY_STRING;
            fundingSource = new ProtocolFundingSource(fundingSourceNumber, FundingSourceType.UNIT, fundingSourceName, fundingSourceTitle); 
        }
        
        return fundingSource;
    }
    
    /**
     * Builds a Protocol funding source for a Development Proposal.
     * 
     * @param fundingSourceNumber the human-readable number for the funding source
     * @param fundingSourceName the name of the funding source
     * @return an instance of a Protocol funding source
     */
    private ProtocolFundingSource buildProposalDevelopmentFundingSource(String fundingSourceNumber, String fundingSourceName) {
        ProtocolFundingSource fundingSource = null;

        if (StringUtils.isNotBlank(fundingSourceNumber)) {
            String fundingSourceTitle = Constants.EMPTY_STRING;
            DevelopmentProposal devProposal = getDevelopmentProposal(fundingSourceNumber);
            if (devProposal != null) {
                fundingSourceName = devProposal.getSponsorName();
                fundingSourceTitle = devProposal.getTitle();
            }
            fundingSource = new ProtocolFundingSource(fundingSourceNumber, FundingSourceType.PROPOSAL_DEVELOPMENT, fundingSourceName, fundingSourceTitle); 
        }
        
        return fundingSource;
    }

    /**
     * Retrieves a Development Proposal using {@code fundingSourceNumber} for identification.
     * 
     * @param fundingSourceNumber the human-readable number for the funding source
     * @return the latest Development Proposal matching {@code fundingSourceNumber}
     */
    private DevelopmentProposal getDevelopmentProposal(String fundingSourceNumber) {
        return getBusinessObjectService().findBySinglePrimaryKey(DevelopmentProposal.class, fundingSourceNumber);
    }
    
    /**
     * Builds a Protocol funding source for an Institutional Proposal.
     * 
     * @param fundingSourceNumber the human-readable number for the funding source
     * @param fundingSourceName the name of the funding source
     * @return an instance of a Protocol funding source
     */
    private ProtocolFundingSource buildInstitutionalProposalFundingSource(String fundingSourceNumber, String fundingSourceName) {
        ProtocolFundingSource fundingSource = null;
        
        if (StringUtils.isNotBlank(fundingSourceNumber)) {
            String fundingSourceTitle = Constants.EMPTY_STRING;
            InstitutionalProposal instProposal = getInstitutionalProposal(fundingSourceNumber);
            if (instProposal != null) {
                fundingSourceName = instProposal.getSponsorName();
                fundingSourceTitle = instProposal.getTitle();
            }
            fundingSource = new ProtocolFundingSource(fundingSourceNumber, FundingSourceType.INSTITUTIONAL_PROPOSAL, fundingSourceName, fundingSourceTitle); 
        }
        
        return fundingSource;
    }

    /**
     * Retrieves an Institutional Proposal using {@code fundingSourceNumber} for identification.
     * 
     * @param fundingSourceNumber the human-readable number for the funding source
     * @return the latest Institutional Proposal matching {@code fundingSourceNumber}
     */
    private InstitutionalProposal getInstitutionalProposal(String fundingSourceNumber) {
        InstitutionalProposal institutionalProposal = getInstitutionalProposalService().getActiveInstitutionalProposalVersion(fundingSourceNumber);
        
        if (institutionalProposal == null) {
            institutionalProposal = getInstitutionalProposalService().getPendingInstitutionalProposalVersion(fundingSourceNumber);
        }

        return institutionalProposal;
    }
    
    /**
     * Builds a Protocol funding source for an Award.
     * 
     * @param fundingSourceNumber the human-readable number for the funding source
     * @param fundingSourceName the name of the funding source
     * @return an instance of a Protocol funding source
     */
    private ProtocolFundingSource buildAwardFundingSource(String fundingSourceNumber, String fundingSourceName) {
        ProtocolFundingSource fundingSource = null;
        
        if (StringUtils.isNotBlank(fundingSourceNumber)) {
            String fundingSourceTitle = Constants.EMPTY_STRING;
            Award award  = getAward(fundingSourceNumber);
            if (award != null) {
                fundingSourceName = award.getSponsorName();
                fundingSourceTitle = award.getTitle();
            }
            fundingSource = new ProtocolFundingSource(fundingSourceNumber, FundingSourceType.AWARD, fundingSourceName, fundingSourceTitle); 
        }
        
        return fundingSource;
    }

    /**
     * Retrieves an Award using {@code fundingSourceNumber} for identification.
     * 
     * @param fundingSourceNumber the human-readable number for the funding source
     * @return the latest Award matching {@code fundingSourceNumber}
     */
    private Award getAward(String fundingSourceNumber) {
        Award award = null;
        
        List<Award> awards = getAwardService().findAwardsForAwardNumber(fundingSourceNumber);
        
        if (!awards.isEmpty()) {
            award = awards.get(awards.size() - 1);
        }
        
        return award;
    }
    
    /**
     * Builds a Protocol funding source for any other entry.
     * 
     * @param fundingSourceTypeCode the type code of the funding source
     * @param fundingSourceNumber the human-readable number for the funding source
     * @param fundingSourceName the name of the funding source
     * @return an instance of a Protocol funding source
     */
    private ProtocolFundingSource buildOtherFundingSource(String fundingSourceTypeCode, String fundingSourceNumber, String fundingSourceName) {
        ProtocolFundingSource fundingSource = null;
        
        FundingSourceType fundingSourceType = getFundingSourceTypeService().getFundingSourceType(fundingSourceTypeCode);
        
        if (fundingSourceType != null && StringUtils.isNotBlank(fundingSourceNumber)) {
            fundingSource = new ProtocolFundingSource(fundingSourceNumber, fundingSourceTypeCode, fundingSourceName, Constants.EMPTY_STRING);
        }
        
        return fundingSource;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService#isValidIdForType(org.kuali.kra.irb.protocol.funding.ProtocolFundingSource)
     */
    public boolean isValidIdForType(ProtocolFundingSource protocolFundingSource) {
        boolean valid = false;

        if (protocolFundingSource != null && StringUtils.isNotBlank(protocolFundingSource.getFundingSourceTypeCode()))  {
            String fundingSourceTypeCode = protocolFundingSource.getFundingSourceTypeCode();
            if (FundingSourceType.OTHER.equals(fundingSourceTypeCode)) {
                valid = true;
            } else if (FundingSourceType.PROPOSAL_DEVELOPMENT.equals(fundingSourceTypeCode) && !isDevelopmentProposalLinkEnabled()) {
                valid = true;
            } else if (FundingSourceType.INSTITUTIONAL_PROPOSAL.equals(fundingSourceTypeCode) && !isInstitionalProposalLinkEnabled()) {
                valid = true;
            } else if (FundingSourceType.AWARD.equals(fundingSourceTypeCode) && !isAwardLinkEnabled()) {
                valid = true;
            } else {
                String number = protocolFundingSource.getFundingSourceNumber();
                String name = protocolFundingSource.getFundingSourceName();
                
                ProtocolFundingSource source = updateProtocolFundingSource(fundingSourceTypeCode.toString(), number, name);       
                
                if (source != null && (StringUtils.isNotBlank(source.getFundingSourceName()))) {
                    valid = true;
                }
            }
        }
        
        return valid;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService#getLookupParameters(java.lang.String)
     */
    public Entry<String, String> getLookupParameters(String fundingSourceTypeCode) {        
        HashMap<String, String> boAndFields = new HashMap<String, String>();
        FundingSourceLookup sourceLookup = FundingSourceLookup.OTHER;
        
        if (FundingSourceType.UNIT.equals(fundingSourceTypeCode)) {
            sourceLookup = FundingSourceLookup.UNIT;
        } else if (FundingSourceType.SPONSOR.equals(fundingSourceTypeCode)) {
            sourceLookup = FundingSourceLookup.SPONSOR;
        } else if (FundingSourceType.AWARD.equals(fundingSourceTypeCode)) {
            sourceLookup = FundingSourceLookup.AWARD;
        } else if (FundingSourceType.PROPOSAL_DEVELOPMENT.equals(fundingSourceTypeCode)) {
            sourceLookup = FundingSourceLookup.PROPOSAL_DEVELOPMENT;
        } else if (FundingSourceType.INSTITUTIONAL_PROPOSAL.equals(fundingSourceTypeCode)) {
            sourceLookup = FundingSourceLookup.INSTITUTIONAL_PROPOSAL;
        } else {
            throw new IllegalArgumentException(
                    "Funding source parameter lookup error. The processIsValidLookup rule was not invoked or missed error condition.");
        }
           
        if (sourceLookup != FundingSourceLookup.OTHER) {
            String fieldConversions = createCustomFieldConversions(sourceLookup);        
            boAndFields.put(sourceLookup.getBOClass().getName(), fieldConversions);
        }
        
        return boAndFields.entrySet().iterator().next();
    }
    
    /**
     * Create the custom field conversion string for the lookup based on {@code fundingSourceLookup}.
     *
     * @param fundingSourceLookup the lookup definition to construct the lookup string for
     * @return a lookup field conversion string
     */
    private String createCustomFieldConversions(FundingSourceLookup fundingSourceLookup) {
        StringBuffer fieldConversions = new StringBuffer();
        if (fundingSourceLookup != FundingSourceLookup.OTHER) {
            // Only some funding sources require a key code for proper lookup
            // This value is unused and goes to a dummy field
            if (StringUtils.isNotBlank(fundingSourceLookup.getKeyCode())) {
                fieldConversions.append(fundingSourceLookup.getKeyCode() + Constants.COLON);
                fieldConversions.append(Constants.PROTOCOL_FUNDING_SOURCE_ID_FIELD + Constants.COMMA);
            }
            
            fieldConversions.append(fundingSourceLookup.getNumber() + Constants.COLON);
            fieldConversions.append(Constants.PROTOCOL_FUNDING_SOURCE_NUMBER_FIELD + Constants.COMMA);
            
            fieldConversions.append(fundingSourceLookup.getName() + Constants.COLON);
            fieldConversions.append(Constants.PROTOCOL_FUNDING_SOURCE_NAME_FIELD);
            
            // Not all funding sources have a title
            if (StringUtils.isNotBlank(fundingSourceLookup.getTitle())) {
                fieldConversions.append(Constants.COMMA);
                fieldConversions.append(fundingSourceLookup.getTitle() + Constants.COLON);
                fieldConversions.append(Constants.PROTOCOL_FUNDING_SOURCE_TITLE_FIELD);
            }
        }
        return fieldConversions.toString();
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService#updateLookupParameter(java.lang.String, java.lang.String, java.lang.String)
     */
    public String updateLookupParameter(String parameter, String boClassName, String fieldConversions) {
        StringBuffer fullParameterBuffer = new StringBuffer(parameter);
        int start = fullParameterBuffer.indexOf(KRADConstants.METHOD_TO_CALL_BOPARM_LEFT_DEL) + KRADConstants.METHOD_TO_CALL_BOPARM_LEFT_DEL.length();
        int end = fullParameterBuffer.indexOf(KRADConstants.METHOD_TO_CALL_BOPARM_RIGHT_DEL);        
        fullParameterBuffer.replace(start, end, boClassName);

        start = fullParameterBuffer.indexOf(KRADConstants.METHOD_TO_CALL_PARM1_LEFT_DEL) + KRADConstants.METHOD_TO_CALL_PARM1_LEFT_DEL.length();
        end = fullParameterBuffer.indexOf(KRADConstants.METHOD_TO_CALL_PARM1_RIGHT_DEL);        
        fullParameterBuffer.replace(start, end, fieldConversions);
        
        return fullParameterBuffer.toString();
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService#getViewProtocolFundingSourceUrl(
     *      org.kuali.kra.irb.protocol.funding.ProtocolFundingSource, org.kuali.kra.irb.protocol.ProtocolProtocolAction)
     */
    public String getViewProtocolFundingSourceUrl(ProtocolFundingSource protocolFundingSource, ProtocolProtocolAction action) throws Exception {
        String fundingSourceTypeCode = protocolFundingSource.getFundingSourceTypeCode();
        String fundingSourceNumber = protocolFundingSource.getFundingSourceNumber();
        String viewUrl = null;
        
        if (FundingSourceType.SPONSOR.equals(fundingSourceTypeCode)) {
            Sponsor sponsor = new Sponsor();
            sponsor.setSponsorCode(fundingSourceNumber);
            viewUrl = buildViewMaintenanceFundingSourceUrl(sponsor, FundingSourceLookup.SPONSOR.getNumber());
        } else if (FundingSourceType.UNIT.equals(fundingSourceTypeCode)) {
            Unit unit = new Unit();
            unit.setUnitNumber(fundingSourceNumber);
            viewUrl = buildViewMaintenanceFundingSourceUrl(unit, FundingSourceLookup.UNIT.getNumber());
        } else if (FundingSourceType.PROPOSAL_DEVELOPMENT.equals(fundingSourceTypeCode)) {
            DevelopmentProposal developmentProposal = getDevelopmentProposal(fundingSourceNumber);
            String documentNumber = developmentProposal.getProposalDocument().getDocumentNumber();
            viewUrl = buildViewTransactionalFundingSourceUrl(documentNumber, action);
        } else if (FundingSourceType.INSTITUTIONAL_PROPOSAL.equals(fundingSourceTypeCode)) {
            InstitutionalProposal institutionalProposal = getInstitutionalProposal(fundingSourceNumber);
            String documentNumber = institutionalProposal.getInstitutionalProposalDocument().getDocumentNumber();
            viewUrl = buildViewTransactionalFundingSourceUrl(documentNumber, action);
        } else if (FundingSourceType.AWARD.equals(fundingSourceTypeCode)) {
            Award award = getAward(fundingSourceNumber);
            String documentNumber = award.getAwardDocument().getDocumentNumber();
            viewUrl = buildViewTransactionalFundingSourceUrl(documentNumber, action);
        }
        
        return viewUrl;
    }
    
    /**
     * Builds a URL to a view-only maintenance document for a funding source.
     * 
     * @param businessObject the maintenance document's business object
     * @param propertyName the primary key of the business object
     * @return the URL to view the maintenance document
     */
    private String buildViewMaintenanceFundingSourceUrl(BusinessObject businessObject, String propertyName) {
        HtmlData forward = getProtocolLookupableHelperService().getInquiryUrl(businessObject, propertyName);
        return Utilities.substituteConfigParameters(MAINT_DOC_LOOKUP_URL_PREFIX + ((HtmlData.AnchorHtmlData) forward).getHref());
    }
    
    /**
     * Builds a URL to a view-only transactional document for a funding source.
     * 
     * @param documentNumber the number of the document
     * @param action a back reference back to the action
     * @return the URL to view the transactional document
     * @throws Exception
     */
    private String buildViewTransactionalFundingSourceUrl(String documentNumber, ProtocolProtocolAction action) throws Exception {
        Document document = getDocumentService().getByDocumentHeaderId(documentNumber);
        String routeHeaderId = document.getDocumentHeader().getWorkflowDocument().getDocumentId();
        
        Properties parameters = new Properties();
        parameters.put("viewDocument", Boolean.TRUE.toString());
        parameters.put("viewFundingSource", Boolean.TRUE.toString());
        
        StringBuilder builder = new StringBuilder();
        builder.append(action.buildForwardUrl(routeHeaderId));
        for (Map.Entry<Object, Object> parameter : parameters.entrySet()) {
            builder.append("&");
            builder.append(parameter.getKey());
            builder.append("=");
            builder.append(parameter.getValue());
        }
        return builder.toString();
    }

    /**
     * Returns whether the Protocol to Development Proposal link is enabled.
     * 
     * @return true if the Protocol to Development Proposal link is enabled
     */
    private boolean isDevelopmentProposalLinkEnabled() {
        return this.isLinkEnabled(Constants.ENABLE_PROTOCOL_TO_DEV_PROPOSAL_LINK);     
    }
    
    /**
     * Returns whether the Protocol to Institutional Proposal link is enabled.
     * 
     * @return true if the Protocol to Institutional Proposal link is enabled
     */
    private boolean isInstitionalProposalLinkEnabled() {
        return this.isLinkEnabled(Constants.ENABLE_PROTOCOL_TO_PROPOSAL_LINK);       
    }
    
    /**
     * Returns whether the Protocol to Award link is enabled.
     * 
     * @return true if the Protocol to Award link is enabled
     */
    private boolean isAwardLinkEnabled() {
        return this.isLinkEnabled(Constants.ENABLE_PROTOCOL_TO_AWARD_LINK);          
    }
    
    /**
     * Returns whether a Protocol can be linked to another transactional document in the system.
     * 
     * @param link the parameter name
     * @return true if the link is enabled, false otherwise
     */
    protected boolean isLinkEnabled(String link) {
        assert link != null : "link is null";
        
        boolean isLinkEnabled = false;
        
        if (!parameterService.parameterExists(ProtocolDocument.class, link)) {
            isLinkEnabled = true;
        } else {
            isLinkEnabled = parameterService.getParameterValueAsBoolean(ProtocolDocument.class, link);
        }
        
        return isLinkEnabled;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService#isEditable(java.lang.String)
     */
    public boolean isEditable(String fundingSourceTypeCode) {
        boolean isEditable = true;
        
        if (FundingSourceType.SPONSOR.equals(fundingSourceTypeCode)) {
            isEditable = false;
        } else if (FundingSourceType.UNIT.equals(fundingSourceTypeCode)) {
            isEditable = false;
        } else if (FundingSourceType.PROPOSAL_DEVELOPMENT.equals(fundingSourceTypeCode) && isDevelopmentProposalLinkEnabled()) {
            isEditable = false;
        } else if (FundingSourceType.INSTITUTIONAL_PROPOSAL.equals(fundingSourceTypeCode) && isInstitionalProposalLinkEnabled()) {
            isEditable = false;
        } else if (FundingSourceType.AWARD.equals(fundingSourceTypeCode) && isAwardLinkEnabled()) {
            isEditable = false;
        }
        
        return isEditable;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService#isLookupable(java.lang.String)
     */
    public boolean isLookupable(String fundingSourceTypeCode) {
        boolean isLookupable = false;
        
        if (FundingSourceType.SPONSOR.equals(fundingSourceTypeCode)) {
            isLookupable = true;
        } else if (FundingSourceType.UNIT.equals(fundingSourceTypeCode)) {
            isLookupable = true;
        } else if (FundingSourceType.AWARD.equals(fundingSourceTypeCode) && isAwardLinkEnabled()) {
            isLookupable = true;
        } else if (FundingSourceType.PROPOSAL_DEVELOPMENT.equals(fundingSourceTypeCode) && isDevelopmentProposalLinkEnabled()) {
            isLookupable = true;
        } else if (FundingSourceType.INSTITUTIONAL_PROPOSAL.equals(fundingSourceTypeCode) && isInstitionalProposalLinkEnabled()) {
            isLookupable = true;
        }
        
        return isLookupable;
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