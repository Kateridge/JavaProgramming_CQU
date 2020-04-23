package com;

import java.util.*;

public class Function {
    private Map<String, Integer> priority;

    public Function() {
        //初始化运算符的优先级
        priority = new HashMap<String, Integer>();
        priority.put("#", -1); //设置为栈底元素，方便操作符的弹出
        priority.put("(", 0); //括号手动弹出，优先级设为最低
        priority.put(")", 0);
        priority.put("+", 1);
        priority.put("-", 1);
        priority.put("*", 2);
        priority.put("/", 2);
    }

    public boolean isPriority(String a,String b){
        return priority.get(a) <= priority.get(b);
    }

    //中缀转后缀
    public Queue<String> toSuffix(String expression){
        Queue<String> opQueue = new LinkedList<String>();
        Stack<String> opStack = new Stack<String>();
        opStack.push("#");
        String temp = "";
        int start = 0;
        int end = 0;
        //遍历表达式
        for(int i = 0; i < expression.length(); i++){
            //一次取出一个char并转化为字符串类型
            temp = String.valueOf(expression.charAt(i));
            //如果这个字符是数字或小数点，则记录下来
            if(temp.matches("[0-9]|\\.")) end++;
            //如果这个字符不是数字
            else{
                if (temp.equals("(")) {
                    opStack.push(temp);
                }
                else{
                    if(start != end)
                        opQueue.add(expression.substring(start, end));
                    // 如果是右括号，执行出栈操作，并将出栈的元素放入队列，直到弹出栈的是左括号，最后将左括号弹出
                    if (temp.equals(")")) {
                        while (!opStack.peek().equals("(")) {
                            opQueue.add(opStack.pop());
                        }
                        opStack.pop();
                    } else {
                        // 如果不是右括号，弹出所有优先级大于或者等于该运算符的栈顶元素，然后将该运算符入栈
                        while (isPriority(temp, opStack.peek())) {
                            opQueue.add(opStack.pop());
                        }
                        opStack.push(temp);
                    }
                }
                start = end = i + 1;
            }
        }
        //将最后一个数字放入队列
        if(start != end){
            opQueue.add(expression.substring(start, end));
        }
        //将运算符放入队列
        int opStack_length = opStack.size();
        for (int i = 0; i < opStack_length - 1; i++) {
            opQueue.add(opStack.pop());
        }
        return opQueue;
    }

    //计算结果
    public double computeResult(String expression){
        Queue<String> sufQueue = toSuffix(expression);
        Stack<String> sufStack = new Stack<String>();
        String temp = "";
        double leftop = 0;
        double rightop = 0;
        double result = 0;
        //从前往后遍历后缀表达式
        int sufQueue_length = sufQueue.size();
        for(String str:sufQueue){
            System.out.print(str);
        }
        for(int i = 0; i < sufQueue_length; i++){
            temp = sufQueue.poll();
            //碰到数字直接入栈
            if(temp.matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])")){
                sufStack.push(temp);
            }
            //每碰到一个操作符，从栈中依次弹出两个数字进行运算
            else{
                rightop = Double.parseDouble(sufStack.pop());
                leftop = Double.parseDouble(sufStack.pop());
                if (temp.equals("+")) {
                    result = leftop + rightop;
                } else if (temp.equals("-")) {
                    result = leftop - rightop;
                } else if (temp.equals("*")) {
                    result = leftop * rightop;
                } else if (temp.equals("/")) {
                    try {
                        result = leftop / rightop;
                    } catch (Exception e) {
                        return 0;
                    }
                }
                //运算得到的结果重新压入栈中，作为下一次运算的操作数
                sufStack.push(String.valueOf(result));
            }
        }
        return Double.parseDouble(sufStack.get(0));
    }
}
