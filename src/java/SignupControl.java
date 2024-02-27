/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import context.DBContext;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author trant
 */
@WebServlet(urlPatterns = {"/signup"})
public class SignupControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignupControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignupControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userU = request.getParameter("user");
        String passP = request.getParameter("pass");
        String confirmPass = request.getParameter("confirmPass");
        String agree = request.getParameter("agree");

        // Kiểm tra xem tất cả các ô đã có dữ liệu chưa, bao gồm cả checkbox
        if (userU == null || passP == null || confirmPass == null || (agree == null)) {
            request.setAttribute("errM", "Please fill in all the information, including checking the license terms.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
        } else {

            // Kiểm tra xem tên người dùng có ít nhất 3 ký tự không
            if (userU.length() < 3) {
                request.setAttribute("errM", "Username must be at least 3 characters long.");
                request.getRequestDispatcher("Signup.jsp").forward(request, response);
                return; // dừng xử lý nếu tên người dùng không đủ dài
            }

            // Kiểm tra mật khẩu phải trùng với confirm password
            if (!passP.equals(confirmPass)) {
                request.setAttribute("errM", "Password and Confirm Password must match.");
                request.getRequestDispatcher("Signup.jsp").forward(request, response);
                return; // dừng xử lý nếu mật khẩu không khớp
            }

            // Kiểm tra mật khẩu phải có ít nhất một chữ hoa, một số và dài ít nhất 3 ký tự
            if (!passP.matches("^(?=.*[A-Z])(?=.*[0-9]).{3,}$")) {
                request.setAttribute("errM", "Password must contain at least one uppercase letter, one digit, and be at least 3 characters long.");
                request.getRequestDispatcher("Signup.jsp").forward(request, response);
                return; // dừng xử lý nếu mật khẩu không đáp ứng yêu cầu
            } else {

                DBContext context = new DBContext();
                Account ab = context.checkExistAccount(userU);
                if (ab == null) {
                    context.signup(userU, passP);
                    response.sendRedirect("Successful.jsp");
                } else {
                    request.setAttribute("errM", "User name was exist");
                    request.getRequestDispatcher("Signup.jsp").forward(request, response);
                }

            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
