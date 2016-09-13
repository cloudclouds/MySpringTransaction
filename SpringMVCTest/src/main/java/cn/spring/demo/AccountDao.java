package cn.spring.demo;

/**
 * 
 * @author chenyun_sx
 * 转账案例的DAO层的接口
 */
public interface AccountDao {
	/**
	 * 
	 * @param out   :转出账号
	 * @param money :转账金额
	 */
  public void outMoney(String out,double money);
  /**
   * 
   * @param in        :转入账号
   * @param money:转入金额
   */
  public void inMoney(String in,double money);
}
