package org.kuali.kra.s2s.phs398careerdevelopmentawardSup;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.s2sgen.impl.generate.support.PHS398CareerDevelopmentAwardSupV2_0Generator;
import org.kuali.kra.s2s.generator.S2SModularBudgetTestBase;
import org.kuali.kra.s2s.generator.util.S2STestConstants;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class PHS398CareerDevelopmentAwardSupV2_0GeneratorTest extends
		S2SModularBudgetTestBase<PHS398CareerDevelopmentAwardSupV2_0Generator> {

	@Override
	protected String getFormGeneratorName() {
		return PHS398CareerDevelopmentAwardSupV2_0Generator.class.getSimpleName();
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		
		NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
		DefaultResourceLoader resourceLoader = new DefaultResourceLoader(
				ClassLoaderUtils.getDefaultClassLoader());
		Resource resource = resourceLoader
				.getResource(S2STestConstants.ATT_PACKAGE + "/exercise2.pdf");
		InputStream inputStream = resource.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		byte[] narrativePdf = new byte[bis.available()];
		narrativeAttachment.setData(narrativePdf);
		narrativeAttachment.setName("exercise1");
		Narrative narrative = new Narrative();
		List<Narrative> narrativeList = new ArrayList<Narrative>();
		narrative.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType = new NarrativeType();
		narrativeType.setCode("128");
		narrativeType.setAllowMultiple(true);
		narrativeType.setSystemGenerated(false);
		narrativeType.setDescription("Test for Attachment");
		getService(DataObjectService.class).save(narrativeType);
        narrative.setName("exercise1");
        narrative.setNarrativeType(narrativeType);
		narrative.setNarrativeTypeCode("128");
		narrative.setNarrativeAttachment(narrativeAttachment);
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.setModuleStatusCode("C");
		narrativeList.add(narrative);
		document.getDevelopmentProposal().setNarratives(narrativeList);		
	}
}
