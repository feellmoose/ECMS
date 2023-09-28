package com.example.demo.common.service.impl;

import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.entity.Record;
import com.example.demo.common.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void getCabinets() {

    }

    @Override
    public List<Record> getPersonalRecords() {
        return null;
    }

    @Override
    public void sendRequest(Cabinet cabinet, Box box, Integer size) {

    }

    @Override
    public void openCabinet(Cabinet cabinet, Box box) {

    }
}
