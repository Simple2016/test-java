package lqw.test;

import java.io.InputStream;

/** 
 *
 * @ClassName   类名：Demo
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年12月1日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年12月1日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */
public interface DemoService {

    String sayHello(String name);

    /** 
     *  Created on:             2013-12-11  
     * <p>Discription:            下载</p> 
     * @return              InputStream 
     */
    public InputStream download(String path);

    /** 
     * 处理上传 
     * @param in 
     * @return 
     */
    String upload(String destPath, InputStream in);
}
