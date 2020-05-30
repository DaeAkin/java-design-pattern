# Iterator 패턴

Iterator 패턴의 목적은 다음과 같습니다. 

> "Provides a way to access the elements of an aggregate object without exposing its underlying represenation."

Iterator 패턴은 collection을 순회하는 것 뿐만 아니라, 요구사항에 따라 다른 종류의 iterator를 제공해줄 수 있습니다. 

Iterator 패턴은 collection을 순회하는 실제 구현체를 숨기기 때문에, 클라이언트에서는 그저 iterator 메소드만 이용합니다.

## Iterator 패턴의 예제

간단한 예제로 Iterator 패턴이 뭔지 알아보겠습니다. 여러 개 라디오 채널과 그 채널을 하나씩 채널의 타입에 따라 순회하는 클라이언트 프로그램이 있다고 가정해보겠습니다. 예를 들어 몇몇 클라이언트 프로그램은 English 채널에 관심이 있을 것이고, 다른 채널 타입엔 관심이 없을 수 있습니다.

그래서 라디오의 채널 collection을 클라이언트에게 제공해주고, 클라이언트에서 채널을 순회하는 로직과 처리하는 방법을 클라이언트에 작성해보겠습니다.(?) 그러나 이 해결법은 클라이언트가 순회하는 방법을 생각해내야된다는 이슈가 있기 때문에 클라이언트 로직이 100% 맞다고 확신할 순 없습니다. 게다가 클라이언트의 수가 많아 질수록 유지보수도 하기 어려워 집니다. 

그래서 이번 이럴 때 iterator 패턴을 사용해서 채널 타입에 따라 순회방법을 제공해주어, 정해준 iterator 방법에 따라 클라이언트가 Collection을 순회할 수 있게 됩니다.

## Interator 패턴 구현

The first part of implementation is to define the contract for our collection and iterator interfaces.

#### ChannelTypeEnum.java

```java
public enum ChannelTypeEnum {
    ENGLISH, HINDI, FRENCH, KOREAN, ALL;
}
```

ChannelTypeEnum은 채널의 언어 종류를 정의해 놓은 enum 클래스 입니다.

#### Channel.java

```java
public class Channel {

    private double frequency;
    private ChannelTypeEnum TYPE;

    public Channel(double freq, ChannelTypeEnum type){
        this.frequency=freq;
        this.TYPE=type;
    }

    public double getFrequency() {
        return frequency;
    }

    public ChannelTypeEnum getTYPE() {
        return TYPE;
    }

    @Override
    public String toString(){
        return "주파수="+this.frequency+", 타입="+this.TYPE;
    }

}
```

Channel 클래스는 주파수와 언어를 정의해 놓은 도메인 클래스 입니다.

#### ChannelCollection.java

```java
public interface ChannelCollection {
     void addChannel(Channel channel);
     void removeChannel(Channel channel);
     ChannelIterator iterator(ChannelTypeEnum type);
}
```

#### ChannelIterator.java

```java
public interface ChannelIterator {
     boolean hasNext();
     Channel next();
}
```

이제 기본 인터페이스와 코어 클래스가 준비가 됐습니다. Collection 인터페이스를 구현해서 사용할 iterator 클래스를 구현해보겠습니다.

#### ChannelCollectionImpl.java

```java
public class ChannelCollectionImpl implements ChannelCollection {

    private List<Channel> channelsList;

    public ChannelCollectionImpl() {
        channelsList = new ArrayList<>();
    }

    public void addChannel(Channel channel) {
        this.channelsList.add(channel);
    }

    public void removeChannel(Channel channel) {
        this.channelsList.remove(channel);
    }

    @Override
    public ChannelIterator iterator(ChannelTypeEnum type) {
        return new ChannelIteratorImpl(type, this.channelsList);
    }

    private class ChannelIteratorImpl implements ChannelIterator {

        private ChannelTypeEnum type;
        private List<Channel> channels;
        private int position;

        public ChannelIteratorImpl(ChannelTypeEnum ty,
                                   List<Channel> channelsList) {
            this.type = ty;
            this.channels = channelsList;
        }

        @Override
        public boolean hasNext() {
            while (position < channels.size()) {
                Channel channel = channels.get(position);
                if (channel.getTYPE().equals(type) || type.equals(ChannelTypeEnum.ALL)) {
                    return true;
                } else
                    position++;
            }
            return false;
        }

        @Override
        public Channel next() {
            Channel channel = channels.get(position);
            position++;
            return channel;
        }

    }
}
```

iterator 인터페이스의 구현을 inner 클래스에 구현했기 때문에 다른 collection에서는 이 클래스를 사용할 수 없습니다. 



#### IteratorPatternTest.java

```java
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
```

