<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="readOnly" value="false"  scope="request"/>
<%-- example to register for security reason 
<c:set var="epMethodToCallAttribute" value="methodToCall.performLookup.(!!org.kuali.kra.budget.bo.RateClassType!!).(((rateClassType:document.newMaintainableObject.rateClassType,))).((#document.newMaintainableObject.rateClassType:rateClassType,#)).((<>)).(([])).((**)).((^^)).((&&)).((/rateClassTypeT/)).((~~)).anchor1"/>
${kfunc:registerEditableProperty(KualiForm, epMethodToCallAttribute)} 
--%>

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Content </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.questionnaire.Questionnaire" altText="help"/> </span>
    </h3>
        
        
  <table id="question-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
   <tr>
     <td>  
       Question : 
           <%-- <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.questionnaireQuestions[0].questionId" --%> 
           <kul:htmlControlAttribute property="newQuestionId" 
                                          attributeEntry="${DataDictionary.QuestionnaireQuestion.attributes.questionId}"  />
           <input type="hidden" name="newQuestion" id="newQuestion" value="${KualiForm.newQuestion}"/>
      </td>        
      <td>
          <input src="static/images/tinybutton-add1.gif" onclick="javascript: addQuestion(); return false;" style="border:none;" alt="add question" title="Add Question" type="image" />
      </td>
    </tr>
  
  <tr>
  <td colspan="2">     
                                      <!-- Section Controls -->
                                	<div style="padding-bottom:5px;">
										<table width="100%" cellpadding="0" cellspacing="0" class="subelement" style="border-top:solid 1px #939393;">
                                            <tr>
                                                <td class="content_grey" style="text-align:right; width:190px;">
                                                    Hierarchy Display Options:
                                                </td>
                                                <td class="content_white" style="text-align:center;">
                                            <div id="treecontrol" style="display:inline;">
                                                <a title="Collapse the entire tree below" href="#"><img src="/${fn:trim(ConfigProperties.app.context.name)}/static/images/jquery/minus.gif" /> Collapse All</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;<a title="Expand the entire tree below" href="#"><img src="/${fn:trim(ConfigProperties.app.context.name)}/static/images/jquery/plus.gif" /> Expand All</a>
                                            </div>
                                                    &nbsp;&nbsp;&nbsp;&nbsp;<input src="/${fn:trim(ConfigProperties.app.context.name)}/static/images/tinybutton-refresh.gif" onclick="javascript: alert('This would refresh the hierarchies in this questionnaire for non-script browsers.'); return false;" style="border:none;" alt="refresh" title="Refresh" type="image" />
                                                </td>
                                                <td class="content_grey" style="text-align:right;">
                                                    Add Question(s):
                                                </td>
                                                <td class="content_white" style="text-align:center;">
                                                    <input name = "rootSearch" id = "rootSearch" src="/${fn:trim(ConfigProperties.app.context.name)}/static/images/searchicon.gif" align="top" alt="search " title="Search " style="border:none;" type="image" />
                                                    <input name="addRootQn" id="addRootQn" src="/kra-dev/kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" type="image" />;
                                                </td>
                                            </tr>
                                        </table> 
                                        
                                    </div>
  
  </td>
  </tr>
  
  <tr> 
  <td colspan="2">     
  <ul id="example" class="filetree">
		<li><span class="folder">Folder 1</span>
			<ul>
				<li><span class="file">Item 1.1</span></li>
			</ul>
		</li>
		<li><span class="folder">Folder 2</span>
			<ul>
				<li><span class="folder">Subfolder 2.1</span>
					<ul>
						<li><span class="file">File 2.1.1</span></li>
						<li><span class="file">File 2.1.2</span></li>
					</ul>
				</li>
				<li><span class="file">File 2.2</span></li>
			</ul>
		</li>
		<li class="closed"><span class="folder">Folder 3 (closed at start)</span>
			<ul id="file31" class="filetree">
				<!-- <li><span class="file">File 3.1</span></li> -->
			</ul>
		</li>
		<li><span class="file">File 4</span></li>
	</ul>
    </td>
   </tr>
   <tr>
       <input name="aq01sc" type="radio" checked="checked" />as sibling&nbsp;&nbsp;&nbsp;
       <input name="aq01sc" type="radio" />as child
       <input type="image" tabindex="1000009" name="methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionId:document.newMaintainableObject.businessObject.questionnaireQuestions[0].questionId,))).((#document.newMaintainableObject.businessObject.questionnaireQuestions[0].questionId:questionId,#)).((<>)).(([])).((**)).((^^)).((&&)).((/questionId/)).((~~)).anchor1"
   src="/kra-dev/kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Search Question" title="Search Question" />
       
       <kul:multipleValueLookup boClassName="org.kuali.kra.questionnaire.question.Question" 
                                                                 lookedUpCollectionName="questionnaireQuestions"
                                                                 anchor="${currentTabIndex}.qnIndex${memberIndex}"/>
yes.        
   </tr>
   <%-- Test: add new question, when it return question lookup, the dynamically qn are still ok--%>
   <c:set var="index" value="0"/>
   <c:forEach var="question" items="${KualiForm.document.newMaintainableObject.businessObject.questionnaireQuestions}" varStatus="status">
     <tr> <td>
     
        <input type="text" id = "document.newMaintainableObject.businessObject.questionnaireQuestions[${status.index}].questionId" 
           name = "document.newMaintainableObject.businessObject.questionnaireQuestions[${status.index}].questionId" 
           value = "${question.questionId}"/>
		   <c:set var="index" value="${index+1}"/>
     
     </td></tr>
   
   </c:forEach>
      <input type = "hidden" id = "qncount" value = "${index}" /> 
  </table>

<%-- 
<ul>
<c:forEach var="i" begin="1" end="100" step="1" varStatus ="status">
</c:forEach>
</ul>
--%>

</div>