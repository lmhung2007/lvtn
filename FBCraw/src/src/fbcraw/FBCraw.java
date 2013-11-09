/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.fbcraw;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import java.io.IOException;

/**
 *
 * @author nghia
 */
public class FBCraw {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FBCraw fbc = new FBCraw();
        WebClient wc = new WebClient(BrowserVersion.FIREFOX_10);
        fbc.addManualCookie(wc);
        fbc.getFriendList(wc, "", 0);
    }
    
    public String getFriendList(WebClient wc, String userid, int offset) throws IOException {
        //String urlFormat = "http://www.facebook.com/ajax/browser/list/allfriends/?uid=%s&infinitescroll=1&location=friends_tab_tl&start=%s&__user=1043357436&__a=1&__dyn=798aD5z5ynU&__req=p";
        //String urlFormat = "https://m.facebook.com/profile.php?v=friends&mutual&id=100004078245066&startindex=24&__ajax__=";
        String urlFormat = "https://m.facebook.com/profile.php?v=friends&mutual&id=%s&startindex=%s&__ajax__=";
        //urlFormat = String.format(urlFormat, type, userid, offset);
        urlFormat = String.format(urlFormat, userid, offset);
        HtmlPage page  = wc.getPage(urlFormat);
        //HtmlPage page = wc.getPage("https://m.facebook.com/thuyduong.candy?v=friends");//String.format(urlFormat, username));
        //List<?> allDiv = page.getByXPath("//div");
        //System.out.println(allDiv);
        //System.out.println(page.getContent());
        System.out.println(page.asXml());
        return null;//page.getContent();
    }
        public void addManualCookie(WebClient wc) {
       String cookieString = "datr=HzplUtTyWkWemtvcka6Czb0k; lu=TgantqwkGN49LbwEy4uoArJw; c_user=100002064830137; csm=2; fr=0WdN8eOYD5CGfUzDm.AWWvL61oy7vQVzdIrV9iK4EJjQ4.BSZTrI.8B.FJ3.AWU8l15O; s=Aa7VsWcySoq7rXT3.BSZiVC; xs=225%3AvhJeziB5Fghjdg%3A2%3A1382425922%3A3169; gz=0; m_pixel_ratio=1";
        
        String[] nameValue = cookieString.split("\\;\\ ");
        for (String s : nameValue) {
            String key = s.split("\\=")[0];
            String val = s.split("\\=")[1];
            wc.getCookieManager().addCookie(new Cookie("facebook.com", key, val));
        }
    }
}
