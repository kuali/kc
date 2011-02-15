/*
 * Copyright 2005-2010 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.generator.impl;

import gov.grants.apply.forms.phs398TrainingBudgetV10.PHS398TrainingBudgetDocument;
import gov.grants.apply.forms.phs398TrainingBudgetV10.PHS398TrainingBudgetDocument.PHS398TrainingBudget;
import gov.grants.apply.forms.phs398TrainingSubawardBudgetV10.PHS398TrainingSubawardBudgetDocument;
import gov.grants.apply.forms.phs398TrainingSubawardBudgetV10.PHS398TrainingSubawardBudgetDocument.PHS398TrainingSubawardBudget;
import gov.grants.apply.forms.phs398TrainingSubawardBudgetV10.PHS398TrainingSubawardBudgetDocument.PHS398TrainingSubawardBudget.BudgetAttachments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.util.S2SConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class for generating the XML object for grants.gov RRSubAwardBudgetV1.1. Form is generated using XMLBean classes and is based on
 * RRSubAwardBudget schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PHS398TrainingSubAwardBudgetV1_0Generator extends S2SAdobeFormAttachmentBaseGenerator {


    private static final String PHS398_TRAINING_BUDGET_10_NAMESPACE_URI = "http://apply.grants.gov/forms/PHS398_TrainingBudget-V1.0";

    /**
     * 
     * This method is to get SubAward Budget details
     * 
     * @return rrSubawardBudgetDocument {@link XmlObject} of type RRSubawardBudgetDocument.
     */
    private PHS398TrainingSubawardBudgetDocument getPHS398TrainingSubawardBudget() throws S2SException{

        PHS398TrainingSubawardBudgetDocument phs398TrainingSubawardBudgetDocument = PHS398TrainingSubawardBudgetDocument.Factory.newInstance();
        PHS398TrainingSubawardBudget phs398TrainingSubawardBudget = PHS398TrainingSubawardBudget.Factory.newInstance();

        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwards> budgetSubAwardsList = getBudgetSubAwards(pdDoc,PHS398_TRAINING_BUDGET_10_NAMESPACE_URI,true);
        PHS398TrainingBudget[] budgetList = new PHS398TrainingBudget[budgetSubAwardsList.size()];
        phs398TrainingSubawardBudget.setFormVersion(S2SConstants.FORMVERSION_1_0);
        int attCount = 1;
        for (BudgetSubAwards budgetSubAwards : budgetSubAwardsList) {
            switch (attCount) {
                case 1:
                    phs398TrainingSubawardBudget.setATT1(prepareAttName(budgetSubAwards));
                    budgetList[0] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 2:
                    phs398TrainingSubawardBudget.setATT2(prepareAttName(budgetSubAwards));
                    budgetList[1] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 3:
                    phs398TrainingSubawardBudget.setATT3(prepareAttName(budgetSubAwards));
                    budgetList[2] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 4:
                    phs398TrainingSubawardBudget.setATT4(prepareAttName(budgetSubAwards));
                    budgetList[3] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 5:
                    phs398TrainingSubawardBudget.setATT5(prepareAttName(budgetSubAwards));
                    budgetList[4] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 6:
                    phs398TrainingSubawardBudget.setATT6(prepareAttName(budgetSubAwards));
                    budgetList[5] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 7:
                    phs398TrainingSubawardBudget.setATT7(prepareAttName(budgetSubAwards));
                    budgetList[6] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 8:
                    phs398TrainingSubawardBudget.setATT8(prepareAttName(budgetSubAwards));
                    budgetList[7] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 9:
                    phs398TrainingSubawardBudget.setATT9(prepareAttName(budgetSubAwards));
                    budgetList[8] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 10:
                    phs398TrainingSubawardBudget.setATT10(prepareAttName(budgetSubAwards));
                    budgetList[9] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
            }
            addSubAwdAttachments(budgetSubAwards);
            attCount++;
        }
        budgetAttachments.setPHS398TrainingBudgetArray(budgetList);
        phs398TrainingSubawardBudget.setBudgetAttachments(budgetAttachments);
        phs398TrainingSubawardBudgetDocument.setPHS398TrainingSubawardBudget(phs398TrainingSubawardBudget);
        return phs398TrainingSubawardBudgetDocument;
    }

    /**
     * 
     * This method is used to get PHS398TrainingBudget from BudgetSubAwards
     * 
     * @param budgetSubAwards(BudgetSubAwards) budget sub awards entry.
     * @return PHS398TrainingBudget corresponding to the BudgetSubAwards object.
     */
    private PHS398TrainingBudget getPHS398TrainingBudget(BudgetSubAwards budgetSubAwards) throws S2SException{
        PHS398TrainingBudget rrBudget = PHS398TrainingBudget.Factory.newInstance();
        PHS398TrainingBudgetDocument rrBudgetDocument = PHS398TrainingBudgetDocument.Factory.newInstance();
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc;
        try {
            subAwdFormsDoc = stringToDom(subAwdXML);
        }
        catch (S2SException e1) {
            return rrBudget;
        }
        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(PHS398_TRAINING_BUDGET_10_NAMESPACE_URI, "PHS398_TrainingBudget");
        Node subAwdNode = null;
        if (subAwdNodeList != null){
            if(subAwdNodeList.getLength() == 0) {
                return null;
            }
            subAwdNode = subAwdNodeList.item(0);
        }
        byte[] subAwdNodeBytes = null;
        InputStream bgtIS  = null;
        try {
            subAwdNodeBytes = docToBytes(nodeToDom(subAwdNode));
            bgtIS = new ByteArrayInputStream(subAwdNodeBytes);
            rrBudgetDocument = (PHS398TrainingBudgetDocument) XmlObject.Factory.parse(bgtIS);
            rrBudget = rrBudgetDocument.getPHS398TrainingBudget();
        }
        catch (S2SException e) {
            return rrBudget;
        }
        catch (XmlException e) {
            return rrBudget;
        }
        catch (IOException e) {
            return rrBudget;
        }finally{
            if(bgtIS!=null){
                try {bgtIS.close();}catch (IOException e) {} 
            }
        }
        return rrBudget;
    }

