import com.joebig7.core.GenericExcelWriter;
import com.joebig7.core.data.HeaderData;
import com.joebig7.enums.FieldTypeEnum;
import com.joebig7.enums.FileTypeEnum;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

/**
 * @Author JoeBig7
 * @date 2021/7/20 13:57:34
 */

public class Bootstrap {

    @Test
    public void testExcelWriter() {
        GenericExcelWriter genericExcelWriter = new GenericExcelWriter("E:/test.xlsx", FileTypeEnum.XLSX);

        List<HeaderData> headerDataList = Lists.newArrayList();
        HeaderData h1 = new HeaderData("优惠券",FieldTypeEnum.STRING);
        HeaderData h2 = new HeaderData("张数",FieldTypeEnum.INTEGER);
        headerDataList.add(h1);
        headerDataList.add(h2);

        String couponStr = "2JWQMRR8ZR, 8CPHNPK5S3, LTP7MTVWJ7, 5WAHQSMD2G, LRCEP2637Y, 4M4ZWYWK2P, BY8GNDW36G, 9PC6YYCN58, 4J63ZJ3QHE, UPQPZQB86S, B8P6HYPTNB, JNY7BQ2UPV, 58QPJCAJF2, DJ6PZLNSZ7, KW7JPQQGAG, 6ZSHEKW4DG, 698972V33A, 8MT7NUUAJP, 8XR5ABCGAD, UU7TVQYZ9G, YMEAVL6TZR, 2LPR3HTRKR, URVDEZBF4D, 9H8XFFSFVK, XY3UETSAEF, ACXCH9CWYL, 7RT2YWUTLX, RFX5CAQ3TU, NAW9T5AYF2, 96X3JXYENX, RA69ZX7KUY, V2NEFWPK3L, 99U5FBGRZ6, REYYB3ZM6M, 34V62JQBNF, LZB245UP2F, RPSHK2TYRY, LLYJ5PFXKH, XCCFTJCUC9, 8YF22EJMTK, NUKFEPSVHZ, QV97CH36Z9, HBUMHR9EU7, PVMN7RCRAV, BDTPSXBCZD, VCCQDWYVFD, 4V6RYS4BN6, QYF6LZ5JJ8, ZNBE7K4PVF, YZLKPQC5TY, ADZV88GKJW, U8DAHKVNL5, A4G39LVWYK, UNN3PLQGJX, L4GSVBMUJP, 7DCF27EJZN, CRCUNJCXTN, EBPUAUN3GB, X7ZGVXHMVL, XMRBXH5V8T, 9G3XDGBYAT, 8UHSNZMMES, 9AEGK6WY7N, 85SJ73G8MN, H4CNBJ9DBV, SAJA3VHRWX, R8GKVHVJGD, 3JGUFNL8UV, ENERRD9YP5, 2G2S6PVUG3, DGDP8KEGZV, KBN7MYEHZH, KLFGSAU2ZQ, BGN7C3GRH6, 8HTB4N47FB, NR6ZFXL5B8, TKR69JQHJ3, VLSCQT88BN, JDTSJBT6E5, 94KLTFWQN2, Y77VM2QUZE, FAEUYWVXVL, PTJ3XQ9X4S, W5EZ6SZA3D, CMYVABWK9D, N88BVBCLN6, 5FL9GGUVK2, FRRVDBBRWW, NSKJV87A8W, MXRUV3GJS5, VPHTAPBGY5, LB2DHS98MR, V4CHTCLMQG, KT66S2G44Y, TNS6XUBDZJ, A4JBVPRBBK, NCJY5N7428, 5747NS9GWH, 9HVKYJXDU3, QAZURXF6JU";


        List<List<Object>> contentList = Lists.newArrayList();


        String[] couponArray = couponStr.split(",");

        Stream.of(couponArray).forEach(c->{
            List<Object> valueList = Lists.newArrayList();
            valueList.add(c.trim());
            valueList.add(10);
            contentList.add(valueList);
        });

        genericExcelWriter.write(headerDataList,contentList);

    }

}
