package com.property.mapper;

import com.property.pojo.Bill;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BillMapper {
    int insertBills(List<Bill> bills);
}
