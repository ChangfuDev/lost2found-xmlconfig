package cn.sevenleave.xmlconfig.support.aspect.authcheck;

/**
 * 描述；常量枚举类
 * @author SevenLeave
 * @date 2018-08-07 16:53
 */
public enum AuthCheckEnum {
    MY_TEMPORARY_USERTOKEN("hasAuthenticated");
    
    private String userToken;
    
    AuthCheckEnum(String userToken) {
        this.userToken = userToken;
    }
    
    public String getUserToken() {
        return userToken;
    }
    
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
