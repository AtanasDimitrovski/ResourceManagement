package project;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// TODO Auto-generated method stub
		// creates the web app context
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(Application.class);

        // registers context load listener
        servletContext.addListener(new ContextLoaderListener(new AnnotationConfigWebApplicationContext()));

        // adds a dispatch servlet, the servlet will be configured from the created application context
        ServletRegistration.Dynamic servletConfig = servletContext.addServlet("employee",
                new DispatcherServlet(webContext));
        servletConfig.setLoadOnStartup(1);
        //servletConfig.addMapping("*.htm");
	}
	
}
