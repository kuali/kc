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
package org.kuali.kra.medusa.service;

import java.util.List;

import org.kuali.kra.medusa.MedusaNode;

/**
 * 
 * Medusa Service provides the methods for displaying the tree-like view and summary of
 * related documents.
 */
public interface MedusaService {

    /**
     * 
     * Returns a list of MedusaNode objects that describe the top level of
     * the tree-like structure of the Medusa object passed in using the Awards(if available)
     * as the top level nodes.
     * @param moduleName name of the module to be looked up (ie. award, IP, DP)
     * @param moduleIdentifier the primary key of the object to be looked up in the specified module
     * @return
     */
    public List<MedusaNode> getMedusaByAward(String moduleName, Long moduleIdentifier);
    
    /**
     * 
     * Returns a list of MedusaNode objects that describe the top level of
     * the tree-like structure of the Medusa object passed in using the Institutional Proposals(if available)
     * as the top level nodes.
     * @param moduleName name of the module to be looked up (ie. award, IP, DP)
     * @param moduleIdentifier the primary key of the object to be looked up in the specified module
     * @return
     */
    public List<MedusaNode> getMedusaByProposal(String moduleName, Long moduleIdentifier);
    
    /**
     * 
     * Returns a single MedusaNode that can be used to render the lazy-loaded summary for the
     * BO contained in that node
     * @param moduleName name of the module to be looked up (ie. award, IP, DP)
     * @param moduleIdentifier the primary key of the object to be looked up in the specified module
     * @return
     */
    public MedusaNode getMedusaNode(String moduleName, Long moduleIdentifier);
    
 
}
