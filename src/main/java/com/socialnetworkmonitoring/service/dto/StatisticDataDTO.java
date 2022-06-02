package com.socialnetworkmonitoring.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDataDTO implements Serializable {
    private List<String> labels;
    private List<StatisticSetDTO> statisticSet;
}
