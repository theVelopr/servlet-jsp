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
| PrintWriter getWriter() | 서비스를 요청한 클라이언트와 서버 간에 연결된 PrintWriter 객체를 생성하여 반환한다. |
| void setBufferSize(int size) | 출력스트림의 버퍼 크기를 설정 |
| void setCharacterEncoding(String charset) | 응답정보 인코딩에 사용할 문자를 설정 |
| void setContentLength(int len) | 응답정보의 데이터 길이를 설정 |
| void setContentType(String type) | 등답정보의 데이터 형식(MIME 타입)을 설정 |
| void setLocale(Locale loc) | 클라이언트가 사용하는 언어, 국가코드 등 클라이언트의 환경을 설정 |

- HttpServletResponse : HTTP 통신 기반의 응답 관련 메소드 확장 제공

|함수                                               |기능                                                                                                              |
|---------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| void addCookie(Cookie cookie)                     | 인자값으로 주어진 쿠키를 응답정보의 헤더에 추가. 쿠키는 응답정보의 Set-Cookie 헤더의 값으로 추가되어 클라이언트로 전송됨 |
| String encodeRedirectURL(String url)              | 클라이언트와 서버 간 세션이 유지되는 상태에서 브라우저 쿠키를 지원하지 않을 때 주어진 URL 뒤에 세션 아이디를 추가하고<br/> 인코딩하여 요청을 재전송한다.                                                                                                                                                     |
| String encodeURL(String url)                      | 주어진 URL에 세션 아이디를 추가하여 인코딩해서 반환한다.                                                            |
| void sendREdirect(String location)                | 응답을 클라이언트가 요청한 URL이 아니라 sendRedrict()에 주어진 URL로 재전송. 매개변수 location은 절대 또는 상대 경로로<br/> 지정한다. 이 메소드는 서버의 특정 자원이 다른 URL로 이동할 때 사용할 수 있는 메소드이다.                                                                                              |
| public void setDateHeader(String name, long date) | 날짜를 밀리 초로 변환하여 주어진 이름과 날짜를 응답정보 헤더에 설정한다.                                              |
| public void setHeader(String name, String value)  | 응답정보의 헤더에 주어진 이름과 값을 설정한다.                                                                      |
| public void setIntHeader(String name, int value)  | 주어진 이름과 정수값을 갖도록 응답정보 헤더에 추가한다.                                                              |
| public void setStatus(int sc)                     | 응답으로 전송될 HTTP 응답에 대한 상태코드를 설정한다.                                                               |             

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


## 4. 질의 문자열(Query String)
> 클라이언트가 웹서버에 서비스를 요청할 때 추가로 전달하는 데이터를 의미한다. 

### Query String 전송 규칙
- name = value 형식으로 전달되며, 여러 개의 name=value 쌍이 있을 때는 &을 구분자로 사용한다.
  - ex) id=guest&name=Amy
- 영문자, 숫자, 일부 특수문자는 그대로 전달되고, 이를 제외한 나머지 문자는 % 기호와 함게 16진수로 바뀌어 전달된다.
  - ex) id=guest&name=%C8%C8%C8
  - 전달되는 문자열에 ASCII 문자코드에 해당하는 문자는 그대로 서버로 전달된다. 그러나 이 문자를 제외한 나머지 문자는 % 기호와 함께 16진수로 변환하여 전달된다. 
    - 따라서, 서버 쪽에서는 변환된 문자코드에 대해 적절한 복원 처리를 해줘야 문자열이 깨지지 않는다. 
- 공백 문자는 + 기호로 변경되어 전달된다.
  - ex) id=guest&name=John+Smith
  - URL은 공백을 포함할 수 없는 특성이 있고, 공백이 있다면 공백전까지를 하나의 URL로 인식하기에 공백을 표현하기위해 + 기호를 사용함.

### HTML 입력양식의 기초 `<form>` 태그
> Query String을 입력하거나 선택할 수 있는 화면을 작성할 때는 `<form>` 태그 단위로 작업한다.

- `<form>` 태그 사용방법 예시
  - `<form action="서버프로그램 경로" method="요청방식">`

#### 입력 태그와 다양한 속성들
- 텍스트 입력상자(한 줄)
  - `<input type="text" name="변수이름" maxlength="숫자" size="숫자" value="문자열">`
    - type : 한 줄 입력상자를 만들어 주는 속성. 비밀번호로 표현할때는 'text' 대신 'password'를 입력하면 된다.
    - maxlength : 입력되는 최대 글자 수를 지정하는 속성
    - size : 화면에 보이는 입력상자의 가로 길이를 지정
    - value : 입력상자에 초기값을 지정할 때 사용.
