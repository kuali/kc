DELIMITER /
UPDATE KRCR_PARM_T SET val = 'AWRD=Award;INPR=Institutional Proposal;ICPR=IACUC Protocol;NGT=Negotiation;PERS=Person;PRDV=Proposal Development;PROT=IRB Protocol;SAWD=SubAward' WHERE NMSPC_CD = 'KC-GEN' AND PARM_NM = 'customAttributeDocumentType'
/
DELIMITER ;
