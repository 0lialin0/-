package cn.wtkj.charge_inspect.data.bean;

public class SortModel {

	private ContactListData.MData.info contactData;   //显示的数据
	private String sortLetters;  //显示数据拼音的首字母

	public ContactListData.MData.info getContactData() {
		return contactData;
	}

	public void setContactData(ContactListData.MData.info contactData) {
		this.contactData = contactData;
	}

	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}
