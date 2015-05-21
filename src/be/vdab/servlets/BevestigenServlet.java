package be.vdab.servlets;

import be.vdab.dao.KlantDAO;
import be.vdab.dao.ReservatieDAO;
import be.vdab.entities.Klant;

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
@WebServlet(urlPatterns = "/bevestig.htm", name = "BevestigenServlet")
public class BevestigenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/bevestig.jsp";

    private final transient KlantDAO klantDAO = new KlantDAO();
    private final transient ReservatieDAO reservatieDAO = new ReservatieDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagina", "bevestiging reservaties");
        request.getAttribute("naam");
        request.getAttribute("paswoord");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagina", "bevestiging reservaties");
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Resource(name = KlantDAO.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        klantDAO.setDataSource(dataSource);

    }
}
