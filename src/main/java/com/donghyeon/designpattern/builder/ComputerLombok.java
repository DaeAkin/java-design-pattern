package com.donghyeon.designpattern.builder;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ComputerLombok {
    //required
    private String HDD;
    private String RAM;

    //optional
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;
}
