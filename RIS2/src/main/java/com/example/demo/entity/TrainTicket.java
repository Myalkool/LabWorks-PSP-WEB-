package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class TrainTicket {

    private Long trainNumber;
    private BigDecimal price;
    private Type type;
    private TicketClass ticketClass;


    public TrainTicket() {
    }

    public TrainTicket(Long trainNumber, int price, Type type, TicketClass ticketClass) {
        this.trainNumber = trainNumber;
        this.price = new BigDecimal(price);
        this.type = type;
        this.ticketClass = ticketClass;
    }

    public TrainTicket(Long trainNumber, BigDecimal price, Type type, TicketClass ticketClass) {
        this.trainNumber = trainNumber;
        this.price = price;
        this.type = type;
        this.ticketClass = ticketClass;
    }



    public Long getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Long trainNumber) {
        this.trainNumber = trainNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public TicketClass getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(TicketClass ticketClass) {
        this.ticketClass = ticketClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainTicket that = (TrainTicket) o;
        return Objects.equals(trainNumber, that.trainNumber) && Objects.equals(price, that.price) && type == that.type && ticketClass == that.ticketClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainNumber, price, type, ticketClass);
    }
}


