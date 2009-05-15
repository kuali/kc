img1 = new Image();
img1.src = closed_file;
img2 = new Image();
img2.src = open_file;

function rend(obj, cc) {
    var len = ((String)(obj.id)).indexOf('-',0)-1;
    if (len == -2)
      len = ((String)(obj.id)).length;
    var index = ((String)(obj.id)).substr(1, len);

    if(document.getElementById) {
      var grpIdx = document.getElementById("G"+index);
      var fldIdx = document.getElementById("F"+index);
      var lnkIdx = document.getElementById("A"+index);
    } else if (document.all) {
      var grpIdx = eval("document.all.G"+index);
      var fldIdx = eval("document.all.F"+index);
      var lnkIdx = eval("document.all.A"+index);
    } else {
      alert('This browser is not supported by this tree...');
      return;
    }

    if (grpIdx.style.display == 'none') {
      grpIdx.style.display = '';
      if(cc){
        fldIdx.src = open_file_cc;
      } else {
        fldIdx.src = open_file;
      }
    } else {
      grpIdx.style.display = 'none';
      if(cc){
        fldIdx.src = closed_file_cc;
      } else {
        fldIdx.src = closed_file;
      }
    }
    return;
}

function expandAll(doit, cc) {
  var index = 1;
  while (index > 0) {
    if(document.getElementById) {
      var grpIdx = document.getElementById("G"+index);
      var fldIdx = document.getElementById("F"+index);
    } else if (document.all) {
      var grpIdx = eval("document.all.G"+index);
      var fldIdx = eval("document.all.F"+index);
    }
    if (!grpIdx) {
      index = -1;
    } else {
      if (doit == "true") {
        grpIdx.style.display = '';
        if(cc && index == 1){
          fldIdx.src = open_file_cc;
        }else{
          fldIdx.src = open_file;
        }
      } else {
        grpIdx.style.display = 'none';
        if(cc && index == 1){
          fldIdx.src = closed_file_cc;
        }else{
          fldIdx.src = closed_file;
        }
      }
      index++;
    }
  }
}