package com.yrw.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(50)
@HeadFontStyle(fontName = "Arial", color = 12)
public class Player {
    @ExcelIgnore
    private String uuid;

//    @ExcelIgnore
//    private String nid;

    @ExcelProperty("编号")
    private String playerId;

//    @ExcelIgnore
//    private String tourId;

//    @ExcelIgnore
//    private String firstName;

//    @ExcelIgnore
//    private String lastName;

    @ExcelProperty("名称")
    @ColumnWidth(20)
    private String fullName;

    @ExcelIgnore
    private String shortName;

    @ExcelProperty("性别")
    @ColumnWidth(5)
    private String gender;

    @ExcelIgnore
    private Nationality nationality;

    @ExcelProperty("头像地址")
    private String heroImage;

//    @ExcelIgnore
//    private Object[] rankings;

    @ExcelProperty("出生地")
    @ColumnWidth(27)
    private String birthPlace;

    @ColumnWidth(20)
    @ExcelProperty("出生日期")
    private Date dob;

//    @ExcelIgnore
//    private Integer careerLoses;

    @ExcelProperty("生涯奖金")
    @ColumnWidth(11)
    private String careerPrizeMoney;

//    @ExcelIgnore
//    private Integer careerTitles;

//    @ExcelIgnore
//    private Integer careerWins;

    @ExcelProperty("教练")
    @ColumnWidth(30)
    private String coach;

//    @ExcelIgnore
//    private Double playerHeight;

//    @ExcelIgnore
//    private Double playerWeight;

//    @ExcelIgnore
//    private Integer turnedPro;

//    @ExcelIgnore
//    private String residentOf;

//    @ExcelIgnore
//    private Object[] eventsContested;

    @ExcelProperty("个人介绍链接")
    @ColumnWidth(55)
    private String profileLink;

    @Override
    public String toString() {
        return  "fullName: " + fullName + '\n' +
                "gender: " + gender + '\n' +
                "nationality: " + nationality + "\n" +
                "birthPlace: " + birthPlace + '\n' +
                "careerPrizeMoney: " + careerPrizeMoney + '\n' +
                "coach: " + coach + '\n' +
                "-------\n";
    }
}
