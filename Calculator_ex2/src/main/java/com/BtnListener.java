package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtnListener implements ActionListener {
    private CalculatorMain inst;
    private String infix = new String("");
    private double ans = 0;
    private String[] memories = {"",""};
    public BtnListener(CalculatorMain inst){
        this.inst = inst;
    }

    public void actionPerformed(ActionEvent event){
        JButton button = (JButton) event.getSource();
        if(button.getText().equals("=")){
            try {
                Function inst_func = new Function();
                ans = inst_func.computeResult(infix);
                inst.textplace.setText(String.valueOf(ans));
                infix = "";
            }catch(Exception e){
                inst.textplace.setText("ERROR");
            }
        }
        else if(button.getText().equals("M1S")){
            memories[0] = infix;
        }
        else if(button.getText().equals("M2S")){
            memories[1] = infix;
        }
        else if(button.getText().equals("M1R")){
            infix += memories[0];
            inst.textplace.setText(infix);
        }
        else if(button.getText().equals("M2R")){
            infix += memories[1];
            inst.textplace.setText(infix);
        }
        else if(button.getText().equals("MC")){
            memories[0] = "";
            memories[1] = "";
        }
        else if(button.getText().equals("AC")){
            infix = "";
            inst.textplace.setText(infix);
        }
        else if(button.getText().equals("DEL")){
            if(infix.length()!=0){
                infix = infix.substring(0,infix.length() - 1);
            }
            inst.textplace.setText(infix);
        }
        //数字和操作符直接加到string上
        else{
            infix = infix + button.getText();
            inst.textplace.setText(infix);
        }
    }
}
