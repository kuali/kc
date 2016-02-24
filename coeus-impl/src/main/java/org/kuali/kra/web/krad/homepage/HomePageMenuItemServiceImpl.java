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
package org.kuali.kra.web.krad.homepage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.criteria.QueryResults;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("homePageMenuItemService")
public class HomePageMenuItemServiceImpl implements HomePageMenuItemService {
	@Autowired
	@Qualifier("dataObjectService")
	private DataObjectService dataObjectService;

	@Autowired
	@Qualifier("kualiConfigurationService")
	private ConfigurationService configurationService;

	protected static final String APP_URL_TOKEN = "<<APPLICATION_URL>>";

	@Override
	public List<HomePageItemSuggestion> getActiveMenuItems() {

		String appUrl = configurationService.getPropertyValueAsString(KRADConstants.APPLICATION_URL_KEY);

		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("active", "Y");

		QueryResults<HomePageMenuItem> menuItems = this.getDataObjectService().findMatching(HomePageMenuItem.class,
				QueryByCriteria.Builder.andAttributes(criteria).build());

		return menuItems.getResults().stream().map(menuItem -> new HomePageItemSuggestion(menuItem, appUrl))
				.collect(Collectors.toList());
	}

	public class HomePageItemSuggestion {
		private String label;
		private String value;
		private String href;

		public HomePageItemSuggestion(HomePageMenuItem item, String appUrl) {
			this.setLabel(item.getMenuItemFormatted());
			this.setValue(item.getMenuItem());

			String href;
			try {
				href = appUrl + item.getMenuAction().replace(APP_URL_TOKEN, URLEncoder.encode(appUrl, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
			this.setHref(href);
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}
	}

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
}
