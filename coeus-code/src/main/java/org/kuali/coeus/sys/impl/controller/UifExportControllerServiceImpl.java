package org.kuali.coeus.sys.impl.controller;

import org.kuali.coeus.sys.framework.controller.UifExportControllerService;
import org.kuali.rice.krad.web.controller.UifExportController;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Properties;

/** this service must @Override methods and call super in order to elevate a method to public to satisfy the interface. */
@Component("uifExportControllerService")
public class UifExportControllerServiceImpl extends UifExportController implements UifExportControllerService {

}
