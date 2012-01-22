/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.web.servlet;


public class KraDWRServlet { 

// extends KualiDWRServlet {
// 
//    /**
//     * Comment for <code>serialVersionUID</code>
//     */
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    public void configure(ServletConfig servletConfig, Configuration configuration) throws ServletException {
//        super.configure(servletConfig, configuration);
//
//        for (Object namedOrderedListBean : KraServiceLocator.getAppContext().getBeansOfType(NamedOrderedListBean.class).values()) {
//            if (((NamedOrderedListBean) namedOrderedListBean).getName().equals("scriptConfigurationFilePaths")) {
//                for (String scriptConfigurationFilePath : ((NamedOrderedListBean) namedOrderedListBean).getList()) {
//                    if (getSpringBasedConfigPath()) {
//                        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
//                        try {
//                            InputStream is = resourceLoader.getResource(scriptConfigurationFilePath).getInputStream();
//                            configuration.addConfig(is);
//                        }
//                        catch (Exception e) {
//                            throw new ServletException(e);
//                        }
//                    }
//                    else {
//                        super.readFile("target/classes/" + scriptConfigurationFilePath, configuration);
//                    }
//                }
//            }
//        }
//    }
//    
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        // TODO Auto-generated method stub
//        UserSession userSession = null;
//        boolean sessionexist = req.getSession(false) != null && req.getSession(false).getAttribute(KRADConstants.USER_SESSION_KEY) != null;
//        if (sessionexist && GlobalVariables.getUserSession() == null) {
//            userSession = (UserSession) req.getSession().getAttribute(KRADConstants.USER_SESSION_KEY);
//            GlobalVariables.setUserSession(userSession);
//        }
//        super.doPost(req, resp);
//    }
    
}