//    /**
//     * 
//     * This method is used to get BudgetSubAwrads from ProposalDevelopmentDocument
//     * 
//     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
//     * @return List<BudgetSubAwards> list of budget sub awards.
//     */
//
//    private List<BudgetSubAwards> getBudgetSubAwards(ProposalDevelopmentDocument proposalDevelopmentDocument) {
//        List<BudgetSubAwards> budgetSubAwardsList = new ArrayList<BudgetSubAwards>();
//        return budgetSubAwardsList;
//    }


    /**
     * This method creates {@link XmlObject} of type {@link RRSubawardBudgetDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) throws S2SException{
        pdDoc=proposalDevelopmentDocument;
        return getPHS398TrainingSubawardBudget();
    }

    /**
     * This method typecasts the given {@link XmlObject} to the required generator type and returns back the document of that
     * generator type.
     * 
     * @param xmlObject which needs to be converted to the document type of the required generator
     * @return {@link XmlObject} document of the required generator type
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
     */
    public XmlObject getFormObject(XmlObject xmlObject) {
        PHS398TrainingSubawardBudget phs398TrainingSubawardBudget = (PHS398TrainingSubawardBudget) xmlObject;
        PHS398TrainingSubawardBudgetDocument phs398TrainingSubawardBudgetDocument = PHS398TrainingSubawardBudgetDocument.Factory.newInstance();
        phs398TrainingSubawardBudgetDocument.setPHS398TrainingSubawardBudget(phs398TrainingSubawardBudget);
        return phs398TrainingSubawardBudgetDocument;
    }

}
