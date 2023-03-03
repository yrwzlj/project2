package org.example;

import com.yrw.core.Output;

import java.io.*;
import java.util.regex.Pattern;


public class AOSearch {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("参数数量错误");
            return;
        }
        Output output = new Output();

        try {
            BufferedWriter bufferedWriter  = new BufferedWriter(new FileWriter(args[1]));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
            while (true) {
                String command = bufferedReader.readLine();
                if (command == null) {
                    return;
                }
                command = command.trim();
                if (null == command || "".equals(command)) {
                    //bufferedWriter.write("--------结束本次使用------");
                    return;
                }
                if ("players".equals(command)) {
                    output.outputPlayers(true,bufferedWriter);
                    //bufferedWriter.write("-----------\n");
                    bufferedWriter.flush();
                    continue;
                }
                if (Pattern.matches("result 01(1[6-9]|2[0-9])", command)) {
                    output.outputResultByDate(command.split(" ")[1],true,bufferedWriter);
                    //bufferedWriter.write("-----------\n");
                    bufferedWriter.flush();
                    continue;
                }
                String[] s = command.split(" ");
                if (command.length() > 6 && "result".equals(command.substring(0,6))) {
                    bufferedWriter.write("N/A \n");
                    bufferedWriter.write("-----------\n");
                    bufferedWriter.flush();
                    continue;
                }
                bufferedWriter.write("ERROR \n-----------\n");
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
