import com.joebig7.core.GenericWriter;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.data.HeaderDataBuilder;
import com.joebig7.enums.FieldTypeEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/12/15 10:46:37
 * @description csv测试demo
 */
public class CsvDemo {


    @Test
    public void testCsvWriter() {
        GenericWriter genericWriter = new GenericWriter("D://test.csv");

        List<HeaderData> headerData = HeaderDataBuilder.instance().setHead("a", FieldTypeEnum.STRING).setHead("b", FieldTypeEnum.STRING).build();

        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        List<Object> list2 = new ArrayList<>();

        list2.add(3);
        list2.add(4);


        List<List<Object>> obj = new ArrayList<>();
        obj.add(list);
        obj.add(list2);

        genericWriter.write(headerData, obj);
    }


    @Test
    public void test() {

    }

}