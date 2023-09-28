package com.example.demo.common.model;

import com.example.demo.common.entity.Cabinet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabinetModel {
    private Cabinet cabinet;
    private List<BoxStorageModel> boxStorages;
}
