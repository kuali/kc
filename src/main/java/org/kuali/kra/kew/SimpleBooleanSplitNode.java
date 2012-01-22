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
package org.kuali.kra.kew;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.engine.RouteHelper;
import org.kuali.rice.kew.engine.node.SplitNode;
import org.kuali.rice.kew.engine.node.SplitResult;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;

/**
 * This code was taken directly from KFS SimpleBooleanSplitNode
 *  @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SimpleBooleanSplitNode implements SplitNode {

    private static org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SimpleBooleanSplitNode.class);

    /**
     * This method will look up the document being routed, if it is an instance of ResearchDocumentBase
     * it will call answerSplitNodeQuestion on it passing the name of the route node.  The default implementation (currently)
     * throws an UnsupportedOperationException for any input. If one wishes to support the SplitNode for a given document, the
     * method should be overridden and return boolean T/F based on which of the branches ( always names "True" and "False" ) 
     * KEW should route to based upon the name of the split node.
     * 
     * @see org.kuali.rice.kew.engine.node.SimpleNode#process(org.kuali.rice.kew.engine.RouteContext, org.kuali.rice.kew.engine.RouteHelper)
     */
    
    public SplitResult process(RouteContext context, RouteHelper helper ) throws Exception {
        String documentID = context.getDocument().getDocumentId().toString();
        String routeNodeName = context.getNodeInstance().getRouteNode().getRouteNodeName();
        if( LOG.isDebugEnabled() )
            LOG.debug(String.format("Entering routeNode:%s for documentId:%s",routeNodeName,documentID ));
        Document document = 
            KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderIdSessionless(documentID);
        
        if( document instanceof ResearchDocumentBase ) {
            boolean ret = ((ResearchDocumentBase)document).answerSplitNodeQuestion( routeNodeName );
            if( LOG.isDebugEnabled() )
                LOG.debug( String.format("answerSplitNodeQuestion returned:%s",ret) );
            return booleanToSplitResult( ret ); 
        }             
        throw new UnsupportedOperationException( "Document was not instance of:"+ResearchDocumentBase.class+", not supported by SimpleBooleanSplitNode." );
    }
    
    
    /**
     * Converts a boolean value to SplitResult where the branch name is "True" or "False" based on the value of the given boolean
     * @param b a boolean to convert to a SplitResult
     * @return the converted SplitResult
     */
    protected SplitResult booleanToSplitResult(boolean b) {
        List<String> branches = new ArrayList<String>();
        final String branchName = b ? "True" : "False";
        branches.add(branchName);
        return new SplitResult(branches);
    }


}
