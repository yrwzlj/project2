package com.yrw.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchResult {
    @ExcelProperty("比赛时间")
    @ColumnWidth(20)
    private String date;

    @ExcelProperty("开始时间")
    private String startTime;

    @ExcelProperty("获胜者")
    @ColumnWidth(20)
    private String winner;

    @ExcelProperty("比分")
    @ColumnWidth(22)
    private String score;

    @Override
    public String toString() {
        return  "time: " + startTime + '\n' +
                "winner: " + winner + '\n' +
                "score: " + score + '\n' +
                "-----\n";
    }
}
