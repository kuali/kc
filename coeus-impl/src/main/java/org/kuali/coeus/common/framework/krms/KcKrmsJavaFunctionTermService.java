package org.kuali.coeus.common.framework.krms;

import org.kuali.coeus.common.framework.sponsor.Sponsorable;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;

public interface KcKrmsJavaFunctionTermService {

    Boolean checkPropertyValueForAnyPreviousVersion(SequenceOwner<?> currentVersion, String property, String comparison);

    Boolean hasPropertyChangedThisVersion(SequenceOwner<?> currentVersion, String property);

    Boolean doSponsorAndPrimeSponsorMatch(Sponsorable grantsBo);

}