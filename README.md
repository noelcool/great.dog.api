# great.dog.api

## 적용 스펙

### LoggingAspect
- controller CRUD 시 로그 발생하도록 적용
#### 구현
- controller 내부의 모든 파일을 PointCut으로 정의
- PointCut으로 정의한 파일들에 대하여 메서드 호출 시점에 requestLogging 메서드를 실행
- 메서드 실행 전후에 시간을 기록하고 request 값에 대한 로그를 남기는 작업을 수행

