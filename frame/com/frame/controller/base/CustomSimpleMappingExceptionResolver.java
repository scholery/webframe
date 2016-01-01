package com.frame.controller.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.frame.exception.AuthorizationException;
import com.frame.exception.BaseAppException;


public class CustomSimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(CustomSimpleMappingExceptionResolver.class);
	
	@Override  
    protected ModelAndView doResolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) {
        // Expose ModelAndView for chosen error view.  
        String viewName = determineViewName(ex, request);  
        ex.printStackTrace();
        if (viewName != null) {  
            if ((request.getHeader("X-Requested-With")== null)) {// 普通视图返回
                // 如果不是异步请求  
                // Apply HTTP status code for error views, if specified.  
                // Only apply it if we're processing a top-level request.  
                Integer statusCode = determineStatusCode(request, viewName);  
                if (statusCode != null) {  
                    applyStatusCodeIfPossible(request, response, statusCode);  
                }  
                return getModelAndView(viewName, ex, request);  
            } else {// JSON格式返回  
                try {
                	response.setStatus(500);
                	JSONObject jo = new JSONObject();
                	
                	if(ex instanceof BaseAppException){
                		jo.put("code", ((BaseAppException)ex).getCode());
                		jo.put("message", ((BaseAppException)ex).getLocaleMessage());
                	}else{
                		jo.put("code", "内部错误");
                		jo.put("message", ex.getMessage());
                	}
                    PrintWriter writer = response.getWriter();  
                    writer.write(jo.toString());  
                    writer.flush();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                return null;  
            }  
        } else {
            return null;  
        }  
    }
	
	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex) {
		ModelAndView mv = new ModelAndView(viewName);
		if (logger.isDebugEnabled()) {
			logger.debug("Exposing Exception as model attribute '" + "exception" + "'");
		}
		if(ex instanceof BaseAppException){
			mv.addObject("code", ((BaseAppException)ex).getCode());
			mv.addObject("message", ((BaseAppException)ex).getMessage());
		}else if(ex instanceof AuthorizationException){
			mv.addObject("message", ex.getMessage());
		}else{
			mv.addObject("code", "内部错误");
			mv.addObject("message", ex.getMessage());
		}
		return mv;
	}
}
