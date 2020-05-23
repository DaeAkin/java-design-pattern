# Interpreter 디자인 패턴

Interpreter 디자인 패턴의 가장 좋은 예시는 자바소스 코드를 JVM이 이해할 수 있도록 바이트 코드로 바꿔주는 자바 컴파일러 입니다. 구글 번역기도 어떠한 언어를 입력해도 해석해주는 좋은 예시 입니다.



## 예시

interpreter 패턴을 구현하기 위해서는 해석의 작업을 위한 Interpreter 컨텍스트 엔진이 필요합니다.

그 다음 interpreter 컨텍스트가 제공하는 기능들을 사용하기 위해 각각 다른 Expression을 구현을 해야 합니다.

마지막으로 유저로부터 입력을 받아 어떤 Expression을 쓸지 결정하고, 출력을 만듭니다.

예제로 두개의 입력을 받아 보겠습니다. 2진수 숫자와 16진수 숫자를 받겠습니다.

`Hexadecimal`.” Our interpreter client should return it in format “` in Binary= `” and “` in Hexadecimal= `” respectively.