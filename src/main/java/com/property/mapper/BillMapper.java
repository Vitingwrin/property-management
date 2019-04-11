package com.property.mapper;

import com.property.pojo.Bill;
import com.property.pojo.BillDetail;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BillMapper {
    int insertBills(List<Bill> bills);
    List<Bill> getAllBills();
    List<BillDetail> getBillDetailByName(String name);
    List<Bill> getBillsByPropertyId(Integer id);
    List<Bill> getPaidBillsByPropertyId(Integer id);
    int payBill(Bill bill);
    Bill getBillById(Integer id);
}
