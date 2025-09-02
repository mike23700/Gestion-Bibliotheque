package com.tp.controller.books;

import com.tp.model.Book;
import com.tp.model.User;
import com.tp.model.generateID.GenerateIdBooks;
import com.tp.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/addBook")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,      // 1 MB
        maxFileSize = 1024 * 1024 * 10,       // 10 MB
        maxRequestSize = 1024 * 1024 * 50     // 50 MB
)
public class AddBookController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "uploaded_books";

    BookService bookService = new BookService();
    Book book = new Book();
    GenerateIdBooks ID = new GenerateIdBooks();

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String s : contentDisp.split(";")) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    protected void doGet(HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/books/AddBooks.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        /* 
        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        */
        if(session == null){
            response.sendRedirect("login");
        }


        String ImageFileNameInBD = null;
        Part filePart = request.getPart("image");

        if(filePart != null && filePart.getSize() > 0){
            String originalFileName = extractFileName(filePart);
            if (!originalFileName.isEmpty()){
                String uniqueFileName = UUID.randomUUID().toString().substring(0,6) + "_" + originalFileName;

                String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()){
                    uploadDir.mkdir();
                }

                filePart.write(uploadPath + File.separator + uniqueFileName);

                ImageFileNameInBD = UPLOAD_DIRECTORY + "/" + uniqueFileName;
            }
        }

        book.setId_Book(ID.generateID());
        book.setTitle(request.getParameter("title"));
        book.setAuthor(request.getParameter("author"));
        String yearString = request.getParameter("year");
        book.setYear(Integer.parseInt(yearString));
        book.setImage(ImageFileNameInBD);
        book.setCategory(request.getParameter("category"));
        book.setDescription(request.getParameter("description"));
        book.setStatus(request.getParameter("status"));
        //String loan_count = request.getParameter("loan_count");
        book.setLoan_count(book.getLoan_count());

        try {
            bookService.addBook(book);
            session.setAttribute("succes","Livre ajouter avec succes");
        } catch (Exception e) {
            session.setAttribute("error","Erreur lors de l'ajout du livre");
        }
        response.sendRedirect("listBooks");
    }
}
