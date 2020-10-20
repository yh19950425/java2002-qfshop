package com.java.yh.web;

import com.java.yh.domain.Address;
import com.java.yh.domain.Cart;
import com.java.yh.domain.Order;
import com.java.yh.domain.User;
import com.java.yh.server.AddressService;
import com.java.yh.server.CartService;
import com.java.yh.server.OrderService;
import com.java.yh.server.ServerImpl.AddressServiceImpl;
import com.java.yh.server.ServerImpl.CartServiceImpl;
import com.java.yh.server.ServerImpl.OrderServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

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

@WebServlet("/order")
@MultipartConfig
public class OrderServlet extends BaseServlet{

    protected void showOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (user != null) {
            int uid = user.getId();

            CartService cart = new CartServiceImpl();

            Cart cart1 = new Cart();

            List<Cart> cartList = cart.QueryCart(uid);

            request.setAttribute("cartList", cartList);

            request.getRequestDispatcher("/order.jsp").forward(request, response);

        } else {
            response.sendRedirect("login.jsp");
        }
    }
        protected void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            Map<String, String[]> parameterMap = request.getParameterMap();

            Order order=new Order();

            HttpSession session = request.getSession();

            User user = (User)session.getAttribute("user");

            int uid = user.getId();



            try {
                BeanUtils.populate(order,parameterMap);

                order.setAid(uid);
                OrderService service=new OrderServiceImpl();

                int money=service.QueryMoney(uid);

                order.setMoney(money);
                service.addOrder(order);
            } catch (Exception e) {

                e.printStackTrace();

            }

            request.getRequestDispatcher("/orderSuccess.jsp").forward(request,response);

        }
    }

