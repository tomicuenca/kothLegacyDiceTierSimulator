package com.cloverscale.KothLegacyDiceTierSimulator.controller;

import com.cloverscale.KothLegacyDiceTierSimulator.model.Dice;
import com.cloverscale.KothLegacyDiceTierSimulator.service.DiceService;
import lombok.extern.slf4j.Slf4j;
import com.cloverscale.KothLegacyDiceTierSimulator.model.DiceJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dice")
@Slf4j
public class DiceController {

    @Autowired
    private DiceService diceService;

    @PostMapping(value = "/rollAll")
    public ResponseEntity<String> rollAll(@RequestBody DiceJSON request) {
        StringBuilder str = new StringBuilder();
        Integer rolls = request.getRolls();
        List<Dice> diceList = request.getDiceList();
        Integer min = diceService.getMin(diceList);
        Integer avg = diceService.getAverage(diceList, rolls);
        Integer max = diceService.getMax(diceList);
        Integer critAvg = diceService.getCritAvg(diceList, rolls);
        Integer elemPosAvg = diceService.getElemPosAvg(diceList, rolls);
        Integer elemNegAvg = diceService.getElemNegAvg(diceList, rolls);
        Integer critElemPosAvg = diceService.getCritElemPosAvg(diceList, rolls);
        Integer critElemNegAvg = diceService.getCritElemNegAvg(diceList, rolls);

        str.append("Dice: ");
        for (Dice dice: diceList){
            str.append(dice.getAmount() + "D" + dice.getFaces() + ", ");
        }
        str.append(rolls);
        if(rolls == 1){
            str.append(" roll:");
        }else{
            str.append(" rolls:");
        }
        str.append(System.lineSeparator());
        str.append(System.lineSeparator());
        str.append("Min: " + min);
        str.append(System.lineSeparator());
        if(rolls == 1){
            str.append("Result: ");
        }else{
            str.append("Avg: ");
        }
        str.append(avg);
        str.append(System.lineSeparator());
        str.append("Max: " + max);
        str.append(System.lineSeparator());
        str.append(System.lineSeparator());
        if(rolls == 1){
            str.append("Crit: ");
        }else{
            str.append("Crit avg: ");
        }
        str.append(critAvg);
        str.append(System.lineSeparator());
        if(rolls == 1){
            str.append("Elemental positive: ");
        }else{
            str.append("Elemental positive avg: ");
        }
        str.append(elemPosAvg);
        str.append(System.lineSeparator());
        if(rolls == 1){
            str.append("Elemental negative: ");
        }else{
            str.append("Elemental negative avg: ");
        }
        str.append(elemNegAvg);
        str.append(System.lineSeparator());
        str.append(System.lineSeparator());
        if(rolls == 1){
            str.append("Elemental positive with crit: ");
        }else{
            str.append("Elemental positive with crit avg: ");
        }
        str.append(critElemPosAvg);
        str.append(System.lineSeparator());
        if(rolls == 1){
            str.append("Elemental negative with crit: ");
        }else{
            str.append("Elemental negative with crit avg: ");
        }
        str.append(critElemNegAvg);
        return ResponseEntity.ok(str.toString());
    }

    @PostMapping(value = "/rollMainStats")
    public ResponseEntity<String> rollMainStats(@RequestBody DiceJSON request) {
        StringBuilder str = new StringBuilder();
        Integer rolls = request.getRolls();
        List<Dice> diceList = request.getDiceList();
        Integer critAvg = diceService.getCritAvg(diceList, rolls);
        Integer max = diceService.getMax(diceList);
        Integer min = diceService.getMin(diceList);
        Integer critElemPosAvg = diceService.getCritElemPosAvg(diceList, rolls);
        Integer critElemNegAvg = diceService.getCritElemNegAvg(diceList, rolls);

        str.append("Dice: ");
        for (Dice dice: diceList){
            str.append(dice.getAmount() + "D" + dice.getFaces() + ", ");
        }
        str.append(rolls);
        if(rolls == 1){
            str.append(" roll:");
        }else{
            str.append(" rolls:");
        }
        str.append(System.lineSeparator());
        str.append(System.lineSeparator());
        if(rolls == 1){
            str.append("Crit: ");
        }else{
            str.append("Crit avg: ");
        }
        str.append(critAvg);
        str.append(System.lineSeparator());
        str.append("Max: " + max);
        str.append(System.lineSeparator());
        str.append("Min: " + min);
        str.append(System.lineSeparator());
        if(rolls == 1){
            str.append("Elemental positive with crit: ");
        }else{
            str.append("Elemental positive with crit avg: ");
        }
        str.append(critElemPosAvg);
        str.append(System.lineSeparator());
        if(rolls == 1){
            str.append("Elemental negative with crit: ");
        }else{
            str.append("Elemental negative with crit avg: ");
        }
        str.append(critElemNegAvg);
        return ResponseEntity.ok(str.toString());
    }
}
