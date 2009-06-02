<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="readOnly" value="false"  scope="request"/>

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Content </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.questionnaire.Questionnaire" altText="help"/> </span>
    </h3>
        
        
  <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
   <tr>
     <td>  
       Question : 
           <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.questionnaireQuestions[0].questionId" 
                                          attributeEntry="${DataDictionary.QuestionnaireQuestion.attributes.questionId}"  />
      </td>        
      <td>
          <input src="static/images/tinybutton-add1.gif" onclick="javascript: addQuestion(); return false;" style="border:none;" alt="add question" title="Add Question" type="image" />
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
  </table>


</div>