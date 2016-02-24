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
package org.kuali.kra.award.lookup;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.document.authorization.AwardDocumentAuthorizer;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.dao.AwardLookupDao;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
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
public class AwardLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final long serialVersionUID = 6304433555064511153L;

    static final String PERSON_ID = "personId";
    static final String ROLODEX_ID = "rolodexId";
    static final String UNIT_NUMBER = "unitNumber";
    static final String PI_NAME = "principalInvestigatorName";
    static final String OSP_ADMIN_NAME = "ospAdministratorName";

    public static final String OSP_ADMIN_USERNAME_PATH = "leadUnit.unitAdministrators.person.userName";
    public static final String OSP_ADMIN_PERSON_ID_PATH = "leadUnit.unitAdministrators.personId";
    public static final String OSP_ADMIN_TYPE_CODE_PATH = "leadUnit.unitAdministrators.unitAdministratorTypeCode";
    public static final String OSP_ADMIN_TYPE_CODE_VALUE = "2";
    public static final String PRINCIPAL_ID = "principalId";
    public static final String PRINCIPAL_NAME = "principalName";


    private transient KcPersonService kcPersonService;
    private AwardLookupDao awardLookupDao;
    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        Map<String, String> formProps = new HashMap<String, String>();
        if (!StringUtils.isEmpty(fieldValues.get(OSP_ADMIN_USERNAME_PATH))) {
            KcPerson person = kcPersonService.getKcPersonByUserName(fieldValues.get(OSP_ADMIN_USERNAME_PATH));
            if (person != null) {
                fieldValues.put(OSP_ADMIN_PERSON_ID_PATH, person.getPersonId());
                fieldValues.put(OSP_ADMIN_TYPE_CODE_PATH, OSP_ADMIN_TYPE_CODE_VALUE);
            }
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
            if (award != null && authorizer.canOpen(award.getAwardDocument(), user)) {
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
                else if (field.getPropertyName().equals(OSP_ADMIN_USERNAME_PATH)) {
                    field.setFieldConversions(PRINCIPAL_NAME + ":" + OSP_ADMIN_USERNAME_PATH
                            + "," + PRINCIPAL_ID + ":" + OSP_ADMIN_PERSON_ID_PATH);
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
                try {
                final KcPerson inqBo = this.kcPersonService.getKcPersonByPersonId(principalInvestigator.getPersonId());
                inquiryUrl = super.getInquiryUrl(inqBo, PERSON_ID);
                }
                catch (IllegalArgumentException e) {
                    LOG.info("getPrincipalInvestigatorNameInquiryUrl(Award award): ignoring missing person/entity: "
                            + principalInvestigator.getPersonId());
                }
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
