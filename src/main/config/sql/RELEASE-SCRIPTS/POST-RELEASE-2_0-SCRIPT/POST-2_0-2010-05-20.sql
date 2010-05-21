DELETE FROM KRIM_ROLE_PERM_T where role_id is null;

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Approver' AND NMSPC_CD = 'KC-AB'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View AwardBudget' AND NMSPC_CD = 'KC-AB'),
'Y','77151AD4B38A9985E0404F8189D80E84',1);

ALTER TABLE KRIM_ENTITY_T MODIFY (LAST_UPDT_DT NULL);                                    
ALTER TABLE KRIM_ENT_NM_TYP_T MODIFY (LAST_UPDT_DT NULL);                                
ALTER TABLE KRIM_EXT_ID_TYP_T MODIFY (LAST_UPDT_DT NULL);                                
ALTER TABLE KRIM_GRP_T MODIFY (LAST_UPDT_DT NULL);                                       
ALTER TABLE KRIM_ENTITY_EMAIL_T MODIFY (LAST_UPDT_DT NULL);                              
ALTER TABLE KRIM_ENTITY_ENT_TYP_T MODIFY (LAST_UPDT_DT NULL);                            
ALTER TABLE KRIM_ENTITY_NM_T MODIFY (LAST_UPDT_DT NULL);                                 
ALTER TABLE KRIM_DLGN_MBR_T MODIFY (LAST_UPDT_DT NULL);                                  
ALTER TABLE KRIM_ENTITY_ADDR_T MODIFY (LAST_UPDT_DT NULL);                               
ALTER TABLE KRIM_ENTITY_AFLTN_T MODIFY (LAST_UPDT_DT NULL);                              
ALTER TABLE KRIM_ENTITY_BIO_T MODIFY (LAST_UPDT_DT NULL);                                
ALTER TABLE KRIM_ENTITY_CTZNSHP_T MODIFY (LAST_UPDT_DT NULL);                            
ALTER TABLE KRIM_ENTITY_EMP_INFO_T MODIFY (LAST_UPDT_DT NULL);                           
ALTER TABLE KRIM_ENTITY_EXT_ID_T MODIFY (LAST_UPDT_DT NULL);                             
ALTER TABLE KRIM_ENTITY_PHONE_T MODIFY (LAST_UPDT_DT NULL);                              
ALTER TABLE KRIM_ADDR_TYP_T MODIFY (LAST_UPDT_DT NULL);                                  
ALTER TABLE KRIM_AFLTN_TYP_T MODIFY (LAST_UPDT_DT NULL);                                 
ALTER TABLE KRIM_EMAIL_TYP_T MODIFY (LAST_UPDT_DT NULL);                                 
ALTER TABLE KRIM_EMP_STAT_T MODIFY (LAST_UPDT_DT NULL);                                  
ALTER TABLE KRIM_EMP_TYP_T MODIFY (LAST_UPDT_DT NULL);                                   
ALTER TABLE KRIM_GRP_MBR_T MODIFY (LAST_UPDT_DT NULL);                                   
ALTER TABLE KRIM_PHONE_TYP_T MODIFY (LAST_UPDT_DT NULL);                                 
ALTER TABLE KRIM_PRNCPL_T MODIFY (LAST_UPDT_DT NULL);                                    
ALTER TABLE KRIM_ROLE_T MODIFY (LAST_UPDT_DT NULL);                                      
ALTER TABLE KRIM_CTZNSHP_STAT_T MODIFY (LAST_UPDT_DT NULL);                              
ALTER TABLE KRIM_ENTITY_PRIV_PREF_T MODIFY (LAST_UPDT_DT NULL);                          
