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