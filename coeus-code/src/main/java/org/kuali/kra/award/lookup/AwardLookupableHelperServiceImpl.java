/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.award.lookup;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.document.authorization.AwardDocumentAuthorizer;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.dao.AwardLookupDao;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

import java.util.*;

/**
 * This class provides Award lookup support
 */
class AwardLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {    

    static final String PERSON_ID = "personId";
    static final String ROLODEX_ID = "rolodexId";
    static final String UNIT_NUMBER = "unitNumber";
    static final String PI_NAME = "principalInvestigatorName";
    static final String OSP_ADMIN_NAME = "ospAdministratorName";

    private static final long serialVersionUID = 6304433555064511153L;
    
    private transient KcPersonService kcPersonService;
    private AwardLookupDao awardLookupDao;
    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
//        if (this.getParameters().containsKey(USER_ID)) {
//            fieldValues.put("projectPersons.personId", ((String[]) this.getParameters().get(USER_ID))[0]);
//        }
        Map<String, String> formProps = new HashMap<String, String>();
        if (!StringUtils.isEmpty(fieldValues.get("lookupOspAdministratorName"))) {
            formProps.put("fullName", fieldValues.get("lookupOspAdministratorName"));
            formProps.put("unitAdministratorTypeCode", "2");
        }
        fieldValues.remove("lookupOspAdministratorName");
        if (!formProps.isEmpty()) {
            List<Long> ids = new ArrayList<Long>();
            Collection<AwardUnitContact> persons = getLookupService().findCollectionBySearch(AwardUnitContact.class, formProps);
            if (persons.isEmpty()) {
                return new ArrayList<Award>();
            }
            for (AwardUnitContact person : persons) {
                ids.add(person.getAwardContactId());
            }
            fieldValues.put("awardUnitContacts.awardContactId", StringUtils.join(ids, '|'));
        }
        
        boolean usePrimaryKeys = getLookupService().allPrimaryKeyValuesPresentAndNotWildcard(Award.class, fieldValues);
        
        setBackLocation(fieldValues.get(KRADConstants.BACK_LOCATION));
        setDocFormKey(fieldValues.get(KRADConstants.DOC_FORM_KEY));
        setReferencesToRefresh(fieldValues.get(KRADConstants.REFERENCES_TO_REFRESH));
        
        List<Award> unboundedResults = (List<Award>)getAwardLookupDao().getAwardSearchResults(fieldValues, usePrimaryKeys);
        
        List<Award> filteredResults = new ArrayList<Award>();
        
