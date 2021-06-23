package edu.bit.ex.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import edu.bit.ex.vo.kakao.KakaoAuth;
import edu.bit.ex.vo.kakao.KakaoProfile;

@Service
public class KakaoServiceImpl {

	private final static String K_CLIENT_ID = "aa2d77e411b24bb048204ca4e5f7a492";
	private final static String K_REDIRECT_URI = "http://localhost:8282/ex/auth/kakao/callback";


	public String getAuthorizationUrl() {
		String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?" + "client_id=" + K_CLIENT_ID + "&redirect_uri="
				+ K_REDIRECT_URI + "&response_type=code";
		return kakaoUrl;
	}

	// POST방식으로 key-value 데이터를 요청(카카오쪽으로)
	private final static String K_TOKEN_URI = "https://kauth.kakao.com/oauth/token";

	// 토큰 받기 설명
	// https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-token
	// public RetKakaoAuth getKakaoTokenInfo(String code) {
	public KakaoAuth getKakaoTokenInfo(String code) {
		RestTemplate restTemplate = new RestTemplate();// http 요청을 간단하게 해줄 수 있는 클래스
		// Set header : Content-type: application/x-www-form-urlencoded
		HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// Set parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", K_CLIENT_ID);
		params.add("redirect_uri", K_REDIRECT_URI);
		params.add("code", code);

		// Set http entity
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// 실제로 요청하기
		// Http 요청하기 - POST 방식으로 - 그리고 response 변수의 응답을 받음.
		ResponseEntity<String> response = restTemplate.postForEntity(K_TOKEN_URI, kakaoTokenRequest,
				String.class);

		System.out.println(response.getBody());
		Gson gson = new Gson();
		//Gson ,Json Simple, ObjectMapper 세가지 정도의 클래스가 있음
		if (response.getStatusCode() == HttpStatus.OK) {
			return gson.fromJson(response.getBody(), KakaoAuth.class); 
		}
		
		return null;

	}
	
	private final static String K_PROFILE_URI = "https://kapi.kakao.com/v2/user/me";
	// 프로파일 받기(토큰을 받았으니 해당 토큰으로 리소스 접근)
	//https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info
	public KakaoProfile getKakaoProfile(String accessToken) {
		
		RestTemplate restTemplate = new RestTemplate();// http 요청을 간단하게 해줄 수 있는 클래스
        
		// Set header : Content-type: application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);
        
        // Set http entity
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
		Gson gson = new Gson();
        try {
            // Request profile
            ResponseEntity<String> response = restTemplate.postForEntity(K_PROFILE_URI, request, String.class);
            
            if (response.getStatusCode() == HttpStatus.OK)
            	System.out.println(response.getBody());
            	KakaoProfile profile = gson.fromJson(response.getBody(), KakaoProfile.class);
            	System.out.println(profile);
            	return gson.fromJson(response.getBody(), KakaoProfile.class);
            
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
	
	

}
