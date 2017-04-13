package lqw.test.test_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 *
 * @ClassName   类名：testRegex
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年10月14日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年10月14日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class testRegex {

    public static void main(String[] args) {
        String xmlstr = "<a>a1<b>b2<c>c3</c></b></a>";
        Pattern p = Pattern.compile("<c>([\\s\\S]*)</c>");
        Matcher m = p.matcher(xmlstr);
        if (m.find()) {
            System.err.println(m.group());
            System.err.println(m.group(1));
        }

    }
}
