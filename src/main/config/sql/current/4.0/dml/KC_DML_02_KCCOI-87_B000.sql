UPDATE COI_DISCLOSURE_EVENT_TYPE SET ACTIVE_FLAG = 'Y'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE 
    SET USE_LNG_TXT_FLD_1 = 'Y', 
        REQ_LNG_TXT_FLD_1 = 'Y', 
        LNG_TXT_FLD_1_LABEL = 'Project Title',
        USE_SHRT_TXT_FLD_1 = 'Y',
        REQ_SHRT_TXT_FLD_1 = 'Y',
        SHRT_TXT_FLD_1_LABEL = 'Project Id',
        USE_SHRT_TXT_FLD_2 = 'Y',
        REQ_SHRT_TXT_FLD_2 = 'Y',
        SHRT_TXT_FLD_2_LABEL = 'Project Role',
        USE_LNG_TXT_FLD_2 = 'Y',
        REQ_LNG_TXT_FLD_2 = 'Y',
        LNG_TXT_FLD_2_LABEL = 'Sponsor',
        USE_NMBR_FLD_1 = 'Y',
        REQ_NMBR_FLD_1 = 'Y',
        NMBR_FLD_1_LABEL = 'Project Funding Amount',
        USE_DATE_FLD_1 = 'Y',
        REQ_DATE_FLD_1 = 'Y',
        DATE_FLD_1_LABEL = 'Project Start Date',
        USE_DATE_FLD_2 = 'Y',
        REQ_DATE_FLD_2 = 'Y',
        DATE_FLD_2_LABEL = 'Project End Date'
    WHERE DESCRIPTION = 'Manual Proposal'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE 
    SET USE_LNG_TXT_FLD_1 = 'Y', 
        REQ_LNG_TXT_FLD_1 = 'Y', 
        LNG_TXT_FLD_1_LABEL = 'Award Title',
        USE_SHRT_TXT_FLD_1 = 'Y',
        REQ_SHRT_TXT_FLD_1 = 'Y',
        SHRT_TXT_FLD_1_LABEL = 'Award Number',
        USE_DATE_FLD_1 = 'Y',
        REQ_DATE_FLD_1 = 'Y',
        DATE_FLD_1_LABEL = 'Award Date'
    WHERE DESCRIPTION = 'Manual Award'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE 
    SET USE_LNG_TXT_FLD_1 = 'Y', 
        REQ_LNG_TXT_FLD_1 = 'Y', 
        LNG_TXT_FLD_1_LABEL = 'Protocol Name',
        USE_SHRT_TXT_FLD_1 = 'Y',
        REQ_SHRT_TXT_FLD_1 = 'Y',
        SHRT_TXT_FLD_1_LABEL = 'Protocol Number'
    WHERE DESCRIPTION = 'Manual IRB Protocol'
/
