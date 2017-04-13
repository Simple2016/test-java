package lqw.test.test_excell;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import jxl.Workbook;
import jxl.write.Boolean;
import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/** 
 *
 * @ClassName   类名：AA
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年11月11日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年11月11日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */
public class ComplexDataExcelWrite {

    public static void createExcel(OutputStream os) throws WriteException, IOException {
        // 创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        // 创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        // 创建要显示的具体内容
        Label formate = new Label(0, 0, "数据格式");
        sheet.addCell(formate);
        Label floats = new Label(1, 0, "浮点型");
        sheet.addCell(floats);
        Label integers = new Label(2, 0, "整型");
        sheet.addCell(integers);
        Label booleans = new Label(3, 0, "布尔型");
        sheet.addCell(booleans);
        Label dates = new Label(4, 0, "日期格式");
        sheet.addCell(dates);

        Label example = new Label(0, 1, "数据示例");
        sheet.addCell(example);
        // 浮点数据

        Number number = new Number(1, 1, 3.1415926535);
        sheet.addCell(number);
        // 整形数据
        Number ints = new Number(2, 1, 15042699);
        sheet.addCell(ints);
        Boolean bools = new Boolean(3, 1, true);
        sheet.addCell(bools);
        // 日期型数据
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        WritableCellFormat cf1 = new WritableCellFormat(DateFormats.FORMAT1);
        DateTime dt = new DateTime(4, 1, date, cf1);
        sheet.addCell(dt);
        // 把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }

    public static void main(String[] args) throws WriteException, IOException {
        System.out.println("Hello World!");
        OutputStream a = new FileOutputStream(new File("c:/2.xls"));
        createExcel(a);
    }

}
