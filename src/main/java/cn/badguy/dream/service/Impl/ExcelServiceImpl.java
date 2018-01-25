package cn.badguy.dream.service.Impl;

import cn.badguy.dream.pojo.Classmate;
import cn.badguy.dream.service.IExcelService;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service("iExcelService")
public class ExcelServiceImpl implements IExcelService {
    public List<Classmate> getStudentsList() {
//        String result = "";
        List<Classmate> result = new ArrayList<>();
        try {
            //创建工作簿
            Workbook writableWorkbook = Workbook.getWorkbook(new File("D:\\workspace\\test.xls"));
            //获取第一个sheet页面
            Sheet sheet = writableWorkbook.getSheet(0);

            for (int i = 1; i < sheet.getRows(); i++) {
                List<String> list = new ArrayList<>();
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    list.add(cell.getContents() + " ");
                }
                Classmate student = new Classmate();
                student.setId(Integer.parseInt(list.get(0).trim()));
                student.setName(list.get(1).trim());
                student.setSex(list.get(2).trim());
                result.add(student);
            }
            writableWorkbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
