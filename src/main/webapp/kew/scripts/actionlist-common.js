/*
 * Copyright 2008-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// ActionList.jsp

function setActions() {
  obj=document.forms[0].elements;
  for (i=0; i<obj.length; i++) {
    if(obj[ i ].type.indexOf('select')==0) {
      if (obj[ i ].name != document.forms[0].defaultActionToTake.name) {
        var lindex = 0;
        for (j=0; j<obj[ i ].options.length; j++) {
          if (obj[ i ].options[j].value == document.forms[0].defaultActionToTake.options[document.forms[0].defaultActionToTake.selectedIndex].value) {
              lindex = j;
          }
        }
        obj[ i ].selectedIndex = lindex;
      }
    }
  }
}

function setRowMouseListeners() {
	var rowCounter = 0;
	var idPrefix = "actionlist_tr_";
	var rowClassNames = new Object();
	var currentRow = document.getElementById(idPrefix + rowCounter);
	while (currentRow != null) {
		rowClassNames[idPrefix + rowCounter] = currentRow.className;
		currentRow.onmouseover = function() { this.className = "over"; };
		currentRow.onmouseout = function () { this.className = rowClassNames[this.id]; };
		rowCounter++;
		currentRow = document.getElementById(idPrefix + rowCounter);
	}
}

setTimeout(setRowMouseListeners, 0);
