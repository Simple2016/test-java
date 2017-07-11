package lqw.test.test_enum;

/** 
 *
 * @ClassName   类名：Vegetable
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年10月9日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年10月9日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */
public enum Vegetable {
    Apple(1), Banana(2), Orange(3);

    private int status;

    Vegetable(int value) {
        this.status = value;
    }

    public int getStatus() {
        return this.status;
    }

    public static Vegetable getEnumByCode(String code) {
        for (Vegetable status : Vegetable.values()) {
            if (status.getStatus() == (Integer.valueOf(code))) {
                return status;
            }
        }
        return null;
    }

    public static Vegetable getEnumByName(String name) {
        for (Vegetable status : Vegetable.values()) {
            if (status.name().equals(name) ) {
                return status;
            }
        }
        return null;
    }
}
