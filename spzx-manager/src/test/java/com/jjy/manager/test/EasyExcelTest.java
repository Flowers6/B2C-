package com.jjy.manager.test;

import com.alibaba.excel.EasyExcel;
import com.jjy.model.vo.product.CategoryExcelVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 20:41
 */
public class EasyExcelTest {
    public static void main(String[] args) {
        write();
        read();
    }

    //读操作
    public static void read() {
        //定义读取excel文件的位置
        String fileName = "E:\\splunkdata\\01.xlsx";
        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>();
        //调用方法
        EasyExcel.read(fileName, CategoryExcelVo.class, excelListener).sheet().doRead();
        List<CategoryExcelVo> data = excelListener.getData();
        for (CategoryExcelVo vo : data) {
            System.out.println(vo);
        }

    }

    //写操作
    public static void write() {
        //文件路径
        String fileName = "E:\\splunkdata\\01.xlsx";

        ArrayList<CategoryExcelVo> list = new ArrayList<>();
        list.add(new CategoryExcelVo(15L, "王德法", "", 0L, 1, 1));
        list.add(new CategoryExcelVo(15L, "王德法", "", 0L, 1, 1));
        list.add(new CategoryExcelVo(15L, "王德法", "", 0L, 1, 1));
        list.add(new CategoryExcelVo(15L, "王德法", "", 0L, 1, 1));

        EasyExcel.write(fileName, CategoryExcelVo.class).sheet().doWrite(list);
    }
}
