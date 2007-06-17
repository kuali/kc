// AllNotesReport.jsp

function setNoteId(methodToCall, noteId)
{
    document.forms[0].elements['methodToCall'].value = methodToCall;
    document.forms[0].elements['noteIdNumber'].value = noteId;
}