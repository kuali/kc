DELIMITER /
UPDATE COI_DISCLOSURE_EVENT_TYPE SET ACTIVE_FLAG = 'Y'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE 
    SET USE_LNG_TXT_FLD_1 = 'Y', 
        REQ_LNG_TXT_FLD_1 = 'Y', 
        LNG_TXT_FLD_1_LABEL = 'Sponsor',
        USE_SHRT_TXT_FLD_1 = 'Y',
        REQ_SHRT_TXT_FLD_1 = 'Y',
        SHRT_TXT_FLD_1_LABEL = 'Project Role',
        USE_NMBR_FLD_1 = 'Y',
        REQ_NMBR_FLD_1 = 'Y',
        NMBR_FLD_1_LABEL = 'Project Funding Amount',
        USE_DATE_FLD_1 = 'Y',
        REQ_DATE_FLD_1 = 'Y',
        DATE_FLD_1_LABEL = 'Project Start Date',
        USE_DATE_FLD_2 = 'Y',
        REQ_DATE_FLD_2 = 'Y',
        DATE_FLD_2_LABEL = 'Project End Date',
        USE_SLCT_BOX_1 = 'Y',
        REQ_SLCT_BOX_1 = 'Y',
        SLCT_BOX_1_LABEL = 'Project Type',
        SLCT_BOX_1_VAL_FNDR = 'org.kuali.kra.coi.lookup.keyvalue.CoiDisclosureProjectsProjectTypeValuesFinder',
        PROJECT_ID_LABEL = 'Project Id',
        PROJECT_TITLE_LABEL = 'Project Title'
    WHERE DESCRIPTION = 'Manual Proposal'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE 
    SET PROJECT_ID_LABEL = 'Award Number',
        PROJECT_TITLE_LABEL = 'Award Title',
        USE_DATE_FLD_1 = 'Y',
        REQ_DATE_FLD_1 = 'Y',
        DATE_FLD_1_LABEL = 'Award Date'
    WHERE DESCRIPTION = 'Manual Award'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE 
    SET PROJECT_ID_LABEL = 'Protocol Number',
        PROJECT_TITLE_LABEL = 'Protocol Name',
        USE_SLCT_BOX_1 = 'Y',
        REQ_SLCT_BOX_1 = 'Y',
        SLCT_BOX_1_LABEL = 'Protocol Type',
        SLCT_BOX_1_VAL_FNDR = 'org.kuali.kra.irb.protocol.ProtocolTypeValuesFinder'            
    WHERE DESCRIPTION = 'Manual IRB Protocol'
/
DELIMITER ;
