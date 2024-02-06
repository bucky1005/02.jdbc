package com.ohgiraffers.section02.update;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.*;

/* 설명. MenuService의 역할
    Connection 로직처리, 트랜잭션, Repository와 상호작용하며 데이터 전달 */
public class MenuService {
    public void modifyMenu(Menu modifyMenu){
        Connection con = getConnection();

        MenuRepository repo = new MenuRepository();
        int result = repo.modifyMenu(con, modifyMenu);
        if(result > 0) {
            System.out.println("성공");
            commit(con);
        } else {
            System.out.println("실패");
            rollback(con);
        }
        repo.modifyMenu(con, modifyMenu);
        close(con);
    }
}
