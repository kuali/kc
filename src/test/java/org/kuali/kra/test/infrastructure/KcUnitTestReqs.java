/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.test.infrastructure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestConfigLifecycle;
import org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestContextLifecycle;
import org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestLifecycle;
import org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestServerLifecycle;

/**
 * This class...
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface KcUnitTestReqs {
	Req[] value();
	/**
	 * This class...
	 */
	public enum Req {
		CONFIG (new KcUnitTestConfigLifecycle()),
		CONTEXT (new KcUnitTestContextLifecycle()),
		SERVER (new KcUnitTestServerLifecycle());
		
		private KcUnitTestLifecycle lifecycle;
		
		Req(KcUnitTestLifecycle lifecycle) {
			this.lifecycle = lifecycle;
		}
		
		/**
		 * This method...
		 */
		public void start() {
			lifecycle.start();
		}
		
		/**
		 * This method...
		 */
		public void stop() {
			lifecycle.stop();
		}
		
        /**
         * This method...
         */
        public void launch() {
            lifecycle.launch();
        }
        
        /**
         * This method...
         */
        public void shutdown() {
            lifecycle.shutdown();
        }
        
        /**
         * This method...
         * @return
         */
        public boolean isStarted() {
            return lifecycle.isStarted();
        }
        
        /**
         * This method...
         * @return
         */
        public boolean isLaunched() {
            return lifecycle.isLaunched();
        }
        
		/**
		 * This method...
		 */
		public static void shutdownAll() {
			for (Req req : Req.values()) {
				if (req.isLaunched()) {
					req.shutdown();
				}
			}
		}	
	}
}
