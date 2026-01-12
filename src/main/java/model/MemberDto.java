package model;

public class MemberDto {

    private String userid;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String lastNameKanji;
    private String firstNameKanji;
    private String lastNameKana;
    private String firstNameKana;
    private String role; // 権限 (USER, ADMIN)
    private String regdate;

    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getLastNameKanji() {
        return lastNameKanji;
    }
    public void setLastNameKanji(String lastNameKanji) {
        this.lastNameKanji = lastNameKanji;
    }
    public String getFirstNameKanji() {
        return firstNameKanji;
    }
    public void setFirstNameKanji(String firstNameKanji) {
        this.firstNameKanji = firstNameKanji;
    }
    public String getLastNameKana() {
        return lastNameKana;
    }
    public void setLastNameKana(String lastNameKana) {
        this.lastNameKana = lastNameKana;
    }
    public String getFirstNameKana() {
        return firstNameKana;
    }
    public void setFirstNameKana(String firstNameKana) {
        this.firstNameKana = firstNameKana;
    }
    public String getRegdate() {
        return regdate;
    }
    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }
}

