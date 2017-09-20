package com.freeman.rss;

import java.util.Date;

import com.freeman.rss.model.BaseRssItem;
import com.freeman.rss.model.ChannelInfo;
import com.freeman.rss.model.ItemInfo;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;


public class RssUtilTest extends AbstractRssUtil{
 
	public static void main(String[] args) throws Exception {
		
		ChannelInfo channelInfo = getChannelInfo();
		Channel channel = createChannel(channelInfo);
		 
		BaseRssItem content1  = new BaseRssItem();
		content1.setTitle("Spring MVC Tutorial 1");
		content1.setUrl("http://www.mkyong.com/spring-mvc/tutorial-1-url");
		content1.setLink("http://www.mkyong.com/spring-mvc/tutorial-1-link");
		content1.setPubDate(new Date());
		Item item1 = createItem(getItemInfo(content1));
		addItemToChannel(channel, item1);
 
		BaseRssItem content2  = new BaseRssItem();
		content2.setTitle("Spring MVC Tutorial 2");
		content2.setLink("http://www.mkyong.com/spring-mvc/tutorial-2-link");
		content2.setUrl("http://www.mkyong.com/spring-mvc/tutorial-2-url");
		content2.setPubDate(new Date());
		Item item2 = createItem(getItemInfo(content2));
		addItemToChannel(channel, item2);
		
		System.out.println(generateRssXmlString(channel));
		generateRssFile(channel, "rssfeeds");
 
	}
 	
	static ChannelInfo getChannelInfo(){
		ChannelInfo channelInfo = new ChannelInfo();
		
		channelInfo.setTitle("Mkyong Dot Com");
		channelInfo.setDescription("Java Tutorials and Examples");
		channelInfo.setLink("http://www.mkyong.com");
		channelInfo.setFeedType("rss_2.0");
		
		return channelInfo;
	}

	static ItemInfo getItemInfo(BaseRssItem item){
		ItemInfo itemInfo = new ItemInfo();
		
		itemInfo.setLink(item.getLink());
		itemInfo.setTitle(item.getTitle());
		itemInfo.setUrl(item.getUrl());
		itemInfo.setPubDate(item.getPubDate());
		
		return itemInfo;
		
	}

}
