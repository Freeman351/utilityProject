package com.freeman.rss;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedOutput;

public class SyndFeedTest {

	public static void main(String[] args) throws Exception {
	String feedType = "rss_2.0";
	String fileName = "rss.xml";

	SyndFeed feed = new SyndFeedImpl();
	feed.setEncoding("utf-8");
	
	feed.setFeedType(feedType);

	feed.setTitle(String.format("<![CDATA[%s]]>", "Developers-Blog.org Feed"));
	feed.setLink("http://developers-blog.org");
	feed.setDescription("");

	List entries = new ArrayList();
	SyndEntry entry;
	SyndContent description;

	entry = new SyndEntryImpl();
	entry.setTitle("My first RSS example with Rome");
	entry.setLink("http://developers-blog.org/...");
	entry.setPublishedDate(new Date());
	description = new SyndContentImpl();
	description.setType("text/html");
	description.setValue("<p>Haha: nothing here</p>");
	entry.setDescription(description);
	entries.add(entry);

	feed.setEntries(entries);

	Writer writer = new FileWriter(fileName);
	SyndFeedOutput output = new SyndFeedOutput();
	output.output(feed,writer);
	writer.close();	}
	
}
