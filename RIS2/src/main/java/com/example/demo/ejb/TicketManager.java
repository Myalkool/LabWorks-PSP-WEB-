package com.example.demo.ejb;


import com.example.demo.entity.TrainTicket;
import jakarta.ejb.Local;

import java.math.BigDecimal;
import java.util.List;

@Local
public interface TicketManager {
    void writeInFile(BigDecimal price);
    void addTickets(List<TrainTicket> trainTickets);
    void addTicket(TrainTicket trainTicket);
    List<TrainTicket> getTickets();
    List<TrainTicket> getTickets(BigDecimal price);

}
