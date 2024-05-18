package com.example.demo;

import com.example.demo.ejb.TicketManagerImpl;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "d", value = "/download")
public class DownloadServlet extends HttpServlet {

    @EJB
    TicketManagerImpl ticketManager;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trainNumber = req.getParameter("trainNumber");


        byte[] excelData = ticketManager.writeInFile(Long.parseLong(trainNumber));

        if(excelData!=null){
            resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            resp.setHeader("Content-Disposition", "attachment; filename=tickets.xlsx");
            resp.setContentLength(excelData.length);
            resp.getOutputStream().write(excelData);
        }


    }
}