        filteredResults = (List<Award>) filterForPermissions(unboundedResults);
        if (unboundedResults instanceof CollectionIncomplete) {
            filteredResults = new CollectionIncomplete<Award>(
                    filteredResults, ((CollectionIncomplete)unboundedResults).getActualSizeIfTruncated());
        }
        return filteredResults;
    }


    /**
     * This method filters results based so that the person doing the lookup only gets back the documents he has permission view.
     * @param results
     * @return
     */
    public List<Award> filterForPermissions(List<Award> results) {
        Person user = GlobalVariables.getUserSession().getPerson();
        AwardDocumentAuthorizer authorizer = new AwardDocumentAuthorizer();
        List<Award> filteredResults = new ArrayList<Award>();
        // if the user has permission.
        for (Award award : results) {
            if (award!=null && authorizer.canOpen(award.getAwardDocument(), user)) {
                filteredResults.add(award);
            }
        }
        return filteredResults;
    }
    /**
     * add open, copy and medusa links to actions list
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.krad.bo.BusinessObject, java.util.List)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
        AwardDocument document = ((Award) businessObject).getAwardDocument();
        htmlDataList.add(getOpenLink((Award) businessObject, false));
        htmlDataList.add(getCopyLink((Award) businessObject, false));
        htmlDataList.add(getMedusaLink((Award) businessObject, false));
        return htmlDataList;
    }

    /**
     * This override is reset field definitions
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
    @Override
    public List<Row> getRows() {
        List<Row> rows =  super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(PI_NAME)) {
                    super.updateLookupField(field, PI_NAME, AwardPerson.class.getName());
                }
            }
        }
        return rows;
    }
                 
    /**
     * Sets the KC Person Service.
     * @param kcPersonService the kc person service
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }


    /**
     * This method is for fields that do not have inquiry created by lookup frame work.
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getInquiryUrl(org.kuali.core.bo.BusinessObject, java.lang.String)
     */
    @Override
    public HtmlData getInquiryUrl(BusinessObject bo, String propertyName) {
        Award award = (Award) bo;
        HtmlData inquiryUrl = super.getInquiryUrl(bo, propertyName);
        if (propertyName.equals(UNIT_NUMBER)) {
            inquiryUrl = getUnitNumberInquiryUrl(award);
        } else if (propertyName.equals(PI_NAME)) {
            inquiryUrl = getPrincipalInvestigatorNameInquiryUrl(award);            
        } else if(propertyName.equals(OSP_ADMIN_NAME)) {
            inquiryUrl = getOspAdminNameInquiryUrl(award);
        }
        return inquiryUrl;
    }

    /**
     * @param award
     * @return
     */
    protected AnchorHtmlData getOpenLink(Award award, Boolean viewOnly) {
        AwardDocument awardDocument = award.getAwardDocument();
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("open");
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.DOC_HANDLER_METHOD);
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("viewDocument", viewOnly.toString());
        parameters.put("docOpenedFromAwardSearch", "true");
        parameters.put("docId", awardDocument.getDocumentNumber());
        parameters.put("placeHolderAwardId", award.getAwardId().toString());
        String href = UrlFactory.parameterizeUrl("../"+getHtmlAction(), parameters);
        htmlData.setHref(href);
        return htmlData;
    }
    
    protected AnchorHtmlData getMedusaLink(Award award, Boolean readOnly) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText(MEDUSA);
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "medusa");
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("viewDocument", readOnly.toString());
        parameters.put("docId", award.getAwardDocument().getDocumentNumber());
        parameters.put("docOpenedFromAwardSearch", "true");
        parameters.put("placeHolderAwardId", award.getAwardId().toString());
        String href  = UrlFactory.parameterizeUrl("../"+getHtmlAction(), parameters);
        
        htmlData.setHref(href);
        return htmlData;
    }    
    
    protected AnchorHtmlData getCopyLink(Award award, Boolean readOnly) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("copy");
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "awardActions");
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("viewDocument", readOnly.toString());
        parameters.put("docId", award.getAwardDocument().getDocumentNumber());
        parameters.put("docOpenedFromAwardSearch", "true");
        parameters.put("placeHolderAwardId", award.getAwardId().toString());
        String href  = UrlFactory.parameterizeUrl("../"+getHtmlAction(), parameters);
        
        htmlData.setHref(href);
        return htmlData;
    }
    
    protected HtmlData getOspAdminNameInquiryUrl(Award award) {
        KcPerson ospAdministrator = award.getOspAdministrator();
        if (ospAdministrator != null) {
            final KcPerson inqBo = this.kcPersonService.getKcPersonByPersonId(ospAdministrator.getPersonId());
            return super.getInquiryUrl(inqBo, PERSON_ID);
        } else {
            return null;
        }
    }

    protected HtmlData getPrincipalInvestigatorNameInquiryUrl(Award award) {
        HtmlData inquiryUrl = null;
        AwardPerson principalInvestigator = award.getPrincipalInvestigator();
        if (principalInvestigator != null) {
            if (StringUtils.isNotBlank(principalInvestigator.getPersonId())) {
                final KcPerson inqBo = this.kcPersonService.getKcPersonByPersonId(principalInvestigator.getPersonId());
                inquiryUrl = super.getInquiryUrl(inqBo, PERSON_ID);
            } else {
                if (principalInvestigator.getRolodexId() != null) {
                    Rolodex inqBo = new Rolodex();
                    inqBo.setRolodexId(principalInvestigator.getRolodexId());
                    inquiryUrl = super.getInquiryUrl(inqBo, ROLODEX_ID);
                }
            }
            
        }
        return inquiryUrl;
    }

    /**
     * This method...
     * @param bo
     * @param propertyName
     * @return
     */
    protected HtmlData getUnitNumberInquiryUrl(Award award) {
        Unit inqBo = new Unit();
        Unit leadUnit = award.getLeadUnit();
        inqBo.setUnitNumber(leadUnit != null ? leadUnit.getUnitNumber() : null);
        return super.getInquiryUrl(inqBo, UNIT_NUMBER);
    }

    @Override
    protected String getHtmlAction() {
        return "awardHome.do";
    }
    
    /**
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#createdEditHtmlData(org.kuali.rice.krad.bo.BusinessObject)
     * 
     * Edit is not supported for Award lookup, so we'll just no-op
     */
    @Override
    protected void addEditHtmlData(List<HtmlData> htmlDataList, BusinessObject businessObject) {
        //no-op
    }

    @Override
    protected String getDocumentTypeName() {
        return "AwardDocument";
    }
    
    @Override
    protected String getKeyFieldName() {
        return "awardId";
    }

    public AwardLookupDao getAwardLookupDao() {
        return awardLookupDao;
    }


    public void setAwardLookupDao(AwardLookupDao awardLookupDao) {
        this.awardLookupDao = awardLookupDao;
    }


}
