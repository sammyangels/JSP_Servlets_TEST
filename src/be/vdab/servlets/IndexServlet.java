package be.vdab.servlets;

import be.vdab.dao.GenresDAO;
import be.vdab.dao.VoorstellingenDAO;
import be.vdab.entities.Genre;
import be.vdab.entities.Voorstelling;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Samuel Engelen on 13/05/2015.
 */
@WebServlet(urlPatterns = "/index.htm", name = "IndexServlet")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/index.jsp";

    private final transient GenresDAO genresDAO = new GenresDAO();
    private final transient VoorstellingenDAO voorstellingenDAO = new VoorstellingenDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Menu van genres instellen
        List<Genre> genres = genresDAO.findAll();
        request.setAttribute("genres", genres);

        // Tabel van voorstellingen instellen

        String genreId = request.getParameter("genre");
        if (genreId != null) {
            Genre genre = genresDAO.read(genreId);
            request.setAttribute("genre", genre.getNaam());
            List<Voorstelling> voorstellingen = voorstellingenDAO.findAllPerGenre(genre.getId());
            request.setAttribute("voorstellingen", voorstellingen);
        } else {
            request.setAttribute("genre", "");
        }
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

//    @Resource(name = GenresDAO.JNDI_NAME)
//    void setDataSource(DataSource dataSource) {
//        genresDAO.setDataSource(dataSource);
//    }

    @Resource(name = VoorstellingenDAO.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        genresDAO.setDataSource(dataSource);
        voorstellingenDAO.setDataSource(dataSource);
    }
}
