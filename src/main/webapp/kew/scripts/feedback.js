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
// Feedback.jsp

// drawForm(): display popup form
function drawForm(file, name, width, height)
{
  if (! win1 || win1.closed)
  {
    var win1;
    win1 = open(file, name, "status=yes,scrollbars=yes,resizable=yes,width=" + width + ",height=" + height);
    if (! (document.all && ! document.getElementById)){
      win1.focus();
    }
  }
  else
  {
    if (! (document.all && ! document.getElementById))
    {
      win1.focus();
    }
  }
  return;
}

              // parseName(): parse user name string into first and last name strings
function parseName(string)
{

              // quit on null string
  if(string.length > 0)
  {

              // check for space delimiter
    if (string.indexOf(' ') != -1)
    {
      var indSpace = string.indexOf(' ');
      if (indSpace > 0)
      {

              // parse first name string into Falcon element
        var first = string.substring(0, indSpace);
        document.FeedbackForm.elements['contact.first'].value=first;
        if (indSpace + 1 < string.length)
        {

              // parse last name string into Falcon element
         var last = string.substring(indSpace + 1, string.length);
         document.FeedbackForm.elements['contact.last'].value=last;
        }
      }
    }

              // load entire string as first name Falcon element
    else
    {
      document.FeedbackForm.elements['contact.first'].value=string.substring(0, string.length);
    }
  }
}
