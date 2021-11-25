import com.alibaba.fastjson.JSON;
import com.joebig7.core.GenericExcelReader;
import com.joebig7.core.GenericExcelWriter;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.data.HeaderDataBuilder;
import com.joebig7.core.listener.DataReadListener;
import com.joebig7.enums.FileTypeEnum;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.joebig7.enums.FieldTypeEnum.*;

/**
 * @Author JoeBig7
 * @date 2021/7/20 13:57:34
 */

public class Bootstrap {

    @Test
    public void testExcelWriter() {
        GenericExcelWriter genericExcelWriter = new GenericExcelWriter("E:/test2.xlsx", FileTypeEnum.XLSX);

        List<HeaderData> headerDataList = HeaderDataBuilder.instance()
                .setHead("优惠券", STRING)
                .setHead("张数", INTEGER)
                .build();

        String couponStr = "2JWQMRR8ZRX, 8CPHNPK5S3Q, LTP7MTVWJ7S, 5WAHQSMD2GF, LRCEP2637YH";

        List<List<Object>> contentList = Lists.newArrayList();
        String[] couponArray = couponStr.split(",");

        Stream.of(couponArray).forEach(c -> {
            List<Object> valueList = Lists.newArrayList();
            valueList.add(c.trim());
            valueList.add(10);
            contentList.add(valueList);
        });
        genericExcelWriter.write(headerDataList, contentList);
    }


    @Test
    public void testExcelReader() {
        GenericExcelReader<User> reader = new GenericExcelReader<>("E:/test.xlsx", User.class);
        List<HeaderData> headerDataList = HeaderDataBuilder.instance()
                .setHead("name", STRING)
                .setHead("age", INTEGER)
                .setHead("address", STRING)
                .setHead("salary", DOUBLE)
                .setHead("isMarried", BOOLEAN)
                .build();

        reader.read(headerDataList, new DataReadListener<>((users -> {
            //这边可以对查询到的结果进行特定逻辑的处理
            System.out.println(JSON.toJSONString(users));
        })));
    }


}
