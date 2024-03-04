package org.example;

import org.example.service.FileService;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class Servlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        FileService fileService = new FileService();
        String path = req.getParameter("path");
        File[] directories = fileService.getDirectories(path);

        if (directories == null) {
            directories = new File[0];
        }

        File[] files = fileService.getFiles(path);
        if (files == null) {
            files = new File[0];
        }

        req.setAttribute("directories", directories);
        req.setAttribute("files", files);
        req.getRequestDispatcher("servlet.jsp").forward(req, resp);
    }
}