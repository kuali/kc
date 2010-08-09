UPDATE KRIM_ATTR_DEFN_T
  SET LBL  = 'Section Name'
  WHERE NM = 'sectionName';
  
UPDATE KRIM_ATTR_DEFN_T
  SET LBL  = 'Document Action'
  WHERE NM = 'documentAction';
  
UPDATE KRIM_ATTR_DEFN_T
  SET CMPNT_NM = 'org.kuali.kra.kim.bo.KcKimAttributes'
  WHERE NM     = 'sectionName';
  
UPDATE KRIM_ATTR_DEFN_T
  SET CMPNT_NM = 'org.kuali.kra.kim.bo.KcKimAttributes'
  WHERE NM     = 'documentAction';
  
COMMIT;
