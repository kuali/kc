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
package org.kuali.kra.protocol.drools.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kuali.kra.protocol.drools.brms.FactBean;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * 
 * This class is to compile rule and execute rule.
 */
public class DroolsRuleHandler {

    private static final Log LOG = LogFactory.getLog(DroolsRuleHandler.class);

    private KieContainer rules;
    
    /**
     * This method is to get get rule from rule file.  
     * So, this is the rule engine class.
     */
    public DroolsRuleHandler(String ruleFile) {
        this.rules = getRuleBase(ruleFile);
    }
    
    /*
     * This method is get rule from rule file and compile it.
     */
    private KieContainer getRuleBase(String rulesFile) {
        final DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
        final Resource resource = resourceLoader.getResource(rulesFile);
        try {
            final KieServices kieServices = KieServices.Factory.get();
            final KieFileSystem kfs = kieServices.newKieFileSystem();
            kfs.write(ResourceFactory.newUrlResource(resource.getURL()));

            final KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();

            final Results results = kieBuilder.getResults();
            if (results.hasMessages(Message.Level.INFO)) {
                LOG.info(results.getMessages(Message.Level.INFO));
            }
            if (results.hasMessages(Message.Level.WARNING)) {
                LOG.warn(results.getMessages(Message.Level.WARNING));
            }
            if (results.hasMessages(Message.Level.ERROR)) {
                throw new RuntimeException(results.getMessages(Message.Level.ERROR).toString());
            }

            return kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * this method executes the rules that were previously loaded in the class's constructor.
     */
    public <T extends FactBean> void executeRules(T fact) { 

        KieSession session = null;
        try {
            session = rules.newKieSession();
            session.insert(fact);
            session.fireAllRules();
        } finally {
            if (session != null) {
                session.dispose();
            }
        }
    }
}
