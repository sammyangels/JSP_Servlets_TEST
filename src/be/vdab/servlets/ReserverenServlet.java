package be.vdab.servlets;

import be.vdab.dao.VoorstellingDAO;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Samuel Engelen on 15/05/15.
 */
@WebServlet(urlPatterns = "/reserveren.htm", name = "ReserverenServlet")
public class ReserverenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/reserveren.jsp";
    private static final String REDIRECT_URL = "%s/reservatiemandje.htm";

    private final transient VoorstellingDAO voorstellingDAO = new VoorstellingDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagina", "reserveren");

        // Voorstelling opzoeken
        Voorstelling voorstelling = voorstellingDAO.findOne(Long.parseLong(request.getParameter("voorstellingsid")));
        if ( voorstelling != null ) {
            request.setAttribute("voorstelling", voorstelling);
            Long voorstellingsid = voorstelling.getId();

            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            Map<Long,Long> mandje = ((Map<Long, Long>) session.getAttribute("mandje"));

            // Mandje aanmaken indien het nog niet bestaat
            if (mandje == null) {
                mandje = new HashMap<>();
            }

            // Aantal plaatsen controleren en reservaties toevoegen aan mandje
            try {
                Long plaatsen = Long.parseLong(request.getParameter("plaatsen"));
                if (plaatsen < 1 || plaatsen > voorstelling.getVrijeplaatsen()) {
                    request.setAttribute("foutmelding", "Tik een getal tussen 1 en " + voorstelling.getVrijeplaatsen());
                    request.getRequestDispatcher(VIEW).forward(request, response);
                } else {
                    mandje.put(voorstellingsid, plaatsen);
                    session.setAttribute("mandje", mandje);
                    response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pagina", "reserveren");

        // Voorstelling opzoeken
        Voorstelling voorstelling = voorstellingDAO.findOne(Long.parseLong(request.getParameter("voorstellingsid")));

        // Eerder ingevoerde aantal plaatsen invullen
        if ( voorstelling != null ) {
            request.setAttribute("voorstelling", voorstelling);
            Long voorstellingsid = voorstelling.getId();
            HttpSession session = request.getSession();
            if (session.getAttribute("mandje") != null) {
                @SuppressWarnings("unchecked")
                Map<Long,Long> mandje = ((Map<Long, Long>) session.getAttribute("mandje"));
                if ( mandje.containsKey(voorstellingsid)) {
                    request.setAttribute("plaatsen", mandje.get(voorstellingsid));
                }
            }
        }
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Resource(name = VoorstellingDAO.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        voorstellingDAO.setDataSource(dataSource);
    }
}
