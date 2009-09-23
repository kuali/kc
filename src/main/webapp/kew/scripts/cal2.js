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
// +------------------------------------------------------------+
// |                   Popup Calendar(Window)                   |
// +------------------------------------------------------------+
// | Last Modified:                  15-Oct-2002                |
// | Web Site:                       http://www.yxscripts.com   |
// | EMail:                          m_yangxin@hotmail.com      |
// +------------------------------------------------------------+
// |       Copyright 2002  Xin Yang   All Rights Reserved.
// |       This version featured on Dynamic Drive (http://www.dynamicdrive.com)
// +------------------------------------------------------------+

// default settings
var fontFace="verdana";
var fontSize=9;

var titleWidth=90;
var titleMode=1;
var dayWidth=12;
var dayDigits=1;

var titleColor="#999999";//"#999933;"
var daysColor="#E6E6E6";
var bodyColor="#ffffff";
var dayColor="#ffffff";
var currentDayColor="#999999";//"#999933;"//"#934C4C";
var footColor="#999999";//"#999933;"//"#E6E6E6";
var borderColor="#333333";

var titleFontColor = "#333333";//"#ffffff";
var daysFontColor = "#333333";
var dayFontColor = "#333333";
var currentDayFontColor = "#333333";//"#ffffff";
var footFontColor = "#333333";

var calFormat = "mm/dd/yyyy";

var weekDay = 0;
// ------

// codes
var calWidth=200, calHeight=200, calOffsetX=40, calOffsetY=10;
var calWin=null;
var winX=0, winY=0;
var cal="cal";
var cals=new Array();
var currentCal=null;

var yxMonths=new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
var yxDays=new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
var yxLinks=new Array("[close]", "[clear]");

var isOpera=(navigator.userAgent.indexOf("Opera")!=-1)?true:false;
var isOpera5=(navigator.appVersion.indexOf("MSIE 5")!=-1 && navigator.userAgent.indexOf("Opera 5")!=-1)?true:false;
var isOpera6=(navigator.appVersion.indexOf("MSIE 5")!=-1 && navigator.userAgent.indexOf("Opera 6")!=-1)?true:false;
var isN6=(navigator.userAgent.indexOf("Gecko")!=-1);
var isN4=(document.layers)?true:false;
var isMac=(navigator.userAgent.indexOf("Mac")!=-1);
var isIE=(document.all && !isOpera && (!isMac || navigator.appVersion.indexOf("MSIE 4")==-1))?true:false;

if (isN4) {
  fontSize+=2;
}

var span2="</span>";

function span1(tag) {
  return "<span class='"+tag+"'>";
}
function spanx(tag, color) {
  return "."+tag+" { font-family:"+fontFace+"; font-size:"+fontSize+"px; color:"+color+"; }\n";
}

function a1(tag) {
  return "<a class='"+tag+"' href=";
}

function ax(tag, color) {
  return "."+tag+" { text-decoration:none; color:"+color+"; }\n";
}

function calOBJ(name, title, field, form) {
  this.name = name;
  this.title = title;
  this.field = field;
  this.formName = form;
  this.form = null
}

function setFont(font, size) {
  if (font != "") {
    fontFace=font;
  }
  if (size > 0) {
    fontSize=size;

    if (isN4) {
      fontSize+=2;
    }
  }
}

function setWidth(tWidth, tMode, dWidth, dDigits) {
  if (tWidth > 0) {
    titleWidth=tWidth;
  }
  if (tMode == 1 || tMode == 2) {
    titleMode=tMode;
  }
  if (dWidth > 0) {
    dayWidth=dWidth;
  }
  if (dDigits > 0) {
    dayDigits=dDigits;
  }
}

function setColor(tColor, dsColor, bColor, dColor, cdColor, fColor, bdColor) {
  if (tColor != "") {
    titleColor=tColor;
  }
  if (dsColor != "") {
    daysColor=dsColor;
  }
  if (bColor != "") {
    bodyColor=bColor;
  }
  if (dColor != "") {
    dayColor=dColor;
  }
  if (cdColor != "") {
    currentDayColor=cdColor;
  }
  if (fColor != "") {
    footColor=fColor;
  }
  if (bdColor != "") {
    borderColor=bdColor;
  }
}

