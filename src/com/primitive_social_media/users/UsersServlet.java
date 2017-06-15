package com.primitive_social_media.users;

import com.primitive_social_media.User;
import com.primitive_social_media.database.DatabaseService;
import com.primitive_social_media.database.MockDatabaseService;
import com.primitive_social_media.sessions.SessionService;
import com.primitive_social_media.sessions.SessionServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by apatters on 6/11/2017.
 */
@WebServlet(name = "UsersServlet")
public class UsersServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();
    private SessionService sessionService = new SessionService();

    public void init(){
        System.out.println("Initialized UserServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPut(request, response);
    }





    protected void getUserAfterAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");

        String query = "^"+username+"$";
        HashMap<String, User> matches = userService.findUsers(query);

        int nMatches = matches.size();
        if(nMatches==1){
            response.setStatus(HttpServletResponse.SC_OK);
            User user = matches.get(username);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(user.toJSON());
            out.flush();
        }else{
            if(nMatches>1){
                System.out.println(String.format("%d matches found for query %s", nMatches, query));
            }
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sessionService.validateThenRespond(request, response, ()->getUserAfterAuth(request, response));
    }

//    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        String location = request.getParameter("location");
//        String DOBString = request.getParameter("DOB");
//        String business = request.getParameter("business");
//        String picture = request.getParameter("picture");
//
//        User newUser = new User(name, password, location, DOBString, business, picture);
//        databaseService.updateUser(newUser);
//
//        response.setContentType("text/plain");
//        PrintWriter out = response.getWriter();
//        out.print("success");
//        out.flush();
//
//        System.out.println("Put user " + name);
//    }
//
////    protected SessionService.ValidationResultHandler deleteUser = (HttpServletRequest request, HttpServletResponse response)->{
////        String name = request.getParameter("name");
////
////        databaseService.deleteUser(name);
////
////        response.setContentType("text/plain");
////        PrintWriter out = response.getWriter();
////        out.print("success");
////        out.flush();
////
////        System.out.println("Deleted user " + name);
////    };
//
//    protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String name = request.getParameter("name");
//
//        databaseService.deleteUser(name);
//
//        response.setContentType("text/plain");
//        PrintWriter out = response.getWriter();
//        out.print("success");
//        out.flush();
//
//        System.out.println("Deleted user " + name);
//    };
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        sessionService.validateThenRespond(request, response, ()->deleteUser(request, response));
//    }

    public void destroy(){

//        databaseService.close();
        System.out.println("destroyed UserServlet");
    }
}
