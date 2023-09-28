package com.example.demo.common.model;

import com.example.demo.common.entity.Box;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxStorageModel {
    private Box box;
    private Storage storage;
}
