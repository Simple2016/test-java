package lqw.test.blog;

/** 
 *
 * @ClassName   类名：A
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年11月17日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年11月17日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class A {

    public static void main123(String[] args) {
        // TODO Auto-generated method stub
        String a = "哈哈哈哈哦哈哈哈哈哦哈哈哈哈哦哈哈哈哈哦哈哈哈哈哦哈哈哈哈哦";
        System.err.println(a.substring(0, 1));
        System.err.println(a.substring(0, 1).length());
        System.err.println(a.substring(0, 27).length());
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String a = "<script>哈哈哈哈哦哈                     哈哈       哈哦哈哈哈哈<adafa>哦哈哈哈哈哦哈哈哈哈哦哈哈哈哈哦<aa>";
        a = a.replaceAll("<[^>]*>", "");
        a = a.replaceAll("\\s+", " ");
        System.err.println(a);

    }

}
