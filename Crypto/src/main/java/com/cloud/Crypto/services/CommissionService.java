package com.cloud.Crypto.service;

import model.Commission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloud.Crypto.repository.CommissionRepository;

import java.sql.Date;
import java.util.List;

@Service
public class CommissionService {

    @Autowired
    private CommissionRepository commissionRepository;

    public Commission insert(Commission commission) {
        return commissionRepository.insert(commission);
    }

    public List<Commission> getAll() {
        return commissionRepository.getAll();
    }

    public List<Commission> getAllByDate(Date dateMax) {
        return commissionRepository.getAllByDate(dateMax);
    }
}
