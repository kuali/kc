DECLARE 
    prop_creator_role_id VARCHAR2(30);

BEGIN 
    SELECT role_id into prop_creator_role_id FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Proposal Creator';
    DECLARE CURSOR INVALID_ROLE_MBR_IDS IS 
            SELECT DISTINCT T1.ROLE_MBR_ID FROM KRIM_ROLE_MBR_ATTR_DATA_T T , KRIM_ROLE_MBR_T T1 
                WHERE T.ROLE_MBR_ID = T1.ROLE_MBR_ID AND T1.ROLE_ID = prop_creator_role_id AND T.KIM_TYP_ID 
                    NOT IN (SELECT KIM_TYP_ID FROM KRIM_ROLE_T WHERE ROLE_ID = prop_creator_role_id) ;
    
    BEGIN     
      FOR REC IN INVALID_ROLE_MBR_IDS
      LOOP
               
           DELETE FROM KRIM_ROLE_MBR_ATTR_DATA_T T WHERE T.ROLE_MBR_ID = REC.ROLE_MBR_ID;
           DELETE FROM KRIM_ROLE_MBR_T T WHERE T.ROLE_MBR_ID = REC.ROLE_MBR_ID;
           --dbms_output.put_line(REC.ROLE_MBR_ID);
                    
      END LOOP;
      COMMIT;
      END;
END;
