package com.cloverscale.KothLegacyDiceTierSimulator.service;

import com.cloverscale.KothLegacyDiceTierSimulator.model.Dice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiceService {


    public Integer getMin(List<Dice> diceList){
        Integer result = 0;
        for (Dice dice: diceList){
            result = result + dice.getAmount();
        }
        return result;
    }
    public Integer getAverage(List<Dice> diceList, Integer rolls){
        List<Integer> rollList = new ArrayList<>();
        Integer currentValue;
        Integer result = 0;
        for(int i = 0; i < rolls; i++){
            currentValue = 0;
            for(Dice dice: diceList){
                currentValue = currentValue + dice.rollAll();
            }
            rollList.add(currentValue);
        }
        for (Integer value: rollList){
            result = result + value;
        }
        result = result / rollList.size();
        return result;
    }
    public Integer getMax(List<Dice> diceList){
        Integer result = 0;
        for (Dice dice: diceList){
            result = result + (dice.getAmount() * dice.getFaces());
        }
        return result;
    }
    public Integer getCritAvg(List<Dice> diceList, Integer rolls){
        return 0;
    }
    public Integer getElemPosAvg(List<Dice> diceList, Integer rolls){
        return 0;
    }
    public Integer getElemNegAvg(List<Dice> diceList, Integer rolls){
        return 0;
    }
}
