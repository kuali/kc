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
package org.kuali.coeus.s2sgen.impl.generate.support;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;

import java.util.ArrayList;
import java.util.List;

/**
 * This class tests the NSFSuggestedReviewersV1_1 Generator
 */
public class NSFSuggestedReviewersV1_1GeneratorTest extends S2STestBase {

    @Override
    protected String getFormGeneratorName() {
        return NSFSuggestedReviewersV1_1Generator.class.getSimpleName();
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        ProposalAbstract propsAbstract = new ProposalAbstract();
        propsAbstract.setAbstractTypeCode("14");
        propsAbstract.setAbstractDetails("NSFSuggestedReviewers AbstractDetails");
        ProposalAbstract propsAbstract1 = new ProposalAbstract();
        propsAbstract1.setAbstractTypeCode("12");
        propsAbstract1.setAbstractDetails("AbstractDetails");
        List<ProposalAbstract> proList = new ArrayList<ProposalAbstract>();
        proList.add(propsAbstract);
        proList.add(propsAbstract1);

        propsAbstract.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        propsAbstract1.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        document.getDevelopmentProposal().setProposalAbstracts(proList);
    }
}
