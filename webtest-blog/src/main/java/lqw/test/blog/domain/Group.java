package lqw.test.blog.domain;

import java.util.Date;

/** 
 *
 * @ClassName   类名：Group
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年11月22日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年11月22日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class Group {

    private int groupId;

    private String groupName;

    private String author;

    private Date createTime;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Group [groupId=" + groupId + ", groupName=" + groupName + ", author=" + author + ", createTime="
                        + createTime + "]";
    }

}
