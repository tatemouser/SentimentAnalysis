package scraper;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

public class Crawler {
	private static String mainText;
	
	public Crawler() {
		mainText = "";
	}
	
    public String run() {
        String url = "https://nypost.com/2023/04/11/ai-bot-chaosgpt-tweet-plans-to-destroy-humanity-after-being-tasked/";
        return crawl(1, url, new ArrayList<String>());
    }

    // Scan first level of website
    private String crawl(int level, String url, ArrayList<String> visited) {
        if (level <= 1) {
            return request(url, visited);
        }
        return null;
    }

    // Convert to text to return for wordSearch class
    private String request(String url, ArrayList<String> visited) {
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();

            if (con.response().statusCode() == 200) {
                visited.add(url);

                // Extract the main text from the page
                mainText = doc.text();                
            }
        } catch (UnknownHostException e) {
            System.out.println("Error: Unknown host - " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mainText;
    }

    /*private static Document connect(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    } */
}
