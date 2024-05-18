package com.example.demo.ejb;


import com.example.demo.entity.TicketClass;
import com.example.demo.entity.TrainTicket;
import com.example.demo.entity.Type;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class TicketManagerImpl  {

    private List<TrainTicket> trainTickets;

    public TicketManagerImpl() {
        trainTickets = new ArrayList<>();
        trainTickets.add(new TrainTicket(1L, 10, Type.COMPARTMENT_SEAT, TicketClass.Economy_Class ));
        trainTickets.add(new TrainTicket(2L, 100, Type.COMPARTMENT_SEAT, TicketClass.Economy_Class ));
        trainTickets.add(new TrainTicket(3L, 1000, Type.RESERVED_SEAT, TicketClass.Economy_Class ));
        trainTickets.add(new TrainTicket(4L, 10000, Type.COMPARTMENT_SEAT, TicketClass.Economy_Class ));
        trainTickets.add(new TrainTicket(3L, 3000, Type.COMPARTMENT_SEAT, TicketClass.Premium_Class ));
        trainTickets.add(new TrainTicket(1L, 1045, Type.RESERVED_SEAT, TicketClass.Economy_Class ));
        trainTickets.add(new TrainTicket(2L, 570, Type.RESERVED_SEAT, TicketClass.Economy_Class ));
        trainTickets.add(new TrainTicket(3L, 1470, Type.COMPARTMENT_SEAT, TicketClass.Economy_Class ));
        trainTickets.add(new TrainTicket(4L, 100300, Type.RESERVED_SEAT, TicketClass.Premium_Class ));
        trainTickets.add(new TrainTicket(3L, 3000, Type.COMPARTMENT_SEAT, TicketClass.Premium_Class ));

    }

    //@Override
    public byte[] writeInFile(Long trainNumber) {
        List<TrainTicket> trainTicketList =  trainTickets
                .stream().filter(x -> Objects.equals(x.getTrainNumber(), trainNumber))
                .collect(Collectors.toList());

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Train tickets");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Train number");
        row.createCell(1).setCellValue("Price");
        row.createCell(2).setCellValue("Type");
        row.createCell(3).setCellValue("TicketClass");
        int rowNum = 1;
        for(TrainTicket trainTicket: trainTicketList){
            Row valueRow = sheet.createRow(rowNum);
            valueRow.createCell(0).setCellValue(trainTicket.getTrainNumber());
            valueRow.createCell(1).setCellValue(trainTicket.getPrice().doubleValue());
            valueRow.createCell(2).setCellValue(trainTicket.getType().toString());
            valueRow.createCell(3).setCellValue(trainTicket.getTicketClass().toString());
            rowNum ++;
        }

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }



    //@Override
    public void addTickets(List<TrainTicket> trainTickets) {
        this.trainTickets = trainTickets;

    }

    //@Override
    public void addTicket(TrainTicket trainTicket) {
        this.trainTickets.add(trainTicket);
    }

    //@Override
    public List<TrainTicket> getTickets() {
        return trainTickets;
    }

    //@Override
    public List<TrainTicket> getTickets(BigDecimal price) {
        return trainTickets.stream().filter(x -> x.getPrice().compareTo(price) <= 0)
                .collect(Collectors.toList());
    }

    public Set<Long> getTrainNumbers() {
        return trainTickets.stream().map(TrainTicket::getTrainNumber)
                .collect(Collectors.toSet());
    }
}
