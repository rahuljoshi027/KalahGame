package com.game.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * This class is implementation for {@link HandlerInterceptorAdapter}
 * which here is specifically used for logging request details after 
 * the request is complete
 * 
 * @author Rahul
 * */
public class LoggingInterceptor extends HandlerInterceptorAdapter
{

    private static final Log LOG = LogFactory.getLog(LoggingInterceptor.class);

    /**
     * Logs message after completion of any request.
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("method: ").append(request.getMethod()).append("\t");
        logMessage.append("uri: ").append(request.getRequestURI()).append("\t");
        logMessage.append("status: ").append(response.getStatus()).append("\t");
        logMessage.append("remoteAddress: ").append(request.getRemoteAddr()).append("\t");
        if (ex != null)
        {
            LoggingInterceptor.LOG.error(logMessage.toString(), ex);
        }
        else
        {
            LoggingInterceptor.LOG.info(logMessage.toString());
        }
    }

}
