INSERT INTO KRIM_PERM_T(PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) 
	VALUES( 1231, 
            'FC8498B022A64A18897A146C5062ECDB', 0, 
            (select perm_tmpl_id from KRIM_PERM_TMPL_T where nm = 'Questionnaire Permission'), 
            'KC-PD', 
            'Maintain Questionnaire Usage', 
            'Add/Edit Questionnaire Usages for Proposal Development', 
            'Y')
/
INSERT INTO KRIM_PERM_T(PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) 
	VALUES( 1232, 
            'FC8498B022A64A18897A146C5062ECDB', 
            0, 
           (select perm_tmpl_id from KRIM_PERM_TMPL_T where nm = 'Questionnaire Permission'), 
            'KC-PROTOCOL', 
            'Maintain Questionnaire Usage', 
            'Add/Edit Questionnaire Usages for Protocol', 
            'Y')
/


UPDATE KRNS_PARM_T SET TXT = 'Modify Protocol:KC-PROTOCOL;Maintain Questionnaire Usage:KC-PD;Maintain Questionnaire Usage:KC-PROTOCOL'
WHERE NMSPC_CD = 'KC-QUESTIONNAIRE' AND PARM_NM = 'associateModuleQuestionnairePermission'
/

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
	VALUES(857, 'FC8498B022A64A18897A146C5062ECDB', 0, 63, 1231, 'Y')
/

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
	VALUES(858, 'FC8498B022A64A18897A146C5062ECDB', 0, 63, 1232, 'Y')
/

