
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



