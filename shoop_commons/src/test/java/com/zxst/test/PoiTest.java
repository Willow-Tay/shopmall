package com.zxst.test;

import com.zxst.shoop.entity.Banner;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PoiTest {

    //导出
     //@Test
     public void test1() throws Exception {
         //从数据库中获取的数据
         List<Banner> list = new ArrayList<Banner>();
         for (int i = 0; i < 10 ; i++) {
             Banner b = new Banner();
             b.setBid(i+1);
             b.setBimg("img"+i+".jpg");
             list.add(b);
         }
         //使用apache 的poi工具导出数据到excel文件中
         //系统中的一个excel文件
         XSSFWorkbook wb = new XSSFWorkbook();
         //创建sheet页
         XSSFSheet sheet = wb.createSheet("首页轮播图");
         //创建sheet中一行
         XSSFRow row = sheet.createRow(0);
         //通过行对象创建单元格
         XSSFCell cell0 = row.createCell(0);
         cell0.setCellValue("编号");

         XSSFCell cell1 = row.createCell(1);
         cell1.setCellValue("图片名称");

         for (int i = 0; i < list.size(); i++) {
             XSSFRow row2 = sheet.createRow(i+1);
             XSSFCell cell2 = row2.createCell(0);
             cell2.setCellValue(list.get(i).getBid());
             XSSFCell cell3 = row2.createCell(1);
             cell3.setCellValue(list.get(i).getBimg());
         }
         String path = "C:\\Users\\Administrator\\Desktop\\2503\\banner.xlsx";
         File file = new File(path);
         FileOutputStream fileOutputStream = new FileOutputStream(file);
         //将excel文件对象中的值传输到指定文件中
         wb.write(fileOutputStream);
         fileOutputStream.flush();
         wb.close();
         fileOutputStream.close();
     }

    //导入
    //@Test
    public void test2() throws Exception{
        String path = "C:\\Users\\Administrator\\Desktop\\2503\\banner.xlsx";
        File file = new File(path);
        //读取文件数据的流
        FileInputStream fileInputStream = new FileInputStream(file);
        //excel 文件对象进行绑定
        XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
        //获取文档中的指定索引页数据
        XSSFSheet sheet = wb.getSheetAt(0);
        //获取sheet页最后一行的行号
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i < lastRowNum ; i++) {
            XSSFRow row = sheet.getRow(i+1);
            XSSFCell cell = row.getCell(0);
            double id =cell.getNumericCellValue();
            XSSFCell cell1 = row.getCell(1);
            String img = cell1.getStringCellValue();
            System.out.println(id+"\t"+img);
        }
    }
}
