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
package org.kuali.kra.protocol.drools.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.compiler.rule.builder.dialect.java.JavaDialectConfiguration;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.RuntimeDroolsException;
import org.drools.core.WorkingMemory;
import org.kie.internal.builder.ResultSeverity;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.compiler.compiler.PackageBuilderConfiguration;
import org.drools.core.rule.Package;

import org.kuali.kra.protocol.drools.brms.FactBean;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 
 * This class is to compile rule and execute rule.
 */
public class DroolsRuleHandler {

    private static final Log LOG = LogFactory.getLog(DroolsRuleHandler.class);

    private RuleBase rules;
    
    /**
     * This method is to get get rule from rule file.  
     * So, this is the rule engine class.
     * Constructs a DroolsRuleHandler.java.
     * @param ruleFile
     */
    public DroolsRuleHandler(String ruleFile) {
        this.rules = getRuleBase(ruleFile);
    }
    
    /*
     * This method is get rule from rule file and compile it.
     */
    private RuleBase getRuleBase(String rulesFile) {
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
            Resource resource = resourceLoader.getResource(rulesFile);
            Reader source =  new InputStreamReader(resource.getInputStream());

            PackageBuilderConfiguration pkgBuilderCfg = new PackageBuilderConfiguration(this.getClass().getClassLoader());
            JavaDialectConfiguration javaConf = (JavaDialectConfiguration)
            pkgBuilderCfg.getDialectConfiguration( "java" );
            javaConf.setCompiler( JavaDialectConfiguration.ECLIPSE );
            PackageBuilder builder = new PackageBuilder( pkgBuilderCfg );

            // This will parse and compile in one step
            builder.addPackageFromDrl(source);
            if (builder.hasInfo()) {
                LOG.info(builder.getProblems(ResultSeverity.INFO));
            }
            if (builder.hasWarnings()) {
                LOG.warn(builder.getProblems(ResultSeverity.WARNING));
            }
            if (builder.hasErrors()) {
                throw new RuntimeDroolsException(builder.getErrors().toString());
            }

            // Get the compiled package
            Package pkg = builder.getPackage();
            
            // Add the package to a rulebase (deploy the rule package).
            rules = RuleBaseFactory.newRuleBase();
            rules.addPackage(pkg);

        } catch (DroolsParserException|IOException e) {
            throw new RuntimeDroolsException(e);
        }
        return rules;
    }

    /**
     * this method executes the rules that were previously loaded in the class's constructor. 
     * @param <T>
     * @param fact
     */
    public <T extends FactBean> void executeRules(T fact) { 
        /*
         * Stateful sessions are not required in this scenario since the protocol rule base does
         * not change once the rules are fired.
         */
        WorkingMemory workingMemory = rules.newStatefulSession(false);
        workingMemory.insert(fact);
        workingMemory.fireAllRules();
    }
}
