package net.group5.itembankmodule.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.group5.itembankmodule.dao.*;
import net.group5.itembankmodule.model.Login;
import net.group5.itembankmodule.model.Item;


@WebServlet("/")
public class ItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1;
    private ItemDAO itemDAO;
    private LoginDAO loginDAO;

    public void init() {
        itemDAO = new ItemDAO();
        loginDAO = new LoginDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                case "/toUpdate":
                	updateUserAdmin(request, response);
                	break;
                case "/toDelete":
                	deleteUserAdmin(request, response);
                	break;
                case "/admin":
                	listUserAdmin(request, response);
                	break;
                case "/login":
                	showLogin(request, response);
                	break;
                case "/auth":
                	checkLogin(request, response);
                	break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void showLogin(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException{
    	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);        
    }
    
    
    private void checkLogin(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	System.out.println("Hey");
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        Login loginBean = new Login();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
        	String status = loginDAO.validate(loginBean);
        	
            if (status.isEmpty()) {
                //HttpSession session = request.getSession();
                // session.setAttribute("username",username);
                response.sendRedirect("login");
            }else if(status.equals("A")) {
            	System.out.println("Helllllliashfjhasfihdhfa         " +  status);
            	response.sendRedirect("admin");
            }
            else if(status.equals("N")) {
            	response.sendRedirect("list");
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
//    private void loginItemBank9

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Item > listUser = itemDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("item-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listUserAdmin(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Item > listUser = itemDAO.selectAllUsers();
        for(Item user: listUser) {
        	System.out.println(user.getId() + " SERVLET " + user.isToBeUpdated());
        }
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("item-list-admin.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("item-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Item existingUser = itemDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("item-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String domain = request.getParameter("domain");
        String itemType = request.getParameter("itemType");
        String itemText = request.getParameter("itemText");
        String answerKey = request.getParameter("answerKey");
        String author = request.getParameter("author");
//        boolean toBeUpdated = (boolean)request.getAttribute("toBeUpdated");
//        boolean toBeDeleted = (boolean)request.getAttribute("toBeDeleted");
        Item newUser = new Item(domain, itemType, itemText, answerKey, author, false, false);
        itemDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String domain = request.getParameter("domain");
        String itemType = request.getParameter("itemType");
        String itemText = request.getParameter("itemText");
        String answerKey = request.getParameter("answerKey");
        String author = request.getParameter("author");
//        boolean toBeUpdated = (boolean)request.getAttribute("toBeUpdated");
//        boolean toBeDeleted = (boolean)request.getAttribute("toBeDeleted");
        Item book = new Item(id, domain, itemType, itemText, answerKey, author, false, false);
        itemDAO.updateUser(book);
        response.sendRedirect("list");
    }
    
    private void updateUserAdmin(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String domain = request.getParameter("domain");
        String itemType = request.getParameter("itemType");
        String itemText = request.getParameter("itemText");
        String answerKey = request.getParameter("answerKey");
        String author = request.getParameter("author");
//    	        boolean toBeUpdated = (boolean)request.getAttribute("toBeUpdated");
//    	        boolean toBeDeleted = (boolean)request.getAttribute("toBeDeleted");
        Item book = new Item(id, domain, itemType, itemText, answerKey, author, true, false);
        itemDAO.updateUserAdmin(book);
        response.sendRedirect("admin");
    }
    private void deleteUserAdmin(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String domain = request.getParameter("domain");
        String itemType = request.getParameter("itemType");
        String itemText = request.getParameter("itemText");
        String answerKey = request.getParameter("answerKey");
        String author = request.getParameter("author");
//        	        boolean toBeUpdated = (boolean)request.getAttribute("toBeUpdated");
//        	        boolean toBeDeleted = (boolean)request.getAttribute("toBeDeleted");
        Item book = new Item(id, domain, itemType, itemText, answerKey, author, false, false);
        itemDAO.deleteUserAdmin(book);
        response.sendRedirect("admin");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        itemDAO.deleteUser(id);
        response.sendRedirect("list");

    }
}