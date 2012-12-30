/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.saas.facebook;

import java.io.IOException;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 * FacebookSocialNetworkingService Service
 *
 * @author GUSTAVO_AL
 */

public class FacebookSocialNetworkingService {

    /** Creates a new instance of FacebookSocialNetworkingService */
    public FacebookSocialNetworkingService() {
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch(Throwable th) {}
    }

    /**
     *
     * @param format
     * @param uid
     * @return an instance of RestResponse
     */
    public static RestResponse profileGetInfo(String format, String uid) throws IOException {
        String v = "1.0";
        String method = "facebook.profile.getInfo";
        FacebookSocialNetworkingServiceAuthenticator.login();
        String callId = String.valueOf(System.currentTimeMillis());
        String apiKey = FacebookSocialNetworkingServiceAuthenticator.getApiKey();
        String sessionKey = FacebookSocialNetworkingServiceAuthenticator.getSessionKey();
        String sig = FacebookSocialNetworkingServiceAuthenticator.sign(new String[][]{{"api_key", apiKey}, {"v", v}, {"format", format}, {"uid", uid}, {"method", method}});
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{{"api_key", "" + apiKey + ""}, {"sig", sig}, {"v", v}, {"format", format}, {"uid", uid}, {"method", method}};
        RestConnection conn = new RestConnection("http://api.facebook.com/restserver.php", pathParams, queryParams);
        sleep(1000);
        return conn.get(null);
    }
}
