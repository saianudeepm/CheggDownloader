/**
 * Created with IntelliJ IDEA.
 * User: sai
 * Date: 7/16/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class CheggDownloadRunner {

    public static void main(String args[]) throws Exception {
        ScreenShotDownloader downloader= new ScreenShotDownloader();
        downloader.loginToServer();
        downloader.navigateToBook();
        downloader.downloadBook();
    }

}
