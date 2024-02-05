package com.ohgiraffers.section02.template;

import java.sql.Connection;
import static com.ohgiraffers.section02.template.JDBCTemplate.close;
import static com.ohgiraffers.section02.template.JDBCTemplate.getConnection;

/* 수업목표. Connection 객체를 따로 두어 import로 관리 */
public class Application {
    public static void main(String[] args) {

        /* 설명. 비즈니스 로직(트랜잭션 처리) 실행 */
        Connection con = getConnection();
        System.out.println("con = " + con);

        close(con);
    }
}
