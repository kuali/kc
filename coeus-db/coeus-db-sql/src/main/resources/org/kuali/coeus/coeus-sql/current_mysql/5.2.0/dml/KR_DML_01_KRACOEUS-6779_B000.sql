DELIMITER /

UPDATE KRCR_PARM_T SET PARM_DESC_TXT = 'Set this to 2 if an error should be thrown when invalid characters are found in the file names of attachments or to 1 if a warning should be thrown instead.' WHERE PARM_NM = 'INVALID_FILE_NAME_CHECK';
/

DELIMITER ;
