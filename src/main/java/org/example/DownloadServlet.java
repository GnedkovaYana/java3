package org.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.*;
import java.net.URLEncoder;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String filePath = request.getParameter("path");

        if (filePath == null) {
            return;
        }

        File file = new File(filePath);

        if (!file.exists()) {
            return;
        }

        response.setContentLengthLong(file.length());

        response.setHeader("Content-Disposition", "attachment; filename=\""
                + URLEncoder.encode(file.getName(), "UTF-8") + "\"");


        FileInputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[4096];

        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }
}