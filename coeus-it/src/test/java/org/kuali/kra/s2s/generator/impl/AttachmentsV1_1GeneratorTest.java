/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.s2s.generator.impl;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.s2sgen.impl.generate.support.AttachmentsV1_1Generator;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.rice.krad.data.DataObjectService;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
/**
 * This class tests the AttachmentsV1_1GeneratorTest
 */
public class AttachmentsV1_1GeneratorTest extends S2STestBase<AttachmentsV1_1Generator> {

    @Override
    protected String getFormGeneratorName() {
        return AttachmentsV1_1Generator.class.getSimpleName();
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        Narrative narrative = new Narrative();
        List<Narrative> naList = new ArrayList<Narrative>();
        narrative.setDevelopmentProposal(document.getDevelopmentProposal());
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.setModuleStatusCode("C");
        narrative.setNarrativeTypeCode("52");
        narrative.setObjectId("12345678890abcd");
        narrative.setName("exercise1");
        NarrativeType narrativeType = new NarrativeType();
        narrativeType.setCode("1");
        narrativeType.setAllowMultiple(false);
        narrativeType.setSystemGenerated(false);
        narrativeType.setDescription("Testing for Attachments Attachment");
        getService(DataObjectService.class).save(narrativeType);
        narrative.setNarrativeType(narrativeType);
        narrative.setNarrativeTypeCode("1");
        naList.add(narrative);
        narrative.setNarrativeAttachment(null);
        document.getDevelopmentProposal().setNarratives(naList);
    }
}
