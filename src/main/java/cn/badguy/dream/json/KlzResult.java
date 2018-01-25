package cn.badguy.dream.json;

public class KlzResult {
    private boolean status;
    private String message;
    private TokenAndCookie data;

    public class TokenAndCookie {
        private String token;
        private String cookie;

        public TokenAndCookie() {
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCookie() {
            return cookie;
        }

        public void setCookie(String cookie) {
            this.cookie = cookie;
        }
    }

    public KlzResult() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TokenAndCookie getData() {
        return data;
    }

    public void setData(TokenAndCookie data) {
        this.data = data;
    }
}