- 체크박스
  - `<input type="checkbox" name="변수이름" value="문자열">`
    - type="checkbox" : 여러 개의 항목을 가진 다중선택 목록을 만드록 원하는 항목을 다중 선택할 수 있는 속성
    - name : 여러 개의 항목을 하나의 그룹으로 묶고, 그룹과 그룹사이를 구분할 수 있게 하는 속성
    - value : 선택된 값을 나타낼 수 있는 문자열을 서버로 전달하기 위해, 의미 있는 값을 지정해주는 속성
      - `<input type="checkbox" name="hobby" value="jiujitsu"/>` 주짓수
- 라디오 버튼
 - `<input type="radio" name="변수이름" value="문자열">`
   - type="radio" : 여러 개의 항목을 가진 단일선택 목록을 만들어주는 속성
   - name : 선택된 항목의 값을 지정하여 서버로 전달되는 이름
   - value : 선택된 값을 나타낼 수 있는 문자열을 서버로 전달하기 위해, 의미 있는 값을 지정해주는 속성
- 펼침목록
 - `<select name="변수이름"> <option value="문자열"> 항목이름 </select>`
   - `<select name="변수이름">` : 펼침목록을 생성해주는 태그
   - `<option value="문자열">` 항목이름 : 펼침목록을 구성하는 하나의 항목을 생성하는 태그
- 텍스트 입력상자(여러 줄)
 - `<textarea cols="숫자" rows="숫자" name="변수이름"></textarea>`
   - cols : 상자를 만들 때 너비, 즉 열의 수를 지정하는 속성
   - rows : 상자를 만들 때 높이, 즉 행의 수를 지정하는 속성
   - name : 상자에 입력된 문자열들을 값으로 지정하여 서버로 전달할 때 사용하는 변수이름을 지정
- 전송버튼
 - `<input type="submit" value="문자열">`
   - 클라이언트로부터 입력받은 데이터를 서버로 전달하여 처리하는 모든 `<form>`태그에는 반드시 하나의 전송 버튼이 있어야 한다. 
- 초기화 버튼
  - `<input type="rest" value="문자열">`
    - 폼에서 클라이언트가 입력한 값을 모두 지우는 기능이 있는 버튼을 생성한다. 

#### HTML 태그 주의사항
- 속성값은 대소문자를 구분한다
- 속성값을 지정할 때는 큰따움표(" ")로 감싸준다
- 시작 태그와 끝 태그의 짝을 맞춰준다
 
### 요청방식에 따른 처리
#### GET 방식
- GET 방식은 Query String을 요청정보 헤더의 URI에 포함함으로 서버로 전달되는 값이 브라우저 주소창에 모두 노출되며, 또한 데이터 크기에 제한이 있어서 서버가 처리할 수 있는 이상의 길이가 전달되면 414 에러코드를 보내도록 정의 되어 있다. 

#### POST 방식
- POST 방식은 Query String이 요청정보의 BODY에 포함된다. 따라서 외부에 노출되지 않도록 서버에 전달이 되며, 길이에 제한도 없다. 다만 클라이언트 측에서 보낼때 인코딩해서 보내고 전달받은 서버측에서 다시 디코딩하는 추가 작업이 필요하다. 

### 서블릿 
#### 서블릿 메소드
- 요청방식에 따라 doGet 메소드와 doPost 메소드를 사용 할 수 있다. 

#### QueryString 추출
- String getParameter(String name)
  - QueryString으로 넘어온 값을 하나씩 추출할 때 사용 (name이 중복되지 않고 유일하게 하나만 넘어올때 사용함)
  - 이 메소드의 반환 타입은 String이기에 추출하고자 하는 정보의 타입에 맞게 int나 float등으로 변환하는 추가 작업이 필요하다.

- String [] getpameterValues(String name)
  - 같은 이름으로 여러 개의 변수가 전달되었을 때 한번에 모든 값을 추출하여 String 타입의 배열로 받고 싶을 대 사용한다. 

- String getQeuryString() 
  - 클라이언트가 전달할 QueryString 전체를 하나의 String으로 추출해준다.
  - GET 방식 요청에서만 사용할 수 있음
    - URI 정보에서 ? 다음에 나오는 문자열들을 추출하기 때문이다. 

