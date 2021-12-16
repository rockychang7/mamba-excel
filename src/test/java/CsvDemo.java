import com.alibaba.fastjson.JSON;
import com.joebig7.MambaExcelFactory;
import com.joebig7.core.GenericWriter;
import com.joebig7.core.data.ContentDataBuilder;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.data.HeaderDataBuilder;
import com.joebig7.core.listener.DataReadListener;
import com.joebig7.enums.FieldTypeEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.joebig7.enums.FieldTypeEnum.*;
import static com.joebig7.enums.FieldTypeEnum.BOOLEAN;

/**
 * @Author JoeBig7
 * @date 2021/12/15 10:46:37
 * @description csv测试demo
 */
public class CsvDemo {

    @Test
    public void testCsvReader() {
        //指定excel标题头，并且指定每个字段的类型
        List<HeaderData> headerDataList = HeaderDataBuilder.instance()
                .fill("name", STRING)
                .fill("age", INTEGER)
                .fill("address", STRING)
                .fill("salary", DOUBLE)
                .fill("married", BOOLEAN)
                .build();

        MambaExcelFactory.build().readInstance("./user.csv", User.class, headerDataList)
                .read(new DataReadListener<>((users -> {
                    //可以对查询到的结果进行特定逻辑的处理
                    System.out.println(JSON.toJSONString(users));
                })));
    }


    @Test
    public void testCsvWriter() {

        List<HeaderData> headerDataList = HeaderDataBuilder.instance()
                .fill("name", STRING)
                .fill("age", INTEGER)
                .fill("address", STRING)
                .fill("salary", DOUBLE)
                .fill("married", BOOLEAN)
                .build();

        List<List<Object>> contentList = ContentDataBuilder.instance(headerDataList)
                .fill(new User("jack", 29, "shanghai", 1111.1, true)).build();


        MambaExcelFactory.build().writeInstance("./user2.csv", headerDataList, contentList).write();
    }


}