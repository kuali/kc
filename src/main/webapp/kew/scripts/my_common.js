/*
 * Copyright 2007-2009 The Kuali Foundation
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
window.onerror=myErrorHandler;
function myErrorHandler() {
    return true;
}
function getDocHeight(doc) {
  var docHt = 0, sh, oh;
  if (doc.height) {
    docHt = doc.height;
  } else if (doc.body) {
    if (doc.body.offsetHeight) { docHt = oh = 0; } //doc.body.offsetHeight;
    if (doc.body.scrollHeight) { docHt = sh = doc.body.scrollHeight; }
    if (sh && oh) { docHt = Math.max(sh, oh); }
  }
  return docHt;
}
function setIframeHeight(iframeName, pubHeight) {
  var iframeWin = window.frames[iframeName];
  var iframeEl = document.getElementById? document.getElementById(iframeName): document.all? document.all[iframeName]: null;
  if (!iframeEl) {
    iframeEl = document.getElementById('focused_frame');
  }
  if ( iframeEl && iframeWin ) {
    iframeEl.style.height = pubHeight + "px";
    var docHt = getDocHeight(iframeWin.document);
    if (docHt) {
      if(docHt>pubHeight){
      	iframeEl.style.height = docHt + 30 + "px";
      }
    }
  }
}
function setFocusedIframeHeight(iframeName, focusHeight) {
  var iframeWin = window.frames[iframeName];
  var iframeEl = document.getElementById? document.getElementById(iframeName): document.all? document.all[iframeName]: null;
  if (!iframeEl) {
    iframeEl = document.getElementById('focused_frame');
  }
  if ( iframeEl && iframeWin ) {
    iframeEl.style.height = focusHeight + "px";
    var docHt = getDocHeight(iframeWin.document);
    if (docHt && docHt > focusHeight) {
      iframeEl.style.height = docHt + 30 + "px";
    }
  }
}
var isChanged = 0;
function ieKeyPress() {
   return;
}
function reloadChannelFromKey(key) {
  if (window.opener != null) {
    eval("window.opener.iframe_" + key + ".location.reload();");
  }
}
function post_to_action(formname, action) {
  document.forms[formname].action = action; 
  document.forms[formname].submit();
  return;
}
function drawForm(file, name, width, height){
  if (!win1 || win1.closed) {
    var win1;
    win1 = open(file,name,"status=yes,scrollbars=yes,resizable=yes,width=" + width + ",height=" + height);
    if (!(document.all && !document.getElementById))
      win1.focus();
  } else {
    if (!(document.all && !document.getElementById))
      win1.focus();
  }
  return;
}
function openWithToolbar(url, width, height) {
  window.open(url, "_blank", "toolbar=yes,status=yes,location=yes,menubar=yes,scrollbars=yes,resizable=yes,directories=yes,width=" + width + ",height=" + height);
  return;
}
function my_o(url, name) {
  openWithToolbar(url, 800, 600);
  return;
}
function replace(argvalue, x, y) {
  if ((x == y) || (parseInt(y.indexOf(x)) > -1)) {
    return false;
  }

  while (argvalue.indexOf(x) != -1) {
    var leading = argvalue.substring(0, argvalue.indexOf(x));
    var trailing = argvalue.substring(argvalue.indexOf(x) + x.length, argvalue.length);
    argvalue = leading + y + trailing;
  }
  return argvalue;
}