function setFontColor(tColorFont, dsColorFont, dColorFont, cdColorFont, fColorFont) {
  if (tColorFont != "") {
    titleFontColor=tColorFont;
  }
  if (dsColorFont != "") {
    daysFontColor=dsColorFont;
  }
  if (dColorFont != "") {
    dayFontColor=dColorFont;
  }
  if (cdColorFont != "") {
    currentDayFontColor=cdColorFont;
  }
  if (fColorFont != "") {
    footFontColor=fColorFont;
  }
}

function setFormat(format) {
  calFormat = format;
}

function setSize(width, height, ox, oy) {
  if (width > 0) {
    calWidth=width;
  }
  if (height > 0) {
    calHeight=height;
  }

  calOffsetX=ox;
  calOffsetY=oy;
}

function setWeekDay(wDay) {
  if (wDay == 0 || wDay == 1) {
    weekDay = wDay;
  }
}

function setMonthNames(janName, febName, marName, aprName, mayName, junName, julName, augName, sepName, octName, novName, decName) {
  if (janName != "") {
    yxMonths[0] = janName;
  }
  if (febName != "") {
    yxMonths[1] = febName;
  }
  if (marName != "") {
    yxMonths[2] = marName;
  }
  if (aprName != "") {
    yxMonths[3] = aprName;
  }
  if (mayName != "") {
    yxMonths[4] = mayName;
  }
  if (junName != "") {
    yxMonths[5] = junName;
  }
  if (julName != "") {
    yxMonths[6] = julName;
  }
  if (augName != "") {
    yxMonths[7] = augName;
  }
  if (sepName != "") {
    yxMonths[8] = sepName;
  }
  if (octName != "") {
    yxMonths[9] = octName;
  }
  if (novName != "") {
    yxMonths[10] = novName;
  }
  if (decName != "") {
    yxMonths[11] = decName;
  }
}

function setDayNames(sunName, monName, tueName, wedName, thuName, friName, satName) {
  if (sunName != "") {
    yxDays[0] = sunName;
    yxDays[7] = sunName;
  }
  if (monName != "") {
    yxDays[1] = monName;
  }
  if (tueName != "") {
    yxDays[2] = tueName;
  }
  if (wedName != "") {
    yxDays[3] = wedName;
  }
  if (thuName != "") {
    yxDays[4] = thuName;
  }
  if (friName != "") {
    yxDays[5] = friName;
  }
  if (satName != "") {
    yxDays[6] = satName;
  }
}

function setLinkNames(closeLink, clearLink) {
  if (closeLink != "") {
    yxLinks[0] = closeLink;
  }
  if (clearLink != "") {
    yxLinks[1] = clearLink;
  }
}

function addCalendar(name, title, field, form) {
  cals[cals.length] = new calOBJ(name, title, field, form);
}

function findCalendar(name) {
  for (var i = 0; i < cals.length; i++) {
    if (cals[i].name == name) {
      if (cals[i].form == null) {
        if (cals[i].formName == "") {
          if (document.forms[0]) {
            cals[i].form = document.forms[0];
          }
        }
        else if (document.forms[cals[i].formName]) {
          cals[i].form = document.forms[cals[i].formName];
        }
      }

      return cals[i];
    }
  }

  return null;
}

function getDayName(y,m,d) {
  var wd=new Date(y,m,d);
  return yxDays[wd.getDay()].substring(0,3);
}

function getMonthFromName(m3) {
  for (var i = 0; i < yxMonths.length; i++) {
    if (yxMonths[i].toLowerCase().substring(0,3) == m3.toLowerCase()) {
      return i;
    }
  }

  return 0;
}

