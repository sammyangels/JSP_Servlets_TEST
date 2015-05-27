package be.vdab.servlets;

import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.Reservatie;
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
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Samuel Engelen on 19/05/2015.
 */
@WebServlet(urlPatterns = "/reservatiemandje.htm", name = "ReservatieMandjeServlet")
public class ReservatieMandjeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/reservatiemandje.jsp";
//    private static final String REDIRECT_URL = "%s/reservatiemandje.htm";

    private final transient VoorstellingDAO voorstellingDAO = new VoorstellingDAO();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagina", "reservatiemandje");
        HttpSession session = request.getSession();

        // Mandje oproepen
        @SuppressWarnings("unchecked")
        Map<Long, Long> mandje = (Map<Long, Long>) session.getAttribute("mandje");

        // De aangevinkte reservaties in lijst plaatsen en vervolgens verwijderen
        if (request.getParameter("verwijder") != null) {
            Set<Long> verwijderReservaties = new LinkedHashSet<>();
            for (String teVerwijderen : request.getParameterValues("verwijder")) {
                verwijderReservaties.add(Long.parseLong(teVerwijderen));
            }
            for (Long reservatie : verwijderReservaties) {
                mandje.remove(reservatie);
            }
        }

        // Mandje opnieuw instellen
        session.setAttribute("mandje", mandje);
        response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagina", "reservatiemandje");
        HttpSession session = request.getSession();

        // Mandje oproepen en totaal te betalen berekenen
        @SuppressWarnings("unchecked")
        Map<Long, Long> mandje = (Map<Long, Long>) session.getAttribute("mandje");
        List<Reservatie> reservaties = new ArrayList<>();
        BigDecimal totaal = new BigDecimal(0);
        for (Long key : mandje.keySet()) {
            Reservatie reservatie = new Reservatie(voorstellingDAO.findOne(key), mandje.get(key));
            reservaties.add(reservatie);
            totaal = totaal.add(reservatie.getVoorstelling().getPrijs().multiply(new BigDecimal(mandje.get(key))));
        }

        // Totaal en reservaties instellen
        request.setAttribute("totaal", totaal);
        request.setAttribute("reservaties", reservaties);
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Resource(name = VoorstellingDAO.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        voorstellingDAO.setDataSource(dataSource);
    }

}