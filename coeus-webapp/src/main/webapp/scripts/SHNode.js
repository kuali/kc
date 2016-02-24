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

SHNode = function(oData, oParent, expanded, hasIcon, isVirtualNode, description) {
    if (oData) { 
        this.init(oData, oParent, expanded, hasIcon);
        this.initContent(oData, hasIcon); // not sure why need this.  otherwise, the lable is empty ?
         this.isVirtualNode = isVirtualNode;
        this.description = description;
    }
};

YAHOO.lang.extend(SHNode, YAHOO.widget.HTMLNode, {

    /**
     * check whether a node is virtual or not.
     * @property isVirtualNode
     * @type boolean
     */
    isVirtualNode: false,

    /**
     * The description of the node.
     * @property description
     * @type string
     */
    description: null,
    sortId: null,
    sponsorCode: null,


    toString: function() { 
        return "SHNode (" + this.index + ")";
    }

});



