/*
 * Copyright 2005-2013 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.rrBudget13V13.RRBudget13Document.RRBudget13;
import gov.grants.apply.forms.rrBudget13V13.RRBudget13Document;
import gov.grants.apply.forms.rrSubawardBudget3013V13.RRSubawardBudget3013Document;
import gov.grants.apply.forms.rrSubawardBudget3013V13.RRSubawardBudget3013Document.RRSubawardBudget3013;
import gov.grants.apply.forms.rrSubawardBudget3013V13.RRSubawardBudget3013Document.RRSubawardBudget3013.BudgetAttachments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardsContract;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class for generating the XML object for grants.gov RRSubAwardBudgetV1.3. Form is generated using XMLBean classes and is based on
 * RRSubAwardBudget schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("RRSubAwardBudget30_1_3V1_3Generator")
public class RRSubAwardBudget30_1_3V1_3Generator extends S2SAdobeFormAttachmentBaseGenerator {

    
    private static final String RR_BUDGET30_13_NAMESPACE_URI = "http://apply.grants.gov/forms/RR_Budget_1_3-V1.3";
    private static final String RR_BUDGET30_13_LOCAL_NAME = "RR_Budget_1_3";

    @Value("http://apply.grants.gov/forms/RR_SubawardBudget30_1_3-V1.3")
    private String namespace;

    @Value("RR_SubawardBudget30_1_3")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/RR_SubawardBudget30_1_3-V1.3.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrSubawardBudget3013V13")
    private String packageName;

    @Value("180")
    private int sortIndex;

    /**
     * 
     * This method is to get SubAward Budget details
     * 
     * @return rrSubawardBudgetDocument {@link XmlObject} of type RRSubawardBudgetDocument.
     */
    private RRSubawardBudget3013Document getRRSubawardBudgetDocument() {

        RRSubawardBudget3013Document rrSubawardBudgetDocument = RRSubawardBudget3013Document.Factory.newInstance();
        RRSubawardBudget3013 rrSubawardBudget = RRSubawardBudget3013.Factory.newInstance();
        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwardsContract> budgetSubAwardsList = getBudgetSubAwards(pdDoc,RR_BUDGET30_13_NAMESPACE_URI,false);
        RRBudget13[] budgetList = new RRBudget13[budgetSubAwardsList.size()];
        rrSubawardBudget.setFormVersion(FormVersion.v1_3.getVersion());

        int attIndex = 1;
        for (BudgetSubAwardsContract budgetSubAwards : budgetSubAwardsList) {
            RRBudget13 rrBudget = getRRBudget(budgetSubAwards).getRRBudget13();
            switch (attIndex) {
                case 1:
                    rrSubawardBudget.setATT1(prepareAttName(budgetSubAwards));
                    budgetList[0] = rrBudget;
                    break;
                case 2:
                    rrSubawardBudget.setATT2(prepareAttName(budgetSubAwards));
                    budgetList[1] = rrBudget;
                    break;
                case 3:
                    rrSubawardBudget.setATT3(prepareAttName(budgetSubAwards));
                    budgetList[2] = rrBudget;
                    break;
                case 4:
                    rrSubawardBudget.setATT4(prepareAttName(budgetSubAwards));
                    budgetList[3] = rrBudget;
                    break;
                case 5:
                    rrSubawardBudget.setATT5(prepareAttName(budgetSubAwards));
                    budgetList[4] = rrBudget;
                    break;
                case 6:
                    rrSubawardBudget.setATT6(prepareAttName(budgetSubAwards));
                    budgetList[5] = rrBudget;
                    break;
                case 7:
                    rrSubawardBudget.setATT7(prepareAttName(budgetSubAwards));
                    budgetList[6] = rrBudget;
                    break;
                case 8:
                    rrSubawardBudget.setATT8(prepareAttName(budgetSubAwards));
                    budgetList[7] = rrBudget;
                    break;
                case 9:
                    rrSubawardBudget.setATT9(prepareAttName(budgetSubAwards));
                    budgetList[8] = rrBudget;
                    break;
                case 10:
                    rrSubawardBudget.setATT10(prepareAttName(budgetSubAwards));
                    budgetList[9] = rrBudget;
                    break;
                case 11:
                    rrSubawardBudget.setATT11(prepareAttName(budgetSubAwards));
                    budgetList[10] = rrBudget;
                    break;
                case 12:
                    rrSubawardBudget.setATT12(prepareAttName(budgetSubAwards));
                    budgetList[11] = rrBudget;
                    break;
                case 13:
                    rrSubawardBudget.setATT13(prepareAttName(budgetSubAwards));
                    budgetList[12] = rrBudget;
                    break;
                case 14:
                    rrSubawardBudget.setATT14(prepareAttName(budgetSubAwards));
                    budgetList[13] = rrBudget;
                    break;
                case 15:
                    rrSubawardBudget.setATT15(prepareAttName(budgetSubAwards));
                    budgetList[14] = rrBudget;
                    break;
                case 16:
                    rrSubawardBudget.setATT16(prepareAttName(budgetSubAwards));
                    budgetList[15] = rrBudget;
                    break;
                case 17:
                    rrSubawardBudget.setATT17(prepareAttName(budgetSubAwards));
                    budgetList[16] = rrBudget;
                    break;
                case 18:
                    rrSubawardBudget.setATT18(prepareAttName(budgetSubAwards));
                    budgetList[17] = rrBudget;
                    break;
                case 19:
                    rrSubawardBudget.setATT19(prepareAttName(budgetSubAwards));
                    budgetList[18] = rrBudget;
                    break;
                case 20:
                    rrSubawardBudget.setATT20(prepareAttName(budgetSubAwards));
                    budgetList[19] = rrBudget;
                    break;
                case 21:
                    rrSubawardBudget.setATT21(prepareAttName(budgetSubAwards));
                    budgetList[20] = rrBudget;
                    break;
                case 22:
                    rrSubawardBudget.setATT22(prepareAttName(budgetSubAwards));
                    budgetList[21] = rrBudget;
                    break;
                case 23:
                    rrSubawardBudget.setATT23(prepareAttName(budgetSubAwards));
                    budgetList[22] = rrBudget;
                    break;
                case 24:
                    rrSubawardBudget.setATT24(prepareAttName(budgetSubAwards));
                    budgetList[23] = rrBudget;
                    break;
                case 25:
                    rrSubawardBudget.setATT25(prepareAttName(budgetSubAwards));
                    budgetList[24] = rrBudget;
                    break;
                case 26:
                    rrSubawardBudget.setATT26(prepareAttName(budgetSubAwards));
                    budgetList[25] = rrBudget;
                    break;
                case 27:
                    rrSubawardBudget.setATT27(prepareAttName(budgetSubAwards));
                    budgetList[26] = rrBudget;
                    break;
                case 28:
                    rrSubawardBudget.setATT28(prepareAttName(budgetSubAwards));
                    budgetList[27] = rrBudget;
                    break;
                case 29:
                    rrSubawardBudget.setATT29(prepareAttName(budgetSubAwards));
                    budgetList[28] = rrBudget;
                    break;
                case 30:
                    rrSubawardBudget.setATT30(prepareAttName(budgetSubAwards));
                    budgetList[29] = rrBudget;
                    break;
            }
            addSubAwdAttachments(budgetSubAwards);
            attIndex++;
            
        }
        budgetAttachments.setRRBudget13Array(budgetList);
        rrSubawardBudget.setBudgetAttachments(budgetAttachments);
        rrSubawardBudgetDocument.setRRSubawardBudget3013(rrSubawardBudget);
        return rrSubawardBudgetDocument;
    }

    /**
     * 
     * This method is used to get RRBudget13 from BudgetSubAwards
     * 
     * @param budgetSubAwards(BudgetSubAwards) budget sub awards entry.
     * @return RRBudget13 corresponding to the BudgetSubAwards object.
     */
    private RRBudget13Document getRRBudget(BudgetSubAwardsContract budgetSubAwards) {
        RRBudget13Document rrBudget = RRBudget13Document.Factory.newInstance();
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc;
        try {
            subAwdFormsDoc = stringToDom(subAwdXML);
        }catch (S2SException e1) {
            return rrBudget;
        }
        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(RR_BUDGET30_13_NAMESPACE_URI, RR_BUDGET30_13_LOCAL_NAME);
        Node subAwdNode = null;
        if (subAwdNodeList != null){
            if(subAwdNodeList.getLength() == 0) {
                return null;
            }
            subAwdNode = subAwdNodeList.item(0);
        }
        byte[] subAwdNodeBytes = null;
        try {
            subAwdNodeBytes = docToBytes(nodeToDom(subAwdNode));
            InputStream bgtIS = new ByteArrayInputStream(subAwdNodeBytes);
            rrBudget = (RRBudget13Document) XmlObject.Factory.parse(bgtIS);
        }
        catch (S2SException e) {
            return rrBudget;
        }
        catch (XmlException e) {
            return rrBudget;
        }
        catch (IOException e) {
            return rrBudget;
        }
        return rrBudget;
    }


    /**
     * This method creates {@link XmlObject} of type {@link RRSubawardBudget3013Document} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        pdDoc=proposalDevelopmentDocument;
        return getRRSubawardBudgetDocument();
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
