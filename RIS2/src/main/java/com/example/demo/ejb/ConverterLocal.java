package com.example.demo.ejb;


import jakarta.ejb.Local;

import java.math.BigDecimal;

@Local
public interface ConverterLocal {
    BigDecimal dollarToYen(BigDecimal dollars);
    BigDecimal yenToEuro(BigDecimal yen);
}
