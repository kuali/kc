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
    function setUnitNumber(unitNumber) {        
    	document.getElementById("selectedUnitNumber").value=unitNumber;
    }
    
    function getUnitNumber(unit) {
       // unitLabel is : "unitnumber : unit name"
        var unit_array=unit.split(":");        
    	return unit_array[0].trim();
    }


  function unitHierarchy() {

	var units = document.getElementById("units").value;
	var depth = document.getElementById("depth").value;
	var unit_0_array=units.split(";1;");
	var unit_array = unit_0_array.slice(0);

	// following is a temp solution to align the action buttons. The buttons is still not 100% align.  need a better solution

    var widthGap=18;
    var table_1 = "<table style=\"width:1080px\"><tr><td style=\"width:760px\">";


    var tree;    
        function buildTree() {
           //create a new tree:
           tree = new YAHOO.widget.TreeView("treeDiv1");
                      
           //get root node for tree:
           	var root = tree.getRoot();
           	// top node
			var tempNode = new YAHOO.widget.HTMLNode( table_1 + unit_0_array[0] + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(unit_0_array[0])+"</td></tr></table>", root, true, true);
           	tempNode.contentStyle="icon-page";
            unit_0_array[0] = tempNode; // save node for later parent reference
           	for (var i=1 ; i < unit_0_array.length;  i++) {
           	    // set up the rest of the nodes
           	    // data structure is like 1$-$IU-UNIV : unit name
           	    // "1$-$" indicated that it's parent is "1" in the array list
                var idx = unit_0_array[i].indexOf("$-$");
                var parentIdx=findIndex(unit_0_array[i].substring(0,idx), unit_array);
                var nodeLabel=unit_0_array[i].substring(idx+3, unit_0_array[i].length);

				var tempNode_1 = new YAHOO.widget.HTMLNode( "<table style=\"width:"+String(1080-(unit_0_array[parentIdx].depth+1)*widthGap)+"px\"><tr><td style=\"width:"+String(760-(unit_0_array[parentIdx].depth+1)*widthGap)+"px\">" + nodeLabel + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(nodeLabel)+"</td></tr></table>", unit_0_array[parentIdx], true, true);
				if (tempNode_1.depth == depth - 1) {
				    // currently initialized with 3 levels
				    // the last level should not be expanded, and also allow dynamic load
					tempNode_1.setDynamicLoad(loadUnitName,1);
					tempNode_1.expanded=false;
				}
           		tempNode_1.contentStyle="icon-page";
                unit_0_array[i]=tempNode_1;
           }
           
           //render tree with these toplevel nodes; all descendants of these nodes
           //will be generated as needed by the dynamic loader.
           tree.draw();
        }

	    function findIndex(parentIdx, unit_array) {
			for (var i = 1; i < unit_array.length; i++) {
				if (unit_array[i].indexOf("$-$" + parentIdx) > -1) {
					return i;
				}
			}
			return 0;
		}

		function loadUnitName(node) {
		   // The ajax code to load node dynamically.  so far it is working fine without the yui connection manager
				var dwrReply = {
					callback:function(data) {
						if ( data != null ) {
							var unitNum=0;
							var unit_array=data.split("#SEPARATOR#");   // see UnitServiceImpl.getSubUnitsForTreeView()
							while (unitNum < unit_array.length){
								var tempNode = new YAHOO.widget.HTMLNode( "<table style=\"width:"+String(1080-(node.depth+1)*widthGap)+"px\"><tr><td style=\"width:"+String(760-(node.depth+1)*widthGap)+"px\">" + unit_array[unitNum] + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(unit_array[unitNum])+"</td></tr></table>", node, false, true);
							   	tempNode.setDynamicLoad(loadUnitName,1);						
           					   	tempNode.contentStyle="icon-page";
				               	unitNum+=1;
			 				}
							
						}
						 node.loadComplete();
						
					},
					errorHandler:function( errorMessage ) {
						window.status = errorMessage;
					}
				};
				UnitService.getSubUnitsForTreeView(node.data,dwrReply);
		}


    return {        
        init: function() {
            buildTree();
        }
    }    
}
