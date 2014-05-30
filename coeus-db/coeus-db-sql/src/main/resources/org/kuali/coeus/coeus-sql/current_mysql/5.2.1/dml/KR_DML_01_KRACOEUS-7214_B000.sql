DELIMITER /

UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/fundingsource.htm'
WHERE APPL_ID = 'KC' AND CMPNT_CD = 'Document' AND NMSPC_CD = 'KC-SUBAWARD' AND PARM_NM = 'subAwardFundingSourceHelpUrl' AND PARM_TYP_CD = 'HELP'
/

DELIMITER ;
