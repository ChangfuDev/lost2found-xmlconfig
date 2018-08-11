package cn.sevenleave.xmlconfig.system.aspect.authcheck;

/**
 * 描述；常量枚举类
 * @author SevenLeave
 * @date 2018-08-07 16:53
 */
public enum  AuthCheckConstants {
    MY_TEMPORARY_USERTOKEN("hasAuthenticated");
    
    private String userToken;
    
    AuthCheckConstants(String userToken) {
        this.userToken = userToken;
    }
    
    public String getUserToken() {
        return userToken;
    }
    
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
