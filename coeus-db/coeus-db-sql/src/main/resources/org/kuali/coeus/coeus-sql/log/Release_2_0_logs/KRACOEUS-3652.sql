Insert into KRNS_PARM_DTL_TYP_T ( nmspc_cd, parm_dtl_typ_cd, OBJ_ID, VER_NBR, NM, ACTV_IND)
Values ('KC-AB','D','D3C1CEB4A1A645EBA83AA4EAD246AA25',1,'Document','Y');
Insert into KRNS_PARM_DTL_TYP_T ( nmspc_cd, parm_dtl_typ_cd, OBJ_ID, VER_NBR, NM, ACTV_IND)
Values ('KC-AWARD','A','8D2232910ACB40B1A0054A1524F41B6C',1,'All','Y');
Insert into KRNS_PARM_DTL_TYP_T ( nmspc_cd, parm_dtl_typ_cd, OBJ_ID, VER_NBR, NM, ACTV_IND)
Values ('KC-AWARD','D','8A1FEC0A24974EA4AFE06B92DC9E0F64',1,'Document','Y');
Insert into KRNS_PARM_DTL_TYP_T ( nmspc_cd, parm_dtl_typ_cd, OBJ_ID, VER_NBR, NM, ACTV_IND)
Values ('KC-GEN','A','5BBB6B8601534307AB917F09846BC18B',1,'All','Y');
Insert into KRNS_PARM_DTL_TYP_T ( nmspc_cd, parm_dtl_typ_cd, OBJ_ID, VER_NBR, NM, ACTV_IND)
Values ('KC-GEN','D','8987E26AF90242E5B57DF760EADB9A4D',1,'Document','Y');
Insert into KRNS_PARM_DTL_TYP_T ( nmspc_cd, parm_dtl_typ_cd, OBJ_ID, VER_NBR, NM, ACTV_IND)
Values ('KC-IP','D','BCEA42AF0E7B4FB2959331D603582446',1,'Document','Y');
Insert into KRNS_PARM_DTL_TYP_T ( nmspc_cd, parm_dtl_typ_cd, OBJ_ID, VER_NBR, NM, ACTV_IND)
Values ('KC-T','D','564A1151F4F94BC389B30AE9FCAC5CAB',1,'Document','Y');
Insert into KRNS_PARM_DTL_TYP_T ( nmspc_cd, parm_dtl_typ_cd, OBJ_ID, VER_NBR, NM, ACTV_IND)
Values ('KR-IDM','PersonDocumentName','DC765D5CFF414A9DB60CCAA743D06C76',1,'Person Document Name','Y');
Insert into KRNS_PARM_DTL_TYP_T ( nmspc_cd, parm_dtl_typ_cd, OBJ_ID, VER_NBR, NM, ACTV_IND)
Values ('KR-WKFLW','DocSearchCriteriaDTO','669A821CEB5945AFA9C0CA33923997D9',1,'Document Search','Y');
update KRNS_PARM_DTL_TYP_T set PARM_DTL_TYP_CD = 'EDocLite' where NMSPC_CD='KR-WKFLW' and PARM_DTL_TYP_CD like 'EDocLite %';
