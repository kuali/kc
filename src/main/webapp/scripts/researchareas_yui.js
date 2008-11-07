    
    function getResearchAreaCode(researchArea) {
       // unitLabel is : "unitnumber : unit name"
        var researchArea_array=researchArea.split(":");        
    	return researchArea_array[0].trim();
    }


  function researchArea() {

	var researchAreas = document.getElementById("researchAreas").value;
//	var depth = document.getElementById("depth").value;
	var unit_0_array=researchAreas.split(";1;");
	
	// following is a temp solution to align the action buttons. The buttons is still not 100% align.  need a better solution

        function buildTree() {
        	Dom = YAHOO.util.Dom,
			Event = YAHOO.util.Event; 
        
           //create a new tree:
           tree = new YAHOO.widget.TreeView("treeDiv1");
                      
           //get root node for tree:
           	var root = tree.getRoot();
           	// top node
    	    nodeKey=unit_0_array[0].substring(0,unit_0_array[0].indexOf(":")-1);
			var tempNode = new YAHOO.widget.HTMLNode( table_1 + unit_0_array[0] + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(unit_0_array[0])+"</td></tr></table>", root, true, true);
			//var tempNode = new YAHOO.widget.HTMLNode( "<td style=\"width:742px\">" + unit_0_array[0] + "</td> <td style=\"width:320px\">"+ setupMaintenanceButtons(unit_0_array[0])+"</td>", root, true, true);
			setDDsend();
           	tempNode.contentStyle="icon-page";
            unit_0_array[0] = tempNode; // save node for later parent reference
    	    //oTextNodeMap[nodeKey++] = tempNode;
    	    //alert(nodeKey)
    	    oTextNodeMap[nodeKey] = tempNode;
    	    tempNode.dynamicLoadComplete=true;
           	for (var i=1 ; i < unit_0_array.length;  i++) {
                var nodeLabel=unit_0_array[i];
    	    	nodeKey=unit_0_array[i].substring(0,unit_0_array[i].indexOf(":")-1);
				var tempNode_1 = new YAHOO.widget.HTMLNode( "<table style=\"width:"+String(1080-(unit_0_array[0].depth+1)*widthGap)+"px\"><tr><td style=\"width:"+String(760-(unit_0_array[0].depth+1)*widthGap)+"px\">" + nodeLabel + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(nodeLabel)+"</td></tr></table>", unit_0_array[0], true, true);
				//if (tempNode_1.depth == depth - 1) {
				    // currently initialized with 3 levels
				    // the last level should not be expanded, and also allow dynamic load
					tempNode_1.setDynamicLoad(loadNextRA,1);
					tempNode_1.expanded=false;
				//}
				setDDsend();
           		tempNode_1.contentStyle="icon-page";
                unit_0_array[i]=tempNode_1;		
                //alert(tempNode_1.getNodeHtml());	
                //alert(nodeKey)
    	        oTextNodeMap[nodeKey] = tempNode_1;
    	        //oTextNodeMap[nodeKey++] = tempNode_1;
           }
           //render tree with these toplevel nodes; all descendants of these nodes
           //will be generated as needed by the dynamic loader.
           tree.draw();
        }



    return {        
        init: function() {
            buildTree();
        }
    }    
    
}    // end researcharea

		function setDDsend() {
		   //alert('in setdd');
		   //var id = 'ygtvcontentel'+ddKey;
		   //alert('in setdd '+id);
		   //new DDSend('ygtvcontentel'+ddKey++);
		}

		function loadNextRA(node) {
		   // The ajax code to load node dynamically.  so far it is working fine without the yui connection manager
				var dwrReply = {
					callback:function(data) {
						if ( data != null ) {
						    //alert("data "+data+node.data)
						    
							var unitNum=0;
							var unit_array=data.split(",");
							while (unitNum < unit_array.length){
    	    					nodeKey=unit_array[unitNum].substring(0,unit_array[unitNum].indexOf(":")-1);
								var tempNode = new YAHOO.widget.HTMLNode( "<table style=\"width:"+String(1080-(node.depth+1)*widthGap)+"px\"><tr><td style=\"width:"+String(760-(node.depth+1)*widthGap)+"px\">" + unit_array[unitNum] + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(unit_array[unitNum])+"</td></tr></table>", node, false, true);
								//alert(tempNode)
								setDDsend();
							   	tempNode.setDynamicLoad(loadNextRA,1);						
           					   	tempNode.contentStyle="icon-page";
    	        				//oTextNodeMap[nodeKey++] = tempNode;
    	        				//alert(nodeKey)
    	                        oTextNodeMap[nodeKey] = tempNode;
				               	unitNum+=1;
			 				}
							
						} else {
							//alert ("data is null");
						}
						 //alert (node + " load complete ")
						 node.loadComplete();
						
					},
					errorHandler:function( errorMessage ) {
						window.status = errorMessage;
					}
				};
				ResearchAreasService.getSubResearchAreasForTreeView(getNodeCode(node),dwrReply);
		}



    function editNodeLabel(mapKey) {
		oCurrentTextNode=oTextNodeMap[mapKey];
		//if (!oCurrentTextNode.dynamicLoadComplete) {
		//    showWait();
        //    loadNextRA(oCurrentTextNode);
		//}
		var nodeDesc = getNodeDescription(oCurrentTextNode);
        var sLabel = window.prompt("Enter a new Description: ", nodeDesc);
		sLabel = sLabel.trim();
		if (sLabel.length > 50) {
		  sLabel = sLabel.substring(0,50);
		}

        var nodeHtml=oCurrentTextNode.data;
        if (sLabel && sLabel.length > 0 && sLabel!=nodeDesc) {
            //if (isUniqueGroupName(oCurrentTextNode,sLabel) == "true" ) {
	            nodeHtml = nodeHtml.replace(":"+nodeDesc+"<",":"+sLabel+"<");            
	            oCurrentTextNode.data=nodeHtml;
	            oCurrentTextNode.html=nodeHtml;
	            oCurrentTextNode.setHtml;
	            //oCurrentTextNode.description=sLabel;
	            oTextNodeMap[mapKey] = oCurrentTextNode;
	            tree.draw();
	           // changeGroupName(oCurrentTextNode, nodeDesc)
            //} else {
            //   alert("A group with name '"+sLabel+"' already exists for level "+ (oCurrentTextNode.depth+1));
            //}
        }
      }
    
    function getNodeDescription(node) {
       //alert ("getnodedesc "+node)
        var label = node.data;
        var startIdx = label.indexOf("px\">", label.indexOf("<tr>"));
        label = label.substring(startIdx + 4, label.indexOf("</td>")); 
        return label.substring(label.indexOf(":")+1)  // "code:" was stripped  
    }
    
    function getNodeCode(node) {
       //alert ("getnodedesc "+node)
        var label = node.data;
        var startIdx = label.indexOf("px\">", label.indexOf("<tr>"));
        label = label.substring(startIdx + 4, label.indexOf("</td>")); 
        return label.substring(0,label.indexOf(":")-1)  
    }

    function search() {
    
			  url=window.location.href
			  pathname=window.location.pathname
			  idx1=url.indexOf(pathname);
			  idx2=url.indexOf("/",idx1+1);
			  extractUrl=url.substr(0,idx2)
			  var winPop = window.open(extractUrl+"/researchAreaSearch.do?", "_blank", "width=800, height=600, scrollbars=yes");

    }

    function returnResearchArea(researchAreaCode) {
      // alert ("return " +researchAreaCode)
       				var dwrReply = {
					callback:function(data) {
						if ( data != null ) {
						    //alert (data)
							var unit_array=data.split(";1;");
							for (var i=0 ; i < unit_array.length ;  i++) {
							   //  alert("loop return node "+unit_array[i]+oTextNodeMap[unit_array[0]]+oTextNodeMap[unit_array[1]]+oTextNodeMap[unit_array[2]])
								//alert(unit_array[i])
								var tempNode = oTextNodeMap[unit_array[i]];
								if (tempNode == null) {
								    tempNode=getTempNode(unit_array[i]);
								}
								if (!tempNode.dynamicLoadComplete) {
								   //alert("load next "+tempNode)
								   loadNextRA(tempNode)
								   /** TODO : still has timing issue.  The node is not completely loading, then it already processing its child node **/
								   checkComplete(tempNode);	
								   //alert ("check complete"+tempNode);							   
								}
			 				}
						} else {
							//alert ("data is null");
						}
						node.loadComplete();
						
					},
					errorHandler:function( errorMessage ) {
						window.status = errorMessage;
					}
				};
				ResearchAreasService.getAscendantList(researchAreaCode,dwrReply);
       
    }
    
    function checkComplete(node) {
  		if (!node.dynamicLoadComplete) {
  		    //alert ("load not complete "+node);
			setTimeout("checkComplete("+node+")", 200);								   
  		}
    
    }
    
