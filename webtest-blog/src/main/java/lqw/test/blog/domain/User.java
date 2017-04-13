package lqw.test.blog.domain;

import java.io.Serializable;

/** 
 *
 * @ClassName   类名：User
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年10月21日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年10月21日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class User implements Serializable {

    /**
    * @Fields serialVersionUID : TODO
    */

    private static final long serialVersionUID = 1L;

    private int id;

    private String userName;

    private String password;

    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", type=" + type + "]";
    }
}
