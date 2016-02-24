/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb.actions.submit;

import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The implementation of the Check List Service.
 */
public class CheckListServiceImpl implements CheckListService {

    private BusinessObjectService businessObjectService;
    
    /**
     * Inject the Business Object Service.
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<ExpeditedReviewCheckListItem> getExpeditedReviewCheckList() { 
        Collection<ExpeditedReviewCheckListItem> items = businessObjectService.findAll(ExpeditedReviewCheckListItem.class);
        List<ExpeditedReviewCheckListItem> checkList = new ArrayList<ExpeditedReviewCheckListItem>();
        checkList.addAll(items);
        return checkList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ExemptStudiesCheckListItem> getExemptStudiesCheckList() {
        Collection<ExemptStudiesCheckListItem> items = businessObjectService.findAll(ExemptStudiesCheckListItem.class);
        List<ExemptStudiesCheckListItem> checkList = new ArrayList<ExemptStudiesCheckListItem>();
        checkList.addAll(items);
        return checkList;
    }
}
