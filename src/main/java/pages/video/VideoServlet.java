package pages.video;

import BL.UserService;
import BL.VideoService;
import DAO.NoteDAO;
import DAO.UserDAO;
import models.Note;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/video")
public class VideoServlet extends HttpServlet {
    private VideoService videoService = new VideoService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("auth", req.getSession().getAttribute("current_user"));
        req.setAttribute("username", userService.getUsernameByEmail((String) req.getSession().getAttribute("current_user")));
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/video.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       videoService.saveNote(req, resp);
    }

}
