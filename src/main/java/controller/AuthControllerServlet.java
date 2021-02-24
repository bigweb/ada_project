package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthControllerServlet extends HttpServlet {

    private static String AUTHORIZE_FIELD = "is_authorize";
    private static String CURRENT_USER = "current_username";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DBUtils.execQuery("SELECT * FROM users");

        System.out.println("Request from browser reached the servlet...");
        HttpSession session = req.getSession();

        if (session.getAttribute(AuthControllerServlet.AUTHORIZE_FIELD) != null) {

            if(req.getParameter("action") != null && req.getParameter("action").equals("logout")) {
                session.removeAttribute(AuthControllerServlet.AUTHORIZE_FIELD);
                session.removeAttribute(AuthControllerServlet.CURRENT_USER);
                resp.sendRedirect("auth.jsp");
            } else {
                if ((Boolean) session.getAttribute(AuthControllerServlet.AUTHORIZE_FIELD)) {
                    getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("auth.jsp");
                }
            }
        } else {
            resp.sendRedirect("auth.jsp");
        }

        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username != null && password != null && username.length() > 3 && password.length() == 6) {
            String userList = this.getInitParameter("userList");
            if(userList != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(userList);
                if(node != null && !node.isEmpty() && node.isArray()) {
                    for(final JsonNode record: node) {
                        if(record.get("name").textValue().equals(username) && record.get("password").textValue().equals(password)) {
                            HttpSession session = req.getSession();
                            session.setAttribute(AuthControllerServlet.AUTHORIZE_FIELD, true);
                            session.setAttribute(AuthControllerServlet.CURRENT_USER, username);
                            System.out.printf("User passed authentication. Trying to forward him...");
                            resp.sendRedirect("/helloworld/auth");
                            continue;
                        }
                    }
                } else {
                    System.out.print("No users exist within system");
                }
            } else {
                System.out.print("No users exist within system");
            }
        } else {
            System.out.println("Data Validation error");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
