package init.luoyu;

import init.luoyu.model.Employee;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class ExcelUtilApplication {
    public static void main( String[] args )
    {

        List<Employee> employees = new ArrayList<>();
        for(int i=0;i<10;i++){
            Employee employee = new Employee();
            employee.setAge(i);
            employee.setSex("男");
            employee.setBirthDate(LocalDate.now());
            employee.setName("张三"+i);
            employees.add(employee);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("employees",employees);
        try (InputStream is = new FileInputStream(new File("E:/luo.xlsx"))){
            try (OutputStream os = new BufferedOutputStream(new FileOutputStream("E:/result.xlsx"))){
                XLSTransformer transformer = new XLSTransformer();
                Workbook workbook = transformer.transformXLS(is, map);
                workbook.write(os);
                is.close();
                os.flush();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}



