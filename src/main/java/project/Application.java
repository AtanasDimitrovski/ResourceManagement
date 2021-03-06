package project;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class Application //extends SpringBootServletInitializer implements WebApplicationInitializer 
{
	
	
/*	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
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
	}*/
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	
}
