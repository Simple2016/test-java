package lqw.test.blog.mybatisHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import lqw.test.blog.domain.Group;
import lqw.test.blog.util.JsonHelper;

public class GroupHandler extends BaseTypeHandler<List<Group>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List parameter, JdbcType jdbcType)
                    throws SQLException {
        ps.setString(i, "");
    }

    @Override
    public List<Group> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String groupJson = rs.getString(columnName);
        try {
            return JsonHelper.fromJson(groupJson, ArrayList.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Group> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String groupJson = rs.getString(columnIndex);
        try {
            return JsonHelper.fromJson(groupJson, ArrayList.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Group> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String groupJson = cs.getString(columnIndex);
        try {
            return JsonHelper.fromJson(groupJson, ArrayList.class);
        } catch (Exception e) {
            return null;
        }
    }

}
