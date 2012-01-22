/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.hierarchyrouting;


import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.core.api.util.xml.XmlException;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.engine.node.NodeState;
import org.kuali.rice.kew.engine.node.RouteNode;
import org.kuali.rice.kew.engine.node.RouteNodeConfigParam;
import org.kuali.rice.kew.engine.node.RouteNodeInstance;
import org.kuali.rice.kew.engine.node.hierarchyrouting.HierarchyProvider;
import org.kuali.rice.kew.rule.NamedRuleSelector;
import org.kuali.rice.kew.util.Utilities;

/**
 * 
 * This class is a HierarchyProvider implementation that will walk the KRA Unit Hierarchy.
 */
public class KraHierarchyProvider implements HierarchyProvider {
    private static final Log LOG = LogFactory.getLog(KraHierarchyProvider.class);
    private static final String DOCUMENT_XML_ELEMENT = "document";
    private static final String UNIT_XML_ELEMENT = "ownedByUnitNumber";

    private String unitNumber;
    /**
     * The root stop
     */
    private UnitStop root;
    /**
     * Map of Stop id-to-Stop instance
     */
    private Map<String, UnitStop> stops = new HashMap<String, UnitStop>();

    public void configureRequestNode(RouteNodeInstance hiearchyNodeInstance, RouteNode node) {
        Map<String, String> cfgMap = Utilities.getKeyValueCollectionAsMap(node.getConfigParams());
        // propagate rule selector and name from hierarchy node
        if (!cfgMap.containsKey(RouteNode.RULE_SELECTOR_CFG_KEY)) {
            Map<String, String> hierarchyCfgMap = Utilities.getKeyValueCollectionAsMap(hiearchyNodeInstance.getRouteNode()
                    .getConfigParams());
            node.getConfigParams().add(
                    new RouteNodeConfigParam(node, RouteNode.RULE_SELECTOR_CFG_KEY, hierarchyCfgMap
                            .get(RouteNode.RULE_SELECTOR_CFG_KEY)));
        }
        if (!cfgMap.containsKey(NamedRuleSelector.RULE_NAME_CFG_KEY)) {
            Map<String, String> hierarchyCfgMap = Utilities.getKeyValueCollectionAsMap(hiearchyNodeInstance.getRouteNode()
                    .getConfigParams());
            node.getConfigParams().add(
                    new RouteNodeConfigParam(node, NamedRuleSelector.RULE_NAME_CFG_KEY, hierarchyCfgMap
                            .get(NamedRuleSelector.RULE_NAME_CFG_KEY)));
        }
    }


    public boolean equals(Stop a, Stop b) {
        return ObjectUtils.equals(a, b);
    }

    public List<Stop> getLeafStops(RouteContext context) {
        List<Stop> leafStops = new ArrayList<Stop>();
        for (UnitStop stop : stops.values()) {
            if (stop.children.size() == 0) {
                leafStops.add(stop);
            }
        }
        return leafStops;
    }

    public Stop getParent(Stop stop) {
        return ((UnitStop) stop).parent;
    }

    public boolean isRoot(Stop stop) {
        return equals(stop, root);
    }

    public Stop getStop(RouteNodeInstance nodeInstance) {
        NodeState state = nodeInstance.getNodeState("id");
        if (state == null) {
            // return null;
            throw new RuntimeException();
        }
        else {
            LOG.warn("id Node state on nodeinstance " + nodeInstance + ": " + state);
            return stops.get(state.getValue());
        }
    }

    public Stop getStopByIdentifier(String stopId) {
        return stops.get(stopId);
    }

    public String getStopIdentifier(Stop stop) {
        return ((UnitStop) stop).id;
    }

    public boolean hasStop(RouteNodeInstance nodeInstance) {
        return nodeInstance.getNodeState("id") != null;
    }

    private String retrieveDocumentUnitNumber(RouteContext context) {
        Document document = XmlHelper.buildJDocument(context.getDocumentContent().getDocument());
        String ownedByUnitNumber = null;
        
        try {
            List<Element> documentElements = (List<Element>) XmlHelper.findElements(document.getRootElement(), DOCUMENT_XML_ELEMENT);
            if (documentElements != null) {
                ownedByUnitNumber = documentElements.get(0).getChildText(UNIT_XML_ELEMENT);
            }
        }
        catch (XmlException e) {
            LOG.debug("XmlException Occurred while retrieving UnitNumber"); 
        }
        
        return ownedByUnitNumber;
    }

    public void init(RouteNodeInstance nodeInstance, RouteContext context) {
        if (StringUtils.isEmpty(unitNumber)) {
            unitNumber = retrieveDocumentUnitNumber(context);
        }

        Unit ownedByUnit = null;

        if (StringUtils.isNotEmpty(unitNumber)) {
            ownedByUnit = getService(UnitService.class).getUnit(unitNumber);
            if (ownedByUnit != null) {
                Collection<Unit> units = getService(UnitService.class).getAllSubUnits(unitNumber);
                for (Unit unit : units) {
                    UnitStop simpleStop = (UnitStop) getStopByIdentifier(unit.getUnitNumber());
                    if (simpleStop == null) {
                        simpleStop = new UnitStop();
                        simpleStop.id = unit.getUnitNumber();
                        stops.put(simpleStop.id, simpleStop);
                    }
                    if (StringUtils.isNotBlank(unit.getParentUnitNumber())) {
                        UnitStop parent = (UnitStop) getStopByIdentifier(unit.getParentUnitNumber());
                        if (parent == null) {
                            parent = new UnitStop();
                            parent.id = unit.getParentUnitNumber();
                            parent.children = new ArrayList<UnitStop>();
                            stops.put(parent.id, parent);
                        }
                        parent.children.add(simpleStop);
                        simpleStop.parent = parent;
                    }
                    else {
                        root = simpleStop;
                        simpleStop.parent = null;
                    }
                }

                if (root == null) {
                    root = (UnitStop) getStopByIdentifier(unitNumber);
                }
            }
        }
    }


    public void setStop(RouteNodeInstance requestNodeInstance, Stop stop) {
        // TODO : not sure about this one yet ?
        // SimpleStop ss = (SimpleStop) stop;
        requestNodeInstance.addNodeState(new NodeState("id", getStopIdentifier(stop)));
        // requestNodeInstance.addNodeState(new NodeState(KEWConstants.RULE_SELECTOR_NODE_STATE_KEY, "named"));
        // requestNodeInstance.addNodeState(new NodeState(KEWConstants.RULE_NAME_NODE_STATE_KEY, "NodeInstanceRecipientRule"));
    }


}
