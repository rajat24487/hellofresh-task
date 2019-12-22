package com.hellofresh.challenge.apiclient;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Class is a wrapper for basic HTTP Functions calls like GET, PUT, DELETE,
 * library used is Simple JSON .
 * 
 * @author rajat.rastogi
 * 
 */
public class BaseRestClient {

	String protocol;
	String server;
	int port;
	String url;
	public long apiResponseTime;

	CloseableHttpClient client;

	/**
	 * Constructor
	 * 
	 * @param        protocol: https or https
	 * @param server : the dns or url to access server
	 */
	public BaseRestClient(String protocol, String server) {
		this.protocol = protocol;
		this.server = server;
		url = protocol + "://" + server;
		client = HttpClients.createDefault();
	}

	/**
	 * constructor to create
	 * 
	 * @param        protocol: https or https
	 * @param server : the dns or url to access server
	 * @param        port: port on which application is running
	 */
	public BaseRestClient(String protocol, String server, int port) {
		this.protocol = protocol;
		this.server = server;
		this.port = port;
		url = protocol + "://" + server + ":" + port;
		client = HttpClients.createDefault();
	}

	/**
	 * Function pertformGet handles http Get request
	 * 
	 * @param endPoint : takes uri end point as a string
	 * @return : response from GET request.
	 */
	public HttpResponse performGet(String endPoint) {
		HttpResponse httpResponse = null;
		StopWatch apiTime = new StopWatch();
		try {
			HttpGet getRequest = new HttpGet(url + "/" + endPoint);
			apiTime.start();
			httpResponse = client.execute(getRequest);
			apiTime.stop();
			apiResponseTime = apiTime.getTime();
			return httpResponse;
		} catch (Exception e) {
			System.out.println("Error while performing get request for endpoint:" + endPoint + " ::Error-->" + e);
			return httpResponse;
		}
	}

	/**
	 * Function performPost handles http POST request
	 * 
	 * @param endPoint : takes uri end point as a string
	 * @param          jsonString: takes post request payload in string format.
	 * @return : response from POST request.
	 */
	public HttpResponse performPost(String endPoint, String jsonString) {
		HttpResponse httpResponse = null;
		try {
			HttpPost postRequest = new HttpPost(url + "/" + endPoint);
			StringEntity stringEntity = new StringEntity(jsonString, ContentType.APPLICATION_JSON);
			postRequest.setEntity(stringEntity);
			httpResponse = client.execute(postRequest);
			return httpResponse;
		} catch (Exception e) {
			System.out.println("Error while performing Post request for endpoint:" + endPoint + " ::Error-->" + e);
			return httpResponse;
		}
	}

	/**
	 * Function pertformDelete handles http DELETE request
	 * 
	 * @param endPoint : takes uri end point as a string
	 * @return : response from DELETE request.
	 */
	public HttpResponse performDelete(String endPoint) {
		HttpResponse deleteResponse = null;
		try {
			HttpDelete deleteRequest = new HttpDelete(url + "/" + endPoint);
			deleteResponse = client.execute(deleteRequest);
			return deleteResponse;
		} catch (Exception e) {
			System.out.println("Error while performing Post request for endpoint:" + endPoint + " ::Error-->" + e);
			return deleteResponse;
		}
	}
}
