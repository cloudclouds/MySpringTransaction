package com.test.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;


public class User implements Serializable {
	private Long uid;

	private String userName;

	private Byte type;

	private String phone;

	private String email;

	private Integer depLevel1;

	private Integer depLevel2;

	private Integer depLevel3;

	private String sshKey1;

	private String sshKey2;

	private String sshKey3;
	
	private String oa;
	
	private String aq;
	
	private String leader;

	private Integer departmentId;


	private Long passPortUid;

	private String passPortUserName;

	private String passPortPhone;

	private String passPortEmail;


	public String getAq() {
		return aq;
	}

	public void setQa(String aq) {
		this.aq = aq;
	}

	public String getOa() {
		return oa;
	}

	public void setOa(String oa) {
		this.oa = oa;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	private Integer admin;

	public Integer getAdmin() {
		return admin;
	}

	public boolean isResouceList() {
		if (this.type != null && type == 2) {
			return true;
		}
		return false;
	}

	public boolean isSysAdmin() {
		if (getAdmin() > 0) {
			return true;
		}
		return false;
	}

	public boolean isSuperSysAdmin() {
		if (getAdmin() == 2) {
			return true;
		}
		return false;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	private static final long serialVersionUID = 1L;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public String getEmailQiyi() {
		if (email == null) {
			return null;
		}
		if (email.contains("@")) {
			return this.email;
		}
		return email ;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Integer getDepLevel1() {
		return depLevel1;
	}

	public void setDepLevel1(Integer depLevel1) {
		this.depLevel1 = depLevel1;
	}

	public Integer getDepLevel2() {
		return depLevel2;
	}

	public void setDepLevel2(Integer depLevel2) {
		this.depLevel2 = depLevel2;
	}

	public Integer getDepLevel3() {
		return depLevel3;
	}

	public void setDepLevel3(Integer depLevel3) {
		this.depLevel3 = depLevel3;
	}

	public String getSshKey() {

		List<String> keys = new ArrayList<String>();
		if (!StringUtils.isBlank(sshKey1)) {
			keys.add(sshKey1);
		}
		if (!StringUtils.isBlank(sshKey2)) {
			keys.add(sshKey2);
		}
		if (!StringUtils.isBlank(sshKey3)) {
			keys.add(sshKey3);
		}
		String ret = "";
		int size = keys.size();
		for (int i = 0; i < size; i++) {
			ret += keys.get(i);
			if (i == size - 1) {

			} else {
				ret += ",";
			}
		}
		return ret;
	}

	public String getSshKey1() {
		return sshKey1;
	}

	public void setSshKey1(String sshKey1) {
		this.sshKey1 = sshKey1 == null ? null : sshKey1.trim();
	}

	public String getSshKey2() {
		return sshKey2;
	}

	public void setSshKey2(String sshKey2) {
		this.sshKey2 = sshKey2 == null ? null : sshKey2.trim();
	}

	public String getSshKey3() {
		return sshKey3;
	}

	public void setSshKey3(String sshKey3) {
		this.sshKey3 = sshKey3 == null ? null : sshKey3.trim();
	}

	public Long getPassPortUid() {
		return passPortUid;
	}

	public void setPassPortUid(Long passPortUid) {
		this.passPortUid = passPortUid;
	}

	public String getPassPortUserName() {
		return passPortUserName;
	}

	public void setPassPortUserName(String passPortUserName) {
		this.passPortUserName = passPortUserName;
	}

	public String getPassPortPhone() {
		return passPortPhone;
	}

	public void setPassPortPhone(String passPortPhone) {
		this.passPortPhone = passPortPhone;
	}

	public String getPassPortEmail() {
		return passPortEmail;
	}

	public void setPassPortEmail(String passPortEmail) {
		this.passPortEmail = passPortEmail;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equal(uid, user.uid);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(uid);
	}
}