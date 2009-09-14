/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.drools.util;

import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.rule.Package;
import org.drools.rule.builder.dialect.java.JavaDialectConfiguration;
import org.kuali.kra.drools.brms.FactBean;

/**
 * 
 * This class is to compile rule and execute rule.
 */
public class DroolsRuleHandler {
   
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
        //RuleBase rules = null;
        try {
            // Read in the rules source file
            Reader source = new InputStreamReader(this.getClass().getResourceAsStream("/" + rulesFile));

            // to force it to use Janino compiler.  There is conflict of JDT compiler.
            PackageBuilderConfiguration pkgBuilderCfg = new PackageBuilderConfiguration();
            pkgBuilderCfg.setClassLoader( this.getClass().getClassLoader() );
            JavaDialectConfiguration javaConf = (JavaDialectConfiguration)
            pkgBuilderCfg.getDialectConfiguration( "java" );
            javaConf.setCompiler( JavaDialectConfiguration.JANINO );
            PackageBuilder builder = new PackageBuilder( pkgBuilderCfg );

            // This will parse and compile in one step
            builder.addPackageFromDrl(source);

            // Get the compiled package
             Package pkg = builder.getPackage();
            
            // Add the package to a rulebase (deploy the rule package).
            rules = RuleBaseFactory.newRuleBase();
            rules.addPackage(pkg);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rules;
    }

    /**
     * this method executes the rules that were previously loaded in the class's constructor. 
     * @param <T>
     * @param fact
     */
    public <T extends FactBean> void executeRules(T fact) { 
        WorkingMemory workingMemory = rules.newStatefulSession();
        workingMemory.insert(fact);
        workingMemory.fireAllRules();
    }
}
