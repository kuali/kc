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
package org.kuali.coeus.s2sgen.impl.generate.support;

import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonDegree;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;

import java.util.*;


public abstract class NSFCoverPageLegacyBaseGeneratorTest extends NSFCoverPageBaseGeneratorTest {

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        super.prepareData(document);

        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonRoleId("PI");
        person.setFirstName("SCHULTE");
        person.setLastName("MARITZA");
        person.setMiddleName("D");
        person.setPersonId("000000077 ");
        person.setOptInCertificationStatus(true);
        person.setOptInUnitStatus(true);
        person.setProposalPersonNumber(1000);
        person.setDevelopmentProposal(document.getDevelopmentProposal());

        ProposalPersonDegree degree = new ProposalPersonDegree();
        degree.setDegree("Computer Science");
        degree.setDegreeCode("BS");
        degree.refreshReferenceObject("degreeType");
        degree.setSchool("Michigan State Univerity");
        degree.setSchoolId("MSU");
        degree.setSchoolIdCode("1");
        degree.setGraduationYear("2004");
        degree.setDegreeSequenceNumber(1);
        degree.setProposalPerson(person);
        List<ProposalPersonDegree> degrees = new ArrayList<>();
        degrees.add(degree);
        person.setProposalPersonDegrees(degrees);
        List<ProposalPerson> perList = new ArrayList<>();
        perList.add(person);
        document.getDevelopmentProposal().setProposalPersons(perList);


        Narrative narrative = new Narrative();
        List<Narrative> naList = new ArrayList<>();
        narrative.setDevelopmentProposal(document.getDevelopmentProposal());
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.setModuleStatusCode("C");
        narrative.setNarrativeTypeCode("13");
        narrative.refreshReferenceObject("narrativeType");
        narrative.setObjectId("12345678890abcd");
        narrative.setName("exercise1");
        narrative.setModuleTitle("A title");

        NarrativeAttachment attachment = new NarrativeAttachment();
        attachment.setName("exercise1");
        attachment.setType("application/octet-stream");
        attachment.setData(new byte[10]);
        attachment.setNarrative(narrative);
        narrative.setNarrativeAttachment(attachment);

        naList.add(narrative);

        document.getDevelopmentProposal().setNarratives(naList);

        ProposalYnq ynq = new ProposalYnq();
        ynq.setQuestionId("19");
        ynq.refreshReferenceObject("ynq");
        ynq.setAnswer("Y");
        ynq.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());

        List<ProposalYnq> ynqs = new ArrayList<>();
        ynqs.add(ynq);

        document.getDevelopmentProposal().setProposalYnqs(ynqs);

    }

    protected abstract String getFormName();
    protected abstract String getNamespace();
}
