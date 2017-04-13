package com.test.object;

import java.io.Serializable;

/** 
 *
 * @ClassName   类名：User
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2017-2-22
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2017-2-22   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class User implements Serializable {

    /**
    * @Fields serialVersionUID : TODO
    */

    private static final long serialVersionUID = 4431834607246206535L;

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
    	* <p>
     * 函数名称： main
     * </p>
    	* <p>
     * 功能说明：TODO
     * </p>
    	* @date   创建时间：2017-2-22
    	* @author 作者：liqw
    	* <p>参数说明：</p>
    	* @param args 		TODO
     * @throws
    	*/
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
