package com;

import java.awt.*;
import javax.swing.*;
import java.lang.*;

public class CalculatorMain extends JFrame{
    JTextField textplace = new JTextField("");
    JPanel rows = new JPanel();
    JButton[][] buttons = new JButton[5][5];
    String[][] buttons_text = {{"M1S","M2S","M1R","M2R","MC"},
                               {"7","8","9","DEL","AC"},
                               {"6","5","4","*","/"},
                               {"1","2","3","+","-"},
                               {"0","(",")",".","="}};
    //初始化窗体
    public CalculatorMain(){
        super("Calculator");
        setSize(500,600);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //开始各部件的布局
          //文本框
        textplace.setPreferredSize(new Dimension(480, 100)); //文本框大小
        textplace.setHorizontalAlignment(JTextField.RIGHT); //文本框居右对齐
        //textplace.setBackground(new Color(0, 155, 137, 65)); //文本框背景色
        textplace.setFont(new Font("Times New Roman",Font.BOLD,36)); //字体参数
        textplace.setEditable(false); //文本框禁止键盘输入
        add(textplace, BorderLayout.NORTH); //设置文本框位置
          //各个按钮
        rows.setLayout(new GridLayout(5,5,3,3));
        BtnListener l = new BtnListener(this); //使用外部类处理按钮事件监听
        for(int i = 0; i < 5; i ++){
            for(int j = 0; j < 5; j++){
                buttons[i][j] = new JButton(buttons_text[i][j]);
                buttons[i][j].setFont(new Font("Times New Roman",Font.BOLD,18));
                rows.add(buttons[i][j]);
                buttons[i][j].addActionListener(l);
            }
        }
        add(rows, BorderLayout.CENTER);
        setVisible(true);
    }

    //main函数
    public static void main(String[] args){
        CalculatorMain inst = new CalculatorMain();
    }
}
