package com.cg.demo5.Controller;

import com.cg.demo5.model.Category;
import com.cg.demo5.model.Product;
import com.cg.demo5.service.CategoryService;
import com.cg.demo5.service.Productservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name ="ProductSeverlet",urlPatterns ="/product")
public class ProductSeverlet extends HttpServlet {
    private Productservice productservice = new Productservice();
    private CategoryService categoryService = new CategoryService();

    @Override
    public void init() throws ServletException {
        productservice = new Productservice();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                deteleproduct(req, resp);
                break;
            case "edit":
//            Khi vào tới đây, mong muốn sẽ hiển thị ra được 1 form để người dùng edit
                showeditProduct(req,resp);
                break;
            case "create":
                showCreateProduct(req, resp);
                break;
            default:
                showProduct(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                editProduct(req,resp);
                break;
            case "create":
                createProduct(req, resp);
                break;
            default:
                showProduct(req, resp);

        }
    }

    private void showProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productservice.findAll());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    private void showCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("product",productservice.createProduct(new Product());
//        Set giá trị cho category
        req.setAttribute("categorys", categoryService.findAll());
//        Điều hướng trang
        req.getRequestDispatcher("createProduct.jsp").forward(req, resp);
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int categoryId = Integer.parseInt(req.getParameter("category"));

        Category category = categoryService.findById(categoryId);
        Product product = new Product(name, price, quantity, category);
        Productservice productservice = new Productservice();
        productservice.createProduct(product);

        req.setAttribute("product", product);
        req.getRequestDispatcher("createProduct.jsp").forward(req, resp);

    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Đoạn này để lấy các giá trị mà form gửi lên
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter(req.getParameter("price")));
        int quantity = Integer.parseInt(req.getParameter(req.getParameter("quantity")));
        int categoryid = Integer.parseInt(req.getParameter(req.getParameter("category")));

//        Đoạn này là để xử lý logic sửa
        Category category = categoryService.findById(categoryid);
        Product product = new Product(id, name, price, quantity, category);

//        Đoạn này xét giá trị trả về lại cho client
        req.setAttribute("product", product);
        req.setAttribute("categorys", categoryService.findAll());
        req.setAttribute("message", "edited");

//        Dòng này để điều hướng về trang edit
        req.getRequestDispatcher("edit.jsp").forward(req, resp);


    }
    private void showeditProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("edit.jsp",productservice.editProduct());
//        Lấy id đã gửi lên khi nhấn edit
        int id = Integer.parseInt(req.getParameter("id"));

//        Tìm kiếm Product theo id để trả về giá trị cho trang edit.jsp
        Product product = productservice.findById(id);

//        Set giá trị product để tr về
        req.setAttribute("product", product);

//        Điều hướng về trang edit.jsp
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }
    private void deteleproduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productservice.detele(id);
        showProduct(req, resp);
    }
}

