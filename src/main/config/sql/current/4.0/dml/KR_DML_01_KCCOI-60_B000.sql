UPDATE KRCR_PARM_T 
SET VAL = CONCAT(VAL, ';Maintain Coi Disclosure:KC-COIDISCLOSURE') 
WHERE NMSPC_CD = 'KC-QUESTIONNAIRE' 
and PARM_NM = 'associateModuleQuestionnairePermission'
and CMPNT_CD = 'P'
and APPL_ID = 'KC'
/