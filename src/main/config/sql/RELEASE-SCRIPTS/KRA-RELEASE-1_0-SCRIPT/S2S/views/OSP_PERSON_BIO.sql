
CREATE OR REPLACE VIEW OSP$PERSON_BIO ( 
	   	  PERSON_ID,
         BIO_NUMBER,
         DESCRIPTION,
         UPDATE_TIMESTAMP,
         UPDATE_USER,
   		PDF_FLAG_BORDER,   -- For setting border of pdf flag in datawindows (6=3D raised)
   		SOURCE_FLAG_BORDER, -- For setting border of source flag in datawindows (6=3D raised)
		PDF_BIO_NUMBER,
		SOURCE_BIO_NUMBER ) AS select null,null,null,null,null,null,null,null,null
	from dual;
