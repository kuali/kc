
Insert into KRNS_PARM_DTL_TYP_T 
( nmspc_cd, parm_dtl_typ_cd, VER_NBR, NM, ACTV_IND)
Values ('KC-GEN','DocumentType',1,'Cusatom Attribute Document Type','Y');


INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC-GEN', 'DocumentType', 'customAttributeDocumentType', 'CONFG', 'AWRD=Award;INPR=Institutional Proposal;PRDV=Proposal Development;PROT=Protocol', 'List of Custom Attribute Document type name.', 'A') ;