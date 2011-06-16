INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES ('KC', 'KC-GEN', 'A', 'ALLOW_PROPOSAL_PERSON_TO_OVERRIDE_KC_PERSON_EXTENDED_ATTRIBUTES', 1, 'CONFG', 'Y', 
    'If Y then the proposal person citizenship type is used, if N then the kc extended attributes citizenship type is used', 'A', sys_guid())
/
commit