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

import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import java.util.ArrayList;
import java.util.List;

public class PHS398ResearchPlanV1_2GeneratorTest extends S2STestBase {

	@Override
	protected String getFormGeneratorName() {
		return PHS398ResearchPlanV1_1Generator.class.getSimpleName();
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		DevelopmentProposal developmentProposal = document
				.getDevelopmentProposal();
        ProposalType type = new ProposalType();
        type.setCode("5");
        developmentProposal.setProposalType(type);
		List<Narrative> naList = new ArrayList<>();

		Narrative narrative1 = createNarrative("20");

		narrative1.setDevelopmentProposal(document.getDevelopmentProposal());
		naList.add(narrative1);
		developmentProposal.setNarratives(naList);
		document.setDevelopmentProposal(developmentProposal);
	}
}
