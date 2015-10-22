package project.securityTest;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import project.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class AuthenticationTest {
	

    @Value("${local.server.port}")
    private int port;

	private String base;
	private URL dataEmployees;
	private RestTemplate template;
	private URL authenticationUrl;
	
	private HttpEntity<String> entity;
	private String token;
	
	private List<String> projectGetUrls;
	private List<String> employeeGetUrls;
	private List<String> effortGetUrls;
	
	@Before
	public void setUp() throws Exception {
		
		this.dataEmployees = new URL("http://localhost:" + port + "/data/employees");
		
		//GET TOKEN
		String plainCreds = "clientapp:123456";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		
		this.authenticationUrl = new URL("http://localhost:" + port + "/oauth/token");
		template = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		headers.add("Accept", "application/json");
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();  
		body.add("grant_type", "password");
		body.add("username", "user");
		body.add("password", "pass");
		
		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
		
		
		ResponseEntity<String> obj = template.exchange(authenticationUrl.toURI(), HttpMethod.POST, entity, String.class);
		String tokenWithQuotes = obj.getBody().split(":")[1].split(",")[0];
		this.token = tokenWithQuotes.substring(1, tokenWithQuotes.length()-1);
		
		HttpHeaders headersToken = new HttpHeaders();
		headersToken.add("Authorization", "Bearer " + token);
		this.entity = new HttpEntity<String>(headersToken);
		
		// URL
		
		this.projectGetUrls = new ArrayList<String>();
		projectGetUrls.add("data/projects"); projectGetUrls.add("data/projects/1"); projectGetUrls.add("data/projects/1/employees"); 
		projectGetUrls.add("data/projects/1/manager"); projectGetUrls.add("data/projects/1/employees"); projectGetUrls.add("data/projects/1/employees/1/effort");
		projectGetUrls.add("data/projects/1/employees/effort");
		this.base = "http://localhost:" + port + "/";
		
		this.employeeGetUrls = new ArrayList<String>();
		employeeGetUrls.add("data/employees"); employeeGetUrls.add("data/employees/1"); employeeGetUrls.add("data/employees/1/projects"); 
		employeeGetUrls.add("data/employees/1/projects/manager"); employeeGetUrls.add("data/employees/user"); 
		employeeGetUrls.add("data/employees/user/role"); employeeGetUrls.add("data/employees/effort/1");
		
		this.effortGetUrls = new ArrayList<String>();
		effortGetUrls.add("data/efforts"); effortGetUrls.add("data/efforts/2");
		
		
	}
	
	@Test
	public void employeeOtherHttpMethods(){
		
		String url = this.base + "data/employees/1/projects";
		HttpHeaders headersToken = new HttpHeaders();
		headersToken.add("Authorization", "Bearer " + token);
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();  
		body.add("projectId", "1");
		HttpEntity<?> entity = new HttpEntity<Object>(body, headersToken);
		ResponseEntity<String> obj = template.exchange(url, HttpMethod.POST, entity, String.class);
		
		System.out.println(url);
		String statusCode = obj.getStatusCode().toString();
		System.out.println(statusCode);
		Assert.assertNotEquals("404", statusCode);
		Assert.assertNotEquals("400", statusCode);
		Assert.assertNotEquals("500", statusCode);
		Assert.assertNotEquals("401", statusCode);
		
		url = this.base + "data/employees/1";
		headersToken.add("Accept", "application/json");
		headersToken.add("Content-Type", "application/json");
		entity = new HttpEntity<Object>("\"name\": \"pero1\", "
				+ "\"lastName\": \"antic\", "
				+ "\"jobDescription\": \"basketball player in fener\"", headersToken);
		
		
		obj = template.exchange(url, HttpMethod.PUT, entity, String.class);
		System.out.println(url);
		statusCode = obj.getStatusCode().toString();
		System.out.println(statusCode);
		Assert.assertNotEquals("404", statusCode);
		Assert.assertNotEquals("400", statusCode);
		Assert.assertNotEquals("500", statusCode);
		Assert.assertNotEquals("401", statusCode);
		
		
		
		url = this.base + "data/employees";
		entity = new HttpEntity<Object>("{\"employee\" : {\"name\": \"pero1oo\",\"lastName\": \"antic\",\"jobDescription\": \"basketball player in fenerbahce\"},\"user\" : {\"username\" : \"korisnik\",\"password\" : \"lozinka\"}}", headersToken);
		
		obj = template.exchange(url, HttpMethod.POST, entity, String.class);
		System.out.println(url);
		statusCode = obj.getStatusCode().toString();
		System.out.println(statusCode);
		Assert.assertNotEquals("404", statusCode);
		Assert.assertNotEquals("400", statusCode);
		Assert.assertNotEquals("500", statusCode);
		Assert.assertNotEquals("401", statusCode);
		
		
		headersToken = new HttpHeaders();
		headersToken.add("Authorization", "Bearer " + token);
		entity =  new HttpEntity<Object>(headersToken);
		
		
		obj = template.exchange(url, HttpMethod.DELETE, entity, String.class);
		url = this.base + "data/employees/1/projects/1";
		System.out.println(url);
		statusCode = obj.getStatusCode().toString();
		System.out.println(statusCode);
		Assert.assertNotEquals("404", statusCode);
		Assert.assertNotEquals("400", statusCode);
		Assert.assertNotEquals("500", statusCode);
		Assert.assertNotEquals("401", statusCode);
		
		
		url = this.base + "data/employees/1";
		
		System.out.println(url);
		obj = template.exchange(url, HttpMethod.DELETE, entity, String.class);
		statusCode = obj.getStatusCode().toString();
		System.out.println(statusCode);
		Assert.assertNotEquals("404", statusCode);
		Assert.assertNotEquals("400", statusCode);
		Assert.assertNotEquals("500", statusCode);
		Assert.assertNotEquals("401", statusCode);
		
		
	}
	
	@Test
	public void projectOtherHttpMethods(){

		String url = this.base + "data/projects";
		
		HttpHeaders headersToken = new HttpHeaders();
		headersToken.add("Authorization", "Bearer " + token);
		HttpEntity<?> entity = new HttpEntity<Object>("\"name\": \"project1New11NEWW\", "
				+ "\"description\": \"descNewRB12\", "
				+ "\"fromDate\": \"2015-10-11\", "
				+ "\"toDate\": \"2015-10-15\", "
				+ "\"status\": \"inDevelopment\", "
				+ "\"managerId\" : 15", headersToken);
		ResponseEntity<String> obj = template.exchange(url, HttpMethod.GET, entity, String.class); 
		System.out.println(url);
		String statusCode = obj.getStatusCode().toString();
		System.out.println(statusCode);
		Assert.assertNotEquals("404", statusCode);
		Assert.assertNotEquals("400", statusCode);
		Assert.assertNotEquals("500", statusCode);
		Assert.assertNotEquals("401", statusCode);
		
		headersToken.add("Accept", "application/json");
		headersToken.add("Content-Type", "application/json");
		entity = new HttpEntity<Object>("\"name\": \"project1New11NEdsadas\", "
				+ "\"description\": \"descNewRB12\", "
				+ "\"fromDate\": \"2015-12-11\", "
				+ "\"toDate\": \"2015-12-15\", "
				+ "\"status\": \"inDevelopment\", "
				+ "\"managerId\" : 15", headersToken);
		url+="/1";
		obj = template.exchange(url, HttpMethod.PUT, entity, String.class); 
		System.out.println(url);
		statusCode = obj.getStatusCode().toString();
		System.out.println(statusCode);
		Assert.assertNotEquals("404", statusCode);
		Assert.assertNotEquals("400", statusCode);
		Assert.assertNotEquals("500", statusCode);
		Assert.assertNotEquals("401", statusCode);
		
		
		
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();  
		body.add("employeeId", "11");
		headersToken = new HttpHeaders();
		headersToken.add("Authorization", "Bearer " + token);
		entity = new HttpEntity<Object>(body, headersToken);
		url+="/employees";
		obj = template.exchange(url, HttpMethod.POST, entity, String.class); 
		System.out.println(url);
		statusCode = obj.getStatusCode().toString();
		System.out.println(statusCode);
		Assert.assertNotEquals("404", statusCode);
		Assert.assertNotEquals("400", statusCode);
		Assert.assertNotEquals("500", statusCode);
		Assert.assertNotEquals("401", statusCode);
		
		headersToken.add("Accept", "application/json");
		headersToken.add("Content-Type", "application/json");
		entity = new HttpEntity<Object>("\"fromDate\": \"2015-06-10\", "
				+ "\"toDate\": \"2015-12-10\", "
				+ "\"percent\": 80, "
				+ "\"role\": \"ROLE_DESIGNER\"", headersToken);
		url=this.base + "data/projects/1/employees/1/effort";
		obj = template.exchange(url, HttpMethod.POST, entity, String.class); 
		System.out.println(url);
		statusCode = obj.getStatusCode().toString();
		System.out.println(statusCode);
		Assert.assertNotEquals("404", statusCode);
		Assert.assertNotEquals("400", statusCode);
		Assert.assertNotEquals("500", statusCode);
		Assert.assertNotEquals("401", statusCode);
		
		entity = new HttpEntity<Object>("\"fromDate\": \"2015-06-10\", "
				+ "\"toDate\": \"2015-12-15\", "
				+ "\"percent\": 90, "
				+ "\"role\": \"ROLE_DESIGNER\"", headersToken);
		url=this.base + "data/projects/effort/1";
		obj = template.exchange(url, HttpMethod.PUT, entity, String.class); 
		System.out.println(url);
		statusCode = obj.getStatusCode().toString();
		System.out.println(statusCode);
		Assert.assertNotEquals("404", statusCode);
		Assert.assertNotEquals("400", statusCode);
		Assert.assertNotEquals("500", statusCode);
		Assert.assertNotEquals("401", statusCode);
		
		
		url=this.base + "data/projects/1/employees/1";
		obj = template.exchange(url, HttpMethod.DELETE, entity, String.class); 
		System.out.println(url);
		statusCode = obj.getStatusCode().toString();
		System.out.println(statusCode);
		Assert.assertNotEquals("404", statusCode);
		Assert.assertNotEquals("400", statusCode);
		Assert.assertNotEquals("500", statusCode);
		Assert.assertNotEquals("401", statusCode);
		
		url=this.base + "data/projects/1";
		obj = template.exchange(url, HttpMethod.DELETE, entity, String.class); 
		System.out.println(url);
		statusCode = obj.getStatusCode().toString();
		System.out.println(statusCode);
		Assert.assertNotEquals("404", statusCode);
		Assert.assertNotEquals("400", statusCode);
		Assert.assertNotEquals("500", statusCode);
		Assert.assertNotEquals("401", statusCode);
	}
	
	@Test
	public void allGetMethods() throws RestClientException, URISyntaxException{
		
		for (String string : projectGetUrls) {
			String url = this.base + string;
			ResponseEntity<String> obj = template.exchange(url, HttpMethod.GET, this.entity, String.class);
			System.out.println(url);
			String statusCode = obj.getStatusCode().toString();
			System.out.println(statusCode);
			Assert.assertNotEquals("404", statusCode);
			Assert.assertNotEquals("400", statusCode);
			Assert.assertNotEquals("500", statusCode);
			Assert.assertNotEquals("401", statusCode);
		}
		
		for (String string : employeeGetUrls) {
			String url = this.base + string;
			ResponseEntity<String> obj = template.exchange(url, HttpMethod.GET, this.entity, String.class);
			System.out.println(url);
			String statusCode = obj.getStatusCode().toString();
			System.out.println(statusCode);
			Assert.assertNotEquals("404", statusCode);
			Assert.assertNotEquals("400", statusCode);
			Assert.assertNotEquals("500", statusCode);
			Assert.assertNotEquals("401", statusCode);
		}
		
		for (String string : effortGetUrls) {
			String url = this.base + string;
			ResponseEntity<String> obj = template.exchange(url, HttpMethod.GET, this.entity, String.class);
			System.out.println(url);
			String statusCode = obj.getStatusCode().toString();
			System.out.println(statusCode);
			Assert.assertNotEquals("404", statusCode);
			Assert.assertNotEquals("400", statusCode);
			Assert.assertNotEquals("500", statusCode);
			Assert.assertNotEquals("401", statusCode);
		}


	}
	
	@Test
	public void security() throws RestClientException, URISyntaxException{
		ResponseEntity<String> response = template.getForEntity(dataEmployees.toString(), String.class);
		System.out.println(response.getBody());
		assertThat(response.getBody(), equalTo("{\"error\":\"unauthorized\",\"error_description\":\"Full authentication is required to access this resource\"}"));
	
	}
	
}
