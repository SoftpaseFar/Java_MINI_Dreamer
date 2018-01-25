package cn.badguy.dream.controller;


import cn.badguy.dream.pojo.Classmate;
import cn.badguy.dream.service.IExcelService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
public class ExcelController {


    @Autowired
    private IExcelService iExcelService;


    @GetMapping("/excel/test")
    public List<Classmate> test() {
        return iExcelService.getStudentsList();
    }

    @GetMapping("/excel/create")
    public String createExcel() {
        String[] title = {"id", "name", "sex"};
        //创建文件
        File file = new File("D:\\workspace\\test.xls");
        try {
            file.createNewFile();
            //创建工作簿
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
            //创建sheet
            WritableSheet sheet = writableWorkbook.createSheet("sheet1", 0);
            Label label = null;
            //设置第一行标题
            for (int i = 0; i < title.length; i++) {
                label = new Label(i, 0, title[i]);
                sheet.addCell(label);
            }
            //追加数据
            for (int i = 1; i < 10; i++) {
                label = new Label(0, i, "" + i);
                sheet.addCell(label);
                label = new Label(1, i, "user" + i);
                sheet.addCell(label);
                label = new Label(2, i, "男");
                sheet.addCell(label);
            }
            //写入数据
            writableWorkbook.write();
            writableWorkbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok.";
    }


//    @GetMapping("/excel/read")
//    public String readExcel() {
//        String result = "";
//        try {
//            //创建工作簿
//            Workbook writableWorkbook = Workbook.getWorkbook(new File("D:\\workspace\\test.xls"));
//            //获取第一个sheet页面
//            Sheet sheet = writableWorkbook.getSheet(0);
//
//            for (int i = 0; i < sheet.getRows(); i++) {
//                for (int j = 0; j < sheet.getColumns(); j++) {
//                    Cell cell = sheet.getCell(j, i);
//                    System.out.print(cell.getContents() + " ");
//                    result += cell.getContents() + "";
//                }
//                System.out.println();
//            }
//            writableWorkbook.close();
//        } catch (exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }


}
