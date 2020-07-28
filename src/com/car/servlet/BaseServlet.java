package com.car.servlet;

import sun.misc.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       String methodName =  req.getParameter("methodName");
       System.out.println(methodName);
       Class clazz = this.getClass();//调用这个方法的字节对象
         System.out.println(clazz);
        try {
            if(methodName!=null&&!"".equals(methodName.trim())) {
                Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);//获取到要调用方法

                String path = (String) method.invoke(this, req, resp);//方法的自我唤醒，自己调用，执行这个方法
                if (path!=null) {
                    req.getRequestDispatcher(path).forward(req, resp);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
