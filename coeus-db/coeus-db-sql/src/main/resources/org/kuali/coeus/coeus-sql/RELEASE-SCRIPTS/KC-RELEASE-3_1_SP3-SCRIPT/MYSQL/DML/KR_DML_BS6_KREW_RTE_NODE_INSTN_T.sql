DROP PROCEDURE IF EXISTS p;
DELIMITER //
CREATE PROCEDURE p ()
BEGIN
  DECLARE brch_id INT;
  INSERT INTO KREW_RTE_NODE_S VALUES (NULL);
  SET @brch_id := (SELECT MAX(ID) FROM KREW_RTE_NODE_S);
  SET @create_rte_brch := concat('INSERT INTO KREW_RTE_BRCH_T (RTE_BRCH_ID,NM,VER_NBR) VALUES (',@brch_id,',"PRIMARY",12)');
  PREPARE CREATE_RTE_BRCH_STMT FROM @create_rte_brch;
  EXECUTE CREATE_RTE_BRCH_STMT;
  INSERT INTO KREW_RTE_NODE_S VALUES (NULL);
  SET @create_rte_node_instn := concat('INSERT INTO KREW_RTE_NODE_INSTN_T (RTE_NODE_INSTN_ID,DOC_HDR_ID,RTE_NODE_ID,BRCH_ID,ACTV_IND,CMPLT_IND,INIT_IND,VER_NBR) VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = "Proposal Person Certification"),(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = "KcSharedMaintenanceDocument")),',@brch_id,',0,1,0,5)');
  PREPARE CREATE_RTE_NODE_INSTN_STMT FROM @create_rte_node_instn;
  EXECUTE CREATE_RTE_NODE_INSTN_STMT;
END //
DELIMITER ;
CALL p ();
INSERT INTO KREW_INIT_RTE_NODE_INSTN_T (DOC_HDR_ID,RTE_NODE_INSTN_ID) 
  VALUES ((SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'Proposal Person Certification'),(SELECT MAX(ID) FROM KREW_RTE_NODE_S));

COMMIT;