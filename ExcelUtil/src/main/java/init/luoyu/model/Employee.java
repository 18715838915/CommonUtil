package init.luoyu.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author LuoYu
 * date 2020/12/26
 */
@Data
public class Employee {

    private String name;

    private LocalDate birthDate;

    private Integer age;

    private String sex;



}
