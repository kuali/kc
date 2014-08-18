package org.kuali.kra.tool.jpa.eclipselink.conv;


import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.metadata.DescriptorRepository;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.sys.framework.persistence.BooleanNFConverter;
import org.kuali.coeus.sys.framework.persistence.OjbBlobClobFieldConversion;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.coeus.sys.framework.persistence.OjbScaleTwoDecimalFieldConversion;
import org.kuali.rice.devtools.jpa.eclipselink.conv.common.CommonUtil;
import org.kuali.rice.devtools.jpa.eclipselink.conv.ojb.OjbUtil;
import org.kuali.rice.devtools.jpa.eclipselink.conv.parser.visitor.EntityVisitor;
import org.kuali.rice.krad.data.jpa.converters.KualiPercentConverter;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;


public class OjbToJpaConversionDriver {

    private static final Log LOG = LogFactory.getLog(OjbToJpaConversionDriver.class);


    private static void setupConfig() {
        ConversionConfig cfg = ConversionConfig.getInstance();
        cfg.setProjectHomeDir("/Users/Travis/Documents/idea/ws/kuali/kc/kc/coeus-impl");
        cfg.setProjectResourceDir("/src/main/resources");
        cfg.setProjectSourceDir("/src/main/java");

        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/award/repository-award.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/budget/repository-budget.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/coi/repository-coi.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/committee/repository-committee.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/common/committee/repository-commonCommittee.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/iacuc/repository-iacuc.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/iacuc/repository-iacucCommittee.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/institutionalproposal/repository-institutionalproposal.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/irb/repository-irb.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/negotiation/repository-negotiation.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/personmasschange/repository-personmasschange.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/coeus/propdev/impl/repository-proposaldevelopment.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/questionnaire/repository-questionnaire.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/subaward/repository-subAward.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/org/kuali/kra/timeandmoney/repository-timeandmoney.xml"));
        cfg.addOjbRepositoryFile(fullResourcePath(cfg, "/repository.xml"));

        cfg.addConverter(OjbScaleTwoDecimalFieldConversion.class.getName(), ScaleTwoDecimalConverter.class.getName());
        cfg.addConverter(org.kuali.rice.core.framework.persistence.ojb.conversion.OjbKualiEncryptDecryptFieldConversion.class.getName(), org.kuali.rice.krad.data.jpa.converters.EncryptionConverter.class.getName());
        cfg.addConverter(org.kuali.kra.infrastructure.OjbOnOffCampusFlagFieldConversion.class.getName(), BooleanNFConverter.class.getName());
        //do not need.  this is an enum type.  should be annotated with @Enumeration
        cfg.addConverter(org.kuali.kra.award.contacts.UnitContactTypeConverter.class.getName(), "");
        cfg.addConverter(org.kuali.rice.core.framework.persistence.ojb.conversion.OjbKualiDecimalFieldConversion.class.getName(), org.kuali.rice.krad.data.jpa.converters.KualiDecimalConverter.class.getName());
        cfg.addConverter(org.kuali.rice.core.framework.persistence.ojb.conversion.OjbKualiPercentFieldConversion.class.getName(), KualiPercentConverter.class.getName());
        //I think that nothing is needed here as JPA will use the correct jdbc type
        cfg.addConverter(OjbBlobClobFieldConversion.class.getName(), "");
        cfg.addConverter(org.kuali.rice.core.framework.persistence.ojb.conversion.OjbCharBooleanConversion.class.getName(), org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter.class.getName());
    }

    public static void main(String...s) throws Exception {

        setupConfig();

        final Collection<DescriptorRepository> drs = OjbUtil.getDescriptorRepositories(ConversionConfig.getInstance().getOjbRepositoryFiles());
        /*for (DescriptorRepository dr : drs) {
            for (Map.Entry<String, ClassDescriptor> entry : ((Map<String, ClassDescriptor>) dr.getDescriptorTable()).entrySet()) {
                boolean ver = false;
                boolean objId = false;
                boolean updateUser = false;
                boolean updateTs = false;


                FieldDescriptor[] fds = entry.getValue().getFieldDescriptions();
                if (fds != null) {
                    for (FieldDescriptor fd : fds) {
                        if (fd.getAttributeName().equals("versionNumber")) {
                            ver = true;
                        }

                        if (fd.getAttributeName().equals("objectId")) {
                            objId = true;
                        }
                    }

                    if (!ver && objId) {
                        System.out.println(entry.getKey());
                    }
                }

            }
        }
        */
        final Collection<String> ojbMappedClasses = OjbUtil.mappedClasses(drs);
        final Collection<String> allSuperClasses = new HashSet<String>();
        for (String ojbMappedClass : ojbMappedClasses) {

            //if (ojbMappedClass.endsWith("CongressionalDistrict") || ojbMappedClass.endsWith("S2sAppAttachments")) {
            //if (ojbMappedClass.endsWith("InstitutionalProposalComment") || ojbMappedClass.endsWith("InstitutionalProposalNotepad")) {
            if (OjbUtil.getMappedTree(BudgetDocument.class.getName(), drs).contains(ojbMappedClass)) {
                //if (ojbMappedClass.endsWith("ProposalDevelopmentDocument")) {
                    System.out.println(ojbMappedClass);
                    visit(ojbMappedClass, ojbMappedClass, drs);

                    final Collection<String> superClasses = OjbUtil.getSuperClasses(ojbMappedClass, "org.kuali.rice");
                    for (String superClass : superClasses) {
                        allSuperClasses.add(superClass);
                        visit(superClass, ojbMappedClass, drs);

                    }
                //}
            }
        }

        for (String superClass : allSuperClasses) {
            System.out.println(superClass);
        }
    }



    private static void visit(String clazz, String mappedClazz, Collection<DescriptorRepository> drs) throws Exception {
        final String clazzFile = toFilePath(ConversionConfig.getInstance(), clazz);
        System.err.println("PROCESSING: " + clazzFile);
        if (clazzFile.endsWith("/org/kuali/coeus/common/budget/framework/core/Budget.java")) {
            final CompilationUnit unit = JavaParser.parse(new File(clazzFile));
            new EntityVisitor(drs, ConversionConfig.getInstance().getConverters(), true, true).visit(unit, mappedClazz);
            //throw new RuntimeException();
            FileUtils.write(new File(clazzFile), unit.toString(), false);
            // LOG.error(unit.toString());
        }
    }

    private static String toFilePath(ConversionConfig cfg, String mappedClass) {
        return CommonUtil.toFilePath(mappedClass, cfg.getProjectHomeDir(), cfg.getProjectSourceDir());
    }

    private static String fullResourcePath(ConversionConfig cfg, String remaining) {
        return cfg.getProjectHomeDir() + cfg.getProjectResourceDir() + remaining;
    }
}
