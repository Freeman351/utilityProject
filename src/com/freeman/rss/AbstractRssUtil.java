package com.freeman.rss;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.freeman.rss.model.ChannelInfo;
import com.freeman.rss.model.ItemInfo;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.io.WireFeedOutput;


public abstract class AbstractRssUtil {
 
	static Channel createChannel(ChannelInfo channelInfo) {
		 
	    Channel channel = new Channel();
	 
	    channel.setFeedType(channelInfo.getFeedType());	 
	    channel.setTitle(channelInfo.getTitle());
	    channel.setDescription(channelInfo.getDescription());
	    channel.setLink(channelInfo.getLink());
	    channel.setLanguage(channelInfo.getLanguage());
	    channel.setPubDate(channelInfo.getPubDate());
	    channel.setLastBuildDate(channel.getLastBuildDate());
	    channel.setCopyright(channelInfo.getCopyright());
	    	 
	    return channel;
	}
	
	static Item createItem(ItemInfo itemInfo) {
	    Item item = new Item();
	 
	    item.setAuthor(itemInfo.getAuthor());
	    item.setCategories(itemInfo.getCategories());
	    item.setComments(itemInfo.getComments());
	    item.setContent(itemInfo.getContent());
	    item.setDescription(itemInfo.getDescription());
	    item.setExpirationDate(itemInfo.getExpirationDate());
	    item.setGuid(itemInfo.getGuid());
	    item.setLink(itemInfo.getLink());
	    item.setPubDate(itemInfo.getPubDate());
	    item.setSource(itemInfo.getSoure());
	    item.setTitle(itemInfo.getTitle());
	    item.setUri(itemInfo.getUrl());
	 
	    return item;
	}	

	static Channel addItemToChannel(Channel channel, Item item) {
	    List items = (List) channel.getItems();
	    if (items == null) {
	        items = new ArrayList();
	    }
	    items.add(item);
	    channel.setItems(items);
	    return channel;
	}

	static boolean generateRssFile(Channel channel, String fileName) {
	    try {
	        File rssDoc = new File(fileName);
	        if (!rssDoc.exists()) {
	        	rssDoc.createNewFile();
	        }
	        WireFeedOutput wfo = new WireFeedOutput();
	        wfo.output(channel, rssDoc);
	    } catch (Exception ee) {
	        System.out.println(ee);
	    }
	    return true;
	}
	
	static String generateRssXmlString(Channel channel) {
		String result = null;
	    try {
	        WireFeedOutput wfo = new WireFeedOutput();
	        result = wfo.outputString(channel);
	    } catch (Exception ee) {
	        System.out.println(ee);
	    }
	    return result;
	}
	
}
