package com.ohgiraffers.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class MenuRepository {

    /* 설명. CRUD 작업(Insert, delete, update 등의 작업을 수행할 때에는
        메소드의 반환형을 int로 주어서 commit, rollback 중 어떤 작업을 수행할지 알려야 한다.
     */
    public int modifyMenu(Connection con, Menu modifyMenu){
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-mapper.xml"));
            System.out.println(prop.getProperty("updateMenu"));

            String query = prop.getProperty("updateMenu");

            pstmt = con.prepareStatement(query);
            /* 설명. ? 데이터에 set메소드로 값을 대입할 때에는 순서가 중요하므로 작성한 쿼리문(xml 파일)을 보며 순서대로 작업한다. */
            pstmt.setString(1, modifyMenu.getMenuName());
            pstmt.setInt(2, modifyMenu.getMenuPrice());
            pstmt.setInt(3, modifyMenu.getMenuCode());

            pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
