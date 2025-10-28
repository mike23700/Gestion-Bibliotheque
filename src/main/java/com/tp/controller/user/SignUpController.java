package com.tp.controller.user;

import com.tp.dao.DAOFactory;
import com.tp.model.User;
import com.tp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/signup")
public class SignUpController extends HttpServlet {

    private UserService userService;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.userService = new UserService(daoFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/auth/signUp.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String name = request.getParameter("username");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String tel_num_str = request.getParameter("tel_num");

        if (name == null || name.trim().isEmpty() ||
                surname == null || surname.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                confirmPassword == null || confirmPassword.trim().isEmpty() ||
                tel_num_str == null || tel_num_str.trim().isEmpty()) {

            session.setAttribute("error", "Tous les champs sont obligatoires.");
            response.sendRedirect("signup");
            return;
        }

        if (!password.equals(confirmPassword)) {
            session.setAttribute("error", "Les mots de passe ne correspondent pas.");
            response.sendRedirect("signup");
            return;
        }

        String regexTel = "^6[0-9]{8}$";
        if (!Pattern.matches(regexTel, tel_num_str)) {
            session.setAttribute("error", "Le numéro de téléphone n'est pas au format correct (ex. 6xxxxxxxx).");
            response.sendRedirect("signup");
            return;
        }

        String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (!Pattern.matches(regexEmail, email)) {
            session.setAttribute("error", "L'adresse email n'est pas au format correct (ex. mike@gmail.com).");
            response.sendRedirect("signup");
            return;
        }

        int tel_num = Integer.parseInt(tel_num_str);

        User newUser = userService.createUserAndSignUp(name, surname, tel_num, email, password);

        if (newUser != null) {
            session.setAttribute("user", newUser);
            session.setAttribute("message", "Bienvenue sur BiblioTech !");
            response.sendRedirect("memberDashboard");
        } else {
            session.setAttribute("error", "Erreur lors de l'inscription. L'email ou le nom d'utilisateur existe peut-être déjà.");
            response.sendRedirect("signup");
        }
    }
}