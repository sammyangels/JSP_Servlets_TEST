package be.vdab.servlets;

import be.vdab.dao.GenreDAO;
import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.Genre;
import be.vdab.entities.Voorstelling;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/index.htm", name = "IndexServlet")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/index.jsp";

    private final transient GenreDAO genreDAO = new GenreDAO();
    private final transient VoorstellingDAO voorstellingDAO = new VoorstellingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagina", "voorstellingen");

        // Menu van genres instellen
        List<Genre> genres = genreDAO.findAll();
        request.setAttribute("genres", genres);

        // Tabel van voorstellingen instellen
        String genreId = request.getParameter("genre");
        if (genreId != null) {
            Genre genre = genreDAO.findByGenre(genreId);
            request.setAttribute("genre", genre.getNaam());
            List<Voorstelling> voorstellingen = voorstellingDAO.findAllPerGenre(genre.getId());
            request.setAttribute("voorstellingen", voorstellingen);
        } else {
            request.setAttribute("genre", "");
        }
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Resource(name = VoorstellingDAO.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        genreDAO.setDataSource(dataSource);
        voorstellingDAO.setDataSource(dataSource);
    }
}
