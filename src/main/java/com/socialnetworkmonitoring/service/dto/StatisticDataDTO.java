package com.socialnetworkmonitoring.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDataDTO implements Serializable {
    private String x;
    private BigInteger y;
}
