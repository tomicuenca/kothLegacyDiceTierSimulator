package com.cloverscale.KothLegacyDiceTierSimulator.service;

import com.cloverscale.KothLegacyDiceTierSimulator.model.Dice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
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
        for(int i = 0; i < rolls; i++){
            Integer currentValue = 0;
            for(Dice dice: diceList){
                currentValue = currentValue + dice.rollAll();
            }
            rollList.add(currentValue);
        }
        return average(rollList);
    }
    public Integer getMax(List<Dice> diceList){
        Integer result = 0;
        for (Dice dice: diceList){
            result = result + (dice.getAmount() * dice.getFaces());
        }
        return result;
    }
    public Integer getCritAvg(List<Dice> diceList, Integer rolls){
        List<Integer> rollList = new ArrayList<>();
        Boolean critHit = false;
        diceList.sort((d1, d2) -> d2.getFaces() - d1.getFaces());
        for(int i = 0; i < rolls; i++) {
            Integer currentValue = 0;
            for (Dice dice : diceList) {
                List<Integer> currentRollList = new ArrayList<>();
                if (critHit == false) {
                    Boolean currentHit = false;
                    for (int j = 0; j < dice.getAmount(); j++) {
                        if (currentHit == false) {
                            Integer currentRoll = dice.roll();
                            if (currentRoll == dice.getFaces()) {
                                if (dice.getAmount() > 1) {
                                    currentHit = true;
                                } else {
                                    critHit = true;
                                }
                            }
                            currentRollList.add(currentRoll);
                        } else {
                            currentRollList.add(dice.getFaces());
                            currentHit = false;
                        }
                    }
                    if(currentHit == true){
                        currentRollList.sort((d1, d2) -> d1 - d2);
                        List<Integer> updatedCurrentRollList = new ArrayList<>();
                        Boolean first = true;
                        for (Integer element: currentRollList){
                            if (first){
                                updatedCurrentRollList.add(dice.getFaces());
                                first = false;
                            }
                            else{
                                updatedCurrentRollList.add(element);
                            }
                        }
                        currentRollList = updatedCurrentRollList;
                    }
                    for (Integer value: currentRollList){
                        currentValue = currentValue + value;
                    }
                } else {
                    currentValue = currentValue + (dice.getAmount() * dice.getFaces());
                }
            }
            rollList.add(currentValue);
        }
        return average(rollList);
    }
    public Integer getElemPosAvg(List<Dice> diceList, Integer rolls){
        List<Integer> rollList = new ArrayList<>();
        Integer first;
        Integer second;
        for(int i = 0; i < rolls; i++){
            Integer currentValue = 0;
            for(Dice dice: diceList){
                for (int j = 0; j < dice.getAmount(); j++){
                    first = dice.roll();
                    second = dice.roll();
                    if (first > second){
                        currentValue = currentValue + first;
                    }
                    else {
                        currentValue = currentValue + second;
                    }
                }
            }
            rollList.add(currentValue);
        }
        return average(rollList);
    }
    public Integer getElemNegAvg(List<Dice> diceList, Integer rolls){
        List<Integer> rollList = new ArrayList<>();
        Integer first;
        Integer second;
        for(int i = 0; i < rolls; i++){
            Integer currentValue = 0;
            for(Dice dice: diceList){
                for (int j = 0; j < dice.getAmount(); j++){
                    first = dice.roll();
                    second = dice.roll();
                    if (first < second){
                        currentValue = currentValue + first;
                    }
                    else {
                        currentValue = currentValue + second;
                    }
                }
            }
            rollList.add(currentValue);
        }
        return average(rollList);
    }


    private Integer average(List<Integer> rollList){
        Integer result = 0;
        for (Integer value: rollList){
            result = result + value;
        }
        result = result / rollList.size();
        return result;
    }
}