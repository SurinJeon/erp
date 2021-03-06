package erp.dto;

import java.util.Arrays;
import java.util.Date;

public class EmployeeDetail {

	private int empNo;
	private boolean gender;
	private Date hireDate;
	private String pass; // 비밀번호 추가
	private byte[] pic; // << img는 0101이런식으로 저장되기 때문에 byte배열
	
	public EmployeeDetail() {
		super();
	}
	
	public EmployeeDetail(int empNo) {
		this.empNo = empNo;
	}

	public EmployeeDetail(int empNo, boolean gender, Date hireDate, byte[] pic) {
		this.empNo = empNo;
		this.gender = gender;
		this.hireDate = hireDate;
		this.pic = pic;
	}

	
	public EmployeeDetail(int empNo, boolean gender, Date hireDate, String pass, byte[] pic) {
		this.empNo = empNo;
		this.gender = gender;
		this.hireDate = hireDate;
		this.pass = pass;
		this.pic = pic;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return String.format("EmpDetail [empNo=%s, gender=%s, hireDate=%s, pic=%s]", empNo, gender, hireDate,
				pic.length); // pic 배열로 원래 가져왔었는데 길이 너무 길어서 length로 바꿈 
	}
	
}
