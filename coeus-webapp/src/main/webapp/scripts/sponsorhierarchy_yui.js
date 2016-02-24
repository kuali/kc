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
var dwrReply = {
	callback:function(data) {
		hideWait();
	},
	errorHandler:function( errorMessage ) {
		if (errorMessage == null || erroreMessage == 'null') {
			alert("Unable to make changes due to an unknown error communicating with the server. Please save or cancel your changes and try again.")
		} else {
			alert("Unable to make changes due to the following error: " + errorMessage + ". Please save or cancel your changes and try again.");
		}
		hideWait();
	}
};

function sponsorHierarchy() {

		//var topSponsorHierarchies = document.getElementById("topSponsorHierarchies").value;
		//var topSponsorHierarchies = document.getElementById("selectedSponsorHierarchy").value;
		var topSponsorHierarchies = document.getElementById("hierarchyName").value;
		var sponsorHierarchy_array=topSponsorHierarchies.split(";1;");
		var actionSelected=document.getElementById("actionSelected").value
		sponsorHierarchyToSave=sponsorHierarchy_array[0]

	        function buildTree() {
	           //create a new tree:
	           tree = new YAHOO.widget.TreeView("treeDiv1");
	                      
	           //get root node for tree:
	           	var root = tree.getRoot();
	           	// root.depth is -1
	           	if (actionSelected == "new") {
	           		emptyNodes="((`0`))";
	           	}
	           	for (var i=0 ; i < sponsorHierarchy_array.length;  i++) {
					var tempNode = new SHNode( table_1 + sponsorHierarchy_array[i] + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(sponsorHierarchy_array[i], root)+"</td></tr></table>", root, false, true, false, sponsorHierarchy_array[i]);
	           		tempNode.contentStyle="icon-page";
	           		//tempNode.isVirtualNode=false;
	           		//tempNode.description=sponsorHierarchy_array[i];
	           		//if (actionSelected == "maint") {
	           			tempNode.setDynamicLoad(loadNextLevelSponsorHierarchy);
	           		//}
	           		//alert(tempNode);
	           		oTextNodeMap[nodeKey++] = tempNode;
	           		
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
    
	} // end sponsorHierarchy
	
	
	
	
	
	function getRootNode (node) {
       	var rootNode = node
        while (rootNode.depth > 0) {
            //alert(rootNode)
           	rootNode =rootNode.parent;
        }
        
    	return rootNode.description;
    }

    
    function setSponsorCode(sponsorCode) {   
    	document.getElementById("sponsorCode").value=sponsorCode;
    	//inquiryPop('org.kuali.coeus.common.framework.sponsor.Sponsor','sponsorCode:sponsorCode');
    }
    
    function addNode(mapKey) {
		oCurrentTextNode=oTextNodeMap[mapKey];
		showWait();
        if (!oCurrentTextNode.dynamicLoadComplete) {
          loadNextLevelSponsorHierarchy(oCurrentTextNode);
		}
        checkToAdd(mapKey)
     }

    function checkToAdd(mapKey) {
    
		oCurrentTextNode=oTextNodeMap[mapKey];
		var childrenNode= {};
		childrenNode=oCurrentTextNode.children;
		if (!oCurrentTextNode.dynamicLoadComplete) {
			loadNextLevelSponsorHierarchy(oCurrentTextNode);
		}
		hideWait();
		
		if (childrenNode.length == 0 || !childrenNode[0].isLeaf)  {
		//alert (childrenNode[0]+childrenNode[0].isLeaf+" ln "+leafNode)
        var sLabel = window.prompt("Enter a name for the new group: ", "");
        while (sLabel.indexOf(":") != -1) {
        	sLabel = window.prompt("The new group name cannot contain ':'. \n\r Enter a name for the new group: ", sLabel);
        }
		sLabel = sLabel.trim();
		if (sLabel.length > 50) {
		  sLabel = sLabel.substring(0,50);
		}
        if (sLabel && sLabel.length > 0) {
 			if (childrenNode.length == 00 || (sLabel != childrenNode[0].description && isUniqueGroupName(childrenNode[0],sLabel) == "true")) {                       
				var oChildNode = new SHNode( "<table style=\"width:"+String(1080-(oCurrentTextNode.depth+1)*widthGap)+"px\"><tr><td style=\"width:"+String(760-(oCurrentTextNode.depth+1)*widthGap)+"px\">" + sLabel + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(sLabel, oCurrentTextNode)+"</td></tr></table>", oCurrentTextNode, false, true, false, sLabel);
   				oChildNode.contentStyle="icon-page";
   				oChildNode.setDynamicLoad(loadNextLevelSponsorHierarchy, 1); // need this. otherwise when 'add sponsor' the sponsor will not be displayed immediately.
   				oCurrentTextNode.refresh();
            	oCurrentTextNode.expand();
				        						
    			updateEmptyNodes(oChildNode,"false");

            	oTextNodeMap[nodeKey++] = oChildNode;
            	tree.draw();
            	oChildNode.loadComplete();
            	
            } else {
   				alert("A group with name '"+sLabel+"' already exists for level "+ (oCurrentTextNode.depth+2));
			}

        }
        
        } else {
        	alert ("The group '"+oCurrentTextNode.description+"' has sponsors assigned to it. Cannot create subgroups for this group.");
        }
        leafNode="false";
    }



    function editNodeLabel(mapKey) {
		oCurrentTextNode=oTextNodeMap[mapKey];
		if (!oCurrentTextNode.dynamicLoadComplete) {
		    showWait();
                    	loadNextLevelSponsorHierarchy(oCurrentTextNode);
		}
		var nodeDesc = oCurrentTextNode.description;
        var sLabel = window.prompt("Enter a new name for this group: ", nodeDesc);
        while (sLabel.indexOf(":") != -1) {
        	sLabel = window.prompt("The new group name cannot contain ':'. \n\r Enter a new name for this group: ", sLabel);
        }
		sLabel = sLabel.trim();
		if (sLabel.length > 50) {
		  sLabel = sLabel.substring(0,50);
		}

        var nodeHtml=oCurrentTextNode.data;
        if (sLabel && sLabel.length > 0 && sLabel!=nodeDesc) {
            if (isUniqueGroupName(oCurrentTextNode,sLabel) == "true" ) {
            nodeHtml = nodeHtml.replace(">"+nodeDesc+"<",">"+sLabel+"<");            
            var subNode2 = oCurrentTextNode.nextSibling;
            if (subNode2 != null && subNode2.isVirtualNode) {
            	nodeHtml = nodeHtml.replace(">"+nodeDesc+" (",">"+sLabel+" (");            
            }
            oCurrentTextNode.data=nodeHtml;
            oCurrentTextNode.html=nodeHtml;
            oCurrentTextNode.setHtml;
            oCurrentTextNode.description=sLabel;
            oTextNodeMap[mapKey] = oCurrentTextNode;
            // subgroups
            
            var subNode2 = oCurrentTextNode.nextSibling;
            var curNode = oCurrentTextNode;
            while (subNode2 != null && subNode2.isVirtualNode) {
                  // alert(subNode2.data);
                    		// find subnode node to change label too
                   	var nextNode = subNode2.nextSibling;
                   	nodeHtml=subNode2.data;
                   	nodeHtml = nodeHtml.replace(">"+nodeDesc+" (",">"+sLabel+" (");
            		subNode2.data=nodeHtml;
            		subNode2.html=nodeHtml;
            		subNode2.setHtml;
            		subNode2.description=subNode2.description.replace(nodeDesc,sLabel);
            		
            		oTextNodeMap[getNodeseq(subNode2)] = subNode2;
                   //alert(getNodeseq(subNode2) +" | "+subNode2.data);

                   curNode = subNode2;
                   subNode2 = nextNode;
            }
            
            
            tree.draw();
            changeGroupName(oCurrentTextNode, nodeDesc)
            } else {
               alert("A group with name '"+sLabel+"' already exists for level "+ (oCurrentTextNode.depth+1));
            }
        }

      }
                
    

      function isUniqueGroupName(node,sLabel) {
      
          var tempNode = node;
          while (tempNode.previousSibling != null) {
          	tempNode = tempNode.previousSibling;
          	//alert(sLabel +" =? " +getNodeDescription(tempNode))
          	if (sLabel == tempNode.description) {
          	    return "false";
          	}
          }
          
          tempNode = node;
          while (tempNode.nextSibling != null) {
          	tempNode = tempNode.nextSibling;
          	if (sLabel == tempNode.description) {
          	    return "false";
          	}
          }
          return "true";
                
      }

      function deleteNode(mapKey) {
      
			oCurrentTextNode=oTextNodeMap[mapKey];
			var msg;
			if (oCurrentTextNode.isLeaf) {
			  	msg = "Do you want to remove the sponsor '"+oCurrentTextNode.description+"' from the hierarchy";
			} else {
			  	msg = "Do you want to remove the group '"+oCurrentTextNode.description+"' and all its children from the hierarchy";
			}
        	var toDelete= confirm(msg);
        	if (toDelete== true) {
	            if (oCurrentTextNode.isLeaf) {                    
	                deleteSponsorHierarchy(oCurrentTextNode, "true");
	                removeFromSponsorList(oCurrentTextNode);
	            } else {
	                if (emptyNodes.indexOf("((`"+getNodeseq(oCurrentTextNode)+"`))") < 0) {
	                	deleteSponsorHierarchy(oCurrentTextNode, "false");
	                }
	            }
	            if (!oCurrentTextNode.isLeaf && oCurrentTextNode.nextSibling != null) {
	               var nextNode = oCurrentTextNode.nextSibling;
	               while (nextNode.isVirtualNode) {
	                   // also delete subgroups
	                   var tnode = nextNode;
	                   nextNode = nextNode.nextSibling;
	                   tree.removeNode(tnode);
	               }
	               updateMove(nextNode);
	            }
	            updateEmptyNodes(oCurrentTextNode,"true");
	            var parentNode = oCurrentTextNode.parent;
	            tree.removeNode(oCurrentTextNode);
	            if (parentNode != null && (parentNode.isVirtualNode || (parentNode.nextSibling != null && parentNode.nextSibling.isVirtualNode)) && parentNode.children.length == 0) {
	               updateDescription(parentNode);
	               if (!parentNode.isVirtualNode) {
	               	  parentNode.nextSibling.description = parentNode.description;
	                  parentNode.nextSibling.isVirtualNode = false;
	               }   	                  
	               emptyNodes=emptyNodes.replace("((`"+getNodeseq(parentNode)+"`))", "");
	               tree.removeNode(parentNode);	
	            }
				//actionList[mapKey]=actionList[mapKey]+":delete:"
	            tree.draw();
            }

       }

       function moveUp(mapKey1) {
            var node1 = oTextNodeMap[mapKey1];
            var node2 = node1.previousSibling;
            if (node1.previousSibling != null) {   
                while (node2 != null && node2.isVirtualNode) {
                     node2 = node2.previousSibling;
                }

           }
                     
           if (node2 != null) {  
                
				changeSortId(mapKey1,"true");
				changeSortId(getNodeseq(node2),"false");
				var subNode1  = node1.nextSibling;
                node1.insertBefore(node2);
                var curNode = node1;
                while (subNode1 != null && subNode1.isVirtualNode) {
                    // find subnode node to move up too
                    var nextNode = subNode1.nextSibling;
                    subNode1.insertAfter(curNode);
                    curNode = subNode1;
                     subNode1 = nextNode;
                }
                						
                tree.draw();
             } else {
               		alert("This is the first in the group, and it can't be moved up");
             }

       }

      function moveDown(mapKey1) {
            var mapKey2=3;
    
                        var node1 = oTextNodeMap[mapKey1];
                        //alert("prev "+node1.previousSibling);
                        //alert("next "+node1.nextSibling);
                    var node2 = node1.nextSibling;
                    if (node1.nextSibling != null) {   
                      
                        while (node2 != null && node2.isVirtualNode) {
                    // find the real node
                     		node2 = node2.nextSibling;
                		}
                     }
                     
                     if (node2 != null) {  
						changeSortId(mapKey1,"false");
						changeSortId(getNodeseq(node2),"true");
                        var subNode2 = node2.nextSibling;
                        node2.insertBefore(node1);
                        
                        var curNode = node2;
                		while (subNode2 != null && subNode2.isVirtualNode) {
                    		// find subnode node to move up too
                    		var nextNode = subNode2.nextSibling;
                    		subNode2.insertAfter(curNode);
                    		curNode = subNode2;
                     		subNode2 = nextNode;
                		}
                        
                        
                        tree.draw();
                 	} else {
                 		alert("This is the last in the group, so it can't be moved down");
                 	}
                   

     }

   
   function updateMove(node) {
        if (!node.isVirtualNode) {
   			changeSortId(getNodeseq(node),"true");
   		}
		if (node.nextSibling != null) {
			updateMove(node.nextSibling);
		}
   
        
   }

   function updateEmptyNodes(node, isDeleteNode) {
        var nodeSeq="((`"+getNodeseq(node)+"`))";
        var parentNodeSeq="((`"+getNodeseq(node.parent)+"`))";
        //alert(emptyNodes)
		if (isDeleteNode == "true") {
			emptyNodes=emptyNodes.replace(nodeSeq, "");
			if (node.parent.children.length == 1) {
				emptyNodes=emptyNodes+parentNodeSeq;
			}
		} else {
			    if (!node.isLeaf) {
					emptyNodes=emptyNodes+nodeSeq;
			    }
			    //alert("replace "+"((`"+parentNodeSeq+"`))")
				emptyNodes=emptyNodes.replace(parentNodeSeq, "");
		}
        //alert(emptyNodes)
   
   }

   function okToSave() {
     	if (emptyNodes.indexOf("((`") >= 0) {
     	  // alert(emptyNodes);
     	   alert ("Can't save hierarchy with empty group.");
     	   return false;
     	} else if (waitCount > 0) {
     		alert ("Can't save hierarchy with waiting transactions.");
     		return false;
     	} else {
     	   return true;
     	}
   }
   
   function removeFromSponsorList(node) {
      var nodeDesc = node.description;
      sponsorCodeList=sponsorCodeList.replace(nodeDesc.substring(0,nodeDesc.indexOf(":"))+";","");
   }
   
   
   function getNodeseq(node) {
       return Number(node.toString().substring(node.toString().indexOf("(")+1,node.toString().length-1)) - 1;
   }
   
	function addSponsor(mapKey) {
		oCurrentTextNode=oTextNodeMap[mapKey];
		if (!oCurrentTextNode.dynamicLoadComplete) {
		    if (!oCurrentTextNode.isVirtualNode) {
		      // alert("not a subgrp")
	       	   loadNextLevelSponsorHierarchy(oCurrentTextNode);
	       	} else {
	       	  //alert("subgrp")
	       	   loadNextLevelSH(oCurrentTextNode);	       	
	       	}
		}
		checkToAddSponsor(mapKey);
		
	 }


    function checkToAddSponsor(mapKey) {
    
		oCurrentTextNode=oTextNodeMap[mapKey];
		var childrenNode= {};
		childrenNode=oCurrentTextNode.children;

		//alert (childrenNode[0].isLeaf+" ln "+leafNode);
		if (childrenNode.length == 0 || childrenNode[0].isLeaf) {
		    updateSponsorCodes();
			  url=window.location.href
			  pathname=window.location.pathname
			  idx1=url.indexOf(pathname);
			  idx2=url.indexOf("/",idx1+1);
			  extractUrl=url.substr(0,idx2)
			  var winPop = window.open(extractUrl+"/sponsorLookup.do?mapKey="+mapKey, "_blank", "width=800, height=600, scrollbars=yes");
         } else {
         	alert ("This node has sub sponsor group; can't add sponsors ");
         }

    }

    function updateSponsorCodes() {
    		
			var customReply = {
					callback:function(data) {
						if ( data != null ) {
						//alert(sponsorCodeList.length +"-"+data)
						} else {
							//alert ("data is null");
						}
						 node.loadComplete();
						
					},
					errorHandler:function( errorMessage ) {
						window.status = errorMessage;
					}
				};
        SponsorHierarchyMaintenanceService.updateSponsorCodes(sponsorCodeList,customReply);
    
    }
    
    function insertSponsors(node, sponsorCodes) {
    	var levels = new Array(10);
    	var sortIds = new Array(10);
        var tempNode = node;
        while (tempNode.isVirtualNode) {
           tempNode = tempNode.previousSibling;
        }
	    while (tempNode.depth > 0) {
	      levels[tempNode.depth-1] = tempNode.description;
	      sortIds[tempNode.depth-1] = getSortId(tempNode);
	      tempNode = tempNode.parent;
	    }
	    showWait();
        SponsorHierarchyMaintenanceService.insertSponsor(hierarchyName, sponsorCodes, levels, sortIds, dwrReply);
    }

    function loadNextLevelSponsorHierarchy(node) {
		   // The ajax code to load node dynamically.  so far it is working fine without the yui connection manager
				var customReply = {
					callback:function(data) {
						if ( data != null ) {
						    var group_array = data.split("#1#");
							//var sponsorHierarchy_array=data.split(";1;");
							var sponsorHierarchy_array=group_array[0].split(";1;");
							//alert(data)
							leafNode = "false";
							var startIdx = 0;
							if (sponsorHierarchy_array[0] == "((leafNodes))") {
								leafNode = "true";
								startIdx=1;
							}
							if (data != "") {
							    showWait();
	           					for (var i=startIdx ; i < sponsorHierarchy_array.length;  i++) {
									var tempNode = new SHNode( "<table style=\"width:"+String(1080-(node.depth+1)*widthGap)+"px\"><tr><td style=\"width:"+String(760-(node.depth+1)*widthGap)+"px\">" + sponsorHierarchy_array[i] + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(sponsorHierarchy_array[i]+ " (1 - " + numberPerGroup+ ")", node)+"</td></tr></table>", node, false, true, false, sponsorHierarchy_array[i]);
	               					tempNode.contentStyle="icon-page";
	               					if (leafNode == "false") {
	           							tempNode.setDynamicLoad(loadNextLevelSponsorHierarchy, 1);
	           						} else {
	           							tempNode.isLeaf="true";
	           						}
	           						oTextNodeMap[nodeKey++] = tempNode;
	           						
								}
							    if (group_array.length > 1) {
							        var nodeHtml=node.data;							    
						            nodeHtml = nodeHtml.replace(">"+node.description+"<",">"+node.description+" (1 - "+numberPerGroup+" )<");
						            node.data=nodeHtml;
						            node.html=nodeHtml;
						            node.setHtml;
							    }
							
								for (var i=1 ; i < group_array.length;  i++) {
									var tempNode = new SHNode( "<table style=\"width:"+String(1080-(node.depth+1)*widthGap)+"px\"><tr><td style=\"width:"+String(760-(node.depth+1)*widthGap)+"px\">" + node.description + " ("+ (i*numberPerGroup+1) +  " - " + ((i+1)*numberPerGroup)+ ")" +
													          "</td><td style=\"width:320px\"><INPUT TYPE=\"button\" SRC=\"button.gif\" VALUE=\"Add Sponsor\" ALT=\"Add Sponsor\" NAME=\"addsponsor\" onClick=\"addSponsor("+nodeKey+");return false;\" > </td></tr></table>" 
                                                              , node.parent, false, true, true, node.description + " ("+ (i*numberPerGroup+1) +  " - " + ((i+1)*numberPerGroup)+ ")");
									// "1" will show leaf node without "+" icon
									//alert("set up" + tempNode.data)
	               					tempNode.contentStyle="icon-page";
	           							tempNode.setDynamicLoad(loadNextLevelSH, 1);
	           						if (i == 1) {
	           							tempNode.insertAfter(node);
	           						} else {
	           							tempNode.insertAfter(oTextNodeMap[nodeKey - 1]);	           						
	           						}
	           						if (startIdx == 1) {
	           						    //alert("node key"+nodeKey)
	           							subgroup[nodeKey] = "((leafNodes));1;"+group_array[i];
	           						} else {
	           							subgroup[nodeKey] = group_array[i];
	           						}
	           						
	           						var idx = nodeKey;
	           						idx=idx+'';
	           						subgroupNodes=subgroupNodes+idx +";";
	           						
	           						oTextNodeMap[nodeKey++] = tempNode;
	           						
	           						//alert(tempNode.getNodeHtml())
								}
							 
							     if (group_array.length > 1) {
							            tree.draw();
							     }
							
							
							}
						} else {
							//alert ("data is null");
						}
						 node.loadComplete();
						hideWait();
					},
					errorHandler:function( errorMessage ) {
						window.status = errorMessage;
					}
				};
        SponsorHierarchyMaintenanceService.getSubSponsorHierarchiesForTreeView(hierarchyName, node.depth, getAscendants(node,"false") ,customReply);
	}

    
    function loadNextLevelSH(node) {
	// load the subgroups that is already retrieved from DB
		var sponsorHierarchy_array=subgroup[getNodeseq(node)].split(";1;");
		leafNode = "false";
		var startIdx = 0;
		//alert (sponsorHierarchy_array.length);
		if (sponsorHierarchy_array[0] == "((leafNodes))") {
			leafNode = "true";
			startIdx=1;
		}
		showWait();
        for (var i=startIdx ; i < sponsorHierarchy_array.length;  i++) {
       					    //alert(i)
				var tempNode = new SHNode( "<table style=\"width:"+String(1080-(node.depth+1)*widthGap)+"px\"><tr><td style=\"width:"+String(760-(node.depth+1)*widthGap)+"px\">" + sponsorHierarchy_array[i] + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(sponsorHierarchy_array[i], node)+"</td></tr></table>", node, false, true, false, sponsorHierarchy_array[i]);
				// "1" will show leaf node without "+" icon
				//alert("set up" + tempNode.data)
         					tempNode.contentStyle="icon-page";
				//actionList[nodeKey] = ":existLabel:"+sponsorHierarchy_array[i];           						
	 					if (leafNode == "false") {
					tempNode.setDynamicLoad(loadNextLevelSponsorHierarchy, 1);
				} else {
					//actionList[nodeKey] = actionList[nodeKey]+":leaf:";
					tempNode.isLeaf="true";
				}
				oTextNodeMap[nodeKey++] = tempNode;
				
				//alert(tempNode.getNodeHtml())
		}
		node.loadComplete();
		hideWait();
	

    }
    
    	function changeGroupName(node, oldLabel) {
    		showWait();
            SponsorHierarchyMaintenanceService.updateGroupName(hierarchyName, node.depth, oldLabel, node.description, getLevelArray(node.parent), hideWait);
		}

    	function changeSortId(nodeseq, moveFlag) {
			var node=oTextNodeMap[nodeseq];
			showWait();
            SponsorHierarchyMaintenanceService.changeSponsorSortOrder(hierarchyName, node.depth, moveFlag, getLevelArray(node), dwrReply);
		}
    	
    	function getLevelArray(node) {
			var levels = new Array(10);
			var tempNode = node;
            while (tempNode.isVirtualNode) {
                tempNode = tempNode.previousSibling;
            }
            while (tempNode.depth > 0) {
            	levels[tempNode.depth-1] = tempNode.description;
                tempNode=tempNode.parent;
            }
            return levels;
    	}

    	function deleteSponsorHierarchy(node, deleteSponsorFlag) {
				var sql ;
				showWait();
				if (deleteSponsorFlag == "true") {
					var sponsorCode = node.description.substring(0,node.description.indexOf(":"));
                    SponsorHierarchyMaintenanceService.deleteSponsor(hierarchyName, sponsorCode, getLevelArray(node.parent), dwrReply);
				} else {
                    SponsorHierarchyMaintenanceService.deleteSponsor(hierarchyName, null, getLevelArray(node), dwrReply);
				}
				
				// The ajax code to load node dynamically.  so far it is working fine without the yui connection manager
				//alert("deletesponsorhierarchy");
				var customReply = {
					callback:function(data) {
						if ( data != null ) {
						//alert(sponsorCodeList.length +"-"+data)
							var sponsorCode_array=data.split(";");
							for (var i=0 ; i < sponsorCode_array.length;  i++) {
							      sponsorCodeList=sponsorCodeList.replace(sponsorCode_array[i]+";","");
							}
							//alert(sponsorCodeList.length)
							
						} else {
							//alert ("data is null");
						}
						 node.loadComplete();
						
					},
					errorHandler:function( errorMessage ) {
						window.status = errorMessage;
					}
				};
            SponsorHierarchyMaintenanceService.getSponsorCodes(getRootNode(node), node.depth, getAscendants(node, "false"),customReply);
		}
     

     
     function getAscendants(node, includeSortid) {
            var ascendants ="";
            var tempNode = node;
            var sortid="";
            while (tempNode.depth > 0) {
                if (includeSortid == "true") {
                	sortid = "((`"+getSortId(tempNode)+"`))";
                }
                if (ascendants == "") {
                	ascendants = tempNode.description+sortid;
                } else {
                	ascendants = tempNode.description+sortid+";1;"+ascendants;                
                }
                tempNode=tempNode.parent;
            }
            return ascendants;
         
       }
         
       function getSortId(tempNode) {
            var sortid = Number(1);
            while (tempNode.previousSibling!=null) {
               if (!tempNode.previousSibling.isVirtualNode) {
               	sortid++;
               }
               tempNode=tempNode.previousSibling;
            } 
            //alert("sortid "+sortid);
            return sortid;
       }
         
                  
		function returnSponsor(sponsors, mapKey){
			var sponsor_array=sponsors.split(";1;");
			//alert("sponsors : "+sponsors + " mapkey= "+mapKey)
			var oCurrentTextNode = oTextNodeMap[mapKey];
			var newSponsors="";
			var sponsorid;
			var duplist="";
			var j = -1;
			var insertSponsorCodes = new Array();
			for (var i=0 ; i < sponsor_array.length;  i++) {
				var sLabel = sponsor_array[i];
				sponsorid = sLabel.substring(0,sLabel.indexOf(":"));
				if (sponsorCodeList.indexOf(sponsorid) < 0) {
				var oChildNode = new SHNode( "<table style=\"width:"+String(1080-(oCurrentTextNode.depth+1)*widthGap)+"px\"><tr><td style=\"width:"+String(760-(oCurrentTextNode.depth+1)*widthGap)+"px\">" + sponsor_array[i] + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(sponsor_array[i], oCurrentTextNode)+"</td></tr></table>", oCurrentTextNode, false, true, false, sponsor_array[i]);
	         			oChildNode.contentStyle="icon-page";
	
	            //oCurrentTextNode.refresh();
	            //oCurrentTextNode.expand();
				oChildNode.isLeaf="true";
	            oTextNodeMap[nodeKey++] = oChildNode;
	            insertSponsorCodes.push(sponsorid);
	            	            
	            if (j == -1) {
	                newSponsors=sLabel;
	            	updateEmptyNodes(oChildNode, "false");
	            	j = i;
	            } else {
	                newSponsors=newSponsors+";1;"+sLabel;	               
	            }
	            sponsorCodeList=sponsorCodeList+sponsorid+";";
	            } else {
	               if (duplist == "") {
	                  duplist = sponsorid;
	               } else {
	               	duplist = duplist+","+sponsorid;
	               }
	            }
	         }
	         
	         if (duplist.length > 0) {
	         	alert ("Duplicate sponsor(s) "+duplist+" are not added");
	         }
	         
	         insertSponsors(oCurrentTextNode, insertSponsorCodes);
	         
	         tree.draw();			
		}
	
	function updateDescription(node) {	  
	
		    var nodeHtml=node.data;		
		    var savedHtml = nodeHtml;
		    var desc = node.description;
		    var saveDesc = desc;
		    var nodeSeq=getNodeseq(node);					    
            var tempNode=node.nextSibling;
            while (tempNode != null && tempNode.isVirtualNode) {
                savedHtml = tempNode.data;
               // alert(nodeHtml+"(addNode"+nodeSeq+")"+"(addNode"+getNodeseq(tempNode)+")");
                nodeHtml = nodeHtml.replace("addNode("+nodeSeq+")","addNode("+getNodeseq(tempNode)+")");
                nodeHtml = nodeHtml.replace("editNodeLabel("+nodeSeq+")","editNodeLabel("+getNodeseq(tempNode)+")");
                nodeHtml = nodeHtml.replace("addSponsor("+nodeSeq+")","addSponsor("+getNodeseq(tempNode)+")");
                nodeHtml = nodeHtml.replace("moveUp("+nodeSeq+")","moveUp("+getNodeseq(tempNode)+")");
                nodeHtml = nodeHtml.replace("moveDown("+nodeSeq+")","moveDown("+getNodeseq(tempNode)+")");
                nodeHtml = nodeHtml.replace("deleteNode("+nodeSeq+")","deleteNode("+getNodeseq(tempNode)+")");
	            tempNode.data=nodeHtml;
	            saveDesc = tempNode.description;
	            tempNode.description = desc;
	           //      alert(nodeHtml);
	            tempNode.html=nodeHtml;
	            tempNode.setHtml;
	            nodeHtml = savedHtml;
	            desc = saveDesc;
	            nodeSeq = getNodeseq(tempNode);
	            tempNode = tempNode.nextSibling;
            }
	
	
	}
	

	var waitCount = 0;
	function showWait() {
		waitCount++;
		document.getElementById("wait").style.visibility="visible";
	}

	function hideWait() {
		waitCount--;
		if (waitCount <= 0) 
			document.getElementById("wait").style.visibility="hidden";
	}
