package great.dog.api.util;

public class Status {

//	public final static int successCode = 200;
//	public final static int failCode = 400;
//	public final static String successMsg = "SUCCESS";
//	public final static String failMsg = "FAIL";

	public class StatusCode {
		public static final int OK = 200;
		public static final int CREATED = 201;
		public static final int NO_CONTENT = 204;
		public static final int BAD_REQUEST =  400;
		public static final int UNAUTHORIZED = 401;
		public static final int FORBIDDEN = 403;
		public static final int NOT_FOUND = 404;
		public static final int INTERNAL_SERVER_ERROR = 500;
		public static final int SERVICE_UNAVAILABLE = 503;
		public static final int DB_ERROR = 600;
	}

	public class ResponseMessage {
		public static final String LOGIN_SUCCESS = "로그인 성공";
		public static final String LOGIN_FAIL = "로그인 실패";
		public static final String READ_USER = "사용자 정보 조회 성공";
		public static final String NOT_FOUND_USER = "사용자를 찾을 수 없습니다.";
		public static final String DUPLICAtEd_USER = "이미 가입한 사용자입니다";
		public static final String CREATED_USER = "사용자 가입 성공";
		public static final String UPDATE_USER = "사용자 정보 수정 성공";
		public static final String DELETE_USER = "사용자 탈퇴 성공";
		public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
		public static final String DB_ERROR = "데이터베이스 에러";
		public static final String RES_SUCCESS = "응답 성공";
	}
}
