package org.kuali.coeus.common.framework.custom;

import java.util.Comparator;

import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;

   /**  Add Sort ID to Custom Attribute Document Maintenance Document */
   /**
    *
    * 1. If SORT_IDs for all the custom data fields have been defined, then display them based on the 
    *    SORT_ID in ascending order from the top to the bottom under the Full Group Name: *** sub panel
    *    
    * 2. If none of the custom data fields has SORT_ID defined, then display them alphabetically
    * 
    * 3. If some of the custom data fields have SORT_ID defined while the rest do not, then display all of the fields with a SORT_ID first, 
    *    organized by sort ID. Then list the ones without a SORT_ID in alphabetical order.
    *
    */
   public class CustomAttributeSortIdComparator implements Comparator<CustomAttributeDocument> {
	   
	   private static final int MAX_CUSTOM_DATA_SORT_ID = 999999;
	   
	   public int compare(CustomAttributeDocument cad1, CustomAttributeDocument cad2) {
		   try {
			   String label1 = cad1.getCustomAttribute().getLabel();
	           String label2 = cad2.getCustomAttribute().getLabel();
	           if (label1 == null) {
	               label1 = "";
	           }
	           if (label2 == null) {
	               label2 = "";
	           }
			   
	           if(cad1.getSortId() == null) {
				   cad1.setSortId(new Integer(MAX_CUSTOM_DATA_SORT_ID));
			   }
			   if(cad2.getSortId() == null) {
				   cad2.setSortId(new Integer(MAX_CUSTOM_DATA_SORT_ID));
			   }
	
			   int sortIdComp = cad1.getSortId().compareTo(cad2.getSortId());
			   return ((sortIdComp == 0) ? label1.compareTo(label2) : sortIdComp);
		   }
           catch (Exception e) {
               return 0;
           }
	   }
   }

