package pages;

import BL.CommentService;
import BL.NoteService;
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

import static java.lang.Integer.parseInt;

@WebServlet("/video")
public class VideoServlet extends HttpServlet {
    private NoteService noteService = new NoteService();
    private UserService userService = new UserService();
    private VideoService videoService = new VideoService();
    private CommentService commentService = new CommentService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("auth", req.getSession().getAttribute("current_user"));
        req.setAttribute("username", userService.getUsernameByEmail((String) req.getSession().getAttribute("current_user")));
        req.setAttribute("video", videoService.findVideoById(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("comments", commentService.getComments(Integer.parseInt(req.getParameter("id"))));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/video.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        commentService.sendComment(req, resp);
        noteService.saveNote(Integer.parseInt(req.getParameter("id")), req, resp);


    }


}
