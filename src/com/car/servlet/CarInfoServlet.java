package com.car.servlet;

import com.car.bean.CarInfo;
import com.car.bean.PageBean;
import com.car.service.CarInfoService;
import com.car.util.GsonUtil;
import com.car.util.ObjectFactory;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;

@WebServlet("/carInfoServlet")
public class CarInfoServlet extends BaseServlet {

    public void getCarInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //servlet to service and get carInfoList
        System.out.println("CarInfoServlet getCarInfo ing...");

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charst=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        int curPage;
        if (request.getParameter("curPage")!=null) {
            curPage = Integer.parseInt(request.getParameter("curPage"));
        }else {
            curPage=1;
        }

        HashMap<String,Object> condition=new HashMap<>();

        String cNumber=URLDecoder.decode(request.getParameter("cNumber"),"UTF-8");

        System.out.println("cNumber:"+cNumber);
        if (cNumber!=null&&!cNumber.trim().equals("")){
            condition.put("cNumber",cNumber);
        }

        CarInfoService carInfoService= (CarInfoService) ObjectFactory.getObject("com.car.service.impl.CarInfoServiceImpl");

        PageBean<CarInfo> pageBean=carInfoService.getCarInfo(condition,curPage,5);
        System.out.println(pageBean);

        request.setAttribute("cNumber",cNumber);
        //search blank value

        response.getWriter().print(GsonUtil.getIncetance().getGson().toJson(pageBean));
        //to Json

    }

}
