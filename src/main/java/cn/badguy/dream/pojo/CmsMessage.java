package cn.badguy.dream.pojo;

public class CmsMessage {
    private String mobilePhoneNumber;
    private String content;

    public CmsMessage() {
    }

    public CmsMessage(String mobilePhoneNumber) {
        this.setMobilePhoneNumber(mobilePhoneNumber);
    }

    public CmsMessage(String mobilePhoneNumber, String content) {
        this.setMobilePhoneNumber(mobilePhoneNumber);
        this.setContent(content);
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CmsMessage{" +
                "mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
