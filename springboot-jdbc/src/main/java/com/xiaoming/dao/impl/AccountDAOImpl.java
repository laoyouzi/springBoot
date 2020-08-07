package com.xiaoming.dao.impl;

import com.xiaoming.dao.IAccountDAO;
import com.xiaoming.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDAOImpl implements IAccountDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Account account) {
        return jdbcTemplate.update("INSERT into account(name, money) values(?, ?)", account.getName(), account.getMoney());
    }

    @Override
    public int update(Account account) {
        return jdbcTemplate.update("UPDATE account SET name=?, money=? WHERE id=?", account.getName(), account.getMoney(), account.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from TABLE account WHERE id=?", id);
    }

    @Override
    public Account findAccountById(int id) {
        List<Account> list = jdbcTemplate.query("select * from account where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Account.class));
        if (list != null && list.size() > 0) {
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<Account> findAccountList() {
        List<Account> list = jdbcTemplate.query("select * from account", new Object[]{}, new BeanPropertyRowMapper<>(Account.class));

        if (list != null && list.size() > 0){
            return list;
        }else {
            return null;
        }
    }

}
