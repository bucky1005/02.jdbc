package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class MenuRepository {

    public int registMenu(Connection con, Menu registMenu) {
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();


        /* 설명. 쿼리는 XML 파일로부터 읽어와서 활용할 것 */
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-mapper.xml"));

            /* 설명. mapper의 xml 파일을 쿼리로 사용하여 더 깔끔하게 코드 작성 가능(유지보수 향상) */
            String query = prop.getProperty("insertMenu");

            System.out.println("query = " + query);

            pstmt = con.prepareStatement(query);
            /* 설명. menu-mapper.xml에 ?로 작성했던 부분에 값을 넣어주는 코드 */
            pstmt.setString(1, registMenu.getMenuName());
            pstmt.setInt(2, registMenu.getMenuPrice());
            pstmt.setInt(3, registMenu.getCategoryCode());
            pstmt.setString(4, registMenu.getOrderableStatus());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
