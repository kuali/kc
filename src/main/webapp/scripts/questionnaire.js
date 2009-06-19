  function getQuestionNew(description, vers) {
  
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
     var tbl196 = $('<table cellpadding="0" cellspacing="0" class="elementtable" style="width:100%; border-top:none;"></table>');
     getAddRequirementRow().appendTo(tbl196);
     tbl196.appendTo(div192);
                         
                         
     var tbl266 = $('<table cellpadding="0" cellspacing="0" class="elementtable" style="width:100%; border-top:none;"></table>').attr("id","tbl266-"+i);
     getRequirementDeleteRow("1", "Current Requirements:", "Matches text", "Yes").appendTo(tbl266);
     getRequirementDeleteRow("2", "(or)", "Matches text", "Sometimes").appendTo(tbl266);
     tbl266.appendTo(div192);
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
     selecttmp = $('<select id="responsetypecontrol1b"></select>');
     $('<option value="0" selected="selected">select</option>').appendTo(selecttmp);
     $('<option value="1">Yes/No</option>').appendTo(selecttmp);
     $('<option value="2">Yes/No/NA</option>').appendTo(selecttmp);
     $('<option value="3">Number</option>').appendTo(selecttmp);
     $('<option value="4">Date</option>').appendTo(selecttmp);
     $('<option value="5">Text</option>').appendTo(selecttmp);
     $('<option value="6">Dropdown</option>').appendTo(selecttmp);
     $('<option value="7">Checkbox</option>').appendTo(selecttmp);
     $('<option value="8">Lookup</option>').appendTo(selecttmp);
     selecttmp.appendTo(tdtmp);
     tdtmp.appendTo(trtmp);
                                                                               
 
      tdtmp = $('<td class="content_white" style="text-align:left;">');
    divtmp = $('<div id="responsetypeSelect1b" class="responsetypediv1b">').html($("<i>[NB: The Type pulldown to the left would be READ ONLY.  It is presented here as a dynamic trigger for the purposes of mocking each type's value.]</i>"));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeYesNo1b" class="responsetypediv1b">').html($('<p>The user will be presented with the following radio buttons: Yes, No.<br />Only one selection is possible.<br />A selection is required.</p>'));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeYesNoNA1b" class="responsetypediv1b">').html($('<p>The user will be presented with the following pulldown: Yes, No, Not Applicable.<br />Only one selection is possible.<br />A selection is required.</p>'));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeNumber1b" class="responsetypediv1b">').html($('<p>The user will be presented with 1 text box.<br />The entered value will be validated requiring a number only.<br />The maximum length of the number in characters is 5.<br />The number of possible answers is 1. </p>'));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeDate1b" class="responsetypediv1b">').html($('<p>The user will be presented with 2 text boxes.<br />The entered value will be validated for a date in MM/DD/YYYY format.<br />A response is required for each text box.</p>'));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeText1b" class="responsetypediv1b">').html($('<p>The user will be presented with 2 text areas.<br />The number of possible answers is 2.<br />Maximum length of each response in characters: 256.</p>'));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeDropdown1b" class="responsetypediv1b">').html($('<p>The user will be presented with a dropdown of options.<br />Only one selection is possible.<br />A selection is required.</p> Possible answers are:<br />1. One Fish<br />2. Two Fish<br />3. Red Fish<br />4. Blue Fish'));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeCheckbox1b" class="responsetypediv1b">').html($('<p>The user will be presented with 4 checkboxes.<br />At least one selection is required.<br />Up to 4 selections are allowed.</p>Possible answers are:<br />1. One Byte<br />2. Two Bites<br />3. Red Light<br />4. Green lights'));
    divtmp = $('<div id="responsetypeSearch1b" class="responsetypediv1b">').html($('<p>The user will be presented with the ability to search for: Person.<br />The field to return is: Name.<br />The number of possible returns is 2.</p>'));
    divtmp.appendTo(tdtmp);  
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
  
     image = $('<a href="#"><img src="/kra-dev/static/images/tinybutton-removenode.gif" width="79" height="15" border="0" alt="Remove Node" title="Remove this node and its child groups/sponsors"></a>&nbsp').attr("id","remove"+i).click(function() {
                      var liId="li#"+qnaireid;
                      removedNode = $(liId).clone(true);
                      //removedNode = $(liId); // this will not work because event also lost
                      alert("Remove node "+removedNode.attr("id"));
                      if ($(liId).prev().size() == 0 && $(liId).next().size() > 0) {
                          $("#moveup"+$(liId).next().attr("id").substring(8)).hide();
                      }
                      if ($(liId).next().size() == 0 && $(liId).prev().size() > 0) {
                          $("#movedn"+$(liId).prev().attr("id").substring(8)).hide();
                      }
                      $(liId).remove();
                      cutNode=null;
                    }); 
      image.appendTo(thtmp);
      image = $('<a href="#"><img src="/kra-dev/static/images/tinybutton-cutnode.gif" width="79" height="15" border="0" alt="Cut Node" title="Cut this node and its child roups/sponsors.  (Node will not be removed until you paste it.)"></a>&nbsp').attr("id","cut"+i).click(function() {
                      alert("Cut node");
                       var liId="li#"+qnaireid;
                      cutNode = $(liId).clone(true);
                      removedNode=null; // remove & cutNode should not co-exist
                    }); 
      image.appendTo(thtmp);                  
      image = $('<a href="#"><img src="/kra-dev/static/images/tinybutton-copynode.gif" width="79" height="15" border="0" alt="Copy Node" title="Copy this node and its child.)"></a>&nbsp').attr("id","copy"+i).click(function() {
                      alert("Copy node");
                    }); 
      image.appendTo(thtmp);                  
      image = $('<a href="#"><img src="/kra-dev/static/images/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Paste Node" title="Paste your previously cut node structure under this node"></a>').attr("id","paste"+i).click(function() {

                   
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
                          removedNode.appendTo(ulTag);
                          idx = removedNode.attr("id").substring(8);
                         // sqlScripts = sqlScripts +"#;#"+getInsertClause(getResearchAreaCode(removedNode.children('a:eq(0)').text()), getResearchAreaCode(name), getResearchAreaDescription(removedNode.children('a:eq(0)').text()));
                          removedNode = null;
                      } else {
                          var liId = cutNode.attr("id");
                          var parentRACode;
                          $("li#"+liId).remove();
                          cutNode.appendTo(ulTag);
                          idx = cutNode.attr("id").substring(8);
                          //sqlScripts = sqlScripts +"#;#"+getInsertClause(getResearchAreaCode(cutNode.children('a:eq(0)').text()), getResearchAreaCode(name), getResearchAreaDescription(cutNode.children('a:eq(0)').text()));
                          cutNode = null;
                      }
                      ulTag.appendTo(parentNode);
                      if (ulTag.children('li').size() == 1) {
                          $("#moveup"+idx).hide();
                          $("#movedn"+idx).hide();
                      } else {
                          alert("prev "+$("#qnaireid"+idx).prev().attr("id"));
                          $("#movedn"+idx).hide();
                          $("#movedn"+$("#qnaireid"+idx).prev().attr("id").substring(8)).show();
                      }
                      //alert("Remove node "+removedNode.children('a:eq(0)').text());
                      //alert (sqlScripts);
                      
                      
                   }// if removednode                         
                   
                                         
                }); 
      image.appendTo(thtmp);                  

      thtmp.appendTo(trtmp);
      trtmp.appendTo(tbl80); // row1

      getAddQuestionRow().appendTo(tbl80); // row2
      return tbl80;
  
  }
  
  function getMoveDownLink() {
      var image = $('<img style="border:none;" alt="move down" title="Move Down" type="image" >');
      var atag = $('<a href="#"></a>').attr("id","movedn"+i).click(function() {
          alert("move down"+$(this).parents('li:eq(0)').next().size());
          var curNode = $(this).parents('li:eq(0)').clone(true);
          var nextNode = $(this).parents('li:eq(0)').next();
          $(this).parents('li:eq(0)').remove();
          curNode.insertAfter(nextNode);
          $("#moveup"+curNode.attr("id").substring(8)).show();
          $("#movedn"+nextNode.attr("id").substring(8)).show();
          if (nextNode.prev().size() == 0) {
              alert("move up next node");
              $("#moveup"+nextNode.attr("id").substring(8)).hide();
          }
          if (curNode.next().size() == 0) {
             alert ("move dn no next node ");
              $("#movedn"+curNode.attr("id").substring(8)).hide();
          }
      }); 
      image.attr("src",jsContextPath+"/static/images/jquery/arrow-down.gif");
      //alert("images "+image.attr("src"));
      atag.html(image);
      return atag;
  }
  
  function getMoveUpLink() {
      var image = $('<img style="border:none;" alt="move up" title="Move up" type="image" >');
      var atag = $('<a href="#"></a>').attr("id","moveup"+i).click(function() {
          alert("move up"+$(this).parents('li:eq(0)').prev().size());
          var curNode = $(this).parents('li:eq(0)').clone(true);
          var nextNode = $(this).parents('li:eq(0)').prev();
          $(this).parents('li:eq(0)').remove();
          curNode.insertBefore(nextNode);
          $("#movedn"+curNode.attr("id").substring(8)).show();
          $("#moveup"+nextNode.attr("id").substring(8)).show();
          if (curNode.prev().size() == 0) {
              $("#moveup"+curNode.attr("id").substring(8)).hide();
          }
          if (nextNode.next().size() == 0) {
              $("#movedn"+nextNode.attr("id").substring(8)).hide();
          }
          
      }); 
      image.attr("src",jsContextPath+"/static/images/jquery/arrow-up.gif");
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
  
     tdtmp = $('<td style="border:none;"></td>').html($('<input type="text" size="50" value="" />').attr("id","qdesc"+i));
     tdtmp.appendTo(trtmp);
     tdtmp = $('<td style="border:none; width:30px; text-align:center;"></td>');

     var atag = $('<a href="#"></a>');

     var qntag = $('<input type="hidden"/>').attr("id","qid"+i).attr("name","qid"+i);
     qntag.appendTo(tdtmp);
     var image = $('<img src="/kra-dev/static/images/searchicon.gif" border="0" class="tinybutton"  alt="Search Question" title="Search Question">').attr("id","search"+i); 
     //image.attr("name","methodToCall\.performLookup\.(!!org\.kuali\.kra\.questionnaire\.question\.Question!!)\.(((questionId:document\.newMaintainableObject\.questionId,)))\.((#document\.newMaintainableObject\.questionId:questionId,#))\.((<>))\.(([]))\.((**))\.((^^))\.((&&))\.((/rateClassTypeT/))\.((~~))\.anchor1");
     //image.attr("name","methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionId:document.newMaintainableObject.questionId,))).((#document.newMaintainableObject.questionId:questionId,#)).((<>)).(([])).((**)).((^^)).((&&)).((/rateClassTypeT/)).((~~)).anchor1");
     //image.attr("name","methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionId:document.newMaintainableObject.questionId,))).((#document.newMaintainableObject.questionId:questionId,#)).((<>)).(([])).((**)).((^^)).((&&)).((/rateClassTypeT/)).((~~)).anchor1");
     image.click(function() {
         checkToAddQn($(this).attr("id").substring(6));
         return false;
     });
     atag.html(image);
     atag.appendTo(tdtmp);
     //name="methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionId:document.newMaintainableObject.businessObject.questionnaireQuestions[0].questionId,))).((#document.newMaintainableObject.businessObject.questionnaireQuestions[0].questionId:questionId,#)).((<>)).(([])).((**)).((^^)).((&&)).((/questionId/)).((~~)).anchor1"
     image = $('<input type="image" src="/kra-dev/kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Search Question" title="Search Question" />').attr("id","search"+i);
     image.attr("name","methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionId:newQuestionId,question:newQuestion))).((#newQuestionId:questionId,#)).((<>)).(([])).((**)).((^^)).((&&)).((/questionId/)).((~~)).anchor1");
     image.appendTo(tdtmp);
     //var testlookup = $('<input type="image" tabindex="1000009" name="methodToCall\.performLookup\.(!!org\.kuali\.kra\.budget\.bo\.RateClassType!!)\.(((rateClassType:document\.newMaintainableObject\.rateClassType,)))\.((#document\.newMaintainableObject\.rateClassType:rateClassType,#))\.((<>))\.(([]))\.((**))\.((^^))\.((&&))\.((/rateClassTypeT/))\.((~~))\.anchor1" src="/kra-dev/kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Search Rate Class Type" title="Search Rate Class Type" />');
     //testlookup.appendTo(tdtmp);
  
      image = $('<input name="addquestionnaire" src="/kra-dev/kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" type="image" />').attr("id","addQn"+i).click(function() {
        //alert("This would add question "+$(".radioQn"+$(this).attr("id").substring(5)+":checked").val()+"-"+$("#newQuestionId").attr("value")+"-"+$("#newQuestion").attr("value"));  
        alert("This would add question "+$(this).parents('li:eq(0)').children('ul:eq(0)').size()+"-"+$(".radioQn"+$(this).attr("id").substring(5)+":checked").val());  
        
        
            i++;

            var listitem = getQuestionNew($("#qdesc"+$(this).attr("id").substring(5)).attr("value"), "V1.01");
			var ultag = $('<ul></ul>');
            ultag.appendTo(listitem);
            var idx = listitem.attr("id").substring(8);
            if ($(".radioQn"+$(this).attr("id").substring(5)+":checked").val() == 'sibling') {
              alert('sibling');
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
                 alert("prev "+listitem.prev().attr("id"));
                 $("#movedn"+idx).hide();
                 $("#movedn"+listitem.prev().attr("id").substring(8)).show();
              }
            }
        
        // also need this to show 'folder' icon
        $('#example').treeview({
           add: listitem
        });

        var intag = $('<input type ="text"></input>').attr("value",$("#newQuestionId").attr("value").trim());
        var name = "document\.newMaintainableObject\.businessObject\.questionnaireQuestions["+j+"]\.questionId";
        intag.attr("id",name).attr("name",name);
        j++;
        var trtmp1 = $('<tr></tr>');
        var tdtmp1 = $('<td></td>').html(intag);
        trtmp1.html(tdtmp1);
        trtmp1.appendTo($("#question-table"));
        
        
        return false;
      });
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
      var tdtmp = $('<td class="content_info" style="text-align:center;"></td>');
      var selecttmp = $('<select name="CustomData"></select>');
      $('<option value="0" selected="selected">select</option>').appendTo(selecttmp);
      $('<option value="1">and</option>').appendTo(selecttmp);
      $('<option value="2">or</option>').appendTo(selecttmp);
      selecttmp.appendTo(tdtmp);
      tdtmp.appendTo(trtmp);
      tdtmp = $('<td class="content_info" style="text-align:center;"></td>').html("Parent Response");
      responseOptions.attr("id","parentResponse"+i).appendTo(tdtmp);
      tdtmp.appendTo(trtmp);
      tdtmp = $('<td class="content_info" style="text-align:center;"></td>').html("Value:");
      $('<input type="text" size="25" />').appendTo(tdtmp);
      tdtmp.appendTo(trtmp);
      tdtmp = $('<td class="content_info" class="content_white" style="width:65px; text-align:center;"></td>');
      image = $('<input name="addquestionnairetemplate" src="/kra-dev/kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" type="image" />').click(function() {
        alert("This would add the specified requirement."+$(this).parents('tr:eq(0)').children('td:eq(0)').children('select:eq(0)').attr("value"));  
        var operator = $(this).parents('tr:eq(0)').children('td:eq(0)').children('select:eq(0)').attr("value");
        var response = $(this).parents('tr:eq(0)').children('td:eq(1)').children('select:eq(0)').attr("value");
        var value = $(this).parents('tr:eq(0)').children('td:eq(2)').children('input:eq(0)').attr("value");
        //var newResponse = getRequirementDeleteRow(sequenceNum, opArray[operator], responseArray[response], value)
        // it seems that 'tbody' is implicitly created.
        var sequence = $(this).parents('div:eq(0)').children('table:eq(1)').children('tbody').children('tr').size() +1;
        alert (sequence +"- "+operator+"-"+response+"-"+responseArray[0]);
        var newResponse = getRequirementDeleteRow(sequence, opArray[operator], responseArray[response], value);
        newResponse.appendTo($(this).parents('div:eq(0)').children('table:eq(1)').children('tbody'));
        return false;
      });

      image.appendTo(tdtmp);
      tdtmp.appendTo(trtmp);
      return trtmp;  
  }
  
  function getRequirementDeleteRow(sequenceNum, operator, response, value) {
      var trtmp = $('<tr></tr>');
      var thtmp = $('<th style="text-align:left; border-top:none; width:150px;">').html(operator);
      thtmp.appendTo(trtmp);
      var tdtmp=$('<td style="text-align:center; width:20px; border-top:none;">').html($('<div></div>').html(sequenceNum));
      tdtmp.appendTo(trtmp);
      tdtmp=$('<td style="text-align:left; border-top:none;">').html(response+" : "+value);
      tdtmp.appendTo(trtmp);
      tdtmp=$('<td class="content_white" style="text-align:center; border-top:none; width:65px;">');
      image = $('<input src="/kra-dev/kr/static/images/tinybutton-delete1.gif"  style="border:none;" alt="delete" type="image" />').click(function() {
         alert("This would delete this requirement."+$(this).parents('tr:eq(0)').next().size()); 
         var nextNode = $(this).parents('tr:eq(0)').next();
         var nextSeq = sequenceNum;
         while (nextNode.size() > 0) {
            // loop to update sequence #
            nextNode.children('td:eq(0)').children('div:eq(0)').remove();
            nextNode.children('td:eq(0)').html($('<div></div>').html(nextSeq));
            nextSeq++;
            nextNode = nextNode.next();
         }
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
			  extractUrl=url.substr(0,idx2)
			  var winPop = window.open(extractUrl+"/questionLookup.do?nodeIndex="+nodeIndex, "_blank", "width=800, height=600, scrollbars=yes");
         //} else {
         //	alert ("This node has sub sponsor group; can't add sponsors ");
         //}

    }
  
  function returnQuestion(newQuestionId, newQuestion,nodeIndex) {
     alert("return QNID "+ newQuestionId);
     $("#qid"+nodeIndex).attr("value",newQuestionId);
     $("#qdesc"+nodeIndex).attr("value",newQuestion);
  }