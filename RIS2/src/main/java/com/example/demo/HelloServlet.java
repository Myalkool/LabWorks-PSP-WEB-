package com.example.demo;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import com.example.demo.ejb.TicketManagerImpl;
import com.example.demo.entity.TrainTicket;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.util.logging.Logger;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(HelloServlet.class.getName());
    @EJB
    private TicketManagerImpl ticketManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            List<TrainTicket> trainTicketList =new ArrayList<>();

            String amount = request.getParameter("price");

            if (amount != null && amount.length() > 0) {

                BigDecimal price = new BigDecimal(amount);
                trainTicketList = ticketManager.getTickets(price);


            } else {

                trainTicketList = ticketManager.getTickets();

            }

            request.setAttribute("tickets", trainTicketList);
            request.setAttribute("trains", ticketManager.getTrainNumbers());
            getServletContext().getRequestDispatcher("/Train.jsp").forward(request, response); //переадресация на страницу


    }
}