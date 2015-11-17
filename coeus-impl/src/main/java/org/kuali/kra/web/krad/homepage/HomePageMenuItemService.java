package org.kuali.kra.web.krad.homepage;

import java.util.List;

import org.kuali.kra.web.krad.homepage.HomePageMenuItemServiceImpl.HomePageItemSuggestion;

public interface HomePageMenuItemService {

	List<HomePageItemSuggestion> getActiveMenuItems();

}