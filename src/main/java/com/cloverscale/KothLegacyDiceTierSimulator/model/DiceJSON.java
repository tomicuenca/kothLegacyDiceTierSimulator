package com.cloverscale.KothLegacyDiceTierSimulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiceJSON {
    private Integer rolls;
    private List<Dice> diceList = new ArrayList<>();
}
