package lqw.test.blog.util;

/** 
 *
 * @ClassName   类名：NumberHelper
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年12月13日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年12月13日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class NumberHelper {

    // 有时间改成加密解密
    public static int encode(int args) {
        return args * 3 + 298972389;

    }

    public static int decode(int args) {
        return (args - 298972389) / 3;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
