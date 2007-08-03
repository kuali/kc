


function textAreaPop(text,textAreaName,htmlFormAction,documentClassName){
  url=window.location.href
  pathname=window.location.pathname
  idx1=url.indexOf(pathname);
  idx2=url.indexOf("/",idx1+1);
  extractUrl=url.substr(0,idx2)
  text=text.replace(/\n/g,'<br>');
  window.open(extractUrl+"/TextArea.jsp?" + text+"&textAreaFieldName="+textAreaName+"&htmlFormAction="+htmlFormAction+"&documentClassName="+documentClassName, "_blank", "width=640, height=600, scrollbars=yes");
}

var textAreaFieldName
function setTextArea() {
  passData=document.location.search.substring(1);
  var idx=passData.indexOf("&textAreaFieldName=")
  var idx2=passData.indexOf("&htmlFormAction=")
  textAreaFieldName=passData.substring(idx+19,idx2)
  text=passData.substr(0,idx)
  text=text.replace(/<br>/g,"\n")
  document.getElementById(textAreaFieldName).value =unescape(text) 
//  alert (escape(text))
//  alert (unescape(text))

}

function postValueToParentWindow() {
  opener.document.getElementById(textAreaFieldName).value = document.getElementById(textAreaFieldName).value;
  self.close();
}
