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



        str.append("Dice: ");
        for (Dice dice: diceList){
            str.append(dice.getAmount() + "D" + dice.getFaces() + ", ");
        }
        str.append(rolls + " rolls:");
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
        str.append("Crit avg: " + critAvg);
        str.append(System.lineSeparator());
        str.append("Elemental positive avg: " + elemPosAvg);
        str.append(System.lineSeparator());
        str.append("Elemental negative avg: " + elemNegAvg);
        return ResponseEntity.ok(str.toString());
    }
}
