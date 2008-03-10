CREATE TABLE "NARRATIVE_ATTACHMENT"                                             
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "NARRATIVE_ATTACHMENTN1" NOT NULL   
ENABLE,                                                                         
"MODULE_NUMBER" NUMBER(4,0) CONSTRAINT "NARRATIVE_ATTACHMENTN2" NOT NULL ENABLE,
"NARRATIVE_DATA" BLOB,                                                          
"FILE_NAME" VARCHAR2(150),                                                      
"CONTENT_TYPE" VARCHAR2(50),                                                    
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "NARRATIVE_ATTACHMENTN3" NOT NULL ENABLE,  
"UPDATE_TIMESTAMP" DATE CONSTRAINT "NARRATIVE_ATTACHMENTN4" NOT NULL ENABLE,    
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "NARRATIVE_ATTACHMENTN5" NOT NULL    
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "NARRATIVE_ATTACHMENTN6" NOT
NULL ENABLE,                                                                    
CONSTRAINT "PK_NARRATIVE_ATTACHMENT_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",        
"MODULE_NUMBER") ENABLE                                                         
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EPS_PROP_ABSTRACT"                                                
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_ABSTRACTN1" NOT NULL      
ENABLE,                                                                         
"ABSTRACT_TYPE_CODE" VARCHAR2(3) CONSTRAINT "EPS_PROP_ABSTRACTN2" NOT NULL      
ENABLE,                                                                         
"ABSTRACT_DETAILS" CLOB CONSTRAINT "EPS_PROP_ABSTRACTN3" NOT NULL ENABLE,       
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_ABSTRACTN4" NOT NULL ENABLE,       
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_ABSTRACTN5" NOT NULL ENABLE,     
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_ABSTRACTN6" NOT NULL       
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROP_ABSTRACTN7" NOT   
NULL ENABLE,                                                                    
CONSTRAINT "PK_EPS_PROP_ABSTRACT_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",           
"ABSTRACT_TYPE_CODE") ENABLE                                                    
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "FP_MAINTENANCE_DOCUMENT_T"                                        
(	"FDOC_NBR" VARCHAR2(14) CONSTRAINT "FP_MAINTENANCE_DOCUMENT_TN1" NOT NULL     
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT                             
"FP_MAINTENANCE_DOCUMENT_TN2" NOT NULL ENABLE,                                  
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "FP_MAINTENANCE_DOCUMENT_TN3" NOT    
NULL ENABLE,                                                                    
"DOCUMENT_CONTENTS" CLOB,                                                       
CONSTRAINT "FP_MAINTENANCE_DOCUMENT_TP1" PRIMARY KEY ("FDOC_NBR") ENABLE,       
CONSTRAINT "FP_MAINTENANCE_DOCUMENT_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
       
                                                                                
CREATE TABLE "EN_BAM_PARAM_T"                                                   
(	"BAM_PARAM_ID" NUMBER(14,0) NOT NULL ENABLE,                                  
"BAM_ID" NUMBER(14,0) NOT NULL ENABLE,                                          
"PARAM" CLOB NOT NULL ENABLE,                                                   
CONSTRAINT "EN_BAM_PARAM_T_PK" PRIMARY KEY ("BAM_PARAM_ID") ENABLE              
 ) ;
                                                                           
                                                                                
CREATE TABLE "EN_BAM_T"                                                         
(	"BAM_ID" NUMBER(14,0) NOT NULL ENABLE,                                        
"SERVICE_NM" VARCHAR2(255) NOT NULL ENABLE,                                     
"SERVICE_URL" VARCHAR2(500) NOT NULL ENABLE,                                    
"METHOD_NM" VARCHAR2(2000) NOT NULL ENABLE,                                     
"THREAD_NM" VARCHAR2(500) NOT NULL ENABLE,                                      
"CALL_DT" DATE NOT NULL ENABLE,                                                 
"TARGET_TO_STRING" VARCHAR2(2000) NOT NULL ENABLE,                              
"SRVR_IND_IND" NUMBER(1,0) NOT NULL ENABLE,                                     
"EXCEPTION_TO_STRING" VARCHAR2(2000),                                           
"EXCEPTION_MSG" CLOB,                                                           
CONSTRAINT "EN_BAM_T_PK" PRIMARY KEY ("BAM_ID") ENABLE
   ) ;
                  
                                                                                
CREATE TABLE "EN_DOC_HDR_CNTNT_T"                                               
(	"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                    
"DOC_CNTNT_TXT" CLOB,                                                           
CONSTRAINT "EN_DOC_HDR_T_CNTNT_PK" PRIMARY KEY ("DOC_HDR_ID") ENABLE
   ) ;
    
                                                                                
CREATE TABLE "EN_DOC_TYP_T"                                                     
(	"DOC_TYP_ID" NUMBER(19,0) NOT NULL ENABLE,                                    
"DOC_TYP_PARNT_ID" NUMBER(19,0),                                                
"DOC_TYP_NM" VARCHAR2(255),                                                     
"DOC_TYP_VER_NBR" NUMBER(10,0) DEFAULT 0,                                       
"DOC_TYP_ACTV_IND" NUMBER(1,0),                                                 
"DOC_TYP_CUR_IND" NUMBER(1,0),                                                  
"DOC_TYP_LBL_TXT" VARCHAR2(255),                                                
"DOC_TYP_PREV_VER" NUMBER(19,0),                                                
"DOC_HDR_ID" NUMBER(14,0),                                                      
"DOC_TYP_DESC" VARCHAR2(255),                                                   
"DOC_TYP_HDLR_URL_ADDR" VARCHAR2(255),                                          
"DOC_TYP_POST_PRCSR_NM" VARCHAR2(255),                                          
"DOC_TYP_JNDI_URL_ADDR" VARCHAR2(255),                                          
"WRKGRP_ID" NUMBER(14,0),                                                       
"BLNKT_APPR_WRKGRP_ID" NUMBER(14,0),                                            
"BLNKT_APPR_PLCY" VARCHAR2(10),                                                 
"ADV_DOC_SRCH_URL_ADDR" VARCHAR2(255),                                          
"CSTM_ACTN_LIST_ATTRIB_CLS_NM" VARCHAR2(255),                                   
"CSTM_ACTN_EMAIL_ATTRIB_CLS_NM" VARCHAR2(255),                                  
"CSTM_DOC_NTE_ATTRIB_CLS_NM" VARCHAR2(255),                                     
"DOC_TYP_RTE_VER_NBR" VARCHAR2(2) DEFAULT '1' NOT NULL ENABLE,                  
"DOC_TYP_NOTIFY_ADDR" VARCHAR2(255),                                            
"MESSAGE_ENTITY_NM" VARCHAR2(10),                                               
"DOC_TYP_EMAIL_XSL" VARCHAR2(255),                                              
"DOC_TYP_SECURITY_XML" CLOB,                                                    
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_DOC_TYP_T_PK" PRIMARY KEY ("DOC_TYP_ID") ENABLE
   ) ;
          
                                                                                
CREATE TABLE "EN_EDOCLT_DEF_T"                                                  
(	"EDOCLT_DEF_ID" NUMBER(19,0) NOT NULL ENABLE,                                 
"EDOCLT_DEF_NM" VARCHAR2(200) NOT NULL ENABLE,                                  
"EDOCLT_DEF_XML" CLOB NOT NULL ENABLE,                                          
"EDOCLT_DEF_ACTV_IND" NUMBER(1,0) NOT NULL ENABLE,                              
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_EDOCLT_DEF_T" PRIMARY KEY ("EDOCLT_DEF_ID") ENABLE
   ) ;
       
                                                                                
CREATE TABLE "EN_EDOCLT_STYLE_T"                                                
(	"EDOCLT_STYLE_ID" NUMBER(19,0) NOT NULL ENABLE,                               
"EDOCLT_STYLE_NM" VARCHAR2(200) NOT NULL ENABLE,                                
"EDOCLT_STYLE_XML" CLOB NOT NULL ENABLE,                                        
"EDOCLT_STYLE_ACTV_IND" NUMBER(1,0) NOT NULL ENABLE,                            
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_EDOCLT_STYLE_T" PRIMARY KEY ("EDOCLT_STYLE_ID") ENABLE
   ) ;
   
                                                                                
CREATE TABLE "EN_MESSAGE_QUE_T"                                                 
(	"MESSAGE_QUE_ID" NUMBER(14,0) NOT NULL ENABLE,                                
"MESSAGE_QUE_DT" DATE NOT NULL ENABLE,                                          
"MESSAGE_EXP_DT" DATE,                                                          
"MESSAGE_QUE_PRIO_NBR" NUMBER(8,0) NOT NULL ENABLE,                             
"MESSAGE_QUE_STAT_CD" CHAR(1) NOT NULL ENABLE,                                  
"MESSAGE_QUE_RTRY_CNT" NUMBER(8,0) NOT NULL ENABLE,                             
"MESSAGE_QUE_IP_NBR" VARCHAR2(2000) NOT NULL ENABLE,                            
"MESSAGE_PAYLOAD" CLOB NOT NULL ENABLE,                                         
"MESSAGE_SERVICE_NM" VARCHAR2(255),                                             
"MESSAGE_ENTITY_NM" VARCHAR2(10) NOT NULL ENABLE,                               
"SERVICE_METHOD_NM" VARCHAR2(2000),                                             
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_MESSAGE_QUE_T_PK" PRIMARY KEY ("MESSAGE_QUE_ID") ENABLE
   ) ;
  
                                                                                
CREATE TABLE "EN_MSG_PAYLOAD_T"                                                 
(	"MESSAGE_QUE_ID" NUMBER(14,0) NOT NULL ENABLE,                                
"MESSAGE_PAYLOAD" CLOB NOT NULL ENABLE,                                         
CONSTRAINT "EN_MSG_PAYLOAD_T" PRIMARY KEY ("MESSAGE_QUE_ID") ENABLE
   ) ;
     
                                                                                
CREATE TABLE "EN_RULE_ATTRIB_T"                                                 
(	"RULE_ATTRIB_ID" NUMBER(19,0) NOT NULL ENABLE,                                
"RULE_ATTRIB_NM" VARCHAR2(255) NOT NULL ENABLE,                                 
"RULE_ATTRIB_LBL_TXT" VARCHAR2(2000) NOT NULL ENABLE,                           
"RULE_ATTRIB_TYP" VARCHAR2(2000) NOT NULL ENABLE,                               
"RULE_ATTRIB_DESC" VARCHAR2(2000),                                              
"RULE_ATTRIB_CLS_NM" VARCHAR2(2000),                                            
"RULE_ATTRIB_XML_RTE_TXT" CLOB,                                                 
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
"MESSAGE_ENTITY_NM" VARCHAR2(10),                                               
CONSTRAINT "EN_RULE_ATTRIB_PK" PRIMARY KEY ("RULE_ATTRIB_ID") ENABLE
   ) ;
    
                                                                                
CREATE TABLE "EN_SERVICE_DEF_DUEX_T"                                            
(	"SERVICE_DEF_ID" NUMBER(14,0) NOT NULL ENABLE,                                
"SERVICE_NM" VARCHAR2(255) NOT NULL ENABLE,                                     
"SERVICE_URL" VARCHAR2(500) NOT NULL ENABLE,                                    
"SERVER_IP" VARCHAR2(40) NOT NULL ENABLE,                                       
"MESSAGE_ENTITY_NM" VARCHAR2(10) NOT NULL ENABLE,                               
"SERVICE_ALIVE" NUMBER(1,0) NOT NULL ENABLE,                                    
"SERVICE_DEFINITION" CLOB NOT NULL ENABLE,                                      
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_SERVICE_DEF_DUEX_T_PK" PRIMARY KEY ("SERVICE_DEF_ID") ENABLE     
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KR_QRTZ_BLOB_TRIGGERS"                                            
(	"TRIGGER_NAME" VARCHAR2(80) NOT NULL ENABLE,                                  
"TRIGGER_GROUP" VARCHAR2(80) NOT NULL ENABLE,                                   
"BLOB_DATA" BLOB,                                                               
PRIMARY KEY ("TRIGGER_NAME", "TRIGGER_GROUP") ENABLE                            
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KR_QRTZ_CALENDARS"                                                
(	"CALENDAR_NAME" VARCHAR2(80) NOT NULL ENABLE,                                 
"CALENDAR" BLOB NOT NULL ENABLE,                                                
PRIMARY KEY ("CALENDAR_NAME") ENABLE
   ) ;
                                    
                                                                                
CREATE TABLE "KR_QRTZ_JOB_DETAILS"                                              
(	"JOB_NAME" VARCHAR2(80) NOT NULL ENABLE,                                      
"JOB_GROUP" VARCHAR2(80) NOT NULL ENABLE,                                       
"DESCRIPTION" VARCHAR2(120),                                                    
"JOB_CLASS_NAME" VARCHAR2(128) NOT NULL ENABLE,                                 
"IS_DURABLE" VARCHAR2(1) NOT NULL ENABLE,                                       
"IS_VOLATILE" VARCHAR2(1) NOT NULL ENABLE,                                      
"IS_STATEFUL" VARCHAR2(1) NOT NULL ENABLE,                                      
"REQUESTS_RECOVERY" VARCHAR2(1) NOT NULL ENABLE,                                
"JOB_DATA" BLOB,                                                                
PRIMARY KEY ("JOB_NAME", "JOB_GROUP") ENABLE                                    
   ) ;
                                                                         
                                                                                
CREATE TABLE "KR_QRTZ_TRIGGERS"                                                 
(	"TRIGGER_NAME" VARCHAR2(80) NOT NULL ENABLE,                                  
"TRIGGER_GROUP" VARCHAR2(80) NOT NULL ENABLE,                                   
"JOB_NAME" VARCHAR2(80) NOT NULL ENABLE,                                        
"JOB_GROUP" VARCHAR2(80) NOT NULL ENABLE,                                       
"IS_VOLATILE" VARCHAR2(1) NOT NULL ENABLE,                                      
"DESCRIPTION" VARCHAR2(120),                                                    
"NEXT_FIRE_TIME" NUMBER(13,0),                                                  
"PREV_FIRE_TIME" NUMBER(13,0),                                                  
"PRIORITY" NUMBER(13,0),                                                        
"TRIGGER_STATE" VARCHAR2(16) NOT NULL ENABLE,                                   
"TRIGGER_TYPE" VARCHAR2(8) NOT NULL ENABLE,                                     
"START_TIME" NUMBER(13,0) NOT NULL ENABLE,                                      
"END_TIME" NUMBER(13,0),                                                        
"CALENDAR_NAME" VARCHAR2(80),                                                   
"MISFIRE_INSTR" NUMBER(2,0),                                                    
"JOB_DATA" BLOB,                                                                
PRIMARY KEY ("TRIGGER_NAME", "TRIGGER_GROUP") ENABLE
   ) ;
                    
                                                                                
CREATE TABLE "NOTIFICATIONS"                                                    
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"DELIVERY_TYPE" VARCHAR2(3) NOT NULL ENABLE,                                    
"CREATED_DATETIME" TIMESTAMP (6) NOT NULL ENABLE,                               
"SEND_DATETIME" TIMESTAMP (6),                                                  
"AUTO_REMOVE_DATETIME" TIMESTAMP (6),                                           
"PRIORITY_ID" NUMBER(8,0) NOT NULL ENABLE,                                      
"TITLE" VARCHAR2(255),                                                          
"CONTENT" CLOB NOT NULL ENABLE,                                                 
"CONTENT_TYPE_ID" NUMBER(8,0) NOT NULL ENABLE,                                  
"NOTIFICATION_CHANNEL_ID" NUMBER(8,0) NOT NULL ENABLE,                          
"PRODUCER_ID" NUMBER(8,0) NOT NULL ENABLE,                                      
"PROCESSING_FLAG" VARCHAR2(15) NOT NULL ENABLE,                                 
"LOCKED_DATE" TIMESTAMP (6),                                                    
"DB_LOCK_VER_NBR" NUMBER(*,0) DEFAULT 0 NOT NULL ENABLE,                        
CONSTRAINT "NOTIFICATIONS_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
                 
                                                                                
CREATE TABLE "NOTIFICATION_CONTENT_TYPES"                                       
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NAME" VARCHAR2(500) NOT NULL ENABLE,                                           
"DESCRIPTION" VARCHAR2(1000) NOT NULL ENABLE,                                   
"NAMESPACE" VARCHAR2(1000) NOT NULL ENABLE,                                     
"XSD" CLOB NOT NULL ENABLE,                                                     
"XSL" CLOB NOT NULL ENABLE,                                                     
"VERSION" NUMBER(8,0) DEFAULT '0' NOT NULL ENABLE,                              
"CUR_IND" CHAR(1) DEFAULT 'T' NOT NULL ENABLE,                                  
CONSTRAINT "NOTIFICATION_CONTENT_TYPE_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
     
                                                                                
CREATE TABLE "FS_LOOKUP_RESULTS_MT"                                             
(	"LOOKUP_RESULT_SEQUENCE_NBR" VARCHAR2(14) CONSTRAINT "FS_LOOKUP_RESULTS_MTN1" 
NOT NULL ENABLE,                                                                
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "FS_LOOKUP_RESULTS_MTN2" NOT
NULL ENABLE,                                                                    
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "FS_LOOKUP_RESULTS_MTN3" NOT NULL    
ENABLE,                                                                         
"PERSON_UNVL_ID" VARCHAR2(10) CONSTRAINT "FS_LOOKUP_RESULTS_MTN4" NOT NULL      
ENABLE,                                                                         
"LOOKUP_DATE" DATE CONSTRAINT "FS_LOOKUP_RESULTS_MTN5" NOT NULL ENABLE,         
"SERIALIZED_LOOKUP_RESULTS" CLOB,                                               
CONSTRAINT "FS_LOOKUP_RESULTS_MTP1" PRIMARY KEY ("LOOKUP_RESULT_SEQUENCE_NBR")  
ENABLE,                                                                         
CONSTRAINT "FS_LOOKUP_RESULTS_MTC0" UNIQUE ("OBJ_ID") ENABLE                    
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "FS_LOOKUP_SELECTIONS_MT"                                          
(	"LOOKUP_RESULT_SEQUENCE_NBR" VARCHAR2(14) CONSTRAINT                          
"FS_LOOKUP_SELECTIONS_MTN1" NOT NULL ENABLE,                                    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "FS_LOOKUP_SELECTIONS_MTN2" 
NOT NULL ENABLE,                                                                
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "FS_LOOKUP_SELECTIONS_MTN3" NOT NULL 
ENABLE,                                                                         
"PERSON_UNVL_ID" VARCHAR2(10) CONSTRAINT "FS_LOOKUP_SELECTIONS_MTN4" NOT NULL   
ENABLE,                                                                         
"LOOKUP_DATE" DATE CONSTRAINT "FS_LOOKUP_SELECTIONS_MTN5" NOT NULL ENABLE,      
"SELECTED_OBJ_IDS" CLOB,                                                        
CONSTRAINT "FS_LOOKUP_SELECTIONS_MTP1" PRIMARY KEY                              
("LOOKUP_RESULT_SEQUENCE_NBR") ENABLE,                                          
CONSTRAINT "FS_LOOKUP_SELECTIONS_MTC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
         
                                                                                
CREATE TABLE "EPS_PROP_PERSON_BIO_ATTACHMENT"                                   
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_PERSON_BIO_ATMTN1" NOT    
NULL ENABLE,                                                                    
"PROP_PERSON_NUMBER" NUMBER(12,0) CONSTRAINT "EPS_PROP_PERSON_BIO_ATMTN2" NOT   
NULL ENABLE,                                                                    
"BIO_NUMBER" NUMBER(3,0) CONSTRAINT "EPS_PROP_PERSON_BIO_ATMTN3" NOT NULL       
ENABLE,                                                                         
"BIO_DATA" BLOB,                                                                
"FILE_NAME" VARCHAR2(150),                                                      
"CONTENT_TYPE" VARCHAR2(50),                                                    
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_PERSON_BIO_ATMTN4" NOT NULL ENABLE,
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_PERSON_BIO_ATMTN5" NOT NULL      
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_PERSON_BIO_ATMTN6" NOT NULL
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROP_PERSON_BIO_ATMTN7"
NOT NULL ENABLE,                                                                
CONSTRAINT "PK_EPS_PROP_PSN_BIO_ATMT_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",       
"PROP_PERSON_NUMBER", "BIO_NUMBER") ENABLE                                      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EPS_PROP_SPECIAL_REVIEW"                                          
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_SPECIAL_REVIEWN1" NOT NULL
ENABLE,                                                                         
"SPECIAL_REVIEW_NUMBER" NUMBER(3,0) CONSTRAINT "EPS_PROP_SPECIAL_REVIEWN2" NOT  
NULL ENABLE,                                                                    
"SPECIAL_REVIEW_CODE" VARCHAR2(3) CONSTRAINT "EPS_PROP_SPECIAL_REVIEWN3" NOT    
NULL ENABLE,                                                                    
"APPROVAL_TYPE_CODE" VARCHAR2(3) CONSTRAINT "EPS_PROP_SPECIAL_REVIEWN4" NOT NULL
ENABLE,                                                                         
"PROTOCOL_NUMBER" VARCHAR2(20),                                                 
"APPLICATION_DATE" DATE,                                                        
"APPROVAL_DATE" DATE,                                                           
"COMMENTS" CLOB,                                                                
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_SPECIAL_REVIEWN5" NOT NULL       
ENABLE,                                                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_SPECIAL_REVIEWN6" NOT NULL ENABLE, 
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_SPECIAL_REVIEWN7" NOT NULL 
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROP_SPECIAL_REVIEWN8" 
NOT NULL ENABLE,                                                                
CONSTRAINT "PK_EPS_PROP_SPECIAL_REVIEW_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",     
"SPECIAL_REVIEW_NUMBER") ENABLE
   ) ;
                                         
                                                                                
CREATE TABLE "BUDGET_SUB_AWARDS"                                                
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "BUDGET_SUB_AWARDSN1" NOT NULL      
ENABLE,                                                                         
"VERSION_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_SUB_AWARDSN2" NOT NULL ENABLE,  
"SUB_AWARD_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_SUB_AWARDSN3" NOT NULL ENABLE,
"ORGANIZATION_NAME" VARCHAR2(60) CONSTRAINT "BUDGET_SUB_AWARDSN4" NOT NULL      
ENABLE,                                                                         
"SUB_AWARD_STATUS_CODE" NUMBER(3,0) CONSTRAINT "BUDGET_SUB_AWARDSN5" NOT NULL   
ENABLE,                                                                         
"SUB_AWARD_XFD_FILE" BLOB DEFAULT empty_blob(),                                 
"SUB_AWARD_XFD_FILE_NAME" VARCHAR2(256) CONSTRAINT "BUDGET_SUB_AWARDSN6" NOT    
NULL ENABLE,                                                                    
"COMMENTS" VARCHAR2(2000),                                                      
"XFD_UPDATE_USER" VARCHAR2(8),                                                  
"XFD_UPDATE_TIMESTAMP" DATE,                                                    
"SUB_AWARD_XML_FILE" CLOB DEFAULT empty_clob(),                                 
"TRANSLATION_COMMENTS" VARCHAR2(2000),                                          
"XML_UPDATE_USER" VARCHAR2(8),                                                  
"XML_UPDATE_TIMESTAMP" DATE,                                                    
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_SUB_AWARDSN7" NOT NULL ENABLE,       
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_SUB_AWARDSN8" NOT NULL ENABLE,     
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_SUB_AWARDSN9" NOT NULL       
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "BUDGET_SUB_AWARDSN10" NOT  
NULL ENABLE,                                                                    
CONSTRAINT "PK_BUDGET_SUB_AWARDS_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",           
"VERSION_NUMBER", "SUB_AWARD_NUMBER") ENABLE                                    
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "BUDGET_SUB_AWARD_ATT"                                             
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "BUDGET_SUB_AWARD_ATTN1" NOT NULL   
ENABLE,                                                                         
"VERSION_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_SUB_AWARD_ATTN2" NOT NULL       
ENABLE,                                                                         
"SUB_AWARD_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_SUB_AWARD_ATTN3" NOT NULL     
ENABLE,                                                                         
"CONTENT_ID" VARCHAR2(350) CONSTRAINT "BUDGET_SUB_AWARD_ATTN4" NOT NULL ENABLE, 
"CONTENT_TYPE" VARCHAR2(30),                                                    
"ATTACHMENT" BLOB DEFAULT empty_blob(),                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_SUB_AWARD_ATTN5" NOT NULL ENABLE,    
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_SUB_AWARD_ATTN6" NOT NULL ENABLE,  
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_SUB_AWARD_ATTN7" NOT NULL    
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "BUDGET_SUB_AWARD_ATTN8" NOT
NULL ENABLE,                                                                    
CONSTRAINT "PK_BUDGET_SUB_AWARD_ATT_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",        
"VERSION_NUMBER", "SUB_AWARD_NUMBER", "CONTENT_ID") ENABLE                      
  ) ;
                                                                          
                                                                                
