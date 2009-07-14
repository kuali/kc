  function getQuestionNew(description, qtypeid, vers, childNode) {
  
     //alert("getquestionnew");
     var qnaireid =  "qnaireid"+i
     var question = $('<li class="closed"></li>').attr("id", qnaireid);
     var divId = "listcontent"+i;
  
     var div62 = $('<div/>');
     var linktmp = $('<a style="margin-left:2px;"></a>').attr("id","listcontrol"+i).html(description);
     linktmp.click(
					function()
					{
                        $(".hierarchydetail:not(#"+divId+")").slideUp(300);
					
						$("#"+divId).slideToggle(300);
					}
	 );
     linktmp.appendTo(div62);
     
     var div64 = $(' <div class="hierarchydetail" style="margin-top:2px;">').attr("id",divId);
     var tbl70 = $('<table width="100%" cellpadding="0" cellspacing="0" class="subelement" />');
     getMaintTableHeader(description, vers).appendTo(tbl70);
     
     
     tbodytmp = $('<tbody/>');
     var tr1 = $('<tr></tr>');
     var td1 = $('<td class="subelementcontent"></td>');

     //getAddQuestionRow().appendTo(td1);
     getQuestionActionSubTable(qnaireid).appendTo(td1);
     getRequirementDisplayTable().appendTo(td1);
                                                                                 
     var div192 = $('<div></div>').attr("id","HSReqdiv"+i);
     if (childNode == 'true') {
         //alert("childnode = "+childNode);
         var tbl196 = $('<table cellpadding="0" cellspacing="0" class="elementtable" style="width:100%; border-top:none;"></table>');
         getAddRequirementRow().appendTo(tbl196);
         tbl196.appendTo(div192);
                                                  
         var tbl266 = $('<table cellpadding="0" cellspacing="0" class="elementtable" style="width:100%; border-top:none;"></table>').attr("id","tbl266-"+i).html('<tbody/>');
         //getRequirementDeleteRow("1", "Current Requirements:", "Matches text", "Yes").appendTo(tbl266);
         //getRequirementDeleteRow("2", "(or)", "Matches text", "Sometimes").appendTo(tbl266);
         tbl266.appendTo(div192);
     } else {
         div192.html("There can be no Requirements for Display on root-level questions. ");
     }
     div192.appendTo(td1);
                                                                                             
     var tbl325 = $('<table width="100%" cellpadding="0" cellspacing="0" style="border-top:#E4E3E4 solid 1px;">');
     trtmp = $('<tr></tr>');
     var hsdiv = "#HSdiv"+i;
     thtmp = $('<th class="subelementheader" style="text-align:left;" colspan="3">').html("Response");
     image = $('<img src="/kra-dev/kr/static/images/tinybutton-show.gif" alt="show/hide this panel" title="show/hide this panel"  style="width:45px; height:15px; border:none; cursor:pointer; padding:2px; vertical-align:middle;" />').attr("id","HScontrol"+i).toggle(
                function()
                {
                    $(hsdiv).slideDown(400);
                    $(this).attr("src",jsContextPath+"/kr/static/images/tinybutton-hide.gif");
                },
                function()
                {
                    $(hsdiv).slideUp(200);
                    $(this).attr("src",jsContextPath+"/kr/static/images/tinybutton-show.gif");
                }
     );
     image.prependTo(thtmp);
     thtmp.appendTo(trtmp);
     trtmp.appendTo(tbl325);
     tbl325.appendTo(td1);
     
     var div360 = $('<div ></div>').attr("id","HSdiv"+i); 
     var tbl362 = $('<table class="content_table">');
     trtmp = $('<tr></tr>');
     tdtmp = $('<td class="content_grey" style="width:110px; text-align:center;">').html("Type");
     tdtmp.appendTo(trtmp);
     tdtmp = $('<td class="content_grey" style="text-align:center;">').html("Values");
     tdtmp.appendTo(trtmp);
     trtmp.appendTo(tbl362);
     trtmp = $('<tr></tr>');
     tdtmp = $('<td class="content_white" style="text-align:center; vertical-align:top;">');
     //alert("qtypeid "+i+$('#qtypeid'+i).attr("value"));
     selecttmp = questionType[qtypeid];
     tdtmp.html(selecttmp);
     tdtmp.appendTo(trtmp);
                                                                               
      tdtmp = $('<td class="content_white" style="text-align:left;">');
    getQnTypeDesc(qtypeid).appendTo(tdtmp);  
    tdtmp.appendTo(trtmp);  
    trtmp.appendTo(tbl362);
    tbl362.appendTo(div360);
    div360.appendTo(td1);    
    td1.appendTo(tr1);
    tr1.appendTo(tbodytmp);
    tbodytmp.appendTo(tbl70);
    tbl70.appendTo(div64);
    div64.appendTo(div62);
                                                                                    
                                                        
  div62.appendTo(question);
                         
      // set up qdesc & qtypeid                                                   
      $("#qdesc"+i).attr("value",description);
      $("#qtypeid"+i).attr("value",qtypeid);
 
      //alert("qtypeid "+$("#qtypeid"+i).attr("value")+"-"+$("#qdesc"+i).attr("value"));
                          
                          
 //alert (question.text())
                                                                                
  return question;


  } // end addQuestion

  
  function getMaintTableHeader(description, vers) {
     var thead = $('<thead/>');
     var trtmp = $('<tr/>');
     var thtmp = $('<th class="subelementheader" align="left">');
     var imgtmp = $('<a href="#" class="hidedetail"><img src="/kra-dev/kr/static/images/tinybutton-hide.gif" align="absmiddle" border="0" width="45" height="15"></a>').toggle(     
     // TODO : really need this toggle.  Mock's toggle did not show up
     );     
     thtmp.html(description+"( "+vers+")");
     imgtmp.prependTo(thtmp);
     thtmp.appendTo(trtmp); 
     trtmp.appendTo(thead); 
     return thead;
  }
  

  function getQuestionActionSubTable(qnaireid) {
     // table for Qn actions : move/remove/cut/past/lookup  

     var tbl80 = $('<table cellpadding="0" cellspacing="0" class="elementtable" width="100%">').attr("id","tbl80"+i);
     var trtmp = $('<tr></tr>');
     var tmp = $('<th style="text-align:right; width:120px;">Node:</th>');
     trtmp.html(tmp);
     var thtmp = $('<th colspan="3"></th>');
     //if ($(this).parents('li:eq(0)').prev().size() > 0) {  // don't have this info yest because node is not complete
        getMoveUpLink().appendTo(thtmp);
        getMoveDownLink().appendTo(thtmp); 
     //}
  
     image = $('<a href="#"><img src="static/images/tinybutton-removenode.gif" width="79" height="15" border="0" alt="Remove Node" title="Remove this node and its child groups/sponsors"></a>&nbsp').attr("id","remove"+i).click(function() {
                      var liId="li#"+qnaireid;
                      // TODO : IE problem , clone does not clone child node
                      //removedNode = $(liId).clone(true);
                      removedNode = $(liId);
                      //removedNode = $(liId); // this will not work because event also lost
                      alert("Remove node "+removedNode.attr("id"));
                      if ($(liId).prev().size() == 0 && $(liId).next().size() > 0) {
                          $("#moveup"+$(liId).next().attr("id").substring(8)).hide();
                      }
                      if ($(liId).next().size() == 0 && $(liId).prev().size() > 0) {
                          $("#movedn"+$(liId).prev().attr("id").substring(8)).hide();
                      }
                      var idx = $(liId).attr("id").substring(8);
                      if ($(liId).parents('ul:eq(0)').parents('li:eq(0)').size() == 0) {
                         parentNum = 0;
                      } else {
                         parentNum = $("#qnum"+$(liId).parents('ul:eq(0)').parents('li:eq(0)').attr("id").substring(8)).attr("value");
                      }
                      
                      deleteChild(parentNum, $(liId).attr("id"));
                      alert(sqlScripts);
                      //sqlScripts = sqlScripts + "#;#" + "delete Q;"+$("#qid"+idx).attr("value")+";"+$("#qnum"+idx).attr("value")+";"+parentNum+";"+$("#qseq"+idx).attr("value");
                      // TODO : update seqnum of the sibling nodes following it
                      $(liId).remove();
                      cutNode=null;
                    }); 
      image.appendTo(thtmp);
      image = $('<a href="#"><img src="static/images/tinybutton-cutnode.gif" width="79" height="15" border="0" alt="Cut Node" title="Cut this node and its child roups/sponsors.  (Node will not be removed until you paste it.)"></a>&nbsp').attr("id","cut"+i).click(function() {
                      alert("Cut node");
                       var liId="li#"+qnaireid;
                      //cutNode = $(liId).clone(true);
                      cutNode = $(liId);
                      removedNode=null; // remove & cutNode should not co-exist
                    }); 
      image.appendTo(thtmp);                  
      image = $('<a href="#"><img src="static/images/jquery/tinybutton-copynode.gif" width="79" height="15" border="0" alt="Copy Node" title="Copy this node and its child.)"></a>&nbsp').attr("id","copy"+i).click(function() {
                      alert("Copy node");
                       var liId="li#"+qnaireid;
                      //copyNode = $(liId).clone(true);
                      copyNode = $(liId);
                    }); 
      image.appendTo(thtmp);                  
      image = $('<a href="#"><img src="static/images/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Paste Node" title="Paste your previously cut node structure under this node"></a>').attr("id","paste"+i).click(function() {

      // TODO : what if paste 'not top level node' to top level, then how about the exist 'condition'
                   
                if (removedNode || cutNode) {
                      var idx;
                      var parentNode = $("#"+qnaireid);
                      var ulTag = parentNode.children('ul');
                      if (ulTag.size() > 0) {                     
                           alert(ulTag.attr("id"));
                      } else {
                         alert("not found")
                          i++;
                          ulTag = $('<ul class="filetree"></ul>').attr("id","ul"+i);                         
                      }   
                      if (removedNode) {
                           pasteChild(qnaireid, removedNode);
                      
                      
                         // sqlScripts = sqlScripts +"#;#"+getInsertClause(getResearchAreaCode(removedNode.children('a:eq(0)').text()), getResearchAreaCode(name), getResearchAreaDescription(removedNode.children('a:eq(0)').text()));

                      //var idx = $(qnaireid).attr("id").substring(8);
//                         var idx = $(removedNode).attr("id").substring(8);
//                         var liId="li#"+qnaireid;
//                         var seqnum = Number($(liId).siblings().size())+1;
//                         var qid = $("#qid"+idx).attr("value");
//                         var qnum = $("#qnum"+idx).attr("value");
//                         var parentNum = $("#qnum"+$(liId).attr("id").substring(8)).attr("value");
                      //alert(idx+"-"+seqnum);
                      // parent_qnum/seq/qid/qnum 
                      //sqlScripts = sqlScripts + "#;#" + "update QPaste;"+$("#qnum"+$(liId).attr("id").substring(8)).attr("value")+";"+seqnum+";"+$("#qid"+idx).attr("value")+";"+$("#qnum"+idx).attr("value");
//                         var insertValues = "insert into Q"+qid +","+qnum+","+ parentNum+",'N','','',"+seqnum+",user,sysdate)"
//                         sqlScripts = sqlScripts+"#;#"+insertValues;
//                         alert (sqlScripts);
//                          removedNode.appendTo(ulTag);
                          removedNode = null;
                      } else if (cutNode) {
                          //var idx =$(cutNode).attr("id").substring(8);
                          if ($(cutNode).parents('ul:eq(0)').parents('li:eq(0)').size() == 0) {
                             parentNum = 0;
                          } else {
                             parentNum = $("#qnum"+$(cutNode).parents('ul:eq(0)').parents('li:eq(0)').attr("id").substring(8)).attr("value");
                          }
                      
                           deleteChild(parentNum, $(cutNode).attr("id"));                      
                           pasteChild(qnaireid, cutNode);
//                         var idx = $(cutNode).attr("id").substring(8);
//                         var liId="li#"+qnaireid;
//                         var seqnum = Number($(liId).children('li').size())+1;
//                         var qid = $("#qid"+idx).attr("value");
//                         var qnum = $("#qnum"+idx).attr("value");
//                         var parentNum = $("#qnum"+$(liId).attr("id").substring(8)).attr("value");
                      //alert(idx+"-"+seqnum);
                      // parent_qnum/seq/qid/qnum 
                      //sqlScripts = sqlScripts + "#;#" + "update QPaste;"+$("#qnum"+$(liId).attr("id").substring(8)).attr("value")+";"+seqnum+";"+$("#qid"+idx).attr("value")+";"+$("#qnum"+idx).attr("value");
                        // var insertValues = "update Qpaste"+qid +","+qnum+","+ parentNum+",'N','','',"+seqnum+",user,sysdate)"
//                         sqlScripts = sqlScripts + "#;#" + "update QPaste;"+parentNum+";"+seqnum+";"+qid+";"+qnum;
                         //sqlScripts = sqlScripts+"#;#"+insertValues;
//                         alert (sqlScripts);
                      
                      
                          var liId = cutNode.attr("id");
                          var parentRACode;
                          $("li#"+liId).remove();
//                          cutNode.appendTo(ulTag);
//                          idx = cutNode.attr("id").substring(8);
                          //sqlScripts = sqlScripts +"#;#"+getInsertClause(getResearchAreaCode(cutNode.children('a:eq(0)').text()), getResearchAreaCode(name), getResearchAreaDescription(cutNode.children('a:eq(0)').text()));
                          cutNode = null;
                      } 
                      
//                      ulTag.appendTo(parentNode);
                      //if (ulTag.children('li').size() == 1) {
                      //    $("#moveup"+idx).hide();
                      //    $("#movedn"+idx).hide();
                      //} else {
                          //alert("prev "+$("#qnaireid"+idx).prev().attr("id"));
                      //    $("#movedn"+idx).hide();
                      //    $("#movedn"+$("#qnaireid"+idx).prev().attr("id").substring(8)).show();
                     //}
                      //alert("Remove node "+removedNode.children('a:eq(0)').text());
                      //alert (sqlScripts);
                      
                      
                   }// if removednode                         
                   
                   if (copyNode) {
                      // paste copy node
                      isCopy="true";
                      getMaxCopyNodeIdx(copyNode);
                      pasteChild(qnaireid, copyNode);
                      maxCopyNodeIdx = 0;
                      isCopy="false";
                      
                   }
                                         
                }); 
      image.appendTo(thtmp);                  

      thtmp.appendTo(trtmp);
      trtmp.appendTo(tbl80); // row1

      getAddQuestionRow().appendTo(tbl80); // row2
      return tbl80;
  
  }
  
  function pasteCopyNode(nodeid) {
         var parentNode = $("#"+nodeid);
         var ulTag = parentNode.children('ul');
         
         startnode = $("#"+copyNode);
         alert("copy node "+$(startnode).attr("id").substring(8));
         qdesc = $('#qdesc'+$(startnode).attr("id").substring(8)).attr("value");
         qtypeid = $('#qtypeid'+$(startnode).attr("id").substring(8)).attr("value");
         i++;
         var listitem = getQuestionNew(qdesc,qtypeid, "V1.01", "true");
			var ultag = $('<ul></ul>');
            ultag.appendTo(listitem);
            var idx = listitem.attr("id").substring(8);
              //listitem.appendTo('ul#example');
              // last one no 'move dn'
                 $("#movedn"+idx).hide();
                 if (listitem.prev().size() > 0) {
                    $("#movedn"+listitem.prev().attr("id").substring(8)).show();
                 }
                       
                 
           alert("node "+nodeid+"-"+ parentNode.children('ul').size());     
           listitem.appendTo(ulTag);
        // also need this to show 'folder' icon
             $('#example').treeview({
                add: listitem
             });
  
        $("#qnum"+idx).attr("value",$("#questionNumber").attr("value"));
        var qid = $("#qid"+$(startnode).attr("id").substring(8)).attr("value");
        $("#qid"+idx).attr("value",qid);
        var seqnum = Number($(listitem).siblings().size())+1;
        $("#qseq"+idx).attr("value",seqnum);
  
  
         var liId="li#"+nodeid;
         //var seqnum = Number($(liId).children('li').size())+1;
         var qid = $("#qid"+idx).attr("value");
        var qnum = $("#questionNumber").attr("value");
         var parentNum = $("#qnum"+$(liId).attr("id").substring(8)).attr("value");
        var insertValues = "insert into Q"+qid +","+qnum+","+ parentNum+",'N','','',"+seqnum+",user,sysdate)"
        sqlScripts = sqlScripts+"#;#"+insertValues;
        alert("paste copy node "+sqlScripts);
        $("#questionNumber").attr("value",Number($("#questionNumber").attr("value"))+1)
  
        alert ("copy li c size "+$("#"+copyNode).attr("id")+"-"+$("#"+copyNode).children('ul.eq(0)').children('li').size());
        if ($("#"+copyNode).children('ul.eq(0)').children('li').size() > 0) {
             
             $("#"+copyNode).children('ul.eq(0)').children('li').each(function(){
                alert("child copy node"+$("deletereq"+$(this).attr("id").substring(8)).size() );
                pasteChild($(listitem).attr("id"), $(this).attr("id"));
            });
        }
  
  }
  
  // traverse thru the node to copy this node tree
  // need to clone copy node, otherwise if paste to its own children, then this will become infinite loop,
  function pasteChild(parentid, startnode) {
         var parentNode = $("#"+parentid);
         var ulTag = parentNode.children('ul');
         
         //startnode = $("#"+childid);
         alert("copy node "+$(startnode).attr("id").substring(8));
         qdesc = $('#qdesc'+$(startnode).attr("id").substring(8)).attr("value");
         qtypeid = $('#qtypeid'+$(startnode).attr("id").substring(8)).attr("value");
         i++;
         var listitem = getQuestionNew(qdesc,qtypeid, "V1.01", "true");
			var ultag = $('<ul></ul>');
            ultag.appendTo(listitem);
            var idx = listitem.attr("id").substring(8);
              //listitem.appendTo('ul#example');
              // last one no 'move dn'
                 $("#movedn"+idx).hide();
                 if (listitem.prev().size() > 0) {
                    $("#movedn"+listitem.prev().attr("id").substring(8)).show();
                 }
                       
                 
          // alert("node "+parentid+"-"+ parentNode.children('ul').size());     
           listitem.appendTo(ulTag);
        // also need this to show 'folder' icon
             $('#example').treeview({
                add: listitem
             });

        $("#qnum"+idx).attr("value",$("#questionNumber").attr("value"));
        var qid = $("#qid"+$(startnode).attr("id").substring(8)).attr("value");
        $("#qid"+idx).attr("value",qid);
        var seqnum = Number($(listitem).siblings().size())+1;
        $("#qseq"+idx).attr("value",seqnum);
  

  
         var liId="li#"+parentid;
       //  var seqnum = Number($(liId).children('li').size())+1;
         var qid = $("#qid"+idx).attr("value");
        var qnum = $("#questionNumber").attr("value");
         var parentNum = $("#qnum"+$(liId).attr("id").substring(8)).attr("value");
        var insertValues = "insert into Q"+qid +","+qnum+","+ parentNum+",'N','','',"+seqnum+",user,sysdate)"
        sqlScripts = sqlScripts+"#;#"+insertValues;
       // alert("paste copy node "+sqlScripts+$(startnode).children('ul.eq(0)').children('li').size());
        $("#questionNumber").attr("value",Number($("#questionNumber").attr("value"))+1)
  
        //alert ("copy li c size "+$("#"+childid).attr("id")+"-"+$("#"+childid).children('ul.eq(0)').children('li').size());
        // $(startnode).children().each(function(){
        //    alert('child '+$(this).html()+"-"+$(this).html());
        // });
        alert("child copy node"+$("#cond"+$(startnode).attr("id").substring(8)).attr("value") );
        cidx = $(startnode).attr("id").substring(8);

        cond = $("#cond"+cidx).attr("value");
        value = $("#condvalue"+cidx).attr("value");
                var newResponse = getRequirementDeleteRow(responseArray[cond], value);
                newResponse.appendTo($("#addrequirement"+i).parents('div:eq(0)').children('table:eq(0)').children('tbody'));
                $("#cond"+idx).attr("value",cond);
                $("#condvalue"+idx).attr("value",value);
               $("#addrequirement"+idx).parents('tr:eq(0)').remove();
                sqlScripts = sqlScripts + "#;#" + "update QCond;'Y';'"+cond+"';'"+value+"';"+$("#qid"+idx).attr("value")+";"+$("#qnum"+idx).attr("value");
        alert(sqlScripts);
        //$("#cond"+idx).attr("value",response);
        //$("#condvalue"+idx).attr("value",value);
        
        if ($(startnode).children('ul.eq(0)').children('li').size() > 0) {
             
             $(startnode).children('ul.eq(0)').children('li').each(function(){
                //alert("child copy node"+$(this).attr("id")+"-"+isCopy+"-"+maxCopyNodeIdx);
               
               if (isCopy == 'false' || (isCopy == 'true' && $(this).attr("id").substring(8) <= maxCopyNodeIdx)) {
                  pasteChild($(listitem).attr("id"), $(this));
                }
            });
        }
  
  
  }
  
  // traverse thru the node to delete this node tree
  function deleteChild(parentNum, childid) {

        idx = $("#"+childid).attr("id").substring(8);
        sqlScripts = sqlScripts + "#;#" + "delete Q;"+$("#qid"+idx).attr("value")+";"+$("#qnum"+idx).attr("value")+";"+parentNum+";"+$("#qseq"+idx).attr("value");
   //      $("#"+childid).children().each(function(){
   //         alert('child '+$(this).html()+"-"+$(this).html());
   //      });
        
        if ($("#"+childid).children('ul.eq(0)').children('li').size() > 0) {
             
             $("#"+childid).children('ul.eq(0)').children('li').each(function(){
                deleteChild($("#qnum"+idx).attr("value"), $(this).attr("id"));
            });
        }
  
  
  }

  // traverse thru the node to collect copy nodes
  // IE issue with clone, so set this max up, in case the copied node is pasted to its children
  // if don't set this up, it will cause infinite loop
  function getMaxCopyNodeIdx(startnode) {

        idx = $(startnode).attr("id").substring(8);
        if (idx > maxCopyNodeIdx) {
            maxCopyNodeIdx=idx;
        }
        
        if ($(startnode).children('ul.eq(0)').children('li').size() > 0) {
             
             $(startnode).children('ul.eq(0)').children('li').each(function(){
                getMaxCopyNodeIdx($(this));
            });
        }
  
  
  }

  
  function getMoveDownLink() {
      var image = $('<img style="border:none;" alt="move down" title="Move Down" type="image" >');
      var atag = $('<a href="#"></a>').attr("id","movedn"+i).click(function() {
          //alert("move down"+$(this).parents('li:eq(0)').size());
          //var curNode = $(this).parents('li:eq(0)').clone(true);
          var curNode = $(this).parents('li:eq(0)');
          var nextNode = $(this).parents('li:eq(0)').next();
         // $(this).parents('li:eq(0)').remove();
          curNode.insertAfter(nextNode);
          var idx = $(curNode).attr("id").substring(8);
          //alert("move dn "+idx+"-"+$("#qseq"+idx)+"-"+$("#qid"+idx)+"-"+$("#qnum"+idx))
          sqlScripts = sqlScripts + "#;#" + "update QMove;"+(Number($("#qseq"+idx).attr("value"))+1)+";"+$("#qid"+idx).attr("value")+";"+$("#qnum"+idx).attr("value");


          $("#moveup"+curNode.attr("id").substring(8)).show();
          $("#movedn"+nextNode.attr("id").substring(8)).show();
          if (nextNode.prev().size() == 0) {
              //alert("move up next node");
              $("#moveup"+nextNode.attr("id").substring(8)).hide();
          }
          if (curNode.next().size() == 0) {
             //alert ("move dn no next node ");
              $("#movedn"+curNode.attr("id").substring(8)).hide();
          }
          idx = $(nextNode).attr("id").substring(8);
          //alert("move dn "+idx+"-"+$("#qseq"+idx)+"-"+$("#qid"+idx)+"-"+$("#qnum"+idx))
          sqlScripts = sqlScripts + "#;#" + "update QMove;"+(Number($("#qseq"+idx).attr("value"))-1)+";"+$("#qid"+idx).attr("value")+";"+$("#qnum"+idx).attr("value");


      }); 
      image.attr("src","static/images/jquery/arrow-down.gif");
      //alert("images "+image.attr("src"));
      atag.html(image);
      return atag;
  }
  
  function getMoveUpLink() {
      var image = $('<img style="border:none;" alt="move up" title="Move up" type="image" >');
      var atag = $('<a href="#"></a>').attr("id","moveup"+i).click(function() {
          //alert("move up"+$(this).parents('li:eq(0)').prev().size());
          var curNode = $(this).parents('li:eq(0)');
          //var curNode = $(this).parents('li:eq(0)').clone(true);
          // TODO : trying to resolve IE issue
          //if( !!document.all ) {
          //curNode.html($(this).parents('li:eq(0)').html());
          //alert(curNode.html());
          //}
          
          var nextNode = $(this).parents('li:eq(0)').prev();
         // $(this).parents('li:eq(0)').remove();
          curNode.insertBefore(nextNode);
          var idx = $(curNode).attr("id").substring(8);
          sqlScripts = sqlScripts + "#;#" + "update QMove;"+(Number($("#qseq"+idx).attr("value"))-1)+";"+$("#qid"+idx).attr("value")+";"+$("#qnum"+idx).attr("value");
          
          $("#movedn"+curNode.attr("id").substring(8)).show();
          $("#moveup"+nextNode.attr("id").substring(8)).show();
          if (curNode.prev().size() == 0) {
              $("#moveup"+curNode.attr("id").substring(8)).hide();
          }
          if (nextNode.next().size() == 0) {
              $("#movedn"+nextNode.attr("id").substring(8)).hide();
          }
          idx = $(nextNode).attr("id").substring(8);
          sqlScripts = sqlScripts + "#;#" + "update QMove;"+(Number($("#qseq"+idx).attr("value"))+1)+";"+$("#qid"+idx).attr("value")+";"+$("#qnum"+idx).attr("value");
          //alert(sqlScripts);
      }); 
      image.attr("src","static/images/jquery/arrow-up.gif");
      //alert("images "+image.attr("src"));
      atag.html(image);
      return atag;
  }
  
  function getAddQuestionRow() {
     var tbl95 = $('<table style="border:none; width:100%;" cellpadding="0" cellspacing="0"></table>').attr("id","tbl95"+i);
     var trtmp = $('<tr></tr>');
     var tdtmp = $('<td style="border:none; width:170px;"></td>');
  // name attribute has to be in 'input' definition, otherwise, IE7 will not work.
     $('<input type="radio" name = "radio" checked="checked" value="sibling" />').attr("class","radioQn"+i).attr("name","radioQn"+i).appendTo(tdtmp);
     $('<span>as sibling&nbsp;&nbsp;&nbsp</span>').appendTo(tdtmp);
     $('<input type="radio" name = "radio" value="child" />').attr("class","radioQn"+i).attr("name","radioQn"+i).appendTo(tdtmp);
     $('<span>as child</span>').appendTo(tdtmp);
     tdtmp.appendTo(trtmp);
  
     tdtmp = $('<td style="border:none;"></td>').html($('<input type="text" id = "newqdesc" name = "newqdesc" size="50" value="" />').attr("id","newqdesc"+i));
     tdtmp.appendTo(trtmp);
     tdtmp = $('<td style="border:none; width:30px; text-align:center;"></td>');

     var atag = $('<a href="#"></a>');

     // question id from lookup - to be added as sibling or child of this node
     var qntag = $('<input type="hidden" id = "newqid" name = "newqid" />').attr("id","newqid"+i).attr("name","newqid"+i);
     qntag.appendTo(tdtmp);
     var hidtr = $('<tr/>');
     var hidtd = $('<td colspan="2"/>');
     // question id for this node
     qntag = $('<input type="hidden" id = "qid" name = "qid" />').attr("id","qid"+i).attr("name","qid"+i);
     qntag.appendTo(hidtd);
     qntag = $('<input type="hidden" id = "qseq" name = "qseq" />').attr("id","qseq"+i).attr("name","qseq"+i);
     qntag.appendTo(hidtd);
     qntag = $('<input type="hidden" id = "newqtypeid" name = "newqtypeid" />').attr("id","newqtypeid"+i).attr("name","newqtypeid"+i);
     qntag.appendTo(tdtmp);
     qntag = $('<input type="hidden" id = "qnum" name = "qnum" />').attr("id","qnum"+i).attr("name","qnum"+i);
     qntag.appendTo(hidtd);
     qntag = $('<input type="hidden" id = "qdesc" name = "qdesc" />').attr("id","qdesc"+i).attr("name","qdesc"+i);
     qntag.appendTo(hidtd);
     qntag = $('<input type="hidden" id = "qtypeid" name = "qtypeid" />').attr("id","qtypeid"+i).attr("name","qtypeid"+i);
     qntag.appendTo(hidtd);
     qntag = $('<input type="hidden" id = "cond" name = "cond" />').attr("id","cond"+i).attr("name","cond"+i);
     qntag.appendTo(hidtd);
     qntag = $('<input type="hidden" id = "condvalue" name = "condvalue" />').attr("id","condvalue"+i).attr("name","condvalue"+i);
     qntag.appendTo(hidtd);
     hidtd.appendTo(hidtr);
     hidtr.appendTo($("#question-table"));
     
     var image = $('<img src="/kra-dev/static/images/searchicon.gif" id="searchQ" name="searchQ" border="0" class="tinybutton"  alt="Search Question" title="Search Question">').attr("id","search"+i).attr("name","search"+i); 
     //image.attr("name","methodToCall\.performLookup\.(!!org\.kuali\.kra\.questionnaire\.question\.Question!!)\.(((questionId:document\.newMaintainableObject\.questionId,)))\.((#document\.newMaintainableObject\.questionId:questionId,#))\.((<>))\.(([]))\.((**))\.((^^))\.((&&))\.((/rateClassTypeT/))\.((~~))\.anchor1");
     //image.attr("name","methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionId:document.newMaintainableObject.questionId,))).((#document.newMaintainableObject.questionId:questionId,#)).((<>)).(([])).((**)).((^^)).((&&)).((/rateClassTypeT/)).((~~)).anchor1");
     //image.attr("name","methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionId:document.newMaintainableObject.questionId,))).((#document.newMaintainableObject.questionId:questionId,#)).((<>)).(([])).((**)).((^^)).((&&)).((/rateClassTypeT/)).((~~)).anchor1");
     image.click(function() {
         //alert($(this).parents('li:eq(0)').attr("id"));
         // TODO : IE problem.  after the node is moved up or down, then the "id" of the image got lost.
         // so, before figuring a solution, use this alternative to fin parents id.
         var idx;
         if ($(this).attr("id") != '') {
             idx = $(this).attr("id").substring(6);
         } else {
             idx = $(this).parents('li:eq(0)').attr("id").substring(8);
         }
         //alert (idx)
         checkToAddQn(idx);
         return false;
     });
     atag.html(image);
     atag.appendTo(tdtmp);
     tdtmp.appendTo(trtmp);
     //name="methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionId:document.newMaintainableObject.businessObject.questionnaireQuestions[0].questionId,))).((#document.newMaintainableObject.businessObject.questionnaireQuestions[0].questionId:questionId,#)).((<>)).(([])).((**)).((^^)).((&&)).((/questionId/)).((~~)).anchor1"
    // image = $('<input type="image" src="/kra-dev/kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Search Question" title="Search Question" />').attr("id","search"+i);
    // image.attr("name","methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionId:newQuestionId,question:newQuestion))).((#newQuestionId:questionId,#)).((<>)).(([])).((**)).((^^)).((&&)).((/questionId/)).((~~)).anchor1");
    // image.appendTo(tdtmp);
     //var testlookup = $('<input type="image" tabindex="1000009" name="methodToCall\.performLookup\.(!!org\.kuali\.kra\.budget\.bo\.RateClassType!!)\.(((rateClassType:document\.newMaintainableObject\.rateClassType,)))\.((#document\.newMaintainableObject\.rateClassType:rateClassType,#))\.((<>))\.(([]))\.((**))\.((^^))\.((&&))\.((/rateClassTypeT/))\.((~~))\.anchor1" src="/kra-dev/kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Search Rate Class Type" title="Search Rate Class Type" />');
     //testlookup.appendTo(tdtmp);
  
      image = $('<input name="addquestionnaire" src="kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" type="image" />').attr("id","addQn"+i).click(function() {
        //alert("This would add question "+$(".radioQn"+$(this).attr("id").substring(5)+":checked").val()+"-"+$("#newQuestionId").attr("value")+"-"+$("#newQuestion").attr("value"));  
//        alert("This would add question "+$(this).parents('li:eq(0)').children('ul:eq(0)').size()+"-"+$(".radioQn"+$(this).attr("id").substring(5)+":checked").val());  
        //alert("This would add question "+$(this).attr("id"));  
        
        
            i++;

            var radioVal = $(".radioQn"+$(this).attr("id").substring(5)+":checked").val();
            var childNode = 'true';
            if (radioVal == 'sibling' && $(this).parents('li:eq(0)').parents('ul:eq(0)').attr("id") == 'example') {
                childNode = 'false';
            }
            var listitem = getQuestionNew($("#newqdesc"+$(this).attr("id").substring(5)).attr("value"),$("#newqtypeid"+$(this).attr("id").substring(5)).attr("value"), "V1.01", childNode);
			var ultag = $('<ul></ul>');
            ultag.appendTo(listitem);
            var idx = listitem.attr("id").substring(8);
            if (radioVal == 'sibling') {
              //alert('sibling');
              var parentUl = $(this).parents('li:eq(0)').parents('ul:eq(0)');
              listitem.appendTo(parentUl);
              //listitem.appendTo('ul#example');
              // last one no 'move dn'
                 $("#movedn"+idx).hide();
                 $("#movedn"+listitem.prev().attr("id").substring(8)).show();
            } else {
              var parentUl = $(this).parents('li:eq(0)').children('ul:eq(0)');
              listitem.appendTo(parentUl);
              //alert('children'+$(this).parents('li:eq(0)').children('ul:eq(0)').children('li').size());
              // TODO : if add 2nd item, then add 'movedn' to 1st item.  maybe use hide/show instead of 'remove'
              // "==1" is the one just added
              if ($(this).parents('li:eq(0)').children('ul:eq(0)').children('li').size() == 1) {
                 $("#moveup"+idx).hide();
                 $("#movedn"+idx).hide();
              } else {
                 //alert("prev "+listitem.prev().attr("id"));
                 $("#movedn"+idx).hide();
                 $("#movedn"+listitem.prev().attr("id").substring(8)).show();
              }
            }
        
        // also need this to show 'folder' icon
        $('#example').treeview({
           add: listitem
        });

        //var intag = $('<input type ="text"></input>').attr("value",$("#newQuestionId").attr("value").trim());
        //var name = "document\.newMaintainableObject\.businessObject\.questionnaireQuestions["+j+"]\.questionId";
        //intag.attr("id",name).attr("name",name);
        //j++;
        //var trtmp1 = $('<tr></tr>');
        //var tdtmp1 = $('<td></td>').html(intag);
        //trtmp1.html(tdtmp1);
        //trtmp1.appendTo($("#question-table"));
        if (childNode == 'true') {
           //alert("parent li "+$(this).parents('li:eq(0)').attr("id"));
          // $(this).parents('li:eq(0)').click();
           //$(this).parents('li:eq(0)').toggle('fast');
        }
       // $("#listcontrol"+i).click(); // to see if this item can become focus
        //$("#listcontrol"+i).click(); // to see if this item can become focus
        // TODO : set up for insert 
        /* questionnairenumber from #questionnairenumber
         * questionId from #qid
         * sequenceNumber from $(this).parents('li:eq(0)').siblings().size() ?
         */
       // $(listitem).parents('ul:eq(0)').parents('li:eq(0)').size() == 0 : check whetehr it is at the top level
         if ($(listitem).parents('ul:eq(0)').parents('li:eq(0)').size() == 0) {
             parentNum = 0;
         } else {
         //alert("parents li "+$(listitem).parents('ul:eq(0)').parents('li:eq(0)').attr("id"));
             parentNum = $("#qnum"+$(listitem).parents('ul:eq(0)').parents('li:eq(0)').attr("id").substring(8)).attr("value");
         }
       // alert("questionnairenumber "+$("#questionNumber").attr("value")+" qid "+$("#qid"+$(this).attr("id").substring(5)).attr("value"));
        $("#qnum"+$(listitem).attr("id").substring(8)).attr("value",$("#questionNumber").attr("value"));
       // alert("parents li "+$(this).attr("id").substring(5)+" "+$("#qnum"+$(this).attr("id").substring(5)).attr("value"));
        var qid = $("#newqid"+$(this).attr("id").substring(5)).attr("value");
        $("#qid"+$(listitem).attr("id").substring(8)).attr("value",qid);




        var seqnum = Number($(listitem).siblings().size())+1;
        $("#qseq"+$(listitem).attr("id").substring(8)).attr("value",seqnum);
        var qnum = $("#questionNumber").attr("value");
        var insertValues = "insert into Q"+qid +","+qnum+","+ parentNum+",'N','','',"+seqnum+",user,sysdate)"
        sqlScripts = sqlScripts+"#;#"+insertValues;
        $("#questionNumber").attr("value",Number($("#questionNumber").attr("value"))+1)
        return false;
      });
      
     tdtmp = $('<td style="border:none; width:30px; text-align:center;"></td>');
      image.appendTo(tdtmp);
  
  
     tdtmp.appendTo(trtmp);
     trtmp.appendTo(tbl95);
     trtmp1 = $('<tr></tr>');
     $('<th style="text-align:right;">Add Question(s):</th>').appendTo(trtmp1);
     thtmp1 = $('<th colspan="3"></th>');
     tbl95.appendTo(thtmp1);
     thtmp1.appendTo(trtmp1);
     return trtmp1;     
  
  }
  
  function getRequirementDisplayTable() {
     var tbl154 = $('<table width="100%" cellpadding="0" cellspacing="0" style="border-top:#E4E3E4 solid 1px;">');
     var trtmp = $('<tr></tr>');
     var thtmp = $('<th class="subelementheader" style="text-align:left;" colspan="3">');
     thtmp.html("Requirements for Display");
     var hsdiv = "#HSReqdiv"+i;
     var image = $('<img src="/kra-dev/kr/images/tinybutton-show.gif" alt="show/hide this panel" title="show/hide this panel"  style="width:45px; height:15px; border:none; cursor:pointer; padding:2px; vertical-align:middle;" />').attr("id","HSReqcontrol"+i).toggle(
                function()
                {
                    $(hsdiv).slideDown(400);
                    $(this).attr("src","/kra-dev/kr/static/images/tinybutton-hide.gif");
                },
                function()
                {
                    $(hsdiv).slideUp(200);
                    $(this).attr("src","/kra-dev/kr/static/images/tinybutton-show.gif");
                }
      );
      image.prependTo(thtmp);
      thtmp.appendTo(trtmp);
      trtmp.appendTo(tbl154);
      return tbl154;
  
  }
  
  function getAddRequirementRow() {
  
      var trtmp = $('<tr></tr>');
      var thtmp = $('<th style="text-align:center; width:150px;"></th>').html("Add");
      thtmp.appendTo(trtmp);
      /*
      var tdtmp = $('<td class="content_info" style="text-align:center;"></td>');
      var selecttmp = $('<select name="CustomData"></select>');
      $('<option value="0" selected="selected">select</option>').appendTo(selecttmp);
      $('<option value="1">and</option>').appendTo(selecttmp);
      $('<option value="2">or</option>').appendTo(selecttmp);
      selecttmp.appendTo(tdtmp);
      tdtmp.appendTo(trtmp);
      */
      tdtmp = $('<td class="content_info" style="text-align:center;"></td>').html("Parent Response ");
      //alert("response options "+responseOptions.html()+"-"+i);
      respclone = responseOptions.clone(true);
      respclone.attr("id","parentResponse"+i).appendTo(tdtmp);
      tdtmp.appendTo(trtmp);
      tdtmp = $('<td class="content_info" style="text-align:center;"></td>').html("Value:");
      $('<input type="text" size="25" />').appendTo(tdtmp);
      tdtmp.appendTo(trtmp);
      tdtmp = $('<td class="content_info" class="content_white" style="width:65px; text-align:center;"></td>');
      image = $('<input name="addrequirement" src="/kra-dev/kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" type="image" />').attr("id","addrequirement"+i).click(function() {
        //alert("This would add the specified requirement."+$(this).parents('tr:eq(0)').children('td:eq(0)').children('select:eq(0)').attr("value"));  
        //var operator = $(this).parents('tr:eq(0)').children('td:eq(0)').children('select:eq(0)').attr("value");
        var response = $(this).parents('tr:eq(0)').children('td:eq(0)').children('select:eq(0)').attr("value");
        var value = $(this).parents('tr:eq(0)').children('td:eq(1)').children('input:eq(0)').attr("value");
        //var newResponse = getRequirementDeleteRow(sequenceNum, opArray[operator], responseArray[response], value)
        // it seems that 'tbody' is implicitly created.
       // var sequence = $(this).parents('div:eq(0)').children('table:eq(1)').children('tbody').children('tr').size() +1;
        //alert (sequence +"- "+operator+"-"+response+"-"+$(this).parents('div:eq(0)').children('table:eq(1)').children('tbody').size());
        //if (sequence == 1 && operator != 0) {
        //   alert("This is the first requirement, and operator is not needed");
       // } else {
            if (okToAddRequirement(response,value)) {   
            /*   
                var opDesc;  
                if (sequence == 1) {
                     opDesc = "Current Requirements:";
                } else {
                     opDesc = opArray[operator];
                }
                */
                var newResponse = getRequirementDeleteRow(responseArray[response], value);
                newResponse.appendTo($(this).parents('div:eq(0)').children('table:eq(0)').children('tbody'));
                var idx = $(this).parents('li:eq(0)').attr("id").substring(8);
                $("#cond"+idx).attr("value",response);
                $("#condvalue"+idx).attr("value",value);
                sqlScripts = sqlScripts + "#;#" + "update QCond;'Y';'"+response+"';'"+value+"';"+$("#qid"+idx).attr("value")+";"+$("#qnum"+idx).attr("value");
                //alert(sqlScripts);
               $(this).parents('tr:eq(0)').remove();
            }
        //}
        return false;
      });

      image.appendTo(tdtmp);
      tdtmp.appendTo(trtmp);
      return trtmp;  
  }
  
  function okToAddRequirement(response,value) {
      var valid = false;
      if (value == '') {
           alert("Please enter a value");
      } else if (response == 0) {
           alert("Please select a response");
     // } else if (sequence == 1 && operator != 0) {
     //      alert("This is the first requirement, and operator is not needed");
     // } else if (sequence > 1 && operator == 0) {
     //      alert("Please select an operator");
      } else {
         valid = true;
      }
      return valid;   
  }
  
  
  function getRequirementDeleteRow(response, value) {
      var trtmp = $('<tr></tr>');
      var thtmp = $('<th style="text-align:left; border-top:none; width:150px;">').html("Current Requirements:");
      thtmp.appendTo(trtmp);
      //var tdtmp=$('<td style="text-align:center; width:20px; border-top:none;">').html($('<div></div>').html(sequenceNum));
      //tdtmp.appendTo(trtmp);
      tdtmp=$('<td style="text-align:left; border-top:none;">').html(response+" : "+value);
      tdtmp.appendTo(trtmp);
      tdtmp=$('<td class="content_white" style="text-align:center; border-top:none; width:65px;">');
      image = $('<input src="/kra-dev/kr/static/images/tinybutton-delete1.gif"  style="border:none;" alt="delete" type="image" />').attr("id","deletereq"+i).click(function() {
         alert("This would delete this requirement."+$(this).parents('tr:eq(0)').next().size()); 
         //var nextNode = $(this).parents('tr:eq(0)').next();
         //var nextSeq = sequenceNum;
        // while (nextNode.size() > 0) {
            // loop to update sequence #
         //   nextNode.children('td:eq(0)').children('div:eq(0)').remove();
         //   nextNode.children('td:eq(0)').html($('<div></div>').html(nextSeq));
         //   if (nextSeq == 1) {
         //       nextNode.children('th:eq(0)').html("Current Requirements:");
        //    }
        //    nextSeq++;
         //   nextNode = nextNode.next();
         //}
         var idx = $(this).parents('li:eq(0)').attr("id").substring(8);
         sqlScripts = sqlScripts + "#;#" + "update QCond;'N';'';'';"+$("#qid"+idx).attr("value")+";"+$("#qnum"+idx).attr("value");
         getAddRequirementRow().appendTo($(this).parents('tr:eq(0)').parents('tbody:eq(0)'));
         $(this).parents('tr:eq(0)').remove(); 
         return false;
      });
      image.appendTo(tdtmp);
      tdtmp.appendTo(trtmp);
      return trtmp;
  
  }
  
  // test lookup pop
  
      function checkToAddQn(nodeIndex) {
    
		//alert (childrenNode[0].isLeaf+" ln "+leafNode);
		//if (childrenNode.length == 0 || childrenNode[0].isLeaf) {
		//    updateSponsorCodes();
		alert(nodeIndex);
			  url=window.location.href
			  pathname=window.location.pathname
			  idx1=url.indexOf(pathname);
			  idx2=url.indexOf("/",idx1+1);
			  extractUrl=url.substr(0,idx2);
			  if (nodeIndex == 0) {
			     lookupType = "multivalue";
			  } else {
			     lookupType = "single";
			  }
			  var winPop = window.open(extractUrl+"/questionLookup.do?nodeIndex="+nodeIndex+"&lookupType="+lookupType, "_blank", "width=1000, height=800, scrollbars=yes");
         //} else {
         //	alert ("This node has sub sponsor group; can't add sponsors ");
         //}

    }
  
  function returnQuestion(newQuestionId, newQuestion,newQuestionTypeId,nodeIndex) {
     //alert("return QNID "+ newQuestionId+newQuestionTypeId);
     // TODO : these need to be defined in 'input' tag, otherwise, the value set will not stuck.
     // questionid, description, and typeid returned from question lookup.
     $("#newqid"+nodeIndex).attr("value",newQuestionId);
     $("#newqdesc"+nodeIndex).attr("value",newQuestion);
     $("#newqtypeid"+nodeIndex).attr("value",newQuestionTypeId);
     //alert("qid "+ nodeIndex+$("#qid"+nodeIndex).attr("value"));
  }
  
  function getQnTypeDesc(qtypeid) {
     //alert("gettypedesc "+qtypeid);
     var divtmp = null;
     switch (Number(qtypeid)) { 
         // has to use Number(qtypeid)
         case 1: divtmp = $('<div id="responsetypeYesNo1b" class="responsetypediv1b">').html($('<p>The user will be presented with the following radio buttons: Yes, No.<br />Only one selection is possible.<br />A selection is required.</p>')); break;
         case 2: divtmp = $('<div id="responsetypeYesNoNA1b" class="responsetypediv1b">').html($('<p>The user will be presented with the following pulldown: Yes, No, Not Applicable.<br />Only one selection is possible.<br />A selection is required.</p>')); break;
         case 3: divtmp = $('<div id="responsetypeNumber1b" class="responsetypediv1b">').html($('<p>The user will be presented with 1 text box.<br />The entered value will be validated requiring a number only.<br />The maximum length of the number in characters is 5.<br />The number of possible answers is 1. </p>')); break;
         case 4: divtmp = $('<div id="responsetypeDate1b" class="responsetypediv1b">').html($('<p>The user will be presented with 2 text boxes.<br />The entered value will be validated for a date in MM/DD/YYYY format.<br />A response is required for each text box.</p>')); break;
         case 5: divtmp = $('<div id="responsetypeText1b" class="responsetypediv1b">').html($('<p>The user will be presented with 2 text areas.<br />The number of possible answers is 2.<br />Maximum length of each response in characters: 256.</p>')); break;
         case 6: divtmp = $('<div id="responsetypeDropdown1b" class="responsetypediv1b">').html($('<p>The user will be presented with a dropdown of options.<br />Only one selection is possible.<br />A selection is required.</p> Possible answers are:<br />1. One Fish<br />2. Two Fish<br />3. Red Fish<br />4. Blue Fish')); break;
         case 7: divtmp = $('<div id="responsetypeCheckbox1b" class="responsetypediv1b">').html($('<p>The user will be presented with 4 checkboxes.<br />At least one selection is required.<br />Up to 4 selections are allowed.</p>Possible answers are:<br />1. One Byte<br />2. Two Bites<br />3. Red Light<br />4. Green lights')); break;
         case 8: divtmp = $('<div id="responsetypeSearch1b" class="responsetypediv1b">').html($('<p>The user will be presented with the ability to search for: Person.<br />The field to return is: Name.<br />The number of possible returns is 2.</p>')); break;
        default: divtmp = $('<div id="responsetypeSearch1b" class="responsetypediv1b">').html($('<p>Unknown</p>'));
     }
     return divtmp;
  }
  
      function getInsertClause(code, parentCode, description) {
     
        // need to rework on real update_user
        var values="'"+code+"','"+parentCode+"', 'N', '"+description+"', sysdate, user";
         return "insert into Q values("+values+")";
     }
  
  
  