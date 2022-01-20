import com.alibaba.fastjson.JSON;
import com.joebig7.MambaExcelFactory;
import com.joebig7.core.data.ContentDataBuilder;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.data.HeaderDataBuilder;
import com.joebig7.core.listener.DataReadListener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.joebig7.enums.FieldTypeEnum.*;

/**
 * @Author JoeBig7
 * @date 2021/7/20 13:57:34
 */

public class ExcelDemo {

    /**
     * 测试读取excel内容
     */
    @Test
    public void testExcelReader() {
        //指定excel标题头，并且指定每个字段的类型
        List<HeaderData> headerDataList = HeaderDataBuilder.instance()
                .fill("name", STRING)
                .fill("age", INTEGER)
                .fill("address", STRING)
                .fill("salary", DOUBLE)
                .fill("married", BOOLEAN)
                .build();

        MambaExcelFactory.build().readInstance("./user.xlsx", User.class, headerDataList)
                .read(new DataReadListener<>((users -> {
                    //可以对查询到的结果进行特定逻辑的处理
                    System.out.println(JSON.toJSONString(users));
                })));
    }


    @Test
    public void testExcelWriter() {

        List<HeaderData> headerDataList = HeaderDataBuilder.instance()
                .fill("name", STRING)
                .fill("age", INTEGER)
                .fill("address", STRING)
                .fill("salary", DOUBLE)
                .fill("married", BOOLEAN)
                .build();

        List<List<Object>> contentList = ContentDataBuilder.instance(headerDataList)
                .fill(new User("jack", 29, "shanghai", 1111.1, true))
                .fill(new User("rose", 24, "beijing", 12000d, true)).
                 fill(new User("jack", 29, "shanghai", 11111.1, true))
                .build();

        MambaExcelFactory.build().writeInstance("./user2.xlsx", headerDataList, contentList).write();

    }

    @Test
    public void testExcelWriter2() {
        List<HeaderData> headerDataList = HeaderDataBuilder.instance()
                .fill("name", STRING)
                .fill("age", INTEGER)
                .fill("address", STRING)
                .fill("salary", DOUBLE)
                .fill("married", BOOLEAN)
                .build();

        List<User> users = new ArrayList<>();
        User user1 = new User("jack", 29, "shanghai", 12111d, true);
        User user2 = new User("rose", 24, "beijing", 12000d, true);
        User user3 = new User("jack", 29, "shanghai", 11111.1, true);
        users.add(user1);
        users.add(user2);
        users.add(user3);

        List<List<Object>> contentList = ContentDataBuilder.instance(headerDataList)
                .fill(users).build();

        MambaExcelFactory.build().writeInstance("./user3.xlsx", headerDataList, contentList).write();

    }

    @Test
    public void testExcelWriter3() {
        List<HeaderData> headerDataList = HeaderDataBuilder.instance()
                .fill("name", STRING)
                .build();

        List<Object> users = new ArrayList<>();
        Object name1 = "joe";
        Object name2 = "rose";
        Object name3 = "jack";
        users.add(name1);
        users.add(name2);
        users.add(name3);
        List<List<Object>> contentList = ContentDataBuilder.instance(headerDataList)
                .fill(users,false).build();
        MambaExcelFactory.build().writeInstance("./user4.xlsx", headerDataList, contentList).write();

    }
}
