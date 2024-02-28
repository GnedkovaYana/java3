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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        FileService fileService = new FileService();
        String path = request.getParameter("path");
        File[] directories = fileService.getDirectories(path);

        if (directories == null) {
            directories = new File[0];
        }

        File[] files = fileService.getFiles(path);
        if (files == null) {
            files = new File[0];
        }

        request.setAttribute("directories", directories);
        request.setAttribute("files", files);
        request.getRequestDispatcher("servlet.jsp").forward(request, response);
    }
}