function getFormat() {
  var calF = calFormat;

  calF = calF.replace(/\\/g, '\\\\');
  calF = calF.replace(/\//g, '\\\/');
  calF = calF.replace(/\[/g, '\\\[');
  calF = calF.replace(/\]/g, '\\\]');
  calF = calF.replace(/\(/g, '\\\(');
  calF = calF.replace(/\)/g, '\\\)');
  calF = calF.replace(/\{/g, '\\\{');
  calF = calF.replace(/\}/g, '\\\}');
  calF = calF.replace(/\</g, '\\\<');
  calF = calF.replace(/\>/g, '\\\>');
  calF = calF.replace(/\|/g, '\\\|');
  calF = calF.replace(/\*/g, '\\\*');
  calF = calF.replace(/\?/g, '\\\?');
  calF = calF.replace(/\+/g, '\\\+');
  calF = calF.replace(/\^/g, '\\\^');
  calF = calF.replace(/\$/g, '\\\$');

  calF = calF.replace(/dd/i, '\\d\\d');
  calF = calF.replace(/mm/i, '\\d\\d');
  calF = calF.replace(/yyyy/i, '\\d\\d\\d\\d');
  calF = calF.replace(/day/i, '\\w\\w\\w');
  calF = calF.replace(/mon/i, '\\w\\w\\w');

  return new RegExp(calF);
}

function getDateNumbers(date) {
  var y, m, d;

  var yIdx = calFormat.search(/yyyy/i);
  var mIdx = calFormat.search(/mm/i);
  var m3Idx = calFormat.search(/mon/i);
  var dIdx = calFormat.search(/dd/i);

  y=date.substring(yIdx,yIdx+4)-0;
  if (mIdx != -1) {
    m=date.substring(mIdx,mIdx+2)-1;
  }
  else {
    var m = getMonthFromName(date.substring(m3Idx,m3Idx+3));
  }
  d=date.substring(dIdx,dIdx+2)-0;

  return new Array(y,m,d);
}

function hideCal() {
  calWin.close();
  calWin = null;
  window.status = "";
}

function getLeftIE(x,m) {
  var dx=0;
  if (x.tagName=="TD"){
    dx=x.offsetLeft;
  }
  else if (x.tagName=="TABLE") {
    dx=x.offsetLeft;
    if (m) { dx+=(x.cellPadding!=""?parseInt(x.cellPadding):2); m=false; }
  }
  return dx+(x.parentElement.tagName=="BODY"?0:getLeftIE(x.parentElement,m));
}
function getTopIE(x,m) {
  var dy=0;
  if (x.tagName=="TR"){
    dy=x.offsetTop;
  }
  else if (x.tagName=="TABLE") {
    dy=x.offsetTop;
    if (m) { dy+=(x.cellPadding!=""?parseInt(x.cellPadding):2); m=false; }
  }
  return dy+(x.parentElement.tagName=="BODY"?0:getTopIE(x.parentElement,m));
}

function getLeftN4(l) { return l.pageX; }
function getTopN4(l) { return l.pageY; }

function getLeftN6(l) { return l.offsetLeft; }
function getTopN6(l) { return l.offsetTop; }

function lastDay(d) {
  var yy=d.getFullYear(), mm=d.getMonth();
  for (var i=31; i>=28; i--) {
    var nd=new Date(yy,mm,i);
    if (mm == nd.getMonth()) {
      return i;
    }
  }
}

function firstDay(d) {
  var yy=d.getFullYear(), mm=d.getMonth();
  var fd=new Date(yy,mm,1);
  return fd.getDay();
}

function dayDisplay(i) {
  if (dayDigits == 0) {
    return yxDays[i];
  }
  else {
    return yxDays[i].substring(0,dayDigits);
  }
}

function calTitle(d) {
  var yy=d.getFullYear(), mm=yxMonths[d.getMonth()];
  var s;

  if (titleMode == 2) {
    s="<tr align='center' bgcolor='"+titleColor+"'><td colspan='7'>\n<table cellpadding='0' cellspacing='0' border='0'><tr align='center' valign='middle'><td align='right'>"+span1("title")+"<b>"+a1("titlea")+"'javascript:if(window.opener && !window.opener.closed && window.opener.moveYear) window.opener.moveYear(-10)'>&nbsp;&#171;</a>&nbsp;"+a1("titlea")+"'javascript:if(window.opener && !window.opener.closed && window.opener.moveYear) window.opener.moveYear(-1)'>&#139;&nbsp;</a></b>"+span2+"</td><td width='"+titleWidth+"'><b>"+span1("title")+yy+span2+"</b></td><td align='left'>"+span1("title")+"<b>"+a1("titlea")+"'javascript:if (window.opener && !window.opener.closed && window.opener.moveYear) window.opener.moveYear(1)'>&nbsp;&#155;</a>&nbsp;"+a1("titlea")+"'javascript:if (window.opener && !window.opener.closed && window.opener.moveYear) window.opener.moveYear(10)'>&#187;&nbsp;</a></b>"+span2+"</td></tr><tr align='center' valign='middle'><td align='right'>"+span1("title")+"<b>"+a1("titlea")+"'javascript:if (window.opener && !window.opener.closed && window.opener.prepMonth) window.opener.prepMonth("+d.getMonth()+")'>&nbsp;&#139;&nbsp;</a></b>"+span2+"</td><td width='"+titleWidth+"'><b>"+span1("title")+mm+span2+"</b></td><td align='left'>"+span1("title")+"<b>"+a1("titlea")+"'javascript:if (window.opener && !window.opener.closed && window.opener.nextMonth) window.opener.nextMonth("+d.getMonth()+")'>&nbsp;&#155;&nbsp;</a></b>"+span2+"</td></tr></table>\n</td></tr><tr align='center' bgcolor='"+daysColor+"'>";
  }
  else {
    s="<tr align='center' bgcolor='"+titleColor+"'><td colspan='7'>\n<table cellpadding='0' cellspacing='0' border='0'><tr align='center' valign='middle'><td>"+span1("title")+"<b>"+a1("titlea")+"'javascript:if(window.opener && !window.opener.closed && window.opener.moveYear) window.opener.moveYear(-1)'>&nbsp;&#171;</a>&nbsp;"+a1("titlea")+"'javascript:if (window.opener && !window.opener.closed && window.opener.prepMonth) window.opener.prepMonth("+d.getMonth()+")'>&#139;&nbsp;</a></b>"+span2+"</td><td width='"+titleWidth+"'><nobr><b>"+span1("title")+mm+" "+yy+span2+"</b></nobr></td><td>"+span1("title")+"<b>"+a1("titlea")+"'javascript:if (window.opener && !window.opener.closed && window.opener.nextMonth) window.opener.nextMonth("+d.getMonth()+")'>&nbsp;&#155;</a>&nbsp;"+a1("titlea")+"'javascript:if(window.opener && !window.opener.closed && window.opener.moveYear) window.opener.moveYear(1)'>&#187;&nbsp;</a></b>"+span2+"</td></tr></table>\n</td></tr><tr align='center' bgcolor='"+daysColor+"'>";
  }

  for (var i=weekDay; i<weekDay+7; i++) {
    s+="<td width='"+dayWidth+"'>"+span1("days")+dayDisplay(i)+span2+"</td>";
  }

  s+="</tr>";

  return s;
}

function calHeader() {
  return "<head>\n<title>"+currentCal.title+"</title>\n<style type='text/css'>\n"+spanx("title",titleFontColor)+spanx("days",daysFontColor)+spanx("foot",footColor)+spanx("day",dayFontColor)+spanx("currentDay",currentDayFontColor)+ax("titlea",titleFontColor)+ax("daya",dayFontColor)+ax("currenta",currentDayFontColor)+ax("foota",footFontColor)+"</style>\n</head>\n<body>\n<table align='center' border='0' bgcolor='"+borderColor+"' cellspacing='0' cellpadding='1'><tr><td>\n<table cellspacing='1' cellpadding='3' border='0'>";
}

function calFooter() {
  return "<tr bgcolor='"+footColor+"'><td colspan='7' align='center'>"+span1("foot")+"<b>"+a1("foota")+"'javascript:if (window.opener && !window.opener.closed && window.opener.hideCal) window.opener.hideCal()'>"+yxLinks[0]+"</a>&nbsp;&nbsp;"+a1("foota")+"'javascript:if (window.opener && !window.opener.closed && window.opener.clearDate) window.opener.clearDate()'>"+yxLinks[1]+"</a></b>"+span2+"</td></tr></table>\n</td></tr></table>\n</body>";
}

function calBody(d,day) {
  var s="", dayCount=1, fd=firstDay(d), ld=lastDay(d);

  if (weekDay > 0 && fd == 0) {
    fd = 7;
  }

  for (var i=0; i<6; i++) {
    s+="<tr align='center' bgcolor='"+bodyColor+"'>";
    for (var j=weekDay; j<weekDay+7; j++) {
      if (i*7+j<fd || dayCount>ld) {
        s+="<td>"+span1("day")+"&nbsp;"+span2+"</td>";
      }
      else {
        var bgColor=dayColor;
        var fgTag="day";
        var fgTagA="daya";
        if (dayCount==day) {
          bgColor=currentDayColor;
          fgTag="currentDay";
          fgTagA="currenta";
        }

        s+="<td bgcolor='"+bgColor+"'>"+span1(fgTag)+a1(fgTagA)+"'javascript: if (window.opener && !window.opener.closed && window.opener.pickDate) window.opener.pickDate("+dayCount+")'>"+(dayCount++)+"</a>"+span2+"</td>";
      }
    }
    s+="</tr>";
  }

  return s;
}

function moveYear(dy) {
  cY+=dy;
  var nd=new Date(cY,cM,1);
  changeCal(nd);
}

function prepMonth(m) {
  cM=m-1;
  if (cM<0) { cM=11; cY--; }
  var nd=new Date(cY,cM,1);
  changeCal(nd);
}

function nextMonth(m) {
  cM=m+1;
  if (cM>11) { cM=0; cY++;}
  var nd=new Date(cY,cM,1);
  changeCal(nd);
}

function changeCal(d) {
  var dd = 0;

  if (currentCal != null) {
    var calRE = getFormat();

    if (currentCal.form[currentCal.field].value!="" && calRE.test(currentCal.form[currentCal.field].value)) {
      var cd = getDateNumbers(currentCal.form[currentCal.field].value);
      if (cd[0] == d.getFullYear() && cd[1] == d.getMonth()) {
        dd=cd[2];
      }
    }
    else {
      var cd = new Date();
      if (cd.getFullYear() == d.getFullYear() && cd.getMonth() == d.getMonth()) {
        dd=cd.getDate();
      }
    }
  }

  var calendar=calHeader()+calTitle(d)+calBody(d,dd)+calFooter();

  calWin.document.open();
  calWin.document.write(calendar);
  calWin.document.close();
}

function markClick(e) {
  if (isIE || isOpera6) {
    winX=event.screenX;
    winY=event.screenY;
  }
  else if (isN4 || isN6) {
    winX=e.screenX;
    winY=e.screenY;

    document.routeEvent(e);
  }

  return true;
}

function showCal(name) {
  var lastCal=currentCal;
  var d=new Date(), hasCal=false;
  
  currentCal = findCalendar(name);

  if (currentCal != null && currentCal.form != null && currentCal.form[currentCal.field]) {
    var calRE = getFormat();
    if (currentCal.form[currentCal.field].value!="" && calRE.test(currentCal.form[currentCal.field].value)) {
      var cd = getDateNumbers(currentCal.form[currentCal.field].value);
      d=new Date(cd[0],cd[1],cd[2]);

      cY=cd[0];
      cM=cd[1];
      dd=cd[2];
    }
    else {
      cY=d.getFullYear();
      cM=d.getMonth();
      dd=d.getDate();
    }

    var calendar=calHeader()+calTitle(d)+calBody(d,dd)+calFooter();
    var newCalOffsetX = calOffsetX;
    var newCalOffsetY = calOffsetY; 

  if (window.screen) {
    var ah = screen.availHeight - 30;
    var aw = screen.availWidth - 10;
    var xw = winX + calWidth + calOffsetX;
    var yh = winY + calHeight + calOffsetY;

    if (xw > aw) {
      newCalOffsetX = - (calOffsetX + calWidth);
    }

    if (yh > ah) {
      newCalOffsetY = - (calOffsetY + calHeight);
    }

  } 

    if (calWin != null && !calWin.closed) {
      hasCal=true;
      calWin.moveTo(winX+newCalOffsetX,winY+newCalOffsetY);
    }

    if (!hasCal) {
      if (isIE || isOpera6) {
        calWin=window.open("","cal","toolbar=0,width="+calWidth+",height="+calHeight+",left="+(winX+newCalOffsetX)+",top="+(winY+newCalOffsetY));
      }
      else {
        calWin=window.open("","cal","toolbar=0,width="+calWidth+",height="+calHeight+",screenx="+(winX+newCalOffsetX)+",screeny="+(winY+newCalOffsetY));
      }
    }

    calWin.document.open();
    calWin.document.write(calendar);
    calWin.document.close();

    calWin.focus();
  }
  else {
    if (currentCal == null) {
      window.status = "Calendar ["+name+"] not found.";
    }
    else if (!currentCal.form) {
      window.status = "Form ["+currentCal.formName+"] not found.";
    }
    else if (!currentCal.form[currentCal.field]) {
      window.status = "Form Field ["+currentCal.formName+"."+currentCal.field+"] not found.";
    }

    if (lastCal != null) {
      currentCal = lastCal;
    }
  }
}

function get2Digits(n) {
  return ((n<10)?"0":"")+n;
}

function clearDate() {
  currentCal.form[currentCal.field].value="";
  hideCal();
}

function pickDate(d) {
  hideCal();
  window.focus();

  var date=calFormat;
  date = date.replace(/yyyy/i, cY);
  date = date.replace(/mm/i, get2Digits(cM+1));
  date = date.replace(/MON/, yxMonths[cM].substring(0,3).toUpperCase());
  date = date.replace(/Mon/i, yxMonths[cM].substring(0,3));
  date = date.replace(/dd/i, get2Digits(d));
  date = date.replace(/DAY/, getDayName(cY,cM,d).toUpperCase());
  date = date.replace(/day/i, getDayName(cY,cM,d));

  currentCal.form[currentCal.field].value=date;
  // IE5/Mac needs focus to show the value, weird.
  currentCal.form[currentCal.field].focus();
}
// ------

// user functions
function checkDate(name) {
  var thisCal = findCalendar(name);

  if (thisCal != null && thisCal.form != null && thisCal.form[thisCal.field]) {
    var calRE = getFormat();

    if (calRE.test(thisCal.form[thisCal.field].value)) {
      return 0;
    }
    else {
      return 1;
    }
  }
  else {
    return 2;
  }
}

function getCurrentDate() {
  var date=calFormat, d = new Date();
  date = date.replace(/yyyy/i, d.getFullYear());
  date = date.replace(/mm/i, get2Digits(d.getMonth()+1));
  date = date.replace(/dd/i, get2Digits(d.getDate()));

  return date;
}

function compareDates(date1, date2) {
  var calRE = getFormat();
  var d1, d2;

  if (calRE.test(date1)) {
    d1 = getNumbers(date1);
  }
  else {
    d1 = getNumbers(getCurrentDate());
  }

  if (calRE.test(date2)) {
    d2 = getNumbers(date2);
  }
  else {
    d2 = getNumbers(getCurrentDate());
  }

  var dStr1 = d1[0] + "" + d1[1] + "" + d1[2];
  var dStr2 = d2[0] + "" + d2[1] + "" + d2[2];

  if (dStr1 == dStr2) {
    return 0;
  }
  else if (dStr1 > dStr2) {
    return 1;
  }
  else {
    return -1;
  }
}

function getNumbers(date) {
  var calRE = getFormat();
  var y, m, d;

  if (calRE.test(date)) {
    var yIdx = calFormat.search(/yyyy/i);
    var mIdx = calFormat.search(/mm/i);
    var m3Idx = calFormat.search(/mon/i);
    var dIdx = calFormat.search(/dd/i);

    y=date.substring(yIdx,yIdx+4);
    if (mIdx != -1) {
      m=date.substring(mIdx,mIdx+2);
    }
    else {
      var mm=getMonthFromName(date.substring(m3Idx,m3Idx+3))+1;
      m=(mm<10)?("0"+mm):(""+mm);
    }
    d=date.substring(dIdx,dIdx+2);

    return new Array(y,m,d);
  }
  else {
    return new Array("", "", "");
  }
}
// ------

if (isN4 || isN6) {
  document.captureEvents(Event.CLICK);
}
document.onclick=markClick;
