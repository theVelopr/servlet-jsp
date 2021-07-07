# servlet & JSP

## Servlet

### 서블릿 클래스 간의 관계
- 서블릿을 구현할 때 반드시 상속받아야 하는 클래스 : HttpServlet.class
- 웹상에서 클라이언트 요청이 있을 때 해당 서블릿을 실행하는 모든 조건이 포함되어 있다. 

| NAME           | TYPE           | |
|----------------|----------------|-|
| Servlet        | Interface      | |
| GenericServlet | Abstract class | |
| HttpServlet    | Abstract class | |

#### Servlet Interface
- 반드시 구현해야 하는 메소드를 선언하고 있는 인터페이스
- init(), service(), destroy(), getServletConfig(), getServletInfo()
- 서블릿 프로그램 실행의 생명주기와 연관된 메소드

#### GenericServlet
- Servlet 인터페이스를 상속하여 클라이언트-서버 환경에서 서버단의 애플리케이션으로서 필요한 기능을 구현한 추상 클래스이다. 
- service() 메소드를 제외한 모든 메소드를 재정의하여 적절한 긴으으로 구현함.
- 상속시 애플리케이션의 프로토콜에 따라 메소드 재정의 구문을 적용해야함

#### HttpServlet 
- GenericServlet 클래스를 상속하여 service() 메소드를 재정의함으로써 HTTP 프로토콜에 알맞은 동작을 수행하도록 구현한 클래스
- HPPT 프로토콜 기반으로 브라우저로부터 요청을 전달받아서 처리하도록 하는 클래스

### 서블릿 실행 순서
1. 클라이언트로부터 처리 요청 받음 
2. 최초의 요청 여부 판단
3. 서블릿 객체 생성
4. init() 메소드 실행
5. service() 메소드 실행

### Callback Method & 서블릿 객체의 생명주기
- callback 메소드들은 서블릿 객체에 어떤 상황(이벤트)이 발생하면 호출된다.

| 메소드이름 | 메소드가 실행되는 시점                                      | 실행 횟수 | 기능 구현                      |
|-----------|-----------------------------------------------------------|-----------|-------------------------------|
| init()    | 클라이언트로부터 최초로 서블릿 요청이 있을 때 실행            | 1         | 초기화 작업                    |
| service() | 클라이언트로부터 요청이 있을 때마다 실행                     | n         | 실제 서블릿이 처리해야 하는 작업 |
| destroy() | 서블릿 객체가 메모리에서 삭제될 때 실행(서비스나 서버 중지 시) | 1         | 자원 해제 작업                 |

### @WebServlet을 통한 접근
- web.xml대신 애노테이션으로 처리할 수 있게 함.


| 속성          | 설명                                       |
|---------------|-------------------------------------------|
| description   | 서블릿 설명 입력                            |
| displayNames  | 외부에 표시되는 서블릿 이름                  |
| initParams    | @WebInitParam 애노테이션들 추가             |
| largeIcon     | 서블릿에서 사용하는 큰 크기 아이콘 위치       |
| loadOnStartUp | 서블릿이 컨테이너에 로드외는 순서 지정        |
| name          | 서블릿 이름                                 |
| smallIcon     | 서블릿에서 사용하는 작은 크기 아이콘 위치     |
| urlPatterns   | 해당 서블릿을 호출할 URL 패턴                | 
| value         | urlPatterns와 같은 용도. 속성 이름 생략 가능 |

### 요청방식에 따른 실행 - HttpServlet의 service() 메소드 
- 서블릿이 실행될 때 service() 메소드가 자동으로 실행되기에, HttpServlet 클래스에 구현되는 service() 메소드를 재정의하여 메소드의 몸체를 구현해야한다. (필수적으로 재정의 해야함)

> 1. protected void service(HttpServletRequest req, HttpServletResonse resp)
> 2. public void service(ServletRequest req, ServletResponse resp)

- 1번 형태의 service() 메소드는 클라이언트의 실행요청에 따라 서로 다른 메소드를 호출하도록 구현되어 있다. (CRUD)
- 2번 형태의 service() 메소드는 매호출시 실행된다.