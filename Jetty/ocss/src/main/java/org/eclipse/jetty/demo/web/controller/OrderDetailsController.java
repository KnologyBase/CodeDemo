/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.eclipse.jetty.demo.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.eclipse.jetty.demo.business.entities.Order;
import org.eclipse.jetty.demo.business.services.OrderService;

public class OrderDetailsController implements IGTVGController {

    
    public OrderDetailsController() {
        super();
    }
    
    
    public void process(
            final HttpServletRequest request, final HttpServletResponse response,
            final ServletContext servletContext, final ITemplateEngine templateEngine)
            throws Exception {
        
        final Integer orderId = Integer.valueOf(request.getParameter("orderId"));
        
        final OrderService orderService = new OrderService();
        final Order order = orderService.findById(orderId);
        
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("order", order);
        
        templateEngine.process("order/details", ctx, response.getWriter());
        
    }

}
