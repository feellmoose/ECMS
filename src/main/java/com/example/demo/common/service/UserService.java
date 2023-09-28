package com.example.demo.common.service;

import com.example.demo.common.entity.Box;
import com.example.demo.common.entity.Cabinet;
import com.example.demo.common.entity.Record;

import java.util.List;

public interface UserService {
    void getCabinets();

    List<Record> getPersonalRecords();

    void sendRequest(Cabinet cabinet, Box box, Integer size);//to who?

    void openCabinet(Cabinet cabinet, Box box);

}
