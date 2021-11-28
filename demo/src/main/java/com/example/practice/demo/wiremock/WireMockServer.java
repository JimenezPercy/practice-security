package com.example.practice.demo.wiremock;

import ch.qos.logback.core.util.FileUtil;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.tomcat.util.http.fileupload.FileUtils;

public class WireMockServer {
    public static void main(String[] args) {
        WireMock.configureFor(8082);
        WireMock.removeAllMappings();
        WireMock.stubFor(WireMock.post(WireMock.urlPathEqualTo("/order"))
                .willReturn(WireMock.aResponse().withBody("{\"id\":1111,\"test\":\"qweqwqweqw\"}")));
    }
}
