package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application1 {
    public static void main(String[] args) {

        /* 수업목표. 해당 DBMS 계정에 맞는 Connection 객체를 생성할 수 있다.(DBMS Driver 추가) */
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            /* 설명. DriverManager 클래스의 getConnection 메소드에 db서버 경로, user명, 비밀번호를 매개변수로 입력하면
                 해당 DB에 접근할 수 있는 Connection 객체가 생성됨 */
            /* 메모. 입력한 매개변수에 오타가 있거나 경로, 이름 등의 변경이 생기면 ReBuild를 해야하는데
                실무 규모의 코드에서 ReBuild를 할 경우 하루 넘게 소요된다.
                => 관련 정보를 설정 파일(jdbc-config.properties)로 따로 만들어 관리하면 Rebuild를 하지 않아도된다.
             */
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "swcamp", "swcamp");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                /* 설명. connection 객체도 stream 이므로 close를 해줌
                    객체가 null을 가리키지 않거나 이미 닫힌 상태가 아니라면 close()실행
                 */
                if(con != null && !con.isClosed()) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

