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
package org.kuali.kra;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// I'm basically disabling this class because of some changes made to KraServiceLocator
// during the merge.  And it's not being used by anyone yet.
// If someone starts to use this, we'll dust it off. -gmcgrego

/**
 * Provides methods to manipulate the application context used by KraServiceLocator.
 * This is intended to be used only in a test scenario.
 * 
 * @author Kuali Coeus Development Team
 */
public class KraServiceLocatorConfigurer {
    
    /**
     * Get a new ConfigurableApplicationContext with no beans registered yet.
     * 
     * @return ConfigurableApplicationContext
     */
    public static ConfigurableApplicationContext getEmptyApplicationContext() {
        return new ClassPathXmlApplicationContext("EmptySpringBeans.xml");
    }
    
    /**
     * Creates a ConfigurableApplicationContext based on the given SpringBeans file and 
     * sets it as the app context.
     * 
     * If an application context already exists, the existing app context will be set as the parent
     * of the new one.  This will override the parent bean registries but non-overriden beans will
     * remain visible.
     * 
     * @param ConfigurableApplicationContext context
     */
    public static void pushApplicationContext(String springBeansFile) {
        pushApplicationContext(new ClassPathXmlApplicationContext(springBeansFile));
    }
    
    /**
     * Creates a ConfigurableApplicationContext based on the given SpringBeans files and 
     * sets it as the app context.
     * 
     * If an application context already exists, the existing app context will be set as the parent
     * of the new one.  This will override the parent bean registries but non-overriden beans will
     * remain visible.
     * 
     * @param ConfigurableApplicationContext context
     */
    public static void pushApplicationContext(String[] springBeansFiles) {
        pushApplicationContext(new ClassPathXmlApplicationContext(springBeansFiles));
    }
    
    /**
     * Sets the given ConfigurableApplicationContext as the app context.
     * 
     * If an application context already exists, the existing app context will be set as the parent
     * of the new one.  This will override the parent bean registries but non-overriden beans will
     * remain visible.
     * 
     * @param ConfigurableApplicationContext context
     */
    public static void pushApplicationContext(ConfigurableApplicationContext context) {
        if (!isApplicationContextInitialized()) {
            //setApplicationContext(context);
        } else {
            //ConfigurableApplicationContext existingAppContext = getAppContextWithoutInitializing();
            //context.getBeanFactory().setParentBeanFactory(existingAppContext.getBeanFactory());
            //context.setParent(existingAppContext);
            //setApplicationContext(context);
        }
    }
    
    /**
     * Remove the current application context.  If it has a parent, the parent will become
     * the application context.  Typically this would be used in conjunction with pushApplicationContext.
     */
    public static void popApplicationContext() {
        if (!isApplicationContextInitialized()) {
            return;
        }
        //ApplicationContext parentApplicationContext = getAppContextWithoutInitializing().getParent();
        //if (parentApplicationContext instanceof ConfigurableApplicationContext || parentApplicationContext == null) {
            //setApplicationContext((ConfigurableApplicationContext) parentApplicationContext);
        //}
    }
    
    /**
     * Checks whether the app context has been initialized.
     * @boolean
     */
    public static boolean isApplicationContextInitialized() {
        return true;
        //return getAppContextWithoutInitializing() != null;
    }
    
    /**
     * Returns int corresponding to the number of contexts in the chain
     */
    public static int getContextDepth() {
        return 1;
        //return getContextDepth(getAppContextWithoutInitializing());
    }
    
    private static int getContextDepth(ApplicationContext appContext) {
        if (appContext == null) {
            return 0;
        }
        return 1 + getContextDepth(appContext.getParent());
    }

}
