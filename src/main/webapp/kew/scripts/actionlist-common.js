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