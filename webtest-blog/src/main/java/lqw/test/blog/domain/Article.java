package lqw.test.blog.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** 
 *
 * @ClassName   类名：Article
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年10月25日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年10月25日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class Article implements Serializable {

    /**
    * @Fields serialVersionUID : TODO
    */

    private static final long serialVersionUID = 1L;

    private int articleldId;

    private String title;

    private String content;

    private String detail;

    private String group;

    private List<Group> groupList;

    private Date createTime;

    private Date modifyTime;

    private String author;

    private String modifier;

    public int getArticleldId() {
        return articleldId;
    }

    public void setArticleldId(int articleldId) {
        this.articleldId = articleldId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    @Override
    public String toString() {
        return "Article [articleldId=" + articleldId + ", title=" + title + ", content=" + content + ", detail="
                        + detail + ", group=" + group + ", createTime=" + createTime + ", modifyTime=" + modifyTime
                        + ", author=" + author + ", modifier=" + modifier + "]";
    }

}
