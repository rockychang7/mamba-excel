import com.alibaba.fastjson.JSON;
import com.joebig7.MambaExcelFactory;
import com.joebig7.core.GenericWriter;
import com.joebig7.core.data.ContentDataBuilder;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.data.HeaderDataBuilder;
import com.joebig7.core.listener.DataReadListener;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

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

        MambaExcelFactory.build().readInstance("E:/user.xlsx", User.class, headerDataList)
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
                .fill(new User("jack", 29, "shanghai", 1111.1, true)).build();

        MambaExcelFactory.build().writeInstance("./user2.xlsx", headerDataList, contentList).write();

    }
}
