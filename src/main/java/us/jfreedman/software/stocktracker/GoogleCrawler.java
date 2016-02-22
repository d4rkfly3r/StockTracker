package us.jfreedman.software.stocktracker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joshua on 2/19/2016.
 * Project: StockTracker
 */
public class GoogleCrawler {
    private static Pattern patternDomainName;
    private Matcher matcher;
    private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";

    static {
        patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
    }


    public String getDomainName(String url) {

        String domainName = "";
        matcher = patternDomainName.matcher(url);
        if (matcher.find()) {
            domainName = matcher.group(0).toLowerCase().trim();
        }
        return domainName;

    }

    public Set<String> getDataFromGoogle(String query) {

        Set<String> result = new HashSet<String>();
        String request = "https://www.google.com/search?q=" + query + "&num=100";
        System.out.println("Sending request..." + request);

        try {

            // need http protocol, set this as a Google bot agent :)
            Document doc = Jsoup
                    .connect(request)
                    .userAgent(
                            "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
                    .timeout(5000).get();

            // get all links
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                String temp = URLDecoder.decode(link.attr("href"), "UTF-8");
                if (temp.startsWith("/url?q=")) {
                    if (temp.substring("/url?q=".length()).startsWith("/")) continue;

                    if (temp.substring("/url?q=".length()).startsWith("http://webcache.googleusercontent.com/search?q=cache:")) {
                        String newUrl = URLDecoder.decode(temp.substring("/url?q=http://webcache.googleusercontent.com/search?q=cache:".length()), "UTF-8");
                        newUrl = newUrl.substring(newUrl.indexOf(':') + 1, newUrl.lastIndexOf("+" + query));
                        result.add(URLDecoder.decode(newUrl, "UTF-8").replace(" ", "%20"));
                    } else {
                        result.add(temp.substring("/url?q=".length(), temp.indexOf("&sa=U")));//, temp.indexOf("&sa=U")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
