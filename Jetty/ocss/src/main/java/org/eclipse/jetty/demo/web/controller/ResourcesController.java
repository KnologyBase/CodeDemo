package org.eclipse.jetty.demo.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourcesController implements IGTVGController{
    private static Logger logger=LoggerFactory.getLogger(ResourcesController.class);
	private String contentType; 
    public ResourcesController(String contentType) {
        super();               
        this.contentType=contentType;
         logger.info("ResourcesController Init............");    

    }


    public String  getContentType(){ 


        return this.contentType;}
        
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext,
			ITemplateEngine templateEngine) throws Exception {

             String resource_location=   getRequestPath(request);

             logger.info("ResourcesController Find Resource ............"  + resource_location);    
                 try{
                    System.out.println("Load Resource:"+resource_location);
                    ResourcesController.class.getResourceAsStream(resource_location).transferTo(response.getOutputStream());
                }  catch(Exception ex){
                    ex.printStackTrace();
                }
    }
    private static String getRequestPath(final HttpServletRequest request) {

        String requestURI = request.getRequestURI();
        final String contextPath = request.getContextPath();

        final int fragmentIndex = requestURI.indexOf(';');


        if (fragmentIndex != -1) {
                requestURI = requestURI.substring(0, fragmentIndex);
        }

        if (requestURI.startsWith(contextPath)) {
                return requestURI.substring(contextPath.length());
        }
        return requestURI;
}

}