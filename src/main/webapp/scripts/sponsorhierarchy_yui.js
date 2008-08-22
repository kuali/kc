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
	           		emptyNodes="((#0#))";
	           	}
	           	for (var i=0 ; i < sponsorHierarchy_array.length;  i++) {
					var tempNode = new YAHOO.widget.HTMLNode( table_1 + sponsorHierarchy_array[i] + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(sponsorHierarchy_array[i], root)+"</td></tr></table>", root, false, true);
	           		tempNode.contentStyle="icon-page";
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
        
    	return getNodeDescription(rootNode);
    }
    
    function getNodeDescription(node) {
       //alert ("getnodedesc "+node)
        var label = node.data;
        var startIdx = label.indexOf("px\">", label.indexOf("<tr>"));
        return label.substring(startIdx + 4, label.indexOf("</td>"));    
    }
    
    function setSponsorCode(sponsorCode) {   
    	document.getElementById("sponsorCode").value=sponsorCode;
    	//inquiryPop('org.kuali.kra.bo.Sponsor','sponsorCode:sponsorCode');
    }
    
          var newLabel;
    function addNode(mapKey) {

		oCurrentTextNode=oTextNodeMap[mapKey];
		showWait();
        if (!oCurrentTextNode.dynamicLoadComplete) {
               var temp = loadNextLevelSponsorHierarchy(oCurrentTextNode);
		}
        setTimeout("checkToAdd("+mapKey+")", 200);



     }

    function checkToAdd(mapKey) {
    
    	// this is timing issue, has to wait until dwr is complete, so this is may still has issue.
					oCurrentTextNode=oTextNodeMap[mapKey];
					var childrenNode= {};
					childrenNode=oCurrentTextNode.children;

					//alert (childrenNode[0].isLeaf+" ln "+leafNode);
					// check leafNode==false is not good
					//if (childrenNode.length == 0 || leafNode=="false" || !childrenNode[0].isLeaf) {
			if (oCurrentTextNode.dynamicLoadComplete) {		
					hideWait();
			
					if (childrenNode.length == 0 || !childrenNode[0].isLeaf)  {
					//alert (childrenNode[0]+childrenNode[0].isLeaf+" ln "+leafNode)
                    var sLabel = window.prompt("Enter a name for the new group: ", ""),
                        oChildNode;
					sLabel = sLabel.trim();
					if (sLabel.length > 50) {
					  sLabel = sLabel.substring(0,50);
					}
                    if (sLabel && sLabel.length > 0) {
             			if (childrenNode.length == 00 || (sLabel != getNodeDescription(childrenNode[0]) && isUniqueGroupName(childrenNode[0],sLabel) == "true")) {                       
							var oChildNode = new YAHOO.widget.HTMLNode( "<table style=\"width:"+String(1080-(oCurrentTextNode.depth+1)*widthGap)+"px\"><tr><td style=\"width:"+String(760-(oCurrentTextNode.depth+1)*widthGap)+"px\">" + sLabel + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(sLabel, oCurrentTextNode)+"</td></tr></table>", oCurrentTextNode, false, true);
               				oChildNode.contentStyle="icon-page";
               				oChildNode.setDynamicLoad(loadNextLevelSponsorHierarchy, 1); // need this. otherwise when 'add sponsor' the sponsor will not be displayed immediately.
               				oCurrentTextNode.refresh();
                        	oCurrentTextNode.expand();
           					actionList[nodeKey] = ":newLabel:"+sLabel;
           					        						
	            			updateEmptyNodes(oChildNode,"false");

                        	oTextNodeMap[nodeKey++] = oChildNode;
                        	tree.draw();
                        } else {
               				alert("A group with name '"+sLabel+"' already exists for level "+ (oCurrentTextNode.depth+2));
            			}

                    }
                    
                    } else {
                    	alert ("The group '"+getNodeDescription(oCurrentTextNode)+"' has sponsors assigned to it. Cannot create subgroups for this group.");
                    }
                    leafNode="false";
               } else {
                    setTimeout("checkToAdd("+mapKey+")", 200);
               }     
    }



    function editNodeLabel(mapKey) {
		oCurrentTextNode=oTextNodeMap[mapKey];
		if (!oCurrentTextNode.dynamicLoadComplete) {
		    showWait();
                    	loadNextLevelSponsorHierarchy(oCurrentTextNode);
		}
		var nodeDesc = getNodeDescription(oCurrentTextNode);
        var sLabel = window.prompt("Enter a new name for this group: ", nodeDesc);
		sLabel = sLabel.trim();
		if (sLabel.length > 50) {
		  sLabel = sLabel.substring(0,50);
		}

        var nodeHtml=oCurrentTextNode.data;
        if (sLabel && sLabel.length > 0 && sLabel!=nodeDesc) {
            //var retstr = isUniqueGroupName(oCurrentTextNode,sLabel);
            //alert (" unique "+retstr);
            if (isUniqueGroupName(oCurrentTextNode,sLabel) == "true" ) {
            nodeHtml = nodeHtml.replace(">"+nodeDesc+"<",">"+sLabel+"<");
            oCurrentTextNode.data=nodeHtml;
            oCurrentTextNode.html=nodeHtml;
            oCurrentTextNode.setHtml;
            oTextNodeMap[mapKey] = oCurrentTextNode;
            tree.draw();
            changeGroupName(oCurrentTextNode, nodeDesc)
            } else {
               alert("A group with name '"+sLabel+"' already exists for level "+ (oCurrentTextNode.depth+1));
            }
        }

      }
                
    

                /*
                    Deletes the TextNode that was the target of the "contextmenu"
                    event that triggered the display of the ContextMenu instance.
                */

      function isUniqueGroupName(node,sLabel) {
      
          var tempNode = node;
          while (tempNode.previousSibling != null) {
          	tempNode = tempNode.previousSibling;
          	//alert(sLabel +" =? " +getNodeDescription(tempNode))
          	if (sLabel == getNodeDescription(tempNode)) {
          	    return "false";
          	}
          }
          
          tempNode = node;
          while (tempNode.nextSibling != null) {
          	tempNode = tempNode.nextSibling;
          	if (sLabel == getNodeDescription(tempNode)) {
          	    return "false";
          	}
          }
          return "true";
                
      }

      function deleteNode(mapKey) {
      
			oCurrentTextNode=oTextNodeMap[mapKey];
			var msg;
			if (oCurrentTextNode.isLeaf) {
			  	msg = "Do you want to remove the sponsor '"+getNodeDescription(oCurrentTextNode)+"' from the hierarchy";
			} else {
			  	msg = "Do you want to remove the group '"+getNodeDescription(oCurrentTextNode)+"' and all its children from the hierarchy";
			}
        	var toDelete= confirm(msg);
        	if (toDelete== true) {
	            if (oCurrentTextNode.isLeaf) {                    
	                deleteSponsorHierarchy(oCurrentTextNode, "true");
	                removeFromSponsorList(oCurrentTextNode);
	            } else {
	                if (emptyNodes.indexOf("((#"+getNodeseq(oCurrentTextNode)+"#))") < 0) {
	                	deleteSponsorHierarchy(oCurrentTextNode, "false");
	                }
	            }
	            if (!oCurrentTextNode.isLeaf && oCurrentTextNode.nextSibling != null) {
	               updateMove(oCurrentTextNode.nextSibling);
	            }
	            updateEmptyNodes(oCurrentTextNode,"true");
	            tree.removeNode(oCurrentTextNode);
				//actionList[mapKey]=actionList[mapKey]+":delete:"
	            tree.draw();
            }

       }

      // IE7 does not accept 'swapNode' name ??
       function moveUp(mapKey1) {
            var node1 = oTextNodeMap[mapKey1];
            if (node1.previousSibling != null) {   
                 var node2 = node1.previousSibling;
				changeSortId(mapKey1,"true");
				setTimeout("changeSortId("+getNodeseq(node2)+",'false')", 1000)
                node1.insertBefore(node2);						
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
                    if (node1.nextSibling != null) {   
                      
                        var node2 = node1.nextSibling;
						changeSortId(mapKey1,"false");
						setTimeout("changeSortId("+getNodeseq(node2)+",'true')", 100)
						
						
						//changeSortId(node2,"true");
                         //alert("load compl "+node1.dynamicLoadComplete)
                        node2.insertBefore(node1);
                        tree.draw();
                 	} else {
                 		alert("This is the last in the group, so it can't be moved down");
                 	}
                   

     }


   
   function getMoveSeq(action, seq) {
       if (action == "moveUp") {
          seq = Number(seq) -1;
       } else {
          seq=Number(seq)+1;
       }
       return seq;   
   
   }
   
   function updateMove(node) {
   		changeSortId(getNodeseq(node),"true");
		//maintainActionList(getNodeseq(node), "moveUp", -1)
		if (node.nextSibling != null) {
			updateMove(node.nextSibling);
		}
   
   }

   function updateEmptyNodes(node, isDeleteNode) {
        //alert("empty in "+emptyNodes)
        var nodeSeq="((#"+getNodeseq(node)+"#))";
        var parentNodeSeq="((#"+getNodeseq(node.parent)+"#))"
		if (isDeleteNode == "true") {
			emptyNodes=emptyNodes.replace(nodeSeq, "");
			if (node.parent.children.length == 1) {
				emptyNodes=emptyNodes+parentNodeSeq;
			}
		} else {
			    if (!node.isLeaf) {
					emptyNodes=emptyNodes+nodeSeq;
			    }
				emptyNodes=emptyNodes.replace(parentNodeSeq, "");
		}
        //alert("empty out "+emptyNodes)
   
   }

   function okToSave() {
        //alert(emptyNodes+" "+emptyNodes.indexOf("((#"))
     	if (emptyNodes.indexOf("((#") >= 0) {
     	   alert ("Can't save hierarchy with empty group");
     	   return "false";
     	} else {
     		document.getElementById("sqlScripts").value=sqlScripts;
     	   return "true";
     	
     	}
   }
   
   function removeFromSponsorList(node) {
      var nodeDesc = getNodeDescription(node);
      sponsorCodeList=sponsorCodeList.replace(nodeDesc.substring(0,nodeDesc.indexOf(":"))+";","");
   }
   
   
   function getNodeseq(node) {
       return Number(node.toString().substring(node.toString().indexOf("(")+1,node.toString().length-1)) - 1;
   }
   
	function addSponsor(mapKey) {
		oCurrentTextNode=oTextNodeMap[mapKey];
		if (!oCurrentTextNode.dynamicLoadComplete) {
	       	loadNextLevelSponsorHierarchy(oCurrentTextNode);
		}
		setTimeout("checkToAddSponsor("+mapKey+")", 500);
		
	 }


    function checkToAddSponsor(mapKey) {
    
    	// this is timing issue, has to wait until dwr is complete, so this is may still has issue.
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
    		
			var dwrReply = {
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
				SponsorService.updateSponsorCodes(sponsorCodeList,dwrReply);
		
		
    
    }

    function loadNextLevelSponsorHierarchy(node) {
		   // The ajax code to load node dynamically.  so far it is working fine without the yui connection manager
				var dwrReply = {
					callback:function(data) {
						if ( data != null ) {
							var sponsorHierarchy_array=data.split(";1;");
							//alert(data)
							leafNode = "false";
							var startIdx = 0;
							if (sponsorHierarchy_array[0] == "((leafNodes))") {
								leafNode = "true";
								startIdx=1;
							}
							if (data != "") {
           					for (var i=startIdx ; i < sponsorHierarchy_array.length;  i++) {
           					    //alert(i)
								var tempNode = new YAHOO.widget.HTMLNode( "<table style=\"width:"+String(1080-(node.depth+1)*widthGap)+"px\"><tr><td style=\"width:"+String(760-(node.depth+1)*widthGap)+"px\">" + sponsorHierarchy_array[i] + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(sponsorHierarchy_array[i], node)+"</td></tr></table>", node, false, true);
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
				SponsorService.getSubSponsorHierarchiesForTreeView(hierarchyName, node.depth, getAscendants(node,"false") ,dwrReply);
	}


    	function changeGroupName(node, oldLabel) {
    		var sql = updatesql + "level"+node.depth+"='"+getNodeDescription(node)+"' "+getWhereClause(node.parent);
			sql = sql+" and level"+node.depth+"='"+oldLabel+"'";
    		sqlScripts = sqlScripts+sql+";1;";
		}

    	function changeSortId(nodeseq, moveFlag) {
			var node=oTextNodeMap[nodeseq]
		    var sortid ;
		    if (moveFlag == "true") {
		       sortid = "level"+node.depth+"_sortid - 1";
		    } else {
		       sortid = "level"+node.depth+"_sortid + 1";
		    }
    		var sql = updatesql + "level"+node.depth+"_sortid="+sortid+" "+getWhereClause(node);
    		sqlScripts = sqlScripts+sql+";1;";
		}

    	function deleteSponsorHierarchy(node, deleteSponsorFlag) {
				var sql ;
				if (deleteSponsorFlag == "true") {
				   	sql = deletesql+getWhereClause(node.parent)+ " and sponsor_code = '" + getNodeDescription(node).substring(0,getNodeDescription(node).indexOf(":"))+"'";
				} else {
					sql = deletesql+getWhereClause(node);
				}
				sqlScripts = sqlScripts+sql+";1;";
				
						   // The ajax code to load node dynamically.  so far it is working fine without the yui connection manager
				//alert("deletesponsorhierarchy");
				var dwrReply = {
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
				SponsorService.getSponsorCodes(getRootNode(node), node.depth, getAscendants(node, "false"),dwrReply);
				
				
		}

    
     function getWhereClause(node) {
     
        var whereClause=" where hierarchy_name = '"+hierarchyName+"'";
        var tempNode = node;
         while (tempNode.depth > 0) {
           whereClause = whereClause +" and level"+tempNode.depth+"='"+getNodeDescription(tempNode)+"'";
              tempNode=tempNode.parent;
         }
         return whereClause;
     }
     
     function getInsertClause(node) {
     
        var columns="hierarchy_name, sponsor_code, update_timestamp, update_user";
        // need to rework on real update_user
        var values="'"+hierarchyName+"','((sponsorcodeholder))', sysdate, 'quickstart'"
        var tempNode = node;
         while (tempNode.depth > 0) {
           columns = columns+",level"+tempNode.depth+",level"+tempNode.depth+"_sortid";
           values = values + ",'"+getNodeDescription(tempNode)+"',"+getSortId(tempNode);
           tempNode=tempNode.parent;
         }
         return "insert into sponsor_hierarchy ("+columns+") values("+values+")";
     }
     
     function getAscendants(node, includeSortid) {
            var ascendants ="";
            var tempNode = node;
            var sortid="";
            while (tempNode.depth > 0) {
                if (includeSortid == "true") {
                	sortid = "((#"+getSortId(tempNode)+"#))";
                }
                if (ascendants == "") {
                	ascendants = getNodeDescription(tempNode)+sortid;
                } else {
                	ascendants = getNodeDescription(tempNode)+sortid+";1;"+ascendants;                
                }
                tempNode=tempNode.parent;
            }
            return ascendants;
         
       }
         
       function getSortId(tempNode) {
            var sortid = Number(1);
            while (tempNode.previousSibling!=null) {
               sortid++;
               tempNode=tempNode.previousSibling;
            } 
            //alert("sortid "+sortid);
            return sortid;
       }
         
                  
		function returnSponsor(sponsors, mapKey){
			var sponsor_array=sponsors.split(";1;");
			//alert("sponsors : "+sponsors + " mapkey= "+mapKey)
			var oCurrentTextNode = oTextNodeMap[mapKey];
			var sqltemplate = getInsertClause(oCurrentTextNode);
			var newSponsors="";
			var sponsorid;
			var duplist="";
			var j = -1;
			for (var i=0 ; i < sponsor_array.length;  i++) {
				var sLabel = sponsor_array[i];
				sponsorid = sLabel.substring(0,sLabel.indexOf(":"));
				if (sponsorCodeList.indexOf(sponsorid) < 0) {
				var oChildNode = new YAHOO.widget.HTMLNode( "<table style=\"width:"+String(1080-(oCurrentTextNode.depth+1)*widthGap)+"px\"><tr><td style=\"width:"+String(760-(oCurrentTextNode.depth+1)*widthGap)+"px\">" + sponsor_array[i] + "</td><td style=\"width:320px\">"+ setupMaintenanceButtons(sponsor_array[i], oCurrentTextNode)+"</td></tr></table>", oCurrentTextNode, false, true);
	         			oChildNode.contentStyle="icon-page";
	
	            oCurrentTextNode.refresh();
	            oCurrentTextNode.expand();
				oChildNode.isLeaf="true";
				//actionList[nodeKey] = ":newLabel:"+sponsor_array[i];           									
	            oTextNodeMap[nodeKey++] = oChildNode;
	            var sql = sqltemplate.replace("((sponsorcodeholder))",sponsorid);
	            sqlScripts = sqlScripts+sql+";1;";
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
	         tree.draw();			
		}
	

	function showWait() {
		document.getElementById("wait").style.visibility="visible";
	}

	function hideWait() {
		document.getElementById("wait").style.visibility="hidden";
	}