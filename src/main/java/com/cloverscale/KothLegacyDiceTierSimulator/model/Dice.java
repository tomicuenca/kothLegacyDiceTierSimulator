package com.cloverscale.KothLegacyDiceTierSimulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dice {

    private Integer faces;
    private Integer amount;

    public Integer roll(){
        Random random = new Random();
        return random.nextInt(faces) + 1;
    }

    public Integer rollAll(){
        Integer total = 0;
        for(int i = 0; i < amount; i++){
            total = total + this.roll();
        }
        return total;
    }
}
