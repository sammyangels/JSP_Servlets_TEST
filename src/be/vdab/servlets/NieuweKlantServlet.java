package be.vdab.servlets;

import be.vdab.dao.KlantDAO;
import be.vdab.entities.Klant;

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

/**
 * Created by Samuel Engelen on 21/05/2015.
 */
@WebServlet(urlPatterns = "/nieuweklant.htm", name = "NieuweKlantServlet")
public class NieuweKlantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/nieuweklant.jsp";
    private static final String REDIRECT_URL = "%s/bevestigen.htm";

    private final transient KlantDAO klantDAO = new KlantDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagina", "nieuwe klant");

        // Foutenlijst aanmaken
        List<String> fouten = new ArrayList<>();
        HttpSession session = request.getSession();

        // Klant aanmaken
        Klant nieuweKlant = new Klant(request.getParameter("voornaam"), request.getParameter("familienaam"),
                                        request.getParameter("straat"), request.getParameter("huisnr"),
                                        request.getParameter("postcode"), request.getParameter("gemeente"),
                                        request.getParameter("gebruikersnaam"), request.getParameter("paswoord"));

        // Controleren of alle velden zijn ingevuld ( HTML tag 'required' wordt niet ondersteund door Safari)
        if (nieuweKlant.getVoornaam() == null || nieuweKlant.getVoornaam().isEmpty()) {
            fouten.add("Voornaam is niet ingevuld.");
        }

        if (nieuweKlant.getFamilienaam() == null || nieuweKlant.getFamilienaam().isEmpty()) {
            fouten.add("Familienaam is niet ingevuld.");
        }

        if (nieuweKlant.getStraat() == null || nieuweKlant.getStraat().isEmpty()) {
            fouten.add("Straat is niet ingevuld.");
        }

        if (nieuweKlant.getHuisnr() == null || nieuweKlant.getHuisnr().isEmpty()) {
            fouten.add("Huisnr is niet ingevuld.");
        }

        if (nieuweKlant.getPostcode() == null || nieuweKlant.getPostcode().isEmpty()) {
            fouten.add("Postcode is niet ingevuld.");
        }

        if (nieuweKlant.getGemeente() == null || nieuweKlant.getGemeente().isEmpty()) {
            fouten.add("Gemeente is niet ingevuld.");
        }

        if (nieuweKlant.getGebruikersnaam() == null || nieuweKlant.getGebruikersnaam().isEmpty()) {
            fouten.add("Gebruikersnaam is niet ingevuld.");
        }

        if (nieuweKlant.getPaswoord() == null || nieuweKlant.getPaswoord().isEmpty()) {
            fouten.add("Paswoord is niet ingevuld.");
        }

        // Paswoordcontrole
        if (!nieuweKlant.getPaswoord().equals(request.getParameter("controle"))) {
            fouten.add("Paswoord en Herhaal paswoord zijn verschillend.");
        }

        // Bestaande klant controle
        if (klantDAO.findOne(nieuweKlant.getGebruikersnaam()) != null) {
            fouten.add("Er bestaat al een klant met deze gebruikersnaam.");
        }

        // Foutenlijst instellen
        if (!fouten.isEmpty()) {
            request.setAttribute("fouten", fouten);
            request.getRequestDispatcher(VIEW).forward(request, response);
        } else {

            // Klant aan database toevoegen en inloggen
            klantDAO.create(nieuweKlant);
            session.setAttribute("gebruiker", nieuweKlant.getGebruikersnaam());
            session.setAttribute("resultaat", nieuweKlant.toString());
            response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagina", "nieuwe klant");
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Resource(name = KlantDAO.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        klantDAO.setDataSource(dataSource);
    }
}
