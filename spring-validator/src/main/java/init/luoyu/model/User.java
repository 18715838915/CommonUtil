package init.luoyu.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author LuoYu
 * date 2020/12/29
 */
@Data
public class User {

    @NotBlank(message = "用户名称不能为空")
    private String name;

    @NotNull(message = "用户类型不能为空")
    private Integer type;

    @NotEmpty(message = "列表不能为空")
    private List<String> list;







}
