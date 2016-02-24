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
package org.kuali.coeus.common.framework.medusa;

import java.util.List;

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
