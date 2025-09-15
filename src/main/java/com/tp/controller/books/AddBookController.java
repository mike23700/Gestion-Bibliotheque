package com.tp.controller.books;

import com.tp.model.Book;
import com.tp.model.User;
import com.tp.model.generateID.GenerateIdBooks;
import com.tp.service.BookService;
import com.tp.service.FileUploader;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addBook")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,      // 1 MB
        maxFileSize = 1024 * 1024 * 10,       // 10 MB
        maxRequestSize = 1024 * 1024 * 50     // 50 MB
)
public class AddBookController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "uploaded_books";
    private static final long serialVersionUID = 1L;

    private final BookService bookService = new BookService();
    private final GenerateIdBooks ID = new GenerateIdBooks();
    private final FileUploader fileUploader = new FileUploader(UPLOAD_DIRECTORY);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/books/AddBooks.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }


        Part filePart = request.getPart("image");
        String imageFileNameInBD = fileUploader.handleFileUpload(filePart, getServletContext().getRealPath("/"));

        Book book = new Book();
        book.setId_Book(ID.generateID());
        String title = request.getParameter("title");
        String yearString = request.getParameter("year");
        Integer year = Integer.parseInt(yearString);
        String auteur = request.getParameter("author");
        String categorie = request.getParameter("category");
        String description = request.getParameter("description");

        if(title != null && year == null && auteur != null && categorie != null && description != null){
            book.setTitle(title);
            book.setAuthor(auteur);

            book.setYear(year);
            book.setImage(imageFileNameInBD);
            book.setCategory(categorie);
            book.setDescription(description);
            book.setStatus("disponible");
            book.setLoan_count(0);

            try {
                bookService.addBook(book);
                session.setAttribute("succes", "Livre ajouté avec succès");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("error", "Erreur lors de l'ajout du livre");
            }
            response.sendRedirect("listBooks");
        }else {
            session.setAttribute("error", "Erreur lors de l'ajout element abscent ou valide");
        }

    }
}