- ServletInputStream getInputStream() throws IOException
  - HTTP의 요청정보 몸체와 연결된 입력스트림을 생성하여 반환한다. 
  - Body 정보를 읽고 싶을때 getInputStream()에서 반환한 ServletInputStream으로 읽어올 수 있다.
  - POST 방식의 QueryString 전체를 한번에 추출할 때 사용 할 수 있는 메소드이다. 
  
    
## 5. 서블릿 설정과 변수 
### 5.1 서블릿 환경설정
#### 1. web.xml
- 서버가 시작할 때 웹서버가 사용하는 웹 애플리케이션 서비스 실행에 관한 전반적인 내용을 정의하는 환경설정 파일
- 서블릿을 정의하려면 `<servlet>` 태그를 추가

- `<servlet>`
  - 설정하려는 서블릿을 등록
  - 필수 하위 태그 (pair 관계)
    - `<servlet-name>`
    - `<servlet-class>`
- `<init-param>`
  - 서블릿에 변수를 전달할 때 사용
  - `<servlet>`의 하위태그 중 하나
  - 반드시 `<param-name>`과 `<param-value>` 태그로 구성해야한다
    - `<param-name>` : 변수의 이름을 지정하는 태그. 대소문자 구분함
    - `<param-value>` : 매핑되는 `<param-name>`에 저장되는 변수의 값을 지정하는 태그
- `<load-on-startup>`
  - 웹 서비스가 시작할 때 서블릿 객체를 생성할 수 있는 태그
  - 태그 값으로 숫자를 지정
  - 서버가 시작될 때 생성해야 하는 서블릿 객체가 여러 개일 때 `<load-on-startup>` 태그의 값으로 우선순위를 지정하며, 숫자값이 낮을수록 우선순위가 높다. 
  
#### 2. ServletConfig
- web.xml의 `<servlet>` 태그에 설정한 정보를 서블릿 페이지 내에서 추출시, ServletConfig 객체에서 제공하는 메소드를 사용해야한다. 
- ServletConfig 객체는 서블릿이 실행될 때 자동으로 생성된다. 

> ServletConfig 객체가 생성되는 시점
> 
> `[client]` --(서블릿 실행 요청)--> `[Web Server]` ----> `[Servlet Contaier]` --(최초요청)--> `[YES]` OR `[NO]`
> IF `[YES]`
>   - 메모리 로딩 -> 서블릿 객체 생성 -> ServletConfig 객체 생성 -> init(ServletCOnfig) -> HttpServletRequest, Response 객체 생성 -> Service(HSRq, HSRsp)
> IF '[NO]`
>   - HttpServletRequest, Response 객체 생성 -> Service(HSRq, HSRsp)

`#` Another way
- HttpServlet 객체를 이용하는 방법
  - 서블릿 페이지를 작성할때 반드시 상속받아야 하는 객체인 HttpServlet을 이용하는 방법이다.

### 5.2 서블릿 변수
#### 1. 서블릿 동시 요청
- CGI 방식 
  - 웹서버의 직접적인 호출로 실행
  - 클라이언트로부터 요청이 들어올 때마다 독립적인 프로세스가 만들어진다
  - 클라이언트의 요청 수에 비례하여 프로세스가 만들어지고 메모리 사용량도 함께 증가  
- 애플리케이션 서버가 실행하는 방식
  - 서블릿 컨테이너(애플리케이션)
  - 서블릿 컨테이너 실행시, 서블릿 객체의 메모리 로딩과 객체 생성, 그리고 init() 메소드 호출 작업을 진행하기 전에 프로세스가 생성됨.
  - service() 메소드는 최초 요청 시 만들어진 프로세스 안에 작은 프로세스를 만들고, 그 작은 프로세스 안에서 실행된다 (Thread)
    > - 최초 요청 : 프로세스 생성 
    >    - service() 요청 : 스레드 생성

#### 2. 서블릿 변수 특징
- 멤버변수와 지역변수를 구분하여 사용 할 수 있음
- 멤버변수로 선언
  - 하나의 서블릿에 여러 클라이언트가 공유해서 사용해야 하는 데이터
  - 객체 생성 시 힙 메모리에 생성되며, 서블릿을 실행하는 클라이언트들이 공통으로 사용함
- 지역변수로 선언
  - 각각의 클라이언트가 독립적으로 사용해야 하는 데이터
  - 스택 메모리에 생성되며, 클라이언트마다 독립적으로 사용함

> 서블릿의 지역변수는 각 요청마다 스레드별로 스택 영역에 할당되어 사용되지만, 멤버변수는 힙 영역에 할당되어 서블릿을 실행하는 여러 스레드에서 공유한다.

