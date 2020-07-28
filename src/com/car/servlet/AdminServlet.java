package com.car.servlet;

import com.car.util.GsonUtil;
import com.car.bean.Admin;
import com.car.util.MD5Utils;
import com.car.util.ObjectFactory;
import com.car.bean.PageBean;
import com.car.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@WebServlet("/adminServlet")
public class AdminServlet extends BaseServlet {

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("AdminServlet login ing...");

        String adminJson=request.getParameter("user");
        System.out.println("adminJson:"+adminJson);
        String vCode=request.getParameter("vCodeUser");
        String vCodeS= (String) request.getSession().getAttribute("vCode");

        request.getSession().removeAttribute("vCode");
        //验证之后移除验证码

        if (vCode.equalsIgnoreCase(vCodeS)){
            Admin admin= GsonUtil.getIncetance().getGson().fromJson(adminJson,Admin.class);
            admin.setcPsd(MD5Utils.md5(admin.getcPsd()));
            System.out.println(admin.toString());

            AdminService adminService= (AdminService) ObjectFactory.getObject("com.pcs.service.impl.AdminServiceImpl");
            Admin admin1=adminService.login(admin.getcAccount(),admin.getcPsd());

            if (admin1!=null){
                System.out.println(admin1.getcName());

                if (admin1.getState().intValue()==1){
                    response.getWriter().print("success");
                    String admin1Json= GsonUtil.getIncetance().getGson().toJson(admin1);
                    request.getSession().setAttribute("admin",admin1Json);
                    request.getSession().setAttribute("userName",admin1.getcName());
                    request.getSession().setAttribute("adAccount",admin1.getcAccount());
                    request.getSession().setAttribute("pID",admin1.getpID());
                }else{
                    response.getWriter().print("账号异常！");
                }
            }else {
                response.getWriter().print("账号密码错误！");
            }
        }else {
            response.getWriter().print("验证码错误！");
        }
    }

    public void getPage (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        System.out.println("adminServlet getPage ing...");

        int curPage;
        if (request.getParameter("curPage")!=null) {
            curPage = Integer.parseInt(request.getParameter("curPage"));
        }else {
            curPage=1;
        }

        HashMap<String,Object> condition=new HashMap<>();

        String uname=request.getParameter("uname");
        System.out.println(uname);
        if (uname!=null&&!uname.trim().equals("")){
            condition.put("uname",uname);
        }
        String ustate=request.getParameter("ustate");
        request.setAttribute("uState",ustate);
        if (ustate!=null&&!ustate.trim().equals("0")&&ustate!=""){

            if (ustate.equals("1")){
                ustate="启用";
            }else{
                ustate="禁用";
            }
            System.out.println(ustate);
            condition.put("ustate",ustate);
        }
        String uphone=request.getParameter("uphone");
        System.out.println(uphone);
        if (uphone!=null&&!uphone.trim().equals("")){
            condition.put("uphone",uphone);
        }

        AdminService adminService= (AdminService) ObjectFactory.getObject("com.pcs.service.impl.AdminServiceImpl");

        PageBean<Admin> pageBean=adminService.findUsersByPage(condition,curPage,5);
        System.out.println(pageBean);
        request.setAttribute("uName",uname);
        request.setAttribute("uphone",uphone);
        request.setAttribute("pageBean",pageBean);
        System.out.println("uState:"+request.getAttribute("uState"));
        request.getRequestDispatcher("jsp/adminList.jsp").forward(request,response);

    }

    public void changeState(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        System.out.println("adminServlet changeState ing...");

        String userAccount=request.getParameter("userId");
        String state=request.getParameter("uState");
        System.out.println("changeState: "+userAccount+" "+state);

        HashMap<String, Object> condition = new HashMap<>();
        condition.put("userAccount",userAccount);
        condition.put("state",state);

        AdminService adminService= (AdminService) ObjectFactory.getObject("com.pcs.service.impl.AdminServiceImpl");
        int i=adminService.changeState(condition);

        if (i>0){
            response.getWriter().print("success");
        }else{
            response.getWriter().print("failed");
        }

    }

    public void resetPsd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        System.out.println("adminServlet resetPsd ing...");

        String userAccountReset=request.getParameter("userId");
        System.out.println("resetPsd: "+userAccountReset);

        HashMap<String, Object> conditionReset = new HashMap<>();
        conditionReset.put("userAccount",userAccountReset);

        AdminService adminService= (AdminService) ObjectFactory.getObject("com.pcs.service.impl.AdminServiceImpl");
        int i=adminService.resetPsd(conditionReset);

        if (i>0){
            response.getWriter().print("success");
        }else{
            response.getWriter().print("failed");
        }
    }

    public void delUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        System.out.println("adminServlet delUser ing...");

        String userAccount=request.getParameter("userId");
        System.out.println("resetPsd: "+userAccount);

        HashMap<String, Object> conditionReset = new HashMap<>();
        conditionReset.put("userAccount",userAccount);

        AdminService adminService= (AdminService) ObjectFactory.getObject("com.pcs.service.impl.AdminServiceImpl");
        int i=adminService.delUser(conditionReset);

        if (i>0){
            response.getWriter().print("success");
        }else{
            response.getWriter().print("failed");
        }
    }


}
