DELIMITER /
--
--

UPDATE KRCR_NMSPC_T SET APPL_ID = 'KC' WHERE NMSPC_CD LIKE 'KC-%'
/
UPDATE KRCR_CMPNT_T SET NM = 'Custom Attribute Document Type' WHERE NM = 'Cusatom Attribute Document Type'
/

DELIMITER ;
