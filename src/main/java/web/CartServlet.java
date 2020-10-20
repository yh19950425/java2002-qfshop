package com.java.yh.web;

import com.java.yh.domain.Cart;
import com.java.yh.domain.Product;
import com.java.yh.domain.User;
import com.java.yh.server.CartService;
import com.java.yh.server.ServerImpl.CartServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/cart")
@MultipartConfig
public class CartServlet extends BaseServlet {
    protected void addCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String goodsId = request.getParameter("goodsId");

        String price = request.getParameter("price");


        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");

        int id = user.getId();

//        String i = request.getParameter("pNum");
//
//        int pNum = Integer.parseInt(i);

        int pNum=1;

        CartService cart=new CartServiceImpl();

        cart.addCard(id,goodsId,price,pNum);



        request.getRequestDispatcher("/cartSuccess.jsp").forward(request,response);

    }
    protected void QueryCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (user!=null){
            int uid = user.getId();

            CartService cart = new CartServiceImpl();

            Cart cart1 = new Cart();

            List<Cart> carts = cart.QueryCart(uid);

            request.setAttribute("carts", carts);

            request.getRequestDispatcher("/cart.jsp").forward(request,response);

        }else{
            response.sendRedirect("login.jsp");
        }



    }
}
