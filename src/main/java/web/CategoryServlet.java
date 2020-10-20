package com.java.yh.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.yh.domain.Category;
import com.java.yh.domain.CategoryManager;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/category")
public class CategoryServlet extends BaseServlet {
    protected void queryByLevel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        String levelParam = request.getParameter("level");

        int level = Integer.parseInt(levelParam);

        List<Category> categories = CategoryManager.queryByLevel(level);

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = objectMapper.writeValueAsString(categories);

        out.write(jsonString);


    }

    protected void queryByParent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        String parent = request.getParameter("parent");

        int i = Integer.parseInt(parent);
        List<Category> categories = CategoryManager.queryByParent(i);

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = objectMapper.writeValueAsString(categories);

        out.write(jsonString);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        response.setContentType("application/json;charset=utf-8");


        Map<String, String[]> parameterMap = request.getParameterMap();

        Category category = new Category();

        try {
            BeanUtils.populate(category, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CategoryManager.add(category);

    }

}