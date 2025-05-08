package com.cc.mcp.server;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cc.mcp.server.service.WeatherService;

@SpringBootApplication
public class McpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }

    /**
     * 注册工具
     *
     * @param weatherService 天气服务
     * @return 工具回调提供者
     */
    @Bean
    public ToolCallbackProvider weatherTools(WeatherService weatherService) {
        // 注册 Function calls !!
        // 可以看出，MCP Server 提供的时 Function calls 的功能 ！！
        return  MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
    }

}
