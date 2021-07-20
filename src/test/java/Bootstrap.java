import com.joebig7.core.GenericExcelWriter;
import com.joebig7.core.data.HeaderData;
import com.joebig7.enums.FieldTypeEnum;
import com.joebig7.enums.FileTypeEnum;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/7/20 13:57:34
 */

public class Bootstrap {

    @Test
    public void testExcelWriter() {
        GenericExcelWriter genericExcelWriter = new GenericExcelWriter("E:/test.xlsx", FileTypeEnum.XLSX);

        List<HeaderData> headerDataList = Lists.newArrayList();
        HeaderData h1 = new HeaderData("name",FieldTypeEnum.STRING);
        HeaderData h2 = new HeaderData("age",FieldTypeEnum.INTEGER);
        headerDataList.add(h1);
        headerDataList.add(h2);

        List<List<Object>> contentList = Lists.newArrayList();
        List<Object> d1 = Lists.newArrayList();
        d1.add("joe");
        d1.add(24);

        List<Object> d2 = Lists.newArrayList();
        d2.add("jack");
        d2.add(25);

        contentList.add(d1);
        contentList.add(d2);

        genericExcelWriter.write(headerDataList,contentList);

    }

}
