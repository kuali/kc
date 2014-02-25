UPDATE KRNS_PARM_T SET TXT = 'default.htm?turl=Documents/additionalinformation.htm' WHERE PARM_NM = 'protocolAdditionalInformationHelpUrl';
UPDATE KRNS_PARM_T SET TXT = 'default.htm?turl=Documents/addpersonnelattachment.htm' WHERE PARM_NM = 'protocolAddPersonnelAttachmentHelpUrl';   
UPDATE KRNS_PARM_T SET TXT = 'default.htm?turl=Documents/addprotocolattachment.htm' WHERE PARM_NM = 'protocolAddProtocolAttachmentHelpUrl';
UPDATE KRNS_PARM_T SET TXT = 'default.htm?turl=Documents/customdata3.htm' WHERE PARM_NM = 'protocolCustomDataHelp';
UPDATE KRNS_PARM_T SET TXT = 'default.htm?turl=Documents/notes2.htm' WHERE PARM_NM = 'protocolNotesHelpUrl';
UPDATE KRNS_PARM_T SET TXT = 'default.htm?turl=Documents/permissions2.htm' WHERE PARM_NM = 'protocolPermissionsHelp';
UPDATE KRNS_PARM_T SET TXT = 'default.htm?turl=Documents/persondetails1.htm' WHERE PARM_NM = 'protocolPersonnelHelpUrl';
UPDATE KRNS_PARM_T SET TXT = 'default.htm?turl=Documents/requiredfieldsforsavingdocument1.htm' WHERE PARM_NM = 'protocolRequiredFieldsHelpUrl';
UPDATE KRNS_PARM_T SET TXT = 'default.htm?turl=Documents/specialreview3.htm' WHERE PARM_NM = 'protocolSpecialReviewHelp';
UPDATE KRNS_PARM_T SET TXT = 'default.htm?turl=Documents/statusdates.htm' WHERE PARM_NM = 'protocolStatusAndDatesHelpUrl';

COMMIT;