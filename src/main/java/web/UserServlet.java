package com.java.yh.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.yh.domain.Address;
import com.java.yh.domain.User;
import com.java.yh.server.AddressService;
import com.java.yh.server.ServerImpl.AddressServiceImpl;
import com.java.yh.server.ServerImpl.UserServiceImpl;
import com.java.yh.server.UserService;
import com.java.yh.utils.EmailUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/user")
public class UserServlet extends BaseServlet  {

    private UserService service = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //        1.接收数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String vcode = request.getParameter("vcode");

        HttpSession session = request.getSession();
        String captcha = (String)session.getAttribute("captcha");

        //        2.处理数据
        Map<String,Object> responseObj=new HashMap<>();




        User user=service.QueryUser(username,password);

        if(!vcode.equalsIgnoreCase(captcha)){
            responseObj.put("success",false);
            responseObj.put("msg","验证码错误，请重新输入");
        }else if(user!=null){
            responseObj.put("success", true);
            responseObj.put("msg", "operation success");
        }else {
            responseObj.put("success", false);
            responseObj.put("msg", "账号或密码错误，请重新输入");
        }


        //        3.响应数据
        session.setAttribute("user",user);
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(responseObj));

    }
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=utf-8");
        //接收数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理数据
        //1)生成激活码
        UUID uuid = UUID.randomUUID();
        String activeCode = uuid.toString();

        //2）发送邮件
        EmailUtils.sendRegisterSuccess(request.getParameter("email"),activeCode);

        user.setCode(activeCode);

        service.add(user);
        //响应数据

    }

    protected void checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");



        boolean result=service.QueryEmail(email);


        if (result==false){
            out.write("false");
        }else {
            out.write("true");
        }

    }
    protected void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=utf-8");

        PrintWriter out = response.getWriter();

        String checkusername = request.getParameter("username");



        boolean usernames=service.QueryUsername(checkusername);

        if (usernames==false){
            out.write("false");
        }else {
            out.write("true");
        }

    }

    protected void activeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String code = request.getParameter("code");



        boolean b = service.queryCode(code);

        if (b){
            out.write("<h1>恭喜你，记过成功，3秒后跳转到登录页面</h1>");
            response.setHeader("Refresh","3;location=login.jsp");
        }else {
            out.write("<h1>对不起，激活失败</h1>");
        }
    }

    protected void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

//        1.接收数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = service.QueryAdmin(username, password);

        Map<String,Object> responseObj=new HashMap<>();

        if(user!=null){
            responseObj.put("success", true);
            responseObj.put("msg", "operation success");
        }else {
            responseObj.put("success", false);
            responseObj.put("msg", "账号或密码错误，请重新输入");
        }


//        2.处理数据
//        3.响应数据
        ObjectMapper objectMapper = new ObjectMapper();

        out.write(objectMapper.writeValueAsString(responseObj));
    }

    protected void QueryAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");

        int uid = user.getId();

        AddressService service=new AddressServiceImpl();

        List<Address> addList= service.QueryAddress(uid);


        request.setAttribute("addList",addList);
        request.getRequestDispatcher("/self_info.jsp").forward(request,response);

    }
    protected void setAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> Address = request.getParameterMap();

        Address address = new Address();
        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");

        int uid = user.getId();


        try {
            BeanUtils.populate(address,Address);

            address.setUid(uid);
        }  catch (Exception e) {
            e.printStackTrace();
        }

        AddressService service=new AddressServiceImpl();


        System.out.println(address.getName()+"+"+address.getPhone()+"+"+address.getDetail()+"+"+address.getUid());


        service.setAddress(address);


        request.getRequestDispatcher("/self_info.jsp").forward(request,response);

    }








}