`#` 자바의 변수
|구분|멤버변수|멤버변수|지역변수 |
|-|-|-|-|
||class 변수|instance 변수| |
|-|-|-|-|
|선언 위치|클래스의 멤버로 선언|클래스의 멤버로 선언|메소드 안에서 선언|
|선언 방법|static 선언| | | 
|사용 메모리|code 영역|heap 영역|stack 영역|
|생성 시점|프로그램 시작 시|객체 생성 시|메소드 실행 시|
|제거 시점|프로그램 종료 시|GC에 의해|메소드 종료 시|

- 클래스 변수
  - 클래스 변수는 코드 영역이나 메소드 영역이라는 곳에 프로그램 시작과 동시에 만들어지나 프로그램이 종료될 때까지 메모리에 존재하므로 프로그램 전반에 걸쳐 사용되는 데이터일 때만 클래스 변수로 저장해야 한다. 
- 인스턴스 변수
  - 자바 객체는 new 키워드를 이용해 직접 객체를 생성할 수 있고, 이때 힙 영역에 멤버변수가 만들어진다. 힙 영역에 만들어진 인스턴스 변수는 객체가 삭제될 때까지 사용할 수 있다.
- 지역 변수
  - 메소드 실행 시 인자값을 받기 위해 선언된 매개변수도 지역변수에 해당된다. 메소드가 종료되면 스택에 있는 모든 지역변수도 삭제된다.
  
#### 3. 서블릿 지역 변수
- 여러 클라이언트가 동시에 요청했을 때, 요청마다 개별적으로 할당된다.
- 예시
  ```java
  import java.io.IOException;
  import java.io.PrintWriter;

  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;

  @WebServlet("/local")
  public class LocalTestServlet extends HttpServlet{
  
  	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws
  	ServletException, IOException {
    
  		int number = 0;
  		String str = req.getParameter("msg");
  		resp.setContentType("text/html; charset=UTF-8");
  		PrintWriter out = resp.getWriter();
  		out.println("<html><head><title>MultiThread Test</title></head></html>");
  		out.println("<body><h2>처리 결과(지역변수)</h2>");
  		while(number ++ < 10) {
  			out.print(str + " : : " + number + "<br>");
  			out.flush();
  			System.out.println(str + " : " + number);
  			try {
  				Thread.sleep(1000);
  			} catch (Exception e) {
  				System.out.println(e);
  			}
  		}
  		out.println("<h2>Done " + str + " !!</h2>");
  		out.println("</body></html>");
  		out.close();
  	}
  }
  ```
  - http://localhost:8080/edu/local?msg=one 과 http://localhost:8080/edu/local?msg=two 두개의 창을 띄워놓고 실행
  - 처리결과를 보면 두 개의 클라이언트로부터 동일한 서블릿 요청이 들어오는 것을 확인 할 수 있다. 
    - 서버 입장에서는 하나의 서블릿에 대하여 동시에 두 개의 요청을 처리해야 함 
    - service() 메소드를 실행하는 두 개의 스레드가 동시에 실행 중
  - 지역변수로 선언된 데이터는 클라이언트별로 독립적으로 사용할 수 있음을 확인
  
#### 4. 서블릿 멤버 변수
- 각 클라이언트들의 동시 요청 수와 관계없이 하나의 메모리 공간을 할당하여 전역적으로 처리할 때만 멤버변수를 선언하여 활용한다.
- 예시
  ```java
  import java.io.IOException;
  import java.io.PrintWriter;

  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;

  @WebServlet("/member")
  public class MemberTestServlet extends HttpServlet {
  
  	String str;
  
  	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws
  	ServletException, IOException {
    
  		int number = 0;
  		str = req.getParameter("msg");
  		resp.setContentType("text/html; charset=UTF-8");
  		PrintWriter out = resp.getWriter();
  		out.println("<html><head><title>MultiThread Test</title></head></html>");
  		out.println("<body><h2>처리 결과(멤버변수)</h2>");
  		while(number ++ < 10) {
  			out.print(str + " : " + number + "<br>");
  			out.flush();
  			System.out.println(str + " : " + number);
  			try {
  				Thread.sleep(1000);
  			} catch (Exception e) {
  				System.out.println(e);
  			}
  		}
  		out.println("<h2>Done " + str + " !!</h2>");
  		out.println("</body></html>");
  		out.close();
  	}
  }
  ```
  - 위의 예시 코드를 통해 서블릿을 구현할 때 멤버변수로 선언하면 같은 서블릿을 요청하는 클라리언트끼리 서로 공유되므로 값을 변경하면 서로에게 영향을 미치는 것을 확인 할 수 있다.
  
