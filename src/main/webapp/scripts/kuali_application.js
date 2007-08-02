


function textAreaPop(text,textAreaId){
  url=window.location.href
  pathname=window.location.pathname
  idx1=url.indexOf(pathname);
  idx2=url.indexOf("/",idx1+1);
  extractUrl=url.substr(0,idx2)
  text=text.replace(/\n/g,'<br>');
  window.open(extractUrl+"/textareapop.html?" + text+"&textAreaId="+textAreaId, "_blank", "width=640, height=600, scrollbars=yes");
}

