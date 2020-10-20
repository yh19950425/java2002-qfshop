package com.java.yh.web;

import com.java.yh.domain.Category;
import com.java.yh.domain.Product;
import com.java.yh.server.ProductService;
import com.java.yh.server.ServerImpl.ProductServiceImpl;
import com.java.yh.utils.FileUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/product")
@MultipartConfig
public class ProductServlet extends BaseServlet {


    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();
        Product product = new Product();

        Part picture = request.getPart("picture");


        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        String format = simpleDateFormat.format(date);

        String exName = FileUtils.getExName(picture.getSubmittedFileName());

        String filename = UUID.randomUUID().toString() + exName;

        //图片位置 picloc
        String picloc = "prodimg/" + format;

        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath(picloc);

        File file = new File(realPath);

        file.mkdirs();

        FileOutputStream fileOutputStream = new FileOutputStream(realPath + "/" + filename);

        int copy = IOUtils.copy(picture.getInputStream(), fileOutputStream);

        fileOutputStream.close();


        // TODO: 将参数转换成javabean，存入数据库

        try {
            BeanUtils.populate(product, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String name = request.getParameter("name");

        String utf8name = new String(name.getBytes("iso-8859-1"), "utf-8");

        product.setName(utf8name);

        String intro = request.getParameter("intro");

        String utfIntro = new String(intro.getBytes("iso-8859-1"), "utf-8");

        product.setIntro(utfIntro);

        ProductService productService = new ProductServiceImpl();

        product.setPicture(picloc + "/" + filename);

        productService.addProduct(product);


    }
    protected void QueryProductType(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("utf-8");

        response.setContentType("application/json;charset=utf-8");

        ProductService service=new ProductServiceImpl();

        List<Category> goodsListType= service.QueryProductType();


        ServletContext servletContext = getServletContext();


        servletContext.setAttribute("goodsListType",goodsListType);


        request.getRequestDispatcher("/admin/showGoodsType.jsp").forward(request,response);


    }
    protected void Queryglist(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("utf-8");

        response.setContentType("application/json;charset=utf-8");

        ProductService service=new ProductServiceImpl();

        List<Product> glist= service.QueryProduct();


        ServletContext servletContext = getServletContext();



        servletContext.setAttribute("glist",glist);


        request.getRequestDispatcher("/goodsList.jsp").forward(request,response);


    }
    protected void getGoodsListByTypeId(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("utf-8");

        response.setContentType("application/json;charset=utf-8");

        ProductService service=new ProductServiceImpl();

        String id = request.getParameter("id");

        List<Product> goods=service.QueryByTypeId(id);

        Map<String, String[]> parameterMap = request.getParameterMap();


        try {
            BeanUtils.populate(goods,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        ServletContext servletContext = getServletContext();


        servletContext.setAttribute("goods",goods.get(0));


        request.getRequestDispatcher("/goodsDetail.jsp").forward(request,response);


    }

}
