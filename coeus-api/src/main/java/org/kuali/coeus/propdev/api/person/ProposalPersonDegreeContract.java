package org.kuali.coeus.propdev.api.person;

import org.kuali.coeus.common.api.person.attr.DegreeTypeContract;

public interface ProposalPersonDegreeContract extends ProposalPersonLink {

    Integer getDegreeSequenceNumber();

    String getGraduationYear();

    String getDegree();

    String getFieldOfStudy();

    String getSpecialization();

    String getSchool();

    String getSchoolIdCode();

    String getSchoolId();

    DegreeTypeContract getDegreeType();
}
