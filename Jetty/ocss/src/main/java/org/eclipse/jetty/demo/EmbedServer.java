//
//  ========================================================================
//  Copyright (c) 1995-2016 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.demo;




import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.websocket.DeploymentException;

import org.eclipse.jetty.demo.web.GTVGApplication;
import org.eclipse.jetty.demo.web.filter.GTVGFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.handler.ContextHandler;  
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer.Configurator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;



public class EmbedServer
{
        public static void main(String[] args)
        {
                Server server = new Server();
                ServerConnector connector = new ServerConnector(server);


                connector.setPort(8080);
                server.addConnector(connector);

                try
                {

 
                        ServletContextHandler admin_context = new ServletContextHandler(ServletContextHandler.SESSIONS);
                        admin_context.setContextPath("/admin");
                        admin_context.addFilter(GTVGFilter.class, "/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
                         System.out.println("Set Context Path '"+admin_context.getContextPath()+"*"+"'");
                         
                          ServletContextHandler ws_context = new ServletContextHandler(ServletContextHandler.SESSIONS);

                          ws_context.setContextPath("/services/");  


                          ServletHolder wsHolder = new ServletHolder("echo",new WebSocketServlet(){

                                @Override
                                public void configure(WebSocketServletFactory factory) {
                                    
                                            factory.register(MessageHandler.class);
                                        
                                    }});
                          ws_context.addServlet(wsHolder,"/echo");

                        //   ws_context.setHandler(new MessageHandler());
                          ContextHandlerCollection contextCollection = new ContextHandlerCollection(
                                admin_context
                                ,ws_context
                        //         ,disp_context
                                );
 

                         System.out.println("Set Context Path '/services'");

 

                        // ContextHandler disp_context = new ContextHandler();

                        // disp_context.setContextPath("/dashtop");
                        // System.out.println("Set Context Path '/dashtop'");

                      

 

                        //         server.setHandler(contextCollection);
                                server.setHandler(contextCollection);
                        // server.setHandler(admin_context);
                        System.out.println("Set Context collection to server");
                        server.start();
                        System.out.println("Server Started and then dump to standard output,server start result:" + server.isStarted());
                        // server.dump(System.out);
                        server.join();
                }
                catch (Throwable t)
                {
                        t.printStackTrace(System.err);
                }
        }
}
