package cn.spring.demo;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

	@Override
	public void outMoney(String out, double money) {
		String sql="";
		
        this.getJdbcTemplate().update(sql,money,out);
	}

	@Override
	public void inMoney(String in, double money) {
		String sql="";
        this.getJdbcTemplate().update(sql,money,in);
	}

}
