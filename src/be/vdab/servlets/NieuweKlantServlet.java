package be.vdab.servlets;

import be.vdab.dao.KlantDAO;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by Samuel Engelen on 21/05/2015.
 */
@WebServlet(urlPatterns = "/nieuweklant.htm", name = "NieuweKlantServlet")
public class NieuweKlantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/nieuweklant.jsp";

    private final transient KlantDAO klantDAO = new KlantDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Resource(name = KlantDAO.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        klantDAO.setDataSource(dataSource);
    }
}
