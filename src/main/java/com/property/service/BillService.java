package com.property.service;

import com.property.mapper.BillMapper;
import com.property.pojo.Bill;
import com.property.pojo.BillDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/9 23:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BillService {

    private final BillMapper billMapper;

    public BillService(BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    public int insertBills(List<Bill> bills) {
        return billMapper.insertBills(bills);
    }

    public List<Bill> getAllBills() {
        return billMapper.getAllBills();
    }

    public List<BillDetail> getBillDetailByName(String name) {
        return billMapper.getBillDetailByName(name);
    }

    public List<Bill> getBillsByPropertyId(Integer id) {
        return billMapper.getBillsByPropertyId(id);
    }

    public List<Bill> getPaidBillsByPropertyId(Integer id) {
        return billMapper.getPaidBillsByPropertyId(id);
    }

    public int payBill(Bill bill) {
        return billMapper.payBill(bill);
    }

    public Bill getBillById(Integer id) {
        return billMapper.getBillById(id);
    }
}
