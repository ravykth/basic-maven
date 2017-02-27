/**
 * 
 */
package com.rupp.sample.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * @author sopheamak
 *
 */
public class PropertiesServlet extends HttpServlet {
    
    private static String content = null;
    static {
        String propsFile = "application.properties";
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResource(propsFile).openStream();
            content = IOUtils.toString(inputStream);
        } catch (IOException e) {
        }
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/plain");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = resp.getWriter();
        writer.write(content);
        writer.close();
    }
    
}
