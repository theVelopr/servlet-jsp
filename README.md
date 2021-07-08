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

## 3. 요청정보와 응답정보

### 응답정보 처리 - HttpServletResponse
- ServletResponse : 일반적인 네트워크 통신에서의 응답 관련 메소드 제공

|함수|기능|
|-|-|
| PrintWriter getWriter() | 서비스를 요청한 클라이언트와 서버 간에 연결된 PrintSriter 객체를 생성하여 반환한다. |
| void setBufferSize(int size) | 출력스트림의 버퍼 크기를 설정 |
| void setCharacterEncoding(String charset) | 응답정보 인코딩에 사용할 문자를 설정 |
| void setContentLength(int len) | 응답정보의 데이터 길이를 설정 |
| void setContentType(String type) | 등답정보의 데이터 형식(MIME 타입)을 설정 |
| void setLocale(Locale loc) | 클라이언트가 사용하는 언어, 국가코드 등 클라이언트의 환경을 설정 |

- HttpServletResponse : HTTP 통신 기반의 응답 관련 메소드 확장 제공

|함수|기능|
|-|-|
| void addCookie(Cookie cookie)                     | 인자값으로 주어진 쿠키를 응답정보의 헤더에 추가. 쿠키는 응답정보의 Set-Cookie 헤더의 값으로 추가되어 클라이언트로 전송됨 |
| String encodeRedirectURL(String url)              | 클라이언트와 서버 간 세션이 유지되는 상태에서 브라우저 쿠키를 지원하지 않을 때 주어진 URL 뒤에 세션 아이디를 추가하고<br/> 인코딩하여 요청을 재전송한다. |
| String encodeURL(String url)                      | 주어진 URL에 세션 아이디를 추가하여 인코딩해서 반환한다. |
| void sendREdirect(String location)                | 응답을 클라이언트가 요청한 URL이 아니라 sendRedrict()에 주어진 URL로 재전송. 매개변수 location은 절대 또는 상대 경로로<br/> 지정한다. 이 메소드는 서버의 특정 자원이 다른 URL로 이동할 때 사용할 수 있는 메소드이다. |
| public void setDateHeader(String name, long date) | 날짜를 밀리 초로 변환하여 주어진 이름과 날짜를 응답정보 헤더에 설정한다. |
| public void setDeader(String name, String value)  | 응답정보의 헤더에 주어진 이름과 값을 설정한다. |
| public void setIntHEader(String name, int value)  | 주어진 이름과 정수값을 갖도록 응답정보 헤더에 추가한다. |
| public void setStatus(int sc)                     | 응답으로 전송될 HTTP 응답에 대한 상태코드를 설정한다. |

### 요청정보 처리 - HttpServletRequest
- 서블릿은 주로 웹서버의 애플리케이션 기술로 활용되므로 HTTP 프로토콜 기반의 애플리케이션이라고 할 수 있다. 
- 클라이언트가 서버로 전달하는 요청정보들
> - 클라이언트의 IP 주소, 포트 번호
> - 클라이언트가 전송한 요청 헤더 정보
> - 요청방식, 프로토콜의 종류와 버전, 요청하는 파일의 URI, 요청받은 서버의 정보
> - 서버의 호스트 이름, 포트 번호
> 사용자가 서블릿 요청 시 추가로 전달할 정보
> 질의(Query) 문자열

- ServletRequest : 일반적인 네트워크 통신에서의 요청 관련 메소드 제공
- HttpServletRequest : HTTP 통신 기반의 요청 관련 메소드를 확장하여 제공

> todo 주요 메소드 리스트작성 (p.103)

### 모든 헤더 정보
- 헤더 정보에 대한 내용을 불러오는 코드를 작성함. <br/>

#### Enumeration과 Iterator
- Enumeration과 Iterator는 Set, List, Map 계열의 Collection 객체와 다른 점은 그룹의 데이터에 접근할 때 인덱스나 키가 아닌 커서(Cursor) 라는 개념으로 접근한다.<br/>
- 주로 Collection 안에 있는 모든 요소를 차례로 접근할 때 사용하면 편리하며, 속도 면에서도 빠르다. 

- Enumeration
> hasMoreElements()
> - Enumeration의 메소드 <br/>
> 
> nextElements()
> - Enumeration의 메소드

#### Collection
- Collection 객체의 특징은 크기를 얼마든지 유동적으로 변경 할 수 있다. 그리고 하나의 그룹에 서로 다른 타입의 데이터를 저장할 수도 있다.

#### 다형성(Polymorphism)
- 서로 다른 타입의 데이터를 한 곳에 저장할 때는 Collection 객체에, 추출할 때는 Object 타입으로 반환하면 되는데, Object 타입으로 추출한 데이터를 사용하는 데 문제가 발생한다. 
실제 메모리에 만들어져 있는 객체는 하위객체인데, 이 객체의 메모리 시작 주소값을 가지는 참조변수의 타입을 상위 객체 타입으로 선언하는 것.
- 나 자신뿐만 아니라 상속받고 있는 상위 객체 타입으로 지정할 수 있다는 것을 의미.


> new
> - 힙(heap) 메모리 영역에 객체 생성. 즉, 객체가 가지고 있는 멤버변수를 생성하여 사용준비를 하라는 뜻이다. 

#### Generics
- Collection 객체를 선언할 때 이 Collection에 어떤 타입의 데이터가 저장될 것인지 미리 설정하고, 추출할때도 설정된 타입으로 자동으로 치리함.