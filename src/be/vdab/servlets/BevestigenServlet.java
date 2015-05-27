package be.vdab.servlets;

import be.vdab.dao.KlantDAO;
import be.vdab.dao.ReservatieDAO;
import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.Klant;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Samuel Engelen on 21/05/2015.
 */
@WebServlet(urlPatterns = "/bevestigen.htm", name = "BevestigenServlet")
public class BevestigenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
    private static final String NEW_USER = "%s/nieuweklant.htm";
    private static final String CONFIRM = "%s/overzicht.htm";

    private final transient KlantDAO klantDAO = new KlantDAO();
    private final transient VoorstellingDAO voorstellingDAO = new VoorstellingDAO();
    private final transient ReservatieDAO reservatieDAO = new ReservatieDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagina", "bevestiging reservaties");

        HttpSession session = request.getSession();

        // "Zoek me op" knop
        if (request.getParameter("klantzoeken") != null) {
            String naam = request.getParameter("naam");
            String paswoord = request.getParameter("paswoord");
            String resultaat;

            if (naam != null) {
                Klant klant = klantDAO.findOne(naam);

                // Controleren of klant reeds bestaat en paswoord klopt
                if (klant != null) {
                    if (!paswoord.equals(klant.getPaswoord())) {
                        resultaat = "Verkeerde gebruiker of paswoord";
                    } else {
                        resultaat = klant.toString();
                        session.setAttribute("gebruiker", klant.getGebruikersnaam());
                    }
                } else {
                    resultaat = "Verkeerde gebruiker of paswoord";
                }
                request.setAttribute("resultaat", resultaat);
            }
            request.getRequestDispatcher(VIEW).forward(request, response);
        }

        // "Ik ben nieuw" knop
        if (request.getParameter("klanttoevoegen") != null) {
            response.sendRedirect(String.format(NEW_USER, request.getContextPath()));
        }

        // "Bevestigen" knop
        if (request.getParameter("bevestigen") != null) {

            // Klant ID ophalen
            Long klantid = klantDAO.findOne((String) session.getAttribute("gebruiker")).getId();

            // Mandje ophalen
            @SuppressWarnings("unchecked")
            Map<Long, Long> mandje = (Map<Long, Long>) session.getAttribute("mandje");

            // Lijsten voor gelukte en mislukte reservaties aanmaken
            List<Reservatie> gelukteReservaties = new ArrayList<>();
            List<Reservatie> mislukteReservaties = new ArrayList<>();

            // Iedere reservatie ophalen en naar de database proberen te schrijven
            for (Map.Entry<Long, Long> entry : mandje.entrySet()) {
                Voorstelling voorstelling = voorstellingDAO.findOne(entry.getKey());

                if (reservatieDAO.nieuweReservatie(klantid, voorstelling.getId(), voorstelling.getVrijeplaatsen(), entry.getValue())) {
                    gelukteReservaties.add(new Reservatie(voorstelling, entry.getValue()));
                } else {
                    mislukteReservaties.add(new Reservatie(voorstelling, entry.getValue()));
                }
            }

            // Mandje leegmaken en session attributes invullen
            mandje.clear();
            session.setAttribute("mandje", mandje);
            session.setAttribute("gelukt", gelukteReservaties);
            session.setAttribute("mislukt", mislukteReservaties);

            response.sendRedirect(response.encodeRedirectURL(String.format(CONFIRM, request.getContextPath())));
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagina", "bevestiging reservaties");

        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Resource(name = KlantDAO.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        klantDAO.setDataSource(dataSource);
        reservatieDAO.setDataSource(dataSource);
        voorstellingDAO.setDataSource(dataSource);
    }
}
