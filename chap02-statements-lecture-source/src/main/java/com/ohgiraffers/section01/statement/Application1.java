package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

/* 수업목표. Connection, Statement, ResultSet을 이용하여 연동된 db의 쿼리를 전달하고 데이터를 받아올 수 있다. */
public class Application1 {
    public static void main(String[] args) {

        /* 설명. 트랜잭션 처리를 위한 DBMS 연동용 Connection 객체 생성 */
        Connection con = getConnection();
        System.out.println("con = " + con);

        /* 설명. 해당 Connection을 통해 트랜잭션 처리(비즈니스 로직 수행, CRUD) */
        Statement stmt = null;      // 쿼리를 운반하고 결과를 반환하는 객체
        ResultSet rset = null;      // 조희의 결과 반환되는 객체

        try {
            stmt = con.createStatement();                       // 쿼리가 이동할 통로 생성

            /* 설명. 쿼리의 결과인 다중행/단일행을 받은 ResultSet(현재는 다중행) */
            rset = stmt.executeQuery("SELECT * FROM EMPLOYEE");    // 전송할 쿼리 내용을 보내고 반환된 값을 rset에 저장

            /* 설명. rset.next()로 한 행씩 확인 */
            while(rset.next()){             // executeQuery로 반한된 결과 행 만큼 반복

                /* 설명. 반복문 안에서의 rset은 next()에서 받아온 행으로, 단일행으로 해석할 것 */
                System.out.println(rset.getString("EMP_NAME")
                        + ", " + rset.getInt("SALARY"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            /* 설명. JDBCTemplate에 close하는 메소드를 생성하여 사용 */
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
