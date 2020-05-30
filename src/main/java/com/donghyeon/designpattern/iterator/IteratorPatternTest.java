package com.donghyeon.designpattern.iterator;

public class IteratorPatternTest {

    public static void main(String[] args) {
        ChannelCollection channels = populateChannels();
        ChannelIterator baseIterator = channels.iterator(ChannelTypeEnum.ALL);
        System.out.println("모든 채널 찾기");
        while (baseIterator.hasNext()) {
            Channel channel = baseIterator.next();
            System.out.println(channel.toString());
        }
        System.out.println("******");
        // 영어로 된 채널 찾기
        System.out.println("영어로 된 채널 찾기");
        ChannelIterator englishIterator = channels.iterator(ChannelTypeEnum.ENGLISH);
        while (englishIterator.hasNext()) {
            Channel channel = englishIterator.next();
            System.out.println(channel.toString());
        }
    }

    private static ChannelCollection populateChannels() {
        ChannelCollection channels = new ChannelCollectionImpl();
        channels.addChannel(new Channel(98.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(99.5, ChannelTypeEnum.HINDI));
        channels.addChannel(new Channel(100.5, ChannelTypeEnum.KOREAN));
        channels.addChannel(new Channel(101.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(102.5, ChannelTypeEnum.HINDI));
        channels.addChannel(new Channel(103.5, ChannelTypeEnum.FRENCH));
        channels.addChannel(new Channel(104.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(105.5, ChannelTypeEnum.KOREAN));
        channels.addChannel(new Channel(106.5, ChannelTypeEnum.FRENCH));
        return channels;
    }

}
