package org.kuali.coeus.s2sgen.impl.generate.support;


import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class PHS398ResearchPlanBaseGeneratorTest extends S2STestBase {

    protected Narrative createNarrative(String narrativeTypeCode) throws IOException {
        Narrative narrative = new Narrative();
        NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
        Resource resource = resourceLoader.getResource(S2STestConstants.ATT_PACKAGE + "/exercise1.pdf");
        InputStream inStream = resource.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inStream);
        byte[] narrativePdf = new byte[bis.available()];
        narrativeAttachment.setData(narrativePdf);
        narrativeAttachment.setName("exercise1.pdf");
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.setModuleStatusCode("C");
        narrative.setNarrativeTypeCode(narrativeTypeCode);
        narrative.refreshReferenceObject("narrativeType");
        narrative.setNarrativeAttachment(narrativeAttachment);
        narrative.setObjectId("12345678890abcd");
        narrative.setName("exercise1");
        return narrative;
    }
}
