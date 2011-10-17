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
package org.kuali.kra.questionnaire.print;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import noNamespace.AnswerHeaderType;
import noNamespace.AnswerInfoType;
import noNamespace.ModuleInfoType;
import noNamespace.ModuleUsageType;
import noNamespace.Person;
import noNamespace.ProposalInfoType;
import noNamespace.ProtocolInfoType;
import noNamespace.QuestionInfoType;
import noNamespace.QuestionnaireDocument;
import noNamespace.QuestionsType;
import noNamespace.UserOptions;
import noNamespace.UserOptionsInfoType;
import noNamespace.QuestionnaireDocument.Questionnaire;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.maintenance.KraMaintenanceDocument;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.QuestionnaireService;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.questionnaire.question.QuestionService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.workflow.KualiDocumentXmlMaterializer;

/**
 * This class generates XML that conforms with the XSD related to Budget total
 * Report. The data for XML is derived from {@link ResearchDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 */
public class QuestionnaireXmlStream implements XmlStream {

    private DateTimeService dateTimeService;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private QuestionnaireService questionnaireService;
    private QuestionService questionService;
    private QuestionnaireAnswerService questionnaireAnswerService;
    private static final Log LOG = LogFactory.getLog(QuestionnaireXmlStream.class);

    
    /**
     * This method generates XML committee report. It uses data passed in
     * {@link ResearchDocumentBase} for populating the XML nodes. The XMl once
     * generated is returned as {@link XmlObject}
     * 
     * @param printableBusinessObject
     *            using which XML is generated
     * @param reportParameters
     *            parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     */
    public Map<String, XmlObject> generateXmlStream(KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
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
            LOG.error(e);
        }
        return xmlObjectList;
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
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
     * @param document
     * @param params
     * @return
     * @throws PrintingException
     */
    @SuppressWarnings("unchecked")
    private QuestionnaireDocument getQuestionnaireData(
        KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> params) throws PrintingException {
        QuestionnaireDocument questionnaireDocument = QuestionnaireDocument.Factory.newInstance();
        Questionnaire questionnaireType = questionnaireDocument.addNewQuestionnaire();
        
        String documentNumber = (String)params.get("documentNumber");
        Integer questionnaireId = (Integer)params.get("questionnaireId");
        org.kuali.kra.questionnaire.Questionnaire questionnaire;
        if(questionnaireId!=null){ 
            Map<String,Integer> qParam = new HashMap<String,Integer>();
            qParam.put("questionnaireId", questionnaireId);
            List<org.kuali.kra.questionnaire.Questionnaire> questionnaires = 
                (List)businessObjectService.findMatchingOrderBy(
                        org.kuali.kra.questionnaire.Questionnaire.class, qParam, "questionnaireRefId", false);
            questionnaire = questionnaires.get(0);
            // not sure why need this.  If it is not refreshed, some may get empty Questions
            questionnaire.refreshReferenceObject("questionnaireQuestions");
        }else{
            questionnaire = findQuestionnaireObject(documentNumber);
        }
        
        Boolean questionnaireCompletionFlag = (Boolean)params.get("QUESTIONNAIRE_COMPLETION_FLAG");
        questionnaireCompletionFlag = questionnaireCompletionFlag==null?Boolean.FALSE:questionnaireCompletionFlag;
        ModuleQuestionnaireBean moduleQuestionnaireBean = getQuestionnaireAnswerHeaderBean(printableBusinessObject, params);
        if(questionnaire != null) {
            Integer questId = questionnaire.getQuestionnaireIdAsInteger();
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
            }else{
                ModuleQuestionnaireBean moduleSubQuestionnaireBean = getQuestionnaireAnswerHeaderBean(printableBusinessObject, params);
                moduleSubQuestionnaireBean.setModuleItemCode(questionnaire.getQuestionnaireUsage(0).getModuleItemCode());
                moduleSubQuestionnaireBean.setModuleSubItemCode(questionnaire.getQuestionnaireUsage(0).getModuleSubItemCode());
                setModuleUsage(moduleSubQuestionnaireBean,questionnaireType);
            }
            String moduleCode = moduleQuestionnaireBean.getModuleItemCode();
            String moduleSubcode = moduleQuestionnaireBean.getModuleSubItemCode();
            if (moduleCode != null) {
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
     * @param protocolDocument
     * @param questionnaireType
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
     * @param document
     * @param questionnaireType
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
    private void setAnswerInfo(KraPersistableBusinessObjectBase printableBusinessObject, ModuleQuestionnaireBean moduleQuestionnaireBean, 
            Questionnaire questionnaireType) {
        AnswerHeaderType answerHeader = questionnaireType.addNewAnswerHeader();
        if (printableBusinessObject instanceof ProposalPerson) {
            ProposalPerson person = (ProposalPerson) printableBusinessObject;
            answerHeader.setModuleKey(person.getDevelopmentProposal().getProposalNumber());
        } else {
            answerHeader.setModuleKey(moduleQuestionnaireBean.getModuleItemKey());
        }
        answerHeader.setSubModuleKey(moduleQuestionnaireBean.getModuleSubItemKey());
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
        CoeusModule moduleData = getQuestionnaireCouesModule(moduleCode);//txnBean.getModuleData(true);
        String moduleSubItemKey = moduleQuestionnaireBean.getModuleSubItemKey();
        // a bug should be the modulesubitemcode NOT modulesubitemkey
       // CoeusSubModule subModuleData1 = getQuestionnaireCoeusSubModule(moduleCode,moduleSubItemKey);
        CoeusSubModule subModuleData = getQuestionnaireCoeusSubModule(moduleCode,moduleQuestionnaireBean.getModuleSubItemCode());
        
        moduleInfo.setModuleCode(Integer.parseInt(moduleCode));
        if(moduleSubItemKey!=null){
            moduleInfo.setSubModuleCode(Integer.parseInt(moduleSubItemKey));
        }
        moduleInfo.setModuleDesc(moduleData.getDescription());
        if(subModuleData!=null){
            moduleInfo.setSubModuleDesc(subModuleData.getDescription());
        }
        moduleUsage.setModuleInfo(moduleInfo);
        
    }

    @SuppressWarnings("unchecked")
    private CoeusSubModule getQuestionnaireCoeusSubModule(String moduleItemCode, String moduleSubItemKey) {
        Map param = new HashMap();
        param.put("moduleCode", moduleItemCode);
        param.put("subModuleCode", moduleSubItemKey);
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
     * @param document
     * @return
     */
    private ModuleQuestionnaireBean getQuestionnaireAnswerHeaderBean(
            KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> params) {
        String moduleItemCode = null;
        String moduleSubItemCode = "0";
        String moduleItemKey = null;
        String moduleSubItemKey = null;
        if(printableBusinessObject instanceof Protocol){
            Protocol protocol = (Protocol)printableBusinessObject;
            moduleItemCode = CoeusModule.IRB_MODULE_CODE;
            moduleSubItemCode = getProtocolSubItemCode(protocol);
            if (params.get("protocolNumber") != null && params.get("submissionNumber") != null) {
                moduleItemKey = (String)params.get("protocolNumber");
                moduleSubItemKey = (String)params.get("submissionNumber");
                moduleSubItemCode = CoeusSubModule.PROTOCOL_SUBMISSION;
            } else {
                moduleItemKey = protocol.getProtocolNumber();
                moduleSubItemKey = protocol.getSequenceNumber().toString();
                if (params.get("moduleSubItemCode") != null) {
                    // for amendquestionnaire
                    moduleSubItemCode = (String)params.get("moduleSubItemCode");
                }
            }
        } else if(printableBusinessObject instanceof DevelopmentProposal){
            DevelopmentProposal developmentProposal = (DevelopmentProposal)printableBusinessObject;
            moduleItemCode = CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE;
            moduleItemKey = developmentProposal.getProposalNumber();
            moduleSubItemCode = (String) params.get("coeusModuleSubItemCode");
            moduleSubItemKey = "0";
        } else if (printableBusinessObject instanceof ProposalPerson) {
            ProposalPerson person = (ProposalPerson) printableBusinessObject;
            moduleItemCode = CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE;
            moduleItemKey = person.getUniqueId();
            moduleSubItemCode = CoeusSubModule.PROPOSAL_PERSON_CERTIFICATION;
            moduleSubItemKey = "0";
        }
        return new ModuleQuestionnaireBean(moduleItemCode,moduleItemKey,moduleSubItemCode,moduleSubItemKey, false);
                
    }

    private String getProtocolSubItemCode(Protocol protocol) {
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
    private org.kuali.kra.questionnaire.Questionnaire findQuestionnaireObject(String documentNumber) throws PrintingException {
        Map qMap = new HashMap();
        qMap.put("documentNumber", documentNumber);
        List<org.kuali.kra.questionnaire.Questionnaire> questionnaires = (List)businessObjectService.findMatching(org.kuali.kra.questionnaire.Questionnaire.class, qMap);
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
    private org.kuali.kra.questionnaire.Questionnaire findUnapprovedQuestionnaire(String documentNumber) throws PrintingException {
        MaintenanceDocumentBase questionnaireDocument;
        org.kuali.kra.questionnaire.Questionnaire questionnaire = null;
        try {
            questionnaireDocument = (MaintenanceDocumentBase)documentService.getByDocumentHeaderId(documentNumber);
            if(questionnaireDocument!=null){
                String content = KraServiceLocator.getService(RouteHeaderService.class).getContent(
                questionnaireDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId()).getDocumentContent();
                questionnaire = (org.kuali.kra.questionnaire.Questionnaire)getBusinessObjectFromXML(content,KualiDocumentXmlMaterializer.class.getName());
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
        String objXml = StringUtils.substringBetween(xmlDocumentContents, "<" + objectTagName + ">", "</"+ objectTagName +">");
        objXml = "<" + objectTagName + ">" + objXml + "</" + objectTagName + ">";
        KualiDocumentXmlMaterializer kualiDocumentXmlMaterializer = (KualiDocumentXmlMaterializer)KNSServiceLocator.getXmlObjectSerializerService().fromXml(objXml);
        
        KraMaintenanceDocument kraMaintenanceDocument = (KraMaintenanceDocument)kualiDocumentXmlMaterializer.getDocument();
        PersistableBusinessObject businessObject = (PersistableBusinessObject)kraMaintenanceDocument.getDocumentBusinessObject();
        return businessObject;
    }

    private void setQuestionInfoData(org.kuali.kra.questionnaire.Questionnaire questionnaire,
            ModuleQuestionnaireBean moduleQuestionnaireBean, Questionnaire questionnaireType, boolean questionnaireCompletionFlag,
            KraPersistableBusinessObjectBase printableBusinessObject) throws PrintingException {
        List<AnswerHeader> answerHeaders = null;
        org.kuali.kra.questionnaire.Questionnaire answeredQuestionnaire = null;
        if (moduleQuestionnaireBean != null) {
            answerHeaders = questionnaireAnswerService.getQuestionnaireAnswer(moduleQuestionnaireBean);
            if (!answerHeaders.isEmpty()) {
                answeredQuestionnaire = answerHeaders.get(0).getQuestionnaire();
                // This is another weird behavior. some have empty questionnairequestions, and require this refresh.
                answeredQuestionnaire.refreshReferenceObject("questionnaireQuestions");
            }

        }
        org.kuali.kra.questionnaire.Questionnaire toSortQuestionnaire = null;
        if (answerHeaders!=null && answerHeaders.size() > 0) {
            for (AnswerHeader header : answerHeaders) {
                if (header.getQuestionnaire().getQuestionnaireId().equals(questionnaire.getQuestionnaireId())) {
                    toSortQuestionnaire = header.getQuestionnaire();
                }
            }
        }
        
        if (toSortQuestionnaire == null) {
            toSortQuestionnaire = questionnaire;
        }
        
        List<QuestionnaireQuestion> sortedQuestionnaireQuestions = getSortedQuestionnaireQuestions(toSortQuestionnaire);
        if (sortedQuestionnaireQuestions != null && sortedQuestionnaireQuestions.size() > 0) {
            QuestionsType questionsType = questionnaireType.addNewQuestions();
            for (QuestionnaireQuestion questionnaireQuestion : sortedQuestionnaireQuestions) {
                Long questionId = questionnaireQuestion.getQuestionnaireQuestionsId();
                int questionNumber = questionnaireQuestion.getQuestionNumber().intValue();
                boolean isAnswerPresent = false;
                QuestionInfoType questionInfo = questionsType.addNewQuestionInfo();
                if (questionId != null) {
                    questionInfo.setQuestionId(questionId.intValue());
                }
                questionInfo.setQuestionNumber(questionNumber);
                if (questionnaireQuestion.getQuestion() == null) {
                    questionnaireQuestion.refreshReferenceObject("question");
                }
                if (questionnaireQuestion.getQuestion() != null) {
                    questionInfo.setQuestion(questionnaireQuestion.getQuestion().getQuestion());
                    if(!questionnaireQuestion.getParentQuestionNumber().equals(0)){
                        questionInfo.setParentQuestionNumber(questionnaireQuestion.getParentQuestionNumber());
                    }
                }
                if (answerHeaders != null && answerHeaders.size() > 0) {
                    for (AnswerHeader answerHeader : answerHeaders) {
                        int selectedAnswer = 0;
                        String answerDescription = null;
                        if (questionnaireQuestion.getQuestionnaireRefIdFk().equals(answerHeader.getQuestionnaireRefIdFk())) {
                            List<Answer> answers = answerHeader.getAnswers();
                            for (Answer answer : answers) {
                                if (answer.getQuestionnaireQuestion().getQuestionnaireQuestionsId().equals(
                                        questionnaireQuestion.getQuestionnaireQuestionsId())
                                        && answer.getQuestionNumber().equals(questionnaireQuestion.getQuestionNumber())
                                        && answer.getQuestionRefIdFk().equals(questionnaireQuestion.getQuestionRefIdFk())) {
                                    boolean updateQuestionDescription = printableBusinessObject instanceof ProposalPerson;
                                    if (answer.getAnswer() != null) {
                                        isAnswerPresent = true;
                                        String name = answer.getAnswer().trim();
                                        if (name != null) {
                                            if (name.trim().equalsIgnoreCase("Y")) {
                                                answerDescription = "Yes";
                                                if (updateQuestionDescription) {
                                                  questionInfo.setQuestion(questionnaireQuestion.getQuestion().getAffirmativeStatementConversion());
                                                }
                                            } else if (name.trim().equalsIgnoreCase("N")) {
                                                answerDescription = "No";
                                                if (updateQuestionDescription) {
                                                    questionInfo.setQuestion(questionnaireQuestion.getQuestion().getNegativeStatementConversion());
                                                }
                                            } else if (name.trim().equalsIgnoreCase("X")) {
                                                answerDescription = "None";
                                            } else {
                                                answerDescription = name;
                                            }
                                        }
                                        selectedAnswer = answer.getAnswerNumber();
                                        break;
                                    }
                                }

                            }
                        }
                        //we don't print answers on the proposal person printout
                        if ((isAnswerPresent || !questionnaireCompletionFlag) && !(printableBusinessObject instanceof ProposalPerson)) {
                        //if (isAnswerPresent || !questionnaireCompletionFlag) {
                            AnswerInfoType answerInfo = questionInfo.addNewAnswerInfo();
                            answerInfo.setAnswerNumber(selectedAnswer);
                            answerInfo.setAnswer(answerDescription);
                        }
                    }
                }
            }
        }
    }

    private List<QuestionnaireQuestion> getSortedQuestionnaireQuestions(org.kuali.kra.questionnaire.Questionnaire questionnaire) {
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
     * Gets the questionService attribute. 
     * @return Returns the questionService.
     */
    public QuestionService getQuestionService() {
        return questionService;
    }

    /**
     * Sets the questionService attribute value.
     * @param questionService The questionService to set.
     */
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
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
    
    
}