CREATE TABLE "ABSTRACT_TYPE"                                                    
(	"ABSTRACT_TYPE_CODE" VARCHAR2(3) CONSTRAINT "ABSTRACT_TYPE_N1" NOT NULL       
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "ABSTRACT_TYPE_N2" NOT NULL ENABLE,      
"UPDATE_TIMESTAMP" DATE CONSTRAINT "ABSTRACT_TYPE_N3" NOT NULL ENABLE,          
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "ABSTRACT_TYPE_N4" NOT NULL ENABLE,        
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "ABSTRACT_TYPE_N5" NOT NULL ENABLE,  
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "ABSTRACT_TYPE_N6" NOT NULL 
ENABLE,                                                                         
CONSTRAINT "ABSTRACT_TYPE_P1" PRIMARY KEY ("ABSTRACT_TYPE_CODE") ENABLE,        
CONSTRAINT "ABSTRACT_TYPE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                  
                                                                                
CREATE TABLE "ACTIVITY_TYPE"                                                    
(	"ACTIVITY_TYPE_CODE" VARCHAR2(3) CONSTRAINT "ACTIVITY_TYPE_N1" NOT NULL       
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "ACTIVITY_TYPE_N2" NOT NULL ENABLE,      
"UPDATE_TIMESTAMP" DATE CONSTRAINT "ACTIVITY_TYPE_N3" NOT NULL ENABLE,          
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "ACTIVITY_TYPE_N4" NOT NULL ENABLE,        
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "ACTIVITY_TYPE_N5" NOT NULL ENABLE,  
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "ACTIVITY_TYPE_N6" NOT NULL 
ENABLE,                                                                         
CONSTRAINT "ACTIVITY_TYPE_P1" PRIMARY KEY ("ACTIVITY_TYPE_CODE") ENABLE,        
CONSTRAINT "ACTIVITY_TYPE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                  
                                                                                
CREATE TABLE "CARRIER_TYPE"                                                     
(	"CARRIER_TYPE_CODE" VARCHAR2(3) CONSTRAINT "CARRIER_TYPE_N1" NOT NULL ENABLE, 
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "CARRIER_TYPE_N2" NOT NULL ENABLE,       
"UPDATE_TIMESTAMP" DATE CONSTRAINT "CARRIER_TYPE_N3" NOT NULL ENABLE,           
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "CARRIER_TYPE_N4" NOT NULL ENABLE,         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "CARRIER_TYPE_N5" NOT NULL ENABLE,   
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "CARRIER_TYPE_N6" NOT NULL  
ENABLE,                                                                         
CONSTRAINT "CARRIER_TYPE_TP1" PRIMARY KEY ("CARRIER_TYPE_CODE") ENABLE,         
CONSTRAINT "CARRIER_TYPE_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                  
                                                                                
CREATE TABLE "DEADLINE_TYPE"                                                    
(	"DEADLINE_TYPE_CODE" VARCHAR2(3) CONSTRAINT "DEADLINE_TYPE_N1" NOT NULL       
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "DEADLINE_TYPE_N2" NOT NULL ENABLE,      
"UPDATE_TIMESTAMP" DATE CONSTRAINT "DEADLINE_TYPE_N3" NOT NULL ENABLE,          
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "DEADLINE_TYPE_N4" NOT NULL ENABLE,        
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "DEADLINE_TYPE_N5" NOT NULL ENABLE,  
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "DEADLINE_TYPE_N6" NOT NULL 
ENABLE,                                                                         
CONSTRAINT "DEADLINE_TYPE_P1" PRIMARY KEY ("DEADLINE_TYPE_CODE") ENABLE,        
CONSTRAINT "DEADLINE_TYPE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                  
                                                                                
CREATE TABLE "SCIENCE_KEYWORD"                                                  
(	"SCIENCE_KEYWORD_CODE" VARCHAR2(3) CONSTRAINT "SCIENCE_KEYWORD_N1" NOT NULL   
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "SCIENCE_KEYWORD_N2" NOT NULL ENABLE,    
"UPDATE_TIMESTAMP" DATE CONSTRAINT "SCIENCE_KEYWORD_N3" NOT NULL ENABLE,        
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "SCIENCE_KEYWORD_N4" NOT NULL ENABLE,      
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SCIENCE_KEYWORD_N5" NOT NULL ENABLE,
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SCIENCE_KEYWORD_N6" NOT    
NULL ENABLE,                                                                    
CONSTRAINT "SCIENCE_KEYWORD_TP1" PRIMARY KEY ("SCIENCE_KEYWORD_CODE") ENABLE,   
CONSTRAINT "SCIENCE_KEYWORD_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
               
                                                                                
CREATE TABLE "STATE_CODE"                                                       
(	"COUNTRY_CODE" CHAR(3) CONSTRAINT "STATE_CODE_N1" NOT NULL ENABLE,            
"STATE_CODE" VARCHAR2(15) CONSTRAINT "STATE_CODE_N2" NOT NULL ENABLE,           
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "STATE_CODE_N3" NOT NULL ENABLE,         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "STATE_CODE_N4" NOT NULL ENABLE,             
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "STATE_CODE_N5" NOT NULL ENABLE,           
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "STATE_CODE_N6" NOT NULL ENABLE,     
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "STATE_CODE_N7" NOT NULL    
ENABLE,                                                                         
CONSTRAINT "STATE_CODE_TP1" PRIMARY KEY ("COUNTRY_CODE", "STATE_CODE") ENABLE,  
CONSTRAINT "STATE_CODE_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                    
                                                                                
CREATE TABLE "KRA_USER"                                                         
(	"USER_ID" VARCHAR2(10) CONSTRAINT "KRA_USERN1" NOT NULL ENABLE,               
"USER_NAME" VARCHAR2(90),                                                       
"NON_MIT_PERSON_FLAG" CHAR(1),                                                  
"PERSON_ID" VARCHAR2(10),                                                       
"USER_TYPE" CHAR(1),                                                            
"UNIT_NUMBER" VARCHAR2(8),                                                      
"STATUS" CHAR(1),                                                               
"UPDATE_TIMESTAMP" DATE CONSTRAINT "KRA_USERN2" NOT NULL ENABLE,                
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "KRA_USERN3" NOT NULL ENABLE,              
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "KRA_USERN4" NOT NULL ENABLE,        
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "KRA_USERN5" NOT NULL       
ENABLE,                                                                         
CONSTRAINT "PK_USER_KRA" PRIMARY KEY ("USER_ID") ENABLE
   ) ;
                 
                                                                                
CREATE TABLE "MAIL_BY"                                                          
(	"MAIL_BY_CODE" VARCHAR2(3) CONSTRAINT "MAIL_BY_N1" NOT NULL ENABLE,           
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "MAIL_BY_N2" NOT NULL ENABLE,            
"UPDATE_TIMESTAMP" DATE CONSTRAINT "MAIL_BY_N3" NOT NULL ENABLE,                
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "MAIL_BY_N4" NOT NULL ENABLE,              
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "MAIL_BY_N5" NOT NULL ENABLE,        
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "MAIL_BY_N6" NOT NULL       
ENABLE,                                                                         
CONSTRAINT "MAIL_BY_P1" PRIMARY KEY ("MAIL_BY_CODE") ENABLE,                    
CONSTRAINT "MAIL_BY_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                        
                                                                                
CREATE TABLE "MAIL_TYPE"                                                        
(	"MAIL_TYPE" VARCHAR2(3) CONSTRAINT "MAIL_TYPE_N1" NOT NULL ENABLE,            
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "MAIL_TYPE_N2" NOT NULL ENABLE,          
"UPDATE_TIMESTAMP" DATE CONSTRAINT "MAIL_TYPE_N3" NOT NULL ENABLE,              
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "MAIL_TYPE_N4" NOT NULL ENABLE,            
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "MAIL_TYPE_N5" NOT NULL ENABLE,      
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "MAIL_TYPE_N6" NOT NULL     
ENABLE,                                                                         
CONSTRAINT "MAIL_TYPE_P1" PRIMARY KEY ("MAIL_TYPE") ENABLE,                     
CONSTRAINT "MAIL_TYPE_C0" UNIQUE ("OBJ_ID") ENABLE                              
   ) ;
                                                                         
                                                                                
CREATE TABLE "NARRATIVE_TYPE"                                                   
(	"NARRATIVE_TYPE_CODE" VARCHAR2(3) CONSTRAINT "NARRATIVE_TYPE_N1" NOT NULL     
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "NARRATIVE_TYPE_N2" NOT NULL ENABLE,     
"SYSTEM_GENERATED" VARCHAR2(1) CONSTRAINT "NARRATIVE_TYPE_N3" NOT NULL ENABLE,  
"ALLOW_MULTIPLE" VARCHAR2(1) CONSTRAINT "NARRATIVE_TYPE_N4" NOT NULL ENABLE,    
"NARRATIVE_TYPE_GROUP" VARCHAR2(1),                                             
"UPDATE_TIMESTAMP" DATE CONSTRAINT "NARRATIVE_TYPE_N5" NOT NULL ENABLE,         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "NARRATIVE_TYPE_N6" NOT NULL ENABLE,       
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "NARRATIVE_TYPE_N7" NOT NULL ENABLE, 
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "NARRATIVE_TYPE_N8" NOT NULL
ENABLE,                                                                         
CONSTRAINT "NARRATIVE_TYPE_P1" PRIMARY KEY ("NARRATIVE_TYPE_CODE") ENABLE,      
CONSTRAINT "NARRATIVE_TYPE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                 
                                                                                
CREATE TABLE "NSF_CODES"                                                        
(	"NSF_SEQUENCE_NUMBER" NUMBER(12,0) CONSTRAINT "NSF_CODES_N1" NOT NULL ENABLE, 
"NSF_CODE" VARCHAR2(15) CONSTRAINT "NSF_CODES_N2" NOT NULL ENABLE,              
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "NSF_CODES_N3" NOT NULL ENABLE,          
"UPDATE_TIMESTAMP" DATE CONSTRAINT "NSF_CODES_N4" NOT NULL ENABLE,              
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "NSF_CODES_N5" NOT NULL ENABLE,            
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "NSF_CODES_N6" NOT NULL ENABLE,      
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "NSF_CODES_N7" NOT NULL     
ENABLE,                                                                         
CONSTRAINT "NSF_CODES_P1" PRIMARY KEY ("NSF_SEQUENCE_NUMBER") ENABLE,           
CONSTRAINT "NSF_CODES_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                      
                                                                                
CREATE TABLE "ORGANIZATION"                                                     
(	"ORGANIZATION_ID" VARCHAR2(8) CONSTRAINT "ORGANIZATIONN1" NOT NULL ENABLE,    
"ORGANIZATION_NAME" VARCHAR2(60) CONSTRAINT "ORGANIZATIONN2" NOT NULL ENABLE,   
"CONTACT_ADDRESS_ID" NUMBER(6,0) CONSTRAINT "ORGANIZATIONN3" NOT NULL ENABLE,   
"ADDRESS" VARCHAR2(60),                                                         
"CABLE_ADDRESS" VARCHAR2(20),                                                   
"TELEX_NUMBER" VARCHAR2(20),                                                    
"COUNTY" VARCHAR2(30),                                                          
"CONGRESSIONAL_DISTRICT" VARCHAR2(50),                                          
"INCORPORATED_IN" VARCHAR2(50),                                                 
"INCORPORATED_DATE" DATE,                                                       
"NUMBER_OF_EMPLOYEES" NUMBER(6,0),                                              
"IRS_TAX_EXCEMPTION" VARCHAR2(30),                                              
"FEDRAL_EMPLOYER_ID" VARCHAR2(15),                                              
"MASS_TAX_EXCEMPT_NUM" VARCHAR2(30),                                            
"AGENCY_SYMBOL" VARCHAR2(30),                                                   
"VENDOR_CODE" VARCHAR2(30),                                                     
"COM_GOV_ENTITY_CODE" VARCHAR2(30),                                             
"MASS_EMPLOYEE_CLAIM" VARCHAR2(30),                                             
"DUNS_NUMBER" VARCHAR2(20),                                                     
"DUNS_PLUS_FOUR_NUMBER" VARCHAR2(20),                                           
"DODAC_NUMBER" VARCHAR2(20),                                                    
"CAGE_NUMBER" VARCHAR2(20),                                                     
"HUMAN_SUB_ASSURANCE" VARCHAR2(30),                                             
"ANIMAL_WELFARE_ASSURANCE" VARCHAR2(20),                                        
"SCIENCE_MISCONDUCT_COMPL_DATE" DATE,                                           
"PHS_ACOUNT" VARCHAR2(30),                                                      
"NSF_INSTITUTIONAL_CODE" VARCHAR2(30),                                          
"INDIRECT_COST_RATE_AGREEMENT" VARCHAR2(50),                                    
"COGNIZANT_AUDITOR" NUMBER(6,0),                                                
"ONR_RESIDENT_REP" NUMBER(6,0),                                                 
"UPDATE_TIMESTAMP" DATE CONSTRAINT "ORGANIZATIONN4" NOT NULL ENABLE,            
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "ORGANIZATIONN5" NOT NULL ENABLE,          
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "ORGANIZATIONN6" NOT NULL ENABLE,    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "ORGANIZATIONN7" NOT NULL   
ENABLE,                                                                         
CONSTRAINT "PK_ORGANIZATION_KRA" PRIMARY KEY ("ORGANIZATION_ID") ENABLE         
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "PERSON"                                                           
(	"PERSON_ID" VARCHAR2(10) CONSTRAINT "PERSONN1" NOT NULL ENABLE,               
"SSN" VARCHAR2(9),                                                              
"LAST_NAME" VARCHAR2(30),                                                       
"FIRST_NAME" VARCHAR2(30),                                                      
"MIDDLE_NAME" VARCHAR2(30),                                                     
"FULL_NAME" VARCHAR2(90),                                                       
"PRIOR_NAME" VARCHAR2(30),                                                      
"USER_NAME" VARCHAR2(60),                                                       
"EMAIL_ADDRESS" VARCHAR2(60),                                                   
"DATE_OF_BIRTH" DATE,                                                           
"AGE" NUMBER(3,0),                                                              
"AGE_BY_FISCAL_YEAR" NUMBER(3,0),                                               
"GENDER" VARCHAR2(30),                                                          
"RACE" VARCHAR2(30),                                                            
"EDUCATION_LEVEL" VARCHAR2(30),                                                 
"DEGREE" VARCHAR2(11),                                                          
"MAJOR" VARCHAR2(30),                                                           
"IS_HANDICAPPED" CHAR(1),                                                       
"HANDICAP_TYPE" VARCHAR2(30),                                                   
"IS_VETERAN" CHAR(1),                                                           
"VETERAN_TYPE" VARCHAR2(30),                                                    
"VISA_CODE" VARCHAR2(20),                                                       
"VISA_TYPE" VARCHAR2(30),                                                       
"VISA_RENEWAL_DATE" DATE,                                                       
"HAS_VISA" CHAR(1),                                                             
"OFFICE_LOCATION" VARCHAR2(30),                                                 
"OFFICE_PHONE" VARCHAR2(20),                                                    
"SECONDRY_OFFICE_LOCATION" VARCHAR2(30),                                        
"SECONDRY_OFFICE_PHONE" VARCHAR2(20),                                           
"SCHOOL" VARCHAR2(50),                                                          
"YEAR_GRADUATED" VARCHAR2(30),                                                  
"DIRECTORY_DEPARTMENT" VARCHAR2(30),                                            
"SALUTATION" VARCHAR2(30),                                                      
"COUNTRY_OF_CITIZENSHIP" VARCHAR2(30),                                          
"PRIMARY_TITLE" VARCHAR2(51),                                                   
"DIRECTORY_TITLE" VARCHAR2(50),                                                 
"HOME_UNIT" VARCHAR2(8),                                                        
"IS_FACULTY" CHAR(1),                                                           
"IS_GRADUATE_STUDENT_STAFF" CHAR(1),                                            
"IS_RESEARCH_STAFF" CHAR(1),                                                    
"IS_SERVICE_STAFF" CHAR(1),                                                     
"IS_SUPPORT_STAFF" CHAR(1),                                                     
"IS_OTHER_ACCADEMIC_GROUP" CHAR(1),                                             
"IS_MEDICAL_STAFF" CHAR(1),                                                     
"VACATION_ACCURAL" CHAR(1),                                                     
"IS_ON_SABBATICAL" CHAR(1),                                                     
"ID_PROVIDED" VARCHAR2(30),                                                     
"ID_VERIFIED" VARCHAR2(30),                                                     
"UPDATE_TIMESTAMP" DATE CONSTRAINT "PERSONN2" NOT NULL ENABLE,                  
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "PERSONN3" NOT NULL ENABLE,                
"ADDRESS_LINE_1" VARCHAR2(80),                                                  
"ADDRESS_LINE_2" VARCHAR2(80),                                                  
"ADDRESS_LINE_3" VARCHAR2(80),                                                  
"CITY" VARCHAR2(30),                                                            
"COUNTY" VARCHAR2(30),                                                          
"STATE" VARCHAR2(30),                                                           
"POSTAL_CODE" VARCHAR2(15),                                                     
"COUNTRY_CODE" CHAR(3),                                                         
"FAX_NUMBER" VARCHAR2(20),                                                      
"PAGER_NUMBER" VARCHAR2(20),                                                    
"MOBILE_PHONE_NUMBER" VARCHAR2(20),                                             
"ERA_COMMONS_USER_NAME" VARCHAR2(20),                                           
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "PERSONN4" NOT NULL ENABLE,          
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "PERSONN5" NOT NULL ENABLE, 
CONSTRAINT "PK_PERSON_KRA" PRIMARY KEY ("PERSON_ID") ENABLE
   ) ;
             
                                                                                
CREATE TABLE "PROPOSAL_TYPE"                                                    
(	"PROPOSAL_TYPE_CODE" VARCHAR2(3) CONSTRAINT "PROPOSAL_TYPE_N1" NOT NULL       
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "PROPOSAL_TYPE_N2" NOT NULL ENABLE,      
"UPDATE_TIMESTAMP" DATE CONSTRAINT "PROPOSAL_TYPE_N3" NOT NULL ENABLE,          
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "PROPOSAL_TYPE_N4" NOT NULL ENABLE,        
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "PROPOSAL_TYPE_N5" NOT NULL ENABLE,  
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "PROPOSAL_TYPE_N6" NOT NULL 
ENABLE,                                                                         
CONSTRAINT "PROPOSAL_TYPE_P1" PRIMARY KEY ("PROPOSAL_TYPE_CODE") ENABLE,        
CONSTRAINT "PROPOSAL_TYPE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                  
                                                                                
CREATE TABLE "S2S_OPPORTUNITY"                                                  
(	"PROPOSAL_NUMBER" VARCHAR2(8) CONSTRAINT "S2S_OPPORTUNITYN1" NOT NULL ENABLE, 
"OPPORTUNITY_TITLE" VARCHAR2(255),                                              
"COMPETETION_ID" VARCHAR2(50),                                                  
"OPENING_DATE" DATE,                                                            
"CLOSING_DATE" DATE,                                                            
"SCHEMA_URL" VARCHAR2(200),                                                     
"INSTRUCTION_URL" VARCHAR2(200),                                                
"OPPORTUNITY_ID" VARCHAR2(50),                                                  
"OPPORTUNITY" CLOB DEFAULT empty_clob(),                                        
"CFDA_NUMBER" VARCHAR2(6),                                                      
"S2S_SUBMISSION_TYPE_CODE" NUMBER(3,0) DEFAULT 2 CONSTRAINT "S2S_OPPORTUNITYN2" 
NOT NULL ENABLE,                                                                
"REVISION_CODE" VARCHAR2(2),                                                    
"REVISION_OTHER_DESCRIPTION" VARCHAR2(45),                                      
"UPDATE_TIMESTAMP" DATE,                                                        
"UPDATE_USER" VARCHAR2(8),                                                      
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "S2S_OPPORTUNITYN3" NOT NULL ENABLE, 
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "S2S_OPPORTUNITYN4" NOT NULL
ENABLE,                                                                         
CONSTRAINT "PK_S2S_OPPORTUNITY_KRA" PRIMARY KEY ("PROPOSAL_NUMBER") ENABLE      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "S2S_APPLICATION"                                                  
(	"PROPOSAL_NUMBER" VARCHAR2(8) CONSTRAINT "S2S_APPLICATIONN1" NOT NULL ENABLE, 
"APPLICATION" CLOB,                                                             
"UPDATE_TIMESTAMP" DATE,                                                        
"UPDATE_USER" VARCHAR2(8),                                                      
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "S2S_APPLICATIONN2" NOT NULL ENABLE, 
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "S2S_APPLICATIONN3" NOT NULL
ENABLE,                                                                         
CONSTRAINT "PK_S2S_APPLICATION_KRA" PRIMARY KEY ("PROPOSAL_NUMBER") ENABLE      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "BUDGET_PERSONS"                                                   
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "BUDGET_PERSONSN1" NOT NULL ENABLE, 
"VERSION_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_PERSONSN2" NOT NULL ENABLE,     
"PERSON_ID" VARCHAR2(9),                                                        
"JOB_CODE" VARCHAR2(6) CONSTRAINT "BUDGET_PERSONSN4" NOT NULL ENABLE,           
"EFFECTIVE_DATE" DATE CONSTRAINT "BUDGET_PERSONSN5" NOT NULL ENABLE,            
"CALCULATION_BASE" NUMBER(12,2),                                                
"PERSON_NAME" VARCHAR2(90),                                                     
"NON_EMPLOYEE_FLAG" VARCHAR2(1) CONSTRAINT "BUDGET_PERSONSN6" NOT NULL ENABLE,  
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_PERSONSN7" NOT NULL ENABLE,          
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_PERSONSN8" NOT NULL ENABLE,        
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_PERSONSN9" NOT NULL ENABLE,  
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "BUDGET_PERSONSN10" NOT NULL
ENABLE,                                                                         
"PERSON_SEQUENCE_NUMBER" NUMBER(3,0) NOT NULL ENABLE,                           
"ROLODEX_ID" NUMBER(6,0),                                                       
"APPOINTMENT_TYPE_CODE" VARCHAR2(3),                                            
CONSTRAINT "PK_BUDGET_PERSONS_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",              
"VERSION_NUMBER", "PERSON_SEQUENCE_NUMBER") ENABLE,                             
CONSTRAINT "UK_BUDGET_PERSONS_KRA" UNIQUE ("PROPOSAL_NUMBER", "VERSION_NUMBER", 
"PERSON_ID", "ROLODEX_ID", "JOB_CODE", "EFFECTIVE_DATE") ENABLE
   ) ;
         
                                                                                
CREATE TABLE "BUDGET_RATE_AND_BASE"                                             
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "BUDGET_RATE_AND_BASEN1" NOT NULL   
ENABLE,                                                                         
"VERSION_NUMBER" NUMBER CONSTRAINT "BUDGET_RATE_AND_BASEN2" NOT NULL ENABLE,    
"BUDGET_PERIOD" NUMBER(3,0) CONSTRAINT "BUDGET_RATE_AND_BASEN3" NOT NULL ENABLE,
"LINE_ITEM_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_RATE_AND_BASEN4" NOT NULL     
ENABLE,                                                                         
"RATE_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_RATE_AND_BASEN5" NOT NULL ENABLE,  
"START_DATE" DATE CONSTRAINT "BUDGET_RATE_AND_BASEN6" NOT NULL ENABLE,          
"END_DATE" DATE CONSTRAINT "BUDGET_RATE_AND_BASEN7" NOT NULL ENABLE,            
"RATE_CLASS_CODE" VARCHAR2(3) CONSTRAINT "BUDGET_RATE_AND_BASEN8" NOT NULL      
ENABLE,                                                                         
"RATE_TYPE_CODE" VARCHAR2(3) CONSTRAINT "BUDGET_RATE_AND_BASEN9" NOT NULL       
ENABLE,                                                                         
"ON_OFF_CAMPUS_FLAG" CHAR(1) CONSTRAINT "BUDGET_RATE_AND_BASEN10" NOT NULL      
ENABLE,                                                                         
"APPLIED_RATE" NUMBER(5,2) CONSTRAINT "BUDGET_RATE_AND_BASEN11" NOT NULL ENABLE,
"BASE_COST" NUMBER(14,2),                                                       
"BASE_COST_SHARING" NUMBER(14,2),                                               
"CALCULATED_COST" NUMBER(14,2),                                                 
"CALCULATED_COST_SHARING" NUMBER(14,2),                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_RATE_AND_BASEN12" NOT NULL ENABLE,   
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_RATE_AND_BASEN13" NOT NULL ENABLE, 
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_RATE_AND_BASEN14" NOT NULL   
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "BUDGET_RATE_AND_BASEN15"   
NOT NULL ENABLE,                                                                
CONSTRAINT "PK_BUDGET_RATE_AND_BASE_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",        
"VERSION_NUMBER", "BUDGET_PERIOD", "LINE_ITEM_NUMBER", "RATE_NUMBER",           
"RATE_CLASS_CODE", "RATE_TYPE_CODE") ENABLE                                     
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "VALID_CALC_TYPES"                                                 
(	"CALC_TYPE_ID" VARCHAR2(8) CONSTRAINT "VALID_CALC_TYPESN1" NOT NULL ENABLE,   
"RATE_CLASS_TYPE" CHAR(1) CONSTRAINT "VALID_CALC_TYPESN2" NOT NULL ENABLE,      
"DEPENDENT_SEQ_NUMBER" NUMBER(3,0) CONSTRAINT "VALID_CALC_TYPESN3" NOT NULL     
ENABLE,                                                                         
"DEPENDENT_RATE_CLASS_TYPE" CHAR(1),                                            
"RATE_CLASS_CODE" VARCHAR2(3),                                                  
"RATE_TYPE_CODE" VARCHAR2(3),                                                   
"UPDATE_TIMESTAMP" DATE CONSTRAINT "VALID_CALC_TYPESN4" NOT NULL ENABLE,        
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "VALID_CALC_TYPESN5" NOT NULL ENABLE,      
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "VALID_CALC_TYPESN6" NOT NULL ENABLE,
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "VALID_CALC_TYPESN7" NOT    
NULL ENABLE,                                                                    
CONSTRAINT "PK_VALID_CALC_TYPES_KRA" PRIMARY KEY ("CALC_TYPE_ID",               
"RATE_CLASS_TYPE", "DEPENDENT_SEQ_NUMBER") ENABLE                               
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "INSTITUTE_LA_RATES"                                               
(	"UNIT_NUMBER" VARCHAR2(8) CONSTRAINT "INSTITUTE_LA_RATESN1" NOT NULL ENABLE,  
"RATE_TYPE_CODE" VARCHAR2(3) CONSTRAINT "INSTITUTE_LA_RATESN2" NOT NULL ENABLE, 
"FISCAL_YEAR" CHAR(4) CONSTRAINT "INSTITUTE_LA_RATESN3" NOT NULL ENABLE,        
"START_DATE" DATE CONSTRAINT "INSTITUTE_LA_RATESN4" NOT NULL ENABLE,            
"ON_OFF_CAMPUS_FLAG" CHAR(1) CONSTRAINT "INSTITUTE_LA_RATESN5" NOT NULL ENABLE, 
"RATE" NUMBER(5,2) CONSTRAINT "INSTITUTE_LA_RATESN6" NOT NULL ENABLE,           
"UPDATE_TIMESTAMP" DATE CONSTRAINT "INSTITUTE_LA_RATESN7" NOT NULL ENABLE,      
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "INSTITUTE_LA_RATESN8" NOT NULL ENABLE,    
"RATE_CLASS_CODE" VARCHAR2(3) CONSTRAINT "INSTITUTE_LA_RATESN9" NOT NULL ENABLE,
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "INSTITUTE_LA_RATESN10" NOT NULL     
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "INSTITUTE_LA_RATESN11" NOT 
NULL ENABLE,                                                                    
CONSTRAINT "PK_INSTITUTE_LA_RATES_KRA" PRIMARY KEY ("UNIT_NUMBER",              
"RATE_CLASS_CODE", "RATE_TYPE_CODE", "FISCAL_YEAR", "START_DATE",               
"ON_OFF_CAMPUS_FLAG") ENABLE                                                    
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "INSTITUTE_RATES"                                                  
(	"RATE_CLASS_CODE" VARCHAR2(3) CONSTRAINT "INSTITUTE_RATESN1" NOT NULL ENABLE, 
"RATE_TYPE_CODE" VARCHAR2(3) CONSTRAINT "INSTITUTE_RATESN2" NOT NULL ENABLE,    
"ACTIVITY_TYPE_CODE" VARCHAR2(3) CONSTRAINT "INSTITUTE_RATESN3" NOT NULL ENABLE,
"FISCAL_YEAR" CHAR(4) CONSTRAINT "INSTITUTE_RATESN4" NOT NULL ENABLE,           
"START_DATE" DATE CONSTRAINT "INSTITUTE_RATESN5" NOT NULL ENABLE,               
"ON_OFF_CAMPUS_FLAG" CHAR(1) CONSTRAINT "INSTITUTE_RATESN6" NOT NULL ENABLE,    
"RATE" NUMBER(5,2) CONSTRAINT "INSTITUTE_RATESN7" NOT NULL ENABLE,              
"UPDATE_TIMESTAMP" DATE CONSTRAINT "INSTITUTE_RATESN8" NOT NULL ENABLE,         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "INSTITUTE_RATESN9" NOT NULL ENABLE,       
"UNIT_NUMBER" VARCHAR2(8) CONSTRAINT "INSTITUTE_RATESN10" NOT NULL ENABLE,      
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "INSTITUTE_RATESN11" NOT NULL ENABLE,
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "INSTITUTE_RATESN12" NOT    
NULL ENABLE,                                                                    
CONSTRAINT "PK_INST_RATES_KRA" PRIMARY KEY ("RATE_CLASS_CODE", "RATE_TYPE_CODE",
"ACTIVITY_TYPE_CODE", "FISCAL_YEAR", "START_DATE", "ON_OFF_CAMPUS_FLAG",        
"UNIT_NUMBER") ENABLE
   ) ;
                                                   
                                                                                
CREATE TABLE "EPS_PROP_RATES"                                                   
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_RATESN1" NOT NULL ENABLE, 
"VERSION_NUMBER" NUMBER(3,0) CONSTRAINT "EPS_PROP_RATESN2" NOT NULL ENABLE,     
"RATE_CLASS_CODE" VARCHAR2(3) CONSTRAINT "EPS_PROP_RATESN3" NOT NULL ENABLE,    
"RATE_TYPE_CODE" VARCHAR2(3) CONSTRAINT "EPS_PROP_RATESN4" NOT NULL ENABLE,     
"FISCAL_YEAR" CHAR(4) CONSTRAINT "EPS_PROP_RATESN5" NOT NULL ENABLE,            
"ON_OFF_CAMPUS_FLAG" CHAR(1) CONSTRAINT "EPS_PROP_RATESN6" NOT NULL ENABLE,     
"ACTIVITY_TYPE_CODE" VARCHAR2(3) CONSTRAINT "EPS_PROP_RATESN7" NOT NULL ENABLE, 
"START_DATE" DATE CONSTRAINT "EPS_PROP_RATESN8" NOT NULL ENABLE,                
"APPLICABLE_RATE" NUMBER(5,2) CONSTRAINT "EPS_PROP_RATESN9" NOT NULL ENABLE,    
"INSTITUTE_RATE" NUMBER(5,2),                                                   
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_RATESN10" NOT NULL ENABLE,         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_RATESN11" NOT NULL ENABLE,       
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_RATESN12" NOT NULL ENABLE, 
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROP_RATESN13" NOT NULL
ENABLE,                                                                         
CONSTRAINT "PK_EPS_PROP_RATES_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",              
"VERSION_NUMBER", "RATE_CLASS_CODE", "RATE_TYPE_CODE", "FISCAL_YEAR",           
"START_DATE", "ON_OFF_CAMPUS_FLAG") ENABLE                                      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "BUDGET_PERSONNEL_CAL_AMTS"                                        
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "BUDGET_PERSONNEL_CAL_AMTSN1" NOT   
NULL ENABLE,                                                                    
"VERSION_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_PERSONNEL_CAL_AMTSN2" NOT NULL  
ENABLE,                                                                         
"BUDGET_PERIOD" NUMBER(3,0) CONSTRAINT "BUDGET_PERSONNEL_CAL_AMTSN3" NOT NULL   
ENABLE,                                                                         
"LINE_ITEM_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_PERSONNEL_CAL_AMTSN4" NOT NULL
ENABLE,                                                                         
"PERSON_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_PERSONNEL_CAL_AMTSN5" NOT NULL   
ENABLE,                                                                         
"RATE_CLASS_CODE" VARCHAR2(3) CONSTRAINT "BUDGET_PERSONNEL_CAL_AMTSN6" NOT NULL 
ENABLE,                                                                         
"RATE_TYPE_CODE" VARCHAR2(3) CONSTRAINT "BUDGET_PERSONNEL_CAL_AMTSN7" NOT NULL  
ENABLE,                                                                         
"APPLY_RATE_FLAG" CHAR(1) CONSTRAINT "BUDGET_PERSONNEL_CAL_AMTSN8" NOT NULL     
ENABLE,                                                                         
"CALCULATED_COST" NUMBER(12,2),                                                 
"CALCULATED_COST_SHARING" NUMBER(12,2),                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_PERSONNEL_CAL_AMTSN9" NOT NULL       
ENABLE,                                                                         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_PERSONNEL_CAL_AMTSN10" NOT NULL    
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_PERSONNEL_CAL_AMTSN11" NOT   
NULL ENABLE,                                                                    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT                             
"BUDGET_PERSONNEL_CAL_AMTSN12" NOT NULL ENABLE,                                 
CONSTRAINT "PK_BUDGET_PERS_CAL_AMTS_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",        
"VERSION_NUMBER", "BUDGET_PERIOD", "LINE_ITEM_NUMBER", "PERSON_NUMBER",         
"RATE_CLASS_CODE", "RATE_TYPE_CODE") ENABLE
   ) ;
                             
                                                                                
CREATE TABLE "VALID_CE_RATE_TYPES"                                              
(	"COST_ELEMENT" VARCHAR2(8) CONSTRAINT "VALID_CE_RATE_TYPESN1" NOT NULL ENABLE,
"RATE_CLASS_CODE" VARCHAR2(3) CONSTRAINT "VALID_CE_RATE_TYPESN2" NOT NULL       
ENABLE,                                                                         
"RATE_TYPE_CODE" VARCHAR2(3) CONSTRAINT "VALID_CE_RATE_TYPESN3" NOT NULL ENABLE,
"UPDATE_TIMESTAMP" DATE CONSTRAINT "VALID_CE_RATE_TYPESN4" NOT NULL ENABLE,     
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "VALID_CE_RATE_TYPESN5" NOT NULL ENABLE,   
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "VALID_CE_RATE_TYPESN6" NOT NULL     
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "VALID_CE_RATE_TYPESN7" NOT 
NULL ENABLE,                                                                    
CONSTRAINT "PK_VALID_CE_RATE_TYPES_KRA" PRIMARY KEY ("COST_ELEMENT",            
"RATE_CLASS_CODE", "RATE_TYPE_CODE") ENABLE                                     
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "COST_ELEMENT"                                                     
(	"COST_ELEMENT" VARCHAR2(8) CONSTRAINT "COST_ELEMENTN1" NOT NULL ENABLE,       
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "COST_ELEMENTN2" NOT NULL ENABLE,        
"BUDGET_CATEGORY_CODE" VARCHAR2(3),                                             
"ON_OFF_CAMPUS_FLAG" CHAR(1) CONSTRAINT "COST_ELEMENTN3" NOT NULL ENABLE,       
"UPDATE_TIMESTAMP" DATE CONSTRAINT "COST_ELEMENTN4" NOT NULL ENABLE,            
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "COST_ELEMENTN5" NOT NULL ENABLE,          
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "COST_ELEMENTN6" NOT NULL ENABLE,    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "COST_ELEMENTN7" NOT NULL   
ENABLE,                                                                         
CONSTRAINT "PK_COST_ELEMENT_KRA" PRIMARY KEY ("COST_ELEMENT") ENABLE
   ) ;
    
                                                                                
CREATE TABLE "BUDGET_CATEGORY"                                                  
(	"BUDGET_CATEGORY_CODE" VARCHAR2(3) CONSTRAINT "BUDGET_CATEGORYN1" NOT NULL    
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "BUDGET_CATEGORYN2" NOT NULL ENABLE,     
"CATEGORY_TYPE" CHAR(1),                                                        
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_CATEGORYN3" NOT NULL ENABLE,         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_CATEGORYN4" NOT NULL ENABLE,       
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_CATEGORYN5" NOT NULL ENABLE, 
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "BUDGET_CATEGORYN6" NOT NULL
ENABLE,                                                                         
CONSTRAINT "PK_BUDGET_CATEGORY_KRA" PRIMARY KEY ("BUDGET_CATEGORY_CODE") ENABLE 
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "VALID_CE_JOB_CODES"                                               
(	"COST_ELEMENT" VARCHAR2(8) CONSTRAINT "VALID_CE_JOB_CODESN1" NOT NULL ENABLE, 
"JOB_CODE" VARCHAR2(6) CONSTRAINT "VALID_CE_JOB_CODESN2" NOT NULL ENABLE,       
"UPDATE_TIMESTAMP" DATE CONSTRAINT "VALID_CE_JOB_CODESN3" NOT NULL ENABLE,      
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "VALID_CE_JOB_CODESN4" NOT NULL ENABLE,    
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "VALID_CE_JOB_CODESN5" NOT NULL      
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "VALID_CE_JOB_CODESN6" NOT  
NULL ENABLE,                                                                    
CONSTRAINT "PK_VALID_CE_JOB_CODES_KRA" PRIMARY KEY ("COST_ELEMENT", "JOB_CODE") 
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "S2S_OPP_FORMS"                                                    
(	"PROPOSAL_NUMBER" VARCHAR2(50) CONSTRAINT "S2S_OPP_FORMSN1" NOT NULL ENABLE,  
"OPP_NAME_SPACE" VARCHAR2(200) CONSTRAINT "S2S_OPP_FORMSN2" NOT NULL ENABLE,    
"FORM_NAME" VARCHAR2(100),                                                      
"MANDATORY" VARCHAR2(1) DEFAULT 'N',                                            
"AVAILABLE" VARCHAR2(1) DEFAULT 'Y',                                            
"INCLUDE" VARCHAR2(1) DEFAULT 'Y',                                              
"UPDATE_TIMESTAMP" DATE CONSTRAINT "S2S_OPP_FORMSN3" NOT NULL ENABLE,           
"UPDATE_USER" VARCHAR2(10) CONSTRAINT "S2S_OPP_FORMSN4" NOT NULL ENABLE,        
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "S2S_OPP_FORMSN5" NOT NULL ENABLE,   
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "S2S_OPP_FORMSN6" NOT NULL  
ENABLE,                                                                         
CONSTRAINT "PK_S2S_OPP_FORMS_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",               
"OPP_NAME_SPACE") ENABLE                                                        
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "S2S_APP_SUBMISSION"                                               
(	"PROPOSAL_NUMBER" VARCHAR2(8) CONSTRAINT "S2S_APP_SUBMISSIONN1" NOT NULL      
ENABLE,                                                                         
"SUBMISSION_NUMBER" NUMBER(3,0) CONSTRAINT "S2S_APP_SUBMISSIONN2" NOT NULL      
ENABLE,                                                                         
"COMMENTS" VARCHAR2(2000),                                                      
"STATUS" VARCHAR2(50),                                                          
"GG_TRACKING_ID" VARCHAR2(50),                                                  
"AGENCY_TRACKING_ID" VARCHAR2(50),                                              
"RECEIVED_DATE" DATE,                                                           
"LAST_MODIFIED_DATE" DATE,                                                      
"LAST_NOTIFIED_DATE" DATE,                                                      
"UPDATE_TIMESTAMP" DATE,                                                        
"UPDATE_USER" VARCHAR2(8),                                                      
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "S2S_APP_SUBMISSIONN3" NOT NULL      
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "S2S_APP_SUBMISSIONN4" NOT  
NULL ENABLE,                                                                    
CONSTRAINT "PK_S2S_APP_SUBMISSION_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",          
"SUBMISSION_NUMBER") ENABLE
   ) ;
                                             
                                                                                
CREATE TABLE "S2S_APP_ATTACHMENTS"                                              
(	"CONTENT_ID" VARCHAR2(300) CONSTRAINT "S2S_APP_ATTACHMENTSN1" NOT NULL ENABLE,
"PROPOSAL_NUMBER" VARCHAR2(8) CONSTRAINT "S2S_APP_ATTACHMENTSN2" NOT NULL       
ENABLE,                                                                         
"HASH_CODE" VARCHAR2(200),                                                      
"UPDATE_TIMESTAMP" DATE,                                                        
"UPDATE_USER" VARCHAR2(10),                                                     
"CONTENT_TYPE" VARCHAR2(30),                                                    
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "S2S_APP_ATTACHMENTSN3" NOT NULL     
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "S2S_APP_ATTACHMENTSN4" NOT 
NULL ENABLE,                                                                    
CONSTRAINT "PK_S2S_ATTACHMENTS_KRA" PRIMARY KEY ("CONTENT_ID",                  
"PROPOSAL_NUMBER") ENABLE                                                       
 ) ;
                                                                           
                                                                                
CREATE TABLE "EN_TRANSACTION_TST_PRSN_T"                                        
(	"PRSN_ID" NUMBER(19,0) NOT NULL ENABLE,                                       
"FRST_NM" VARCHAR2(2000) NOT NULL ENABLE,                                       
"LST_NM" VARCHAR2(2000) NOT NULL ENABLE,                                        
"EMAIL" VARCHAR2(2000),                                                         
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_TRANSACTION_TST_PRSN_T_PK" PRIMARY KEY ("PRSN_ID") ENABLE        
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_USR_OPTN_T"                                                    
(	"PRSN_EN_ID" VARCHAR2(30),                                                    
"PRSN_OPTN_ID" VARCHAR2(200) NOT NULL ENABLE,                                   
"PRSN_OPTN_VAL" VARCHAR2(2000),                                                 
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_USR_OPTN_T_PK" PRIMARY KEY ("PRSN_EN_ID", "PRSN_OPTN_ID") ENABLE 
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_USR_T"                                                         
(	"PRSN_EN_ID" VARCHAR2(30) NOT NULL ENABLE,                                    
"PRSN_UNIV_ID" VARCHAR2(11) NOT NULL ENABLE,                                    
"PRSN_NTWRK_ID" VARCHAR2(30),                                                   
"PRSN_UNVL_USR_ID" VARCHAR2(10),                                                
"PRSN_EMAIL_ADDR" VARCHAR2(255),                                                
"PRSN_NM" VARCHAR2(255),                                                        
"PRSN_GVN_NM" VARCHAR2(255),                                                    
"PRSN_LST_NM" VARCHAR2(255),                                                    
"USR_CRTE_DT" DATE,                                                             
"USR_LST_UPDT_DT" DATE,                                                         
"PRSN_ID_MSNG_IND" NUMBER(1,0) DEFAULT 0,                                       
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_USR_T_PK" PRIMARY KEY ("PRSN_EN_ID") ENABLE
   ) ;
              
                                                                                
CREATE TABLE "EN_WRKGRP_EXT_DTA_T"                                              
(	"WRKGRP_EXT_DTA_ID" NUMBER(19,0) NOT NULL ENABLE,                             
"WRKGRP_EXT_ID" NUMBER(19,0) NOT NULL ENABLE,                                   
"EXT_KEY" VARCHAR2(2000) NOT NULL ENABLE,                                       
"EXT_VAL" VARCHAR2(2000),                                                       
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_WRKGRP_EXT_DTA_T_PK" PRIMARY KEY ("WRKGRP_EXT_DTA_ID") ENABLE    
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_WRKGRP_EXT_T"                                                  
(	"WRKGRP_EXT_ID" NUMBER(19,0) NOT NULL ENABLE,                                 
"WRKGRP_ID" NUMBER(19,0) NOT NULL ENABLE,                                       
"WRKGRP_VER_NBR" NUMBER(8,0) NOT NULL ENABLE,                                   
"WRKGRP_TYP_ATTRIB_ID" NUMBER(19,0) NOT NULL ENABLE,                            
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_WRKGRP_EXT_T_PK" PRIMARY KEY ("WRKGRP_EXT_ID") ENABLE
   ) ;
    
                                                                                
CREATE TABLE "EN_WRKGRP_MBR_T"                                                  
(	"WRKGRP_MBR_PRSN_EN_ID" VARCHAR2(30) NOT NULL ENABLE,                         
"WRKGRP_ID" NUMBER(14,0) NOT NULL ENABLE,                                       
"WRKGRP_MBR_TYP" CHAR(1) NOT NULL ENABLE,                                       
"WRKGRP_VER_NBR" NUMBER(8,0) DEFAULT 0,                                         
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_WRKGRP_MBR_T_PK" PRIMARY KEY ("WRKGRP_MBR_PRSN_EN_ID",           
"WRKGRP_ID", "WRKGRP_VER_NBR") ENABLE                                           
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_WRKGRP_T"                                                      
(	"WRKGRP_ID" NUMBER(14,0) NOT NULL ENABLE,                                     
"WRKGRP_VER_NBR" NUMBER(8,0) DEFAULT 0,                                         
"WRKGRP_NM" VARCHAR2(70) NOT NULL ENABLE,                                       
"WRKGRP_ACTV_IND" NUMBER(1,0) NOT NULL ENABLE,                                  
"WRKGRP_TYP_CD" VARCHAR2(255),                                                  
"WRKGRP_DESC" VARCHAR2(2000),                                                   
"WRKGRP_CUR_IND" NUMBER(1,0) DEFAULT 0,                                         
"DOC_HDR_ID" NUMBER(14,0),                                                      
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_WRKGRP_T_PK" PRIMARY KEY ("WRKGRP_ID", "WRKGRP_VER_NBR") ENABLE  
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_WRKGRP_TYP_ATTRIB_T"                                           
(	"WRKGRP_TYP_ATTRIB_ID" NUMBER(19,0) NOT NULL ENABLE,                          
"WRKGRP_TYP_ID" NUMBER(19,0) NOT NULL ENABLE,                                   
"ATTRIB_ID" NUMBER(19,0) NOT NULL ENABLE,                                       
"ACTV_IND" NUMBER(1,0) DEFAULT 1,                                               
"ORD_INDX" NUMBER(4,0) DEFAULT 0,                                               
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_WRKGRP_TYP_ATTRIB_PK" PRIMARY KEY ("WRKGRP_TYP_ATTRIB_ID")       
ENABLE
   ) ;
                                                                  
                                                                                
CREATE TABLE "EN_WRKGRP_TYP_T"                                                  
(	"WRKGRP_TYP_ID" NUMBER(19,0) NOT NULL ENABLE,                                 
"WRKGRP_TYP_NM" VARCHAR2(250) NOT NULL ENABLE,                                  
"WRKGRP_TYP_LBL" VARCHAR2(2000) NOT NULL ENABLE,                                
"WRKGRP_TYP_DESC" VARCHAR2(2000),                                               
"DOC_TYP_NM" VARCHAR2(255),                                                     
"ACTV_IND" NUMBER(1,0) DEFAULT 1,                                               
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_WRKGRP_TYP_T_PK" PRIMARY KEY ("WRKGRP_TYP_ID") ENABLE
   ) ;
    
                                                                                
CREATE TABLE "KR_QRTZ_CRON_TRIGGERS"                                            
(	"TRIGGER_NAME" VARCHAR2(80) NOT NULL ENABLE,                                  
"TRIGGER_GROUP" VARCHAR2(80) NOT NULL ENABLE,                                   
"CRON_EXPRESSION" VARCHAR2(80) NOT NULL ENABLE,                                 
"TIME_ZONE_ID" VARCHAR2(80),                                                    
PRIMARY KEY ("TRIGGER_NAME", "TRIGGER_GROUP") ENABLE
   ) ;
                    
                                                                                
CREATE TABLE "KR_QRTZ_FIRED_TRIGGERS"                                           
(	"ENTRY_ID" VARCHAR2(95) NOT NULL ENABLE,                                      
"TRIGGER_NAME" VARCHAR2(80) NOT NULL ENABLE,                                    
"TRIGGER_GROUP" VARCHAR2(80) NOT NULL ENABLE,                                   
"IS_VOLATILE" VARCHAR2(1) NOT NULL ENABLE,                                      
"INSTANCE_NAME" VARCHAR2(80) NOT NULL ENABLE,                                   
"FIRED_TIME" NUMBER(13,0) NOT NULL ENABLE,                                      
"PRIORITY" NUMBER(13,0) NOT NULL ENABLE,                                        
"STATE" VARCHAR2(16) NOT NULL ENABLE,                                           
"JOB_NAME" VARCHAR2(80),                                                        
"JOB_GROUP" VARCHAR2(80),                                                       
"IS_STATEFUL" VARCHAR2(1),                                                      
"REQUESTS_RECOVERY" VARCHAR2(1),                                                
PRIMARY KEY ("ENTRY_ID") ENABLE
   ) ;
                                         
                                                                                
CREATE TABLE "KR_QRTZ_JOB_LISTENERS"                                            
(	"JOB_NAME" VARCHAR2(80) NOT NULL ENABLE,                                      
"JOB_GROUP" VARCHAR2(80) NOT NULL ENABLE,                                       
"JOB_LISTENER" VARCHAR2(80) NOT NULL ENABLE,                                    
PRIMARY KEY ("JOB_NAME", "JOB_GROUP", "JOB_LISTENER") ENABLE
   ) ;
            
                                                                                
CREATE TABLE "KR_QRTZ_LOCKS"                                                    
(	"LOCK_NAME" VARCHAR2(40) NOT NULL ENABLE,                                     
PRIMARY KEY ("LOCK_NAME") ENABLE                                                
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KR_QRTZ_PAUSED_TRIGGERS_GRPS"                                     
(	"TRIGGER_GROUP" VARCHAR2(80) NOT NULL ENABLE,                                 
PRIMARY KEY ("TRIGGER_GROUP") ENABLE                                            
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KR_QRTZ_SCHEDULER_STATE"                                          
(	"INSTANCE_NAME" VARCHAR2(80) NOT NULL ENABLE,                                 
"LAST_CHECKIN_TIME" NUMBER(13,0) NOT NULL ENABLE,                               
"CHECKIN_INTERVAL" NUMBER(13,0) NOT NULL ENABLE,                                
PRIMARY KEY ("INSTANCE_NAME") ENABLE                                            
   ) ;
                                                                         
                                                                                
CREATE TABLE "KR_QRTZ_SIMPLE_TRIGGERS"                                          
(	"TRIGGER_NAME" VARCHAR2(80) NOT NULL ENABLE,                                  
"TRIGGER_GROUP" VARCHAR2(80) NOT NULL ENABLE,                                   
"REPEAT_COUNT" NUMBER(7,0) NOT NULL ENABLE,                                     
"REPEAT_INTERVAL" NUMBER(12,0) NOT NULL ENABLE,                                 
"TIMES_TRIGGERED" NUMBER(7,0) NOT NULL ENABLE,                                  
PRIMARY KEY ("TRIGGER_NAME", "TRIGGER_GROUP") ENABLE
   ) ;
                    
                                                                                
CREATE TABLE "KR_QRTZ_TRIGGER_LISTENERS"                                        
(	"TRIGGER_NAME" VARCHAR2(80) NOT NULL ENABLE,                                  
"TRIGGER_GROUP" VARCHAR2(80) NOT NULL ENABLE,                                   
"TRIGGER_LISTENER" VARCHAR2(80) NOT NULL ENABLE,                                
PRIMARY KEY ("TRIGGER_NAME", "TRIGGER_GROUP", "TRIGGER_LISTENER") ENABLE        
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KIM_ATTRIBUTE_TYPES_T"                                            
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NAME" VARCHAR2(500) NOT NULL ENABLE,                                           
"DESCRIPTION" VARCHAR2(4000),                                                   
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_ATTRIBUTE_TYPES_UK1" UNIQUE ("NAME") ENABLE,                    
CONSTRAINT "KIM_ATTRIBUTE_TYPES_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
           
                                                                                
CREATE TABLE "KIM_GROUPS_GROUPS_T"                                              
(	"PARENT_GROUP_ID" NUMBER(8,0) NOT NULL ENABLE,                                
"MEMBER_GROUP_ID" NUMBER(8,0) NOT NULL ENABLE,                                  
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_GROUPS_GROUPS_PK" PRIMARY KEY ("PARENT_GROUP_ID",               
"MEMBER_GROUP_ID") ENABLE                                                       
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KIM_GROUPS_PERSONS_T"                                             
(	"GROUP_ID" NUMBER(8,0) NOT NULL ENABLE,                                       
"PERSON_ID" NUMBER(8,0) NOT NULL ENABLE,                                        
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_GROUPS_PERSONS_PK" PRIMARY KEY ("GROUP_ID", "PERSON_ID") ENABLE 
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KIM_GROUPS_T"                                                     
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NAME" VARCHAR2(500) NOT NULL ENABLE,                                           
"DESCRIPTION" VARCHAR2(4000),                                                   
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_GROUPS_UK1" UNIQUE ("NAME") ENABLE,                             
CONSTRAINT "KIM_GROUPS_PK" PRIMARY KEY ("ID") ENABLE                            
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KIM_GROUP_ATTRIBUTES_T"                                           
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"GROUP_ID" NUMBER(8,0) NOT NULL ENABLE,                                         
"ATTRIBUTE_NAME" VARCHAR2(500) NOT NULL ENABLE,                                 
"ATTRIBUTE_TYPE_ID" NUMBER(8,0) NOT NULL ENABLE,                                
"ATTRIBUTE_VALUES" VARCHAR2(4000),                                              
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_GROUP_ATTRIBUTES_UK1" UNIQUE ("GROUP_ID", "ATTRIBUTE_NAME")     
ENABLE,                                                                         
CONSTRAINT "KIM_GROUP_ATTRIBUTES_PK" PRIMARY KEY ("ID") ENABLE                  
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KIM_NAMESPACES_T"                                                 
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NAME" VARCHAR2(500) NOT NULL ENABLE,                                           
"DESCRIPTION" VARCHAR2(4000),                                                   
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_NAMESPACES_T_UK1" UNIQUE ("NAME") ENABLE,                       
CONSTRAINT "KIM_NAMESPACE_PK" PRIMARY KEY ("ID") ENABLE                         
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KIM_NAMESPACE_DFLT_ATTRIBS_T"                                     
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NAMESPACE_ID" NUMBER(8,0) NOT NULL ENABLE,                                     
"ATTRIBUTE_NAME" VARCHAR2(500) NOT NULL ENABLE,                                 
"ATTRIBUTE_TYPE_ID" NUMBER(8,0) NOT NULL ENABLE,                                
"DESCRIPTION" VARCHAR2(4000),                                                   
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_NMSPCE_DFLT_ATTR_UK1" UNIQUE ("NAMESPACE_ID",                   
"ATTRIBUTE_NAME") ENABLE,                                                       
CONSTRAINT "KIM_NMSPCE_DFLT_ATTR_PK" PRIMARY KEY ("ID") ENABLE                  
) ;
                                                                            
                                                                                
CREATE TABLE "KIM_PERMISSIONS_T"                                                
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NAME" VARCHAR2(500) NOT NULL ENABLE,                                           
"DESCRIPTION" VARCHAR2(4000),                                                   
"NAMESPACE_ID" NUMBER(8,0) NOT NULL ENABLE,                                     
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_PERMISSIONS_UK1" UNIQUE ("NAME", "NAMESPACE_ID") ENABLE,        
CONSTRAINT "KIM_PERMISSIONS_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
               
                                                                                
CREATE TABLE "KIM_PERSONS_T"                                                    
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"USERNAME" VARCHAR2(500) NOT NULL ENABLE,                                       
"PASSWORD" VARCHAR2(500),                                                       
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_PERSONS_UK1" UNIQUE ("USERNAME") ENABLE,                        
CONSTRAINT "KIM_PERSONS_PK" PRIMARY KEY ("ID") ENABLE                           
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KIM_PERSON_ATTRIBUTES_T"                                          
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"PERSON_ID" NUMBER(8,0) NOT NULL ENABLE,                                        
"SPONSOR_NAMESPACE_ID" NUMBER(8,0) NOT NULL ENABLE,                             
"ATTRIBUTE_NAME" VARCHAR2(500) NOT NULL ENABLE,                                 
"ATTRIBUTE_TYPE_ID" NUMBER(8,0) NOT NULL ENABLE,                                
"ATTRIBUTE_VALUES" VARCHAR2(4000),                                              
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_PERSON_ATTRIBUTES_UK1" UNIQUE ("PERSON_ID",                     
"SPONSOR_NAMESPACE_ID", "ATTRIBUTE_NAME") ENABLE,                               
CONSTRAINT "KIM_PERSON_ATTRIBUTES_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
         
                                                                                
CREATE TABLE "KIM_ROLES_PERMISSIONS_T"                                          
(	"ROLE_ID" NUMBER(8,0) NOT NULL ENABLE,                                        
"PERMISSION_ID" NUMBER(8,0) NOT NULL ENABLE,                                    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_ROLES_PERMISSIONS_PK" PRIMARY KEY ("ROLE_ID", "PERMISSION_ID")  
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KIM_ROLES_T"                                                      
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NAME" VARCHAR2(500) NOT NULL ENABLE,                                           
"DESCRIPTION" VARCHAR2(4000),                                                   
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_ROLES_PK" PRIMARY KEY ("ID") ENABLE,                            
CONSTRAINT "KIM_ROLES_UK1" UNIQUE ("NAME") ENABLE                               
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KIM_ROLE_ATTRIBUTES_T"                                            
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"ROLE_ID" NUMBER(8,0) NOT NULL ENABLE,                                          
"ATTRIBUTE_NAME" VARCHAR2(500) NOT NULL ENABLE,                                 
"ATTRIBUTE_TYPE_ID" NUMBER(8,0) NOT NULL ENABLE,                                
"ATTRIBUTE_VALUE" VARCHAR2(4000),                                               
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_ROLE_ATTRIBUTES_PK" PRIMARY KEY ("ID") ENABLE,                  
CONSTRAINT "KIM_ROLE_ATTRIBUTES_UK1" UNIQUE ("ROLE_ID", "ATTRIBUTE_NAME") ENABLE
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "NOTIFICATION_CHANNELS"                                            
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NAME" VARCHAR2(1000) NOT NULL ENABLE,                                          
"DESCRIPTION" VARCHAR2(4000) NOT NULL ENABLE,                                   
"SUBSCRIBABLE" CHAR(1) NOT NULL ENABLE,                                         
CONSTRAINT "NOTIFICATION_CHANNELS_UK1" UNIQUE ("NAME") ENABLE,                  
CONSTRAINT "NOTIFICATION_CHANNELS_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
         
                                                                                
CREATE TABLE "NOTIFICATION_CHANNEL_PRODUCERS"                                   
(	"CHANNEL_ID" NUMBER(8,0) NOT NULL ENABLE,                                     
"PRODUCER_ID" NUMBER(6,0) NOT NULL ENABLE,                                      
CONSTRAINT "NOTIFICATION_CHANNEL_PROD_PK" PRIMARY KEY ("CHANNEL_ID",            
"PRODUCER_ID") ENABLE                                                           
   ) ;
                                                                         
                                                                                
CREATE TABLE "NOTIFICATION_MSG_DELIVS"                                          
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NOTIFICATION_ID" NUMBER(8,0) NOT NULL ENABLE,                                  
"USER_RECIPIENT_ID" VARCHAR2(300) NOT NULL ENABLE,                              
"MESSAGE_DELIVERY_TYPE_NAME" VARCHAR2(500) NOT NULL ENABLE,                     
"MESSAGE_DELIVERY_STATUS" VARCHAR2(15) NOT NULL ENABLE,                         
"DELIVERY_SYSTEM_ID" VARCHAR2(300),                                             
"LOCKED_DATE" TIMESTAMP (6),                                                    
"DB_LOCK_VER_NBR" NUMBER(*,0) DEFAULT 0 NOT NULL ENABLE,                        
CONSTRAINT "NOTIF_MSG_DELIVS_UK1" UNIQUE ("NOTIFICATION_ID",                    
"USER_RECIPIENT_ID", "MESSAGE_DELIVERY_TYPE_NAME") ENABLE,                      
CONSTRAINT "NOTIFICATION_MSG_DELIVS_PK" PRIMARY KEY ("ID") ENABLE               
) ;
                                                                            
                                                                                
CREATE TABLE "NOTIFICATION_PRIORITIES"                                          
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NAME" VARCHAR2(40) NOT NULL ENABLE,                                            
"DESCRIPTION" VARCHAR2(500) NOT NULL ENABLE,                                    
"PRIORITY_ORDER" NUMBER(4,0) NOT NULL ENABLE,                                   
CONSTRAINT "NOTIFICATION_PRIORITIES_UK1" UNIQUE ("NAME") ENABLE,                
CONSTRAINT "NOTIFICATION_PRIORITY_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
         
                                                                                
CREATE TABLE "NOTIFICATION_PRODUCERS"                                           
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NAME" VARCHAR2(500) NOT NULL ENABLE,                                           
"DESCRIPTION" VARCHAR2(1000) NOT NULL ENABLE,                                   
"CONTACT_INFO" VARCHAR2(1000) NOT NULL ENABLE,                                  
CONSTRAINT "NOTIFICATION_PRODUCERS_UK1" UNIQUE ("NAME") ENABLE,                 
CONSTRAINT "NOTIFICATION_PRODUCER_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
         
                                                                                
CREATE TABLE "NOTIFICATION_RECIPIENTS"                                          
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NOTIFICATION_ID" NUMBER(8,0) NOT NULL ENABLE,                                  
"RECIPIENT_TYPE" VARCHAR2(10) NOT NULL ENABLE,                                  
"RECIPIENT_ID" VARCHAR2(300) NOT NULL ENABLE,                                   
CONSTRAINT "NOTIFICATION_RECIPIENTS_UK1" UNIQUE ("NOTIFICATION_ID",             
"RECIPIENT_TYPE", "RECIPIENT_ID") ENABLE,                                       
CONSTRAINT "NOTIFICATION_RECIPIENTS_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
       
                                                                                
CREATE TABLE "NOTIFICATION_RECIPIENTS_LISTS"                                    
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"CHANNEL_ID" NUMBER(8,0) NOT NULL ENABLE,                                       
"RECIPIENT_TYPE" VARCHAR2(10) NOT NULL ENABLE,                                  
"RECIPIENT_ID" VARCHAR2(300) NOT NULL ENABLE,                                   
CONSTRAINT "NOTIFICATION_RECIPIENTS_L_UK1" UNIQUE ("CHANNEL_ID",                
"RECIPIENT_TYPE", "RECIPIENT_ID") ENABLE,                                       
CONSTRAINT "NOTIFICATION_RECIPIENTS_L_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
     
                                                                                
CREATE TABLE "NOTIFICATION_REVIEWERS"                                           
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"CHANNEL_ID" NUMBER(8,0) NOT NULL ENABLE,                                       
"REVIEWER_TYPE" VARCHAR2(10) NOT NULL ENABLE,                                   
"REVIEWER_ID" VARCHAR2(300) NOT NULL ENABLE,                                    
CONSTRAINT "NOTIFICATION_REVIEWERS_UK1" UNIQUE ("CHANNEL_ID", "REVIEWER_TYPE",  
"REVIEWER_ID") ENABLE,                                                          
CONSTRAINT "NOTIFICATION_REVIEWERS_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
        
                                                                                
CREATE TABLE "NOTIFICATION_SENDERS"                                             
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"NOTIFICATION_ID" NUMBER(8,0) NOT NULL ENABLE,                                  
"NAME" VARCHAR2(500) NOT NULL ENABLE,                                           
CONSTRAINT "NOTIFICATION_SENDER_PK" PRIMARY KEY ("ID") ENABLE,                  
CONSTRAINT "NOTIFICATION_SENDERS_UK1" UNIQUE ("NOTIFICATION_ID", "NAME") ENABLE 
) ;
                                                                            
                                                                                
CREATE TABLE "RECIPIENT_PREFERENCES"                                            
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"RECIPIENT_TYPE" VARCHAR2(10) NOT NULL ENABLE,                                  
"RECIPIENT_ID" VARCHAR2(300) NOT NULL ENABLE,                                   
"PROPERTY" VARCHAR2(300) NOT NULL ENABLE,                                       
"VALUE" VARCHAR2(1000) NOT NULL ENABLE,                                         
CONSTRAINT "RECIPIENT_PREFERENCES_UK1" UNIQUE ("RECIPIENT_TYPE", "RECIPIENT_ID",
"PROPERTY") ENABLE,                                                             
CONSTRAINT "RECIPIENT_PREFERENCES_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
         
                                                                                
CREATE TABLE "USER_CHANNEL_SUBSCRIPTIONS"                                       
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"CHANNEL_ID" NUMBER(8,0) NOT NULL ENABLE,                                       
"USER_ID" VARCHAR2(300) NOT NULL ENABLE,                                        
CONSTRAINT "USER_CHANNEL_SUBSCRIPTION_UK1" UNIQUE ("CHANNEL_ID", "USER_ID")     
ENABLE,                                                                         
CONSTRAINT "USER_CHANNEL_SUBSCRIPTION_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
     
                                                                                
CREATE TABLE "USER_DELIVERER_CONFIG"                                            
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"CHANNEL_ID" NUMBER(8,0) NOT NULL ENABLE,                                       
"USER_ID" VARCHAR2(300) NOT NULL ENABLE,                                        
"DELIVERER_NAME" VARCHAR2(300) NOT NULL ENABLE,                                 
CONSTRAINT "USER_DELIVERER_CONFIG_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
         
                                                                                
CREATE TABLE "TRV_DOC_2"                                                        
(	"FDOC_NBR" VARCHAR2(14) CONSTRAINT "FP_INT_BILL_DOC_TN1" NOT NULL ENABLE,     
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "FP_INT_BILL_DOC_TN2" NOT   
NULL ENABLE,                                                                    
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "FP_INT_BILL_DOC_TN3" NOT NULL       
ENABLE,                                                                         
"FDOC_EXPLAIN_TXT" VARCHAR2(400),                                               
"REQUEST_TRAV" VARCHAR2(30) NOT NULL ENABLE,                                    
"TRAVELER" VARCHAR2(200),                                                       
"ORG" VARCHAR2(60),                                                             
"DEST" VARCHAR2(60),                                                            
CONSTRAINT "TRV_DOC_2P1" PRIMARY KEY ("FDOC_NBR") ENABLE
   ) ;
                
                                                                                
CREATE TABLE "TRV_ACCT"                                                         
(	"ACCT_NUM" VARCHAR2(10) NOT NULL ENABLE,                                      
"ACCT_NAME" VARCHAR2(50),                                                       
"ACCT_TYPE" VARCHAR2(100),                                                      
"ACCT_FO_ID" NUMBER(14,0),                                                      
CONSTRAINT "TRV_ACCT_PK" PRIMARY KEY ("ACCT_NUM") ENABLE                        
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "TRV_DOC_ACCT"                                                     
(	"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                    
"ACCT_NUM" VARCHAR2(10) NOT NULL ENABLE,                                        
CONSTRAINT "TRV_DOC_ACCT_PK" PRIMARY KEY ("DOC_HDR_ID", "ACCT_NUM") ENABLE      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "TRV_ACCT_FO"                                                      
(	"ACCT_FO_ID" NUMBER(14,0) NOT NULL ENABLE,                                    
"ACCT_FO_USER_NAME" VARCHAR2(50) NOT NULL ENABLE,                               
CONSTRAINT "TRV_ACCT_FO_ID_PK" PRIMARY KEY ("ACCT_FO_ID") ENABLE
   ) ;
        
                                                                                
CREATE TABLE "TRAV_DOC_2_ACCOUNTS"                                              
(	"FDOC_NBR" VARCHAR2(14),                                                      
"ACCT_NUM" VARCHAR2(10),                                                        
CONSTRAINT "TRAV_DOC_2_ACCOUNTS_P1" PRIMARY KEY ("FDOC_NBR", "ACCT_NUM") ENABLE 
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "TRV_ACCT_TYPE"                                                    
(	"ACCT_TYPE" VARCHAR2(10),                                                     
"ACCT_TYPE_NAME" VARCHAR2(50),                                                  
CONSTRAINT "TRV_ACCT_TYPE_PK" PRIMARY KEY ("ACCT_TYPE") ENABLE
   ) ;
          
                                                                                
CREATE TABLE "TRV_ACCT_EXT"                                                     
(	"ACCT_NUM" VARCHAR2(10),                                                      
"ACCT_TYPE" VARCHAR2(100),                                                      
CONSTRAINT "TRV_ACCT_TYPE_P1" PRIMARY KEY ("ACCT_NUM", "ACCT_TYPE") ENABLE      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EPS_PROP_PERSON_UNITS"                                            
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_PERSON_UNITS_N2" NOT NULL 
ENABLE,                                                                         
"PROP_PERSON_NUMBER" NUMBER(12,0) CONSTRAINT "EPS_PROP_PERSON_UNITS_N3" NOT NULL
ENABLE,                                                                         
"UNIT_NUMBER" VARCHAR2(8) CONSTRAINT "EPS_PROP_PERSON_UNITS_N4" NOT NULL ENABLE,
"LEAD_UNIT_FLAG" CHAR(1),                                                       
"UPDATE_TIMESTAMP" DATE NOT NULL ENABLE,                                        
"UPDATE_USER" VARCHAR2(8) NOT NULL ENABLE,                                      
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
CONSTRAINT "EPS_PROP_PERSON_UNITS_C0" UNIQUE ("OBJ_ID") ENABLE,                 
CONSTRAINT "EPS_PROP_PERSON_UNITS_N5" PRIMARY KEY ("PROPOSAL_NUMBER",           
"PROP_PERSON_NUMBER", "UNIT_NUMBER") ENABLE
   ) ;
                             
                                                                                
CREATE TABLE "EPS_PROP_USER_ROLES"                                              
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_USER_ROLESN1" NOT NULL    
ENABLE,                                                                         
"USER_ID" VARCHAR2(10) CONSTRAINT "EPS_PROP_USER_ROLESN2" NOT NULL ENABLE,      
"ROLE_ID" NUMBER(5,0) CONSTRAINT "EPS_PROP_USER_ROLESN3" NOT NULL ENABLE,       
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_USER_ROLESN4" NOT NULL ENABLE,     
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_USER_ROLESN5" NOT NULL ENABLE,   
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_USER_ROLESN6" NOT NULL     
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROP_USER_ROLESN7" NOT 
NULL ENABLE,                                                                    
CONSTRAINT "PK_EPS_PROP_USER_ROLES_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",         
"ROLE_ID", "USER_ID") ENABLE                                                    
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "PERSON_EDITABLE_FIELDS"                                           
(	"FIELD_NAME" VARCHAR2(30) CONSTRAINT "PERSON_EDITABLE_FIELDS_N1" NOT NULL     
ENABLE,                                                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "PERSON_EDITABLE_FIELDS_N2" NOT NULL ENABLE, 
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "PERSON_EDITABLE_FIELDS_N3" NOT NULL       
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "PERSON_EDITABLE_FIELDS_N4" NOT NULL 
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"ACTIVE_FLAG" CHAR(1),                                                          
CONSTRAINT "PERSON_EDITABLE_FIELDS_P1" PRIMARY KEY ("FIELD_NAME") ENABLE,       
CONSTRAINT "PERSON_EDITABLE_FIELDS_C0" UNIQUE ("OBJ_ID") ENABLE                 
) ;
                                                                            
                                                                                
CREATE TABLE "PROPOSAL_INV_CERTIFICATION"                                       
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "PROPOSAL_INV_CERTIFICATION_N1" NOT 
NULL ENABLE,                                                                    
"PROP_PERSON_NUMBER" NUMBER(12,0) CONSTRAINT "PROPOSAL_INV_CERTIFICATION_N2" NOT
NULL ENABLE,                                                                    
"CERTIFIED_FLAG" CHAR(1),                                                       
"DATE_CERTIFIED" DATE,                                                          
"DATE_RECEIVED_BY_OSP" DATE,                                                    
"UPDATE_TIMESTAMP" DATE NOT NULL ENABLE,                                        
"UPDATE_USER" VARCHAR2(8) NOT NULL ENABLE,                                      
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
CONSTRAINT "PROPOSAL_INV_CERTIFICATION_N3" PRIMARY KEY ("PROPOSAL_NUMBER",      
"PROP_PERSON_NUMBER") ENABLE,                                                   
CONSTRAINT "PROPOSAL_INV_CERTIFICATION_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
     
                                                                                
CREATE TABLE "VALID_SP_REV_APPROVAL"                                            
(	"SPECIAL_REVIEW_CODE" VARCHAR2(3) CONSTRAINT "VALID_SP_REV_APPROVALN1" NOT    
NULL ENABLE,                                                                    
"APPROVAL_TYPE_CODE" VARCHAR2(3) CONSTRAINT "VALID_SP_REV_APPROVALN2" NOT NULL  
ENABLE,                                                                         
"PROTOCOL_NUMBER_FLAG" CHAR(1) CONSTRAINT "VALID_SP_REV_APPROVALN3" NOT NULL    
ENABLE,                                                                         
"APPROVAL_DATE_FLAG" CHAR(1) CONSTRAINT "VALID_SP_REV_APPROVALN4" NOT NULL      
ENABLE,                                                                         
"APPLICATION_DATE_FLAG" CHAR(1) CONSTRAINT "VALID_SP_REV_APPROVALN5" NOT NULL   
ENABLE,                                                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "VALID_SP_REV_APPROVALN6" NOT NULL ENABLE,   
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "VALID_SP_REV_APPROVALN7" NOT NULL ENABLE, 
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "VALID_SP_REV_APPROVALN8" NOT NULL   
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "VALID_SP_REV_APPROVALN9"   
NOT NULL ENABLE,                                                                
CONSTRAINT "PK_VALID_SP_REV_APPROVAL_KRA" PRIMARY KEY ("SPECIAL_REVIEW_CODE",   
"APPROVAL_TYPE_CODE") ENABLE
   ) ;
                                            
                                                                                
CREATE TABLE "YNQ"                                                              
(	"QUESTION_ID" VARCHAR2(4) CONSTRAINT "YNQN1" NOT NULL ENABLE,                 
"DESCRIPTION" VARCHAR2(400) CONSTRAINT "YNQN2" NOT NULL ENABLE,                 
"QUESTION_TYPE" CHAR(1) CONSTRAINT "YNQN3" NOT NULL ENABLE,                     
"NO_OF_ANSWERS" NUMBER(2,0) CONSTRAINT "YNQN4" NOT NULL ENABLE,                 
"EXPLANATION_REQUIRED_FOR" VARCHAR2(3),                                         
"DATE_REQUIRED_FOR" VARCHAR2(3),                                                
"STATUS" CHAR(1) CONSTRAINT "YNQN5" NOT NULL ENABLE,                            
"EFFECTIVE_DATE" DATE CONSTRAINT "YNQN6" NOT NULL ENABLE,                       
"GROUP_NAME" VARCHAR2(150),                                                     
"UPDATE_TIMESTAMP" DATE CONSTRAINT "YNQN7" NOT NULL ENABLE,                     
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "YNQN8" NOT NULL ENABLE,                   
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "YNQN9" NOT NULL ENABLE,             
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "YNQN10" NOT NULL ENABLE,   
CONSTRAINT "PK_YNQ_KRA" PRIMARY KEY ("QUESTION_ID") ENABLE                      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "RIGHTS"                                                           
(	"RIGHT_ID" VARCHAR2(30) CONSTRAINT "RIGHTSN1" NOT NULL ENABLE,                
"DESCRIPTION" VARCHAR2(80) CONSTRAINT "RIGHTSN2" NOT NULL ENABLE,               
"RIGHT_TYPE" CHAR(1) CONSTRAINT "RIGHTSN3" NOT NULL ENABLE,                     
"DESCEND_FLAG" CHAR(1) CONSTRAINT "RIGHTSN4" NOT NULL ENABLE,                   
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "RIGHTSN5" NOT NULL ENABLE,                
"UPDATE_TIMESTAMP" DATE CONSTRAINT "RIGHTSN6" NOT NULL ENABLE,                  
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "RIGHTSN7" NOT NULL ENABLE,          
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "RIGHTSN8" NOT NULL ENABLE, 
CONSTRAINT "PK_RIGHTS_KRA" PRIMARY KEY ("RIGHT_ID") ENABLE
   ) ;
              
                                                                                
CREATE TABLE "ROLE_RIGHTS"                                                      
(	"RIGHT_ID" VARCHAR2(30) CONSTRAINT "ROLE_RIGHTSN1" NOT NULL ENABLE,           
"ROLE_ID" NUMBER(5,0) CONSTRAINT "ROLE_RIGHTSN2" NOT NULL ENABLE,               
"DESCEND_FLAG" CHAR(1) CONSTRAINT "ROLE_RIGHTSN3" NOT NULL ENABLE,              
"UPDATE_TIMESTAMP" DATE CONSTRAINT "ROLE_RIGHTSN4" NOT NULL ENABLE,             
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "ROLE_RIGHTSN5" NOT NULL ENABLE,           
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "ROLE_RIGHTSN6" NOT NULL ENABLE,     
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "ROLE_RIGHTSN7" NOT NULL    
ENABLE,                                                                         
CONSTRAINT "PK_ROLE_RIGHTS_KRA" PRIMARY KEY ("ROLE_ID", "RIGHT_ID") ENABLE      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "USER_ROLES"                                                       
(	"USER_ID" VARCHAR2(10) CONSTRAINT "USER_ROLESN1" NOT NULL ENABLE,             
"ROLE_ID" NUMBER(5,0) CONSTRAINT "USER_ROLESN2" NOT NULL ENABLE,                
"UNIT_NUMBER" VARCHAR2(8) CONSTRAINT "USER_ROLESN3" NOT NULL ENABLE,            
"DESCEND_FLAG" CHAR(1) CONSTRAINT "USER_ROLESN4" NOT NULL ENABLE,               
"UPDATE_TIMESTAMP" DATE CONSTRAINT "USER_ROLESN5" NOT NULL ENABLE,              
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "USER_ROLESN6" NOT NULL ENABLE,            
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "USER_ROLESN7" NOT NULL ENABLE,      
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "USER_ROLESN8" NOT NULL     
ENABLE,                                                                         
CONSTRAINT "PK_USER_ROLES_KRA" PRIMARY KEY ("ROLE_ID", "USER_ID", "UNIT_NUMBER")
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "PROPOSAL_RESPONSE"                                                
(	"PROPOSAL_RESPONSE_CODE" VARCHAR2(3) CONSTRAINT "PROPOSAL_RESPONSE_N1" NOT    
NULL ENABLE,                                                                    
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "PROPOSAL_RESPONSE_N2" NOT NULL ENABLE,  
"UPDATE_TIMESTAMP" DATE CONSTRAINT "PROPOSAL_RESPONSE_N3" NOT NULL ENABLE,      
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "PROPOSAL_RESPONSE_N4" NOT NULL ENABLE,    
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "PROPOSAL_RESPONSE_N5" NOT NULL      
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "PROPOSAL_RESPONSE_N6" NOT  
NULL ENABLE,                                                                    
CONSTRAINT "PROPOSAL_RESPONSE_P1" PRIMARY KEY ("PROPOSAL_RESPONSE_CODE") ENABLE,
CONSTRAINT "PROPOSAL_RESPONSE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
              
                                                                                
CREATE TABLE "INV_CREDIT_TYPE"                                                  
(	"INV_CREDIT_TYPE_CODE" VARCHAR2(3) CONSTRAINT "INV_CREDIT_TYPE_N1" NOT NULL   
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(300) NOT NULL ENABLE,                                    
"ADDS_TO_HUNDRED" CHAR(1) NOT NULL ENABLE,                                      
"UPDATE_TIMESTAMP" DATE CONSTRAINT "INV_CREDIT_TYPE_N2" NOT NULL ENABLE,        
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "INV_CREDIT_TYPE_N3" NOT NULL ENABLE,      
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "INV_CREDIT_TYPE_N4" NOT NULL ENABLE,
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"ACTIVE_FLAG" CHAR(1),                                                          
CONSTRAINT "INV_CREDIT_TYPE_P1" PRIMARY KEY ("INV_CREDIT_TYPE_CODE") ENABLE,    
CONSTRAINT "INV_CREDIT_TYPE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                
                                                                                
CREATE TABLE "NOTICE_OF_OPPORTUNITY"                                            
(	"NOTICE_OF_OPPORTUNITY_CODE" VARCHAR2(3) CONSTRAINT "NOTICE_OF_OPPORTUNITY_N1"
NOT NULL ENABLE,                                                                
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "NOTICE_OF_OPPORTUNITY_N2" NOT NULL      
ENABLE,                                                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "NOTICE_OF_OPPORTUNITY_N3" NOT NULL ENABLE,  
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "NOTICE_OF_OPPORTUNITY_N4" NOT NULL ENABLE,
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "NOTICE_OF_OPPORTUNITY_N5" NOT NULL  
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "NOTICE_OF_OPPORTUNITY_N6"  
NOT NULL ENABLE,                                                                
CONSTRAINT "NOTICE_OF_OPPORTUNITY_TP1" PRIMARY KEY                              
("NOTICE_OF_OPPORTUNITY_CODE") ENABLE,                                          
	 CONSTRAINT "NOTICE_OF_OPPORTUNITY_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
       
                                                                                
CREATE TABLE "EPS_PROP_PER_CREDIT_SPLIT"                                        
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_PER_CREDIT_SPLIT_N1" NOT  
NULL ENABLE,                                                                    
"INV_CREDIT_TYPE_CODE" VARCHAR2(3) CONSTRAINT "EPS_PROP_PER_CREDIT_SPLIT_N3" NOT
NULL ENABLE,                                                                    
"PROP_PERSON_NUMBER" NUMBER(12,0) CONSTRAINT "EPS_PROP_PER_CREDIT_SPLIT_N4" NOT 
NULL ENABLE,                                                                    
"CREDIT" NUMBER(5,2),                                                           
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_PER_CREDIT_SPLIT_N6" NOT NULL      
ENABLE,                                                                         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_PER_CREDIT_SPLIT_N7" NOT NULL    
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PER_UNIT_CREDIT_SPLIT_N8" NOT   
NULL ENABLE,                                                                    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
CONSTRAINT "EPS_PROP_PER_CREDIT_SPLIT_P1" PRIMARY KEY ("PROPOSAL_NUMBER",       
"PROP_PERSON_NUMBER", "INV_CREDIT_TYPE_CODE") ENABLE,                           
CONSTRAINT "EPS_PROP_PER_CREDIT_SPLIT_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
      
                                                                                
CREATE TABLE "EPS_PROP_UNIT_CREDIT_SPLIT"                                       
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_UNIT_CREDIT_SPLIT_N1" NOT 
NULL ENABLE,                                                                    
"INV_CREDIT_TYPE_CODE" VARCHAR2(3) CONSTRAINT "EPS_PROP_UNIT_CREDIT_SPLIT_N3"   
NOT NULL ENABLE,                                                                
"PROP_PERSON_NUMBER" NUMBER(12,0) CONSTRAINT "EPS_PROP_UNIT_CREDIT_SPLIT_N4" NOT
NULL ENABLE,                                                                    
"UNIT_NUMBER" VARCHAR2(8) CONSTRAINT "EPS_PROP_UNIT_CREDIT_SPLIT_N5" NOT NULL   
ENABLE,                                                                         
"CREDIT" NUMBER(5,2),                                                           
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_UNIT_CREDIT_SPLIT_N6" NOT NULL     
ENABLE,                                                                         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_UNIT_CREDIT_SPLIT_N7" NOT NULL   
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_UNIT_CREDIT_SPLIT_N8" NOT  
NULL ENABLE,                                                                    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
CONSTRAINT "EPS_PROP_UNIT_CREDIT_SPLIT_P1" PRIMARY KEY ("PROPOSAL_NUMBER",      
"PROP_PERSON_NUMBER", "INV_CREDIT_TYPE_CODE", "UNIT_NUMBER") ENABLE,            
CONSTRAINT "EPS_PROP_UNIT_CREDIT_SPLIT_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
     
                                                                                
CREATE TABLE "YNQ_EXPLANATION_TYPE"                                             
(	"EXPLANATION_TYPE" CHAR(1) CONSTRAINT "YNQ_EXPLANATION_TYPEN2" NOT NULL       
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200),                                                    
"UPDATE_TIMESTAMP" DATE CONSTRAINT "YNQ_EXPLANATION_TYPEN3" NOT NULL ENABLE,    
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "YNQ_EXPLANATION_TYPEN4" NOT NULL ENABLE,  
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "YNQ_EXPLANATION_TYPEN5" NOT NULL    
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "YNQ_EXPLANATION_TYPEN6" NOT
NULL ENABLE,                                                                    
CONSTRAINT "PK_YNQ_EXPLANATION_TYPE_KRA" PRIMARY KEY ("EXPLANATION_TYPE") ENABLE
) ;
                                                                            
                                                                                
CREATE TABLE "EPS_PROP_PERS_YNQ"                                                
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_PERS_YNQN1" NOT NULL      
ENABLE,                                                                         
"PROP_PERSON_NUMBER" NUMBER(12,0) CONSTRAINT "EPS_PROP_PERS_YNQN2" NOT NULL     
ENABLE,                                                                         
"QUESTION_ID" VARCHAR2(4) CONSTRAINT "EPS_PROP_PERS_YNQN3" NOT NULL ENABLE,     
"ANSWER" CHAR(1),                                                               
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_PERS_YNQN5" NOT NULL ENABLE,       
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_PERS_YNQN6" NOT NULL ENABLE,     
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_PERS_YNQN7" NOT NULL       
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROP_PERS_YNQN8" NOT   
NULL ENABLE,                                                                    
CONSTRAINT "PK_EPS_PROP_PERS_YNQ_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",           
"PROP_PERSON_NUMBER", "QUESTION_ID") ENABLE                                     
) ;
                                                                            
                                                                                
CREATE TABLE "EPS_PROP_YNQ"                                                     
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_YNQN1" NOT NULL ENABLE,   
"QUESTION_ID" VARCHAR2(4) CONSTRAINT "EPS_PROP_YNQN2" NOT NULL ENABLE,          
"ANSWER" CHAR(1),                                                               
"EXPLANATION" LONG,                                                             
"REVIEW_DATE" DATE,                                                             
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_YNQN4" NOT NULL ENABLE,            
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_YNQN5" NOT NULL ENABLE,          
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_YNQN6" NOT NULL ENABLE,    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROP_YNQN7" NOT NULL   
ENABLE,                                                                         
CONSTRAINT "PK_EPS_PROP_YNQ_KRA" PRIMARY KEY ("PROPOSAL_NUMBER", "QUESTION_ID") 
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EPS_PROP_PERSON_BIO"                                              
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_PERSON_BION1" NOT NULL    
ENABLE,                                                                         
"PROP_PERSON_NUMBER" NUMBER(12,0) CONSTRAINT "EPS_PROP_PERSON_BION2" NOT NULL   
ENABLE,                                                                         
"BIO_NUMBER" NUMBER(3,0) CONSTRAINT "EPS_PROP_PERSON_BION3" NOT NULL ENABLE,    
"PERSON_ID" VARCHAR2(10),                                                       
"ROLODEX_ID" NUMBER(6,0),                                                       
"DESCRIPTION" VARCHAR2(200),                                                    
"DOCUMENT_TYPE_CODE" VARCHAR2(3),                                               
"FILE_NAME" VARCHAR2(150),                                                      
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_PERSON_BION5" NOT NULL ENABLE,     
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_PERSON_BION6" NOT NULL ENABLE,   
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_PERSON_BION7" NOT NULL     
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROP_PERSON_BION8" NOT 
NULL ENABLE,                                                                    
CONSTRAINT "PK_EPS_PROP_PERSON_BIO_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",         
"PROP_PERSON_NUMBER", "BIO_NUMBER") ENABLE                                      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_OUT_BOX_ITM_T"                                                 
(	"ACTN_ITM_ID" NUMBER(14,0) NOT NULL ENABLE,                                   
"ACTN_ITM_PRSN_EN_ID" VARCHAR2(30) NOT NULL ENABLE,                             
"ACTN_ITM_ASND_DT" DATE NOT NULL ENABLE,                                        
"ACTN_ITM_RQST_CD" CHAR(1) NOT NULL ENABLE,                                     
"ACTN_RQST_ID" NUMBER(14,0) NOT NULL ENABLE,                                    
"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                      
"WRKGRP_ID" NUMBER(14,0),                                                       
"ROLE_NM" VARCHAR2(2000),                                                       
"ACTN_ITM_DLGN_PRSN_EN_ID" VARCHAR2(30),                                        
"ACTN_ITM_DLGN_WRKGRP_ID" NUMBER(14,0),                                         
"DOC_TTL" VARCHAR2(255),                                                        
"DOC_TYP_LBL_TXT" VARCHAR2(255) NOT NULL ENABLE,                                
"DOC_TYP_HDLR_URL_ADDR" VARCHAR2(255) NOT NULL ENABLE,                          
"DOC_TYP_NM" VARCHAR2(255) NOT NULL ENABLE,                                     
"ACTN_ITM_RESP_ID" NUMBER(14,0) NOT NULL ENABLE,                                
"DLGN_TYP" VARCHAR2(1),                                                         
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_OUT_BOX_ITM_T_PK" PRIMARY KEY ("ACTN_ITM_ID") ENABLE
   ) ;
     
                                                                                
CREATE TABLE "EPS_PROP_PERSON_DEGREE"                                           
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_PERSON_DEGREE_N1" NOT NULL
ENABLE,                                                                         
"PROP_PERSON_NUMBER" NUMBER(12,0) CONSTRAINT "EPS_PROP_PERSON_DEGREE_N2" NOT    
NULL ENABLE,                                                                    
"DEGREE_SEQUENCE_NUMBER" NUMBER(3,0) CONSTRAINT "EPS_PROP_PERSON_DEGREE_N3" NOT 
NULL ENABLE,                                                                    
"GRADUATION_YEAR" VARCHAR2(4),                                                  
"DEGREE_CODE" VARCHAR2(6),                                                      
"DEGREE" VARCHAR2(80),                                                          
"FIELD_OF_STUDY" VARCHAR2(80),                                                  
"SPECIALIZATION" VARCHAR2(80),                                                  
"SCHOOL" VARCHAR2(50),                                                          
"SCHOOL_ID_CODE" VARCHAR2(3),                                                   
"SCHOOL_ID" VARCHAR2(20),                                                       
"UPDATE_TIMESTAMP" DATE NOT NULL ENABLE,                                        
"UPDATE_USER" VARCHAR2(8) NOT NULL ENABLE,                                      
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
CONSTRAINT "EPS_PROP_PERSON_DEGREE_N6" PRIMARY KEY ("PROPOSAL_NUMBER",          
"PROP_PERSON_NUMBER", "DEGREE_SEQUENCE_NUMBER") ENABLE,                         
CONSTRAINT "EPS_PROP_PERSON_DEGREE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
         
                                                                                
CREATE TABLE "UNIT_ADMINISTRATOR"                                               
(	"UNIT_NUMBER" VARCHAR2(8) CONSTRAINT "UNIT_ADMINISTRATORN1" NOT NULL ENABLE,  
"PERSON_ID" VARCHAR2(10) CONSTRAINT "UNIT_ADMINISTRATORSN2" NOT NULL ENABLE,    
"ROLE_ID" NUMBER(5,0) CONSTRAINT "UNIT_ADMINISTRATORN3" NOT NULL ENABLE,        
"UPDATE_TIMESTAMP" DATE CONSTRAINT "UNIT_ADMINISTRATORN4" NOT NULL ENABLE,      
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "UNIT_ADMINISTRATORN5" NOT NULL ENABLE,    
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "UNIT_ADMINISTRATORN6" NOT NULL      
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "UNIT_ADMINISTRATORN7" NOT  
NULL ENABLE,                                                                    
CONSTRAINT "PK_UNIT_ADMINISTRATOR_KRA" PRIMARY KEY ("UNIT_NUMBER", "PERSON_ID", 
"ROLE_ID") ENABLE
   ) ;
                                                       
                                                                                
CREATE TABLE "KIM_ROLES_PERSONS_T"                                              
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"ROLE_ID" NUMBER(8,0) NOT NULL ENABLE,                                          
"PERSON_ID" NUMBER(8,0) NOT NULL ENABLE,                                        
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_ROLES_PERSONS_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
             
                                                                                
CREATE TABLE "KIM_ROLES_PERSONS_QUAL_T"                                         
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"ROLE_ID" NUMBER(8,0) NOT NULL ENABLE,                                          
"PERSON_ID" NUMBER(8,0) NOT NULL ENABLE,                                        
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_ROLES_PERSONS_QUAL_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
        
                                                                                
CREATE TABLE "KIM_PERSON_QUAL_ATTR_T"                                           
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"ROLE_PERSON_ID" NUMBER(8,0) NOT NULL ENABLE,                                   
"ATTRIBUTE_NAME" VARCHAR2(500) NOT NULL ENABLE,                                 
"ATTRIBUTE_VALUE" VARCHAR2(4000),                                               
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_PERSON_QUAL_ATTR_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
          
                                                                                
CREATE TABLE "KIM_ROLES_GROUPS_T"                                               
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"ROLE_ID" NUMBER(8,0) NOT NULL ENABLE,                                          
"GROUP_ID" NUMBER(8,0) NOT NULL ENABLE,                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_ROLES_GROUPS_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
              
                                                                                
CREATE TABLE "KIM_ROLES_GROUPS_QUAL_T"                                          
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"ROLE_ID" NUMBER(8,0) NOT NULL ENABLE,                                          
"GROUP_ID" NUMBER(8,0) NOT NULL ENABLE,                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_ROLES_GROUPS_QUAL_PK" PRIMARY KEY ("ID") ENABLE
   ) ;
         
                                                                                
CREATE TABLE "KIM_GROUP_QUAL_ATTR_T"                                            
(	"ID" NUMBER(8,0) NOT NULL ENABLE,                                             
"ROLE_GROUP_ID" NUMBER(8,0) NOT NULL ENABLE,                                    
"ATTRIBUTE_NAME" VARCHAR2(500) NOT NULL ENABLE,                                 
"ATTRIBUTE_VALUE" VARCHAR2(4000),                                               
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
CONSTRAINT "KIM_GROUP_ROLE_QUAL_PK" PRIMARY KEY ("ID") ENABLE                   
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "BUDGET_STATUS"                                                    
(	"BUDGET_STATUS_CODE" VARCHAR2(3) CONSTRAINT "BUDGET_STATUSN1" NOT NULL ENABLE,
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "BUDGET_STATUSN2" NOT NULL ENABLE,       
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_STATUSN3" NOT NULL ENABLE,           
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_STATUSN4" NOT NULL ENABLE,         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_STATUSN5" NOT NULL ENABLE,   
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "BUDGET_STATUSN6" NOT NULL  
ENABLE,                                                                         
CONSTRAINT "PK_BUDGET_STATUS_KRA" PRIMARY KEY ("BUDGET_STATUS_CODE") ENABLE     
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "RATE_CLASS"                                                       
(	"RATE_CLASS_CODE" VARCHAR2(3) CONSTRAINT "RATE_CLASSN1" NOT NULL ENABLE,      
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "RATE_CLASSN2" NOT NULL ENABLE,          
"RATE_CLASS_TYPE" CHAR(1) CONSTRAINT "RATE_CLASSN3" NOT NULL ENABLE,            
"UPDATE_TIMESTAMP" DATE CONSTRAINT "RATE_CLASSN4" NOT NULL ENABLE,              
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "RATE_CLASSN5" NOT NULL ENABLE,            
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "RATE_CLASSN6" NOT NULL ENABLE,      
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "RATE_CLASSN7" NOT NULL     
ENABLE,                                                                         
CONSTRAINT "PK_RATE_CLASS_KRA" PRIMARY KEY ("RATE_CLASS_CODE") ENABLE
   ) ;
   
                                                                                
CREATE TABLE "RATE_TYPE"                                                        
(	"RATE_CLASS_CODE" VARCHAR2(3) CONSTRAINT "RATE_TYPEN1" NOT NULL ENABLE,       
"RATE_TYPE_CODE" VARCHAR2(3) CONSTRAINT "RATE_TYPEN2" NOT NULL ENABLE,          
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "RATE_TYPEN3" NOT NULL ENABLE,           
"UPDATE_TIMESTAMP" DATE CONSTRAINT "RATE_TYPEN4" NOT NULL ENABLE,               
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "RATE_TYPEN5" NOT NULL ENABLE,             
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "RATE_TYPEN6" NOT NULL ENABLE,       
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "RATE_TYPEN7" NOT NULL      
ENABLE,                                                                         
CONSTRAINT "PK_RATE_TYPE_KRA" PRIMARY KEY ("RATE_CLASS_CODE", "RATE_TYPE_CODE") 
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "JOB_CODE"                                                         
(	"JOB_CODE" VARCHAR2(6) CONSTRAINT "JOB_CODEN1" NOT NULL ENABLE,               
"JOB_TITLE" VARCHAR2(50) CONSTRAINT "JOB_CODEN2" NOT NULL ENABLE,               
"UPDATE_TIMESTAMP" DATE CONSTRAINT "JOB_CODEN3" NOT NULL ENABLE,                
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "JOB_CODEN4" NOT NULL ENABLE,              
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "JOB_CODEN5" NOT NULL ENABLE,        
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "JOB_CODEN6" NOT NULL       
ENABLE,                                                                         
CONSTRAINT "PK_JOB_CODE_KRA" PRIMARY KEY ("JOB_CODE") ENABLE
   ) ;
            
                                                                                
CREATE TABLE "BUDGET_CATEGORY_MAPS"                                             
(	"MAPPING_NAME" VARCHAR2(100) CONSTRAINT "BUDGET_CATEGORY_MAPSN1" NOT NULL     
ENABLE,                                                                         
"TARGET_CATEGORY_CODE" VARCHAR2(15) CONSTRAINT "BUDGET_CATEGORY_MAPSN2" NOT NULL
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "BUDGET_CATEGORY_MAPSN3" NOT NULL ENABLE,
"CATEGORY_TYPE" CHAR(200),                                                      
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_CATEGORY_MAPSN4" NOT NULL ENABLE,    
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_CATEGORY_MAPSN5" NOT NULL ENABLE,  
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_CATEGORY_MAPSN6" NOT NULL    
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "BUDGET_CATEGORY_MAPSN7" NOT
NULL ENABLE,                                                                    
CONSTRAINT "PK_BUDGET_CATEGORY_MAPS_KRA" PRIMARY KEY ("MAPPING_NAME",           
"TARGET_CATEGORY_CODE") ENABLE
   ) ;
                                          
                                                                                
CREATE TABLE "BUDGET_CATEGORY_MAPPING"                                          
(	"MAPPING_NAME" VARCHAR2(100) CONSTRAINT "BUDGET_CATEGORY_MAPPINGN1" NOT NULL  
ENABLE,                                                                         
"TARGET_CATEGORY_CODE" VARCHAR2(15) CONSTRAINT "BUDGET_CATEGORY_MAPPINGN2" NOT  
NULL ENABLE,                                                                    
"COEUS_CATEGORY_CODE" NUMBER(3,0) CONSTRAINT "BUDGET_CATEGORY_MAPPINGN3" NOT    
NULL ENABLE,                                                                    
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_CATEGORY_MAPPINGN4" NOT NULL ENABLE, 
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_CATEGORY_MAPPINGN5" NOT NULL       
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_CATEGORY_MAPPINGN6" NOT NULL 
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "BUDGET_CATEGORY_MAPPINGN7" 
NOT NULL ENABLE,                                                                
CONSTRAINT "PK_BUDGET_CATEGORY_MAPPING_KRA" PRIMARY KEY ("MAPPING_NAME",        
"TARGET_CATEGORY_CODE", "COEUS_CATEGORY_CODE") ENABLE
   ) ;
                   
                                                                                
CREATE TABLE "EPS_PROP_LA_RATES"                                                
(	"PROPOSAL_NUMBER" VARCHAR2(8) CONSTRAINT "EPS_PROP_LA_RATESN1" NOT NULL       
ENABLE,                                                                         
"VERSION_NUMBER" NUMBER(3,0) CONSTRAINT "EPS_PROP_LA_RATESN2" NOT NULL ENABLE,  
"RATE_TYPE_CODE" VARCHAR2(3) CONSTRAINT "EPS_PROP_LA_RATESN3" NOT NULL ENABLE,  
"FISCAL_YEAR" CHAR(4) CONSTRAINT "EPS_PROP_LA_RATESN4" NOT NULL ENABLE,         
"ON_OFF_CAMPUS_FLAG" CHAR(1) CONSTRAINT "EPS_PROP_LA_RATESN5" NOT NULL ENABLE,  
"START_DATE" DATE CONSTRAINT "EPS_PROP_LA_RATESN6" NOT NULL ENABLE,             
"APPLICABLE_RATE" NUMBER(5,2) CONSTRAINT "EPS_PROP_LA_RATESN7" NOT NULL ENABLE, 
"INSTITUTE_RATE" NUMBER(5,2),                                                   
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_LA_RATESN8" NOT NULL ENABLE,       
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_LA_RATESN9" NOT NULL ENABLE,     
"RATE_CLASS_CODE" VARCHAR2(3) CONSTRAINT "EPS_PROP_LA_RATESN10" NOT NULL ENABLE,
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_LA_RATESN11" NOT NULL      
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROP_LA_RATESN12" NOT  
NULL ENABLE,                                                                    
CONSTRAINT "PK_EPS_PROP_LA_RATES_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",           
"VERSION_NUMBER", "RATE_CLASS_CODE", "RATE_TYPE_CODE", "FISCAL_YEAR",           
"START_DATE", "ON_OFF_CAMPUS_FLAG") ENABLE
   ) ;
                              
                                                                                
CREATE TABLE "BUDGET_DETAILS_CAL_AMTS"                                          
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "BUDGET_DETAILS_CAL_AMTSN1" NOT NULL
ENABLE,                                                                         
"VERSION_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_DETAILS_CAL_AMTSN2" NOT NULL    
ENABLE,                                                                         
"BUDGET_PERIOD" NUMBER(3,0) CONSTRAINT "BUDGET_DETAILS_CAL_AMTSN3" NOT NULL     
ENABLE,                                                                         
"LINE_ITEM_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_DETAILS_CAL_AMTSN4" NOT NULL  
ENABLE,                                                                         
"RATE_CLASS_CODE" VARCHAR2(3) CONSTRAINT "BUDGET_DETAILS_CAL_AMTSN5" NOT NULL   
ENABLE,                                                                         
"RATE_TYPE_CODE" VARCHAR2(3) CONSTRAINT "BUDGET_DETAILS_CAL_AMTSN6" NOT NULL    
ENABLE,                                                                         
"APPLY_RATE_FLAG" CHAR(1) CONSTRAINT "BUDGET_DETAILS_CAL_AMTSN7" NOT NULL       
ENABLE,                                                                         
"CALCULATED_COST" NUMBER(12,2),                                                 
"CALCULATED_COST_SHARING" NUMBER(12,2),                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_DETAILS_CAL_AMTSN8" NOT NULL ENABLE, 
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_DETAILS_CAL_AMTSN9" NOT NULL       
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_DETAILS_CAL_AMTSN10" NOT NULL
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT                             
"BUDGET_DETAILS_CAL_AMTSN11" NOT NULL ENABLE,                                   
CONSTRAINT "PK_BUDGET_DETAILS_CAL_AMTS_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",     
"VERSION_NUMBER", "BUDGET_PERIOD", "LINE_ITEM_NUMBER", "RATE_CLASS_CODE",       
"RATE_TYPE_CODE") ENABLE                                                        
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "ROLE"                                                             
(	"ROLE_ID" NUMBER(5,0) CONSTRAINT "ROLEN1" NOT NULL ENABLE,                    
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "ROLEN2" NOT NULL ENABLE,                
"ROLE_NAME" VARCHAR2(50) CONSTRAINT "ROLEN3" NOT NULL ENABLE,                   
"ROLE_TYPE" CHAR(1) CONSTRAINT "ROLEN4" NOT NULL ENABLE,                        
"OWNED_BY_UNIT" VARCHAR2(8) CONSTRAINT "ROLEN5" NOT NULL ENABLE,                
"DESCEND_FLAG" CHAR(1) CONSTRAINT "ROLEN6" NOT NULL ENABLE,                     
"STATUS_FLAG" CHAR(1) CONSTRAINT "ROLEN7" NOT NULL ENABLE,                      
"CREATE_TIMESTAMP" DATE CONSTRAINT "ROLEN8" NOT NULL ENABLE,                    
"CREATE_USER" VARCHAR2(8) CONSTRAINT "ROLEN9" NOT NULL ENABLE,                  
"UPDATE_TIMESTAMP" DATE CONSTRAINT "ROLEN10" NOT NULL ENABLE,                   
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "ROLEN11" NOT NULL ENABLE,                 
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "ROLEN12" NOT NULL ENABLE,           
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "ROLEN13" NOT NULL ENABLE,  
CONSTRAINT "PK_ROLE_KRA" PRIMARY KEY ("ROLE_ID") ENABLE
   ) ;
                 
                                                                                
CREATE TABLE "S2S_REVISION_TYPE"                                                
(	"S2S_REVISION_TYPE_CODE" VARCHAR2(3) CONSTRAINT "S2S_REVISION_TYPEN1" NOT NULL
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "S2S_REVISION_TYPEN2" NOT NULL ENABLE,   
"UPDATE_TIMESTAMP" DATE CONSTRAINT "S2S_REVISION_TYPEN3" NOT NULL ENABLE,       
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "S2S_REVISION_TYPEN4" NOT NULL ENABLE,     
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "S2S_REVISION_TYPEN5" NOT NULL       
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "S2S_REVISION_TYPEN6" NOT   
NULL ENABLE,                                                                    
CONSTRAINT "PK_S2S_REVISION_TYPE_KRA" PRIMARY KEY ("S2S_REVISION_TYPE_CODE")    
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "S2S_SUBMISSION_TYPE"                                              
(	"S2S_SUBMISSION_TYPE_CODE" VARCHAR2(3) CONSTRAINT "S2S_SUBMISSION_TYPEN1" NOT 
NULL ENABLE,                                                                    
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "S2S_SUBMISSION_TYPEN2" NOT NULL ENABLE, 
"UPDATE_TIMESTAMP" DATE CONSTRAINT "S2S_SUBMISSION_TYPEN3" NOT NULL ENABLE,     
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "S2S_SUBMISSION_TYPEN4" NOT NULL ENABLE,   
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "S2S_SUBMISSION_TYPEN5" NOT NULL     
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "S2S_SUBMISSION_TYPEN6" NOT 
NULL ENABLE,                                                                    
CONSTRAINT "PK_S2S_SUBMISSION_TYPE_KRA" PRIMARY KEY ("S2S_SUBMISSION_TYPE_CODE")
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "SPECIAL_REVIEW"                                                   
(	"SPECIAL_REVIEW_CODE" VARCHAR2(3) CONSTRAINT "SPECIAL_REVIEW_N1" NOT NULL     
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "SPECIAL_REVIEW_N2" NOT NULL ENABLE,     
"UPDATE_TIMESTAMP" DATE CONSTRAINT "SPECIAL_REVIEW_N3" NOT NULL ENABLE,         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "SPECIAL_REVIEW_N4" NOT NULL ENABLE,       
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SPECIAL_REVIEW_N5" NOT NULL ENABLE, 
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SPECIAL_REVIEW_N6" NOT NULL
ENABLE,                                                                         
CONSTRAINT "SPECIAL_REVIEW_P1" PRIMARY KEY ("SPECIAL_REVIEW_CODE") ENABLE,      
CONSTRAINT "SPECIAL_REVIEW_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                 
                                                                                
CREATE TABLE "SPONSOR"                                                          
(	"SPONSOR_CODE" CHAR(6) CONSTRAINT "SPONSOR_N1" NOT NULL ENABLE,               
"SPONSOR_NAME" VARCHAR2(60) CONSTRAINT "SPONSOR_N2" NOT NULL ENABLE,            
"ACRONYM" VARCHAR2(10),                                                         
"SPONSOR_TYPE_CODE" VARCHAR2(3) CONSTRAINT "SPONSOR_N3" NOT NULL ENABLE,        
"DUN_AND_BRADSTREET_NUMBER" VARCHAR2(20),                                       
"DUNS_PLUS_FOUR_NUMBER" VARCHAR2(20),                                           
"DODAC_NUMBER" VARCHAR2(20),                                                    
"CAGE_NUMBER" VARCHAR2(20),                                                     
"POSTAL_CODE" VARCHAR2(15),                                                     
"STATE" VARCHAR2(30),                                                           
"COUNTRY_CODE" CHAR(3),                                                         
"ROLODEX_ID" NUMBER(6,0) CONSTRAINT "SPONSOR_N4" NOT NULL ENABLE,               
"AUDIT_REPORT_SENT_FOR_FY" CHAR(4),                                             
"OWNED_BY_UNIT" VARCHAR2(8) CONSTRAINT "SPONSOR_N5" NOT NULL ENABLE,            
"CREATE_USER" VARCHAR2(8) CONSTRAINT "SPONSOR_N6" NOT NULL ENABLE,              
"UPDATE_TIMESTAMP" DATE CONSTRAINT "SPONSOR_N7" NOT NULL ENABLE,                
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "SPONSOR_N8" NOT NULL ENABLE,              
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SPONSOR_N9" NOT NULL ENABLE,        
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SPONSORN_10" NOT NULL      
ENABLE,                                                                         
CONSTRAINT "PK_SPONSOR_KRA" PRIMARY KEY ("SPONSOR_CODE") ENABLE                 
   ) ;
                                                                         
                                                                                
CREATE TABLE "SP_REV_APPROVAL_TYPE"                                             
(	"APPROVAL_TYPE_CODE" VARCHAR2(3) CONSTRAINT "SP_REV_APPROVAL_TYPE_N1" NOT NULL
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "SP_REV_APPROVAL_TYPE_N2" NOT NULL       
ENABLE,                                                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "SP_REV_APPROVAL_TYPE_N3" NOT NULL ENABLE,   
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "SP_REV_APPROVAL_TYPE_N4" NOT NULL ENABLE, 
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SP_REV_APPROVAL_TYPE_N5" NOT NULL   
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SP_REV_APPROVAL_TYPE_N6"   
NOT NULL ENABLE,                                                                
CONSTRAINT "SP_REV_APPROVAL_TYPE_P1" PRIMARY KEY ("APPROVAL_TYPE_CODE") ENABLE, 
CONSTRAINT "SP_REV_APPROVAL_TYPE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
           
                                                                                
CREATE TABLE "UNIT"                                                             
(	"UNIT_NUMBER" VARCHAR2(8) CONSTRAINT "UNITN1" NOT NULL ENABLE,                
"UNIT_NAME" VARCHAR2(60),                                                       
"ORGANIZATION_ID" VARCHAR2(8),                                                  
"UPDATE_TIMESTAMP" DATE CONSTRAINT "UNITN2" NOT NULL ENABLE,                    
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "UNITN3" NOT NULL ENABLE,                  
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "UNITN4" NOT NULL ENABLE,            
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "UNITN5" NOT NULL ENABLE,   
"PARENT_UNIT_NUMBER" VARCHAR2(8),                                               
CONSTRAINT "PK_UNIT_KRA" PRIMARY KEY ("UNIT_NUMBER") ENABLE                     
) ;
                                                                            
                                                                                
CREATE TABLE "EPS_PROPOSAL"                                                     
(	"DOCUMENT_NUMBER" NUMBER(10,0) CONSTRAINT "EPS_PROPOSAL_N1" NOT NULL ENABLE,  
"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROPOSAL_N2" NOT NULL ENABLE,    
"PROPOSAL_TYPE_CODE" VARCHAR2(3),                                               
"STATUS_CODE" NUMBER(3,0),                                                      
"CREATION_STATUS_CODE" NUMBER(3,0),                                             
"BASE_PROPOSAL_NUMBER" VARCHAR2(8),                                             
"CONTINUED_FROM" VARCHAR2(8),                                                   
"TEMPLATE_FLAG" CHAR(1),                                                        
"ORGANIZATION_ID" VARCHAR2(8),                                                  
"PERFORMING_ORGANIZATION_ID" VARCHAR2(8),                                       
"CURRENT_ACCOUNT_NUMBER" CHAR(7),                                               
"CURRENT_AWARD_NUMBER" CHAR(10),                                                
"TITLE" VARCHAR2(150),                                                          
"SPONSOR_CODE" CHAR(6),                                                         
"SPONSOR_PROPOSAL_NUMBER" VARCHAR2(70),                                         
"INTR_COOP_ACTIVITIES_FLAG" CHAR(1),                                            
"INTR_COUNTRY_LIST" VARCHAR2(150),                                              
"OTHER_AGENCY_FLAG" CHAR(1),                                                    
"NOTICE_OF_OPPORTUNITY_CODE" NUMBER(3,0),                                       
"PROGRAM_ANNOUNCEMENT_NUMBER" VARCHAR2(50),                                     
"PROGRAM_ANNOUNCEMENT_TITLE" VARCHAR2(255),                                     
"ACTIVITY_TYPE_CODE" VARCHAR2(3),                                               
"REQUESTED_START_DATE_INITIAL" DATE,                                            
"REQUESTED_START_DATE_TOTAL" DATE,                                              
"REQUESTED_END_DATE_INITIAL" DATE,                                              
"REQUESTED_END_DATE_TOTAL" DATE,                                                
"DURATION_MONTHS" NUMBER(3,0),                                                  
"NUMBER_OF_COPIES" VARCHAR2(7),                                                 
"DEADLINE_DATE" DATE,                                                           
"DEADLINE_TYPE" CHAR(1),                                                        
"MAILING_ADDRESS_ID" NUMBER(6,0),                                               
"MAIL_BY" CHAR(1),                                                              
"MAIL_TYPE" VARCHAR2(3),                                                        
"CARRIER_CODE_TYPE" VARCHAR2(3),                                                
"CARRIER_CODE" VARCHAR2(20),                                                    
"MAIL_DESCRIPTION" VARCHAR2(80),                                                
"MAIL_ACCOUNT_NUMBER" VARCHAR2(9),                                              
"SUBCONTRACT_FLAG" CHAR(1),                                                     
"NARRATIVE_STATUS" CHAR(1),                                                     
"BUDGET_STATUS" CHAR(1),                                                        
"OWNED_BY_UNIT" VARCHAR2(8),                                                    
"CREATE_TIMESTAMP" DATE,                                                        
"CREATE_USER" VARCHAR2(8),                                                      
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROPOSAL_N5" NOT NULL ENABLE,           
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROPOSAL_N6" NOT NULL ENABLE,         
"NSF_CODE" VARCHAR2(15),                                                        
"PRIME_SPONSOR_CODE" CHAR(6),                                                   
"CFDA_NUMBER" VARCHAR2(7),                                                      
"AGENCY_PROGRAM_CODE" VARCHAR2(50),                                             
"AGENCY_DIVISION_CODE" VARCHAR2(50),                                            
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROPOSAL_N7" NOT NULL ENABLE,   
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROPOSAL_N8" NOT NULL  
ENABLE,                                                                         
CONSTRAINT "EPS_PROPOSAL_P1" PRIMARY KEY ("PROPOSAL_NUMBER") ENABLE,            
CONSTRAINT "EPS_PROPOSAL_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                   
                                                                                
CREATE TABLE "EPS_PROP_LOCATION"                                                
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_LOCATIONN1" NOT NULL      
ENABLE,                                                                         
"LOCATION_SEQUENCE_NUMBER" NUMBER(3,0) CONSTRAINT "EPS_PROP_LOCATIONN2" NOT NULL
ENABLE,                                                                         
"LOCATION" VARCHAR2(60) CONSTRAINT "EPS_PROP_LOCATIONN3" NOT NULL ENABLE,       
"ROLODEX_ID" NUMBER(6,0),                                                       
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_LOCATIONN4" NOT NULL ENABLE,       
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_LOCATIONN5" NOT NULL ENABLE,     
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_LOCATIONN6" NOT NULL       
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROP_LOCATIONN7" NOT   
NULL ENABLE,                                                                    
CONSTRAINT "PK_EPS_PROP_LOCATION_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",           
"LOCATION_SEQUENCE_NUMBER") ENABLE                                              
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EPS_PROP_PER_DOC_TYPE"                                            
(	"DOCUMENT_TYPE_CODE" VARCHAR2(3) CONSTRAINT "EPS_PROP_PER_DOC_TYPEN1" NOT NULL
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "EPS_PROP_PER_DOC_TYPEN2" NOT NULL       
ENABLE,                                                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_PER_DOC_TYPEN3" NOT NULL ENABLE,   
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_PER_DOC_TYPEN4" NOT NULL ENABLE, 
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_PER_DOC_TYPEN5" NOT NULL   
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROP_PER_DOC_TYPEN6"   
NOT NULL ENABLE,                                                                
CONSTRAINT "PK_EPS_PROP_PER_DOC_TYPE_KRA" PRIMARY KEY ("DOCUMENT_TYPE_CODE")    
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EPS_PROP_SCIENCE_KEYWORD"                                         
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_SCIENCE_KEYWORDN1" NOT    
NULL ENABLE,                                                                    
"SCIENCE_KEYWORD_CODE" VARCHAR2(3) CONSTRAINT "EPS_PROP_SCIENCE_KEYWORDN2" NOT  
NULL ENABLE,                                                                    
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_SCIENCE_KEYWORDN3" NOT NULL ENABLE,
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_SCIENCE_KEYWORDN4" NOT NULL      
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_SCIENCE_KEYWORDN5" NOT     
NULL ENABLE,                                                                    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROP_SCIENCE_KEYWORDN6"
NOT NULL ENABLE,                                                                
CONSTRAINT "PK_EPS_PROP_SCIE_KEYWORD_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",       
"SCIENCE_KEYWORD_CODE") ENABLE
   ) ;
                                          
                                                                                
CREATE TABLE "SPONSOR_TYPE"                                                     
(	"SPONSOR_TYPE_CODE" VARCHAR2(3) CONSTRAINT "SPONSOR_TYPE_N1" NOT NULL ENABLE, 
"DESCRIPTION" VARCHAR2(100) CONSTRAINT "SPONSOR_TYPE_N2" NOT NULL ENABLE,       
"UPDATE_TIMESTAMP" DATE CONSTRAINT "SPONSOR_TYPE_N3" NOT NULL ENABLE,           
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "SPONSOR_TYPE_N4" NOT NULL ENABLE,         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SPONSOR_TYPE_N5" NOT NULL ENABLE,   
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SPONSOR_TYPE_N6" NOT NULL  
ENABLE,                                                                         
CONSTRAINT "PK_SPONSOR_TYPE_KRA" PRIMARY KEY ("SPONSOR_TYPE_CODE") ENABLE       
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "COUNTRY_CODE"                                                     
(	"COUNTRY_CODE" CHAR(3) CONSTRAINT "COUNTRY_CODE_N1" NOT NULL ENABLE,          
"COUNTRY_NAME" VARCHAR2(40) NOT NULL ENABLE,                                    
"UPDATE_TIMESTAMP" DATE NOT NULL ENABLE,                                        
"UPDATE_USER" VARCHAR2(8) NOT NULL ENABLE,                                      
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
CONSTRAINT "COUNTRY_CODE_N2" PRIMARY KEY ("COUNTRY_CODE") ENABLE,               
CONSTRAINT "COUNTRY_CODE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                   
                                                                                
CREATE TABLE "ROLODEX"                                                          
(	"ROLODEX_ID" NUMBER(6,0) CONSTRAINT "ROLODEXN1" NOT NULL ENABLE,              
"LAST_NAME" VARCHAR2(20),                                                       
"FIRST_NAME" VARCHAR2(20),                                                      
"MIDDLE_NAME" VARCHAR2(20),                                                     
"SUFFIX" VARCHAR2(10),                                                          
"PREFIX" VARCHAR2(10),                                                          
"TITLE" VARCHAR2(35),                                                           
"ORGANIZATION" VARCHAR2(80) CONSTRAINT "ROLODEXN2" NOT NULL ENABLE,             
"ADDRESS_LINE_1" VARCHAR2(80),                                                  
"ADDRESS_LINE_2" VARCHAR2(80),                                                  
"ADDRESS_LINE_3" VARCHAR2(80),                                                  
"FAX_NUMBER" VARCHAR2(20),                                                      
"EMAIL_ADDRESS" VARCHAR2(60),                                                   
"CITY" VARCHAR2(30),                                                            
"COUNTY" VARCHAR2(30),                                                          
"STATE" VARCHAR2(30),                                                           
"POSTAL_CODE" VARCHAR2(15),                                                     
"COMMENTS" VARCHAR2(300),                                                       
"PHONE_NUMBER" VARCHAR2(20),                                                    
"COUNTRY_CODE" CHAR(3),                                                         
"SPONSOR_CODE" CHAR(6),                                                         
"OWNED_BY_UNIT" VARCHAR2(8) CONSTRAINT "ROLODEXN3" NOT NULL ENABLE,             
"SPONSOR_ADDRESS_FLAG" CHAR(1) CONSTRAINT "ROLODEXN4" NOT NULL ENABLE,          
"DELETE_FLAG" CHAR(1),                                                          
"CREATE_USER" VARCHAR2(8) CONSTRAINT "ROLODEXN5" NOT NULL ENABLE,               
"UPDATE_TIMESTAMP" DATE CONSTRAINT "ROLODEXN6" NOT NULL ENABLE,                 
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "ROLODEXN7" NOT NULL ENABLE,               
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "ROLODEXN8" NOT NULL ENABLE,         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "ROLODEXN9" NOT NULL ENABLE,
CONSTRAINT "PK_ROLODEX_KRA" PRIMARY KEY ("ROLODEX_ID") ENABLE
   ) ;
           
                                                                                
CREATE TABLE "FS_UNIVERSAL_USR_T"                                               
(	"PERSON_UNVL_ID" VARCHAR2(10) CONSTRAINT "FS_UNIVERSAL_USR_TN1" NOT NULL      
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "FS_UNIVERSAL_USR_TN2" NOT  
NULL ENABLE,                                                                    
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "FS_UNIVERSAL_USR_TN3" NOT NULL      
ENABLE,                                                                         
"PERSON_USER_ID" VARCHAR2(8) CONSTRAINT "FS_UNIVERSAL_USR_TN4" NOT NULL ENABLE, 
"FS_ENCRPTD_PWD_TXT" VARCHAR2(4000),                                            
"PERSON_NM" VARCHAR2(80),                                                       
"PRSN_1ST_NM" VARCHAR2(20),                                                     
"PRSN_LST_NM" VARCHAR2(20),                                                     
"PRSN_EMAIL_ADDR" VARCHAR2(100),                                                
"CAMPUS_CD" VARCHAR2(2),                                                        
"PRSN_CMP_ADDR" VARCHAR2(55),                                                   
"PRSN_LOC_PHN_NBR" VARCHAR2(10),                                                
"EMP_STAT_CD" VARCHAR2(1),                                                      
"EMP_TYPE_CD" VARCHAR2(1),                                                      
"PRSN_BASE_SLRY_AMT" NUMBER(19,2),                                              
"PRSN_PYRL_ID" VARCHAR2(255),                                                   
"PRSN_TAX_ID" VARCHAR2(255),                                                    
"PRSN_TAX_ID_TYP_CD" CHAR(1),                                                   
"PRSN_STAFF_IND" CHAR(1),                                                       
"PRSN_FAC_IND" CHAR(1),                                                         
"PRSN_STU_IND" CHAR(1),                                                         
"PRSN_AFLT_IND" CHAR(1),                                                        
"EMP_PRM_DEPT_CD" VARCHAR2(10),                                                 
"PRSN_MID_NM" VARCHAR2(20),                                                     
CONSTRAINT "FS_UNIVERSAL_USR_TP1" PRIMARY KEY ("PERSON_UNVL_ID") ENABLE,        
CONSTRAINT "FS_UNIVERSAL_USR_TC0" UNIQUE ("OBJ_ID") ENABLE,                     
CONSTRAINT "FS_UNIVERSAL_USR_TC1" UNIQUE ("PRSN_PYRL_ID") ENABLE
   ) ;
        
                                                                                
CREATE TABLE "UNIT_HIERARCHY"                                                   
(	"UNIT_NUMBER" VARCHAR2(8) CONSTRAINT "UNIT_HIERARCHYN1" NOT NULL ENABLE,      
"PARENT_UNIT_NUMBER" VARCHAR2(8) CONSTRAINT "UNIT_HIERARCHYN2" NOT NULL ENABLE, 
"HAS_CHILDREN_FLAG" CHAR(1),                                                    
"UPDATE_TIMESTAMP" DATE CONSTRAINT "UNIT_HIERARCHYN3" NOT NULL ENABLE,          
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "UNIT_HIERARCHYN4" NOT NULL ENABLE,        
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "UNIT_HIERARCHYN5" NOT NULL ENABLE,  
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "UNIT_HIERARCHYN6" NOT NULL 
ENABLE,                                                                         
CONSTRAINT "PK_UNIT_HIERARCHY_KRA" PRIMARY KEY ("UNIT_NUMBER") ENABLE           
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EPS_PROP_PERSON"                                                  
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROP_PERSON_N1" NOT NULL       
ENABLE,                                                                         
"PROP_PERSON_NUMBER" NUMBER(12,0) CONSTRAINT "EPS_PROP_PERSON_N2" NOT NULL      
ENABLE,                                                                         
"PROP_PERSON_ROLE_ID" VARCHAR2(12) CONSTRAINT "EPS_PROP_PERSON_N3" NOT NULL     
ENABLE,                                                                         
"PERSON_ID" VARCHAR2(10),                                                       
"ROLODEX_ID" NUMBER(6,0),                                                       
"SSN" VARCHAR2(9),                                                              
"LAST_NAME" VARCHAR2(30),                                                       
"FIRST_NAME" VARCHAR2(30),                                                      
"MIDDLE_NAME" VARCHAR2(30),                                                     
"FULL_NAME" VARCHAR2(90),                                                       
"PRIOR_NAME" VARCHAR2(30),                                                      
"USER_NAME" VARCHAR2(60),                                                       
"EMAIL_ADDRESS" VARCHAR2(60),                                                   
"DATE_OF_BIRTH" DATE,                                                           
"AGE" NUMBER(3,0),                                                              
"AGE_BY_FISCAL_YEAR" NUMBER(3,0),                                               
"GENDER" VARCHAR2(30),                                                          
"RACE" VARCHAR2(30),                                                            
"EDUCATION_LEVEL" VARCHAR2(30),                                                 
"DEGREE" VARCHAR2(11),                                                          
"MAJOR" VARCHAR2(30),                                                           
"IS_HANDICAPPED" CHAR(1),                                                       
"HANDICAP_TYPE" VARCHAR2(30),                                                   
"IS_VETERAN" CHAR(1),                                                           
"VETERAN_TYPE" VARCHAR2(30),                                                    
"VISA_CODE" VARCHAR2(20),                                                       
"VISA_TYPE" VARCHAR2(30),                                                       
"VISA_RENEWAL_DATE" DATE,                                                       
"HAS_VISA" CHAR(1),                                                             
"OFFICE_LOCATION" VARCHAR2(30),                                                 
"OFFICE_PHONE" VARCHAR2(20),                                                    
"SECONDRY_OFFICE_LOCATION" VARCHAR2(30),                                        
"SECONDRY_OFFICE_PHONE" VARCHAR2(20),                                           
"SCHOOL" VARCHAR2(50),                                                          
"YEAR_GRADUATED" VARCHAR2(30),                                                  
"DIRECTORY_DEPARTMENT" VARCHAR2(30),                                            
"SALUTATION" VARCHAR2(30),                                                      
"COUNTRY_OF_CITIZENSHIP" VARCHAR2(30),                                          
"PRIMARY_TITLE" VARCHAR2(51),                                                   
"DIRECTORY_TITLE" VARCHAR2(50),                                                 
"HOME_UNIT" VARCHAR2(8),                                                        
"IS_FACULTY" CHAR(1),                                                           
"IS_GRADUATE_STUDENT_STAFF" CHAR(1),                                            
"IS_RESEARCH_STAFF" CHAR(1),                                                    
"IS_SERVICE_STAFF" CHAR(1),                                                     
"IS_SUPPORT_STAFF" CHAR(1),                                                     
"IS_OTHER_ACCADEMIC_GROUP" CHAR(1),                                             
"IS_MEDICAL_STAFF" CHAR(1),                                                     
"VACATION_ACCURAL" CHAR(1),                                                     
"IS_ON_SABBATICAL" CHAR(1),                                                     
"ID_PROVIDED" VARCHAR2(30),                                                     
"ID_VERIFIED" VARCHAR2(30),                                                     
"ADDRESS_LINE_1" VARCHAR2(80),                                                  
"ADDRESS_LINE_2" VARCHAR2(80),                                                  
"ADDRESS_LINE_3" VARCHAR2(80),                                                  
"CITY" VARCHAR2(30),                                                            
"COUNTY" VARCHAR2(30),                                                          
"STATE" VARCHAR2(30),                                                           
"POSTAL_CODE" VARCHAR2(15),                                                     
"COUNTRY_CODE" CHAR(3),                                                         
"FAX_NUMBER" VARCHAR2(20),                                                      
"PAGER_NUMBER" VARCHAR2(20),                                                    
"MOBILE_PHONE_NUMBER" VARCHAR2(20),                                             
"ERA_COMMONS_USER_NAME" VARCHAR2(20),                                           
"CONFLICT_OF_INTEREST_FLAG" CHAR(1),                                            
"PERCENTAGE_EFFORT" NUMBER(5,2),                                                
"FEDR_DEBR_FLAG" CHAR(1),                                                       
"FEDR_DELQ_FLAG" CHAR(1),                                                       
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROP_PERSON_N4" NOT NULL ENABLE,        
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROP_PERSON_N5" NOT NULL ENABLE,      
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROP_PERSON_N6" NOT NULL ENABLE,
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
CONSTRAINT "EPS_PROP_PERSON_P1" PRIMARY KEY ("PROPOSAL_NUMBER",                 
"PROP_PERSON_NUMBER", "PROP_PERSON_ROLE_ID") ENABLE,                            
CONSTRAINT "EPS_PROP_PERSON_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                
                                                                                
CREATE TABLE "NARRATIVE"                                                        
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "NARRATIVEN1" NOT NULL ENABLE,      
"MODULE_NUMBER" NUMBER(4,0) CONSTRAINT "NARRATIVEN2" NOT NULL ENABLE,           
"MODULE_SEQUENCE_NUMBER" NUMBER(4,0) CONSTRAINT "NARRATIVEN3" NOT NULL ENABLE,  
"MODULE_TITLE" VARCHAR2(150),                                                   
"MODULE_STATUS_CODE" VARCHAR2(3) CONSTRAINT "NARRATIVEN4" NOT NULL ENABLE,      
"CONTACT_NAME" VARCHAR2(30),                                                    
"PHONE_NUMBER" VARCHAR2(20),                                                    
"EMAIL_ADDRESS" VARCHAR2(60),                                                   
"COMMENTS" VARCHAR2(300),                                                       
"FILE_NAME" VARCHAR2(150),                                                      
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "NARRATIVEN5" NOT NULL ENABLE,             
"UPDATE_TIMESTAMP" DATE CONSTRAINT "NARRATIVEN6" NOT NULL ENABLE,               
"NARRATIVE_TYPE_CODE" VARCHAR2(3) CONSTRAINT "NARRATIVEN9" NOT NULL ENABLE,     
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "NARRATIVEN7" NOT NULL ENABLE,       
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "NARRATIVEN8" NOT NULL      
ENABLE,                                                                         
CONSTRAINT "PK_NARRATIVE_KRA" PRIMARY KEY ("PROPOSAL_NUMBER", "MODULE_NUMBER")  
ENABLE                                                                          
) ;
                                                                            
                                                                                
CREATE TABLE "NARRATIVE_STATUS"                                                 
(	"NARRATIVE_STATUS_CODE" VARCHAR2(3) CONSTRAINT "NARRATIVE_STATUSN1" NOT NULL  
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(20) CONSTRAINT "NARRATIVE_STATUSN2" NOT NULL ENABLE,     
"UPDATE_TIMESTAMP" DATE CONSTRAINT "NARRATIVE_STATUSN3" NOT NULL ENABLE,        
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "NARRATIVE_STATUSN4" NOT NULL ENABLE,      
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "NARRATIVE_STATUSN5" NOT NULL ENABLE,
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "NARRATIVE_STATUSN6" NOT    
NULL ENABLE,                                                                    
CONSTRAINT "PK_NARRATIVE_STATUS_KRA" PRIMARY KEY ("NARRATIVE_STATUS_CODE")      
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "NARRATIVE_USER_RIGHTS"                                            
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "NARRATIVE_USER_RIGHTSN1" NOT NULL  
ENABLE,                                                                         
"MODULE_NUMBER" NUMBER(4,0) CONSTRAINT "NARRATIVE_USER_RIGHTSN2" NOT NULL       
ENABLE,                                                                         
"USER_ID" VARCHAR2(10) CONSTRAINT "NARRATIVE_USER_RIGHTSN3" NOT NULL ENABLE,    
"ACCESS_TYPE" CHAR(1) CONSTRAINT "NARRATIVE_USER_RIGHTSN4" NOT NULL ENABLE,     
"UPDATE_TIMESTAMP" DATE CONSTRAINT "NARRATIVE_USER_RIGHTSN5" NOT NULL ENABLE,   
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "NARRATIVE_USER_RIGHTSN6" NOT NULL ENABLE, 
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "NARRATIVE_USER_RIGHTSN7" NOT NULL   
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "NARRATIVE_USER_RIGHTSN8"   
NOT NULL ENABLE,                                                                
CONSTRAINT "PK_NARRATIVE_USER_RIGHTS_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",       
"MODULE_NUMBER", "USER_ID") ENABLE                                              
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "DEGREE_TYPE"                                                      
(	"DEGREE_CODE" VARCHAR2(6) CONSTRAINT "DEGREE_TYPE_N1" NOT NULL ENABLE,        
"DESCRIPTION" VARCHAR2(200),                                                    
"UPDATE_TIMESTAMP" DATE NOT NULL ENABLE,                                        
"UPDATE_USER" VARCHAR2(8) NOT NULL ENABLE,                                      
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
CONSTRAINT "DEGREE_TYPE_N2" PRIMARY KEY ("DEGREE_CODE") ENABLE,                 
CONSTRAINT "DEGREE_TYPE_C0" UNIQUE ("OBJ_ID") ENABLE                            
   ) ;
                                                                         
                                                                                
CREATE TABLE "EPS_PROP_PERSON_ROLE"                                             
(	"PROP_PERSON_ROLE_ID" VARCHAR2(12) CONSTRAINT "EPS_PROP_PERSON_ROLE_N1" NOT   
NULL ENABLE,                                                                    
"DESCRIPTION" VARCHAR2(25) NOT NULL ENABLE,                                     
"UPDATE_TIMESTAMP" DATE NOT NULL ENABLE,                                        
"UPDATE_USER" VARCHAR2(8) NOT NULL ENABLE,                                      
"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE,                                
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL ENABLE,                       
"CERTIFICATION_REQUIRED" CHAR(1) DEFAULT 'Y' CONSTRAINT                         
"EPS_PROP_PERSON_ROLE_N3" NOT NULL ENABLE,                                      
CONSTRAINT "EPS_PROP_PERSON_ROLE_N2" PRIMARY KEY ("PROP_PERSON_ROLE_ID") ENABLE,
CONSTRAINT "EPS_PROP_PERSON_ROLE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
           
                                                                                
CREATE TABLE "FP_DOC_GROUP_T"                                                   
(	"FDOC_GRP_CD" VARCHAR2(2) CONSTRAINT "FP_DOC_GROUP_TN1" NOT NULL ENABLE,      
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "FP_DOC_GROUP_TN2" NOT NULL 
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "FP_DOC_GROUP_TN3" NOT NULL ENABLE,  
"FDOC_GRP_NM" VARCHAR2(40),                                                     
"FDOC_CLASS_CD" VARCHAR2(2),                                                    
CONSTRAINT "FP_DOC_GROUP_TP1" PRIMARY KEY ("FDOC_GRP_CD") ENABLE,               
CONSTRAINT "FP_DOC_GROUP_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                  
                                                                                
CREATE TABLE "FP_DOC_HEADER_T"                                                  
(	"FDOC_NBR" VARCHAR2(14) CONSTRAINT "FP_DOC_HEADER_TN1" NOT NULL ENABLE,       
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "FP_DOC_HEADER_TN2" NOT NULL
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "FP_DOC_HEADER_TN3" NOT NULL ENABLE, 
"FDOC_STATUS_CD" VARCHAR2(2),                                                   
"FDOC_DESC" VARCHAR2(40),                                                       
"FDOC_TOTAL_AMT" NUMBER(19,2),                                                  
"ORG_DOC_NBR" VARCHAR2(10),                                                     
"FDOC_IN_ERR_NBR" VARCHAR2(14),                                                 
"FDOC_TMPL_NBR" VARCHAR2(14),                                                   
"TEMP_DOC_FNL_DT" DATE,                                                         
"FDOC_EXPLAIN_TXT" VARCHAR2(400),                                               
CONSTRAINT "FP_DOC_HEADER_TP1" PRIMARY KEY ("FDOC_NBR") ENABLE,                 
CONSTRAINT "FP_DOC_HEADER_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                 
                                                                                
CREATE TABLE "FP_DOC_STATUS_T"                                                  
(	"FDOC_STATUS_CD" VARCHAR2(2) CONSTRAINT "FP_DOC_STATUS_TN1" NOT NULL ENABLE,  
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "FP_DOC_STATUS_TN2" NOT NULL
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "FP_DOC_STATUS_TN3" NOT NULL ENABLE, 
"FDOC_STATUS_NM" VARCHAR2(10),                                                  
CONSTRAINT "FP_DOC_STATUS_TP1" PRIMARY KEY ("FDOC_STATUS_CD") ENABLE,           
CONSTRAINT "FP_DOC_STATUS_TC0" UNIQUE ("OBJ_ID") ENABLE                         
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "FP_DOC_TYPE_T"                                                    
(	"FDOC_TYP_CD" VARCHAR2(4) CONSTRAINT "FP_DOC_TYPE_TN1" NOT NULL ENABLE,       
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "FP_DOC_TYPE_TN2" NOT NULL  
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "FP_DOC_TYPE_TN3" NOT NULL ENABLE,   
"FDOC_GRP_CD" VARCHAR2(2),                                                      
"FDOC_NM" VARCHAR2(40),                                                         
"FIN_ELIM_ELGBL_CD" VARCHAR2(1),                                                
"FDOC_TYP_ACTIVE_CD" VARCHAR2(1),                                               
"FDOC_RTNG_RULE_CD" VARCHAR2(1),                                                
"FDOC_AUTOAPRV_DAYS" NUMBER(7,0),                                               
"FDOC_BALANCED_CD" VARCHAR2(1),                                                 
"TRN_SCRBBR_OFST_GEN_IND" CHAR(1),                                              
CONSTRAINT "FP_DOC_TYPE_TP1" PRIMARY KEY ("FDOC_TYP_CD") ENABLE,                
CONSTRAINT "FP_DOC_TYPE_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                   
                                                                                
CREATE TABLE "FP_MAINT_LOCK_T"                                                  
(	"LOCK_REPRESENTATION_TXT" VARCHAR2(255) CONSTRAINT "FP_MAINT_LOCK_TN1" NOT    
NULL ENABLE,                                                                    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "FP_MAINT_LOCK_TN2" NOT NULL
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "FP_MAINT_LOCK_TN3" NOT NULL ENABLE, 
"FDOC_NBR" VARCHAR2(14) CONSTRAINT "FP_MAINT_LOCK_TN4" NOT NULL ENABLE,         
CONSTRAINT "FP_MAINT_LOCK_TP1" PRIMARY KEY ("LOCK_REPRESENTATION_TXT") ENABLE,  
CONSTRAINT "FP_MAINT_LOCK_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                 
                                                                                
CREATE TABLE "FS_ADHOC_RTE_ACTN_RECP_T"                                         
(	"ACTN_RQST_RECP_TYP_CD" NUMBER(1,0) CONSTRAINT "FS_ADHOC_RTE_ACTN_RECP_TN1"   
NOT NULL ENABLE,                                                                
"ACTN_RQST_CD" VARCHAR2(30) CONSTRAINT "FS_ADHOC_RTE_ACTN_RECP_TN2" NOT NULL    
ENABLE,                                                                         
"ACTN_RQST_RECP_ID" VARCHAR2(70) CONSTRAINT "FS_ADHOC_RTE_ACTN_RECP_TN3" NOT    
NULL ENABLE,                                                                    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "FS_ADHOC_RTE_ACTN_RECP_TN4"
NOT NULL ENABLE,                                                                
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "FS_ADHOC_RTE_ACTN_RECP_TN5" NOT NULL
ENABLE,                                                                         
"FDOC_NBR" VARCHAR2(14) CONSTRAINT "FS_ADHOC_RTE_ACTN_RECP_TN6" NOT NULL ENABLE,
CONSTRAINT "FS_ADHOC_RTE_ACTN_RECP_TP1" PRIMARY KEY ("ACTN_RQST_RECP_TYP_CD",   
"ACTN_RQST_CD", "ACTN_RQST_RECP_ID") ENABLE,                                    
CONSTRAINT "FS_ADHOC_RTE_ACTN_RECP_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
        
                                                                                
CREATE TABLE "FS_CODES_DESC_T"                                                  
(	"CODE" VARCHAR2(50) CONSTRAINT "FS_CODES_DESC_TN1" NOT NULL ENABLE,           
"CLASS_NAME" VARCHAR2(75) CONSTRAINT "FS_CODES_DESC_TN2" NOT NULL ENABLE,       
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "FS_CODES_DESC_TN3" NOT NULL
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "FS_CODES_DESC_TN4" NOT NULL ENABLE, 
"NAME" VARCHAR2(2000),                                                          
"ACTIVE_IND" VARCHAR2(1) CONSTRAINT "FS_CODES_DESC_TN5" NOT NULL ENABLE,        
CONSTRAINT "FS_CODES_DESC_TP1" PRIMARY KEY ("CODE", "CLASS_NAME") ENABLE,       
CONSTRAINT "FS_CODES_DESC_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                 
                                                                                
CREATE TABLE "SH_ATT_T"                                                         
(	"NTE_ID" NUMBER(14,0) CONSTRAINT "SH_ATT_TN1" NOT NULL ENABLE,                
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SH_ATT_TN2" NOT NULL       
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SH_ATT_TN3" NOT NULL ENABLE,        
"ATT_MIME_TYP_CD" VARCHAR2(40),                                                 
"ATT_FL_NM" VARCHAR2(250),                                                      
"ATT_ID" VARCHAR2(36),                                                          
"ATT_FL_SZ" NUMBER(14,0),                                                       
"ATT_TYP_CD" VARCHAR2(2),                                                       
CONSTRAINT "SH_ATT_TP1" PRIMARY KEY ("NTE_ID") ENABLE,                          
CONSTRAINT "SH_ATT_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                        
                                                                                
CREATE TABLE "SH_LOCK_T"                                                        
(	"TRN_SMPHR_TYP_CD" VARCHAR2(10) CONSTRAINT "SH_LOCK_TN1" NOT NULL ENABLE,     
"TRN_SMPHR_ID" VARCHAR2(10) CONSTRAINT "SH_LOCK_TN2" NOT NULL ENABLE,           
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SH_LOCK_TN3" NOT NULL      
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SH_LOCK_TN4" NOT NULL ENABLE,       
"PERSON_UNVL_ID" VARCHAR2(10),                                                  
"TRN_LCKTM_TS" DATE,                                                            
CONSTRAINT "SH_LOCK_TP1" PRIMARY KEY ("TRN_SMPHR_TYP_CD", "TRN_SMPHR_ID")       
ENABLE,                                                                         
CONSTRAINT "SH_LOCK_TC0" UNIQUE ("OBJ_ID") ENABLE                               
) ;
                                                                            
                                                                                
CREATE TABLE "SH_LOCK_TYP_DESC_T"                                               
(	"TRN_SMPHR_TYP_CD" VARCHAR2(10) CONSTRAINT "SH_LOCK_TYP_DESC_TN1" NOT NULL    
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SH_LOCK_TYP_DESC_TN2" NOT  
NULL ENABLE,                                                                    
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SH_LOCK_TYP_DESC_TN3" NOT NULL      
ENABLE,                                                                         
"PRSN_UPDTABLTY_NBR" NUMBER(12,0),                                              
"TRN_SMPHR_DESC" VARCHAR2(60),                                                  
CONSTRAINT "SH_LOCK_TYP_DESC_TP1" PRIMARY KEY ("TRN_SMPHR_TYP_CD") ENABLE,      
CONSTRAINT "SH_LOCK_TYP_DESC_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
              
                                                                                
CREATE TABLE "SH_NTE_T"                                                         
(	"NTE_ID" NUMBER(14,0) CONSTRAINT "SH_NTE_TN1" NOT NULL ENABLE,                
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SH_NTE_TN2" NOT NULL       
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SH_NTE_TN3" NOT NULL ENABLE,        
"RMT_OBJ_ID" VARCHAR2(36) CONSTRAINT "SH_NTE_TN4" NOT NULL ENABLE,              
"NTE_AUTH_ID" VARCHAR2(10) CONSTRAINT "SH_NTE_TN5" NOT NULL ENABLE,             
"NTE_POST_TS" DATE CONSTRAINT "SH_NTE_TN6" NOT NULL ENABLE,                     
"NTE_TYP_CD" VARCHAR2(4) CONSTRAINT "SH_NTE_TN7" NOT NULL ENABLE,               
"NTE_TXT" VARCHAR2(800),                                                        
"NTE_PRG_CD" VARCHAR2(1),                                                       
"NTE_TPC_TXT" VARCHAR2(40),                                                     
CONSTRAINT "SH_NTE_TP1" PRIMARY KEY ("NTE_ID") ENABLE,                          
CONSTRAINT "SH_NTE_TC0" UNIQUE ("OBJ_ID") ENABLE,                               
CONSTRAINT "SH_NTE_TC1" UNIQUE ("RMT_OBJ_ID", "NTE_AUTH_ID", "NTE_POST_TS",     
"NTE_TYP_CD") ENABLE                                                            
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "SH_NTE_TYP_T"                                                     
(	"NTE_TYP_CD" VARCHAR2(4) CONSTRAINT "SH_NTE_TYP_TN1" NOT NULL ENABLE,         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SH_NTE_TYP_TN2" NOT NULL   
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SH_NTE_TYP_TN3" NOT NULL ENABLE,    
"NTE_TYP_DESC" VARCHAR2(100),                                                   
"NTE_TYP_ACTV_IND" VARCHAR2(1),                                                 
CONSTRAINT "SH_NTE_TYP_TP1" PRIMARY KEY ("NTE_TYP_CD") ENABLE,                  
CONSTRAINT "SH_NTE_TYP_TC0" UNIQUE ("OBJ_ID") ENABLE                            
  ) ;
                                                                          
                                                                                
CREATE TABLE "SH_PARM_DTL_TYP_T"                                                
(	"SH_PARM_NMSPC_CD" VARCHAR2(20),                                              
"SH_PARM_DTL_TYP_CD" VARCHAR2(100),                                             
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SH_PARM_ADDL_DTL_TYP_TN1"  
NOT NULL ENABLE,                                                                
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SH_PARM_ADDL_DTL_TYP_TN2" NOT NULL  
ENABLE,                                                                         
"SH_PARM_DTL_TYP_NM" VARCHAR2(255),                                             
"ACTIVE_IND" CHAR(1) DEFAULT 'Y' CONSTRAINT "SH_PARM_ADDL_DTL_TYP_TN3" NOT NULL 
ENABLE,                                                                         
CONSTRAINT "SH_PARM_DTL_TYP_TP1" PRIMARY KEY ("SH_PARM_NMSPC_CD",               
"SH_PARM_DTL_TYP_CD") ENABLE,                                                   
CONSTRAINT "SH_PARM_DTL_TYP_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
               
                                                                                
CREATE TABLE "SH_PARM_NMSPC_T"                                                  
(	"SH_PARM_NMSPC_CD" VARCHAR2(20),                                              
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SH_PARM_NMSPC_TN1" NOT NULL
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SH_PARM_NMSPC_TN2" NOT NULL ENABLE, 
"SH_PARM_NMSPC_NM" VARCHAR2(40),                                                
"ACTIVE_IND" CHAR(1) DEFAULT 'Y' CONSTRAINT "SH_PARM_NMSPC_TN3" NOT NULL ENABLE,
CONSTRAINT "SH_PARM_NMSPC_TP1" PRIMARY KEY ("SH_PARM_NMSPC_CD") ENABLE,         
CONSTRAINT "SH_PARM_NMSPC_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                 
                                                                                
CREATE TABLE "SH_PARM_T"                                                        
(	"SH_PARM_NMSPC_CD" VARCHAR2(20),                                              
"SH_PARM_DTL_TYP_CD" VARCHAR2(100),                                             
"SH_PARM_NM" VARCHAR2(100),                                                     
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SH_PARM_TN1" NOT NULL      
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SH_PARM_TN2" NOT NULL ENABLE,       
"SH_PARM_TYP_CD" VARCHAR2(5) CONSTRAINT "SH_PARM_TN3" NOT NULL ENABLE,          
"SH_PARM_TXT" VARCHAR2(4000),                                                   
"SH_PARM_DESC" VARCHAR2(4000),                                                  
"SH_PARM_CONS_CD" VARCHAR2(1),                                                  
"WRKGRP_NM" VARCHAR2(70) DEFAULT 'WorkflowAdmin' CONSTRAINT "SH_PARM_TN4" NOT   
NULL ENABLE,                                                                    
"ACTIVE_IND" CHAR(1) DEFAULT 'Y' CONSTRAINT "SH_PARM_TN5" NOT NULL ENABLE,      
CONSTRAINT "SH_PARM_TP1" PRIMARY KEY ("SH_PARM_NMSPC_CD", "SH_PARM_NM") ENABLE, 
CONSTRAINT "SH_PARM_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                       
                                                                                
CREATE TABLE "SH_PARM_TYP_T"                                                    
(	"SH_PARM_TYP_CD" VARCHAR2(5),                                                 
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SH_PARM_TYP_TN1" NOT NULL  
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SH_PARM_TYP_TN2" NOT NULL ENABLE,   
"SH_PARM_TYP_NM" VARCHAR2(40),                                                  
"ACTIVE_IND" CHAR(1) DEFAULT 'Y' CONSTRAINT "SH_PARM_TYP_TN3" NOT NULL ENABLE,  
CONSTRAINT "SH_PARM_TYP_TP1" PRIMARY KEY ("SH_PARM_TYP_CD") ENABLE,             
CONSTRAINT "SH_PARM_TYP_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
                   
                                                                                
CREATE TABLE "SH_USR_PROP_T"                                                    
(	"PERSON_UNVL_ID" VARCHAR2(10) CONSTRAINT "SH_USR_PROP_TN1" NOT NULL ENABLE,   
"APPL_MOD_ID" VARCHAR2(20) CONSTRAINT "SH_USR_PROP_TN2" NOT NULL ENABLE,        
"USR_PROP_NM" VARCHAR2(40) CONSTRAINT "SH_USR_PROP_TN3" NOT NULL ENABLE,        
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "SH_USR_PROP_TN4" NOT NULL  
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "SH_USR_PROP_TN5" NOT NULL ENABLE,   
"USR_PROP_VAL" VARCHAR2(4000),                                                  
CONSTRAINT "SH_USR_PROP_TP1" PRIMARY KEY ("PERSON_UNVL_ID", "APPL_MOD_ID",      
"USR_PROP_NM") ENABLE,                                                          
CONSTRAINT "SH_USR_PROP_TC0" UNIQUE ("OBJ_ID") ENABLE                           
) ;
                                                                            
                                                                                
CREATE TABLE "EN_ACTN_ITM_T"                                                    
(	"ACTN_ITM_ID" NUMBER(14,0) NOT NULL ENABLE,                                   
"ACTN_ITM_PRSN_EN_ID" VARCHAR2(30) NOT NULL ENABLE,                             
"ACTN_ITM_ASND_DT" DATE NOT NULL ENABLE,                                        
"ACTN_ITM_RQST_CD" CHAR(1) NOT NULL ENABLE,                                     
"ACTN_RQST_ID" NUMBER(14,0) NOT NULL ENABLE,                                    
"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                      
"WRKGRP_ID" NUMBER(14,0),                                                       
"ROLE_NM" VARCHAR2(2000),                                                       
"ACTN_ITM_DLGN_PRSN_EN_ID" VARCHAR2(30),                                        
"ACTN_ITM_DLGN_WRKGRP_ID" NUMBER(14,0),                                         
"DOC_TTL" VARCHAR2(255),                                                        
"DOC_TYP_LBL_TXT" VARCHAR2(255) NOT NULL ENABLE,                                
"DOC_TYP_HDLR_URL_ADDR" VARCHAR2(255) NOT NULL ENABLE,                          
"DOC_TYP_NM" VARCHAR2(255) NOT NULL ENABLE,                                     
"ACTN_ITM_RESP_ID" NUMBER(14,0) NOT NULL ENABLE,                                
"DLGN_TYP" VARCHAR2(1),                                                         
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_ACTN_ITM_T_PK" PRIMARY KEY ("ACTN_ITM_ID") ENABLE
   ) ;
        
                                                                                
CREATE TABLE "EN_ACTN_RQST_T"                                                   
(	"ACTN_RQST_ID" NUMBER(14,0) NOT NULL ENABLE,                                  
"ACTN_RQST_PARNT_ID" NUMBER(14,0),                                              
"ACTN_RQST_CD" CHAR(1) NOT NULL ENABLE,                                         
"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                      
"RULE_BASE_VALUES_ID" NUMBER(19,0),                                             
"ACTN_RQST_STAT_CD" CHAR(1) NOT NULL ENABLE,                                    
"ACTN_RQST_RESP_ID" NUMBER(14,0) NOT NULL ENABLE,                               
"ACTN_RQST_PRSN_EN_ID" VARCHAR2(30),                                            
"WRKGRP_ID" NUMBER(14,0),                                                       
"ROLE_NM" VARCHAR2(2000),                                                       
"QUAL_ROLE_NM" VARCHAR2(2000),                                                  
"QUAL_ROLE_NM_LBL_TXT" VARCHAR2(2000),                                          
"ACTN_RQST_RECP_TYP_CD" CHAR(1),                                                
"ACTN_RQST_PRIO_NBR" NUMBER(8,0) NOT NULL ENABLE,                               
"ACTN_RQST_RTE_TYP_NM" VARCHAR2(255),                                           
"ACTN_RQST_RTE_LVL_NBR" NUMBER(8,0) NOT NULL ENABLE,                            
"ACTN_RQST_RTE_NODE_INSTN_ID" NUMBER(19,0),                                     
"ACTN_TKN_ID" NUMBER(14,0),                                                     
"DOC_VER_NBR" NUMBER(8,0) NOT NULL ENABLE,                                      
"ACTN_RQST_CRTE_DT" DATE NOT NULL ENABLE,                                       
"ACTN_RQST_RESP_DESC" VARCHAR2(200),                                            
"ACTN_RQST_IGN_PREV_ACTN_IND" NUMBER(1,0) DEFAULT 0,                            
"ACTN_RQST_ANNOTN_TXT" VARCHAR2(2000),                                          
"DLGN_TYP" CHAR(1),                                                             
"ACTN_RQST_APPR_PLCY" CHAR(1),                                                  
"ACTN_RQST_CUR_IND" NUMBER(1,0) DEFAULT 1,                                      
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_ACTN_RQST_TEMP_T_PK" PRIMARY KEY ("ACTN_RQST_ID") ENABLE         
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_ACTN_TKN_T"                                                    
(	"ACTN_TKN_ID" NUMBER(14,0) NOT NULL ENABLE,                                   
"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                      
"ACTN_TKN_PRSN_EN_ID" VARCHAR2(30) NOT NULL ENABLE,                             
"ACTN_TKN_DLGTR_PRSN_EN_ID" VARCHAR2(30),                                       
"ACTN_TKN_DLGTR_WRKGRP_ID" NUMBER(14,0),                                        
"ACTN_TKN_CD" CHAR(1) NOT NULL ENABLE,                                          
"ACTN_TKN_DT" DATE NOT NULL ENABLE,                                             
"DOC_VER_NBR" NUMBER(8,0) NOT NULL ENABLE,                                      
"ACTN_TKN_ANNOTN_TXT" VARCHAR2(2000),                                           
"ACTN_TKN_CUR_IND" NUMBER(1,0) DEFAULT 1,                                       
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_ACTN_TKN_T_PK" PRIMARY KEY ("ACTN_TKN_ID") ENABLE
   ) ;
        
                                                                                
CREATE TABLE "EN_APPL_CNST_T"                                                   
(	"APPL_CNST_NM" VARCHAR2(100) NOT NULL ENABLE,                                 
"APPL_CNST_VAL_TXT" VARCHAR2(2000),                                             
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_APPL_CNST_T_PK" PRIMARY KEY ("APPL_CNST_NM") ENABLE
   ) ;
      
                                                                                
CREATE TABLE "EN_ATTACHMENT_T"                                                  
(	"ATTACHMENT_ID" NUMBER(19,0) NOT NULL ENABLE,                                 
"NTE_ID" NUMBER(19,0) NOT NULL ENABLE,                                          
"FILE_NM" VARCHAR2(255) NOT NULL ENABLE,                                        
"FILE_LOC" VARCHAR2(255) NOT NULL ENABLE,                                       
"MIME_TYP" VARCHAR2(255) NOT NULL ENABLE,                                       
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_ATTACHMENT_T_PK" PRIMARY KEY ("ATTACHMENT_ID") ENABLE
   ) ;
    
                                                                                
CREATE TABLE "EN_CACHE_SERVER_T"                                                
(	"IP_ADDRESS" VARCHAR2(250) NOT NULL ENABLE,                                   
CONSTRAINT "EN_CACHE_SERVER_T_PK" PRIMARY KEY ("IP_ADDRESS") ENABLE             
  ) ;
                                                                          
                                                                                
CREATE TABLE "EN_DIRTY_CACHE_T"                                                 
(	"CACHE_ENTRY_ID" NUMBER(19,0) NOT NULL ENABLE,                                
"CACHE_NAME" VARCHAR2(2000) NOT NULL ENABLE,                                    
"CACHE_ID" VARCHAR2(2000) NOT NULL ENABLE,                                      
"IP_ADDRESS" VARCHAR2(2000) NOT NULL ENABLE,                                    
CONSTRAINT "EN_DIRTY_CACHE_T_PK" PRIMARY KEY ("CACHE_ENTRY_ID") ENABLE          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_DLGN_RSP_T"                                                    
(	"DLGN_RULE_ID" NUMBER(19,0) NOT NULL ENABLE,                                  
"RULE_RSP_ID" NUMBER(19,0) NOT NULL ENABLE,                                     
"DLGN_RULE_BASE_VAL_ID" NUMBER(19,0) NOT NULL ENABLE,                           
"DLGN_TYP" VARCHAR2(20) NOT NULL ENABLE,                                        
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_DLGN_RSP_PK" PRIMARY KEY ("DLGN_RULE_ID") ENABLE
   ) ;
         
                                                                                
CREATE TABLE "EN_DOC_HDR_EXT_DT_T"                                              
(	"DOC_HDR_EXT_ID" NUMBER(19,0) NOT NULL ENABLE,                                
"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                      
"DOC_HDR_EXT_VAL_KEY" VARCHAR2(256) NOT NULL ENABLE,                            
"DOC_HDR_EXT_VAL" DATE,                                                         
CONSTRAINT "EN_DOC_HDR_EXT_DT_T_PK" PRIMARY KEY ("DOC_HDR_EXT_ID") ENABLE       
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_DOC_HDR_EXT_FLT_T"                                             
(	"DOC_HDR_EXT_ID" NUMBER(19,0) NOT NULL ENABLE,                                
"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                      
"DOC_HDR_EXT_VAL_KEY" VARCHAR2(256) NOT NULL ENABLE,                            
"DOC_HDR_EXT_VAL" FLOAT(126),                                                   
CONSTRAINT "EN_DOC_HDR_EXT_FLT_T_PK" PRIMARY KEY ("DOC_HDR_EXT_ID") ENABLE      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_DOC_HDR_EXT_LONG_T"                                            
(	"DOC_HDR_EXT_ID" NUMBER(19,0) NOT NULL ENABLE,                                
"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                      
"DOC_HDR_EXT_VAL_KEY" VARCHAR2(256) NOT NULL ENABLE,                            
"DOC_HDR_EXT_VAL" NUMBER(*,0),                                                  
CONSTRAINT "EN_DOC_HDR_EXT_LONG_T_PK" PRIMARY KEY ("DOC_HDR_EXT_ID") ENABLE     
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_DOC_HDR_EXT_T"                                                 
(	"DOC_HDR_EXT_ID" NUMBER(19,0) NOT NULL ENABLE,                                
"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                      
"DOC_HDR_EXT_VAL_KEY" VARCHAR2(256) NOT NULL ENABLE,                            
"DOC_HDR_EXT_VAL" VARCHAR2(2000),                                               
CONSTRAINT "EN_DOC_HDR_EXT_T_PK" PRIMARY KEY ("DOC_HDR_EXT_ID") ENABLE
   ) ;
  
                                                                                
CREATE TABLE "EN_DOC_HDR_T"                                                     
(	"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                    
"DOC_TYP_ID" NUMBER(19,0),                                                      
"DOC_RTE_STAT_CD" CHAR(1) NOT NULL ENABLE,                                      
"DOC_RTE_LVL_NBR" NUMBER(8,0) NOT NULL ENABLE,                                  
"DOC_STAT_MDFN_DT" DATE NOT NULL ENABLE,                                        
"DOC_CRTE_DT" DATE NOT NULL ENABLE,                                             
"DOC_APRV_DT" DATE,                                                             
"DOC_FNL_DT" DATE,                                                              
"DOC_RTE_STAT_MDFN_DT" DATE,                                                    
"DOC_RTE_LVL_MDFN_DT" DATE,                                                     
"DOC_TTL" VARCHAR2(255),                                                        
"DOC_APPL_DOC_ID" VARCHAR2(20),                                                 
"DOC_VER_NBR" NUMBER NOT NULL ENABLE,                                           
"DOC_INITR_PRSN_EN_ID" VARCHAR2(30) NOT NULL ENABLE,                            
"DOC_OVRD_IND" NUMBER(1,0) DEFAULT 0,                                           
"DOC_LOCK_CD" CHAR(1),                                                          
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
"DOC_RTE_USR_PRSN_EN_ID" VARCHAR2(30),                                          
CONSTRAINT "EN_DOC_HDR_T_PK" PRIMARY KEY ("DOC_HDR_ID") ENABLE
   ) ;
          
                                                                                
CREATE TABLE "EN_DOC_NTE_T"                                                     
(	"DOC_NTE_ID" NUMBER(19,0) NOT NULL ENABLE,                                    
"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                      
"DOC_NTE_AUTH_PRSN_EN_ID" VARCHAR2(30) NOT NULL ENABLE,                         
"DOC_NTE_CRT_DT" DATE NOT NULL ENABLE,                                          
"DOC_NTE_TXT" VARCHAR2(4000),                                                   
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_DOC_NTE_T_PK" PRIMARY KEY ("DOC_NTE_ID") ENABLE
   ) ;
          
                                                                                
CREATE TABLE "EN_DOC_RTE_TYP_T"                                                 
(	"DOC_RTE_TYP_NM" VARCHAR2(255) NOT NULL ENABLE,                               
"DOC_RTE_TYP_LBL_TXT" VARCHAR2(250),                                            
"DOC_RTE_MOD_NM" VARCHAR2(250),                                                 
"DOC_RTE_TYP_DESC" VARCHAR2(250),                                               
"DOC_RTE_TYP_ACTV_IND" NUMBER(1,0) DEFAULT 0 NOT NULL ENABLE,                   
"DOC_RTE_MOD_JNDI_FTRY_CLS_NM" VARCHAR2(200),                                   
"DOC_RTE_MOD_JNDI_URL_ADDR" VARCHAR2(200),                                      
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_DOC_RTE_TYP_TEMP_T_PK" PRIMARY KEY ("DOC_RTE_TYP_NM") ENABLE     
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_DOC_TYP_ATTRIB_T"                                              
(	"DOC_TYP_ATTRIB_ID" NUMBER(19,0) NOT NULL ENABLE,                             
"DOC_TYP_ID" NUMBER(19,0) NOT NULL ENABLE,                                      
"RULE_ATTRIB_ID" NUMBER(19,0) NOT NULL ENABLE,                                  
"ORD_INDX" NUMBER(4,0) DEFAULT 0,                                               
CONSTRAINT "EN_DOC_TYP_ATTRIB_T_PK" PRIMARY KEY ("DOC_TYP_ATTRIB_ID") ENABLE    
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_DOC_TYP_PLCY_RELN_T"                                           
(	"DOC_TYP_ID" NUMBER(19,0) NOT NULL ENABLE,                                    
"DOC_PLCY_NM" VARCHAR2(255) NOT NULL ENABLE,                                    
"DOC_PLCY_VAL" NUMBER(1,0) NOT NULL ENABLE,                                     
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_DOC_TYP_PLCY_RELN_T_PK" PRIMARY KEY ("DOC_TYP_ID", "DOC_PLCY_NM")
ENABLE
   ) ;
                                                                  
                                                                                
CREATE TABLE "EN_DOC_TYP_PROC_T"                                                
(	"DOC_TYP_PROC_ID" NUMBER(19,0) NOT NULL ENABLE,                               
"DOC_TYP_ID" NUMBER(19,0) NOT NULL ENABLE,                                      
"INIT_RTE_NODE_ID" NUMBER(19,0) NOT NULL ENABLE,                                
"PROC_NM" VARCHAR2(255) NOT NULL ENABLE,                                        
"INIT_IND" NUMBER(1,0) DEFAULT 0 NOT NULL ENABLE,                               
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_DOC_TYP_PROC_T_PK" PRIMARY KEY ("DOC_TYP_PROC_ID") ENABLE        
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_DOC_TYP_RTE_LVL_T"                                             
(	"DOC_RTE_LVL_ID" NUMBER(19,0) NOT NULL ENABLE,                                
"DOC_TYP_ID" NUMBER(19,0),                                                      
"DOC_RTE_LVL_NM" VARCHAR2(255),                                                 
"DOC_RTE_LVL_PRIO_NBR" NUMBER(10,0),                                            
"DOC_RTE_MTHD_NM" VARCHAR2(255) NOT NULL ENABLE,                                
"DOC_FNL_APRVR_IND" NUMBER(1,0),                                                
"DOC_MNDTRY_RTE_IND" NUMBER(1,0),                                               
"WRKGRP_ID" NUMBER(14,0),                                                       
"DOC_RTE_MTHD_CD" VARCHAR2(2),                                                  
"DOC_ACTVN_TYP_TXT" VARCHAR2(250),                                              
"DOC_RTE_LVL_IGN_PREV_ACTN_IND" NUMBER(1,0) DEFAULT 0,                          
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_DOC_TYP_RTE_LVL_T_PK" PRIMARY KEY ("DOC_RTE_LVL_ID") ENABLE      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_EDL_DMP_T"                                                     
(	"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                    
"DOC_TYP_NM" VARCHAR2(255) NOT NULL ENABLE,                                     
"DOC_RTE_STAT_CD" CHAR(1) NOT NULL ENABLE,                                      
"DOC_MDFN_DT" DATE NOT NULL ENABLE,                                             
"DOC_CRTE_DT" DATE NOT NULL ENABLE,                                             
"DOC_TTL" VARCHAR2(255),                                                        
"DOC_INITR_ID" VARCHAR2(30) NOT NULL ENABLE,                                    
"DOC_CRNT_NODE_NM" VARCHAR2(30) NOT NULL ENABLE,                                
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_EDL_DMP_PK" PRIMARY KEY ("DOC_HDR_ID") ENABLE
   ) ;
            
                                                                                
CREATE TABLE "EN_EDL_FIELD_DMP_T"                                               
(	"EDL_FIELD_DMP_ID" NUMBER(14,0) NOT NULL ENABLE,                              
"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                      
"FLD_NM" VARCHAR2(255) NOT NULL ENABLE,                                         
"FLD_VAL" VARCHAR2(4000),                                                       
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_EDL_FIELD_DMP_T_PK" PRIMARY KEY ("EDL_FIELD_DMP_ID") ENABLE      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_EDOCLT_ASSOC_T"                                                
(	"EDOCLT_ASSOC_ID" NUMBER(19,0) NOT NULL ENABLE,                               
"EDOCLT_ASSOC_DOCTYPE_NM" VARCHAR2(200) NOT NULL ENABLE,                        
"EDOCLT_ASSOC_DEF_NM" VARCHAR2(200),                                            
"EDOCLT_ASSOC_STYLE_NM" VARCHAR2(200),                                          
"EDOCLT_ASSOC_ACTV_IND" NUMBER(1,0) NOT NULL ENABLE,                            
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_EDOCLT_ASSOC_T" PRIMARY KEY ("EDOCLT_ASSOC_ID") ENABLE
   ) ;
   
                                                                                
CREATE TABLE "EN_HLP_T"                                                         
(	"EN_HLP_ID" NUMBER(19,0) NOT NULL ENABLE,                                     
"EN_HLP_NM" VARCHAR2(500) NOT NULL ENABLE,                                      
"EN_HLP_KY" VARCHAR2(500) NOT NULL ENABLE,                                      
"EN_HLP_TXT" VARCHAR2(4000) NOT NULL ENABLE,                                    
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_HLP_T_PK" PRIMARY KEY ("EN_HLP_ID") ENABLE
   ) ;
               
                                                                                
CREATE TABLE "EN_INIT_RTE_NODE_INSTN_T"                                         
(	"DOC_HDR_ID" NUMBER(19,0) NOT NULL ENABLE,                                    
"RTE_NODE_INSTN_ID" NUMBER(19,0) NOT NULL ENABLE,                               
CONSTRAINT "EN_INIT_RTE_NODE_INSTN_T_PK" PRIMARY KEY ("DOC_HDR_ID",             
"RTE_NODE_INSTN_ID") ENABLE
   ) ;
                                             
                                                                                
CREATE TABLE "EN_MSG_QUE_T"                                                     
(	"MESSAGE_QUE_ID" NUMBER(14,0) NOT NULL ENABLE,                                
"MESSAGE_QUE_DT" DATE NOT NULL ENABLE,                                          
"MESSAGE_EXP_DT" DATE,                                                          
"MESSAGE_QUE_PRIO_NBR" NUMBER(8,0) NOT NULL ENABLE,                             
"MESSAGE_QUE_STAT_CD" CHAR(1) NOT NULL ENABLE,                                  
"MESSAGE_QUE_RTRY_CNT" NUMBER(8,0) NOT NULL ENABLE,                             
"MESSAGE_QUE_IP_NBR" VARCHAR2(2000) NOT NULL ENABLE,                            
"MESSAGE_SERVICE_NM" VARCHAR2(255),                                             
"MESSAGE_ENTITY_NM" VARCHAR2(10) NOT NULL ENABLE,                               
"SERVICE_METHOD_NM" VARCHAR2(2000),                                             
"VAL_ONE" VARCHAR2(2000),                                                       
"VAL_TWO" VARCHAR2(2000),                                                       
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_MSG_QUE_T_PK" PRIMARY KEY ("MESSAGE_QUE_ID") ENABLE
   ) ;
      
                                                                                
CREATE TABLE "EN_ORG_RESP_ID_T"                                                 
(	"ORG_CD" VARCHAR2(4) NOT NULL ENABLE,                                         
"FIN_COA_CD" VARCHAR2(2) NOT NULL ENABLE,                                       
"ORG_RESP_ID" NUMBER(14,0) NOT NULL ENABLE,                                     
"ORG_RESP_ID_APRVR_TYP_CD" CHAR(1) NOT NULL ENABLE,                             
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_ORG_RESP_ID_T_PK" PRIMARY KEY ("ORG_CD", "FIN_COA_CD",           
"ORG_RESP_ID_APRVR_TYP_CD") ENABLE                                              
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_RMV_RPLC_DOC_T"                                                
(	"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                    
"OPRN" CHAR(1) NOT NULL ENABLE,                                                 
"PRSN_EN_ID" VARCHAR2(30) NOT NULL ENABLE,                                      
"RPLC_PRSN_EN_ID" VARCHAR2(30),                                                 
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RMV_RPLC_DOC_T_PK" PRIMARY KEY ("DOC_HDR_ID") ENABLE
   ) ;
     
                                                                                
CREATE TABLE "EN_RMV_RPLC_RULE_T"                                               
(	"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                    
"RULE_ID" NUMBER(19,0) NOT NULL ENABLE,                                         
CONSTRAINT "EN_RMV_RPLC_RULE_T_PK" PRIMARY KEY ("DOC_HDR_ID", "RULE_ID") ENABLE 
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_RMV_RPLC_WRKGRP_T"                                             
(	"DOC_HDR_ID" NUMBER(14,0) NOT NULL ENABLE,                                    
"WRKGRP_ID" NUMBER(14,0) NOT NULL ENABLE,                                       
CONSTRAINT "EN_RMV_RPLC_WRKGRP_T_PK" PRIMARY KEY ("DOC_HDR_ID", "WRKGRP_ID")    
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_RTE_BRCH_PROTO_T"                                              
(	"RTE_BRCH_PROTO_ID" NUMBER(19,0) NOT NULL ENABLE,                             
"RTE_BRCH_PROTO_NM" VARCHAR2(255) NOT NULL ENABLE,                              
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RTE_BRCH_PROTO_T_PK" PRIMARY KEY ("RTE_BRCH_PROTO_ID") ENABLE    
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_RTE_BRCH_ST_T"                                                 
(	"RTE_BRCH_ST_ID" NUMBER(19,0) NOT NULL ENABLE,                                
"RTE_BRCH_ID" NUMBER(19,0) NOT NULL ENABLE,                                     
"ST_KEY" VARCHAR2(255) NOT NULL ENABLE,                                         
"ST_VAL_TXT" VARCHAR2(2000),                                                    
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RTE_BRCH_ST_T_PK" PRIMARY KEY ("RTE_BRCH_ST_ID") ENABLE
   ) ;
  
                                                                                
CREATE TABLE "EN_RTE_BRCH_T"                                                    
(	"RTE_BRCH_ID" NUMBER(19,0) NOT NULL ENABLE,                                   
"BRCH_NM" VARCHAR2(255) NOT NULL ENABLE,                                        
"PARNT_RTE_BRCH_ID" NUMBER(19,0),                                               
"INIT_RTE_NODE_INSTN_ID" NUMBER(19,0),                                          
"SPLT_RTE_NODE_INSTN_ID" NUMBER(19,0),                                          
"JOIN_RTE_NODE_INSTN_ID" NUMBER(19,0),                                          
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RTE_BRCH_T_PK" PRIMARY KEY ("RTE_BRCH_ID") ENABLE
   ) ;
        
                                                                                
CREATE TABLE "EN_RTE_NODE_CFG_PARM_T"                                           
(	"RTE_NODE_CFG_PARM_ID" NUMBER(19,0) NOT NULL ENABLE,                          
"RTE_NODE_CFG_PARM_ND" NUMBER(19,0) NOT NULL ENABLE,                            
"RTE_NODE_CFG_PARM_KEY" VARCHAR2(255) NOT NULL ENABLE,                          
"RTE_NODE_CFG_PARM_VAL" VARCHAR2(4000),                                         
CONSTRAINT "EN_RTE_NODE_CFG_PARM_T_PK" PRIMARY KEY ("RTE_NODE_CFG_PARM_ID")     
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_RTE_NODE_INSTN_LNK_T"                                          
(	"FROM_RTE_NODE_INSTN_ID" NUMBER(19,0) NOT NULL ENABLE,                        
"TO_RTE_NODE_INSTN_ID" NUMBER(19,0) NOT NULL ENABLE,                            
CONSTRAINT "EN_RTE_NODE_INSTN_LNK_T_PK" PRIMARY KEY ("FROM_RTE_NODE_INSTN_ID",  
"TO_RTE_NODE_INSTN_ID") ENABLE                                                  
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_RTE_NODE_INSTN_ST_T"                                           
(	"RTE_NODE_INSTN_ST_ID" NUMBER(19,0) NOT NULL ENABLE,                          
"RTE_NODE_INSTN_ID" NUMBER(19,0) NOT NULL ENABLE,                               
"ST_KEY" VARCHAR2(255) NOT NULL ENABLE,                                         
"ST_VAL_TXT" VARCHAR2(2000),                                                    
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RTE_NODE_INSTN_ST_T_PK" PRIMARY KEY ("RTE_NODE_INSTN_ST_ID")     
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_RTE_NODE_INSTN_T"                                              
(	"RTE_NODE_INSTN_ID" NUMBER(19,0) NOT NULL ENABLE,                             
"DOC_ID" NUMBER(19,0) NOT NULL ENABLE,                                          
"RTE_NODE_ID" NUMBER(19,0) NOT NULL ENABLE,                                     
"BRCH_ID" NUMBER(19,0),                                                         
"PROC_RTE_NODE_INSTN_ID" NUMBER(19,0),                                          
"ACTV_IND" NUMBER(1,0) DEFAULT 0 NOT NULL ENABLE,                               
"CMPLT_IND" NUMBER(1,0) DEFAULT 0 NOT NULL ENABLE,                              
"INIT_IND" NUMBER(1,0) DEFAULT 0 NOT NULL ENABLE,                               
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RTE_NODE_INSTN_T_PK" PRIMARY KEY ("RTE_NODE_INSTN_ID") ENABLE    
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_RTE_NODE_LNK_T"                                                
(	"FROM_RTE_NODE_ID" NUMBER(19,0) NOT NULL ENABLE,                              
"TO_RTE_NODE_ID" NUMBER(19,0) NOT NULL ENABLE,                                  
CONSTRAINT "EN_RTE_NODE_LNK_T_PK" PRIMARY KEY ("FROM_RTE_NODE_ID",              
"TO_RTE_NODE_ID") ENABLE
   ) ;
                                                
                                                                                
CREATE TABLE "EN_RTE_NODE_T"                                                    
(	"RTE_NODE_ID" NUMBER(19,0) NOT NULL ENABLE,                                   
"DOC_TYP_ID" NUMBER(19,0),                                                      
"RTE_NODE_NM" VARCHAR2(255) NOT NULL ENABLE,                                    
"RTE_NODE_TYP" VARCHAR2(255) NOT NULL ENABLE,                                   
"DOC_RTE_MTHD_NM" VARCHAR2(255),                                                
"DOC_RTE_MTHD_CD" VARCHAR2(2),                                                  
"DOC_FNL_APRVR_IND" NUMBER(1,0),                                                
"DOC_MNDTRY_RTE_IND" NUMBER(1,0),                                               
"WRKGRP_ID" NUMBER(14,0),                                                       
"DOC_ACTVN_TYP_TXT" VARCHAR2(250),                                              
"BRCH_PROTO_ID" NUMBER(19,0),                                                   
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
"CONTENT_FRAGMENT" VARCHAR2(4000),                                              
CONSTRAINT "EN_RTE_NODE_T_PK" PRIMARY KEY ("RTE_NODE_ID") ENABLE
   ) ;
        
                                                                                
CREATE TABLE "EN_RULE_ATTRIB_KEY_VAL_T"                                         
(	"RULE_ATTRIB_KEY_VAL_ID" NUMBER(19,0) NOT NULL ENABLE,                        
"RULE_ATTRIB_ID" NUMBER(19,0) NOT NULL ENABLE,                                  
"RULE_ATTRIB_KEY" VARCHAR2(2000) NOT NULL ENABLE,                               
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RULE_ATTRIB_KEY_VAL_T" PRIMARY KEY ("RULE_ATTRIB_KEY_VAL_ID")    
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_RULE_ATTRIB_VLD_VAL_T"                                         
(	"RULE_ATTRIB_VLD_VAL_ID" NUMBER(19,0) NOT NULL ENABLE,                        
"RULE_ATTRIB_VLD_VAL_NM" VARCHAR2(2000) NOT NULL ENABLE,                        
"RULE_ATTRIB_VLD_VAL_LBL_TXT" VARCHAR2(2000) NOT NULL ENABLE,                   
"RULE_ATTRIB_ID" NUMBER(19,0) NOT NULL ENABLE,                                  
"RULE_ATTRIB_VLD_VAL_CUR_IND" NUMBER(1,0) DEFAULT 0,                            
"RULE_ATTRIB_VLD_VAL_VER_NBR" NUMBER(8,0) DEFAULT 0,                            
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RULE_ATTRIB_VLD_VAL_PK" PRIMARY KEY ("RULE_ATTRIB_VLD_VAL_ID")   
ENABLE                                                                          
) ;
                                                                            
                                                                                
CREATE TABLE "EN_RULE_BASE_VAL_T"                                               
(	"RULE_BASE_VAL_ID" NUMBER(19,0) NOT NULL ENABLE,                              
"RULE_NM" VARCHAR2(256),                                                        
"RULE_TMPL_ID" NUMBER(19,0),                                                    
"RULE_EXPR_ID" NUMBER(19,0),                                                    
"RULE_BASE_VAL_ACTV_IND" NUMBER(1,0) NOT NULL ENABLE,                           
"RULE_BASE_VAL_DESC" VARCHAR2(2000),                                            
"RULE_BASE_VAL_IGNR_PRVS" NUMBER(1,0) NOT NULL ENABLE,                          
"DOC_TYP_NM" VARCHAR2(2000) NOT NULL ENABLE,                                    
"DOC_HDR_ID" NUMBER(14,0),                                                      
"TMPL_RULE_IND" NUMBER(1,0),                                                    
"RULE_BASE_VAL_FRM_DT" DATE NOT NULL ENABLE,                                    
"RULE_BASE_VAL_TO_DT" DATE NOT NULL ENABLE,                                     
"RULE_BASE_VAL_DACTVN_DT" DATE,                                                 
"RULE_BASE_VAL_CUR_IND" NUMBER(1,0) DEFAULT 0,                                  
"RULE_BASE_VAL_VER_NBR" NUMBER(8,0) DEFAULT 0,                                  
"RULE_BASE_VAL_DLGN_IND" NUMBER(1,0),                                           
"RULE_BASE_VAL_PREV_VER" NUMBER(19,0),                                          
"RULE_BASE_VAL_ACTVN_DT" DATE,                                                  
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RULE_BASE_VAL_PK" PRIMARY KEY ("RULE_BASE_VAL_ID") ENABLE        
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_RULE_EXPR_T"                                                   
(	"RULE_EXPR_ID" NUMBER(19,0) NOT NULL ENABLE,                                  
"RULE_EXPR_TYP" VARCHAR2(256) NOT NULL ENABLE,                                  
"RULE_EXPR" VARCHAR2(4000),                                                     
CONSTRAINT "EN_RULE_EXPR_T_PK" PRIMARY KEY ("RULE_EXPR_ID") ENABLE
   ) ;
      
                                                                                
CREATE TABLE "EN_RULE_EXT_T"                                                    
(	"RULE_EXT_ID" NUMBER(19,0) NOT NULL ENABLE,                                   
"RULE_TMPL_ATTRIB_ID" NUMBER(19,0) NOT NULL ENABLE,                             
"RULE_BASE_VAL_ID" NUMBER(19,0) NOT NULL ENABLE,                                
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RULE_EXT_PK" PRIMARY KEY ("RULE_EXT_ID") ENABLE
   ) ;
          
                                                                                
CREATE TABLE "EN_RULE_EXT_VAL_T"                                                
(	"RULE_EXT_VAL_ID" NUMBER(19,0) NOT NULL ENABLE,                               
"RULE_EXT_ID" NUMBER(19,0) NOT NULL ENABLE,                                     
"RULE_EXT_VAL" VARCHAR2(2000) NOT NULL ENABLE,                                  
"RULE_EXT_VAL_KEY" VARCHAR2(2000) NOT NULL ENABLE,                              
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RULE_EXT_VAL_PK" PRIMARY KEY ("RULE_EXT_VAL_ID") ENABLE
   ) ;
  
                                                                                
CREATE TABLE "EN_RULE_RSP_T"                                                    
(	"RULE_RSP_ID" NUMBER(19,0) NOT NULL ENABLE,                                   
"RSP_ID" NUMBER(19,0) NOT NULL ENABLE,                                          
"RULE_BASE_VAL_ID" NUMBER(19,0) NOT NULL ENABLE,                                
"RULE_RSP_PRIO_NBR" NUMBER(5,0),                                                
"ACTION_RQST_CD" VARCHAR2(2000),                                                
"RULE_RSP_NM" VARCHAR2(200),                                                    
"RULE_RSP_TYP" VARCHAR2(1),                                                     
"RULE_RSP_APPR_PLCY" CHAR(1),                                                   
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RULE_RSP_PK" PRIMARY KEY ("RULE_RSP_ID") ENABLE                  
) ;
                                                                            
                                                                                
CREATE TABLE "EN_RULE_TMPL_ATTRIB_T"                                            
(	"RULE_TMPL_ATTRIB_ID" NUMBER(19,0) NOT NULL ENABLE,                           
"RULE_TMPL_ID" NUMBER(19,0) NOT NULL ENABLE,                                    
"RULE_ATTRIB_ID" NUMBER(19,0) NOT NULL ENABLE,                                  
"REQ_IND" NUMBER(1,0) NOT NULL ENABLE,                                          
"ACTV_IND" NUMBER(1,0) NOT NULL ENABLE,                                         
"DSPL_ORD" NUMBER(5,0) NOT NULL ENABLE,                                         
"DFLT_VAL" VARCHAR2(2000),                                                      
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RULE_TMPL_ATTRIB_PK" PRIMARY KEY ("RULE_TMPL_ATTRIB_ID") ENABLE  
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_RULE_TMPL_OPTN_T"                                              
(	"RULE_TMPL_OPTN_ID" NUMBER(19,0) NOT NULL ENABLE,                             
"RULE_TMPL_ID" NUMBER(19,0),                                                    
"RULE_TMPL_OPTN_KEY" VARCHAR2(250),                                             
"RULE_TMPL_OPTN_VAL" VARCHAR2(2000),                                            
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RULE_TMPL_OPTN_T_PK" PRIMARY KEY ("RULE_TMPL_OPTN_ID") ENABLE    
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_RULE_TMPL_T"                                                   
(	"RULE_TMPL_ID" NUMBER(19,0) NOT NULL ENABLE,                                  
"RULE_TMPL_NM" VARCHAR2(250) NOT NULL ENABLE,                                   
"RULE_TMPL_DESC" VARCHAR2(2000),                                                
"DLGN_RULE_TMPL_ID" NUMBER(19,0),                                               
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_RULE_TMPL_T_PK" PRIMARY KEY ("RULE_TMPL_ID") ENABLE
   ) ;
      
                                                                                
CREATE TABLE "EN_SERVICE_DEF_INTER_T"                                           
(	"SERVICE_DEF_INTER_ID" NUMBER(14,0) NOT NULL ENABLE,                          
"SERVICE_DEF_ID" NUMBER(14,0) NOT NULL ENABLE,                                  
"SERVICE_INTERFACE" VARCHAR2(500) NOT NULL ENABLE,                              
"DB_LOC_VER_NBR" NUMBER(8,0) DEFAULT 0,                                         
CONSTRAINT "EN_SERVICE_DEF_INTER_T_PK" PRIMARY KEY ("SERVICE_DEF_INTER_ID")     
ENABLE                                                                          
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "EN_SERVICE_DEF_T"                                                 
(	"SERVICE_DEF_ID" NUMBER(14,0) NOT NULL ENABLE,                                
"SERVICE_NM" VARCHAR2(255) NOT NULL ENABLE,                                     
"SERVICE_URL" VARCHAR2(500) NOT NULL ENABLE,                                    
"EXCEPTION_HANDLER" VARCHAR2(500),                                              
"ASYNC_QUEUE_IND" NUMBER(1,0),                                                  
"PRIORITY" NUMBER(8,0),                                                         
"RTRY_CNT" NUMBER(8,0),                                                         
"MILLIS_TO_LIVE" NUMBER(14,0),                                                  
"MESSAGE_ENTITY_NM" VARCHAR2(10) NOT NULL ENABLE,                               
"SERVER_IP" VARCHAR2(40) NOT NULL ENABLE,                                       
"SERVICE_ALIVE" NUMBER(1,0) NOT NULL ENABLE,                                    
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_SERVICE_DEF_T_PK" PRIMARY KEY ("SERVICE_DEF_ID") ENABLE
   ) ;
  
                                                                                
CREATE TABLE "EN_TRANSACTION_TST_ADDRS_T"                                       
(	"ADDRS_ID" NUMBER(19,0) NOT NULL ENABLE,                                      
"STREET" VARCHAR2(2000),                                                        
"CITY" VARCHAR2(2000),                                                          
"STATE" VARCHAR2(2),                                                            
"ZIP" NUMBER(8,0),                                                              
"DB_LOCK_VER_NBR" NUMBER(8,0) DEFAULT 0,                                        
CONSTRAINT "EN_TRANSACTION_TST_ADDRS_T_PK" PRIMARY KEY ("ADDRS_ID") ENABLE      
) ;
                                                                            
                                                                                
CREATE TABLE "APPOINTMENT_TYPE"                                                 
(	"APPOINTMENT_TYPE_CODE" VARCHAR2(3) CONSTRAINT "APPOINTMENT_TYPE_N1" NOT NULL 
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "APPOINTMENT_TYPE_N2" NOT NULL ENABLE,   
"DURATION" NUMBER(2,0) NOT NULL ENABLE,                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "APPOINTMENT_TYPE_N4" NOT NULL ENABLE,       
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "APPOINTMENT_TYPE_N5" NOT NULL ENABLE,     
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "APPOINTMENT_TYPE_N6" NOT NULL       
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "APPOINTMENT_TYPE_N7" NOT   
NULL ENABLE,                                                                    
CONSTRAINT "APPOINTMENT_TYPE_P1" PRIMARY KEY ("APPOINTMENT_TYPE_CODE") ENABLE   
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "RATE_CLASS_TYPE"                                                  
(	"RATE_CLASS_TYPE" CHAR(1) CONSTRAINT "RATE_CLASS_TYPEN1" NOT NULL ENABLE,     
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "RATE_CLASS_TYPEN2" NOT NULL ENABLE,     
"UPDATE_TIMESTAMP" DATE CONSTRAINT "RATE_CLASS_TYPEN3" NOT NULL ENABLE,         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "RATE_CLASS_TYPEN4" NOT NULL ENABLE,       
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "RATE_CLASS_TYPEN5" NOT NULL ENABLE, 
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "RATE_CLASS_TYPEN6" NOT NULL
ENABLE,                                                                         
CONSTRAINT "PK_RATE_CLASS_TYPE_KRA" PRIMARY KEY ("RATE_CLASS_TYPE") ENABLE      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "KNS_PESSIMISTIC_LOCK_T"                                           
(	"LOCK_ID" NUMBER(14,0) CONSTRAINT "KNS_PESSIMISTIC_LOCK_TN1" NOT NULL ENABLE, 
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "KNS_PESSIMISTIC_LOCK_TN2"  
NOT NULL ENABLE,                                                                
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "KNS_PESSIMISTIC_LOCK_TN3" NOT NULL  
ENABLE,                                                                         
"LOCK_DESCRIPTOR" VARCHAR2(4000),                                               
"FDOC_NBR" VARCHAR2(14) CONSTRAINT "KNS_PESSIMISTIC_LOCK_TN4" NOT NULL ENABLE,  
"LOCK_GENERATED_TS" DATE CONSTRAINT "KNS_PESSIMISTIC_LOCK_TN5" NOT NULL ENABLE, 
"PERSON_UNVL_ID" VARCHAR2(10) CONSTRAINT "KNS_PESSIMISTIC_LOCK_TN6" NOT NULL    
ENABLE,                                                                         
CONSTRAINT "KNS_PESSIMISTIC_LOCK_TP1" PRIMARY KEY ("LOCK_ID") ENABLE,           
CONSTRAINT "KNS_PESSIMISTIC_LOCK_TC0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
          
                                                                                
CREATE TABLE "DOCUMENT_NEXTVALUE"                                               
(	"DOCUMENT_NUMBER" NUMBER(12,0) CONSTRAINT "DOCUMENT_NEXTVALUE_N1" NOT NULL    
ENABLE,                                                                         
"PROPERTY_NAME" VARCHAR2(200) CONSTRAINT "DOCUMENT_NEXTVALUE_N2" NOT NULL       
ENABLE,                                                                         
"NEXT_VALUE" NUMBER(12,0) CONSTRAINT "DOCUMENT_NEXTVALUE_N3" NOT NULL ENABLE,   
"UPDATE_TIMESTAMP" DATE CONSTRAINT "DOCUMENT_NEXTVALUE_N4" NOT NULL ENABLE,     
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "DOCUMENT_NEXTVALUE_N5" NOT NULL ENABLE,   
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "DOCUMENT_NEXTVALUE_N6" NOT NULL     
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "DOCUMENT_NEXTVALUE_N7" NOT 
NULL ENABLE,                                                                    
CONSTRAINT "PK_DOCUMENT_NEXTVALUE" PRIMARY KEY ("DOCUMENT_NUMBER",              
"PROPERTY_NAME") ENABLE                                                         
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "CUSTOM_ATTRIBUTE"                                                 
(	"ID" NUMBER(12,0) CONSTRAINT "CUSTOM_ATTRIBUTE_N1" NOT NULL ENABLE,           
"NAME" VARCHAR2(30) CONSTRAINT "CUSTOM_ATTRIBUTE_N2" NOT NULL ENABLE,           
"LABEL" VARCHAR2(30) CONSTRAINT "CUSTOM_ATTRIBUTE_N3" NOT NULL ENABLE,          
"DATA_TYPE_CODE" VARCHAR2(3) CONSTRAINT "CUSTOM_ATTRIBUTE_N4" NOT NULL ENABLE,  
"DATA_LENGTH" NUMBER(4,0),                                                      
"DEFAULT_VALUE" VARCHAR2(2000),                                                 
"LOOKUP_CLASS" VARCHAR2(100),                                                   
"LOOKUP_RETURN" VARCHAR2(30),                                                   
"GROUP_NAME" VARCHAR2(250),                                                     
"UPDATE_TIMESTAMP" DATE CONSTRAINT "CUSTOM_ATTRIBUTE_N5" NOT NULL ENABLE,       
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "CUSTOM_ATTRIBUTE_N6" NOT NULL ENABLE,     
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "CUSTOM_ATTRIBUTE_N7" NOT NULL       
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "CUSTOM_ATTRIBUTE_N8" NOT   
NULL ENABLE,                                                                    
CONSTRAINT "CUSTOM_ATTRIBUTE_P1" PRIMARY KEY ("ID") ENABLE,                     
CONSTRAINT "CUSTOM_ATTRIBUTE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
               
                                                                                
CREATE TABLE "CUSTOM_ATTRIBUTE_DATA_TYPE"                                       
(	"DATA_TYPE_CODE" VARCHAR2(3) CONSTRAINT "CUSTOM_ATTRIBUTE_DATA_TYPE_N1" NOT   
NULL ENABLE,                                                                    
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "CUSTOM_ATTRIBUTE_DATA_TYPE_N2" NOT NULL 
ENABLE,                                                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "CUSTOM_ATTRIBUTE_DATA_TYPE_N3" NOT NULL     
ENABLE,                                                                         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "CUSTOM_ATTRIBUTE_DATA_TYPE_N4" NOT NULL   
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "CUSTOM_ATTRIBUTE_DATA_TYPE_N5" NOT  
NULL ENABLE,                                                                    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT                             
"CUSTOM_ATTRIBUTE_DATA_TYPE_N6" NOT NULL ENABLE,                                
CONSTRAINT "CUSTOM_ATTRIBUTE_DATA_TYPE_P1" PRIMARY KEY ("DATA_TYPE_CODE")       
ENABLE,                                                                         
CONSTRAINT "CUSTOM_ATTRIBUTE_DATA_TYPE_C0" UNIQUE ("OBJ_ID") ENABLE             
) ;
                                                                            
                                                                                
CREATE TABLE "CUSTOM_ATTRIBUTE_DOC_VALUE"                                       
(	"DOCUMENT_NUMBER" NUMBER(10,0) CONSTRAINT "CUSTOM_ATTRIBUTE_DOC_VALUE_N1" NOT 
NULL ENABLE,                                                                    
"CUSTOM_ATTRIBUTE_ID" NUMBER(12,0) CONSTRAINT "CUSTOM_ATTRIBUTE_DOC_VALUE_N2"   
NOT NULL ENABLE,                                                                
"VALUE" VARCHAR2(2000),                                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "CUSTOM_ATTRIBUTE_DOC_VALUE_N3" NOT NULL     
ENABLE,                                                                         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "CUSTOM_ATTRIBUTE_DOC_VALUE_N4" NOT NULL   
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "CUSTOM_ATTRIBUTE_DOC_VALUE_N5" NOT  
NULL ENABLE,                                                                    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT                             
"CUSTOM_ATTRIBUTE_DOC_VALUE_N6" NOT NULL ENABLE,                                
CONSTRAINT "CUSTOM_ATTRIBUTE_DOC_VALUE_P1" PRIMARY KEY ("DOCUMENT_NUMBER",      
"CUSTOM_ATTRIBUTE_ID") ENABLE,                                                  
CONSTRAINT "CUSTOM_ATTRIBUTE_DOC_VALUE_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
     
                                                                                
CREATE TABLE "CUSTOM_ATTRIBUTE_DOCUMENT"                                        
(	"DOCUMENT_TYPE_CODE" VARCHAR2(4) CONSTRAINT "CUSTOM_ATTRIBUTE_DOCUMENT_N1" NOT
NULL ENABLE,                                                                    
"CUSTOM_ATTRIBUTE_ID" NUMBER(12,0) CONSTRAINT "CUSTOM_ATTRIBUTE_DOCUMENT_N2" NOT
NULL ENABLE,                                                                    
"TYPE_NAME" VARCHAR2(100),                                                      
"IS_REQUIRED" CHAR(1),                                                          
"UPDATE_TIMESTAMP" DATE CONSTRAINT "CUSTOM_ATTRIBUTE_DOCUMENT_N3" NOT NULL      
ENABLE,                                                                         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "CUSTOM_ATTRIBUTE_DOCUMENT_N4" NOT NULL    
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "CUSTOM_ATTRIBUTE_DOCUMENT_N5" NOT   
NULL ENABLE,                                                                    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT                             
"CUSTOM_ATTRIBUTE_DOCUMENT_N6" NOT NULL ENABLE,                                 
CONSTRAINT "CUSTOM_ATTRIBUTE_DOCUMENT_P1" PRIMARY KEY ("DOCUMENT_TYPE_CODE",    
"CUSTOM_ATTRIBUTE_ID") ENABLE,                                                  
CONSTRAINT "CUSTOM_ATTRIBUTE_DOCUMENT_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
      
                                                                                
CREATE TABLE "BUDGET_PROJECT_INCOMES"                                           
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "BUDGET_PROJECT_INCOME_PN" NOT NULL 
ENABLE,                                                                         
"BUDGET_VERSION_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_PROJECT_INCOME_BV" NOT   
NULL ENABLE,                                                                    
"BUDGET_PERIOD_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_PROJECT_INCOME_BP" NOT    
NULL ENABLE,                                                                    
"PROJECT_INCOME" NUMBER(19,2) CONSTRAINT "BUDGET_PROJECT_INCOME_PI" NOT NULL    
ENABLE,                                                                         
"DESCRIPTION" VARCHAR2(200) CONSTRAINT "BUDGET_PROJECT_INCOME_DE" NOT NULL      
ENABLE,                                                                         
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_PROJECT_INCOME_UT" NOT NULL ENABLE,  
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_PROJECT_INCOME_UU" NOT NULL ENABLE,
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_PROJECT_INCOME_VN" NOT NULL  
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "BUDGET_PROJECT_INCOME_OI"  
NOT NULL ENABLE,                                                                
CONSTRAINT "PK_BUDGET_PROJECT_INCOME_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",       
"BUDGET_VERSION_NUMBER", "BUDGET_PERIOD_NUMBER", "PROJECT_INCOME",              
"DESCRIPTION") ENABLE                                                           
   ) ;
                                                                         
                                                                                
CREATE TABLE "EPS_PROPOSAL_STATUS"                                              
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "EPS_PROPOSAL_STATUS_N1" NOT NULL   
ENABLE,                                                                         
"BUDGET_STATUS_CODE" CHAR(1),                                                   
"UPDATE_TIMESTAMP" DATE CONSTRAINT "EPS_PROPOSAL_STATUS_N2" NOT NULL ENABLE,    
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "EPS_PROPOSAL__STATUS_N3" NOT NULL ENABLE, 
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "EPS_PROPOSAL_STATUS_N4" NOT NULL    
ENABLE,                                                                         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "EPS_PROPOSAL_STATUS_N5" NOT
NULL ENABLE,                                                                    
CONSTRAINT "EPS_PROPOSAL_STATUS_P1" PRIMARY KEY ("PROPOSAL_NUMBER") ENABLE,     
CONSTRAINT "EPS_PROPOSAL_STATUS_C0" UNIQUE ("OBJ_ID") ENABLE
   ) ;
            
                                                                                
CREATE TABLE "BUDGET_PERIODS"                                                   
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "BUDGET_PERIODSN1" NOT NULL ENABLE, 
"VERSION_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_PERIODSN2" NOT NULL ENABLE,     
"BUDGET_PERIOD" NUMBER(3,0) CONSTRAINT "BUDGET_PERIODSN3" NOT NULL ENABLE,      
"START_DATE" DATE CONSTRAINT "BUDGET_PERIODSN4" NOT NULL ENABLE,                
"END_DATE" DATE CONSTRAINT "BUDGET_PERIODSN5" NOT NULL ENABLE,                  
"TOTAL_COST" NUMBER(12,2),                                                      
"TOTAL_DIRECT_COST" NUMBER(12,2),                                               
"TOTAL_INDIRECT_COST" NUMBER(12,2),                                             
"COST_SHARING_AMOUNT" NUMBER(12,2),                                             
"UNDERRECOVERY_AMOUNT" NUMBER(12,2),                                            
"TOTAL_COST_LIMIT" NUMBER(12,2),                                                
"COMMENTS" CLOB,                                                                
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_PERIODSN6" NOT NULL ENABLE,          
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_PERIODSN7" NOT NULL ENABLE,        
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_PERIODSN8" NOT NULL ENABLE,  
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "BUDGET_PERIODSN9" NOT NULL 
ENABLE,                                                                         
CONSTRAINT "PK_BUDGET_PERIODS_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",              
"VERSION_NUMBER", "BUDGET_PERIOD") ENABLE
   ) ;
                               
                                                                                
CREATE TABLE "BUDGET_PERSONNEL_DETAILS"                                         
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "BUDGET_PERSONNEL_DETAILSN1" NOT    
NULL ENABLE,                                                                    
"VERSION_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_PERSONNEL_DETAILSN2" NOT NULL   
ENABLE,                                                                         
"BUDGET_PERIOD" NUMBER(3,0) CONSTRAINT "BUDGET_PERSONNEL_DETAILSN3" NOT NULL    
ENABLE,                                                                         
"LINE_ITEM_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_PERSONNEL_DETAILSN4" NOT NULL 
ENABLE,                                                                         
"PERSON_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_PERSONNEL_DETAILSN5" NOT NULL    
ENABLE,                                                                         
"PERSON_ID" VARCHAR2(9) CONSTRAINT "BUDGET_PERSONNEL_DETAILSN6" NOT NULL ENABLE,
"JOB_CODE" VARCHAR2(6) CONSTRAINT "BUDGET_PERSONNEL_DETAILSN7" NOT NULL ENABLE, 
"START_DATE" DATE CONSTRAINT "BUDGET_PERSONNEL_DETAILSN8" NOT NULL ENABLE,      
"END_DATE" DATE CONSTRAINT "BUDGET_PERSONNEL_DETAILSN9" NOT NULL ENABLE,        
"PERIOD_TYPE" VARCHAR2(2),                                                      
"LINE_ITEM_DESCRIPTION" VARCHAR2(80),                                           
"SEQUENCE_NUMBER" NUMBER(3,0),                                                  
"SALARY_REQUESTED" NUMBER(12,2),                                                
"PERCENT_CHARGED" NUMBER(5,2),                                                  
"PERCENT_EFFORT" NUMBER(5,2),                                                   
"COST_SHARING_PERCENT" NUMBER(5,2),                                             
"COST_SHARING_AMOUNT" NUMBER(12,2),                                             
"UNDERRECOVERY_AMOUNT" NUMBER(12,2),                                            
"ON_OFF_CAMPUS_FLAG" CHAR(1) CONSTRAINT "BUDGET_PERSONNEL_DETAILSN10" NOT NULL  
ENABLE,                                                                         
"APPLY_IN_RATE_FLAG" CHAR(1) CONSTRAINT "BUDGET_PERSONNEL_DETAILSN11" NOT NULL  
ENABLE,                                                                         
"BUDGET_JUSTIFICATION" CLOB,                                                    
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_PERSONNEL_DETAILSN12" NOT NULL       
ENABLE,                                                                         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_PERSONNEL_DETAILSN13" NOT NULL     
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_PERSONNEL_DETAILSN14" NOT    
NULL ENABLE,                                                                    
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT                             
"BUDGET_PERSONNEL_DETAILSN15" NOT NULL ENABLE,                                  
"PERSON_SEQUENCE_NUMBER" NUMBER(3,0),                                           
CONSTRAINT "PK_BUDGET_PERSONNEL_DET_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",        
"VERSION_NUMBER", "BUDGET_PERIOD", "LINE_ITEM_NUMBER", "PERSON_NUMBER") ENABLE  
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "BUDGET_DETAILS"                                                   
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "BUDGET_DETAILSN1" NOT NULL ENABLE, 
"VERSION_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_DETAILSN2" NOT NULL ENABLE,     
"BUDGET_PERIOD" NUMBER(3,0) CONSTRAINT "BUDGET_DETAILSN3" NOT NULL ENABLE,      
"LINE_ITEM_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGET_DETAILSN4" NOT NULL ENABLE,   
"BUDGET_CATEGORY_CODE" NUMBER(3,0) CONSTRAINT "BUDGET_DETAILSN5" NOT NULL       
ENABLE,                                                                         
"COST_ELEMENT" VARCHAR2(8) CONSTRAINT "BUDGET_DETAILSN6" NOT NULL ENABLE,       
"LINE_ITEM_DESCRIPTION" VARCHAR2(80),                                           
"BASED_ON_LINE_ITEM" NUMBER(3,0),                                               
"LINE_ITEM_SEQUENCE" NUMBER(3,0),                                               
"START_DATE" DATE CONSTRAINT "BUDGET_DETAILSN7" NOT NULL ENABLE,                
"END_DATE" DATE CONSTRAINT "BUDGET_DETAILSN8" NOT NULL ENABLE,                  
"LINE_ITEM_COST" NUMBER(12,2),                                                  
"COST_SHARING_AMOUNT" NUMBER(12,2),                                             
"UNDERRECOVERY_AMOUNT" NUMBER(12,2),                                            
"ON_OFF_CAMPUS_FLAG" CHAR(1) CONSTRAINT "BUDGET_DETAILSN9" NOT NULL ENABLE,     
"APPLY_IN_RATE_FLAG" CHAR(1) CONSTRAINT "BUDGET_DETAILSN10" NOT NULL ENABLE,    
"BUDGET_JUSTIFICATION" CLOB,                                                    
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGET_DETAILSN11" NOT NULL ENABLE,         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET_DETAILSN12" NOT NULL ENABLE,       
"QUANTITY" NUMBER(4,0),                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGET_DETAILSN13" NOT NULL ENABLE, 
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "BUDGET_DETAILSN14" NOT NULL
ENABLE,                                                                         
CONSTRAINT "PK_BUDGET_DETAILS_KRA" PRIMARY KEY ("PROPOSAL_NUMBER",              
"VERSION_NUMBER", "BUDGET_PERIOD", "LINE_ITEM_NUMBER") ENABLE                   
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "YNQ_EXPLANATION"                                                  
(	"QUESTION_ID" VARCHAR2(4) CONSTRAINT "YNQ_EXPLANATIONN1" NOT NULL ENABLE,     
"EXPLANATION_TYPE" CHAR(1) CONSTRAINT "YNQ_EXPLANATIONN2" NOT NULL ENABLE,      
"EXPLANATION" CLOB,                                                             
"UPDATE_TIMESTAMP" DATE CONSTRAINT "YNQ_EXPLANATIONN3" NOT NULL ENABLE,         
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "YNQ_EXPLANATIONN4" NOT NULL ENABLE,       
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "YNQ_EXPLANATIONN5" NOT NULL ENABLE, 
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "YNQ_EXPLANATIONN6" NOT NULL
ENABLE,                                                                         
CONSTRAINT "PK_YNQ_EXPLANATION_KRA" PRIMARY KEY ("QUESTION_ID",                 
"EXPLANATION_TYPE") ENABLE                                                      
) ;                                                                             
                                                                                
                                                                                
CREATE TABLE "BUDGET"                                                           
(	"PROPOSAL_NUMBER" VARCHAR2(12) CONSTRAINT "BUDGETN1" NOT NULL ENABLE,         
"VERSION_NUMBER" NUMBER(3,0) CONSTRAINT "BUDGETN2" NOT NULL ENABLE,             
"DOCUMENT_NUMBER" NUMBER(10,0) CONSTRAINT "BUDGETN3" NOT NULL ENABLE,           
"START_DATE" DATE CONSTRAINT "BUDGETN4" NOT NULL ENABLE,                        
"END_DATE" DATE CONSTRAINT "BUDGETN5" NOT NULL ENABLE,                          
"TOTAL_COST" NUMBER(12,2),                                                      
"TOTAL_DIRECT_COST" NUMBER(12,2),                                               
"TOTAL_INDIRECT_COST" NUMBER(12,2),                                             
"COST_SHARING_AMOUNT" NUMBER(12,2),                                             
"UNDERRECOVERY_AMOUNT" NUMBER(12,2),                                            
"RESIDUAL_FUNDS" NUMBER(12,2),                                                  
"TOTAL_COST_LIMIT" NUMBER(12,2),                                                
"OH_RATE_CLASS_CODE" VARCHAR2(3) CONSTRAINT "BUDGETN6" NOT NULL ENABLE,         
"OH_RATE_TYPE_CODE" VARCHAR2(3),                                                
"COMMENTS" CLOB,                                                                
"FINAL_VERSION_FLAG" CHAR(1),                                                   
"UPDATE_TIMESTAMP" DATE CONSTRAINT "BUDGETN7" NOT NULL ENABLE,                  
"UPDATE_USER" VARCHAR2(8) CONSTRAINT "BUDGET87" NOT NULL ENABLE,                
"UR_RATE_CLASS_CODE" VARCHAR2(3) DEFAULT 1 CONSTRAINT "BUDGETN9" NOT NULL       
ENABLE,                                                                         
"MODULAR_BUDGET_FLAG" VARCHAR2(1) DEFAULT 'N' CONSTRAINT "BUDGETN10" NOT NULL   
ENABLE,                                                                         
"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "BUDGETN11" NOT NULL ENABLE,         
"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT "BUDGETN12" NOT NULL ENABLE,
CONSTRAINT "PK_BUDGET_KRA" PRIMARY KEY ("PROPOSAL_NUMBER", "VERSION_NUMBER")    
ENABLE
   ) ;
                                                                  