## 6. 상태정보 유지 기술
### 6.1 상태정보 유지
- 무상태(Stateless)
  - 클라이언트와 서버 간에 연결상태가 유지되지 않는 통신 방식
  - 웹에서 사용하는 HTTP 프로토콜의 통신 방식은 클라이언트와 서버 간의 연결을 클라이언트로부터 요청이 있을 때마다 매번 새롭게 연결하는 방식을 사용한다. 
    - 요청이 있을 때마다 연결 작업이 새롭게 이루어지고, 서버가 클라이언트에게 응답을 보내는 즉시 끊어진다.
  - 다수의 사용자를 상대로 하는 인터넷에서 네트워크나 서버의 오버헤드를 줄일 수 있는 장점이 있다.
  - 요청을 연속적으로 이어나가기 위해 이전의 처리결과를 저장하여 유지하는 추가 작업을 하지 않으면 지속적인 서비스 처리가 어렵다는 단점도 있다.

- Stateless 특성때문에 이전 요청에서의 처리결과를 계속해서 다른 요청에 사용할 수 없기에, 이를 가능하게 하려면 서버 측이든, 클라이언트 측이든 어딘가에 저장해서 정보를 유지해야 한다. 
- 상태정보(State Information)
  - 이전 요청에서 처리된 정보를 계속해서 사용하고 싶다면, 반드시 클라이언트나 서버에 저장해서 유지해야하고, 클라이언트나 서버에 계속된 요청에서 사용할 수 있도록 저장한 정보들이 상태정보이다.
  
  #### 1. 저장 위치 분류
  - 클라이언트 측에 저장 기술
    - 쿠키 (Cookie)
    - 서블릿에서 쿠키 기능이 있는 객체
      - javax.servlet.http.Cookie
  - 서버 측에 저장 기술
    - 상태정보가 저장된 객체가 힙 메모리 영역에 존재하는 한 동록된 상태정보는 계속해서 사용할 수 있다.
    - 서블릿에서 서버 측에 상태정보를 저장할 수 있는 객체
      - javax.servlet.SErvletContext
      - javax.servlet.http.HttpSession
      - javax.servlet.http.HttpServletRequest
  
  #### 2. 유지 기간 분류
  - 웹 애플리케이션 단위 유지
    - ServletContext
      - 생명주기가 웹 애플리케이션과 같은 객체
      - 웹 애플리케이션 서비스가 시작될 때 생성되고 종료될 때 소멸한다.
      - javax.servlet.ServletContext
  - 클라이언트 단위 유지
    - 대표적인 예시 
      - 로그인 작업
    - 클라이언트별로 생성되는 HttpSession 객체를 통해 클라이언트 단위로 상태정보를 유지할 수 있다.
      - javax.servlet.http.Cookie
      - javax.servlet.http.HttpSession
  - 요청 단위 유지
    - 클라이언트로부터 요청이 들어오고 응답이 나가기까지가 하나의 요청
    - 하나의 요청에서만 상태정보를 유지하고자 할 때 HttpServletReq  uest 객체를 통해서 할 수 있다.
      - javax.servlet.http.HttpServletRequest

### 6.2 ServletContext
- 웹 애플리케이션 단위로 정보를 유지하는 기능도 있지만, 서블릿과 서블릿 컨테이너 간의 통신하는 기능도 제공함

#### 1. ServletContext 생성
- 서블릿 컨테이너와 통신하기 위해서 사용되는 메소드를 지원하는 인터페이스
- 서블릿 컨테이너가 시작될 때 웹서버에 등록된 웹 애플리이케이션 단위로 하나의 ServletContext 객체가 자동으로 생성됨
  - 생명주기 : 서비스 종료시 소멸
- Web Application 내에 있는 모든 Servlet, 그리고 JSP간에 정보를 공유할 수 있고, Servelt Container에 대한 정보를 추출할 수 있게 하는 기술

1. init()을 재정의하여 추출하는 방법
```java
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/context1")
public class ServletCOntextTestServlet extends HttpServlet{
	
	ServletContext servletContext; // serlvetContext 주소값을 갖는 참조 변수 선언
	
	@Override // ServletConfig 객체를 config 변수로 받음 
	public void init(ServletConfig config) throws ServletException {
		servletContext = config.getServletContext(); // servletContext 주소값을 추출 후, serlvetContext 변수에 저장
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.print("Context : " + servletContext); // 추출한 serlvetContext 주소값을 출력
	}

}
```
2. HttpServlet을 통해 추출하는 방법
