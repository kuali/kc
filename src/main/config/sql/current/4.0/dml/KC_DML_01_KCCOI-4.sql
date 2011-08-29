INSERT INTO FIN_INT_ENTITY_STATUS ( STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 1, 'Active', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO FIN_INT_ENTITY_STATUS ( STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 2, 'Inactive', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO FIN_INT_ENTITY_REL_TYPE ( RELATIONSHIP_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 1, 'Self', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO FIN_INT_ENTITY_REL_TYPE ( RELATIONSHIP_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 2, 'Spouse', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO FIN_INT_ENTITY_REL_TYPE ( RELATIONSHIP_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 3, 'Children', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO FIN_INT_ENTITY_REL_TYPE ( RELATIONSHIP_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 4, 'Student', sysdate, 'admin',  SYS_GUID() )
/
INSERT INTO FIN_INT_ENTITY_REL_TYPE ( RELATIONSHIP_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 5, 'Other', sysdate, 'admin',  SYS_GUID() )
/
