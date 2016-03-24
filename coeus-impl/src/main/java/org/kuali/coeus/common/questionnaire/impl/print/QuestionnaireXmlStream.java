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
package org.kuali.coeus.common.questionnaire.impl.print;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.printing.schema.*;
import org.kuali.kra.printing.schema.QuestionnaireDocument.Questionnaire;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireService;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.XmlObjectSerializerService;
import org.kuali.rice.krad.workflow.KualiDocumentXmlMaterializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * This class generates XML that conforms with the XSD related to Budget total
 * Report. The data for XML is derived from {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 */
@Component("questionnaireXmlStream")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuestionnaireXmlStream implements XmlStream {

    public static final String DOCUMENT_NUMBER = "documentNumber";
    public static final String QUESTIONNAIRE = "questionnaire";
    public static final String QUESTIONNAIRE_QUESTIONS = "questionnaireQuestions";
    public static final String ID = "id";
    public static final String MODULE_CODE = "moduleCode";
    public static final String SUB_MODULE_CODE = "subModuleCode";
    public static final String SUBMISSION_NUMBER = "submissionNumber";
    public static final String PROTOCOL_NUMBER = "protocolNumber";
    public static final String MODULE_SUB_ITEM_CODE = "moduleSubItemCode";
    public static final String COEUS_MODULE_SUB_ITEM_CODE = "coeusModuleSubItemCode";
    public static final String QUESTION = "question";
    public static final String PERSON_ID = "personId";
    private static final Log LOG = LogFactory.getLog(QuestionnaireXmlStream.class);
    private static final int QUESTION_TYPE_INT = 3;

    List<QuestionnaireQuestion> sortedQuestionnaireQuestions;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("questionnaireService")
    private QuestionnaireService questionnaireService;

    @Autowired
    @Qualifier("questionnaireAnswerService")
    private QuestionnaireAnswerService questionnaireAnswerService;

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Autowired
    @Qualifier("routeHeaderService")
    private RouteHeaderService routeHeaderService;

    @Autowired
    @Qualifier("xmlObjectSerializerService")
    private XmlObjectSerializerService xmlObjectSerializerService;

   /**
     * This method generates XML committee report. It uses data passed in
     * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The XMl once
     * generated is returned as {@link XmlObject}
     * 
     * @param printableBusinessObject
     *            using which XML is generated
     * @param reportParameters
     *            parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     */
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        /* 
         * Just want to mention that Questionnaire is a maintenance document (not transactional doc), so passed in document will be null.
         * the report parameters does have a documentNumber, so it can be retrieved from document xml content.
         * "Questionnaire Answer" will pass in protocol document, so, it is fine.
         */
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        try {
            xmlObjectList.put("Questionnaire", getQuestionnaireData(printableBusinessObject,reportParameters));
        }
        catch (PrintingException e) {
            LOG.error(e.getMessage(), e);
        }
        return xmlObjectList;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }


    /**
     * 
     * This method is to get the Questionnaire data from Questionnaire BO and populate it to QuestionnaireDocument,
     * which generated Questionnaire schema.
     * @throws PrintingException
     */
    @SuppressWarnings("unchecked")
    private QuestionnaireDocument getQuestionnaireData(
        KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> params) throws PrintingException {
        QuestionnaireDocument questionnaireDocument = QuestionnaireDocument.Factory.newInstance();
        Questionnaire questionnaireType = questionnaireDocument.addNewQuestionnaire();
        
        String documentNumber = (String)params.get(DOCUMENT_NUMBER);
        Object questionnaireSeqenceId= params.get(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME);
        org.kuali.coeus.common.questionnaire.framework.core.Questionnaire questionnaire =
                (org.kuali.coeus.common.questionnaire.framework.core.Questionnaire)params.get(QUESTIONNAIRE);
        if (questionnaire == null) {
            if (questionnaireSeqenceId != null) { 
                Map<String,Long> qParam = new HashMap<String,Long>();
                qParam.put(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME, Long.parseLong(questionnaireSeqenceId.toString()));
                List<org.kuali.coeus.common.questionnaire.framework.core.Questionnaire> questionnaires =
                    (List)businessObjectService.findMatchingOrderBy(
                            org.kuali.coeus.common.questionnaire.framework.core.Questionnaire.class, qParam, ID, false);
                questionnaire = questionnaires.get(0);
                // not sure why need this.  If it is not refreshed, some may get empty Questions
                questionnaire.refreshReferenceObject(QUESTIONNAIRE_QUESTIONS);
            } else {
                questionnaire = findQuestionnaireObject(documentNumber);
            }
        }
        
        Boolean questionnaireCompletionFlag = (Boolean)params.get("QUESTIONNAIRE_COMPLETION_FLAG");
        questionnaireCompletionFlag = questionnaireCompletionFlag==null?Boolean.FALSE:questionnaireCompletionFlag;
        ModuleQuestionnaireBean moduleQuestionnaireBean = null;
        if (printableBusinessObject != null) {
            moduleQuestionnaireBean = getQuestionnaireAnswerHeaderBean(printableBusinessObject, params);
        }
        if(questionnaire != null) {
            Integer questId = questionnaire.getQuestionnaireSeqIdAsInteger();
            if(questId!=null){
                questionnaireType.setQuestionnaireId(questId);
            }
            questionnaireType.setQuestionnaireName(questionnaire.getName());
            questionnaireType.setQuestionnaireDesc(questionnaire.getDescription());
            setQuestionInfoData(questionnaire, moduleQuestionnaireBean,questionnaireType,questionnaireCompletionFlag, printableBusinessObject);
            setUserOption(params,questionnaireType);
            setAnswerInfo(printableBusinessObject,moduleQuestionnaireBean,questionnaireType);
            if(moduleQuestionnaireBean!=null && moduleQuestionnaireBean.getModuleItemCode() != null) {
                setModuleUsage(moduleQuestionnaireBean,questionnaireType);
            }
            else{
                setModuleUsageList(questionnaire,questionnaireType);
            }
            if (moduleQuestionnaireBean != null && moduleQuestionnaireBean.getModuleItemCode() != null) {
                String moduleCode = moduleQuestionnaireBean.getModuleItemCode();
                String moduleSubcode = moduleQuestionnaireBean.getModuleSubItemCode();
                if (moduleCode.equals(CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE)  && "0".equals(moduleSubcode)) {
                    setDevProposalInfo((DevelopmentProposal) printableBusinessObject,questionnaireType);
                } else if (moduleCode.equals(CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE)  
                        && CoeusSubModule.PROPOSAL_PERSON_CERTIFICATION.equals(moduleSubcode)) {
                    ProposalPerson person = (ProposalPerson) printableBusinessObject;
                    setDevProposalInfo(person,questionnaireType);
                } else if (moduleCode.equals(CoeusModule.IRB_MODULE_CODE)) {
                    setProtocolInfo((Protocol) printableBusinessObject,questionnaireType);
                }
            }
        }
        return questionnaireDocument;
    }
    /**
     * This method is to set the protocol info
     */
    private void setProtocolInfo(Protocol protocolInfoBean, Questionnaire questionnaireType) {
        ProtocolInfoType protocolInfo = questionnaireType.addNewProtocolInfo();
        if(protocolInfoBean != null) {
            Person personInfo = protocolInfo.addNewInvestigator();
            if(protocolInfoBean.getPrincipalInvestigator()!=null){
                personInfo.setFullname(protocolInfoBean.getPrincipalInvestigator().getPersonName());
            }
            protocolInfo.setTitle(protocolInfoBean.getTitle());
            protocolInfo.setInvestigator(personInfo);
        }
    }
    /**
     * 
     * This method is to set development proposal info to XMLBeans object
     */
    private void setDevProposalInfo(DevelopmentProposal proposalBean, Questionnaire questionnaireType) {
        if(proposalBean!=null){
            ProposalInfoType proposalInfo = questionnaireType.addNewProposalInfo();
            proposalInfo.setTitle(proposalBean.getTitle());
            proposalInfo.setProposalPersonUsed(0);
            String investigatorName = proposalBean.getPrincipalInvestigatorName();
            if(investigatorName!=null){
                Person personInfo = proposalInfo.addNewInvestigator();
                personInfo.setFullname(investigatorName);
            }  
        }
    }
    
    private void setDevProposalInfo(ProposalPerson person, Questionnaire questionnaireType) {
        DevelopmentProposal proposalBean = person.getDevelopmentProposal();
        if (proposalBean != null) {
            ProposalInfoType proposalInfo = questionnaireType.addNewProposalInfo();
            proposalInfo.setTitle(proposalBean.getTitle());
            String investigatorName = proposalBean.getPrincipalInvestigatorName();
            if (investigatorName != null) {
                Person personInfo = proposalInfo.addNewInvestigator();
                personInfo.setFullname(investigatorName);
            }
            String personName = person.getFullName();
            String personRole = person.getRole().getDescription();
            proposalInfo.setProposalPersonUsed(1);
            proposalInfo.setProposalPersonName(personName);
            proposalInfo.setProposalPersonRole(personRole);
            
        }
    }
    
    /**
     * 
     * This method is to set the Answer details to Questionnaire XMLBeans object
     * @param printableBusinessObject
     * @param moduleQuestionnaireBean
     * @param questionnaireType
     */
    private void setAnswerInfo(KcPersistableBusinessObjectBase printableBusinessObject, ModuleQuestionnaireBean moduleQuestionnaireBean,
            Questionnaire questionnaireType) {
        AnswerHeaderType answerHeader = questionnaireType.addNewAnswerHeader();
        if (printableBusinessObject instanceof ProposalPerson) {
            ProposalPerson person = (ProposalPerson) printableBusinessObject;
            answerHeader.setModuleKey(person.getDevelopmentProposal().getProposalNumber());
        } else {
            if (moduleQuestionnaireBean != null) {
                answerHeader.setModuleKey(moduleQuestionnaireBean.getModuleItemKey());
            } else {
                answerHeader.setModuleKey(null);
            }
        }
        if (moduleQuestionnaireBean != null) {
            answerHeader.setSubModuleKey(moduleQuestionnaireBean.getModuleSubItemKey());
        } else {
            answerHeader.setSubModuleKey("0");
        }
    }

    /**
     * 
     * This method is to set the Module Usage info to Questionnaire XMLBeans object
     * @param moduleQuestionnaireBean
     * @param questionnaireType
     */
    private void setModuleUsage(ModuleQuestionnaireBean moduleQuestionnaireBean, Questionnaire questionnaireType) {
        ModuleUsageType moduleUsage = questionnaireType.addNewModuleUsage();
        ModuleInfoType moduleInfo = moduleUsage.addNewModuleInfo();
        String moduleCode = moduleQuestionnaireBean.getModuleItemCode();
        CoeusModule moduleData = getQuestionnaireCouesModule(moduleCode);
        String moduleSubItemKey = moduleQuestionnaireBean.getModuleSubItemKey();
        // a bug should be the modulesubitemcode NOT modulesubitemkey
       // CoeusSubModule subModuleData1 = getQuestionnaireCoeusSubModule(moduleCode,moduleSubItemKey);
        CoeusSubModule subModuleData = getQuestionnaireCoeusSubModule(moduleCode,moduleQuestionnaireBean.getModuleSubItemCode());
        if(moduleCode!=null){
            moduleInfo.setModuleCode(Integer.parseInt(moduleCode));
        }
        if(moduleSubItemKey!=null){
            moduleInfo.setSubModuleCode(Integer.parseInt(moduleSubItemKey));
        }
        if(moduleData!=null){
            moduleInfo.setModuleDesc(moduleData.getDescription());
        }
        if(subModuleData!=null){
            moduleInfo.setSubModuleDesc(subModuleData.getDescription());
        }
        moduleUsage.setModuleInfoArray(0, moduleInfo);
        
    }

    /**
     * 
     * This method is to set the Module subModule info to Questionnaire XMLBeans object
     * @param questionnaire
     * @param questionnaireType
     */
    private void setModuleUsageList(org.kuali.coeus.common.questionnaire.framework.core.Questionnaire questionnaire,Questionnaire questionnaireType){
        ModuleUsageType moduleUsage = questionnaireType.addNewModuleUsage();
        
        List moduleInfoTypeList =new ArrayList<ModuleInfoType>();
        List <QuestionnaireUsage> questionnaireUsageList = questionnaire.getQuestionnaireUsages();
        for (QuestionnaireUsage usage : questionnaireUsageList) {
            ModuleInfoType moduleInfoType= ModuleInfoType.Factory.newInstance();
            String moduleCode = usage.getModuleItemCode();
            CoeusModule moduleData = getQuestionnaireCouesModule(moduleCode);//txnBean.getModuleData(true); 
            String moduleSubItemCode = usage.getModuleSubItemCode();
            CoeusSubModule subModuleData = getQuestionnaireCoeusSubModule(moduleCode,moduleSubItemCode);
            moduleInfoType.setModuleCode(Integer.parseInt(moduleCode));
            moduleInfoType.setSubModuleCode(Integer.parseInt(moduleSubItemCode));
            moduleInfoType.setModuleDesc(moduleData.getDescription());
            if(subModuleData!=null){
                moduleInfoType.setSubModuleDesc(subModuleData.getDescription());
            }
            moduleInfoTypeList.add(moduleInfoType);
        }
        moduleUsage.setModuleInfoArray((ModuleInfoType[]) moduleInfoTypeList.toArray(new ModuleInfoType[0]));
    }
    

    @SuppressWarnings("unchecked")
    private CoeusSubModule getQuestionnaireCoeusSubModule(String moduleItemCode, String moduleSubItemKey) {
        Map param = new HashMap();
        param.put(MODULE_CODE, moduleItemCode);
        param.put(SUB_MODULE_CODE, moduleSubItemKey);
        return (CoeusSubModule)businessObjectService.findByPrimaryKey(CoeusSubModule.class, param);
    }

    private CoeusModule getQuestionnaireCouesModule(String moduleCode) {
        return businessObjectService.findBySinglePrimaryKey(CoeusModule.class,moduleCode);
    }

    /**
     * 
     * This method is to set the user option data to Questionnaire xmlbean object. User option determines how answers should render.
     * @param params
     * @param questionnaireType
     */
    private void setUserOption(Map<String,Object> params, Questionnaire questionnaireType) {
        UserOptions userOptions = questionnaireType.addNewUserOption();
        UserOptionsInfoType userOptionsInfo = userOptions.addNewUserOptionsInfo();
        Boolean printAnswersFlag = (Boolean)params.get("PRINT_ANSWERS_OPTION");
        Boolean printAnsweredOnlyFlag = (Boolean)params.get("PRINT_ONLY_ANSWERED_OPTION");
        Boolean printAllFlag = (Boolean)params.get("PRINT_ALL_OPTION");
        userOptionsInfo.setPrintAnsweredQuestionsOnly("No");
        userOptionsInfo.setPrintAnswers("Yes");
        if(printAnswersFlag!=null && !printAnswersFlag) {
            userOptionsInfo.setPrintAnswers("No");
        } else if(printAllFlag!=null && printAllFlag) {
            userOptionsInfo.setPrintAnsweredQuestionsOnly("No");
        } else if(printAnsweredOnlyFlag!=null && printAnsweredOnlyFlag) {
            userOptionsInfo.setPrintAnsweredQuestionsOnly("Yes");
        }
        userOptions.setUserOptionsInfo(userOptionsInfo);

    }

    /**
     * 
     * This method is to fetch the AnswerHeader data from the document
     */
    private ModuleQuestionnaireBean getQuestionnaireAnswerHeaderBean(
            KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> params) {
        String moduleItemCode = null;
        String moduleSubItemCode = "0";
        String moduleItemKey = null;
        String moduleSubItemKey = null;
        if(printableBusinessObject instanceof Protocol){
            Protocol protocol = (Protocol)printableBusinessObject;
            moduleItemCode = CoeusModule.IRB_MODULE_CODE;
            moduleSubItemCode = getProtocolSubItemCode(protocol);
            if (params.get(PROTOCOL_NUMBER) != null && params.get(SUBMISSION_NUMBER) != null) {
                moduleItemKey = (String)params.get(PROTOCOL_NUMBER);
                moduleSubItemKey = (String)params.get(SUBMISSION_NUMBER);
                moduleSubItemCode = CoeusSubModule.PROTOCOL_SUBMISSION;
            } else {
                moduleItemKey = protocol.getProtocolNumber();
                moduleSubItemKey = protocol.getSequenceNumber().toString();
                if (params.get(MODULE_SUB_ITEM_CODE) != null) {
                    // for amendquestionnaire
                    moduleSubItemCode = (String)params.get(MODULE_SUB_ITEM_CODE);
                }
            }
        } else if(printableBusinessObject instanceof IacucProtocol){
            IacucProtocol protocol = (IacucProtocol)printableBusinessObject;
            moduleItemCode = CoeusModule.IACUC_PROTOCOL_MODULE_CODE;
            moduleSubItemCode = getIacucProtocolSubItemCode(protocol);
            if (params.get(PROTOCOL_NUMBER) != null && params.get(SUBMISSION_NUMBER) != null) {
                moduleItemKey = (String)params.get(PROTOCOL_NUMBER);
                moduleSubItemKey = (String)params.get(SUBMISSION_NUMBER);
                moduleSubItemCode = CoeusSubModule.PROTOCOL_SUBMISSION;
            } else {
                moduleItemKey = protocol.getProtocolNumber();
                moduleSubItemKey = protocol.getSequenceNumber().toString();
                if (params.get(MODULE_SUB_ITEM_CODE) != null) {
                    // for amendquestionnaire
                    moduleSubItemCode = (String)params.get(MODULE_SUB_ITEM_CODE);
                }
            }
        } else if(printableBusinessObject instanceof DevelopmentProposal){
            DevelopmentProposal developmentProposal = (DevelopmentProposal)printableBusinessObject;
            moduleItemCode = CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE;
            moduleItemKey = developmentProposal.getProposalNumber();
            moduleSubItemCode = (String) params.get(COEUS_MODULE_SUB_ITEM_CODE);
            moduleSubItemKey = "0";
        } else if (printableBusinessObject instanceof ProposalPerson) {
            ProposalPerson person = (ProposalPerson) printableBusinessObject;
            moduleItemCode = CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE;
            moduleItemKey = person.getUniqueId();
            moduleSubItemCode = CoeusSubModule.PROPOSAL_PERSON_CERTIFICATION;
            moduleSubItemKey = "0";
        } else if (printableBusinessObject instanceof CoiDisclosure) {
            CoiDisclosure disclosure = (CoiDisclosure) printableBusinessObject;
            moduleItemCode = CoeusModule.COI_DISCLOSURE_MODULE_CODE;
            moduleItemKey = disclosure.getCoiDisclosureNumber();
            moduleSubItemCode = (String) params.get(COEUS_MODULE_SUB_ITEM_CODE);
        }
        return getQuestionnaireAnswerService().getModuleSpecificBean(moduleItemCode,moduleItemKey,moduleSubItemCode,moduleSubItemKey, false);
                
    }

    private String getProtocolSubItemCode(Protocol protocol) {
        // For now check renewal/amendment.  will add 'Protocol Submission' when it is cleared
            String subModuleCode = "0";
            if (protocol.isAmendment() || protocol.isRenewal()) {
                subModuleCode = "1";
            }
            return subModuleCode;
        }
    
    private String getIacucProtocolSubItemCode(IacucProtocol protocol) {
        // For now check renewal/amendment.  will add 'Protocol Submission' when it is cleared
            String subModuleCode = "0";
            if (protocol.isAmendment() || protocol.isRenewal()) {
                subModuleCode = "1";
            }
            return subModuleCode;
        }

    /**
     * 
     * This method is used when Print job call comes from Questionnaire Maintenance Document. If It doesn't find any approved questionnaire document with
     * the documentNumber, it fetches the xml data, deserializing it to Questionnaire BO.
     * @param documentNumber
     * @return
     * @throws PrintingException 
     */
    @SuppressWarnings("unchecked")
    private org.kuali.coeus.common.questionnaire.framework.core.Questionnaire findQuestionnaireObject(String documentNumber) throws PrintingException {
        Map qMap = new HashMap();
        qMap.put(DOCUMENT_NUMBER, documentNumber);
        List<org.kuali.coeus.common.questionnaire.framework.core.Questionnaire> questionnaires = (List)businessObjectService.findMatching(org.kuali.coeus.common.questionnaire.framework.core.Questionnaire.class, qMap);
        if(questionnaires.isEmpty()){
            return findUnapprovedQuestionnaire(documentNumber);
        }
        return questionnaires.get(0);
    }

    /**
     * 
     * This method to fetch the xml content and deserialize it to Questionnaire BO
     * @param documentNumber
     * @return
     * @throws PrintingException 
     */
    private org.kuali.coeus.common.questionnaire.framework.core.Questionnaire findUnapprovedQuestionnaire(String documentNumber) throws PrintingException {
        MaintenanceDocumentBase questionnaireDocument;
        org.kuali.coeus.common.questionnaire.framework.core.Questionnaire questionnaire = null;
        try {
            questionnaireDocument = (MaintenanceDocumentBase)documentService.getByDocumentHeaderId(documentNumber);
            if(questionnaireDocument!=null){
                String content = routeHeaderService.getContent(
                        questionnaireDocument.getDocumentHeader().getWorkflowDocument().getDocumentId()).getDocumentContent();
                questionnaire = (org.kuali.coeus.common.questionnaire.framework.core.Questionnaire)getBusinessObjectFromXML(content,KualiDocumentXmlMaterializer.class.getName());
           }            
        }
        catch (WorkflowException e) {
            LOG.error("Problem in deserializing xmldata to Questionnaire",e);
            throw new PrintingException("Problem in deserializing xmldata to Questionnaire", e);
        }
        return questionnaire;
    }
    /**
     * Retrieves substring of document contents from maintainable tag name. Then use xml service to translate xml into a business
     * object.
     */
    private PersistableBusinessObject getBusinessObjectFromXML(String xmlDocumentContents, String objectTagName) {
        String beginTag = "<document class=\"" + MaintenanceDocumentBase.class.getName() + "\">";
        String endTag = "</document>";
        String objXml = StringUtils.substringBetween(xmlDocumentContents, beginTag, endTag);
        objXml = beginTag + objXml + endTag;

        MaintenanceDocumentBase maintenanceDocument = (MaintenanceDocumentBase)xmlObjectSerializerService.fromXml(objXml);
        PersistableBusinessObject businessObject = (PersistableBusinessObject)maintenanceDocument.getDocumentBusinessObject();
        return businessObject;
    }

    private void allocateQuestionHierarchy(QuestionsType questionsType,QuestionInfoType parentQuestionInfo,boolean questionnaireCompletionFlag,
            KcPersistableBusinessObjectBase printableBusinessObject,List<AnswerHeader> answerHeaders){
        
        Long questionId;
        int questionNumber,parentQuestionNumber;
        int tempParentQuestionNumber;
        boolean answeredFlag = true;                      
        try {
            for (QuestionnaireQuestion questionnaireQuestion : sortedQuestionnaireQuestions) {                 
                answeredFlag = true;                
                if (questionnaireQuestion.getConditionValue() != null) {
                    for (AnswerHeader ansHeader : answerHeaders) {                       
                        if (questionnaireQuestion.getQuestionnaireId().equals(ansHeader.getQuestionnaireId())) {
                            for (Answer answer : ansHeader.getAnswers()) {
                                if (answer.getQuestionnaireQuestion().getId().equals(
                                        questionnaireQuestion.getId())
                                        && answer.getQuestionNumber().equals(questionnaireQuestion.getQuestionNumber())
                                        && answer.getQuestionId().equals(questionnaireQuestion.getQuestionId())) {
                              
                                    if (answer.getParentAnswers() != null && answer.getParentAnswers().get(0).getAnswer() != null) {
                                        if (answer.getParentAnswers().get(0).getQuestion().getQuestionTypeId() == QUESTION_TYPE_INT) {
                                            if (answer.getParentAnswers().get(0).getAnswer().equals(
                                                    questionnaireQuestion.getConditionValue())) {
                                                answeredFlag = false;
                                            }
                                        } else if (!answer.getParentAnswers().get(0).getAnswer().equals(
                                                            questionnaireQuestion.getConditionValue())) {
                                            answeredFlag = false;
                                        }                                 
                                    }
                                }
                            }
                        }                         
                    }
                }                    
                               
                if (answeredFlag) {
                 questionId = questionnaireQuestion.getId();
                 questionNumber = questionnaireQuestion.getQuestionNumber().intValue();
                 parentQuestionNumber = questionnaireQuestion.getParentQuestionNumber().intValue();
                 if(questionsType!=null)    {tempParentQuestionNumber=0;}
                 else   {tempParentQuestionNumber=(int) parentQuestionInfo.getQuestionNumber();}
                 if(questionnaireQuestion.isAllow()){
                     if(parentQuestionNumber ==tempParentQuestionNumber){
                         QuestionInfoType questionInfo= QuestionInfoType.Factory.newInstance();
                         questionInfo.setQuestionNumber(questionNumber);
                         if(tempParentQuestionNumber==0){
                              questionInfo=questionsType.addNewQuestionInfo();
                         }else{
                              questionInfo = parentQuestionInfo.addNewQuestionInfo();
            }
                         setAnswerInfoDetails(questionnaireQuestion,questionInfo,questionnaireCompletionFlag,printableBusinessObject,questionId,
                                        questionNumber, answerHeaders);

                         questionnaireQuestion.setAllow(false);
                     }
        }
                }
                }
            }
        catch (PrintingException e) {
            LOG.error("Problem in deserializing xmldata to Questionnaire",e);
        }
        
        QuestionInfoType[] childQuestionsList;
        QuestionInfoType questionInfoListData;
        if(questionsType!=null)
            childQuestionsList=questionsType.getQuestionInfoArray();
        else
            childQuestionsList = parentQuestionInfo.getQuestionInfoArray();
             
        if(childQuestionsList != null&& childQuestionsList.length>0){
            for(QuestionInfoType questionInfoType :childQuestionsList){
                if(questionsType!=null)
                    questionInfoListData = questionInfoType; 
                else
                    questionInfoListData = questionInfoType; 
                allocateQuestionHierarchy(null,questionInfoListData,
                        questionnaireCompletionFlag, printableBusinessObject, answerHeaders);
            }
        }
        }
        
    private void setAnswerInfoDetails(QuestionnaireQuestion questionnaireQuestion,QuestionInfoType questionInfo,
            boolean questionnaireCompletionFlag,KcPersistableBusinessObjectBase printableBusinessObject,Long questionId,
            int questionNumber, List<AnswerHeader> answerHeaders)throws PrintingException {
       
        
        boolean isAnswerPresent = false;
        boolean answeredFlag = true;                
        if (questionnaireQuestion.getConditionValue() != null) {                    
            for (AnswerHeader ansHeader : answerHeaders) {                        
                if (questionnaireQuestion.getQuestionnaireId().equals(ansHeader.getQuestionnaireId())) {
                    for (Answer answer : ansHeader.getAnswers()) {
                        if (answer.getQuestionnaireQuestion().getId().equals(
                                        questionnaireQuestion.getId())
                                        && answer.getQuestionNumber().equals(questionnaireQuestion.getQuestionNumber())
                                        && answer.getQuestionId().equals(questionnaireQuestion.getQuestionId())) {
                            if (answer.getParentAnswers() != null && answer.getParentAnswers().get(0).getAnswer() != null) {
                                if (answer.getParentAnswers().get(0).getQuestion().getQuestionTypeId() == QUESTION_TYPE_INT) {
                                    if (answer.getParentAnswers().get(0).getAnswer().equals(
                                                    questionnaireQuestion.getConditionValue())) {
                                        answeredFlag = false; 
                                    }
                                } else if (!answer.getParentAnswers().get(0).getAnswer().equals(
                                                questionnaireQuestion.getConditionValue())) {
                                    answeredFlag = false; 
                                } 
                            }
                        }
                    }
                }
            }
        }                
        if (answeredFlag) {
                if (questionId != null) {
                    questionInfo.setQuestionId(questionId.intValue());
                }
                questionInfo.setQuestionNumber(questionNumber);
                if (questionnaireQuestion.getQuestion() == null) {
                    questionnaireQuestion.refreshReferenceObject(QUESTION);
                }
                if (questionnaireQuestion.getQuestion() != null) {
                    questionInfo.setQuestion(questionnaireQuestion.getQuestion().getQuestion());
                    if(!questionnaireQuestion.getParentQuestionNumber().equals(0)){
                        questionInfo.setParentQuestionNumber(questionnaireQuestion.getParentQuestionNumber());
                    }
                }
                if (answerHeaders != null && answerHeaders.size() > 0) {
                    boolean isAnswerNamePresent=false;
                    int selectedAnswer = 0;
                    for (AnswerHeader answerHeader : answerHeaders) {
                        String answerName="";
                        String answerPerson="";
                        String answerDescription = null;
                        if (questionnaireQuestion.getQuestionnaireId().equals(answerHeader.getQuestionnaireId())) {
                            List<Answer> answers = answerHeader.getAnswers();
                            for (Answer answer : answers) {
                                if (answer.getQuestionnaireQuestion().getId().equals(
                                        questionnaireQuestion.getId())
                                        && answer.getQuestionNumber().equals(questionnaireQuestion.getQuestionNumber())
                                        && answer.getQuestionId().equals(questionnaireQuestion.getQuestionId())) {
                                    boolean updateQuestionDescription = printableBusinessObject instanceof ProposalPerson;
                                    if (answer.getAnswer() != null) {
                                        isAnswerPresent = true;
                                        if(isAnswerNamePresent==true)
                                            answerName+=", ";
                                        answerName += answer.getAnswer().trim();
                                        if (answerName != null) {
                                            if (answerName.trim().equalsIgnoreCase("Y")) {
                                                answerDescription = "Yes";
                                                if (updateQuestionDescription) {
                                                  questionInfo.setQuestion(questionnaireQuestion.getQuestion().getAffirmativeStatementConversion());
                                                }
                                            } else if (answerName.trim().equalsIgnoreCase("N")) {
                                                answerDescription = "No";
                                                if (updateQuestionDescription) {
                                                    questionInfo.setQuestion(questionnaireQuestion.getQuestion().getNegativeStatementConversion());
                                                }
                                            } else if (answerName.trim().equalsIgnoreCase("X")) {
                                                answerDescription = "None";
                                            } else {
                                                answerDescription = answerName;
                                            }
                                        }
                                        selectedAnswer = answer.getAnswerNumber();
                                        isAnswerNamePresent=true;
                                    }
                                }

                            }
                        }
                
                            AnswerInfoType answerInfo = questionInfo.addNewAnswerInfo();
                            answerInfo.setAnswerNumber(selectedAnswer);
                    if((questionnaireQuestion.getQuestion().getQuestionTypeId().equals(6)) && (questionnaireQuestion.getQuestion().getLookupClass().equals(KcPerson.class.getName()))) {
                        if((questionnaireQuestion.getQuestion().getLookupReturn().equals(PERSON_ID))){
                            if(answerDescription!=null && !answerDescription.equals("")){
                        String personAnswers[]=answerDescription.split(",");
                        answerDescription = "";
                       for (int personAnswer = 0; personAnswer < personAnswers.length; personAnswer++) {
                           KcPerson kcPerson=kcPersonService.getKcPersonByPersonId(personAnswers[personAnswer].trim());
                           if(kcPerson != null)
                               answerDescription = answerDescription + kcPerson.getFullName()+ ",";
                           }
                       answerDescription = answerDescription.substring(0, answerDescription.lastIndexOf(",")-1);
                        }
                            
                        }}
                            answerInfo.setAnswer(answerDescription);
                        }
                    }
        }
                }
    
    /**
     * 
     * This method for setting the question and answers corresponding to the questionnaire.
     * @param questionnaire
     * @param moduleQuestionnaireBean
     * @param questionnaireType
     * @param questionnaireCompletionFlag
     * @param printableBusinessObject
     * @throws PrintingException
     */
    private void setQuestionInfoData(org.kuali.coeus.common.questionnaire.framework.core.Questionnaire questionnaire,
            ModuleQuestionnaireBean moduleQuestionnaireBean, Questionnaire questionnaireType, boolean questionnaireCompletionFlag,
            KcPersistableBusinessObjectBase printableBusinessObject) throws PrintingException {
        List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        org.kuali.coeus.common.questionnaire.framework.core.Questionnaire answeredQuestionnaire = null;
        if (moduleQuestionnaireBean != null) {
            answerHeaders = questionnaireAnswerService.getQuestionnaireAnswer(moduleQuestionnaireBean);
            if (!answerHeaders.isEmpty()) {
                answeredQuestionnaire = answerHeaders.get(0).getQuestionnaire();
                // This is another weird behavior. some have empty questionnairequestions, and require this refresh.
                answeredQuestionnaire.refreshReferenceObject(QUESTIONNAIRE_QUESTIONS);
            }

        }
        org.kuali.coeus.common.questionnaire.framework.core.Questionnaire toSortQuestionnaire = null;
        for (AnswerHeader header : answerHeaders) {
            if (header.getQuestionnaire().getQuestionnaireSeqId().equals(questionnaire.getQuestionnaireSeqId())) {
                toSortQuestionnaire = header.getQuestionnaire();
            }
        }
        
        if (toSortQuestionnaire == null) {
            toSortQuestionnaire = questionnaire;
        }
        
        sortedQuestionnaireQuestions = getSortedQuestionnaireQuestions(toSortQuestionnaire);
        if (sortedQuestionnaireQuestions != null && sortedQuestionnaireQuestions.size() > 0) {
            
            for (QuestionnaireQuestion questionnaireQuestion : sortedQuestionnaireQuestions) {
                questionnaireQuestion.setAllow(true); 
            }
            int parentQuestionNumbercheck=0;
            
            QuestionsType questionsType = questionnaireType.addNewQuestions();
            QuestionInfoType rootQuestionInfo =QuestionInfoType.Factory.newInstance();
            try
            {
                rootQuestionInfo.setQuestionNumber(parentQuestionNumbercheck);
                if(sortedQuestionnaireQuestions.size()>=1)
                {
                    allocateQuestionHierarchy(questionsType,rootQuestionInfo,questionnaireCompletionFlag,printableBusinessObject,answerHeaders);
                }
             }
             catch(Exception e){
                 LOG.error("Problem in deserializing xmldata to Questionnaire",e);
            }
        }
    }

    private List<QuestionnaireQuestion> getSortedQuestionnaireQuestions(org.kuali.coeus.common.questionnaire.framework.core.Questionnaire questionnaire) {
        return getSortedVector(questionnaire.getQuestionnaireQuestions());
    }
    
    public static List<QuestionnaireQuestion> getSortedVector(List<QuestionnaireQuestion> questions) {
        return sort(questions);
    }
    
    private static List<QuestionnaireQuestion> sort(List<QuestionnaireQuestion> questions) {
        List<QuestionnaireQuestion> tempQuestions = questions;
            
        List<QuestionnaireQuestion> elements = new ArrayList<QuestionnaireQuestion>();
        QuestionnaireQuestion questionaireQuestion = null;
        for (int i = 0; i < tempQuestions.size(); i++) {
            questionaireQuestion = (QuestionnaireQuestion)questions.get(i);
            if ( !elements.contains(questionaireQuestion) ) {
                findChildrenAndUpdate(elements, tempQuestions, questionaireQuestion);
                i--;
            }
        }
        return elements;
    }
    private static void findChildrenAndUpdate(List<QuestionnaireQuestion> elements, List<QuestionnaireQuestion> questions,QuestionnaireQuestion parent) {
        elements.add(parent);
        questions.remove(parent);
        List<QuestionnaireQuestion> children = findChildren(questions, parent.getQuestionNumber());
        if(children != null && children.size() > 0){
            for(int index=0;index<children.size();index++){
                QuestionnaireQuestion childBean = children.get(index);
                findChildrenAndUpdate(elements, questions, childBean);
            }
        }
    }
    private static List<QuestionnaireQuestion> findChildren(List<QuestionnaireQuestion> questionnaireQuestions, Integer parent) {
        List<QuestionnaireQuestion> list = new ArrayList<QuestionnaireQuestion>();
        if(questionnaireQuestions != null && questionnaireQuestions.size() > 0){
            for(int index=0;index<questionnaireQuestions.size();index++){
                QuestionnaireQuestion bean = questionnaireQuestions.get(index);
                if(bean.getParentQuestionNumber() != null && parent != null){
                    if (bean.getParentQuestionNumber().intValue() == parent.intValue()) {
                        list.add(bean);
                    }
                }
            }
        }
        
        QuestionnaireQuestion firstChild = null;
        QuestionnaireQuestion secondChild = null;
        QuestionnaireQuestion temp = null;
        // To the the questions according to the questionSequenceNumber
        for (int i = 0; i < list.size(); i++) {
            for (int j = 1; j <= (list.size() - 1); j++) {
                firstChild = list.get(j - 1);
                secondChild = list.get(j);
                if (secondChild.getQuestionSeqNumber() < firstChild
                        .getQuestionSeqNumber() ) {
                    temp = firstChild;
                    list.set((j - 1), secondChild);
                    list.set(j, temp);
                }
            }
        }
        return list;
    }

    /**
     * Gets the questionnaireService attribute. 
     * @return Returns the questionnaireService.
     */
    public QuestionnaireService getQuestionnaireService() {
        return questionnaireService;
    }

    /**
     * Sets the questionnaireService attribute value.
     * @param questionnaireService The questionnaireService to set.
     */
    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    /**
     * Gets the questionnaireAnswerService attribute. 
     * @return Returns the questionnaireAnswerService.
     */
    public QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return questionnaireAnswerService;
    }

    /**
     * Sets the questionnaireAnswerService attribute value.
     * @param questionnaireAnswerService The questionnaireAnswerService to set.
     */
    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }

    /**
     * Gets the documentService attribute. 
     * @return Returns the documentService.
     */
    public DocumentService getDocumentService() {
        return documentService;
    }

    /**
     * Sets the documentService attribute value.
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    /**
     * Gets the kcPersonService attribute value.
     * @return Returns the kcPersonService.
     */
    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }
    /**
     * Sets the kcPersonService attribute value.
     * @param kcPersonService The kcPersonService to set.
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public RouteHeaderService getRouteHeaderService() {
        return routeHeaderService;
    }

    public void setRouteHeaderService(RouteHeaderService routeHeaderService) {
        this.routeHeaderService = routeHeaderService;
    }

    public XmlObjectSerializerService getXmlObjectSerializerService() {
        return xmlObjectSerializerService;
    }

    public void setXmlObjectSerializerService(XmlObjectSerializerService xmlObjectSerializerService) {
        this.xmlObjectSerializerService = xmlObjectSerializerService;
    }
}
